package com.a.a.c.i;

import com.a.a.c.b.q;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/i/d.class */
public abstract class d {
    public Collection<b> a(q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2) {
        qVar.j();
        return a(jVar, qVar, jVar2);
    }

    public Collection<b> a(q<?> qVar, com.a.a.c.f.d dVar) {
        qVar.j();
        return a(dVar, qVar);
    }

    public Collection<b> b(q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2) {
        qVar.j();
        return a(jVar, qVar, jVar2);
    }

    public Collection<b> b(q<?> qVar, com.a.a.c.f.d dVar) {
        qVar.j();
        return a(dVar, qVar);
    }

    @Deprecated
    private Collection<b> a(com.a.a.c.f.j jVar, q<?> qVar, com.a.a.c.j jVar2) {
        return a(qVar, jVar, jVar2);
    }

    @Deprecated
    private Collection<b> a(com.a.a.c.f.d dVar, q<?> qVar) {
        return a(qVar, dVar);
    }
}
