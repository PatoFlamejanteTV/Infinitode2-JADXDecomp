package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/UnoOneJarClassLoaderHandler.class */
class UnoOneJarClassLoaderHandler implements ClassLoaderHandler {
    private UnoOneJarClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "com.needhamsoftware.unojar.JarClassLoader".equals(cls.getName()) || "com.simontuffs.onejar.JarClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        classpathOrder.addClasspathEntry((String) classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getOneJarPath"), classLoader, scanSpec, logNode);
        classpathOrder.addClasspathEntry(System.getProperty("uno-jar.jar.path"), classLoader, scanSpec, logNode);
        classpathOrder.addClasspathEntry(System.getProperty("one-jar.jar.path"), classLoader, scanSpec, logNode);
        String property = System.getProperty("one-jar.class.path");
        if (property != null) {
            classpathOrder.addClasspathEntryObject(property.split("\\|"), classLoader, scanSpec, logNode);
        }
    }
}
