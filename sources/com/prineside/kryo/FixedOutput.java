package com.prineside.kryo;

import com.esotericsoftware.kryo.KryoException;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/prineside/kryo/FixedOutput.class */
public class FixedOutput extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private int f1464a;

    /* renamed from: b, reason: collision with root package name */
    private long f1465b;
    private int c;
    private int d;
    private byte[] e;
    private OutputStream f;

    public FixedOutput() {
    }

    public FixedOutput(int i) {
        this(i, i);
    }

    public FixedOutput(int i, int i2) {
        if (i2 < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + i2);
        }
        this.d = i;
        this.f1464a = i2 == -1 ? Integer.MAX_VALUE : i2;
        this.e = new byte[i];
    }

    public FixedOutput(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public FixedOutput(byte[] bArr, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        setBuffer(bArr, i);
    }

    public FixedOutput(OutputStream outputStream) {
        this(4096, 4096);
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.f = outputStream;
    }

    public FixedOutput(OutputStream outputStream, int i) {
        this(i, i);
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.f = outputStream;
    }

    public OutputStream getOutputStream() {
        return this.f;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.f = outputStream;
        this.c = 0;
        this.f1465b = 0L;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void setBuffer(byte[] bArr) {
        setBuffer(bArr, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void setBuffer(byte[] bArr, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        if (i < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + i);
        }
        this.e = bArr;
        this.f1464a = i == -1 ? Integer.MAX_VALUE : i;
        this.d = bArr.length;
        this.c = 0;
        this.f1465b = 0L;
        this.f = null;
    }

    public byte[] getBuffer() {
        return this.e;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.c];
        System.arraycopy(this.e, 0, bArr, 0, this.c);
        return bArr;
    }

    public final int position() {
        return this.c;
    }

    public void setPosition(int i) {
        this.c = i;
    }

    public final long total() {
        return this.f1465b + this.c;
    }

    public void clear() {
        this.c = 0;
        this.f1465b = 0L;
    }

    private boolean a(int i) {
        if (this.d - this.c >= i) {
            return false;
        }
        if (i > this.f1464a) {
            throw new KryoException("Buffer overflow. Max capacity: " + this.f1464a + ", required: " + i);
        }
        flush();
        while (this.d - this.c < i) {
            if (this.d == this.f1464a) {
                throw new KryoException("Buffer overflow. Available: " + (this.d - this.c) + ", required: " + i);
            }
            if (this.d == 0) {
                this.d = 1;
            }
            this.d = Math.min(this.d << 1, this.f1464a);
            if (this.d < 0) {
                this.d = this.f1464a;
            }
            byte[] bArr = new byte[this.d];
            System.arraycopy(this.e, 0, bArr, 0, this.c);
            this.e = bArr;
        }
        return true;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (this.f == null) {
            return;
        }
        try {
            this.f.write(this.e, 0, this.c);
            this.f1465b += this.c;
            this.c = 0;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
        if (this.f != null) {
            try {
                this.f.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(int i) {
        if (this.c == this.d) {
            a(1);
        }
        byte[] bArr = this.e;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) i;
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        writeBytes(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(byte[] bArr, int i, int i2) {
        writeBytes(bArr, i, i2);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeByte(byte b2) {
        if (this.c == this.d) {
            a(1);
        }
        byte[] bArr = this.e;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b2;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeByte(int i) {
        if (this.c == this.d) {
            a(1);
        }
        byte[] bArr = this.e;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeBytes(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        writeBytes(bArr, 0, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeBytes(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.d - this.c, i2);
        while (true) {
            System.arraycopy(bArr, i, this.e, this.c, min);
            this.c += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 == 0) {
                return;
            }
            i += min;
            min = Math.min(this.d, i2);
            a(min);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeInt(int i) {
        a(4);
        byte[] bArr = this.e;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) (i >> 24);
        int i3 = this.c;
        this.c = i3 + 1;
        bArr[i3] = (byte) (i >> 16);
        int i4 = this.c;
        this.c = i4 + 1;
        bArr[i4] = (byte) (i >> 8);
        int i5 = this.c;
        this.c = i5 + 1;
        bArr[i5] = (byte) i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int writeInt(int i, boolean z) {
        return writeVarInt(i, z);
    }

    public int writeVarInt(int i, boolean z) {
        if (!z) {
            i = (i << 1) ^ (i >> 31);
        }
        if ((i >>> 7) == 0) {
            a(1);
            byte[] bArr = this.e;
            int i2 = this.c;
            this.c = i2 + 1;
            bArr[i2] = (byte) i;
            return 1;
        }
        if ((i >>> 14) == 0) {
            a(2);
            byte[] bArr2 = this.e;
            int i3 = this.c;
            this.c = i3 + 1;
            bArr2[i3] = (byte) ((i & 127) | 128);
            byte[] bArr3 = this.e;
            int i4 = this.c;
            this.c = i4 + 1;
            bArr3[i4] = (byte) (i >>> 7);
            return 2;
        }
        if ((i >>> 21) == 0) {
            a(3);
            byte[] bArr4 = this.e;
            int i5 = this.c;
            this.c = i5 + 1;
            bArr4[i5] = (byte) ((i & 127) | 128);
            byte[] bArr5 = this.e;
            int i6 = this.c;
            this.c = i6 + 1;
            bArr5[i6] = (byte) ((i >>> 7) | 128);
            byte[] bArr6 = this.e;
            int i7 = this.c;
            this.c = i7 + 1;
            bArr6[i7] = (byte) (i >>> 14);
            return 3;
        }
        if ((i >>> 28) == 0) {
            a(4);
            byte[] bArr7 = this.e;
            int i8 = this.c;
            this.c = i8 + 1;
            bArr7[i8] = (byte) ((i & 127) | 128);
            byte[] bArr8 = this.e;
            int i9 = this.c;
            this.c = i9 + 1;
            bArr8[i9] = (byte) ((i >>> 7) | 128);
            byte[] bArr9 = this.e;
            int i10 = this.c;
            this.c = i10 + 1;
            bArr9[i10] = (byte) ((i >>> 14) | 128);
            byte[] bArr10 = this.e;
            int i11 = this.c;
            this.c = i11 + 1;
            bArr10[i11] = (byte) (i >>> 21);
            return 4;
        }
        a(5);
        byte[] bArr11 = this.e;
        int i12 = this.c;
        this.c = i12 + 1;
        bArr11[i12] = (byte) ((i & 127) | 128);
        byte[] bArr12 = this.e;
        int i13 = this.c;
        this.c = i13 + 1;
        bArr12[i13] = (byte) ((i >>> 7) | 128);
        byte[] bArr13 = this.e;
        int i14 = this.c;
        this.c = i14 + 1;
        bArr13[i14] = (byte) ((i >>> 14) | 128);
        byte[] bArr14 = this.e;
        int i15 = this.c;
        this.c = i15 + 1;
        bArr14[i15] = (byte) ((i >>> 21) | 128);
        byte[] bArr15 = this.e;
        int i16 = this.c;
        this.c = i16 + 1;
        bArr15[i16] = (byte) (i >>> 28);
        return 5;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeString(String str) {
        char charAt;
        if (str == null) {
            writeByte(128);
            return;
        }
        int length = str.length();
        if (length == 0) {
            writeByte(129);
            return;
        }
        boolean z = false;
        if (length > 1 && length < 64) {
            z = true;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (str.charAt(i) <= 127) {
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            if (this.d - this.c < length) {
                a(str, length);
            } else {
                str.getBytes(0, length, this.e, this.c);
                this.c += length;
            }
            byte[] bArr = this.e;
            int i2 = this.c - 1;
            bArr[i2] = (byte) (bArr[i2] | 128);
            return;
        }
        b(length + 1);
        int i3 = 0;
        if (this.d - this.c >= length) {
            byte[] bArr2 = this.e;
            int i4 = this.c;
            while (i3 < length && (charAt = str.charAt(i3)) <= 127) {
                int i5 = i4;
                i4++;
                bArr2[i5] = (byte) charAt;
                i3++;
            }
            this.c = i4;
        }
        if (i3 < length) {
            a(str, length, i3);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeString(CharSequence charSequence) {
        char charAt;
        if (charSequence == null) {
            writeByte(128);
            return;
        }
        int length = charSequence.length();
        if (length == 0) {
            writeByte(129);
            return;
        }
        b(length + 1);
        int i = 0;
        if (this.d - this.c >= length) {
            byte[] bArr = this.e;
            int i2 = this.c;
            while (i < length && (charAt = charSequence.charAt(i)) <= 127) {
                int i3 = i2;
                i2++;
                bArr[i3] = (byte) charAt;
                i++;
            }
            this.c = i2;
        }
        if (i < length) {
            a(charSequence, length, i);
        }
    }

    public void writeAscii(String str) {
        if (str == null) {
            writeByte(128);
            return;
        }
        int length = str.length();
        switch (length) {
            case 0:
                writeByte(129);
                return;
            case 1:
                writeByte(130);
                writeByte(str.charAt(0));
                return;
            default:
                if (this.d - this.c < length) {
                    a(str, length);
                } else {
                    str.getBytes(0, length, this.e, this.c);
                    this.c += length;
                }
                byte[] bArr = this.e;
                int i = this.c - 1;
                bArr[i] = (byte) (bArr[i] | 128);
                return;
        }
    }

    private void b(int i) {
        if ((i >>> 6) == 0) {
            a(1);
            byte[] bArr = this.e;
            int i2 = this.c;
            this.c = i2 + 1;
            bArr[i2] = (byte) (i | 128);
            return;
        }
        if ((i >>> 13) == 0) {
            a(2);
            byte[] bArr2 = this.e;
            int i3 = this.c;
            this.c = i3 + 1;
            bArr2[i3] = (byte) (i | 64 | 128);
            int i4 = this.c;
            this.c = i4 + 1;
            bArr2[i4] = (byte) (i >>> 6);
            return;
        }
        if ((i >>> 20) == 0) {
            a(3);
            byte[] bArr3 = this.e;
            int i5 = this.c;
            this.c = i5 + 1;
            bArr3[i5] = (byte) (i | 64 | 128);
            int i6 = this.c;
            this.c = i6 + 1;
            bArr3[i6] = (byte) ((i >>> 6) | 128);
            int i7 = this.c;
            this.c = i7 + 1;
            bArr3[i7] = (byte) (i >>> 13);
            return;
        }
        if ((i >>> 27) == 0) {
            a(4);
            byte[] bArr4 = this.e;
            int i8 = this.c;
            this.c = i8 + 1;
            bArr4[i8] = (byte) (i | 64 | 128);
            int i9 = this.c;
            this.c = i9 + 1;
            bArr4[i9] = (byte) ((i >>> 6) | 128);
            int i10 = this.c;
            this.c = i10 + 1;
            bArr4[i10] = (byte) ((i >>> 13) | 128);
            int i11 = this.c;
            this.c = i11 + 1;
            bArr4[i11] = (byte) (i >>> 20);
            return;
        }
        a(5);
        byte[] bArr5 = this.e;
        int i12 = this.c;
        this.c = i12 + 1;
        bArr5[i12] = (byte) (i | 64 | 128);
        int i13 = this.c;
        this.c = i13 + 1;
        bArr5[i13] = (byte) ((i >>> 6) | 128);
        int i14 = this.c;
        this.c = i14 + 1;
        bArr5[i14] = (byte) ((i >>> 13) | 128);
        int i15 = this.c;
        this.c = i15 + 1;
        bArr5[i15] = (byte) ((i >>> 20) | 128);
        int i16 = this.c;
        this.c = i16 + 1;
        bArr5[i16] = (byte) (i >>> 27);
    }

    private void a(CharSequence charSequence, int i, int i2) {
        while (i2 < i) {
            if (this.c == this.d) {
                a(Math.min(this.d, i - i2));
            }
            char charAt = charSequence.charAt(i2);
            if (charAt <= 127) {
                byte[] bArr = this.e;
                int i3 = this.c;
                this.c = i3 + 1;
                bArr[i3] = (byte) charAt;
            } else if (charAt > 2047) {
                byte[] bArr2 = this.e;
                int i4 = this.c;
                this.c = i4 + 1;
                bArr2[i4] = (byte) (224 | ((charAt >> '\f') & 15));
                a(2);
                byte[] bArr3 = this.e;
                int i5 = this.c;
                this.c = i5 + 1;
                bArr3[i5] = (byte) (128 | ((charAt >> 6) & 63));
                byte[] bArr4 = this.e;
                int i6 = this.c;
                this.c = i6 + 1;
                bArr4[i6] = (byte) (128 | (charAt & '?'));
            } else {
                byte[] bArr5 = this.e;
                int i7 = this.c;
                this.c = i7 + 1;
                bArr5[i7] = (byte) (192 | ((charAt >> 6) & 31));
                a(1);
                byte[] bArr6 = this.e;
                int i8 = this.c;
                this.c = i8 + 1;
                bArr6[i8] = (byte) (128 | (charAt & '?'));
            }
            i2++;
        }
    }

    private void a(String str, int i) {
        byte[] bArr = this.e;
        int i2 = 0;
        int min = Math.min(i, this.d - this.c);
        while (i2 < i) {
            int i3 = i2;
            str.getBytes(i3, i3 + min, bArr, this.c);
            i2 += min;
            this.c += min;
            min = Math.min(i - i2, this.d);
            if (a(min)) {
                bArr = this.e;
            }
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeFloat(float f) {
        writeInt(Float.floatToIntBits(f));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int writeFloat(float f, float f2, boolean z) {
        return writeInt((int) (f * f2), z);
    }

    public void writeShort(int i) {
        a(2);
        byte[] bArr = this.e;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        byte[] bArr2 = this.e;
        int i3 = this.c;
        this.c = i3 + 1;
        bArr2[i3] = (byte) i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeLong(long j) {
        a(8);
        byte[] bArr = this.e;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = (byte) (j >>> 56);
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) (j >>> 48);
        int i3 = this.c;
        this.c = i3 + 1;
        bArr[i3] = (byte) (j >>> 40);
        int i4 = this.c;
        this.c = i4 + 1;
        bArr[i4] = (byte) (j >>> 32);
        int i5 = this.c;
        this.c = i5 + 1;
        bArr[i5] = (byte) (j >>> 24);
        int i6 = this.c;
        this.c = i6 + 1;
        bArr[i6] = (byte) (j >>> 16);
        int i7 = this.c;
        this.c = i7 + 1;
        bArr[i7] = (byte) (j >>> 8);
        int i8 = this.c;
        this.c = i8 + 1;
        bArr[i8] = (byte) j;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int writeLong(long j, boolean z) {
        return writeVarLong(j, z);
    }

    public int writeVarLong(long j, boolean z) {
        if (!z) {
            j = (j << 1) ^ (j >> 63);
        }
        if ((j >>> 7) == 0) {
            a(1);
            byte[] bArr = this.e;
            int i = this.c;
            this.c = i + 1;
            bArr[i] = (byte) j;
            return 1;
        }
        if ((j >>> 14) == 0) {
            a(2);
            byte[] bArr2 = this.e;
            int i2 = this.c;
            this.c = i2 + 1;
            bArr2[i2] = (byte) ((j & 127) | 128);
            byte[] bArr3 = this.e;
            int i3 = this.c;
            this.c = i3 + 1;
            bArr3[i3] = (byte) (j >>> 7);
            return 2;
        }
        if ((j >>> 21) == 0) {
            a(3);
            byte[] bArr4 = this.e;
            int i4 = this.c;
            this.c = i4 + 1;
            bArr4[i4] = (byte) ((j & 127) | 128);
            byte[] bArr5 = this.e;
            int i5 = this.c;
            this.c = i5 + 1;
            bArr5[i5] = (byte) ((j >>> 7) | 128);
            byte[] bArr6 = this.e;
            int i6 = this.c;
            this.c = i6 + 1;
            bArr6[i6] = (byte) (j >>> 14);
            return 3;
        }
        if ((j >>> 28) == 0) {
            a(4);
            byte[] bArr7 = this.e;
            int i7 = this.c;
            this.c = i7 + 1;
            bArr7[i7] = (byte) ((j & 127) | 128);
            byte[] bArr8 = this.e;
            int i8 = this.c;
            this.c = i8 + 1;
            bArr8[i8] = (byte) ((j >>> 7) | 128);
            byte[] bArr9 = this.e;
            int i9 = this.c;
            this.c = i9 + 1;
            bArr9[i9] = (byte) ((j >>> 14) | 128);
            byte[] bArr10 = this.e;
            int i10 = this.c;
            this.c = i10 + 1;
            bArr10[i10] = (byte) (j >>> 21);
            return 4;
        }
        if ((j >>> 35) == 0) {
            a(5);
            byte[] bArr11 = this.e;
            int i11 = this.c;
            this.c = i11 + 1;
            bArr11[i11] = (byte) ((j & 127) | 128);
            byte[] bArr12 = this.e;
            int i12 = this.c;
            this.c = i12 + 1;
            bArr12[i12] = (byte) ((j >>> 7) | 128);
            byte[] bArr13 = this.e;
            int i13 = this.c;
            this.c = i13 + 1;
            bArr13[i13] = (byte) ((j >>> 14) | 128);
            byte[] bArr14 = this.e;
            int i14 = this.c;
            this.c = i14 + 1;
            bArr14[i14] = (byte) ((j >>> 21) | 128);
            byte[] bArr15 = this.e;
            int i15 = this.c;
            this.c = i15 + 1;
            bArr15[i15] = (byte) (j >>> 28);
            return 5;
        }
        if ((j >>> 42) == 0) {
            a(6);
            byte[] bArr16 = this.e;
            int i16 = this.c;
            this.c = i16 + 1;
            bArr16[i16] = (byte) ((j & 127) | 128);
            byte[] bArr17 = this.e;
            int i17 = this.c;
            this.c = i17 + 1;
            bArr17[i17] = (byte) ((j >>> 7) | 128);
            byte[] bArr18 = this.e;
            int i18 = this.c;
            this.c = i18 + 1;
            bArr18[i18] = (byte) ((j >>> 14) | 128);
            byte[] bArr19 = this.e;
            int i19 = this.c;
            this.c = i19 + 1;
            bArr19[i19] = (byte) ((j >>> 21) | 128);
            byte[] bArr20 = this.e;
            int i20 = this.c;
            this.c = i20 + 1;
            bArr20[i20] = (byte) ((j >>> 28) | 128);
            byte[] bArr21 = this.e;
            int i21 = this.c;
            this.c = i21 + 1;
            bArr21[i21] = (byte) (j >>> 35);
            return 6;
        }
        if ((j >>> 49) == 0) {
            a(7);
            byte[] bArr22 = this.e;
            int i22 = this.c;
            this.c = i22 + 1;
            bArr22[i22] = (byte) ((j & 127) | 128);
            byte[] bArr23 = this.e;
            int i23 = this.c;
            this.c = i23 + 1;
            bArr23[i23] = (byte) ((j >>> 7) | 128);
            byte[] bArr24 = this.e;
            int i24 = this.c;
            this.c = i24 + 1;
            bArr24[i24] = (byte) ((j >>> 14) | 128);
            byte[] bArr25 = this.e;
            int i25 = this.c;
            this.c = i25 + 1;
            bArr25[i25] = (byte) ((j >>> 21) | 128);
            byte[] bArr26 = this.e;
            int i26 = this.c;
            this.c = i26 + 1;
            bArr26[i26] = (byte) ((j >>> 28) | 128);
            byte[] bArr27 = this.e;
            int i27 = this.c;
            this.c = i27 + 1;
            bArr27[i27] = (byte) ((j >>> 35) | 128);
            byte[] bArr28 = this.e;
            int i28 = this.c;
            this.c = i28 + 1;
            bArr28[i28] = (byte) (j >>> 42);
            return 7;
        }
        if ((j >>> 56) == 0) {
            a(8);
            byte[] bArr29 = this.e;
            int i29 = this.c;
            this.c = i29 + 1;
            bArr29[i29] = (byte) ((j & 127) | 128);
            byte[] bArr30 = this.e;
            int i30 = this.c;
            this.c = i30 + 1;
            bArr30[i30] = (byte) ((j >>> 7) | 128);
            byte[] bArr31 = this.e;
            int i31 = this.c;
            this.c = i31 + 1;
            bArr31[i31] = (byte) ((j >>> 14) | 128);
            byte[] bArr32 = this.e;
            int i32 = this.c;
            this.c = i32 + 1;
            bArr32[i32] = (byte) ((j >>> 21) | 128);
            byte[] bArr33 = this.e;
            int i33 = this.c;
            this.c = i33 + 1;
            bArr33[i33] = (byte) ((j >>> 28) | 128);
            byte[] bArr34 = this.e;
            int i34 = this.c;
            this.c = i34 + 1;
            bArr34[i34] = (byte) ((j >>> 35) | 128);
            byte[] bArr35 = this.e;
            int i35 = this.c;
            this.c = i35 + 1;
            bArr35[i35] = (byte) ((j >>> 42) | 128);
            byte[] bArr36 = this.e;
            int i36 = this.c;
            this.c = i36 + 1;
            bArr36[i36] = (byte) (j >>> 49);
            return 8;
        }
        a(9);
        byte[] bArr37 = this.e;
        int i37 = this.c;
        this.c = i37 + 1;
        bArr37[i37] = (byte) ((j & 127) | 128);
        byte[] bArr38 = this.e;
        int i38 = this.c;
        this.c = i38 + 1;
        bArr38[i38] = (byte) ((j >>> 7) | 128);
        byte[] bArr39 = this.e;
        int i39 = this.c;
        this.c = i39 + 1;
        bArr39[i39] = (byte) ((j >>> 14) | 128);
        byte[] bArr40 = this.e;
        int i40 = this.c;
        this.c = i40 + 1;
        bArr40[i40] = (byte) ((j >>> 21) | 128);
        byte[] bArr41 = this.e;
        int i41 = this.c;
        this.c = i41 + 1;
        bArr41[i41] = (byte) ((j >>> 28) | 128);
        byte[] bArr42 = this.e;
        int i42 = this.c;
        this.c = i42 + 1;
        bArr42[i42] = (byte) ((j >>> 35) | 128);
        byte[] bArr43 = this.e;
        int i43 = this.c;
        this.c = i43 + 1;
        bArr43[i43] = (byte) ((j >>> 42) | 128);
        byte[] bArr44 = this.e;
        int i44 = this.c;
        this.c = i44 + 1;
        bArr44[i44] = (byte) ((j >>> 49) | 128);
        byte[] bArr45 = this.e;
        int i45 = this.c;
        this.c = i45 + 1;
        bArr45[i45] = (byte) (j >>> 56);
        return 9;
    }

    public void writeBoolean(boolean z) {
        if (this.c == this.d) {
            a(1);
        }
        byte[] bArr = this.e;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = (byte) (z ? 1 : 0);
    }

    public void writeChar(char c) {
        a(2);
        byte[] bArr = this.e;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = (byte) (c >>> '\b');
        byte[] bArr2 = this.e;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr2[i2] = (byte) c;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeDouble(double d) {
        writeLong(Double.doubleToLongBits(d));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int writeDouble(double d, double d2, boolean z) {
        return writeLong((long) (d * d2), z);
    }

    public static int intLength(int i, boolean z) {
        if (!z) {
            i = (i << 1) ^ (i >> 31);
        }
        if ((i >>> 7) == 0) {
            return 1;
        }
        if ((i >>> 14) == 0) {
            return 2;
        }
        if ((i >>> 21) == 0) {
            return 3;
        }
        return (i >>> 28) == 0 ? 4 : 5;
    }

    public static int longLength(long j, boolean z) {
        if (!z) {
            j = (j << 1) ^ (j >> 63);
        }
        if ((j >>> 7) == 0) {
            return 1;
        }
        if ((j >>> 14) == 0) {
            return 2;
        }
        if ((j >>> 21) == 0) {
            return 3;
        }
        if ((j >>> 28) == 0) {
            return 4;
        }
        if ((j >>> 35) == 0) {
            return 5;
        }
        if ((j >>> 42) == 0) {
            return 6;
        }
        if ((j >>> 49) == 0) {
            return 7;
        }
        return (j >>> 56) == 0 ? 8 : 9;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeInts(int[] iArr, boolean z) {
        for (int i : iArr) {
            writeInt(i, z);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeLongs(long[] jArr, boolean z) {
        for (long j : jArr) {
            writeLong(j, z);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeInts(int[] iArr) {
        for (int i : iArr) {
            writeInt(i);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void writeLongs(long[] jArr) {
        for (long j : jArr) {
            writeLong(j);
        }
    }

    public void writeFloats(float[] fArr) {
        for (float f : fArr) {
            writeFloat(f);
        }
    }

    public void writeShorts(short[] sArr) {
        for (short s : sArr) {
            writeShort(s);
        }
    }

    public void writeChars(char[] cArr) {
        for (char c : cArr) {
            writeChar(c);
        }
    }

    public void writeDoubles(double[] dArr) {
        for (double d : dArr) {
            writeDouble(d);
        }
    }
}
