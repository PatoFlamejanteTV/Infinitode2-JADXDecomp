package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.FileSystem;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin.class */
public interface Plugin extends Closeable, ElementMatcher<TypeDescription> {

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$WithInitialization.class */
    public interface WithInitialization extends Plugin {
        Map<TypeDescription, byte[]> initialize(ClassFileLocator classFileLocator);
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$WithPreprocessor.class */
    public interface WithPreprocessor extends Plugin {
        void onPreprocess(TypeDescription typeDescription, ClassFileLocator classFileLocator);
    }

    DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator);

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory.class */
    public interface Factory {
        Plugin make();

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$Simple.class */
        public static class Simple implements Factory {
            private final Plugin plugin;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.plugin.equals(((Simple) obj).plugin);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.plugin.hashCode();
            }

            public Simple(Plugin plugin) {
                this.plugin = plugin;
            }

            @Override // net.bytebuddy.build.Plugin.Factory
            public Plugin make() {
                return this.plugin;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection.class */
        public static class UsingReflection implements Factory {
            private final Class<? extends Plugin> type;
            private final List<ArgumentResolver> argumentResolvers;

            @Target({ElementType.CONSTRUCTOR})
            @Documented
            @Retention(RetentionPolicy.RUNTIME)
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$Priority.class */
            public @interface Priority {
                public static final int DEFAULT = 0;

                int value();
            }

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.type.equals(((UsingReflection) obj).type) && this.argumentResolvers.equals(((UsingReflection) obj).argumentResolvers);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.type.hashCode()) * 31) + this.argumentResolvers.hashCode();
            }

            public UsingReflection(Class<? extends Plugin> cls) {
                this(cls, Collections.emptyList());
            }

            protected UsingReflection(Class<? extends Plugin> cls, List<ArgumentResolver> list) {
                this.type = cls;
                this.argumentResolvers = list;
            }

            public UsingReflection with(ArgumentResolver... argumentResolverArr) {
                return with(Arrays.asList(argumentResolverArr));
            }

            public UsingReflection with(List<? extends ArgumentResolver> list) {
                return new UsingReflection(this.type, CompoundList.of((List) list, (List) this.argumentResolvers));
            }

