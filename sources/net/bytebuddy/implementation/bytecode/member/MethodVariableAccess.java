package net.bytebuddy.implementation.bytecode.member;

import java.util.ArrayList;
import java.util.Iterator;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess.class */
public enum MethodVariableAccess {
    INTEGER(21, 54, StackSize.SINGLE),
    LONG(22, 55, StackSize.DOUBLE),
    FLOAT(23, 56, StackSize.SINGLE),
    DOUBLE(24, 57, StackSize.DOUBLE),
    REFERENCE(25, 58, StackSize.SINGLE);

    private final int loadOpcode;
    private final int storeOpcode;
    private final StackSize size;
    private static /* synthetic */ StackManipulation loadThis;

    MethodVariableAccess(int i, int i2, StackSize stackSize) {
        this.loadOpcode = i;
        this.size = stackSize;
        this.storeOpcode = i2;
    }

    public static MethodVariableAccess of(TypeDefinition typeDefinition) {
        if (typeDefinition.isPrimitive()) {
            if (typeDefinition.represents(Long.TYPE)) {
                return LONG;
            }
            if (typeDefinition.represents(Double.TYPE)) {
                return DOUBLE;
            }
            if (typeDefinition.represents(Float.TYPE)) {
                return FLOAT;
            }
            if (typeDefinition.represents(Void.TYPE)) {
                throw new IllegalArgumentException("Variable type cannot be void");
            }
            return INTEGER;
        }
        return REFERENCE;
    }

    public static MethodLoading allArgumentsOf(MethodDescription methodDescription) {
        return new MethodLoading(methodDescription, MethodLoading.TypeCastingHandler.NoOp.INSTANCE);
    }

    @CachedReturnPlugin.Enhance("loadThis")
    public static StackManipulation loadThis() {
        StackManipulation loadFrom = loadThis != null ? null : REFERENCE.loadFrom(0);
        StackManipulation stackManipulation = loadFrom;
        if (loadFrom == null) {
            stackManipulation = loadThis;
        } else {
            loadThis = stackManipulation;
        }
        return stackManipulation;
    }

    public final StackManipulation loadFrom(int i) {
        return new OffsetLoading(i);
    }

    public final StackManipulation storeAt(int i) {
        return new OffsetWriting(i);
    }

    public final StackManipulation increment(int i, int i2) {
        if (this != INTEGER) {
            throw new IllegalStateException("Cannot increment type: " + this);
        }
        return new OffsetIncrementing(i, i2);
    }

    public static StackManipulation load(ParameterDescription parameterDescription) {
        return of(parameterDescription.getType()).loadFrom(parameterDescription.getOffset());
    }

    public static StackManipulation store(ParameterDescription parameterDescription) {
        return of(parameterDescription.getType()).storeAt(parameterDescription.getOffset());
    }

    public static StackManipulation increment(ParameterDescription parameterDescription, int i) {
        return of(parameterDescription.getType()).increment(parameterDescription.getOffset(), i);
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$MethodLoading.class */
    public static class MethodLoading extends StackManipulation.AbstractBase {
        private final MethodDescription methodDescription;
        private final TypeCastingHandler typeCastingHandler;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((MethodLoading) obj).methodDescription) && this.typeCastingHandler.equals(((MethodLoading) obj).typeCastingHandler);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.typeCastingHandler.hashCode();
        }

        protected MethodLoading(MethodDescription methodDescription, TypeCastingHandler typeCastingHandler) {
            this.methodDescription = methodDescription;
            this.typeCastingHandler = typeCastingHandler;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.methodDescription.getParameters().iterator();
            while (it.hasNext()) {
                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                TypeDescription asErasure = parameterDescription.getType().asErasure();
                arrayList.add(MethodVariableAccess.of(asErasure).loadFrom(parameterDescription.getOffset()));
                arrayList.add(this.typeCastingHandler.ofIndex(asErasure, parameterDescription.getIndex()));
            }
            return new StackManipulation.Compound(arrayList).apply(methodVisitor, context);
        }

        public StackManipulation prependThisReference() {
            return this.methodDescription.isStatic() ? this : new StackManipulation.Compound(MethodVariableAccess.loadThis(), this);
        }

        public MethodLoading asBridgeOf(MethodDescription methodDescription) {
            return new MethodLoading(this.methodDescription, new TypeCastingHandler.ForBridgeTarget(methodDescription));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$MethodLoading$TypeCastingHandler.class */
        public interface TypeCastingHandler {
            StackManipulation ofIndex(TypeDescription typeDescription, int i);

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$MethodLoading$TypeCastingHandler$NoOp.class */
            public enum NoOp implements TypeCastingHandler {
                INSTANCE;

                @Override // net.bytebuddy.implementation.bytecode.member.MethodVariableAccess.MethodLoading.TypeCastingHandler
                public final StackManipulation ofIndex(TypeDescription typeDescription, int i) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$MethodLoading$TypeCastingHandler$ForBridgeTarget.class */
            public static class ForBridgeTarget implements TypeCastingHandler {
                private final MethodDescription bridgeTarget;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.bridgeTarget.equals(((ForBridgeTarget) obj).bridgeTarget);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.bridgeTarget.hashCode();
                }

                public ForBridgeTarget(MethodDescription methodDescription) {
                    this.bridgeTarget = methodDescription;
                }

                @Override // net.bytebuddy.implementation.bytecode.member.MethodVariableAccess.MethodLoading.TypeCastingHandler
                public StackManipulation ofIndex(TypeDescription typeDescription, int i) {
                    TypeDescription asErasure = ((ParameterDescription) this.bridgeTarget.getParameters().get(i)).getType().asErasure();
                    return typeDescription.equals(asErasure) ? StackManipulation.Trivial.INSTANCE : TypeCasting.to(asErasure);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$OffsetLoading.class */
    public class OffsetLoading extends StackManipulation.AbstractBase {
        private final int offset;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.offset == ((OffsetLoading) obj).offset && MethodVariableAccess.this.equals(MethodVariableAccess.this);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.offset) * 31) + MethodVariableAccess.this.hashCode();
        }

        protected OffsetLoading(int i) {
            this.offset = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitVarInsn(MethodVariableAccess.this.loadOpcode, this.offset);
            return MethodVariableAccess.this.size.toIncreasingSize();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$OffsetWriting.class */
    public class OffsetWriting extends StackManipulation.AbstractBase {
        private final int offset;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.offset == ((OffsetWriting) obj).offset && MethodVariableAccess.this.equals(MethodVariableAccess.this);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.offset) * 31) + MethodVariableAccess.this.hashCode();
        }

        protected OffsetWriting(int i) {
            this.offset = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitVarInsn(MethodVariableAccess.this.storeOpcode, this.offset);
            return MethodVariableAccess.this.size.toDecreasingSize();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodVariableAccess$OffsetIncrementing.class */
    public static class OffsetIncrementing extends StackManipulation.AbstractBase {
        private final int offset;
        private final int value;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.offset == ((OffsetIncrementing) obj).offset && this.value == ((OffsetIncrementing) obj).value;
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.offset) * 31) + this.value;
        }

        protected OffsetIncrementing(int i, int i2) {
            this.offset = i;
            this.value = i2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitIincInsn(this.offset, this.value);
            return StackManipulation.Size.ZERO;
        }
    }
}
