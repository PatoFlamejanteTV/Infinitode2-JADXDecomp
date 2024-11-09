package com.a.a.c.c;

import com.a.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/m.class */
public final class m extends v {
    private com.a.a.c.f.n g;
    private b.a h;
    private v i;
    private int j;
    private boolean k;

    private m(com.a.a.c.w wVar, com.a.a.c.j jVar, com.a.a.c.w wVar2, com.a.a.c.i.e eVar, com.a.a.c.m.b bVar, com.a.a.c.f.n nVar, int i, b.a aVar, com.a.a.c.v vVar) {
        super(wVar, jVar, wVar2, eVar, bVar, vVar);
        this.g = nVar;
        this.j = i;
        this.h = aVar;
        this.i = null;
    }

    public static m a(com.a.a.c.w wVar, com.a.a.c.j jVar, com.a.a.c.w wVar2, com.a.a.c.i.e eVar, com.a.a.c.m.b bVar, com.a.a.c.f.n nVar, int i, b.a aVar, com.a.a.c.v vVar) {
        return new m(wVar, jVar, wVar2, eVar, bVar, nVar, i, aVar, vVar);
    }

    private m(m mVar, com.a.a.c.w wVar) {
        super(mVar, wVar);
        this.g = mVar.g;
        this.h = mVar.h;
        this.i = mVar.i;
        this.j = mVar.j;
        this.k = mVar.k;
    }

    private m(m mVar, com.a.a.c.k<?> kVar, s sVar) {
        super(mVar, kVar, sVar);
        this.g = mVar.g;
        this.h = mVar.h;
        this.i = mVar.i;
        this.j = mVar.j;
        this.k = mVar.k;
    }

    @Override // com.a.a.c.c.v
    public final v a(com.a.a.c.w wVar) {
        return new m(this, wVar);
    }

    @Override // com.a.a.c.c.v
    public final v a(com.a.a.c.k<?> kVar) {
        if (this.c == kVar) {
            return this;
        }
        return new m(this, kVar, this.c == this.e ? kVar : this.e);
    }

    @Override // com.a.a.c.c.v
    public final v a(s sVar) {
        return new m(this, this.c, sVar);
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.c.f fVar) {
        if (this.i != null) {
            this.i.a(fVar);
        }
    }

    public final void a(v vVar) {
        this.i = vVar;
    }

    @Override // com.a.a.c.c.v
    public final void f() {
        this.k = true;
    }

    @Override // com.a.a.c.c.v
    public final boolean g() {
        return this.k;
    }

    @Override // com.a.a.c.c.v, com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return this.g;
    }

    @Override // com.a.a.c.c.v
    public final int h() {
        return this.j;
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        u();
        this.i.a(obj, a(lVar, gVar));
    }

    @Override // com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        u();
        return this.i.b(obj, a(lVar, gVar));
    }

    @Override // com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        u();
        this.i.a(obj, obj2);
    }

    @Override // com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        u();
        return this.i.b(obj, obj2);
    }

    @Override // com.a.a.c.f.v, com.a.a.c.c
    public final com.a.a.c.v d() {
        com.a.a.c.v d = super.d();
        if (this.i != null) {
            return d.a(this.i.d().d());
        }
        return d;
    }

    @Override // com.a.a.c.c.v
    public final Object i() {
        if (this.h == null) {
            return null;
        }
        return this.h.a();
    }

    @Override // com.a.a.c.c.v
    public final boolean j() {
        return (this.h == null || this.h.a(true)) ? false : true;
    }

    @Override // com.a.a.c.c.v
    public final String toString() {
        return "[creator property, name " + com.a.a.c.m.i.b(a()) + "; inject id '" + i() + "']";
    }

    private final void u() {
        if (this.i == null) {
            b((com.a.a.b.l) null, (com.a.a.c.g) null);
        }
    }

    private void b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String str = "No fallback setter/field defined for creator property " + com.a.a.c.m.i.b(a());
        if (gVar != null) {
            gVar.a(c(), str);
            return;
        }
        throw com.a.a.c.d.b.a(lVar, str, c());
    }
}
