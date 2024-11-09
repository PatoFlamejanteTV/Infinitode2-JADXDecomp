package org.a.c.h.g.e;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/c.class */
public class c {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/c$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final String f4619a;

        /* renamed from: b, reason: collision with root package name */
        private final String f4620b;

        a(String str, String str2) {
            this.f4619a = str;
            this.f4620b = str2;
        }

        public final String a() {
            return this.f4619a;
        }

        public final String b() {
            return this.f4620b;
        }

        public final String toString() {
            return "(" + this.f4619a + ", " + this.f4620b + ")";
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/c$b.class */
    static class b implements Serializable, Comparator<a> {
        b() {
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(a aVar, a aVar2) {
            return a(aVar, aVar2);
        }

        private static int a(a aVar, a aVar2) {
            return aVar.f4620b.compareTo(aVar2.f4620b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<a> a(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new a(list.get(i), list2.get(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(List<a> list) {
        Collections.sort(list, new b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> a(org.a.c.b.b bVar, int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Only 0 and 1 are allowed as an index into two-element arrays");
        }
        if (bVar instanceof org.a.c.b.s) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((org.a.c.b.s) bVar).b());
            return arrayList;
        }
        if (bVar instanceof org.a.c.b.a) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<org.a.c.b.b> it = ((org.a.c.b.a) bVar).iterator();
            while (it.hasNext()) {
                org.a.c.b.b next = it.next();
                if (next instanceof org.a.c.b.s) {
                    arrayList2.add(((org.a.c.b.s) next).b());
                } else if (next instanceof org.a.c.b.a) {
                    org.a.c.b.a aVar = (org.a.c.b.a) next;
                    if (aVar.b() >= i + 1 && (aVar.b(i) instanceof org.a.c.b.s)) {
                        arrayList2.add(((org.a.c.b.s) aVar.b(i)).b());
                    }
                }
            }
            return arrayList2;
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static j a(d dVar, org.a.c.b.d dVar2, l lVar) {
        org.a.c.b.a aVar;
        String a2 = a(dVar2);
        if (dVar2.o(org.a.c.b.j.bR) && (aVar = (org.a.c.b.a) dVar2.a(org.a.c.b.j.bR)) != null && aVar.b() > 0) {
            for (int i = 0; i < aVar.b(); i++) {
                org.a.c.b.b a3 = aVar.a(i);
                if ((a3 instanceof org.a.c.b.d) && ((org.a.c.b.d) a3).h(org.a.c.b.j.dG) != null) {
                    return new l(dVar, dVar2, lVar);
                }
            }
        }
        if ("Ch".equals(a2)) {
            return b(dVar, dVar2, lVar);
        }
        if ("Tx".equals(a2)) {
            return new q(dVar, dVar2, lVar);
        }
        if ("Sig".equals(a2)) {
            return new o(dVar, dVar2, lVar);
        }
        if ("Btn".equals(a2)) {
            return c(dVar, dVar2, lVar);
        }
        return null;
    }

    private static j b(d dVar, org.a.c.b.d dVar2, l lVar) {
        if ((dVar2.b(org.a.c.b.j.aV, 0) & 131072) != 0) {
            return new h(dVar, dVar2, lVar);
        }
        return new k(dVar, dVar2, lVar);
    }

    private static j c(d dVar, org.a.c.b.d dVar2, l lVar) {
        int b2 = dVar2.b(org.a.c.b.j.aV, 0);
        if ((b2 & 32768) != 0) {
            return new n(dVar, dVar2, lVar);
        }
        if ((b2 & 65536) != 0) {
            return new m(dVar, dVar2, lVar);
        }
        return new f(dVar, dVar2, lVar);
    }

    private static String a(org.a.c.b.d dVar) {
        String g = dVar.g(org.a.c.b.j.bq);
        String str = g;
        if (g == null) {
            org.a.c.b.b a2 = dVar.a(org.a.c.b.j.cN, org.a.c.b.j.cJ);
            if (a2 instanceof org.a.c.b.d) {
                str = a((org.a.c.b.d) a2);
            }
        }
        return str;
    }
}
