package net.bytebuddy.jar.asm;

import net.bytebuddy.jar.asm.Attribute;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/FieldWriter.class */
public final class FieldWriter extends FieldVisitor {
    private final SymbolTable symbolTable;
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private int signatureIndex;
    private int constantValueIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private Attribute firstAttribute;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldWriter(SymbolTable symbolTable, int i, String str, String str2, String str3, Object obj) {
        super(589824);
        this.symbolTable = symbolTable;
        this.accessFlags = i;
        this.nameIndex = symbolTable.b(str);
        this.descriptorIndex = symbolTable.b(str2);
        if (str3 != null) {
            this.signatureIndex = symbolTable.b(str3);
        }
        if (obj != null) {
            this.constantValueIndex = symbolTable.a(obj).f4146a;
        }
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
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

    @Override // net.bytebuddy.jar.asm.FieldVisitor
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

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.f4132a = this.firstAttribute;
        this.firstAttribute = attribute;
    }

    @Override // net.bytebuddy.jar.asm.FieldVisitor
    public final void visitEnd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = 8;
        if (this.constantValueIndex != 0) {
            this.symbolTable.b("ConstantValue");
            i = 8 + 8;
        }
        int a2 = i + Attribute.a(this.symbolTable, this.accessFlags, this.signatureIndex) + AnnotationWriter.a(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.firstAttribute != null) {
            a2 += this.firstAttribute.a(this.symbolTable);
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        boolean z = this.symbolTable.b() < 49;
        boolean z2 = z;
        byteVector.putShort(this.accessFlags & ((z ? 4096 : 0) ^ (-1))).putShort(this.nameIndex).putShort(this.descriptorIndex);
        int i = 0;
        if (this.constantValueIndex != 0) {
            i = 0 + 1;
        }
        if ((this.accessFlags & 4096) != 0 && z2) {
            i++;
        }
        if (this.signatureIndex != 0) {
            i++;
        }
        if ((this.accessFlags & 131072) != 0) {
            i++;
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
        if (this.constantValueIndex != 0) {
            byteVector.putShort(this.symbolTable.b("ConstantValue")).putInt(2).putShort(this.constantValueIndex);
        }
        Attribute.a(this.symbolTable, this.accessFlags, this.signatureIndex, byteVector);
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
