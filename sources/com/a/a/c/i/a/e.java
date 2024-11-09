package com.a.a.c.i.a;

import com.a.a.a.af;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/e.class */
public final class e extends a {
    public e(com.a.a.c.j jVar, com.a.a.c.i.g gVar, String str, boolean z, com.a.a.c.j jVar2) {
        super(jVar, gVar, str, z, jVar2);
    }

    private e(e eVar, com.a.a.c.c cVar) {
        super(eVar, cVar);
    }

    @Override // com.a.a.c.i.a.a, com.a.a.c.i.e
    public final com.a.a.c.i.e a(com.a.a.c.c cVar) {
        if (cVar == this.c) {
            return this;
        }
        return new e(this, cVar);
    }

    @Override // com.a.a.c.i.a.a, com.a.a.c.i.e
    public final af.a a() {
        return af.a.EXTERNAL_PROPERTY;
    }

    @Override // com.a.a.c.i.a.a
    protected final boolean f() {
        return true;
    }
}
