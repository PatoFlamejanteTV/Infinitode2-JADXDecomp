package nonapi.io.github.classgraph.classloaderhandler;

import java.util.Iterator;
import java.util.SortedSet;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/PlexusClassWorldsClassRealmClassLoaderHandler.class */
class PlexusClassWorldsClassRealmClassLoaderHandler implements ClassLoaderHandler {
    private PlexusClassWorldsClassRealmClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "org.codehaus.plexus.classworlds.realm.ClassRealm".equals(cls.getName());
    }

    private static boolean isParentFirstStrategy(ClassLoader classLoader, ReflectionUtils reflectionUtils) {
        Object fieldVal = reflectionUtils.getFieldVal(false, (Object) classLoader, "strategy");
        if (fieldVal != null) {
            String name = fieldVal.getClass().getName();
            if (name.equals("org.codehaus.plexus.classworlds.strategy.SelfFirstStrategy") || name.equals("org.codehaus.plexus.classworlds.strategy.OsgiBundleStrategy")) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        Object fieldVal = classLoaderOrder.reflectionUtils.getFieldVal(false, (Object) classLoader, "foreignImports");
        if (fieldVal != null) {
            Iterator it = ((SortedSet) fieldVal).iterator();
            while (it.hasNext()) {
                classLoaderOrder.delegateTo((ClassLoader) classLoaderOrder.reflectionUtils.invokeMethod(false, it.next(), "getClassLoader"), true, logNode);
            }
        }
        boolean isParentFirstStrategy = isParentFirstStrategy(classLoader, classLoaderOrder.reflectionUtils);
        if (!isParentFirstStrategy) {
            classLoaderOrder.add(classLoader, logNode);
        }
        classLoaderOrder.delegateTo((ClassLoader) classLoaderOrder.reflectionUtils.invokeMethod(false, classLoader, "getParentClassLoader"), true, logNode);
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        if (isParentFirstStrategy) {
            classLoaderOrder.add(classLoader, logNode);
        }
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        URLClassLoaderHandler.findClasspathOrder(classLoader, classpathOrder, scanSpec, logNode);
    }
}
