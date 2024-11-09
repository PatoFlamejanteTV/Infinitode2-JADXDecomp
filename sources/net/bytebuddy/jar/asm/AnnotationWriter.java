package net.bytebuddy.jar.asm;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/AnnotationWriter.class */
public final class AnnotationWriter extends AnnotationVisitor {
    private final SymbolTable symbolTable;
    private final boolean useNamedValues;
    private final ByteVector annotation;
    private final int numElementValuePairsOffset;
    private int numElementValuePairs;
    private final AnnotationWriter previousAnnotation;
    private AnnotationWriter nextAnnotation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationWriter(SymbolTable symbolTable, boolean z, ByteVector byteVector, AnnotationWriter annotationWriter) {
        super(589824);
        this.symbolTable = symbolTable;
        this.useNamedValues = z;
        this.annotation = byteVector;
        this.numElementValuePairsOffset = byteVector.f4134b == 0 ? -1 : byteVector.f4134b - 2;
        this.previousAnnotation = annotationWriter;
        if (annotationWriter != null) {
            annotationWriter.nextAnnotation = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnnotationWriter a(SymbolTable symbolTable, String str, AnnotationWriter annotationWriter) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(symbolTable.b(str)).putShort(0);
        return new AnnotationWriter(symbolTable, true, byteVector, annotationWriter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnnotationWriter a(SymbolTable symbolTable, int i, TypePath typePath, String str, AnnotationWriter annotationWriter) {
        ByteVector byteVector = new ByteVector();
        TypeReference.a(i, byteVector);
        TypePath.a(typePath, byteVector);
        byteVector.putShort(symbolTable.b(str)).putShort(0);
        return new AnnotationWriter(symbolTable, true, byteVector, annotationWriter);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public final void visit(String str, Object obj) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.b(str));
        }
        if (obj instanceof String) {
            this.annotation.b(115, this.symbolTable.b((String) obj));
            return;
        }
        if (obj instanceof Byte) {
            this.annotation.b(66, this.symbolTable.a((int) ((Byte) obj).byteValue()).f4146a);
            return;
        }
        if (obj instanceof Boolean) {
            this.annotation.b(90, this.symbolTable.a(((Boolean) obj).booleanValue() ? 1 : 0).f4146a);
            return;
        }
        if (obj instanceof Character) {
            this.annotation.b(67, this.symbolTable.a((int) ((Character) obj).charValue()).f4146a);
            return;
        }
        if (obj instanceof Short) {
            this.annotation.b(83, this.symbolTable.a((int) ((Short) obj).shortValue()).f4146a);
            return;
        }
        if (obj instanceof Type) {
            this.annotation.b(99, this.symbolTable.b(((Type) obj).getDescriptor()));
            return;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            this.annotation.b(91, bArr.length);
            for (byte b2 : bArr) {
                this.annotation.b(66, this.symbolTable.a((int) b2).f4146a);
            }
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            this.annotation.b(91, zArr.length);
            for (boolean z : zArr) {
                this.annotation.b(90, this.symbolTable.a(z ? 1 : 0).f4146a);
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            this.annotation.b(91, sArr.length);
            for (short s : sArr) {
                this.annotation.b(83, this.symbolTable.a((int) s).f4146a);
            }
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            this.annotation.b(91, cArr.length);
            for (char c : cArr) {
                this.annotation.b(67, this.symbolTable.a((int) c).f4146a);
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            this.annotation.b(91, iArr.length);
            for (int i : iArr) {
                this.annotation.b(73, this.symbolTable.a(i).f4146a);
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            this.annotation.b(91, jArr.length);
            for (long j : jArr) {
                this.annotation.b(74, this.symbolTable.a(j).f4146a);
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            this.annotation.b(91, fArr.length);
            for (float f : fArr) {
                this.annotation.b(70, this.symbolTable.a(f).f4146a);
            }
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            this.annotation.b(91, dArr.length);
            for (double d : dArr) {
                this.annotation.b(68, this.symbolTable.a(d).f4146a);
            }
            return;
        }
        Symbol a2 = this.symbolTable.a(obj);
        this.annotation.b(".s.IFJDCS".charAt(a2.f4147b), a2.f4146a);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public final void visitEnum(String str, String str2, String str3) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.b(str));
        }
        this.annotation.b(101, this.symbolTable.b(str2)).putShort(this.symbolTable.b(str3));
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public final AnnotationVisitor visitAnnotation(String str, String str2) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.b(str));
        }
        this.annotation.b(64, this.symbolTable.b(str2)).putShort(0);
        return new AnnotationWriter(this.symbolTable, true, this.annotation, null);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public final AnnotationVisitor visitArray(String str) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.b(str));
        }
        this.annotation.b(91, 0);
        return new AnnotationWriter(this.symbolTable, false, this.annotation, null);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public final void visitEnd() {
        if (this.numElementValuePairsOffset != -1) {
            byte[] bArr = this.annotation.f4133a;
            bArr[this.numElementValuePairsOffset] = (byte) (this.numElementValuePairs >>> 8);
            bArr[this.numElementValuePairsOffset + 1] = (byte) this.numElementValuePairs;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str) {
        if (str != null) {
            this.symbolTable.b(str);
        }
        int i = 8;
        AnnotationWriter annotationWriter = this;
        while (true) {
            AnnotationWriter annotationWriter2 = annotationWriter;
            if (annotationWriter2 != null) {
                i += annotationWriter2.annotation.f4134b;
                annotationWriter = annotationWriter2.previousAnnotation;
            } else {
                return i;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(AnnotationWriter annotationWriter, AnnotationWriter annotationWriter2, AnnotationWriter annotationWriter3, AnnotationWriter annotationWriter4) {
        int i = 0;
        if (annotationWriter != null) {
            i = 0 + annotationWriter.a("RuntimeVisibleAnnotations");
        }
        if (annotationWriter2 != null) {
            i += annotationWriter2.a("RuntimeInvisibleAnnotations");
        }
        if (annotationWriter3 != null) {
            i += annotationWriter3.a("RuntimeVisibleTypeAnnotations");
        }
        if (annotationWriter4 != null) {
            i += annotationWriter4.a("RuntimeInvisibleTypeAnnotations");
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, ByteVector byteVector) {
        int i2 = 2;
        int i3 = 0;
        AnnotationWriter annotationWriter = null;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.previousAnnotation) {
            annotationWriter2.visitEnd();
            i2 += annotationWriter2.annotation.f4134b;
            i3++;
            annotationWriter = annotationWriter2;
        }
        byteVector.putShort(i);
        byteVector.putInt(i2);
        byteVector.putShort(i3);
        AnnotationWriter annotationWriter3 = annotationWriter;
        while (true) {
            AnnotationWriter annotationWriter4 = annotationWriter3;
            if (annotationWriter4 != null) {
                byteVector.putByteArray(annotationWriter4.annotation.f4133a, 0, annotationWriter4.annotation.f4134b);
                annotationWriter3 = annotationWriter4.nextAnnotation;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(SymbolTable symbolTable, AnnotationWriter annotationWriter, AnnotationWriter annotationWriter2, AnnotationWriter annotationWriter3, AnnotationWriter annotationWriter4, ByteVector byteVector) {
        if (annotationWriter != null) {
            annotationWriter.a(symbolTable.b("RuntimeVisibleAnnotations"), byteVector);
        }
        if (annotationWriter2 != null) {
            annotationWriter2.a(symbolTable.b("RuntimeInvisibleAnnotations"), byteVector);
        }
        if (annotationWriter3 != null) {
            annotationWriter3.a(symbolTable.b("RuntimeVisibleTypeAnnotations"), byteVector);
        }
        if (annotationWriter4 != null) {
            annotationWriter4.a(symbolTable.b("RuntimeInvisibleTypeAnnotations"), byteVector);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(String str, AnnotationWriter[] annotationWriterArr, int i) {
        int i2 = 7 + (2 * i);
        for (int i3 = 0; i3 < i; i3++) {
            AnnotationWriter annotationWriter = annotationWriterArr[i3];
            i2 += annotationWriter == null ? 0 : annotationWriter.a(str) - 8;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i, AnnotationWriter[] annotationWriterArr, int i2, ByteVector byteVector) {
        int i3 = 1 + (2 * i2);
        for (int i4 = 0; i4 < i2; i4++) {
            AnnotationWriter annotationWriter = annotationWriterArr[i4];
            i3 += annotationWriter == null ? 0 : annotationWriter.a(null) - 8;
        }
        byteVector.putShort(i);
        byteVector.putInt(i3);
        byteVector.putByte(i2);
        for (int i5 = 0; i5 < i2; i5++) {
            AnnotationWriter annotationWriter2 = null;
            int i6 = 0;
            for (AnnotationWriter annotationWriter3 = annotationWriterArr[i5]; annotationWriter3 != null; annotationWriter3 = annotationWriter3.previousAnnotation) {
                annotationWriter3.visitEnd();
                i6++;
                annotationWriter2 = annotationWriter3;
            }
            byteVector.putShort(i6);
            AnnotationWriter annotationWriter4 = annotationWriter2;
            while (true) {
                AnnotationWriter annotationWriter5 = annotationWriter4;
                if (annotationWriter5 != null) {
                    byteVector.putByteArray(annotationWriter5.annotation.f4133a, 0, annotationWriter5.annotation.f4134b);
                    annotationWriter4 = annotationWriter5.nextAnnotation;
                }
            }
        }
    }
}
