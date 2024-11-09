package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/ShiftLeft.class */
public enum ShiftLeft implements StackManipulation {
    INTEGER(120, StackSize.SINGLE),
    LONG(121, StackSize.DOUBLE);

    private final int opcode;
    private final StackSize stackSize;

    ShiftLeft(int i, StackSize stackSize) {
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
