package nonapi.io.github.classgraph.classloaderhandler;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;
import net.bytebuddy.implementation.MethodDelegation;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/EquinoxClassLoaderHandler.class */
class EquinoxClassLoaderHandler implements ClassLoaderHandler {
    private static boolean alreadyReadSystemBundles;
    private static final String[] FIELD_NAMES = {"cp", "nestedDirName"};

    private EquinoxClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.eclipse.osgi.internal.loader.EquinoxClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    private static void addBundleFile(Object obj, Set<Object> set, ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Object fieldVal;
        if (obj != null && set.add(obj)) {
            Object fieldVal2 = classpathOrder.reflectionUtils.getFieldVal(false, obj, "basefile");
            if (fieldVal2 != null) {
                boolean z = false;
                String[] strArr = FIELD_NAMES;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Object fieldVal3 = classpathOrder.reflectionUtils.getFieldVal(false, obj, strArr[i]);
                    if (fieldVal3 == null) {
                        i++;
                    } else {
                        z = true;
                        Object obj2 = fieldVal2;
                        String str = "/";
                        if (obj.getClass().getName().equals("org.eclipse.osgi.storage.bundlefile.NestedDirBundleFile") && (fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, obj, "baseBundleFile")) != null && fieldVal.getClass().getName().equals("org.eclipse.osgi.storage.bundlefile.ZipBundleFile")) {
                            obj2 = fieldVal;
                            str = "!/";
                        }
                        classpathOrder.addClasspathEntry(obj2 + str + fieldVal3, classLoader, scanSpec, logNode);
                    }
                }
                if (!z) {
                    classpathOrder.addClasspathEntry(fieldVal2.toString(), classLoader, scanSpec, logNode);
                }
            }
            addBundleFile(classpathOrder.reflectionUtils.getFieldVal(false, obj, "wrapped"), set, classLoader, classpathOrder, scanSpec, logNode);
            addBundleFile(classpathOrder.reflectionUtils.getFieldVal(false, obj, "next"), set, classLoader, classpathOrder, scanSpec, logNode);
        }
    }

    private static void addClasspathEntries(Object obj, ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Object fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, obj, "entries");
        if (fieldVal != null) {
            int length = Array.getLength(fieldVal);
            for (int i = 0; i < length; i++) {
                addBundleFile(classpathOrder.reflectionUtils.getFieldVal(false, Array.get(fieldVal, i), "bundlefile"), new HashSet(), classLoader, classpathOrder, scanSpec, logNode);
            }
        }
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        int indexOf;
        Object fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "manager");
        addClasspathEntries(fieldVal, classLoader, classpathOrder, scanSpec, logNode);
        Object fieldVal2 = classpathOrder.reflectionUtils.getFieldVal(false, fieldVal, "fragments");
        if (fieldVal2 != null) {
            int length = Array.getLength(fieldVal2);
            for (int i = 0; i < length; i++) {
                addClasspathEntries(Array.get(fieldVal2, i), classLoader, classpathOrder, scanSpec, logNode);
            }
        }
        if (!alreadyReadSystemBundles) {
            Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, classpathOrder.reflectionUtils.invokeMethod(false, classpathOrder.reflectionUtils.invokeMethod(false, classpathOrder.reflectionUtils.invokeMethod(false, classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX), "container"), "storage"), "moduleContainer"), "moduleDatabase"), "modulesById"), "get", Object.class, 0L), "getBundle"), "getBundleContext"), "getBundles");
            if (invokeMethod != null) {
                int length2 = Array.getLength(invokeMethod);
                for (int i2 = 0; i2 < length2; i2++) {
                    String str = (String) classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, Array.get(invokeMethod, i2), "module"), "location");
                    if (str != null && (indexOf = str.indexOf("file:")) >= 0) {
                        classpathOrder.addClasspathEntry(str.substring(indexOf), classLoader, scanSpec, logNode);
                    }
                }
            }
            alreadyReadSystemBundles = true;
        }
    }
}
