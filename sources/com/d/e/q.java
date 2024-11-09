package com.d.e;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: infinitode-2.jar:com/d/e/q.class */
public class q implements com.d.c.b.d {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Node, Map<Node, Integer>> f1157a = new HashMap();

    public static void a(v vVar, com.d.i.c cVar, int i, int i2) {
        int d;
        byte a2 = vVar.f().a(cVar.ai()).a();
        int d_ = cVar.d_();
        com.d.i.u a3 = a(vVar, i, cVar);
        com.d.i.u uVar = a3;
        a3.a(a2);
        com.d.i.r rVar = null;
        List<com.d.i.q> list = null;
        HashMap hashMap = new HashMap();
        if (cVar instanceof com.d.i.b) {
            List<com.d.i.q> f = ((com.d.i.b) cVar).f();
            list = f;
            if (f != null) {
                list = new ArrayList(list);
                rVar = a(vVar, uVar, list, d_, hashMap);
            }
        }
        if (list == null) {
            list = new ArrayList();
        }
        int c = d_ - vVar.l().c(vVar, uVar, d_);
        com.d.c.f.c a4 = cVar.a();
        int c2 = (int) a4.c(vVar);
        int b2 = (int) a4.b(com.d.c.a.a.ap, d_, vVar);
        int i3 = c - b2;
        int i4 = b2 + 0;
        com.d.i.x u = vVar.u();
        com.d.i.x xVar = u;
        if (u != null && cVar.a().au()) {
            i3 -= xVar.e();
            i4 += xVar.e();
        }
        vVar.a((com.d.i.x) null);
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        ArrayList arrayList2 = new ArrayList();
        if (vVar.s().b()) {
            cVar.a(vVar, vVar.s().a(cVar.a()));
            z = true;
        }
        boolean b3 = vVar.t().b();
        boolean z2 = false;
        int i7 = 0;
        for (ac acVar : cVar.G()) {
            if (acVar.a().o()) {
                com.d.i.q qVar = (com.d.i.q) acVar;
                com.d.c.f.c a5 = qVar.a();
                if (qVar.g()) {
                    com.d.i.r rVar2 = rVar;
                    rVar = new com.d.i.r(vVar, qVar.h(), a5, d_);
                    list.add(qVar);
                    hashMap.put(qVar, rVar);
                    if (rVar2 == null) {
                        uVar.a(vVar, rVar);
                    } else {
                        rVar2.a(vVar, (Object) rVar);
                    }
                    if (rVar.ai() != null) {
                        String g = vVar.e().g(rVar.ai());
                        if (g != null) {
                            vVar.a(g, rVar);
                        }
                        String b4 = vVar.e().b(rVar.ai());
                        if (b4 != null) {
                            vVar.a(b4, rVar);
                        }
                    }
                    i5 += a5.a(vVar, d_, 1);
                    i6 += a5.a(vVar, d_, 2);
                }
                x xVar2 = new x();
                xVar2.a(qVar.c());
                xVar2.a(qVar.q());
                if (qVar.j()) {
                    xVar2.a(qVar.i().a());
                }
                do {
                    xVar2.b();
                    int i8 = 0;
                    if (xVar2.e() == 0) {
                        i8 = 0 + i5 + i6;
                    }
                    if (a(uVar, a5, xVar2, z2)) {
                        a(xVar2);
                    }
                    z2 = false;
                    if (xVar2.f().length() == 0) {
                        break;
                    }
                    if (b3 && !xVar2.k()) {
                        i3 -= a(vVar, uVar, rVar, xVar2, d_, i3, qVar.b()).i();
                        if (rVar.g()) {
                            i5 -= rVar.a().a(vVar, d_, 1);
                        }
                        b3 = false;
                    } else {
                        xVar2.m();
                        com.d.i.s a6 = a(vVar, qVar.a(), i3 - i8, xVar2, false, qVar.b());
                        if (qVar.a().aA()) {
                            a6.a(qVar.a().b(com.d.c.a.a.T, 0.0f, vVar));
                        }
                        if (xVar2.h() && !uVar.f() && (d = vVar.l().d(vVar, uVar, d_)) > 0) {
                            com.d.i.u uVar2 = uVar;
                            uVar2.o(uVar2.an() + d);
                            uVar.B();
                            i3 = d_ - vVar.l().c(vVar, uVar, d_);
                            xVar2.l();
                        } else if (!xVar2.h() || (xVar2.h() && !uVar.f())) {
                            if (qVar.j()) {
                                a6.a(new o(qVar.i(), qVar.o()));
                            }
                            uVar.c(a6.h());
                            rVar.a(vVar, a6);
                            uVar.a(true);
                            xVar2.b(xVar2.c());
                            i3 -= a6.f();
                            if (rVar.g()) {
                                int a7 = rVar.a().a(vVar, d_, 1);
                                i5 -= a7;
                                i3 -= a7;
                            }
                        } else {
                            xVar2.l();
                        }
                    }
                    if (xVar2.i()) {
                        if (qVar.a().at()) {
                            uVar.a(vVar);
                        }
                        a(uVar, vVar, cVar, c2, d_, arrayList, z, arrayList2, xVar, i4, b(vVar, cVar, i2, i7));
                        i7++;
                        xVar = null;
                        i4 = 0;
                        if (uVar.c() && z) {
                            xVar2.a(ad.a(qVar.c(), qVar.a()));
                        }
                        com.d.i.u a8 = a(vVar, uVar, cVar);
                        uVar = a8;
                        a8.a(a2);
                        com.d.i.r a9 = a(vVar, uVar, list, d_, hashMap);
                        rVar = a9;
                        if (!(a9.U() instanceof com.d.i.u)) {
                            rVar.U();
                        }
                        i3 = d_ - vVar.l().c(vVar, uVar, d_);
                    }
                } while (!xVar2.k());
                if (qVar.f()) {
                    int a10 = a5.a(vVar, d_, 2);
                    i6 -= a10;
                    i3 -= a10;
                    List<com.d.i.q> list2 = list;
                    list2.remove(list2.size() - 1);
                    if (rVar.h()) {
                        rVar.a(vVar);
                        rVar.b(qVar.g());
                    }
                    rVar.a(true);
                    if (rVar.a().O()) {
                        if (!rVar.h() && (rVar.ai() == null || rVar.ai() != vVar.o().f().ai())) {
                            throw new RuntimeException("internal error");
                        }
                        if (!rVar.h()) {
                            vVar.o();
                            vVar.n();
                            arrayList2.add(rVar.af());
                        }
                    }
                    rVar = rVar.U() instanceof com.d.i.u ? null : (com.d.i.r) rVar.U();
                } else {
                    continue;
                }
            } else {
                com.d.i.c cVar2 = (com.d.i.c) acVar;
                if (cVar2.a().ag()) {
                    i3 -= a(vVar, uVar, cVar2, i3, arrayList);
                } else if (cVar2.a().p() || cVar2.a().r()) {
                    a(vVar, cVar, cVar2, i);
                    if (cVar2.Q() > i3 && uVar.f()) {
                        a(uVar, vVar, cVar, c2, d_, arrayList, z, arrayList2, xVar, i4, b(vVar, cVar, i2, i7));
                        i7++;
                        xVar = null;
                        i4 = 0;
                        com.d.i.u a11 = a(vVar, uVar, cVar);
                        uVar = a11;
                        a11.a(a2);
                        com.d.i.r a12 = a(vVar, uVar, list, d_, hashMap);
                        rVar = a12;
                        if (a12 != null && !(rVar.U() instanceof com.d.i.u)) {
                            rVar.U();
                        }
                        i3 = d_ - vVar.l().c(vVar, uVar, d_);
                        cVar2.c(vVar);
                        a(vVar, cVar, cVar2, i);
                    }
                    if (rVar == null) {
                        uVar.a(vVar, cVar2);
                    } else {
                        rVar.a(vVar, (Object) cVar2);
                    }
                    uVar.a(true);
                    uVar.b(true);
                    i3 -= cVar2.Q();
                    if (rVar != null && rVar.g()) {
                        i5 -= rVar.a().a(vVar, d_, 1);
                    }
                    b3 = false;
                    if (cVar2.Q() == 0) {
                        z2 = true;
                    }
                }
            }
        }
        uVar.a(vVar);
        a(uVar, vVar, cVar, c2, d_, arrayList, z, arrayList2, xVar, i4, b(vVar, cVar, i2, i7));
        if (uVar.c() && uVar.as() == 0 && xVar != null) {
            vVar.a(xVar);
        }
        cVar.u(d_);
        cVar.t(uVar.an() + uVar.as());
    }

