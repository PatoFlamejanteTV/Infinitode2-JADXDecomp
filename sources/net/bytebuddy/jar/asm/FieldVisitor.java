package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/FieldVisitor.class */
public abstract class FieldVisitor {
    protected final int api;
    protected FieldVisitor fv;

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldVisitor(int i) {
        this(i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldVisitor(int i, FieldVisitor fieldVisitor) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        if (i == 17432576) {
            Constants.a(this);
        }
        this.api = i;
        this.fv = fieldVisitor;
    }

    public FieldVisitor getDelegate() {
        return this.fv;
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        if (this.fv != null) {
            return this.fv.visitAnnotation(str, z);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException("This feature requires ASM5");
        }
        if (this.fv != null) {
            return this.fv.visitTypeAnnotation(i, typePath, str, z);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.fv != null) {
            this.fv.visitAttribute(attribute);
        }
    }

    public void visitEnd() {
        if (this.fv != null) {
            this.fv.visitEnd();
        }
    }
}
