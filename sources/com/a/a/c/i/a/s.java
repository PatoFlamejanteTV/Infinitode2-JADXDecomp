package com.a.a.c.i.a;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/s.class */
public abstract class s implements com.a.a.c.i.g {

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.l.o f513a;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.j f514b;

    /* JADX INFO: Access modifiers changed from: protected */
    public s(com.a.a.c.j jVar, com.a.a.c.l.o oVar) {
        this.f514b = jVar;
        this.f513a = oVar;
    }

    @Override // com.a.a.c.i.g
    public final String a() {
        return a((Object) null, this.f514b.b());
    }

    @Override // com.a.a.c.i.g
    public com.a.a.c.j a(com.a.a.c.d dVar, String str) {
        throw new IllegalStateException("Sub-class " + getClass().getName() + " MUST implement `typeFromId(DatabindContext,String)");
    }

    @Override // com.a.a.c.i.g
    public String b() {
        return null;
    }
}
