package net.bytebuddy.dynamic;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import net.bytebuddy.dynamic.scaffold.ClassWriterStrategy;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.RecordComponentRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.EqualsMethod;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.HashCodeMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.ToStringMethod;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.FileSystem;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.visitor.ContextClassVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType.class */
public interface DynamicType extends ClassFileLocator {

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Loaded.class */
    public interface Loaded<T> extends DynamicType {
        Class<? extends T> getLoaded();

        Map<TypeDescription, Class<?>> getLoadedAuxiliaryTypes();

        Map<TypeDescription, Class<?>> getAllLoaded();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Unloaded.class */
    public interface Unloaded<T> extends DynamicType {
        Loaded<T> load(@MaybeNull ClassLoader classLoader);

        <S extends ClassLoader> Loaded<T> load(@MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy);

        Unloaded<T> include(DynamicType... dynamicTypeArr);

        Unloaded<T> include(List<? extends DynamicType> list);
    }

    TypeDescription getTypeDescription();

    byte[] getBytes();

    Map<TypeDescription, byte[]> getAuxiliaryTypes();

    Map<TypeDescription, byte[]> getAllTypes();

    Map<TypeDescription, LoadedTypeInitializer> getLoadedTypeInitializers();

    boolean hasAliveLoadedTypeInitializers();

    Map<TypeDescription, File> saveIn(File file);

    File inject(File file, File file2);

    File inject(File file);

    File toJar(File file);

    File toJar(File file, Manifest manifest);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder.class */
    public interface Builder<T> {

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$InnerTypeDefinition.class */
        public interface InnerTypeDefinition<S> extends Builder<S> {

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$InnerTypeDefinition$ForType.class */
            public interface ForType<U> extends InnerTypeDefinition<U> {
                Builder<U> asMemberType();
            }

            Builder<S> asAnonymousType();
        }

        Builder<T> visit(AsmVisitorWrapper asmVisitorWrapper);

        Builder<T> name(String str);

        Builder<T> suffix(String str);

        Builder<T> modifiers(ModifierContributor.ForType... forTypeArr);

        Builder<T> modifiers(Collection<? extends ModifierContributor.ForType> collection);

        Builder<T> modifiers(int i);

        Builder<T> merge(ModifierContributor.ForType... forTypeArr);

        Builder<T> merge(Collection<? extends ModifierContributor.ForType> collection);

        Builder<T> topLevelType();

        InnerTypeDefinition.ForType<T> innerTypeOf(Class<?> cls);

        InnerTypeDefinition.ForType<T> innerTypeOf(TypeDescription typeDescription);

        InnerTypeDefinition<T> innerTypeOf(Method method);

        InnerTypeDefinition<T> innerTypeOf(Constructor<?> constructor);

        InnerTypeDefinition<T> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape);

        Builder<T> declaredTypes(Class<?>... clsArr);

        Builder<T> declaredTypes(TypeDescription... typeDescriptionArr);

        Builder<T> declaredTypes(List<? extends Class<?>> list);

        Builder<T> declaredTypes(Collection<? extends TypeDescription> collection);

        Builder<T> noNestMate();

        Builder<T> nestHost(Class<?> cls);

        Builder<T> nestHost(TypeDescription typeDescription);

        Builder<T> nestMembers(Class<?>... clsArr);

        Builder<T> nestMembers(TypeDescription... typeDescriptionArr);

        Builder<T> nestMembers(List<? extends Class<?>> list);

        Builder<T> nestMembers(Collection<? extends TypeDescription> collection);

        Builder<T> permittedSubclass(Class<?>... clsArr);

        Builder<T> permittedSubclass(TypeDescription... typeDescriptionArr);

        Builder<T> permittedSubclass(List<? extends Class<?>> list);

        Builder<T> permittedSubclass(Collection<? extends TypeDescription> collection);

        Builder<T> unsealed();

        Builder<T> attribute(TypeAttributeAppender typeAttributeAppender);

        Builder<T> annotateType(Annotation... annotationArr);

        Builder<T> annotateType(List<? extends Annotation> list);

        Builder<T> annotateType(AnnotationDescription... annotationDescriptionArr);

        Builder<T> annotateType(Collection<? extends AnnotationDescription> collection);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(Type... typeArr);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(List<? extends Type> list);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(TypeDefinition... typeDefinitionArr);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(Collection<? extends TypeDefinition> collection);

        Builder<T> initializer(ByteCodeAppender byteCodeAppender);

        Builder<T> initializer(LoadedTypeInitializer loadedTypeInitializer);

        Builder<T> require(TypeDescription typeDescription, byte[] bArr);

        Builder<T> require(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer);

        Builder<T> require(DynamicType... dynamicTypeArr);

        Builder<T> require(Collection<DynamicType> collection);

        TypeVariableDefinition<T> typeVariable(String str);

        TypeVariableDefinition<T> typeVariable(String str, Type... typeArr);

        TypeVariableDefinition<T> typeVariable(String str, List<? extends Type> list);

        TypeVariableDefinition<T> typeVariable(String str, TypeDefinition... typeDefinitionArr);

        TypeVariableDefinition<T> typeVariable(String str, Collection<? extends TypeDefinition> collection);

