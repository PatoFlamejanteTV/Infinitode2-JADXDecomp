package net.bytebuddy.implementation.auxiliary;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.Throw;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy.class */
public class TypeProxy implements AuxiliaryType {
    public static final String REFLECTION_METHOD = "make";
    public static final String INSTANCE_FIELD = "target";
    private final TypeDescription proxiedType;
    private final Implementation.Target implementationTarget;
    private final InvocationFactory invocationFactory;
    private final boolean ignoreFinalizer;
    private final boolean serializableProxy;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.ignoreFinalizer == ((TypeProxy) obj).ignoreFinalizer && this.serializableProxy == ((TypeProxy) obj).serializableProxy && this.proxiedType.equals(((TypeProxy) obj).proxiedType) && this.implementationTarget.equals(((TypeProxy) obj).implementationTarget) && this.invocationFactory.equals(((TypeProxy) obj).invocationFactory);
    }

    public int hashCode() {
        return (((((((((getClass().hashCode() * 31) + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + this.invocationFactory.hashCode()) * 31) + (this.ignoreFinalizer ? 1 : 0)) * 31) + (this.serializableProxy ? 1 : 0);
    }

    public TypeProxy(TypeDescription typeDescription, Implementation.Target target, InvocationFactory invocationFactory, boolean z, boolean z2) {
        this.proxiedType = typeDescription;
        this.implementationTarget = target;
        this.invocationFactory = invocationFactory;
        this.ignoreFinalizer = z;
        this.serializableProxy = z2;
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public String getSuffix() {
        return RandomString.hashOf(this.proxiedType.hashCode()) + (this.ignoreFinalizer ? "I" : "0") + (this.serializableProxy ? "S" : "0");
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        return new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).ignore(this.ignoreFinalizer ? ElementMatchers.isFinalizer() : ElementMatchers.none()).subclass(this.proxiedType).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).method(ElementMatchers.any()).intercept(new MethodCall(methodAccessorFactory)).defineMethod(REFLECTION_METHOD, TargetType.class, Ownership.STATIC).intercept(SilentConstruction.INSTANCE).make();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$AbstractMethodErrorThrow.class */
    protected enum AbstractMethodErrorThrow implements StackManipulation {
        INSTANCE;

        private final transient StackManipulation implementation;

        AbstractMethodErrorThrow() {
            TypeDescription of = TypeDescription.ForLoadedType.of(AbstractMethodError.class);
            this.implementation = new StackManipulation.Compound(TypeCreation.of(of), Duplication.SINGLE, MethodInvocation.invoke((MethodDescription) of.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0))).getOnly()), Throw.INSTANCE);
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final boolean isValid() {
            return this.implementation.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.implementation.apply(methodVisitor, context);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$SilentConstruction.class */
    protected enum SilentConstruction implements Implementation {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public final InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public final ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType(), (byte) 0);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$SilentConstruction$Appender.class */
        protected static class Appender implements ByteCodeAppender {
            public static final String REFLECTION_FACTORY_INTERNAL_NAME = "sun/reflect/ReflectionFactory";
            public static final String GET_REFLECTION_FACTORY_METHOD_NAME = "getReflectionFactory";
            public static final String GET_REFLECTION_FACTORY_METHOD_DESCRIPTOR = "()Lsun/reflect/ReflectionFactory;";
            public static final String NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_NAME = "newConstructorForSerialization";
            public static final String NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_DESCRIPTOR = "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;)Ljava/lang/reflect/Constructor;";
            public static final String JAVA_LANG_OBJECT_DESCRIPTOR = "Ljava/lang/Object;";
            public static final String JAVA_LANG_OBJECT_INTERNAL_NAME = "java/lang/Object";
            public static final String JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME = "java/lang/reflect/Constructor";
            public static final String NEW_INSTANCE_METHOD_NAME = "newInstance";
            public static final String NEW_INSTANCE_METHOD_DESCRIPTOR = "([Ljava/lang/Object;)Ljava/lang/Object;";
            public static final String JAVA_LANG_CLASS_INTERNAL_NAME = "java/lang/Class";
            public static final String GET_DECLARED_CONSTRUCTOR_METHOD_NAME = "getDeclaredConstructor";
            public static final String GET_DECLARED_CONSTRUCTOR_METHOD_DESCRIPTOR = "([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;";
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Appender) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            /* synthetic */ Appender(TypeDescription typeDescription, byte b2) {
                this(typeDescription);
            }

            private Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                methodVisitor.visitMethodInsn(184, REFLECTION_FACTORY_INTERNAL_NAME, GET_REFLECTION_FACTORY_METHOD_NAME, GET_REFLECTION_FACTORY_METHOD_DESCRIPTOR, false);
                methodVisitor.visitLdcInsn(Type.getType(this.instrumentedType.getDescriptor()));
                methodVisitor.visitLdcInsn(Type.getType(JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, JAVA_LANG_CLASS_INTERNAL_NAME, GET_DECLARED_CONSTRUCTOR_METHOD_NAME, GET_DECLARED_CONSTRUCTOR_METHOD_DESCRIPTOR, false);
                methodVisitor.visitMethodInsn(182, REFLECTION_FACTORY_INTERNAL_NAME, NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_NAME, NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_DESCRIPTOR, false);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, NEW_INSTANCE_METHOD_NAME, NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor.visitTypeInsn(192, this.instrumentedType.getInternalName());
                methodVisitor.visitInsn(176);
                return new ByteCodeAppender.Size(4, 0);
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$InvocationFactory.class */
    public interface InvocationFactory {
        Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$InvocationFactory$Default.class */
        public enum Default implements InvocationFactory {
            SUPER_METHOD { // from class: net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory.Default.1
                @Override // net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory
                public final Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription) {
                    return target.invokeDominant(methodDescription.asSignatureToken());
                }
            },
            DEFAULT_METHOD { // from class: net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory.Default.2
                @Override // net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory
                public final Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription) {
                    return target.invokeDefault(methodDescription.asSignatureToken(), typeDescription);
                }
            };

            /* synthetic */ Default(byte b2) {
                this();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$ForSuperMethodByConstructor.class */
    public static class ForSuperMethodByConstructor extends StackManipulation.AbstractBase {
        private final TypeDescription proxiedType;
        private final Implementation.Target implementationTarget;
        private final List<TypeDescription> constructorParameters;
        private final boolean ignoreFinalizer;
        private final boolean serializableProxy;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.ignoreFinalizer == ((ForSuperMethodByConstructor) obj).ignoreFinalizer && this.serializableProxy == ((ForSuperMethodByConstructor) obj).serializableProxy && this.proxiedType.equals(((ForSuperMethodByConstructor) obj).proxiedType) && this.implementationTarget.equals(((ForSuperMethodByConstructor) obj).implementationTarget) && this.constructorParameters.equals(((ForSuperMethodByConstructor) obj).constructorParameters);
        }

        public int hashCode() {
            return (((((((((getClass().hashCode() * 31) + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + this.constructorParameters.hashCode()) * 31) + (this.ignoreFinalizer ? 1 : 0)) * 31) + (this.serializableProxy ? 1 : 0);
        }

        public ForSuperMethodByConstructor(TypeDescription typeDescription, Implementation.Target target, List<TypeDescription> list, boolean z, boolean z2) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.constructorParameters = list;
            this.ignoreFinalizer = z;
            this.serializableProxy = z2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.SUPER_METHOD, this.ignoreFinalizer, this.serializableProxy));
            StackManipulation[] stackManipulationArr = new StackManipulation[this.constructorParameters.size()];
            int i = 0;
            Iterator<TypeDescription> it = this.constructorParameters.iterator();
            while (it.hasNext()) {
                int i2 = i;
                i++;
                stackManipulationArr[i2] = DefaultValue.of(it.next());
            }
            return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, new StackManipulation.Compound(stackManipulationArr), MethodInvocation.invoke((MethodDescription.InDefinedShape) register.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(this.constructorParameters))).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) register.getDeclaredFields().filter(ElementMatchers.named("target")).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$ForSuperMethodByReflectionFactory.class */
    public static class ForSuperMethodByReflectionFactory extends StackManipulation.AbstractBase {
        private final TypeDescription proxiedType;
        private final Implementation.Target implementationTarget;
        private final boolean ignoreFinalizer;
        private final boolean serializableProxy;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.ignoreFinalizer == ((ForSuperMethodByReflectionFactory) obj).ignoreFinalizer && this.serializableProxy == ((ForSuperMethodByReflectionFactory) obj).serializableProxy && this.proxiedType.equals(((ForSuperMethodByReflectionFactory) obj).proxiedType) && this.implementationTarget.equals(((ForSuperMethodByReflectionFactory) obj).implementationTarget);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + (this.ignoreFinalizer ? 1 : 0)) * 31) + (this.serializableProxy ? 1 : 0);
        }

        public ForSuperMethodByReflectionFactory(TypeDescription typeDescription, Implementation.Target target, boolean z, boolean z2) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.ignoreFinalizer = z;
            this.serializableProxy = z2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.SUPER_METHOD, this.ignoreFinalizer, this.serializableProxy));
            return new StackManipulation.Compound(MethodInvocation.invoke((MethodDescription.InDefinedShape) register.getDeclaredMethods().filter(ElementMatchers.named(TypeProxy.REFLECTION_METHOD).and(ElementMatchers.takesArguments(0))).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) register.getDeclaredFields().filter(ElementMatchers.named("target")).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$ForDefaultMethod.class */
    public static class ForDefaultMethod extends StackManipulation.AbstractBase {
        private final TypeDescription proxiedType;
        private final Implementation.Target implementationTarget;
        private final boolean serializableProxy;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.serializableProxy == ((ForDefaultMethod) obj).serializableProxy && this.proxiedType.equals(((ForDefaultMethod) obj).proxiedType) && this.implementationTarget.equals(((ForDefaultMethod) obj).implementationTarget);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + (this.serializableProxy ? 1 : 0);
        }

        public ForDefaultMethod(TypeDescription typeDescription, Implementation.Target target, boolean z) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.serializableProxy = z;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.DEFAULT_METHOD, true, this.serializableProxy));
            return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, MethodInvocation.invoke((MethodDescription.InDefinedShape) register.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) register.getDeclaredFields().filter(ElementMatchers.named("target")).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$MethodCall.class */
    public class MethodCall implements Implementation {
        private final MethodAccessorFactory methodAccessorFactory;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.methodAccessorFactory.equals(((MethodCall) obj).methodAccessorFactory) && TypeProxy.this.equals(TypeProxy.this);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.methodAccessorFactory.hashCode()) * 31) + TypeProxy.this.hashCode();
        }

        protected MethodCall(MethodAccessorFactory methodAccessorFactory) {
            this.methodAccessorFactory = methodAccessorFactory;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token("target", 65, TypeProxy.this.implementationTarget.getInstrumentedType().asGenericType()));
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$MethodCall$Appender.class */
        protected class Appender implements ByteCodeAppender {
            private final StackManipulation fieldLoadingInstruction;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldLoadingInstruction.equals(((Appender) obj).fieldLoadingInstruction) && MethodCall.this.equals(MethodCall.this);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.fieldLoadingInstruction.hashCode()) * 31) + MethodCall.this.hashCode();
            }

            protected Appender(TypeDescription typeDescription) {
                this.fieldLoadingInstruction = FieldAccess.forField((FieldDescription.InDefinedShape) typeDescription.getDeclaredFields().filter(ElementMatchers.named("target")).getOnly()).read();
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                Implementation.SpecialMethodInvocation invoke = TypeProxy.this.invocationFactory.invoke(TypeProxy.this.implementationTarget, TypeProxy.this.proxiedType, methodDescription);
                return new ByteCodeAppender.Size((invoke.isValid() ? new AccessorMethodInvocation(methodDescription, invoke) : AbstractMethodErrorThrow.INSTANCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/TypeProxy$MethodCall$Appender$AccessorMethodInvocation.class */
            protected class AccessorMethodInvocation implements StackManipulation {
                private final MethodDescription instrumentedMethod;
                private final Implementation.SpecialMethodInvocation specialMethodInvocation;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.instrumentedMethod.equals(((AccessorMethodInvocation) obj).instrumentedMethod) && this.specialMethodInvocation.equals(((AccessorMethodInvocation) obj).specialMethodInvocation) && Appender.this.equals(Appender.this);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.instrumentedMethod.hashCode()) * 31) + this.specialMethodInvocation.hashCode()) * 31) + Appender.this.hashCode();
                }

                protected AccessorMethodInvocation(MethodDescription methodDescription, Implementation.SpecialMethodInvocation specialMethodInvocation) {
                    this.instrumentedMethod = methodDescription;
                    this.specialMethodInvocation = specialMethodInvocation;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public boolean isValid() {
                    return this.specialMethodInvocation.isValid();
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    MethodDescription.InDefinedShape registerAccessorFor = MethodCall.this.methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT);
                    return new StackManipulation.Compound(MethodVariableAccess.loadThis(), Appender.this.fieldLoadingInstruction, MethodVariableAccess.allArgumentsOf(this.instrumentedMethod).asBridgeOf(registerAccessorFor), MethodInvocation.invoke(registerAccessorFor), MethodReturn.of(this.instrumentedMethod.getReturnType())).apply(methodVisitor, context);
                }
            }
        }
    }
}
