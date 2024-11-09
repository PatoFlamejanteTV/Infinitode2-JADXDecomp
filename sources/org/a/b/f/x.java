package org.a.b.f;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/b/f/x.class */
final class x extends ak {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f4333a;

    /* renamed from: b, reason: collision with root package name */
    private int f4334b = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(InputStream inputStream) {
        this.f4333a = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    this.f4333a = byteArrayOutputStream.toByteArray();
                    return;
                }
            }
        } finally {
            inputStream.close();
        }
    }

    @Override // org.a.b.f.ak
    public final long a() {
        return (n() << 32) + (n() & 4294967295L);
    }

    private int n() {
        int b2 = b();
        int b3 = b();
        int b4 = b();
        int b5 = b();
        if ((b2 | b3 | b4 | b5) < 0) {
            throw new EOFException();
        }
        return (b2 << 24) + (b3 << 16) + (b4 << 8) + b5;
    }

    @Override // org.a.b.f.ak
    public final int b() {
        if (this.f4334b >= this.f4333a.length) {
            return -1;
        }
        byte b2 = this.f4333a[this.f4334b];
        this.f4334b++;
        return (b2 + 256) % 256;
    }

    @Override // org.a.b.f.ak
    public final int c() {
        int b2 = b();
        int b3 = b();
        if ((b2 | b3) < 0) {
            throw new EOFException();
        }
        return (b2 << 8) + b3;
    }

    @Override // org.a.b.f.ak
    public final short d() {
        int b2 = b();
        int b3 = b();
        if ((b2 | b3) < 0) {
            throw new EOFException();
        }
        return (short) ((b2 << 8) + b3);
    }

    @Override // org.a.b.f.ak, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // org.a.b.f.ak
    public final void a(long j) {
        this.f4334b = (int) j;
    }

    @Override // org.a.b.f.ak
    public final int a(byte[] bArr, int i, int i2) {
        if (this.f4334b < this.f4333a.length) {
            int min = Math.min(i2, this.f4333a.length - this.f4334b);
            System.arraycopy(this.f4333a, this.f4334b, bArr, i, min);
            this.f4334b += min;
            return min;
        }
        return -1;
    }

    @Override // org.a.b.f.ak
    public final long e() {
        return this.f4334b;
    }

    @Override // org.a.b.f.ak
    public final InputStream f() {
        return new ByteArrayInputStream(this.f4333a);
    }

    @Override // org.a.b.f.ak
    public final long g() {
        return this.f4333a.length;
    }
}
