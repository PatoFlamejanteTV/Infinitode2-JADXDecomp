package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/Duplication.class */
public enum Duplication implements StackManipulation {
    ZERO(StackSize.ZERO, 0) { // from class: net.bytebuddy.implementation.bytecode.Duplication.1
        @Override // net.bytebuddy.implementation.bytecode.Duplication, net.bytebuddy.implementation.bytecode.StackManipulation
        public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.size;
        }

        @Override // net.bytebuddy.implementation.bytecode.Duplication
        public final StackManipulation flipOver(TypeDefinition typeDefinition) {
            throw new IllegalStateException("Cannot flip zero value");
        }
    },
    SINGLE(StackSize.SINGLE, 89) { // from class: net.bytebuddy.implementation.bytecode.Duplication.2
        @Override // net.bytebuddy.implementation.bytecode.Duplication
        public final StackManipulation flipOver(TypeDefinition typeDefinition) {
            switch (AnonymousClass4.f4117a[typeDefinition.getStackSize().ordinal()]) {
                case 1:
                    return WithFlip.SINGLE_SINGLE;
                case 2:
                    return WithFlip.SINGLE_DOUBLE;
                default:
                    throw new IllegalArgumentException("Cannot flip: " + typeDefinition);
            }
        }
    },
    DOUBLE(StackSize.DOUBLE, 92) { // from class: net.bytebuddy.implementation.bytecode.Duplication.3
        @Override // net.bytebuddy.implementation.bytecode.Duplication
        public final StackManipulation flipOver(TypeDefinition typeDefinition) {
            switch (AnonymousClass4.f4117a[typeDefinition.getStackSize().ordinal()]) {
                case 1:
                    return WithFlip.DOUBLE_SINGLE;
                case 2:
                    return WithFlip.DOUBLE_DOUBLE;
                default:
                    throw new IllegalArgumentException("Cannot flip: " + typeDefinition);
            }
        }
    };

    protected final StackManipulation.Size size;
    private final int opcode;

    public abstract StackManipulation flipOver(TypeDefinition typeDefinition);

    /* synthetic */ Duplication(StackSize stackSize, int i, byte b2) {
        this(stackSize, i);
    }

    Duplication(StackSize stackSize, int i) {
        this.size = stackSize.toIncreasingSize();
        this.opcode = i;
    }

    public static Duplication of(TypeDefinition typeDefinition) {
        switch (typeDefinition.getStackSize()) {
            case SINGLE:
                return SINGLE;
            case DOUBLE:
                return DOUBLE;
            case ZERO:
                return ZERO;
            default:
                throw new AssertionError("Unexpected type: " + typeDefinition);
        }
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return this.size;
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/Duplication$WithFlip.class */
    protected enum WithFlip implements StackManipulation {
        SINGLE_SINGLE(90, StackSize.SINGLE),
        SINGLE_DOUBLE(91, StackSize.SINGLE),
        DOUBLE_SINGLE(93, StackSize.DOUBLE),
        DOUBLE_DOUBLE(94, StackSize.DOUBLE);

        private final int opcode;
        private final StackSize stackSize;

        WithFlip(int i, StackSize stackSize) {
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
            return this.stackSize.toIncreasingSize();
        }
    }
}
