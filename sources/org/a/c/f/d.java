package org.a.c.f;

import java.io.InputStream;
import java.io.PushbackInputStream;

/* loaded from: infinitode-2.jar:org/a/c/f/d.class */
final class d implements k {

    /* renamed from: a, reason: collision with root package name */
    private final PushbackInputStream f4433a;

    /* renamed from: b, reason: collision with root package name */
    private int f4434b = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(InputStream inputStream) {
        this.f4433a = new PushbackInputStream(inputStream, 32767);
    }

    @Override // org.a.c.f.k
    public final int a() {
        int read = this.f4433a.read();
        this.f4434b++;
        return read;
    }

    @Override // org.a.c.f.k
    public final int a(byte[] bArr) {
        int read = this.f4433a.read(bArr);
        if (read > 0) {
            this.f4434b += read;
            return read;
        }
        return -1;
    }

    @Override // org.a.c.f.k
    public final int a(byte[] bArr, int i, int i2) {
        int read = this.f4433a.read(bArr, i, i2);
        if (read > 0) {
            this.f4434b += read;
            return read;
        }
        return -1;
    }

    @Override // org.a.c.f.k
    public final long b() {
        return this.f4434b;
    }

    @Override // org.a.c.f.k
    public final int c() {
        int read = this.f4433a.read();
        if (read != -1) {
            this.f4433a.unread(read);
        }
        return read;
    }

    @Override // org.a.c.f.k
    public final void a(int i) {
        this.f4433a.unread(i);
        this.f4434b--;
    }

    @Override // org.a.c.f.k
    public final void b(byte[] bArr) {
        this.f4433a.unread(bArr);
        this.f4434b -= bArr.length;
    }

    @Override // org.a.c.f.k
    public final void b(byte[] bArr, int i, int i2) {
        this.f4433a.unread(bArr, 0, i2);
        this.f4434b -= i2;
    }

    @Override // org.a.c.f.k
    public final byte[] b(int i) {
        int a2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = i;
        while (i3 > 0 && (a2 = a(bArr, i2, i3)) > 0) {
            i2 += a2;
            i3 -= a2;
            this.f4434b += a2;
        }
        return bArr;
    }

    @Override // org.a.c.f.k
    public final boolean d() {
        return c() == -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f4433a.close();
    }
}
