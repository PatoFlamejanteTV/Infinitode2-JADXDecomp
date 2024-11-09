package nonapi.io.github.classgraph.classpath;

import io.github.classgraph.ClassGraphClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/ClasspathFinder.class */
public class ClasspathFinder {
    private final ClasspathOrder classpathOrder;
    private final ModuleFinder moduleFinder;
    private ClassLoader[] classLoaderOrderRespectingParentDelegation;
    private ClassGraphClassLoader delegateClassGraphClassLoader;

    public ClasspathOrder getClasspathOrder() {
        return this.classpathOrder;
    }

    public ModuleFinder getModuleFinder() {
        return this.moduleFinder;
    }

    public ClassLoader[] getClassLoaderOrderRespectingParentDelegation() {
        return this.classLoaderOrderRespectingParentDelegation;
    }

    public ClassGraphClassLoader getDelegateClassGraphClassLoader() {
        return this.delegateClassGraphClassLoader;
    }

    public ClasspathFinder(ScanSpec scanSpec, ReflectionUtils reflectionUtils, LogNode logNode) {
        boolean z;
        ClassLoader[] classLoaderArr;
        LogNode log = logNode == null ? null : logNode.log("Finding classpath and modules");
        boolean z2 = false;
        if (scanSpec.overrideClasspath != null) {
            z = false;
        } else if (scanSpec.overrideClassLoaders != null) {
            z = false;
            Iterator<ClassLoader> it = scanSpec.overrideClassLoaders.iterator();
            while (it.hasNext()) {
                String name = it.next().getClass().getName();
                if (!scanSpec.enableSystemJarsAndModules && name.equals("jdk.internal.loader.ClassLoaders$PlatformClassLoader")) {
                    if (log != null) {
                        log.log("overrideClassLoaders() was called with an instance of " + name + ", so enableSystemJarsAndModules() was called automatically");
                    }
                    scanSpec.enableSystemJarsAndModules = true;
                }
                if (name.equals("jdk.internal.loader.ClassLoaders$AppClassLoader") || name.equals("jdk.internal.loader.ClassLoaders$PlatformClassLoader")) {
                    if (log != null) {
                        log.log("overrideClassLoaders() was called with an instance of " + name + ", so the `java.class.path` classpath will also be scanned");
                    }
                    z2 = true;
                }
            }
        } else {
            z = scanSpec.scanModules;
        }
        this.moduleFinder = (z || scanSpec.enableSystemJarsAndModules) ? new ModuleFinder(new CallStackReader(reflectionUtils).getClassContext(log), scanSpec, z, scanSpec.enableSystemJarsAndModules, reflectionUtils, log) : null;
        this.classpathOrder = new ClasspathOrder(scanSpec, reflectionUtils);
        ClassLoaderFinder classLoaderFinder = (scanSpec.overrideClasspath == null && scanSpec.overrideClassLoaders == null) ? new ClassLoaderFinder(scanSpec, reflectionUtils, log) : null;
        ClassLoader[] contextClassLoaders = classLoaderFinder == null ? new ClassLoader[0] : classLoaderFinder.getContextClassLoaders();
        ClassLoader[] classLoaderArr2 = contextClassLoaders;
        ClassLoader classLoader = contextClassLoaders.length > 0 ? classLoaderArr2[0] : null;
        if (scanSpec.overrideClasspath != null) {
            if (scanSpec.overrideClassLoaders != null && log != null) {
                log.log("It is not possible to override both the classpath and the ClassLoaders -- ignoring the ClassLoader override");
            }
            LogNode log2 = log == null ? null : log.log("Overriding classpath with: " + scanSpec.overrideClasspath);
            this.classpathOrder.addClasspathEntries(scanSpec.overrideClasspath, classLoader, scanSpec, log2);
            if (log2 != null) {
                log2.log("WARNING: when the classpath is overridden, there is no guarantee that the classes found by classpath scanning will be the same as the classes loaded by the context classloader");
            }
            this.classLoaderOrderRespectingParentDelegation = classLoaderArr2;
        }
        if (scanSpec.enableSystemJarsAndModules) {
            String jreRtJarPath = SystemJarFinder.getJreRtJarPath();
            LogNode log3 = log == null ? null : log.log("System jars:");
            if (jreRtJarPath != null) {
                if (scanSpec.enableSystemJarsAndModules) {
                    this.classpathOrder.addSystemClasspathEntry(jreRtJarPath, classLoader);
                    if (log3 != null) {
                        log3.log("Found rt.jar: " + jreRtJarPath);
                    }
                } else if (log3 != null) {
                    log3.log((scanSpec.enableSystemJarsAndModules ? "" : "Scanning disabled for rt.jar: ") + jreRtJarPath);
                }
            }
            boolean z3 = !scanSpec.libOrExtJarAcceptReject.acceptAndRejectAreEmpty();
            for (String str : SystemJarFinder.getJreLibOrExtJars()) {
                if (z3 || scanSpec.libOrExtJarAcceptReject.isSpecificallyAcceptedAndNotRejected(str)) {
                    this.classpathOrder.addSystemClasspathEntry(str, classLoader);
                    if (log3 != null) {
                        log3.log("Found lib or ext jar: " + str);
                    }
                } else if (log3 != null) {
                    log3.log("Scanning disabled for lib or ext jar: " + str);
                }
            }
        }
        if (scanSpec.overrideClasspath == null) {
            if (log != null) {
                LogNode log4 = log.log("ClassLoaderHandlers:");
                Iterator<ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry> it2 = ClassLoaderHandlerRegistry.CLASS_LOADER_HANDLERS.iterator();
                while (it2.hasNext()) {
                    log4.log(it2.next().classLoaderHandlerClass.getName());
                }
            }
            LogNode log5 = log == null ? null : log.log("Finding unique classloaders in delegation order");
            ClassLoaderOrder classLoaderOrder = new ClassLoaderOrder(reflectionUtils);
            if (scanSpec.overrideClassLoaders == null) {
                classLoaderArr = classLoaderArr2;
            } else {
                classLoaderArr = (ClassLoader[]) scanSpec.overrideClassLoaders.toArray(new ClassLoader[0]);
            }
            ClassLoader[] classLoaderArr3 = classLoaderArr;
            if (classLoaderArr != null) {
                for (ClassLoader classLoader2 : classLoaderArr3) {
                    classLoaderOrder.delegateTo(classLoader2, false, log5);
                }
            }
            Set<ClassLoader> allParentClassLoaders = classLoaderOrder.getAllParentClassLoaders();
            LogNode log6 = log == null ? null : log.log("Obtaining URLs from classloaders in delegation order");
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry> entry : classLoaderOrder.getClassLoaderOrder()) {
                ClassLoader key = entry.getKey();
                ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry value = entry.getValue();
                if (!scanSpec.ignoreParentClassLoaders || !allParentClassLoaders.contains(key)) {
                    value.findClasspathOrder(key, this.classpathOrder, scanSpec, log6 == null ? null : log6.log("Classloader " + key.getClass().getName() + " is handled by " + value.classLoaderHandlerClass.getName()));
                    arrayList.add(key);
                } else if (log6 != null) {
                    log6.log("Ignoring parent classloader " + key + ", normally handled by " + value.classLoaderHandlerClass.getName());
                }
                if (key instanceof ClassGraphClassLoader) {
                    this.delegateClassGraphClassLoader = (ClassGraphClassLoader) key;
                }
            }
            this.classLoaderOrderRespectingParentDelegation = (ClassLoader[]) arrayList.toArray(new ClassLoader[0]);
        }
        if (z2 || ((!scanSpec.ignoreParentClassLoaders && scanSpec.overrideClassLoaders == null && scanSpec.overrideClasspath == null) || (this.moduleFinder != null && this.moduleFinder.forceScanJavaClassPath()))) {
            String[] smartPathSplit = JarUtils.smartPathSplit(System.getProperty("java.class.path"), scanSpec);
            if (smartPathSplit.length > 0) {
                LogNode log7 = log == null ? null : log.log("Getting classpath entries from java.class.path");
                for (String str2 : smartPathSplit) {
                    this.classpathOrder.addClasspathEntry(FastPathResolver.resolve(FileUtils.currDirPath(), str2), classLoader, scanSpec, log7);
                }
            }
        }
    }
}
