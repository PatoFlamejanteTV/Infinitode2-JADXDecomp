package org.a.c.f;

/* loaded from: infinitode-2.jar:org/a/c/f/j.class */
final class j implements k {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.d.e f4441a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(org.a.c.d.e eVar) {
        this.f4441a = eVar;
    }

    @Override // org.a.c.f.k
    public final int a() {
        return this.f4441a.b();
    }

    @Override // org.a.c.f.k
    public final int a(byte[] bArr) {
        return this.f4441a.b(bArr);
    }

    @Override // org.a.c.f.k
    public final int a(byte[] bArr, int i, int i2) {
        return this.f4441a.a(bArr, i, i2);
    }

    @Override // org.a.c.f.k
    public final long b() {
        return this.f4441a.a();
    }

    @Override // org.a.c.f.k
    public final int c() {
        return this.f4441a.f();
    }

    @Override // org.a.c.f.k
    public final void a(int i) {
        this.f4441a.b(1);
    }

    @Override // org.a.c.f.k
    public final void b(byte[] bArr) {
        this.f4441a.b(bArr.length);
    }

    @Override // org.a.c.f.k
    public final void b(byte[] bArr, int i, int i2) {
        this.f4441a.b(i2);
    }

    @Override // org.a.c.f.k
    public final byte[] b(int i) {
        return this.f4441a.c(i);
    }

    @Override // org.a.c.f.k
    public final boolean d() {
        return this.f4441a.e();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f4441a.close();
    }
}
