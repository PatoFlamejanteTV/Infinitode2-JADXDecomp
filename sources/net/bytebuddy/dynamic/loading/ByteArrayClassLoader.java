package net.bytebuddy.dynamic.loading;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassFilePostProcessor;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import net.bytebuddy.utility.GraalImageCode;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader.class */
public class ByteArrayClassLoader extends InjectionClassLoader {
    public static final String URL_SCHEMA = "bytebuddy";
    private static final int FROM_BEGINNING = 0;

    @AlwaysNull
    private static final URL NO_URL;
    private static final PackageLookupStrategy PACKAGE_LOOKUP_STRATEGY;
    protected static final SynchronizationStrategy.Initializable SYNCHRONIZATION_STRATEGY;
    protected final ConcurrentMap<String, byte[]> typeDefinitions;
    protected final PersistenceHandler persistenceHandler;

    @MaybeNull
    protected final ProtectionDomain protectionDomain;
    protected final PackageDefinitionStrategy packageDefinitionStrategy;
    protected final ClassFilePostProcessor classFilePostProcessor;

    @MaybeNull
    protected final Object accessControlContext;
    private static final boolean ACCESS_CONTROLLER;

    static /* synthetic */ Object a() {
        return methodHandle();
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
        NO_URL = null;
        PACKAGE_LOOKUP_STRATEGY = (PackageLookupStrategy) doPrivileged(PackageLookupStrategy.CreationAction.INSTANCE);
        SYNCHRONIZATION_STRATEGY = (SynchronizationStrategy.Initializable) doPrivileged(SynchronizationStrategy.CreationAction.INSTANCE);
        doRegisterAsParallelCapable();
    }

