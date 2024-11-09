package nonapi.io.github.classgraph.classloaderhandler;

import java.net.URL;
import java.net.URLClassLoader;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/URLClassLoaderHandler.class */
class URLClassLoaderHandler implements ClassLoaderHandler {
    private URLClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "java.net.URLClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
        if (uRLs != null) {
            for (URL url : uRLs) {
                if (url != null) {
                    classpathOrder.addClasspathEntry(url, classLoader, scanSpec, logNode);
                }
            }
        }
    }
}