        Builder<T> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, ModifierContributor.ForField... forFieldArr);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, Collection<? extends ModifierContributor.ForField> collection);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, int i);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, ModifierContributor.ForField... forFieldArr);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForField> collection);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, int i);

        FieldDefinition.Optional.Valuable<T> define(Field field);

        FieldDefinition.Optional.Valuable<T> define(FieldDescription fieldDescription);

        FieldDefinition.Optional<T> serialVersionUid(long j);

        FieldDefinition.Valuable<T> field(ElementMatcher<? super FieldDescription> elementMatcher);

        FieldDefinition.Valuable<T> field(LatentMatcher<? super FieldDescription> latentMatcher);

        Builder<T> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher);

        Builder<T> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, ModifierContributor.ForMethod... forMethodArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, ModifierContributor.ForMethod... forMethodArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(ModifierContributor.ForMethod... forMethodArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(int i);

        MethodDefinition.ImplementationDefinition<T> define(Method method);

        MethodDefinition.ImplementationDefinition<T> define(Constructor<?> constructor);

        MethodDefinition.ImplementationDefinition<T> define(MethodDescription methodDescription);

        FieldDefinition.Optional<T> defineProperty(String str, Type type);

        FieldDefinition.Optional<T> defineProperty(String str, Type type, boolean z);

        FieldDefinition.Optional<T> defineProperty(String str, TypeDefinition typeDefinition);

        FieldDefinition.Optional<T> defineProperty(String str, TypeDefinition typeDefinition, boolean z);

        MethodDefinition.ImplementationDefinition<T> method(ElementMatcher<? super MethodDescription> elementMatcher);

        MethodDefinition.ImplementationDefinition<T> constructor(ElementMatcher<? super MethodDescription> elementMatcher);

        MethodDefinition.ImplementationDefinition<T> invokable(ElementMatcher<? super MethodDescription> elementMatcher);

        MethodDefinition.ImplementationDefinition<T> invokable(LatentMatcher<? super MethodDescription> latentMatcher);

        Builder<T> withHashCodeEquals();

        Builder<T> withToString();

        RecordComponentDefinition.Optional<T> defineRecordComponent(String str, Type type);

        RecordComponentDefinition.Optional<T> defineRecordComponent(String str, TypeDefinition typeDefinition);

        RecordComponentDefinition.Optional<T> define(RecordComponentDescription recordComponentDescription);

        RecordComponentDefinition<T> recordComponent(ElementMatcher<? super RecordComponentDescription> elementMatcher);

        RecordComponentDefinition<T> recordComponent(LatentMatcher<? super RecordComponentDescription> latentMatcher);

        ContextClassVisitor wrap(ClassVisitor classVisitor);

        ContextClassVisitor wrap(ClassVisitor classVisitor, int i, int i2);

        ContextClassVisitor wrap(ClassVisitor classVisitor, TypePool typePool);

        ContextClassVisitor wrap(ClassVisitor classVisitor, TypePool typePool, int i, int i2);

        Unloaded<T> make();

        Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy);

        Unloaded<T> make(TypePool typePool);

        Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool);

        TypeDescription toTypeDescription();

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$TypeVariableDefinition.class */
        public interface TypeVariableDefinition<S> extends Builder<S> {
            TypeVariableDefinition<S> annotateTypeVariable(Annotation... annotationArr);

            TypeVariableDefinition<S> annotateTypeVariable(List<? extends Annotation> list);

            TypeVariableDefinition<S> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr);

            TypeVariableDefinition<S> annotateTypeVariable(Collection<? extends AnnotationDescription> collection);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$TypeVariableDefinition$AbstractBase.class */
            public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements TypeVariableDefinition<U> {
                @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                public TypeVariableDefinition<U> annotateTypeVariable(Annotation... annotationArr) {
                    return annotateTypeVariable(Arrays.asList(annotationArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                public TypeVariableDefinition<U> annotateTypeVariable(List<? extends Annotation> list) {
                    return annotateTypeVariable((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                public TypeVariableDefinition<U> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr) {
                    return annotateTypeVariable((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition.class */
        public interface FieldDefinition<S> {

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition$Valuable.class */
            public interface Valuable<U> extends FieldDefinition<U> {
                Optional<U> value(boolean z);

                Optional<U> value(int i);

                Optional<U> value(long j);

                Optional<U> value(float f);

                Optional<U> value(double d);

                Optional<U> value(String str);
            }

            Optional<S> annotateField(Annotation... annotationArr);

            Optional<S> annotateField(List<? extends Annotation> list);

            Optional<S> annotateField(AnnotationDescription... annotationDescriptionArr);

            Optional<S> annotateField(Collection<? extends AnnotationDescription> collection);

            Optional<S> attribute(FieldAttributeAppender.Factory factory);

            Optional<S> transform(Transformer<FieldDescription> transformer);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition$Optional.class */
            public interface Optional<U> extends Builder<U>, FieldDefinition<U> {

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition$Optional$Valuable.class */
                public interface Valuable<V> extends Optional<V>, Valuable<V> {

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition$Optional$Valuable$AbstractBase.class */
                    public static abstract class AbstractBase<U> extends AbstractBase<U> implements Valuable<U> {
                        protected abstract Optional<U> defaultValue(Object obj);

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(boolean z) {
                            return defaultValue(Integer.valueOf(z ? 1 : 0));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(int i) {
                            return defaultValue(Integer.valueOf(i));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(long j) {
                            return defaultValue(Long.valueOf(j));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(float f) {
                            return defaultValue(Float.valueOf(f));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(double d) {
                            return defaultValue(Double.valueOf(d));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(String str) {
                            if (str == null) {
                                throw new IllegalArgumentException("Cannot define 'null' as constant value");
                            }
                            return defaultValue(str);
                        }

                        @HashCodeAndEqualsPlugin.Enhance
                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition$Optional$Valuable$AbstractBase$Adapter.class */
                        private static abstract class Adapter<V> extends AbstractBase<V> {
                            protected final FieldAttributeAppender.Factory fieldAttributeAppenderFactory;
                            protected final Transformer<FieldDescription> transformer;

                            @MaybeNull
                            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                            protected final Object defaultValue;

                            protected abstract Optional<V> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, @MaybeNull Object obj);

                            public boolean equals(@MaybeNull Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                if (obj == null || getClass() != obj.getClass() || !this.fieldAttributeAppenderFactory.equals(((Adapter) obj).fieldAttributeAppenderFactory) || !this.transformer.equals(((Adapter) obj).transformer)) {
                                    return false;
                                }
                                Object obj2 = this.defaultValue;
                                Object obj3 = ((Adapter) obj).defaultValue;
                                return obj3 != null ? obj2 != null && obj2.equals(obj3) : obj2 == null;
                            }

                            public int hashCode() {
                                int hashCode = ((((getClass().hashCode() * 31) + this.fieldAttributeAppenderFactory.hashCode()) * 31) + this.transformer.hashCode()) * 31;
                                Object obj = this.defaultValue;
                                return obj != null ? hashCode + obj.hashCode() : hashCode;
                            }

                            protected Adapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, @MaybeNull Object obj) {
                                this.fieldAttributeAppenderFactory = factory;
                                this.transformer = transformer;
                                this.defaultValue = obj;
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                            public Optional<V> attribute(FieldAttributeAppender.Factory factory) {
                                return materialize(new FieldAttributeAppender.Factory.Compound(this.fieldAttributeAppenderFactory, factory), this.transformer, this.defaultValue);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                            public Optional<V> transform(Transformer<FieldDescription> transformer) {
                                return materialize(this.fieldAttributeAppenderFactory, new Transformer.Compound(this.transformer, transformer), this.defaultValue);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase
                            protected Optional<V> defaultValue(Object obj) {
                                return materialize(this.fieldAttributeAppenderFactory, this.transformer, obj);
                            }
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$FieldDefinition$Optional$AbstractBase.class */
                public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements Optional<U> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public Optional<U> annotateField(Annotation... annotationArr) {
                        return annotateField(Arrays.asList(annotationArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public Optional<U> annotateField(List<? extends Annotation> list) {
                        return annotateField((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public Optional<U> annotateField(AnnotationDescription... annotationDescriptionArr) {
                        return annotateField((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition.class */
        public interface MethodDefinition<S> extends Builder<S> {
            MethodDefinition<S> annotateMethod(Annotation... annotationArr);

            MethodDefinition<S> annotateMethod(List<? extends Annotation> list);

            MethodDefinition<S> annotateMethod(AnnotationDescription... annotationDescriptionArr);

            MethodDefinition<S> annotateMethod(Collection<? extends AnnotationDescription> collection);

            MethodDefinition<S> annotateParameter(int i, Annotation... annotationArr);

            MethodDefinition<S> annotateParameter(int i, List<? extends Annotation> list);

            MethodDefinition<S> annotateParameter(int i, AnnotationDescription... annotationDescriptionArr);

            MethodDefinition<S> annotateParameter(int i, Collection<? extends AnnotationDescription> collection);

            MethodDefinition<S> attribute(MethodAttributeAppender.Factory factory);

            MethodDefinition<S> transform(Transformer<MethodDescription> transformer);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ReceiverTypeDefinition.class */
            public interface ReceiverTypeDefinition<U> extends MethodDefinition<U> {
                MethodDefinition<U> receiverType(AnnotatedElement annotatedElement);

                MethodDefinition<U> receiverType(TypeDescription.Generic generic);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ReceiverTypeDefinition$AbstractBase.class */
                public static abstract class AbstractBase<V> extends AbstractBase<V> implements ReceiverTypeDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition
                    public MethodDefinition<V> receiverType(AnnotatedElement annotatedElement) {
                        return receiverType(TypeDefinition.Sort.describeAnnotated(annotatedElement));
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ImplementationDefinition.class */
            public interface ImplementationDefinition<U> {

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ImplementationDefinition$Optional.class */
                public interface Optional<V> extends Builder<V>, ImplementationDefinition<V> {
                }

                ReceiverTypeDefinition<U> intercept(Implementation implementation);

                ReceiverTypeDefinition<U> withoutCode();

                ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue);

                <W> ReceiverTypeDefinition<U> defaultValue(W w, Class<? extends W> cls);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ImplementationDefinition$AbstractBase.class */
                public static abstract class AbstractBase<V> implements ImplementationDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public <W> ReceiverTypeDefinition<V> defaultValue(W w, Class<? extends W> cls) {
                        return defaultValue(AnnotationDescription.ForLoadedAnnotation.asValue(w, cls));
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$TypeVariableDefinition.class */
            public interface TypeVariableDefinition<U> extends ImplementationDefinition<U> {
                Annotatable<U> typeVariable(String str);

                Annotatable<U> typeVariable(String str, Type... typeArr);

                Annotatable<U> typeVariable(String str, List<? extends Type> list);

                Annotatable<U> typeVariable(String str, TypeDefinition... typeDefinitionArr);

                Annotatable<U> typeVariable(String str, Collection<? extends TypeDefinition> collection);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$TypeVariableDefinition$Annotatable.class */
                public interface Annotatable<V> extends TypeVariableDefinition<V> {
                    Annotatable<V> annotateTypeVariable(Annotation... annotationArr);

                    Annotatable<V> annotateTypeVariable(List<? extends Annotation> list);

                    Annotatable<V> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr);

                    Annotatable<V> annotateTypeVariable(Collection<? extends AnnotationDescription> collection);

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$TypeVariableDefinition$Annotatable$AbstractBase.class */
                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public Annotatable<W> annotateTypeVariable(Annotation... annotationArr) {
                            return annotateTypeVariable(Arrays.asList(annotationArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public Annotatable<W> annotateTypeVariable(List<? extends Annotation> list) {
                            return annotateTypeVariable((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public Annotatable<W> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr) {
                            return annotateTypeVariable((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                        }

                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$TypeVariableDefinition$Annotatable$AbstractBase$Adapter.class */
                        protected static abstract class Adapter<X> extends AbstractBase<X> {
                            protected abstract ParameterDefinition<X> materialize();

                            protected Adapter() {
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                            public Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                return materialize().typeVariable(str, collection);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                return materialize().intercept(implementation);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> withoutCode() {
                                return materialize().withoutCode();
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                return materialize().defaultValue(annotationValue);
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                return materialize().defaultValue(v, cls);
                            }
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$TypeVariableDefinition$AbstractBase.class */
                public static abstract class AbstractBase<V> extends ImplementationDefinition.AbstractBase<V> implements TypeVariableDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str) {
                        return typeVariable(str, Collections.singletonList(Object.class));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str, Type... typeArr) {
                        return typeVariable(str, Arrays.asList(typeArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str, List<? extends Type> list) {
                        return typeVariable(str, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str, TypeDefinition... typeDefinitionArr) {
                        return typeVariable(str, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ExceptionDefinition.class */
            public interface ExceptionDefinition<U> extends TypeVariableDefinition<U> {
                ExceptionDefinition<U> throwing(Type... typeArr);

                ExceptionDefinition<U> throwing(List<? extends Type> list);

                ExceptionDefinition<U> throwing(TypeDefinition... typeDefinitionArr);

                ExceptionDefinition<U> throwing(Collection<? extends TypeDefinition> collection);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ExceptionDefinition$AbstractBase.class */
                public static abstract class AbstractBase<V> extends TypeVariableDefinition.AbstractBase<V> implements ExceptionDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public ExceptionDefinition<V> throwing(Type... typeArr) {
                        return throwing(Arrays.asList(typeArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public ExceptionDefinition<V> throwing(List<? extends Type> list) {
                        return throwing((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public ExceptionDefinition<V> throwing(TypeDefinition... typeDefinitionArr) {
                        return throwing((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition.class */
            public interface ParameterDefinition<U> extends ExceptionDefinition<U> {
                Annotatable<U> withParameter(Type type, String str, ModifierContributor.ForParameter... forParameterArr);

                Annotatable<U> withParameter(Type type, String str, Collection<? extends ModifierContributor.ForParameter> collection);

                Annotatable<U> withParameter(Type type, String str, int i);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, ModifierContributor.ForParameter... forParameterArr);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, Collection<? extends ModifierContributor.ForParameter> collection);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, int i);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Annotatable.class */
                public interface Annotatable<V> extends ParameterDefinition<V> {
                    Annotatable<V> annotateParameter(Annotation... annotationArr);

                    Annotatable<V> annotateParameter(List<? extends Annotation> list);

                    Annotatable<V> annotateParameter(AnnotationDescription... annotationDescriptionArr);

                    Annotatable<V> annotateParameter(Collection<? extends AnnotationDescription> collection);

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Annotatable$AbstractBase.class */
                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public Annotatable<W> annotateParameter(Annotation... annotationArr) {
                            return annotateParameter(Arrays.asList(annotationArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public Annotatable<W> annotateParameter(List<? extends Annotation> list) {
                            return annotateParameter((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public Annotatable<W> annotateParameter(AnnotationDescription... annotationDescriptionArr) {
                            return annotateParameter((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                        }

                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Annotatable$AbstractBase$Adapter.class */
                        protected static abstract class Adapter<X> extends AbstractBase<X> {
                            protected abstract ParameterDefinition<X> materialize();

                            protected Adapter() {
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                            public Annotatable<X> withParameter(TypeDefinition typeDefinition, String str, int i) {
                                return materialize().withParameter(typeDefinition, str, i);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                            public ExceptionDefinition<X> throwing(Collection<? extends TypeDefinition> collection) {
                                return materialize().throwing(collection);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                            public TypeVariableDefinition.Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                return materialize().typeVariable(str, collection);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                return materialize().intercept(implementation);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> withoutCode() {
                                return materialize().withoutCode();
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                return materialize().defaultValue(annotationValue);
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                return materialize().defaultValue(v, cls);
                            }
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Simple.class */
                public interface Simple<V> extends ExceptionDefinition<V> {
                    Annotatable<V> withParameter(Type type);

                    Annotatable<V> withParameter(TypeDefinition typeDefinition);

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Simple$Annotatable.class */
                    public interface Annotatable<V> extends Simple<V> {
                        Annotatable<V> annotateParameter(Annotation... annotationArr);

                        Annotatable<V> annotateParameter(List<? extends Annotation> list);

                        Annotatable<V> annotateParameter(AnnotationDescription... annotationDescriptionArr);

                        Annotatable<V> annotateParameter(Collection<? extends AnnotationDescription> collection);

                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Simple$Annotatable$AbstractBase.class */
                        public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                            public Annotatable<W> annotateParameter(Annotation... annotationArr) {
                                return annotateParameter(Arrays.asList(annotationArr));
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                            public Annotatable<W> annotateParameter(List<? extends Annotation> list) {
                                return annotateParameter((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                            public Annotatable<W> annotateParameter(AnnotationDescription... annotationDescriptionArr) {
                                return annotateParameter((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                            }

                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Simple$Annotatable$AbstractBase$Adapter.class */
                            protected static abstract class Adapter<X> extends AbstractBase<X> {
                                protected abstract Simple<X> materialize();

                                protected Adapter() {
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                                public Annotatable<X> withParameter(TypeDefinition typeDefinition) {
                                    return materialize().withParameter(typeDefinition);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                                public ExceptionDefinition<X> throwing(Collection<? extends TypeDefinition> collection) {
                                    return materialize().throwing(collection);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                                public TypeVariableDefinition.Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                    return materialize().typeVariable(str, collection);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                    return materialize().intercept(implementation);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public ReceiverTypeDefinition<X> withoutCode() {
                                    return materialize().withoutCode();
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                    return materialize().defaultValue(annotationValue);
                                }

                                /* JADX WARN: Multi-variable type inference failed */
                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                    return materialize().defaultValue(v, cls);
                                }
                            }
                        }
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Simple$AbstractBase.class */
                    public static abstract class AbstractBase<W> extends ExceptionDefinition.AbstractBase<W> implements Simple<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                        public Annotatable<W> withParameter(Type type) {
                            return withParameter(TypeDefinition.Sort.describe(type));
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Initial.class */
                public interface Initial<V> extends ParameterDefinition<V>, Simple<V> {
                    ExceptionDefinition<V> withParameters(Type... typeArr);

                    ExceptionDefinition<V> withParameters(List<? extends Type> list);

                    ExceptionDefinition<V> withParameters(TypeDefinition... typeDefinitionArr);

                    ExceptionDefinition<V> withParameters(Collection<? extends TypeDefinition> collection);

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$Initial$AbstractBase.class */
                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Initial<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                        public Simple.Annotatable<W> withParameter(Type type) {
                            return withParameter(TypeDefinition.Sort.describe(type));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(Type... typeArr) {
                            return withParameters(Arrays.asList(typeArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(List<? extends Type> list) {
                            return withParameters((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(TypeDefinition... typeDefinitionArr) {
                            return withParameters((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(Collection<? extends TypeDefinition> collection) {
                            AbstractBase<W> abstractBase = this;
                            Iterator<? extends TypeDefinition> it = collection.iterator();
                            while (it.hasNext()) {
                                abstractBase = abstractBase.withParameter(it.next());
                            }
                            return abstractBase;
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$ParameterDefinition$AbstractBase.class */
                public static abstract class AbstractBase<V> extends ExceptionDefinition.AbstractBase<V> implements ParameterDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(Type type, String str, ModifierContributor.ForParameter... forParameterArr) {
                        return withParameter(type, str, Arrays.asList(forParameterArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(Type type, String str, Collection<? extends ModifierContributor.ForParameter> collection) {
                        return withParameter(type, str, ModifierContributor.Resolver.of(collection).resolve());
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(Type type, String str, int i) {
                        return withParameter(TypeDefinition.Sort.describe(type), str, i);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(TypeDefinition typeDefinition, String str, ModifierContributor.ForParameter... forParameterArr) {
                        return withParameter(typeDefinition, str, Arrays.asList(forParameterArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(TypeDefinition typeDefinition, String str, Collection<? extends ModifierContributor.ForParameter> collection) {
                        return withParameter(typeDefinition, str, ModifierContributor.Resolver.of(collection).resolve());
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$AbstractBase.class */
            public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements MethodDefinition<U> {
                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateMethod(Annotation... annotationArr) {
                    return annotateMethod(Arrays.asList(annotationArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateMethod(List<? extends Annotation> list) {
                    return annotateMethod((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateMethod(AnnotationDescription... annotationDescriptionArr) {
                    return annotateMethod((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateParameter(int i, Annotation... annotationArr) {
                    return annotateParameter(i, Arrays.asList(annotationArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateParameter(int i, List<? extends Annotation> list) {
                    return annotateParameter(i, (Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateParameter(int i, AnnotationDescription... annotationDescriptionArr) {
                    return annotateParameter(i, (Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$MethodDefinition$AbstractBase$Adapter.class */
                protected static abstract class Adapter<V> extends ReceiverTypeDefinition.AbstractBase<V> {
                    protected final MethodRegistry.Handler handler;
                    protected final MethodAttributeAppender.Factory methodAttributeAppenderFactory;
                    protected final Transformer<MethodDescription> transformer;

                    protected abstract MethodDefinition<V> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer);

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.handler.equals(((Adapter) obj).handler) && this.methodAttributeAppenderFactory.equals(((Adapter) obj).methodAttributeAppenderFactory) && this.transformer.equals(((Adapter) obj).transformer);
                    }

                    public int hashCode() {
                        return (((((getClass().hashCode() * 31) + this.handler.hashCode()) * 31) + this.methodAttributeAppenderFactory.hashCode()) * 31) + this.transformer.hashCode();
                    }

                    protected Adapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                        this.handler = handler;
                        this.methodAttributeAppenderFactory = factory;
                        this.transformer = transformer;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                    public MethodDefinition<V> attribute(MethodAttributeAppender.Factory factory) {
                        return materialize(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, factory), this.transformer);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                    public MethodDefinition<V> transform(Transformer<MethodDescription> transformer) {
                        return materialize(this.handler, this.methodAttributeAppenderFactory, new Transformer.Compound(this.transformer, transformer));
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$RecordComponentDefinition.class */
        public interface RecordComponentDefinition<S> {
            Optional<S> annotateRecordComponent(Annotation... annotationArr);

            Optional<S> annotateRecordComponent(List<? extends Annotation> list);

            Optional<S> annotateRecordComponent(AnnotationDescription... annotationDescriptionArr);

            Optional<S> annotateRecordComponent(Collection<? extends AnnotationDescription> collection);

            Optional<S> attribute(RecordComponentAttributeAppender.Factory factory);

            Optional<S> transform(Transformer<RecordComponentDescription> transformer);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$RecordComponentDefinition$Optional.class */
            public interface Optional<U> extends Builder<U>, RecordComponentDefinition<U> {

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$RecordComponentDefinition$Optional$AbstractBase.class */
                public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements Optional<U> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public Optional<U> annotateRecordComponent(Annotation... annotationArr) {
                        return annotateRecordComponent(Arrays.asList(annotationArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public Optional<U> annotateRecordComponent(List<? extends Annotation> list) {
                        return annotateRecordComponent((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public Optional<U> annotateRecordComponent(AnnotationDescription... annotationDescriptionArr) {
                        return annotateRecordComponent((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase.class */
        public static abstract class AbstractBase<S> implements Builder<S> {
            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public InnerTypeDefinition.ForType<S> innerTypeOf(Class<?> cls) {
                return innerTypeOf(TypeDescription.ForLoadedType.of(cls));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public InnerTypeDefinition<S> innerTypeOf(Method method) {
                return innerTypeOf(new MethodDescription.ForLoadedMethod(method));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public InnerTypeDefinition<S> innerTypeOf(Constructor<?> constructor) {
                return innerTypeOf(new MethodDescription.ForLoadedConstructor(constructor));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> declaredTypes(Class<?>... clsArr) {
                return declaredTypes(Arrays.asList(clsArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> declaredTypes(TypeDescription... typeDescriptionArr) {
                return declaredTypes((Collection<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> declaredTypes(List<? extends Class<?>> list) {
                return declaredTypes((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> noNestMate() {
                return nestHost(TargetType.DESCRIPTION);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> nestHost(Class<?> cls) {
                return nestHost(TypeDescription.ForLoadedType.of(cls));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> nestMembers(Class<?>... clsArr) {
                return nestMembers(Arrays.asList(clsArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> nestMembers(TypeDescription... typeDescriptionArr) {
                return nestMembers((Collection<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> nestMembers(List<? extends Class<?>> list) {
                return nestMembers((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> permittedSubclass(Class<?>... clsArr) {
                return permittedSubclass(Arrays.asList(clsArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> permittedSubclass(TypeDescription... typeDescriptionArr) {
                return permittedSubclass((Collection<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> permittedSubclass(List<? extends Class<?>> list) {
                return permittedSubclass((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> annotateType(Annotation... annotationArr) {
                return annotateType(Arrays.asList(annotationArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> annotateType(List<? extends Annotation> list) {
                return annotateType((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> annotateType(AnnotationDescription... annotationDescriptionArr) {
                return annotateType((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> modifiers(ModifierContributor.ForType... forTypeArr) {
                return modifiers(Arrays.asList(forTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> modifiers(Collection<? extends ModifierContributor.ForType> collection) {
                return modifiers(ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> merge(ModifierContributor.ForType... forTypeArr) {
                return merge(Arrays.asList(forTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition.Optional<S> implement(Type... typeArr) {
                return implement(Arrays.asList(typeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition.Optional<S> implement(List<? extends Type> list) {
                return implement((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition.Optional<S> implement(TypeDefinition... typeDefinitionArr) {
                return implement((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str) {
                return typeVariable(str, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str, Type... typeArr) {
                return typeVariable(str, Arrays.asList(typeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str, List<? extends Type> list) {
                return typeVariable(str, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str, TypeDefinition... typeDefinitionArr) {
                return typeVariable(str, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public RecordComponentDefinition.Optional<S> defineRecordComponent(String str, Type type) {
                return defineRecordComponent(str, TypeDefinition.Sort.describe(type));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public RecordComponentDefinition.Optional<S> define(RecordComponentDescription recordComponentDescription) {
                return defineRecordComponent(recordComponentDescription.getActualName(), recordComponentDescription.getType());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public RecordComponentDefinition<S> recordComponent(ElementMatcher<? super RecordComponentDescription> elementMatcher) {
                return recordComponent(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, ModifierContributor.ForField... forFieldArr) {
                return defineField(str, type, Arrays.asList(forFieldArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, Collection<? extends ModifierContributor.ForField> collection) {
                return defineField(str, type, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, int i) {
                return defineField(str, TypeDefinition.Sort.describe(type), i);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, TypeDefinition typeDefinition, ModifierContributor.ForField... forFieldArr) {
                return defineField(str, typeDefinition, Arrays.asList(forFieldArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForField> collection) {
                return defineField(str, typeDefinition, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> define(Field field) {
                return define(new FieldDescription.ForLoadedField(field));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> define(FieldDescription fieldDescription) {
                return defineField(fieldDescription.getName(), fieldDescription.getType(), fieldDescription.getModifiers());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> serialVersionUid(long j) {
                return defineField("serialVersionUID", Long.TYPE, Visibility.PRIVATE, FieldManifestation.FINAL, Ownership.STATIC).value(j);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Valuable<S> field(ElementMatcher<? super FieldDescription> elementMatcher) {
                return field(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher) {
                return ignoreAlso(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, ModifierContributor.ForMethod... forMethodArr) {
                return defineMethod(str, type, Arrays.asList(forMethodArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineMethod(str, type, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, int i) {
                return defineMethod(str, TypeDefinition.Sort.describe(type), i);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, TypeDefinition typeDefinition, ModifierContributor.ForMethod... forMethodArr) {
                return defineMethod(str, typeDefinition, Arrays.asList(forMethodArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineMethod(str, typeDefinition, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineConstructor(ModifierContributor.ForMethod... forMethodArr) {
                return defineConstructor(Arrays.asList(forMethodArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineConstructor(Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineConstructor(ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> define(Method method) {
                return define(new MethodDescription.ForLoadedMethod(method));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> define(Constructor<?> constructor) {
                return define(new MethodDescription.ForLoadedConstructor(constructor));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> define(MethodDescription methodDescription) {
                MethodDefinition.ParameterDefinition.Initial<S> defineMethod;
                MethodDefinition.ParameterDefinition.Initial<S> withParameters;
                if (methodDescription.isConstructor()) {
                    defineMethod = defineConstructor(methodDescription.getModifiers());
                } else {
                    defineMethod = defineMethod(methodDescription.getInternalName(), methodDescription.getReturnType(), methodDescription.getModifiers());
                }
                MethodDefinition.ParameterDefinition.Initial<S> initial = defineMethod;
                ParameterList<?> parameters = methodDescription.getParameters();
                if (parameters.hasExplicitMetaData()) {
                    MethodDefinition.ParameterDefinition.Initial<S> initial2 = initial;
                    Iterator it = parameters.iterator();
                    while (it.hasNext()) {
                        ParameterDescription parameterDescription = (ParameterDescription) it.next();
                        initial2 = initial2.withParameter(parameterDescription.getType(), parameterDescription.getName(), parameterDescription.getModifiers());
                    }
                    withParameters = initial2;
                } else {
                    withParameters = initial.withParameters((Collection<? extends TypeDefinition>) parameters.asTypeList());
                }
                MethodDefinition.ExceptionDefinition<S> throwing = withParameters.throwing((Collection<? extends TypeDefinition>) methodDescription.getExceptionTypes());
                for (TypeDescription.Generic generic : methodDescription.getTypeVariables()) {
                    throwing = throwing.typeVariable(generic.getSymbol(), (Collection<? extends TypeDefinition>) generic.getUpperBounds());
                }
                return throwing;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, Type type) {
                return defineProperty(str, TypeDefinition.Sort.describe(type));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, Type type, boolean z) {
                return defineProperty(str, TypeDefinition.Sort.describe(type), z);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, TypeDefinition typeDefinition) {
                return defineProperty(str, typeDefinition, false);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, TypeDefinition typeDefinition, boolean z) {
                FieldManifestation fieldManifestation;
                if (str.length() == 0) {
                    throw new IllegalArgumentException("A bean property cannot have an empty name");
                }
                if (typeDefinition.represents(Void.TYPE)) {
                    throw new IllegalArgumentException("A bean property cannot have a void type");
                }
                AbstractBase<S> abstractBase = this;
                if (!z) {
                    abstractBase = abstractBase.defineMethod("set" + Character.toUpperCase(str.charAt(0)) + str.substring(1), Void.TYPE, Visibility.PUBLIC).withParameters(typeDefinition).intercept(FieldAccessor.ofField(str));
                    fieldManifestation = FieldManifestation.PLAIN;
                } else {
                    fieldManifestation = FieldManifestation.FINAL;
                }
                return abstractBase.defineMethod((typeDefinition.represents(Boolean.TYPE) ? "is" : "get") + Character.toUpperCase(str.charAt(0)) + str.substring(1), typeDefinition, Visibility.PUBLIC).intercept(FieldAccessor.ofField(str)).defineField(str, typeDefinition, Visibility.PRIVATE, fieldManifestation);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> method(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(ElementMatchers.isMethod().and(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> constructor(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(ElementMatchers.isConstructor().and(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> invokable(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> withHashCodeEquals() {
                return method(ElementMatchers.isHashCode()).intercept(HashCodeMethod.usingDefaultOffset().withIgnoredFields(ElementMatchers.isSynthetic())).method(ElementMatchers.isEquals()).intercept(EqualsMethod.isolated().withIgnoredFields(ElementMatchers.isSynthetic()));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> withToString() {
                return method(ElementMatchers.isToString()).intercept(ToStringMethod.prefixedBySimpleClassName());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> require(TypeDescription typeDescription, byte[] bArr) {
                return require(typeDescription, bArr, LoadedTypeInitializer.NoOp.INSTANCE);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> require(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer) {
                return require(new Default(typeDescription, bArr, loadedTypeInitializer, Collections.emptyList()));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> require(DynamicType... dynamicTypeArr) {
                return require(Arrays.asList(dynamicTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public ContextClassVisitor wrap(ClassVisitor classVisitor) {
                return wrap(classVisitor, 0, 0);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public ContextClassVisitor wrap(ClassVisitor classVisitor, TypePool typePool) {
                return wrap(classVisitor, typePool, 0, 0);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Unloaded<S> make(TypePool typePool) {
                return make(TypeResolutionStrategy.Passive.INSTANCE, typePool);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Unloaded<S> make() {
                return make(TypeResolutionStrategy.Passive.INSTANCE);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Delegator.class */
            public static abstract class Delegator<U> extends AbstractBase<U> {
                protected abstract Builder<U> materialize();

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> visit(AsmVisitorWrapper asmVisitorWrapper) {
                    return materialize().visit(asmVisitorWrapper);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(LoadedTypeInitializer loadedTypeInitializer) {
                    return materialize().initializer(loadedTypeInitializer);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> annotateType(Collection<? extends AnnotationDescription> collection) {
                    return materialize().annotateType(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> attribute(TypeAttributeAppender typeAttributeAppender) {
                    return materialize().attribute(typeAttributeAppender);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> modifiers(int i) {
                    return materialize().modifiers(i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> merge(Collection<? extends ModifierContributor.ForType> collection) {
                    return materialize().merge(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> suffix(String str) {
                    return materialize().suffix(str);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> name(String str) {
                    return materialize().name(str);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> topLevelType() {
                    return materialize().topLevelType();
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public InnerTypeDefinition.ForType<U> innerTypeOf(TypeDescription typeDescription) {
                    return materialize().innerTypeOf(typeDescription);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public InnerTypeDefinition<U> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape) {
                    return materialize().innerTypeOf(inDefinedShape);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> declaredTypes(Collection<? extends TypeDescription> collection) {
                    return materialize().declaredTypes(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> nestHost(TypeDescription typeDescription) {
                    return materialize().nestHost(typeDescription);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> nestMembers(Collection<? extends TypeDescription> collection) {
                    return materialize().nestMembers(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> permittedSubclass(Collection<? extends TypeDescription> collection) {
                    return materialize().permittedSubclass(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> unsealed() {
                    return materialize().unsealed();
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition.Optional<U> implement(Collection<? extends TypeDefinition> collection) {
                    return materialize().implement(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(ByteCodeAppender byteCodeAppender) {
                    return materialize().initializer(byteCodeAppender);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher) {
                    return materialize().ignoreAlso(elementMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize().ignoreAlso(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public TypeVariableDefinition<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                    return materialize().typeVariable(str, collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
                    return materialize().transform(elementMatcher, transformer);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Optional.Valuable<U> defineField(String str, TypeDefinition typeDefinition, int i) {
                    return materialize().defineField(str, typeDefinition, i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Valuable<U> field(LatentMatcher<? super FieldDescription> latentMatcher) {
                    return materialize().field(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineMethod(String str, TypeDefinition typeDefinition, int i) {
                    return materialize().defineMethod(str, typeDefinition, i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineConstructor(int i) {
                    return materialize().defineConstructor(i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition<U> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize().invokable(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> require(Collection<DynamicType> collection) {
                    return materialize().require(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public RecordComponentDefinition.Optional<U> defineRecordComponent(String str, TypeDefinition typeDefinition) {
                    return materialize().defineRecordComponent(str, typeDefinition);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public RecordComponentDefinition.Optional<U> define(RecordComponentDescription recordComponentDescription) {
                    return materialize().define(recordComponentDescription);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public RecordComponentDefinition<U> recordComponent(ElementMatcher<? super RecordComponentDescription> elementMatcher) {
                    return materialize().recordComponent(elementMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public RecordComponentDefinition<U> recordComponent(LatentMatcher<? super RecordComponentDescription> latentMatcher) {
                    return materialize().recordComponent(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public ContextClassVisitor wrap(ClassVisitor classVisitor, int i, int i2) {
                    return materialize().wrap(classVisitor, i, i2);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public ContextClassVisitor wrap(ClassVisitor classVisitor, TypePool typePool, int i, int i2) {
                    return materialize().wrap(classVisitor, typePool, i, i2);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make() {
                    return materialize().make();
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy) {
                    return materialize().make(typeResolutionStrategy);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypePool typePool) {
                    return materialize().make(typePool);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
                    return materialize().make(typeResolutionStrategy, typePool);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public TypeDescription toTypeDescription() {
                    return materialize().toTypeDescription();
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$UsingTypeWriter.class */
            public static abstract class UsingTypeWriter<U> extends AbstractBase<U> {
                protected abstract TypeWriter<U> toTypeWriter();

                /* JADX INFO: Access modifiers changed from: protected */
                public abstract TypeWriter<U> toTypeWriter(TypePool typePool);

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public ContextClassVisitor wrap(ClassVisitor classVisitor, int i, int i2) {
                    return toTypeWriter().wrap(classVisitor, i, i2);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public ContextClassVisitor wrap(ClassVisitor classVisitor, TypePool typePool, int i, int i2) {
                    return toTypeWriter(typePool).wrap(classVisitor, i, i2);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy) {
                    return toTypeWriter().make(typeResolutionStrategy.resolve());
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
                    return toTypeWriter(typePool).make(typeResolutionStrategy.resolve());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter.class */
            public static abstract class Adapter<U> extends UsingTypeWriter<U> {
                protected final InstrumentedType.WithFlexibleName instrumentedType;
                protected final FieldRegistry fieldRegistry;
                protected final MethodRegistry methodRegistry;
                protected final RecordComponentRegistry recordComponentRegistry;
                protected final TypeAttributeAppender typeAttributeAppender;
                protected final AsmVisitorWrapper asmVisitorWrapper;
                protected final ClassFileVersion classFileVersion;
                protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
                protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
                protected final AnnotationRetention annotationRetention;
                protected final Implementation.Context.Factory implementationContextFactory;
                protected final MethodGraph.Compiler methodGraphCompiler;
                protected final TypeValidation typeValidation;
                protected final VisibilityBridgeStrategy visibilityBridgeStrategy;
                protected final ClassWriterStrategy classWriterStrategy;
                protected final LatentMatcher<? super MethodDescription> ignoredMethods;
                protected final List<? extends DynamicType> auxiliaryTypes;

                protected abstract Builder<U> materialize(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, RecordComponentRegistry recordComponentRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, VisibilityBridgeStrategy visibilityBridgeStrategy, ClassWriterStrategy classWriterStrategy, LatentMatcher<? super MethodDescription> latentMatcher, List<? extends DynamicType> list);

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationRetention.equals(((Adapter) obj).annotationRetention) && this.typeValidation.equals(((Adapter) obj).typeValidation) && this.instrumentedType.equals(((Adapter) obj).instrumentedType) && this.fieldRegistry.equals(((Adapter) obj).fieldRegistry) && this.methodRegistry.equals(((Adapter) obj).methodRegistry) && this.recordComponentRegistry.equals(((Adapter) obj).recordComponentRegistry) && this.typeAttributeAppender.equals(((Adapter) obj).typeAttributeAppender) && this.asmVisitorWrapper.equals(((Adapter) obj).asmVisitorWrapper) && this.classFileVersion.equals(((Adapter) obj).classFileVersion) && this.auxiliaryTypeNamingStrategy.equals(((Adapter) obj).auxiliaryTypeNamingStrategy) && this.annotationValueFilterFactory.equals(((Adapter) obj).annotationValueFilterFactory) && this.implementationContextFactory.equals(((Adapter) obj).implementationContextFactory) && this.methodGraphCompiler.equals(((Adapter) obj).methodGraphCompiler) && this.visibilityBridgeStrategy.equals(((Adapter) obj).visibilityBridgeStrategy) && this.classWriterStrategy.equals(((Adapter) obj).classWriterStrategy) && this.ignoredMethods.equals(((Adapter) obj).ignoredMethods) && this.auxiliaryTypes.equals(((Adapter) obj).auxiliaryTypes);
                }

                public int hashCode() {
                    return (((((((((((((((((((((((((((((((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.fieldRegistry.hashCode()) * 31) + this.methodRegistry.hashCode()) * 31) + this.recordComponentRegistry.hashCode()) * 31) + this.typeAttributeAppender.hashCode()) * 31) + this.asmVisitorWrapper.hashCode()) * 31) + this.classFileVersion.hashCode()) * 31) + this.auxiliaryTypeNamingStrategy.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.annotationRetention.hashCode()) * 31) + this.implementationContextFactory.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.typeValidation.hashCode()) * 31) + this.visibilityBridgeStrategy.hashCode()) * 31) + this.classWriterStrategy.hashCode()) * 31) + this.ignoredMethods.hashCode()) * 31) + this.auxiliaryTypes.hashCode();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Adapter(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, RecordComponentRegistry recordComponentRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, VisibilityBridgeStrategy visibilityBridgeStrategy, ClassWriterStrategy classWriterStrategy, LatentMatcher<? super MethodDescription> latentMatcher, List<? extends DynamicType> list) {
                    this.instrumentedType = withFlexibleName;
                    this.fieldRegistry = fieldRegistry;
                    this.methodRegistry = methodRegistry;
                    this.recordComponentRegistry = recordComponentRegistry;
                    this.typeAttributeAppender = typeAttributeAppender;
                    this.asmVisitorWrapper = asmVisitorWrapper;
                    this.classFileVersion = classFileVersion;
                    this.auxiliaryTypeNamingStrategy = namingStrategy;
                    this.annotationValueFilterFactory = factory;
                    this.annotationRetention = annotationRetention;
                    this.implementationContextFactory = factory2;
                    this.methodGraphCompiler = compiler;
                    this.typeValidation = typeValidation;
                    this.visibilityBridgeStrategy = visibilityBridgeStrategy;
                    this.classWriterStrategy = classWriterStrategy;
                    this.ignoredMethods = latentMatcher;
                    this.auxiliaryTypes = list;
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Optional.Valuable<U> defineField(String str, TypeDefinition typeDefinition, int i) {
                    return new FieldDefinitionAdapter(this, new FieldDescription.Token(str, i, typeDefinition.asGenericType()));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Valuable<U> field(LatentMatcher<? super FieldDescription> latentMatcher) {
                    return new FieldMatchAdapter(this, latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineMethod(String str, TypeDefinition typeDefinition, int i) {
                    return new MethodDefinitionAdapter(new MethodDescription.Token(str, i, typeDefinition.asGenericType()));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineConstructor(int i) {
                    return new MethodDefinitionAdapter(new MethodDescription.Token(i));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition<U> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return new MethodMatchAdapter(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition.Optional<U> implement(Collection<? extends TypeDefinition> collection) {
                    return new OptionalMethodMatchAdapter(new TypeList.Generic.Explicit(new ArrayList(collection)));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, new LatentMatcher.Disjunction(this.ignoredMethods, latentMatcher), this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public RecordComponentDefinition.Optional<U> defineRecordComponent(String str, TypeDefinition typeDefinition) {
                    return new RecordComponentDefinitionAdapter(this, new RecordComponentDescription.Token(str, typeDefinition.asGenericType()));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public RecordComponentDefinition<U> recordComponent(LatentMatcher<? super RecordComponentDescription> latentMatcher) {
                    return new RecordComponentMatchAdapter(this, latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(ByteCodeAppender byteCodeAppender) {
                    return materialize(this.instrumentedType.withInitializer(byteCodeAppender), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(LoadedTypeInitializer loadedTypeInitializer) {
                    return materialize(this.instrumentedType.withInitializer(loadedTypeInitializer), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> name(String str) {
                    return materialize(this.instrumentedType.withName(str), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> suffix(String str) {
                    return name(this.instrumentedType.getName() + "$" + str);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> modifiers(int i) {
                    return materialize(this.instrumentedType.withModifiers(i), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> merge(Collection<? extends ModifierContributor.ForType> collection) {
                    return materialize(this.instrumentedType.withModifiers(ModifierContributor.Resolver.of(collection).resolve(this.instrumentedType.getModifiers())), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> topLevelType() {
                    return materialize(this.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingType(TypeDescription.UNDEFINED).withLocalClass(false), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public InnerTypeDefinition.ForType<U> innerTypeOf(TypeDescription typeDescription) {
                    return new InnerTypeDefinitionForTypeAdapter(typeDescription);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public InnerTypeDefinition<U> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape) {
                    return inDefinedShape.isTypeInitializer() ? new InnerTypeDefinitionForTypeAdapter(inDefinedShape.getDeclaringType()) : new InnerTypeDefinitionForMethodAdapter(inDefinedShape);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> declaredTypes(Collection<? extends TypeDescription> collection) {
                    return materialize(this.instrumentedType.withDeclaredTypes((TypeList) new TypeList.Explicit(new ArrayList(collection))), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> nestHost(TypeDescription typeDescription) {
                    return materialize(this.instrumentedType.withNestHost(typeDescription), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> nestMembers(Collection<? extends TypeDescription> collection) {
                    return materialize(this.instrumentedType.withNestMembers((TypeList) new TypeList.Explicit(new ArrayList(collection))), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> permittedSubclass(Collection<? extends TypeDescription> collection) {
                    return materialize(this.instrumentedType.withPermittedSubclasses((TypeList) new TypeList.Explicit(new ArrayList(collection))), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> unsealed() {
                    return materialize(this.instrumentedType.withPermittedSubclasses(TypeList.UNDEFINED), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public TypeVariableDefinition<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                    return new TypeVariableDefinitionAdapter(new TypeVariableToken(str, new TypeList.Generic.Explicit(new ArrayList(collection))));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
                    return materialize(this.instrumentedType.withTypeVariables(elementMatcher, transformer), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> attribute(TypeAttributeAppender typeAttributeAppender) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, new TypeAttributeAppender.Compound(this.typeAttributeAppender, typeAttributeAppender), this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> annotateType(Collection<? extends AnnotationDescription> collection) {
                    return materialize(this.instrumentedType.withAnnotations((List<? extends AnnotationDescription>) new ArrayList(collection)), this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> visit(AsmVisitorWrapper asmVisitorWrapper) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, new AsmVisitorWrapper.Compound(this.asmVisitorWrapper, asmVisitorWrapper), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> require(Collection<DynamicType> collection) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.recordComponentRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.visibilityBridgeStrategy, this.classWriterStrategy, this.ignoredMethods, CompoundList.of((List) this.auxiliaryTypes, (List) new ArrayList(collection)));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public TypeDescription toTypeDescription() {
                    return this.instrumentedType;
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$InnerTypeDefinitionForTypeAdapter.class */
                protected class InnerTypeDefinitionForTypeAdapter extends Delegator<U> implements InnerTypeDefinition.ForType<U> {
                    private final TypeDescription typeDescription;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((InnerTypeDefinitionForTypeAdapter) obj).typeDescription) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.typeDescription.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected InnerTypeDefinitionForTypeAdapter(TypeDescription typeDescription) {
                        this.typeDescription = typeDescription;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.InnerTypeDefinition
                    public Builder<U> asAnonymousType() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingType(this.typeDescription).withAnonymousClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.InnerTypeDefinition.ForType
                    public Builder<U> asMemberType() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withDeclaringType(this.typeDescription).withEnclosingType(this.typeDescription).withAnonymousClass(false).withLocalClass(false), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingType(this.typeDescription).withLocalClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$InnerTypeDefinitionForMethodAdapter.class */
                protected class InnerTypeDefinitionForMethodAdapter extends Delegator<U> implements InnerTypeDefinition<U> {
                    private final MethodDescription.InDefinedShape methodDescription;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((InnerTypeDefinitionForMethodAdapter) obj).methodDescription) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected InnerTypeDefinitionForMethodAdapter(MethodDescription.InDefinedShape inDefinedShape) {
                        this.methodDescription = inDefinedShape;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.InnerTypeDefinition
                    public Builder<U> asAnonymousType() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingMethod(this.methodDescription).withAnonymousClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingMethod(this.methodDescription).withLocalClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$TypeVariableDefinitionAdapter.class */
                protected class TypeVariableDefinitionAdapter extends TypeVariableDefinition.AbstractBase<U> {
                    private final TypeVariableToken token;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.token.equals(((TypeVariableDefinitionAdapter) obj).token) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.token.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected TypeVariableDefinitionAdapter(TypeVariableToken typeVariableToken) {
                        this.token = typeVariableToken;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                    public TypeVariableDefinition<U> annotateTypeVariable(Collection<? extends AnnotationDescription> collection) {
                        return new TypeVariableDefinitionAdapter(new TypeVariableToken(this.token.getSymbol(), this.token.getBounds(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withTypeVariable(this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$FieldDefinitionAdapter.class */
                protected class FieldDefinitionAdapter extends FieldDefinition.Optional.Valuable.AbstractBase.Adapter<U> {
                    private final FieldDescription.Token token;

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.token.equals(((FieldDefinitionAdapter) obj).token) && Adapter.this.equals(Adapter.this);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.token.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected FieldDefinitionAdapter(Adapter adapter, FieldDescription.Token token) {
                        this(FieldAttributeAppender.ForInstrumentedField.INSTANCE, Transformer.NoOp.make(), FieldDescription.NO_DEFAULT_VALUE, token);
                    }

                    protected FieldDefinitionAdapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, @MaybeNull Object obj, FieldDescription.Token token) {
                        super(factory, transformer, obj);
                        this.token = token;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public FieldDefinition.Optional<U> annotateField(Collection<? extends AnnotationDescription> collection) {
                        return new FieldDefinitionAdapter(this.fieldAttributeAppenderFactory, this.transformer, this.defaultValue, new FieldDescription.Token(this.token.getName(), this.token.getModifiers(), this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withField(this.token), Adapter.this.fieldRegistry.prepend(new LatentMatcher.ForFieldToken(this.token), this.fieldAttributeAppenderFactory, this.defaultValue, this.transformer), Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    protected FieldDefinition.Optional<U> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, @MaybeNull Object obj) {
                        return new FieldDefinitionAdapter(factory, transformer, obj, this.token);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$FieldMatchAdapter.class */
                protected class FieldMatchAdapter extends FieldDefinition.Optional.Valuable.AbstractBase.Adapter<U> {
                    private final LatentMatcher<? super FieldDescription> matcher;

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((FieldMatchAdapter) obj).matcher) && Adapter.this.equals(Adapter.this);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.matcher.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected FieldMatchAdapter(Adapter adapter, LatentMatcher<? super FieldDescription> latentMatcher) {
                        this(FieldAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make(), FieldDescription.NO_DEFAULT_VALUE, latentMatcher);
                    }

                    protected FieldMatchAdapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, @MaybeNull Object obj, LatentMatcher<? super FieldDescription> latentMatcher) {
                        super(factory, transformer, obj);
                        this.matcher = latentMatcher;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public FieldDefinition.Optional<U> annotateField(Collection<? extends AnnotationDescription> collection) {
                        return attribute(new FieldAttributeAppender.Explicit(new ArrayList(collection)));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType, Adapter.this.fieldRegistry.prepend(this.matcher, this.fieldAttributeAppenderFactory, this.defaultValue, this.transformer), Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    protected FieldDefinition.Optional<U> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, @MaybeNull Object obj) {
                        return new FieldMatchAdapter(factory, transformer, obj, this.matcher);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodDefinitionAdapter.class */
                protected class MethodDefinitionAdapter extends MethodDefinition.ParameterDefinition.Initial.AbstractBase<U> {
                    private final MethodDescription.Token token;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.token.equals(((MethodDefinitionAdapter) obj).token) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.token.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected MethodDefinitionAdapter(MethodDescription.Token token) {
                        this.token = token;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public MethodDefinition.ParameterDefinition.Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, int i) {
                        return new ParameterAnnotationAdapter(new ParameterDescription.Token(typeDefinition.asGenericType(), str, Integer.valueOf(i)));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                    public MethodDefinition.ParameterDefinition.Simple.Annotatable<U> withParameter(TypeDefinition typeDefinition) {
                        return new SimpleParameterAnnotationAdapter(new ParameterDescription.Token(typeDefinition.asGenericType()));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public MethodDefinition.ExceptionDefinition<U> throwing(Collection<? extends TypeDefinition> collection) {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), this.token.getModifiers(), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), CompoundList.of((List) this.token.getExceptionTypes(), (List) new TypeList.Generic.Explicit(new ArrayList(collection))), this.token.getAnnotations(), this.token.getDefaultValue(), this.token.getReceiverType()));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public MethodDefinition.TypeVariableDefinition.Annotatable<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                        return new TypeVariableAnnotationAdapter(new TypeVariableToken(str, new TypeList.Generic.Explicit(new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return materialize(new MethodRegistry.Handler.ForImplementation(implementation));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        int modifiers;
                        Adapter adapter = Adapter.this;
                        String name = this.token.getName();
                        if ((this.token.getModifiers() & 256) == 0) {
                            modifiers = ModifierContributor.Resolver.of(MethodManifestation.ABSTRACT).resolve(this.token.getModifiers());
                        } else {
                            modifiers = this.token.getModifiers();
                        }
                        return new MethodDefinitionAdapter(new MethodDescription.Token(name, modifiers, this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), this.token.getExceptionTypes(), this.token.getAnnotations(), this.token.getDefaultValue(), this.token.getReceiverType())).materialize(MethodRegistry.Handler.ForAbstractMethod.INSTANCE);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), ModifierContributor.Resolver.of(MethodManifestation.ABSTRACT).resolve(this.token.getModifiers()), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), this.token.getExceptionTypes(), this.token.getAnnotations(), annotationValue, this.token.getReceiverType())).materialize(new MethodRegistry.Handler.ForAnnotationValue(annotationValue));
                    }

                    private MethodDefinition.ReceiverTypeDefinition<U> materialize(MethodRegistry.Handler handler) {
                        return new AnnotationAdapter(this, handler);
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodDefinitionAdapter$TypeVariableAnnotationAdapter.class */
                    protected class TypeVariableAnnotationAdapter extends MethodDefinition.TypeVariableDefinition.Annotatable.AbstractBase.Adapter<U> {
                        private final TypeVariableToken token;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.token.equals(((TypeVariableAnnotationAdapter) obj).token) && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return (((getClass().hashCode() * 31) + this.token.hashCode()) * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected TypeVariableAnnotationAdapter(TypeVariableToken typeVariableToken) {
                            this.token = typeVariableToken;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable.AbstractBase.Adapter
                        protected MethodDefinition.ParameterDefinition<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), CompoundList.of(MethodDefinitionAdapter.this.token.getTypeVariableTokens(), this.token), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public MethodDefinition.TypeVariableDefinition.Annotatable<U> annotateTypeVariable(Collection<? extends AnnotationDescription> collection) {
                            return new TypeVariableAnnotationAdapter(new TypeVariableToken(this.token.getSymbol(), this.token.getBounds(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodDefinitionAdapter$ParameterAnnotationAdapter.class */
                    protected class ParameterAnnotationAdapter extends MethodDefinition.ParameterDefinition.Annotatable.AbstractBase.Adapter<U> {
                        private final ParameterDescription.Token token;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.token.equals(((ParameterAnnotationAdapter) obj).token) && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return (((getClass().hashCode() * 31) + this.token.hashCode()) * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected ParameterAnnotationAdapter(ParameterDescription.Token token) {
                            this.token = token;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public MethodDefinition.ParameterDefinition.Annotatable<U> annotateParameter(Collection<? extends AnnotationDescription> collection) {
                            return new ParameterAnnotationAdapter(new ParameterDescription.Token(this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection)), this.token.getName(), this.token.getModifiers()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable.AbstractBase.Adapter
                        protected MethodDefinition.ParameterDefinition<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), CompoundList.of(MethodDefinitionAdapter.this.token.getParameterTokens(), this.token), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodDefinitionAdapter$SimpleParameterAnnotationAdapter.class */
                    protected class SimpleParameterAnnotationAdapter extends MethodDefinition.ParameterDefinition.Simple.Annotatable.AbstractBase.Adapter<U> {
                        private final ParameterDescription.Token token;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.token.equals(((SimpleParameterAnnotationAdapter) obj).token) && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return (((getClass().hashCode() * 31) + this.token.hashCode()) * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected SimpleParameterAnnotationAdapter(ParameterDescription.Token token) {
                            this.token = token;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                        public MethodDefinition.ParameterDefinition.Simple.Annotatable<U> annotateParameter(Collection<? extends AnnotationDescription> collection) {
                            return new SimpleParameterAnnotationAdapter(new ParameterDescription.Token(this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection)), this.token.getName(), this.token.getModifiers()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable.AbstractBase.Adapter
                        protected MethodDefinition.ParameterDefinition.Simple<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), CompoundList.of(MethodDefinitionAdapter.this.token.getParameterTokens(), this.token), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodDefinitionAdapter$AnnotationAdapter.class */
                    public class AnnotationAdapter extends MethodDefinition.AbstractBase.Adapter<U> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public boolean equals(@MaybeNull Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public int hashCode() {
                            return (super.hashCode() * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected AnnotationAdapter(MethodDefinitionAdapter methodDefinitionAdapter, MethodRegistry.Handler handler) {
                            this(handler, MethodAttributeAppender.ForInstrumentedMethod.INCLUDING_RECEIVER, Transformer.NoOp.make());
                        }

                        protected AnnotationAdapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            super(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition
                        public MethodDefinition<U> receiverType(TypeDescription.Generic generic) {
                            MethodDefinitionAdapter methodDefinitionAdapter = new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), generic));
                            methodDefinitionAdapter.getClass();
                            return new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateMethod(Collection<? extends AnnotationDescription> collection) {
                            MethodDefinitionAdapter methodDefinitionAdapter = new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), CompoundList.of((List) MethodDefinitionAdapter.this.token.getAnnotations(), (List) new ArrayList(collection)), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                            methodDefinitionAdapter.getClass();
                            return new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
                            ArrayList arrayList = new ArrayList(MethodDefinitionAdapter.this.token.getParameterTokens());
                            arrayList.set(i, new ParameterDescription.Token(MethodDefinitionAdapter.this.token.getParameterTokens().get(i).getType(), CompoundList.of((List) MethodDefinitionAdapter.this.token.getParameterTokens().get(i).getAnnotations(), (List) new ArrayList(collection)), MethodDefinitionAdapter.this.token.getParameterTokens().get(i).getName(), MethodDefinitionAdapter.this.token.getParameterTokens().get(i).getModifiers()));
                            MethodDefinitionAdapter methodDefinitionAdapter = new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), arrayList, MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                            methodDefinitionAdapter.getClass();
                            return new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        protected MethodDefinition<U> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            return new AnnotationAdapter(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                        protected Builder<U> materialize() {
                            return Adapter.this.materialize(Adapter.this.instrumentedType.withMethod(MethodDefinitionAdapter.this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry.prepend(new LatentMatcher.ForMethodToken(MethodDefinitionAdapter.this.token), this.handler, this.methodAttributeAppenderFactory, this.transformer), Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodMatchAdapter.class */
                protected class MethodMatchAdapter extends MethodDefinition.ImplementationDefinition.AbstractBase<U> {
                    private final LatentMatcher<? super MethodDescription> matcher;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((MethodMatchAdapter) obj).matcher) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected MethodMatchAdapter(LatentMatcher<? super MethodDescription> latentMatcher) {
                        this.matcher = latentMatcher;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return materialize(new MethodRegistry.Handler.ForImplementation(implementation));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return materialize(MethodRegistry.Handler.ForAbstractMethod.INSTANCE);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return materialize(new MethodRegistry.Handler.ForAnnotationValue(annotationValue));
                    }

                    private MethodDefinition.ReceiverTypeDefinition<U> materialize(MethodRegistry.Handler handler) {
                        return new AnnotationAdapter(this, handler);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$MethodMatchAdapter$AnnotationAdapter.class */
                    public class AnnotationAdapter extends MethodDefinition.AbstractBase.Adapter<U> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public boolean equals(@MaybeNull Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && MethodMatchAdapter.this.equals(MethodMatchAdapter.this);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public int hashCode() {
                            return (super.hashCode() * 31) + MethodMatchAdapter.this.hashCode();
                        }

                        protected AnnotationAdapter(MethodMatchAdapter methodMatchAdapter, MethodRegistry.Handler handler) {
                            this(handler, MethodAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make());
                        }

                        protected AnnotationAdapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            super(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition
                        public MethodDefinition<U> receiverType(TypeDescription.Generic generic) {
                            return new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.ForReceiverType(generic)), this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateMethod(Collection<? extends AnnotationDescription> collection) {
                            return new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.Explicit(new ArrayList(collection))), this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
                            return new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.Explicit(i, new ArrayList(collection))), this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        protected MethodDefinition<U> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            return new AnnotationAdapter(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                        protected Builder<U> materialize() {
                            return Adapter.this.materialize(Adapter.this.instrumentedType, Adapter.this.fieldRegistry, Adapter.this.methodRegistry.prepend(MethodMatchAdapter.this.matcher, this.handler, this.methodAttributeAppenderFactory, this.transformer), Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$OptionalMethodMatchAdapter.class */
                protected class OptionalMethodMatchAdapter extends Delegator<U> implements MethodDefinition.ImplementationDefinition.Optional<U> {
                    private final TypeList.Generic interfaces;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.interfaces.equals(((OptionalMethodMatchAdapter) obj).interfaces) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.interfaces.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected OptionalMethodMatchAdapter(TypeList.Generic generic) {
                        this.interfaces = generic;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withInterfaces(this.interfaces), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return interfaceType().intercept(implementation);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return interfaceType().withoutCode();
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return interfaceType().defaultValue(annotationValue);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public <V> MethodDefinition.ReceiverTypeDefinition<U> defaultValue(V v, Class<? extends V> cls) {
                        return interfaceType().defaultValue(v, cls);
                    }

                    private MethodDefinition.ImplementationDefinition<U> interfaceType() {
                        ElementMatcher.Junction none = ElementMatchers.none();
                        Iterator it = this.interfaces.asErasures().iterator();
                        while (it.hasNext()) {
                            none = none.or(ElementMatchers.isSuperTypeOf((TypeDescription) it.next()));
                        }
                        return materialize().invokable(ElementMatchers.isDeclaredBy(ElementMatchers.isInterface().and(none)));
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$RecordComponentDefinitionAdapter.class */
                protected class RecordComponentDefinitionAdapter extends RecordComponentDefinition.Optional.AbstractBase<U> {
                    private final RecordComponentAttributeAppender.Factory recordComponentAttributeAppenderFactory;
                    private final RecordComponentDescription.Token token;
                    private final Transformer<RecordComponentDescription> transformer;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.recordComponentAttributeAppenderFactory.equals(((RecordComponentDefinitionAdapter) obj).recordComponentAttributeAppenderFactory) && this.token.equals(((RecordComponentDefinitionAdapter) obj).token) && this.transformer.equals(((RecordComponentDefinitionAdapter) obj).transformer) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.recordComponentAttributeAppenderFactory.hashCode()) * 31) + this.token.hashCode()) * 31) + this.transformer.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected RecordComponentDefinitionAdapter(Adapter adapter, RecordComponentDescription.Token token) {
                        this(RecordComponentAttributeAppender.ForInstrumentedRecordComponent.INSTANCE, Transformer.NoOp.make(), token);
                    }

                    protected RecordComponentDefinitionAdapter(RecordComponentAttributeAppender.Factory factory, Transformer<RecordComponentDescription> transformer, RecordComponentDescription.Token token) {
                        this.recordComponentAttributeAppenderFactory = factory;
                        this.transformer = transformer;
                        this.token = token;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public RecordComponentDefinition.Optional<U> annotateRecordComponent(Collection<? extends AnnotationDescription> collection) {
                        return new RecordComponentDefinitionAdapter(this.recordComponentAttributeAppenderFactory, this.transformer, new RecordComponentDescription.Token(this.token.getName(), this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public RecordComponentDefinition.Optional<U> attribute(RecordComponentAttributeAppender.Factory factory) {
                        return new RecordComponentDefinitionAdapter(new RecordComponentAttributeAppender.Factory.Compound(this.recordComponentAttributeAppenderFactory, factory), this.transformer, this.token);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public RecordComponentDefinition.Optional<U> transform(Transformer<RecordComponentDescription> transformer) {
                        return new RecordComponentDefinitionAdapter(this.recordComponentAttributeAppenderFactory, new Transformer.Compound(this.transformer, transformer), this.token);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType.withRecordComponent(this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry.prepend(new LatentMatcher.ForRecordComponentToken(this.token), this.recordComponentAttributeAppenderFactory, this.transformer), Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Builder$AbstractBase$Adapter$RecordComponentMatchAdapter.class */
                protected class RecordComponentMatchAdapter extends RecordComponentDefinition.Optional.AbstractBase<U> {
                    private final LatentMatcher<? super RecordComponentDescription> matcher;
                    private final RecordComponentAttributeAppender.Factory recordComponentAttributeAppenderFactory;
                    private final Transformer<RecordComponentDescription> transformer;

                    protected RecordComponentMatchAdapter(Adapter adapter, LatentMatcher<? super RecordComponentDescription> latentMatcher) {
                        this(latentMatcher, RecordComponentAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make());
                    }

                    protected RecordComponentMatchAdapter(LatentMatcher<? super RecordComponentDescription> latentMatcher, RecordComponentAttributeAppender.Factory factory, Transformer<RecordComponentDescription> transformer) {
                        this.matcher = latentMatcher;
                        this.recordComponentAttributeAppenderFactory = factory;
                        this.transformer = transformer;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public RecordComponentDefinition.Optional<U> annotateRecordComponent(Collection<? extends AnnotationDescription> collection) {
                        return attribute(new RecordComponentAttributeAppender.Explicit(new ArrayList(collection)));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public RecordComponentDefinition.Optional<U> attribute(RecordComponentAttributeAppender.Factory factory) {
                        return new RecordComponentMatchAdapter(this.matcher, new RecordComponentAttributeAppender.Factory.Compound(this.recordComponentAttributeAppenderFactory, factory), this.transformer);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition
                    public RecordComponentDefinition.Optional<U> transform(Transformer<RecordComponentDescription> transformer) {
                        return new RecordComponentMatchAdapter(this.matcher, this.recordComponentAttributeAppenderFactory, new Transformer.Compound(this.transformer, transformer));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        return Adapter.this.materialize(Adapter.this.instrumentedType, Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.recordComponentRegistry.prepend(this.matcher, this.recordComponentAttributeAppenderFactory, this.transformer), Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.visibilityBridgeStrategy, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Default.class */
    public static class Default implements DynamicType {
        private static final String CLASS_FILE_EXTENSION = ".class";
        private static final String MANIFEST_VERSION = "1.0";
        private static final int BUFFER_SIZE = 1024;
        private static final int FROM_BEGINNING = 0;
        private static final int END_OF_FILE = -1;
        private static final String TEMP_SUFFIX = "tmp";
        protected final TypeDescription typeDescription;
        protected final byte[] binaryRepresentation;
        protected final LoadedTypeInitializer loadedTypeInitializer;
        protected final List<? extends DynamicType> auxiliaryTypes;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Default) obj).typeDescription) && Arrays.equals(this.binaryRepresentation, ((Default) obj).binaryRepresentation) && this.loadedTypeInitializer.equals(((Default) obj).loadedTypeInitializer) && this.auxiliaryTypes.equals(((Default) obj).auxiliaryTypes);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.typeDescription.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.auxiliaryTypes.hashCode();
        }

        @SuppressFBWarnings(value = {"EI_EXPOSE_REP2"}, justification = "The array is not modified by class contract.")
        public Default(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list) {
            this.typeDescription = typeDescription;
            this.binaryRepresentation = bArr;
            this.loadedTypeInitializer = loadedTypeInitializer;
            this.auxiliaryTypes = list;
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public ClassFileLocator.Resolution locate(String str) {
            if (this.typeDescription.getName().equals(str)) {
                return new ClassFileLocator.Resolution.Explicit(this.binaryRepresentation);
            }
            Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
            while (it.hasNext()) {
                ClassFileLocator.Resolution locate = it.next().locate(str);
                if (locate.isResolved()) {
                    return locate;
                }
            }
            return new ClassFileLocator.Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public TypeDescription getTypeDescription() {
            return this.typeDescription;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, byte[]> getAllTypes() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(this.typeDescription, this.binaryRepresentation);
            Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
            while (it.hasNext()) {
                linkedHashMap.putAll(it.next().getAllTypes());
            }
            return linkedHashMap;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, LoadedTypeInitializer> getLoadedTypeInitializers() {
            HashMap hashMap = new HashMap();
            Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
            while (it.hasNext()) {
                hashMap.putAll(it.next().getLoadedTypeInitializers());
            }
            hashMap.put(this.typeDescription, this.loadedTypeInitializer);
            return hashMap;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public boolean hasAliveLoadedTypeInitializers() {
            Iterator<LoadedTypeInitializer> it = getLoadedTypeInitializers().values().iterator();
            while (it.hasNext()) {
                if (it.next().isAlive()) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        @SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "The array is not modified by class contract.")
        public byte[] getBytes() {
            return this.binaryRepresentation;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, byte[]> getAuxiliaryTypes() {
            HashMap hashMap = new HashMap();
            for (DynamicType dynamicType : this.auxiliaryTypes) {
                hashMap.put(dynamicType.getTypeDescription(), dynamicType.getBytes());
                hashMap.putAll(dynamicType.getAuxiliaryTypes());
            }
            return hashMap;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, File> saveIn(File file) {
            HashMap hashMap = new HashMap();
            File file2 = new File(file, this.typeDescription.getName().replace('.', File.separatorChar) + ".class");
            if (file2.getParentFile() == null || file2.getParentFile().isDirectory() || file2.getParentFile().mkdirs()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    fileOutputStream.write(this.binaryRepresentation);
                    hashMap.put(this.typeDescription, file2);
                    Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
                    while (it.hasNext()) {
                        hashMap.putAll(it.next().saveIn(file));
                    }
                    return hashMap;
                } finally {
                    fileOutputStream.close();
                }
            }
            throw new IllegalArgumentException("Could not create directory: " + file2.getParentFile());
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File inject(File file, File file2) {
            if (file.equals(file2)) {
                return inject(file);
            }
            return doInject(file, file2);
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File inject(File file) {
            FileSystem.getInstance().move(doInject(file, File.createTempFile(file.getName(), "tmp")), file);
            return file;
        }

        private File doInject(File file, File file2) {
            JarInputStream jarInputStream = new JarInputStream(new FileInputStream(file));
            try {
                if (!file2.isFile() && !file2.createNewFile()) {
                    throw new IllegalArgumentException("Could not create file: " + file2);
                }
                Manifest manifest = jarInputStream.getManifest();
                JarOutputStream jarOutputStream = manifest == null ? new JarOutputStream(new FileOutputStream(file2)) : new JarOutputStream(new FileOutputStream(file2), manifest);
                try {
                    Map<TypeDescription, byte[]> auxiliaryTypes = getAuxiliaryTypes();
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<TypeDescription, byte[]> entry : auxiliaryTypes.entrySet()) {
                        hashMap.put(entry.getKey().getInternalName() + ".class", entry.getValue());
                    }
                    hashMap.put(this.typeDescription.getInternalName() + ".class", this.binaryRepresentation);
                    while (true) {
                        JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
                        if (nextJarEntry == null) {
                            break;
                        }
                        byte[] bArr = (byte[]) hashMap.remove(nextJarEntry.getName());
                        if (bArr == null) {
                            jarOutputStream.putNextEntry(nextJarEntry);
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read = jarInputStream.read(bArr2);
                                if (read != -1) {
                                    jarOutputStream.write(bArr2, 0, read);
                                }
                            }
                        } else {
                            jarOutputStream.putNextEntry(new JarEntry(nextJarEntry.getName()));
                            jarOutputStream.write(bArr);
                        }
                        jarInputStream.closeEntry();
                        jarOutputStream.closeEntry();
                    }
                    for (Map.Entry entry2 : hashMap.entrySet()) {
                        jarOutputStream.putNextEntry(new JarEntry((String) entry2.getKey()));
                        jarOutputStream.write((byte[]) entry2.getValue());
                        jarOutputStream.closeEntry();
                    }
                    return file2;
                } finally {
                    jarOutputStream.close();
                }
            } finally {
                jarInputStream.close();
            }
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File toJar(File file) {
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, MANIFEST_VERSION);
            return toJar(file, manifest);
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File toJar(File file, Manifest manifest) {
            if (!file.isFile() && !file.createNewFile()) {
                throw new IllegalArgumentException("Could not create file: " + file);
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file), manifest);
            try {
                for (Map.Entry<TypeDescription, byte[]> entry : getAuxiliaryTypes().entrySet()) {
                    jarOutputStream.putNextEntry(new JarEntry(entry.getKey().getInternalName() + ".class"));
                    jarOutputStream.write(entry.getValue());
                    jarOutputStream.closeEntry();
                }
                jarOutputStream.putNextEntry(new JarEntry(this.typeDescription.getInternalName() + ".class"));
                jarOutputStream.write(this.binaryRepresentation);
                jarOutputStream.closeEntry();
                return file;
            } finally {
                jarOutputStream.close();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Default$Unloaded.class */
        public static class Unloaded<T> extends Default implements Unloaded<T> {
            private final TypeResolutionStrategy.Resolved typeResolutionStrategy;

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeResolutionStrategy.equals(((Unloaded) obj).typeResolutionStrategy);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public int hashCode() {
                return (super.hashCode() * 31) + this.typeResolutionStrategy.hashCode();
            }

            public Unloaded(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list, TypeResolutionStrategy.Resolved resolved) {
                super(typeDescription, bArr, loadedTypeInitializer, list);
                this.typeResolutionStrategy = resolved;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public Loaded<T> load(@MaybeNull ClassLoader classLoader) {
                if ((classLoader instanceof InjectionClassLoader) && !((InjectionClassLoader) classLoader).isSealed()) {
                    return load((InjectionClassLoader) classLoader, InjectionClassLoader.Strategy.INSTANCE);
                }
                return load(classLoader, ClassLoadingStrategy.Default.WRAPPER);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public <S extends ClassLoader> Loaded<T> load(@MaybeNull S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
                return new Loaded(this.typeDescription, this.binaryRepresentation, this.loadedTypeInitializer, this.auxiliaryTypes, this.typeResolutionStrategy.initialize(this, s, classLoadingStrategy));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public Unloaded<T> include(DynamicType... dynamicTypeArr) {
                return include(Arrays.asList(dynamicTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public Unloaded<T> include(List<? extends DynamicType> list) {
                return new Unloaded(this.typeDescription, this.binaryRepresentation, this.loadedTypeInitializer, CompoundList.of((List) this.auxiliaryTypes, (List) list), this.typeResolutionStrategy);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/DynamicType$Default$Loaded.class */
        public static class Loaded<T> extends Default implements Loaded<T> {
            private final Map<TypeDescription, Class<?>> loadedTypes;

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.loadedTypes.equals(((Loaded) obj).loadedTypes);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public int hashCode() {
                return (super.hashCode() * 31) + this.loadedTypes.hashCode();
            }

            protected Loaded(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list, Map<TypeDescription, Class<?>> map) {
                super(typeDescription, bArr, loadedTypeInitializer, list);
                this.loadedTypes = map;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Loaded
            public Class<? extends T> getLoaded() {
                return (Class) this.loadedTypes.get(this.typeDescription);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Loaded
            public Map<TypeDescription, Class<?>> getLoadedAuxiliaryTypes() {
                HashMap hashMap = new HashMap(this.loadedTypes);
                hashMap.remove(this.typeDescription);
                return hashMap;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Loaded
            public Map<TypeDescription, Class<?>> getAllLoaded() {
                return new HashMap(this.loadedTypes);
            }
        }
    }
}
