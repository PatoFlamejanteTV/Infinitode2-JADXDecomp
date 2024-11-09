package com.d.i.a;

import com.d.e.t;
import com.d.i.a.c;
import com.d.i.a.e;
import com.d.i.aa;
import com.d.i.ab;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/d/i/a/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final List<aa> f1285a;

    /* loaded from: infinitode-2.jar:com/d/i/a/b$a.class */
    protected enum a {
    }

    public b(List<aa> list) {
        this.f1285a = list;
    }

    private void a(ab abVar, List<t> list, c cVar, Set<a> set) {
        Iterator<t> it = list.iterator();
        while (it.hasNext()) {
            a(abVar, it.next(), cVar, set);
        }
    }

    private static void a(com.d.d.b bVar, int i, int i2, c cVar) {
        for (int i3 = i; i3 <= i2; i3++) {
            cVar.a(i3).a(bVar);
        }
    }

    private static void a(com.d.d.b bVar, List<e.g> list, c cVar) {
        for (e.g gVar : list) {
            if (gVar.f1299b != -1) {
                cVar.a(gVar.f1298a).a(gVar.f1299b).a(bVar);
            } else {
                cVar.a(gVar.f1298a).a(bVar);
            }
        }
    }

    private static void a(com.d.i.f fVar, List<e.g> list, c cVar) {
        for (e.g gVar : list) {
            if (gVar.f1299b != -1) {
                cVar.a(gVar.f1298a).a(gVar.f1299b).a(new n(fVar, gVar.f1299b));
            } else {
                cVar.a(gVar.f1298a).a(new n(fVar, -1));
            }
        }
    }

    public final c a(ab abVar, t tVar) {
        if (!tVar.h()) {
            return null;
        }
        tVar.a((com.d.c.f.d) abVar);
        com.d.i.a.a aVar = new com.d.i.a.a(0, this.f1285a.size() - 1);
        a(abVar, tVar, aVar, EnumSet.noneOf(a.class));
        return aVar;
    }

    private void a(ab abVar, t tVar, c cVar, Set<a> set) {
        if (tVar.f().a().B()) {
            g gVar = new g(tVar);
            for (int a2 = cVar.a(); a2 <= cVar.b(); a2++) {
                c.a a3 = cVar.a(a2);
                a3.a(gVar);
                for (int i = 0; i < a3.a().size(); i++) {
                    a3.a(i).a(gVar);
                }
            }
            return;
        }
        if (tVar.c()) {
            return;
        }
        List<e.g> a4 = e.a(abVar, tVar, this.f1285a);
        int b2 = b(abVar, tVar);
        int c = c(abVar, tVar);
        boolean z = false;
        Rectangle a5 = tVar.f().a(abVar, tVar.d());
        if (a5 != null) {
            a(new m(a5), a4, cVar);
            z = true;
        }
        if (tVar.b()) {
            a(tVar.f(), a4, cVar);
        }
        if (tVar.h() && tVar.f().ae()) {
            a(new q(tVar.f()), cVar.a(), cVar.b(), cVar);
        }
        if (!tVar.j() && ((com.d.i.c) tVar.f()).A()) {
            b(tVar, cVar, b2, c);
        } else {
            e a6 = a(b2, c);
            a6.b(abVar, tVar);
            a6.a(abVar, tVar);
            if (!tVar.j() && (tVar.f() instanceof com.d.i.c)) {
                a(tVar, cVar, b2, c);
            }
            if (tVar.h() || tVar.e()) {
                a(abVar, tVar.b(3), cVar, set);
            }
            for (int i2 = b2; i2 <= c; i2++) {
                e.h a7 = a6.a(i2);
                c.a a8 = cVar.a(i2);
                a(abVar, tVar, a7, a8, true, i2, -1);
                a(abVar, tVar, i2, a7, a8, true);
            }
            if (tVar.h() || tVar.e()) {
                a(abVar, tVar.a(4), cVar, set);
                a(abVar, tVar.b(2), cVar, set);
                a(abVar, tVar.b(1), cVar, set);
            }
        }
        if (tVar.b()) {
            a(new l(tVar.f()), a4, cVar);
        }
        if (z) {
            a(new k(), a4, cVar);
        }
    }

    private void a(ab abVar, t tVar, e.h hVar, c.a aVar, boolean z, int i, int i2) {
        if (!hVar.b().isEmpty()) {
            aVar.a(new f(hVar.b(), hVar.d().isEmpty() ? null : a(hVar.d())));
        }
        if (z) {
            Iterator<com.d.i.c> it = hVar.a().iterator();
            while (it.hasNext()) {
                a(abVar, tVar, it.next(), aVar, i, i2);
            }
        }
        if (!hVar.f().isEmpty()) {
            aVar.a(new j(hVar.f()));
        }
        if (!hVar.c().isEmpty()) {
            aVar.a(new h(hVar.c()));
        }
        if (!hVar.e().isEmpty()) {
            aVar.a(new p(hVar.e()));
        }
    }

    private void a(ab abVar, t tVar, int i, e.h hVar, c.a aVar, boolean z) {
        int i2 = 0;
        Iterator<e.h> it = hVar.g().iterator();
        while (it.hasNext()) {
            a(abVar, tVar, it.next(), aVar.a(i2), true, i, i2);
            i2++;
        }
    }

    private void a(ab abVar, t tVar, com.d.i.c cVar, c.a aVar, int i, int i2) {
        e a2 = a(i, i);
        a2.a(abVar, tVar, cVar, i, i, i2);
        e.h a3 = a2.a(i);
        if (i2 >= 0 && a3.a(i2)) {
            a3 = a3.g().get(i2);
        } else if (i2 >= 0) {
            return;
        }
        boolean z = false;
        Rectangle a4 = cVar.a(abVar, cVar.af());
        if (a4 != null) {
            aVar.a(new m(a4));
            z = true;
        }
        a(abVar, tVar, a3, aVar, false, i, i2);
        if (z) {
            aVar.a(new k());
        }
    }

    private void a(t tVar, c cVar, int i, int i2) {
        a(new i(tVar.f()), i, i2, cVar);
    }

    private void b(t tVar, c cVar, int i, int i2) {
        a(new i(tVar.f()), i, i2, cVar);
        a(new o((com.d.i.c) tVar.f()), i, i2, cVar);
    }

    public static Map<com.d.f.f, List<com.d.e.k>> a(List<com.d.f.f> list) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashSet hashSet = new HashSet(0);
        for (com.d.f.f fVar : list) {
            List<com.d.e.k> list2 = (List) hashMap.get(fVar.f());
            List<com.d.e.k> list3 = list2;
            if (list2 == null) {
                list3 = new ArrayList();
                hashMap.put(fVar.f(), list3);
            }
            hashMap2.put(fVar.f(), fVar);
            fVar.a(hashSet, list3);
        }
        if (hashMap2.isEmpty()) {
            return null;
        }
        HashMap hashMap3 = new HashMap(hashMap2.size());
        for (com.d.f.f fVar2 : hashMap2.values()) {
            List list4 = (List) hashMap.get(fVar2.f());
            Collections.sort(list4);
            hashMap3.put(fVar2, list4);
        }
        return hashMap3;
    }

    public final c.a a(ab abVar, com.d.i.c cVar, int i) {
        c.a aVar = new c.a(null);
        e a2 = a(abVar.t(), abVar.t());
        a2.a(abVar, cVar.af(), cVar, abVar.t(), abVar.t(), i);
        e.h a3 = a2.a(abVar.t());
        if (i >= 0 && a3.a(i)) {
            a(abVar, cVar.af(), a3.g().get(i), aVar, false, abVar.t(), i);
        } else if (i < 0) {
            a(abVar, cVar.af(), a3, aVar, false, abVar.t(), i);
        }
        return aVar;
    }

    private e a(int i, int i2) {
        return new e(this.f1285a, i, i2);
    }

    private int b(ab abVar, t tVar) {
        int a2 = e.a(abVar, tVar.f(), this.f1285a);
        Iterator<com.d.i.c> it = tVar.g().iterator();
        while (it.hasNext()) {
            a2 = Math.min(a2, e.a(abVar, it.next(), this.f1285a));
        }
        return a2;
    }

    private int c(ab abVar, t tVar) {
        int b2 = e.b(abVar, tVar.f(), this.f1285a);
        Iterator<com.d.i.c> it = tVar.g().iterator();
        while (it.hasNext()) {
            b2 = Math.max(b2, e.b(abVar, it.next(), this.f1285a));
        }
        return b2;
    }
}
