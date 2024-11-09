package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/ByteVector.class */
public class ByteVector {

    /* renamed from: a, reason: collision with root package name */
    byte[] f1435a;

    /* renamed from: b, reason: collision with root package name */
    int f1436b;

    public ByteVector() {
        this.f1435a = new byte[64];
    }

    public ByteVector(int i) {
        this.f1435a = new byte[i];
    }

    public ByteVector putByte(int i) {
        int i2 = this.f1436b;
        if (i2 + 1 > this.f1435a.length) {
            a(1);
        }
        this.f1435a[i2] = (byte) i;
        this.f1436b = i2 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteVector a(int i, int i2) {
        int i3 = this.f1436b;
        if (i3 + 2 > this.f1435a.length) {
            a(2);
        }
        byte[] bArr = this.f1435a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        bArr[i4] = (byte) i2;
        this.f1436b = i4 + 1;
        return this;
    }

    public ByteVector putShort(int i) {
        int i2 = this.f1436b;
        if (i2 + 2 > this.f1435a.length) {
            a(2);
        }
        byte[] bArr = this.f1435a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
        this.f1436b = i3 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteVector b(int i, int i2) {
        int i3 = this.f1436b;
        if (i3 + 3 > this.f1435a.length) {
            a(3);
        }
        byte[] bArr = this.f1435a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i5] = (byte) i2;
        this.f1436b = i5 + 1;
        return this;
    }

    public ByteVector putInt(int i) {
        int i2 = this.f1436b;
        if (i2 + 4 > this.f1435a.length) {
            a(4);
        }
        byte[] bArr = this.f1435a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
        this.f1436b = i5 + 1;
        return this;
    }

    public ByteVector putLong(long j) {
        int i = this.f1436b;
        if (i + 8 > this.f1435a.length) {
            a(8);
        }
        byte[] bArr = this.f1435a;
        int i2 = (int) (j >>> 32);
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        int i7 = (int) j;
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 >>> 24);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >>> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i7 >>> 8);
        bArr[i10] = (byte) i7;
        this.f1436b = i10 + 1;
        return this;
    }

    public ByteVector putUTF8(String str) {
        int length = str.length();
        if (length > 65535) {
            throw new IllegalArgumentException();
        }
        int i = this.f1436b;
        if (i + 2 + length > this.f1435a.length) {
            a(length + 2);
        }
        byte[] bArr = this.f1435a;
        int i2 = i + 1;
        bArr[i] = (byte) (length >>> 8);
        int i3 = i2 + 1;
        bArr[i2] = (byte) length;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt <= 0 || charAt > 127) {
                this.f1436b = i3;
                return c(str, i4, 65535);
            }
            int i5 = i3;
            i3++;
            bArr[i5] = (byte) charAt;
        }
        this.f1436b = i3;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteVector c(String str, int i, int i2) {
        int length = str.length();
        int i3 = i;
        for (int i4 = i; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            i3 = (charAt <= 0 || charAt > 127) ? charAt > 2047 ? i3 + 3 : i3 + 2 : i3 + 1;
        }
        if (i3 > i2) {
            throw new IllegalArgumentException();
        }
        int i5 = (this.f1436b - i) - 2;
        if (i5 >= 0) {
            this.f1435a[i5] = (byte) (i3 >>> 8);
            this.f1435a[i5 + 1] = (byte) i3;
        }
        if ((this.f1436b + i3) - i > this.f1435a.length) {
            a(i3 - i);
        }
        int i6 = this.f1436b;
        for (int i7 = i; i7 < length; i7++) {
            char charAt2 = str.charAt(i7);
            if (charAt2 > 0 && charAt2 <= 127) {
                int i8 = i6;
                i6++;
                this.f1435a[i8] = (byte) charAt2;
            } else if (charAt2 > 2047) {
                int i9 = i6;
                int i10 = i6 + 1;
                this.f1435a[i9] = (byte) (224 | ((charAt2 >> '\f') & 15));
                int i11 = i10 + 1;
                this.f1435a[i10] = (byte) (128 | ((charAt2 >> 6) & 63));
                i6 = i11 + 1;
                this.f1435a[i11] = (byte) (128 | (charAt2 & '?'));
            } else {
                int i12 = i6;
                int i13 = i6 + 1;
                this.f1435a[i12] = (byte) (192 | ((charAt2 >> 6) & 31));
                i6 = i13 + 1;
                this.f1435a[i13] = (byte) (128 | (charAt2 & '?'));
            }
        }
        this.f1436b = i6;
        return this;
    }

    public ByteVector putByteArray(byte[] bArr, int i, int i2) {
        if (this.f1436b + i2 > this.f1435a.length) {
            a(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.f1435a, this.f1436b, i2);
        }
        this.f1436b += i2;
        return this;
    }

    private void a(int i) {
        int length = 2 * this.f1435a.length;
        int i2 = this.f1436b + i;
        byte[] bArr = new byte[length > i2 ? length : i2];
        System.arraycopy(this.f1435a, 0, bArr, 0, this.f1436b);
        this.f1435a = bArr;
    }
}
