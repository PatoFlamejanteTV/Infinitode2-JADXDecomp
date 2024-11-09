package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.FieldPersistence;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.SyntheticState;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin.class */
public class CachedReturnPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
    private static final String NAME_INFIX = "_";
    private static final String ADVICE_INFIX = "$Advice$";
    private static final MethodDescription.InDefinedShape ENHANCE_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Enhance.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
    private final boolean ignoreExistingFields;

    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    private final RandomString randomString;
    private final ClassFileLocator classFileLocator;

    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    private final Map<TypeDescription, TypeDescription> adviceByType;

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$CacheField.class */
    protected @interface CacheField {
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$Enhance.class */
    public @interface Enhance {
        String value() default "";
    }

    @Override // net.bytebuddy.build.Plugin.ForElementMatcher
    public boolean equals(@MaybeNull Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.ignoreExistingFields == ((CachedReturnPlugin) obj).ignoreExistingFields && this.classFileLocator.equals(((CachedReturnPlugin) obj).classFileLocator);
    }

    @Override // net.bytebuddy.build.Plugin.ForElementMatcher
    public int hashCode() {
        return (((super.hashCode() * 31) + (this.ignoreExistingFields ? 1 : 0)) * 31) + this.classFileLocator.hashCode();
    }

    public CachedReturnPlugin() {
        this(false);
    }

    public CachedReturnPlugin(boolean z) {
        super(ElementMatchers.declaresMethod(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Enhance.class)));
        this.ignoreExistingFields = z;
        this.randomString = new RandomString();
        this.classFileLocator = ClassFileLocator.ForClassLoader.of(CachedReturnPlugin.class.getClassLoader());
        TypePool of = TypePool.Default.of(this.classFileLocator);
        this.adviceByType = new HashMap();
        Class[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class};
        for (int i = 0; i < 9; i++) {
            Class cls = clsArr[i];
            this.adviceByType.put(TypeDescription.ForLoadedType.of(cls), of.describe(CachedReturnPlugin.class.getName() + ADVICE_INFIX + cls.getSimpleName()).resolve());
        }
    }

    @Override // net.bytebuddy.build.Plugin.Factory
    public Plugin make() {
        return this;
    }

    @Override // net.bytebuddy.build.Plugin
    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Annotation presence is required by matcher.")
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        TypeDescription of;
        for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods().filter(ElementMatchers.not(ElementMatchers.isBridge()).and(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Enhance.class)))) {
            if (inDefinedShape.isAbstract()) {
                throw new IllegalStateException("Cannot cache the value of an abstract method: " + inDefinedShape);
            }
            if (!inDefinedShape.getParameters().isEmpty()) {
                throw new IllegalStateException("Cannot cache the value of a method with parameters: " + inDefinedShape);
            }
            if (inDefinedShape.getReturnType().represents(Void.TYPE)) {
                throw new IllegalStateException("Cannot cache void result for " + inDefinedShape);
            }
            String str = (String) inDefinedShape.getDeclaredAnnotations().ofType(Enhance.class).getValue(ENHANCE_VALUE).resolve(String.class);
            String str2 = str;
            if (str.length() == 0) {
                str2 = inDefinedShape.getName() + "_" + this.randomString.nextString();
            } else if (this.ignoreExistingFields && !typeDescription.getDeclaredFields().filter(ElementMatchers.named(str2)).isEmpty()) {
                return builder;
            }
            DynamicType.Builder<?> builder2 = builder;
            String str3 = str2;
            TypeDescription asErasure = inDefinedShape.getReturnType().asErasure();
            ModifierContributor.ForField[] forFieldArr = new ModifierContributor.ForField[4];
            forFieldArr[0] = inDefinedShape.isStatic() ? Ownership.STATIC : Ownership.MEMBER;
            forFieldArr[1] = inDefinedShape.isStatic() ? FieldPersistence.PLAIN : FieldPersistence.TRANSIENT;
            forFieldArr[2] = Visibility.PRIVATE;
            forFieldArr[3] = SyntheticState.SYNTHETIC;
            DynamicType.Builder.FieldDefinition.Optional.Valuable<?> defineField = builder2.defineField(str3, asErasure, forFieldArr);
            Advice.WithCustomMapping bind = Advice.withCustomMapping().bind(CacheField.class, (Advice.OffsetMapping) new CacheFieldOffsetMapping(str2));
            Map<TypeDescription, TypeDescription> map = this.adviceByType;
            if (inDefinedShape.getReturnType().isPrimitive()) {
                of = inDefinedShape.getReturnType().asErasure();
            } else {
                of = TypeDescription.ForLoadedType.of(Object.class);
            }
            builder = defineField.visit(bind.to(map.get(of), this.classFileLocator).on(ElementMatchers.is(inDefinedShape)));
        }
        return builder;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$CacheFieldOffsetMapping.class */
    protected static class CacheFieldOffsetMapping implements Advice.OffsetMapping {
        private final String name;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.name.equals(((CacheFieldOffsetMapping) obj).name);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.name.hashCode();
        }

        protected CacheFieldOffsetMapping(String str) {
            this.name = str;
        }

        @Override // net.bytebuddy.asm.Advice.OffsetMapping
        public Advice.OffsetMapping.Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Advice.ArgumentHandler argumentHandler, Advice.OffsetMapping.Sort sort) {
            return new Advice.OffsetMapping.Target.ForField.ReadWrite((FieldDescription) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name)).getOnly());
        }
    }
}
