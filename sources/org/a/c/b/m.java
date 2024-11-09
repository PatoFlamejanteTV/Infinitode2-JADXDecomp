package org.a.c.b;

/* loaded from: infinitode-2.jar:org/a/c/b/m.class */
public final class m extends b implements t {

    /* renamed from: a, reason: collision with root package name */
    private b f4372a;

    /* renamed from: b, reason: collision with root package name */
    private long f4373b;
    private int c;

    public m(b bVar) {
        a(bVar);
    }

    public final b a() {
        return this.f4372a;
    }

    public final void a(b bVar) {
        this.f4372a = bVar;
    }

    public final String toString() {
        return "COSObject{" + Long.toString(this.f4373b) + ", " + Integer.toString(this.c) + "}";
    }

    public final long b() {
        return this.f4373b;
    }

    public final void a(long j) {
        this.f4373b = j;
    }

    public final int d() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return a() != null ? a().a(uVar) : k.f4371a.a(uVar);
    }

    @Override // org.a.c.b.t
    public final boolean c() {
        return false;
    }
}
