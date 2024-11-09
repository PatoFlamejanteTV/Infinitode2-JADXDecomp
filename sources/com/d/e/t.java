package com.d.e;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/e/t.class */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    private t f1160a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1161b;
    private List<t> c;
    private com.d.i.f d;
    private List<com.d.i.c> e;
    private boolean f;
    private boolean g;
    private List<com.d.i.aa> h;
    private com.d.i.aa i;
    private Set<com.d.i.c> j;
    private List<com.d.i.c> k;
    private Map<String, List<com.d.i.c>> l;
    private boolean m;
    private boolean n;
    private AffineTransform o;
    private final boolean p;

    public t(com.d.i.f fVar, com.d.c.f.d dVar) {
        this((t) null, fVar);
        d(true);
    }

    public t(t tVar, com.d.i.f fVar) {
        this.i = null;
        this.f1160a = tVar;
        this.d = fVar;
        d((fVar.a().G() && !fVar.a().M()) || !fVar.a().a(com.d.c.a.a.ay, com.d.c.a.c.ap));
        fVar.a(this);
        fVar.b(this);
        this.p = !fVar.a().a(com.d.c.a.a.ay, com.d.c.a.c.ap);
        this.n = (tVar != null && tVar.n) || fVar.a().B();
    }

    public final void a(com.d.c.f.d dVar) {
        AffineTransform affineTransform = this.f1160a == null ? null : this.f1160a.o;
        this.o = this.p ? com.d.i.a.r.a(f(), dVar, affineTransform) : affineTransform;
        Iterator<t> it = q().iterator();
        while (it.hasNext()) {
            it.next().a(dVar);
        }
    }

    public final AffineTransform a() {
        return this.o;
    }

    public final boolean b() {
        return this.p;
    }

    public final void a(boolean z) {
        this.m = true;
    }

    private boolean n() {
        return this.m;
    }

    public final boolean c() {
        return this.n;
    }

    public final t d() {
        return this.f1160a;
    }

    public final boolean e() {
        return this.f1161b;
    }

    private void d(boolean z) {
        this.f1161b = z;
    }

    private int o() {
        if (this.d.a().a(com.d.c.a.a.aB, com.d.c.a.c.e)) {
            return 0;
        }
        return (int) this.d.a().b(com.d.c.a.a.aB);
    }

    private boolean p() {
        return this.d.a().a(com.d.c.a.a.aB, com.d.c.a.c.e);
    }

    public final com.d.i.f f() {
        return this.d;
    }

    public final void a(t tVar) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(tVar);
    }

    public static com.d.i.aa a(com.d.c.f.d dVar, String str) {
        com.d.i.aa aaVar = new com.d.i.aa();
        String str2 = null;
        if (dVar instanceof v) {
            str2 = ((v) dVar).B();
        }
        com.d.c.c.e a2 = dVar.c().a(str2, str);
        aaVar.a(a2);
        aaVar.a(new com.d.c.f.f().a(a2.a()));
        aaVar.e(aaVar.a(dVar));
        return aaVar;
    }

    public final void a(com.d.i.c cVar) {
        if (this.e != null) {
            this.e.remove(cVar);
        }
    }

    @Deprecated
    private void e(com.d.i.ab abVar) {
        if (this.e != null) {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                a(abVar, this.e.get(size));
            }
        }
    }

    @Deprecated
    private static void a(com.d.i.ab abVar, List<t> list) {
        Iterator<t> it = list.iterator();
        while (it.hasNext()) {
            it.next().a(abVar);
        }
    }

    public final void b(com.d.i.c cVar) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(cVar);
        cVar.L().a(this);
    }

    public final List<t> a(int i) {
        ArrayList arrayList = new ArrayList();
        List<t> q = q();
        arrayList.addAll(e(i));
        for (t tVar : q) {
            if (!tVar.e()) {
                if (!tVar.n()) {
                    if (i == 4 && tVar.p()) {
                        arrayList.add(tVar);
                    } else if (i == 3 && tVar.o() < 0) {
                        arrayList.add(tVar);
                    } else if (i == 1 && tVar.o() > 0) {
                        arrayList.add(tVar);
                    } else if (i == 2 && !tVar.p() && tVar.o() == 0) {
                        arrayList.add(tVar);
                    }
                }
                arrayList.addAll(tVar.a(i));
            }
        }
        return arrayList;
    }

    private List<t> e(int i) {
        ArrayList arrayList = new ArrayList();
        for (t tVar : q()) {
            if (!tVar.n() && tVar.e()) {
                if (!tVar.p()) {
                    int o = tVar.o();
                    if (i == 3 && o < 0) {
                        arrayList.add(tVar);
                    } else if (i == 1 && o > 0) {
                        arrayList.add(tVar);
                    } else if (i == 2 && o == 0) {
                        arrayList.add(tVar);
                    }
                } else if (i == 4) {
                    arrayList.add(tVar);
                }
            }
        }
        return arrayList;
    }

    public final List<t> b(int i) {
        List<t> a2 = a(i);
        Collections.sort(a2, (tVar, tVar2) -> {
            return tVar.o() - tVar2.o();
        });
        return a2;
    }

    @Deprecated
    private void a(com.d.i.ab abVar, List<com.d.i.f> list, Map map, h hVar) {
        List list2;
        g gVar = new g(abVar.p(), hVar.a());
        for (int i = 0; i < list.size(); i++) {
            gVar.a(i);
            com.d.i.c cVar = (com.d.i.c) list.get(i);
            cVar.b(abVar);
            cVar.c(abVar);
            if (abVar.j()) {
                cVar.g(abVar);
            }
            if (map != null && (cVar instanceof com.d.f.f)) {
                com.d.f.f fVar = (com.d.f.f) cVar;
                if (fVar.o() && (list2 = (List) map.get(fVar)) != null) {
                    c(abVar, list2);
                }
            }
            gVar.a(abVar, i);
        }
        gVar.a(list.size());
    }

    @Deprecated
    private static void b(com.d.i.ab abVar, List list) {
        abVar.p();
    }

    public final Dimension a(v vVar) {
        return e(vVar).b();
    }

    @Deprecated
    private static void a(com.d.i.ab abVar, List list, h hVar) {
        g gVar = new g(abVar.p(), hVar.b());
        for (int i = 0; i < list.size(); i++) {
            gVar.a(i);
            gVar.a(abVar, i);
            ((s) list.get(i)).a(abVar);
        }
        gVar.a(list.size());
    }

    @Deprecated
    public final void a(com.d.i.ab abVar) {
        List<AffineTransform> a2;
        if (f().a().B()) {
            b(abVar);
        }
        if (h()) {
            f().h(abVar);
        }
        if (!j() && ((com.d.i.c) f()).A()) {
            a2 = a(abVar, f());
            f(abVar);
            b(abVar, (com.d.i.c) f());
        } else {
            h hVar = new h();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            new d().a(abVar, abVar.p().c(), this, arrayList, arrayList2, hVar);
            a2 = a(abVar, f());
            if (!j()) {
                f(abVar);
                if (abVar.j()) {
                    ((com.d.i.c) f()).g(abVar);
                }
            }
            if (h() || e()) {
                a(abVar, b(3));
            }
            a(abVar, arrayList, a(arrayList), hVar);
            e(abVar);
            b(abVar, arrayList, hVar);
            a(abVar, arrayList2, hVar);
            c(abVar, arrayList, hVar);
            b(abVar, arrayList2);
            if (h() || e()) {
                a(abVar, a(4));
                a(abVar, b(2));
                a(abVar, b(1));
            }
        }
        abVar.p().b(a2);
    }

    @Deprecated
    private static float a(com.d.c.d.j jVar) {
        if (jVar.a() == 11) {
            return (float) Math.toRadians(jVar.f());
        }
        if (jVar.a() == 12) {
            return jVar.f();
        }
        return (float) (jVar.f() * 0.015707963267948967d);
    }

    public final List<com.d.i.c> g() {
        return this.e == null ? Collections.emptyList() : this.e;
    }

    @Deprecated
    private List<AffineTransform> a(com.d.i.ab abVar, com.d.i.f fVar) {
        com.d.c.f.g i = fVar.a().i(com.d.c.a.a.ay);
        if (i.h() && i.f() == com.d.c.a.c.ap) {
            return Collections.emptyList();
        }
        float b2 = fVar.a().b(com.d.c.a.a.az, fVar.Q(), abVar);
        float c = fVar.a().c(com.d.c.a.a.aA, fVar.as(), abVar);
        float f = abVar.p().f() ? -1.0f : 1.0f;
        float ab = (b2 + fVar.ab()) - abVar.p().a();
        float aa = (c + fVar.aa()) - abVar.p().b();
        if (abVar.p().f()) {
            com.d.c.f.a.h g = abVar.s().g(abVar);
            ab += g.w();
            aa += g.t();
            for (int i2 = 0; i2 < abVar.t() && i2 < k().size(); i2++) {
                com.d.c.f.a.h g2 = k().get(i2).g(abVar);
                aa += g2.t() + g2.v();
            }
            com.d.c.a.d[] h = abVar.s().h();
            if (h != null) {
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                boolean z5 = false;
                for (com.d.c.a.d dVar : h) {
                    if (dVar == com.d.c.a.d.k || dVar == com.d.c.a.d.l || dVar == com.d.c.a.d.m) {
                        z = true;
                    }
                    if (dVar == com.d.c.a.d.f971b || dVar == com.d.c.a.d.c || dVar == com.d.c.a.d.d) {
                        z2 = true;
                    }
                    if (dVar == com.d.c.a.d.g || dVar == com.d.c.a.d.h || dVar == com.d.c.a.d.i) {
                        z4 = true;
                    }
                    com.d.c.a.d dVar2 = com.d.c.a.d.f970a;
                    if (dVar == com.d.c.a.d.e) {
                        z3 = true;
                    }
                    com.d.c.a.d dVar3 = com.d.c.a.d.f;
                    if (dVar == com.d.c.a.d.j) {
                        z5 = true;
                    }
                }
                if (z) {
                    ab -= g.w();
                }
                if (z2) {
                    aa -= g.t();
                }
                if (z4) {
                    aa -= g.t() + g.v();
                }
                ab -= g.w();
                aa -= g.t();
                if (z3) {
                    ab -= g.w();
                    aa -= g.t();
                }
                if (z4) {
                    aa -= g.t() + g.v();
                }
                if (z5) {
                    ab -= g.w();
                    aa -= g.t() + g.v();
                }
            }
        }
        List<com.d.c.d.j> j = ((com.d.c.f.a.f) i).j();
        ArrayList arrayList = new ArrayList();
        AffineTransform translateInstance = AffineTransform.getTranslateInstance(ab, aa);
        AffineTransform translateInstance2 = AffineTransform.getTranslateInstance(-ab, -aa);
        arrayList.add(translateInstance);
        a(f, j, arrayList);
        arrayList.add(translateInstance2);
        return abVar.p().a((List<AffineTransform>) arrayList);
    }

    @Deprecated
    private void a(float f, List<com.d.c.d.j> list, List<AffineTransform> list2) {
        for (com.d.c.d.j jVar : list) {
            String a2 = jVar.n().a();
            List<com.d.c.d.j> b2 = jVar.n().b();
            if ("rotate".equalsIgnoreCase(a2)) {
                list2.add(AffineTransform.getRotateInstance(f * a(b2.get(0))));
            } else if ("scale".equalsIgnoreCase(a2) || "scalex".equalsIgnoreCase(a2) || "scaley".equalsIgnoreCase(a2)) {
                float f2 = b2.get(0).f();
                float f3 = b2.get(0).f();
                if (b2.size() > 1) {
                    f3 = b2.get(1).f();
                }
                if ("scalex".equalsIgnoreCase(a2)) {
                    f3 = 1.0f;
                }
                if ("scaley".equalsIgnoreCase(a2)) {
                    f2 = 1.0f;
                }
                list2.add(AffineTransform.getScaleInstance(f2, f3));
            } else if ("skew".equalsIgnoreCase(a2)) {
                float a3 = f * a(b2.get(0));
                float f4 = 0.0f;
                if (b2.size() > 1) {
                    f4 = a(b2.get(1));
                }
                list2.add(AffineTransform.getShearInstance(Math.tan(a3), Math.tan(f4)));
            } else if ("skewx".equalsIgnoreCase(a2)) {
                list2.add(AffineTransform.getShearInstance(Math.tan(f * a(b2.get(0))), 0.0d));
            } else if ("skewy".equalsIgnoreCase(a2)) {
                list2.add(AffineTransform.getShearInstance(0.0d, Math.tan(f * a(b2.get(0)))));
            } else if ("matrix".equalsIgnoreCase(a2)) {
                list2.add(new AffineTransform(b2.get(0).f(), b2.get(1).f(), b2.get(2).f(), b2.get(3).f(), b2.get(4).f(), b2.get(5).f()));
            } else if ("translate".equalsIgnoreCase(a2)) {
                com.d.m.l.g(Level.WARNING, "translate function not implemented at this time");
            } else if ("translateX".equalsIgnoreCase(a2)) {
                com.d.m.l.g(Level.WARNING, "translateX function not implemented at this time");
            } else if ("translateY".equalsIgnoreCase(a2)) {
                com.d.m.l.g(Level.WARNING, "translateY function not implemented at this time");
            }
        }
    }

    @Deprecated
    private static void c(com.d.i.ab abVar, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            k kVar = (k) it.next();
            kVar.a().a(abVar, kVar.b());
        }
    }

    @Deprecated
    private static Map a(List list) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.d.i.f fVar = (com.d.i.f) it.next();
            if (fVar instanceof com.d.f.f) {
                com.d.f.f fVar2 = (com.d.f.f) fVar;
                if (fVar2.o()) {
                    List<k> list2 = (List) hashMap.get(fVar2.f());
                    List<k> list3 = list2;
                    if (list2 == null) {
                        list3 = new ArrayList();
                        hashMap.put(fVar2.f(), list3);
                    }
                    hashMap2.put(fVar2.f(), fVar2);
                    fVar2.a(hashSet, list3);
                }
            }
        }
        if (hashMap2.size() == 0) {
            return null;
        }
        HashMap hashMap3 = new HashMap();
        for (com.d.f.f fVar3 : hashMap2.values()) {
            List list4 = (List) hashMap.get(fVar3.f());
            Collections.sort(list4);
            hashMap3.put(fVar3, list4);
        }
        return hashMap3;
    }

    @Deprecated
    public final void a(com.d.i.ab abVar, com.d.i.c cVar) {
        h hVar = new h();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        new d().a(abVar, abVar.p().c(), this, cVar, arrayList, arrayList2, hVar);
        a(abVar, arrayList, a(arrayList), hVar);
        b(abVar, arrayList, hVar);
        a(abVar, arrayList2, hVar);
        b(abVar, arrayList2);
        c(abVar, arrayList, hVar);
    }

    @Deprecated
    private static void b(com.d.i.ab abVar, List list, h hVar) {
        g gVar = new g(abVar.p(), hVar.a());
        for (int i = 0; i < list.size(); i++) {
            gVar.a(i);
            ((com.d.i.c) list.get(i)).f(abVar);
            gVar.a(abVar, i);
        }
        gVar.a(list.size());
    }

    @Deprecated
    private void c(com.d.i.ab abVar, List list, h hVar) {
        g gVar = new g(abVar.p(), hVar.a());
        for (int i = 0; i < list.size(); i++) {
            gVar.a(i);
            com.d.i.c cVar = (com.d.i.c) list.get(i);
            if (cVar.A()) {
                b(abVar, cVar);
            }
            gVar.a(abVar, i);
        }
        gVar.a(list.size());
    }

    @Deprecated
    private void f(com.d.i.ab abVar) {
        if (f() instanceof com.d.i.c) {
            com.d.i.c cVar = (com.d.i.c) f();
            cVar.b(abVar);
            cVar.c(abVar);
        }
    }

    public final void b(com.d.i.ab abVar) {
        Rectangle h = abVar.h();
        com.d.i.f f = f();
        f.n(0);
        f.o(0);
        f.m(0);
        f.l(0);
        f.g(new com.d.i.ae(h));
        ((com.d.i.c) f).b(abVar, 3);
        f.c((com.d.c.f.d) abVar, false);
    }

    public final boolean h() {
        return d() == null && e();
    }

    private static void a(Dimension dimension, Dimension dimension2) {
        if (dimension2.width > dimension.width) {
            dimension.width = dimension2.width;
        }
        if (dimension2.height > dimension.height) {
            dimension.height = dimension2.height;
        }
    }

    @Deprecated
    private static void b(com.d.i.ab abVar, com.d.i.c cVar) {
        Rectangle c = cVar.c(cVar.ab(), cVar.aa(), abVar);
        Point c2 = cVar.E().c();
        if (c.x != c2.x || c.y != c2.y) {
            cVar.E().a(c.x, c.y);
        }
        if (abVar.n()) {
            cVar.E();
        } else {
            abVar.p().a(abVar, cVar);
        }
    }

    public final void b(v vVar) {
        Iterator<t> it = q().iterator();
        while (it.hasNext()) {
            it.next().g(vVar);
        }
    }

    private y e(v vVar) {
        f().c((com.d.c.f.d) vVar, true);
        y c = f().at().c();
        for (t tVar : q()) {
            if (!tVar.f().a().B() && tVar.f().a().A()) {
                a(c.b(), tVar.e(vVar).b());
            }
        }
        return c;
    }

    private List<t> q() {
        return this.c == null ? Collections.emptyList() : this.c;
    }

    private void b(t tVar) {
        boolean z = false;
        if (this.c != null) {
            Iterator<t> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next() == tVar) {
                    z = true;
                    it.remove();
                    break;
                }
            }
        }
        if (!z) {
            throw new RuntimeException("Could not find layer to remove");
        }
    }

    public final void i() {
        if (d() != null) {
            d().b(this);
        }
        a(true);
    }

    public final boolean j() {
        return this.f;
    }

    public final void b(boolean z) {
        this.f = true;
    }

    private boolean r() {
        return this.g;
    }

    public final void c(boolean z) {
        this.g = z;
    }

    public final void c(v vVar) {
        if (vVar.r()) {
            f(vVar);
        }
        if (!j()) {
            b(vVar);
        }
    }

    private void f(v vVar) {
        List<t> q = q();
        if (q.size() > 0) {
            w j = vVar.j();
            for (int i = 0; i < q.size(); i++) {
                t tVar = q.get(i);
                boolean B = tVar.f().a().B();
                if (tVar.r()) {
                    a(vVar, tVar);
                    if (!B && tVar.f().a().ad() && tVar.f().u(vVar)) {
                        com.d.i.c cVar = (com.d.i.c) tVar.f();
                        cVar.c(vVar);
                        cVar.e(true);
                        a(vVar, tVar);
                        if (cVar.u(vVar)) {
                            cVar.c(vVar);
                            a(vVar, tVar);
                        }
                    }
                    tVar.c(false);
                    tVar.c(vVar);
                    if (!B) {
                        vVar.p().c(vVar, tVar.f());
                    }
                }
            }
            vVar.a(j);
        }
    }

    private void g(v vVar) {
        if (f().a().A() && !vVar.r()) {
            ((com.d.i.c) f()).b(vVar, 3);
            return;
        }
        if (f().a().F()) {
            if (j() || ((com.d.i.c) f()).v()) {
                f().m(vVar);
                if (!j()) {
                    f().B();
                    f().C();
                }
            }
        }
    }

    public final List<com.d.i.aa> k() {
        if (this.h == null) {
            return this.f1160a == null ? Collections.emptyList() : this.f1160a.k();
        }
        return this.h;
    }

    private static void a(v vVar, t tVar) {
        com.d.i.c cVar = (com.d.i.c) tVar.f();
        if (tVar.f().a().W()) {
            cVar.b(vVar, 3);
            cVar.j(vVar);
            vVar.a(true);
            ((com.d.i.c) tVar.f()).b(vVar);
            cVar.b(vVar, 2);
            return;
        }
        vVar.a(true);
        cVar.b(vVar);
        com.d.i.g av = cVar.av();
        cVar.c(vVar);
        com.d.i.g av2 = cVar.av();
        cVar.a(av);
        cVar.b(vVar, 3);
        cVar.j(vVar);
        cVar.a(av2);
        vVar.a(true);
        ((com.d.i.c) tVar.f()).b(vVar);
    }

    public final void l() {
        if (this.h.remove(this.h.size() - 1) == t()) {
            a((com.d.i.aa) null);
        }
    }

    public final void b(com.d.c.f.d dVar) {
        String str;
        if (this.h == null) {
            this.h = new ArrayList();
        }
        List<com.d.i.aa> k = k();
        if (k.size() == 0) {
            str = "first";
        } else if (k.size() % 2 == 0) {
            str = "right";
        } else {
            str = "left";
        }
        com.d.i.aa a2 = a(dVar, str);
        if (k.size() == 0) {
            a2.a(dVar, 0);
        } else {
            a2.a(dVar, k.get(k.size() - 1).a());
        }
        a2.d(k.size());
        k.add(a2);
    }

    public final com.d.i.aa a(com.d.c.f.d dVar, com.d.i.f fVar) {
        return a(dVar, fVar.aa());
    }

    public final com.d.i.aa b(com.d.c.f.d dVar, com.d.i.f fVar) {
        return a(dVar, (fVar.aa() + fVar.as()) - 1);
    }

    public final void c(com.d.c.f.d dVar, com.d.i.f fVar) {
        b(dVar, fVar);
    }

    public final com.d.i.aa a(com.d.c.f.d dVar, int i) {
        List<com.d.i.aa> k = k();
        if (i < 0) {
            return null;
        }
        com.d.i.aa t = t();
        if (t != null && i >= t.b() && i < t.a()) {
            return t;
        }
        if (i < k.get(k.size() - 1).a()) {
            int size = k.size();
            for (int i2 = size - 1; i2 >= 0 && i2 >= size - 5; i2--) {
                com.d.i.aa aaVar = k.get(i2);
                if (i >= aaVar.b() && i < aaVar.a()) {
                    a(aaVar);
                    return aaVar;
                }
            }
            int i3 = 0;
            int i4 = size - 6;
            while (i3 <= i4) {
                int i5 = (i3 + i4) >> 1;
                com.d.i.aa aaVar2 = k.get(i5);
                if (i >= aaVar2.b() && i < aaVar2.a()) {
                    a(aaVar2);
                    return aaVar2;
                }
                if (aaVar2.b() < i) {
                    i3 = i5 + 1;
                } else {
                    i4 = i5 - 1;
                }
            }
            throw new RuntimeException("internal error");
        }
        b(dVar, i);
        com.d.i.aa aaVar3 = k.get(k.size() - 1);
        a(aaVar3);
        return aaVar3;
    }

    private void b(com.d.c.f.d dVar, int i) {
        List<com.d.i.aa> k = k();
        for (com.d.i.aa aaVar = k.get(k.size() - 1); i >= aaVar.a(); aaVar = k.get(k.size() - 1)) {
            b(dVar);
        }
    }

    public final void c(int i) {
        List<com.d.i.aa> k = k();
        for (int size = k.size() - 1; size > 0; size--) {
            com.d.i.aa aaVar = k.get(size);
            if (aaVar.b() >= i) {
                if (aaVar == t()) {
                    a((com.d.i.aa) null);
                }
                k.remove(size);
            } else {
                return;
            }
        }
    }

    public final void d(int i) {
        while (this.h.size() > i) {
            if (this.h.remove(this.h.size() - 1) == t()) {
                a((com.d.i.aa) null);
            }
        }
    }

    public final void a(com.d.c.f.d dVar, short s) {
        a(dVar, 2, 0);
    }

    private void a(com.d.c.f.d dVar, int i, int i2) {
        int i3 = 0;
        for (com.d.i.aa aaVar : k()) {
            aaVar.c(i3);
            if (i == 1) {
                aaVar.b(i3 + aaVar.b(dVar));
            } else if (i == 2) {
                aaVar.b(i3 + aaVar.c(dVar));
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            i3 = aaVar.c();
        }
    }

    public final com.d.i.aa m() {
        List<com.d.i.aa> k = k();
        if (k.size() == 0) {
            return null;
        }
        return k.get(k.size() - 1);
    }

    public final boolean a(v vVar, int i, int i2) {
        return i >= 0 && i2 >= a(vVar, i).a() - vVar.z();
    }

    public final void c(com.d.i.c cVar) {
        if (this.l == null) {
            this.l = new HashMap();
        }
        String Q = cVar.a().Q();
        List<com.d.i.c> list = this.l.get(Q);
        List<com.d.i.c> list2 = list;
        if (list == null) {
            list2 = new ArrayList();
            this.l.put(Q, list2);
        }
        list2.add(cVar);
        Collections.sort(list2, (cVar2, cVar3) -> {
            return cVar2.aa() - cVar3.aa();
        });
    }

    public final void d(com.d.i.c cVar) {
        if (this.l == null) {
            return;
        }
        List<com.d.i.c> list = this.l.get(cVar.a().Q());
        if (list == null) {
            return;
        }
        list.remove(cVar);
    }

    public final com.d.i.c a(String str, com.d.i.aa aaVar, com.d.c.a.e eVar) {
        List<com.d.i.c> list;
        if (this.l == null || (list = this.l.get(str)) == null) {
            return null;
        }
        if (eVar == com.d.c.a.e.f972a) {
            com.d.i.c cVar = null;
            for (com.d.i.c cVar2 : list) {
                if (cVar2.y().aa() >= aaVar.b()) {
                    break;
                }
                cVar = cVar2;
            }
            return cVar;
        }
        if (eVar == com.d.c.a.e.f973b) {
            for (com.d.i.c cVar3 : list) {
                int aa = cVar3.y().aa();
                if (aa >= aaVar.b() && aa < aaVar.a()) {
                    return cVar3;
                }
            }
            return a(str, aaVar, com.d.c.a.e.f972a);
        }
        if (eVar == com.d.c.a.e.c) {
            com.d.i.c cVar4 = null;
            for (com.d.i.c cVar5 : list) {
                if (cVar5.y().aa() > aaVar.a()) {
                    break;
                }
                cVar4 = cVar5;
            }
            return cVar4;
        }
        if (eVar == com.d.c.a.e.d) {
            com.d.i.c cVar6 = null;
            for (com.d.i.c cVar7 : list) {
                int aa2 = cVar7.y().aa();
                if (aa2 >= aaVar.b() && aa2 < aaVar.a()) {
                    return null;
                }
                if (aa2 > aaVar.a()) {
                    break;
                }
                cVar6 = cVar7;
            }
            return cVar6;
        }
        throw new RuntimeException("bug: internal error");
    }

    public final void d(v vVar) {
        vVar.a(vVar.p());
        Iterator<com.d.i.aa> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().a(vVar);
        }
    }

    public final void e(com.d.i.c cVar) {
        if (this.j == null) {
            this.j = new HashSet();
        }
        this.j.add(cVar);
    }

    private List<com.d.i.c> s() {
        if (this.j == null) {
            return null;
        }
        if (this.k == null) {
            ArrayList arrayList = new ArrayList(this.j);
            Collections.sort(arrayList, new u(this));
            this.k = arrayList;
        }
        return this.k;
    }

    public final int a(com.d.i.ab abVar, int i) {
        List<com.d.i.c> s = s();
        int i2 = 0;
        if (abVar.v() > 0) {
            i2 = abVar.v() - 1;
        }
        if (s == null || s.isEmpty()) {
            return i2 + a((com.d.c.f.d) abVar, i).i();
        }
        return a((com.d.c.f.d) abVar, i).i() - a((com.d.c.f.d) abVar, a(s, i).aa()).i();
    }

    private static com.d.i.c a(List list, int i) {
        com.d.i.c cVar = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            cVar = (com.d.i.c) list.get(i2);
            if (i2 < list.size() - 1 && ((com.d.i.c) list.get(i2 + 1)).aa() > i) {
                break;
            }
        }
        return cVar;
    }

    public final int c(com.d.i.ab abVar) {
        List<com.d.i.c> s = s();
        int i = 0;
        if (abVar.v() > 0) {
            i = abVar.v() - 1;
        }
        if (s == null) {
            return i + abVar.t();
        }
        int a2 = a(s, abVar.s());
        if (a2 == -1) {
            return i + abVar.t();
        }
        return abVar.t() - a((com.d.c.f.d) abVar, (com.d.i.f) s.get(a2)).i();
    }

    public final int d(com.d.i.ab abVar) {
        int i;
        int r;
        List<com.d.i.c> s = s();
        int i2 = 0;
        if (abVar.v() > 0) {
            i2 = abVar.v() - 1;
        }
        if (s == null) {
            return i2 + abVar.r();
        }
        int a2 = a(s, abVar.s());
        if (a2 == -1) {
            i = 0;
        } else {
            i = a((com.d.c.f.d) abVar, (com.d.i.f) s.get(a2)).i();
        }
        if (a2 < s.size() - 1) {
            r = a((com.d.c.f.d) abVar, (com.d.i.f) s.get(a2 + 1)).i();
        } else {
            r = abVar.r();
        }
        int i3 = r - i;
        if (a2 == -1) {
            i3 += i2;
        }
        return i3;
    }

    private static int a(List list, com.d.i.aa aaVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (((com.d.i.c) list.get(size)).aa() < aaVar.a() - 1) {
                return size;
            }
        }
        return -1;
    }

    private com.d.i.aa t() {
        return this.i;
    }

    private void a(com.d.i.aa aaVar) {
        this.i = aaVar;
    }
}
