package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/n.class */
public final class n extends a {
    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        if (list.size() == 1) {
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            if (jVar.e() == 0) {
                return Collections.EMPTY_LIST;
            }
            if (jVar.a() == 21) {
                com.d.c.a.a aVar2 = com.d.c.a.a.ai;
                if (b(jVar) == com.d.c.a.c.ap) {
                    return Collections.singletonList(new v(com.d.c.a.a.ai, jVar, z, i));
                }
            }
        }
        if (list.size() % 2 == 1) {
            throw new com.d.c.d.b("Mismatched quotes " + list, -1);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.d.c.d.j jVar2 = (com.d.c.d.j) it.next();
            if (jVar2.j() != null) {
                throw new com.d.c.d.b("Found unexpected operator, " + jVar2.j().b(), -1);
            }
            short a2 = jVar2.a();
            if (a2 == 19) {
                arrayList.add(jVar2);
            } else {
                if (a2 == 20) {
                    throw new com.d.c.d.b("URI is not allowed here", -1);
                }
                if (jVar2.i() == 7) {
                    throw new com.d.c.d.b("Function " + jVar2.n().a() + " is not allowed here", -1);
                }
                if (a2 == 21) {
                    throw new com.d.c.d.b("Identifier is not a valid value for the quotes property", -1);
                }
                throw new com.d.c.d.b(jVar2.d() + " is not a value value for the quotes property", -1);
            }
        }
        if (arrayList.size() > 0) {
            return Collections.singletonList(new v(com.d.c.a.a.ai, new com.d.c.d.j(arrayList), z, i));
        }
        return Collections.EMPTY_LIST;
    }
}
