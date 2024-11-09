package com.a.a.c.k.a;

import com.a.a.a.al;
import com.a.a.c.aa;
import com.a.a.c.w;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/m.class */
public class m implements com.a.a.c.k.d, com.a.a.c.k.o {

    /* renamed from: a, reason: collision with root package name */
    public final com.a.a.c.j f576a;

    /* renamed from: b, reason: collision with root package name */
    public final com.a.a.b.r f577b;
    public final al<?> c;
    public final com.a.a.c.o<Object> d;
    public final boolean e;

    public static com.a.a.c.k.o a(com.a.a.c.k.d dVar) {
        return new n(dVar);
    }

    @Override // com.a.a.c.k.d
    @Deprecated
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, com.a.a.c.k.e eVar) {
        eVar.a(obj, hVar, aaVar);
    }

    @Override // com.a.a.c.k.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, com.a.a.c.k.p pVar) {
        pVar.a(obj, hVar, aaVar);
    }

    private m(com.a.a.c.j jVar, com.a.a.b.r rVar, al<?> alVar, com.a.a.c.o<?> oVar, boolean z) {
        this.f576a = jVar;
        this.f577b = rVar;
        this.c = alVar;
        this.d = oVar;
        this.e = z;
    }

    public static m a(com.a.a.c.j jVar, w wVar, al<?> alVar, boolean z) {
        String b2 = wVar == null ? null : wVar.b();
        return new m(jVar, b2 == null ? null : new com.a.a.b.c.k(b2), alVar, null, z);
    }

    public m a(com.a.a.c.o<?> oVar) {
        return new m(this.f576a, this.f577b, this.c, oVar, this.e);
    }

    public m a(boolean z) {
        if (z == this.e) {
            return this;
        }
        return new m(this.f576a, this.f577b, this.c, this.d, z);
    }
}
