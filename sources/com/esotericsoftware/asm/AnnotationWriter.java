package com.esotericsoftware.asm;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/AnnotationWriter.class */
public final class AnnotationWriter extends AnnotationVisitor {

    /* renamed from: a, reason: collision with root package name */
    private final ClassWriter f1431a;

    /* renamed from: b, reason: collision with root package name */
    private int f1432b;
    private final boolean c;
    private final ByteVector d;
    private final ByteVector e;
    private final int f;
    AnnotationWriter g;
    AnnotationWriter h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationWriter(ClassWriter classWriter, boolean z, ByteVector byteVector, ByteVector byteVector2, int i) {
        super(327680);
        this.f1431a = classWriter;
        this.c = z;
        this.d = byteVector;
        this.e = byteVector2;
        this.f = i;
    }

    @Override // com.esotericsoftware.asm.AnnotationVisitor
    public final void visit(String str, Object obj) {
        this.f1432b++;
        if (this.c) {
            this.d.putShort(this.f1431a.newUTF8(str));
        }
        if (obj instanceof String) {
            this.d.b(115, this.f1431a.newUTF8((String) obj));
            return;
        }
        if (obj instanceof Byte) {
            this.d.b(66, this.f1431a.a((int) ((Byte) obj).byteValue()).f1452a);
            return;
        }
        if (obj instanceof Boolean) {
            this.d.b(90, this.f1431a.a(((Boolean) obj).booleanValue() ? 1 : 0).f1452a);
            return;
        }
        if (obj instanceof Character) {
            this.d.b(67, this.f1431a.a((int) ((Character) obj).charValue()).f1452a);
            return;
        }
        if (obj instanceof Short) {
            this.d.b(83, this.f1431a.a((int) ((Short) obj).shortValue()).f1452a);
            return;
        }
        if (obj instanceof Type) {
            this.d.b(99, this.f1431a.newUTF8(((Type) obj).getDescriptor()));
            return;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            this.d.b(91, bArr.length);
            for (byte b2 : bArr) {
                this.d.b(66, this.f1431a.a((int) b2).f1452a);
            }
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            this.d.b(91, zArr.length);
            for (boolean z : zArr) {
                this.d.b(90, this.f1431a.a(z ? 1 : 0).f1452a);
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            this.d.b(91, sArr.length);
            for (short s : sArr) {
                this.d.b(83, this.f1431a.a((int) s).f1452a);
            }
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            this.d.b(91, cArr.length);
            for (char c : cArr) {
                this.d.b(67, this.f1431a.a((int) c).f1452a);
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            this.d.b(91, iArr.length);
            for (int i : iArr) {
                this.d.b(73, this.f1431a.a(i).f1452a);
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            this.d.b(91, jArr.length);
            for (long j : jArr) {
                this.d.b(74, this.f1431a.a(j).f1452a);
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            this.d.b(91, fArr.length);
            for (float f : fArr) {
                this.d.b(70, this.f1431a.a(f).f1452a);
            }
            return;
        }
        if (!(obj instanceof double[])) {
            Item a2 = this.f1431a.a(obj);
            this.d.b(".s.IFJDCS".charAt(a2.f1453b), a2.f1452a);
            return;
        }
        double[] dArr = (double[]) obj;
        this.d.b(91, dArr.length);
        for (double d : dArr) {
            this.d.b(68, this.f1431a.a(d).f1452a);
        }
    }

    @Override // com.esotericsoftware.asm.AnnotationVisitor
    public final void visitEnum(String str, String str2, String str3) {
        this.f1432b++;
        if (this.c) {
            this.d.putShort(this.f1431a.newUTF8(str));
        }
        this.d.b(101, this.f1431a.newUTF8(str2)).putShort(this.f1431a.newUTF8(str3));
    }

    @Override // com.esotericsoftware.asm.AnnotationVisitor
    public final AnnotationVisitor visitAnnotation(String str, String str2) {
        this.f1432b++;
        if (this.c) {
            this.d.putShort(this.f1431a.newUTF8(str));
        }
        this.d.b(64, this.f1431a.newUTF8(str2)).putShort(0);
        return new AnnotationWriter(this.f1431a, true, this.d, this.d, this.d.f1436b - 2);
    }

    @Override // com.esotericsoftware.asm.AnnotationVisitor
    public final AnnotationVisitor visitArray(String str) {
        this.f1432b++;
        if (this.c) {
            this.d.putShort(this.f1431a.newUTF8(str));
        }
        this.d.b(91, 0);
        return new AnnotationWriter(this.f1431a, false, this.d, this.d, this.d.f1436b - 2);
    }

    @Override // com.esotericsoftware.asm.AnnotationVisitor
    public final void visitEnd() {
        if (this.e != null) {
            byte[] bArr = this.e.f1435a;
            bArr[this.f] = (byte) (this.f1432b >>> 8);
            bArr[this.f + 1] = (byte) this.f1432b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = 0;
        AnnotationWriter annotationWriter = this;
        while (true) {
            AnnotationWriter annotationWriter2 = annotationWriter;
            if (annotationWriter2 == null) {
                return i;
            }
            i += annotationWriter2.d.f1436b;
            annotationWriter = annotationWriter2.g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        int i = 0;
        int i2 = 2;
        AnnotationWriter annotationWriter = null;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.g) {
            i++;
            i2 += annotationWriter2.d.f1436b;
            annotationWriter2.visitEnd();
            annotationWriter2.h = annotationWriter;
            annotationWriter = annotationWriter2;
        }
        byteVector.putInt(i2);
        byteVector.putShort(i);
        AnnotationWriter annotationWriter3 = annotationWriter;
        while (true) {
            AnnotationWriter annotationWriter4 = annotationWriter3;
            if (annotationWriter4 == null) {
                return;
            }
            byteVector.putByteArray(annotationWriter4.d.f1435a, 0, annotationWriter4.d.f1436b);
            annotationWriter3 = annotationWriter4.h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(AnnotationWriter[] annotationWriterArr, int i, ByteVector byteVector) {
        int length = 1 + (2 * (annotationWriterArr.length - i));
        for (int i2 = i; i2 < annotationWriterArr.length; i2++) {
            length += annotationWriterArr[i2] == null ? 0 : annotationWriterArr[i2].a();
        }
        byteVector.putInt(length).putByte(annotationWriterArr.length - i);
        for (int i3 = i; i3 < annotationWriterArr.length; i3++) {
            AnnotationWriter annotationWriter = null;
            int i4 = 0;
            for (AnnotationWriter annotationWriter2 = annotationWriterArr[i3]; annotationWriter2 != null; annotationWriter2 = annotationWriter2.g) {
                i4++;
                annotationWriter2.visitEnd();
                annotationWriter2.h = annotationWriter;
                annotationWriter = annotationWriter2;
            }
            byteVector.putShort(i4);
            AnnotationWriter annotationWriter3 = annotationWriter;
            while (true) {
                AnnotationWriter annotationWriter4 = annotationWriter3;
                if (annotationWriter4 != null) {
                    byteVector.putByteArray(annotationWriter4.d.f1435a, 0, annotationWriter4.d.f1436b);
                    annotationWriter3 = annotationWriter4.h;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i, TypePath typePath, ByteVector byteVector) {
        switch (i >>> 24) {
            case 0:
            case 1:
            case 22:
                byteVector.putShort(i >>> 16);
                break;
            case 19:
            case 20:
            case 21:
                byteVector.putByte(i >>> 24);
                break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                byteVector.putInt(i);
                break;
            default:
                byteVector.b(i >>> 24, (i & 16776960) >> 8);
                break;
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            byteVector.putByteArray(typePath.f1459a, typePath.f1460b, (typePath.f1459a[typePath.f1460b] << 1) + 1);
        }
    }
}
