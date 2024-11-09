package nonapi.io.github.classgraph.classloaderhandler;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/JBossClassLoaderHandler.class */
class JBossClassLoaderHandler implements ClassLoaderHandler {
    private JBossClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.jboss.modules.ModuleClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    private static void handleResourceLoader(Object obj, ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        String str;
        File file;
        if (obj == null) {
            return;
        }
        Object fieldVal = classpathOrder.reflectionUtils.getFieldVal(false, obj, "root");
        File file2 = (File) classpathOrder.reflectionUtils.invokeMethod(false, fieldVal, "getPhysicalFile");
        if (file2 != null) {
            String str2 = (String) classpathOrder.reflectionUtils.invokeMethod(false, fieldVal, "getName");
            if (str2 != null) {
                File file3 = new File(file2.getParentFile(), str2);
                str = FileUtils.canRead(file3) ? file3.getAbsolutePath() : file2.getAbsolutePath();
            } else {
                str = file2.getAbsolutePath();
            }
        } else {
            String str3 = (String) classpathOrder.reflectionUtils.invokeMethod(false, fieldVal, "getPathName");
            str = str3;
            if (str3 == null) {
                File file4 = fieldVal instanceof Path ? ((Path) fieldVal).toFile() : fieldVal instanceof File ? (File) fieldVal : null;
                File file5 = file4;
                if (file4 != null) {
                    str = file5.getAbsolutePath();
                }
            }
        }
        if (str == null && (file = (File) classpathOrder.reflectionUtils.getFieldVal(false, obj, "fileOfJar")) != null) {
            str = file.getAbsolutePath();
        }
        if (str != null) {
            classpathOrder.addClasspathEntry(str, classLoader, scanSpec, logNode);
        } else if (logNode != null) {
            logNode.log("Could not determine classpath for ResourceLoader: " + obj);
        }
    }

    private static void handleRealModule(Object obj, Set<Object> set, ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        if (!set.add(obj)) {
            return;
        }
        ClassLoader classLoader2 = (ClassLoader) classpathOrder.reflectionUtils.invokeMethod(false, obj, "getClassLoader");
        ClassLoader classLoader3 = classLoader2;
        if (classLoader2 == null) {
            classLoader3 = classLoader;
        }
        Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, classLoader3, "getResourceLoaders");
        if (invokeMethod != null) {
            int length = Array.getLength(invokeMethod);
            for (int i = 0; i < length; i++) {
                handleResourceLoader(Array.get(invokeMethod, i), classLoader3, classpathOrder, scanSpec, logNode);
            }
        }
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        Set emptySet;
        Object invokeMethod = classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getModule");
        Object invokeMethod2 = classpathOrder.reflectionUtils.invokeMethod(false, invokeMethod, "getCallerModuleLoader");
        HashSet hashSet = new HashSet();
        Map map = (Map) classpathOrder.reflectionUtils.getFieldVal(false, invokeMethod2, "moduleMap");
        if (map != null) {
            emptySet = map.entrySet();
        } else {
            emptySet = Collections.emptySet();
        }
        Iterator it = emptySet.iterator();
        while (it.hasNext()) {
            handleRealModule(classpathOrder.reflectionUtils.invokeMethod(false, ((Map.Entry) it.next()).getValue(), "getModule"), hashSet, classLoader, classpathOrder, scanSpec, logNode);
        }
        Iterator it2 = ((Map) classpathOrder.reflectionUtils.invokeMethod(false, invokeMethod, "getPaths")).entrySet().iterator();
        while (it2.hasNext()) {
            Iterator it3 = ((List) ((Map.Entry) it2.next()).getValue()).iterator();
            while (it3.hasNext()) {
                handleRealModule(classpathOrder.reflectionUtils.getFieldVal(false, classpathOrder.reflectionUtils.getFieldVal(false, it3.next(), "this$0"), "module"), hashSet, classLoader, classpathOrder, scanSpec, logNode);
            }
        }
    }
}
