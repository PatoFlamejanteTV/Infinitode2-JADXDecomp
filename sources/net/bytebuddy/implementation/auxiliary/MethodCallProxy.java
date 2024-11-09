package net.bytebuddy.implementation.auxiliary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy.class */
public class MethodCallProxy implements AuxiliaryType {
    private static final String FIELD_NAME_PREFIX = "argument";
    private final Implementation.SpecialMethodInvocation specialMethodInvocation;
    private final boolean serializableProxy;
    private final Assigner assigner;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.serializableProxy == ((MethodCallProxy) obj).serializableProxy && this.specialMethodInvocation.equals(((MethodCallProxy) obj).specialMethodInvocation) && this.assigner.equals(((MethodCallProxy) obj).assigner);
    }

    public int hashCode() {
        return (((((getClass().hashCode() * 31) + this.specialMethodInvocation.hashCode()) * 31) + (this.serializableProxy ? 1 : 0)) * 31) + this.assigner.hashCode();
    }

    public MethodCallProxy(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z) {
        this(specialMethodInvocation, z, Assigner.DEFAULT);
    }

    public MethodCallProxy(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z, Assigner assigner) {
        this.specialMethodInvocation = specialMethodInvocation;
        this.serializableProxy = z;
        this.assigner = assigner;
    }

    private static LinkedHashMap<String, TypeDescription> extractFields(MethodDescription methodDescription) {
        LinkedHashMap<String, TypeDescription> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        if (!methodDescription.isStatic()) {
            i = 0 + 1;
            linkedHashMap.put(fieldName(0), methodDescription.getDeclaringType().asErasure());
        }
        Iterator it = methodDescription.getParameters().iterator();
        while (it.hasNext()) {
            int i2 = i;
            i++;
            linkedHashMap.put(fieldName(i2), ((ParameterDescription) it.next()).getType().asErasure());
        }
        return linkedHashMap;
    }

    private static String fieldName(int i) {
        return FIELD_NAME_PREFIX + i;
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public String getSuffix() {
        return RandomString.hashOf(this.specialMethodInvocation.getMethodDescription().hashCode()) + (this.serializableProxy ? "S" : "0");
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        MethodDescription.InDefinedShape registerAccessorFor = methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT);
        LinkedHashMap<String, TypeDescription> extractFields = extractFields(registerAccessorFor);
        DynamicType.Builder intercept = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).with(PrecomputedMethodGraph.INSTANCE).subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(Runnable.class, Callable.class).intercept(new MethodCall(registerAccessorFor, this.assigner)).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]).withParameters(extractFields.values()).intercept(ConstructorCall.INSTANCE);
        for (Map.Entry<String, TypeDescription> entry : extractFields.entrySet()) {
            intercept = intercept.defineField(entry.getKey(), entry.getValue(), Visibility.PRIVATE);
        }
        return intercept.make();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy$PrecomputedMethodGraph.class */
    protected enum PrecomputedMethodGraph implements MethodGraph.Compiler {
        INSTANCE;

        private final transient MethodGraph.Linked methodGraph;

        PrecomputedMethodGraph() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            MethodDescription.Latent latent = new MethodDescription.Latent(TypeDescription.ForLoadedType.of(Callable.class), "call", 1025, Collections.emptyList(), TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class), Collections.emptyList(), Collections.singletonList(TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Exception.class)), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
            linkedHashMap.put(latent.asSignatureToken(), new MethodGraph.Node.Simple(latent));
            MethodDescription.Latent latent2 = new MethodDescription.Latent(TypeDescription.ForLoadedType.of(Runnable.class), "run", 1025, Collections.emptyList(), TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
            linkedHashMap.put(latent2.asSignatureToken(), new MethodGraph.Node.Simple(latent2));
            MethodGraph.Simple simple = new MethodGraph.Simple(linkedHashMap);
            this.methodGraph = new MethodGraph.Linked.Delegation(simple, simple, Collections.emptyMap());
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public final MethodGraph.Linked compile(TypeDefinition typeDefinition) {
            return this.methodGraph;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        @Deprecated
        public final MethodGraph.Linked compile(TypeDescription typeDescription) {
            return this.methodGraph;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public final MethodGraph.Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
            return this.methodGraph;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        @Deprecated
        public final MethodGraph.Linked compile(TypeDescription typeDescription, TypeDescription typeDescription2) {
            return this.methodGraph;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy$ConstructorCall.class */
    protected enum ConstructorCall implements Implementation {
        INSTANCE;

        private final MethodDescription objectTypeDefaultConstructor = (MethodDescription) TypeDescription.ForLoadedType.of(Object.class).getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly();

        ConstructorCall() {
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public final InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public final ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType(), (byte) 0);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy$ConstructorCall$Appender.class */
        protected static class Appender implements ByteCodeAppender {
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
                FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                StackManipulation[] stackManipulationArr = new StackManipulation[declaredFields.size()];
                int i = 0;
                Iterator it = declaredFields.iterator();
                while (it.hasNext()) {
                    stackManipulationArr[i] = new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodVariableAccess.load((ParameterDescription) methodDescription.getParameters().get(i)), FieldAccess.forField((FieldDescription) it.next()).write());
                    i++;
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(ConstructorCall.INSTANCE.objectTypeDefaultConstructor), new StackManipulation.Compound(stackManipulationArr), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy$MethodCall.class */
    public static class MethodCall implements Implementation {
        private final MethodDescription accessorMethod;
        private final Assigner assigner;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.accessorMethod.equals(((MethodCall) obj).accessorMethod) && this.assigner.equals(((MethodCall) obj).assigner);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.accessorMethod.hashCode()) * 31) + this.assigner.hashCode();
        }

        protected MethodCall(MethodDescription methodDescription, Assigner assigner) {
            this.accessorMethod = methodDescription;
            this.assigner = assigner;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(this, target.getInstrumentedType(), (byte) 0);
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy$MethodCall$Appender.class */
        protected class Appender implements ByteCodeAppender {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Appender) obj).instrumentedType) && MethodCall.this.equals(MethodCall.this);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + MethodCall.this.hashCode();
            }

            /* synthetic */ Appender(MethodCall methodCall, TypeDescription typeDescription, byte b2) {
                this(typeDescription);
            }

            private Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                ArrayList arrayList = new ArrayList(declaredFields.size());
                Iterator it = declaredFields.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription) it.next()).read()));
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(new StackManipulation.Compound(arrayList), MethodInvocation.invoke(MethodCall.this.accessorMethod), MethodCall.this.assigner.assign(MethodCall.this.accessorMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/MethodCallProxy$AssignableSignatureCall.class */
    public static class AssignableSignatureCall extends StackManipulation.AbstractBase {
        private final Implementation.SpecialMethodInvocation specialMethodInvocation;
        private final boolean serializable;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.serializable == ((AssignableSignatureCall) obj).serializable && this.specialMethodInvocation.equals(((AssignableSignatureCall) obj).specialMethodInvocation);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.specialMethodInvocation.hashCode()) * 31) + (this.serializable ? 1 : 0);
        }

        public AssignableSignatureCall(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z) {
            this.specialMethodInvocation = specialMethodInvocation;
            this.serializable = z;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new MethodCallProxy(this.specialMethodInvocation, this.serializable));
            return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(this.specialMethodInvocation.getMethodDescription()).prependThisReference(), MethodInvocation.invoke((MethodDescription.InDefinedShape) register.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly())).apply(methodVisitor, context);
        }
    }
}
