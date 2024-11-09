package org.a.c.h.g.b;

/* loaded from: infinitode-2.jar:org/a/c/h/g/b/r.class */
public final class r implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4613a;

    public r() {
        this.f4613a = new org.a.c.b.d();
    }

    public r(org.a.c.b.d dVar) {
        this.f4613a = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.f4613a;
    }

    public final void a(float f) {
        f().a(org.a.c.b.j.dX, 0);
    }

    public final float a() {
        if (f().a(org.a.c.b.j.dX) instanceof org.a.c.b.j) {
            return 0.0f;
        }
        return f().b(org.a.c.b.j.dX, 1.0f);
    }

    public final void a(String str) {
        f().a(org.a.c.b.j.dn, str);
    }
}
