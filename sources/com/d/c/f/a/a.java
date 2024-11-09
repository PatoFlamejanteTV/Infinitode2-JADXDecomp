package com.d.c.f.a;

import java.awt.Rectangle;

/* loaded from: infinitode-2.jar:com/d/c/f/a/a.class */
public final class a extends h {

    /* renamed from: a, reason: collision with root package name */
    public static final a f1074a = new a(0.0f, 0.0f, 0.0f, 0.0f);
    private com.d.c.a.c g;
    private com.d.c.a.c h;
    private com.d.c.a.c i;
    private com.d.c.a.c j;
    private com.d.c.d.g k;
    private com.d.c.d.g l;
    private com.d.c.d.g m;
    private com.d.c.d.g n;
    private com.d.c.f.b o;
    private com.d.c.f.b p;
    private com.d.c.f.b q;
    private com.d.c.f.b r;

    private a(a aVar) {
        this(aVar.t(), aVar.u(), aVar.v(), aVar.w());
        this.g = aVar.f();
        this.h = aVar.g();
        this.i = aVar.h();
        this.j = aVar.i();
        this.k = aVar.j();
        this.l = aVar.k();
        this.m = aVar.l();
        this.n = aVar.m();
        this.o = aVar.o;
        this.p = aVar.p;
        this.r = aVar.r;
        this.q = aVar.q;
    }

    private a(float f, float f2, float f3, float f4, com.d.c.f.b bVar, com.d.c.f.b bVar2, com.d.c.f.b bVar3, com.d.c.f.b bVar4) {
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.f = f4;
        this.o = bVar;
        this.p = bVar2;
        this.r = bVar4;
        this.q = bVar3;
    }

    public a(float f, float f2, float f3, float f4) {
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.f = f4;
        this.o = new com.d.c.f.b();
        this.p = new com.d.c.f.b();
        this.r = new com.d.c.f.b();
        this.q = new com.d.c.f.b();
    }

    public a(com.d.f.a aVar, com.d.f.a aVar2, com.d.f.a aVar3, com.d.f.a aVar4) {
        this(aVar.c(), aVar2.c(), aVar3.c(), aVar4.c());
        this.g = aVar.b();
        this.h = aVar2.b();
        this.i = aVar3.b();
        this.j = aVar4.b();
        this.k = aVar.a();
        this.l = aVar2.a();
        this.m = aVar3.a();
        this.n = aVar4.a();
        this.o = new com.d.c.f.b();
        this.p = new com.d.c.f.b();
        this.r = new com.d.c.f.b();
        this.q = new com.d.c.f.b();
    }

    private a(com.d.c.f.c cVar, com.d.c.f.d dVar) {
        this.c = (cVar.a(com.d.c.a.a.aG, com.d.c.a.c.ap) || cVar.a(com.d.c.a.a.aG, com.d.c.a.c.P)) ? 0.0f : cVar.c(com.d.c.a.a.aK, 0.0f, dVar);
        this.d = (cVar.a(com.d.c.a.a.aH, com.d.c.a.c.ap) || cVar.a(com.d.c.a.a.aH, com.d.c.a.c.P)) ? 0.0f : cVar.c(com.d.c.a.a.aL, 0.0f, dVar);
        this.e = (cVar.a(com.d.c.a.a.aI, com.d.c.a.c.ap) || cVar.a(com.d.c.a.a.aI, com.d.c.a.c.P)) ? 0.0f : cVar.c(com.d.c.a.a.aM, 0.0f, dVar);
        this.f = (cVar.a(com.d.c.a.a.aJ, com.d.c.a.c.ap) || cVar.a(com.d.c.a.a.aJ, com.d.c.a.c.P)) ? 0.0f : cVar.c(com.d.c.a.a.aN, 0.0f, dVar);
        this.k = cVar.a(com.d.c.a.a.aC);
        this.l = cVar.a(com.d.c.a.a.aD);
        this.m = cVar.a(com.d.c.a.a.aE);
        this.n = cVar.a(com.d.c.a.a.aF);
        this.g = cVar.e(com.d.c.a.a.aG);
        this.h = cVar.e(com.d.c.a.a.aH);
        this.i = cVar.e(com.d.c.a.a.aI);
        this.j = cVar.e(com.d.c.a.a.aJ);
        this.o = new com.d.c.f.b(com.d.c.a.a.aO, cVar, dVar);
        this.p = new com.d.c.f.b(com.d.c.a.a.aP, cVar, dVar);
        this.r = new com.d.c.f.b(com.d.c.a.a.aR, cVar, dVar);
        this.q = new com.d.c.f.b(com.d.c.a.a.aQ, cVar, dVar);
    }

