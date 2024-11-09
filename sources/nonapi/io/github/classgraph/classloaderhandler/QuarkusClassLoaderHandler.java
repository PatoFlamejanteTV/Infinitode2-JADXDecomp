package nonapi.io.github.classgraph.classloaderhandler;

import java.io.IOError;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/QuarkusClassLoaderHandler.class */
class QuarkusClassLoaderHandler implements ClassLoaderHandler {
    private static final String RUNTIME_CLASSLOADER = "io.quarkus.runner.RuntimeClassLoader";
    private static final String QUARKUS_CLASSLOADER = "io.quarkus.bootstrap.classloading.QuarkusClassLoader";
    private static final String RUNNER_CLASSLOADER = "io.quarkus.bootstrap.runner.RunnerClassLoader";

    private QuarkusClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return RUNTIME_CLASSLOADER.equals(cls.getName()) || QUARKUS_CLASSLOADER.equals(cls.getName()) || RUNNER_CLASSLOADER.equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        String name = classLoader.getClass().getName();
        if (RUNTIME_CLASSLOADER.equals(name)) {
            findClasspathOrderForRuntimeClassloader(classLoader, classpathOrder, scanSpec, logNode);
        } else if (QUARKUS_CLASSLOADER.equals(name)) {
            findClasspathOrderForQuarkusClassloader(classLoader, classpathOrder, scanSpec, logNode);
        } else if (RUNNER_CLASSLOADER.equals(name)) {
            findClasspathOrderForRunnerClassloader(classLoader, classpathOrder, scanSpec, logNode);
        }
    }

    private static void findClasspathOrderForQuarkusClassloader(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        for (Object obj : (Collection) classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "elements")) {
            String name = obj.getClass().getName();
            if ("io.quarkus.bootstrap.classloading.JarClassPathElement".equals(name)) {
                classpathOrder.addClasspathEntry(classpathOrder.reflectionUtils.getFieldVal(false, obj, "file"), classLoader, scanSpec, logNode);
            } else if (!"io.quarkus.bootstrap.classloading.DirectoryClassPathElement".equals(name)) {
                Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, obj, "getRoot");
                if (invokeMethod instanceof Path) {
                    classpathOrder.addClasspathEntry(invokeMethod, classLoader, scanSpec, logNode);
                }
            } else {
                classpathOrder.addClasspathEntry(classpathOrder.reflectionUtils.getFieldVal(false, obj, "root"), classLoader, scanSpec, logNode);
            }
        }
    }

    private static void findClasspathOrderForRuntimeClassloader(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Collection<Path> collection = (Collection) classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "applicationClassDirectories");
        if (collection != null) {
            for (Path path : collection) {
                try {
                    classpathOrder.addClasspathEntryObject(path.toUri(), classLoader, scanSpec, logNode);
                } catch (IOError | SecurityException unused) {
                    if (logNode != null) {
                        logNode.log("Could not convert path to URI: " + path);
                    }
                }
            }
        }
    }

    private static void findClasspathOrderForRunnerClassloader(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        for (Object[] objArr : ((Map) classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "resourceDirectoryMap")).values()) {
            for (Object obj : objArr) {
                if ("io.quarkus.bootstrap.runner.JarResource".equals(obj.getClass().getName())) {
                    classpathOrder.addClasspathEntry(classpathOrder.reflectionUtils.getFieldVal(false, obj, "jarPath"), classLoader, scanSpec, logNode);
                }
            }
        }
    }
}
