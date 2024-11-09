package com.a.a.c.c.a;

import com.a.a.c.c.a.z;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/t.class */
public final class t extends com.a.a.c.c.v {
    private final com.a.a.c.c.v g;

    public t(com.a.a.c.c.v vVar, com.a.a.c.f.ad adVar) {
        super(vVar);
        this.g = vVar;
        this.f = adVar;
    }

    private t(t tVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar) {
        super(tVar, kVar, sVar);
        this.g = tVar.g;
        this.f = tVar.f;
    }

    private t(t tVar, com.a.a.c.w wVar) {
        super(tVar, wVar);
        this.g = tVar.g;
        this.f = tVar.f;
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.w wVar) {
        return new t(this, wVar);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.k<?> kVar) {
        if (this.c == kVar) {
            return this;
        }
        return new t(this, kVar, this.c == this.e ? kVar : this.e);
    }

    @Override // com.a.a.c.c.v
    public final com.a.a.c.c.v a(com.a.a.c.c.s sVar) {
        return new t(this, this.c, sVar);
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.c.f fVar) {
        if (this.g != null) {
            this.g.a(fVar);
        }
    }

    @Override // com.a.a.c.c.v, com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return this.g.e();
    }

    @Override // com.a.a.c.c.v
    public final int h() {
        return this.g.h();
    }

    @Override // com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        b(lVar, gVar, obj);
    }

    @Override // com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        try {
            return b(obj, a(lVar, gVar));
        } catch (com.a.a.c.c.w e) {
            if (!((this.f == null && this.c.f() == null) ? false : true)) {
                throw com.a.a.c.l.a(lVar, "Unresolved forward reference but no identity info", e);
            }
            e.d().a((z.a) new a(this, e, this.f415b.b(), obj));
            return null;
        }
    }

    @Override // com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        this.g.a(obj, obj2);
    }

    @Override // com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        return this.g.b(obj, obj2);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/t$a.class */
    public static final class a extends z.a {

        /* renamed from: a, reason: collision with root package name */
        private final t f282a;

        /* renamed from: b, reason: collision with root package name */
        private Object f283b;

        public a(t tVar, com.a.a.c.c.w wVar, Class<?> cls, Object obj) {
            super(wVar, cls);
            this.f282a = tVar;
            this.f283b = obj;
        }

        @Override // com.a.a.c.c.a.z.a
        public final void a(Object obj, Object obj2) {
            if (!b(obj)) {
                throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
            }
            this.f282a.a(this.f283b, obj2);
        }
    }
}
