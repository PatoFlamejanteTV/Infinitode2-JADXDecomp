package com.a.a.c.c.a;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/u.class */
public final class u extends com.a.a.c.c.v {
    private s g;

    public u(s sVar, com.a.a.c.v vVar) {
        super(sVar.f280a, sVar.b(), vVar, sVar.a());
        this.g = sVar;
    }

    private u(u uVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar) {
        super(uVar, kVar, sVar);
        this.g = uVar.g;
    }

    private u(u uVar, com.a.a.c.w wVar) {
        super(uVar, wVar);
        this.g = uVar.g;
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.w wVar) {
        return new u(this, wVar);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.k<?> kVar) {
        if (this.c == kVar) {
            return this;
        }
        return new u(this, kVar, this.c == this.e ? kVar : this.e);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.c.s sVar) {
        return new u(this, this.c, sVar);
    }

    @Override // com.a.a.c.c.v, com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return null;
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        b(lVar, gVar, obj);
    }

    @Override // com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            return null;
        }
        Object a2 = this.c.a(lVar, gVar);
        gVar.a(a2, this.g.f281b, this.g.c).a(obj);
        com.a.a.c.c.v vVar = this.g.d;
        if (vVar != null) {
            return vVar.b(obj, a2);
        }
        return obj;
    }

    @Override // com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        b(obj, obj2);
    }

    @Override // com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        com.a.a.c.c.v vVar = this.g.d;
        if (vVar == null) {
            throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
        }
        return vVar.b(obj, obj2);
    }
}
