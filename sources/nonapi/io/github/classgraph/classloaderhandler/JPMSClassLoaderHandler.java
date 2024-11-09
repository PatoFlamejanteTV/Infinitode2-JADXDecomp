package nonapi.io.github.classgraph.classloaderhandler;

import java.net.URL;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/JPMSClassLoaderHandler.class */
class JPMSClassLoaderHandler implements ClassLoaderHandler {
    private JPMSClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "jdk.internal.loader.ClassLoaders$AppClassLoader".equals(cls.getName()) || "jdk.internal.loader.BuiltinClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Object fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "ucp");
        if (fieldVal != null) {
            classpathOrder.addClasspathEntryObject((URL[]) classpathOrder.reflectionUtils.invokeMethod(false, fieldVal, "getURLs"), classLoader, scanSpec, logNode);
        }
    }
}
