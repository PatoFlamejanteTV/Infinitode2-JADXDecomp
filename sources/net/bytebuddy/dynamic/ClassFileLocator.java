package net.bytebuddy.dynamic;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.StreamDrainer;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator.class */
public interface ClassFileLocator extends Closeable {
    public static final String CLASS_FILE_EXTENSION = ".class";

    Resolution locate(String str);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$Resolution.class */
    public interface Resolution {
        boolean isResolved();

        byte[] resolve();

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$Resolution$Illegal.class */
        public static class Illegal implements Resolution {
            private final String typeName;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeName.equals(((Illegal) obj).typeName);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.typeName.hashCode();
            }

            public Illegal(String str) {
                this.typeName = str;
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public boolean isResolved() {
                return false;
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public byte[] resolve() {
                throw new IllegalStateException("Could not locate class file for " + this.typeName);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$Resolution$Explicit.class */
        public static class Explicit implements Resolution {
            private final byte[] binaryRepresentation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && Arrays.equals(this.binaryRepresentation, ((Explicit) obj).binaryRepresentation);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + Arrays.hashCode(this.binaryRepresentation);
            }

            @SuppressFBWarnings(value = {"EI_EXPOSE_REP2"}, justification = "The array is not modified by class contract.")
            public Explicit(byte[] bArr) {
                this.binaryRepresentation = bArr;
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public boolean isResolved() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            @SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "The array is not modified by class contract.")
            public byte[] resolve() {
                return this.binaryRepresentation;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$NoOp.class */
    public enum NoOp implements ClassFileLocator {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public final Resolution locate(String str) {
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$Simple.class */
    public static class Simple implements ClassFileLocator {
        private final Map<String, byte[]> classFiles;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFiles.equals(((Simple) obj).classFiles);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.classFiles.hashCode();
        }

        public Simple(Map<String, byte[]> map) {
            this.classFiles = map;
        }

        public static ClassFileLocator of(String str, byte[] bArr) {
            return new Simple(Collections.singletonMap(str, bArr));
        }

        public static ClassFileLocator of(DynamicType dynamicType) {
            return of(dynamicType.getAllTypes());
        }

        public static ClassFileLocator of(Map<TypeDescription, byte[]> map) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                hashMap.put(entry.getKey().getName(), entry.getValue());
            }
            return new Simple(hashMap);
        }

        public static ClassFileLocator ofResources(Map<String, byte[]> map) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, byte[]> entry : map.entrySet()) {
                if (entry.getKey().endsWith(".class")) {
                    hashMap.put(entry.getKey().substring(0, entry.getKey().length() - 6).replace('/', '.'), entry.getValue());
                }
            }
            return new Simple(hashMap);
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            byte[] bArr = this.classFiles.get(str);
            return bArr == null ? new Resolution.Illegal(str) : new Resolution.Explicit(bArr);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForClassLoader.class */
    public static class ForClassLoader implements ClassFileLocator {
        private static final ClassLoader BOOT_LOADER_PROXY;
        private final ClassLoader classLoader;
        private static final boolean ACCESS_CONTROLLER;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((ForClassLoader) obj).classLoader);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.classLoader.hashCode();
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
            BOOT_LOADER_PROXY = (ClassLoader) doPrivileged(BootLoaderProxyCreationAction.INSTANCE);
        }

        protected ForClassLoader(ClassLoader classLoader) {
            this.classLoader = classLoader;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static ClassFileLocator ofSystemLoader() {
            return new ForClassLoader(ClassLoader.getSystemClassLoader());
        }

        public static ClassFileLocator ofPlatformLoader() {
            return of(ClassLoader.getSystemClassLoader().getParent());
        }

        public static ClassFileLocator ofBootLoader() {
            return new ForClassLoader(BOOT_LOADER_PROXY);
        }

        public static ClassFileLocator of(@MaybeNull ClassLoader classLoader) {
            return new ForClassLoader(classLoader == null ? BOOT_LOADER_PROXY : classLoader);
        }

        public static byte[] read(Class<?> cls) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                return locate(classLoader == null ? BOOT_LOADER_PROXY : classLoader, TypeDescription.ForLoadedType.getName(cls)).resolve();
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read class file for " + cls, e);
            }
        }

        public static Map<Class<?>, byte[]> read(Class<?>... clsArr) {
            return read(Arrays.asList(clsArr));
        }

        public static Map<Class<?>, byte[]> read(Collection<? extends Class<?>> collection) {
            HashMap hashMap = new HashMap();
            for (Class<?> cls : collection) {
                hashMap.put(cls, read(cls));
            }
            return hashMap;
        }

        public static Map<String, byte[]> readToNames(Class<?>... clsArr) {
            return readToNames(Arrays.asList(clsArr));
        }

        public static Map<String, byte[]> readToNames(Collection<? extends Class<?>> collection) {
            HashMap hashMap = new HashMap();
            for (Class<?> cls : collection) {
                hashMap.put(cls.getName(), read(cls));
            }
            return hashMap;
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            return locate(this.classLoader, str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        protected static Resolution locate(ClassLoader classLoader, String str) {
            InputStream resourceAsStream = classLoader.getResourceAsStream(str.replace('.', '/') + ".class");
            if (resourceAsStream != null) {
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(resourceAsStream));
                } finally {
                    resourceAsStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForClassLoader$BootLoaderProxyCreationAction.class */
        protected enum BootLoaderProxyCreationAction implements PrivilegedAction<ClassLoader> {
            INSTANCE;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public final ClassLoader run() {
                return new URLClassLoader(new URL[0], ClassLoadingStrategy.BOOTSTRAP_LOADER);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForClassLoader$WeaklyReferenced.class */
        public static class WeaklyReferenced extends WeakReference<ClassLoader> implements ClassFileLocator {
            private final int hashCode;

            protected WeaklyReferenced(ClassLoader classLoader) {
                super(classLoader);
                this.hashCode = System.identityHashCode(classLoader);
            }

            public static ClassFileLocator of(@MaybeNull ClassLoader classLoader) {
                return (classLoader == null || classLoader == ClassLoader.getSystemClassLoader() || classLoader == ClassLoader.getSystemClassLoader().getParent()) ? ForClassLoader.of(classLoader) : new WeaklyReferenced(classLoader);
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator
            public Resolution locate(String str) {
                ClassLoader classLoader = (ClassLoader) get();
                return classLoader == null ? new Resolution.Illegal(str) : ForClassLoader.locate(classLoader, str);
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            public int hashCode() {
                return this.hashCode;
            }

            public boolean equals(@MaybeNull Object obj) {
                ClassLoader classLoader;
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && (classLoader = (ClassLoader) ((WeaklyReferenced) obj).get()) != null && get() == classLoader;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForModule.class */
    public static class ForModule implements ClassFileLocator {
        private static final Object[] NO_ARGUMENT = new Object[0];
        private final JavaModule module;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.module.equals(((ForModule) obj).module);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.module.hashCode();
        }

        protected ForModule(JavaModule javaModule) {
            this.module = javaModule;
        }

        @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should always be wrapped for clarity")
        public static ClassFileLocator ofBootLayer() {
            try {
                HashMap hashMap = new HashMap();
                Class<?> cls = Class.forName("java.lang.ModuleLayer");
                Method method = JavaType.MODULE.load().getMethod("getPackages", new Class[0]);
                for (Object obj : (Set) cls.getMethod("modules", new Class[0]).invoke(cls.getMethod("boot", new Class[0]).invoke(null, new Object[0]), new Object[0])) {
                    ClassFileLocator of = of(JavaModule.of(obj));
                    Iterator it = ((Set) method.invoke(obj, NO_ARGUMENT)).iterator();
                    while (it.hasNext()) {
                        hashMap.put((String) it.next(), of);
                    }
                }
                return new PackageDiscriminating(hashMap);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot process boot layer", e);
            }
        }

        public static ClassFileLocator of(JavaModule javaModule) {
            return javaModule.isNamed() ? new ForModule(javaModule) : ForClassLoader.of(javaModule.getClassLoader());
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            return locate(this.module, str);
        }

        protected static Resolution locate(JavaModule javaModule, String str) {
            InputStream resourceAsStream = javaModule.getResourceAsStream(str.replace('.', '/') + ".class");
            if (resourceAsStream != null) {
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(resourceAsStream));
                } finally {
                    resourceAsStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForModule$WeaklyReferenced.class */
        public static class WeaklyReferenced extends WeakReference<Object> implements ClassFileLocator {
            private final int hashCode;

            protected WeaklyReferenced(Object obj) {
                super(obj);
                this.hashCode = System.identityHashCode(obj);
            }

            public static ClassFileLocator of(JavaModule javaModule) {
                if (javaModule.isNamed()) {
                    return (javaModule.getClassLoader() == null || javaModule.getClassLoader() == ClassLoader.getSystemClassLoader() || javaModule.getClassLoader() == ClassLoader.getSystemClassLoader().getParent()) ? new ForModule(javaModule) : new WeaklyReferenced(javaModule.unwrap());
                }
                return ForClassLoader.WeaklyReferenced.of(javaModule.getClassLoader());
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator
            public Resolution locate(String str) {
                Object obj = get();
                return obj == null ? new Resolution.Illegal(str) : ForModule.locate(JavaModule.of(obj), str);
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            public int hashCode() {
                return this.hashCode;
            }

            public boolean equals(@MaybeNull Object obj) {
                Object obj2;
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && (obj2 = ((WeaklyReferenced) obj).get()) != null && get() == obj2;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForJarFile.class */
    public static class ForJarFile implements ClassFileLocator {
        private static final List<String> RUNTIME_LOCATIONS = Arrays.asList("lib/rt.jar", "../lib/rt.jar", "../Classes/classes.jar");
        private final JarFile jarFile;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.jarFile.equals(((ForJarFile) obj).jarFile);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.jarFile.hashCode();
        }

        public ForJarFile(JarFile jarFile) {
            this.jarFile = jarFile;
        }

        public static ClassFileLocator of(File file) {
            return new ForJarFile(new JarFile(file));
        }

        public static ClassFileLocator ofClassPath() {
            return ofClassPath(System.getProperty("java.class.path"));
        }

        public static ClassFileLocator ofClassPath(String str) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : Pattern.compile(System.getProperty("path.separator"), 16).split(str)) {
                File file = new File(str2);
                if (file.isDirectory()) {
                    arrayList.add(new ForFolder(file));
                } else if (file.isFile()) {
                    arrayList.add(of(file));
                }
            }
            return new Compound(arrayList);
        }

        public static ClassFileLocator ofRuntimeJar() {
            String replace = System.getProperty("java.home").replace('\\', '/');
            File file = null;
            Iterator<String> it = RUNTIME_LOCATIONS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                File file2 = new File(replace, it.next());
                if (file2.isFile()) {
                    file = file2;
                    break;
                }
            }
            if (file == null) {
                throw new IllegalStateException("Runtime jar does not exist in " + replace + " for any of " + RUNTIME_LOCATIONS);
            }
            return of(file);
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            ZipEntry entry = this.jarFile.getEntry(str.replace('.', '/') + ".class");
            if (entry != null) {
                InputStream inputStream = this.jarFile.getInputStream(entry);
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(inputStream));
                } finally {
                    inputStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.jarFile.close();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForModuleFile.class */
    public static class ForModuleFile implements ClassFileLocator {
        private static final String JMOD_FILE_EXTENSION = ".jmod";
        private static final List<String> BOOT_LOCATIONS = Arrays.asList("jmods", "../jmods", "modules");
        private final ZipFile zipFile;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.zipFile.equals(((ForModuleFile) obj).zipFile);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.zipFile.hashCode();
        }

        public ForModuleFile(ZipFile zipFile) {
            this.zipFile = zipFile;
        }

        public static ClassFileLocator ofBootPath() {
            String replace = System.getProperty("java.home").replace('\\', '/');
            File file = null;
            Iterator<String> it = BOOT_LOCATIONS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                File file2 = new File(replace, it.next());
                if (file2.isDirectory()) {
                    file = file2;
                    break;
                }
            }
            if (file == null) {
                throw new IllegalStateException("Boot modules do not exist in " + replace + " for any of " + BOOT_LOCATIONS);
            }
            return ofBootPath(file);
        }

        public static ClassFileLocator ofBootPath(File file) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return NoOp.INSTANCE;
            }
            ArrayList arrayList = new ArrayList(listFiles.length);
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    arrayList.add(of(file2));
                } else if (file2.isDirectory()) {
                    arrayList.add(new ForFolder(file2));
                }
            }
            return new Compound(arrayList);
        }

        public static ClassFileLocator ofModulePath() {
            String property = System.getProperty("jdk.module.path");
            return property == null ? NoOp.INSTANCE : ofModulePath(property);
        }

        public static ClassFileLocator ofModulePath(String str) {
            return ofModulePath(str, System.getProperty("user.dir"));
        }

        public static ClassFileLocator ofModulePath(String str, String str2) {
            ClassFileLocator of;
            ClassFileLocator of2;
            ArrayList arrayList = new ArrayList();
            for (String str3 : Pattern.compile(System.getProperty("path.separator"), 16).split(str)) {
                File file = new File(str2, str3);
                if (!file.isDirectory()) {
                    if (file.isFile()) {
                        if (file.getName().endsWith(JMOD_FILE_EXTENSION)) {
                            of = of(file);
                        } else {
                            of = ForJarFile.of(file);
                        }
                        arrayList.add(of);
                    }
                } else {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            if (file2.isDirectory()) {
                                arrayList.add(new ForFolder(file2));
                            } else if (file2.isFile()) {
                                if (file2.getName().endsWith(JMOD_FILE_EXTENSION)) {
                                    of2 = of(file2);
                                } else {
                                    of2 = ForJarFile.of(file2);
                                }
                                arrayList.add(of2);
                            }
                        }
                    }
                }
            }
            return new Compound(arrayList);
        }

        public static ClassFileLocator of(File file) {
            return new ForModuleFile(new ZipFile(file));
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            ZipEntry entry = this.zipFile.getEntry("classes/" + str.replace('.', '/') + ".class");
            if (entry != null) {
                InputStream inputStream = this.zipFile.getInputStream(entry);
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(inputStream));
                } finally {
                    inputStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.zipFile.close();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForFolder.class */
    public static class ForFolder implements ClassFileLocator {
        private final File folder;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.folder.equals(((ForFolder) obj).folder);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.folder.hashCode();
        }

        public ForFolder(File file) {
            this.folder = file;
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            File file = new File(this.folder, str.replace('.', File.separatorChar) + ".class");
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(fileInputStream));
                } finally {
                    fileInputStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForUrl.class */
    public static class ForUrl implements ClassFileLocator {
        private final ClassLoader classLoader;
        private static final boolean ACCESS_CONTROLLER;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((ForUrl) obj).classLoader);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.classLoader.hashCode();
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
        }

        public ForUrl(URL... urlArr) {
            this.classLoader = (ClassLoader) doPrivileged(new ClassLoaderCreationAction(urlArr));
        }

        public ForUrl(Collection<? extends URL> collection) {
            this((URL[]) collection.toArray(new URL[0]));
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            return ForClassLoader.locate(this.classLoader, str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.classLoader instanceof Closeable) {
                ((Closeable) this.classLoader).close();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForUrl$ClassLoaderCreationAction.class */
        protected static class ClassLoaderCreationAction implements PrivilegedAction<ClassLoader> {
            private final URL[] url;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && Arrays.equals(this.url, ((ClassLoaderCreationAction) obj).url);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + Arrays.hashCode(this.url);
            }

            protected ClassLoaderCreationAction(URL[] urlArr) {
                this.url = urlArr;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public ClassLoader run() {
                return new URLClassLoader(this.url, ClassLoadingStrategy.BOOTSTRAP_LOADER);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation.class */
    public static class ForInstrumentation implements ClassFileLocator {
        private static final Dispatcher DISPATCHER;
        private final Instrumentation instrumentation;
        private final ClassLoadingDelegate classLoadingDelegate;
        private static final boolean ACCESS_CONTROLLER;

        @JavaDispatcher.Proxied("java.lang.instrument.Instrumentation")
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$Dispatcher.class */
        protected interface Dispatcher {
            @JavaDispatcher.Proxied("isRetransformClassesSupported")
            boolean isRetransformClassesSupported(Instrumentation instrumentation);

            @JavaDispatcher.Proxied("addTransformer")
            void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z);

            @JavaDispatcher.Proxied("retransformClasses")
            void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr);
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.instrumentation.equals(((ForInstrumentation) obj).instrumentation) && this.classLoadingDelegate.equals(((ForInstrumentation) obj).classLoadingDelegate);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.instrumentation.hashCode()) * 31) + this.classLoadingDelegate.hashCode();
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

        public ForInstrumentation(Instrumentation instrumentation, @MaybeNull ClassLoader classLoader) {
            this(instrumentation, ClassLoadingDelegate.Default.of(classLoader));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @AccessControllerPlugin.Enhance
        public static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public ForInstrumentation(Instrumentation instrumentation, ClassLoadingDelegate classLoadingDelegate) {
            if (!DISPATCHER.isRetransformClassesSupported(instrumentation)) {
                throw new IllegalArgumentException(instrumentation + " does not support retransformation");
            }
            this.instrumentation = instrumentation;
            this.classLoadingDelegate = classLoadingDelegate;
        }

        private static Instrumentation resolveByteBuddyAgentInstrumentation() {
            Instrumentation instrumentation;
            try {
                Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass("net.bytebuddy.agent.Installer");
                JavaModule ofType = JavaModule.ofType(AgentBuilder.class);
                JavaModule ofType2 = JavaModule.ofType(loadClass);
                if (ofType != null && !ofType.canRead(ofType2)) {
                    Class<?> cls = Class.forName("java.lang.Module");
                    cls.getMethod("addReads", cls).invoke(ofType.unwrap(), ofType2.unwrap());
                }
                instrumentation = (Instrumentation) loadClass.getMethod("getInstrumentation", new Class[0]).invoke(null, new Object[0]);
                return instrumentation;
            } catch (RuntimeException e) {
                throw instrumentation;
            } catch (Exception e2) {
                throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
            }
        }

        public static ClassFileLocator fromInstalledAgent(@MaybeNull ClassLoader classLoader) {
            return new ForInstrumentation(resolveByteBuddyAgentInstrumentation(), classLoader);
        }

        public static ClassFileLocator of(Instrumentation instrumentation, Class<?> cls) {
            return new ForInstrumentation(instrumentation, ClassLoadingDelegate.Explicit.of(cls));
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [net.bytebuddy.dynamic.ClassFileLocator$ForInstrumentation$Dispatcher, java.lang.Throwable] */
        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            ?? r0;
            try {
                ExtractionClassFileTransformer extractionClassFileTransformer = new ExtractionClassFileTransformer(this.classLoadingDelegate.getClassLoader(), str);
                r0 = DISPATCHER;
                r0.addTransformer(this.instrumentation, extractionClassFileTransformer, true);
                try {
                    DISPATCHER.retransformClasses(this.instrumentation, new Class[]{this.classLoadingDelegate.locate(str)});
                    byte[] binaryRepresentation = extractionClassFileTransformer.getBinaryRepresentation();
                    return binaryRepresentation == null ? new Resolution.Illegal(str) : new Resolution.Explicit(binaryRepresentation);
                } finally {
                    this.instrumentation.removeTransformer(extractionClassFileTransformer);
                }
            } catch (RuntimeException e) {
                throw r0;
            } catch (Exception unused) {
                return new Resolution.Illegal(str);
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate.class */
        public interface ClassLoadingDelegate {
            Class<?> locate(String str);

            @MaybeNull
            ClassLoader getClassLoader();

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$Default.class */
            public static class Default implements ClassLoadingDelegate {
                private static final ClassLoader BOOT_LOADER_PROXY = (ClassLoader) ForInstrumentation.doPrivileged(BootLoaderProxyCreationAction.INSTANCE);
                protected final ClassLoader classLoader;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((Default) obj).classLoader);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.classLoader.hashCode();
                }

                protected Default(ClassLoader classLoader) {
                    this.classLoader = classLoader;
                }

                public static ClassLoadingDelegate of(@MaybeNull ClassLoader classLoader) {
                    if (ForDelegatingClassLoader.isDelegating(classLoader)) {
                        return new ForDelegatingClassLoader(classLoader);
                    }
                    return new Default(classLoader == null ? BOOT_LOADER_PROXY : classLoader);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate
                public Class<?> locate(String str) {
                    return this.classLoader.loadClass(str);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate
                @MaybeNull
                public ClassLoader getClassLoader() {
                    return this.classLoader == BOOT_LOADER_PROXY ? ClassLoadingStrategy.BOOTSTRAP_LOADER : this.classLoader;
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$Default$BootLoaderProxyCreationAction.class */
                protected enum BootLoaderProxyCreationAction implements PrivilegedAction<ClassLoader> {
                    INSTANCE;

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.security.PrivilegedAction
                    public final ClassLoader run() {
                        return new URLClassLoader(new URL[0], ClassLoadingStrategy.BOOTSTRAP_LOADER);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$ForDelegatingClassLoader.class */
            public static class ForDelegatingClassLoader extends Default {
                private static final String DELEGATING_CLASS_LOADER_NAME = "sun.reflect.DelegatingClassLoader";
                private static final int ONLY = 0;
                private static final Dispatcher.Initializable DISPATCHER;
                private static final boolean ACCESS_CONTROLLER;

                static {
                    try {
                        Class.forName("java.security.AccessController", false, null);
                        ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
                    } catch (ClassNotFoundException unused) {
                        ACCESS_CONTROLLER = false;
                    } catch (SecurityException unused2) {
                        ACCESS_CONTROLLER = true;
                    }
                    DISPATCHER = (Dispatcher.Initializable) doPrivileged(Dispatcher.CreationAction.INSTANCE);
                }

                protected ForDelegatingClassLoader(ClassLoader classLoader) {
                    super(classLoader);
                }

                @AccessControllerPlugin.Enhance
                private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
                    return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
                }

                protected static boolean isDelegating(@MaybeNull ClassLoader classLoader) {
                    return classLoader != null && classLoader.getClass().getName().equals(DELEGATING_CLASS_LOADER_NAME);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate.Default, net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate
                public Class<?> locate(String str) {
                    try {
                        Vector<Class<?>> extract = DISPATCHER.initialize().extract(this.classLoader);
                        if (extract.size() != 1) {
                            return super.locate(str);
                        }
                        Class<?> cls = extract.get(0);
                        if (TypeDescription.ForLoadedType.getName(cls).equals(str)) {
                            return cls;
                        }
                        return super.locate(str);
                    } catch (RuntimeException unused) {
                        return super.locate(str);
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$ForDelegatingClassLoader$Dispatcher.class */
                protected interface Dispatcher {

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$ForDelegatingClassLoader$Dispatcher$Initializable.class */
                    public interface Initializable {
                        Dispatcher initialize();
                    }

                    Vector<Class<?>> extract(ClassLoader classLoader);

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$ForDelegatingClassLoader$Dispatcher$CreationAction.class */
                    public enum CreationAction implements PrivilegedAction<Initializable> {
                        INSTANCE;

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.security.PrivilegedAction
                        public final Initializable run() {
                            try {
                                return new Resolved(ClassLoader.class.getDeclaredField("classes"));
                            } catch (Exception e) {
                                return new Unresolved(e.getMessage());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$ForDelegatingClassLoader$Dispatcher$Resolved.class */
                    public static class Resolved implements PrivilegedAction<Dispatcher>, Dispatcher, Initializable {
                        private final Field field;
                        private static final boolean ACCESS_CONTROLLER;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.field.equals(((Resolved) obj).field);
                        }

                        public int hashCode() {
                            return (getClass().hashCode() * 31) + this.field.hashCode();
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
                        }

                        public Resolved(Field field) {
                            this.field = field;
                        }

                        @AccessControllerPlugin.Enhance
                        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
                            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
                        }

                        @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate.ForDelegatingClassLoader.Dispatcher.Initializable
                        public Dispatcher initialize() {
                            return (Dispatcher) doPrivileged(this);
                        }

                        @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate.ForDelegatingClassLoader.Dispatcher
                        public Vector<Class<?>> extract(ClassLoader classLoader) {
                            try {
                                return (Vector) this.field.get(classLoader);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access field", e);
                            }
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.security.PrivilegedAction
                        public Dispatcher run() {
                            this.field.setAccessible(true);
                            return this;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$ForDelegatingClassLoader$Dispatcher$Unresolved.class */
                    public static class Unresolved implements Initializable {
                        private final String message;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.message.equals(((Unresolved) obj).message);
                        }

                        public int hashCode() {
                            return (getClass().hashCode() * 31) + this.message.hashCode();
                        }

                        public Unresolved(String str) {
                            this.message = str;
                        }

                        @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate.ForDelegatingClassLoader.Dispatcher.Initializable
                        public Dispatcher initialize() {
                            throw new UnsupportedOperationException("Could not locate classes vector: " + this.message);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ClassLoadingDelegate$Explicit.class */
            public static class Explicit implements ClassLoadingDelegate {
                private final ClassLoadingDelegate fallbackDelegate;
                private final Map<String, Class<?>> types;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fallbackDelegate.equals(((Explicit) obj).fallbackDelegate) && this.types.equals(((Explicit) obj).types);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.fallbackDelegate.hashCode()) * 31) + this.types.hashCode();
                }

                public Explicit(@MaybeNull ClassLoader classLoader, Collection<? extends Class<?>> collection) {
                    this(Default.of(classLoader), collection);
                }

                public Explicit(ClassLoadingDelegate classLoadingDelegate, Collection<? extends Class<?>> collection) {
                    this.fallbackDelegate = classLoadingDelegate;
                    this.types = new HashMap();
                    for (Class<?> cls : collection) {
                        this.types.put(TypeDescription.ForLoadedType.getName(cls), cls);
                    }
                }

                public static ClassLoadingDelegate of(Class<?> cls) {
                    return new Explicit(cls.getClassLoader(), Collections.singleton(cls));
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate
                public Class<?> locate(String str) {
                    Class<?> cls = this.types.get(str);
                    if (cls != null) {
                        return cls;
                    }
                    return this.fallbackDelegate.locate(str);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.ForInstrumentation.ClassLoadingDelegate
                @MaybeNull
                public ClassLoader getClassLoader() {
                    return this.fallbackDelegate.getClassLoader();
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$ForInstrumentation$ExtractionClassFileTransformer.class */
        protected static class ExtractionClassFileTransformer implements ClassFileTransformer {

            @AlwaysNull
            private static final byte[] DO_NOT_TRANSFORM = null;

            @MaybeNull
            private final ClassLoader classLoader;
            private final String typeName;

            @MaybeNull
            @SuppressFBWarnings(value = {"VO_VOLATILE_REFERENCE_TO_ARRAY"}, justification = "The array is not to be modified by contract")
            private volatile byte[] binaryRepresentation;

            protected ExtractionClassFileTransformer(@MaybeNull ClassLoader classLoader, String str) {
                this.classLoader = classLoader;
                this.typeName = str;
            }

            @MaybeNull
            @SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"}, justification = "The array is not modified by class contract.")
            public byte[] transform(@MaybeNull ClassLoader classLoader, @MaybeNull String str, @MaybeNull Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (str != null && ElementMatchers.isChildOf(this.classLoader).matches(classLoader) && this.typeName.equals(str.replace('/', '.'))) {
                    this.binaryRepresentation = (byte[]) bArr.clone();
                }
                return DO_NOT_TRANSFORM;
            }

            @MaybeNull
            @SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "The array is not to be modified by contract")
            protected byte[] getBinaryRepresentation() {
                return this.binaryRepresentation;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$PackageDiscriminating.class */
    public static class PackageDiscriminating implements ClassFileLocator {
        private final Map<String, ClassFileLocator> classFileLocators;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFileLocators.equals(((PackageDiscriminating) obj).classFileLocators);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.classFileLocators.hashCode();
        }

        public PackageDiscriminating(Map<String, ClassFileLocator> map) {
            this.classFileLocators = map;
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            int lastIndexOf = str.lastIndexOf(46);
            ClassFileLocator classFileLocator = this.classFileLocators.get(lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf));
            return classFileLocator == null ? new Resolution.Illegal(str) : classFileLocator.locate(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Iterator<ClassFileLocator> it = this.classFileLocators.values().iterator();
            while (it.hasNext()) {
                it.next().close();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/ClassFileLocator$Compound.class */
    public static class Compound implements Closeable, ClassFileLocator {
        private final List<ClassFileLocator> classFileLocators;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFileLocators.equals(((Compound) obj).classFileLocators);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.classFileLocators.hashCode();
        }

        public Compound(ClassFileLocator... classFileLocatorArr) {
            this((List<? extends ClassFileLocator>) Arrays.asList(classFileLocatorArr));
        }

        public Compound(List<? extends ClassFileLocator> list) {
            this.classFileLocators = new ArrayList();
            for (ClassFileLocator classFileLocator : list) {
                if (classFileLocator instanceof Compound) {
                    this.classFileLocators.addAll(((Compound) classFileLocator).classFileLocators);
                } else if (!(classFileLocator instanceof NoOp)) {
                    this.classFileLocators.add(classFileLocator);
                }
            }
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            Iterator<ClassFileLocator> it = this.classFileLocators.iterator();
            while (it.hasNext()) {
                Resolution locate = it.next().locate(str);
                if (locate.isResolved()) {
                    return locate;
                }
            }
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Iterator<ClassFileLocator> it = this.classFileLocators.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
        }
    }
}
