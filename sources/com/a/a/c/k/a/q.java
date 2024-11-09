package com.a.a.c.k.a;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/q.class */
public final class q extends com.a.a.c.o<Object> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.i.i f581a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.o<Object> f582b;

    public q(com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar) {
        this.f581a = iVar;
        this.f582b = oVar;
    }

    @Override // com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        this.f582b.a(obj, hVar, aaVar, this.f581a);
    }

    @Override // com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        this.f582b.a(obj, hVar, aaVar, iVar);
    }

    @Override // com.a.a.c.o
    public final Class<Object> a() {
        return Object.class;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<?> oVar = this.f582b;
        com.a.a.c.o<?> oVar2 = oVar;
        if (oVar instanceof com.a.a.c.k.k) {
            oVar2 = aaVar.b(oVar2, cVar);
        }
        if (oVar2 == this.f582b) {
            return this;
        }
        return new q(this.f581a, oVar2);
    }
}
