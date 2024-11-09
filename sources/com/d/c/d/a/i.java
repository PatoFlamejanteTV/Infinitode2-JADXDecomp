package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/i.class */
public final class i extends a {

    /* renamed from: a, reason: collision with root package name */
    private static final com.d.c.a.a[] f998a = {com.d.c.a.a.U, com.d.c.a.a.V, com.d.c.a.a.W};

    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        List a2 = a(f998a, list, i, z, z2);
        if (a2 != null) {
            return a2;
        }
        v vVar = null;
        v vVar2 = null;
        v vVar3 = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.d.c.d.j jVar = (com.d.c.d.j) it.next();
            a((com.d.c.d.d) jVar, false);
            short a3 = jVar.a();
            if (a3 != 21) {
                if (a3 != 20) {
                    continue;
                } else if (vVar3 == null) {
                    vVar3 = new v(com.d.c.a.a.W, jVar, z, i);
                } else {
                    throw new com.d.c.d.b("A list-style-image value cannot be set twice", -1);
                }
            } else {
                com.d.c.a.a aVar2 = com.d.c.a.a.bh;
                com.d.c.a.c b2 = b(jVar);
                if (b2 != com.d.c.a.c.ap) {
                    if (!l.j.get(b2.f968a)) {
                        if (!l.k.get(b2.f968a)) {
                            continue;
                        } else if (vVar == null) {
                            vVar = new v(com.d.c.a.a.U, jVar, z, i);
                        } else {
                            throw new com.d.c.d.b("A list-style-type value cannot be set twice", -1);
                        }
                    } else if (vVar2 == null) {
                        vVar2 = new v(com.d.c.a.a.V, jVar, z, i);
                    } else {
                        throw new com.d.c.d.b("A list-style-position value cannot be set twice", -1);
                    }
                } else {
                    if (vVar == null) {
                        vVar = new v(com.d.c.a.a.U, jVar, z, i);
                    }
                    if (vVar3 == null) {
                        vVar3 = new v(com.d.c.a.a.W, jVar, z, i);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList(3);
        if (vVar != null) {
            arrayList.add(vVar);
        }
        if (vVar2 != null) {
            arrayList.add(vVar2);
        }
        if (vVar3 != null) {
            arrayList.add(vVar3);
        }
        return arrayList;
    }
}
