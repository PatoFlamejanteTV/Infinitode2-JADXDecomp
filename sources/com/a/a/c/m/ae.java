package com.a.a.c.m;

/* loaded from: infinitode-2.jar:com/a/a/c/m/ae.class */
public final class ae extends com.a.a.b.n {
    private com.a.a.b.n c;
    private com.a.a.b.j d;
    private String e;
    private Object f;

    private ae(com.a.a.b.n nVar, com.a.a.b.c.d dVar) {
        super(nVar);
        this.c = nVar.a();
        this.e = nVar.g();
        this.f = nVar.h();
        if (nVar instanceof com.a.a.b.d.d) {
            this.d = ((com.a.a.b.d.d) nVar).a(dVar);
        } else {
            this.d = com.a.a.b.j.f174a;
        }
    }

    private ae(com.a.a.b.n nVar, com.a.a.b.j jVar) {
        super(nVar);
        this.c = nVar.a();
        this.e = nVar.g();
        this.f = nVar.h();
        this.d = jVar;
    }

    protected ae() {
        super(0, -1);
        this.c = null;
        this.d = com.a.a.b.j.f174a;
    }

    private ae(ae aeVar, int i, int i2) {
        super(i, -1);
        this.c = aeVar;
        this.d = aeVar.d;
    }

    @Override // com.a.a.b.n
    public final Object h() {
        return this.f;
    }

    @Override // com.a.a.b.n
    public final void a(Object obj) {
        this.f = obj;
    }

    public static ae a(com.a.a.b.n nVar) {
        if (nVar == null) {
            return new ae();
        }
        return new ae(nVar, com.a.a.b.c.d.a());
    }

    public final ae i() {
        this.f184b++;
        return new ae(this, 1, -1);
    }

    public final ae j() {
        this.f184b++;
        return new ae(this, 2, -1);
    }

    public final ae k() {
        if (this.c instanceof ae) {
            return (ae) this.c;
        }
        if (this.c == null) {
            return new ae();
        }
        return new ae(this.c, this.d);
    }

    @Override // com.a.a.b.n
    public final String g() {
        return this.e;
    }

    @Override // com.a.a.b.n
    public final com.a.a.b.n a() {
        return this.c;
    }

    public final void a(String str) {
        this.e = str;
    }

    public final void l() {
        this.f184b++;
    }
}
