package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/FallbackClassLoaderHandler.class */
class FallbackClassLoaderHandler implements ClassLoaderHandler {
    private FallbackClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return true;
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        boolean addClasspathEntryObject = false | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getClassPath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getClasspath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "classpath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "classPath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "cp"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "classpath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "classPath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "cp"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getPath"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getPaths"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "path"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "paths"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "paths"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "paths"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getDir"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getDirs"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "dir"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "dirs"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "dir"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "dirs"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getFile"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getFiles"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "file"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "files"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "file"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "files"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getJar"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getJars"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "jar"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "jars"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "jar"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "jars"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getURL"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getURLs"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getUrl"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getUrls"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "url"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "urls"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "url"), classLoader, scanSpec, logNode) | classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "urls"), classLoader, scanSpec, logNode);
        if (logNode != null) {
            logNode.log("FallbackClassLoaderHandler " + (addClasspathEntryObject ? "found" : "did not find") + " classpath entries in unknown ClassLoader " + classLoader);
        }
    }
}