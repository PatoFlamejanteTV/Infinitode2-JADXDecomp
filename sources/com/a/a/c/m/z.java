package com.a.a.c.m;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/m/z.class */
public final class z implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private transient o<com.a.a.c.l.b, com.a.a.c.w> f753a = new o<>(20, 200);

    public final com.a.a.c.w a(com.a.a.c.j jVar, com.a.a.c.b.q<?> qVar) {
        return a(jVar.b(), qVar);
    }

    public final com.a.a.c.w a(Class<?> cls, com.a.a.c.b.q<?> qVar) {
        com.a.a.c.l.b bVar = new com.a.a.c.l.b(cls);
        com.a.a.c.w a2 = this.f753a.a(bVar);
        if (a2 != null) {
            return a2;
        }
        com.a.a.c.w a3 = qVar.j().a(qVar.c(cls).d());
        com.a.a.c.w wVar = a3;
        if (a3 == null || !wVar.c()) {
            wVar = com.a.a.c.w.a(cls.getSimpleName());
        }
        this.f753a.a(bVar, wVar);
        return wVar;
    }
}
