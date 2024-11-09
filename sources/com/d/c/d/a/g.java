package com.d.c.d.a;

import com.d.e.ad;
import com.d.i.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/g.class */
public abstract class g extends com.d.c.d.a.a {
    protected abstract int a();

    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z, boolean z2) {
        if (list.size() == 1) {
            com.d.c.d.j jVar = (com.d.c.d.j) list.get(0);
            a(jVar, z2);
            if (jVar.e() == 0) {
                return Collections.singletonList(new v(aVar, jVar, z, i));
            }
            if (jVar.a() == 21) {
                if (jVar.d().equals("none")) {
                    return Collections.singletonList(new v(aVar, jVar, z, i));
                }
                return Collections.singletonList(new v(aVar, new com.d.c.d.j(Collections.singletonList(new ad(jVar.c(), a())), (byte) 0), z, i));
            }
            throw new com.d.c.d.b("The syntax of the " + aVar + " property is invalid", -1);
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            com.d.c.d.j jVar2 = (com.d.c.d.j) list.get(i2);
            if (jVar2.a() == 21) {
                String c = jVar2.c();
                int a2 = a();
                if (i2 < list.size() - 1) {
                    com.d.c.d.j jVar3 = (com.d.c.d.j) list.get(i2 + 1);
                    if (jVar3.a() == 1) {
                        o(aVar, jVar3);
                        a2 = (int) jVar3.f();
                    }
                    i2++;
                }
                arrayList.add(new ad(c, a2));
                i2++;
            } else {
                throw new com.d.c.d.b("The syntax of the " + aVar + " property is invalid", -1);
            }
        }
        return Collections.singletonList(new v(aVar, new com.d.c.d.j(arrayList, (byte) 0), z, i));
    }

    private static void o(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        if (((int) dVar.b()) != Math.round(dVar.b())) {
            throw new com.d.c.d.b("The value " + dVar.b() + " in " + aVar + " must be an integer", -1);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/g$b.class */
    public static class b extends g {
        @Override // com.d.c.d.a.g
        protected final int a() {
            return 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/c/d/a/g$a.class */
    public static class a extends g {
        @Override // com.d.c.d.a.g
        protected final int a() {
            return 1;
        }
    }
}
