package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy.class */
public class ClassReloadingStrategy implements ClassLoadingStrategy<ClassLoader> {
    protected static final Dispatcher DISPATCHER;
    private final Instrumentation instrumentation;
    private final Strategy strategy;
    private final BootstrapInjection bootstrapInjection;
    private final Map<String, Class<?>> preregisteredTypes;
    private static final boolean ACCESS_CONTROLLER;

    /* JADX INFO: Access modifiers changed from: protected */
    @JavaDispatcher.Proxied("java.lang.instrument.Instrumentation")
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$Dispatcher.class */
    public interface Dispatcher {
        @JavaDispatcher.Proxied("isModifiableClass")
        boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls);

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
        return obj != null && getClass() == obj.getClass() && this.strategy.equals(((ClassReloadingStrategy) obj).strategy) && this.instrumentation.equals(((ClassReloadingStrategy) obj).instrumentation) && this.bootstrapInjection.equals(((ClassReloadingStrategy) obj).bootstrapInjection) && this.preregisteredTypes.equals(((ClassReloadingStrategy) obj).preregisteredTypes);
    }

    public int hashCode() {
        return (((((((getClass().hashCode() * 31) + this.instrumentation.hashCode()) * 31) + this.strategy.hashCode()) * 31) + this.bootstrapInjection.hashCode()) * 31) + this.preregisteredTypes.hashCode();
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

    public ClassReloadingStrategy(Instrumentation instrumentation, Strategy strategy) {
        this(instrumentation, strategy, BootstrapInjection.Disabled.INSTANCE, Collections.emptyMap());
    }

    protected ClassReloadingStrategy(Instrumentation instrumentation, Strategy strategy, BootstrapInjection bootstrapInjection, Map<String, Class<?>> map) {
        this.instrumentation = instrumentation;
        this.strategy = strategy.validate(instrumentation);
        this.bootstrapInjection = bootstrapInjection;
        this.preregisteredTypes = map;
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    public static ClassReloadingStrategy of(Instrumentation instrumentation) {
        if (DISPATCHER.isRetransformClassesSupported(instrumentation)) {
            return new ClassReloadingStrategy(instrumentation, Strategy.RETRANSFORMATION);
        }
        if (instrumentation.isRedefineClassesSupported()) {
            return new ClassReloadingStrategy(instrumentation, Strategy.REDEFINITION);
        }
        throw new IllegalArgumentException("Instrumentation does not support reloading of classes: " + instrumentation);
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

    public static ClassReloadingStrategy fromInstalledAgent() {
        return of(resolveByteBuddyAgentInstrumentation());
    }

    public static ClassReloadingStrategy fromInstalledAgent(Strategy strategy) {
        return new ClassReloadingStrategy(resolveByteBuddyAgentInstrumentation(), strategy);
    }

    @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
    public Map<TypeDescription, Class<?>> load(@MaybeNull ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
        HashMap hashMap = new HashMap(this.preregisteredTypes);
        for (Class cls : this.instrumentation.getInitiatedClasses(classLoader)) {
            hashMap.put(TypeDescription.ForLoadedType.getName(cls), cls);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap hashMap2 = new HashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
            Class cls2 = (Class) hashMap.get(entry.getKey().getName());
            if (cls2 != null) {
                concurrentHashMap.put(cls2, new ClassDefinition(cls2, entry.getValue()));
                hashMap2.put(entry.getKey(), cls2);
            } else {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        try {
            this.strategy.apply(this.instrumentation, concurrentHashMap);
            if (!linkedHashMap.isEmpty()) {
                hashMap2.putAll((classLoader == null ? this.bootstrapInjection.make(this.instrumentation) : new ClassInjector.UsingReflection(classLoader)).inject(linkedHashMap));
            }
            return hashMap2;
        } catch (UnmodifiableClassException e) {
            throw new IllegalStateException("Cannot redefine specified class", e);
        } catch (ClassNotFoundException e2) {
            throw new IllegalArgumentException("Could not locate classes for redefinition", e2);
        }
    }

    public ClassReloadingStrategy reset(Class<?>... clsArr) {
        return clsArr.length == 0 ? this : reset(ClassFileLocator.ForClassLoader.of(clsArr[0].getClassLoader()), clsArr);
    }

    public ClassReloadingStrategy reset(ClassFileLocator classFileLocator, Class<?>... clsArr) {
        if (clsArr.length > 0) {
            try {
                this.strategy.reset(this.instrumentation, classFileLocator, Arrays.asList(clsArr));
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Cannot locate types " + Arrays.toString(clsArr), e);
            } catch (UnmodifiableClassException e2) {
                throw new IllegalStateException("Cannot reset types " + Arrays.toString(clsArr), e2);
            }
        }
        return this;
    }

    public ClassReloadingStrategy enableBootstrapInjection(File file) {
        return new ClassReloadingStrategy(this.instrumentation, this.strategy, new BootstrapInjection.Enabled(file), this.preregisteredTypes);
    }

    public ClassReloadingStrategy preregistered(Class<?>... clsArr) {
        HashMap hashMap = new HashMap(this.preregisteredTypes);
        for (Class<?> cls : clsArr) {
            hashMap.put(TypeDescription.ForLoadedType.getName(cls), cls);
        }
        return new ClassReloadingStrategy(this.instrumentation, this.strategy, this.bootstrapInjection, hashMap);
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$Strategy.class */
    public enum Strategy {
        REDEFINITION(true) { // from class: net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy.1
            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected final void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) {
                instrumentation.redefineClasses((ClassDefinition[]) map.values().toArray(new ClassDefinition[0]));
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected final Strategy validate(Instrumentation instrumentation) {
                if (!instrumentation.isRedefineClassesSupported()) {
                    throw new IllegalArgumentException("Does not support redefinition: " + instrumentation);
                }
                return this;
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            public final void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) {
                HashMap hashMap = new HashMap(list.size());
                for (Class<?> cls : list) {
                    hashMap.put(cls, new ClassDefinition(cls, classFileLocator.locate(TypeDescription.ForLoadedType.getName(cls)).resolve()));
                }
                apply(instrumentation, hashMap);
            }
        },
        RETRANSFORMATION(false) { // from class: net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy.2
            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected final void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) {
                ClassRedefinitionTransformer classRedefinitionTransformer = new ClassRedefinitionTransformer(map);
                synchronized (this) {
                    ClassReloadingStrategy.DISPATCHER.addTransformer(instrumentation, classRedefinitionTransformer, true);
                    try {
                        ClassReloadingStrategy.DISPATCHER.retransformClasses(instrumentation, (Class[]) map.keySet().toArray(new Class[0]));
                    } finally {
                        instrumentation.removeTransformer(classRedefinitionTransformer);
                    }
                }
                classRedefinitionTransformer.assertTransformation();
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected final Strategy validate(Instrumentation instrumentation) {
                if (!ClassReloadingStrategy.DISPATCHER.isRetransformClassesSupported(instrumentation)) {
                    throw new IllegalArgumentException("Does not support retransformation: " + instrumentation);
                }
                return this;
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            public final void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) {
                for (Class<?> cls : list) {
                    if (!ClassReloadingStrategy.DISPATCHER.isModifiableClass(instrumentation, cls)) {
                        throw new IllegalArgumentException("Cannot modify type: " + cls);
                    }
                }
                ClassReloadingStrategy.DISPATCHER.addTransformer(instrumentation, ClassResettingTransformer.INSTANCE, true);
                try {
                    ClassReloadingStrategy.DISPATCHER.retransformClasses(instrumentation, (Class[]) list.toArray(new Class[0]));
                } finally {
                    instrumentation.removeTransformer(ClassResettingTransformer.INSTANCE);
                }
            }
        };


        @AlwaysNull
        private static final byte[] NO_REDEFINITION = null;
        private static final boolean REDEFINE_CLASSES = true;
        private final boolean redefinition;

        protected abstract void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map);

        protected abstract Strategy validate(Instrumentation instrumentation);

        public abstract void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list);

        /* synthetic */ Strategy(boolean z, byte b2) {
            this(z);
        }

        Strategy(boolean z) {
            this.redefinition = z;
        }

        public boolean isRedefinition() {
            return this.redefinition;
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$Strategy$ClassRedefinitionTransformer.class */
        protected static class ClassRedefinitionTransformer implements ClassFileTransformer {
            private final Map<Class<?>, ClassDefinition> redefinedClasses;

            protected ClassRedefinitionTransformer(Map<Class<?>, ClassDefinition> map) {
                this.redefinedClasses = map;
            }

            @MaybeNull
            public byte[] transform(@MaybeNull ClassLoader classLoader, @MaybeNull String str, @MaybeNull Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                ClassDefinition remove;
                if (str != null && (remove = this.redefinedClasses.remove(cls)) != null) {
                    return remove.getDefinitionClassFile();
                }
                return Strategy.NO_REDEFINITION;
            }

            public void assertTransformation() {
                if (!this.redefinedClasses.isEmpty()) {
                    throw new IllegalStateException("Could not transform: " + this.redefinedClasses.keySet());
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$Strategy$ClassResettingTransformer.class */
        protected enum ClassResettingTransformer implements ClassFileTransformer {
            INSTANCE;

            @MaybeNull
            public final byte[] transform(@MaybeNull ClassLoader classLoader, @MaybeNull String str, @MaybeNull Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                return Strategy.NO_REDEFINITION;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$BootstrapInjection.class */
    protected interface BootstrapInjection {
        ClassInjector make(Instrumentation instrumentation);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$BootstrapInjection$Disabled.class */
        public enum Disabled implements BootstrapInjection {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.BootstrapInjection
            public final ClassInjector make(Instrumentation instrumentation) {
                throw new IllegalStateException("Bootstrap injection is not enabled");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/ClassReloadingStrategy$BootstrapInjection$Enabled.class */
        public static class Enabled implements BootstrapInjection {
            private final File folder;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.folder.equals(((Enabled) obj).folder);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.folder.hashCode();
            }

            protected Enabled(File file) {
                this.folder = file;
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.BootstrapInjection
            public ClassInjector make(Instrumentation instrumentation) {
                return ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, instrumentation);
            }
        }
    }
}
