package com.a.a.c.k;

import com.a.a.a.s;
import com.a.a.c.aa;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/k/s.class */
public abstract class s extends e implements Serializable {
    protected abstract Object a(aa aaVar);

    public abstract s l();

    protected s() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public s(com.a.a.c.f.s sVar, com.a.a.c.m.b bVar, com.a.a.c.j jVar, com.a.a.c.o<?> oVar, com.a.a.c.i.i iVar, com.a.a.c.j jVar2, s.b bVar2, Class<?>[] clsArr) {
        super(sVar, sVar.v(), bVar, jVar, oVar, iVar, jVar2, a(bVar2), b(bVar2), clsArr);
    }

    private static boolean a(s.b bVar) {
        s.a b2;
        return (bVar == null || (b2 = bVar.b()) == s.a.ALWAYS || b2 == s.a.USE_DEFAULTS) ? false : true;
    }

    private static Object b(s.b bVar) {
        if (bVar == null) {
            return Boolean.FALSE;
        }
        s.a b2 = bVar.b();
        if (b2 == s.a.ALWAYS || b2 == s.a.NON_NULL || b2 == s.a.USE_DEFAULTS) {
            return null;
        }
        return f642b;
    }

    @Override // com.a.a.c.k.e, com.a.a.c.k.p
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        Object a2 = a(aaVar);
        if (a2 == null) {
            if (this.f != null) {
                hVar.b((com.a.a.b.r) this.c);
                this.f.a(null, hVar, aaVar);
                return;
            }
            return;
        }
        com.a.a.c.o<Object> oVar = this.e;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            Class<?> cls = a2.getClass();
            com.a.a.c.k.a.k kVar = this.h;
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
        hVar.b((com.a.a.b.r) this.c);
        if (this.g == null) {
            oVar2.a(a2, hVar, aaVar);
        } else {
            oVar2.a(a2, hVar, aaVar, this.g);
        }
    }

    @Override // com.a.a.c.k.e
    public final void b(Object obj, com.a.a.b.h hVar, aa aaVar) {
        Object a2 = a(aaVar);
        if (a2 == null) {
            if (this.f != null) {
                this.f.a(null, hVar, aaVar);
                return;
            } else {
                hVar.k();
                return;
            }
        }
        com.a.a.c.o<Object> oVar = this.e;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            Class<?> cls = a2.getClass();
            com.a.a.c.k.a.k kVar = this.h;
            com.a.a.c.o<Object> a3 = kVar.a(cls);
            oVar2 = a3;
            if (a3 == null) {
                oVar2 = a(kVar, cls, aaVar);
            }
        }
        if (this.i != null) {
            if (f642b == this.i) {
                if (oVar2.a(aaVar, a2)) {
                    a(hVar, aaVar);
                    return;
                }
            } else if (this.i.equals(a2)) {
                a(hVar, aaVar);
                return;
            }
        }
        if (a2 == obj && a(hVar, aaVar, (com.a.a.c.o<?>) oVar2)) {
            return;
        }
        if (this.g == null) {
            oVar2.a(a2, hVar, aaVar);
        } else {
            oVar2.a(a2, hVar, aaVar, this.g);
        }
    }
}
