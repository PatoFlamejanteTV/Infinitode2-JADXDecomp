package net.bytebuddy.implementation.bytecode.constant;

import java.util.Iterator;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/JavaConstantValue.class */
public class JavaConstantValue extends StackManipulation.AbstractBase {
    private final JavaConstant constant;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.constant.equals(((JavaConstantValue) obj).constant);
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.constant.hashCode();
    }

    public JavaConstantValue(JavaConstant javaConstant) {
        this.constant = javaConstant;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitLdcInsn(this.constant.accept(Visitor.INSTANCE));
        return this.constant.getTypeDescription().getStackSize().toIncreasingSize();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/JavaConstantValue$Visitor.class */
    public enum Visitor implements JavaConstant.Visitor<Object> {
        INSTANCE;

        @Override // net.bytebuddy.utility.JavaConstant.Visitor
        /* renamed from: onType, reason: avoid collision after fix types in other method */
        public final /* bridge */ /* synthetic */ Object onType2(JavaConstant.Simple simple) {
            return onType((JavaConstant.Simple<TypeDescription>) simple);
        }

        @Override // net.bytebuddy.utility.JavaConstant.Visitor
        public final Object onValue(JavaConstant.Simple<?> simple) {
            return simple.getValue();
        }

        @Override // net.bytebuddy.utility.JavaConstant.Visitor
        public final Object onType(JavaConstant.Simple<TypeDescription> simple) {
            return Type.getType(simple.getValue().getDescriptor());
        }

        @Override // net.bytebuddy.utility.JavaConstant.Visitor
        /* renamed from: onMethodType, reason: merged with bridge method [inline-methods] */
        public final Object onMethodType2(JavaConstant.MethodType methodType) {
            StringBuilder sb = new StringBuilder("(");
            Iterator it = methodType.getParameterTypes().iterator();
            while (it.hasNext()) {
                sb.append(((TypeDescription) it.next()).getDescriptor());
            }
            return Type.getMethodType(sb.append(')').append(methodType.getReturnType().getDescriptor()).toString());
        }

        @Override // net.bytebuddy.utility.JavaConstant.Visitor
        /* renamed from: onMethodHandle, reason: merged with bridge method [inline-methods] */
        public final Object onMethodHandle2(JavaConstant.MethodHandle methodHandle) {
            return new Handle(methodHandle.getHandleType().getIdentifier(), methodHandle.getOwnerType().getInternalName(), methodHandle.getName(), methodHandle.getDescriptor(), methodHandle.getOwnerType().isInterface());
        }

        @Override // net.bytebuddy.utility.JavaConstant.Visitor
        /* renamed from: onDynamic, reason: merged with bridge method [inline-methods] */
        public final Object onDynamic2(JavaConstant.Dynamic dynamic) {
            Object[] objArr = new Object[dynamic.getArguments().size()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = dynamic.getArguments().get(i).accept(this);
            }
            return new ConstantDynamic(dynamic.getName(), dynamic.getTypeDescription().getDescriptor(), onMethodHandle2(dynamic.getBootstrap()), objArr);
        }
    }
}
