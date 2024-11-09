package com.a.a.c.c.a;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/r.class */
public final class r implements com.a.a.c.c.s, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.w f278a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.j f279b;

    private r(com.a.a.c.w wVar, com.a.a.c.j jVar) {
        this.f278a = wVar;
        this.f279b = jVar;
    }

    public static r a(com.a.a.c.c cVar) {
        return a(cVar, cVar.c());
    }

    public static r a(com.a.a.c.c cVar, com.a.a.c.j jVar) {
        return new r(cVar.b(), jVar);
    }

    public static r a(com.a.a.c.j jVar) {
        return new r(null, jVar);
    }

    @Override // com.a.a.c.c.s
    public final Object a(com.a.a.c.g gVar) {
        throw com.a.a.c.d.d.a(gVar, this.f278a, this.f279b);
    }
}
