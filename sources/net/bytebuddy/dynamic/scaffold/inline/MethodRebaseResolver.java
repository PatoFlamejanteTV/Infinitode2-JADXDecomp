package net.bytebuddy.dynamic.scaffold.inline;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.auxiliary.TrivialType;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver.class */
public interface MethodRebaseResolver {
    Resolution resolve(MethodDescription.InDefinedShape inDefinedShape);

    List<DynamicType> getAuxiliaryTypes();

    Map<MethodDescription.SignatureToken, Resolution> asTokenMap();

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Disabled.class */
    public enum Disabled implements MethodRebaseResolver {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public final Resolution resolve(MethodDescription.InDefinedShape inDefinedShape) {
            return new Resolution.Preserved(inDefinedShape);
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public final List<DynamicType> getAuxiliaryTypes() {
            return Collections.emptyList();
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public final Map<MethodDescription.SignatureToken, Resolution> asTokenMap() {
            return Collections.emptyMap();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Resolution.class */
    public interface Resolution {
        boolean isRebased();

        MethodDescription.InDefinedShape getResolvedMethod();

        TypeList getAppendedParameters();

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Resolution$Preserved.class */
        public static class Preserved implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((Preserved) obj).methodDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
            }

            public Preserved(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public boolean isRebased() {
                return false;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public TypeList getAppendedParameters() {
                throw new IllegalStateException("Cannot process additional parameters for non-rebased method: " + this.methodDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Resolution$ForRebasedMethod.class */
        public static class ForRebasedMethod implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForRebasedMethod) obj).methodDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
            }

            protected ForRebasedMethod(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            public static Resolution of(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodNameTransformer methodNameTransformer) {
                return new ForRebasedMethod(new RebasedMethod(typeDescription, inDefinedShape, methodNameTransformer));
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public boolean isRebased() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public TypeList getAppendedParameters() {
                return new TypeList.Empty();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Resolution$ForRebasedMethod$RebasedMethod.class */
            public static class RebasedMethod extends MethodDescription.InDefinedShape.AbstractBase {
                private final TypeDescription instrumentedType;
                private final MethodDescription.InDefinedShape methodDescription;
                private final MethodNameTransformer methodNameTransformer;

                protected RebasedMethod(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodNameTransformer methodNameTransformer) {
                    this.instrumentedType = typeDescription;
                    this.methodDescription = inDefinedShape;
                    this.methodNameTransformer = methodNameTransformer;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.methodDescription.getReturnType().asRawType();
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, this.methodDescription.getParameters().asTypeList().asRawTypes());
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                @AlwaysNull
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.methodDescription.getDeclaringType();
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return 4096 | (this.methodDescription.isStatic() ? 8 : 0) | (this.methodDescription.isNative() ? 272 : 0) | ((!this.instrumentedType.isInterface() || this.methodDescription.isNative()) ? 2 : 1);
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return this.methodNameTransformer.transform(this.methodDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Resolution$ForRebasedConstructor.class */
        public static class ForRebasedConstructor implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;
            private final TypeDescription placeholderType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForRebasedConstructor) obj).methodDescription) && this.placeholderType.equals(((ForRebasedConstructor) obj).placeholderType);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.placeholderType.hashCode();
            }

            protected ForRebasedConstructor(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                this.methodDescription = inDefinedShape;
                this.placeholderType = typeDescription;
            }

            public static Resolution of(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                return new ForRebasedConstructor(new RebasedConstructor(inDefinedShape, typeDescription), typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public boolean isRebased() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public TypeList getAppendedParameters() {
                return new TypeList.Explicit(this.placeholderType);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Resolution$ForRebasedConstructor$RebasedConstructor.class */
            public static class RebasedConstructor extends MethodDescription.InDefinedShape.AbstractBase {
                private final MethodDescription.InDefinedShape methodDescription;
                private final TypeDescription placeholderType;

                protected RebasedConstructor(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                    this.methodDescription = inDefinedShape;
                    this.placeholderType = typeDescription;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE);
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, (List<? extends TypeDefinition>) CompoundList.of(this.methodDescription.getParameters().asTypeList().asErasures(), this.placeholderType));
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                @AlwaysNull
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.methodDescription.getDeclaringType();
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return 4098;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return MethodDescription.CONSTRUCTOR_INTERNAL_NAME;
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/inline/MethodRebaseResolver$Default.class */
    public static class Default implements MethodRebaseResolver {
        private final Map<MethodDescription.InDefinedShape, Resolution> resolutions;
        private final List<DynamicType> dynamicTypes;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.resolutions.equals(((Default) obj).resolutions) && this.dynamicTypes.equals(((Default) obj).dynamicTypes);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.resolutions.hashCode()) * 31) + this.dynamicTypes.hashCode();
        }

        protected Default(Map<MethodDescription.InDefinedShape, Resolution> map, List<DynamicType> list) {
            this.resolutions = map;
            this.dynamicTypes = list;
        }

        public static MethodRebaseResolver make(TypeDescription typeDescription, Set<? extends MethodDescription.SignatureToken> set, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, MethodNameTransformer methodNameTransformer) {
            Resolution of;
            DynamicType dynamicType = null;
            HashMap hashMap = new HashMap();
            for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods()) {
                if (set.contains(inDefinedShape.asSignatureToken())) {
                    if (inDefinedShape.isConstructor()) {
                        if (dynamicType == null) {
                            dynamicType = TrivialType.SIGNATURE_RELEVANT.make(namingStrategy.name(typeDescription, TrivialType.SIGNATURE_RELEVANT), classFileVersion, MethodAccessorFactory.Illegal.INSTANCE);
                        }
                        of = Resolution.ForRebasedConstructor.of(inDefinedShape, dynamicType.getTypeDescription());
                    } else {
                        of = Resolution.ForRebasedMethod.of(typeDescription, inDefinedShape, methodNameTransformer);
                    }
                    hashMap.put(inDefinedShape, of);
                }
            }
            if (dynamicType == null) {
                return new Default(hashMap, Collections.emptyList());
            }
            return new Default(hashMap, Collections.singletonList(dynamicType));
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public Resolution resolve(MethodDescription.InDefinedShape inDefinedShape) {
            Resolution resolution = this.resolutions.get(inDefinedShape);
            return resolution == null ? new Resolution.Preserved(inDefinedShape) : resolution;
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public List<DynamicType> getAuxiliaryTypes() {
            return this.dynamicTypes;
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public Map<MethodDescription.SignatureToken, Resolution> asTokenMap() {
            HashMap hashMap = new HashMap();
            for (Map.Entry<MethodDescription.InDefinedShape, Resolution> entry : this.resolutions.entrySet()) {
                hashMap.put(entry.getKey().asSignatureToken(), entry.getValue());
            }
            return hashMap;
        }
    }
}
