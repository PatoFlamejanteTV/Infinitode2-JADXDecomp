package net.bytebuddy.dynamic;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer.class */
public interface Transformer<T> {
    T transform(TypeDescription typeDescription, T t);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$NoOp.class */
    public enum NoOp implements Transformer<Object> {
        INSTANCE;

        public static <T> Transformer<T> make() {
            return INSTANCE;
        }

        @Override // net.bytebuddy.dynamic.Transformer
        public final Object transform(TypeDescription typeDescription, Object obj) {
            return obj;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForField.class */
    public static class ForField implements Transformer<FieldDescription> {
        private final Transformer<FieldDescription.Token> transformer;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.transformer.equals(((ForField) obj).transformer);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.transformer.hashCode();
        }

        public ForField(Transformer<FieldDescription.Token> transformer) {
            this.transformer = transformer;
        }

        public static Transformer<FieldDescription> withModifiers(ModifierContributor.ForField... forFieldArr) {
            return withModifiers((List<? extends ModifierContributor.ForField>) Arrays.asList(forFieldArr));
        }

        public static Transformer<FieldDescription> withModifiers(List<? extends ModifierContributor.ForField> list) {
            return new ForField(new FieldModifierTransformer(ModifierContributor.Resolver.of(list)));
        }

        @Override // net.bytebuddy.dynamic.Transformer
        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming declaring type for type member.")
        public FieldDescription transform(TypeDescription typeDescription, FieldDescription fieldDescription) {
            return new TransformedField(typeDescription, fieldDescription.getDeclaringType(), this.transformer.transform(typeDescription, fieldDescription.asToken(ElementMatchers.none())), fieldDescription.asDefined());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForField$FieldModifierTransformer.class */
        public static class FieldModifierTransformer implements Transformer<FieldDescription.Token> {
            private final ModifierContributor.Resolver<ModifierContributor.ForField> resolver;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.resolver.equals(((FieldModifierTransformer) obj).resolver);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.resolver.hashCode();
            }

            protected FieldModifierTransformer(ModifierContributor.Resolver<ModifierContributor.ForField> resolver) {
                this.resolver = resolver;
            }

            @Override // net.bytebuddy.dynamic.Transformer
            public FieldDescription.Token transform(TypeDescription typeDescription, FieldDescription.Token token) {
                return new FieldDescription.Token(token.getName(), this.resolver.resolve(token.getModifiers()), token.getType(), token.getAnnotations());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForField$TransformedField.class */
        public static class TransformedField extends FieldDescription.AbstractBase {
            private final TypeDescription instrumentedType;
            private final TypeDefinition declaringType;
            private final FieldDescription.Token token;
            private final FieldDescription.InDefinedShape fieldDescription;

            protected TransformedField(TypeDescription typeDescription, TypeDefinition typeDefinition, FieldDescription.Token token, FieldDescription.InDefinedShape inDefinedShape) {
                this.instrumentedType = typeDescription;
                this.declaringType = typeDefinition;
                this.token = token;
                this.fieldDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.description.field.FieldDescription
            public TypeDescription.Generic getType() {
                return (TypeDescription.Generic) this.token.getType().accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this.instrumentedType));
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return this.token.getAnnotations();
            }

            @Override // net.bytebuddy.description.DeclaredByType
            public TypeDefinition getDeclaringType() {
                return this.declaringType;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return this.token.getModifiers();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public FieldDescription.InDefinedShape asDefined() {
                return this.fieldDescription;
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getName() {
                return this.token.getName();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForMethod.class */
    public static class ForMethod implements Transformer<MethodDescription> {
        private final Transformer<MethodDescription.Token> transformer;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.transformer.equals(((ForMethod) obj).transformer);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.transformer.hashCode();
        }

        public ForMethod(Transformer<MethodDescription.Token> transformer) {
            this.transformer = transformer;
        }

        public static Transformer<MethodDescription> withModifiers(ModifierContributor.ForMethod... forMethodArr) {
            return withModifiers((List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
        }

        public static Transformer<MethodDescription> withModifiers(List<? extends ModifierContributor.ForMethod> list) {
            return new ForMethod(new MethodModifierTransformer(ModifierContributor.Resolver.of(list)));
        }

        @Override // net.bytebuddy.dynamic.Transformer
        public MethodDescription transform(TypeDescription typeDescription, MethodDescription methodDescription) {
            return new TransformedMethod(typeDescription, methodDescription.getDeclaringType(), this.transformer.transform(typeDescription, methodDescription.asToken(ElementMatchers.none())), methodDescription.asDefined());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForMethod$MethodModifierTransformer.class */
        public static class MethodModifierTransformer implements Transformer<MethodDescription.Token> {
            private final ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.resolver.equals(((MethodModifierTransformer) obj).resolver);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.resolver.hashCode();
            }

            protected MethodModifierTransformer(ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver) {
                this.resolver = resolver;
            }

            @Override // net.bytebuddy.dynamic.Transformer
            public MethodDescription.Token transform(TypeDescription typeDescription, MethodDescription.Token token) {
                return new MethodDescription.Token(token.getName(), this.resolver.resolve(token.getModifiers()), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), token.getReceiverType());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForMethod$TransformedMethod.class */
        public static class TransformedMethod extends MethodDescription.AbstractBase {
            private final TypeDescription instrumentedType;
            private final TypeDefinition declaringType;
            private final MethodDescription.Token token;
            private final MethodDescription.InDefinedShape methodDescription;

            protected TransformedMethod(TypeDescription typeDescription, TypeDefinition typeDefinition, MethodDescription.Token token, MethodDescription.InDefinedShape inDefinedShape) {
                this.instrumentedType = typeDescription;
                this.declaringType = typeDefinition;
                this.token = token;
                this.methodDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.description.TypeVariableSource
            public TypeList.Generic getTypeVariables() {
                return new TypeList.Generic.ForDetachedTypes.OfTypeVariables(this, this.token.getTypeVariableTokens(), new AttachmentVisitor());
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeDescription.Generic getReturnType() {
                return (TypeDescription.Generic) this.token.getReturnType().accept(new AttachmentVisitor());
            }

            @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
            public ParameterList<?> getParameters() {
                return new TransformedParameterList();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeList.Generic getExceptionTypes() {
                return new TypeList.Generic.ForDetachedTypes(this.token.getExceptionTypes(), new AttachmentVisitor());
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return this.token.getAnnotations();
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getInternalName() {
                return this.token.getName();
            }

            @Override // net.bytebuddy.description.DeclaredByType
            public TypeDefinition getDeclaringType() {
                return this.declaringType;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return this.token.getModifiers();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            @MaybeNull
            public AnnotationValue<?, ?> getDefaultValue() {
                return this.token.getDefaultValue();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public MethodDescription.InDefinedShape asDefined() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeDescription.Generic getReceiverType() {
                TypeDescription.Generic receiverType = this.token.getReceiverType();
                return receiverType == null ? TypeDescription.Generic.UNDEFINED : (TypeDescription.Generic) receiverType.accept(new AttachmentVisitor());
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForMethod$TransformedMethod$TransformedParameterList.class */
            protected class TransformedParameterList extends ParameterList.AbstractBase<ParameterDescription> {
                protected TransformedParameterList() {
                }

                @Override // java.util.AbstractList, java.util.List
                public ParameterDescription get(int i) {
                    TransformedMethod transformedMethod = TransformedMethod.this;
                    return new TransformedParameter(i, transformedMethod.token.getParameterTokens().get(i));
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return TransformedMethod.this.token.getParameterTokens().size();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForMethod$TransformedMethod$TransformedParameter.class */
            public class TransformedParameter extends ParameterDescription.AbstractBase {
                private final int index;
                private final ParameterDescription.Token parameterToken;

                protected TransformedParameter(int i, ParameterDescription.Token token) {
                    this.index = i;
                    this.parameterToken = token;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription
                public TypeDescription.Generic getType() {
                    return (TypeDescription.Generic) this.parameterToken.getType().accept(new AttachmentVisitor());
                }

                @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
                public MethodDescription getDeclaringMethod() {
                    return TransformedMethod.this;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.description.NamedElement.WithOptionalName
                public boolean isNamed() {
                    return this.parameterToken.getName() != null;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription
                public boolean hasModifiers() {
                    return this.parameterToken.getModifiers() != null;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    String name = this.parameterToken.getName();
                    if (name != null) {
                        return name;
                    }
                    return super.getName();
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    Integer modifiers = this.parameterToken.getModifiers();
                    if (modifiers == null) {
                        return super.getModifiers();
                    }
                    return modifiers.intValue();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.parameterToken.getAnnotations();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
                public ParameterDescription.InDefinedShape asDefined() {
                    return (ParameterDescription.InDefinedShape) TransformedMethod.this.methodDescription.getParameters().get(this.index);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$ForMethod$TransformedMethod$AttachmentVisitor.class */
            public class AttachmentVisitor extends TypeDescription.Generic.Visitor.Substitutor.WithoutTypeSubstitution {
                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && TransformedMethod.this.equals(TransformedMethod.this);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + TransformedMethod.this.hashCode();
                }

                protected AttachmentVisitor() {
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription.Generic onTypeVariable(TypeDescription.Generic generic) {
                    TypeDescription.Generic only;
                    TypeList.Generic filter = TransformedMethod.this.getTypeVariables().filter(ElementMatchers.named(generic.getSymbol()));
                    if (filter.isEmpty()) {
                        only = TransformedMethod.this.instrumentedType.findExpectedVariable(generic.getSymbol());
                    } else {
                        only = filter.getOnly();
                    }
                    return new TypeDescription.Generic.OfTypeVariable.WithAnnotationOverlay(only, generic);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/Transformer$Compound.class */
    public static class Compound<S> implements Transformer<S> {
        private final List<Transformer<S>> transformers;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.transformers.equals(((Compound) obj).transformers);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.transformers.hashCode();
        }

        public Compound(Transformer<S>... transformerArr) {
            this(Arrays.asList(transformerArr));
        }

        public Compound(List<? extends Transformer<S>> list) {
            this.transformers = new ArrayList();
            for (Transformer<S> transformer : list) {
                if (transformer instanceof Compound) {
                    this.transformers.addAll(((Compound) transformer).transformers);
                } else if (!(transformer instanceof NoOp)) {
                    this.transformers.add(transformer);
                }
            }
        }

        @Override // net.bytebuddy.dynamic.Transformer
        public S transform(TypeDescription typeDescription, S s) {
            Iterator<Transformer<S>> it = this.transformers.iterator();
            while (it.hasNext()) {
                s = it.next().transform(typeDescription, s);
            }
            return s;
        }
    }
}
