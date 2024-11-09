package net.bytebuddy.jar.asm;

import net.bytebuddy.jar.asm.Attribute;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/RecordComponentWriter.class */
public final class RecordComponentWriter extends RecordComponentVisitor {
    private final SymbolTable symbolTable;
    private final int nameIndex;
    private final int descriptorIndex;
    private int signatureIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private Attribute firstAttribute;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordComponentWriter(SymbolTable symbolTable, String str, String str2, String str3) {
        super(589824);
        this.symbolTable = symbolTable;
        this.nameIndex = symbolTable.b(str);
        this.descriptorIndex = symbolTable.b(str2);
        if (str3 != null) {
            this.signatureIndex = symbolTable.b(str3);
        }
    }

    @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        if (z) {
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, str, this.lastRuntimeVisibleAnnotation);
            this.lastRuntimeVisibleAnnotation = a2;
            return a2;
        }
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, str, this.lastRuntimeInvisibleAnnotation);
        this.lastRuntimeInvisibleAnnotation = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (z) {
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, i, typePath, str, this.lastRuntimeVisibleTypeAnnotation);
            this.lastRuntimeVisibleTypeAnnotation = a2;
            return a2;
        }
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, i, typePath, str, this.lastRuntimeInvisibleTypeAnnotation);
        this.lastRuntimeInvisibleTypeAnnotation = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.f4132a = this.firstAttribute;
        this.firstAttribute = attribute;
    }

    @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
    public final void visitEnd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int a2 = 6 + Attribute.a(this.symbolTable, 0, this.signatureIndex) + AnnotationWriter.a(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.firstAttribute != null) {
            a2 += this.firstAttribute.a(this.symbolTable);
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        byteVector.putShort(this.nameIndex).putShort(this.descriptorIndex);
        int i = 0;
        if (this.signatureIndex != 0) {
            i = 0 + 1;
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            i++;
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            i++;
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            i++;
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            i++;
        }
        if (this.firstAttribute != null) {
            i += this.firstAttribute.a();
        }
        byteVector.putShort(i);
        Attribute.a(this.symbolTable, 0, this.signatureIndex, byteVector);
        AnnotationWriter.a(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        if (this.firstAttribute != null) {
            this.firstAttribute.a(this.symbolTable, byteVector);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Attribute.Set set) {
        set.a(this.firstAttribute);
    }
}
