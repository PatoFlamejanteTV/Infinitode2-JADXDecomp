package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/TypePath.class */
public class TypePath {
    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int WILDCARD_BOUND = 2;
    public static final int TYPE_ARGUMENT = 3;

    /* renamed from: a, reason: collision with root package name */
    byte[] f1459a;

    /* renamed from: b, reason: collision with root package name */
    int f1460b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypePath(byte[] bArr, int i) {
        this.f1459a = bArr;
        this.f1460b = i;
    }

    public int getLength() {
        return this.f1459a[this.f1460b];
    }

    public int getStep(int i) {
        return this.f1459a[this.f1460b + (2 * i) + 1];
    }

    public int getStepArgument(int i) {
        return this.f1459a[this.f1460b + (2 * i) + 2];
    }

    public static TypePath fromString(String str) {
        char charAt;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        ByteVector byteVector = new ByteVector(length);
        byteVector.putByte(0);
        int i = 0;
        while (i < length) {
            int i2 = i;
            i++;
            char charAt2 = str.charAt(i2);
            if (charAt2 == '[') {
                byteVector.a(0, 0);
            } else if (charAt2 == '.') {
                byteVector.a(1, 0);
            } else if (charAt2 == '*') {
                byteVector.a(2, 0);
            } else if (charAt2 >= '0' && charAt2 <= '9') {
                int i3 = charAt2 - '0';
                while (i < length && (charAt = str.charAt(i)) >= '0' && charAt <= '9') {
                    i3 = ((i3 * 10) + charAt) - 48;
                    i++;
                }
                if (i < length && str.charAt(i) == ';') {
                    i++;
                }
                byteVector.a(3, i3);
            }
        }
        byteVector.f1435a[0] = (byte) (byteVector.f1436b / 2);
        return new TypePath(byteVector.f1435a, 0);
    }

    public String toString() {
        int length = getLength();
        StringBuffer stringBuffer = new StringBuffer(length << 1);
        for (int i = 0; i < length; i++) {
            switch (getStep(i)) {
                case 0:
                    stringBuffer.append('[');
                    break;
                case 1:
                    stringBuffer.append('.');
                    break;
                case 2:
                    stringBuffer.append('*');
                    break;
                case 3:
                    stringBuffer.append(getStepArgument(i)).append(';');
                    break;
                default:
                    stringBuffer.append('_');
                    break;
            }
        }
        return stringBuffer.toString();
    }
}