    private static boolean b(v vVar, com.d.i.c cVar, int i, int i2) {
        return cVar.n(vVar) ? i2 == i : i > 0 && i2 == i;
    }

    private static com.d.i.r a(v vVar, com.d.i.u uVar, com.d.i.r rVar, x xVar, int i, int i2, byte b2) {
        com.d.c.f.c a2 = rVar.a();
        rVar.a(vVar.t().a(rVar.a()));
        com.d.i.r rVar2 = new com.d.i.r(vVar, null, rVar.a(), i);
        rVar2.b(true);
        rVar2.a(true);
        rVar.a(vVar, (Object) rVar2);
        uVar.a(true);
        com.d.i.s a3 = a(vVar, rVar2.a(), i2, xVar, true, b2);
        if (rVar2.a().aA()) {
            a3.a(rVar2.a().b(com.d.c.a.a.T, 0.0f, vVar));
        }
        rVar2.a(vVar, a3);
        rVar2.c(a3.f());
        xVar.b(xVar.c());
        vVar.t().c();
        rVar.a(a2);
        return rVar2;
    }

    private static void a(v vVar, com.d.i.c cVar, com.d.i.c cVar2, int i) {
        cVar2.g(cVar);
        cVar2.b(vVar.o());
        cVar2.a(vVar, cVar, i);
        cVar2.B();
        cVar2.b(vVar);
    }

