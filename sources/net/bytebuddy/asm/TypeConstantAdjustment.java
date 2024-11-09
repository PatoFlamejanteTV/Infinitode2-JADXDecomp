package net.bytebuddy.asm;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/asm/TypeConstantAdjustment.class */
public enum TypeConstantAdjustment implements AsmVisitorWrapper {
    INSTANCE;

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public final int mergeWriter(int i) {
        return i;
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public final int mergeReader(int i) {
        return i;
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public final ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        return new TypeConstantDissolvingClassVisitor(classVisitor);
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/TypeConstantAdjustment$TypeConstantDissolvingClassVisitor.class */
    protected static class TypeConstantDissolvingClassVisitor extends ClassVisitor {
        private boolean supportsTypeConstants;

        protected TypeConstantDissolvingClassVisitor(ClassVisitor classVisitor) {
            super(OpenedClassReader.ASM_API, classVisitor);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public void visit(int i, int i2, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
            this.supportsTypeConstants = ClassFileVersion.ofMinorMajor(i).isAtLeast(ClassFileVersion.JAVA_V5);
            super.visit(i, i2, str, str2, str3, strArr);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        @MaybeNull
        public MethodVisitor visitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
            MethodVisitor visitMethod = super.visitMethod(i, str, str2, str3, strArr);
            return (this.supportsTypeConstants || visitMethod == null) ? visitMethod : new TypeConstantDissolvingMethodVisitor(visitMethod);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/TypeConstantAdjustment$TypeConstantDissolvingClassVisitor$TypeConstantDissolvingMethodVisitor.class */
        protected static class TypeConstantDissolvingMethodVisitor extends MethodVisitor {
            private static final String JAVA_LANG_CLASS = "java/lang/Class";
            private static final String FOR_NAME = "forName";
            private static final String DESCRIPTOR = "(Ljava/lang/String;)Ljava/lang/Class;";

            protected TypeConstantDissolvingMethodVisitor(MethodVisitor methodVisitor) {
                super(OpenedClassReader.ASM_API, methodVisitor);
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            @SuppressFBWarnings(value = {"SF_SWITCH_NO_DEFAULT"}, justification = "Fall through to default case is intentional.")
            public void visitLdcInsn(Object obj) {
                if (obj instanceof Type) {
                    Type type = (Type) obj;
                    switch (type.getSort()) {
                        case 9:
                        case 10:
                            super.visitLdcInsn(type.getInternalName().replace('/', '.'));
                            super.visitMethodInsn(184, "java/lang/Class", FOR_NAME, DESCRIPTOR, false);
                            return;
                    }
                }
                super.visitLdcInsn(obj);
            }
        }
    }
}
