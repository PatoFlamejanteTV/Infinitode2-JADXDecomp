package com.d.i.a;

import com.d.e.s;
import com.d.e.t;
import com.d.i.a.b;
import com.d.i.a.c;
import com.d.i.ab;
import com.d.i.y;
import com.d.i.z;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/i/a/d.class */
public final class d {
    private void a(ab abVar, y yVar) {
        yVar.a();
        abVar.p().f(yVar.a());
    }

    private void a(ab abVar) {
        abVar.p().h();
    }

    private static void a(ab abVar, com.d.i.c cVar) {
        if (cVar.a().q()) {
            com.d.f.d dVar = (com.d.f.d) cVar;
            if (dVar.m()) {
                dVar.d(abVar);
            }
        }
    }

    private void a(ab abVar, List<com.d.d.b> list, Map<com.d.f.f, List<com.d.e.k>> map) {
        List<com.d.e.k> list2;
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (bVar instanceof z) {
                    a(abVar);
                } else {
                    com.d.i.c cVar = (com.d.i.c) bVar;
                    Object a2 = abVar.p().a(com.d.d.q.BLOCK, cVar);
                    Object a3 = abVar.p().a(com.d.d.q.BACKGROUND, cVar);
                    a(abVar, cVar);
                    cVar.b(abVar);
                    cVar.c(abVar);
                    if (map != null && (cVar instanceof com.d.f.f)) {
                        com.d.f.f fVar = (com.d.f.f) cVar;
                        if (fVar.o() && (list2 = map.get(fVar)) != null) {
                            for (com.d.e.k kVar : list2) {
                                kVar.a().a(abVar, kVar.b());
                            }
                        }
                    }
                    abVar.p().a(a3);
                    abVar.p().a(a2);
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private void a(ab abVar, List<com.d.d.b> list) {
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (!(bVar instanceof z)) {
                    Object a2 = abVar.p().a(com.d.d.q.LIST_MARKER, (com.d.i.f) bVar);
                    ((com.d.i.c) bVar).f(abVar);
                    abVar.p().a(a2);
                } else {
                    a(abVar);
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
                    if (bVar instanceof com.d.i.c) {
                        com.d.i.c cVar = (com.d.i.c) bVar;
                        b bVar2 = new b(cVar.af().k());
                        EnumSet.noneOf(b.a.class);
                        a(abVar, bVar2.a(abVar, cVar, abVar.w()));
                    } else {
                        s sVar = (s) bVar;
                        Object a2 = abVar.p().a(com.d.d.q.INLINE, (com.d.i.f) bVar);
                        sVar.a(abVar);
                        abVar.p().a(a2);
                    }
                } else {
                    a(abVar);
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private void c(ab abVar, List<com.d.d.b> list) {
        for (com.d.d.b bVar : list) {
            if (!(bVar instanceof y)) {
                if (bVar instanceof z) {
                    a(abVar);
                } else {
                    b(abVar, (com.d.i.c) bVar);
                }
            } else {
                a(abVar, (y) bVar);
            }
        }
    }

    private static void b(ab abVar, com.d.i.c cVar) {
        Rectangle c = cVar.c(cVar.ab(), cVar.aa(), abVar);
        Point c2 = cVar.E().c();
        if (c.x != c2.x || c.y != c2.y) {
            cVar.E().a(c.x, c.y);
        }
        Object a2 = abVar.p().a(com.d.d.q.REPLACED, cVar);
        abVar.p().a(abVar, cVar);
        abVar.p().a(a2);
    }

    private void a(ab abVar, com.d.i.f fVar, int i) {
        abVar.p().a(r.a(abVar, fVar, abVar.s(), i));
    }

    private void b(ab abVar) {
        abVar.p().g();
    }

    private void a(ab abVar, Rectangle rectangle) {
        abVar.p().f(rectangle);
    }

    private void c(ab abVar) {
        abVar.p().h();
    }

    private static void a(ab abVar, t tVar) {
        tVar.b(abVar);
        if (abVar.w() == -1) {
            new com.d.i.b.b(abVar.s().d(abVar, 1), abVar.s().d(abVar, 3)).a(abVar, tVar);
            return;
        }
        Rectangle b2 = abVar.s().b(abVar, abVar.w());
        abVar.p().a(b2.x, 0.0d);
        new com.d.i.b.b(b2.x, abVar.s().d(abVar, 3)).a(abVar, tVar);
        abVar.p().a(-b2.x, 0.0d);
    }

    public final void a(ab abVar, c.a aVar) {
        for (com.d.d.b bVar : aVar.b()) {
            if (!(bVar instanceof q)) {
                if (!(bVar instanceof i)) {
                    if (!(bVar instanceof o)) {
                        if (!(bVar instanceof f)) {
                            if (!(bVar instanceof j)) {
                                if (!(bVar instanceof h)) {
                                    if (!(bVar instanceof p)) {
                                        if (!(bVar instanceof n)) {
                                            if (!(bVar instanceof l)) {
                                                if (!(bVar instanceof g)) {
                                                    if (!(bVar instanceof m)) {
                                                        if (bVar instanceof k) {
                                                            c(abVar);
                                                        }
                                                    } else {
                                                        a(abVar, ((m) bVar).a());
                                                    }
                                                } else {
                                                    g gVar = (g) bVar;
                                                    Object a2 = abVar.p().a(com.d.d.q.RUNNING, gVar.a().f());
                                                    a(abVar, gVar.a());
                                                    abVar.p().a(a2);
                                                }
                                            } else {
                                                ((l) bVar).a();
                                                b(abVar);
                                            }
                                        } else {
                                            n nVar = (n) bVar;
                                            a(abVar, nVar.b(), nVar.a());
                                        }
                                    } else {
                                        c(abVar, ((p) bVar).a());
                                    }
                                } else {
                                    b(abVar, ((h) bVar).a());
                                }
                            } else {
                                a(abVar, ((j) bVar).a());
                            }
                        } else {
                            f fVar = (f) bVar;
                            a(abVar, fVar.a(), fVar.b());
                        }
                    } else {
                        b(abVar, ((o) bVar).a());
                    }
                } else {
                    i iVar = (i) bVar;
                    Object a3 = abVar.p().a(com.d.d.q.LAYER, iVar.a());
                    Object a4 = abVar.p().a(com.d.d.q.BACKGROUND, iVar.a());
                    iVar.a().b(abVar);
                    iVar.a().c(abVar);
                    abVar.p().a(a4);
                    abVar.p().a(a3);
                }
            } else {
                q qVar = (q) bVar;
                Object a5 = abVar.p().a(com.d.d.q.BACKGROUND, qVar.a());
                qVar.a().h(abVar);
                abVar.p().a(a5);
            }
        }
    }
}