    public static int a(com.d.c.f.d dVar, com.d.i.f fVar, int i, int i2) {
        int i3 = i;
        com.d.i.r rVar = null;
        for (int i4 = 0; i4 < fVar.V(); i4++) {
            com.d.i.f k = fVar.k(i4);
            if (!(k instanceof com.d.i.r)) {
                i3 -= k.Q();
                k.n(i3);
            } else {
                com.d.i.r rVar2 = (com.d.i.r) k;
                int a2 = a(dVar, rVar2, i3, i2);
                a(dVar, rVar2, i3, a2);
                i3 -= a2;
                rVar2.n(i3);
            }
        }
        if (0 != 0) {
            i3 -= rVar.a(dVar);
            rVar.c(i - i3);
        }
        return i - i3;
    }

    private static int a(com.d.c.f.d dVar, com.d.i.r rVar, int i, int i2) {
        int c = rVar.c(dVar);
        int i3 = i - c;
        int i4 = i2 - c;
        for (int i5 = 0; i5 < rVar.e(); i5++) {
            Object b2 = rVar.b(i5);
            if (!(b2 instanceof com.d.i.r)) {
                if (!(b2 instanceof com.d.i.s)) {
                    if (b2 instanceof com.d.i.f) {
                        com.d.i.f fVar = (com.d.i.f) b2;
                        i3 -= fVar.Q();
                        i4 -= fVar.Q();
                        fVar.n(i3);
                    }
                } else {
                    com.d.i.s sVar = (com.d.i.s) b2;
                    i3 -= sVar.f();
                    i4 -= sVar.f();
                    sVar.a(i4);
                }
            } else {
                com.d.i.r rVar2 = (com.d.i.r) b2;
                int a2 = a(dVar, rVar2, i3, a(dVar, rVar2, i3, i2));
                rVar2.n(i3 - a2);
                i3 -= a2;
                i4 -= a2;
            }
        }
        int a3 = i3 - rVar.a(dVar);
        rVar.c(i - a3);
        return i - a3;
    }

    public static int a(com.d.c.f.d dVar, com.d.i.f fVar, int i) {
        int i2;
        int Q;
        int i3 = 0;
        com.d.i.r rVar = null;
        for (int i4 = 0; i4 < fVar.V(); i4++) {
            com.d.i.f k = fVar.k(i4);
            if (k instanceof com.d.i.r) {
                com.d.i.r rVar2 = (com.d.i.r) fVar.k(i4);
                rVar2.n(i3);
                i2 = i3;
                Q = a(dVar, rVar2, i3);
            } else {
                k.n(i3);
                i2 = i3;
                Q = k.Q();
            }
            i3 = i2 + Q;
        }
        if (0 != 0) {
            i3 += rVar.c(dVar);
            rVar.c(i3);
        }
        return i3;
    }

