package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ByteVector.class */
public class ByteVector {

    /* renamed from: a, reason: collision with root package name */
    byte[] f4133a;

    /* renamed from: b, reason: collision with root package name */
    int f4134b;

    public ByteVector() {
        this.f4133a = new byte[64];
    }

    public ByteVector(int i) {
        this.f4133a = new byte[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteVector(byte[] bArr) {
        this.f4133a = bArr;
        this.f4134b = bArr.length;
    }

    public int size() {
        return this.f4134b;
    }

    public ByteVector putByte(int i) {
        int i2 = this.f4134b;
        if (i2 + 1 > this.f4133a.length) {
            enlarge(1);
        }
        this.f4133a[i2] = (byte) i;
        this.f4134b = i2 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteVector a(int i, int i2) {
        int i3 = this.f4134b;
        if (i3 + 2 > this.f4133a.length) {
            enlarge(2);
        }
        byte[] bArr = this.f4133a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        bArr[i4] = (byte) i2;
        this.f4134b = i4 + 1;
        return this;
    }

    public ByteVector putShort(int i) {
        int i2 = this.f4134b;
        if (i2 + 2 > this.f4133a.length) {
            enlarge(2);
        }
        byte[] bArr = this.f4133a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
        this.f4134b = i3 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteVector b(int i, int i2) {
        int i3 = this.f4134b;
        if (i3 + 3 > this.f4133a.length) {
            enlarge(3);
        }
        byte[] bArr = this.f4133a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i5] = (byte) i2;
        this.f4134b = i5 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteVector a(int i, int i2, int i3) {
        int i4 = this.f4134b;
        if (i4 + 4 > this.f4133a.length) {
            enlarge(4);
        }
        byte[] bArr = this.f4133a;
        int i5 = i4 + 1;
        bArr[i4] = 15;
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        int i7 = i6 + 1;
        bArr[i6] = (byte) (i3 >>> 8);
        bArr[i7] = (byte) i3;
        this.f4134b = i7 + 1;
        return this;
    }

    public ByteVector putInt(int i) {
        int i2 = this.f4134b;
        if (i2 + 4 > this.f4133a.length) {
            enlarge(4);
        }
        byte[] bArr = this.f4133a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
        this.f4134b = i5 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteVector b(int i, int i2, int i3) {
        int i4 = this.f4134b;
        if (i4 + 5 > this.f4133a.length) {
            enlarge(5);
        }
        byte[] bArr = this.f4133a;
        int i5 = i4 + 1;
        bArr[i4] = (byte) i;
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i2 >>> 8);
        int i7 = i6 + 1;
        bArr[i6] = (byte) i2;
        int i8 = i7 + 1;
        bArr[i7] = (byte) (i3 >>> 8);
        bArr[i8] = (byte) i3;
        this.f4134b = i8 + 1;
        return this;
    }

    public ByteVector putLong(long j) {
        int i = this.f4134b;
        if (i + 8 > this.f4133a.length) {
            enlarge(8);
        }
        byte[] bArr = this.f4133a;
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
        this.f4134b = i10 + 1;
        return this;
    }

    public ByteVector putUTF8(String str) {
        int length = str.length();
        if (length > 65535) {
            throw new IllegalArgumentException("UTF8 string too large");
        }
        int i = this.f4134b;
        if (i + 2 + length > this.f4133a.length) {
            enlarge(length + 2);
        }
        byte[] bArr = this.f4133a;
        int i2 = i + 1;
        bArr[i] = (byte) (length >>> 8);
        int i3 = i2 + 1;
        bArr[i2] = (byte) length;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt > 0 && charAt <= 127) {
                int i5 = i3;
                i3++;
                bArr[i5] = (byte) charAt;
            } else {
                this.f4134b = i3;
                return a(str, i4, 65535);
            }
        }
        this.f4134b = i3;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteVector a(String str, int i, int i2) {
        int length = str.length();
        int i3 = i;
        for (int i4 = i; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt > 0 && charAt <= 127) {
                i3++;
            } else if (charAt <= 2047) {
                i3 += 2;
            } else {
                i3 += 3;
            }
        }
        if (i3 > i2) {
            throw new IllegalArgumentException("UTF8 string too large");
        }
        int i5 = (this.f4134b - i) - 2;
        if (i5 >= 0) {
            this.f4133a[i5] = (byte) (i3 >>> 8);
            this.f4133a[i5 + 1] = (byte) i3;
        }
        if ((this.f4134b + i3) - i > this.f4133a.length) {
            enlarge(i3 - i);
        }
        int i6 = this.f4134b;
        for (int i7 = i; i7 < length; i7++) {
            char charAt2 = str.charAt(i7);
            if (charAt2 > 0 && charAt2 <= 127) {
                int i8 = i6;
                i6++;
                this.f4133a[i8] = (byte) charAt2;
            } else if (charAt2 <= 2047) {
                int i9 = i6;
                int i10 = i6 + 1;
                this.f4133a[i9] = (byte) (192 | ((charAt2 >> 6) & 31));
                i6 = i10 + 1;
                this.f4133a[i10] = (byte) (128 | (charAt2 & '?'));
            } else {
                int i11 = i6;
                int i12 = i6 + 1;
                this.f4133a[i11] = (byte) (224 | ((charAt2 >> '\f') & 15));
                int i13 = i12 + 1;
                this.f4133a[i12] = (byte) (128 | ((charAt2 >> 6) & 63));
                i6 = i13 + 1;
                this.f4133a[i13] = (byte) (128 | (charAt2 & '?'));
            }
        }
        this.f4134b = i6;
        return this;
    }

    public ByteVector putByteArray(byte[] bArr, int i, int i2) {
        if (this.f4134b + i2 > this.f4133a.length) {
            enlarge(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.f4133a, this.f4134b, i2);
        }
        this.f4134b += i2;
        return this;
    }

    private void enlarge(int i) {
        if (this.f4134b > this.f4133a.length) {
            throw new AssertionError("Internal error");
        }
        int length = 2 * this.f4133a.length;
        int i2 = this.f4134b + i;
        byte[] bArr = new byte[length > i2 ? length : i2];
        System.arraycopy(this.f4133a, 0, bArr, 0, this.f4134b);
        this.f4133a = bArr;
    }
}
