package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin.class */
public class AccessControllerPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
    private static final String ACCESS_CONTROLLER = "java.security.AccessController";
    private static final String NAME = "ACCESS_CONTROLLER";
    private static final Object[] EMPTY = new Object[0];
    private static final Map<MethodDescription.SignatureToken, MethodDescription.SignatureToken> SIGNATURES;

    @MaybeNull
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
    private final String property;

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin$Enhance.class */
    public @interface Enhance {
    }

    @Override // net.bytebuddy.build.Plugin.ForElementMatcher
    public boolean equals(@MaybeNull Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.property;
        String str2 = ((AccessControllerPlugin) obj).property;
        return str2 != null ? str != null && str.equals(str2) : str == null;
    }

    @Override // net.bytebuddy.build.Plugin.ForElementMatcher
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.property;
        return str != null ? hashCode + str.hashCode() : hashCode;
    }

    static {
        HashMap hashMap = new HashMap();
        SIGNATURES = hashMap;
        hashMap.put(new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class)), new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class)), new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class), TypeDescription.ForLoadedType.of(Object.class)), new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class), JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub()));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class), TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(Permission[].class)), new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class), JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub(), TypeDescription.ForLoadedType.of(Permission[].class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class), TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(Permission[].class)), new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedAction.class), JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub(), TypeDescription.ForLoadedType.of(Permission[].class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class)), new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class)), new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class), TypeDescription.ForLoadedType.of(Object.class)), new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class), JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub()));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class), TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(Permission[].class)), new MethodDescription.SignatureToken("doPrivileged", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class), JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub(), TypeDescription.ForLoadedType.of(Permission[].class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class), TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(Permission[].class)), new MethodDescription.SignatureToken("doPrivilegedWithCombiner", TypeDescription.ForLoadedType.of(Object.class), TypeDescription.ForLoadedType.of(PrivilegedExceptionAction.class), JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub(), TypeDescription.ForLoadedType.of(Permission[].class)));
        SIGNATURES.put(new MethodDescription.SignatureToken("getContext", TypeDescription.ForLoadedType.of(Object.class), new TypeDescription[0]), new MethodDescription.SignatureToken("getContext", JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub(), new TypeDescription[0]));
        SIGNATURES.put(new MethodDescription.SignatureToken("checkPermission", TypeDescription.ForLoadedType.of(Void.TYPE), TypeDescription.ForLoadedType.of(Permission.class)), new MethodDescription.SignatureToken("checkPermission", TypeDescription.ForLoadedType.of(Void.TYPE), TypeDescription.ForLoadedType.of(Permission.class)));
    }

    public AccessControllerPlugin() {
        this(null);
    }

    @Plugin.Factory.UsingReflection.Priority(Integer.MAX_VALUE)
    public AccessControllerPlugin(@MaybeNull String str) {
        super(ElementMatchers.declaresMethod(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Enhance.class)));
        this.property = str;
    }

    @Override // net.bytebuddy.build.Plugin.Factory
    public Plugin make() {
        return this;
    }

    @Override // net.bytebuddy.build.Plugin
    @SuppressFBWarnings(value = {"SBSC_USE_STRINGBUFFER_CONCATENATION"}, justification = "Collision is unlikely and buffer overhead not justified.")
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        String str;
        String str2 = NAME;
        while (true) {
            str = str2;
            if (typeDescription.getDeclaredFields().filter(ElementMatchers.named(str)).isEmpty()) {
                break;
            }
            str2 = str + "$";
        }
        return builder.defineField(str, Boolean.TYPE, Visibility.PRIVATE, Ownership.STATIC, FieldManifestation.FINAL).visit(new AsmVisitorWrapper.ForDeclaredMethods().method(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Enhance.class), new AccessControlWrapper(str))).initializer(this.property == null ? new Initializer.WithoutProperty(typeDescription, str) : new Initializer.WithProperty(typeDescription, str, this.property));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin$Initializer.class */
    protected static abstract class Initializer implements ByteCodeAppender {
        private final TypeDescription instrumentedType;
        private final String name;

        protected abstract int onAccessController(MethodVisitor methodVisitor);

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.name.equals(((Initializer) obj).name) && this.instrumentedType.equals(((Initializer) obj).instrumentedType);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.name.hashCode();
        }

        protected Initializer(TypeDescription typeDescription, String str) {
            this.instrumentedType = typeDescription;
            this.name = str;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            Label label4 = new Label();
            Label label5 = new Label();
            methodVisitor.visitTryCatchBlock(label, label2, label3, Type.getInternalName(ClassNotFoundException.class));
            methodVisitor.visitTryCatchBlock(label, label2, label4, Type.getInternalName(SecurityException.class));
            methodVisitor.visitLabel(label);
            methodVisitor.visitLdcInsn(AccessControllerPlugin.ACCESS_CONTROLLER);
            methodVisitor.visitInsn(3);
            methodVisitor.visitInsn(1);
            methodVisitor.visitMethodInsn(184, Type.getInternalName(Class.class), "forName", Type.getMethodDescriptor(Type.getType((Class<?>) Class.class), Type.getType((Class<?>) String.class), Type.getType((Class<?>) Boolean.TYPE), Type.getType((Class<?>) ClassLoader.class)), false);
            methodVisitor.visitInsn(87);
            int onAccessController = onAccessController(methodVisitor);
            methodVisitor.visitFieldInsn(179, this.instrumentedType.getInternalName(), this.name, Type.getDescriptor(Boolean.TYPE));
            methodVisitor.visitLabel(label2);
            methodVisitor.visitJumpInsn(167, label5);
            methodVisitor.visitLabel(label3);
            context.getFrameGeneration().same1(methodVisitor, TypeDescription.ForLoadedType.of(ClassNotFoundException.class), Collections.emptyList());
            methodVisitor.visitInsn(87);
            methodVisitor.visitInsn(3);
            methodVisitor.visitFieldInsn(179, this.instrumentedType.getInternalName(), this.name, Type.getDescriptor(Boolean.TYPE));
            methodVisitor.visitJumpInsn(167, label5);
            methodVisitor.visitLabel(label4);
            context.getFrameGeneration().same1(methodVisitor, TypeDescription.ForLoadedType.of(SecurityException.class), Collections.emptyList());
            methodVisitor.visitInsn(87);
            methodVisitor.visitInsn(4);
            methodVisitor.visitFieldInsn(179, this.instrumentedType.getInternalName(), this.name, Type.getDescriptor(Boolean.TYPE));
            methodVisitor.visitLabel(label5);
            context.getFrameGeneration().same(methodVisitor, Collections.emptyList());
            return new ByteCodeAppender.Size(Math.max(3, onAccessController), 0);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin$Initializer$WithProperty.class */
        protected static class WithProperty extends Initializer {
            private final String property;

            @Override // net.bytebuddy.build.AccessControllerPlugin.Initializer
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.property.equals(((WithProperty) obj).property);
            }

            @Override // net.bytebuddy.build.AccessControllerPlugin.Initializer
            public int hashCode() {
                return (super.hashCode() * 31) + this.property.hashCode();
            }

            protected WithProperty(TypeDescription typeDescription, String str, String str2) {
                super(typeDescription, str);
                this.property = str2;
            }

            @Override // net.bytebuddy.build.AccessControllerPlugin.Initializer
            protected int onAccessController(MethodVisitor methodVisitor) {
                methodVisitor.visitLdcInsn(this.property);
                methodVisitor.visitLdcInsn("true");
                methodVisitor.visitMethodInsn(184, Type.getInternalName(System.class), "getProperty", Type.getMethodDescriptor(Type.getType((Class<?>) String.class), Type.getType((Class<?>) String.class), Type.getType((Class<?>) String.class)), false);
                methodVisitor.visitMethodInsn(184, Type.getInternalName(Boolean.class), "parseBoolean", Type.getMethodDescriptor(Type.getType((Class<?>) Boolean.TYPE), Type.getType((Class<?>) String.class)), false);
                return 2;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin$Initializer$WithoutProperty.class */
        protected static class WithoutProperty extends Initializer {
            @Override // net.bytebuddy.build.AccessControllerPlugin.Initializer
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass();
            }

            @Override // net.bytebuddy.build.AccessControllerPlugin.Initializer
            public int hashCode() {
                return super.hashCode();
            }

            protected WithoutProperty(TypeDescription typeDescription, String str) {
                super(typeDescription, str);
            }

            @Override // net.bytebuddy.build.AccessControllerPlugin.Initializer
            protected int onAccessController(MethodVisitor methodVisitor) {
                methodVisitor.visitInsn(4);
                return 1;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin$AccessControlWrapper.class */
    protected static class AccessControlWrapper implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
        private final String name;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.name.equals(((AccessControlWrapper) obj).name);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.name.hashCode();
        }

        protected AccessControlWrapper(String str) {
            this.name = str;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
        public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
            MethodDescription.SignatureToken signatureToken = (MethodDescription.SignatureToken) AccessControllerPlugin.SIGNATURES.get(methodDescription.asDefined().asSignatureToken());
            if (signatureToken == null) {
                throw new IllegalStateException(methodDescription + " does not have a method with a matching signature in java.security.AccessController");
            }
            if (!methodDescription.isPublic() && !methodDescription.isProtected()) {
                return new PrefixingMethodVisitor(methodVisitor, typeDescription, signatureToken, this.name, methodDescription.isStatic() ? 0 : 1, context.getFrameGeneration());
            }
            throw new IllegalStateException(methodDescription + " is either public or protected what is not permitted to avoid context leaks");
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/build/AccessControllerPlugin$AccessControlWrapper$PrefixingMethodVisitor.class */
        protected static class PrefixingMethodVisitor extends MethodVisitor {
            private final TypeDescription instrumentedType;
            private final MethodDescription.SignatureToken token;
            private final String name;
            private final int offset;
            private final Implementation.Context.FrameGeneration frameGeneration;

            protected PrefixingMethodVisitor(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodDescription.SignatureToken signatureToken, String str, int i, Implementation.Context.FrameGeneration frameGeneration) {
                super(OpenedClassReader.ASM_API, methodVisitor);
                this.instrumentedType = typeDescription;
                this.token = signatureToken;
                this.name = str;
                this.offset = i;
                this.frameGeneration = frameGeneration;
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitCode() {
                this.mv.visitCode();
                this.mv.visitFieldInsn(178, this.instrumentedType.getInternalName(), this.name, Type.getDescriptor(Boolean.TYPE));
                Label label = new Label();
                this.mv.visitJumpInsn(153, label);
                int i = this.offset;
                for (TypeDescription typeDescription : this.token.getParameterTypes()) {
                    this.mv.visitVarInsn(Type.getType(typeDescription.getDescriptor()).getOpcode(21), i);
                    if (typeDescription.equals(JavaType.ACCESS_CONTROL_CONTEXT.getTypeStub())) {
                        this.mv.visitTypeInsn(192, typeDescription.getInternalName());
                    }
                    i += typeDescription.getStackSize().getSize();
                }
                this.mv.visitMethodInsn(184, AccessControllerPlugin.ACCESS_CONTROLLER.replace('.', '/'), this.token.getName(), this.token.getDescriptor(), false);
                this.mv.visitInsn(Type.getType(this.token.getReturnType().getDescriptor()).getOpcode(172));
                this.mv.visitLabel(label);
                this.frameGeneration.same(this.mv, this.token.getParameterTypes());
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitMaxs(int i, int i2) {
                this.mv.visitMaxs(Math.max(Math.max(StackSize.of(this.token.getParameterTypes()), this.token.getReturnType().getStackSize().getSize()), i), i2);
            }
        }
    }
}
