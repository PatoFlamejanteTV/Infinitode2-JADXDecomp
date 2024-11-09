package com.a.a.c.k.b;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/at.class */
public abstract class at extends ao<Object> {
    public abstract String a(Object obj);

    public at(Class<?> cls) {
        super(cls, (byte) 0);
    }

    @Override // com.a.a.c.o
    public boolean a(com.a.a.c.aa aaVar, Object obj) {
        return a(obj).isEmpty();
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        hVar.b(a(obj));
    }

    @Override // com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(obj, com.a.a.b.o.VALUE_STRING));
        a(obj, hVar, aaVar);
        iVar.b(hVar, a2);
    }
}
