package com.d.i.a;

import com.d.e.t;
import com.d.i.aa;
import com.d.i.ab;
import com.d.i.u;
import com.d.i.y;
import com.d.i.z;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/a/e.class */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private final List<h> f1289a;

    /* renamed from: b, reason: collision with root package name */
    private final List<aa> f1290b;
    private final f c;
    private final int d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/a/e$d.class */
    public interface d {
        int a();

        boolean a(e eVar, h hVar, com.d.i.f fVar, Shape shape, t tVar);
    }

    /* loaded from: infinitode-2.jar:com/d/i/a/e$h.class */
    public static class h {

        /* renamed from: a, reason: collision with root package name */
        private List<com.d.d.b> f1300a = null;

        /* renamed from: b, reason: collision with root package name */
        private List<com.d.d.b> f1301b = null;
        private List<com.d.f.f> c = null;
        private List<com.d.d.b> d = null;
        private List<com.d.d.b> e = null;
        private List<com.d.i.c> f = null;
        private List<h> g = null;
        private boolean h = false;
        private boolean i = false;
        private Rectangle j = null;
        private Rectangle k = null;

        /* JADX INFO: Access modifiers changed from: private */
        public void b(h hVar) {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(hVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(com.d.i.c cVar) {
            if (this.f == null) {
                this.f = new ArrayList();
            }
            this.f.add(cVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(com.d.d.b bVar) {
            if (this.f1300a == null) {
                this.f1300a = new ArrayList();
            }
            this.f1300a.add(bVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(com.d.d.b bVar) {
            if (this.f1301b == null) {
                this.f1301b = new ArrayList();
            }
            this.f1301b.add(bVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(com.d.f.f fVar) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(fVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(com.d.d.b bVar) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(bVar);
            if (!(bVar instanceof y) && !(bVar instanceof z)) {
                this.i = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(com.d.d.b bVar) {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            this.e.add(bVar);
            if (!(bVar instanceof y) && !(bVar instanceof z)) {
                this.h = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(y yVar) {
            a((com.d.d.b) yVar);
            b(yVar);
            c(yVar);
            d(yVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(z zVar) {
            a((com.d.d.b) zVar);
            b(zVar);
            c(zVar);
            d(zVar);
        }

        public final List<com.d.i.c> a() {
            return this.f == null ? Collections.emptyList() : this.f;
        }

        public final List<com.d.d.b> b() {
            return this.f1300a == null ? Collections.emptyList() : this.f1300a;
        }

        public final List<com.d.d.b> c() {
            return this.f1301b == null ? Collections.emptyList() : this.f1301b;
        }

        public final List<com.d.f.f> d() {
            return this.c == null ? Collections.emptyList() : this.c;
        }

        public final List<com.d.d.b> e() {
            return this.i ? this.d : Collections.emptyList();
        }

        public final List<com.d.d.b> f() {
            return this.h ? this.e : Collections.emptyList();
        }

        public final List<h> g() {
            return this.g == null ? Collections.emptyList() : this.g;
        }

        public final boolean a(int i) {
            return this.g != null && i < this.g.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Rectangle a(aa aaVar, com.d.c.f.d dVar) {
            if (this.j == null) {
                this.j = aaVar.e(dVar);
            }
            return this.j;
        }

        public final Rectangle a(aa aaVar, com.d.c.f.d dVar, int i) {
            if (i == 0 && this.k == null) {
                this.k = aaVar.b(dVar, i);
                return this.k;
            }
            if (i == 0) {
                return this.k;
            }
            return aaVar.b(dVar, i);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/a/e$f.class */
    public static class f {

        /* renamed from: a, reason: collision with root package name */
        private int f1296a = 0;

        /* renamed from: b, reason: collision with root package name */
        private final List<aa> f1297b;

        public f(List<aa> list) {
            this.f1297b = list;
        }

        public final int a(com.d.c.f.d dVar, int i) {
            int a2 = a(i);
            if (a2 == -1) {
                return 0;
            }
            if (a2 == -2) {
                return this.f1297b.size() - 1;
            }
            return a2;
        }

        private int a(int i) {
            if (i < 0) {
                return -1;
            }
            aa aaVar = this.f1297b.get(this.f1296a);
            if (i >= aaVar.b() && i < aaVar.a()) {
                return this.f1296a;
            }
            aa aaVar2 = this.f1297b.get(this.f1297b.size() - 1);
            if (i >= aaVar2.b() && i < aaVar2.a()) {
                this.f1296a = this.f1297b.size() - 1;
                return this.f1296a;
            }
            if (i < aaVar2.a()) {
                int size = this.f1297b.size();
                for (int i2 = size - 1; i2 >= 0 && i2 >= size - 5; i2--) {
                    aa aaVar3 = this.f1297b.get(i2);
                    if (i >= aaVar3.b() && i < aaVar3.a()) {
                        this.f1296a = i2;
                        return i2;
                    }
                }
                int i3 = 0;
                int i4 = size - 6;
                while (i3 <= i4) {
                    int i5 = (i3 + i4) >> 1;
                    aa aaVar4 = this.f1297b.get(i5);
                    if (i >= aaVar4.b() && i < aaVar4.a()) {
                        this.f1296a = i5;
                        return i5;
                    }
                    if (aaVar4.b() < i) {
                        i3 = i5 + 1;
                    } else {
                        i4 = i5 - 1;
                    }
                }
                return -1;
            }
            return -2;
        }
    }

    public e(List<aa> list, int i, int i2) {
        this.f1290b = list;
        this.f1289a = new ArrayList((i2 - i) + 1);
        this.c = new f(list);
        this.d = i;
        for (int i3 = i; i3 <= i2; i3++) {
            this.f1289a.add(new h());
        }
    }

    public final void a(com.d.c.f.d dVar, t tVar) {
        if (tVar.j()) {
            c(dVar, tVar);
        } else {
            a(dVar, tVar, tVar.f(), -2);
        }
    }

    private void c(com.d.c.f.d dVar, t tVar) {
        for (com.d.i.f fVar : ((com.d.i.r) tVar.f()).l()) {
            int a2 = a(dVar, fVar, tVar.a());
            int b2 = b(dVar, fVar, tVar.a());
            for (int i = a2; i <= b2; i++) {
                Shape a3 = a(i).a(d(i), dVar);
                if (fVar.a(dVar, a3)) {
                    if (fVar instanceof com.d.i.r) {
                        a(i).b(fVar);
                    } else {
                        com.d.i.c cVar = (com.d.i.c) fVar;
                        if (cVar.v()) {
                            if (a(dVar, a3, fVar, fVar)) {
                                a(i).b(fVar);
                            }
                        } else {
                            a(dVar, tVar, cVar, -2);
                        }
                    }
                }
            }
        }
    }

    public final void b(com.d.c.f.d dVar, t tVar) {
        for (int size = tVar.g().size() - 1; size >= 0; size--) {
            com.d.i.c cVar = tVar.g().get(size);
            int a2 = a(dVar, cVar, tVar.a());
            int b2 = b(dVar, cVar, tVar.a());
            for (int b3 = b(a2); b3 <= c(b2); b3++) {
                h a3 = a(b3);
                aa d2 = d(b3);
                if (a(dVar, a3.a(d2, dVar), cVar)) {
                    a3.a(cVar);
                }
                if (d2.e()) {
                    a(dVar, cVar, b3, a3, (Shape) null, (List<h>) null, tVar, b.f1292a);
                }
            }
        }
    }

    private void a(com.d.c.f.d dVar, t tVar, com.d.i.f fVar, int i) {
        int a2;
        int b2;
        if (fVar instanceof com.d.i.c) {
            Rectangle r = fVar.r(dVar);
            a2 = a(dVar, r, tVar.a());
            b2 = b(dVar, r, tVar.a());
        } else {
            a2 = a(dVar, fVar, tVar.a());
            b2 = b(dVar, fVar, tVar.a());
        }
        a(dVar, tVar, fVar, a2, b2, i);
    }

    public final void a(com.d.c.f.d dVar, t tVar, com.d.i.f fVar, int i, int i2, int i3) {
        if (tVar != fVar.af()) {
            return;
        }
        if (fVar instanceof u) {
            for (int b2 = b(i); b2 <= c(i2); b2++) {
                if (i3 == -2) {
                    a(dVar, tVar, (u) fVar, b2, true);
                } else if (i3 == -1) {
                    a(dVar, tVar, (u) fVar, b2, false);
                } else {
                    a(dVar, tVar, (u) fVar, b2, i3);
                }
            }
            return;
        }
        Rectangle rectangle = null;
        ArrayList arrayList = null;
        if (fVar.Z() == null || tVar.f() == fVar || !(fVar instanceof com.d.i.c)) {
            if ((dVar instanceof ab) && (fVar instanceof com.d.i.c)) {
                com.d.i.c cVar = (com.d.i.c) fVar;
                if (cVar.P()) {
                    rectangle = cVar.k((ab) dVar);
                    arrayList = new ArrayList();
                }
            }
            if (i3 == -2) {
                a(dVar, tVar, fVar, i, i2, (Shape) rectangle, (List<h>) arrayList, true);
            } else if (i3 == -1) {
                a(dVar, tVar, fVar, i, i2, (Shape) rectangle, (List<h>) arrayList, false);
            } else {
                a(dVar, fVar, i, i2, rectangle, arrayList, i3);
            }
        }
        if ((fVar instanceof com.d.f.j) && ((((com.d.f.j) fVar).q() || ((com.d.f.j) fVar).p()) && ((com.d.f.j) fVar).f().m() && ((fVar.Z() == null || fVar == tVar.f()) && (dVar instanceof ab)))) {
            b(dVar, tVar, fVar, i3);
        } else if (fVar.Z() == null || fVar == tVar.f()) {
            for (int i4 = 0; i4 < fVar.V(); i4++) {
                a(dVar, tVar, fVar.k(i4), i3);
            }
        }
        if (arrayList != null) {
            Iterator<h> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a(new z(null));
            }
        }
    }

    private void a(com.d.c.f.d dVar, t tVar, com.d.i.f fVar, int i, int i2, Shape shape, List<h> list, boolean z) {
        for (int b2 = b(i); b2 <= c(i2); b2++) {
            h a2 = a(b2);
            aa d2 = d(b2);
            if (b(dVar, (Shape) a2.a(d2, dVar), fVar)) {
                a(fVar, a2);
                if (shape != null) {
                    a2.a(new y(shape));
                    list.add(a2);
                }
            }
            if (z && d2.e()) {
                a(dVar, fVar, b2, a2, shape, list, tVar, a.f1291a);
            }
        }
    }

    private void a(com.d.c.f.d dVar, com.d.i.f fVar, int i, int i2, Shape shape, List<h> list, int i3) {
        for (int b2 = b(i); b2 <= c(i2); b2++) {
            h a2 = a(b2);
            if (b(dVar, (Shape) a2.a(d(b2), dVar, i3), fVar)) {
                h a3 = a(a2, i3);
                a(fVar, a3);
                if (shape != null) {
                    a3.a(new y(shape));
                    list.add(a3);
                }
            }
        }
    }

    private void a(com.d.c.f.d dVar, t tVar, u uVar, int i, int i2) {
        h a2 = a(i);
        if (a(dVar, (Shape) a2.a(d(i), dVar, i2), (com.d.i.f) uVar)) {
            h a3 = a(a2, i2);
            a3.b(uVar);
            uVar.a(a3.f1301b, tVar);
        }
    }

    private void a(com.d.c.f.d dVar, t tVar, u uVar, int i, boolean z) {
        h a2 = a(i);
        aa d2 = d(i);
        if (a(dVar, (Shape) a2.a(d2, dVar), (com.d.i.f) uVar)) {
            a2.b(uVar);
            uVar.a(a2.f1301b, tVar);
        }
        if (z && d2.e()) {
            a(dVar, uVar, i, a2, (Shape) null, (List<h>) null, tVar, c.f1293a);
        }
    }

    private static h a(h hVar, int i) {
        while (i >= hVar.g().size()) {
            hVar.b(new h());
        }
        return hVar.g().get(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/a/e$a.class */
    public static class a implements d {

        /* renamed from: a, reason: collision with root package name */
        private static final d f1291a = new a();

        private a() {
        }

        @Override // com.d.i.a.e.d
        public final int a() {
            return 1;
        }

        @Override // com.d.i.a.e.d
        public final boolean a(e eVar, h hVar, com.d.i.f fVar, Shape shape, t tVar) {
            e.a(fVar, hVar);
            if (shape != null) {
                hVar.a(new y(shape));
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/a/e$c.class */
    public static class c implements d {

        /* renamed from: a, reason: collision with root package name */
        private static final d f1293a = new c();

        private c() {
        }

        @Override // com.d.i.a.e.d
        public final int a() {
            return 2;
        }

        @Override // com.d.i.a.e.d
        public final boolean a(e eVar, h hVar, com.d.i.f fVar, Shape shape, t tVar) {
            hVar.b(fVar);
            ((u) fVar).a(hVar.f1301b, tVar);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/a/e$b.class */
    public static class b implements d {

        /* renamed from: a, reason: collision with root package name */
        private static final d f1292a = new b();

        private b() {
        }

        @Override // com.d.i.a.e.d
        public final int a() {
            return 1;
        }

        @Override // com.d.i.a.e.d
        public final boolean a(e eVar, h hVar, com.d.i.f fVar, Shape shape, t tVar) {
            hVar.a((com.d.i.c) fVar);
            return false;
        }
    }

    private void a(com.d.c.f.d dVar, com.d.i.f fVar, int i, h hVar, Shape shape, List<h> list, t tVar, d dVar2) {
        int min;
        boolean b2;
        aa d2 = d(i);
        AffineTransform a2 = fVar.af().a();
        Rectangle r = fVar.r(dVar);
        C0026e a3 = a2 == null ? null : a(r, a2);
        if (d2.g() == com.d.c.a.c.ak) {
            min = Math.min(d2.f(), d2.c(dVar, (int) (a2 == null ? r.getMaxX() : d(a3))));
        } else {
            min = Math.min(d2.f(), d2.c(dVar, (int) (a2 == null ? r.getMinX() : b(a3))));
        }
        for (int i2 = 0; i2 < min; i2++) {
            Rectangle a4 = hVar.a(d2, dVar, i2);
            if (dVar2.a() == 2) {
                b2 = a(dVar, (Shape) a4, fVar);
            } else {
                b2 = b(dVar, (Shape) a4, fVar);
            }
            if (b2) {
                h a5 = a(hVar, i2);
                if (dVar2.a(this, a5, fVar, shape, tVar)) {
                    list.add(a5);
                }
            }
        }
    }

    private void b(com.d.c.f.d dVar, t tVar, com.d.i.f fVar, int i) {
        com.d.f.d f2 = ((com.d.f.j) fVar).f();
        ab abVar = (ab) dVar;
        int a2 = a(dVar, f2, tVar.a());
        int b2 = b(dVar, f2, tVar.a());
        for (int b3 = b(a2); b3 <= c(b2); b3++) {
            abVar.a(b3, d(b3));
            f2.d(abVar);
            for (int i2 = 0; i2 < fVar.V(); i2++) {
                a(dVar, tVar, fVar.k(i2), i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(com.d.i.f fVar, h hVar) {
        hVar.a(fVar);
        if (fVar instanceof com.d.i.c) {
            com.d.i.c cVar = (com.d.i.c) fVar;
            if (cVar.E() != null) {
                hVar.c(cVar);
            }
            if (cVar.u()) {
                hVar.d(cVar);
            }
        }
        if ((fVar instanceof com.d.f.f) && ((com.d.f.f) fVar).o()) {
            hVar.a((com.d.f.f) fVar);
        }
    }

    private boolean a(com.d.c.f.d dVar, Shape shape, com.d.i.f fVar) {
        if (shape == null) {
            return true;
        }
        com.d.e.y at = fVar.at();
        if (at == null) {
            return false;
        }
        return a(dVar, shape, fVar, at.a());
    }

    private boolean b(com.d.c.f.d dVar, Shape shape, com.d.i.f fVar) {
        return a(dVar, shape, fVar, fVar.r(dVar));
    }

    private static boolean a(com.d.c.f.d dVar, Shape shape, com.d.i.f fVar, Rectangle rectangle) {
        AffineTransform a2 = fVar.af().a();
        Area i = fVar.i(dVar);
        if (a2 == null && i == null) {
            return shape.intersects(rectangle);
        }
        if (a2 != null && i == null) {
            Area area = new Area(a2.createTransformedShape(rectangle));
            area.intersect(new Area(shape));
            return !area.isEmpty();
        }
        Area area2 = new Area(a2 == null ? rectangle : a2.createTransformedShape(rectangle));
        area2.intersect(i);
        area2.intersect(new Area(shape));
        return !area2.isEmpty();
    }

    private boolean a(com.d.c.f.d dVar, Shape shape, com.d.i.f fVar, com.d.i.f fVar2) {
        if (fVar2 instanceof u) {
            if (fVar2.a(dVar, shape)) {
                return true;
            }
            return false;
        }
        if ((fVar2.Z() == null || !(fVar2 instanceof com.d.i.c)) && fVar2.a(dVar, shape)) {
            return true;
        }
        if (fVar2.Z() == null || fVar2 == fVar) {
            for (int i = 0; i < fVar2.V(); i++) {
                if (a(dVar, shape, fVar, fVar2.k(i))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.d.i.a.e$e, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/i/a/e$e.class */
    public static class C0026e {

        /* renamed from: a, reason: collision with root package name */
        private final Point2D f1294a;

        /* renamed from: b, reason: collision with root package name */
        private final Point2D f1295b;
        private final Point2D c;
        private final Point2D d;

        /* synthetic */ C0026e(Point2D point2D, Point2D point2D2, Point2D point2D3, Point2D point2D4, byte b2) {
            this(point2D, point2D2, point2D3, point2D4);
        }

        private C0026e(Point2D point2D, Point2D point2D2, Point2D point2D3, Point2D point2D4) {
            this.f1294a = point2D;
            this.f1295b = point2D2;
            this.c = point2D3;
            this.d = point2D4;
        }
    }

    private static C0026e a(Rectangle rectangle, AffineTransform affineTransform) {
        return new C0026e(affineTransform.transform(new Point2D.Double(rectangle.getMinX(), rectangle.getMinY()), (Point2D) null), affineTransform.transform(new Point2D.Double(rectangle.getMaxX(), rectangle.getMinY()), (Point2D) null), affineTransform.transform(new Point2D.Double(rectangle.getMinX(), rectangle.getMaxY()), (Point2D) null), affineTransform.transform(new Point2D.Double(rectangle.getMaxX(), rectangle.getMaxY()), (Point2D) null), (byte) 0);
    }

    private static double b(Rectangle rectangle, AffineTransform affineTransform) {
        C0026e a2 = a(rectangle, affineTransform);
        return Math.min(a2.d.getY(), Math.min(a2.c.getY(), Math.min(a2.f1294a.getY(), a2.f1295b.getY())));
    }

    private static double c(Rectangle rectangle, AffineTransform affineTransform) {
        C0026e a2 = a(rectangle, affineTransform);
        return Math.max(a2.d.getY(), Math.max(a2.c.getY(), Math.max(a2.f1294a.getY(), a2.f1295b.getY())));
    }

    private int a(com.d.c.f.d dVar, Rectangle rectangle, AffineTransform affineTransform) {
        return this.c.a(dVar, (int) (affineTransform == null ? rectangle.getMinY() : b(rectangle, affineTransform)));
    }

    private int b(com.d.c.f.d dVar, Rectangle rectangle, AffineTransform affineTransform) {
        return this.c.a(dVar, (int) (affineTransform == null ? rectangle.getMaxY() : c(rectangle, affineTransform)));
    }

    private int a(com.d.c.f.d dVar, com.d.i.f fVar, AffineTransform affineTransform) {
        Rectangle a2;
        com.d.e.y c2 = fVar.c(dVar, true);
        if (c2 == null || (a2 = c2.a()) == null) {
            return -1;
        }
        return this.c.a(dVar, (int) (affineTransform == null ? a2.getMinY() : b(a2, affineTransform)));
    }

    private int b(com.d.c.f.d dVar, com.d.i.f fVar, AffineTransform affineTransform) {
        Rectangle a2;
        com.d.e.y c2 = fVar.c(dVar, true);
        if (c2 == null || (a2 = c2.a()) == null) {
            return -1;
        }
        return this.c.a(dVar, (int) (affineTransform == null ? a2.getMaxY() : c(a2, affineTransform)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final h a(int i) {
        return this.f1289a.get(i - this.d);
    }

    private int a() {
        return (this.d + this.f1289a.size()) - 1;
    }

    private int b() {
        return this.d;
    }

    private int b(int i) {
        return Math.max(i, b());
    }

    private int c(int i) {
        return Math.min(i, a());
    }

    private aa d(int i) {
        return this.f1290b.get(i);
    }

    private static Rectangle a(com.d.c.f.d dVar, com.d.i.f fVar) {
        return fVar.c(dVar, true).a();
    }

    private static Rectangle d(com.d.c.f.d dVar, t tVar) {
        Rectangle a2 = a(dVar, tVar.f());
        Iterator<com.d.i.c> it = tVar.g().iterator();
        while (it.hasNext()) {
            a2.add(a(dVar, it.next()));
        }
        return a2;
    }

    private static double a(C0026e c0026e) {
        return Math.min(c0026e.d.getY(), Math.min(c0026e.c.getY(), Math.min(c0026e.f1294a.getY(), c0026e.f1295b.getY())));
    }

    private static double b(C0026e c0026e) {
        return Math.min(c0026e.d.getX(), Math.min(c0026e.c.getX(), Math.min(c0026e.f1294a.getX(), c0026e.f1295b.getX())));
    }

    private static double c(C0026e c0026e) {
        return Math.max(c0026e.d.getY(), Math.max(c0026e.c.getY(), Math.max(c0026e.f1294a.getY(), c0026e.f1295b.getY())));
    }

    private static double d(C0026e c0026e) {
        return Math.max(c0026e.d.getX(), Math.max(c0026e.c.getX(), Math.max(c0026e.f1294a.getX(), c0026e.f1295b.getX())));
    }

    /* loaded from: infinitode-2.jar:com/d/i/a/e$g.class */
    public static class g {

        /* renamed from: a, reason: collision with root package name */
        public final int f1298a;

        /* renamed from: b, reason: collision with root package name */
        public final int f1299b;

        /* synthetic */ g(int i, int i2, byte b2) {
            this(i, i2);
        }

        private g(int i, int i2) {
            this.f1298a = i;
            this.f1299b = i2;
        }
    }

    private static void d(Rectangle rectangle, AffineTransform affineTransform) {
        if (affineTransform != null) {
            C0026e a2 = a(rectangle, affineTransform);
            double b2 = b(a2);
            double a3 = a(a2);
            rectangle.setBounds((int) b2, (int) a3, (int) (d(a2) - b2), (int) (c(a2) - a3));
        }
    }

    private static Rectangle a(Rectangle rectangle, Area area) {
        if (area != null) {
            Area area2 = new Area(rectangle);
            area2.intersect(area);
            return area2.getBounds();
        }
        return rectangle;
    }

    public static List<g> a(com.d.c.f.d dVar, t tVar, List<aa> list) {
        f fVar = new f(list);
        Rectangle d2 = d(dVar, tVar);
        com.d.i.f f2 = tVar.f();
        AffineTransform a2 = f2.af().a();
        Area i = f2.i(dVar);
        d(d2, a2);
        Rectangle a3 = a(d2, i);
        int a4 = fVar.a(dVar, (int) a3.getMinY());
        int a5 = fVar.a(dVar, (int) a3.getMaxY());
        ArrayList arrayList = new ArrayList();
        for (int i2 = a4; i2 <= a5; i2++) {
            arrayList.add(new g(i2, -1, (byte) 0));
            if (list.get(i2).e()) {
                int min = Math.min(Math.max(list.get(i2).c(dVar, (int) a3.getMaxX()), list.get(i2).c(dVar, (int) a3.getMinX())), list.get(i2).f());
                for (int i3 = 0; i3 < min; i3++) {
                    arrayList.add(new g(i2, i3, (byte) 0));
                }
            }
        }
        return arrayList;
    }

    public static int a(com.d.c.f.d dVar, com.d.i.f fVar, List<aa> list) {
        f fVar2 = new f(list);
        Rectangle a2 = fVar.c(dVar, true).a();
        AffineTransform a3 = fVar.af().a();
        return fVar2.a(dVar, (int) (a3 == null ? a2.getMinY() : b(a2, a3)));
    }

    public static int b(com.d.c.f.d dVar, com.d.i.f fVar, List<aa> list) {
        f fVar2 = new f(list);
        Rectangle a2 = fVar.c(dVar, true).a();
        AffineTransform a3 = fVar.af().a();
        return fVar2.a(dVar, (int) (a3 == null ? a2.getMaxY() : c(a2, a3)));
    }
}
