package com.a.a.c.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ab.class */
public abstract class ab<T> extends ae<T> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.j f303a;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.c.x f304b;
    private com.a.a.c.i.e d;
    protected final com.a.a.c.k<Object> c;

    protected abstract ab<T> a(com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar);

    @Override // com.a.a.c.k, com.a.a.c.c.s
    public abstract T a(com.a.a.c.g gVar);

    public abstract T b(Object obj);

    public abstract T a(T t, Object obj);

    public abstract Object a(T t);

    public ab(com.a.a.c.j jVar, com.a.a.c.c.x xVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        super(jVar);
        this.f304b = xVar;
        this.f303a = jVar;
        this.c = kVar;
        this.d = eVar;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        com.a.a.c.k<?> kVar = this.c;
        if (kVar == null) {
            b2 = gVar.a(this.f303a.E(), cVar);
        } else {
            b2 = gVar.b(kVar, cVar, this.f303a.E());
        }
        com.a.a.c.i.e eVar = this.d;
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar != null) {
            eVar2 = eVar2.a(cVar);
        }
        if (b2 == this.c && eVar2 == this.d) {
            return this;
        }
        return a(eVar2, b2);
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.DYNAMIC;
    }

    @Override // com.a.a.c.k
    public Object c(com.a.a.c.g gVar) {
        return a(gVar);
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.c.x i() {
        return this.f304b;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.j h() {
        return this.f303a;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        if (this.c != null) {
            return this.c.b();
        }
        return super.b();
    }

    @Override // com.a.a.c.k
    public Boolean a(com.a.a.c.f fVar) {
        if (this.c == null) {
            return null;
        }
        return this.c.a(fVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.a.a.c.k
    public final T a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2;
        if (this.f304b != null) {
            return (T) a(lVar, gVar, (com.a.a.c.g) this.f304b.a(gVar));
        }
        if (this.d == null) {
            a2 = this.c.a(lVar, gVar);
        } else {
            a2 = this.c.a(lVar, gVar, this.d);
        }
        return (T) b(a2);
    }

    @Override // com.a.a.c.k
    public final T a(com.a.a.b.l lVar, com.a.a.c.g gVar, T t) {
        Object a2;
        Object obj;
        Object a3;
        if (this.c.a(gVar.a()).equals(Boolean.FALSE) || this.d != null) {
            if (this.d == null) {
                a2 = this.c.a(lVar, gVar);
            } else {
                a2 = this.c.a(lVar, gVar, this.d);
            }
            obj = a2;
        } else {
            Object a4 = a((ab<T>) t);
            if (a4 != null) {
                obj = this.c.a(lVar, gVar, (com.a.a.c.g) a4);
            } else {
                if (this.d == null) {
                    a3 = this.c.a(lVar, gVar);
                } else {
                    a3 = this.c.a(lVar, gVar, this.d);
                }
                return b(a3);
            }
        }
        return a((ab<T>) t, obj);
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            return a(gVar);
        }
        if (this.d == null) {
            return a(lVar, gVar);
        }
        return b(this.d.d(lVar, gVar));
    }
}
