package com.prineside.kryo;

import com.esotericsoftware.kryo.KryoException;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/prineside/kryo/FixedInput.class */
public class FixedInput extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f1462a;

    /* renamed from: b, reason: collision with root package name */
    private int f1463b;
    private int c;
    private int d;
    private long e;
    private char[] f;
    private InputStream g;

    public FixedInput() {
        this.f = new char[32];
    }

    public FixedInput(int i) {
        this.f = new char[32];
        this.c = i;
        this.f1462a = new byte[i];
    }

    public FixedInput(byte[] bArr) {
        this.f = new char[32];
        setBuffer(bArr, 0, bArr.length);
    }

    public FixedInput(byte[] bArr, int i, int i2) {
        this.f = new char[32];
        setBuffer(bArr, i, i2);
    }

    public FixedInput(InputStream inputStream) {
        this(4096);
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.g = inputStream;
    }

    public FixedInput(InputStream inputStream, int i) {
        this(i);
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.g = inputStream;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void setBuffer(byte[] bArr) {
        setBuffer(bArr, 0, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void setBuffer(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        this.f1462a = bArr;
        this.f1463b = i;
        this.d = i + i2;
        this.c = bArr.length;
        this.e = 0L;
        this.g = null;
    }

    public byte[] getBuffer() {
        return this.f1462a;
    }

    public InputStream getInputStream() {
        return this.g;
    }

    public void setInputStream(InputStream inputStream) {
        this.g = inputStream;
        this.d = 0;
        rewind();
    }

    public long total() {
        return this.e + this.f1463b;
    }

    public void setTotal(int i) {
        this.e = i;
    }

    public final int position() {
        return this.f1463b;
    }

    public void setPosition(int i) {
        this.f1463b = i;
    }

    public final int limit() {
        return this.d;
    }

    public void setLimit(int i) {
        this.d = i;
    }

    public void rewind() {
        this.f1463b = 0;
        this.e = 0L;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void skip(int i) {
        int min = Math.min(this.d - this.f1463b, i);
        while (true) {
            this.f1463b += min;
            int i2 = i - min;
            i = i2;
            if (i2 != 0) {
                min = Math.min(i, this.c);
                a(min);
            } else {
                return;
            }
        }
    }

    private int a(byte[] bArr, int i, int i2) {
        if (this.g == null) {
            return -1;
        }
        try {
            return this.g.read(bArr, i, i2);
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    private int a(int i) {
        int i2 = this.d - this.f1463b;
        int i3 = i2;
        if (i2 >= i) {
            return i3;
        }
        if (i > this.c) {
            throw new KryoException("Buffer too small: capacity: " + this.c + ", required: " + i);
        }
        if (i3 > 0) {
            int a2 = a(this.f1462a, this.d, this.c - this.d);
            if (a2 == -1) {
                throw new KryoException("Buffer underflow.");
            }
            int i4 = i3 + a2;
            i3 = i4;
            if (i4 >= i) {
                this.d += a2;
                return i3;
            }
        }
        System.arraycopy(this.f1462a, this.f1463b, this.f1462a, 0, i3);
        this.e += this.f1463b;
        this.f1463b = 0;
        while (true) {
            int a3 = a(this.f1462a, i3, this.c - i3);
            if (a3 != -1) {
                int i5 = i3 + a3;
                i3 = i5;
                if (i5 >= i) {
                    break;
                }
            } else if (i3 < i) {
                throw new KryoException("Buffer underflow.");
            }
        }
        this.d = i3;
        return i3;
    }

    private int b(int i) {
        int i2;
        int i3 = this.d - this.f1463b;
        if (i3 >= i) {
            return i;
        }
        int min = Math.min(i, this.c);
        int a2 = a(this.f1462a, this.d, this.c - this.d);
        if (a2 == -1) {
            if (i3 == 0) {
                return -1;
            }
            return Math.min(i3, min);
        }
        int i4 = i3 + a2;
        int i5 = i4;
        if (i4 >= min) {
            this.d += a2;
            return min;
        }
        System.arraycopy(this.f1462a, this.f1463b, this.f1462a, 0, i5);
        this.e += this.f1463b;
        this.f1463b = 0;
        do {
            int a3 = a(this.f1462a, i5, this.c - i5);
            if (a3 == -1) {
                break;
            }
            i2 = i5 + a3;
            i5 = i2;
        } while (i2 < min);
        this.d = i5;
        if (i5 == 0) {
            return -1;
        }
        return Math.min(i5, min);
    }

    public boolean eof() {
        return b(1) <= 0;
    }

    @Override // java.io.InputStream
    public int available() {
        return (this.d - this.f1463b) + (this.g != null ? this.g.available() : 0);
    }

    @Override // java.io.InputStream
    @IgnoreMethodOverloadLuaDefWarning
    public int read() {
        if (b(1) <= 0) {
            return -1;
        }
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    @IgnoreMethodOverloadLuaDefWarning
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    @IgnoreMethodOverloadLuaDefWarning
    public int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.d - this.f1463b, i2);
        while (true) {
            System.arraycopy(this.f1462a, this.f1463b, bArr, i, min);
            this.f1463b += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 == 0) {
                break;
            }
            i += min;
            int b2 = b(i2);
            min = b2;
            if (b2 == -1) {
                if (i2 == i2) {
                    return -1;
                }
            } else if (this.f1463b == this.d) {
                break;
            }
        }
        return i2 - i2;
    }

    @Override // java.io.InputStream
    @IgnoreMethodOverloadLuaDefWarning
    public long skip(long j) {
        long j2 = j;
        while (true) {
            long j3 = j2;
            if (j3 > 0) {
                int min = (int) Math.min(2147483647L, j3);
                skip(min);
                j2 = j3 - min;
            } else {
                return j;
            }
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.g != null) {
            try {
                this.g.close();
            } catch (IOException unused) {
            }
        }
    }

    public byte readByte() {
        a(1);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        return bArr[i];
    }

    public int readByteUnsigned() {
        a(1);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        return bArr[i] & 255;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public byte[] readBytes(int i) {
        byte[] bArr = new byte[i];
        readBytes(bArr, 0, i);
        return bArr;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void readBytes(byte[] bArr) {
        readBytes(bArr, 0, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void readBytes(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.d - this.f1463b, i2);
        while (true) {
            System.arraycopy(this.f1462a, this.f1463b, bArr, i, min);
            this.f1463b += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 != 0) {
                i += min;
                min = Math.min(i2, this.c);
                a(min);
            } else {
                return;
            }
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int readInt() {
        a(4);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 4;
        return ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int readInt(boolean z) {
        return readVarInt(z);
    }

    public int readVarInt(boolean z) {
        if (a(1) < 5) {
            return a(z);
        }
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        byte b2 = bArr[i];
        int i2 = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            byte[] bArr2 = this.f1462a;
            int i3 = this.f1463b;
            this.f1463b = i3 + 1;
            byte b3 = bArr2[i3];
            i2 |= (b3 & Byte.MAX_VALUE) << 7;
            if ((b3 & 128) != 0) {
                int i4 = this.f1463b;
                this.f1463b = i4 + 1;
                byte b4 = bArr2[i4];
                i2 |= (b4 & Byte.MAX_VALUE) << 14;
                if ((b4 & 128) != 0) {
                    int i5 = this.f1463b;
                    this.f1463b = i5 + 1;
                    byte b5 = bArr2[i5];
                    i2 |= (b5 & Byte.MAX_VALUE) << 21;
                    if ((b5 & 128) != 0) {
                        int i6 = this.f1463b;
                        this.f1463b = i6 + 1;
                        i2 |= (bArr2[i6] & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i2 : (i2 >>> 1) ^ (-(i2 & 1));
    }

    private int a(boolean z) {
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        byte b2 = bArr[i];
        int i2 = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            a(1);
            byte[] bArr2 = this.f1462a;
            int i3 = this.f1463b;
            this.f1463b = i3 + 1;
            byte b3 = bArr2[i3];
            i2 |= (b3 & Byte.MAX_VALUE) << 7;
            if ((b3 & 128) != 0) {
                a(1);
                int i4 = this.f1463b;
                this.f1463b = i4 + 1;
                byte b4 = bArr2[i4];
                i2 |= (b4 & Byte.MAX_VALUE) << 14;
                if ((b4 & 128) != 0) {
                    a(1);
                    int i5 = this.f1463b;
                    this.f1463b = i5 + 1;
                    byte b5 = bArr2[i5];
                    i2 |= (b5 & Byte.MAX_VALUE) << 21;
                    if ((b5 & 128) != 0) {
                        a(1);
                        int i6 = this.f1463b;
                        this.f1463b = i6 + 1;
                        i2 |= (bArr2[i6] & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i2 : (i2 >>> 1) ^ (-(i2 & 1));
    }

    public boolean canReadInt() {
        if (this.d - this.f1463b >= 5) {
            return true;
        }
        if (b(5) <= 0) {
            return false;
        }
        int i = this.f1463b;
        int i2 = i + 1;
        if ((this.f1462a[i] & 128) == 0) {
            return true;
        }
        if (i2 == this.d) {
            return false;
        }
        int i3 = i2 + 1;
        if ((this.f1462a[i2] & 128) == 0) {
            return true;
        }
        if (i3 == this.d) {
            return false;
        }
        int i4 = i3 + 1;
        if ((this.f1462a[i3] & 128) == 0) {
            return true;
        }
        if (i4 == this.d) {
            return false;
        }
        return (this.f1462a[i4] & 128) == 0 || i4 + 1 != this.d;
    }

    public boolean canReadLong() {
        if (this.d - this.f1463b >= 9) {
            return true;
        }
        if (b(5) <= 0) {
            return false;
        }
        int i = this.f1463b;
        int i2 = i + 1;
        if ((this.f1462a[i] & 128) == 0) {
            return true;
        }
        if (i2 == this.d) {
            return false;
        }
        int i3 = i2 + 1;
        if ((this.f1462a[i2] & 128) == 0) {
            return true;
        }
        if (i3 == this.d) {
            return false;
        }
        int i4 = i3 + 1;
        if ((this.f1462a[i3] & 128) == 0) {
            return true;
        }
        if (i4 == this.d) {
            return false;
        }
        int i5 = i4 + 1;
        if ((this.f1462a[i4] & 128) == 0) {
            return true;
        }
        if (i5 == this.d) {
            return false;
        }
        int i6 = i5 + 1;
        if ((this.f1462a[i5] & 128) == 0) {
            return true;
        }
        if (i6 == this.d) {
            return false;
        }
        int i7 = i6 + 1;
        if ((this.f1462a[i6] & 128) == 0) {
            return true;
        }
        if (i7 == this.d) {
            return false;
        }
        int i8 = i7 + 1;
        if ((this.f1462a[i7] & 128) == 0) {
            return true;
        }
        if (i8 == this.d) {
            return false;
        }
        return (this.f1462a[i8] & 128) == 0 || i8 + 1 != this.d;
    }

    public String readString() {
        int a2 = a(1);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 128) == 0) {
            return a();
        }
        int c = a2 >= 5 ? c(b2) : d(b2);
        int i2 = c;
        switch (c) {
            case 0:
                return null;
            case 1:
                return "";
            default:
                int i3 = i2 - 1;
                if (this.f.length < i3) {
                    this.f = new char[i3];
                }
                e(i3);
                return new String(this.f, 0, i3);
        }
    }

    private int c(int i) {
        int i2 = i & 63;
        if ((i & 64) != 0) {
            byte[] bArr = this.f1462a;
            int i3 = this.f1463b;
            this.f1463b = i3 + 1;
            byte b2 = bArr[i3];
            i2 |= (b2 & Byte.MAX_VALUE) << 6;
            if ((b2 & 128) != 0) {
                int i4 = this.f1463b;
                this.f1463b = i4 + 1;
                byte b3 = bArr[i4];
                i2 |= (b3 & Byte.MAX_VALUE) << 13;
                if ((b3 & 128) != 0) {
                    int i5 = this.f1463b;
                    this.f1463b = i5 + 1;
                    byte b4 = bArr[i5];
                    i2 |= (b4 & Byte.MAX_VALUE) << 20;
                    if ((b4 & 128) != 0) {
                        int i6 = this.f1463b;
                        this.f1463b = i6 + 1;
                        i2 |= (bArr[i6] & Byte.MAX_VALUE) << 27;
                    }
                }
            }
        }
        return i2;
    }

    private int d(int i) {
        int i2 = i & 63;
        if ((i & 64) != 0) {
            a(1);
            byte[] bArr = this.f1462a;
            int i3 = this.f1463b;
            this.f1463b = i3 + 1;
            byte b2 = bArr[i3];
            i2 |= (b2 & Byte.MAX_VALUE) << 6;
            if ((b2 & 128) != 0) {
                a(1);
                int i4 = this.f1463b;
                this.f1463b = i4 + 1;
                byte b3 = bArr[i4];
                i2 |= (b3 & Byte.MAX_VALUE) << 13;
                if ((b3 & 128) != 0) {
                    a(1);
                    int i5 = this.f1463b;
                    this.f1463b = i5 + 1;
                    byte b4 = bArr[i5];
                    i2 |= (b4 & Byte.MAX_VALUE) << 20;
                    if ((b4 & 128) != 0) {
                        a(1);
                        int i6 = this.f1463b;
                        this.f1463b = i6 + 1;
                        i2 |= (bArr[i6] & Byte.MAX_VALUE) << 27;
                    }
                }
            }
        }
        return i2;
    }

    private void e(int i) {
        byte[] bArr = this.f1462a;
        char[] cArr = this.f;
        int i2 = 0;
        int min = Math.min(a(1), i);
        int i3 = this.f1463b;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = i3;
            i3++;
            byte b2 = bArr[i4];
            if (b2 < 0) {
                i3--;
                break;
            } else {
                int i5 = i2;
                i2++;
                cArr[i5] = (char) b2;
            }
        }
        this.f1463b = i3;
        if (i2 < i) {
            a(i, i2);
        }
    }

    private void a(int i, int i2) {
        char[] cArr = this.f;
        byte[] bArr = this.f1462a;
        while (i2 < i) {
            if (this.f1463b == this.d) {
                a(1);
            }
            int i3 = this.f1463b;
            this.f1463b = i3 + 1;
            int i4 = bArr[i3] & 255;
            switch (i4 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    cArr[i2] = (char) i4;
                    break;
                case 12:
                case 13:
                    if (this.f1463b == this.d) {
                        a(1);
                    }
                    int i5 = this.f1463b;
                    this.f1463b = i5 + 1;
                    cArr[i2] = (char) (((i4 & 31) << 6) | (bArr[i5] & 63));
                    break;
                case 14:
                    a(2);
                    int i6 = this.f1463b;
                    this.f1463b = i6 + 1;
                    int i7 = ((i4 & 15) << 12) | ((bArr[i6] & 63) << 6);
                    int i8 = this.f1463b;
                    this.f1463b = i8 + 1;
                    cArr[i2] = (char) (i7 | (bArr[i8] & 63));
                    break;
            }
            i2++;
        }
    }

    private String a() {
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        int i2 = i;
        int i3 = i - 1;
        int i4 = this.d;
        while (i2 != i4) {
            int i5 = i2;
            i2++;
            if ((bArr[i5] & 128) != 0) {
                int i6 = i2 - 1;
                bArr[i6] = (byte) (bArr[i6] & Byte.MAX_VALUE);
                String str = new String(bArr, 0, i3, i2 - i3);
                int i7 = i2 - 1;
                bArr[i7] = (byte) (bArr[i7] | 128);
                this.f1463b = i2;
                return str;
            }
        }
        return b();
    }

    private String b() {
        this.f1463b--;
        int i = this.d - this.f1463b;
        int i2 = i;
        if (i > this.f.length) {
            this.f = new char[i2 << 1];
        }
        char[] cArr = this.f;
        byte[] bArr = this.f1462a;
        int i3 = this.f1463b;
        int i4 = 0;
        int i5 = this.d;
        while (i3 < i5) {
            cArr[i4] = (char) bArr[i3];
            i3++;
            i4++;
        }
        this.f1463b = this.d;
        while (true) {
            a(1);
            int i6 = this.f1463b;
            this.f1463b = i6 + 1;
            byte b2 = bArr[i6];
            if (i2 == cArr.length) {
                char[] cArr2 = new char[i2 << 1];
                System.arraycopy(cArr, 0, cArr2, 0, i2);
                cArr = cArr2;
                this.f = cArr2;
            }
            if ((b2 & 128) == 128) {
                int i7 = i2;
                int i8 = i2 + 1;
                cArr[i7] = (char) (b2 & Byte.MAX_VALUE);
                return new String(cArr, 0, i8);
            }
            int i9 = i2;
            i2++;
            cArr[i9] = (char) b2;
        }
    }

    public StringBuilder readStringBuilder() {
        int a2 = a(1);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 128) == 0) {
            return new StringBuilder(a());
        }
        int c = a2 >= 5 ? c(b2) : d(b2);
        int i2 = c;
        switch (c) {
            case 0:
                return null;
            case 1:
                return new StringBuilder("");
            default:
                int i3 = i2 - 1;
                if (this.f.length < i3) {
                    this.f = new char[i3];
                }
                e(i3);
                StringBuilder sb = new StringBuilder(i3);
                sb.append(this.f, 0, i3);
                return sb;
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @IgnoreMethodOverloadLuaDefWarning
    public float readFloat(float f, boolean z) {
        return readInt(z) / f;
    }

    public short readShort() {
        a(2);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.f1462a;
        int i3 = this.f1463b;
        this.f1463b = i3 + 1;
        return (short) (i2 | (bArr2[i3] & 255));
    }

    public int readShortUnsigned() {
        a(2);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.f1462a;
        int i3 = this.f1463b;
        this.f1463b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public long readLong() {
        a(8);
        byte[] bArr = this.f1462a;
        this.f1463b = this.f1463b + 1;
        this.f1463b = this.f1463b + 1;
        long j = (bArr[r2] << 56) | ((bArr[r3] & 255) << 48);
        this.f1463b = this.f1463b + 1;
        long j2 = j | ((bArr[r3] & 255) << 40);
        this.f1463b = this.f1463b + 1;
        long j3 = j2 | ((bArr[r3] & 255) << 32);
        this.f1463b = this.f1463b + 1;
        long j4 = j3 | ((bArr[r3] & 255) << 24);
        this.f1463b = this.f1463b + 1;
        long j5 = j4 | ((bArr[r3] & 255) << 16);
        this.f1463b = this.f1463b + 1;
        long j6 = j5 | ((bArr[r3] & 255) << 8);
        this.f1463b = this.f1463b + 1;
        return j6 | (bArr[r3] & 255);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public long readLong(boolean z) {
        return readVarLong(z);
    }

    public long readVarLong(boolean z) {
        if (a(1) < 9) {
            return b(z);
        }
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        byte b2 = bArr[i];
        long j = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            byte[] bArr2 = this.f1462a;
            int i2 = this.f1463b;
            this.f1463b = i2 + 1;
            j |= (r0 & Byte.MAX_VALUE) << 7;
            if ((bArr2[i2] & 128) != 0) {
                int i3 = this.f1463b;
                this.f1463b = i3 + 1;
                j |= (r0 & Byte.MAX_VALUE) << 14;
                if ((bArr2[i3] & 128) != 0) {
                    int i4 = this.f1463b;
                    this.f1463b = i4 + 1;
                    j |= (r0 & Byte.MAX_VALUE) << 21;
                    if ((bArr2[i4] & 128) != 0) {
                        int i5 = this.f1463b;
                        this.f1463b = i5 + 1;
                        j |= (r0 & Byte.MAX_VALUE) << 28;
                        if ((bArr2[i5] & 128) != 0) {
                            int i6 = this.f1463b;
                            this.f1463b = i6 + 1;
                            j |= (r0 & Byte.MAX_VALUE) << 35;
                            if ((bArr2[i6] & 128) != 0) {
                                int i7 = this.f1463b;
                                this.f1463b = i7 + 1;
                                j |= (r0 & Byte.MAX_VALUE) << 42;
                                if ((bArr2[i7] & 128) != 0) {
                                    int i8 = this.f1463b;
                                    this.f1463b = i8 + 1;
                                    j |= (r0 & Byte.MAX_VALUE) << 49;
                                    if ((bArr2[i8] & 128) != 0) {
                                        this.f1463b = this.f1463b + 1;
                                        j |= bArr2[r2] << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!z) {
            j = (j >>> 1) ^ (-(j & 1));
        }
        return j;
    }

    private long b(boolean z) {
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        byte b2 = bArr[i];
        long j = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            a(1);
            byte[] bArr2 = this.f1462a;
            int i2 = this.f1463b;
            this.f1463b = i2 + 1;
            j |= (r0 & Byte.MAX_VALUE) << 7;
            if ((bArr2[i2] & 128) != 0) {
                a(1);
                int i3 = this.f1463b;
                this.f1463b = i3 + 1;
                j |= (r0 & Byte.MAX_VALUE) << 14;
                if ((bArr2[i3] & 128) != 0) {
                    a(1);
                    int i4 = this.f1463b;
                    this.f1463b = i4 + 1;
                    j |= (r0 & Byte.MAX_VALUE) << 21;
                    if ((bArr2[i4] & 128) != 0) {
                        a(1);
                        int i5 = this.f1463b;
                        this.f1463b = i5 + 1;
                        j |= (r0 & Byte.MAX_VALUE) << 28;
                        if ((bArr2[i5] & 128) != 0) {
                            a(1);
                            int i6 = this.f1463b;
                            this.f1463b = i6 + 1;
                            j |= (r0 & Byte.MAX_VALUE) << 35;
                            if ((bArr2[i6] & 128) != 0) {
                                a(1);
                                int i7 = this.f1463b;
                                this.f1463b = i7 + 1;
                                j |= (r0 & Byte.MAX_VALUE) << 42;
                                if ((bArr2[i7] & 128) != 0) {
                                    a(1);
                                    int i8 = this.f1463b;
                                    this.f1463b = i8 + 1;
                                    j |= (r0 & Byte.MAX_VALUE) << 49;
                                    if ((bArr2[i8] & 128) != 0) {
                                        a(1);
                                        this.f1463b = this.f1463b + 1;
                                        j |= bArr2[r2] << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!z) {
            j = (j >>> 1) ^ (-(j & 1));
        }
        return j;
    }

    public boolean readBoolean() {
        a(1);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        return bArr[i] == 1;
    }

    public char readChar() {
        a(2);
        byte[] bArr = this.f1462a;
        int i = this.f1463b;
        this.f1463b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.f1462a;
        int i3 = this.f1463b;
        this.f1463b = i3 + 1;
        return (char) (i2 | (bArr2[i3] & 255));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @IgnoreMethodOverloadLuaDefWarning
    public double readDouble(double d, boolean z) {
        return readLong(z) / d;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int[] readInts(int i, boolean z) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt(z);
        }
        return iArr;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public long[] readLongs(int i, boolean z) {
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = readLong(z);
        }
        return jArr;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public int[] readInts(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt();
        }
        return iArr;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public long[] readLongs(int i) {
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = readLong();
        }
        return jArr;
    }

    public float[] readFloats(int i) {
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = readFloat();
        }
        return fArr;
    }

    public short[] readShorts(int i) {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readShort();
        }
        return sArr;
    }

    public char[] readChars(int i) {
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = readChar();
        }
        return cArr;
    }

    public double[] readDoubles(int i) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = readDouble();
        }
        return dArr;
    }
}
