package com.a.a.c.k.a;

import com.a.a.a.s;
import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/a.class */
public final class a extends com.a.a.c.k.s {
    private String j;

    private a(String str, com.a.a.c.f.s sVar, com.a.a.c.m.b bVar, com.a.a.c.j jVar) {
        this(str, sVar, bVar, jVar, sVar.B());
    }

    private a(String str, com.a.a.c.f.s sVar, com.a.a.c.m.b bVar, com.a.a.c.j jVar, s.b bVar2) {
        super(sVar, bVar, jVar, null, null, null, bVar2, null);
        this.j = str;
    }

    public static a a(String str, com.a.a.c.f.s sVar, com.a.a.c.m.b bVar, com.a.a.c.j jVar) {
        return new a(str, sVar, bVar, jVar);
    }

    @Override // com.a.a.c.k.s
    public final com.a.a.c.k.s l() {
        throw new IllegalStateException("Should not be called on this type");
    }

    @Override // com.a.a.c.k.s
    protected final Object a(aa aaVar) {
        return aaVar.a((Object) this.j);
    }
}