    public final a a() {
        a aVar = new a(this);
        aVar.k = this.k == null ? null : this.k.e();
        aVar.m = this.m == null ? null : this.m.e();
        aVar.n = this.n == null ? null : this.n.e();
        aVar.l = this.l == null ? null : this.l.e();
        return aVar;
    }

    public static a a(com.d.c.f.c cVar, com.d.c.f.d dVar) {
        return new a(cVar, dVar);
    }

    @Override // com.d.c.f.a.h
    public final String toString() {
        return "BorderPropertySet[top=" + this.c + ",right=" + this.d + ",bottom=" + this.e + ",left=" + this.f + "]";
    }

    public final boolean b() {
        return this.g == com.d.c.a.c.ap || ((int) this.c) == 0;
    }

    public final boolean c() {
        return this.h == com.d.c.a.c.ap || ((int) this.d) == 0;
    }

    public final boolean d() {
        return this.i == com.d.c.a.c.ap || ((int) this.e) == 0;
    }

    public final boolean e() {
        return this.j == com.d.c.a.c.ap || ((int) this.f) == 0;
    }

    public final com.d.c.a.c f() {
        return this.g;
    }

    public final com.d.c.a.c g() {
        return this.h;
    }

    public final com.d.c.a.c h() {
        return this.i;
    }

    public final com.d.c.a.c i() {
        return this.j;
    }

    public final com.d.c.d.g j() {
        return this.k;
    }

    public final com.d.c.d.g k() {
        return this.l;
    }

    public final com.d.c.d.g l() {
        return this.m;
    }

    public final com.d.c.d.g m() {
        return this.n;
    }

    public final boolean n() {
        return this.g == com.d.c.a.c.P || this.h == com.d.c.a.c.P || this.i == com.d.c.a.c.P || this.j == com.d.c.a.c.P;
    }

    public final boolean o() {
        return s().a() || r().a() || q().a() || p().a();
    }

    public final com.d.c.f.b p() {
        return this.q;
    }

    public final com.d.c.f.b q() {
        return this.r;
    }

    public final com.d.c.f.b r() {
        return this.p;
    }

    public final com.d.c.f.b s() {
        return this.o;
    }

    public final a a(Rectangle rectangle) {
        float min = Math.min(Math.min(Math.min(Math.min(1.0f, rectangle.width / a(this.o, this.p, rectangle.width)), rectangle.width / a(this.q, this.r, rectangle.width)), rectangle.height / a(this.p, this.q, rectangle.height)), rectangle.height / a(this.r, this.q, rectangle.height));
        a aVar = new a(this.c, this.d, this.e, this.f, new com.d.c.f.b(min * this.o.a(rectangle.height), min * this.o.b(rectangle.width)), new com.d.c.f.b(min * this.p.a(rectangle.width), min * this.p.b(rectangle.height)), new com.d.c.f.b(min * this.q.a(rectangle.height), min * this.q.b(rectangle.width)), new com.d.c.f.b(min * this.r.a(rectangle.width), min * this.r.b(rectangle.height)));
        aVar.k = this.k;
        aVar.l = this.l;
        aVar.m = this.m;
        aVar.n = this.n;
        aVar.g = this.g;
        aVar.h = this.h;
        aVar.i = this.i;
        aVar.j = this.j;
        return aVar;
    }

    private static float a(com.d.c.f.b bVar, com.d.c.f.b bVar2, float f) {
        return Math.max(f, bVar.b(f) + bVar2.a(f));
    }
}
