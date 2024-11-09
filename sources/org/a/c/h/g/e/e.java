package org.a.c.h.g.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/e.class */
public abstract class e extends p {
    public e(d dVar) {
        super(dVar);
        f().a(org.a.c.b.j.bq, (org.a.c.b.b) org.a.c.b.j.F);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar, org.a.c.b.d dVar2, l lVar) {
        super(dVar, dVar2, lVar);
    }

    public final void a(boolean z) {
        f().a(org.a.c.b.j.aV, 65536, true);
    }

    public final void b(boolean z) {
        f().a(org.a.c.b.j.aV, 32768, true);
    }

    public String a() {
        org.a.c.b.b a2 = a(org.a.c.b.j.dU);
        if (a2 instanceof org.a.c.b.j) {
            return ((org.a.c.b.j) a2).a();
        }
        return "Off";
    }

    public final void a(String str) {
        b(str);
        if (b().size() > 0) {
            f(str);
        } else {
            e(str);
        }
        m();
    }

    public List<String> b() {
        org.a.c.b.b a2 = a(org.a.c.b.j.cC);
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

    public void a(List<String> list) {
        if (!list.isEmpty()) {
            f().a(org.a.c.b.j.cC, (org.a.c.b.b) org.a.c.h.a.a.a(list));
        } else {
            f().m(org.a.c.b.j.cC);
        }
    }

    @Override // org.a.c.h.g.e.p
    void c() {
        List<String> b2 = b();
        if (b2.size() > 0) {
            try {
                int parseInt = Integer.parseInt(a());
                if (parseInt < b2.size()) {
                    f(b2.get(parseInt));
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                return;
            }
        }
        e(a());
    }

    private Set<String> d() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (b().size() > 0) {
            linkedHashSet.addAll(b());
            return linkedHashSet;
        }
        Iterator<org.a.c.h.g.b.m> it = l().iterator();
        while (it.hasNext()) {
            linkedHashSet.add(a(it.next()));
        }
        return linkedHashSet;
    }

    private String a(int i) {
        List<org.a.c.h.g.b.m> l = l();
        if (i < l.size()) {
            return a(l.get(i));
        }
        return "";
    }

    private static String a(org.a.c.h.g.b.m mVar) {
        org.a.c.h.g.b.p b2;
        org.a.c.h.g.b.o c = mVar.c();
        if (c != null && (b2 = c.b()) != null) {
            for (org.a.c.b.j jVar : b2.d().keySet()) {
                if (org.a.c.b.j.cB.compareTo(jVar) != 0) {
                    return jVar.a();
                }
            }
            return "";
        }
        return "";
    }

    private void b(String str) {
        Set<String> d = d();
        if (org.a.c.b.j.cB.a().compareTo(str) != 0 && !d.contains(str)) {
            throw new IllegalArgumentException("value '" + str + "' is not a valid option for the field " + j() + ", valid values are: " + d + " and " + org.a.c.b.j.cB.a());
        }
    }

    private void e(String str) {
        f().a(org.a.c.b.j.dU, str);
        for (org.a.c.h.g.b.m mVar : l()) {
            if (mVar.c() != null) {
                if (((org.a.c.b.d) mVar.c().b().f()).b(str)) {
                    mVar.a(str);
                } else {
                    mVar.a(org.a.c.b.j.cB.a());
                }
            }
        }
    }

    private void f(String str) {
        List<org.a.c.h.g.b.m> l = l();
        List<String> b2 = b();
        if (l.size() != b2.size()) {
            throw new IllegalArgumentException("The number of options doesn't match the number of widgets");
        }
        if (str.equals(org.a.c.b.j.cB.a())) {
            e(str);
            return;
        }
        int indexOf = b2.indexOf(str);
        if (indexOf != -1) {
            e(a(indexOf));
        }
    }
}
