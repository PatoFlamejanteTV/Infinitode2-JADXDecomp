package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Attribute.class */
public class Attribute {
    public final String type;
    private byte[] content;

    /* renamed from: a, reason: collision with root package name */
    Attribute f4132a;

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

    protected Label[] getLabels() {
        return new Label[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute attribute = new Attribute(this.type);
        attribute.content = new byte[i2];
        System.arraycopy(classReader.f4136a, i, attribute.content, 0, i2);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        return new ByteVector(this.content);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = 0;
        Attribute attribute = this;
        while (true) {
            Attribute attribute2 = attribute;
            if (attribute2 != null) {
                i++;
                attribute = attribute2.f4132a;
            } else {
                return i;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(SymbolTable symbolTable) {
        return a(symbolTable, null, 0, -1, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(SymbolTable symbolTable, byte[] bArr, int i, int i2, int i3) {
        ClassWriter classWriter = symbolTable.f4148a;
        int i4 = 0;
        Attribute attribute = this;
        while (true) {
            Attribute attribute2 = attribute;
            if (attribute2 != null) {
                symbolTable.b(attribute2.type);
                i4 += 6 + attribute2.write(classWriter, bArr, i, i2, i3).f4134b;
                attribute = attribute2.f4132a;
            } else {
                return i4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(SymbolTable symbolTable, int i, int i2) {
        int i3 = 0;
        if ((i & 4096) != 0 && symbolTable.b() < 49) {
            symbolTable.b("Synthetic");
            i3 = 0 + 6;
        }
        if (i2 != 0) {
            symbolTable.b("Signature");
            i3 += 8;
        }
        if ((i & 131072) != 0) {
            symbolTable.b("Deprecated");
            i3 += 6;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SymbolTable symbolTable, ByteVector byteVector) {
        a(symbolTable, null, 0, -1, -1, byteVector);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SymbolTable symbolTable, byte[] bArr, int i, int i2, int i3, ByteVector byteVector) {
        ClassWriter classWriter = symbolTable.f4148a;
        Attribute attribute = this;
        while (true) {
            Attribute attribute2 = attribute;
            if (attribute2 != null) {
                ByteVector write = attribute2.write(classWriter, bArr, i, i2, i3);
                byteVector.putShort(symbolTable.b(attribute2.type)).putInt(write.f4134b);
                byteVector.putByteArray(write.f4133a, 0, write.f4134b);
                attribute = attribute2.f4132a;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(SymbolTable symbolTable, int i, int i2, ByteVector byteVector) {
        if ((i & 4096) != 0 && symbolTable.b() < 49) {
            byteVector.putShort(symbolTable.b("Synthetic")).putInt(0);
        }
        if (i2 != 0) {
            byteVector.putShort(symbolTable.b("Signature")).putInt(2).putShort(i2);
        }
        if ((i & 131072) != 0) {
            byteVector.putShort(symbolTable.b("Deprecated")).putInt(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Attribute$Set.class */
    public static final class Set {
        private static final int SIZE_INCREMENT = 6;
        private int size;
        private Attribute[] data = new Attribute[6];

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void a(Attribute attribute) {
            Attribute attribute2 = attribute;
            while (true) {
                Attribute attribute3 = attribute2;
                if (attribute3 != null) {
                    if (!contains(attribute3)) {
                        add(attribute3);
                    }
                    attribute2 = attribute3.f4132a;
                } else {
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Attribute[] a() {
            Attribute[] attributeArr = new Attribute[this.size];
            System.arraycopy(this.data, 0, attributeArr, 0, this.size);
            return attributeArr;
        }

        private boolean contains(Attribute attribute) {
            for (int i = 0; i < this.size; i++) {
                if (this.data[i].type.equals(attribute.type)) {
                    return true;
                }
            }
            return false;
        }

        private void add(Attribute attribute) {
            if (this.size >= this.data.length) {
                Attribute[] attributeArr = new Attribute[this.data.length + 6];
                System.arraycopy(this.data, 0, attributeArr, 0, this.size);
                this.data = attributeArr;
            }
            Attribute[] attributeArr2 = this.data;
            int i = this.size;
            this.size = i + 1;
            attributeArr2[i] = attribute;
        }
    }
}
