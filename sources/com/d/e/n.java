package com.d.e;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/e/n.class */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    private List<b> f1147a = Collections.emptyList();

    /* renamed from: b, reason: collision with root package name */
    private List<b> f1148b = Collections.emptyList();
    private final com.d.i.f c;

    /* loaded from: infinitode-2.jar:com/d/e/n$c.class */
    public interface c {
        void a(com.d.i.f fVar);
    }

    public n(com.d.i.f fVar) {
        this.c = fVar;
    }

    private List<b> a(int i) {
        if (b(i).isEmpty()) {
            a(i, new ArrayList());
        }
        return b(i);
    }

    private void a(int i, List<b> list) {
        if (i == 1) {
            this.f1147a = list;
        } else {
            this.f1148b = list;
        }
    }

    public final void a(v vVar, t tVar, com.d.e.b bVar, com.d.i.c cVar) {
        if (cVar.a().D()) {
            a(vVar, bVar, cVar, 1);
            a(cVar, tVar, bVar, 1);
        } else if (cVar.a().E()) {
            a(vVar, bVar, cVar, 2);
            a(cVar, tVar, bVar, 2);
        }
    }

    public final void a(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.f fVar) {
        if (fVar.a().k()) {
            b(dVar, bVar, fVar, b(1));
        }
        if (fVar.a().l()) {
            b(dVar, bVar, fVar, b(2));
        }
    }

    private void a(com.d.i.c cVar, t tVar, com.d.e.b bVar, int i) {
        Point a2 = bVar.a();
        a(i).add(new b(cVar, a2.x, a2.y));
        tVar.b(cVar);
        cVar.L().a(this);
        cVar.B();
        cVar.C();
    }

    private void a(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.c cVar, int i) {
        a(cVar, i);
        c(dVar, bVar, cVar, i);
        b(dVar, bVar, cVar, i);
        if (!c(cVar) || a(dVar, bVar, cVar, b(i))) {
            a(cVar, i);
            a(dVar, bVar, (com.d.i.f) cVar, b(i));
        }
        if (a(dVar, bVar, cVar, c(i))) {
            a(cVar, i);
            a(dVar, bVar, (com.d.i.f) cVar, b(i));
            a(dVar, bVar, (com.d.i.f) cVar, c(i));
        }
        if (cVar.a().m()) {
            if (cVar.a().k() && i == 1) {
                a(cVar, 1);
            } else if (cVar.a().l() && i == 2) {
                a(cVar, 2);
            }
            a(dVar, bVar, (com.d.i.f) cVar, b(i));
        }
    }

    private List<b> b(int i) {
        return i == 1 ? this.f1147a : this.f1148b;
    }

    private List<b> c(int i) {
        return i == 1 ? this.f1148b : this.f1147a;
    }

    private void b(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.c cVar, int i) {
        List<b> b2 = b(i);
        if (b2.size() > 0) {
            Point a2 = bVar.a();
            b bVar2 = b2.get(b2.size() - 1);
            com.d.i.c a3 = bVar2.a();
            Rectangle a4 = cVar.a(dVar, -a2.x, -a2.y);
            Rectangle a5 = a3.a(dVar, -bVar2.b(), -bVar2.c());
            boolean z = false;
            if (a4.y < a5.y) {
                a4.translate(0, a5.y - a4.y);
                z = true;
            }
            if (a4.y >= a5.y && a4.y < a5.y + a5.height) {
                z = true;
            }
            if (z) {
                if (i == 1) {
                    a4.x = a5.x + a3.Q();
                } else if (i == 2) {
                    a4.x = a5.x - cVar.Q();
                }
                a4.translate(a2.x, a2.y);
                cVar.n(a4.x);
                cVar.o(a4.y);
            }
        }
    }

    private void c(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.c cVar, int i) {
        List<b> c2 = c(i);
        if (c2.size() > 0) {
            Point a2 = bVar.a();
            b bVar2 = c2.get(c2.size() - 1);
            Rectangle a3 = cVar.a(dVar, -a2.x, -a2.y);
            Rectangle a4 = bVar2.a().a(dVar, -bVar2.b(), -bVar2.c());
            if (a3.y < a4.y) {
                a3.translate(0, a4.y - a3.y);
                a3.translate(a2.x, a2.y);
                cVar.o(a3.y);
            }
        }
    }

    private static void a(com.d.i.c cVar, int i) {
        if (i == 1) {
            cVar.n(0);
        } else if (i == 2) {
            cVar.n(cVar.Y().d_() - cVar.Q());
        }
    }

    private static boolean c(com.d.i.c cVar) {
        return cVar.am() >= 0 && cVar.am() + cVar.Q() <= cVar.Y().d_();
    }

    private static int a(com.d.c.f.d dVar, List<b> list) {
        int i = 0;
        for (b bVar : list) {
            Rectangle a2 = bVar.a().a(dVar, -bVar.b(), -bVar.c());
            if (a2.y + a2.height > i) {
                i = a2.y + a2.height;
            }
        }
        return i;
    }

    public final int a(com.d.c.f.d dVar, int i) {
        return Math.max(a(dVar, b(1)), a(dVar, b(2))) - i;
    }

    private static boolean a(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.c cVar, List<b> list) {
        Point a2 = bVar.a();
        Rectangle a3 = cVar.a(dVar, -a2.x, -a2.y);
        for (b bVar2 : list) {
            if (bVar2.a().a(dVar, -bVar2.b(), -bVar2.c()).intersects(a3)) {
                return true;
            }
        }
        return false;
    }

    private void a(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.f fVar, List<b> list) {
        if (list.size() == 0) {
            return;
        }
        int an = fVar.an() - bVar.a().y;
        int a2 = a(dVar, list);
        if (a2 - an > 0) {
            fVar.o(fVar.an() + (a2 - an));
        }
    }

    private void b(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.f fVar, List<b> list) {
        if (list.size() == 0) {
            return;
        }
        Point a2 = bVar.a();
        Rectangle a3 = fVar.a(fVar.am() - a2.x, fVar.an() - a2.y, dVar);
        int a4 = a(dVar, list);
        if (a3.y < a4) {
            a3.y = a4;
            a3.translate(a2.x, a2.y);
            fVar.o(a3.y - ((int) fVar.n(dVar).t()));
        }
    }

    public final void a(com.d.i.c cVar) {
        a(cVar, b(1));
        a(cVar, b(2));
    }

    private static void a(com.d.i.c cVar, List<b> list) {
        Iterator<b> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().a().equals(cVar)) {
                it.remove();
                cVar.L().a((n) null);
            }
        }
    }

    public final void a() {
        a(b(1));
        a(b(2));
    }

    private static void a(List<b> list) {
        for (b bVar : list) {
            bVar.a().B();
            bVar.a().C();
        }
    }

    private static void a(com.d.c.f.d dVar, com.d.i.f fVar, Rectangle rectangle) {
        if (fVar.as() == 0) {
            rectangle.height = (int) fVar.a().c(dVar);
        }
    }

    public final int a(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.u uVar, int i) {
        int i2;
        int i3;
        a a2 = a(dVar, bVar, uVar, i, b(1), 1);
        a a3 = a(dVar, bVar, uVar, i, b(2), 2);
        if (a2.a() != null) {
            i2 = a(dVar, uVar, a2);
        } else {
            i2 = 0;
        }
        if (a3.a() != null) {
            i3 = a(dVar, uVar, a3);
        } else {
            i3 = 0;
        }
        return Math.max(i2, i3);
    }

    private static int a(com.d.c.f.d dVar, com.d.i.u uVar, a aVar) {
        com.d.i.c a2 = aVar.a();
        Rectangle a3 = a2.a(a2.ab(), a2.aa(), dVar);
        return (a3.y + a3.height) - uVar.aa();
    }

    public final int b(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.u uVar, int i) {
        return a(dVar, bVar, uVar, i, b(1), 1).b();
    }

    public final int c(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.u uVar, int i) {
        return a(dVar, bVar, uVar, i, b(2), 2).b();
    }

    private a a(com.d.c.f.d dVar, com.d.e.b bVar, com.d.i.u uVar, int i, List<b> list, int i2) {
        if (list.size() == 0) {
            return new a(null, 0);
        }
        Point a2 = bVar.a();
        Rectangle a3 = uVar.a(dVar, -a2.x, -a2.y);
        a3.width = i;
        int i3 = i2 == 1 ? a3.x : a3.x + a3.width;
        a(dVar, uVar, a3);
        com.d.i.c cVar = null;
        for (int i4 = 0; i4 < list.size(); i4++) {
            b bVar2 = list.get(i4);
            Rectangle a4 = bVar2.a().a(dVar, -bVar2.b(), -bVar2.c());
            if (a3.intersects(a4)) {
                if (i2 == 1 && a4.x + a4.width > i3) {
                    i3 = a4.x + a4.width;
                } else if (i2 == 2 && a4.x < i3) {
                    i3 = a4.x;
                }
                cVar = bVar2.a();
            }
        }
        if (i2 == 1) {
            return new a(cVar, i3 - a3.x);
        }
        return new a(cVar, (a3.x + a3.width) - i3);
    }

    public final com.d.i.f b() {
        return this.c;
    }

    public final Point b(com.d.i.c cVar) {
        return b(cVar, cVar.a().D() ? b(1) : b(2));
    }

    private static Point b(com.d.i.c cVar, List<b> list) {
        for (b bVar : list) {
            if (bVar.a().equals(cVar)) {
                return new Point(bVar.b(), bVar.c());
            }
        }
        return null;
    }

    private void a(c cVar, List<b> list) {
        for (b bVar : list) {
            com.d.i.c a2 = bVar.a();
            a2.m((a2.am() + b().ab()) - bVar.b());
            a2.l((a2.an() + b().aa()) - bVar.c());
            cVar.a(a2);
        }
    }

    public final void a(c cVar) {
        a(cVar, b(1));
        a(cVar, b(2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/n$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private com.d.i.c f1151a;

        /* renamed from: b, reason: collision with root package name */
        private int f1152b;
        private int c;

        public b(com.d.i.c cVar, int i, int i2) {
            this.f1151a = cVar;
            this.f1152b = i;
            this.c = i2;
        }

        public final com.d.i.c a() {
            return this.f1151a;
        }

        public final int b() {
            return this.f1152b;
        }

        public final int c() {
            return this.c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/n$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private com.d.i.c f1149a;

        /* renamed from: b, reason: collision with root package name */
        private int f1150b;

        public a(com.d.i.c cVar, int i) {
            this.f1149a = cVar;
            this.f1150b = i;
        }

        final com.d.i.c a() {
            return this.f1149a;
        }

        final int b() {
            return this.f1150b;
        }
    }
}
