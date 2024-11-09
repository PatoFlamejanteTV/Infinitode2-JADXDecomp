package com.prineside.reflectasm;

import com.esotericsoftware.asm.ClassWriter;
import com.esotericsoftware.asm.MethodVisitor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.auxiliary.TypeProxy;

/* loaded from: infinitode-2.jar:com/prineside/reflectasm/ConstructorAccess.class */
public abstract class ConstructorAccess<T> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1650a;

    public abstract T newInstance();

    public abstract T newInstance(Object obj);

    public boolean isNonStaticMemberClass() {
        return this.f1650a;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0146  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> com.prineside.reflectasm.ConstructorAccess<T> get(java.lang.Class<T> r8) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.reflectasm.ConstructorAccess.get(java.lang.Class):com.prineside.reflectasm.ConstructorAccess");
    }

    private static void a(ClassWriter classWriter, String str) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, str, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
    }

    private static void b(ClassWriter classWriter, String str) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, "()Ljava/lang/Object;", null, null);
        visitMethod.visitCode();
        visitMethod.visitTypeInsn(187, str);
        visitMethod.visitInsn(89);
        visitMethod.visitMethodInsn(183, str, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        visitMethod.visitInsn(176);
        visitMethod.visitMaxs(2, 1);
        visitMethod.visitEnd();
    }

    private static void a(ClassWriter classWriter, String str, String str2) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        visitMethod.visitCode();
        if (str2 != null) {
            visitMethod.visitTypeInsn(187, str);
            visitMethod.visitInsn(89);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitTypeInsn(192, str2);
            visitMethod.visitInsn(89);
            visitMethod.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME, "getClass", "()Ljava/lang/Class;");
            visitMethod.visitInsn(87);
            visitMethod.visitMethodInsn(183, str, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(L" + str2 + ";)V");
            visitMethod.visitInsn(176);
            visitMethod.visitMaxs(4, 2);
        } else {
            visitMethod.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
            visitMethod.visitInsn(89);
            visitMethod.visitLdcInsn("Not an inner class.");
            visitMethod.visitMethodInsn(183, "java/lang/UnsupportedOperationException", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/String;)V");
            visitMethod.visitInsn(191);
            visitMethod.visitMaxs(3, 2);
        }
        visitMethod.visitEnd();
    }
}
