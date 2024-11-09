package com.a.a.b.c;

import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/a/a/b/c/g.class */
public final class g extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private final a f113a;

    /* renamed from: b, reason: collision with root package name */
    private final InputStream f114b;
    private byte[] c;
    private int d;
    private final int e;

    public g(a aVar, InputStream inputStream, byte[] bArr, int i, int i2) {
        this.f113a = aVar;
        this.f114b = inputStream;
        this.c = bArr;
        this.d = i;
        this.e = i2;
    }

    @Override // java.io.InputStream
    public final int available() {
        if (this.c != null) {
            return this.e - this.d;
        }
        return this.f114b.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a();
        this.f114b.close();
    }

    @Override // java.io.InputStream
    public final synchronized void mark(int i) {
        if (this.c == null) {
            this.f114b.mark(i);
        }
    }

    @Override // java.io.InputStream
    public final boolean markSupported() {
        return this.c == null && this.f114b.markSupported();
    }

    @Override // java.io.InputStream
    public final int read() {
        if (this.c != null) {
            byte[] bArr = this.c;
            int i = this.d;
            this.d = i + 1;
            int i2 = bArr[i] & 255;
            if (this.d >= this.e) {
                a();
            }
            return i2;
        }
        return this.f114b.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (this.c != null) {
            int i3 = this.e - this.d;
            if (i2 > i3) {
                i2 = i3;
            }
            System.arraycopy(this.c, this.d, bArr, i, i2);
            this.d += i2;
            if (this.d >= this.e) {
                a();
            }
            return i2;
        }
        return this.f114b.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public final synchronized void reset() {
        if (this.c == null) {
            this.f114b.reset();
        }
    }

    @Override // java.io.InputStream
    public final long skip(long j) {
        long j2 = 0;
        if (this.c != null) {
            int i = this.e - this.d;
            if (i > j) {
                this.d += (int) j;
                return j;
            }
            a();
            j2 = 0 + i;
            j -= i;
        }
        if (j > 0) {
            j2 += this.f114b.skip(j);
        }
        return j2;
    }

    private void a() {
        byte[] bArr = this.c;
        if (bArr != null) {
            this.c = null;
            if (this.f113a != null) {
                this.f113a.a(bArr);
            }
        }
    }
}