    private static int a(com.d.c.f.d dVar, com.d.i.r rVar, int i) {
        int a2 = i + rVar.a(dVar);
        for (int i2 = 0; i2 < rVar.e(); i2++) {
            Object b2 = rVar.b(i2);
            if (!(b2 instanceof com.d.i.r)) {
                if (!(b2 instanceof com.d.i.s)) {
                    if (b2 instanceof com.d.i.f) {
                        com.d.i.f fVar = (com.d.i.f) b2;
                        fVar.n(a2);
                        a2 += fVar.Q();
                    }
                } else {
                    com.d.i.s sVar = (com.d.i.s) b2;
                    sVar.a(a2 - i);
                    a2 += sVar.f();
                }
            } else {
                com.d.i.r rVar2 = (com.d.i.r) b2;
                rVar2.n(a2);
                a2 += a(dVar, rVar2, a2);
            }
        }
        int c = a2 + rVar.c(dVar);
        rVar.c(c - i);
        return c - i;
    }

    public static com.d.i.ac a(v vVar, com.d.i.f fVar) {
        com.d.i.l e = fVar.a().e(vVar);
        return new com.d.i.ac(e.a(), a(vVar, fVar, e).a(), e.b());
    }

    private static void a(v vVar, com.d.i.f fVar, com.d.i.u uVar, com.d.i.x xVar) {
        if (uVar.V() == 0 || !uVar.l()) {
            uVar.t(0);
            return;
        }
        com.d.i.l e = fVar.a().e(vVar);
        af afVar = new af();
        p a2 = a(vVar, fVar, e);
        afVar.b(a2);
        List<com.d.i.ad> a3 = a(fVar, a2.a(), e);
        if (a3 != null) {
            uVar.a(a3);
        }
        for (int i = 0; i < uVar.V(); i++) {
            a(vVar, afVar, uVar.k(i));
        }
        afVar.h();
        uVar.t(afVar.c());
        int g = afVar.g();
        int f = afVar.f();
        if (afVar.b() < 0) {
            a(uVar, -afVar.b());
            if (a3 != null) {
                for (com.d.i.ad adVar : a3) {
                    adVar.a(adVar.a() - afVar.b());
                }
            }
            g -= afVar.b();
            f -= afVar.b();
        }
        if (xVar != null) {
            xVar.d().a(a2.a() - afVar.b());
            xVar.a(uVar);
            uVar.a(xVar);
        }
        uVar.d(a2.a() - afVar.b());
        uVar.b(g);
        uVar.a(f - g);
    }

    private static void a(v vVar, af afVar, com.d.i.r rVar) {
        afVar.a(a(vVar, rVar, afVar));
        b(vVar, rVar, afVar);
        afVar.e();
    }

    private static void a(v vVar, af afVar, com.d.i.c cVar) {
        a(vVar, cVar, cVar.d((com.d.c.f.d) vVar), cVar.as() - r0, afVar);
        afVar.a(cVar.an());
        afVar.b(cVar.an());
        afVar.c(cVar.an() + cVar.as());
        afVar.d(cVar.an() + cVar.as());
    }

    private static void a(com.d.i.u uVar, int i) {
        for (int i2 = 0; i2 < uVar.V(); i2++) {
            com.d.i.f k = uVar.k(i2);
            k.o(k.an() + i);
            if (k instanceof com.d.i.r) {
                a((com.d.i.r) k, i);
            }
        }
    }

    private static void a(com.d.i.r rVar, int i) {
        for (int i2 = 0; i2 < rVar.e(); i2++) {
            Object b2 = rVar.b(i2);
            if (b2 instanceof com.d.i.f) {
                ((com.d.i.f) b2).o(((com.d.i.f) b2).an() + i);
                if (b2 instanceof com.d.i.r) {
                    a((com.d.i.r) b2, i);
                }
            }
        }
    }

    private static p a(v vVar, com.d.i.r rVar, af afVar) {
        com.d.i.l e = rVar.a().e(vVar);
        float c = rVar.a().c(vVar);
        int round = Math.round((c - rVar.a().b(vVar).f1094a) / 2.0f);
        int i = round;
        if (round > 0) {
            i = Math.round((c - (e.b() + e.a())) / 2.0f);
        }
        rVar.a(Math.round(e.a()));
        a(vVar, rVar, e.a(), e.b(), afVar);
        List<com.d.i.ad> a2 = a(rVar, rVar.c(), e);
        if (a2 != null) {
            rVar.a(a2);
        }
        p pVar = new p();
        pVar.a(rVar.an() + rVar.c());
        pVar.c(rVar.an() - i);
        pVar.b(Math.round(pVar.c() + c));
        pVar.e(rVar.an());
        pVar.d((int) (pVar.a() + e.b()));
        com.d.c.f.a.h o = rVar.o(vVar);
        com.d.c.f.a.a b2 = rVar.b((com.d.c.f.d) vVar);
        pVar.g((int) Math.floor((rVar.an() - b2.t()) - o.t()));
        pVar.f((int) Math.ceil(rVar.an() + e.a() + e.b() + b2.v() + o.v()));
        return pVar;
    }

