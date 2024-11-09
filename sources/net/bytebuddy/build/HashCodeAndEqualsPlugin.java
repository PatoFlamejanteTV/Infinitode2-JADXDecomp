package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.EqualsMethod;
import net.bytebuddy.implementation.HashCodeMethod;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.MaybeNull;

@Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin.class */
public class HashCodeAndEqualsPlugin implements Plugin, Plugin.Factory, MethodAttributeAppender, MethodAttributeAppender.Factory {
    private static final MethodDescription.InDefinedShape ENHANCE_INVOKE_SUPER;
    private static final MethodDescription.InDefinedShape ENHANCE_SIMPLE_COMPARISON_FIRST;
    private static final MethodDescription.InDefinedShape ENHANCE_INCLUDE_SYNTHETIC_FIELDS;
    private static final MethodDescription.InDefinedShape ENHANCE_PERMIT_SUBCLASS_EQUALITY;
    private static final MethodDescription.InDefinedShape ENHANCE_USE_TYPE_HASH_CONSTANT;
    private static final MethodDescription.InDefinedShape VALUE_HANDLING_VALUE;
    private static final MethodDescription.InDefinedShape SORTED_VALUE;

    @MaybeNull
    @ValueHandling(ValueHandling.Sort.REVERSE_NULLABILITY)
    private final String annotationType;

