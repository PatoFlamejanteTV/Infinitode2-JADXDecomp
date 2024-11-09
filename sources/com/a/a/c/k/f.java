package com.a.a.c.k;

import com.a.a.c.aa;
import com.a.a.c.k.a.u;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/k/f.class */
public final class f extends com.a.a.c.k.b.d {
    public f(com.a.a.c.j jVar, g gVar, e[] eVarArr, e[] eVarArr2) {
        super(jVar, gVar, eVarArr, eVarArr2);
    }

    private f(com.a.a.c.k.b.d dVar, com.a.a.c.k.a.m mVar, Object obj) {
        super(dVar, mVar, obj);
    }

    private f(com.a.a.c.k.b.d dVar, Set<String> set, Set<String> set2) {
        super(dVar, set, set2);
    }

    private f(com.a.a.c.k.b.d dVar, e[] eVarArr, e[] eVarArr2) {
        super(dVar, eVarArr, eVarArr2);
    }

    public static f a(com.a.a.c.j jVar, g gVar) {
        return new f(jVar, gVar, f616b, null);
    }

    @Override // com.a.a.c.o
    public final com.a.a.c.o<Object> a(com.a.a.c.m.r rVar) {
        return new u(this, rVar);
    }

    @Override // com.a.a.c.k.b.d
    public final com.a.a.c.k.b.d a(com.a.a.c.k.a.m mVar) {
        return new f(this, mVar, this.f);
    }

    @Override // com.a.a.c.k.b.d
    public final com.a.a.c.k.b.d a(Object obj) {
        return new f(this, this.g, obj);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d a(Set<String> set, Set<String> set2) {
        return new f(this, set, set2);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d a(e[] eVarArr, e[] eVarArr2) {
        return new f(this, eVarArr, eVarArr2);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d d() {
        if (this.g == null && this.e == null && this.f == null) {
            return new com.a.a.c.k.a.b(this);
        }
        return this;
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        if (this.g != null) {
            hVar.a(obj);
            a(obj, hVar, aaVar, true);
            return;
        }
        hVar.c(obj);
        if (this.f != null) {
            c(obj, hVar, aaVar);
        } else {
            b(obj, hVar, aaVar);
        }
        hVar.j();
    }

    public final String toString() {
        return "BeanSerializer for " + a().getName();
    }
}
