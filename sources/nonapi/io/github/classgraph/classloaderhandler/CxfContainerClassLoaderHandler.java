package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/CxfContainerClassLoaderHandler.class */
class CxfContainerClassLoaderHandler implements ClassLoaderHandler {
    private CxfContainerClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.apache.openejb.server.cxf.transport.util.CxfContainerClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        try {
            classLoaderOrder.delegateTo(Class.forName("org.apache.openejb.server.cxf.transport.util.CxfUtil").getClassLoader(), true, logNode);
        } catch (ClassNotFoundException | LinkageError unused) {
        }
        classLoaderOrder.delegateTo((ClassLoader) classLoaderOrder.reflectionUtils.invokeMethod(false, classLoader, "tccl"), false, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
    }
}
