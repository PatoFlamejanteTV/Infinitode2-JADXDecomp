package com.a.a.c.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ad.class */
public class ad<T> extends ae<T> implements com.a.a.c.c.k, com.a.a.c.c.t {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.m.k<Object, T> f308a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.j f309b;
    private com.a.a.c.k<Object> c;

    public ad(com.a.a.c.m.k<?, T> kVar) {
        super((Class<?>) Object.class);
        this.f308a = kVar;
        this.f309b = null;
        this.c = null;
    }

    public ad(com.a.a.c.m.k<Object, T> kVar, com.a.a.c.j jVar, com.a.a.c.k<?> kVar2) {
        super(jVar);
        this.f308a = kVar;
        this.f309b = jVar;
        this.c = kVar2;
    }

    private ad<T> a(com.a.a.c.m.k<Object, T> kVar, com.a.a.c.j jVar, com.a.a.c.k<?> kVar2) {
        com.a.a.c.m.i.a((Class<?>) ad.class, this, "withDelegate");
        return new ad<>(kVar, jVar, kVar2);
    }

    @Override // com.a.a.c.c.t
    public final void d(com.a.a.c.g gVar) {
        if (this.c != null && (this.c instanceof com.a.a.c.c.t)) {
            ((com.a.a.c.c.t) this.c).d(gVar);
        }
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        if (this.c != null) {
            com.a.a.c.k<?> b2 = gVar.b(this.c, cVar, this.f309b);
            if (b2 != this.c) {
                return a(this.f308a, this.f309b, b2);
            }
            return this;
        }
        com.a.a.c.m.k<Object, T> kVar = this.f308a;
        gVar.b();
        com.a.a.c.j a2 = kVar.a();
        return a(this.f308a, a2, (com.a.a.c.k<?>) gVar.a(a2, cVar));
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Class<?> a() {
        return this.c.a();
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return this.c.b();
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return this.c.a(fVar);
    }

    @Override // com.a.a.c.k
    public final T a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2 = this.c.a(lVar, gVar);
        if (a2 == null) {
            return null;
        }
        return b(a2);
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        Object a2 = this.c.a(lVar, gVar);
        if (a2 == null) {
            return null;
        }
        return b(a2);
    }

    @Override // com.a.a.c.k
    public final T a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        if (this.f309b.b().isAssignableFrom(obj.getClass())) {
            return (T) this.c.a(lVar, gVar, (com.a.a.c.g) obj);
        }
        return (T) a(obj);
    }

    private Object a(Object obj) {
        throw new UnsupportedOperationException(String.format("Cannot update object of type %s (using deserializer for type %s)" + obj.getClass().getName(), this.f309b));
    }

    private T b(Object obj) {
        return this.f308a.a(obj);
    }
}
