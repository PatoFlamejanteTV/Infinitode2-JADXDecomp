package nonapi.io.github.classgraph.classloaderhandler;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/FelixClassLoaderHandler.class */
class FelixClassLoaderHandler implements ClassLoaderHandler {
    private FelixClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.apache.felix.framework.BundleWiringImpl$BundleClassLoaderJava5".equals(cls.getName()) || "org.apache.felix.framework.BundleWiringImpl$BundleClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    private static File getContentLocation(Object obj, ReflectionUtils reflectionUtils) {
        return (File) reflectionUtils.invokeMethod(false, obj, "getFile");
    }

    private static void addBundle(Object obj, ClassLoader classLoader, ClasspathOrder classpathOrder, Set<Object> set, ScanSpec scanSpec, LogNode logNode) {
        set.add(obj);
        Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, obj, "getRevision");
        Object invokeMethod2 = classpathOrder.reflectionUtils.invokeMethod(false, invokeMethod, "getContent");
        File contentLocation = invokeMethod2 != null ? getContentLocation(invokeMethod2, classpathOrder.reflectionUtils) : null;
        File file = contentLocation;
        if (contentLocation != null) {
            classpathOrder.addClasspathEntry(file, classLoader, scanSpec, logNode);
            List list = (List) classpathOrder.reflectionUtils.invokeMethod(false, invokeMethod, "getContentPath");
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next != invokeMethod2) {
                        File contentLocation2 = next != null ? getContentLocation(next, classpathOrder.reflectionUtils) : null;
                        File file2 = contentLocation2;
                        if (contentLocation2 != null) {
                            classpathOrder.addClasspathEntry(file2, classLoader, scanSpec, logNode);
                        }
                    }
                }
            }
        }
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        HashSet hashSet = new HashSet();
        Object fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "m_wiring");
        addBundle(fieldVal, classLoader, classpathOrder, hashSet, scanSpec, logNode);
        List list = (List) classpathOrder.reflectionUtils.invokeMethod(false, fieldVal, "getRequiredWires", String.class, null);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, it.next(), "getProviderWiring");
                if (!hashSet.contains(invokeMethod)) {
                    addBundle(invokeMethod, classLoader, classpathOrder, hashSet, scanSpec, logNode);
                }
            }
        }
    }
}
