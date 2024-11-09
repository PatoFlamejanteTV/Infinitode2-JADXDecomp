package org.a.c.h.g.b;

/* loaded from: infinitode-2.jar:org/a/c/h/g/b/o.class */
public final class o implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4611a;

    public o() {
        this.f4611a = new org.a.c.b.d();
        this.f4611a.a(org.a.c.b.j.co, (org.a.c.b.b) new org.a.c.b.d());
    }

    public o(org.a.c.b.d dVar) {
        this.f4611a = dVar;
    }

    @Override // org.a.c.h.a.c
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4611a;
    }

    public final p b() {
        org.a.c.b.b a2 = this.f4611a.a(org.a.c.b.j.co);
        if (a2 instanceof org.a.c.b.d) {
            return new p(a2);
        }
        return null;
    }

    public final void a(q qVar) {
        this.f4611a.a(org.a.c.b.j.co, qVar);
    }
}
