package com.esotericsoftware.asm;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/FieldWriter.class */
public final class FieldWriter extends FieldVisitor {

    /* renamed from: b, reason: collision with root package name */
    private final ClassWriter f1445b;
    private final int c;
    private final int d;
    private final int e;
    private int f;
    private int g;
    private AnnotationWriter h;
    private AnnotationWriter i;
    private AnnotationWriter k;
    private AnnotationWriter l;
    private Attribute j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldWriter(ClassWriter classWriter, int i, String str, String str2, String str3, Object obj) {
        super(327680);
        if (classWriter.B == null) {
            classWriter.B = this;
        } else {
            classWriter.C.fv = this;
        }
        classWriter.C = this;
        this.f1445b = classWriter;
        this.c = i;
        this.d = classWriter.newUTF8(str);
        this.e = classWriter.newUTF8(str2);
        if (str3 != null) {
            this.f = classWriter.newUTF8(str3);
        }
        if (obj != null) {
            this.g = classWriter.a(obj).f1452a;
        }
    }

    @Override // com.esotericsoftware.asm.FieldVisitor
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.f1445b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1445b, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.g = this.h;
            this.h = annotationWriter;
        } else {
            annotationWriter.g = this.i;
            this.i = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.FieldVisitor
    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i, typePath, byteVector);
        byteVector.putShort(this.f1445b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1445b, true, byteVector, byteVector, byteVector.f1436b - 2);
        if (z) {
            annotationWriter.g = this.k;
            this.k = annotationWriter;
        } else {
            annotationWriter.g = this.l;
            this.l = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.FieldVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.f1434a = this.j;
        this.j = attribute;
    }

    @Override // com.esotericsoftware.asm.FieldVisitor
    public final void visitEnd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = 8;
        if (this.g != 0) {
            this.f1445b.newUTF8("ConstantValue");
            i = 8 + 8;
        }
        if ((this.c & 4096) != 0 && ((this.f1445b.f1440b & 65535) < 49 || (this.c & 262144) != 0)) {
            this.f1445b.newUTF8("Synthetic");
            i += 6;
        }
        if ((this.c & 131072) != 0) {
            this.f1445b.newUTF8("Deprecated");
            i += 6;
        }
        if (this.f != 0) {
            this.f1445b.newUTF8("Signature");
            i += 8;
        }
        if (this.h != null) {
            this.f1445b.newUTF8("RuntimeVisibleAnnotations");
            i += 8 + this.h.a();
        }
        if (this.i != null) {
            this.f1445b.newUTF8("RuntimeInvisibleAnnotations");
            i += 8 + this.i.a();
        }
        if (this.k != null) {
            this.f1445b.newUTF8("RuntimeVisibleTypeAnnotations");
            i += 8 + this.k.a();
        }
        if (this.l != null) {
            this.f1445b.newUTF8("RuntimeInvisibleTypeAnnotations");
            i += 8 + this.l.a();
        }
        if (this.j != null) {
            i += this.j.a(this.f1445b, null, 0, -1, -1);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        byteVector.putShort(this.c & ((393216 | ((this.c & 262144) / 64)) ^ (-1))).putShort(this.d).putShort(this.e);
        int i = 0;
        if (this.g != 0) {
            i = 0 + 1;
        }
        if ((this.c & 4096) != 0 && ((this.f1445b.f1440b & 65535) < 49 || (this.c & 262144) != 0)) {
            i++;
        }
        if ((this.c & 131072) != 0) {
            i++;
        }
        if (this.f != 0) {
            i++;
        }
        if (this.h != null) {
            i++;
        }
        if (this.i != null) {
            i++;
        }
        if (this.k != null) {
            i++;
        }
        if (this.l != null) {
            i++;
        }
        if (this.j != null) {
            i += this.j.a();
        }
        byteVector.putShort(i);
        if (this.g != 0) {
            byteVector.putShort(this.f1445b.newUTF8("ConstantValue"));
            byteVector.putInt(2).putShort(this.g);
        }
        if ((this.c & 4096) != 0 && ((this.f1445b.f1440b & 65535) < 49 || (this.c & 262144) != 0)) {
            byteVector.putShort(this.f1445b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.c & 131072) != 0) {
            byteVector.putShort(this.f1445b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.f != 0) {
            byteVector.putShort(this.f1445b.newUTF8("Signature"));
            byteVector.putInt(2).putShort(this.f);
        }
        if (this.h != null) {
            byteVector.putShort(this.f1445b.newUTF8("RuntimeVisibleAnnotations"));
            this.h.a(byteVector);
        }
        if (this.i != null) {
            byteVector.putShort(this.f1445b.newUTF8("RuntimeInvisibleAnnotations"));
            this.i.a(byteVector);
        }
        if (this.k != null) {
            byteVector.putShort(this.f1445b.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.k.a(byteVector);
        }
        if (this.l != null) {
            byteVector.putShort(this.f1445b.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.l.a(byteVector);
        }
        if (this.j != null) {
            this.j.a(this.f1445b, null, 0, -1, -1, byteVector);
        }
    }
}