    @Target({ElementType.FIELD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$Sorted.class */
    public @interface Sorted {
        public static final int DEFAULT = 0;

        int value();
    }

    @Target({ElementType.FIELD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$ValueHandling.class */
    public @interface ValueHandling {

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$ValueHandling$Sort.class */
        public enum Sort {
            IGNORE,
            REVERSE_NULLABILITY
        }

        Sort value();
    }

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.annotationType;
        String str2 = ((HashCodeAndEqualsPlugin) obj).annotationType;
        return str2 != null ? str != null && str.equals(str2) : str == null;
    }

    public int hashCode() {
        int hashCode = getClass().hashCode() * 31;
        String str = this.annotationType;
        return str != null ? hashCode + str.hashCode() : hashCode;
    }

    static {
        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Enhance.class).getDeclaredMethods();
        ENHANCE_INVOKE_SUPER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("invokeSuper")).getOnly();
        ENHANCE_SIMPLE_COMPARISON_FIRST = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("simpleComparisonsFirst")).getOnly();
        ENHANCE_INCLUDE_SYNTHETIC_FIELDS = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("includeSyntheticFields")).getOnly();
        ENHANCE_PERMIT_SUBCLASS_EQUALITY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("permitSubclassEquality")).getOnly();
        ENHANCE_USE_TYPE_HASH_CONSTANT = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("useTypeHashConstant")).getOnly();
        VALUE_HANDLING_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(ValueHandling.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
        SORTED_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Sorted.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
    }

    public HashCodeAndEqualsPlugin() {
        this(null);
    }

    public HashCodeAndEqualsPlugin(@MaybeNull String str) {
        this.annotationType = str;
    }

    @Override // net.bytebuddy.build.Plugin.Factory
    public Plugin make() {
        return this;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(@MaybeNull TypeDescription typeDescription) {
        return typeDescription != null && typeDescription.getDeclaredAnnotations().isAnnotationPresent(Enhance.class);
    }

    @Override // net.bytebuddy.build.Plugin
    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Annotation presence is required by matcher.")
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        ElementMatcher.Junction isSynthetic;
        ElementMatcher.Junction isSynthetic2;
        AnnotationDescription.Loadable ofType = typeDescription.getDeclaredAnnotations().ofType(Enhance.class);
        if (typeDescription.getDeclaredMethods().filter(ElementMatchers.isHashCode()).isEmpty()) {
            DynamicType.Builder.MethodDefinition.ImplementationDefinition<?> method = builder.method(ElementMatchers.isHashCode());
            HashCodeMethod hashCodeMethod = ((Enhance.InvokeSuper) ofType.getValue(ENHANCE_INVOKE_SUPER).load(Enhance.class.getClassLoader()).resolve(Enhance.InvokeSuper.class)).hashCodeMethod(typeDescription, ((Boolean) ofType.getValue(ENHANCE_USE_TYPE_HASH_CONSTANT).resolve(Boolean.class)).booleanValue(), ((Boolean) ofType.getValue(ENHANCE_PERMIT_SUBCLASS_EQUALITY).resolve(Boolean.class)).booleanValue());
            if (((Boolean) ofType.getValue(ENHANCE_INCLUDE_SYNTHETIC_FIELDS).resolve(Boolean.class)).booleanValue()) {
                isSynthetic2 = ElementMatchers.none();
            } else {
                isSynthetic2 = ElementMatchers.isSynthetic();
            }
            builder = method.intercept(hashCodeMethod.withIgnoredFields(isSynthetic2).withIgnoredFields(new ValueMatcher(ValueHandling.Sort.IGNORE)).withNonNullableFields(nonNullable(new ValueMatcher(ValueHandling.Sort.REVERSE_NULLABILITY))));
        }
        if (typeDescription.getDeclaredMethods().filter(ElementMatchers.isEquals()).isEmpty()) {
            EqualsMethod equalsMethod = ((Enhance.InvokeSuper) ofType.getValue(ENHANCE_INVOKE_SUPER).load(Enhance.class.getClassLoader()).resolve(Enhance.InvokeSuper.class)).equalsMethod(typeDescription);
            if (((Boolean) ofType.getValue(ENHANCE_INCLUDE_SYNTHETIC_FIELDS).resolve(Boolean.class)).booleanValue()) {
                isSynthetic = ElementMatchers.none();
            } else {
                isSynthetic = ElementMatchers.isSynthetic();
            }
            EqualsMethod withFieldOrder = equalsMethod.withIgnoredFields(isSynthetic).withIgnoredFields(new ValueMatcher(ValueHandling.Sort.IGNORE)).withNonNullableFields(nonNullable(new ValueMatcher(ValueHandling.Sort.REVERSE_NULLABILITY))).withFieldOrder(AnnotationOrderComparator.INSTANCE);
            if (((Boolean) ofType.getValue(ENHANCE_SIMPLE_COMPARISON_FIRST).resolve(Boolean.class)).booleanValue()) {
                withFieldOrder = withFieldOrder.withPrimitiveTypedFieldsFirst().withEnumerationTypedFieldsFirst().withPrimitiveWrapperTypedFieldsFirst().withStringTypedFieldsFirst();
            }
            builder = builder.method(ElementMatchers.isEquals()).intercept(((Boolean) ofType.getValue(ENHANCE_PERMIT_SUBCLASS_EQUALITY).resolve(Boolean.class)).booleanValue() ? withFieldOrder.withSubclassEquality() : withFieldOrder).attribute(this);
        }
        return builder;
    }

    protected ElementMatcher<FieldDescription> nonNullable(ElementMatcher<FieldDescription> elementMatcher) {
        return elementMatcher;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Factory
    public MethodAttributeAppender make(TypeDescription typeDescription) {
        return this;
    }

    @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender
    public void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter) {
        AnnotationVisitor visitParameterAnnotation;
        if (this.annotationType != null && (visitParameterAnnotation = methodVisitor.visitParameterAnnotation(0, "L" + this.annotationType.replace('.', '/') + ";", true)) != null) {
            visitParameterAnnotation.visitEnd();
        }
    }

    @Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$WithNonNullableFields.class */
    public static class WithNonNullableFields extends HashCodeAndEqualsPlugin {
        @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin
        public boolean equals(@MaybeNull Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin
        public int hashCode() {
            return super.hashCode();
        }

        @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin, net.bytebuddy.matcher.ElementMatcher
        public /* bridge */ /* synthetic */ boolean matches(@MaybeNull TypeDescription typeDescription) {
            return super.matches(typeDescription);
        }

        public WithNonNullableFields() {
            this(null);
        }

        public WithNonNullableFields(@MaybeNull String str) {
            super(str);
        }

        @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin
        protected ElementMatcher<FieldDescription> nonNullable(ElementMatcher<FieldDescription> elementMatcher) {
            return ElementMatchers.not(elementMatcher);
        }
    }

    @Target({ElementType.TYPE})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$Enhance.class */
    public @interface Enhance {
        InvokeSuper invokeSuper() default InvokeSuper.IF_DECLARED;

        boolean simpleComparisonsFirst() default true;

        boolean includeSyntheticFields() default false;

        boolean permitSubclassEquality() default false;

        boolean useTypeHashConstant() default true;

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$Enhance$InvokeSuper.class */
        public enum InvokeSuper {
            IF_DECLARED { // from class: net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper.1
                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final HashCodeMethod hashCodeMethod(TypeDescription typeDescription, boolean z, boolean z2) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    while (true) {
                        TypeDescription.Generic generic = superClass;
                        if (generic == null || generic.represents(Object.class)) {
                            break;
                        }
                        if (generic.asErasure().getDeclaredAnnotations().isAnnotationPresent(Enhance.class)) {
                            return HashCodeMethod.usingSuperClassOffset();
                        }
                        MethodList filter = generic.getDeclaredMethods().filter(ElementMatchers.isHashCode());
                        if (!filter.isEmpty()) {
                            if (!((MethodDescription) filter.getOnly()).isAbstract()) {
                                return HashCodeMethod.usingSuperClassOffset();
                            }
                            if (z) {
                                return HashCodeMethod.usingTypeHashOffset(!z2);
                            }
                            return HashCodeMethod.usingDefaultOffset();
                        }
                        superClass = generic.getSuperClass();
                    }
                    if (z) {
                        return HashCodeMethod.usingTypeHashOffset(!z2);
                    }
                    return HashCodeMethod.usingDefaultOffset();
                }

                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    while (true) {
                        TypeDescription.Generic generic = superClass;
                        if (generic == null || generic.represents(Object.class)) {
                            break;
                        }
                        if (generic.asErasure().getDeclaredAnnotations().isAnnotationPresent(Enhance.class)) {
                            return EqualsMethod.requiringSuperClassEquality();
                        }
                        MethodList filter = generic.getDeclaredMethods().filter(ElementMatchers.isHashCode());
                        if (!filter.isEmpty()) {
                            if (((MethodDescription) filter.getOnly()).isAbstract()) {
                                return EqualsMethod.isolated();
                            }
                            return EqualsMethod.requiringSuperClassEquality();
                        }
                        superClass = generic.getSuperClass();
                    }
                    return EqualsMethod.isolated();
                }
            },
            IF_ANNOTATED { // from class: net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper.2
                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final HashCodeMethod hashCodeMethod(TypeDescription typeDescription, boolean z, boolean z2) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    if (superClass != null && superClass.asErasure().getDeclaredAnnotations().isAnnotationPresent(Enhance.class)) {
                        return HashCodeMethod.usingSuperClassOffset();
                    }
                    if (z) {
                        return HashCodeMethod.usingTypeHashOffset(!z2);
                    }
                    return HashCodeMethod.usingDefaultOffset();
                }

                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    if (superClass != null && superClass.asErasure().getDeclaredAnnotations().isAnnotationPresent(Enhance.class)) {
                        return EqualsMethod.requiringSuperClassEquality();
                    }
                    return EqualsMethod.isolated();
                }
            },
            ALWAYS { // from class: net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper.3
                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final HashCodeMethod hashCodeMethod(TypeDescription typeDescription, boolean z, boolean z2) {
                    return HashCodeMethod.usingSuperClassOffset();
                }

                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    return EqualsMethod.requiringSuperClassEquality();
                }
            },
            NEVER { // from class: net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper.4
                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final HashCodeMethod hashCodeMethod(TypeDescription typeDescription, boolean z, boolean z2) {
                    if (z) {
                        return HashCodeMethod.usingTypeHashOffset(!z2);
                    }
                    return HashCodeMethod.usingDefaultOffset();
                }

                @Override // net.bytebuddy.build.HashCodeAndEqualsPlugin.Enhance.InvokeSuper
                protected final EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    return EqualsMethod.isolated();
                }
            };

            protected abstract HashCodeMethod hashCodeMethod(TypeDescription typeDescription, boolean z, boolean z2);

            protected abstract EqualsMethod equalsMethod(TypeDescription typeDescription);

            /* synthetic */ InvokeSuper(byte b2) {
                this();
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$AnnotationOrderComparator.class */
    protected enum AnnotationOrderComparator implements Comparator<FieldDescription.InDefinedShape> {
        INSTANCE;

        @Override // java.util.Comparator
        public final int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            AnnotationDescription.Loadable ofType = inDefinedShape.getDeclaredAnnotations().ofType(Sorted.class);
            AnnotationDescription.Loadable ofType2 = inDefinedShape2.getDeclaredAnnotations().ofType(Sorted.class);
            int intValue = ofType == null ? 0 : ((Integer) ofType.getValue(HashCodeAndEqualsPlugin.SORTED_VALUE).resolve(Integer.class)).intValue();
            int intValue2 = ofType2 == null ? 0 : ((Integer) ofType2.getValue(HashCodeAndEqualsPlugin.SORTED_VALUE).resolve(Integer.class)).intValue();
            if (intValue > intValue2) {
                return -1;
            }
            if (intValue < intValue2) {
                return 1;
            }
            return 0;
        }
    }

    @Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/HashCodeAndEqualsPlugin$ValueMatcher.class */
    protected static class ValueMatcher extends ElementMatcher.Junction.ForNonNullValues<FieldDescription> {
        private final ValueHandling.Sort sort;

        @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
        public boolean equals(@MaybeNull Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sort.equals(((ValueMatcher) obj).sort);
        }

        @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
        public int hashCode() {
            return (super.hashCode() * 31) + this.sort.hashCode();
        }

        protected ValueMatcher(ValueHandling.Sort sort) {
            this.sort = sort;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
        public boolean doMatch(FieldDescription fieldDescription) {
            AnnotationDescription.Loadable ofType = fieldDescription.getDeclaredAnnotations().ofType(ValueHandling.class);
            return ofType != null && ofType.getValue(HashCodeAndEqualsPlugin.VALUE_HANDLING_VALUE).load(ValueHandling.class.getClassLoader()).resolve(ValueHandling.Sort.class) == this.sort;
        }
    }
}
