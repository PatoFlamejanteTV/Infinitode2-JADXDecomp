package com.d.i.b;

import com.d.e.k;
import com.d.e.s;
import com.d.e.t;
import com.d.i.a.r;
import com.d.i.ab;
import com.d.i.c;
import com.d.i.f;
import com.d.i.y;
import com.d.i.z;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/i/b/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final int f1336a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1337b;

    public b(int i, int i2) {
        this.f1336a = i;
        this.f1337b = i2;
    }

    public final void a(ab abVar, t tVar) {
        AffineTransform a2;
        f f = tVar.f();
        Rectangle a3 = tVar.f().a(abVar, tVar.d());
        if (a3 != null) {
            abVar.p().f(a3);
        }
        if (tVar.b()) {
            if (abVar.x()) {
                a2 = r.a(abVar, f, abVar.s(), this.f1336a, this.f1337b);
            } else {
                a2 = r.a(abVar, f, abVar.s(), abVar.w());
            }
            abVar.p().a(a2);
        }
        if (!tVar.j() && ((c) f).A()) {
            a(abVar, f);
            a(abVar, (c) f);
        } else {
            a aVar = new a();
            aVar.a(abVar, tVar);
            if (!tVar.j() && (f instanceof c)) {
                a(abVar, f);
            }
            if (tVar.h() || tVar.e() || f.a().B()) {
                e(abVar, tVar.b(3));
            }
            a(abVar, aVar.a(), aVar.c().isEmpty() ? null : com.d.i.a.b.a(aVar.c()));
            d(abVar, tVar.g());
            a(abVar, aVar.e());
            b(abVar, aVar.b());
            c(abVar, aVar.d());
            if (tVar.h() || tVar.e() || f.a().B()) {
                e(abVar, tVar.a(4));
                e(abVar, tVar.b(2));
                e(abVar, tVar.b(1));
            }
        }
        if (tVar.b()) {
            abVar.p().g();
        }
        if (a3 != null) {
            abVar.p().h();
        }
    }

    private static void a(ab abVar, f fVar) {
        fVar.b(abVar);
        fVar.c(abVar);
    }

    private void a(ab abVar, y yVar) {
        yVar.a();
        abVar.p().f(yVar.a());
    }

    private void a(ab abVar) {
        abVar.p().h();
    }

    private void a(ab abVar, List<com.d.d.b> list, Map<com.d.f.f, List<k>> map) {
        List<k> list2;
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (bVar instanceof z) {
                    a(abVar);
                } else {
                    c cVar = (c) bVar;
                    cVar.b(abVar);
                    cVar.c(abVar);
                    if (map != null && (cVar instanceof com.d.f.f)) {
                        com.d.f.f fVar = (com.d.f.f) cVar;
                        if (fVar.o() && (list2 = map.get(fVar)) != null) {
                            for (k kVar : list2) {
                                kVar.a().a(abVar, kVar.b());
                            }
                        }
                    }
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private void a(ab abVar, List<com.d.d.b> list) {
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (bVar instanceof z) {
                    a(abVar);
                } else {
                    ((c) bVar).f(abVar);
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private void b(ab abVar, List<com.d.d.b> list) {
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (!(bVar instanceof z)) {
                    if (bVar instanceof c) {
                        b(abVar, (c) bVar);
                    } else {
                        ((s) bVar).a(abVar);
                    }
                } else {
                    a(abVar);
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private static void a(ab abVar, c cVar) {
        Rectangle c = cVar.c(cVar.ab(), cVar.aa(), abVar);
        Point c2 = cVar.E().c();
        if (c.x != c2.x || c.y != c2.y) {
            cVar.E().a(c.x, c.y);
        }
        abVar.p().a(abVar, cVar);
    }

    private void c(ab abVar, List<com.d.d.b> list) {
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (bVar instanceof z) {
                    a(abVar);
                } else {
                    a(abVar, (c) bVar);
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private void d(ab abVar, List<c> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            b(abVar, list.get(size));
        }
    }

    private void e(ab abVar, List<t> list) {
        Iterator<t> it = list.iterator();
        while (it.hasNext()) {
            a(abVar, it.next());
        }
    }

    private void b(ab abVar, c cVar) {
        if (cVar.a().O()) {
            return;
        }
        a aVar = new a();
        aVar.a(abVar, cVar.af(), cVar);
        a(abVar, aVar.a(), aVar.c().isEmpty() ? null : com.d.i.a.b.a(aVar.c()));
        a(abVar, aVar.e());
        b(abVar, aVar.b());
        c(abVar, aVar.d());
    }
}
