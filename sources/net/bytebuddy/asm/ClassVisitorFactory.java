package net.bytebuddy.asm;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.MultipleParentClassLoader;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.ModuleVisitor;
import net.bytebuddy.jar.asm.RecordComponentVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory.class */
public abstract class ClassVisitorFactory<T> {
    private static final String DELEGATE = "delegate";
    private static final String LABELS = "labels";
    private static final String WRAP = "wrap";
    private final Class<?> type;
    private static final boolean ACCESS_CONTROLLER;

    public abstract T wrap(ClassVisitor classVisitor);

    public abstract ClassVisitor unwrap(T t);

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.type.equals(((ClassVisitorFactory) obj).type);
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.type.hashCode();
    }

    static {
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
    }

    protected ClassVisitorFactory(Class<?> cls) {
        this.type = cls;
    }

    public Class<?> getType() {
        return this.type;
    }

    public static <S> ClassVisitorFactory<S> of(Class<S> cls) {
        return of(cls, new ByteBuddy().with(TypeValidation.DISABLED));
    }

    public static <S> ClassVisitorFactory<S> of(Class<S> cls, ByteBuddy byteBuddy) {
        return (ClassVisitorFactory) doPrivileged(new CreateClassVisitorFactory(cls, byteBuddy));
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DynamicType.Builder<?> toVisitorBuilder(ByteBuddy byteBuddy, Class<?> cls, Class<?> cls2, @MaybeNull Class<?> cls3, @MaybeNull Class<?> cls4, Implementation implementation) {
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<T> intercept = byteBuddy.subclass((Class) cls, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).defineField("delegate", cls2, Visibility.PRIVATE, FieldManifestation.FINAL).defineConstructor(Visibility.PUBLIC).withParameters(cls2).intercept(MethodCall.invoke(cls.getDeclaredConstructor(Integer.TYPE)).with(Integer.valueOf(OpenedClassReader.ASM_API)).andThen(FieldAccessor.ofField("delegate").setsArgumentAt(0)).andThen(implementation)).defineMethod(WRAP, cls, Visibility.PUBLIC, Ownership.STATIC).withParameters(cls2).intercept(new Implementation.Simple(new NullCheckedConstruction(cls2)));
        if (cls3 == null || cls4 == null) {
            return intercept;
        }
        return intercept.defineMethod("typePath", cls4, Visibility.PRIVATE, Ownership.STATIC).withParameters(cls3).intercept(new Implementation.Simple(new TypePathTranslator(cls3, cls4)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DynamicType.Builder<?> toMethodVisitorBuilder(ByteBuddy byteBuddy, Class<?> cls, Class<?> cls2, @MaybeNull Class<?> cls3, @MaybeNull Class<?> cls4, @MaybeNull Class<?> cls5, @MaybeNull Class<?> cls6, @MaybeNull Class<?> cls7, @MaybeNull Class<?> cls8, @MaybeNull Class<?> cls9, @MaybeNull Class<?> cls10, @MaybeNull Class<?> cls11, @MaybeNull Class<?> cls12) {
        DynamicType.Builder<?> visitorBuilder = toVisitorBuilder(byteBuddy, cls, cls2, cls3, cls4, FieldAccessor.ofField(LABELS).setsValue(new StackManipulation.Compound(TypeCreation.of(TypeDescription.ForLoadedType.of(HashMap.class)), Duplication.SINGLE, MethodInvocation.invoke((MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(HashMap.class).getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0))).getOnly())), Map.class));
        if (cls5 != null && cls6 != null) {
            visitorBuilder = visitorBuilder.defineField(LABELS, Map.class, Visibility.PRIVATE, FieldManifestation.FINAL).defineMethod("label", cls6, Visibility.PRIVATE).withParameters(cls5).intercept(new Implementation.Simple(new LabelTranslator(cls6))).defineMethod(LABELS, TypeDescription.ArrayProjection.of(TypeDescription.ForLoadedType.of(cls6)), Visibility.PRIVATE).withParameters(TypeDescription.ArrayProjection.of(TypeDescription.ForLoadedType.of(cls5))).intercept(new Implementation.Simple(new LabelArrayTranslator(cls5, cls6))).defineMethod("frames", Object[].class, Visibility.PRIVATE).withParameters(Object[].class).intercept(new Implementation.Simple(new FrameTranslator(cls5, cls6)));
        }
        if (cls9 != null && cls10 != null) {
            visitorBuilder = visitorBuilder.defineMethod("handle", cls10, Visibility.PRIVATE, Ownership.STATIC).withParameters(cls9).intercept(new Implementation.Simple(new HandleTranslator(cls9, cls10)));
        }
        if (cls11 != null && cls12 != null && cls9 != null && cls10 != null) {
            visitorBuilder = visitorBuilder.defineMethod("constantDyanmic", cls12, Visibility.PRIVATE, Ownership.STATIC).withParameters(cls11).intercept(new Implementation.Simple(new ConstantDynamicTranslator(cls11, cls12, cls9, cls10)));
        }
        return visitorBuilder.defineMethod("constant", Object.class, Visibility.PRIVATE, Ownership.STATIC).withParameters(Object.class).intercept(new Implementation.Simple(new ConstantTranslator(cls9, cls10, cls7, cls8, cls11, cls12))).defineMethod("constants", Object[].class, Visibility.PRIVATE, Ownership.STATIC).withParameters(Object[].class).intercept(new Implementation.Simple(new ConstantArrayTranslator()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MethodCall.ArgumentLoader.Factory toConvertedParameter(TypeDescription typeDescription, Class<?> cls, String str, int i, boolean z) {
        StackManipulation[] stackManipulationArr = new StackManipulation[3];
        stackManipulationArr[0] = z ? MethodVariableAccess.loadThis() : StackManipulation.Trivial.INSTANCE;
        stackManipulationArr[1] = MethodVariableAccess.REFERENCE.loadFrom(i);
        stackManipulationArr[2] = MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescription.getDeclaredMethods().filter(ElementMatchers.named(str)).getOnly());
        return new MethodCall.ArgumentLoader.ForStackManipulation(new StackManipulation.Compound(stackManipulationArr), cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DynamicType toAttributeWrapper(DynamicType.Builder<?> builder, Class<?> cls, Class<?> cls2, TypeDescription typeDescription, TypeDescription typeDescription2) {
        return builder.defineField("delegate", cls2, Visibility.PUBLIC, FieldManifestation.FINAL).defineConstructor(Visibility.PUBLIC).withParameters(cls2).intercept(MethodCall.invoke(cls.getDeclaredConstructor(String.class)).onSuper().with(new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), FieldAccess.forField((FieldDescription.InDefinedShape) new FieldDescription.ForLoadedField(cls2.getField("type"))).read()), String.class).andThen(FieldAccessor.ofField("delegate").setsArgumentAt(0))).defineMethod("attribute", cls, Visibility.PUBLIC, Ownership.STATIC).withParameters(cls2).intercept(new Implementation.Simple(new AttributeTranslator(cls, cls2, typeDescription, typeDescription2))).method(ElementMatchers.isProtected()).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) UnsupportedOperationException.class)).method(ElementMatchers.named("isUnknown")).intercept(MethodCall.invoke(cls2.getMethod("isUnknown", new Class[0])).onField("delegate")).method(ElementMatchers.named("isCodeAttribute")).intercept(MethodCall.invoke(cls2.getMethod("isCodeAttribute", new Class[0])).onField("delegate")).make();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$NullCheckedConstruction.class */
    public static class NullCheckedConstruction implements ByteCodeAppender {
        private final Class<?> type;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.type.equals(((NullCheckedConstruction) obj).type);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.type.hashCode();
        }

        protected NullCheckedConstruction(Class<?> cls) {
            this.type = cls;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            methodVisitor.visitVarInsn(25, 0);
            Label label = new Label();
            methodVisitor.visitJumpInsn(198, label);
            methodVisitor.visitTypeInsn(187, context.getInstrumentedType().getInternalName());
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(183, context.getInstrumentedType().getInternalName(), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(this.type)), false);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(3, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$LabelTranslator.class */
    public static class LabelTranslator implements ByteCodeAppender {
        protected static final String NAME = "label";
        private final Class<?> target;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.target.equals(((LabelTranslator) obj).target);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.target.hashCode();
        }

        protected LabelTranslator(Class<?> cls) {
            this.target = cls;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, CompoundList.of(context.getInstrumentedType(), methodDescription.getParameters().asTypeList()));
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitFieldInsn(180, context.getInstrumentedType().getInternalName(), ClassVisitorFactory.LABELS, Type.getDescriptor(Map.class));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitMethodInsn(185, Type.getInternalName(Map.class), "get", Type.getMethodDescriptor(Type.getType((Class<?>) Object.class), Type.getType((Class<?>) Object.class)), true);
            methodVisitor.visitTypeInsn(192, Type.getInternalName(this.target));
            methodVisitor.visitVarInsn(58, 2);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitJumpInsn(199, label2);
            methodVisitor.visitTypeInsn(187, Type.getInternalName(this.target));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, Type.getInternalName(this.target), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V", false);
            methodVisitor.visitVarInsn(58, 2);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitFieldInsn(180, context.getInstrumentedType().getInternalName(), ClassVisitorFactory.LABELS, Type.getDescriptor(Map.class));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(185, Type.getInternalName(Map.class), "put", Type.getMethodDescriptor(Type.getType((Class<?>) Object.class), Type.getType((Class<?>) Object.class), Type.getType((Class<?>) Object.class)), true);
            methodVisitor.visitInsn(87);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().append(methodVisitor, Collections.singletonList(TypeDescription.ForLoadedType.of(this.target)), CompoundList.of(context.getInstrumentedType(), methodDescription.getParameters().asTypeList()));
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(3, 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$LabelArrayTranslator.class */
    public static class LabelArrayTranslator implements ByteCodeAppender {
        protected static final String NAME = "labels";
        private final Class<?> sourceLabel;
        private final Class<?> targetLabel;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sourceLabel.equals(((LabelArrayTranslator) obj).sourceLabel) && this.targetLabel.equals(((LabelArrayTranslator) obj).targetLabel);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.sourceLabel.hashCode()) * 31) + this.targetLabel.hashCode();
        }

        protected LabelArrayTranslator(Class<?> cls, Class<?> cls2) {
            this.sourceLabel = cls;
            this.targetLabel = cls2;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, CompoundList.of(context.getInstrumentedType(), methodDescription.getParameters().asTypeList()));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(190);
            methodVisitor.visitTypeInsn(189, Type.getInternalName(this.targetLabel));
            methodVisitor.visitVarInsn(58, 2);
            methodVisitor.visitInsn(3);
            methodVisitor.visitVarInsn(54, 3);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().append(methodVisitor, Arrays.asList(TypeDescription.ArrayProjection.of(TypeDescription.ForLoadedType.of(this.targetLabel)), TypeDescription.ForLoadedType.of(Integer.TYPE)), CompoundList.of(context.getInstrumentedType(), methodDescription.getParameters().asTypeList()));
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(190);
            methodVisitor.visitJumpInsn(162, label3);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitInsn(50);
            methodVisitor.visitMethodInsn(183, context.getInstrumentedType().getInternalName(), "label", Type.getMethodDescriptor(Type.getType(this.targetLabel), Type.getType(this.sourceLabel)), false);
            methodVisitor.visitInsn(83);
            methodVisitor.visitIincInsn(3, 1);
            methodVisitor.visitJumpInsn(167, label2);
            methodVisitor.visitLabel(label3);
            context.getFrameGeneration().chop(methodVisitor, 1, CompoundList.of(Collections.singletonList(context.getInstrumentedType()), methodDescription.getParameters().asTypeList(), Collections.singletonList(TypeDescription.ArrayProjection.of(TypeDescription.ForLoadedType.of(this.targetLabel)))));
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(5, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$HandleTranslator.class */
    public static class HandleTranslator implements ByteCodeAppender {
        protected static final String NAME = "handle";
        private final Class<?> sourceHandle;
        private final Class<?> targetHandle;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sourceHandle.equals(((HandleTranslator) obj).sourceHandle) && this.targetHandle.equals(((HandleTranslator) obj).targetHandle);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.sourceHandle.hashCode()) * 31) + this.targetHandle.hashCode();
        }

        protected HandleTranslator(Class<?> cls, Class<?> cls2) {
            this.sourceHandle = cls;
            this.targetHandle = cls2;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            methodVisitor.visitTypeInsn(187, Type.getInternalName(this.targetHandle));
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceHandle), "getTag", Type.getMethodDescriptor(Type.INT_TYPE, new Type[0]), false);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceHandle), "getOwner", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceHandle), "getName", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceHandle), "getDesc", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceHandle), "isInterface", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, new Type[0]), false);
            methodVisitor.visitMethodInsn(183, Type.getInternalName(this.targetHandle), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, Type.INT_TYPE, Type.getType((Class<?>) String.class), Type.getType((Class<?>) String.class), Type.getType((Class<?>) String.class), Type.BOOLEAN_TYPE), false);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(7, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$ConstantDynamicTranslator.class */
    public static class ConstantDynamicTranslator implements ByteCodeAppender {
        protected static final String NAME = "constantDyanmic";
        private final Class<?> sourceConstantDynamic;
        private final Class<?> targetConstantDynamic;
        private final Class<?> sourceHandle;
        private final Class<?> targetHandle;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sourceConstantDynamic.equals(((ConstantDynamicTranslator) obj).sourceConstantDynamic) && this.targetConstantDynamic.equals(((ConstantDynamicTranslator) obj).targetConstantDynamic) && this.sourceHandle.equals(((ConstantDynamicTranslator) obj).sourceHandle) && this.targetHandle.equals(((ConstantDynamicTranslator) obj).targetHandle);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.sourceConstantDynamic.hashCode()) * 31) + this.targetConstantDynamic.hashCode()) * 31) + this.sourceHandle.hashCode()) * 31) + this.targetHandle.hashCode();
        }

        protected ConstantDynamicTranslator(Class<?> cls, Class<?> cls2, Class<?> cls3, Class<?> cls4) {
            this.sourceConstantDynamic = cls;
            this.targetConstantDynamic = cls2;
            this.sourceHandle = cls3;
            this.targetHandle = cls4;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceConstantDynamic), "getBootstrapMethodArgumentCount", Type.getMethodDescriptor(Type.INT_TYPE, new Type[0]), false);
            methodVisitor.visitTypeInsn(189, Type.getInternalName(Object.class));
            methodVisitor.visitVarInsn(58, 1);
            methodVisitor.visitInsn(3);
            methodVisitor.visitVarInsn(54, 2);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().append(methodVisitor, Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE)), methodDescription.getParameters().asTypeList());
            methodVisitor.visitVarInsn(21, 2);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(190);
            methodVisitor.visitJumpInsn(162, label2);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(21, 2);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(21, 2);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceConstantDynamic), "getBootstrapMethodArgument", Type.getMethodDescriptor(Type.getType((Class<?>) Object.class), Type.INT_TYPE), false);
            methodVisitor.visitMethodInsn(184, context.getInstrumentedType().getInternalName(), "ldc", Type.getMethodDescriptor(Type.getType((Class<?>) Object.class), Type.getType((Class<?>) Object.class)), false);
            methodVisitor.visitInsn(83);
            methodVisitor.visitIincInsn(2, 1);
            methodVisitor.visitJumpInsn(167, label);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().chop(methodVisitor, 1, CompoundList.of(methodDescription.getParameters().asTypeList(), TypeDescription.ForLoadedType.of(Object[].class)));
            methodVisitor.visitTypeInsn(187, Type.getInternalName(this.targetConstantDynamic));
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceConstantDynamic), "getName", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceConstantDynamic), "getDescriptor", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceConstantDynamic), "getBootstrapMethod", Type.getMethodDescriptor(Type.getType(this.sourceHandle), new Type[0]), false);
            methodVisitor.visitMethodInsn(184, context.getInstrumentedType().getInternalName(), "handle", Type.getMethodDescriptor(Type.getType(this.targetHandle), Type.getType(this.sourceHandle)), false);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitMethodInsn(183, Type.getInternalName(this.targetConstantDynamic), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType((Class<?>) String.class), Type.getType((Class<?>) String.class), Type.getType(this.targetHandle), Type.getType((Class<?>) Object[].class)), false);
            methodVisitor.visitInsn(176);
            methodVisitor.visitMaxs(6, 3);
            return new ByteCodeAppender.Size(6, 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$ConstantTranslator.class */
    public static class ConstantTranslator implements ByteCodeAppender {
        protected static final String NAME = "constant";

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final Class<?> sourceHandle;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final Class<?> targetHandle;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final Class<?> sourceType;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final Class<?> targetType;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final Class<?> sourceConstantDynamic;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
        private final Class<?> targetConstantDynamic;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Class<?> cls = this.sourceHandle;
            Class<?> cls2 = ((ConstantTranslator) obj).sourceHandle;
            if (cls2 != null) {
                if (cls == null || !cls.equals(cls2)) {
                    return false;
                }
            } else if (cls != null) {
                return false;
            }
            Class<?> cls3 = this.targetHandle;
            Class<?> cls4 = ((ConstantTranslator) obj).targetHandle;
            if (cls4 != null) {
                if (cls3 == null || !cls3.equals(cls4)) {
                    return false;
                }
            } else if (cls3 != null) {
                return false;
            }
            Class<?> cls5 = this.sourceType;
            Class<?> cls6 = ((ConstantTranslator) obj).sourceType;
            if (cls6 != null) {
                if (cls5 == null || !cls5.equals(cls6)) {
                    return false;
                }
            } else if (cls5 != null) {
                return false;
            }
            Class<?> cls7 = this.targetType;
            Class<?> cls8 = ((ConstantTranslator) obj).targetType;
            if (cls8 != null) {
                if (cls7 == null || !cls7.equals(cls8)) {
                    return false;
                }
            } else if (cls7 != null) {
                return false;
            }
            Class<?> cls9 = this.sourceConstantDynamic;
            Class<?> cls10 = ((ConstantTranslator) obj).sourceConstantDynamic;
            if (cls10 != null) {
                if (cls9 == null || !cls9.equals(cls10)) {
                    return false;
                }
            } else if (cls9 != null) {
                return false;
            }
            Class<?> cls11 = this.targetConstantDynamic;
            Class<?> cls12 = ((ConstantTranslator) obj).targetConstantDynamic;
            return cls12 != null ? cls11 != null && cls11.equals(cls12) : cls11 == null;
        }

        public int hashCode() {
            int hashCode = getClass().hashCode() * 31;
            Class<?> cls = this.sourceHandle;
            if (cls != null) {
                hashCode += cls.hashCode();
            }
            int i = hashCode * 31;
            Class<?> cls2 = this.targetHandle;
            if (cls2 != null) {
                i += cls2.hashCode();
            }
            int i2 = i * 31;
            Class<?> cls3 = this.sourceType;
            if (cls3 != null) {
                i2 += cls3.hashCode();
            }
            int i3 = i2 * 31;
            Class<?> cls4 = this.targetType;
            if (cls4 != null) {
                i3 += cls4.hashCode();
            }
            int i4 = i3 * 31;
            Class<?> cls5 = this.sourceConstantDynamic;
            if (cls5 != null) {
                i4 += cls5.hashCode();
            }
            int i5 = i4 * 31;
            Class<?> cls6 = this.targetConstantDynamic;
            return cls6 != null ? i5 + cls6.hashCode() : i5;
        }

        protected ConstantTranslator(@MaybeNull Class<?> cls, @MaybeNull Class<?> cls2, @MaybeNull Class<?> cls3, @MaybeNull Class<?> cls4, @MaybeNull Class<?> cls5, @MaybeNull Class<?> cls6) {
            this.sourceHandle = cls;
            this.targetHandle = cls2;
            this.sourceType = cls3;
            this.targetType = cls4;
            this.sourceConstantDynamic = cls5;
            this.targetConstantDynamic = cls6;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            if (this.sourceType != null && this.targetType != null) {
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitTypeInsn(193, Type.getInternalName(this.sourceType));
                methodVisitor.visitJumpInsn(153, label);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitTypeInsn(192, Type.getInternalName(this.sourceType));
                methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceType), "getDescriptor", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
                methodVisitor.visitMethodInsn(184, Type.getInternalName(this.targetType), "getType", Type.getMethodDescriptor(Type.getType(this.targetType), Type.getType((Class<?>) String.class)), false);
                methodVisitor.visitInsn(176);
                methodVisitor.visitLabel(label);
                context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            }
            if (this.sourceHandle != null && this.targetHandle != null) {
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitTypeInsn(193, Type.getInternalName(this.sourceHandle));
                methodVisitor.visitJumpInsn(153, label2);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitTypeInsn(192, Type.getInternalName(this.sourceHandle));
                methodVisitor.visitMethodInsn(184, context.getInstrumentedType().getInternalName(), "handle", Type.getMethodDescriptor(Type.getType(this.targetHandle), Type.getType(this.sourceHandle)), false);
                methodVisitor.visitInsn(176);
                methodVisitor.visitLabel(label2);
                context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            }
            if (this.sourceConstantDynamic != null && this.targetConstantDynamic != null) {
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitTypeInsn(193, Type.getInternalName(this.sourceConstantDynamic));
                methodVisitor.visitJumpInsn(153, label3);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitTypeInsn(192, Type.getInternalName(this.sourceConstantDynamic));
                methodVisitor.visitMethodInsn(184, context.getInstrumentedType().getInternalName(), "constantDyanmic", Type.getMethodDescriptor(Type.getType(this.targetConstantDynamic), Type.getType(this.sourceConstantDynamic)), false);
                methodVisitor.visitInsn(176);
                methodVisitor.visitLabel(label3);
                context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            }
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(1, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$ConstantArrayTranslator.class */
    public static class ConstantArrayTranslator implements ByteCodeAppender {
        protected static final String NAME = "constants";

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        protected ConstantArrayTranslator() {
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitInsn(190);
            methodVisitor.visitTypeInsn(189, Type.getInternalName(Object.class));
            methodVisitor.visitVarInsn(58, 1);
            methodVisitor.visitInsn(3);
            methodVisitor.visitVarInsn(54, 2);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().append(methodVisitor, Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE)), methodDescription.getParameters().asTypeList());
            methodVisitor.visitVarInsn(21, 2);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(190);
            methodVisitor.visitJumpInsn(162, label3);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(21, 2);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(21, 2);
            methodVisitor.visitInsn(50);
            methodVisitor.visitMethodInsn(184, context.getInstrumentedType().getInternalName(), "constant", Type.getMethodDescriptor(Type.getType((Class<?>) Object.class), Type.getType((Class<?>) Object.class)), false);
            methodVisitor.visitInsn(83);
            methodVisitor.visitIincInsn(2, 1);
            methodVisitor.visitJumpInsn(167, label2);
            methodVisitor.visitLabel(label3);
            context.getFrameGeneration().chop(methodVisitor, 1, CompoundList.of(methodDescription.getParameters().asTypeList(), TypeDescription.ForLoadedType.of(Object[].class)));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(4, 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$FrameTranslator.class */
    public static class FrameTranslator implements ByteCodeAppender {
        protected static final String NAME = "frames";
        private final Class<?> sourceLabel;
        private final Class<?> targetLabel;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sourceLabel.equals(((FrameTranslator) obj).sourceLabel) && this.targetLabel.equals(((FrameTranslator) obj).targetLabel);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.sourceLabel.hashCode()) * 31) + this.targetLabel.hashCode();
        }

        protected FrameTranslator(Class<?> cls, Class<?> cls2) {
            this.sourceLabel = cls;
            this.targetLabel = cls2;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            Label label4 = new Label();
            Label label5 = new Label();
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, CompoundList.of(context.getInstrumentedType(), methodDescription.getParameters().asTypeList()));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(190);
            methodVisitor.visitTypeInsn(189, Type.getInternalName(Object.class));
            methodVisitor.visitVarInsn(58, 2);
            methodVisitor.visitInsn(3);
            methodVisitor.visitVarInsn(54, 3);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().append(methodVisitor, Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE)), CompoundList.of(context.getInstrumentedType(), methodDescription.getParameters().asTypeList()));
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitInsn(190);
            methodVisitor.visitJumpInsn(162, label4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitInsn(50);
            methodVisitor.visitTypeInsn(193, Type.getInternalName(this.sourceLabel));
            methodVisitor.visitJumpInsn(153, label5);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitInsn(50);
            methodVisitor.visitTypeInsn(192, Type.getInternalName(this.sourceLabel));
            methodVisitor.visitMethodInsn(183, context.getInstrumentedType().getInternalName(), "label", Type.getMethodDescriptor(Type.getType(this.targetLabel), Type.getType(this.sourceLabel)), false);
            methodVisitor.visitJumpInsn(167, label3);
            methodVisitor.visitLabel(label5);
            context.getFrameGeneration().full(methodVisitor, Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE)), CompoundList.of(Collections.singletonList(context.getInstrumentedType()), methodDescription.getParameters().asTypeList(), Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE))));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(21, 3);
            methodVisitor.visitInsn(50);
            methodVisitor.visitLabel(label3);
            context.getFrameGeneration().full(methodVisitor, Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE), TypeDescription.ForLoadedType.of(Object.class)), CompoundList.of(Collections.singletonList(context.getInstrumentedType()), methodDescription.getParameters().asTypeList(), Arrays.asList(TypeDescription.ForLoadedType.of(Object[].class), TypeDescription.ForLoadedType.of(Integer.TYPE))));
            methodVisitor.visitInsn(83);
            methodVisitor.visitIincInsn(3, 1);
            methodVisitor.visitJumpInsn(167, label2);
            methodVisitor.visitLabel(label4);
            context.getFrameGeneration().chop(methodVisitor, 1, CompoundList.of(Collections.singletonList(context.getInstrumentedType()), methodDescription.getParameters().asTypeList(), Collections.singletonList(TypeDescription.ForLoadedType.of(Object[].class))));
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(5, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$TypePathTranslator.class */
    public static class TypePathTranslator implements ByteCodeAppender {
        protected static final String NAME = "typePath";
        private final Class<?> sourceTypePath;
        private final Class<?> targetTypePath;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sourceTypePath.equals(((TypePathTranslator) obj).sourceTypePath) && this.targetTypePath.equals(((TypePathTranslator) obj).targetTypePath);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.sourceTypePath.hashCode()) * 31) + this.targetTypePath.hashCode();
        }

        protected TypePathTranslator(Class<?> cls, Class<?> cls2) {
            this.sourceTypePath = cls;
            this.targetTypePath = cls2;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitJumpInsn(167, label2);
            context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            methodVisitor.visitLabel(label);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, Type.getInternalName(this.sourceTypePath), "toString", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), new Type[0]), false);
            methodVisitor.visitMethodInsn(184, Type.getInternalName(this.targetTypePath), "fromString", Type.getMethodDescriptor(Type.getType(this.targetTypePath), Type.getType((Class<?>) String.class)), false);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().same1(methodVisitor, TypeDescription.ForLoadedType.of(this.targetTypePath), methodDescription.getParameters().asTypeList());
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(1, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$AttributeTranslator.class */
    public static class AttributeTranslator implements ByteCodeAppender {
        protected static final String NAME = "attribute";
        private final Class<?> sourceAttribute;
        private final Class<?> targetAttribute;
        private final TypeDescription sourceWrapper;
        private final TypeDescription targetWrapper;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sourceAttribute.equals(((AttributeTranslator) obj).sourceAttribute) && this.targetAttribute.equals(((AttributeTranslator) obj).targetAttribute) && this.sourceWrapper.equals(((AttributeTranslator) obj).sourceWrapper) && this.targetWrapper.equals(((AttributeTranslator) obj).targetWrapper);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.sourceAttribute.hashCode()) * 31) + this.targetAttribute.hashCode()) * 31) + this.sourceWrapper.hashCode()) * 31) + this.targetWrapper.hashCode();
        }

        protected AttributeTranslator(Class<?> cls, Class<?> cls2, TypeDescription typeDescription, TypeDescription typeDescription2) {
            this.sourceAttribute = cls;
            this.targetAttribute = cls2;
            this.sourceWrapper = typeDescription;
            this.targetWrapper = typeDescription2;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitJumpInsn(199, label);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label);
            context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitTypeInsn(193, this.targetWrapper.getInternalName());
            methodVisitor.visitJumpInsn(153, label2);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitTypeInsn(192, this.targetWrapper.getInternalName());
            methodVisitor.visitFieldInsn(180, this.targetWrapper.getInternalName(), "delegate", Type.getDescriptor(this.sourceAttribute));
            methodVisitor.visitInsn(176);
            methodVisitor.visitLabel(label2);
            context.getFrameGeneration().same(methodVisitor, methodDescription.getParameters().asTypeList());
            methodVisitor.visitTypeInsn(187, this.sourceWrapper.getInternalName());
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(183, this.sourceWrapper.getInternalName(), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(this.targetAttribute)), false);
            methodVisitor.visitInsn(176);
            return new ByteCodeAppender.Size(3, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/ClassVisitorFactory$CreateClassVisitorFactory.class */
    public static class CreateClassVisitorFactory<S> implements PrivilegedAction<ClassVisitorFactory<S>> {
        private final Class<S> classVisitor;
        private final ByteBuddy byteBuddy;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classVisitor.equals(((CreateClassVisitorFactory) obj).classVisitor) && this.byteBuddy.equals(((CreateClassVisitorFactory) obj).byteBuddy);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.classVisitor.hashCode()) * 31) + this.byteBuddy.hashCode();
        }

        protected CreateClassVisitorFactory(Class<S> cls, ByteBuddy byteBuddy) {
            this.classVisitor = cls;
            this.byteBuddy = byteBuddy;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.security.PrivilegedAction
        public ClassVisitorFactory<S> run() {
            DynamicType dynamicType;
            DynamicType dynamicType2;
            Method method;
            DynamicType.Builder visitorBuilder;
            DynamicType.Builder visitorBuilder2;
            if (!ClassVisitor.class.getSimpleName().equals(this.classVisitor.getSimpleName())) {
                throw new IllegalArgumentException("Expected a class named " + ClassVisitor.class.getSimpleName() + ": " + this.classVisitor);
            }
            try {
                String name = this.classVisitor.getPackage().getName();
                HashMap hashMap = new HashMap();
                for (Class cls : Arrays.asList(Attribute.class, Label.class, Type.class, TypePath.class, Handle.class, ConstantDynamic.class)) {
                    try {
                        hashMap.put(cls, Class.forName(name + "." + cls.getSimpleName(), false, this.classVisitor.getClassLoader()));
                    } catch (ClassNotFoundException unused) {
                    }
                }
                if (hashMap.containsKey(Label.class)) {
                    hashMap.put(Label[].class, Class.forName("[L" + ((Class) hashMap.get(Label.class)).getName() + ";", false, this.classVisitor.getClassLoader()));
                }
                HashMap hashMap2 = new HashMap();
                HashMap hashMap3 = new HashMap();
                for (Class cls2 : Arrays.asList(ClassVisitor.class, AnnotationVisitor.class, ModuleVisitor.class, RecordComponentVisitor.class, FieldVisitor.class, MethodVisitor.class)) {
                    try {
                        Class<?> cls3 = Class.forName(name + "." + cls2.getSimpleName(), false, this.classVisitor.getClassLoader());
                        if (cls2 == MethodVisitor.class) {
                            visitorBuilder = ClassVisitorFactory.toMethodVisitorBuilder(this.byteBuddy, cls2, cls3, TypePath.class, (Class) hashMap.get(TypePath.class), Label.class, (Class) hashMap.get(Label.class), Type.class, (Class) hashMap.get(Type.class), Handle.class, (Class) hashMap.get(Handle.class), ConstantDynamic.class, (Class) hashMap.get(ConstantDynamic.class));
                            visitorBuilder2 = ClassVisitorFactory.toMethodVisitorBuilder(this.byteBuddy, cls3, cls2, (Class) hashMap.get(TypePath.class), TypePath.class, (Class) hashMap.get(Label.class), Label.class, (Class) hashMap.get(Type.class), Type.class, (Class) hashMap.get(Handle.class), Handle.class, (Class) hashMap.get(ConstantDynamic.class), ConstantDynamic.class);
                        } else {
                            visitorBuilder = ClassVisitorFactory.toVisitorBuilder(this.byteBuddy, cls2, cls3, TypePath.class, (Class) hashMap.get(TypePath.class), new Implementation.Simple(MethodReturn.VOID));
                            visitorBuilder2 = ClassVisitorFactory.toVisitorBuilder(this.byteBuddy, cls3, cls2, (Class) hashMap.get(TypePath.class), TypePath.class, new Implementation.Simple(MethodReturn.VOID));
                        }
                        hashMap2.put(cls2, cls3);
                        hashMap3.put(cls2, visitorBuilder);
                        hashMap3.put(cls3, visitorBuilder2);
                    } catch (ClassNotFoundException unused2) {
                    }
                }
                ArrayList arrayList = new ArrayList();
                HashMap hashMap4 = new HashMap();
                if (hashMap.containsKey(Attribute.class)) {
                    DynamicType.Builder<T> subclass = this.byteBuddy.subclass((Class) Attribute.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS);
                    DynamicType.Builder<T> subclass2 = this.byteBuddy.subclass((Class) hashMap.get(Attribute.class), (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS);
                    dynamicType = ClassVisitorFactory.toAttributeWrapper(subclass, Attribute.class, (Class) hashMap.get(Attribute.class), subclass.toTypeDescription(), subclass2.toTypeDescription());
                    arrayList.add(dynamicType);
                    dynamicType2 = ClassVisitorFactory.toAttributeWrapper(subclass2, (Class) hashMap.get(Attribute.class), Attribute.class, subclass2.toTypeDescription(), subclass.toTypeDescription());
                    arrayList.add(dynamicType2);
                } else {
                    dynamicType = null;
                    dynamicType2 = null;
                }
                for (Map.Entry entry : hashMap2.entrySet()) {
                    DynamicType.Builder builder = (DynamicType.Builder) hashMap3.get(entry.getKey());
                    DynamicType.Builder builder2 = (DynamicType.Builder) hashMap3.get(entry.getValue());
                    for (Method method2 : ((Class) entry.getKey()).getMethods()) {
                        if (method2.getDeclaringClass() != Object.class) {
                            Class<?>[] parameterTypes = method2.getParameterTypes();
                            Class<?>[] clsArr = new Class[parameterTypes.length];
                            ArrayList arrayList2 = new ArrayList(parameterTypes.length);
                            ArrayList arrayList3 = new ArrayList(clsArr.length);
                            boolean z = false;
                            boolean z2 = false;
                            int i = 1;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= parameterTypes.length) {
                                    break;
                                }
                                if (entry.getKey() == MethodVisitor.class && parameterTypes[i2] == Label.class) {
                                    clsArr[i2] = (Class) hashMap.get(Label.class);
                                    arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), clsArr[i2], "label", i, true));
                                    arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), parameterTypes[i2], "label", i, true));
                                } else if (entry.getKey() == MethodVisitor.class && parameterTypes[i2] == Label[].class) {
                                    clsArr[i2] = (Class) hashMap.get(Label[].class);
                                    arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), clsArr[i2], ClassVisitorFactory.LABELS, i, true));
                                    arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), parameterTypes[i2], ClassVisitorFactory.LABELS, i, true));
                                } else if (parameterTypes[i2] == TypePath.class) {
                                    clsArr[i2] = (Class) hashMap.get(TypePath.class);
                                    arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), clsArr[i2], "typePath", i, false));
                                    arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), parameterTypes[i2], "typePath", i, false));
                                } else if (entry.getKey() == MethodVisitor.class && parameterTypes[i2] == Handle.class) {
                                    clsArr[i2] = (Class) hashMap.get(Handle.class);
                                    arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), clsArr[i2], "handle", i, false));
                                    arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), parameterTypes[i2], "handle", i, false));
                                } else if (entry.getKey() == MethodVisitor.class && parameterTypes[i2] == Object.class) {
                                    clsArr[i2] = Object.class;
                                    arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), Object.class, "constant", i, false));
                                    arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), Object.class, "constant", i, false));
                                } else if (entry.getKey() == MethodVisitor.class && parameterTypes[i2] == Object[].class) {
                                    clsArr[i2] = Object[].class;
                                    if (method2.getName().equals("visitFrame")) {
                                        arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), Object[].class, "frames", i, true));
                                        arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), Object[].class, "frames", i, true));
                                    } else {
                                        arrayList2.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getKey())).toTypeDescription(), Object[].class, "constants", i, false));
                                        arrayList3.add(ClassVisitorFactory.toConvertedParameter(((DynamicType.Builder) hashMap3.get(entry.getValue())).toTypeDescription(), Object[].class, "constants", i, false));
                                    }
                                } else if (parameterTypes[i2] == Attribute.class) {
                                    clsArr[i2] = (Class) hashMap.get(Attribute.class);
                                    if (dynamicType != null && dynamicType2 != null) {
                                        arrayList2.add(ClassVisitorFactory.toConvertedParameter(dynamicType2.getTypeDescription(), (Class) hashMap.get(Attribute.class), "attribute", i, false));
                                        arrayList3.add(ClassVisitorFactory.toConvertedParameter(dynamicType.getTypeDescription(), Attribute.class, "attribute", i, false));
                                    } else {
                                        z = true;
                                    }
                                } else {
                                    clsArr[i2] = parameterTypes[i2];
                                    arrayList2.add(new MethodCall.ArgumentLoader.ForMethodParameter.Factory(i2));
                                    arrayList3.add(new MethodCall.ArgumentLoader.ForMethodParameter.Factory(i2));
                                }
                                if (clsArr[i2] == null) {
                                    z2 = true;
                                    break;
                                }
                                i += (parameterTypes[i2] == Long.TYPE || parameterTypes[i2] == Double.TYPE) ? 2 : 1;
                                i2++;
                            }
                            if (!z2) {
                                try {
                                    method = ((Class) entry.getValue()).getMethod(method2.getName(), clsArr);
                                } catch (NoSuchMethodException unused3) {
                                    method = null;
                                    z = true;
                                }
                            } else {
                                method = null;
                                z = true;
                            }
                            if (z) {
                                builder = builder.method(ElementMatchers.is(method2)).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) UnsupportedOperationException.class));
                                if (method != null) {
                                    builder2 = builder2.method(ElementMatchers.is(method)).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) UnsupportedOperationException.class));
                                }
                            } else {
                                MethodCall with = MethodCall.invoke(method).onField("delegate").with(arrayList2);
                                MethodCall with2 = MethodCall.invoke(method2).onField("delegate").with(arrayList3);
                                Class cls4 = (Class) hashMap2.get(method2.getReturnType());
                                if (cls4 != null) {
                                    with = MethodCall.invoke((MethodDescription) ((DynamicType.Builder) hashMap3.get(method2.getReturnType())).toTypeDescription().getDeclaredMethods().filter(ElementMatchers.named(ClassVisitorFactory.WRAP)).getOnly()).withMethodCall(with);
                                    with2 = MethodCall.invoke((MethodDescription) ((DynamicType.Builder) hashMap3.get(cls4)).toTypeDescription().getDeclaredMethods().filter(ElementMatchers.named(ClassVisitorFactory.WRAP)).getOnly()).withMethodCall(with2);
                                }
                                builder = builder.method(ElementMatchers.is(method2)).intercept(with);
                                builder2 = builder2.method(ElementMatchers.is(method)).intercept(with2);
                            }
                        }
                    }
                    DynamicType.Unloaded<T> make = builder.make();
                    DynamicType.Unloaded<T> make2 = builder2.make();
                    hashMap4.put(entry.getKey(), make.getTypeDescription());
                    hashMap4.put(entry.getValue(), make2.getTypeDescription());
                    arrayList.add(make);
                    arrayList.add(make2);
                }
                ClassLoader build = new MultipleParentClassLoader.Builder(false).appendMostSpecific((Class<?>[]) new Class[]{ClassVisitor.class, this.classVisitor}).build();
                ClassVisitorFactory<S> classVisitorFactory = (ClassVisitorFactory) this.byteBuddy.subclass((Class) ClassVisitorFactory.class, (ConstructorStrategy) ConstructorStrategy.Default.IMITATE_SUPER_CLASS_OPENING).method(ElementMatchers.named(ClassVisitorFactory.WRAP)).intercept(MethodCall.construct((MethodDescription) ((TypeDescription) hashMap4.get(this.classVisitor)).getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly()).withArgument(0)).method(ElementMatchers.named("unwrap")).intercept(MethodCall.construct((MethodDescription) ((TypeDescription) hashMap4.get(ClassVisitor.class)).getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly()).withArgument(0).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC)).make().include(arrayList).load(build).getLoaded().getConstructor(Class.class).newInstance(this.classVisitor);
                if ((build instanceof MultipleParentClassLoader) && build != ClassVisitor.class.getClassLoader() && build != this.classVisitor.getClassLoader() && !((MultipleParentClassLoader) build).seal()) {
                    throw new IllegalStateException("Failed to seal multiple parent class loader: " + build);
                }
                return classVisitorFactory;
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to generate factory for " + this.classVisitor.getName(), e);
            }
        }
    }
}
