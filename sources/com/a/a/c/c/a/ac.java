package com.a.a.c.c.a;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/ac.class */
public final class ac extends com.a.a.c.c.b.ae<Object> {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.j f250a;

    /* renamed from: b, reason: collision with root package name */
    private String f251b;

    public ac(com.a.a.c.j jVar, String str) {
        super(jVar);
        this.f250a = jVar;
        this.f251b = str;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object N;
        if (lVar.k() == com.a.a.b.o.VALUE_EMBEDDED_OBJECT && ((N = lVar.N()) == null || this.f250a.b().isAssignableFrom(N.getClass()))) {
            return N;
        }
        gVar.a(this.f250a, this.f251b);
        return null;
    }
}
