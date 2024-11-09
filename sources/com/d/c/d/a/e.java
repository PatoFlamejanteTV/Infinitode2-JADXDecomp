package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/e.class */
public final class e extends a {
    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        if (list.size() == 1) {
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            if (jVar.e() == 0) {
                return Collections.EMPTY_LIST;
            }
            if (jVar.a() == 21) {
                com.d.c.a.a aVar2 = com.d.c.a.a.C;
                com.d.c.a.c b2 = b(jVar);
                if (b2 == com.d.c.a.c.ap || b2 == com.d.c.a.c.aq) {
                    return Collections.singletonList(new v(com.d.c.a.a.C, jVar, z, i));
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.d.c.d.j jVar2 = (com.d.c.d.j) it.next();
            if (jVar2.j() != null) {
                throw new com.d.c.d.b("Found unexpected operator, " + jVar2.j().b(), -1);
            }
            short a2 = jVar2.a();
            if (a2 != 20) {
                if (a2 == 19) {
                    arrayList.add(jVar2);
                } else if (jVar2.i() == 7) {
                    if (!a(jVar2.n())) {
                        throw new com.d.c.d.b("Function " + jVar2.n().a() + " is not allowed here", -1);
                    }
                    arrayList.add(jVar2);
                } else if (a2 == 21) {
                    com.d.c.a.a aVar3 = com.d.c.a.a.C;
                    com.d.c.a.c b3 = b(jVar2);
                    if (b3 == com.d.c.a.c.au || b3 == com.d.c.a.c.p || b3 == com.d.c.a.c.am || b3 == com.d.c.a.c.an) {
                        arrayList.add(jVar2);
                    } else {
                        throw new com.d.c.d.b("Identifier " + b3 + " is not a valid value for the content property", -1);
                    }
                } else {
                    throw new com.d.c.d.b(jVar2.d() + " is not a value value for the content property", -1);
                }
            }
        }
        if (arrayList.size() > 0) {
            return Collections.singletonList(new v(com.d.c.a.a.C, new com.d.c.d.j(arrayList), z, i));
        }
        return Collections.EMPTY_LIST;
    }

    private static boolean a(com.d.i.e eVar) {
        String a2 = eVar.a();
        return a2.equals("attr") || a2.equals("counter") || a2.equals("counters") || a2.equals("element") || a2.startsWith("-fs") || a2.equals("target-counter") || a2.equals("leader");
    }
}
