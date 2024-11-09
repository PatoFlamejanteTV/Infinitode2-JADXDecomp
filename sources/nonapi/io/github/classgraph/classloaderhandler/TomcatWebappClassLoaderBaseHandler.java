package nonapi.io.github.classgraph.classloaderhandler;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.implementation.MethodDelegation;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/TomcatWebappClassLoaderBaseHandler.class */
class TomcatWebappClassLoaderBaseHandler implements ClassLoaderHandler {
    private TomcatWebappClassLoaderBaseHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.apache.catalina.loader.WebappClassLoaderBase".equals(cls.getName());
    }

    private static boolean isParentFirst(ClassLoader classLoader, ReflectionUtils reflectionUtils) {
        Object fieldVal = reflectionUtils.getFieldVal(false, (Object) classLoader, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX);
        if (fieldVal != null) {
            return ((Boolean) fieldVal).booleanValue();
        }
        return true;
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        boolean isParentFirst = isParentFirst(classLoader, classLoaderOrder.reflectionUtils);
        if (isParentFirst) {
            classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        }
        if ("org.apache.tomee.catalina.TomEEWebappClassLoader".equals(classLoader.getClass().getName())) {
            try {
                classLoaderOrder.delegateTo(Class.forName("org.apache.openejb.OpenEJB").getClassLoader(), true, logNode);
            } catch (ClassNotFoundException | LinkageError unused) {
            }
        }
        classLoaderOrder.add(classLoader, logNode);
        if (!isParentFirst) {
            classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        }
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getResources");
        classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, invokeMethod, "getBaseUrls"), classLoader, scanSpec, logNode);
        List list = (List) classpathOrder.reflectionUtils.getFieldVal(false, invokeMethod, "allResources");
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                for (Object obj : (List) it.next()) {
                    if (obj != null) {
                        File file = (File) classpathOrder.reflectionUtils.invokeMethod(false, obj, "getFileBase");
                        String path = file == null ? null : file.getPath();
                        String str = path;
                        if (path == null) {
                            str = (String) classpathOrder.reflectionUtils.invokeMethod(false, obj, "getBase");
                        }
                        if (str == null) {
                            str = (String) classpathOrder.reflectionUtils.invokeMethod(false, obj, "getBaseUrlString");
                        }
                        if (str != null) {
                            String str2 = (String) classpathOrder.reflectionUtils.getFieldVal(false, obj, "archivePath");
                            if (str2 != null && !str2.isEmpty()) {
                                str = str + "!" + (str2.startsWith("/") ? str2 : "/" + str2);
                            }
                            String name = obj.getClass().getName();
                            boolean z = name.equals("java.org.apache.catalina.webresources.JarResourceSet") || name.equals("java.org.apache.catalina.webresources.JarWarResourceSet");
                            String str3 = (String) classpathOrder.reflectionUtils.invokeMethod(false, obj, "getInternalPath");
                            if (str3 != null && !str3.isEmpty() && !str3.equals("/")) {
                                classpathOrder.addClasspathEntryObject(str + (z ? "!" : "") + (str3.startsWith("/") ? str3 : "/" + str3), classLoader, scanSpec, logNode);
                            } else {
                                classpathOrder.addClasspathEntryObject(str, classLoader, scanSpec, logNode);
                            }
                        }
                    }
                }
            }
        }
        classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getURLs"), classLoader, scanSpec, logNode);
    }
}
