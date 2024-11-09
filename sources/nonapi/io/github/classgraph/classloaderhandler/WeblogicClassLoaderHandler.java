package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/WeblogicClassLoaderHandler.class */
class WeblogicClassLoaderHandler implements ClassLoaderHandler {
    private WeblogicClassLoaderHandler() {
    }

    public static boolean canHandle(Class<?> cls, LogNode logNode) {
        return "weblogic.utils.classloaders.ChangeAwareClassLoader".equals(cls.getName()) || "weblogic.utils.classloaders.GenericClassLoader".equals(cls.getName()) || "weblogic.utils.classloaders.FilteringClassLoader".equals(cls.getName()) || "weblogic.servlet.jsp.JspClassLoader".equals(cls.getName()) || "weblogic.servlet.jsp.TagFileClassLoader".equals(cls.getName());
    }

    public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, logNode);
        classLoaderOrder.add(classLoader, logNode);
    }

    public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
        classpathOrder.addClasspathPathStr((String) classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getFinderClassPath"), classLoader, scanSpec, logNode);
        classpathOrder.addClasspathPathStr((String) classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getClassPath"), classLoader, scanSpec, logNode);
    }
}
