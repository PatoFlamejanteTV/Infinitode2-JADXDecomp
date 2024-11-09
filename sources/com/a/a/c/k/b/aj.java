package com.a.a.c.k.b;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/aj.class */
public class aj extends ao<Object> implements com.a.a.c.k.k, com.a.a.c.k.q {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.m.k<Object, ?> f604a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.j f605b;
    private com.a.a.c.o<Object> c;

    public aj(com.a.a.c.m.k<Object, ?> kVar, com.a.a.c.j jVar, com.a.a.c.o<?> oVar) {
        super(jVar);
        this.f604a = kVar;
        this.f605b = jVar;
        this.c = oVar;
    }

    private aj a(com.a.a.c.m.k<Object, ?> kVar, com.a.a.c.j jVar, com.a.a.c.o<?> oVar) {
        com.a.a.c.m.i.a((Class<?>) aj.class, this, "withDelegate");
        return new aj(kVar, jVar, oVar);
    }

    @Override // com.a.a.c.k.q
    public final void a(com.a.a.c.aa aaVar) {
        if (this.c != null && (this.c instanceof com.a.a.c.k.q)) {
            ((com.a.a.c.k.q) this.c).a(aaVar);
        }
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<?> oVar = this.c;
        com.a.a.c.j jVar = this.f605b;
        if (oVar == null) {
            if (jVar == null) {
                com.a.a.c.m.k<Object, ?> kVar = this.f604a;
                aaVar.b();
                jVar = kVar.b();
            }
            if (!jVar.q()) {
                oVar = aaVar.a(jVar);
            }
        }
        if (oVar instanceof com.a.a.c.k.k) {
            oVar = aaVar.b(oVar, cVar);
        }
        if (oVar == this.c && jVar == this.f605b) {
            return this;
        }
        return a(this.f604a, jVar, oVar);
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        Object a2 = a(obj);
        if (a2 == null) {
            aaVar.a(hVar);
            return;
        }
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            oVar2 = a(a2, aaVar);
        }
        oVar2.a(a2, hVar, aaVar);
    }

    @Override // com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        Object a2 = a(obj);
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            oVar2 = a(obj, aaVar);
        }
        oVar2.a(a2, hVar, aaVar, iVar);
    }

    @Override // com.a.a.c.o
    public final boolean a(com.a.a.c.aa aaVar, Object obj) {
        Object a2 = a(obj);
        if (a2 == null) {
            return true;
        }
        if (this.c == null) {
            return obj == null;
        }
        return this.c.a(aaVar, a2);
    }

    private Object a(Object obj) {
        return this.f604a.a(obj);
    }

    private static com.a.a.c.o<Object> a(Object obj, com.a.a.c.aa aaVar) {
        return aaVar.c(obj.getClass());
    }
}
