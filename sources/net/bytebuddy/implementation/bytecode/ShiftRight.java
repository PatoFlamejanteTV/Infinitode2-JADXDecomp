package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/ShiftRight.class */
public enum ShiftRight implements StackManipulation {
    INTEGER(122, StackSize.SINGLE, Unsigned.INTEGER),
    LONG(123, StackSize.DOUBLE, Unsigned.LONG);

    private final int opcode;
    private final StackSize stackSize;
    private final StackManipulation unsigned;

    ShiftRight(int i, StackSize stackSize, StackManipulation stackManipulation) {
        this.opcode = i;
        this.stackSize = stackSize;
        this.unsigned = stackManipulation;
    }

    public final StackManipulation toUnsigned() {
        return this.unsigned;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public final boolean isValid() {
        return true;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return this.stackSize.toDecreasingSize();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/ShiftRight$Unsigned.class */
    protected enum Unsigned implements StackManipulation {
        INTEGER(124, StackSize.SINGLE),
        LONG(125, StackSize.DOUBLE);

        private final int opcode;
        private final StackSize stackSize;

        Unsigned(int i, StackSize stackSize) {
            this.opcode = i;
            this.stackSize = stackSize;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final boolean isValid() {
            return true;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitInsn(this.opcode);
            return this.stackSize.toDecreasingSize();
        }
    }
}
