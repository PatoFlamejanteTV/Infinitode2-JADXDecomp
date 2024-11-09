package net.bytebuddy.implementation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.InstanceCheck;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod.class */
public class EqualsMethod implements Implementation {
    private static final MethodDescription.InDefinedShape EQUALS = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Object.class).getDeclaredMethods().filter(ElementMatchers.isEquals()).getOnly();
    private final SuperClassCheck superClassCheck;
    private final TypeCompatibilityCheck typeCompatibilityCheck;
    private final ElementMatcher.Junction<? super FieldDescription.InDefinedShape> ignored;
    private final ElementMatcher.Junction<? super FieldDescription.InDefinedShape> nonNullable;
    private final Comparator<? super FieldDescription.InDefinedShape> comparator;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.superClassCheck.equals(((EqualsMethod) obj).superClassCheck) && this.typeCompatibilityCheck.equals(((EqualsMethod) obj).typeCompatibilityCheck) && this.ignored.equals(((EqualsMethod) obj).ignored) && this.nonNullable.equals(((EqualsMethod) obj).nonNullable) && this.comparator.equals(((EqualsMethod) obj).comparator);
    }

    public int hashCode() {
        return (((((((((getClass().hashCode() * 31) + this.superClassCheck.hashCode()) * 31) + this.typeCompatibilityCheck.hashCode()) * 31) + this.ignored.hashCode()) * 31) + this.nonNullable.hashCode()) * 31) + this.comparator.hashCode();
    }

    protected EqualsMethod(SuperClassCheck superClassCheck) {
        this(superClassCheck, TypeCompatibilityCheck.EXACT, ElementMatchers.none(), ElementMatchers.none(), NaturalOrderComparator.INSTANCE);
    }

    private EqualsMethod(SuperClassCheck superClassCheck, TypeCompatibilityCheck typeCompatibilityCheck, ElementMatcher.Junction<? super FieldDescription.InDefinedShape> junction, ElementMatcher.Junction<? super FieldDescription.InDefinedShape> junction2, Comparator<? super FieldDescription.InDefinedShape> comparator) {
        this.superClassCheck = superClassCheck;
        this.typeCompatibilityCheck = typeCompatibilityCheck;
        this.ignored = junction;
        this.nonNullable = junction2;
        this.comparator = comparator;
    }

    public static EqualsMethod requiringSuperClassEquality() {
        return new EqualsMethod(SuperClassCheck.ENABLED);
    }

    public static EqualsMethod isolated() {
        return new EqualsMethod(SuperClassCheck.DISABLED);
    }

    public EqualsMethod withIgnoredFields(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new EqualsMethod(this.superClassCheck, this.typeCompatibilityCheck, this.ignored.or(elementMatcher), this.nonNullable, this.comparator);
    }

    public EqualsMethod withNonNullableFields(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new EqualsMethod(this.superClassCheck, this.typeCompatibilityCheck, this.ignored, this.nonNullable.or(elementMatcher), this.comparator);
    }

    public EqualsMethod withPrimitiveTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_PRIMITIVE_TYPES);
    }

    public EqualsMethod withEnumerationTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_ENUMERATION_TYPES);
    }

    public EqualsMethod withPrimitiveWrapperTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_PRIMITIVE_WRAPPER_TYPES);
    }

    public EqualsMethod withStringTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_STRING_TYPES);
    }

    public EqualsMethod withFieldOrder(Comparator<? super FieldDescription.InDefinedShape> comparator) {
        return new EqualsMethod(this.superClassCheck, this.typeCompatibilityCheck, this.ignored, this.nonNullable, new CompoundComparator((Comparator<? super FieldDescription.InDefinedShape>[]) new Comparator[]{this.comparator, comparator}));
    }

    public Implementation withSubclassEquality() {
        return new EqualsMethod(this.superClassCheck, TypeCompatibilityCheck.SUBCLASS, this.ignored, this.nonNullable, this.comparator);
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        if (target.getInstrumentedType().isInterface()) {
            throw new IllegalStateException("Cannot implement meaningful equals method for " + target.getInstrumentedType());
        }
        ArrayList arrayList = new ArrayList(target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.not(ElementMatchers.isStatic().or(this.ignored))));
        Collections.sort(arrayList, this.comparator);
        return new Appender(target.getInstrumentedType(), new StackManipulation.Compound(this.superClassCheck.resolve(target.getInstrumentedType()), MethodVariableAccess.loadThis(), MethodVariableAccess.REFERENCE.loadFrom(1), ConditionalReturn.onIdentity().returningTrue(), this.typeCompatibilityCheck.resolve(target.getInstrumentedType())), arrayList, this.nonNullable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$SuperClassCheck.class */
    public enum SuperClassCheck {
        DISABLED { // from class: net.bytebuddy.implementation.EqualsMethod.SuperClassCheck.1
            @Override // net.bytebuddy.implementation.EqualsMethod.SuperClassCheck
            protected final StackManipulation resolve(TypeDescription typeDescription) {
                return StackManipulation.Trivial.INSTANCE;
            }
        },
        ENABLED { // from class: net.bytebuddy.implementation.EqualsMethod.SuperClassCheck.2
            @Override // net.bytebuddy.implementation.EqualsMethod.SuperClassCheck
            protected final StackManipulation resolve(TypeDescription typeDescription) {
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    throw new IllegalStateException(typeDescription + " does not declare a super class");
                }
                return new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodVariableAccess.REFERENCE.loadFrom(1), MethodInvocation.invoke(EqualsMethod.EQUALS).special(superClass.asErasure()), ConditionalReturn.onZeroInteger());
            }
        };

        protected abstract StackManipulation resolve(TypeDescription typeDescription);

        /* synthetic */ SuperClassCheck(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$TypeCompatibilityCheck.class */
    public enum TypeCompatibilityCheck {
        EXACT { // from class: net.bytebuddy.implementation.EqualsMethod.TypeCompatibilityCheck.1
            @Override // net.bytebuddy.implementation.EqualsMethod.TypeCompatibilityCheck
            public final StackManipulation resolve(TypeDescription typeDescription) {
                return new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), ConditionalReturn.onNullValue(), MethodVariableAccess.REFERENCE.loadFrom(0), MethodInvocation.invoke(GET_CLASS), MethodVariableAccess.REFERENCE.loadFrom(1), MethodInvocation.invoke(GET_CLASS), ConditionalReturn.onNonIdentity());
            }
        },
        SUBCLASS { // from class: net.bytebuddy.implementation.EqualsMethod.TypeCompatibilityCheck.2
            @Override // net.bytebuddy.implementation.EqualsMethod.TypeCompatibilityCheck
            protected final StackManipulation resolve(TypeDescription typeDescription) {
                return new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), InstanceCheck.of(typeDescription), ConditionalReturn.onZeroInteger());
            }
        };

        protected static final MethodDescription.InDefinedShape GET_CLASS = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Object.class).getDeclaredMethods().filter(ElementMatchers.named("getClass")).getOnly();

        protected abstract StackManipulation resolve(TypeDescription typeDescription);

        /* synthetic */ TypeCompatibilityCheck(byte b2) {
            this();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$NullValueGuard.class */
    protected interface NullValueGuard {
        StackManipulation before();

        StackManipulation after();

        int getRequiredVariablePadding();

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$NullValueGuard$NoOp.class */
        public enum NoOp implements NullValueGuard {
            INSTANCE;

            @Override // net.bytebuddy.implementation.EqualsMethod.NullValueGuard
            public final StackManipulation before() {
                return StackManipulation.Trivial.INSTANCE;
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.NullValueGuard
            public final StackManipulation after() {
                return StackManipulation.Trivial.INSTANCE;
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.NullValueGuard
            public final int getRequiredVariablePadding() {
                return StackSize.ZERO.getSize();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$NullValueGuard$UsingJump.class */
        public static class UsingJump implements NullValueGuard {
            private final MethodDescription instrumentedMethod;
            private final Label firstValueNull = new Label();
            private final Label secondValueNull = new Label();
            private final Label endOfBlock = new Label();

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedMethod.equals(((UsingJump) obj).instrumentedMethod) && this.firstValueNull.equals(((UsingJump) obj).firstValueNull) && this.secondValueNull.equals(((UsingJump) obj).secondValueNull) && this.endOfBlock.equals(((UsingJump) obj).endOfBlock);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.instrumentedMethod.hashCode()) * 31) + this.firstValueNull.hashCode()) * 31) + this.secondValueNull.hashCode()) * 31) + this.endOfBlock.hashCode();
            }

            protected UsingJump(MethodDescription methodDescription) {
                this.instrumentedMethod = methodDescription;
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.NullValueGuard
            public StackManipulation before() {
                return new BeforeInstruction();
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.NullValueGuard
            public StackManipulation after() {
                return new AfterInstruction();
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.NullValueGuard
            public int getRequiredVariablePadding() {
                return 2;
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$NullValueGuard$UsingJump$BeforeInstruction.class */
            protected class BeforeInstruction extends StackManipulation.AbstractBase {
                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && UsingJump.this.equals(UsingJump.this);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + UsingJump.this.hashCode();
                }

                protected BeforeInstruction() {
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    methodVisitor.visitVarInsn(58, UsingJump.this.instrumentedMethod.getStackSize());
                    methodVisitor.visitVarInsn(58, UsingJump.this.instrumentedMethod.getStackSize() + 1);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize() + 1);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize());
                    methodVisitor.visitJumpInsn(198, UsingJump.this.secondValueNull);
                    methodVisitor.visitJumpInsn(198, UsingJump.this.firstValueNull);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize() + 1);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize());
                    return StackManipulation.Size.ZERO;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$NullValueGuard$UsingJump$AfterInstruction.class */
            protected class AfterInstruction extends StackManipulation.AbstractBase {
                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && UsingJump.this.equals(UsingJump.this);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + UsingJump.this.hashCode();
                }

                protected AfterInstruction() {
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    methodVisitor.visitJumpInsn(167, UsingJump.this.endOfBlock);
                    methodVisitor.visitLabel(UsingJump.this.secondValueNull);
                    context.getFrameGeneration().same1(methodVisitor, TypeDescription.ForLoadedType.of(Object.class), Arrays.asList(context.getInstrumentedType(), TypeDescription.ForLoadedType.of(Object.class)));
                    methodVisitor.visitJumpInsn(198, UsingJump.this.endOfBlock);
                    methodVisitor.visitLabel(UsingJump.this.firstValueNull);
                    context.getFrameGeneration().same(methodVisitor, Arrays.asList(context.getInstrumentedType(), TypeDescription.ForLoadedType.of(Object.class)));
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitInsn(172);
                    methodVisitor.visitLabel(UsingJump.this.endOfBlock);
                    context.getFrameGeneration().same(methodVisitor, Arrays.asList(context.getInstrumentedType(), TypeDescription.ForLoadedType.of(Object.class)));
                    return StackManipulation.Size.ZERO;
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$ValueComparator.class */
    protected enum ValueComparator implements StackManipulation {
        LONG { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.1
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(148);
                return new StackManipulation.Size(-2, 0);
            }
        },
        FLOAT { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.2
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/lang/Float", "compare", "(FF)I", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        DOUBLE { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.3
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/lang/Double", "compare", "(DD)I", false);
                return new StackManipulation.Size(-2, 0);
            }
        },
        BOOLEAN_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.4
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([Z[Z)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        BYTE_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.5
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([B[B)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        SHORT_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.6
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([S[S)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        CHARACTER_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.7
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([C[C)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        INTEGER_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.8
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([I[I)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        LONG_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.9
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([J[J)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        FLOAT_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.10
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([F[F)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        DOUBLE_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.11
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([D[D)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        REFERENCE_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.12
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        NESTED_ARRAY { // from class: net.bytebuddy.implementation.EqualsMethod.ValueComparator.13
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "deepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        };

        /* synthetic */ ValueComparator(byte b2) {
            this();
        }

        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
        public static StackManipulation of(TypeDefinition typeDefinition) {
            if (typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Byte.TYPE) || typeDefinition.represents(Short.TYPE) || typeDefinition.represents(Character.TYPE) || typeDefinition.represents(Integer.TYPE)) {
                return ConditionalReturn.onNonEqualInteger();
            }
            if (typeDefinition.represents(Long.TYPE)) {
                return new StackManipulation.Compound(LONG, ConditionalReturn.onNonZeroInteger());
            }
            if (typeDefinition.represents(Float.TYPE)) {
                return new StackManipulation.Compound(FLOAT, ConditionalReturn.onNonZeroInteger());
            }
            if (typeDefinition.represents(Double.TYPE)) {
                return new StackManipulation.Compound(DOUBLE, ConditionalReturn.onNonZeroInteger());
            }
            if (typeDefinition.represents(boolean[].class)) {
                return new StackManipulation.Compound(BOOLEAN_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(byte[].class)) {
                return new StackManipulation.Compound(BYTE_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(short[].class)) {
                return new StackManipulation.Compound(SHORT_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(char[].class)) {
                return new StackManipulation.Compound(CHARACTER_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(int[].class)) {
                return new StackManipulation.Compound(INTEGER_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(long[].class)) {
                return new StackManipulation.Compound(LONG_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(float[].class)) {
                return new StackManipulation.Compound(FLOAT_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.represents(double[].class)) {
                return new StackManipulation.Compound(DOUBLE_ARRAY, ConditionalReturn.onZeroInteger());
            }
            if (typeDefinition.isArray()) {
                StackManipulation[] stackManipulationArr = new StackManipulation[2];
                stackManipulationArr[0] = typeDefinition.getComponentType().isArray() ? NESTED_ARRAY : REFERENCE_ARRAY;
                stackManipulationArr[1] = ConditionalReturn.onZeroInteger();
                return new StackManipulation.Compound(stackManipulationArr);
            }
            return new StackManipulation.Compound(MethodInvocation.invoke(EqualsMethod.EQUALS).virtual(typeDefinition.asErasure()), ConditionalReturn.onZeroInteger());
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$Appender.class */
    protected static class Appender implements ByteCodeAppender {
        private final TypeDescription instrumentedType;
        private final StackManipulation baseline;
        private final List<FieldDescription.InDefinedShape> fieldDescriptions;
        private final ElementMatcher<? super FieldDescription.InDefinedShape> nonNullable;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Appender) obj).instrumentedType) && this.baseline.equals(((Appender) obj).baseline) && this.fieldDescriptions.equals(((Appender) obj).fieldDescriptions) && this.nonNullable.equals(((Appender) obj).nonNullable);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.baseline.hashCode()) * 31) + this.fieldDescriptions.hashCode()) * 31) + this.nonNullable.hashCode();
        }

        protected Appender(TypeDescription typeDescription, StackManipulation stackManipulation, List<FieldDescription.InDefinedShape> list, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
            this.instrumentedType = typeDescription;
            this.baseline = stackManipulation;
            this.fieldDescriptions = list;
            this.nonNullable = elementMatcher;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            if (methodDescription.isStatic()) {
                throw new IllegalStateException("Hash code method must not be static: " + methodDescription);
            }
            if (methodDescription.getParameters().size() != 1 || ((ParameterDescription) methodDescription.getParameters().getOnly()).getType().isPrimitive()) {
                throw new IllegalStateException();
            }
            if (!methodDescription.getReturnType().represents(Boolean.TYPE)) {
                throw new IllegalStateException("Hash code method does not return primitive boolean: " + methodDescription);
            }
            ArrayList arrayList = new ArrayList(3 + (this.fieldDescriptions.size() << 3));
            arrayList.add(this.baseline);
            int i = 0;
            for (FieldDescription.InDefinedShape inDefinedShape : this.fieldDescriptions) {
                arrayList.add(MethodVariableAccess.loadThis());
                arrayList.add(FieldAccess.forField(inDefinedShape).read());
                arrayList.add(MethodVariableAccess.REFERENCE.loadFrom(1));
                arrayList.add(TypeCasting.to(this.instrumentedType));
                arrayList.add(FieldAccess.forField(inDefinedShape).read());
                NullValueGuard usingJump = (inDefinedShape.getType().isPrimitive() || inDefinedShape.getType().isArray() || this.nonNullable.matches(inDefinedShape)) ? NullValueGuard.NoOp.INSTANCE : new NullValueGuard.UsingJump(methodDescription);
                arrayList.add(usingJump.before());
                arrayList.add(ValueComparator.of(inDefinedShape.getType()));
                arrayList.add(usingJump.after());
                i = Math.max(i, usingJump.getRequiredVariablePadding());
            }
            arrayList.add(IntegerConstant.forValue(true));
            arrayList.add(MethodReturn.INTEGER);
            return new ByteCodeAppender.Size(new StackManipulation.Compound(arrayList).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize() + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$ConditionalReturn.class */
    public static class ConditionalReturn extends StackManipulation.AbstractBase {
        private static final Object[] EMPTY = new Object[0];
        private final int jumpCondition;
        private final int value;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.jumpCondition == ((ConditionalReturn) obj).jumpCondition && this.value == ((ConditionalReturn) obj).value;
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.jumpCondition) * 31) + this.value;
        }

        protected ConditionalReturn(int i) {
            this(i, 3);
        }

        private ConditionalReturn(int i, int i2) {
            this.jumpCondition = i;
            this.value = i2;
        }

        protected static ConditionalReturn onZeroInteger() {
            return new ConditionalReturn(154);
        }

        protected static ConditionalReturn onNonZeroInteger() {
            return new ConditionalReturn(153);
        }

        protected static ConditionalReturn onNullValue() {
            return new ConditionalReturn(199);
        }

        protected static ConditionalReturn onNonIdentity() {
            return new ConditionalReturn(165);
        }

        protected static ConditionalReturn onIdentity() {
            return new ConditionalReturn(166);
        }

        protected static ConditionalReturn onNonEqualInteger() {
            return new ConditionalReturn(159);
        }

        protected StackManipulation returningTrue() {
            return new ConditionalReturn(this.jumpCondition, 4);
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            Label label = new Label();
            methodVisitor.visitJumpInsn(this.jumpCondition, label);
            methodVisitor.visitInsn(this.value);
            methodVisitor.visitInsn(172);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, Arrays.asList(context.getInstrumentedType(), TypeDescription.ForLoadedType.of(Object.class)));
            return new StackManipulation.Size(-1, 1);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$NaturalOrderComparator.class */
    protected enum NaturalOrderComparator implements Comparator<FieldDescription.InDefinedShape> {
        INSTANCE;

        @Override // java.util.Comparator
        public final int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            return 0;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$TypePropertyComparator.class */
    protected enum TypePropertyComparator implements Comparator<FieldDescription.InDefinedShape> {
        FOR_PRIMITIVE_TYPES { // from class: net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator.1
            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator, java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
                return super.compare(inDefinedShape, inDefinedShape2);
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator
            protected final boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.isPrimitive();
            }
        },
        FOR_ENUMERATION_TYPES { // from class: net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator.2
            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator, java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
                return super.compare(inDefinedShape, inDefinedShape2);
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator
            protected final boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.isEnum();
            }
        },
        FOR_STRING_TYPES { // from class: net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator.3
            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator, java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
                return super.compare(inDefinedShape, inDefinedShape2);
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator
            protected final boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.represents(String.class);
            }
        },
        FOR_PRIMITIVE_WRAPPER_TYPES { // from class: net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator.4
            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator, java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
                return super.compare(inDefinedShape, inDefinedShape2);
            }

            @Override // net.bytebuddy.implementation.EqualsMethod.TypePropertyComparator
            protected final boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.asErasure().isPrimitiveWrapper();
            }
        };

        protected abstract boolean resolve(TypeDefinition typeDefinition);

        /* synthetic */ TypePropertyComparator(byte b2) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            if (resolve(inDefinedShape.getType()) && !resolve(inDefinedShape2.getType())) {
                return -1;
            }
            if (!resolve(inDefinedShape.getType()) && resolve(inDefinedShape2.getType())) {
                return 1;
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(value = {"SE_COMPARATOR_SHOULD_BE_SERIALIZABLE"}, justification = "Not used within a serializable instance")
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/EqualsMethod$CompoundComparator.class */
    public static class CompoundComparator implements Comparator<FieldDescription.InDefinedShape> {
        private final List<Comparator<? super FieldDescription.InDefinedShape>> comparators;

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.comparators.hashCode();
        }

        @Override // java.util.Comparator
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.comparators.equals(((CompoundComparator) obj).comparators);
        }

        protected CompoundComparator(Comparator<? super FieldDescription.InDefinedShape>... comparatorArr) {
            this((List<? extends Comparator<? super FieldDescription.InDefinedShape>>) Arrays.asList(comparatorArr));
        }

        protected CompoundComparator(List<? extends Comparator<? super FieldDescription.InDefinedShape>> list) {
            this.comparators = new ArrayList();
            for (Comparator<? super FieldDescription.InDefinedShape> comparator : list) {
                if (comparator instanceof CompoundComparator) {
                    this.comparators.addAll(((CompoundComparator) comparator).comparators);
                } else if (!(comparator instanceof NaturalOrderComparator)) {
                    this.comparators.add(comparator);
                }
            }
        }

        @Override // java.util.Comparator
        public int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            Iterator<Comparator<? super FieldDescription.InDefinedShape>> it = this.comparators.iterator();
            while (it.hasNext()) {
                int compare = it.next().compare(inDefinedShape, inDefinedShape2);
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        }
    }
}
