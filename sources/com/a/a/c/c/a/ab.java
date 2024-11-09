package com.a.a.c.c.a;

import java.io.Serializable;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/ab.class */
public final class ab extends com.a.a.c.k<Object> implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.i.e f248a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.k<Object> f249b;

    public ab(com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        this.f248a = eVar;
        this.f249b = kVar;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return this.f249b.b();
    }

    @Override // com.a.a.c.k
    public final Class<?> a() {
        return this.f249b.a();
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return this.f249b.a(fVar);
    }

    @Override // com.a.a.c.k
    public final Collection<Object> d() {
        return this.f249b.d();
    }

    @Override // com.a.a.c.k, com.a.a.c.c.s
    public final Object a(com.a.a.c.g gVar) {
        return this.f249b.a(gVar);
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return this.f249b.c(gVar);
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return this.f249b.a(lVar, gVar, this.f248a);
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return this.f249b.a(lVar, gVar, (com.a.a.c.g) obj);
    }
}
