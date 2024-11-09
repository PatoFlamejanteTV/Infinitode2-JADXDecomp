package net.bytebuddy.dynamic.loading;

import com.sun.jna.FunctionMapper;
import com.sun.jna.JNIEnv;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.net.URL;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.MemberRemoval;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.GraalImageCode;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.nullability.UnknownNull;
import net.bytebuddy.utility.privilege.GetMethodAction;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector.class */
public interface ClassInjector {
    public static final Permission SUPPRESS_ACCESS_CHECKS = new ReflectPermission("suppressAccessChecks");
    public static final boolean ALLOW_EXISTING_TYPES = false;

    boolean isAlive();

    Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map);

    Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$AbstractBase.class */
    public static abstract class AbstractBase implements ClassInjector {
        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<? extends TypeDescription, byte[]> entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey().getName(), entry.getValue());
            }
            Map<String, Class<?>> injectRaw = injectRaw(linkedHashMap);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (TypeDescription typeDescription : map.keySet()) {
                linkedHashMap2.put(typeDescription, injectRaw.get(typeDescription.getName()));
            }
            return linkedHashMap2;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection.class */
    public static class UsingReflection extends AbstractBase {
        private static final Dispatcher.Initializable DISPATCHER;
        private static final System SYSTEM;
        private static final Method CHECK_PERMISSION;
        private final ClassLoader classLoader;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ProtectionDomain protectionDomain;
        private final PackageDefinitionStrategy packageDefinitionStrategy;
        private final boolean forbidExisting;
        private static final boolean ACCESS_CONTROLLER;

        @JavaDispatcher.Proxied("java.lang.System")
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$System.class */
        protected interface System {
            @JavaDispatcher.IsStatic
            @JavaDispatcher.Defaults
            @MaybeNull
            @JavaDispatcher.Proxied("getSecurityManager")
            Object getSecurityManager();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || this.forbidExisting != ((UsingReflection) obj).forbidExisting || !this.classLoader.equals(((UsingReflection) obj).classLoader)) {
                return false;
            }
            ProtectionDomain protectionDomain = this.protectionDomain;
            ProtectionDomain protectionDomain2 = ((UsingReflection) obj).protectionDomain;
            if (protectionDomain2 != null) {
                if (protectionDomain == null || !protectionDomain.equals(protectionDomain2)) {
                    return false;
                }
            } else if (protectionDomain != null) {
                return false;
            }
            return this.packageDefinitionStrategy.equals(((UsingReflection) obj).packageDefinitionStrategy);
        }

        public int hashCode() {
            int hashCode = ((getClass().hashCode() * 31) + this.classLoader.hashCode()) * 31;
            ProtectionDomain protectionDomain = this.protectionDomain;
            if (protectionDomain != null) {
                hashCode += protectionDomain.hashCode();
            }
            return (((hashCode * 31) + this.packageDefinitionStrategy.hashCode()) * 31) + (this.forbidExisting ? 1 : 0);
        }

        static {
            try {
                Class.forName("java.security.AccessController", false, null);
                ACCESS_CONTROLLER = Boolean.parseBoolean(java.lang.System.getProperty("net.bytebuddy.securitymanager", "true"));
            } catch (ClassNotFoundException unused) {
                ACCESS_CONTROLLER = false;
            } catch (SecurityException unused2) {
                ACCESS_CONTROLLER = true;
            }
            DISPATCHER = (Dispatcher.Initializable) doPrivileged(Dispatcher.CreationAction.INSTANCE);
            SYSTEM = (System) doPrivileged(JavaDispatcher.of(System.class));
            CHECK_PERMISSION = (Method) doPrivileged(new GetMethodAction("java.lang.SecurityManager", "checkPermission", Permission.class));
        }

        public UsingReflection(ClassLoader classLoader) {
            this(classLoader, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingReflection(ClassLoader classLoader, @MaybeNull ProtectionDomain protectionDomain) {
            this(classLoader, protectionDomain, PackageDefinitionStrategy.Trivial.INSTANCE, false);
        }

        public UsingReflection(ClassLoader classLoader, @MaybeNull ProtectionDomain protectionDomain, PackageDefinitionStrategy packageDefinitionStrategy, boolean z) {
            if (classLoader == null) {
                throw new IllegalArgumentException("Cannot inject classes into the bootstrap class loader");
            }
            this.classLoader = classLoader;
            this.protectionDomain = protectionDomain;
            this.packageDefinitionStrategy = packageDefinitionStrategy;
            this.forbidExisting = z;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public boolean isAlive() {
            return isAvailable();
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            Dispatcher initialize = DISPATCHER.initialize();
            HashMap hashMap = new HashMap();
            for (Map.Entry<? extends String, byte[]> entry : map.entrySet()) {
                synchronized (initialize.getClassLoadingLock(this.classLoader, entry.getKey())) {
                    Class<?> findClass = initialize.findClass(this.classLoader, entry.getKey());
                    Class<?> cls = findClass;
                    if (findClass == null) {
                        int lastIndexOf = entry.getKey().lastIndexOf(46);
                        if (lastIndexOf != -1) {
                            String substring = entry.getKey().substring(0, lastIndexOf);
                            PackageDefinitionStrategy.Definition define = this.packageDefinitionStrategy.define(this.classLoader, substring, entry.getKey());
                            if (define.isDefined()) {
                                Package definedPackage = initialize.getDefinedPackage(this.classLoader, substring);
                                if (definedPackage == null) {
                                    try {
                                        initialize.definePackage(this.classLoader, substring, define.getSpecificationTitle(), define.getSpecificationVersion(), define.getSpecificationVendor(), define.getImplementationTitle(), define.getImplementationVersion(), define.getImplementationVendor(), define.getSealBase());
                                    } catch (IllegalStateException e) {
                                        Package r0 = initialize.getPackage(this.classLoader, substring);
                                        if (r0 == null) {
                                            throw e;
                                        }
                                        if (!define.isCompatibleTo(r0)) {
                                            throw new SecurityException("Sealing violation for package " + substring + " (getPackage fallback)");
                                        }
                                    }
                                } else if (!define.isCompatibleTo(definedPackage)) {
                                    throw new SecurityException("Sealing violation for package " + substring);
                                }
                            }
                        }
                        cls = initialize.defineClass(this.classLoader, entry.getKey(), entry.getValue(), this.protectionDomain);
                    } else if (this.forbidExisting) {
                        throw new IllegalStateException("Cannot inject already loaded type: " + cls);
                    }
                    hashMap.put(entry.getKey(), cls);
                }
            }
            return hashMap;
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofSystemClassLoader() {
            return new UsingReflection(ClassLoader.getSystemClassLoader());
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher.class */
        protected interface Dispatcher {

            @AlwaysNull
            public static final Class<?> UNDEFINED = null;

            Object getClassLoadingLock(ClassLoader classLoader, String str);

            @MaybeNull
            Class<?> findClass(ClassLoader classLoader, String str);

            Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain);

            @MaybeNull
            Package getDefinedPackage(ClassLoader classLoader, String str);

            @MaybeNull
            Package getPackage(ClassLoader classLoader, String str);

            Package definePackage(ClassLoader classLoader, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull String str7, @MaybeNull URL url);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$Initializable.class */
            public interface Initializable {
                boolean isAvailable();

                Dispatcher initialize();

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$Initializable$Unavailable.class */
                public static class Unavailable implements Dispatcher, Initializable {
                    private final String message;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.message.hashCode();
                    }

                    protected Unavailable(String str) {
                        this.message = str;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                    public boolean isAvailable() {
                        return false;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                    public Dispatcher initialize() {
                        return this;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Class<?> findClass(ClassLoader classLoader, String str) {
                        try {
                            return classLoader.loadClass(str);
                        } catch (ClassNotFoundException unused) {
                            return UNDEFINED;
                        }
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                        throw new UnsupportedOperationException("Cannot define class using reflection: " + this.message);
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Package getDefinedPackage(ClassLoader classLoader, String str) {
                        throw new UnsupportedOperationException("Cannot get defined package using reflection: " + this.message);
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Package getPackage(ClassLoader classLoader, String str) {
                        throw new UnsupportedOperationException("Cannot get package using reflection: " + this.message);
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Package definePackage(ClassLoader classLoader, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull String str7, @MaybeNull URL url) {
                        throw new UnsupportedOperationException("Cannot define package using injection: " + this.message);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$CreationAction.class */
            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
                public final Initializable run() {
                    try {
                        if (JavaModule.isSupported()) {
                            if (UsingUnsafe.isAvailable()) {
                                return UsingUnsafeInjection.make();
                            }
                            return UsingUnsafeOverride.make();
                        }
                        return Direct.make();
                    } catch (InvocationTargetException e) {
                        return new Initializable.Unavailable(e.getTargetException().getMessage());
                    } catch (Exception e2) {
                        return new Initializable.Unavailable(e2.getMessage());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$Direct.class */
            public static abstract class Direct implements Dispatcher, Initializable {
                protected final Method findLoadedClass;
                protected final Method defineClass;

                @UnknownNull
                protected final Method getDefinedPackage;
                protected final Method getPackage;
                protected final Method definePackage;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.findLoadedClass.equals(((Direct) obj).findLoadedClass) && this.defineClass.equals(((Direct) obj).defineClass) && this.getDefinedPackage.equals(((Direct) obj).getDefinedPackage) && this.getPackage.equals(((Direct) obj).getPackage) && this.definePackage.equals(((Direct) obj).definePackage);
                }

                public int hashCode() {
                    return (((((((((getClass().hashCode() * 31) + this.findLoadedClass.hashCode()) * 31) + this.defineClass.hashCode()) * 31) + this.getDefinedPackage.hashCode()) * 31) + this.getPackage.hashCode()) * 31) + this.definePackage.hashCode();
                }

                protected Direct(Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5) {
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getDefinedPackage = method3;
                    this.getPackage = method4;
                    this.definePackage = method5;
                }

                @SuppressFBWarnings(value = {"DP_DO_INSIDE_DO_PRIVILEGED"}, justification = "Assuring privilege is explicit user responsibility.")
                protected static Initializable make() {
                    Method method;
                    Method declaredMethod;
                    Method declaredMethod2;
                    Method declaredMethod3;
                    Method declaredMethod4;
                    try {
                        if (JavaModule.isSupported()) {
                            try {
                                method = ClassLoader.class.getMethod("getDefinedPackage", String.class);
                            } catch (NoSuchMethodException unused) {
                            }
                            declaredMethod = ClassLoader.class.getDeclaredMethod("getPackage", String.class);
                            declaredMethod.setAccessible(true);
                            declaredMethod2 = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
                            declaredMethod2.setAccessible(true);
                            declaredMethod3 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                            declaredMethod3.setAccessible(true);
                            declaredMethod4 = ClassLoader.class.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class);
                            declaredMethod4.setAccessible(true);
                            Method declaredMethod5 = ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class);
                            declaredMethod5.setAccessible(true);
                            return new ForJava7CapableVm(declaredMethod2, declaredMethod3, method, declaredMethod, declaredMethod4, declaredMethod5);
                        }
                        Method declaredMethod52 = ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class);
                        declaredMethod52.setAccessible(true);
                        return new ForJava7CapableVm(declaredMethod2, declaredMethod3, method, declaredMethod, declaredMethod4, declaredMethod52);
                    } catch (NoSuchMethodException unused2) {
                        return new ForLegacyVm(declaredMethod2, declaredMethod3, method, declaredMethod, declaredMethod4);
                    }
                    method = null;
                    declaredMethod = ClassLoader.class.getDeclaredMethod("getPackage", String.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod2 = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
                    declaredMethod2.setAccessible(true);
                    declaredMethod3 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    declaredMethod3.setAccessible(true);
                    declaredMethod4 = ClassLoader.class.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class);
                    declaredMethod4.setAccessible(true);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public Dispatcher initialize() {
                    Object securityManager = UsingReflection.SYSTEM.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            UsingReflection.CHECK_PERMISSION.invoke(securityManager, ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (InvocationTargetException e) {
                            return new Unavailable(e.getTargetException().getMessage());
                        } catch (Exception e2) {
                            return new Unavailable(e2.getMessage());
                        }
                    }
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(classLoader, str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                @MaybeNull
                public Package getDefinedPackage(ClassLoader classLoader, String str) {
                    if (this.getDefinedPackage == null) {
                        return getPackage(classLoader, str);
                    }
                    try {
                        return (Package) this.getDefinedPackage.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull String str7, @MaybeNull URL url) {
                    try {
                        return (Package) this.definePackage.invoke(classLoader, str, str2, str3, str4, str5, str6, str7, url);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$Direct$ForJava7CapableVm.class */
                public static class ForJava7CapableVm extends Direct {
                    private final Method getClassLoadingLock;

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.getClassLoadingLock.equals(((ForJava7CapableVm) obj).getClassLoadingLock);
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.getClassLoadingLock.hashCode();
                    }

                    protected ForJava7CapableVm(Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5, Method method6) {
                        super(method, method2, method3, method4, method5);
                        this.getClassLoadingLock = method6;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        try {
                            return this.getClassLoadingLock.invoke(classLoader, str);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException(e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException(e2.getTargetException());
                        }
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$Direct$ForLegacyVm.class */
                public static class ForLegacyVm extends Direct {
                    protected ForLegacyVm(Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5) {
                        super(method, method2, method3, method4, method5);
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$UsingUnsafeInjection.class */
            public static class UsingUnsafeInjection implements Dispatcher, Initializable {
                private final Object accessor;
                private final Method findLoadedClass;
                private final Method defineClass;

                @UnknownNull
                private final Method getDefinedPackage;
                private final Method getPackage;
                private final Method definePackage;
                private final Method getClassLoadingLock;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.accessor.equals(((UsingUnsafeInjection) obj).accessor) && this.findLoadedClass.equals(((UsingUnsafeInjection) obj).findLoadedClass) && this.defineClass.equals(((UsingUnsafeInjection) obj).defineClass) && this.getDefinedPackage.equals(((UsingUnsafeInjection) obj).getDefinedPackage) && this.getPackage.equals(((UsingUnsafeInjection) obj).getPackage) && this.definePackage.equals(((UsingUnsafeInjection) obj).definePackage) && this.getClassLoadingLock.equals(((UsingUnsafeInjection) obj).getClassLoadingLock);
                }

                public int hashCode() {
                    return (((((((((((((getClass().hashCode() * 31) + this.accessor.hashCode()) * 31) + this.findLoadedClass.hashCode()) * 31) + this.defineClass.hashCode()) * 31) + this.getDefinedPackage.hashCode()) * 31) + this.getPackage.hashCode()) * 31) + this.definePackage.hashCode()) * 31) + this.getClassLoadingLock.hashCode();
                }

                protected UsingUnsafeInjection(Object obj, Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5, Method method6) {
                    this.accessor = obj;
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getDefinedPackage = method3;
                    this.getPackage = method4;
                    this.definePackage = method5;
                    this.getClassLoadingLock = method6;
                }

                /* JADX WARN: Can't wrap try/catch for region: R(11:6|(10:22|23|9|(1:11)|12|13|14|(1:16)(1:19)|17|18)|8|9|(0)|12|13|14|(0)(0)|17|18) */
                /* JADX WARN: Code restructure failed: missing block: B:21:0x02f5, code lost:            r18 = r18.defineMethod("getClassLoadingLock", java.lang.Object.class, net.bytebuddy.description.modifier.Visibility.PUBLIC).withParameters(java.lang.ClassLoader.class, java.lang.String.class).intercept(net.bytebuddy.implementation.FixedValue.argument(0));     */
                /* JADX WARN: Removed duplicated region for block: B:11:0x0265  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x03a4  */
                /* JADX WARN: Removed duplicated region for block: B:19:0x03bb  */
                @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {"DP_DO_INSIDE_DO_PRIVILEGED"}, justification = "Assuring privilege is explicit user responsibility.")
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                protected static net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable make() {
                    /*
                        Method dump skipped, instructions count: 1059
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.UsingUnsafeInjection.make():net.bytebuddy.dynamic.loading.ClassInjector$UsingReflection$Dispatcher$Initializable");
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public Dispatcher initialize() {
                    Object securityManager = UsingReflection.SYSTEM.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            UsingReflection.CHECK_PERMISSION.invoke(securityManager, ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (InvocationTargetException e) {
                            return new Unavailable(e.getTargetException().getMessage());
                        } catch (Exception e2) {
                            return new Unavailable(e2.getMessage());
                        }
                    }
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                    try {
                        return this.getClassLoadingLock.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(this.accessor, classLoader, str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                @MaybeNull
                public Package getDefinedPackage(ClassLoader classLoader, String str) {
                    if (this.getDefinedPackage == null) {
                        return getPackage(classLoader, str);
                    }
                    try {
                        return (Package) this.getDefinedPackage.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull String str7, @MaybeNull URL url) {
                    try {
                        return (Package) this.definePackage.invoke(this.accessor, classLoader, str, str2, str3, str4, str5, str6, str7, url);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$UsingUnsafeOverride.class */
            public static abstract class UsingUnsafeOverride implements Dispatcher, Initializable {
                protected final Method findLoadedClass;
                protected final Method defineClass;

                @MaybeNull
                protected final Method getDefinedPackage;
                protected final Method getPackage;
                protected final Method definePackage;

                protected UsingUnsafeOverride(Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5) {
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getDefinedPackage = method3;
                    this.getPackage = method4;
                    this.definePackage = method5;
                }

                @SuppressFBWarnings(value = {"DP_DO_INSIDE_DO_PRIVILEGED"}, justification = "Assuring privilege is explicit user responsibility.")
                protected static Initializable make() {
                    Field declaredField;
                    Method method;
                    Method declaredMethod;
                    Method declaredMethod2;
                    Method declaredMethod3;
                    Method declaredMethod4;
                    if (Boolean.parseBoolean(java.lang.System.getProperty(UsingUnsafe.SAFE_PROPERTY, Boolean.toString(GraalImageCode.getCurrent().isDefined())))) {
                        return new Initializable.Unavailable("Use of Unsafe was disabled by system property");
                    }
                    Class<?> cls = Class.forName("sun.misc.Unsafe");
                    Field declaredField2 = cls.getDeclaredField("theUnsafe");
                    declaredField2.setAccessible(true);
                    Object obj = declaredField2.get(null);
                    try {
                        declaredField = AccessibleObject.class.getDeclaredField("override");
                    } catch (NoSuchFieldException unused) {
                        declaredField = new ByteBuddy().redefine(AccessibleObject.class).name("net.bytebuddy.mirror." + AccessibleObject.class.getSimpleName()).noNestMate().visit(new MemberRemoval().stripInvokables(ElementMatchers.any())).make().load(AccessibleObject.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER.with(AccessibleObject.class.getProtectionDomain())).getLoaded().getDeclaredField("override");
                    }
                    long longValue = ((Long) cls.getMethod("objectFieldOffset", Field.class).invoke(obj, declaredField)).longValue();
                    Method method2 = cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
                    try {
                        if (JavaModule.isSupported()) {
                            try {
                                method = ClassLoader.class.getMethod("getDefinedPackage", String.class);
                            } catch (NoSuchMethodException unused2) {
                            }
                            declaredMethod = ClassLoader.class.getDeclaredMethod("getPackage", String.class);
                            method2.invoke(obj, declaredMethod, Long.valueOf(longValue), Boolean.TRUE);
                            declaredMethod2 = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
                            declaredMethod3 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                            declaredMethod4 = ClassLoader.class.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class);
                            method2.invoke(obj, declaredMethod3, Long.valueOf(longValue), Boolean.TRUE);
                            method2.invoke(obj, declaredMethod2, Long.valueOf(longValue), Boolean.TRUE);
                            method2.invoke(obj, declaredMethod4, Long.valueOf(longValue), Boolean.TRUE);
                            Method declaredMethod5 = ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class);
                            method2.invoke(obj, declaredMethod5, Long.valueOf(longValue), Boolean.TRUE);
                            return new ForJava7CapableVm(declaredMethod2, declaredMethod3, method, declaredMethod, declaredMethod4, declaredMethod5);
                        }
                        Method declaredMethod52 = ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class);
                        method2.invoke(obj, declaredMethod52, Long.valueOf(longValue), Boolean.TRUE);
                        return new ForJava7CapableVm(declaredMethod2, declaredMethod3, method, declaredMethod, declaredMethod4, declaredMethod52);
                    } catch (NoSuchMethodException unused3) {
                        return new ForLegacyVm(declaredMethod2, declaredMethod3, method, declaredMethod, declaredMethod4);
                    }
                    method = null;
                    declaredMethod = ClassLoader.class.getDeclaredMethod("getPackage", String.class);
                    method2.invoke(obj, declaredMethod, Long.valueOf(longValue), Boolean.TRUE);
                    declaredMethod2 = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
                    declaredMethod3 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    declaredMethod4 = ClassLoader.class.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class);
                    method2.invoke(obj, declaredMethod3, Long.valueOf(longValue), Boolean.TRUE);
                    method2.invoke(obj, declaredMethod2, Long.valueOf(longValue), Boolean.TRUE);
                    method2.invoke(obj, declaredMethod4, Long.valueOf(longValue), Boolean.TRUE);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public Dispatcher initialize() {
                    Object securityManager = UsingReflection.SYSTEM.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            UsingReflection.CHECK_PERMISSION.invoke(securityManager, ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (InvocationTargetException e) {
                            return new Unavailable(e.getTargetException().getMessage());
                        } catch (Exception e2) {
                            return new Unavailable(e2.getMessage());
                        }
                    }
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(classLoader, str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                @MaybeNull
                public Package getDefinedPackage(ClassLoader classLoader, String str) {
                    if (this.getDefinedPackage == null) {
                        return getPackage(classLoader, str);
                    }
                    try {
                        return (Package) this.getDefinedPackage.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull String str7, @MaybeNull URL url) {
                    try {
                        return (Package) this.definePackage.invoke(classLoader, str, str2, str3, str4, str5, str6, str7, url);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$UsingUnsafeOverride$ForJava7CapableVm.class */
                public static class ForJava7CapableVm extends UsingUnsafeOverride {
                    private final Method getClassLoadingLock;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.getClassLoadingLock.equals(((ForJava7CapableVm) obj).getClassLoadingLock);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.getClassLoadingLock.hashCode();
                    }

                    protected ForJava7CapableVm(Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5, Method method6) {
                        super(method, method2, method3, method4, method5);
                        this.getClassLoadingLock = method6;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        try {
                            return this.getClassLoadingLock.invoke(classLoader, str);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException(e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException(e2.getTargetException());
                        }
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$UsingUnsafeOverride$ForLegacyVm.class */
                public static class ForLegacyVm extends UsingUnsafeOverride {
                    protected ForLegacyVm(Method method, Method method2, @MaybeNull Method method3, Method method4, Method method5) {
                        super(method, method2, method3, method4, method5);
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingReflection$Dispatcher$Unavailable.class */
            public static class Unavailable implements Dispatcher {
                private final String message;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.message.hashCode();
                }

                protected Unavailable(String str) {
                    this.message = str;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                    return classLoader;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return classLoader.loadClass(str);
                    } catch (ClassNotFoundException unused) {
                        return UNDEFINED;
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    throw new UnsupportedOperationException("Cannot define class using reflection: " + this.message);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getDefinedPackage(ClassLoader classLoader, String str) {
                    throw new UnsupportedOperationException("Cannot get defined package using reflection: " + this.message);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    throw new UnsupportedOperationException("Cannot get package using reflection: " + this.message);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull String str7, @MaybeNull URL url) {
                    throw new UnsupportedOperationException("Cannot define package using injection: " + this.message);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingLookup.class */
    public static class UsingLookup extends AbstractBase {
        private static final MethodHandles METHOD_HANDLES;
        private static final MethodHandles.Lookup METHOD_HANDLES_LOOKUP;
        private static final int PACKAGE_LOOKUP = 8;
        private final Object lookup;
        private static final boolean ACCESS_CONTROLLER;

        @JavaDispatcher.Proxied("java.lang.invoke.MethodHandles")
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingLookup$MethodHandles.class */
        protected interface MethodHandles {

            @JavaDispatcher.Proxied("java.lang.invoke.MethodHandles$Lookup")
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingLookup$MethodHandles$Lookup.class */
            public interface Lookup {
                @JavaDispatcher.Proxied("lookupClass")
                Class<?> lookupClass(Object obj);

                @JavaDispatcher.Proxied("lookupModes")
                int lookupModes(Object obj);

                @JavaDispatcher.Proxied("defineClass")
                Class<?> defineClass(Object obj, byte[] bArr);
            }

            @JavaDispatcher.IsStatic
            @JavaDispatcher.Proxied("privateLookupIn")
            Object privateLookupIn(Class<?> cls, @JavaDispatcher.Proxied("java.lang.invoke.MethodHandles$Lookup") Object obj);
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.lookup.equals(((UsingLookup) obj).lookup);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.lookup.hashCode();
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
            METHOD_HANDLES = (MethodHandles) doPrivileged(JavaDispatcher.of(MethodHandles.class));
            METHOD_HANDLES_LOOKUP = (MethodHandles.Lookup) doPrivileged(JavaDispatcher.of(MethodHandles.Lookup.class));
        }

        protected UsingLookup(Object obj) {
            this.lookup = obj;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static UsingLookup of(Object obj) {
            if (!isAvailable()) {
                throw new IllegalStateException("The current VM does not support class definition via method handle lookups");
            }
            if (!JavaType.METHOD_HANDLES_LOOKUP.isInstance(obj)) {
                throw new IllegalArgumentException("Not a method handle lookup: " + obj);
            }
            if ((METHOD_HANDLES_LOOKUP.lookupModes(obj) & 8) == 0) {
                throw new IllegalArgumentException("Lookup does not imply package-access: " + obj);
            }
            return new UsingLookup(obj);
        }

        public Class<?> lookupType() {
            return METHOD_HANDLES_LOOKUP.lookupClass(this.lookup);
        }

        public UsingLookup in(Class<?> cls) {
            try {
                return new UsingLookup(METHOD_HANDLES.privateLookupIn(cls, this.lookup));
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot access " + cls.getName() + " from " + this.lookup, e);
            }
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public boolean isAlive() {
            return isAvailable();
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            PackageDescription packageDescription = TypeDescription.ForLoadedType.of(lookupType()).getPackage();
            if (packageDescription == null) {
                throw new IllegalArgumentException("Cannot inject array or primitive type");
            }
            HashMap hashMap = new HashMap();
            for (Map.Entry<? extends String, byte[]> entry : map.entrySet()) {
                int lastIndexOf = entry.getKey().lastIndexOf(46);
                if (!packageDescription.getName().equals(lastIndexOf == -1 ? "" : entry.getKey().substring(0, lastIndexOf))) {
                    throw new IllegalArgumentException(entry.getKey() + " must be defined in the same package as " + this.lookup);
                }
                try {
                    hashMap.put(entry.getKey(), METHOD_HANDLES_LOOKUP.defineClass(this.lookup, entry.getValue()));
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
            return hashMap;
        }

        public static boolean isAvailable() {
            return JavaType.MODULE.isAvailable();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe.class */
    public static class UsingUnsafe extends AbstractBase {
        public static final String SAFE_PROPERTY = "net.bytebuddy.safe";
        private static final Dispatcher.Initializable DISPATCHER;
        private static final System SYSTEM;
        private static final Method CHECK_PERMISSION;
        private static final Object BOOTSTRAP_LOADER_LOCK;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ClassLoader classLoader;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ProtectionDomain protectionDomain;
        private final Dispatcher.Initializable dispatcher;
        private static final boolean ACCESS_CONTROLLER;

        @JavaDispatcher.Proxied("java.lang.System")
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$System.class */
        protected interface System {
            @JavaDispatcher.IsStatic
            @JavaDispatcher.Defaults
            @MaybeNull
            @JavaDispatcher.Proxied("getSecurityManager")
            Object getSecurityManager();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = ((UsingUnsafe) obj).classLoader;
            if (classLoader2 != null) {
                if (classLoader == null || !classLoader.equals(classLoader2)) {
                    return false;
                }
            } else if (classLoader != null) {
                return false;
            }
            ProtectionDomain protectionDomain = this.protectionDomain;
            ProtectionDomain protectionDomain2 = ((UsingUnsafe) obj).protectionDomain;
            if (protectionDomain2 != null) {
                if (protectionDomain == null || !protectionDomain.equals(protectionDomain2)) {
                    return false;
                }
            } else if (protectionDomain != null) {
                return false;
            }
            return this.dispatcher.equals(((UsingUnsafe) obj).dispatcher);
        }

        public int hashCode() {
            int hashCode = getClass().hashCode() * 31;
            ClassLoader classLoader = this.classLoader;
            if (classLoader != null) {
                hashCode += classLoader.hashCode();
            }
            int i = hashCode * 31;
            ProtectionDomain protectionDomain = this.protectionDomain;
            if (protectionDomain != null) {
                i += protectionDomain.hashCode();
            }
            return (i * 31) + this.dispatcher.hashCode();
        }

        static {
            try {
                Class.forName("java.security.AccessController", false, null);
                ACCESS_CONTROLLER = Boolean.parseBoolean(java.lang.System.getProperty("net.bytebuddy.securitymanager", "true"));
            } catch (ClassNotFoundException unused) {
                ACCESS_CONTROLLER = false;
            } catch (SecurityException unused2) {
                ACCESS_CONTROLLER = true;
            }
            DISPATCHER = (Dispatcher.Initializable) doPrivileged(Dispatcher.CreationAction.INSTANCE);
            SYSTEM = (System) doPrivileged(JavaDispatcher.of(System.class));
            CHECK_PERMISSION = (Method) doPrivileged(new GetMethodAction("java.lang.SecurityManager", "checkPermission", Permission.class));
            BOOTSTRAP_LOADER_LOCK = new Object();
        }

        public UsingUnsafe(@MaybeNull ClassLoader classLoader) {
            this(classLoader, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingUnsafe(@MaybeNull ClassLoader classLoader, @MaybeNull ProtectionDomain protectionDomain) {
            this(classLoader, protectionDomain, DISPATCHER);
        }

        protected UsingUnsafe(@MaybeNull ClassLoader classLoader, @MaybeNull ProtectionDomain protectionDomain, Dispatcher.Initializable initializable) {
            this.classLoader = classLoader;
            this.protectionDomain = protectionDomain;
            this.dispatcher = initializable;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public boolean isAlive() {
            return this.dispatcher.isAvailable();
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            Dispatcher initialize = this.dispatcher.initialize();
            HashMap hashMap = new HashMap();
            Object obj = this.classLoader == null ? BOOTSTRAP_LOADER_LOCK : this.classLoader;
            Object obj2 = obj;
            synchronized (obj) {
                for (Map.Entry<? extends String, byte[]> entry : map.entrySet()) {
                    try {
                        hashMap.put(entry.getKey(), Class.forName(entry.getKey(), false, this.classLoader));
                    } catch (ClassNotFoundException unused) {
                        hashMap.put(entry.getKey(), initialize.defineClass(this.classLoader, entry.getKey(), entry.getValue(), this.protectionDomain));
                    }
                }
                return hashMap;
            }
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofSystemLoader() {
            return new UsingUnsafe(ClassLoader.getSystemClassLoader());
        }

        public static ClassInjector ofPlatformLoader() {
            return new UsingUnsafe(ClassLoader.getSystemClassLoader().getParent());
        }

        public static ClassInjector ofBootLoader() {
            return new UsingUnsafe(ClassLoadingStrategy.BOOTSTRAP_LOADER);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Dispatcher.class */
        protected interface Dispatcher {

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Dispatcher$Initializable.class */
            public interface Initializable {
                boolean isAvailable();

                Dispatcher initialize();
            }

            Class<?> defineClass(@MaybeNull ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Dispatcher$CreationAction.class */
            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
                public final Initializable run() {
                    Field declaredField;
                    if (Boolean.parseBoolean(java.lang.System.getProperty(UsingUnsafe.SAFE_PROPERTY, Boolean.toString(GraalImageCode.getCurrent().isDefined())))) {
                        return new Unavailable("Use of Unsafe was disabled by system property");
                    }
                    try {
                        Class<?> cls = Class.forName("sun.misc.Unsafe");
                        Field declaredField2 = cls.getDeclaredField("theUnsafe");
                        declaredField2.setAccessible(true);
                        Object obj = declaredField2.get(null);
                        try {
                            Method method = cls.getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
                            method.setAccessible(true);
                            return new Enabled(obj, method);
                        } catch (Exception e) {
                            try {
                                try {
                                    declaredField = AccessibleObject.class.getDeclaredField("override");
                                } catch (Exception unused) {
                                    throw e;
                                }
                            } catch (NoSuchFieldException unused2) {
                                declaredField = new ByteBuddy().redefine(AccessibleObject.class).name("net.bytebuddy.mirror." + AccessibleObject.class.getSimpleName()).noNestMate().visit(new MemberRemoval().stripInvokables(ElementMatchers.any())).make().load(AccessibleObject.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER.with(AccessibleObject.class.getProtectionDomain())).getLoaded().getDeclaredField("override");
                            }
                            long longValue = ((Long) cls.getMethod("objectFieldOffset", Field.class).invoke(obj, declaredField)).longValue();
                            Method method2 = cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
                            Class<?> cls2 = Class.forName("jdk.internal.misc.Unsafe");
                            Field declaredField3 = cls2.getDeclaredField("theUnsafe");
                            method2.invoke(obj, declaredField3, Long.valueOf(longValue), Boolean.TRUE);
                            Method method3 = cls2.getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
                            method2.invoke(obj, method3, Long.valueOf(longValue), Boolean.TRUE);
                            return new Enabled(declaredField3.get(null), method3);
                        }
                    } catch (Exception e2) {
                        return new Unavailable(e2.getMessage());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Dispatcher$Enabled.class */
            public static class Enabled implements Dispatcher, Initializable {
                private final Object unsafe;
                private final Method defineClass;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.unsafe.equals(((Enabled) obj).unsafe) && this.defineClass.equals(((Enabled) obj).defineClass);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.unsafe.hashCode()) * 31) + this.defineClass.hashCode();
                }

                protected Enabled(Object obj, Method method) {
                    this.unsafe = obj;
                    this.defineClass = method;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public Dispatcher initialize() {
                    Object securityManager = UsingUnsafe.SYSTEM.getSecurityManager();
                    if (securityManager != null) {
                        try {
                            UsingUnsafe.CHECK_PERMISSION.invoke(securityManager, ClassInjector.SUPPRESS_ACCESS_CHECKS);
                        } catch (InvocationTargetException e) {
                            return new Unavailable(e.getTargetException().getMessage());
                        } catch (Exception e2) {
                            return new Unavailable(e2.getMessage());
                        }
                    }
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher
                public Class<?> defineClass(@MaybeNull ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(this.unsafe, str, bArr, 0, Integer.valueOf(bArr.length), classLoader, protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException(e2.getTargetException());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Dispatcher$Unavailable.class */
            public static class Unavailable implements Dispatcher, Initializable {
                private final String message;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.message.hashCode();
                }

                protected Unavailable(String str) {
                    this.message = str;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public boolean isAvailable() {
                    return false;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public Dispatcher initialize() {
                    throw new UnsupportedOperationException("Could not access Unsafe class: " + this.message);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher
                public Class<?> defineClass(@MaybeNull ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    throw new UnsupportedOperationException("Could not access Unsafe class: " + this.message);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Factory.class */
        public static class Factory {
            private final Dispatcher.Initializable dispatcher;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.dispatcher.equals(((Factory) obj).dispatcher);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.dispatcher.hashCode();
            }

            public Factory() {
                this(AccessResolver.Default.INSTANCE);
            }

            @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
            public Factory(AccessResolver accessResolver) {
                Dispatcher.Initializable unavailable;
                if (UsingUnsafe.DISPATCHER.isAvailable()) {
                    unavailable = UsingUnsafe.DISPATCHER;
                } else {
                    try {
                        Class<?> cls = Class.forName("jdk.internal.misc.Unsafe");
                        Field declaredField = cls.getDeclaredField("theUnsafe");
                        accessResolver.apply(declaredField);
                        Object obj = declaredField.get(null);
                        Method method = cls.getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
                        accessResolver.apply(method);
                        unavailable = new Dispatcher.Enabled(obj, method);
                    } catch (Exception e) {
                        unavailable = new Dispatcher.Unavailable(e.getMessage());
                    }
                }
                this.dispatcher = unavailable;
            }

            protected Factory(Dispatcher.Initializable initializable) {
                this.dispatcher = initializable;
            }

            public static Factory resolve(Instrumentation instrumentation) {
                return resolve(instrumentation, false);
            }

            @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION", "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Exception intends to trigger disabled injection strategy. Modules are assumed if module system is supported.")
            public static Factory resolve(Instrumentation instrumentation, boolean z) {
                if (UsingUnsafe.isAvailable() || !JavaModule.isSupported()) {
                    return new Factory();
                }
                try {
                    Class<?> cls = Class.forName("jdk.internal.misc.Unsafe");
                    PackageDescription.ForLoadedPackage forLoadedPackage = new PackageDescription.ForLoadedPackage(cls.getPackage());
                    JavaModule ofType = JavaModule.ofType(cls);
                    if (ofType.isOpened(forLoadedPackage, JavaModule.ofType(UsingUnsafe.class))) {
                        return new Factory();
                    }
                    if (z) {
                        JavaModule ofType2 = JavaModule.ofType(AccessResolver.Default.class);
                        UsingInstrumentation.redefineModule(instrumentation, ofType, Collections.singleton(ofType2), Collections.emptyMap(), Collections.singletonMap(forLoadedPackage.getName(), Collections.singleton(ofType2)), Collections.emptySet(), Collections.emptyMap());
                        return new Factory();
                    }
                    Class loaded = new ByteBuddy().subclass(AccessResolver.class).method(ElementMatchers.named("apply")).intercept(MethodCall.invoke(AccessibleObject.class.getMethod("setAccessible", Boolean.TYPE)).onArgument(0).with(Boolean.TRUE)).make().load(AccessResolver.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER.with(AccessResolver.class.getProtectionDomain())).getLoaded();
                    JavaModule ofType3 = JavaModule.ofType(loaded);
                    UsingInstrumentation.redefineModule(instrumentation, ofType, Collections.singleton(ofType3), Collections.emptyMap(), Collections.singletonMap(forLoadedPackage.getName(), Collections.singleton(ofType3)), Collections.emptySet(), Collections.emptyMap());
                    return new Factory((AccessResolver) loaded.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception e) {
                    return new Factory(new Dispatcher.Unavailable(e.getMessage()));
                }
            }

            public boolean isAvailable() {
                return this.dispatcher.isAvailable();
            }

            public ClassInjector make(@MaybeNull ClassLoader classLoader) {
                return make(classLoader, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
            }

            public ClassInjector make(@MaybeNull ClassLoader classLoader, @MaybeNull ProtectionDomain protectionDomain) {
                return new UsingUnsafe(classLoader, protectionDomain, this.dispatcher);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Factory$AccessResolver.class */
            public interface AccessResolver {
                void apply(AccessibleObject accessibleObject);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingUnsafe$Factory$AccessResolver$Default.class */
                public enum Default implements AccessResolver {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Factory.AccessResolver
                    public final void apply(AccessibleObject accessibleObject) {
                        accessibleObject.setAccessible(true);
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingInstrumentation.class */
    public static class UsingInstrumentation extends AbstractBase {
        private static final String JAR = "jar";
        private static final String CLASS_FILE_EXTENSION = ".class";
        private static final Dispatcher DISPATCHER;
        private final Instrumentation instrumentation;
        private final Target target;
        private final File folder;
        private final RandomString randomString;
        private static final boolean ACCESS_CONTROLLER;

        /* JADX INFO: Access modifiers changed from: protected */
        @JavaDispatcher.Proxied("java.lang.instrument.Instrumentation")
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingInstrumentation$Dispatcher.class */
        public interface Dispatcher {
            @JavaDispatcher.Proxied("appendToBootstrapClassLoaderSearch")
            void appendToBootstrapClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile);

            @JavaDispatcher.Proxied("appendToSystemClassLoaderSearch")
            void appendToSystemClassLoaderSearch(Instrumentation instrumentation, JarFile jarFile);

            @JavaDispatcher.Proxied("isModifiableModule")
            boolean isModifiableModule(Instrumentation instrumentation, @JavaDispatcher.Proxied("java.lang.Module") Object obj);

            @JavaDispatcher.Proxied("redefineModule")
            void redefineModule(Instrumentation instrumentation, @JavaDispatcher.Proxied("java.lang.Module") Object obj, Set<?> set, Map<String, Set<?>> map, Map<String, Set<?>> map2, Set<Class<?>> set2, Map<Class<?>, List<Class<?>>> map3);
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.target.equals(((UsingInstrumentation) obj).target) && this.instrumentation.equals(((UsingInstrumentation) obj).instrumentation) && this.folder.equals(((UsingInstrumentation) obj).folder) && this.randomString.equals(((UsingInstrumentation) obj).randomString);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.instrumentation.hashCode()) * 31) + this.target.hashCode()) * 31) + this.folder.hashCode()) * 31) + this.randomString.hashCode();
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
            DISPATCHER = (Dispatcher) doPrivileged(JavaDispatcher.of(Dispatcher.class));
        }

        protected UsingInstrumentation(File file, Target target, Instrumentation instrumentation, RandomString randomString) {
            this.folder = file;
            this.target = target;
            this.instrumentation = instrumentation;
            this.randomString = randomString;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static void redefineModule(Instrumentation instrumentation, JavaModule javaModule, Set<JavaModule> set, Map<String, Set<JavaModule>> map, Map<String, Set<JavaModule>> map2, Set<Class<?>> set2, Map<Class<?>, List<Class<?>>> map3) {
            if (!DISPATCHER.isModifiableModule(instrumentation, javaModule.unwrap())) {
                throw new IllegalArgumentException("Cannot modify module: " + javaModule);
            }
            HashSet hashSet = new HashSet();
            Iterator<JavaModule> it = set.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().unwrap());
            }
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, Set<JavaModule>> entry : map.entrySet()) {
                HashSet hashSet2 = new HashSet();
                Iterator<JavaModule> it2 = entry.getValue().iterator();
                while (it2.hasNext()) {
                    hashSet2.add(it2.next().unwrap());
                }
                hashMap.put(entry.getKey(), hashSet2);
            }
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<String, Set<JavaModule>> entry2 : map2.entrySet()) {
                HashSet hashSet3 = new HashSet();
                Iterator<JavaModule> it3 = entry2.getValue().iterator();
                while (it3.hasNext()) {
                    hashSet3.add(it3.next().unwrap());
                }
                hashMap2.put(entry2.getKey(), hashSet3);
            }
            DISPATCHER.redefineModule(instrumentation, javaModule.unwrap(), hashSet, hashMap, hashMap2, set2, map3);
        }

        public static ClassInjector of(File file, Target target, Instrumentation instrumentation) {
            return new UsingInstrumentation(file, target, instrumentation, new RandomString());
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public boolean isAlive() {
            return isAvailable();
        }

        /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
            java.lang.NullPointerException: Cannot invoke "java.util.List.isEmpty()" because "s" is null
            	at jadx.core.utils.BlockUtils.getNextBlock(BlockUtils.java:411)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:172)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
            	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1110)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1046)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public java.util.Map<java.lang.String, java.lang.Class<?>> injectRaw(java.util.Map<? extends java.lang.String, byte[]> r9) {
            /*
                Method dump skipped, instructions count: 377
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.injectRaw(java.util.Map):java.util.Map");
        }

        public static boolean isAvailable() {
            return ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V5).isAtLeast(ClassFileVersion.JAVA_V6);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingInstrumentation$Target.class */
        public enum Target {
            BOOTSTRAP(null) { // from class: net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target.1
                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target
                protected final void inject(Instrumentation instrumentation, JarFile jarFile) {
                    UsingInstrumentation.DISPATCHER.appendToBootstrapClassLoaderSearch(instrumentation, jarFile);
                }
            },
            SYSTEM(ClassLoader.getSystemClassLoader()) { // from class: net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target.2
                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target
                protected final void inject(Instrumentation instrumentation, JarFile jarFile) {
                    UsingInstrumentation.DISPATCHER.appendToSystemClassLoaderSearch(instrumentation, jarFile);
                }
            };


            @MaybeNull
            private final ClassLoader classLoader;

            protected abstract void inject(Instrumentation instrumentation, JarFile jarFile);

            /* synthetic */ Target(ClassLoader classLoader, byte b2) {
                this(classLoader);
            }

            Target(@MaybeNull ClassLoader classLoader) {
                this.classLoader = classLoader;
            }

            @MaybeNull
            protected ClassLoader getClassLoader() {
                return this.classLoader;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna.class */
    public static class UsingJna extends AbstractBase {
        private static final Dispatcher DISPATCHER;
        private static final Object BOOTSTRAP_LOADER_LOCK;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ClassLoader classLoader;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final ProtectionDomain protectionDomain;
        private static final boolean ACCESS_CONTROLLER;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = ((UsingJna) obj).classLoader;
            if (classLoader2 != null) {
                if (classLoader == null || !classLoader.equals(classLoader2)) {
                    return false;
                }
            } else if (classLoader != null) {
                return false;
            }
            ProtectionDomain protectionDomain = this.protectionDomain;
            ProtectionDomain protectionDomain2 = ((UsingJna) obj).protectionDomain;
            return protectionDomain2 != null ? protectionDomain != null && protectionDomain.equals(protectionDomain2) : protectionDomain == null;
        }

        public int hashCode() {
            int hashCode = getClass().hashCode() * 31;
            ClassLoader classLoader = this.classLoader;
            if (classLoader != null) {
                hashCode += classLoader.hashCode();
            }
            int i = hashCode * 31;
            ProtectionDomain protectionDomain = this.protectionDomain;
            return protectionDomain != null ? i + protectionDomain.hashCode() : i;
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
            DISPATCHER = (Dispatcher) doPrivileged(Dispatcher.CreationAction.INSTANCE);
            BOOTSTRAP_LOADER_LOCK = new Object();
        }

        public UsingJna(@MaybeNull ClassLoader classLoader) {
            this(classLoader, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingJna(@MaybeNull ClassLoader classLoader, @MaybeNull ProtectionDomain protectionDomain) {
            this.classLoader = classLoader;
            this.protectionDomain = protectionDomain;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofSystemLoader() {
            return new UsingJna(ClassLoader.getSystemClassLoader());
        }

        public static ClassInjector ofPlatformLoader() {
            return new UsingJna(ClassLoader.getSystemClassLoader().getParent());
        }

        public static ClassInjector ofBootLoader() {
            return new UsingJna(ClassLoadingStrategy.BOOTSTRAP_LOADER);
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public boolean isAlive() {
            return DISPATCHER.isAvailable();
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<String, Class<?>> injectRaw(Map<? extends String, byte[]> map) {
            HashMap hashMap = new HashMap();
            Object obj = this.classLoader == null ? BOOTSTRAP_LOADER_LOCK : this.classLoader;
            Object obj2 = obj;
            synchronized (obj) {
                for (Map.Entry<? extends String, byte[]> entry : map.entrySet()) {
                    try {
                        hashMap.put(entry.getKey(), Class.forName(entry.getKey(), false, this.classLoader));
                    } catch (ClassNotFoundException unused) {
                        hashMap.put(entry.getKey(), DISPATCHER.defineClass(this.classLoader, entry.getKey(), entry.getValue(), this.protectionDomain));
                    }
                }
                return hashMap;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna$Dispatcher.class */
        protected interface Dispatcher {

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna$Dispatcher$Jvm.class */
            public interface Jvm extends Library {
                Class<?> JVM_DefineClass(JNIEnv jNIEnv, String str, @MaybeNull ClassLoader classLoader, byte[] bArr, int i, @MaybeNull ProtectionDomain protectionDomain);
            }

            boolean isAvailable();

            Class<?> defineClass(@MaybeNull ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna$Dispatcher$CreationAction.class */
            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                public final Dispatcher run() {
                    if (System.getProperty("java.vm.name", "").toUpperCase(Locale.US).contains("J9")) {
                        return new Unavailable("J9 does not support JNA-based class definition");
                    }
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("allow-objects", Boolean.TRUE);
                        if (Platform.isWindows() && !Platform.is64Bit()) {
                            hashMap.put("function-mapper", Windows32BitFunctionMapper.INSTANCE);
                        }
                        return new Enabled((Jvm) Native.loadLibrary("jvm", Jvm.class, hashMap));
                    } catch (Throwable th) {
                        return new Unavailable(th.getMessage());
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna$Dispatcher$Windows32BitFunctionMapper.class */
            public enum Windows32BitFunctionMapper implements FunctionMapper {
                INSTANCE;

                public final String getFunctionName(NativeLibrary nativeLibrary, Method method) {
                    if (method.getName().equals("JVM_DefineClass")) {
                        return "_JVM_DefineClass@24";
                    }
                    return method.getName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna$Dispatcher$Enabled.class */
            public static class Enabled implements Dispatcher {
                private final Jvm jvm;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.jvm.equals(((Enabled) obj).jvm);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.jvm.hashCode();
                }

                protected Enabled(Jvm jvm) {
                    this.jvm = jvm;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingJna.Dispatcher
                public boolean isAvailable() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingJna.Dispatcher
                public Class<?> defineClass(@MaybeNull ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    return this.jvm.JVM_DefineClass(JNIEnv.CURRENT, str.replace('.', '/'), classLoader, bArr, bArr.length, protectionDomain);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassInjector$UsingJna$Dispatcher$Unavailable.class */
            public static class Unavailable implements Dispatcher {
                private final String error;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.error.equals(((Unavailable) obj).error);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.error.hashCode();
                }

                protected Unavailable(String str) {
                    this.error = str;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingJna.Dispatcher
                public boolean isAvailable() {
                    return false;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingJna.Dispatcher
                public Class<?> defineClass(@MaybeNull ClassLoader classLoader, String str, byte[] bArr, @MaybeNull ProtectionDomain protectionDomain) {
                    throw new UnsupportedOperationException("JNA is not available and JNA-based injection cannot be used: " + this.error);
                }
            }
        }
    }
}
