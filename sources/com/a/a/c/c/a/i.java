package com.a.a.c.c.a;

import java.lang.reflect.Field;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/i.class */
public final class i extends com.a.a.c.c.v {
    private com.a.a.c.f.h g;
    private transient Field h;
    private boolean i;

    public i(com.a.a.c.f.s sVar, com.a.a.c.j jVar, com.a.a.c.i.e eVar, com.a.a.c.m.b bVar, com.a.a.c.f.h hVar) {
        super(sVar, jVar, eVar, bVar);
        this.g = hVar;
        this.h = hVar.a();
        this.i = q.a(this.e);
    }

    private i(i iVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar) {
        super(iVar, kVar, sVar);
        this.g = iVar.g;
        this.h = iVar.h;
        this.i = q.a(sVar);
    }

    private i(i iVar, com.a.a.c.w wVar) {
        super(iVar, wVar);
        this.g = iVar.g;
        this.h = iVar.h;
        this.i = iVar.i;
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.w wVar) {
        return new i(this, wVar);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.k<?> kVar) {
        if (this.c == kVar) {
            return this;
        }
        return new i(this, kVar, this.c == this.e ? kVar : this.e);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.c.s sVar) {
        return new i(this, this.c, sVar);
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.c.f fVar) {
        com.a.a.c.m.i.a(this.h, fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    @Override // com.a.a.c.c.v, com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return this.g;
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Object a2;
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            if (this.i) {
                return;
            } else {
                a2 = this.e.a(gVar);
            }
        } else if (this.d == null) {
            Object a3 = this.c.a(lVar, gVar);
            a2 = a3;
            if (a3 == null) {
                if (this.i) {
                    return;
                } else {
                    a2 = this.e.a(gVar);
                }
            }
        } else {
            a2 = this.c.a(lVar, gVar, this.d);
        }
        try {
            this.h.set(obj, a2);
        } catch (Exception e) {
            a(lVar, e, a2);
        }
    }

    @Override // com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Object a2;
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            if (this.i) {
                return obj;
            }
            a2 = this.e.a(gVar);
        } else if (this.d == null) {
            Object a3 = this.c.a(lVar, gVar);
            a2 = a3;
            if (a3 == null) {
                if (this.i) {
                    return obj;
                }
                a2 = this.e.a(gVar);
            }
        } else {
            a2 = this.c.a(lVar, gVar, this.d);
        }
        try {
            this.h.set(obj, a2);
        } catch (Exception e) {
            a(lVar, e, a2);
        }
        return obj;
    }

    @Override // com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        try {
            this.h.set(obj, obj2);
        } catch (Exception e) {
            a(e, obj2);
        }
    }

    @Override // com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        try {
            this.h.set(obj, obj2);
        } catch (Exception e) {
            a(e, obj2);
        }
        return obj;
    }
}