    private static List<com.d.i.ad> a(com.d.i.f fVar, int i, com.d.i.l lVar) {
        int b2;
        ArrayList arrayList = null;
        List<com.d.c.a.c> ar = fVar.a().ar();
        if (ar != null) {
            arrayList = new ArrayList(ar.size());
            if (ar.contains(com.d.c.a.c.bk)) {
                com.d.i.ad adVar = new com.d.i.ad(com.d.c.a.c.bk);
                if (lVar.e() == 0.0f) {
                    adVar.a(Math.round(i + lVar.f()));
                } else {
                    adVar.a(Math.round(i + lVar.e()));
                }
                adVar.b(Math.round(lVar.f()));
                if (lVar.e() == 0.0f && adVar.a() > (b2 = (i + ((int) lVar.b())) - adVar.b())) {
                    adVar.a(b2);
                }
                arrayList.add(adVar);
            }
            if (ar.contains(com.d.c.a.c.ad)) {
                com.d.i.ad adVar2 = new com.d.i.ad(com.d.c.a.c.ad);
                adVar2.a(Math.round(i + lVar.c()));
                adVar2.b(Math.round(lVar.d()));
                arrayList.add(adVar2);
            }
            if (ar.contains(com.d.c.a.c.ax)) {
                com.d.i.ad adVar3 = new com.d.i.ad(com.d.c.a.c.ax);
                adVar3.a(0);
                adVar3.b(Math.round(lVar.f()));
                arrayList.add(adVar3);
            }
        }
        return arrayList;
    }

    private static void a(v vVar, com.d.i.f fVar, float f, float f2, af afVar) {
        p d = afVar.d();
        com.d.c.f.c a2 = fVar.a();
        if (a2.g(com.d.c.a.a.as)) {
            fVar.o((int) ((d.a() - f) - a2.a(com.d.c.a.a.as, a2.c(vVar), vVar)));
            return;
        }
        com.d.c.a.c e = a2.e(com.d.c.a.a.as);
        if (e != com.d.c.a.c.g) {
            if (e == com.d.c.a.c.bf) {
                fVar.o(d.e());
                return;
            }
            if (e == com.d.c.a.c.be) {
                fVar.o(Math.round((d.d() - f2) - f));
                return;
            }
            if (e == com.d.c.a.c.al) {
                fVar.o(Math.round(((d.a() - d.e()) / 2) - ((f + f2) / 2.0f)));
                return;
            } else if (e == com.d.c.a.c.aU) {
                fVar.o(Math.round(d.a() - ((3.0f * f) / 2.0f)));
                return;
            } else if (e == com.d.c.a.c.aT) {
                fVar.o(Math.round(d.a() - (f / 2.0f)));
                return;
            }
        }
        fVar.o(Math.round(d.a() - f));
    }

    private static p a(v vVar, com.d.i.f fVar, com.d.i.l lVar) {
        float c = fVar.a().c(vVar);
        int round = Math.round((c - fVar.a().b(vVar).f1094a) / 2.0f);
        int i = round;
        if (round > 0) {
            i = Math.round((c - (lVar.b() + lVar.a())) / 2.0f);
        }
        p pVar = new p();
        pVar.a((int) (i + lVar.a()));
        pVar.e(i);
        pVar.d((int) (pVar.a() + lVar.b()));
        pVar.c(i);
        pVar.b((int) (i + c));
        return pVar;
    }

    private static void b(v vVar, com.d.i.r rVar, af afVar) {
        for (int i = 0; i < rVar.e(); i++) {
            Object b2 = rVar.b(i);
            if (b2 instanceof com.d.i.f) {
                a(vVar, afVar, (com.d.i.f) b2);
            }
        }
    }

