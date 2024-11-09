package com.a.a.c.k.a;

import com.a.a.c.aa;
import com.a.a.c.z;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/b.class */
public final class b extends com.a.a.c.k.b.d {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.k.b.d f556a;

    @Override // com.a.a.c.k.b.d
    protected final /* synthetic */ com.a.a.c.k.b.d a(Set set, Set set2) {
        return b((Set<String>) set, (Set<String>) set2);
    }

    public b(com.a.a.c.k.b.d dVar) {
        super(dVar, (m) null);
        this.f556a = dVar;
    }

    private b(com.a.a.c.k.b.d dVar, Set<String> set, Set<String> set2) {
        super(dVar, set, set2);
        this.f556a = dVar;
    }

    private b(com.a.a.c.k.b.d dVar, m mVar, Object obj) {
        super(dVar, mVar, obj);
        this.f556a = dVar;
    }

    @Override // com.a.a.c.o
    public final com.a.a.c.o<Object> a(com.a.a.c.m.r rVar) {
        return this.f556a.a(rVar);
    }

    @Override // com.a.a.c.o
    public final boolean c() {
        return false;
    }

    @Override // com.a.a.c.k.b.d
    public final com.a.a.c.k.b.d a(m mVar) {
        return this.f556a.a(mVar);
    }

    @Override // com.a.a.c.k.b.d
    public final com.a.a.c.k.b.d a(Object obj) {
        return new b(this, this.g, obj);
    }

    private b b(Set<String> set, Set<String> set2) {
        return new b(this, set, set2);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d a(com.a.a.c.k.e[] eVarArr, com.a.a.c.k.e[] eVarArr2) {
        return this;
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d d() {
        return this;
    }

    @Override // com.a.a.c.k.b.d, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        if (this.g != null) {
            b(obj, hVar, aaVar, iVar);
            return;
        }
        com.a.a.b.f.a a2 = a(iVar, obj, com.a.a.b.o.START_ARRAY);
        iVar.a(hVar, a2);
        hVar.a(obj);
        d(obj, hVar, aaVar);
        iVar.b(hVar, a2);
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        if (aaVar.a(z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && b(aaVar)) {
            d(obj, hVar, aaVar);
            return;
        }
        hVar.b(obj);
        d(obj, hVar, aaVar);
        hVar.h();
    }

    private boolean b(aa aaVar) {
        com.a.a.c.k.e[] eVarArr;
        if (this.d != null && aaVar.e() != null) {
            eVarArr = this.d;
        } else {
            eVarArr = this.c;
        }
        return eVarArr.length == 1;
    }

    private void d(Object obj, com.a.a.b.h hVar, aa aaVar) {
        com.a.a.c.k.e[] eVarArr;
        if (this.d != null && aaVar.e() != null) {
            eVarArr = this.d;
        } else {
            eVarArr = this.c;
        }
        int i = 0;
        try {
            int length = eVarArr.length;
            while (i < length) {
                com.a.a.c.k.e eVar = eVarArr[i];
                if (eVar == null) {
                    hVar.k();
                } else {
                    eVar.b(obj, hVar, aaVar);
                }
                i++;
            }
        } catch (Exception e) {
            a(aaVar, e, obj, eVarArr[i].a());
        } catch (StackOverflowError e2) {
            com.a.a.c.l a2 = com.a.a.c.l.a(hVar, "Infinite recursion (StackOverflowError)", e2);
            a2.a(obj, eVarArr[i].a());
            throw a2;
        }
    }

    public final String toString() {
        return "BeanAsArraySerializer for " + a().getName();
    }
}
