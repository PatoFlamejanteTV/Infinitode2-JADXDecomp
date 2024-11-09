package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/ClassVisitor.class */
public abstract class ClassVisitor {
    protected final int api;
    protected ClassVisitor cv;

    public ClassVisitor(int i) {
        this(i, null);
    }

    public ClassVisitor(int i, ClassVisitor classVisitor) {
        if (i != 262144 && i != 327680) {
            throw new IllegalArgumentException();
        }
        this.api = i;
        this.cv = classVisitor;
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (this.cv != null) {
            this.cv.visit(i, i2, str, str2, str3, strArr);
        }
    }

    public void visitSource(String str, String str2) {
        if (this.cv != null) {
            this.cv.visitSource(str, str2);
        }
    }

    public void visitOuterClass(String str, String str2, String str3) {
        if (this.cv != null) {
            this.cv.visitOuterClass(str, str2, str3);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        if (this.cv != null) {
            return this.cv.visitAnnotation(str, z);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.cv != null) {
            return this.cv.visitTypeAnnotation(i, typePath, str, z);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.cv != null) {
            this.cv.visitAttribute(attribute);
        }
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.cv != null) {
            this.cv.visitInnerClass(str, str2, str3, i);
        }
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        if (this.cv != null) {
            return this.cv.visitField(i, str, str2, str3, obj);
        }
        return null;
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        if (this.cv != null) {
            return this.cv.visitMethod(i, str, str2, str3, strArr);
        }
        return null;
    }

    public void visitEnd() {
        if (this.cv != null) {
            this.cv.visitEnd();
        }
    }
}