            @Override // net.bytebuddy.build.Plugin.Factory
            public Plugin make() {
                Instantiator.Unresolved unresolved = new Instantiator.Unresolved(this.type);
                for (Constructor<?> constructor : this.type.getConstructors()) {
                    if (!constructor.isSynthetic()) {
                        ArrayList arrayList = new ArrayList(constructor.getParameterTypes().length);
                        int i = 0;
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        int length = parameterTypes.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                unresolved = unresolved.replaceBy(new Instantiator.Resolved(constructor, arrayList));
                                break;
                            }
                            Class<?> cls = parameterTypes[i2];
                            boolean z = false;
                            Iterator<ArgumentResolver> it = this.argumentResolvers.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                ArgumentResolver.Resolution resolve = it.next().resolve(i, cls);
                                if (resolve.isResolved()) {
                                    arrayList.add(resolve.getArgument());
                                    z = true;
                                    break;
                                }
                            }
                            if (z) {
                                i++;
                                i2++;
                            }
                        }
                    }
                }
                return unresolved.instantiate();
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$Instantiator.class */
            protected interface Instantiator {
                Instantiator replaceBy(Resolved resolved);

                Plugin instantiate();

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$Instantiator$Unresolved.class */
                public static class Unresolved implements Instantiator {
                    private final Class<? extends Plugin> type;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.type.equals(((Unresolved) obj).type);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.type.hashCode();
                    }

                    protected Unresolved(Class<? extends Plugin> cls) {
                        this.type = cls;
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.Instantiator
                    public Instantiator replaceBy(Resolved resolved) {
                        return resolved;
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.Instantiator
                    public Plugin instantiate() {
                        throw new IllegalStateException("No constructor resolvable for " + this.type);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$Instantiator$Ambiguous.class */
                public static class Ambiguous implements Instantiator {
                    private final Constructor<?> left;
                    private final Constructor<?> right;
                    private final int priority;
                    private final int parameters;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.priority == ((Ambiguous) obj).priority && this.parameters == ((Ambiguous) obj).parameters && this.left.equals(((Ambiguous) obj).left) && this.right.equals(((Ambiguous) obj).right);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.left.hashCode()) * 31) + this.right.hashCode()) * 31) + this.priority) * 31) + this.parameters;
                    }

                    protected Ambiguous(Constructor<?> constructor, Constructor<?> constructor2, int i, int i2) {
                        this.left = constructor;
                        this.right = constructor2;
                        this.priority = i;
                        this.parameters = i2;
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.Instantiator
                    public Instantiator replaceBy(Resolved resolved) {
                        Priority priority = (Priority) resolved.getConstructor().getAnnotation(Priority.class);
                        if ((priority == null ? 0 : priority.value()) > this.priority) {
                            return resolved;
                        }
                        if ((priority == null ? 0 : priority.value()) < this.priority) {
                            return this;
                        }
                        if (resolved.getConstructor().getParameterTypes().length > this.parameters) {
                            return resolved;
                        }
                        return this;
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.Instantiator
                    public Plugin instantiate() {
                        throw new IllegalStateException("Ambiguous constructors " + this.left + " and " + this.right);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$Instantiator$Resolved.class */
                public static class Resolved implements Instantiator {
                    private final Constructor<? extends Plugin> constructor;
                    private final List<?> arguments;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.constructor.equals(((Resolved) obj).constructor) && this.arguments.equals(((Resolved) obj).arguments);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.constructor.hashCode()) * 31) + this.arguments.hashCode();
                    }

                    protected Resolved(Constructor<? extends Plugin> constructor, List<?> list) {
                        this.constructor = constructor;
                        this.arguments = list;
                    }

                    protected Constructor<? extends Plugin> getConstructor() {
                        return this.constructor;
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.Instantiator
                    public Instantiator replaceBy(Resolved resolved) {
                        Priority priority = (Priority) this.constructor.getAnnotation(Priority.class);
                        Priority priority2 = (Priority) resolved.getConstructor().getAnnotation(Priority.class);
                        int value = priority == null ? 0 : priority.value();
                        int value2 = priority2 == null ? 0 : priority2.value();
                        if (value > value2) {
                            return this;
                        }
                        if (value < value2) {
                            return resolved;
                        }
                        if (this.constructor.getParameterTypes().length > resolved.getConstructor().getParameterTypes().length) {
                            return this;
                        }
                        if (this.constructor.getParameterTypes().length < resolved.getConstructor().getParameterTypes().length) {
                            return resolved;
                        }
                        return new Ambiguous(this.constructor, resolved.getConstructor(), value, this.constructor.getParameterTypes().length);
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.Instantiator
                    public Plugin instantiate() {
                        try {
                            return this.constructor.newInstance(this.arguments.toArray(new Object[0]));
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Failed to access " + this.constructor, e);
                        } catch (InstantiationException e2) {
                            throw new IllegalStateException("Failed to instantiate plugin via " + this.constructor, e2);
                        } catch (InvocationTargetException e3) {
                            throw new IllegalStateException("Error during construction of" + this.constructor, e3.getTargetException());
                        }
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver.class */
            public interface ArgumentResolver {
                Resolution resolve(int i, Class<?> cls);

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$Resolution.class */
                public interface Resolution {
                    boolean isResolved();

                    @MaybeNull
                    Object getArgument();

                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Unresolved.class */
                    public enum Unresolved implements Resolution {
                        INSTANCE;

                        @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution
                        public final boolean isResolved() {
                            return false;
                        }

                        @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution
                        public final Object getArgument() {
                            throw new IllegalStateException("Cannot get the argument for an unresolved parameter");
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$Resolution$Resolved.class */
                    public static class Resolved implements Resolution {

                        @MaybeNull
                        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                        private final Object argument;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            Object obj2 = this.argument;
                            Object obj3 = ((Resolved) obj).argument;
                            return obj3 != null ? obj2 != null && obj2.equals(obj3) : obj2 == null;
                        }

                        public int hashCode() {
                            int hashCode = getClass().hashCode() * 31;
                            Object obj = this.argument;
                            return obj != null ? hashCode + obj.hashCode() : hashCode;
                        }

                        public Resolved(@MaybeNull Object obj) {
                            this.argument = obj;
                        }

                        @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution
                        public boolean isResolved() {
                            return true;
                        }

                        @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver.Resolution
                        @MaybeNull
                        public Object getArgument() {
                            return this.argument;
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$NoOp.class */
                public enum NoOp implements ArgumentResolver {
                    INSTANCE;

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver
                    public final Resolution resolve(int i, Class<?> cls) {
                        return Resolution.Unresolved.INSTANCE;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$ForType.class */
                public static class ForType<T> implements ArgumentResolver {
                    private final Class<? extends T> type;
                    private final T value;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.type.equals(((ForType) obj).type) && this.value.equals(((ForType) obj).value);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.type.hashCode()) * 31) + this.value.hashCode();
                    }

                    protected ForType(Class<? extends T> cls, T t) {
                        this.type = cls;
                        this.value = t;
                    }

                    public static <S> ArgumentResolver of(Class<? extends S> cls, S s) {
                        return new ForType(cls, s);
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver
                    public Resolution resolve(int i, Class<?> cls) {
                        return cls == this.type ? new Resolution.Resolved(this.value) : Resolution.Unresolved.INSTANCE;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$ForIndex.class */
                public static class ForIndex implements ArgumentResolver {
                    private static final Map<Class<?>, Class<?>> WRAPPER_TYPES;
                    private final int index;

                    @MaybeNull
                    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                    private final Object value;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass() || this.index != ((ForIndex) obj).index) {
                            return false;
                        }
                        Object obj2 = this.value;
                        Object obj3 = ((ForIndex) obj).value;
                        return obj3 != null ? obj2 != null && obj2.equals(obj3) : obj2 == null;
                    }

                    public int hashCode() {
                        int hashCode = ((getClass().hashCode() * 31) + this.index) * 31;
                        Object obj = this.value;
                        return obj != null ? hashCode + obj.hashCode() : hashCode;
                    }

                    static {
                        HashMap hashMap = new HashMap();
                        WRAPPER_TYPES = hashMap;
                        hashMap.put(Boolean.TYPE, Boolean.class);
                        WRAPPER_TYPES.put(Byte.TYPE, Byte.class);
                        WRAPPER_TYPES.put(Short.TYPE, Short.class);
                        WRAPPER_TYPES.put(Character.TYPE, Character.class);
                        WRAPPER_TYPES.put(Integer.TYPE, Integer.class);
                        WRAPPER_TYPES.put(Long.TYPE, Long.class);
                        WRAPPER_TYPES.put(Float.TYPE, Float.class);
                        WRAPPER_TYPES.put(Double.TYPE, Double.class);
                    }

                    public ForIndex(int i, @MaybeNull Object obj) {
                        this.index = i;
                        this.value = obj;
                    }

                    @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver
                    public Resolution resolve(int i, Class<?> cls) {
                        if (this.index != i) {
                            return Resolution.Unresolved.INSTANCE;
                        }
                        return cls.isPrimitive() ? WRAPPER_TYPES.get(cls).isInstance(this.value) ? new Resolution.Resolved(this.value) : Resolution.Unresolved.INSTANCE : (this.value == null || cls.isInstance(this.value)) ? new Resolution.Resolved(this.value) : Resolution.Unresolved.INSTANCE;
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Factory$UsingReflection$ArgumentResolver$ForIndex$WithDynamicType.class */
                    public static class WithDynamicType implements ArgumentResolver {
                        private final int index;

                        @MaybeNull
                        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                        private final String value;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass() || this.index != ((WithDynamicType) obj).index) {
                                return false;
                            }
                            String str = this.value;
                            String str2 = ((WithDynamicType) obj).value;
                            return str2 != null ? str != null && str.equals(str2) : str == null;
                        }

                        public int hashCode() {
                            int hashCode = ((getClass().hashCode() * 31) + this.index) * 31;
                            String str = this.value;
                            return str != null ? hashCode + str.hashCode() : hashCode;
                        }

                        public WithDynamicType(int i, @MaybeNull String str) {
                            this.index = i;
                            this.value = str;
                        }

                        @Override // net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver
                        public Resolution resolve(int i, Class<?> cls) {
                            if (this.index != i) {
                                return Resolution.Unresolved.INSTANCE;
                            }
                            if (cls == Character.TYPE || cls == Character.class) {
                                return (this.value == null || this.value.length() != 1) ? Resolution.Unresolved.INSTANCE : new Resolution.Resolved(Character.valueOf(this.value.charAt(0)));
                            }
                            if (cls == String.class) {
                                return new Resolution.Resolved(this.value);
                            }
                            if (cls.isPrimitive()) {
                                cls = (Class) ForIndex.WRAPPER_TYPES.get(cls);
                            }
                            try {
                                Method method = cls.getMethod("valueOf", String.class);
                                return (Modifier.isStatic(method.getModifiers()) && cls.isAssignableFrom(method.getReturnType())) ? new Resolution.Resolved(method.invoke(null, this.value)) : Resolution.Unresolved.INSTANCE;
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException(e);
                            } catch (NoSuchMethodException unused) {
                                return Resolution.Unresolved.INSTANCE;
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException(e2.getTargetException());
                            }
                        }
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine.class */
    public interface Engine {
        public static final String CLASS_FILE_EXTENSION = ".class";
        public static final String MODULE_INFO = "module-info.class";
        public static final String PACKAGE_INFO = "package-info.class";
        public static final String PLUGIN_FILE = "META-INF/net.bytebuddy/build.plugins";

        Engine with(ByteBuddy byteBuddy);

        Engine with(TypeStrategy typeStrategy);

        Engine with(PoolStrategy poolStrategy);

        Engine with(ClassFileLocator classFileLocator);

        Engine with(Listener listener);

        Engine withoutErrorHandlers();

        Engine withErrorHandlers(ErrorHandler... errorHandlerArr);

        Engine withErrorHandlers(List<? extends ErrorHandler> list);

        Engine withParallelTransformation(int i);

        Engine with(Dispatcher.Factory factory);

        Engine ignore(ElementMatcher<? super TypeDescription> elementMatcher);

        Summary apply(File file, File file2, Factory... factoryArr);

        Summary apply(File file, File file2, List<? extends Factory> list);

        Summary apply(Source source, Target target, Factory... factoryArr);

        Summary apply(Source source, Target target, List<? extends Factory> list);

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$TypeStrategy.class */
        public interface TypeStrategy {
            DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator);

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$TypeStrategy$Default.class */
            public enum Default implements TypeStrategy {
                REDEFINE { // from class: net.bytebuddy.build.Plugin.Engine.TypeStrategy.Default.1
                    @Override // net.bytebuddy.build.Plugin.Engine.TypeStrategy
                    public final DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                        return byteBuddy.redefine(typeDescription, classFileLocator);
                    }
                },
                REBASE { // from class: net.bytebuddy.build.Plugin.Engine.TypeStrategy.Default.2
                    @Override // net.bytebuddy.build.Plugin.Engine.TypeStrategy
                    public final DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                        return byteBuddy.rebase(typeDescription, classFileLocator);
                    }
                },
                DECORATE { // from class: net.bytebuddy.build.Plugin.Engine.TypeStrategy.Default.3
                    @Override // net.bytebuddy.build.Plugin.Engine.TypeStrategy
                    public final DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                        return byteBuddy.decorate(typeDescription, classFileLocator);
                    }
                };

                /* synthetic */ Default(byte b2) {
                    this();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$TypeStrategy$ForEntryPoint.class */
            public static class ForEntryPoint implements TypeStrategy {
                private final EntryPoint entryPoint;
                private final MethodNameTransformer methodNameTransformer;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.entryPoint.equals(((ForEntryPoint) obj).entryPoint) && this.methodNameTransformer.equals(((ForEntryPoint) obj).methodNameTransformer);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.entryPoint.hashCode()) * 31) + this.methodNameTransformer.hashCode();
                }

                public ForEntryPoint(EntryPoint entryPoint, MethodNameTransformer methodNameTransformer) {
                    this.entryPoint = entryPoint;
                    this.methodNameTransformer = methodNameTransformer;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.TypeStrategy
                public DynamicType.Builder<?> builder(ByteBuddy byteBuddy, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
                    return this.entryPoint.transform(typeDescription, byteBuddy, classFileLocator, this.methodNameTransformer);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$PoolStrategy.class */
        public interface PoolStrategy {
            TypePool typePool(ClassFileLocator classFileLocator);

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$PoolStrategy$Default.class */
            public enum Default implements PoolStrategy {
                FAST(TypePool.Default.ReaderMode.FAST),
                EXTENDED(TypePool.Default.ReaderMode.EXTENDED);

                private final TypePool.Default.ReaderMode readerMode;

                Default(TypePool.Default.ReaderMode readerMode) {
                    this.readerMode = readerMode;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.PoolStrategy
                public final TypePool typePool(ClassFileLocator classFileLocator) {
                    return new TypePool.Default.WithLazyResolution(new TypePool.CacheProvider.Simple(), classFileLocator, this.readerMode, TypePool.ClassLoading.ofPlatformLoader());
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$PoolStrategy$Eager.class */
            public enum Eager implements PoolStrategy {
                FAST(TypePool.Default.ReaderMode.FAST),
                EXTENDED(TypePool.Default.ReaderMode.EXTENDED);

                private final TypePool.Default.ReaderMode readerMode;

                Eager(TypePool.Default.ReaderMode readerMode) {
                    this.readerMode = readerMode;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.PoolStrategy
                public final TypePool typePool(ClassFileLocator classFileLocator) {
                    return new TypePool.Default(new TypePool.CacheProvider.Simple(), classFileLocator, this.readerMode, TypePool.ClassLoading.ofPlatformLoader());
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$ErrorHandler.class */
        public interface ErrorHandler {
            void onError(TypeDescription typeDescription, Plugin plugin, Throwable th);

            void onError(TypeDescription typeDescription, List<Throwable> list);

            void onError(Map<TypeDescription, List<Throwable>> map);

            void onError(Plugin plugin, Throwable th);

            void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2);

            void onUnresolved(String str);

            void onManifest(@MaybeNull Manifest manifest);

            void onResource(String str);

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$ErrorHandler$Failing.class */
            public enum Failing implements ErrorHandler {
                FAIL_FAST { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Failing.1
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                        throw new IllegalStateException("Failed to transform " + typeDescription + " using " + plugin, th);
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(TypeDescription typeDescription, List<Throwable> list) {
                        throw new UnsupportedOperationException("onError");
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(Map<TypeDescription, List<Throwable>> map) {
                        throw new UnsupportedOperationException("onError");
                    }
                },
                FAIL_AFTER_TYPE { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Failing.2
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(TypeDescription typeDescription, List<Throwable> list) {
                        throw new IllegalStateException("Failed to transform " + typeDescription + ": " + list);
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(Map<TypeDescription, List<Throwable>> map) {
                        throw new UnsupportedOperationException("onError");
                    }
                },
                FAIL_LAST { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Failing.3
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(TypeDescription typeDescription, List<Throwable> list) {
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onError(Map<TypeDescription, List<Throwable>> map) {
                        throw new IllegalStateException("Failed to transform at least one type: " + map);
                    }
                };

                /* synthetic */ Failing(byte b2) {
                    this();
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    throw new IllegalStateException("Failed to close plugin " + plugin, th);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(Manifest manifest) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$ErrorHandler$Enforcing.class */
            public enum Enforcing implements ErrorHandler {
                ALL_TYPES_RESOLVED { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing.1
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onUnresolved(String str) {
                        throw new IllegalStateException("Failed to resolve type description for " + str);
                    }
                },
                NO_LIVE_INITIALIZERS { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing.2
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                        throw new IllegalStateException("Failed to instrument " + typeDescription + " due to live initializer for " + typeDescription2);
                    }
                },
                CLASS_FILES_ONLY { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing.3
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onResource(String str) {
                        throw new IllegalStateException("Discovered a resource when only class files were allowed: " + str);
                    }
                },
                MANIFEST_REQUIRED { // from class: net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing.4
                    @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler.Enforcing, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                    public final void onManifest(@MaybeNull Manifest manifest) {
                        if (manifest == null) {
                            throw new IllegalStateException("Required a manifest but no manifest was found");
                        }
                    }
                };

                /* synthetic */ Enforcing(byte b2) {
                    this();
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(@MaybeNull Manifest manifest) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$ErrorHandler$Compound.class */
            public static class Compound implements ErrorHandler {
                private final List<ErrorHandler> errorHandlers;

                public Compound(ErrorHandler... errorHandlerArr) {
                    this((List<? extends ErrorHandler>) Arrays.asList(errorHandlerArr));
                }

                public Compound(List<? extends ErrorHandler> list) {
                    this.errorHandlers = new ArrayList();
                    for (ErrorHandler errorHandler : list) {
                        if (errorHandler instanceof Compound) {
                            this.errorHandlers.addAll(((Compound) errorHandler).errorHandlers);
                        } else if (!(errorHandler instanceof Listener.NoOp)) {
                            this.errorHandlers.add(errorHandler);
                        }
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onError(typeDescription, plugin, th);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onError(typeDescription, list);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onError(map);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onError(plugin, th);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onLiveInitializer(typeDescription, typeDescription2);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onUnresolved(str);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(@MaybeNull Manifest manifest) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onManifest(manifest);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                    Iterator<ErrorHandler> it = this.errorHandlers.iterator();
                    while (it.hasNext()) {
                        it.next().onResource(str);
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener.class */
        public interface Listener extends ErrorHandler {
            void onDiscovery(String str);

            void onTransformation(TypeDescription typeDescription, Plugin plugin);

            void onTransformation(TypeDescription typeDescription, List<Plugin> list);

            void onIgnored(TypeDescription typeDescription, Plugin plugin);

            void onIgnored(TypeDescription typeDescription, List<Plugin> list);

            void onComplete(TypeDescription typeDescription);

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$NoOp.class */
            public enum NoOp implements Listener {
                INSTANCE;

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public final void onDiscovery(String str) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public final void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public final void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public final void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public final void onIgnored(TypeDescription typeDescription, List<Plugin> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onError(TypeDescription typeDescription, List<Throwable> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onError(Map<TypeDescription, List<Throwable>> map) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onError(Plugin plugin, Throwable th) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public final void onComplete(TypeDescription typeDescription) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onUnresolved(String str) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onManifest(@MaybeNull Manifest manifest) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public final void onResource(String str) {
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$Adapter.class */
            public static abstract class Adapter implements Listener {
                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onDiscovery(String str) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onIgnored(TypeDescription typeDescription, List<Plugin> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onComplete(TypeDescription typeDescription) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(@MaybeNull Manifest manifest) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$StreamWriting.class */
            public static class StreamWriting extends Adapter {
                protected static final String PREFIX = "[Byte Buddy]";
                private final PrintStream printStream;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.printStream.equals(((StreamWriting) obj).printStream);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.printStream.hashCode();
                }

                public StreamWriting(PrintStream printStream) {
                    this.printStream = printStream;
                }

                public static StreamWriting toSystemOut() {
                    return new StreamWriting(System.out);
                }

                public static StreamWriting toSystemError() {
                    return new StreamWriting(System.err);
                }

                public Listener withTransformationsOnly() {
                    return new WithTransformationsOnly(this);
                }

                public Listener withErrorsOnly() {
                    return new WithErrorsOnly(this);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.Listener
                public void onDiscovery(String str) {
                    this.printStream.printf("[Byte Buddy] DISCOVERY %s", str);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                    this.printStream.printf("[Byte Buddy] TRANSFORM %s for %s", typeDescription, plugin);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.Listener
                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                    this.printStream.printf("[Byte Buddy] IGNORE %s for %s", typeDescription, plugin);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    synchronized (this.printStream) {
                        this.printStream.printf("[Byte Buddy] ERROR %s for %s", typeDescription, plugin);
                        th.printStackTrace(this.printStream);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    synchronized (this.printStream) {
                        this.printStream.printf("[Byte Buddy] ERROR %s", plugin);
                        th.printStackTrace(this.printStream);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                    this.printStream.printf("[Byte Buddy] UNRESOLVED %s", str);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    this.printStream.printf("[Byte Buddy] LIVE %s on %s", typeDescription, typeDescription2);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.Listener
                public void onComplete(TypeDescription typeDescription) {
                    this.printStream.printf("[Byte Buddy] COMPLETE %s", typeDescription);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(@MaybeNull Manifest manifest) {
                    PrintStream printStream = this.printStream;
                    Object[] objArr = new Object[1];
                    objArr[0] = Boolean.valueOf(manifest != null);
                    printStream.printf("[Byte Buddy] MANIFEST %b", objArr);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                    this.printStream.printf("[Byte Buddy] RESOURCE %s", str);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$WithTransformationsOnly.class */
            public static class WithTransformationsOnly extends Adapter {
                private final Listener delegate;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithTransformationsOnly) obj).delegate);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.delegate.hashCode();
                }

                public WithTransformationsOnly(Listener listener) {
                    this.delegate = listener;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                    this.delegate.onTransformation(typeDescription, plugin);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                    this.delegate.onTransformation(typeDescription, list);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    this.delegate.onError(typeDescription, plugin, th);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    this.delegate.onError(typeDescription, list);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    this.delegate.onError(map);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    this.delegate.onError(plugin, th);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$WithErrorsOnly.class */
            public static class WithErrorsOnly extends Adapter {
                private final Listener delegate;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithErrorsOnly) obj).delegate);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.delegate.hashCode();
                }

                public WithErrorsOnly(Listener listener) {
                    this.delegate = listener;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    this.delegate.onError(typeDescription, plugin, th);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    this.delegate.onError(typeDescription, list);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    this.delegate.onError(map);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    this.delegate.onError(plugin, th);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$ForErrorHandler.class */
            public static class ForErrorHandler extends Adapter {
                private final ErrorHandler errorHandler;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.errorHandler.equals(((ForErrorHandler) obj).errorHandler);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.errorHandler.hashCode();
                }

                public ForErrorHandler(ErrorHandler errorHandler) {
                    this.errorHandler = errorHandler;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    this.errorHandler.onError(typeDescription, plugin, th);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    this.errorHandler.onError(typeDescription, list);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    this.errorHandler.onError(map);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    this.errorHandler.onError(plugin, th);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    this.errorHandler.onLiveInitializer(typeDescription, typeDescription2);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                    this.errorHandler.onUnresolved(str);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(@MaybeNull Manifest manifest) {
                    this.errorHandler.onManifest(manifest);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener.Adapter, net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                    this.errorHandler.onResource(str);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Listener$Compound.class */
            public static class Compound implements Listener {
                private final List<Listener> listeners;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.listeners.equals(((Compound) obj).listeners);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.listeners.hashCode();
                }

                public Compound(Listener... listenerArr) {
                    this((List<? extends Listener>) Arrays.asList(listenerArr));
                }

                public Compound(List<? extends Listener> list) {
                    this.listeners = new ArrayList();
                    for (Listener listener : list) {
                        if (listener instanceof Compound) {
                            this.listeners.addAll(((Compound) listener).listeners);
                        } else if (!(listener instanceof NoOp)) {
                            this.listeners.add(listener);
                        }
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onDiscovery(String str) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onDiscovery(str);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, Plugin plugin) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onTransformation(typeDescription, plugin);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onTransformation(TypeDescription typeDescription, List<Plugin> list) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onTransformation(typeDescription, list);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onIgnored(TypeDescription typeDescription, Plugin plugin) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onIgnored(typeDescription, plugin);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onIgnored(TypeDescription typeDescription, List<Plugin> list) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onIgnored(typeDescription, list);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, Plugin plugin, Throwable th) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onError(typeDescription, plugin, th);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(TypeDescription typeDescription, List<Throwable> list) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onError(typeDescription, list);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Map<TypeDescription, List<Throwable>> map) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onError(map);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onError(Plugin plugin, Throwable th) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onError(plugin, th);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onLiveInitializer(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onLiveInitializer(typeDescription, typeDescription2);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Listener
                public void onComplete(TypeDescription typeDescription) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onComplete(typeDescription);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onUnresolved(String str) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onUnresolved(str);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onManifest(@MaybeNull Manifest manifest) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onManifest(manifest);
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.ErrorHandler
                public void onResource(String str) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onResource(str);
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source.class */
        public interface Source {
            Origin read();

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Origin.class */
            public interface Origin extends Closeable, Iterable<Element> {

                @AlwaysNull
                public static final Manifest NO_MANIFEST = null;

                @MaybeNull
                Manifest getManifest();

                ClassFileLocator getClassFileLocator();

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Origin$ForJarFile.class */
                public static class ForJarFile implements Origin {
                    private final JarFile file;

                    public ForJarFile(JarFile jarFile) {
                        this.file = jarFile;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                    @MaybeNull
                    public Manifest getManifest() {
                        return this.file.getManifest();
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                    public ClassFileLocator getClassFileLocator() {
                        return new ClassFileLocator.ForJarFile(this.file);
                    }

                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        this.file.close();
                    }

                    @Override // java.lang.Iterable
                    public Iterator<Element> iterator() {
                        return new JarFileIterator(this.file.entries());
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Origin$ForJarFile$JarFileIterator.class */
                    protected class JarFileIterator implements Iterator<Element> {
                        private final Enumeration<JarEntry> enumeration;

                        protected JarFileIterator(Enumeration<JarEntry> enumeration) {
                            this.enumeration = enumeration;
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return this.enumeration.hasMoreElements();
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Iterator
                        public Element next() {
                            return new Element.ForJarEntry(ForJarFile.this.file, this.enumeration.nextElement());
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Origin$Filtering.class */
                public static class Filtering implements Origin {
                    private final Origin delegate;
                    private final ElementMatcher<Element> matcher;
                    private final boolean manifest;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.manifest == ((Filtering) obj).manifest && this.delegate.equals(((Filtering) obj).delegate) && this.matcher.equals(((Filtering) obj).matcher);
                    }

                    public int hashCode() {
                        return (((((getClass().hashCode() * 31) + this.delegate.hashCode()) * 31) + this.matcher.hashCode()) * 31) + (this.manifest ? 1 : 0);
                    }

                    public Filtering(Origin origin, ElementMatcher<Element> elementMatcher) {
                        this(origin, elementMatcher, true);
                    }

                    public Filtering(Origin origin, ElementMatcher<Element> elementMatcher, boolean z) {
                        this.delegate = origin;
                        this.matcher = elementMatcher;
                        this.manifest = z;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                    @MaybeNull
                    public Manifest getManifest() {
                        return this.manifest ? this.delegate.getManifest() : NO_MANIFEST;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                    public ClassFileLocator getClassFileLocator() {
                        return this.delegate.getClassFileLocator();
                    }

                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        this.delegate.close();
                    }

                    @Override // java.lang.Iterable
                    public Iterator<Element> iterator() {
                        return new FilteringIterator(this.delegate.iterator(), this.matcher, (byte) 0);
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Origin$Filtering$FilteringIterator.class */
                    private static class FilteringIterator implements Iterator<Element> {
                        private final Iterator<Element> iterator;
                        private final ElementMatcher<Element> matcher;

                        @MaybeNull
                        private Element current;

                        /* synthetic */ FilteringIterator(Iterator it, ElementMatcher elementMatcher, byte b2) {
                            this(it, elementMatcher);
                        }

                        private FilteringIterator(Iterator<Element> it, ElementMatcher<Element> elementMatcher) {
                            this.iterator = it;
                            this.matcher = elementMatcher;
                            while (it.hasNext()) {
                                Element next = it.next();
                                if (elementMatcher.matches(next)) {
                                    this.current = next;
                                    return;
                                }
                            }
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return this.current != null;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Iterator
                        public Element next() {
                            boolean hasNext;
                            boolean matches;
                            if (this.current == null) {
                                throw new NoSuchElementException();
                            }
                            try {
                                while (true) {
                                    if (!hasNext) {
                                        break;
                                    }
                                    if (matches) {
                                        break;
                                    }
                                }
                                return this.current;
                            } finally {
                                this.current = null;
                                while (true) {
                                    if (!this.iterator.hasNext()) {
                                        break;
                                    }
                                    Element next = this.iterator.next();
                                    if (this.matcher.matches(next)) {
                                        this.current = next;
                                        break;
                                    }
                                }
                            }
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            this.iterator.remove();
                        }
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Element.class */
            public interface Element {
                String getName();

                InputStream getInputStream();

                @MaybeNull
                <T> T resolveAs(Class<T> cls);

                @SuppressFBWarnings(value = {"EI_EXPOSE_REP2"}, justification = "The array is not modified by class contract.")
                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Element$ForByteArray.class */
                public static class ForByteArray implements Element {
                    private final String name;
                    private final byte[] binaryRepresentation;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.name.equals(((ForByteArray) obj).name) && Arrays.equals(this.binaryRepresentation, ((ForByteArray) obj).binaryRepresentation);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.name.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
                    }

                    public ForByteArray(String str, byte[] bArr) {
                        this.name = str;
                        this.binaryRepresentation = bArr;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    public String getName() {
                        return this.name;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    public InputStream getInputStream() {
                        return new ByteArrayInputStream(this.binaryRepresentation);
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    @AlwaysNull
                    public <T> T resolveAs(Class<T> cls) {
                        return null;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Element$ForFile.class */
                public static class ForFile implements Element {
                    private final File root;
                    private final File file;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.root.equals(((ForFile) obj).root) && this.file.equals(((ForFile) obj).file);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.root.hashCode()) * 31) + this.file.hashCode();
                    }

                    public ForFile(File file, File file2) {
                        this.root = file;
                        this.file = file2;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    public String getName() {
                        return this.root.getAbsoluteFile().toURI().relativize(this.file.getAbsoluteFile().toURI()).getPath();
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    public InputStream getInputStream() {
                        return new FileInputStream(this.file);
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    @MaybeNull
                    public <T> T resolveAs(Class<T> cls) {
                        if (File.class.isAssignableFrom(cls)) {
                            return (T) this.file;
                        }
                        return null;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Element$ForJarEntry.class */
                public static class ForJarEntry implements Element {
                    private final JarFile file;
                    private final JarEntry entry;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.file.equals(((ForJarEntry) obj).file) && this.entry.equals(((ForJarEntry) obj).entry);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.file.hashCode()) * 31) + this.entry.hashCode();
                    }

                    public ForJarEntry(JarFile jarFile, JarEntry jarEntry) {
                        this.file = jarFile;
                        this.entry = jarEntry;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    public String getName() {
                        return this.entry.getName();
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    public InputStream getInputStream() {
                        return this.file.getInputStream(this.entry);
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Element
                    @MaybeNull
                    public <T> T resolveAs(Class<T> cls) {
                        if (JarEntry.class.isAssignableFrom(cls)) {
                            return (T) this.entry;
                        }
                        return null;
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Empty.class */
            public enum Empty implements Source, Origin {
                INSTANCE;

                @Override // net.bytebuddy.build.Plugin.Engine.Source
                public final Origin read() {
                    return this;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                public final ClassFileLocator getClassFileLocator() {
                    return ClassFileLocator.NoOp.INSTANCE;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                @MaybeNull
                public final Manifest getManifest() {
                    return NO_MANIFEST;
                }

                @Override // java.lang.Iterable
                public final Iterator<Element> iterator() {
                    return Collections.emptySet().iterator();
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public final void close() {
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Compound.class */
            public static class Compound implements Source {
                private final Collection<? extends Source> sources;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.sources.equals(((Compound) obj).sources);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.sources.hashCode();
                }

                public Compound(Collection<? extends Source> collection) {
                    this.sources = collection;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source
                public Origin read() {
                    if (this.sources.isEmpty()) {
                        return Empty.INSTANCE;
                    }
                    ArrayList arrayList = new ArrayList(this.sources.size());
                    try {
                        Iterator<? extends Source> it = this.sources.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().read());
                        }
                        return new Origin(arrayList);
                    } catch (IOException e) {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            ((Origin) it2.next()).close();
                        }
                        throw e;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Compound$Origin.class */
                protected static class Origin implements Origin {
                    private final List<Origin> origins;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.origins.equals(((Origin) obj).origins);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.origins.hashCode();
                    }

                    protected Origin(List<Origin> list) {
                        this.origins = list;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                    public Manifest getManifest() {
                        Iterator<Origin> it = this.origins.iterator();
                        while (it.hasNext()) {
                            Manifest manifest = it.next().getManifest();
                            if (manifest != null) {
                                return manifest;
                            }
                        }
                        return NO_MANIFEST;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                    public ClassFileLocator getClassFileLocator() {
                        ArrayList arrayList = new ArrayList(this.origins.size());
                        Iterator<Origin> it = this.origins.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().getClassFileLocator());
                        }
                        return new ClassFileLocator.Compound(arrayList);
                    }

                    @Override // java.lang.Iterable
                    public Iterator<Element> iterator() {
                        return new CompoundIterator(this.origins);
                    }

                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        Iterator<Origin> it = this.origins.iterator();
                        while (it.hasNext()) {
                            it.next().close();
                        }
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Compound$Origin$CompoundIterator.class */
                    protected static class CompoundIterator implements Iterator<Element> {

                        @MaybeNull
                        private Iterator<? extends Element> current;
                        private final List<? extends Iterable<? extends Element>> backlog;

                        protected CompoundIterator(List<? extends Iterable<? extends Element>> list) {
                            this.backlog = list;
                            forward();
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return this.current != null && this.current.hasNext();
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Iterator
                        public Element next() {
                            try {
                                if (this.current != null) {
                                    Element next = this.current.next();
                                    forward();
                                    return next;
                                }
                                throw new NoSuchElementException();
                            } catch (Throwable th) {
                                forward();
                                throw th;
                            }
                        }

                        private void forward() {
                            while (true) {
                                if ((this.current == null || !this.current.hasNext()) && !this.backlog.isEmpty()) {
                                    this.current = this.backlog.remove(0).iterator();
                                } else {
                                    return;
                                }
                            }
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$InMemory.class */
            public static class InMemory implements Source, Origin {
                private final Map<String, byte[]> storage;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.storage.equals(((InMemory) obj).storage);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.storage.hashCode();
                }

                public InMemory(Map<String, byte[]> map) {
                    this.storage = map;
                }

                public static Source ofTypes(Class<?>... clsArr) {
                    return ofTypes(Arrays.asList(clsArr));
                }

                public static Source ofTypes(Collection<? extends Class<?>> collection) {
                    HashMap hashMap = new HashMap();
                    for (Class<?> cls : collection) {
                        hashMap.put(TypeDescription.ForLoadedType.of(cls), ClassFileLocator.ForClassLoader.read(cls));
                    }
                    return ofTypes(hashMap);
                }

                public static Source ofTypes(Map<TypeDescription, byte[]> map) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                        hashMap.put(entry.getKey().getInternalName() + ".class", entry.getValue());
                    }
                    return new InMemory(hashMap);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source
                public Origin read() {
                    return this;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                public ClassFileLocator getClassFileLocator() {
                    return ClassFileLocator.Simple.ofResources(this.storage);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                @MaybeNull
                public Manifest getManifest() {
                    byte[] bArr = this.storage.get("META-INF/MANIFEST.MF");
                    if (bArr == null) {
                        return NO_MANIFEST;
                    }
                    return new Manifest(new ByteArrayInputStream(bArr));
                }

                @Override // java.lang.Iterable
                public Iterator<Element> iterator() {
                    return new MapEntryIterator(this.storage.entrySet().iterator());
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$InMemory$MapEntryIterator.class */
                protected static class MapEntryIterator implements Iterator<Element> {
                    private final Iterator<Map.Entry<String, byte[]>> iterator;

                    protected MapEntryIterator(Iterator<Map.Entry<String, byte[]>> it) {
                        this.iterator = it;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.iterator.hasNext();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Element next() {
                        Map.Entry<String, byte[]> next = this.iterator.next();
                        return new Element.ForByteArray(next.getKey(), next.getValue());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("remove");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$ForFolder.class */
            public static class ForFolder implements Source, Origin {
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

                @Override // net.bytebuddy.build.Plugin.Engine.Source
                public Origin read() {
                    return this;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                public ClassFileLocator getClassFileLocator() {
                    return new ClassFileLocator.ForFolder(this.folder);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source.Origin
                @MaybeNull
                public Manifest getManifest() {
                    File file = new File(this.folder, "META-INF/MANIFEST.MF");
                    if (file.exists()) {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            return new Manifest(fileInputStream);
                        } finally {
                            fileInputStream.close();
                        }
                    }
                    return NO_MANIFEST;
                }

                @Override // java.lang.Iterable
                public Iterator<Element> iterator() {
                    return new FolderIterator(this.folder);
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$ForFolder$FolderIterator.class */
                protected class FolderIterator implements Iterator<Element> {
                    private final List<File> files;

                    protected FolderIterator(File file) {
                        this.files = new ArrayList(Collections.singleton(file));
                        while (true) {
                            File[] listFiles = this.files.remove(this.files.size() - 1).listFiles();
                            if (listFiles != null) {
                                this.files.addAll(Arrays.asList(listFiles));
                            }
                            if (this.files.isEmpty()) {
                                return;
                            }
                            if (!this.files.get(this.files.size() - 1).isDirectory() && !this.files.get(this.files.size() - 1).equals(new File(file, "META-INF/MANIFEST.MF"))) {
                                return;
                            }
                        }
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return !this.files.isEmpty();
                    }

                    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
                        java.lang.NullPointerException: Cannot invoke "java.util.List.contains(Object)" because the return value of "jadx.core.dex.nodes.BlockNode.getCleanSuccessors()" is null
                        	at jadx.core.utils.BlockUtils.isPathExists(BlockUtils.java:629)
                        	at jadx.core.dex.visitors.regions.RegionMaker.insertLoopBreak(RegionMaker.java:482)
                        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:227)
                        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
                        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
                        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1110)
                        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1046)
                        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
                        */
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {"IT_NO_SUCH_ELEMENT"}, justification = "Exception is thrown by invoking removeFirst on an empty list.")
                    public net.bytebuddy.build.Plugin.Engine.Source.Element next() {
                        /*
                            r7 = this;
                            net.bytebuddy.build.Plugin$Engine$Source$Element$ForFile r0 = new net.bytebuddy.build.Plugin$Engine$Source$Element$ForFile     // Catch: java.lang.Throwable -> L2b
                            r1 = r0
                            r2 = r7
                            net.bytebuddy.build.Plugin$Engine$Source$ForFolder r2 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.this     // Catch: java.lang.Throwable -> L2b
                            java.io.File r2 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.a(r2)     // Catch: java.lang.Throwable -> L2b
                            r3 = r7
                            java.util.List<java.io.File> r3 = r3.files     // Catch: java.lang.Throwable -> L2b
                            r4 = r7
                            java.util.List<java.io.File> r4 = r4.files     // Catch: java.lang.Throwable -> L2b
                            int r4 = r4.size()     // Catch: java.lang.Throwable -> L2b
                            r5 = 1
                            int r4 = r4 - r5
                            java.lang.Object r3 = r3.remove(r4)     // Catch: java.lang.Throwable -> L2b
                            java.io.File r3 = (java.io.File) r3     // Catch: java.lang.Throwable -> L2b
                            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L2b
                            r8 = r0
                            r0 = jsr -> L31
                        L29:
                            r1 = r8
                            return r1
                        L2b:
                            r9 = move-exception
                            r0 = jsr -> L31
                        L2f:
                            r1 = r9
                            throw r1
                        L31:
                            r10 = r0
                        L32:
                            r0 = r7
                            java.util.List<java.io.File> r0 = r0.files
                            boolean r0 = r0.isEmpty()
                            if (r0 != 0) goto Lbd
                            r0 = r7
                            java.util.List<java.io.File> r0 = r0.files
                            r1 = r7
                            java.util.List<java.io.File> r1 = r1.files
                            int r1 = r1.size()
                            r2 = 1
                            int r1 = r1 - r2
                            java.lang.Object r0 = r0.get(r1)
                            java.io.File r0 = (java.io.File) r0
                            boolean r0 = r0.isDirectory()
                            if (r0 != 0) goto L88
                            r0 = r7
                            java.util.List<java.io.File> r0 = r0.files
                            r1 = r7
                            java.util.List<java.io.File> r1 = r1.files
                            int r1 = r1.size()
                            r2 = 1
                            int r1 = r1 - r2
                            java.lang.Object r0 = r0.get(r1)
                            java.io.File r0 = (java.io.File) r0
                            java.io.File r1 = new java.io.File
                            r2 = r1
                            r3 = r7
                            net.bytebuddy.build.Plugin$Engine$Source$ForFolder r3 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.this
                            java.io.File r3 = net.bytebuddy.build.Plugin.Engine.Source.ForFolder.a(r3)
                            java.lang.String r4 = "META-INF/MANIFEST.MF"
                            r2.<init>(r3, r4)
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto Lbd
                        L88:
                            r0 = r7
                            java.util.List<java.io.File> r0 = r0.files
                            r1 = r7
                            java.util.List<java.io.File> r1 = r1.files
                            int r1 = r1.size()
                            r2 = 1
                            int r1 = r1 - r2
                            java.lang.Object r0 = r0.remove(r1)
                            java.io.File r0 = (java.io.File) r0
                            r1 = r0
                            r11 = r1
                            java.io.File[] r0 = r0.listFiles()
                            r1 = r0
                            r11 = r1
                            if (r0 == 0) goto Lba
                            r0 = r7
                            java.util.List<java.io.File> r0 = r0.files
                            r1 = r11
                            java.util.List r1 = java.util.Arrays.asList(r1)
                            boolean r0 = r0.addAll(r1)
                        Lba:
                            goto L32
                        Lbd:
                            ret r10
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.build.Plugin.Engine.Source.ForFolder.FolderIterator.next():net.bytebuddy.build.Plugin$Engine$Source$Element");
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("remove");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$ForJarFile.class */
            public static class ForJarFile implements Source {
                private final File file;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.file.equals(((ForJarFile) obj).file);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.file.hashCode();
                }

                public ForJarFile(File file) {
                    this.file = file;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source
                public Origin read() {
                    return new Origin.ForJarFile(new JarFile(this.file));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Source$Filtering.class */
            public static class Filtering implements Source {
                private final Source delegate;
                private final ElementMatcher<Element> matcher;
                private final boolean manifest;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.manifest == ((Filtering) obj).manifest && this.delegate.equals(((Filtering) obj).delegate) && this.matcher.equals(((Filtering) obj).matcher);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.delegate.hashCode()) * 31) + this.matcher.hashCode()) * 31) + (this.manifest ? 1 : 0);
                }

                public Filtering(Source source, ElementMatcher<Element> elementMatcher) {
                    this(source, elementMatcher, true);
                }

                public Filtering(Source source, ElementMatcher<Element> elementMatcher, boolean z) {
                    this.delegate = source;
                    this.matcher = elementMatcher;
                    this.manifest = z;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Source
                public Origin read() {
                    return new Origin.Filtering(this.delegate.read(), this.matcher, this.manifest);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target.class */
        public interface Target {
            Sink write(@MaybeNull Manifest manifest);

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target$Sink.class */
            public interface Sink extends Closeable {
                void store(Map<TypeDescription, byte[]> map);

                void retain(Source.Element element);

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target$Sink$ForJarOutputStream.class */
                public static class ForJarOutputStream implements Sink {
                    private final JarOutputStream outputStream;

                    public ForJarOutputStream(JarOutputStream jarOutputStream) {
                        this.outputStream = jarOutputStream;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                    public void store(Map<TypeDescription, byte[]> map) {
                        for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                            this.outputStream.putNextEntry(new JarEntry(entry.getKey().getInternalName() + ".class"));
                            this.outputStream.write(entry.getValue());
                            this.outputStream.closeEntry();
                        }
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                    public void retain(Source.Element element) {
                        JarEntry jarEntry = (JarEntry) element.resolveAs(JarEntry.class);
                        this.outputStream.putNextEntry(jarEntry == null ? new JarEntry(element.getName()) : jarEntry);
                        InputStream inputStream = element.getInputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read != -1) {
                                    this.outputStream.write(bArr, 0, read);
                                } else {
                                    this.outputStream.closeEntry();
                                    return;
                                }
                            }
                        } finally {
                            inputStream.close();
                        }
                    }

                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        this.outputStream.close();
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target$Discarding.class */
            public enum Discarding implements Target, Sink {
                INSTANCE;

                @Override // net.bytebuddy.build.Plugin.Engine.Target
                public final Sink write(@MaybeNull Manifest manifest) {
                    return this;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                public final void store(Map<TypeDescription, byte[]> map) {
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                public final void retain(Source.Element element) {
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public final void close() {
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target$InMemory.class */
            public static class InMemory implements Target, Sink {
                private final Map<String, byte[]> storage;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.storage.equals(((InMemory) obj).storage);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.storage.hashCode();
                }

                public InMemory() {
                    this(new HashMap());
                }

                public InMemory(Map<String, byte[]> map) {
                    this.storage = map;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target
                public Sink write(@MaybeNull Manifest manifest) {
                    if (manifest != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            manifest.write(byteArrayOutputStream);
                            this.storage.put("META-INF/MANIFEST.MF", byteArrayOutputStream.toByteArray());
                        } finally {
                            byteArrayOutputStream.close();
                        }
                    }
                    return this;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                public void store(Map<TypeDescription, byte[]> map) {
                    for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                        this.storage.put(entry.getKey().getInternalName() + ".class", entry.getValue());
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                public void retain(Source.Element element) {
                    if (!element.getName().endsWith("/")) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            InputStream inputStream = element.getInputStream();
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream.read(bArr);
                                    if (read != -1) {
                                        byteArrayOutputStream.write(bArr, 0, read);
                                    } else {
                                        this.storage.put(element.getName(), byteArrayOutputStream.toByteArray());
                                        return;
                                    }
                                }
                            } finally {
                                inputStream.close();
                            }
                        } finally {
                            byteArrayOutputStream.close();
                        }
                    }
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }

                public Map<String, byte[]> getStorage() {
                    return this.storage;
                }

                public Map<String, byte[]> toTypeMap() {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, byte[]> entry : this.storage.entrySet()) {
                        if (entry.getKey().endsWith(".class")) {
                            hashMap.put(entry.getKey().substring(0, entry.getKey().length() - 6).replace('/', '.'), entry.getValue());
                        }
                    }
                    return hashMap;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target$ForFolder.class */
            public static class ForFolder implements Target, Sink {
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

                @Override // net.bytebuddy.build.Plugin.Engine.Target
                public Sink write(@MaybeNull Manifest manifest) {
                    if (manifest != null) {
                        File file = new File(this.folder, "META-INF/MANIFEST.MF");
                        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            try {
                                manifest.write(fileOutputStream);
                            } finally {
                                fileOutputStream.close();
                            }
                        } else {
                            throw new IOException("Could not create directory: " + file.getParent());
                        }
                    }
                    return this;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                public void store(Map<TypeDescription, byte[]> map) {
                    for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                        File file = new File(this.folder, entry.getKey().getInternalName() + ".class");
                        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            try {
                                fileOutputStream.write(entry.getValue());
                            } finally {
                                fileOutputStream.close();
                            }
                        } else {
                            throw new IOException("Could not create directory: " + file.getParent());
                        }
                    }
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target.Sink
                public void retain(Source.Element element) {
                    String name = element.getName();
                    if (!name.endsWith("/")) {
                        File file = new File(this.folder, name);
                        File file2 = (File) element.resolveAs(File.class);
                        if (!file.getCanonicalPath().startsWith(this.folder.getCanonicalPath() + File.separatorChar)) {
                            throw new IllegalArgumentException(file + " is not a subdirectory of " + this.folder);
                        }
                        if (!file.getParentFile().isDirectory() && !file.getParentFile().mkdirs()) {
                            throw new IOException("Could not create directory: " + file.getParent());
                        }
                        if (file2 != null && !file2.equals(file)) {
                            FileSystem.getInstance().copy(file2, file);
                            return;
                        }
                        if (!file.equals(file2)) {
                            InputStream inputStream = element.getInputStream();
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read != -1) {
                                            fileOutputStream.write(bArr, 0, read);
                                        } else {
                                            return;
                                        }
                                    }
                                } finally {
                                    fileOutputStream.close();
                                }
                            } finally {
                                inputStream.close();
                            }
                        }
                    }
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Target$ForJarFile.class */
            public static class ForJarFile implements Target {
                private final File file;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.file.equals(((ForJarFile) obj).file);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.file.hashCode();
                }

                public ForJarFile(File file) {
                    this.file = file;
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Target
                public Sink write(@MaybeNull Manifest manifest) {
                    return manifest == null ? new Sink.ForJarOutputStream(new JarOutputStream(new FileOutputStream(this.file))) : new Sink.ForJarOutputStream(new JarOutputStream(new FileOutputStream(this.file), manifest));
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher.class */
        public interface Dispatcher extends Closeable {

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$Factory.class */
            public interface Factory {
                Dispatcher make(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2);
            }

            void accept(Callable<? extends Callable<? extends Materializable>> callable, boolean z);

            void complete();

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$Materializable.class */
            public interface Materializable {
                void materialize(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2);

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$Materializable$ForTransformedElement.class */
                public static class ForTransformedElement implements Materializable {
                    private final DynamicType dynamicType;

                    protected ForTransformedElement(DynamicType dynamicType) {
                        this.dynamicType = dynamicType;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Materializable
                    public void materialize(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        sink.store(this.dynamicType.getAllTypes());
                        list.add(this.dynamicType.getTypeDescription());
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$Materializable$ForRetainedElement.class */
                public static class ForRetainedElement implements Materializable {
                    private final Source.Element element;

                    protected ForRetainedElement(Source.Element element) {
                        this.element = element;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Materializable
                    public void materialize(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        sink.retain(this.element);
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$Materializable$ForFailedElement.class */
                public static class ForFailedElement implements Materializable {
                    private final Source.Element element;
                    private final TypeDescription typeDescription;
                    private final List<Throwable> errored;

                    protected ForFailedElement(Source.Element element, TypeDescription typeDescription, List<Throwable> list) {
                        this.element = element;
                        this.typeDescription = typeDescription;
                        this.errored = list;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Materializable
                    public void materialize(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        sink.retain(this.element);
                        map.put(this.typeDescription, this.errored);
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$Materializable$ForUnresolvedElement.class */
                public static class ForUnresolvedElement implements Materializable {
                    private final Source.Element element;
                    private final String typeName;

                    protected ForUnresolvedElement(Source.Element element, String str) {
                        this.element = element;
                        this.typeName = str;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Materializable
                    public void materialize(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        sink.retain(this.element);
                        list2.add(this.typeName);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForSerialTransformation.class */
            public static class ForSerialTransformation implements Dispatcher {
                private final Target.Sink sink;
                private final List<TypeDescription> transformed;
                private final Map<TypeDescription, List<Throwable>> failed;
                private final List<String> unresolved;
                private final List<Callable<? extends Materializable>> preprocessings = new ArrayList();

                protected ForSerialTransformation(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                    this.sink = sink;
                    this.transformed = list;
                    this.failed = map;
                    this.unresolved = list2;
                }

                /* JADX WARN: Not initialized variable reg: 0, insn: 0x003d: INSTANCE_OF (r0 I:boolean) = (r0 I:??[OBJECT, ARRAY]) java.io.IOException, block:B:10:0x003c */
                /* JADX WARN: Type inference failed for: r0v0 */
                @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher
                public void accept(Callable<? extends Callable<? extends Materializable>> callable, boolean z) {
                    ?? r0;
                    try {
                        Callable<? extends Materializable> call = callable.call();
                        if (z) {
                            call.call().materialize(this.sink, this.transformed, this.failed, this.unresolved);
                        } else {
                            this.preprocessings.add(call);
                        }
                    } catch (Exception e) {
                        if (r0 instanceof IOException) {
                            throw ((IOException) e);
                        }
                        if (!(e instanceof RuntimeException)) {
                            throw new IllegalStateException(e);
                        }
                        throw ((RuntimeException) e);
                    }
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v11 */
                /* JADX WARN: Type inference failed for: r0v22, types: [net.bytebuddy.build.Plugin$Engine$Dispatcher$Materializable] */
                /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
                @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher
                public void complete() {
                    for (Callable<? extends Materializable> callable : this.preprocessings) {
                        Materializable interrupted = Thread.interrupted();
                        if (interrupted != 0) {
                            Thread.currentThread().interrupt();
                            throw new IllegalStateException("Interrupted during plugin engine completion");
                        }
                        try {
                            interrupted = callable.call();
                            interrupted.materialize(this.sink, this.transformed, this.failed, this.unresolved);
                        } catch (Exception e) {
                            if (interrupted instanceof IOException) {
                                throw ((IOException) e);
                            }
                            if (!(e instanceof RuntimeException)) {
                                throw new IllegalStateException(e);
                            }
                            throw ((RuntimeException) e);
                        }
                    }
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForSerialTransformation$Factory.class */
                public enum Factory implements Factory {
                    INSTANCE;

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Factory
                    public final Dispatcher make(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        return new ForSerialTransformation(sink, list, map, list2);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForParallelTransformation.class */
            public static class ForParallelTransformation implements Dispatcher {
                private final Target.Sink sink;
                private final List<TypeDescription> transformed;
                private final Map<TypeDescription, List<Throwable>> failed;
                private final List<String> unresolved;
                private final CompletionService<Callable<Materializable>> preprocessings;
                private final CompletionService<Materializable> materializers;
                private int deferred;
                private final Set<Future<?>> futures = new HashSet();

                protected ForParallelTransformation(Executor executor, Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                    this.sink = sink;
                    this.transformed = list;
                    this.failed = map;
                    this.unresolved = list2;
                    this.preprocessings = new ExecutorCompletionService(executor);
                    this.materializers = new ExecutorCompletionService(executor);
                }

                @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher
                public void accept(Callable<? extends Callable<? extends Materializable>> callable, boolean z) {
                    if (z) {
                        this.futures.add(this.materializers.submit(new EagerWork(callable)));
                    } else {
                        this.deferred++;
                        this.futures.add(this.preprocessings.submit(callable));
                    }
                }

                /* JADX WARN: Type inference failed for: r0v25, types: [java.util.concurrent.ExecutionException, boolean] */
                @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher
                public void complete() {
                    ?? isEmpty;
                    try {
                        ArrayList arrayList = new ArrayList(this.deferred);
                        while (true) {
                            int i = this.deferred;
                            this.deferred = i - 1;
                            if (i <= 0) {
                                break;
                            }
                            Future<Callable<Materializable>> take = this.preprocessings.take();
                            this.futures.remove(take);
                            arrayList.add(take.get());
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            this.futures.add(this.materializers.submit((Callable) it.next()));
                        }
                        while (true) {
                            isEmpty = this.futures.isEmpty();
                            if (isEmpty == 0) {
                                Future<Materializable> take2 = this.materializers.take();
                                this.futures.remove(take2);
                                take2.get().materialize(this.sink, this.transformed, this.failed, this.unresolved);
                            } else {
                                return;
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IllegalStateException(e);
                    } catch (ExecutionException e2) {
                        Throwable cause = isEmpty.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        if (cause instanceof RuntimeException) {
                            throw ((RuntimeException) cause);
                        }
                        if (!(cause instanceof Error)) {
                            throw new IllegalStateException(cause);
                        }
                        throw ((Error) cause);
                    }
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    Iterator<Future<?>> it = this.futures.iterator();
                    while (it.hasNext()) {
                        it.next().cancel(true);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForParallelTransformation$WithThrowawayExecutorService.class */
                public static class WithThrowawayExecutorService extends ForParallelTransformation {
                    private final ExecutorService executorService;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.executorService.equals(((WithThrowawayExecutorService) obj).executorService);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.executorService.hashCode();
                    }

                    protected WithThrowawayExecutorService(ExecutorService executorService, Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        super(executorService, sink, list, map, list2);
                        this.executorService = executorService;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.ForParallelTransformation, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        try {
                            super.close();
                        } finally {
                            this.executorService.shutdown();
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForParallelTransformation$WithThrowawayExecutorService$Factory.class */
                    public static class Factory implements Factory {
                        private final int threads;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.threads == ((Factory) obj).threads;
                        }

                        public int hashCode() {
                            return (getClass().hashCode() * 31) + this.threads;
                        }

                        public Factory(int i) {
                            this.threads = i;
                        }

                        @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Factory
                        public Dispatcher make(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                            return new WithThrowawayExecutorService(Executors.newFixedThreadPool(this.threads), sink, list, map, list2);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForParallelTransformation$Factory.class */
                public static class Factory implements Factory {
                    private final Executor executor;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.executor.equals(((Factory) obj).executor);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.executor.hashCode();
                    }

                    public Factory(Executor executor) {
                        this.executor = executor;
                    }

                    @Override // net.bytebuddy.build.Plugin.Engine.Dispatcher.Factory
                    public Dispatcher make(Target.Sink sink, List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                        return new ForParallelTransformation(this.executor, sink, list, map, list2);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Dispatcher$ForParallelTransformation$EagerWork.class */
                protected static class EagerWork implements Callable<Materializable> {
                    private final Callable<? extends Callable<? extends Materializable>> work;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.work.equals(((EagerWork) obj).work);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.work.hashCode();
                    }

                    protected EagerWork(Callable<? extends Callable<? extends Materializable>> callable) {
                        this.work = callable;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public Materializable call() {
                        return this.work.call().call();
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Summary.class */
        public static class Summary {
            private final List<TypeDescription> transformed;
            private final Map<TypeDescription, List<Throwable>> failed;
            private final List<String> unresolved;

            public Summary(List<TypeDescription> list, Map<TypeDescription, List<Throwable>> map, List<String> list2) {
                this.transformed = list;
                this.failed = map;
                this.unresolved = list2;
            }

            public List<TypeDescription> getTransformed() {
                return this.transformed;
            }

            public Map<TypeDescription, List<Throwable>> getFailed() {
                return this.failed;
            }

            public List<String> getUnresolved() {
                return this.unresolved;
            }

            public int hashCode() {
                return (((this.transformed.hashCode() * 31) + this.failed.hashCode()) * 31) + this.unresolved.hashCode();
            }

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Summary summary = (Summary) obj;
                return this.transformed.equals(summary.transformed) && this.failed.equals(summary.failed) && this.unresolved.equals(summary.unresolved);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$AbstractBase.class */
        public static abstract class AbstractBase implements Engine {
            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine withErrorHandlers(ErrorHandler... errorHandlerArr) {
                return withErrorHandlers(Arrays.asList(errorHandlerArr));
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine withParallelTransformation(int i) {
                if (i <= 0) {
                    throw new IllegalArgumentException("Number of threads must be positive: " + i);
                }
                return with(new Dispatcher.ForParallelTransformation.WithThrowawayExecutorService.Factory(i));
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Summary apply(File file, File file2, Factory... factoryArr) {
                return apply(file, file2, Arrays.asList(factoryArr));
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Summary apply(File file, File file2, List<? extends Factory> list) {
                return apply(file.isDirectory() ? new Source.ForFolder(file) : new Source.ForJarFile(file), file2.isDirectory() ? new Target.ForFolder(file2) : new Target.ForJarFile(file2), list);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Summary apply(Source source, Target target, Factory... factoryArr) {
                return apply(source, target, Arrays.asList(factoryArr));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Default.class */
        public static class Default extends AbstractBase {
            private final ByteBuddy byteBuddy;
            private final TypeStrategy typeStrategy;
            private final PoolStrategy poolStrategy;
            private final ClassFileLocator classFileLocator;
            private final Listener listener;
            private final ErrorHandler errorHandler;
            private final Dispatcher.Factory dispatcherFactory;
            private final ElementMatcher.Junction<? super TypeDescription> ignoredTypeMatcher;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.byteBuddy.equals(((Default) obj).byteBuddy) && this.typeStrategy.equals(((Default) obj).typeStrategy) && this.poolStrategy.equals(((Default) obj).poolStrategy) && this.classFileLocator.equals(((Default) obj).classFileLocator) && this.listener.equals(((Default) obj).listener) && this.errorHandler.equals(((Default) obj).errorHandler) && this.dispatcherFactory.equals(((Default) obj).dispatcherFactory) && this.ignoredTypeMatcher.equals(((Default) obj).ignoredTypeMatcher);
            }

            public int hashCode() {
                return (((((((((((((((getClass().hashCode() * 31) + this.byteBuddy.hashCode()) * 31) + this.typeStrategy.hashCode()) * 31) + this.poolStrategy.hashCode()) * 31) + this.classFileLocator.hashCode()) * 31) + this.listener.hashCode()) * 31) + this.errorHandler.hashCode()) * 31) + this.dispatcherFactory.hashCode()) * 31) + this.ignoredTypeMatcher.hashCode();
            }

            public Default() {
                this(new ByteBuddy());
            }

            public Default(ByteBuddy byteBuddy) {
                this(byteBuddy, TypeStrategy.Default.REBASE);
            }

            protected Default(ByteBuddy byteBuddy, TypeStrategy typeStrategy) {
                this(byteBuddy, typeStrategy, PoolStrategy.Default.FAST, ClassFileLocator.NoOp.INSTANCE, Listener.NoOp.INSTANCE, new ErrorHandler.Compound(ErrorHandler.Failing.FAIL_FAST, ErrorHandler.Enforcing.ALL_TYPES_RESOLVED, ErrorHandler.Enforcing.NO_LIVE_INITIALIZERS), Dispatcher.ForSerialTransformation.Factory.INSTANCE, ElementMatchers.none());
            }

            protected Default(ByteBuddy byteBuddy, TypeStrategy typeStrategy, PoolStrategy poolStrategy, ClassFileLocator classFileLocator, Listener listener, ErrorHandler errorHandler, Dispatcher.Factory factory, ElementMatcher.Junction<? super TypeDescription> junction) {
                this.byteBuddy = byteBuddy;
                this.typeStrategy = typeStrategy;
                this.poolStrategy = poolStrategy;
                this.classFileLocator = classFileLocator;
                this.listener = listener;
                this.errorHandler = errorHandler;
                this.dispatcherFactory = factory;
                this.ignoredTypeMatcher = junction;
            }

            public static Engine of(EntryPoint entryPoint, ClassFileVersion classFileVersion, MethodNameTransformer methodNameTransformer) {
                return new Default(entryPoint.byteBuddy(classFileVersion), new TypeStrategy.ForEntryPoint(entryPoint, methodNameTransformer));
            }

            public static Set<String> scan(ClassLoader classLoader) {
                HashSet hashSet = new HashSet();
                Enumeration<URL> resources = classLoader.getResources(Engine.PLUGIN_FILE);
                while (resources.hasMoreElements()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resources.nextElement().openStream(), "UTF-8"));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            hashSet.add(readLine);
                        } finally {
                            bufferedReader.close();
                        }
                    }
                }
                return hashSet;
            }

            public static void main(String... strArr) {
                if (strArr.length < 2) {
                    throw new IllegalArgumentException("Expected arguments: <source> <target> [<plugin>, ...]");
                }
                ArrayList arrayList = new ArrayList(strArr.length - 2);
                Iterator it = Arrays.asList(strArr).subList(2, strArr.length).iterator();
                while (it.hasNext()) {
                    arrayList.add(new Factory.UsingReflection(Class.forName((String) it.next())));
                }
                new Default().apply(new File(strArr[0]), new File(strArr[1]), arrayList);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine with(ByteBuddy byteBuddy) {
                return new Default(byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine with(TypeStrategy typeStrategy) {
                return new Default(this.byteBuddy, typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine with(PoolStrategy poolStrategy) {
                return new Default(this.byteBuddy, this.typeStrategy, poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine with(ClassFileLocator classFileLocator) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, new ClassFileLocator.Compound(this.classFileLocator, classFileLocator), this.listener, this.errorHandler, this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine with(Listener listener) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, new Listener.Compound(this.listener, listener), this.errorHandler, this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine withoutErrorHandlers() {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, Listener.NoOp.INSTANCE, this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine withErrorHandlers(List<? extends ErrorHandler> list) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, new ErrorHandler.Compound(list), this.dispatcherFactory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine with(Dispatcher.Factory factory) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, factory, this.ignoredTypeMatcher);
            }

            @Override // net.bytebuddy.build.Plugin.Engine
            public Engine ignore(ElementMatcher<? super TypeDescription> elementMatcher) {
                return new Default(this.byteBuddy, this.typeStrategy, this.poolStrategy, this.classFileLocator, this.listener, this.errorHandler, this.dispatcherFactory, this.ignoredTypeMatcher.or(elementMatcher));
            }

            /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
                jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:57:0x029c
                	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:88)
                	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
                	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
                */
            @Override // net.bytebuddy.build.Plugin.Engine
            public net.bytebuddy.build.Plugin.Engine.Summary apply(net.bytebuddy.build.Plugin.Engine.Source r14, net.bytebuddy.build.Plugin.Engine.Target r15, java.util.List<? extends net.bytebuddy.build.Plugin.Factory> r16) {
                /*
                    Method dump skipped, instructions count: 759
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.build.Plugin.Engine.Default.apply(net.bytebuddy.build.Plugin$Engine$Source, net.bytebuddy.build.Plugin$Engine$Target, java.util.List):net.bytebuddy.build.Plugin$Engine$Summary");
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Default$Preprocessor.class */
            public class Preprocessor implements Callable<Callable<? extends Dispatcher.Materializable>> {
                private final Source.Element element;
                private final String typeName;
                private final ClassFileLocator classFileLocator;
                private final TypePool typePool;
                private final Listener listener;
                private final List<Plugin> plugins;
                private final List<WithPreprocessor> preprocessors;

                /* synthetic */ Preprocessor(Default r11, Source.Element element, String str, ClassFileLocator classFileLocator, TypePool typePool, Listener listener, List list, List list2, byte b2) {
                    this(element, str, classFileLocator, typePool, listener, list, list2);
                }

                private Preprocessor(Source.Element element, String str, ClassFileLocator classFileLocator, TypePool typePool, Listener listener, List<Plugin> list, List<WithPreprocessor> list2) {
                    this.element = element;
                    this.typeName = str;
                    this.classFileLocator = classFileLocator;
                    this.typePool = typePool;
                    this.listener = listener;
                    this.plugins = list;
                    this.preprocessors = list2;
                }

                @Override // java.util.concurrent.Callable
                /* renamed from: call, reason: merged with bridge method [inline-methods] */
                public Callable<? extends Dispatcher.Materializable> call2() {
                    this.listener.onDiscovery(this.typeName);
                    TypePool.Resolution describe = this.typePool.describe(this.typeName);
                    if (describe.isResolved()) {
                        TypeDescription resolve = describe.resolve();
                        try {
                            if (!Default.this.ignoredTypeMatcher.matches(resolve)) {
                                Iterator<WithPreprocessor> it = this.preprocessors.iterator();
                                while (it.hasNext()) {
                                    it.next().onPreprocess(resolve, this.classFileLocator);
                                }
                                return new Resolved(this, resolve, (byte) 0);
                            }
                            return new Ignored(this, resolve, (byte) 0);
                        } catch (Throwable th) {
                            this.listener.onComplete(resolve);
                            if (th instanceof Exception) {
                                throw ((Exception) th);
                            }
                            if (th instanceof Error) {
                                throw ((Error) th);
                            }
                            throw new IllegalStateException(th);
                        }
                    }
                    return new Unresolved(this, (byte) 0);
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Default$Preprocessor$Resolved.class */
                public class Resolved implements Callable<Dispatcher.Materializable> {
                    private final TypeDescription typeDescription;

                    /* synthetic */ Resolved(Preprocessor preprocessor, TypeDescription typeDescription, byte b2) {
                        this(typeDescription);
                    }

                    private Resolved(TypeDescription typeDescription) {
                        this.typeDescription = typeDescription;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public Dispatcher.Materializable call() {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        try {
                            DynamicType.Builder<?> builder = Default.this.typeStrategy.builder(Default.this.byteBuddy, this.typeDescription, Preprocessor.this.classFileLocator);
                            for (Plugin plugin : Preprocessor.this.plugins) {
                                try {
                                } catch (Throwable th) {
                                    Preprocessor.this.listener.onError(this.typeDescription, plugin, th);
                                    arrayList3.add(th);
                                }
                                if (!plugin.matches(this.typeDescription)) {
                                    Preprocessor.this.listener.onIgnored(this.typeDescription, plugin);
                                    arrayList2.add(plugin);
                                } else {
                                    builder = plugin.apply(builder, this.typeDescription, Preprocessor.this.classFileLocator);
                                    Preprocessor.this.listener.onTransformation(this.typeDescription, plugin);
                                    arrayList.add(plugin);
                                }
                            }
                            if (!arrayList3.isEmpty()) {
                                Preprocessor.this.listener.onError(this.typeDescription, arrayList3);
                                return new Dispatcher.Materializable.ForFailedElement(Preprocessor.this.element, this.typeDescription, arrayList3);
                            }
                            if (!arrayList.isEmpty()) {
                                DynamicType.Unloaded<?> make = builder.make(TypeResolutionStrategy.Disabled.INSTANCE, Preprocessor.this.typePool);
                                Preprocessor.this.listener.onTransformation(this.typeDescription, arrayList);
                                for (Map.Entry<TypeDescription, LoadedTypeInitializer> entry : make.getLoadedTypeInitializers().entrySet()) {
                                    if (entry.getValue().isAlive()) {
                                        Preprocessor.this.listener.onLiveInitializer(this.typeDescription, entry.getKey());
                                    }
                                }
                                return new Dispatcher.Materializable.ForTransformedElement(make);
                            }
                            Preprocessor.this.listener.onIgnored(this.typeDescription, arrayList2);
                            return new Dispatcher.Materializable.ForRetainedElement(Preprocessor.this.element);
                        } finally {
                            Preprocessor.this.listener.onComplete(this.typeDescription);
                        }
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Default$Preprocessor$Ignored.class */
                public class Ignored implements Callable<Dispatcher.Materializable> {
                    private final TypeDescription typeDescription;

                    /* synthetic */ Ignored(Preprocessor preprocessor, TypeDescription typeDescription, byte b2) {
                        this(typeDescription);
                    }

                    private Ignored(TypeDescription typeDescription) {
                        this.typeDescription = typeDescription;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public Dispatcher.Materializable call() {
                        try {
                            Preprocessor.this.listener.onIgnored(this.typeDescription, Preprocessor.this.plugins);
                            return new Dispatcher.Materializable.ForRetainedElement(Preprocessor.this.element);
                        } finally {
                            Preprocessor.this.listener.onComplete(this.typeDescription);
                        }
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$Engine$Default$Preprocessor$Unresolved.class */
                public class Unresolved implements Callable<Dispatcher.Materializable> {
                    private Unresolved() {
                    }

                    /* synthetic */ Unresolved(Preprocessor preprocessor, byte b2) {
                        this();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public Dispatcher.Materializable call() {
                        Preprocessor.this.listener.onUnresolved(Preprocessor.this.typeName);
                        return new Dispatcher.Materializable.ForUnresolvedElement(Preprocessor.this.element, Preprocessor.this.typeName);
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$NoOp.class */
    public static class NoOp implements Plugin, Factory {
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        @Override // net.bytebuddy.build.Plugin.Factory
        public Plugin make() {
            return this;
        }

        @Override // net.bytebuddy.matcher.ElementMatcher
        public boolean matches(@MaybeNull TypeDescription typeDescription) {
            return false;
        }

        @Override // net.bytebuddy.build.Plugin
        public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            throw new IllegalStateException("Cannot apply non-operational plugin");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/Plugin$ForElementMatcher.class */
    public static abstract class ForElementMatcher implements Plugin {
        private final ElementMatcher<? super TypeDescription> matcher;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matcher.equals(((ForElementMatcher) obj).matcher);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.matcher.hashCode();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ForElementMatcher(ElementMatcher<? super TypeDescription> elementMatcher) {
            this.matcher = elementMatcher;
        }

        @Override // net.bytebuddy.matcher.ElementMatcher
        public boolean matches(@MaybeNull TypeDescription typeDescription) {
            return this.matcher.matches(typeDescription);
        }
    }
}