    private static void a(v vVar, af afVar, com.d.i.f fVar) {
        com.d.c.a.c e;
        af afVar2 = afVar;
        if (!fVar.a().g(com.d.c.a.a.as) && ((e = fVar.a().e(com.d.c.a.a.as)) == com.d.c.a.c.bi || e == com.d.c.a.c.l)) {
            afVar2 = afVar.a(fVar);
        }
        if (fVar instanceof com.d.i.r) {
            a(vVar, afVar2, (com.d.i.r) fVar);
        } else {
            a(vVar, afVar2, (com.d.i.c) fVar);
        }
    }

    private static void a(com.d.i.u uVar, v vVar, com.d.i.c cVar, int i, int i2, List<m> list, boolean z, List<t> list2, com.d.i.x xVar, int i3, boolean z2) {
        int a2;
        uVar.c(i3);
        uVar.e();
        if (uVar.n()) {
            a2 = a(vVar, uVar, 0, 0);
            a(vVar, uVar, a2, a2);
        } else {
            a2 = a((com.d.c.f.d) vVar, (com.d.i.f) uVar, 0);
        }
        uVar.u(a2);
        a(vVar, cVar, uVar, xVar);
        if (uVar.as() != 0 && uVar.as() < i && !uVar.k()) {
            uVar.t(i);
        }
        if (vVar.r()) {
            uVar.a(vVar, z2);
        }
        a(vVar, uVar, i2);
        uVar.C();
        cVar.a(vVar, uVar);
        if (list2.size() > 0) {
            a(vVar, list2);
            list2.clear();
        }
        if (z && uVar.c()) {
            vVar.s().c();
            cVar.a_(vVar);
        }
        if (list.size() > 0) {
            for (m mVar : list) {
                com.a.a.b.c.a.a(vVar, uVar, mVar.b(), i2, (List) null);
                uVar.a(mVar.b());
            }
            list.clear();
        }
    }

    private static void a(v vVar, com.d.i.u uVar, int i) {
        if (!uVar.i() && !uVar.U().a().at()) {
            uVar.a(new r(vVar, uVar, i));
        } else {
            com.d.i.m mVar = new com.d.i.m();
            mVar.a(vVar.l().a(vVar, uVar, i));
            mVar.b(vVar.l().b(vVar, uVar, i));
            uVar.a(mVar);
        }
        uVar.a(false, (com.d.c.f.d) vVar);
        if (!uVar.i() && !uVar.U().a().at()) {
            uVar.a((com.d.i.m) null);
        }
    }

    private static void a(v vVar, List<t> list) {
        Iterator<t> it = list.iterator();
        while (it.hasNext()) {
            it.next().b(vVar);
        }
    }

    private static com.d.i.s a(v vVar, com.d.c.f.c cVar, int i, x xVar, boolean z, byte b2) {
        com.d.i.s sVar = new com.d.i.s();
        String d = xVar.d();
        if (z) {
            d = ad.b(d, cVar);
            xVar.a(d);
            j.a(vVar, xVar, i, cVar);
        } else {
            j.b(vVar, xVar, i, cVar);
        }
        sVar.a(d);
        xVar.n();
        sVar.a(xVar.e(), xVar.c());
        sVar.b(xVar.j());
        sVar.a(b2);
        return sVar;
    }

    private static int a(v vVar, com.d.i.u uVar, com.d.i.c cVar, int i, List<m> list) {
        int i2 = 0;
        com.d.c.f.c a2 = cVar.a();
        if (a2.A() || a2.B()) {
            com.a.a.b.c.a.a(vVar, uVar, cVar);
            uVar.a(cVar);
        } else if (!a2.C()) {
            if (a2.P()) {
                cVar.a(uVar);
                vVar.p().c(cVar);
            }
        } else {
            m a3 = com.a.a.b.c.a.a(vVar, uVar, cVar, i, list);
            if (a3.a()) {
                list.add(a3);
            } else {
                i2 = a3.b().Q();
                uVar.a(a3.b());
            }
        }
        return i2;
    }

