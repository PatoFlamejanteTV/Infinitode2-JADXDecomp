package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/ParentLastDelegationOrderTestClassLoaderHandler.class */
class ParentLastDelegationOrderTestClassLoaderHandler implements ClassLoaderHandler {
    private ParentLastDelegationOrderTestClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "io.github.classgraph.issues.issue267.FakeRestartClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.add(classLoader, logNode);
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        classpathOrder.addClasspathEntry((String) classpathOrder.reflectionUtils.invokeMethod(true, classLoader, "getClasspath"), classLoader, scanSpec, logNode);
    }
}
