package com.a.a.c.k.b;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ad.class */
public final class ad<T> extends ao<T> {
    public ad(Class<?> cls) {
        super(cls, (byte) 0);
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        hVar.d(t.toString());
    }

    @Override // com.a.a.c.o
    public final void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(t, com.a.a.b.o.VALUE_EMBEDDED_OBJECT));
        a((ad<T>) t, hVar, aaVar);
        iVar.b(hVar, a2);
    }
}
