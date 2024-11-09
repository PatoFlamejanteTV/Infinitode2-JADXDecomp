package com.a.a.b.c;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/a/a/b/c/l.class */
public final class l extends Reader {

    /* renamed from: a, reason: collision with root package name */
    private a f122a;

    /* renamed from: b, reason: collision with root package name */
    private InputStream f123b;
    private byte[] c;
    private int d;
    private int e;
    private boolean f;
    private char g = 0;
    private int h;
    private int i;
    private boolean j;
    private char[] k;

    public l(a aVar, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        this.f122a = aVar;
        this.f123b = inputStream;
        this.c = bArr;
        this.d = i;
        this.e = i2;
        this.f = z;
        this.j = inputStream != null;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        InputStream inputStream = this.f123b;
        if (inputStream != null) {
            this.f123b = null;
            a();
            inputStream.close();
        }
    }

    @Override // java.io.Reader
    public final int read() {
        if (this.k == null) {
            this.k = new char[1];
        }
        if (read(this.k, 0, 1) <= 0) {
            return -1;
        }
        return this.k[0];
    }

    @Override // java.io.Reader
    public final int read(char[] cArr, int i, int i2) {
        int i3;
        int i4;
        if (this.c == null) {
            return -1;
        }
        if (i2 <= 0) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            a(cArr, i, i2);
        }
        int i5 = i;
        int i6 = i2 + i;
        if (this.g != 0) {
            i5++;
            cArr[i] = this.g;
            this.g = (char) 0;
        } else {
            int i7 = this.e - this.d;
            if (i7 < 4 && !a(i7)) {
                if (i7 == 0) {
                    return -1;
                }
                a(this.e - this.d, 4);
            }
        }
        int i8 = this.e - 4;
        while (true) {
            if (i5 >= i6 || this.d > i8) {
                break;
            }
            int i9 = this.d;
            if (this.f) {
                i4 = (this.c[i9] << 8) | (this.c[i9 + 1] & 255);
                i3 = ((this.c[i9 + 2] & 255) << 8) | (this.c[i9 + 3] & 255);
            } else {
                i3 = (this.c[i9] & 255) | ((this.c[i9 + 1] & 255) << 8);
                i4 = (this.c[i9 + 2] & 255) | (this.c[i9 + 3] << 8);
            }
            this.d += 4;
            if (i4 != 0) {
                int i10 = i4 & 65535;
                int i11 = ((i10 - 1) << 16) | i3;
                if (i10 > 16) {
                    a(i11, i5 - i, String.format(" (above 0x%08x)", 1114111));
                }
                int i12 = i5;
                i5++;
                cArr[i12] = (char) (55296 + (i11 >> 10));
                i3 = 56320 | (i11 & 1023);
                if (i5 >= i6) {
                    this.g = (char) i11;
                    break;
                }
            }
            int i13 = i5;
            i5++;
            cArr[i13] = (char) i3;
        }
        int i14 = i5 - i;
        this.h += i14;
        return i14;
    }

    private void a(int i, int i2) {
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed 4, at char #" + this.h + ", byte #" + (this.i + i) + ")");
    }

    private void a(int i, int i2, String str) {
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this.h + i2) + ", byte #" + ((this.i + this.d) - 1) + ")");
    }

    private boolean a(int i) {
        if (this.f123b == null || this.c == null) {
            return false;
        }
        this.i += this.e - i;
        if (i > 0) {
            if (this.d > 0) {
                System.arraycopy(this.c, this.d, this.c, 0, i);
                this.d = 0;
            }
            this.e = i;
        } else {
            this.d = 0;
            int read = this.f123b.read(this.c);
            if (read <= 0) {
                this.e = 0;
                if (read < 0) {
                    if (this.j) {
                        a();
                        return false;
                    }
                    return false;
                }
                b();
            }
            this.e = read;
        }
        while (this.e < 4) {
            int read2 = this.f123b.read(this.c, this.e, this.c.length - this.e);
            if (read2 <= 0) {
                if (read2 < 0) {
                    if (this.j) {
                        a();
                    }
                    a(this.e, 4);
                }
                b();
            }
            this.e += read2;
        }
        return true;
    }

    private void a() {
        byte[] bArr = this.c;
        if (bArr != null) {
            this.c = null;
            if (this.f122a != null) {
                this.f122a.a(bArr);
            }
        }
    }

    private static void a(char[] cArr, int i, int i2) {
        throw new ArrayIndexOutOfBoundsException(String.format("read(buf,%d,%d), cbuf[%d]", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(cArr.length)));
    }

    private static void b() {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
