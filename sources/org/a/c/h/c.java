package org.a.c.h;

import org.a.c.h.f.a.s;

/* loaded from: infinitode-2.jar:org/a/c/h/c.class */
public final class c implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4494a;

    /* renamed from: b, reason: collision with root package name */
    private final b f4495b;

    public c(b bVar) {
        this.f4495b = bVar;
        this.f4494a = new org.a.c.b.d();
        this.f4494a.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.N);
        this.f4495b.a().h().a(org.a.c.b.j.di, (org.a.c.b.b) this.f4494a);
    }

    public c(b bVar, org.a.c.b.d dVar) {
        this.f4495b = bVar;
        this.f4494a = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.f4494a;
    }

    public final void a(org.a.c.h.g.e.d dVar) {
        this.f4494a.a(org.a.c.b.j.c, dVar);
    }

    public final h a() {
        return new h((org.a.c.b.d) this.f4494a.a(org.a.c.b.j.cL), this.f4495b);
    }

    public final org.a.c.h.g.f.a b() {
        org.a.c.b.b a2 = this.f4494a.a(org.a.c.b.j.dW);
        if (a2 instanceof org.a.c.b.d) {
            return new org.a.c.h.g.f.a((org.a.c.b.d) a2);
        }
        return null;
    }

    public final void a(org.a.c.h.g.f.a aVar) {
        this.f4494a.a(org.a.c.b.j.dW, aVar);
    }

    public final org.a.c.h.g.d.b.a c() {
        org.a.c.b.b a2 = this.f4494a.a(org.a.c.b.j.cE);
        if (a2 instanceof org.a.c.b.d) {
            return new org.a.c.h.g.d.b.a((org.a.c.b.d) a2);
        }
        return null;
    }

    public final void a(org.a.c.h.g.d.b.a aVar) {
        this.f4494a.a(org.a.c.b.j.cE, aVar);
    }

    public final void a(org.a.c.h.a.f fVar) {
        this.f4494a.a(org.a.c.b.j.cj, fVar);
    }

    public final void a(org.a.c.h.b.a.a aVar) {
        this.f4494a.a(org.a.c.b.j.ce, aVar);
    }

    public final void a(s sVar) {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4494a.a(org.a.c.b.j.cI);
        org.a.c.b.a aVar2 = aVar;
        if (aVar == null) {
            aVar2 = new org.a.c.b.a();
            this.f4494a.a(org.a.c.b.j.cI, (org.a.c.b.b) aVar2);
        }
        aVar2.a(sVar.f());
    }

    public final void a(String str) {
        this.f4494a.b(org.a.c.b.j.bU, str);
    }

    public final String d() {
        return this.f4494a.g(org.a.c.b.j.dV);
    }

    public final void b(String str) {
        this.f4494a.a(org.a.c.b.j.dV, str);
    }

    public final org.a.c.h.f.d.a e() {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4494a.a(org.a.c.b.j.cz);
        if (dVar == null) {
            return null;
        }
        return new org.a.c.h.f.d.a(dVar);
    }

    public final void a(org.a.c.h.f.d.a aVar) {
        this.f4494a.a(org.a.c.b.j.cz, aVar);
        if (this.f4495b.i() < 1.5d) {
            this.f4495b.a(1.5f);
        }
    }
}
