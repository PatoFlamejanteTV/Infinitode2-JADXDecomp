package net.bytebuddy.implementation.bytecode.member;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess.class */
public enum FieldAccess {
    STATIC(179, 178, StackSize.ZERO),
    INSTANCE(181, 180, StackSize.SINGLE);

    private final int putterOpcode;
    private final int getterOpcode;
    private final int targetSizeChange;

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess$Defined.class */
    public interface Defined {
        StackManipulation read();

        StackManipulation write();
    }

    FieldAccess(int i, int i2, StackSize stackSize) {
        this.putterOpcode = i;
        this.getterOpcode = i2;
        this.targetSizeChange = stackSize.getSize();
    }

    public static StackManipulation forEnumeration(EnumerationDescription enumerationDescription) {
        FieldList filter = enumerationDescription.getEnumerationType().getDeclaredFields().filter(ElementMatchers.named(enumerationDescription.getValue()));
        if (filter.size() != 1 || !((FieldDescription.InDefinedShape) filter.getOnly()).isStatic() || !((FieldDescription.InDefinedShape) filter.getOnly()).isPublic() || !((FieldDescription.InDefinedShape) filter.getOnly()).isEnum()) {
            return StackManipulation.Illegal.INSTANCE;
        }
        FieldAccess fieldAccess = STATIC;
        fieldAccess.getClass();
        return new AccessDispatcher((FieldDescription.InDefinedShape) filter.getOnly()).read();
    }

    public static Defined forField(FieldDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isStatic()) {
            FieldAccess fieldAccess = STATIC;
            fieldAccess.getClass();
            return new AccessDispatcher(inDefinedShape);
        }
        FieldAccess fieldAccess2 = INSTANCE;
        fieldAccess2.getClass();
        return new AccessDispatcher(inDefinedShape);
    }

    public static Defined forField(FieldDescription fieldDescription) {
        FieldDescription.InDefinedShape asDefined = fieldDescription.asDefined();
        if (fieldDescription.getType().asErasure().equals(asDefined.getType().asErasure())) {
            return forField(asDefined);
        }
        return OfGenericField.of(fieldDescription, forField(asDefined));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess$OfGenericField.class */
    public static class OfGenericField implements Defined {
        private final TypeDefinition targetType;
        private final Defined defined;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.targetType.equals(((OfGenericField) obj).targetType) && this.defined.equals(((OfGenericField) obj).defined);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.targetType.hashCode()) * 31) + this.defined.hashCode();
        }

        protected OfGenericField(TypeDefinition typeDefinition, Defined defined) {
            this.targetType = typeDefinition;
            this.defined = defined;
        }

        protected static Defined of(FieldDescription fieldDescription, Defined defined) {
            return new OfGenericField(fieldDescription.getType(), defined);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation read() {
            return new StackManipulation.Compound(this.defined.read(), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation write() {
            return this.defined.write();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess$AccessDispatcher.class */
    public class AccessDispatcher implements Defined {
        private final FieldDescription.InDefinedShape fieldDescription;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && FieldAccess.this.equals(FieldAccess.this) && this.fieldDescription.equals(((AccessDispatcher) obj).fieldDescription);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.fieldDescription.hashCode()) * 31) + FieldAccess.this.hashCode();
        }

        protected AccessDispatcher(FieldDescription.InDefinedShape inDefinedShape) {
            this.fieldDescription = inDefinedShape;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation read() {
            return new FieldGetInstruction();
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation write() {
            return new FieldPutInstruction();
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess$AccessDispatcher$AbstractFieldInstruction.class */
        private abstract class AbstractFieldInstruction extends StackManipulation.AbstractBase {
            protected abstract int getOpcode();

            protected abstract StackManipulation.Size resolveSize(StackSize stackSize);

            private AbstractFieldInstruction() {
            }

            /* synthetic */ AbstractFieldInstruction(AccessDispatcher accessDispatcher, byte b2) {
                this();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitFieldInsn(getOpcode(), AccessDispatcher.this.fieldDescription.getDeclaringType().getInternalName(), AccessDispatcher.this.fieldDescription.getInternalName(), AccessDispatcher.this.fieldDescription.getDescriptor());
                return resolveSize(AccessDispatcher.this.fieldDescription.getType().getStackSize());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess$AccessDispatcher$FieldGetInstruction.class */
        public class FieldGetInstruction extends AbstractFieldInstruction {
            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && AccessDispatcher.this.equals(AccessDispatcher.this);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + AccessDispatcher.this.hashCode();
            }

            protected FieldGetInstruction() {
                super(AccessDispatcher.this, (byte) 0);
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected int getOpcode() {
                return FieldAccess.this.getterOpcode;
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected StackManipulation.Size resolveSize(StackSize stackSize) {
                int size = stackSize.getSize() - FieldAccess.this.targetSizeChange;
                return new StackManipulation.Size(size, size);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/FieldAccess$AccessDispatcher$FieldPutInstruction.class */
        protected class FieldPutInstruction extends AbstractFieldInstruction {
            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && AccessDispatcher.this.equals(AccessDispatcher.this);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + AccessDispatcher.this.hashCode();
            }

            protected FieldPutInstruction() {
                super(AccessDispatcher.this, (byte) 0);
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected int getOpcode() {
                return FieldAccess.this.putterOpcode;
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected StackManipulation.Size resolveSize(StackSize stackSize) {
                return new StackManipulation.Size((-1) * (stackSize.getSize() + FieldAccess.this.targetSizeChange), 0);
            }
        }
    }
}
