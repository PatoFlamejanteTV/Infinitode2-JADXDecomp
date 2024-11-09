package nonapi.io.github.classgraph.classloaderhandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/ClassLoaderHandlerRegistry.class */
public class ClassLoaderHandlerRegistry {
    public static final List<ClassLoaderHandlerRegistryEntry> CLASS_LOADER_HANDLERS = Collections.unmodifiableList(Arrays.asList(new ClassLoaderHandlerRegistryEntry(AntClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(EquinoxClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(EquinoxContextFinderClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(FelixClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(JBossClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(WeblogicClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(WebsphereLibertyClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(WebsphereTraditionalClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(OSGiDefaultClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(SpringBootRestartClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(TomcatWebappClassLoaderBaseHandler.class), new ClassLoaderHandlerRegistryEntry(CxfContainerClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(PlexusClassWorldsClassRealmClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(QuarkusClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(UnoOneJarClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(ParentLastDelegationOrderTestClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(JPMSClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(URLClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(ClassGraphClassLoaderHandler.class)));
    public static final ClassLoaderHandlerRegistryEntry FALLBACK_HANDLER = new ClassLoaderHandlerRegistryEntry(FallbackClassLoaderHandler.class);
    public static final String[] AUTOMATIC_LIB_DIR_PREFIXES = {"BOOT-INF/lib/", "WEB-INF/lib/", "WEB-INF/lib-provided/", "META-INF/lib/", "lib/", "lib/ext/", "main/"};
    public static final String[] AUTOMATIC_PACKAGE_ROOT_PREFIXES = {"classes/", "test-classes/", "BOOT-INF/classes/", "WEB-INF/classes/"};

    private ClassLoaderHandlerRegistry() {
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classloaderhandler/ClassLoaderHandlerRegistry$ClassLoaderHandlerRegistryEntry.class */
    public static class ClassLoaderHandlerRegistryEntry {
        private final Method canHandleMethod;
        private final Method findClassLoaderOrderMethod;
        private final Method findClasspathOrderMethod;
        public final Class<? extends ClassLoaderHandler> classLoaderHandlerClass;

        private ClassLoaderHandlerRegistryEntry(Class<? extends ClassLoaderHandler> cls) {
            this.classLoaderHandlerClass = cls;
            try {
                this.canHandleMethod = cls.getDeclaredMethod("canHandle", Class.class, LogNode.class);
                try {
                    this.findClassLoaderOrderMethod = cls.getDeclaredMethod("findClassLoaderOrder", ClassLoader.class, ClassLoaderOrder.class, LogNode.class);
                    try {
                        this.findClasspathOrderMethod = cls.getDeclaredMethod("findClasspathOrder", ClassLoader.class, ClasspathOrder.class, ScanSpec.class, LogNode.class);
                    } catch (Exception e) {
                        throw new RuntimeException("Could not find findClasspathOrder method for " + cls.getName(), e);
                    }
                } catch (Exception e2) {
                    throw new RuntimeException("Could not find findClassLoaderOrder method for " + cls.getName(), e2);
                }
            } catch (Exception e3) {
                throw new RuntimeException("Could not find canHandle method for " + cls.getName(), e3);
            }
        }

        public boolean canHandle(Class<?> cls, LogNode logNode) {
            try {
                return ((Boolean) this.canHandleMethod.invoke(null, cls, logNode)).booleanValue();
            } catch (Throwable th) {
                throw new RuntimeException("Exception while calling canHandle for " + this.classLoaderHandlerClass.getName(), th);
            }
        }

        public void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode logNode) {
            try {
                this.findClassLoaderOrderMethod.invoke(null, classLoader, classLoaderOrder, logNode);
            } catch (Throwable th) {
                throw new RuntimeException("Exception while calling findClassLoaderOrder for " + this.classLoaderHandlerClass.getName(), th);
            }
        }

        public void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode logNode) {
            try {
                this.findClasspathOrderMethod.invoke(null, classLoader, classpathOrder, scanSpec, logNode);
            } catch (Throwable th) {
                throw new RuntimeException("Exception while calling findClassLoaderOrder for " + this.classLoaderHandlerClass.getName(), th);
            }
        }
    }
}
