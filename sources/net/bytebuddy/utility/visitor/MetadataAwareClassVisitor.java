package net.bytebuddy.utility.visitor;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.RecordComponentVisitor;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/visitor/MetadataAwareClassVisitor.class */
public abstract class MetadataAwareClassVisitor extends ClassVisitor {
    private boolean triggerNestHost;
    private boolean triggerOuterClass;
    private boolean triggerAttributes;

    /* JADX INFO: Access modifiers changed from: protected */
    public MetadataAwareClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
        this.triggerNestHost = true;
        this.triggerOuterClass = true;
        this.triggerAttributes = true;
    }

    protected void onNestHost() {
    }

    protected void onOuterType() {
    }

    protected void onAfterAttributes() {
    }

    private void considerTriggerNestHost() {
        if (this.triggerNestHost) {
            this.triggerNestHost = false;
            onNestHost();
        }
    }

    private void considerTriggerOuterClass() {
        if (this.triggerOuterClass) {
            this.triggerOuterClass = false;
            onOuterType();
        }
    }

    private void considerTriggerAfterAttributes() {
        if (this.triggerAttributes) {
            this.triggerAttributes = false;
            onAfterAttributes();
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitNestHost(String str) {
        this.triggerNestHost = false;
        onVisitNestHost(str);
    }

    protected void onVisitNestHost(String str) {
        super.visitNestHost(str);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitOuterClass(String str, @MaybeNull String str2, @MaybeNull String str3) {
        considerTriggerNestHost();
        this.triggerOuterClass = false;
        onVisitOuterClass(str, str2, str3);
    }

    protected void onVisitOuterClass(String str, @MaybeNull String str2, @MaybeNull String str3) {
        super.visitOuterClass(str, str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitPermittedSubclass(String str) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitPermittedSubclass(str);
    }

    protected void onVisitPermittedSubclass(String str) {
        super.visitPermittedSubclass(str);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    @MaybeNull
    public RecordComponentVisitor visitRecordComponent(String str, String str2, @MaybeNull String str3) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        return onVisitRecordComponent(str, str2, str3);
    }

    @MaybeNull
    protected RecordComponentVisitor onVisitRecordComponent(String str, String str2, @MaybeNull String str3) {
        return super.visitRecordComponent(str, str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    @MaybeNull
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        return onVisitAnnotation(str, z);
    }

    @MaybeNull
    protected AnnotationVisitor onVisitAnnotation(String str, boolean z) {
        return super.visitAnnotation(str, z);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    @MaybeNull
    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        return onVisitTypeAnnotation(i, typePath, str, z);
    }

    @MaybeNull
    protected AnnotationVisitor onVisitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        return super.visitTypeAnnotation(i, typePath, str, z);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitAttribute(Attribute attribute) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        onVisitAttribute(attribute);
    }

    protected void onVisitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitNestMember(String str) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitNestMember(str);
    }

    protected void onVisitNestMember(String str) {
        super.visitNestMember(str);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitInnerClass(String str, @MaybeNull String str2, @MaybeNull String str3, int i) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitInnerClass(str, str2, str3, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onVisitInnerClass(String str, @MaybeNull String str2, @MaybeNull String str3, int i) {
        super.visitInnerClass(str, str2, str3, i);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    @MaybeNull
    public final FieldVisitor visitField(int i, String str, String str2, @MaybeNull String str3, @MaybeNull Object obj) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        return onVisitField(i, str, str2, str3, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MaybeNull
    public FieldVisitor onVisitField(int i, String str, String str2, @MaybeNull String str3, @MaybeNull Object obj) {
        return super.visitField(i, str, str2, str3, obj);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    @MaybeNull
    public final MethodVisitor visitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        return onVisitMethod(i, str, str2, str3, strArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MaybeNull
    public MethodVisitor onVisitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
        return super.visitMethod(i, str, str2, str3, strArr);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitEnd() {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitEnd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onVisitEnd() {
        super.visitEnd();
    }
}
