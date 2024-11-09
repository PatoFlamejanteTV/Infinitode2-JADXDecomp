package net.bytebuddy.utility.visitor;

import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/visitor/ExceptionTableSensitiveMethodVisitor.class */
public abstract class ExceptionTableSensitiveMethodVisitor extends MethodVisitor {
    private boolean trigger;

    protected abstract void onAfterExceptionTable();

    /* JADX INFO: Access modifiers changed from: protected */
    public ExceptionTableSensitiveMethodVisitor(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
        this.trigger = true;
    }

    private void considerEndOfExceptionTable() {
        if (this.trigger) {
            this.trigger = false;
            onAfterExceptionTable();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        considerEndOfExceptionTable();
        onVisitFrame(i, i2, objArr, i3, objArr2);
    }

    protected void onVisitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        super.visitFrame(i, i2, objArr, i3, objArr2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLabel(Label label) {
        considerEndOfExceptionTable();
        onVisitLabel(label);
    }

    protected void onVisitLabel(Label label) {
        super.visitLabel(label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitIntInsn(int i, int i2) {
        considerEndOfExceptionTable();
        onVisitIntInsn(i, i2);
    }

    protected void onVisitIntInsn(int i, int i2) {
        super.visitIntInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitVarInsn(int i, int i2) {
        considerEndOfExceptionTable();
        onVisitVarInsn(i, i2);
    }

    protected void onVisitVarInsn(int i, int i2) {
        super.visitVarInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitTypeInsn(int i, String str) {
        considerEndOfExceptionTable();
        onVisitTypeInsn(i, str);
    }

    protected void onVisitTypeInsn(int i, String str) {
        super.visitTypeInsn(i, str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitFieldInsn(int i, String str, String str2, String str3) {
        considerEndOfExceptionTable();
        onVisitFieldInsn(i, str, str2, str3);
    }

    protected void onVisitFieldInsn(int i, String str, String str2, String str3) {
        super.visitFieldInsn(i, str, str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitMethodInsn(int i, String str, String str2, String str3) {
        considerEndOfExceptionTable();
        onVisitMethodInsn(i, str, str2, str3);
    }

    @Deprecated
    protected void onVisitMethodInsn(int i, String str, String str2, String str3) {
        super.visitMethodInsn(i, str, str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        considerEndOfExceptionTable();
        onVisitMethodInsn(i, str, str2, str3, z);
    }

    protected void onVisitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        super.visitMethodInsn(i, str, str2, str3, z);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        considerEndOfExceptionTable();
        onVisitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    protected void onVisitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        super.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitJumpInsn(int i, Label label) {
        considerEndOfExceptionTable();
        onVisitJumpInsn(i, label);
    }

    protected void onVisitJumpInsn(int i, Label label) {
        super.visitJumpInsn(i, label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLdcInsn(Object obj) {
        considerEndOfExceptionTable();
        onVisitLdcInsn(obj);
    }

    protected void onVisitLdcInsn(Object obj) {
        super.visitLdcInsn(obj);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitIincInsn(int i, int i2) {
        considerEndOfExceptionTable();
        onVisitIincInsn(i, i2);
    }

    protected void onVisitIincInsn(int i, int i2) {
        super.visitIincInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        considerEndOfExceptionTable();
        onVisitTableSwitchInsn(i, i2, label, labelArr);
    }

    protected void onVisitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        super.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        considerEndOfExceptionTable();
        onVisitLookupSwitchInsn(label, iArr, labelArr);
    }

    protected void onVisitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        super.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitMultiANewArrayInsn(String str, int i) {
        considerEndOfExceptionTable();
        onVisitMultiANewArrayInsn(str, i);
    }

    protected void onVisitMultiANewArrayInsn(String str, int i) {
        super.visitMultiANewArrayInsn(str, i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitInsn(int i) {
        considerEndOfExceptionTable();
        onVisitInsn(i);
    }

    protected void onVisitInsn(int i) {
        super.visitInsn(i);
    }
}
