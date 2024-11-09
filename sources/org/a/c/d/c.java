package org.a.c.d;

import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/c/d/c.class */
public class c extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4418a = org.a.a.a.c.a(c.class);

    /* renamed from: b, reason: collision with root package name */
    private final e f4419b;
    private long c = 0;

    public c(e eVar) {
        this.f4419b = eVar;
    }

    private void a() {
        this.f4419b.a(this.c);
    }

    @Override // java.io.InputStream
    public int available() {
        a();
        long c = this.f4419b.c() - this.f4419b.a();
        if (c > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) c;
    }

    @Override // java.io.InputStream
    public int read() {
        a();
        if (this.f4419b.e()) {
            return -1;
        }
        int b2 = this.f4419b.b();
        if (b2 != -1) {
            this.c++;
        } else {
            new StringBuilder("read() returns -1, assumed position: ").append(this.c).append(", actual position: ").append(this.f4419b.a());
        }
        return b2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        a();
        if (this.f4419b.e()) {
            return -1;
        }
        int a2 = this.f4419b.a(bArr, i, i2);
        if (a2 != -1) {
            this.c += a2;
        } else {
            new StringBuilder("read() returns -1, assumed position: ").append(this.c).append(", actual position: ").append(this.f4419b.a());
        }
        return a2;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        a();
        this.f4419b.a(this.c + j);
        this.c += j;
        return j;
    }
}