    private static boolean a(com.d.i.u uVar, com.d.c.f.c cVar, x xVar, boolean z) {
        if ((!uVar.f() || z) && xVar.f().startsWith(SequenceUtils.SPACE)) {
            com.d.c.a.c i = cVar.i();
            if (i != com.d.c.a.c.aq && i != com.d.c.a.c.ar && i != com.d.c.a.c.aC) {
                if (i == com.d.c.a.c.aD && xVar.e() > 0 && xVar.d().length() > xVar.e() - 1 && xVar.d().charAt(xVar.e() - 1) != '\n') {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private static void a(x xVar) {
        String f = xVar.f();
        int i = 0;
        while (i < f.length() && f.charAt(i) == ' ') {
            i++;
        }
        xVar.b(xVar.e() + i);
    }

    private static com.d.i.u a(v vVar, com.d.i.u uVar, com.d.i.f fVar) {
        int i = 0;
        if (uVar != null) {
            i = uVar.an() + uVar.as();
        }
        return a(vVar, i, fVar);
    }

    private static com.d.i.u a(v vVar, int i, com.d.i.f fVar) {
        com.d.i.u uVar = new com.d.i.u();
        uVar.a(fVar.a().a(com.d.c.a.c.h));
        uVar.f(fVar);
        uVar.r(vVar);
        uVar.o(i);
        uVar.B();
        return uVar;
    }

    private static com.d.i.r a(v vVar, com.d.i.u uVar, List<com.d.i.q> list, int i, Map<com.d.i.q, com.d.i.r> map) {
        com.d.i.r rVar = null;
        com.d.i.r rVar2 = null;
        boolean z = true;
        for (com.d.i.q qVar : list) {
            rVar = new com.d.i.r(vVar, qVar.h(), qVar.a(), i);
            com.d.i.r rVar3 = map.get(qVar);
            if (rVar3 != null) {
                rVar.c(rVar3.h());
            }
            map.put(qVar, rVar);
            if (z) {
                uVar.a(vVar, rVar);
                z = false;
            } else {
                rVar2.a(vVar, (Object) rVar, false);
            }
            rVar2 = rVar;
        }
        return rVar;
    }

    @Override // com.d.c.b.d
    public Object a(Object obj) {
        Node parentNode = ((Element) obj).getParentNode();
        Node node = parentNode;
        if (parentNode.getNodeType() != 1) {
            node = null;
        }
        return node;
    }

    @Override // com.d.c.b.d
    public Object b(Object obj) {
        Node node;
        Node previousSibling = ((Element) obj).getPreviousSibling();
        while (true) {
            node = previousSibling;
            if (node == null || node.getNodeType() == 1) {
                break;
            }
            previousSibling = node.getPreviousSibling();
        }
        if (node == null || node.getNodeType() != 1) {
            return null;
        }
        return node;
    }

    @Override // com.d.c.b.d
    public boolean c(Object obj) {
        Node node;
        Node firstChild = ((Element) obj).getParentNode().getFirstChild();
        while (true) {
            node = firstChild;
            if (node == null || node.getNodeType() == 1) {
                break;
            }
            firstChild = node.getNextSibling();
        }
        return node == obj;
    }

    @Override // com.d.c.b.d
    public boolean d(Object obj) {
        Node node;
        Node lastChild = ((Element) obj).getParentNode().getLastChild();
        while (true) {
            node = lastChild;
            if (node == null || node.getNodeType() == 1) {
                break;
            }
            lastChild = node.getPreviousSibling();
        }
        return node == obj;
    }

    @Override // com.d.c.b.d
    public boolean a(Object obj, String str, String str2) {
        String str3;
        Element element = (Element) obj;
        String localName = element.getLocalName();
        if (localName == null) {
            str3 = element.getNodeName();
        } else {
            str3 = localName;
        }
        if (str != null) {
            return str2.equals(localName) && str.equals(element.getNamespaceURI());
        }
        if (str == "") {
            return str2.equals(str3) && element.getNamespaceURI() == null;
        }
        return str2.equals(str3);
    }

    @Override // com.d.c.b.d
    public int e(Object obj) {
        Node parentNode = ((Element) obj).getParentNode();
        Map<Node, Integer> map = this.f1157a.get(parentNode);
        Map<Node, Integer> map2 = map;
        if (map == null) {
            NodeList childNodes = parentNode.getChildNodes();
            int length = childNodes.getLength();
            map2 = new HashMap();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                Node item = childNodes.item(i2);
                if (item.getNodeType() == 1) {
                    int i3 = i;
                    i++;
                    map2.put(item, Integer.valueOf(i3));
                }
            }
            this.f1157a.put(parentNode, map2);
        }
        return map2.get(obj).intValue();
    }
}
