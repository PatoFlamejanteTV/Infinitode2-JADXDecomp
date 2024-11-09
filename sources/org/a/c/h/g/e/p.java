package org.a.c.h.g.e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/p.class */
public abstract class p extends j {
    abstract void c();

    /* JADX INFO: Access modifiers changed from: protected */
    public p(d dVar) {
        super(dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(d dVar, org.a.c.b.d dVar2, l lVar) {
        super(dVar, dVar2, lVar);
    }

    public final List<org.a.c.h.g.b.m> l() {
        ArrayList arrayList = new ArrayList();
        org.a.c.b.a aVar = (org.a.c.b.a) f().a(org.a.c.b.j.bR);
        if (aVar == null) {
            arrayList.add(new org.a.c.h.g.b.m(f()));
        } else if (aVar.b() > 0) {
            for (int i = 0; i < aVar.b(); i++) {
                org.a.c.b.b a2 = aVar.a(i);
                if (a2 instanceof org.a.c.b.d) {
                    arrayList.add(new org.a.c.h.g.b.m((org.a.c.b.d) a2));
                }
            }
        }
        return arrayList;
    }

    public final void b(List<org.a.c.h.g.b.m> list) {
        f().a(org.a.c.b.j.bR, (org.a.c.b.b) org.a.c.h.a.a.b(list));
        Iterator<org.a.c.h.g.b.m> it = list.iterator();
        while (it.hasNext()) {
            it.next().f().a(org.a.c.b.j.cN, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void m() {
        if (!h().d()) {
            c();
        }
    }
}
