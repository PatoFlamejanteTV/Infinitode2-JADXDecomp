package net.bytebuddy.description.method;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.DeclaredByType;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription.class */
public interface MethodDescription extends ByteCodeElement, ByteCodeElement.TypeDependant<InDefinedShape, Token>, DeclaredByType.WithMandatoryDeclaration, ModifierReviewable.ForMethodDescription, NamedElement.WithGenericName, TypeVariableSource {
    public static final String CONSTRUCTOR_INTERNAL_NAME = "<init>";
    public static final String TYPE_INITIALIZER_INTERNAL_NAME = "<clinit>";
    public static final int TYPE_INITIALIZER_MODIFIER = 8;

    @AlwaysNull
    public static final InDefinedShape UNDEFINED = null;

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$InGenericShape.class */
    public interface InGenericShape extends MethodDescription {
        @Override // net.bytebuddy.description.DeclaredByType
        TypeDescription.Generic getDeclaringType();

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        ParameterList<ParameterDescription.InGenericShape> getParameters();
    }

    TypeDescription.Generic getReturnType();

    ParameterList<?> getParameters();

    TypeList.Generic getExceptionTypes();

    int getActualModifiers();

    int getActualModifiers(boolean z);

    int getActualModifiers(boolean z, Visibility visibility);

    boolean isConstructor();

    boolean isMethod();

    boolean isTypeInitializer();

    boolean represents(Method method);

    boolean represents(Constructor<?> constructor);

    boolean isVirtual();

    int getStackSize();

    boolean isDefaultMethod();

    boolean isSpecializableFor(TypeDescription typeDescription);

    @MaybeNull
    AnnotationValue<?, ?> getDefaultValue();

    @MaybeNull
    <T> T getDefaultValue(Class<T> cls);

    boolean isInvokableOn(TypeDescription typeDescription);

    boolean isInvokeBootstrap();

    boolean isInvokeBootstrap(List<? extends TypeDefinition> list);

    boolean isConstantBootstrap();

    boolean isConstantBootstrap(List<? extends TypeDefinition> list);

    boolean isDefaultValue();

    boolean isDefaultValue(AnnotationValue<?, ?> annotationValue);

    @MaybeNull
    TypeDescription.Generic getReceiverType();

    SignatureToken asSignatureToken();

    TypeToken asTypeToken();

    boolean isBridgeCompatible(TypeToken typeToken);

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$InDefinedShape.class */
    public interface InDefinedShape extends MethodDescription {
        @Override // net.bytebuddy.description.DeclaredByType
        TypeDescription getDeclaringType();

        ParameterList<ParameterDescription.InDefinedShape> getParameters();

