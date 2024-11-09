package org.a.c.b;

/* loaded from: infinitode-2.jar:org/a/c/b/h.class */
class h extends org.a.c.d.c {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ org.a.c.d.e f4365a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(org.a.c.d.e eVar, org.a.c.d.e eVar2) {
        super(eVar);
        this.f4365a = eVar2;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f4365a.close();
    }
}
