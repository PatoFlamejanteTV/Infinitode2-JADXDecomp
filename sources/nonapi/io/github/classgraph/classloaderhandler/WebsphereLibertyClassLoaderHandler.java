package nonapi.io.github.classgraph.classloaderhandler;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.implementation.MethodDelegation;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/WebsphereLibertyClassLoaderHandler.class */
class WebsphereLibertyClassLoaderHandler implements ClassLoaderHandler {
    private static final String PKG_PREFIX = "com.ibm.ws.classloading.internal.";
    private static final String IBM_APP_CLASS_LOADER = "com.ibm.ws.classloading.internal.AppClassLoader";
    private static final String IBM_THREAD_CONTEXT_CLASS_LOADER = "com.ibm.ws.classloading.internal.ThreadContextClassLoader";

    private WebsphereLibertyClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return IBM_APP_CLASS_LOADER.equals(cls.getName()) || IBM_THREAD_CONTEXT_CLASS_LOADER.equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    private static Collection<Object> getPaths(Object obj, ReflectionUtils reflectionUtils) {
        if (obj == null) {
            return Collections.emptyList();
        }
        Collection<Object> callGetUrls = callGetUrls(obj, "getContainerURLs", reflectionUtils);
        if (callGetUrls != null && !callGetUrls.isEmpty()) {
            return callGetUrls;
        }
        Object fieldVal = reflectionUtils.getFieldVal(false, obj, "container");
        if (fieldVal == null) {
            return Collections.emptyList();
        }
        Collection<Object> callGetUrls2 = callGetUrls(fieldVal, "getURLs", reflectionUtils);
        if (callGetUrls2 != null && !callGetUrls2.isEmpty()) {
            return callGetUrls2;
        }
        Object fieldVal2 = reflectionUtils.getFieldVal(false, fieldVal, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX);
        if (fieldVal2 == null) {
            return Collections.emptyList();
        }
        String str = (String) reflectionUtils.getFieldVal(false, fieldVal2, "path");
        if (str != null && str.length() > 0) {
            return Collections.singletonList(str);
        }
        Object fieldVal3 = reflectionUtils.getFieldVal(false, fieldVal2, "base");
        if (fieldVal3 == null) {
            return Collections.emptyList();
        }
        Object fieldVal4 = reflectionUtils.getFieldVal(false, fieldVal3, "archiveFile");
        if (fieldVal4 != null) {
            return Collections.singletonList(((File) fieldVal4).getAbsolutePath());
        }
        return Collections.emptyList();
    }

    private static Collection<Object> callGetUrls(Object obj, String str, ReflectionUtils reflectionUtils) {
        if (obj != null) {
            try {
                Collection collection = (Collection) reflectionUtils.invokeMethod(false, obj, str);
                if (collection != null && !collection.isEmpty()) {
                    HashSet hashSet = new HashSet();
                    for (Object obj2 : collection) {
                        if (obj2 instanceof Collection) {
                            for (Object obj3 : (Collection) obj2) {
                                if (obj3 != null) {
                                    hashSet.add(obj3);
                                }
                            }
                        } else if (obj2 != null) {
                            hashSet.add(obj2);
                        }
                    }
                    return hashSet;
                }
            } catch (UnsupportedOperationException unused) {
            }
        }
        return Collections.emptyList();
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Object fieldVal;
        Object fieldVal2 = classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "appLoader");
        if (fieldVal2 != null) {
            fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, fieldVal2, "smartClassPath");
        } else {
            fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "smartClassPath");
        }
        if (fieldVal != null) {
            Collection<Object> callGetUrls = callGetUrls(fieldVal, "getClassPath", classpathOrder.reflectionUtils);
            if (!callGetUrls.isEmpty()) {
                Iterator<Object> it = callGetUrls.iterator();
                while (it.hasNext()) {
                    classpathOrder.addClasspathEntry(it.next(), classLoader, scanSpec, logNode);
                }
                return;
            }
            List list = (List) classpathOrder.reflectionUtils.getFieldVal(false, fieldVal, "classPath");
            if (list != null && !list.isEmpty()) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    Iterator<Object> it3 = getPaths(it2.next(), classpathOrder.reflectionUtils).iterator();
                    while (it3.hasNext()) {
                        classpathOrder.addClasspathEntry(it3.next(), classLoader, scanSpec, logNode);
                    }
                }
            }
        }
    }
}