        /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$InDefinedShape$AbstractBase.class */
        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {

            /* JADX INFO: Access modifiers changed from: protected */
            @JavaDispatcher.Proxied("java.lang.reflect.Executable")
            /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$InDefinedShape$AbstractBase$Executable.class */
            public interface Executable {
                @JavaDispatcher.Defaults
                @MaybeNull
                @JavaDispatcher.Proxied("getAnnotatedReceiverType")
                AnnotatedElement getAnnotatedReceiverType(Object obj);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public InDefinedShape asDefined() {
                return this;
            }

            @MaybeNull
            public TypeDescription.Generic getReceiverType() {
                if (isStatic()) {
                    return TypeDescription.Generic.UNDEFINED;
                }
                if (isConstructor()) {
                    TypeDescription declaringType = getDeclaringType();
                    TypeDescription enclosingType = getDeclaringType().getEnclosingType();
                    if (enclosingType == null) {
                        return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(declaringType);
                    }
                    if (declaringType.isStatic()) {
                        return enclosingType.asGenericType();
                    }
                    return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(enclosingType);
                }
                return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(getDeclaringType());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$InDefinedShape$AbstractBase$ForLoadedExecutable.class */
            public static abstract class ForLoadedExecutable<T extends AnnotatedElement> extends AbstractBase {
                protected static final Executable EXECUTABLE;
                protected final T executable;
                private static final boolean ACCESS_CONTROLLER;

                @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.ByteCodeElement.TypeDependant
                public /* bridge */ /* synthetic */ InDefinedShape asDefined() {
                    return super.asDefined();
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
                    EXECUTABLE = (Executable) doPrivileged(JavaDispatcher.of(Executable.class));
                }

                protected ForLoadedExecutable(T t) {
                    this.executable = t;
                }

                @AccessControllerPlugin.Enhance
                private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
                    return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
                }

                @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReceiverType() {
                    AnnotatedElement annotatedReceiverType = EXECUTABLE.getAnnotatedReceiverType(this.executable);
                    if (annotatedReceiverType == null) {
                        return super.getReceiverType();
                    }
                    return TypeDefinition.Sort.describeAnnotated(annotatedReceiverType);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$AbstractBase.class */
    public static abstract class AbstractBase extends TypeVariableSource.AbstractBase implements MethodDescription {
        private static final int SOURCE_MODIFIERS = 1343;
        private transient /* synthetic */ int hashCode;

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public /* bridge */ /* synthetic */ Token asToken(ElementMatcher elementMatcher) {
            return asToken((ElementMatcher<? super TypeDescription>) elementMatcher);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getStackSize() {
            return getParameters().asTypeList().getStackSize() + (isStatic() ? 0 : 1);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isMethod() {
            return (isConstructor() || isTypeInitializer()) ? false : true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(getInternalName());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(getInternalName());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean represents(Method method) {
            return equals(new ForLoadedMethod(method));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean represents(Constructor<?> constructor) {
            return equals(new ForLoadedConstructor(constructor));
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            if (isMethod()) {
                return getInternalName();
            }
            return getDeclaringType().asErasure().getName();
        }

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return isMethod() ? getName() : "";
        }

        @Override // net.bytebuddy.description.NamedElement.WithDescriptor
        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            Iterator it = getParameters().asTypeList().asErasures().iterator();
            while (it.hasNext()) {
                sb.append(((TypeDescription) it.next()).getDescriptor());
            }
            return sb.append(')').append(getReturnType().asErasure().getDescriptor()).toString();
        }

        @Override // net.bytebuddy.description.NamedElement.WithDescriptor
        @MaybeNull
        public String getGenericSignature() {
            SignatureVisitor visitInterfaceBound;
            try {
                SignatureWriter signatureWriter = new SignatureWriter();
                boolean z = false;
                for (TypeDescription.Generic generic : getTypeVariables()) {
                    signatureWriter.visitFormalTypeParameter(generic.getSymbol());
                    boolean z2 = true;
                    for (TypeDescription.Generic generic2 : generic.getUpperBounds()) {
                        if (z2) {
                            visitInterfaceBound = signatureWriter.visitClassBound();
                        } else {
                            visitInterfaceBound = signatureWriter.visitInterfaceBound();
                        }
                        generic2.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(visitInterfaceBound));
                        z2 = false;
                    }
                    z = true;
                }
                for (TypeDescription.Generic generic3 : getParameters().asTypeList()) {
                    generic3.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureWriter.visitParameterType()));
                    z = z || !generic3.getSort().isNonGeneric();
                }
                TypeDescription.Generic returnType = getReturnType();
                returnType.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureWriter.visitReturnType()));
                boolean z3 = z || !returnType.getSort().isNonGeneric();
                TypeList.Generic<TypeDescription.Generic> exceptionTypes = getExceptionTypes();
                if (!exceptionTypes.filter(ElementMatchers.not(ElementMatchers.ofSort(TypeDefinition.Sort.NON_GENERIC))).isEmpty()) {
                    for (TypeDescription.Generic generic4 : exceptionTypes) {
                        generic4.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureWriter.visitExceptionType()));
                        z3 = z3 || !generic4.getSort().isNonGeneric();
                    }
                }
                return z3 ? signatureWriter.toString() : NON_GENERIC_SIGNATURE;
            } catch (GenericSignatureFormatError unused) {
                return NON_GENERIC_SIGNATURE;
            }
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getActualModifiers() {
            return getModifiers() | (getDeclaredAnnotations().isAnnotationPresent(Deprecated.class) ? 131072 : 0);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getActualModifiers(boolean z) {
            if (z) {
                return getActualModifiers() & (-1281);
            }
            return (getActualModifiers() & (-257)) | 1024;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getActualModifiers(boolean z, Visibility visibility) {
            return ModifierContributor.Resolver.of(Collections.singleton(getVisibility().expandTo(visibility))).resolve(getActualModifiers(z));
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isVisibleTo(TypeDescription typeDescription) {
            if (!isVirtual() && !getDeclaringType().asErasure().isVisibleTo(typeDescription)) {
                return false;
            }
            if (isPublic() || typeDescription.equals(getDeclaringType().asErasure())) {
                return true;
            }
            if (isProtected() && getDeclaringType().asErasure().isAssignableFrom(typeDescription)) {
                return true;
            }
            if (isPrivate() || !typeDescription.isSamePackage(getDeclaringType().asErasure())) {
                return isPrivate() && typeDescription.isNestMateOf(getDeclaringType().asErasure());
            }
            return true;
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isAccessibleTo(TypeDescription typeDescription) {
            if (isVirtual() || getDeclaringType().asErasure().isVisibleTo(typeDescription)) {
                if (isPublic() || typeDescription.equals(getDeclaringType().asErasure())) {
                    return true;
                }
                if (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure())) {
                    return true;
                }
            }
            return isPrivate() && typeDescription.isNestMateOf(getDeclaringType().asErasure());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isVirtual() {
            return (isConstructor() || isPrivate() || isStatic() || isTypeInitializer()) ? false : true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isDefaultMethod() {
            return (isAbstract() || isBridge() || !getDeclaringType().isInterface()) ? false : true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isSpecializableFor(TypeDescription typeDescription) {
            if (isStatic()) {
                return false;
            }
            if (isPrivate() || isConstructor()) {
                return getDeclaringType().equals(typeDescription);
            }
            return !isAbstract() && getDeclaringType().asErasure().isAssignableFrom(typeDescription);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        @MaybeNull
        public <T> T getDefaultValue(Class<T> cls) {
            return cls.cast(getDefaultValue());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isInvokableOn(TypeDescription typeDescription) {
            if (isStatic() || isTypeInitializer() || !isVisibleTo(typeDescription)) {
                return false;
            }
            return isVirtual() ? getDeclaringType().asErasure().isAssignableFrom(typeDescription) : getDeclaringType().asErasure().equals(typeDescription);
        }

        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
        private boolean isBootstrap(TypeDescription typeDescription) {
            TypeList asErasures = getParameters().asTypeList().asErasures();
            switch (asErasures.size()) {
                case 0:
                    return false;
                case 1:
                    return asErasures.getOnly().represents(Object[].class);
                case 2:
                    return JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) asErasures.get(0)) && ((TypeDescription) asErasures.get(1)).represents(Object[].class);
                case 3:
                    if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) asErasures.get(0))) {
                        return false;
                    }
                    if (((TypeDescription) asErasures.get(1)).represents(Object.class) || ((TypeDescription) asErasures.get(1)).represents(String.class)) {
                        return (((TypeDescription) asErasures.get(2)).isArray() && ((TypeDescription) asErasures.get(2)).getComponentType().isAssignableFrom(typeDescription)) || ((TypeDescription) asErasures.get(2)).isAssignableFrom(typeDescription);
                    }
                    return false;
                default:
                    if (JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) asErasures.get(0))) {
                        return (((TypeDescription) asErasures.get(1)).represents(Object.class) || ((TypeDescription) asErasures.get(1)).represents(String.class)) && ((TypeDescription) asErasures.get(2)).isAssignableFrom(typeDescription);
                    }
                    return false;
            }
        }

        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
        private boolean isBootstrapping(List<? extends TypeDefinition> list) {
            TypeList asErasures = getParameters().asTypeList().asErasures();
            if (asErasures.size() < 4) {
                if (list.isEmpty()) {
                    return true;
                }
                if (((TypeDescription) asErasures.get(asErasures.size() - 1)).isArray()) {
                    Iterator<? extends TypeDefinition> it = list.iterator();
                    while (it.hasNext()) {
                        if (!it.next().asErasure().isAssignableTo(((TypeDescription) asErasures.get(asErasures.size() - 1)).getComponentType())) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            Iterator it2 = asErasures.subList(3, asErasures.size()).iterator();
            for (TypeDefinition typeDefinition : list) {
                if (!it2.hasNext()) {
                    return false;
                }
                TypeDescription typeDescription = (TypeDescription) it2.next();
                if (!it2.hasNext() && typeDescription.isArray()) {
                    return true;
                }
                if (!typeDefinition.asErasure().isAssignableTo(typeDescription)) {
                    return false;
                }
            }
            if (it2.hasNext()) {
                return ((TypeDescription) it2.next()).isArray() && !it2.hasNext();
            }
            return true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isInvokeBootstrap() {
            TypeDescription asErasure = getReturnType().asErasure();
            if (isMethod()) {
                if (isStatic()) {
                    if (!JavaType.CALL_SITE.getTypeStub().isAssignableFrom(asErasure) && !JavaType.CALL_SITE.getTypeStub().isAssignableTo(asErasure)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            if (isConstructor() && !JavaType.CALL_SITE.getTypeStub().isAssignableFrom(getDeclaringType().asErasure())) {
                return false;
            }
            return isBootstrap(JavaType.METHOD_TYPE.getTypeStub());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isInvokeBootstrap(List<? extends TypeDefinition> list) {
            return isInvokeBootstrap() && isBootstrapping(list);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isConstantBootstrap() {
            return isBootstrap(TypeDescription.ForLoadedType.of(Class.class));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isConstantBootstrap(List<? extends TypeDefinition> list) {
            return isConstantBootstrap() && isBootstrapping(list);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isDefaultValue() {
            return !isConstructor() && !isStatic() && getReturnType().asErasure().isAnnotationReturnType() && getParameters().isEmpty();
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
        public boolean isDefaultValue(AnnotationValue<?, ?> annotationValue) {
            if (!isDefaultValue()) {
                return false;
            }
            TypeDescription asErasure = getReturnType().asErasure();
            Object resolve = annotationValue.resolve();
            if (asErasure.represents(Boolean.TYPE) && (resolve instanceof Boolean)) {
                return true;
            }
            if (asErasure.represents(Byte.TYPE) && (resolve instanceof Byte)) {
                return true;
            }
            if (asErasure.represents(Character.TYPE) && (resolve instanceof Character)) {
                return true;
            }
            if (asErasure.represents(Short.TYPE) && (resolve instanceof Short)) {
                return true;
            }
            if (asErasure.represents(Integer.TYPE) && (resolve instanceof Integer)) {
                return true;
            }
            if (asErasure.represents(Long.TYPE) && (resolve instanceof Long)) {
                return true;
            }
            if (asErasure.represents(Float.TYPE) && (resolve instanceof Float)) {
                return true;
            }
            if (asErasure.represents(Double.TYPE) && (resolve instanceof Double)) {
                return true;
            }
            if (asErasure.represents(String.class) && (resolve instanceof String)) {
                return true;
            }
            if (asErasure.isAssignableTo(Enum.class) && (resolve instanceof EnumerationDescription) && isEnumerationType(asErasure, (EnumerationDescription) resolve)) {
                return true;
            }
            if (asErasure.isAssignableTo(Annotation.class) && (resolve instanceof AnnotationDescription) && isAnnotationType(asErasure, (AnnotationDescription) resolve)) {
                return true;
            }
            if (asErasure.represents(Class.class) && (resolve instanceof TypeDescription)) {
                return true;
            }
            if (asErasure.represents(boolean[].class) && (resolve instanceof boolean[])) {
                return true;
            }
            if (asErasure.represents(byte[].class) && (resolve instanceof byte[])) {
                return true;
            }
            if (asErasure.represents(char[].class) && (resolve instanceof char[])) {
                return true;
            }
            if (asErasure.represents(short[].class) && (resolve instanceof short[])) {
                return true;
            }
            if (asErasure.represents(int[].class) && (resolve instanceof int[])) {
                return true;
            }
            if (asErasure.represents(long[].class) && (resolve instanceof long[])) {
                return true;
            }
            if (asErasure.represents(float[].class) && (resolve instanceof float[])) {
                return true;
            }
            if (asErasure.represents(double[].class) && (resolve instanceof double[])) {
                return true;
            }
            if (asErasure.represents(String[].class) && (resolve instanceof String[])) {
                return true;
            }
            if (asErasure.isAssignableTo(Enum[].class) && (resolve instanceof EnumerationDescription[]) && isEnumerationType(asErasure.getComponentType(), (EnumerationDescription[]) resolve)) {
                return true;
            }
            if (asErasure.isAssignableTo(Annotation[].class) && (resolve instanceof AnnotationDescription[]) && isAnnotationType(asErasure.getComponentType(), (AnnotationDescription[]) resolve)) {
                return true;
            }
            return asErasure.represents(Class[].class) && (resolve instanceof TypeDescription[]);
        }

        private static boolean isEnumerationType(TypeDescription typeDescription, EnumerationDescription... enumerationDescriptionArr) {
            for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
                if (!enumerationDescription.getEnumerationType().equals(typeDescription)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isAnnotationType(TypeDescription typeDescription, AnnotationDescription... annotationDescriptionArr) {
            for (AnnotationDescription annotationDescription : annotationDescriptionArr) {
                if (!annotationDescription.getAnnotationType().equals(typeDescription)) {
                    return false;
                }
            }
            return true;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        @MaybeNull
        public TypeVariableSource getEnclosingSource() {
            return isStatic() ? TypeVariableSource.UNDEFINED : getDeclaringType().asErasure();
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public boolean isInferrable() {
            return true;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public <T> T accept(TypeVariableSource.Visitor<T> visitor) {
            return visitor.onMethod(asDefined());
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public boolean isGenerified() {
            return !getTypeVariables().isEmpty();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            TypeDescription.Generic receiverType = getReceiverType();
            return new Token(getInternalName(), getModifiers(), getTypeVariables().asTokenList(elementMatcher), (TypeDescription.Generic) getReturnType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getParameters().asTokenList(elementMatcher), getExceptionTypes().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getDeclaredAnnotations(), getDefaultValue(), receiverType == null ? TypeDescription.Generic.UNDEFINED : (TypeDescription.Generic) receiverType.accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public SignatureToken asSignatureToken() {
            return new SignatureToken(getInternalName(), getReturnType().asErasure(), getParameters().asTypeList().asErasures());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeToken asTypeToken() {
            return new TypeToken(getReturnType().asErasure(), getParameters().asTypeList().asErasures());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isBridgeCompatible(TypeToken typeToken) {
            TypeList asErasures = getParameters().asTypeList().asErasures();
            List<TypeDescription> parameterTypes = typeToken.getParameterTypes();
            if (asErasures.size() != parameterTypes.size()) {
                return false;
            }
            for (int i = 0; i < asErasures.size(); i++) {
                if (!asErasures.get(i).equals(parameterTypes.get(i)) && (asErasures.get(i).isPrimitive() || parameterTypes.get(i).isPrimitive())) {
                    return false;
                }
            }
            TypeDescription asErasure = getReturnType().asErasure();
            TypeDescription returnType = typeToken.getReturnType();
            if (asErasure.equals(returnType)) {
                return true;
            }
            return (asErasure.isPrimitive() || returnType.isPrimitive()) ? false : true;
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int hashCode = this.hashCode != 0 ? 0 : ((((((17 + getDeclaringType().hashCode()) * 31) + getInternalName().hashCode()) * 31) + getReturnType().asErasure().hashCode()) * 31) + getParameters().asTypeList().asErasures().hashCode();
            int i = hashCode;
            if (hashCode == 0) {
                i = this.hashCode;
            } else {
                this.hashCode = i;
            }
            return i;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodDescription)) {
                return false;
            }
            MethodDescription methodDescription = (MethodDescription) obj;
            return getInternalName().equals(methodDescription.getInternalName()) && getDeclaringType().equals(methodDescription.getDeclaringType()) && getReturnType().asErasure().equals(methodDescription.getReturnType().asErasure()) && getParameters().asTypeList().asErasures().equals(methodDescription.getParameters().asTypeList().asErasures());
        }

        @Override // net.bytebuddy.description.NamedElement.WithGenericName
        public String toGenericString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & SOURCE_MODIFIERS;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers)).append(' ');
            }
            if (isMethod()) {
                sb.append(getReturnType().getActualName()).append(' ');
                sb.append(getDeclaringType().asErasure().getActualName()).append('.');
            }
            sb.append(getName()).append('(');
            boolean z = true;
            for (TypeDescription.Generic generic : getParameters().asTypeList()) {
                if (!z) {
                    sb.append(',');
                } else {
                    z = false;
                }
                sb.append(generic.getActualName());
            }
            sb.append(')');
            TypeList.Generic<TypeDescription.Generic> exceptionTypes = getExceptionTypes();
            if (!exceptionTypes.isEmpty()) {
                sb.append(" throws ");
                boolean z2 = true;
                for (TypeDescription.Generic generic2 : exceptionTypes) {
                    if (!z2) {
                        sb.append(',');
                    } else {
                        z2 = false;
                    }
                    sb.append(generic2.getActualName());
                }
            }
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & SOURCE_MODIFIERS;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers)).append(' ');
            }
            if (isMethod()) {
                sb.append(getReturnType().asErasure().getActualName()).append(' ');
                sb.append(getDeclaringType().asErasure().getActualName()).append('.');
            }
            sb.append(getName()).append('(');
            boolean z = true;
            for (TypeDescription typeDescription : getParameters().asTypeList().asErasures()) {
                if (!z) {
                    sb.append(',');
                } else {
                    z = false;
                }
                sb.append(typeDescription.getActualName());
            }
            sb.append(')');
            TypeList<TypeDescription> asErasures = getExceptionTypes().asErasures();
            if (!asErasures.isEmpty()) {
                sb.append(" throws ");
                boolean z2 = true;
                for (TypeDescription typeDescription2 : asErasures) {
                    if (!z2) {
                        sb.append(',');
                    } else {
                        z2 = false;
                    }
                    sb.append(typeDescription2.getActualName());
                }
            }
            return sb.toString();
        }

        @Override // net.bytebuddy.description.TypeVariableSource.AbstractBase
        protected String toSafeString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & SOURCE_MODIFIERS;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers)).append(' ');
            }
            if (isMethod()) {
                sb.append(getReturnType().asErasure().getActualName()).append(' ');
                sb.append(getDeclaringType().asErasure().getActualName()).append('.');
            }
            return sb.append(getName()).append("(?)").toString();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$ForLoadedConstructor.class */
    public static class ForLoadedConstructor extends InDefinedShape.AbstractBase.ForLoadedExecutable<Constructor<?>> implements ParameterDescription.ForLoadedParameter.ParameterAnnotationSource {
        private transient /* synthetic */ ParameterList parameters;
        private transient /* synthetic */ AnnotationList declaredAnnotations;
        private transient /* synthetic */ Annotation[][] parameterAnnotations;

        @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase.ForLoadedExecutable, net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public /* bridge */ /* synthetic */ TypeDescription.Generic getReceiverType() {
            return super.getReceiverType();
        }

        public ForLoadedConstructor(Constructor<?> constructor) {
            super(constructor);
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return TypeDescription.ForLoadedType.of(((Constructor) this.executable).getDeclaringClass());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE);
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        @CachedReturnPlugin.Enhance("parameters")
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            ParameterList<ParameterDescription.InDefinedShape> of = this.parameters != null ? null : ParameterList.ForLoadedExecutable.of((Constructor<?>) this.executable, (ParameterDescription.ForLoadedParameter.ParameterAnnotationSource) this);
            ParameterList<ParameterDescription.InDefinedShape> parameterList = of;
            if (of == null) {
                parameterList = this.parameters;
            } else {
                this.parameters = parameterList;
            }
            return parameterList;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.OfConstructorExceptionTypes((Constructor) this.executable);
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Method method) {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Constructor<?> constructor) {
            return ((Constructor) this.executable).equals(constructor) || equals(new ForLoadedConstructor(constructor));
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return ((Constructor) this.executable).getName();
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return ((Constructor) this.executable).getModifiers();
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public boolean isSynthetic() {
            return ((Constructor) this.executable).isSynthetic();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return MethodDescription.CONSTRUCTOR_INTERNAL_NAME;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithDescriptor
        public String getDescriptor() {
            return Type.getConstructorDescriptor((Constructor) this.executable);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        @AlwaysNull
        public AnnotationValue<?, ?> getDefaultValue() {
            return AnnotationValue.UNDEFINED;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [net.bytebuddy.description.annotation.AnnotationList] */
        @Override // net.bytebuddy.description.annotation.AnnotationSource
        @CachedReturnPlugin.Enhance("declaredAnnotations")
        public AnnotationList getDeclaredAnnotations() {
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations = this.declaredAnnotations != null ? null : new AnnotationList.ForLoadedAnnotations(((Constructor) this.executable).getDeclaredAnnotations());
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations2 = forLoadedAnnotations;
            if (forLoadedAnnotations == null) {
                forLoadedAnnotations2 = this.declaredAnnotations;
            } else {
                this.declaredAnnotations = forLoadedAnnotations2;
            }
            return forLoadedAnnotations2;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of((GenericDeclaration) this.executable);
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.ParameterAnnotationSource
        @CachedReturnPlugin.Enhance("parameterAnnotations")
        public Annotation[][] getParameterAnnotations() {
            Annotation[][] parameterAnnotations = this.parameterAnnotations != null ? null : ((Constructor) this.executable).getParameterAnnotations();
            Annotation[][] annotationArr = parameterAnnotations;
            if (parameterAnnotations == null) {
                annotationArr = this.parameterAnnotations;
            } else {
                this.parameterAnnotations = annotationArr;
            }
            return annotationArr;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$ForLoadedMethod.class */
    public static class ForLoadedMethod extends InDefinedShape.AbstractBase.ForLoadedExecutable<Method> implements ParameterDescription.ForLoadedParameter.ParameterAnnotationSource {
        private transient /* synthetic */ ParameterList parameters;
        private transient /* synthetic */ AnnotationList declaredAnnotations;
        private transient /* synthetic */ Annotation[][] parameterAnnotations;

        @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase.ForLoadedExecutable, net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public /* bridge */ /* synthetic */ TypeDescription.Generic getReceiverType() {
            return super.getReceiverType();
        }

        public ForLoadedMethod(Method method) {
            super(method);
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return TypeDescription.ForLoadedType.of(((Method) this.executable).getDeclaringClass());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(((Method) this.executable).getReturnType());
            }
            return new TypeDescription.Generic.LazyProjection.ForLoadedReturnType((Method) this.executable);
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        @CachedReturnPlugin.Enhance("parameters")
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            ParameterList<ParameterDescription.InDefinedShape> of = this.parameters != null ? null : ParameterList.ForLoadedExecutable.of((Method) this.executable, (ParameterDescription.ForLoadedParameter.ParameterAnnotationSource) this);
            ParameterList<ParameterDescription.InDefinedShape> parameterList = of;
            if (of == null) {
                parameterList = this.parameters;
            } else {
                this.parameters = parameterList;
            }
            return parameterList;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeList.Generic.ForLoadedTypes(((Method) this.executable).getExceptionTypes());
            }
            return new TypeList.Generic.OfMethodExceptionTypes((Method) this.executable);
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return false;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isBridge() {
            return ((Method) this.executable).isBridge();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Method method) {
            return ((Method) this.executable).equals(method) || equals(new ForLoadedMethod(method));
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Constructor<?> constructor) {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return ((Method) this.executable).getName();
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return ((Method) this.executable).getModifiers();
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public boolean isSynthetic() {
            return ((Method) this.executable).isSynthetic();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return ((Method) this.executable).getName();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithDescriptor
        public String getDescriptor() {
            return Type.getMethodDescriptor((Method) this.executable);
        }

        public Method getLoadedMethod() {
            return (Method) this.executable;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [net.bytebuddy.description.annotation.AnnotationList] */
        @Override // net.bytebuddy.description.annotation.AnnotationSource
        @CachedReturnPlugin.Enhance("declaredAnnotations")
        public AnnotationList getDeclaredAnnotations() {
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations = this.declaredAnnotations != null ? null : new AnnotationList.ForLoadedAnnotations(((Method) this.executable).getDeclaredAnnotations());
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations2 = forLoadedAnnotations;
            if (forLoadedAnnotations == null) {
                forLoadedAnnotations2 = this.declaredAnnotations;
            } else {
                this.declaredAnnotations = forLoadedAnnotations2;
            }
            return forLoadedAnnotations2;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        @MaybeNull
        public AnnotationValue<?, ?> getDefaultValue() {
            Object defaultValue = ((Method) this.executable).getDefaultValue();
            return defaultValue == null ? AnnotationValue.UNDEFINED : AnnotationDescription.ForLoadedAnnotation.asValue(defaultValue, ((Method) this.executable).getReturnType());
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeList.Generic.Empty();
            }
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of((GenericDeclaration) this.executable);
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.ParameterAnnotationSource
        @CachedReturnPlugin.Enhance("parameterAnnotations")
        public Annotation[][] getParameterAnnotations() {
            Annotation[][] parameterAnnotations = this.parameterAnnotations != null ? null : ((Method) this.executable).getParameterAnnotations();
            Annotation[][] annotationArr = parameterAnnotations;
            if (parameterAnnotations == null) {
                annotationArr = this.parameterAnnotations;
            } else {
                this.parameterAnnotations = annotationArr;
            }
            return annotationArr;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$Latent.class */
    public static class Latent extends InDefinedShape.AbstractBase {
        private final TypeDescription declaringType;
        private final String internalName;
        private final int modifiers;
        private final List<? extends TypeVariableToken> typeVariables;
        private final TypeDescription.Generic returnType;
        private final List<? extends ParameterDescription.Token> parameterTokens;
        private final List<? extends TypeDescription.Generic> exceptionTypes;
        private final List<? extends AnnotationDescription> declaredAnnotations;

        @MaybeNull
        private final AnnotationValue<?, ?> defaultValue;

        @MaybeNull
        private final TypeDescription.Generic receiverType;

        public Latent(TypeDescription typeDescription, Token token) {
            this(typeDescription, token.getName(), token.getModifiers(), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), token.getReceiverType());
        }

        public Latent(TypeDescription typeDescription, String str, int i, List<? extends TypeVariableToken> list, TypeDescription.Generic generic, List<? extends ParameterDescription.Token> list2, List<? extends TypeDescription.Generic> list3, List<? extends AnnotationDescription> list4, @MaybeNull AnnotationValue<?, ?> annotationValue, @MaybeNull TypeDescription.Generic generic2) {
            this.declaringType = typeDescription;
            this.internalName = str;
            this.modifiers = i;
            this.typeVariables = list;
            this.returnType = generic;
            this.parameterTokens = list2;
            this.exceptionTypes = list3;
            this.declaredAnnotations = list4;
            this.defaultValue = annotationValue;
            this.receiverType = generic2;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForDetachedTypes.attachVariables(this, this.typeVariables);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            return (TypeDescription.Generic) this.returnType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this));
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            return new ParameterList.ForTokens(this, this.parameterTokens);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            return TypeList.Generic.ForDetachedTypes.attach(this, this.exceptionTypes);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return this.internalName;
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return this.declaringType;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.modifiers;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        @MaybeNull
        public AnnotationValue<?, ?> getDefaultValue() {
            return this.defaultValue;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
        @MaybeNull
        public TypeDescription.Generic getReceiverType() {
            if (this.receiverType == null) {
                return super.getReceiverType();
            }
            return (TypeDescription.Generic) this.receiverType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this));
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$Latent$TypeInitializer.class */
        public static class TypeInitializer extends InDefinedShape.AbstractBase {
            private final TypeDescription typeDescription;

            public TypeInitializer(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeDescription.Generic getReturnType() {
                return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE);
            }

            @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
            public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                return new ParameterList.Empty();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeList.Generic getExceptionTypes() {
                return new TypeList.Generic.Empty();
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
                return this.typeDescription;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return 8;
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getInternalName() {
                return MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$TypeSubstituting.class */
    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final TypeDescription.Generic declaringType;
        private final MethodDescription methodDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, MethodDescription methodDescription, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringType = generic;
            this.methodDescription = methodDescription;
            this.visitor = visitor;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            return (TypeDescription.Generic) this.methodDescription.getReturnType().accept(this.visitor);
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return this.methodDescription.getTypeVariables().accept(this.visitor).filter(ElementMatchers.ofSort(TypeDefinition.Sort.VARIABLE));
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        public ParameterList<ParameterDescription.InGenericShape> getParameters() {
            return new ParameterList.TypeSubstituting(this, this.methodDescription.getParameters(), this.visitor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.ForDetachedTypes(this.methodDescription.getExceptionTypes(), this.visitor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        @MaybeNull
        public AnnotationValue<?, ?> getDefaultValue() {
            return this.methodDescription.getDefaultValue();
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic receiverType = this.methodDescription.getReceiverType();
            return receiverType == null ? TypeDescription.Generic.UNDEFINED : (TypeDescription.Generic) receiverType.accept(this.visitor);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return this.methodDescription.getDeclaredAnnotations();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription.Generic getDeclaringType() {
            return this.declaringType;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.methodDescription.getModifiers();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return this.methodDescription.getInternalName();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public InDefinedShape asDefined() {
            return this.methodDescription.asDefined();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return this.methodDescription.isConstructor();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isMethod() {
            return this.methodDescription.isMethod();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return this.methodDescription.isTypeInitializer();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$Token.class */
    public static class Token implements ByteCodeElement.Token<Token> {
        private final String name;
        private final int modifiers;
        private final List<? extends TypeVariableToken> typeVariableTokens;
        private final TypeDescription.Generic returnType;
        private final List<? extends ParameterDescription.Token> parameterTokens;
        private final List<? extends TypeDescription.Generic> exceptionTypes;
        private final List<? extends AnnotationDescription> annotations;

        @MaybeNull
        private final AnnotationValue<?, ?> defaultValue;

        @MaybeNull
        private final TypeDescription.Generic receiverType;
        private transient /* synthetic */ int hashCode;

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public /* bridge */ /* synthetic */ Token accept(TypeDescription.Generic.Visitor visitor) {
            return accept((TypeDescription.Generic.Visitor<? extends TypeDescription.Generic>) visitor);
        }

        public Token(int i) {
            this(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, i, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE));
        }

        public Token(String str, int i, TypeDescription.Generic generic) {
            this(str, i, generic, Collections.emptyList());
        }

        public Token(String str, int i, TypeDescription.Generic generic, List<? extends TypeDescription.Generic> list) {
            this(str, i, Collections.emptyList(), generic, new ParameterDescription.Token.TypeList(list), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
        }

        public Token(String str, int i, List<? extends TypeVariableToken> list, TypeDescription.Generic generic, List<? extends ParameterDescription.Token> list2, List<? extends TypeDescription.Generic> list3, List<? extends AnnotationDescription> list4, @MaybeNull AnnotationValue<?, ?> annotationValue, @MaybeNull TypeDescription.Generic generic2) {
            this.name = str;
            this.modifiers = i;
            this.typeVariableTokens = list;
            this.returnType = generic;
            this.parameterTokens = list2;
            this.exceptionTypes = list3;
            this.annotations = list4;
            this.defaultValue = annotationValue;
            this.receiverType = generic2;
        }

        public String getName() {
            return this.name;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public ByteCodeElement.Token.TokenList<TypeVariableToken> getTypeVariableTokens() {
            return new ByteCodeElement.Token.TokenList<>(this.typeVariableTokens);
        }

        public TypeDescription.Generic getReturnType() {
            return this.returnType;
        }

        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> getParameterTokens() {
            return new ByteCodeElement.Token.TokenList<>(this.parameterTokens);
        }

        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.Explicit(this.exceptionTypes);
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        @MaybeNull
        public AnnotationValue<?, ?> getDefaultValue() {
            return this.defaultValue;
        }

        @MaybeNull
        public TypeDescription.Generic getReceiverType() {
            return this.receiverType;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            return new Token(this.name, this.modifiers, getTypeVariableTokens().accept(visitor), (TypeDescription.Generic) this.returnType.accept(visitor), getParameterTokens().accept(visitor), getExceptionTypes().accept(visitor), this.annotations, this.defaultValue, this.receiverType == null ? TypeDescription.Generic.UNDEFINED : (TypeDescription.Generic) this.receiverType.accept(visitor));
        }

        public SignatureToken asSignatureToken(TypeDescription typeDescription) {
            TypeDescription.Generic.Visitor.Reducing reducing = new TypeDescription.Generic.Visitor.Reducing(typeDescription, this.typeVariableTokens);
            ArrayList arrayList = new ArrayList(this.parameterTokens.size());
            Iterator<? extends ParameterDescription.Token> it = this.parameterTokens.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getType().accept(reducing));
            }
            return new SignatureToken(this.name, (TypeDescription) this.returnType.accept(reducing), arrayList);
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int hashCode;
            if (this.hashCode != 0) {
                hashCode = 0;
            } else {
                hashCode = (((((((((((((((this.name.hashCode() * 31) + this.modifiers) * 31) + this.typeVariableTokens.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTokens.hashCode()) * 31) + this.exceptionTypes.hashCode()) * 31) + this.annotations.hashCode()) * 31) + (this.defaultValue != null ? this.defaultValue.hashCode() : 0)) * 31) + (this.receiverType != null ? this.receiverType.hashCode() : 0);
            }
            int i = hashCode;
            if (hashCode == 0) {
                i = this.hashCode;
            } else {
                this.hashCode = i;
            }
            return i;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Token token = (Token) obj;
            if (this.modifiers != token.modifiers || !this.name.equals(token.name) || !this.typeVariableTokens.equals(token.typeVariableTokens) || !this.returnType.equals(token.returnType) || !this.parameterTokens.equals(token.parameterTokens) || !this.exceptionTypes.equals(token.exceptionTypes) || !this.annotations.equals(token.annotations)) {
                return false;
            }
            if (this.defaultValue != null) {
                if (!this.defaultValue.equals(token.defaultValue)) {
                    return false;
                }
            } else if (token.defaultValue != null) {
                return false;
            }
            return this.receiverType != null ? this.receiverType.equals(token.receiverType) : token.receiverType == null;
        }

        public String toString() {
            return "MethodDescription.Token{name='" + this.name + "', modifiers=" + this.modifiers + ", typeVariableTokens=" + this.typeVariableTokens + ", returnType=" + this.returnType + ", parameterTokens=" + this.parameterTokens + ", exceptionTypes=" + this.exceptionTypes + ", annotations=" + this.annotations + ", defaultValue=" + this.defaultValue + ", receiverType=" + this.receiverType + '}';
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$SignatureToken.class */
    public static class SignatureToken {
        private final String name;
        private final TypeDescription returnType;
        private final List<? extends TypeDescription> parameterTypes;
        private transient /* synthetic */ int hashCode;

        public SignatureToken(String str, TypeDescription typeDescription, TypeDescription... typeDescriptionArr) {
            this(str, typeDescription, (List<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
        }

        public SignatureToken(String str, TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.name = str;
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public List<TypeDescription> getParameterTypes() {
            return this.parameterTypes;
        }

        public TypeToken asTypeToken() {
            return new TypeToken(this.returnType, this.parameterTypes);
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            return sb.append(')').append(this.returnType.getDescriptor()).toString();
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int hashCode = this.hashCode != 0 ? 0 : (((this.name.hashCode() * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
            int i = hashCode;
            if (hashCode == 0) {
                i = this.hashCode;
            } else {
                this.hashCode = i;
            }
            return i;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureToken)) {
                return false;
            }
            SignatureToken signatureToken = (SignatureToken) obj;
            return this.name.equals(signatureToken.name) && this.returnType.equals(signatureToken.returnType) && this.parameterTypes.equals(signatureToken.parameterTypes);
        }

        public String toString() {
            StringBuilder append = new StringBuilder().append(this.returnType).append(' ').append(this.name).append('(');
            boolean z = true;
            for (TypeDescription typeDescription : this.parameterTypes) {
                if (z) {
                    z = false;
                } else {
                    append.append(',');
                }
                append.append(typeDescription);
            }
            return append.append(')').toString();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/method/MethodDescription$TypeToken.class */
    public static class TypeToken {
        private final TypeDescription returnType;
        private final List<? extends TypeDescription> parameterTypes;
        private transient /* synthetic */ int hashCode;

        public TypeToken(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public List<TypeDescription> getParameterTypes() {
            return this.parameterTypes;
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int hashCode = this.hashCode != 0 ? 0 : (this.returnType.hashCode() * 31) + this.parameterTypes.hashCode();
            int i = hashCode;
            if (hashCode == 0) {
                i = this.hashCode;
            } else {
                this.hashCode = i;
            }
            return i;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TypeToken)) {
                return false;
            }
            TypeToken typeToken = (TypeToken) obj;
            return this.returnType.equals(typeToken.returnType) && this.parameterTypes.equals(typeToken.parameterTypes);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            return sb.append(')').append(this.returnType.getDescriptor()).toString();
        }
    }
}
