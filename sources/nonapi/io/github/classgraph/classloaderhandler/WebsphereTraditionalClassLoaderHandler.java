package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/WebsphereTraditionalClassLoaderHandler.class */
class WebsphereTraditionalClassLoaderHandler implements ClassLoaderHandler {
    private WebsphereTraditionalClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "com.ibm.ws.classloader.CompoundClassLoader".equals(cls.getName()) || "com.ibm.ws.classloader.ProtectionClassLoader".equals(cls.getName()) || "com.ibm.ws.bootstrap.ExtClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        classpathOrder.addClasspathPathStr((String) classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getClassPath"), classLoader, scanSpec, logNode);
    }
}
