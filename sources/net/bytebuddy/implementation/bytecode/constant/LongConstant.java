package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/LongConstant.class */
public enum LongConstant implements StackManipulation {
    ZERO(9),
    ONE(10);

    private static final StackManipulation.Size SIZE = StackSize.DOUBLE.toIncreasingSize();
    private final int opcode;

    LongConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(long j) {
        if (j == 0) {
            return ZERO;
        }
        if (j == 1) {
            return ONE;
        }
        return new ConstantPool(j);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public final boolean isValid() {
        return true;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/LongConstant$ConstantPool.class */
    protected static class ConstantPool extends StackManipulation.AbstractBase {
        private final long value;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((ConstantPool) obj).value;
        }

        public int hashCode() {
            int hashCode = getClass().hashCode() * 31;
            return hashCode + ((int) (hashCode ^ (this.value >>> 32)));
        }

        protected ConstantPool(long j) {
            this.value = j;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Long.valueOf(this.value));
            return LongConstant.SIZE;
        }
    }
}
