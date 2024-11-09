package net.bytebuddy.implementation.bytecode.member;

import java.util.Iterator;
import java.util.List;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation.class */
public enum MethodInvocation {
    VIRTUAL(182, 5, 182, 5),
    INTERFACE(185, 9, 185, 9),
    STATIC(184, 6, 184, 6),
    SPECIAL(183, 7, 183, 7),
    SPECIAL_CONSTRUCTOR(183, 8, 183, 8),
    VIRTUAL_PRIVATE(182, 5, 183, 7),
    INTERFACE_PRIVATE(185, 9, 183, 7);

    private final int opcode;
    private final int handle;
    private final int legacyOpcode;
    private final int legacyHandle;

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$WithImplicitInvocationTargetType.class */
    public interface WithImplicitInvocationTargetType extends StackManipulation {
        StackManipulation virtual(TypeDescription typeDescription);

        StackManipulation special(TypeDescription typeDescription);

        StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<? extends JavaConstant> list2);

        StackManipulation onHandle(HandleType handleType);
    }

    MethodInvocation(int i, int i2, int i3, int i4) {
        this.opcode = i;
        this.handle = i2;
        this.legacyOpcode = i3;
        this.legacyHandle = i4;
    }

    public static WithImplicitInvocationTargetType invoke(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return IllegalInvocation.INSTANCE;
        }
        if (inDefinedShape.isStatic()) {
            MethodInvocation methodInvocation = STATIC;
            methodInvocation.getClass();
            return new Invocation(methodInvocation, inDefinedShape);
        }
        if (inDefinedShape.isConstructor()) {
            MethodInvocation methodInvocation2 = SPECIAL_CONSTRUCTOR;
            methodInvocation2.getClass();
            return new Invocation(methodInvocation2, inDefinedShape);
        }
        if (inDefinedShape.isPrivate()) {
            MethodInvocation methodInvocation3 = inDefinedShape.getDeclaringType().isInterface() ? INTERFACE_PRIVATE : VIRTUAL_PRIVATE;
            methodInvocation3.getClass();
            return new Invocation(methodInvocation3, inDefinedShape);
        }
        if (inDefinedShape.getDeclaringType().isInterface()) {
            MethodInvocation methodInvocation4 = INTERFACE;
            methodInvocation4.getClass();
            return new Invocation(methodInvocation4, inDefinedShape);
        }
        MethodInvocation methodInvocation5 = VIRTUAL;
        methodInvocation5.getClass();
        return new Invocation(methodInvocation5, inDefinedShape);
    }

    public static WithImplicitInvocationTargetType invoke(MethodDescription methodDescription) {
        MethodDescription.InDefinedShape asDefined = methodDescription.asDefined();
        if (asDefined.getReturnType().asErasure().equals(methodDescription.getReturnType().asErasure())) {
            return invoke(asDefined);
        }
        return OfGenericMethod.of(methodDescription, invoke(asDefined));
    }

    public static StackManipulation lookup() {
        return invoke((MethodDescription.InDefinedShape) new MethodDescription.Latent(JavaType.METHOD_HANDLES.getTypeStub(), new MethodDescription.Token("lookup", 9, JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().asGenericType())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$IllegalInvocation.class */
    public enum IllegalInvocation implements WithImplicitInvocationTargetType {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public final StackManipulation virtual(TypeDescription typeDescription) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public final StackManipulation special(TypeDescription typeDescription) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public final StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<? extends JavaConstant> list2) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public final StackManipulation onHandle(HandleType handleType) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final boolean isValid() {
            return false;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackManipulation.Illegal.INSTANCE.apply(methodVisitor, context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$OfGenericMethod.class */
    public static class OfGenericMethod implements WithImplicitInvocationTargetType {
        private final TypeDescription targetType;
        private final WithImplicitInvocationTargetType invocation;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.targetType.equals(((OfGenericMethod) obj).targetType) && this.invocation.equals(((OfGenericMethod) obj).invocation);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.targetType.hashCode()) * 31) + this.invocation.hashCode();
        }

        protected OfGenericMethod(TypeDescription typeDescription, WithImplicitInvocationTargetType withImplicitInvocationTargetType) {
            this.targetType = typeDescription;
            this.invocation = withImplicitInvocationTargetType;
        }

        protected static WithImplicitInvocationTargetType of(MethodDescription methodDescription, WithImplicitInvocationTargetType withImplicitInvocationTargetType) {
            return new OfGenericMethod(methodDescription.getReturnType().asErasure(), withImplicitInvocationTargetType);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation virtual(TypeDescription typeDescription) {
            return new StackManipulation.Compound(this.invocation.virtual(typeDescription), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation special(TypeDescription typeDescription) {
            return new StackManipulation.Compound(this.invocation.special(typeDescription), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<? extends JavaConstant> list2) {
            return this.invocation.dynamic(str, typeDescription, list, list2);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation onHandle(HandleType handleType) {
            return new StackManipulation.Compound(this.invocation.onHandle(handleType), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.invocation.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return new StackManipulation.Compound(this.invocation, TypeCasting.to(this.targetType)).apply(methodVisitor, context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$Invocation.class */
    public class Invocation extends StackManipulation.AbstractBase implements WithImplicitInvocationTargetType {
        private final TypeDescription typeDescription;
        private final MethodDescription.InDefinedShape methodDescription;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && MethodInvocation.this.equals(MethodInvocation.this) && this.typeDescription.equals(((Invocation) obj).typeDescription) && this.methodDescription.equals(((Invocation) obj).methodDescription);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.typeDescription.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + MethodInvocation.this.hashCode();
        }

        protected Invocation(MethodInvocation methodInvocation, MethodDescription.InDefinedShape inDefinedShape) {
            this(inDefinedShape, inDefinedShape.getDeclaringType());
        }

        protected Invocation(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
            this.typeDescription = typeDescription;
            this.methodDescription = inDefinedShape;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitMethodInsn((MethodInvocation.this.opcode == MethodInvocation.this.legacyOpcode || context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V11)) ? MethodInvocation.this.opcode : MethodInvocation.this.legacyOpcode, this.typeDescription.getInternalName(), this.methodDescription.getInternalName(), this.methodDescription.getDescriptor(), this.typeDescription.isInterface());
            int stackSize = this.methodDescription.getStackSize();
            int size = this.methodDescription.getReturnType().getStackSize().getSize();
            return new StackManipulation.Size(size - stackSize, Math.max(0, size - stackSize));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation virtual(TypeDescription typeDescription) {
            if (this.methodDescription.isConstructor() || this.methodDescription.isStatic()) {
                return StackManipulation.Illegal.INSTANCE;
            }
            if (this.methodDescription.isPrivate()) {
                return this.methodDescription.getDeclaringType().equals(typeDescription) ? this : StackManipulation.Illegal.INSTANCE;
            }
            if (typeDescription.isInterface()) {
                if (this.methodDescription.getDeclaringType().represents(Object.class)) {
                    return this;
                }
                MethodInvocation methodInvocation = MethodInvocation.INTERFACE;
                methodInvocation.getClass();
                return new Invocation(this.methodDescription, typeDescription);
            }
            MethodInvocation methodInvocation2 = MethodInvocation.VIRTUAL;
            methodInvocation2.getClass();
            return new Invocation(this.methodDescription, typeDescription);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation special(TypeDescription typeDescription) {
            if (!this.methodDescription.isSpecializableFor(typeDescription)) {
                return StackManipulation.Illegal.INSTANCE;
            }
            MethodInvocation methodInvocation = MethodInvocation.SPECIAL;
            methodInvocation.getClass();
            return new Invocation(this.methodDescription, typeDescription);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<? extends JavaConstant> list2) {
            return this.methodDescription.isInvokeBootstrap() ? new DynamicInvocation(str, typeDescription, new TypeList.Explicit(list), this.methodDescription.asDefined(), list2) : StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation onHandle(HandleType handleType) {
            return new HandleInvocation(this.methodDescription, handleType);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$DynamicInvocation.class */
    protected class DynamicInvocation extends StackManipulation.AbstractBase {
        private final String methodName;
        private final TypeDescription returnType;
        private final List<? extends TypeDescription> parameterTypes;
        private final MethodDescription.InDefinedShape bootstrapMethod;
        private final List<? extends JavaConstant> arguments;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && MethodInvocation.this.equals(MethodInvocation.this) && this.methodName.equals(((DynamicInvocation) obj).methodName) && this.returnType.equals(((DynamicInvocation) obj).returnType) && this.parameterTypes.equals(((DynamicInvocation) obj).parameterTypes) && this.bootstrapMethod.equals(((DynamicInvocation) obj).bootstrapMethod) && this.arguments.equals(((DynamicInvocation) obj).arguments);
        }

        public int hashCode() {
            return (((((((((((getClass().hashCode() * 31) + this.methodName.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode()) * 31) + this.bootstrapMethod.hashCode()) * 31) + this.arguments.hashCode()) * 31) + MethodInvocation.this.hashCode();
        }

        public DynamicInvocation(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, MethodDescription.InDefinedShape inDefinedShape, List<? extends JavaConstant> list2) {
            this.methodName = str;
            this.returnType = typeDescription;
            this.parameterTypes = list;
            this.bootstrapMethod = inDefinedShape;
            this.arguments = list2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            String sb2 = sb.append(')').append(this.returnType.getDescriptor()).toString();
            Object[] objArr = new Object[this.arguments.size()];
            int i = 0;
            Iterator<? extends JavaConstant> it2 = this.arguments.iterator();
            while (it2.hasNext()) {
                int i2 = i;
                i++;
                objArr[i2] = it2.next().accept(JavaConstantValue.Visitor.INSTANCE);
            }
            methodVisitor.visitInvokeDynamicInsn(this.methodName, sb2, new Handle((MethodInvocation.this.handle == MethodInvocation.this.legacyHandle || context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V11)) ? MethodInvocation.this.handle : MethodInvocation.this.legacyHandle, this.bootstrapMethod.getDeclaringType().getInternalName(), this.bootstrapMethod.getInternalName(), this.bootstrapMethod.getDescriptor(), this.bootstrapMethod.getDeclaringType().isInterface()), objArr);
            int size = this.returnType.getStackSize().getSize() - StackSize.of(this.parameterTypes);
            return new StackManipulation.Size(size, Math.max(size, 0));
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$HandleInvocation.class */
    protected static class HandleInvocation extends StackManipulation.AbstractBase {
        private static final String METHOD_HANDLE = "java/lang/invoke/MethodHandle";
        private final MethodDescription.InDefinedShape methodDescription;
        private final HandleType type;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.type.equals(((HandleInvocation) obj).type) && this.methodDescription.equals(((HandleInvocation) obj).methodDescription);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.type.hashCode();
        }

        protected HandleInvocation(MethodDescription.InDefinedShape inDefinedShape, HandleType handleType) {
            this.methodDescription = inDefinedShape;
            this.type = handleType;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            String descriptor;
            String methodName = this.type.getMethodName();
            if (this.methodDescription.isStatic() || this.methodDescription.isConstructor()) {
                descriptor = this.methodDescription.getDescriptor();
            } else {
                descriptor = "(" + this.methodDescription.getDeclaringType().getDescriptor() + this.methodDescription.getDescriptor().substring(1);
            }
            methodVisitor.visitMethodInsn(182, METHOD_HANDLE, methodName, descriptor, false);
            int stackSize = 1 + this.methodDescription.getStackSize();
            int size = this.methodDescription.getReturnType().getStackSize().getSize();
            return new StackManipulation.Size(size - stackSize, Math.max(0, size - stackSize));
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/member/MethodInvocation$HandleType.class */
    public enum HandleType {
        EXACT("invokeExact"),
        REGULAR("invoke");

        private final String methodName;

        HandleType(String str) {
            this.methodName = str;
        }

        protected final String getMethodName() {
            return this.methodName;
        }
    }
}
