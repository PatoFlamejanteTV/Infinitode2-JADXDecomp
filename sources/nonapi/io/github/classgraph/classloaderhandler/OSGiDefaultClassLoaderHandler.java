package nonapi.io.github.classgraph.classloaderhandler;

import java.io.File;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/OSGiDefaultClassLoaderHandler.class */
class OSGiDefaultClassLoaderHandler implements ClassLoaderHandler {
    private OSGiDefaultClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Object[] objArr = (Object[]) classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getClasspathManager"), "entries");
        if (objArr != null) {
            for (Object obj : objArr) {
                File file = (File) classpathOrder.reflectionUtils.invokeMethod(false, classpathOrder.reflectionUtils.invokeMethod(false, obj, "getBundleFile"), "getBaseFile");
                if (file != null) {
                    classpathOrder.addClasspathEntry(file.getPath(), classLoader, scanSpec, logNode);
                }
            }
        }
    }
}
