package com.a.a.c.k.a;

import com.a.a.c.aa;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/t.class */
public final class t extends com.a.a.c.k.e implements Serializable {
    private com.a.a.c.m.r j;

    public t(com.a.a.c.k.e eVar, com.a.a.c.m.r rVar) {
        super(eVar);
        this.j = rVar;
    }

    private t(t tVar, com.a.a.c.m.r rVar, com.a.a.b.c.k kVar) {
        super(tVar, kVar);
        this.j = rVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.e
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public t a(com.a.a.c.m.r rVar) {
        return a(com.a.a.c.m.r.a(rVar, this.j), new com.a.a.b.c.k(rVar.a(this.c.a())));
    }

    private t a(com.a.a.c.m.r rVar, com.a.a.b.c.k kVar) {
        return new t(this, rVar, kVar);
    }

    @Override // com.a.a.c.k.e, com.a.a.c.k.p
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        Object a2 = a(obj);
        if (a2 == null) {
            return;
        }
        com.a.a.c.o<Object> oVar = this.e;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            Class<?> cls = a2.getClass();
            k kVar = this.h;
            com.a.a.c.o<Object> a3 = kVar.a(cls);
            oVar2 = a3;
            if (a3 == null) {
                oVar2 = a(kVar, cls, aaVar);
            }
        }
        if (this.i != null) {
            if (f642b == this.i) {
                if (oVar2.a(aaVar, a2)) {
                    return;
                }
            } else if (this.i.equals(a2)) {
                return;
            }
        }
        if (a2 == obj && a(hVar, aaVar, (com.a.a.c.o<?>) oVar2)) {
            return;
        }
        if (!oVar2.c()) {
            hVar.b((com.a.a.b.r) this.c);
        }
        if (this.g == null) {
            oVar2.a(a2, hVar, aaVar);
        } else {
            oVar2.a(a2, hVar, aaVar, this.g);
        }
    }

    @Override // com.a.a.c.k.e
    public final void a(com.a.a.c.o<Object> oVar) {
        if (oVar != null) {
            com.a.a.c.m.r rVar = this.j;
            if (oVar.c() && (oVar instanceof u)) {
                rVar = com.a.a.c.m.r.a(rVar, ((u) oVar).f585a);
            }
            oVar = oVar.a(rVar);
        }
        super.a(oVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.c.k.e
    public final com.a.a.c.o<Object> a(k kVar, Class<?> cls, aa aaVar) {
        com.a.a.c.o<Object> a2;
        if (this.d != null) {
            a2 = aaVar.a(aaVar.a(this.d, cls), (com.a.a.c.c) this);
        } else {
            a2 = aaVar.a(cls, (com.a.a.c.c) this);
        }
        com.a.a.c.m.r rVar = this.j;
        if (a2.c() && (a2 instanceof u)) {
            rVar = com.a.a.c.m.r.a(rVar, ((u) a2).f585a);
        }
        com.a.a.c.o<Object> a3 = a2.a(rVar);
        this.h = this.h.b(cls, a3);
        return a3;
    }
}