    @SuppressFBWarnings(value = {"DP_DO_INSIDE_DO_PRIVILEGED"}, justification = "Must be invoked from targeting class loader type.")
    private static void doRegisterAsParallelCapable() {
        try {
            Method declaredMethod = ClassLoader.class.getDeclaredMethod("registerAsParallelCapable", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, new Object[0]);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AccessControllerPlugin.Enhance
    public static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map) {
        this(classLoader, true, map);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map) {
        this(classLoader, z, map, PersistenceHandler.LATENT);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
        this(classLoader, true, map, persistenceHandler);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
        this(classLoader, z, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, persistenceHandler, PackageDefinitionStrategy.Trivial.INSTANCE);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
        this(classLoader, true, map, protectionDomain, persistenceHandler, packageDefinitionStrategy);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
        this(classLoader, z, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, ClassFilePostProcessor.NoOp.INSTANCE);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFilePostProcessor classFilePostProcessor) {
        this(classLoader, true, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, classFilePostProcessor);
    }

    public ByteArrayClassLoader(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFilePostProcessor classFilePostProcessor) {
        super(classLoader, z);
        this.typeDefinitions = new ConcurrentHashMap(map);
        this.protectionDomain = protectionDomain;
        this.persistenceHandler = persistenceHandler;
        this.packageDefinitionStrategy = packageDefinitionStrategy;
        this.classFilePostProcessor = classFilePostProcessor;
        this.accessControlContext = getContext();
    }

    @MaybeNull
    @AccessControllerPlugin.Enhance
    private static Object getContext() {
        if (ACCESS_CONTROLLER) {
            return AccessController.getContext();
        }
        return null;
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction, @MaybeNull Object obj) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction, (AccessControlContext) obj) : privilegedAction.run();
    }

    private static Object methodHandle() {
        return Class.forName("java.lang.invoke.MethodHandles").getMethod("lookup", new Class[0]).invoke(null, new Object[0]);
    }

    public static Map<TypeDescription, Class<?>> load(@MaybeNull ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
        return load(classLoader, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, PersistenceHandler.LATENT, PackageDefinitionStrategy.Trivial.INSTANCE, false, true);
    }

    @SuppressFBWarnings(value = {"DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED"}, justification = "Assuring privilege is explicit user responsibility.")
    public static Map<TypeDescription, Class<?>> load(@MaybeNull ClassLoader classLoader, Map<TypeDescription, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
            hashMap.put(entry.getKey().getName(), entry.getValue());
        }
        ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader(classLoader, z2, hashMap, protectionDomain, persistenceHandler, packageDefinitionStrategy, ClassFilePostProcessor.NoOp.INSTANCE);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TypeDescription typeDescription : map.keySet()) {
            try {
                Class<?> cls = Class.forName(typeDescription.getName(), false, byteArrayClassLoader);
                if (!GraalImageCode.getCurrent().isNativeImageExecution() && z && cls.getClassLoader() != byteArrayClassLoader) {
                    throw new IllegalStateException("Class already loaded: " + cls);
                }
                linkedHashMap.put(typeDescription, cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot load class " + typeDescription, e);
            }
        }
        return linkedHashMap;
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException: Cannot invoke "java.util.List.isEmpty()" because "s" is null
        	at jadx.core.utils.BlockUtils.getNextBlock(BlockUtils.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1110)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1046)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd A[DONT_GENERATE] */
    @Override // net.bytebuddy.dynamic.loading.InjectionClassLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.util.Map<java.lang.String, java.lang.Class<?>> doDefineClasses(java.util.Map<java.lang.String, byte[]> r7) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.doDefineClasses(java.util.Map):java.util.Map");
    }

    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) {
        byte[] lookup = this.persistenceHandler.lookup(str, this.typeDefinitions);
        if (lookup == null) {
            throw new ClassNotFoundException(str);
        }
        return (Class) doPrivileged(new ClassDefinitionAction(str, this.classFilePostProcessor.transform(this, str, this.protectionDomain, lookup)), this.accessControlContext);
    }

    @Override // java.lang.ClassLoader
    @MaybeNull
    protected URL findResource(String str) {
        return this.persistenceHandler.url(str, this.typeDefinitions);
    }

    @Override // java.lang.ClassLoader
    protected Enumeration<URL> findResources(String str) {
        URL url = this.persistenceHandler.url(str, this.typeDefinitions);
        return url == null ? EmptyEnumeration.INSTANCE : new SingletonEnumeration(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @MaybeNull
    public Package doGetPackage(String str) {
        return getPackage(str);
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SynchronizationStrategy.class */
    protected interface SynchronizationStrategy {

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SynchronizationStrategy$Initializable.class */
        public interface Initializable {
            SynchronizationStrategy initialize();
        }

        Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SynchronizationStrategy$CreationAction.class */
        public enum CreationAction implements PrivilegedAction<Initializable> {
            INSTANCE;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
            public final Initializable run() {
                try {
                    try {
                        Class<?> cls = Class.forName("java.lang.invoke.MethodType");
                        Class<?> cls2 = Class.forName("java.lang.invoke.MethodHandle");
                        return new ForJava8CapableVm(Class.forName("java.lang.invoke.MethodHandles$Lookup").getMethod("findVirtual", Class.class, String.class, cls).invoke(ByteArrayClassLoader.a(), ClassLoader.class, "getClassLoadingLock", cls.getMethod("methodType", Class.class, Class[].class).invoke(null, Object.class, new Class[]{String.class})), cls2.getMethod("bindTo", Object.class), cls2.getMethod("invokeWithArguments", Object[].class));
                    } catch (Exception unused) {
                        return (ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V5).isAtLeast(ClassFileVersion.JAVA_V9) && ByteArrayClassLoader.class.getClassLoader() == null) ? ForLegacyVm.INSTANCE : new ForJava7CapableVm(ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class));
                    }
                } catch (Exception unused2) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SynchronizationStrategy$ForLegacyVm.class */
        public enum ForLegacyVm implements SynchronizationStrategy, Initializable {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy
            public final Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str) {
                return byteArrayClassLoader;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.Initializable
            public final SynchronizationStrategy initialize() {
                return this;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SynchronizationStrategy$ForJava7CapableVm.class */
        public static class ForJava7CapableVm implements SynchronizationStrategy, Initializable {
            private final Method method;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.method.equals(((ForJava7CapableVm) obj).method);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.method.hashCode();
            }

            protected ForJava7CapableVm(Method method) {
                this.method = method;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy
            public Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str) {
                try {
                    return this.method.invoke(byteArrayClassLoader, str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException(e2.getTargetException());
                }
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.Initializable
            @SuppressFBWarnings(value = {"DP_DO_INSIDE_DO_PRIVILEGED"}, justification = "Assuring privilege is explicit user responsibility.")
            public SynchronizationStrategy initialize() {
                try {
                    this.method.setAccessible(true);
                    return this;
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SynchronizationStrategy$ForJava8CapableVm.class */
        public static class ForJava8CapableVm implements SynchronizationStrategy, Initializable {
            private final Object methodHandle;
            private final Method bindTo;
            private final Method invokeWithArguments;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodHandle.equals(((ForJava8CapableVm) obj).methodHandle) && this.bindTo.equals(((ForJava8CapableVm) obj).bindTo) && this.invokeWithArguments.equals(((ForJava8CapableVm) obj).invokeWithArguments);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.methodHandle.hashCode()) * 31) + this.bindTo.hashCode()) * 31) + this.invokeWithArguments.hashCode();
            }

            protected ForJava8CapableVm(Object obj, Method method, Method method2) {
                this.methodHandle = obj;
                this.bindTo = method;
                this.invokeWithArguments = method2;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.Initializable
            public SynchronizationStrategy initialize() {
                return this;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy
            public Object getClassLoadingLock(ByteArrayClassLoader byteArrayClassLoader, String str) {
                try {
                    return this.invokeWithArguments.invoke(this.bindTo.invoke(this.methodHandle, byteArrayClassLoader), new Object[]{str});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException(e2.getTargetException());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$ClassDefinitionAction.class */
    public class ClassDefinitionAction implements PrivilegedAction<Class<?>> {
        private final String name;
        private final byte[] binaryRepresentation;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.name.equals(((ClassDefinitionAction) obj).name) && Arrays.equals(this.binaryRepresentation, ((ClassDefinitionAction) obj).binaryRepresentation) && ByteArrayClassLoader.this.equals(ByteArrayClassLoader.this);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.name.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + ByteArrayClassLoader.this.hashCode();
        }

        protected ClassDefinitionAction(String str, byte[] bArr) {
            this.name = str;
            this.binaryRepresentation = bArr;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Class<?> run() {
            int lastIndexOf = this.name.lastIndexOf(46);
            if (lastIndexOf != -1) {
                String substring = this.name.substring(0, lastIndexOf);
                PackageDefinitionStrategy.Definition define = ByteArrayClassLoader.this.packageDefinitionStrategy.define(ByteArrayClassLoader.this, substring, this.name);
                if (define.isDefined()) {
                    Package apply = ByteArrayClassLoader.PACKAGE_LOOKUP_STRATEGY.apply(ByteArrayClassLoader.this, substring);
                    if (apply == null) {
                        ByteArrayClassLoader.this.definePackage(substring, define.getSpecificationTitle(), define.getSpecificationVersion(), define.getSpecificationVendor(), define.getImplementationTitle(), define.getImplementationVersion(), define.getImplementationVendor(), define.getSealBase());
                    } else if (!define.isCompatibleTo(apply)) {
                        throw new SecurityException("Sealing violation for package " + substring);
                    }
                }
            }
            return ByteArrayClassLoader.this.defineClass(this.name, this.binaryRepresentation, 0, this.binaryRepresentation.length, ByteArrayClassLoader.this.protectionDomain);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PackageLookupStrategy.class */
    public interface PackageLookupStrategy {
        @MaybeNull
        Package apply(ByteArrayClassLoader byteArrayClassLoader, String str);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PackageLookupStrategy$CreationAction.class */
        public enum CreationAction implements PrivilegedAction<PackageLookupStrategy> {
            INSTANCE;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
            public final PackageLookupStrategy run() {
                if (JavaModule.isSupported()) {
                    try {
                        return new ForJava9CapableVm(ClassLoader.class.getMethod("getDefinedPackage", String.class));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
                return ForLegacyVm.INSTANCE;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PackageLookupStrategy$ForLegacyVm.class */
        public enum ForLegacyVm implements PackageLookupStrategy {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PackageLookupStrategy
            @MaybeNull
            public final Package apply(ByteArrayClassLoader byteArrayClassLoader, String str) {
                return byteArrayClassLoader.doGetPackage(str);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PackageLookupStrategy$ForJava9CapableVm.class */
        public static class ForJava9CapableVm implements PackageLookupStrategy {
            private final Method getDefinedPackage;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.getDefinedPackage.equals(((ForJava9CapableVm) obj).getDefinedPackage);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.getDefinedPackage.hashCode();
            }

            protected ForJava9CapableVm(Method method) {
                this.getDefinedPackage = method;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PackageLookupStrategy
            @MaybeNull
            public Package apply(ByteArrayClassLoader byteArrayClassLoader, String str) {
                try {
                    return (Package) this.getDefinedPackage.invoke(byteArrayClassLoader, str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException(e2.getTargetException());
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PersistenceHandler.class */
    public enum PersistenceHandler {
        MANIFEST(true) { // from class: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler.1
            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected final byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return concurrentMap.get(str);
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected final URL url(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                if (!str.endsWith(".class")) {
                    return ByteArrayClassLoader.NO_URL;
                }
                if (str.startsWith("/")) {
                    str = str.substring(1);
                }
                byte[] bArr = concurrentMap.get(str.replace('/', '.').substring(0, str.length() - 6));
                return bArr == null ? ByteArrayClassLoader.NO_URL : (URL) ByteArrayClassLoader.doPrivileged(new UrlDefinitionAction(str, bArr));
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected final void release(String str, ConcurrentMap<String, byte[]> concurrentMap) {
            }
        },
        LATENT(false) { // from class: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler.2
            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected final byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return concurrentMap.remove(str);
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected final URL url(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return ByteArrayClassLoader.NO_URL;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected final void release(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                concurrentMap.remove(str);
            }
        };

        private static final String CLASS_FILE_SUFFIX = ".class";
        private final boolean manifest;

        @MaybeNull
        protected abstract byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap);

        @MaybeNull
        protected abstract URL url(String str, ConcurrentMap<String, byte[]> concurrentMap);

        protected abstract void release(String str, ConcurrentMap<String, byte[]> concurrentMap);

        /* synthetic */ PersistenceHandler(boolean z, byte b2) {
            this(z);
        }

        PersistenceHandler(boolean z) {
            this.manifest = z;
        }

        public boolean isManifest() {
            return this.manifest;
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PersistenceHandler$UrlDefinitionAction.class */
        protected static class UrlDefinitionAction implements PrivilegedAction<URL> {
            private static final String ENCODING = "UTF-8";
            private static final int NO_PORT = -1;
            private static final String NO_FILE = "";
            private final String typeName;
            private final byte[] binaryRepresentation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeName.equals(((UrlDefinitionAction) obj).typeName) && Arrays.equals(this.binaryRepresentation, ((UrlDefinitionAction) obj).binaryRepresentation);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.typeName.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
            }

            protected UrlDefinitionAction(String str, byte[] bArr) {
                this.typeName = str;
                this.binaryRepresentation = bArr;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public URL run() {
                try {
                    return new URL(ByteArrayClassLoader.URL_SCHEMA, URLEncoder.encode(this.typeName.replace('.', '/'), ENCODING), -1, "", new ByteArrayUrlStreamHandler(this.binaryRepresentation));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalStateException("Could not find encoding: UTF-8", e);
                } catch (MalformedURLException e2) {
                    throw new IllegalStateException("Cannot create URL for " + this.typeName, e2);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PersistenceHandler$UrlDefinitionAction$ByteArrayUrlStreamHandler.class */
            public static class ByteArrayUrlStreamHandler extends URLStreamHandler {
                private final byte[] binaryRepresentation;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && Arrays.equals(this.binaryRepresentation, ((ByteArrayUrlStreamHandler) obj).binaryRepresentation);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + Arrays.hashCode(this.binaryRepresentation);
                }

                protected ByteArrayUrlStreamHandler(byte[] bArr) {
                    this.binaryRepresentation = bArr;
                }

                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url) {
                    return new ByteArrayUrlConnection(url, new ByteArrayInputStream(this.binaryRepresentation));
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$PersistenceHandler$UrlDefinitionAction$ByteArrayUrlStreamHandler$ByteArrayUrlConnection.class */
                protected static class ByteArrayUrlConnection extends URLConnection {
                    private final InputStream inputStream;

                    protected ByteArrayUrlConnection(URL url, InputStream inputStream) {
                        super(url);
                        this.inputStream = inputStream;
                    }

                    @Override // java.net.URLConnection
                    public void connect() {
                        this.connected = true;
                    }

                    @Override // java.net.URLConnection
                    public InputStream getInputStream() {
                        connect();
                        return this.inputStream;
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$ChildFirst.class */
    public static class ChildFirst extends ByteArrayClassLoader {
        private static final String CLASS_FILE_SUFFIX = ".class";

        static {
            doRegisterAsParallelCapable();
        }

        @SuppressFBWarnings(value = {"DP_DO_INSIDE_DO_PRIVILEGED"}, justification = "Must be invoked from targeting class loader type.")
        private static void doRegisterAsParallelCapable() {
            try {
                Method declaredMethod = ClassLoader.class.getDeclaredMethod("registerAsParallelCapable", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(null, new Object[0]);
            } catch (Throwable unused) {
            }
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map) {
            super(classLoader, map);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map) {
            super(classLoader, z, map);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
            super(classLoader, map, persistenceHandler);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
            super(classLoader, z, map, persistenceHandler);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
            super(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
            super(classLoader, z, map, protectionDomain, persistenceHandler, packageDefinitionStrategy);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFilePostProcessor classFilePostProcessor) {
            super(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, classFilePostProcessor);
        }

        public ChildFirst(@MaybeNull ClassLoader classLoader, boolean z, Map<String, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFilePostProcessor classFilePostProcessor) {
            super(classLoader, z, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, classFilePostProcessor);
        }

        public static Map<TypeDescription, Class<?>> load(@MaybeNull ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return load(classLoader, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, PersistenceHandler.LATENT, PackageDefinitionStrategy.Trivial.INSTANCE, false, true);
        }

        @SuppressFBWarnings(value = {"DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED"}, justification = "Assuring privilege is explicit user responsibility.")
        public static Map<TypeDescription, Class<?>> load(@MaybeNull ClassLoader classLoader, Map<TypeDescription, byte[]> map, @MaybeNull ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, boolean z, boolean z2) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                hashMap.put(entry.getKey().getName(), entry.getValue());
            }
            ChildFirst childFirst = new ChildFirst(classLoader, z2, hashMap, protectionDomain, persistenceHandler, packageDefinitionStrategy, ClassFilePostProcessor.NoOp.INSTANCE);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (TypeDescription typeDescription : map.keySet()) {
                try {
                    Class<?> cls = Class.forName(typeDescription.getName(), false, childFirst);
                    if (!GraalImageCode.getCurrent().isNativeImageExecution() && z && cls.getClassLoader() != childFirst) {
                        throw new IllegalStateException("Class already loaded: " + cls);
                    }
                    linkedHashMap.put(typeDescription, cls);
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Cannot load class " + typeDescription, e);
                }
            }
            return linkedHashMap;
        }

        @Override // java.lang.ClassLoader
        protected Class<?> loadClass(String str, boolean z) {
            synchronized (SYNCHRONIZATION_STRATEGY.initialize().getClassLoadingLock(this, str)) {
                Class<?> findLoadedClass = findLoadedClass(str);
                if (findLoadedClass != null) {
                    return findLoadedClass;
                }
                try {
                    Class<?> findClass = findClass(str);
                    if (z) {
                        resolveClass(findClass);
                    }
                    return findClass;
                } catch (ClassNotFoundException unused) {
                    return super.loadClass(str, z);
                }
            }
        }

        @Override // java.lang.ClassLoader
        public URL getResource(String str) {
            URL url = this.persistenceHandler.url(str, this.typeDefinitions);
            if (url != null || isShadowed(str)) {
                return url;
            }
            return super.getResource(str);
        }

        @Override // java.lang.ClassLoader
        public Enumeration<URL> getResources(String str) {
            URL url = this.persistenceHandler.url(str, this.typeDefinitions);
            if (url != null) {
                return new PrependingEnumeration(url, super.getResources(str));
            }
            return super.getResources(str);
        }

        private boolean isShadowed(String str) {
            if (this.persistenceHandler.isManifest() || !str.endsWith(".class")) {
                return false;
            }
            synchronized (this) {
                String substring = str.replace('/', '.').substring(0, str.length() - 6);
                if (this.typeDefinitions.containsKey(substring)) {
                    return true;
                }
                Class findLoadedClass = findLoadedClass(substring);
                return findLoadedClass != null && findLoadedClass.getClassLoader() == this;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$ChildFirst$PrependingEnumeration.class */
        protected static class PrependingEnumeration implements Enumeration<URL> {

            @MaybeNull
            private URL nextElement;
            private final Enumeration<URL> enumeration;

            protected PrependingEnumeration(URL url, Enumeration<URL> enumeration) {
                this.nextElement = url;
                this.enumeration = enumeration;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.nextElement != null && this.enumeration.hasMoreElements();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Enumeration
            public URL nextElement() {
                if (this.nextElement != null && this.enumeration.hasMoreElements()) {
                    try {
                        return this.nextElement;
                    } finally {
                        this.nextElement = this.enumeration.nextElement();
                    }
                }
                throw new NoSuchElementException();
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$EmptyEnumeration.class */
    protected enum EmptyEnumeration implements Enumeration<URL> {
        INSTANCE;

        @Override // java.util.Enumeration
        public final boolean hasMoreElements() {
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Enumeration
        public final URL nextElement() {
            throw new NoSuchElementException();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ByteArrayClassLoader$SingletonEnumeration.class */
    protected static class SingletonEnumeration implements Enumeration<URL> {

        @MaybeNull
        private URL element;

        protected SingletonEnumeration(URL url) {
            this.element = url;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.element != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Enumeration
        public URL nextElement() {
            if (this.element == null) {
                throw new NoSuchElementException();
            }
            try {
                return this.element;
            } finally {
                this.element = null;
            }
        }
    }
}
