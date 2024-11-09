package com.prineside.tdi2.ibxm;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Data.class */
public class Data {

    /* renamed from: a, reason: collision with root package name */
    private int f2202a;

    /* renamed from: b, reason: collision with root package name */
    private byte[] f2203b;
    private InputStream c;

    public Data(InputStream inputStream) {
        this.f2202a = 65536;
        this.f2203b = new byte[this.f2202a];
        this.c = inputStream;
        a(this.c, this.f2203b, 0, this.f2202a);
    }

    public Data(byte[] bArr) {
        this.f2202a = bArr.length;
        this.f2203b = bArr;
    }

    public byte sByte(int i) {
        a(i, 1);
        return this.f2203b[i];
    }

    public int uByte(int i) {
        a(i, 1);
        return this.f2203b[i] & 255;
    }

    public int ubeShort(int i) {
        a(i, 2);
        return ((this.f2203b[i] & 255) << 8) | (this.f2203b[i + 1] & 255);
    }

    public int uleShort(int i) {
        a(i, 2);
        return (this.f2203b[i] & 255) | ((this.f2203b[i + 1] & 255) << 8);
    }

    public int uleInt(int i) {
        a(i, 4);
        return (this.f2203b[i] & 255) | ((this.f2203b[i + 1] & 255) << 8) | ((this.f2203b[i + 2] & 255) << 16) | ((this.f2203b[i + 3] & Byte.MAX_VALUE) << 24);
    }

    public String strLatin1(int i, int i2) {
        a(i, i2);
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.f2203b[i + i3] & 255;
            cArr[i3] = i4 < 32 ? ' ' : (char) i4;
        }
        return new String(cArr);
    }

    public String strCp850(int i, int i2) {
        a(i, i2);
        try {
            char[] charArray = new String(this.f2203b, i, i2, "Cp850").toCharArray();
            for (int i3 = 0; i3 < charArray.length; i3++) {
                charArray[i3] = charArray[i3] < ' ' ? ' ' : charArray[i3];
            }
            return new String(charArray);
        } catch (UnsupportedEncodingException unused) {
            return strLatin1(i, i2);
        }
    }

    public short[] samS8(int i, int i2) {
        a(i, i2);
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i3] = (short) (this.f2203b[i + i3] << 8);
        }
        return sArr;
    }

    public short[] samS8D(int i, int i2) {
        a(i, i2);
        short[] sArr = new short[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += this.f2203b[i + i4];
            sArr[i4] = (short) (i3 << 8);
        }
        return sArr;
    }

    public short[] samU8(int i, int i2) {
        a(i, i2);
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i3] = (short) (((this.f2203b[i + i3] & 255) - 128) << 8);
        }
        return sArr;
    }

    public short[] samS16(int i, int i2) {
        a(i, i2 << 1);
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i3] = (short) ((this.f2203b[i + (i3 << 1)] & 255) | (this.f2203b[(i + (i3 << 1)) + 1] << 8));
        }
        return sArr;
    }

    public short[] samS16D(int i, int i2) {
        a(i, i2 << 1);
        short[] sArr = new short[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += (this.f2203b[i + (i4 << 1)] & 255) | (this.f2203b[(i + (i4 << 1)) + 1] << 8);
            sArr[i4] = (short) i3;
        }
        return sArr;
    }

    public short[] samU16(int i, int i2) {
        a(i, i2 << 1);
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i3] = (short) (((this.f2203b[i + (i3 << 1)] & 255) | ((this.f2203b[(i + (i3 << 1)) + 1] & 255) << 8)) - 32768);
        }
        return sArr;
    }

    private void a(int i, int i2) {
        while (i + i2 > this.f2202a) {
            int i3 = this.f2202a << 1;
            byte[] bArr = new byte[i3];
            System.arraycopy(this.f2203b, 0, bArr, 0, this.f2202a);
            if (this.c != null) {
                InputStream inputStream = this.c;
                int i4 = this.f2202a;
                a(inputStream, bArr, i4, i3 - i4);
            }
            this.f2202a = i3;
            this.f2203b = bArr;
        }
    }

    private static void a(InputStream inputStream, byte[] bArr, int i, int i2) {
        int i3 = 1;
        int i4 = i + i2;
        while (i3 > 0) {
            i3 = inputStream.read(bArr, i, i4 - i);
            i += i3;
        }
    }
}
