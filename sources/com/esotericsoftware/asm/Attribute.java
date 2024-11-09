package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/Attribute.class */
public class Attribute {
    public final String type;

    /* renamed from: b, reason: collision with root package name */
    byte[] f1433b;

    /* renamed from: a, reason: collision with root package name */
    Attribute f1434a;

    /* JADX INFO: Access modifiers changed from: protected */
    public Attribute(String str) {
        this.type = str;
    }

    public boolean isUnknown() {
        return true;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Label[] getLabels() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute attribute = new Attribute(this.type);
        attribute.f1433b = new byte[i2];
        System.arraycopy(classReader.f1437b, i, attribute.f1433b, 0, i2);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byteVector.f1435a = this.f1433b;
        byteVector.f1436b = this.f1433b.length;
        return byteVector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = 0;
        Attribute attribute = this;
        while (true) {
            Attribute attribute2 = attribute;
            if (attribute2 == null) {
                return i;
            }
            i++;
            attribute = attribute2.f1434a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.f1434a) {
            classWriter.newUTF8(attribute.type);
            i4 += attribute.write(classWriter, bArr, i, i2, i3).f1436b + 6;
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3, ByteVector byteVector) {
        Attribute attribute = this;
        while (true) {
            Attribute attribute2 = attribute;
            if (attribute2 == null) {
                return;
            }
            ByteVector write = attribute2.write(classWriter, bArr, i, i2, i3);
            byteVector.putShort(classWriter.newUTF8(attribute2.type)).putInt(write.f1436b);
            byteVector.putByteArray(write.f1435a, 0, write.f1436b);
            attribute = attribute2.f1434a;
        }
    }
}
