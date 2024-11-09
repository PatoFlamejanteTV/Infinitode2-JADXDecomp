package org.a.c.h.g.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.a.c.h.g.e.c;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/g.class */
public abstract class g extends r {
    @Override // org.a.c.h.g.e.p
    abstract void c();

    public g(d dVar) {
        super(dVar);
        f().a(org.a.c.b.j.bq, (org.a.c.b.b) org.a.c.b.j.S);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(d dVar, org.a.c.b.d dVar2, l lVar) {
        super(dVar, dVar2, lVar);
    }

    private List<String> k() {
        return c.a(f().a(org.a.c.b.j.cC), 0);
    }

    public final void a(List<String> list, List<String> list2) {
        if (!list.isEmpty() && !list2.isEmpty()) {
            if (list.size() != list2.size()) {
                throw new IllegalArgumentException("The number of entries for exportValue and displayValue shall be the same.");
            }
            List<c.a> a2 = c.a(list, list2);
            if (p()) {
                c.a(a2);
            }
            org.a.c.b.a aVar = new org.a.c.b.a();
            for (int i = 0; i < list.size(); i++) {
                org.a.c.b.a aVar2 = new org.a.c.b.a();
                aVar2.a((org.a.c.b.b) new org.a.c.b.s(a2.get(i).a()));
                aVar2.a((org.a.c.b.b) new org.a.c.b.s(a2.get(i).b()));
                aVar.a((org.a.c.b.b) aVar2);
            }
            f().a(org.a.c.b.j.cC, (org.a.c.b.b) aVar);
            return;
        }
        f().m(org.a.c.b.j.cC);
    }

    public final List<String> a() {
        return c.a(f().a(org.a.c.b.j.cC), 1);
    }

    public final List<String> b() {
        return k();
    }

    public final List<Integer> d() {
        org.a.c.b.b a2 = f().a(org.a.c.b.j.by);
        if (a2 != null) {
            return org.a.c.h.a.a.a((org.a.c.b.a) a2);
        }
        return Collections.emptyList();
    }

    public final void a(List<Integer> list) {
        if (list != null && !list.isEmpty()) {
            if (!q()) {
                throw new IllegalArgumentException("Setting the indices is not allowed for choice fields not allowing multiple selections.");
            }
            f().a(org.a.c.b.j.by, (org.a.c.b.b) org.a.c.h.a.a.b(list));
            return;
        }
        f().m(org.a.c.b.j.by);
    }

    private boolean p() {
        return f().c(org.a.c.b.j.aV, 524288);
    }

    private boolean q() {
        return f().c(org.a.c.b.j.aV, 2097152);
    }

    public final void a(boolean z) {
        f().a(org.a.c.b.j.aV, 2097152, true);
    }

    public final void b(boolean z) {
        f().a(org.a.c.b.j.aV, 131072, true);
    }

    public final void a(String str) {
        f().b(org.a.c.b.j.dU, str);
        a((List<Integer>) null);
        m();
    }

    public final void b(String str) {
        f().b(org.a.c.b.j.aL, str);
    }

    public final List<String> e() {
        return b(org.a.c.b.j.dU);
    }

    private List<String> b(org.a.c.b.j jVar) {
        org.a.c.b.b a2 = f().a(jVar);
        if (a2 instanceof org.a.c.b.s) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((org.a.c.b.s) a2).b());
            return arrayList;
        }
        if (a2 instanceof org.a.c.b.a) {
            return org.a.c.h.a.a.d((org.a.c.b.a) a2);
        }
        return Collections.emptyList();
    }
}
