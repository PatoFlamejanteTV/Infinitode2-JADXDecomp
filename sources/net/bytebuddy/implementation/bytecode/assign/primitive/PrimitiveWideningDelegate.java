package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.nullability.MaybeNull;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'BOOLEAN' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/primitive/PrimitiveWideningDelegate.class */
public final class PrimitiveWideningDelegate {
    public static final PrimitiveWideningDelegate BOOLEAN;
    public static final PrimitiveWideningDelegate BYTE;
    public static final PrimitiveWideningDelegate SHORT;
    public static final PrimitiveWideningDelegate CHARACTER;
    public static final PrimitiveWideningDelegate INTEGER;
    public static final PrimitiveWideningDelegate LONG;
    public static final PrimitiveWideningDelegate FLOAT;
    public static final PrimitiveWideningDelegate DOUBLE;
    private final StackManipulation toBooleanStackManipulation;
    private final StackManipulation toByteStackManipulation;
    private final StackManipulation toShortStackManipulation;
    private final StackManipulation toCharacterStackManipulation;
    private final StackManipulation toIntegerStackManipulation;
    private final StackManipulation toLongStackManipulation;
    private final StackManipulation toFloatStackManipulation;
    private final StackManipulation toDoubleStackManipulation;
    private static final /* synthetic */ PrimitiveWideningDelegate[] $VALUES;

    public static PrimitiveWideningDelegate[] values() {
        return (PrimitiveWideningDelegate[]) $VALUES.clone();
    }

    public static PrimitiveWideningDelegate valueOf(String str) {
        return (PrimitiveWideningDelegate) Enum.valueOf(PrimitiveWideningDelegate.class, str);
    }

    static {
        StackManipulation.Trivial trivial = StackManipulation.Trivial.INSTANCE;
        StackManipulation.Illegal illegal = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal2 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal3 = StackManipulation.Illegal.INSTANCE;
        BOOLEAN = new PrimitiveWideningDelegate("BOOLEAN", 0, trivial, illegal, illegal, illegal2, illegal2, illegal3, illegal3, StackManipulation.Illegal.INSTANCE);
        StackManipulation.Illegal illegal4 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Trivial trivial2 = StackManipulation.Trivial.INSTANCE;
        BYTE = new PrimitiveWideningDelegate("BYTE", 1, illegal4, trivial2, trivial2, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation.AbstractBase(133, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(134, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(133, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        StackManipulation.Illegal illegal5 = StackManipulation.Illegal.INSTANCE;
        SHORT = new PrimitiveWideningDelegate("SHORT", 2, illegal5, illegal5, StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation.AbstractBase(133, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(134, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(135, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        StackManipulation.Illegal illegal6 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal7 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Trivial trivial3 = StackManipulation.Trivial.INSTANCE;
        CHARACTER = new PrimitiveWideningDelegate("CHARACTER", 3, illegal6, illegal6, illegal7, trivial3, trivial3, new StackManipulation.AbstractBase(133, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(134, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(135, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        StackManipulation.Illegal illegal8 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal9 = StackManipulation.Illegal.INSTANCE;
        INTEGER = new PrimitiveWideningDelegate("INTEGER", 4, illegal8, illegal8, illegal9, illegal9, StackManipulation.Trivial.INSTANCE, new StackManipulation.AbstractBase(133, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(134, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(135, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        StackManipulation.Illegal illegal10 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal11 = StackManipulation.Illegal.INSTANCE;
        LONG = new PrimitiveWideningDelegate("LONG", 5, illegal10, illegal10, illegal11, illegal11, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation.AbstractBase(137, StackSize.SINGLE.toDecreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation.AbstractBase(138, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        StackManipulation.Illegal illegal12 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal13 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal14 = StackManipulation.Illegal.INSTANCE;
        FLOAT = new PrimitiveWideningDelegate("FLOAT", 6, illegal12, illegal12, illegal13, illegal13, illegal14, illegal14, StackManipulation.Trivial.INSTANCE, new StackManipulation.AbstractBase(141, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.conversionOpcode == ((WideningStackManipulation) obj).conversionOpcode && this.size.equals(((WideningStackManipulation) obj).size);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.conversionOpcode) * 31) + this.size.hashCode();
            }

            {
                this.conversionOpcode = r4;
                this.size = r5;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        StackManipulation.Illegal illegal15 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal16 = StackManipulation.Illegal.INSTANCE;
        StackManipulation.Illegal illegal17 = StackManipulation.Illegal.INSTANCE;
        DOUBLE = new PrimitiveWideningDelegate("DOUBLE", 7, illegal15, illegal15, illegal16, illegal16, illegal17, illegal17, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE);
        $VALUES = new PrimitiveWideningDelegate[]{BOOLEAN, BYTE, SHORT, CHARACTER, INTEGER, LONG, FLOAT, DOUBLE};
    }

    private PrimitiveWideningDelegate(String str, int i, StackManipulation stackManipulation, StackManipulation stackManipulation2, StackManipulation stackManipulation3, StackManipulation stackManipulation4, StackManipulation stackManipulation5, StackManipulation stackManipulation6, StackManipulation stackManipulation7, StackManipulation stackManipulation8) {
        this.toBooleanStackManipulation = stackManipulation;
        this.toByteStackManipulation = stackManipulation2;
        this.toShortStackManipulation = stackManipulation3;
        this.toCharacterStackManipulation = stackManipulation4;
        this.toIntegerStackManipulation = stackManipulation5;
        this.toLongStackManipulation = stackManipulation6;
        this.toFloatStackManipulation = stackManipulation7;
        this.toDoubleStackManipulation = stackManipulation8;
    }

    public static PrimitiveWideningDelegate forPrimitive(TypeDefinition typeDefinition) {
        if (typeDefinition.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return SHORT;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return DOUBLE;
        }
        throw new IllegalArgumentException("Not a primitive, non-void type: " + typeDefinition);
    }

    public final StackManipulation widenTo(TypeDefinition typeDefinition) {
        if (typeDefinition.represents(Boolean.TYPE)) {
            return this.toBooleanStackManipulation;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return this.toByteStackManipulation;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return this.toShortStackManipulation;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return this.toCharacterStackManipulation;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return this.toIntegerStackManipulation;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return this.toLongStackManipulation;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return this.toFloatStackManipulation;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return this.toDoubleStackManipulation;
        }
        throw new IllegalArgumentException("Not a primitive non-void type: " + typeDefinition);
    }
}
