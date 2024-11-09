package nonapi.io.github.classgraph.classloaderhandler;

import io.github.classgraph.ClassGraphClassLoader;
import java.net.URL;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/ClassGraphClassLoaderHandler.class */
class ClassGraphClassLoaderHandler implements ClassLoaderHandler {
    private ClassGraphClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        boolean equals = "io.github.classgraph.ClassGraphClassLoader".equals(cls.getName());
        if (equals && logNode != null) {
            logNode.log("Sharing a `ClassGraphClassLoader` between multiple nested scans is not advisable, because scan criteria may differ between scans. See: https://github.com/classgraph/classgraph/issues/485");
        }
        return equals;
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        for (URL url : ((ClassGraphClassLoader) classLoader).getURLs()) {
            if (url != null) {
                classpathOrder.addClasspathEntry(url, classLoader, scanSpec, logNode);
            }
        }
    }
}
