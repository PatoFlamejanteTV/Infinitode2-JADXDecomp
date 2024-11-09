package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/d.class */
public final class d extends a {

    /* renamed from: a, reason: collision with root package name */
    private static final com.d.c.a.a[] f994a = {com.d.c.a.a.j, com.d.c.a.a.k};

    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        v vVar;
        v vVar2;
        List a2 = a(f994a, list, i, z, z2);
        if (a2 != null) {
            return a2;
        }
        a(com.d.c.a.a.bf, 1, 2, list.size());
        if (list.size() == 1) {
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            j(aVar, jVar);
            if (jVar.f() < 0.0f) {
                throw new com.d.c.d.b("border-spacing may not be negative", -1);
            }
            vVar = new v(com.d.c.a.a.j, jVar, z, i);
            vVar2 = new v(com.d.c.a.a.k, jVar, z, i);
        } else {
            com.d.c.d.j jVar2 = (com.d.c.d.j) list.get(0);
            j(aVar, jVar2);
            if (jVar2.f() < 0.0f) {
                throw new com.d.c.d.b("border-spacing may not be negative", -1);
            }
            vVar = new v(com.d.c.a.a.j, jVar2, z, i);
            com.d.c.d.j jVar3 = (com.d.c.d.j) list.get(1);
            j(aVar, jVar3);
            if (jVar3.f() < 0.0f) {
                throw new com.d.c.d.b("border-spacing may not be negative", -1);
            }
            vVar2 = new v(com.d.c.a.a.k, jVar3, z, i);
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(vVar);
        arrayList.add(vVar2);
        return arrayList;
    }
}
