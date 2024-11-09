package net.bytebuddy.utility;

import java.io.InputStream;
import java.lang.reflect.AnnotatedElement;
import java.security.AccessController;
import java.security.PrivilegedAction;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaModule.class */
public class JavaModule implements NamedElement.WithOptionalName, AnnotationSource {

    @AlwaysNull
    public static final JavaModule UNSUPPORTED;
    protected static final Resolver RESOLVER;
    protected static final Module MODULE;
    private final AnnotatedElement module;
    private static final boolean ACCESS_CONTROLLER;

    /* JADX INFO: Access modifiers changed from: protected */
    @JavaDispatcher.Proxied("java.lang.Module")
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaModule$Module.class */
    public interface Module {
        @JavaDispatcher.Instance
        @JavaDispatcher.Proxied("isInstance")
        boolean isInstance(Object obj);

        @JavaDispatcher.Proxied("isNamed")
        boolean isNamed(Object obj);

        @JavaDispatcher.Proxied("getName")
        String getName(Object obj);

        @MaybeNull
        @JavaDispatcher.Proxied("getClassLoader")
        ClassLoader getClassLoader(Object obj);

        @MaybeNull
        @JavaDispatcher.Proxied("getResourceAsStream")
        InputStream getResourceAsStream(Object obj, String str);

        @JavaDispatcher.Proxied("isExported")
        boolean isExported(Object obj, String str, @JavaDispatcher.Proxied("java.lang.Module") Object obj2);

        @JavaDispatcher.Proxied("isOpen")
        boolean isOpen(Object obj, String str, @JavaDispatcher.Proxied("java.lang.Module") Object obj2);

        @JavaDispatcher.Proxied("canRead")
        boolean canRead(Object obj, @JavaDispatcher.Proxied("java.lang.Module") Object obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @JavaDispatcher.Proxied("java.lang.Class")
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaModule$Resolver.class */
    public interface Resolver {
        @JavaDispatcher.Defaults
        @MaybeNull
        @JavaDispatcher.Proxied("getModule")
        Object getModule(Class<?> cls);
    }

    static {
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
        UNSUPPORTED = null;
        RESOLVER = (Resolver) doPrivileged(JavaDispatcher.of(Resolver.class));
        MODULE = (Module) doPrivileged(JavaDispatcher.of(Module.class));
    }

    protected JavaModule(AnnotatedElement annotatedElement) {
        this.module = annotatedElement;
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    @MaybeNull
    public static JavaModule ofType(Class<?> cls) {
        Object module = RESOLVER.getModule(cls);
        return module == null ? UNSUPPORTED : new JavaModule((AnnotatedElement) module);
    }

    public static JavaModule of(Object obj) {
        if (!MODULE.isInstance(obj)) {
            throw new IllegalArgumentException("Not a Java module: " + obj);
        }
        return new JavaModule((AnnotatedElement) obj);
    }

    public static boolean isSupported() {
        return ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V5).isAtLeast(ClassFileVersion.JAVA_V9);
    }

    @Override // net.bytebuddy.description.NamedElement.WithOptionalName
    public boolean isNamed() {
        return MODULE.isNamed(this.module);
    }

    @Override // net.bytebuddy.description.NamedElement
    public String getActualName() {
        return MODULE.getName(this.module);
    }

    @MaybeNull
    public InputStream getResourceAsStream(String str) {
        return MODULE.getResourceAsStream(this.module, str);
    }

    @MaybeNull
    public ClassLoader getClassLoader() {
        return MODULE.getClassLoader(this.module);
    }

    public Object unwrap() {
        return this.module;
    }

    public boolean canRead(JavaModule javaModule) {
        return MODULE.canRead(this.module, javaModule.unwrap());
    }

    public boolean isExported(@MaybeNull PackageDescription packageDescription, JavaModule javaModule) {
        return packageDescription == null || packageDescription.isDefault() || MODULE.isExported(this.module, packageDescription.getName(), javaModule.unwrap());
    }

    public boolean isOpened(@MaybeNull PackageDescription packageDescription, JavaModule javaModule) {
        return packageDescription == null || packageDescription.isDefault() || MODULE.isOpen(this.module, packageDescription.getName(), javaModule.unwrap());
    }

    @Override // net.bytebuddy.description.annotation.AnnotationSource
    public AnnotationList getDeclaredAnnotations() {
        return new AnnotationList.ForLoadedAnnotations(this.module.getDeclaredAnnotations());
    }

    public int hashCode() {
        return this.module.hashCode();
    }

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JavaModule)) {
            return false;
        }
        return this.module.equals(((JavaModule) obj).module);
    }

    public String toString() {
        return this.module.toString();
    }
}
