package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/AnnotationVisitor.class */
public abstract class AnnotationVisitor {
    protected final int api;
    protected AnnotationVisitor av;

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotationVisitor(int i) {
        this(i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotationVisitor(int i, AnnotationVisitor annotationVisitor) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        if (i == 17432576) {
            Constants.a(this);
        }
        this.api = i;
        this.av = annotationVisitor;
    }

    public AnnotationVisitor getDelegate() {
        return this.av;
    }

    public void visit(String str, Object obj) {
        if (this.av != null) {
            this.av.visit(str, obj);
        }
    }

    public void visitEnum(String str, String str2, String str3) {
        if (this.av != null) {
            this.av.visitEnum(str, str2, str3);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, String str2) {
        if (this.av != null) {
            return this.av.visitAnnotation(str, str2);
        }
        return null;
    }

    public AnnotationVisitor visitArray(String str) {
        if (this.av != null) {
            return this.av.visitArray(str);
        }
        return null;
    }

    public void visitEnd() {
        if (this.av != null) {
            this.av.visitEnd();
        }
    }
}
