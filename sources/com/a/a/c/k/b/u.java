package com.a.a.c.k.b;

import com.a.a.c.c;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/u.class */
public final class u extends com.a.a.c.k.p {

    /* renamed from: b, reason: collision with root package name */
    private static final com.a.a.c.c f632b = new c.a();
    private com.a.a.c.i.i c;
    private com.a.a.c.c d;
    private Object e;
    private Object f;
    private com.a.a.c.o<Object> g;
    private com.a.a.c.o<Object> h;

    public u(com.a.a.c.i.i iVar, com.a.a.c.c cVar) {
        super(cVar == null ? com.a.a.c.v.c : cVar.d());
        this.c = iVar;
        this.d = cVar == null ? f632b : cVar;
    }

    public final void a(Object obj, Object obj2, com.a.a.c.o<Object> oVar, com.a.a.c.o<Object> oVar2) {
        this.e = obj;
        this.f = obj2;
        this.g = oVar;
        this.h = oVar2;
    }

    @Override // com.a.a.c.c, com.a.a.c.m.v
    public final String a() {
        if (this.e instanceof String) {
            return (String) this.e;
        }
        return String.valueOf(this.e);
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.w b() {
        return new com.a.a.c.w(a());
    }

    @Override // com.a.a.c.k.p
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        this.g.a(this.e, hVar, aaVar);
        if (this.c == null) {
            this.h.a(this.f, hVar, aaVar);
        } else {
            this.h.a(this.f, hVar, aaVar, this.c);
        }
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.j c() {
        return this.d.c();
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return this.d.e();
    }
}
