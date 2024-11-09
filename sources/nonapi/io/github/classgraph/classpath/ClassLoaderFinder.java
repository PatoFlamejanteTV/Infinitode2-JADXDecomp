package nonapi.io.github.classgraph.classpath;

import java.util.Iterator;
import java.util.LinkedHashSet;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/ClassLoaderFinder.class */
public class ClassLoaderFinder {
    private final ClassLoader[] contextClassLoaders;

    public ClassLoader[] getContextClassLoaders() {
        return this.contextClassLoaders;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassLoaderFinder(ScanSpec scanSpec, ReflectionUtils reflectionUtils, LogNode logNode) {
        LinkedHashSet linkedHashSet;
        LogNode log;
        if (scanSpec.overrideClassLoaders == null) {
            linkedHashSet = new LinkedHashSet();
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                linkedHashSet.add(contextClassLoader);
            }
            ClassLoader classLoader = getClass().getClassLoader();
            if (classLoader != null) {
                linkedHashSet.add(classLoader);
            }
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (systemClassLoader != null) {
                linkedHashSet.add(systemClassLoader);
            }
            try {
                Class<?>[] classContext = new CallStackReader(reflectionUtils).getClassContext(logNode);
                for (int length = classContext.length - 1; length >= 0; length--) {
                    ClassLoader classLoader2 = classContext[length].getClassLoader();
                    if (classLoader2 != null) {
                        linkedHashSet.add(classLoader2);
                    }
                }
            } catch (IllegalArgumentException e) {
                if (logNode != null) {
                    logNode.log("Could not get call stack", e);
                }
            }
            if (scanSpec.addedClassLoaders != null) {
                linkedHashSet.addAll(scanSpec.addedClassLoaders);
            }
            log = logNode == null ? null : logNode.log("Found ClassLoaders:");
        } else {
            linkedHashSet = new LinkedHashSet(scanSpec.overrideClassLoaders);
            log = logNode == null ? null : logNode.log("Override ClassLoaders:");
        }
        if (log != null) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                log.log(((ClassLoader) it.next()).getClass().getName());
            }
        }
        this.contextClassLoaders = (ClassLoader[]) linkedHashSet.toArray(new ClassLoader[0]);
    }
}
