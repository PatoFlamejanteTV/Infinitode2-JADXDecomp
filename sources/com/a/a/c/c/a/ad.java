package com.a.a.c.c.a;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/ad.class */
public final class ad {

    /* renamed from: a, reason: collision with root package name */
    private List<com.a.a.c.c.v> f252a;

    public ad() {
        this.f252a = new ArrayList();
    }

    private ad(List<com.a.a.c.c.v> list) {
        this.f252a = list;
    }

    public final void a(com.a.a.c.c.v vVar) {
        this.f252a.add(vVar);
    }

    public final ad a(com.a.a.c.m.r rVar) {
        com.a.a.c.k<Object> a2;
        ArrayList arrayList = new ArrayList(this.f252a.size());
        for (com.a.a.c.c.v vVar : this.f252a) {
            com.a.a.c.c.v a3 = vVar.a(rVar.a(vVar.a()));
            com.a.a.c.c.v vVar2 = a3;
            com.a.a.c.k<Object> p = a3.p();
            if (p != null && (a2 = p.a(rVar)) != p) {
                vVar2 = vVar2.a((com.a.a.c.k<?>) a2);
            }
            arrayList.add(vVar2);
        }
        return new ad(arrayList);
    }

    public final Object a(com.a.a.c.g gVar, Object obj, com.a.a.c.m.ac acVar) {
        int size = this.f252a.size();
        for (int i = 0; i < size; i++) {
            com.a.a.c.c.v vVar = this.f252a.get(i);
            com.a.a.b.l o = acVar.o();
            o.g();
            vVar.a(o, gVar, obj);
        }
        return obj;
    }
}
