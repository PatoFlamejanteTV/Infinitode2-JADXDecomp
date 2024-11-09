package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/o.class */
public final class o extends a {

    /* renamed from: a, reason: collision with root package name */
    private static final com.d.c.a.a[] f1047a = {com.d.c.a.a.t, com.d.c.a.a.q, com.d.c.a.a.p};

    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList(3);
        a(aVar, 1, 2, list.size());
        if (list.size() == 1) {
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() == 0) {
                return a(f1047a, list, i, z, z2);
            }
            if (jVar.a() == 21) {
                k a2 = k.a(jVar.c());
                if (a2 != null) {
                    arrayList.add(new v(com.d.c.a.a.t, new com.d.c.d.j(com.d.c.a.c.e), z, i));
                    arrayList.add(new v(com.d.c.a.a.p, a2.b(), z, i));
                    arrayList.add(new v(com.d.c.a.a.q, a2.a(), z, i));
                    return arrayList;
                }
                com.d.c.a.c b2 = b(jVar);
                if (b2 == com.d.c.a.c.Z || b2 == com.d.c.a.c.aA) {
                    arrayList.add(new v(com.d.c.a.a.t, jVar, z, i));
                    arrayList.add(new v(com.d.c.a.a.p, new com.d.c.d.j(com.d.c.a.c.e), z, i));
                    arrayList.add(new v(com.d.c.a.a.q, new com.d.c.d.j(com.d.c.a.c.e), z, i));
                    return arrayList;
                }
                if (b2 == com.d.c.a.c.e) {
                    arrayList.add(new v(com.d.c.a.a.t, jVar, z, i));
                    arrayList.add(new v(com.d.c.a.a.p, jVar, z, i));
                    arrayList.add(new v(com.d.c.a.a.q, jVar, z, i));
                    return arrayList;
                }
                throw new com.d.c.d.b("Identifier " + b2 + " is not a valid value for " + aVar, -1);
            }
            if (!a(jVar)) {
                throw new com.d.c.d.b("Value for " + aVar + " must be a length or identifier", -1);
            }
            if (jVar.f() < 0.0f) {
                throw new com.d.c.d.b("A page dimension may not be negative", -1);
            }
            arrayList.add(new v(com.d.c.a.a.t, new com.d.c.d.j(com.d.c.a.c.e), z, i));
            arrayList.add(new v(com.d.c.a.a.p, jVar, z, i));
            arrayList.add(new v(com.d.c.a.a.q, jVar, z, i));
            return arrayList;
        }
        com.d.c.d.j jVar2 = (com.d.c.d.j) list.get(0);
        com.d.c.d.j jVar3 = (com.d.c.d.j) list.get(1);
        com.d.c.d.j jVar4 = jVar3;
        a((com.d.c.d.d) jVar3, false);
        if (a(jVar2) && a(jVar4)) {
            if (jVar2.f() < 0.0f) {
                throw new com.d.c.d.b("A page dimension may not be negative", -1);
            }
            if (jVar4.f() < 0.0f) {
                throw new com.d.c.d.b("A page dimension may not be negative", -1);
            }
            arrayList.add(new v(com.d.c.a.a.t, new com.d.c.d.j(com.d.c.a.c.e), z, i));
            arrayList.add(new v(com.d.c.a.a.p, jVar2, z, i));
            arrayList.add(new v(com.d.c.a.a.q, jVar4, z, i));
            return arrayList;
        }
        if (jVar2.a() == 21 && jVar4.a() == 21) {
            if (jVar4.c().equals("landscape") || jVar4.c().equals("portrait")) {
                jVar2 = jVar4;
                jVar4 = jVar2;
            }
            if (!jVar2.toString().equals("landscape") && !jVar2.toString().equals("portrait")) {
                throw new com.d.c.d.b("Value " + jVar2 + " is not a valid page orientation", -1);
            }
            arrayList.add(new v(com.d.c.a.a.t, jVar2, z, i));
            k a3 = k.a(jVar4.c());
            if (a3 == null) {
                throw new com.d.c.d.b("Value " + jVar2 + " is not a valid page size", -1);
            }
            arrayList.add(new v(com.d.c.a.a.p, a3.b(), z, i));
            arrayList.add(new v(com.d.c.a.a.q, a3.a(), z, i));
            return arrayList;
        }
        throw new com.d.c.d.b("Invalid value for size property", -1);
    }
}
