package net.bytebuddy.dynamic;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.NexusAccessor;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy.class */
public interface TypeResolutionStrategy {

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy$Resolved.class */
    public interface Resolved {
        TypeInitializer injectedInto(TypeInitializer typeInitializer);

        <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, @MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy);
    }

    Resolved resolve();

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy$Passive.class */
    public enum Passive implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public final Resolved resolve() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public final TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public final <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, @MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            Map<TypeDescription, Class<?>> load = classLoadingStrategy.load(s, dynamicType.getAllTypes());
            for (Map.Entry<TypeDescription, LoadedTypeInitializer> entry : dynamicType.getLoadedTypeInitializers().entrySet()) {
                entry.getValue().onLoad(load.get(entry.getKey()));
            }
            return new HashMap(load);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy$Active.class */
    public static class Active implements TypeResolutionStrategy {
        private final NexusAccessor nexusAccessor;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.nexusAccessor.equals(((Active) obj).nexusAccessor);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.nexusAccessor.hashCode();
        }

        public Active() {
            this(new NexusAccessor());
        }

        public Active(NexusAccessor nexusAccessor) {
            this.nexusAccessor = nexusAccessor;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        @SuppressFBWarnings(value = {"DMI_RANDOM_USED_ONLY_ONCE"}, justification = "Avoids thread-contention.")
        public Resolved resolve() {
            return new Resolved(this.nexusAccessor, new Random().nextInt());
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy$Active$Resolved.class */
        protected static class Resolved implements Resolved {
            private final NexusAccessor nexusAccessor;
            private final int identification;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.identification == ((Resolved) obj).identification && this.nexusAccessor.equals(((Resolved) obj).nexusAccessor);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.nexusAccessor.hashCode()) * 31) + this.identification;
            }

            protected Resolved(NexusAccessor nexusAccessor, int i) {
                this.nexusAccessor = nexusAccessor;
                this.identification = i;
            }

            @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
            public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
                return typeInitializer.expandWith(new NexusAccessor.InitializationAppender(this.identification));
            }

            @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
            public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, @MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
                HashMap hashMap = new HashMap(dynamicType.getLoadedTypeInitializers());
                TypeDescription typeDescription = dynamicType.getTypeDescription();
                Map<TypeDescription, Class<?>> load = classLoadingStrategy.load(s, dynamicType.getAllTypes());
                this.nexusAccessor.register(typeDescription.getName(), load.get(typeDescription).getClassLoader(), this.identification, (LoadedTypeInitializer) hashMap.remove(typeDescription));
                for (Map.Entry entry : hashMap.entrySet()) {
                    ((LoadedTypeInitializer) entry.getValue()).onLoad(load.get(entry.getKey()));
                }
                return load;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy$Lazy.class */
    public enum Lazy implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public final Resolved resolve() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public final TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public final <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, @MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            return classLoadingStrategy.load(s, dynamicType.getAllTypes());
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/TypeResolutionStrategy$Disabled.class */
    public enum Disabled implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public final Resolved resolve() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public final TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public final <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, @MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            throw new IllegalStateException("Cannot initialize a dynamic type for a disabled type resolution strategy");
        }
    }
}
