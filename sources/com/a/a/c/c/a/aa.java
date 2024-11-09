package com.a.a.c.c.a;

import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/aa.class */
public final class aa extends com.a.a.c.c.v {
    private com.a.a.c.f.k g;
    private Method h;

    public aa(com.a.a.c.f.s sVar, com.a.a.c.j jVar, com.a.a.c.i.e eVar, com.a.a.c.m.b bVar, com.a.a.c.f.k kVar) {
        super(sVar, jVar, eVar, bVar);
        this.g = kVar;
        this.h = kVar.a();
    }

    private aa(aa aaVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar) {
        super(aaVar, kVar, sVar);
        this.g = aaVar.g;
        this.h = aaVar.h;
    }

    private aa(aa aaVar, com.a.a.c.w wVar) {
        super(aaVar, wVar);
        this.g = aaVar.g;
        this.h = aaVar.h;
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.w wVar) {
        return new aa(this, wVar);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.k<?> kVar) {
        if (this.c == kVar) {
            return this;
        }
        return new aa(this, kVar, this.c == this.e ? kVar : this.e);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.c.s sVar) {
        return new aa(this, this.c, sVar);
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.c.f fVar) {
        this.g.a(fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    @Override // com.a.a.c.c.v, com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return this.g;
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            return;
        }
        if (this.d != null) {
            gVar.a(c(), String.format("Problem deserializing 'setterless' property (\"%s\"): no way to handle typed deser with setterless yet", a()));
        }
        try {
            Object invoke = this.h.invoke(obj, (Object[]) null);
            if (invoke == null) {
                gVar.a(c(), String.format("Problem deserializing 'setterless' property '%s': get method returned null", a()));
            }
            this.c.a(lVar, gVar, (com.a.a.c.g) invoke);
        } catch (Exception e) {
            a(lVar, e);
        }
    }

    @Override // com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        a(lVar, gVar, obj);
        return obj;
    }

    @Override // com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should never call `set()` on setterless property ('" + a() + "')");
    }

    @Override // com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        a(obj, obj2);
        return obj;
    }
}
