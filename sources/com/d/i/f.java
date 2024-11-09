package com.d.i;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/i/f.class */
public abstract class f implements com.d.d.b, com.d.e.ac {

    /* renamed from: a, reason: collision with root package name */
    private Element f1350a;

    /* renamed from: b, reason: collision with root package name */
    private int f1351b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int i;
    private com.d.e.t k;
    private f l;
    private List<f> m;
    private int n;
    private int o;
    private com.d.c.f.c p;
    private f q;
    private Dimension r;
    private com.d.e.y s;
    private com.d.c.f.a.h t;
    private int u;
    private String v;
    private boolean w;
    private Area x;
    private int g = 0;
    private int h = 0;
    private com.d.e.t j = null;
    private boolean y = false;

    public abstract void B();

    static {
        System.getProperty("line.separator");
    }

    private Rectangle b(ab abVar, com.d.e.t tVar) {
        return c(abVar, tVar);
    }

    private f c() {
        if (a() != null && a().G()) {
            return Y();
        }
        if ((this instanceof c) && ((c) this).K()) {
            return Y();
        }
        return U();
    }

    public final Rectangle a(ab abVar, com.d.e.t tVar) {
        f c = c();
        if (c == null || c.af() != tVar) {
            return null;
        }
        return c.b(abVar, tVar);
    }

    private Rectangle c(ab abVar, com.d.e.t tVar) {
        if (af() != tVar) {
            return null;
        }
        if (a() != null && a().a(com.d.c.a.a.ac, com.d.c.a.c.P)) {
            Rectangle a2 = a(abVar, tVar);
            return a2 != null ? k(abVar).intersection(a2) : k(abVar);
        }
        return a(abVar, tVar);
    }

    public final Area i(com.d.c.f.d dVar) {
        if (!this.y) {
            this.x = a(dVar);
            this.y = true;
        }
        if (this.x != null) {
            return (Area) this.x.clone();
        }
        return null;
    }

    private Area a(com.d.c.f.d dVar) {
        Rectangle k = (a() == null || !a().a(com.d.c.a.a.ac, com.d.c.a.c.P)) ? null : k(dVar);
        f c = c();
        Area i = c != null ? c.i(dVar) : null;
        if (k != null) {
            AffineTransform a2 = af().a();
            Area area = new Area(a2 != null ? a2.createTransformedShape(k) : k);
            if (i != null) {
                area.intersect(i);
            }
            return area;
        }
        return i;
    }

    public int Q() {
        return d_() + ar() + aq();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Box: ");
        sb.append(" (" + ab() + "," + aa() + ")->(" + Q() + " x " + as() + ")");
        return sb.toString();
    }

    public final void a(com.d.e.v vVar, f fVar) {
        b(fVar);
        fVar.r(vVar);
    }

    public final void b(f fVar) {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        if (fVar == null) {
            throw new NullPointerException("trying to add null child");
        }
        fVar.f(this);
        fVar.a(this.m.size());
        this.m.add(fVar);
    }

    public final void c(List<f> list) {
        Iterator<f> it = list.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    public final void R() {
        if (this.m != null) {
            this.m.clear();
        }
    }

    public void c(f fVar) {
        if (this.m != null) {
            boolean z = false;
            Iterator<f> W = W();
            while (W.hasNext()) {
                f next = W.next();
                if (next.equals(fVar)) {
                    W.remove();
                    z = true;
                } else if (z) {
                    next.a(next.ak() - 1);
                }
            }
        }
    }

    public final f S() {
        f U = U();
        if (U == null) {
            return null;
        }
        return U.d(this);
    }

    public final f T() {
        f U = U();
        if (U == null) {
            return null;
        }
        return U.e(this);
    }

    protected f d(f fVar) {
        if (fVar.ak() == 0) {
            return null;
        }
        return k(fVar.ak() - 1);
    }

    protected f e(f fVar) {
        if (fVar.ak() == V() - 1) {
            return null;
        }
        return k(fVar.ak() + 1);
    }

    public void j(int i) {
        if (this.m != null) {
            c(k(i));
        }
    }

    public final void f(f fVar) {
        this.l = fVar;
    }

    public f U() {
        return this.l;
    }

    public f O() {
        return U();
    }

    public final int V() {
        if (this.m == null) {
            return 0;
        }
        return this.m.size();
    }

    public final f k(int i) {
        if (this.m == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.m.get(i);
    }

    public final Iterator<f> W() {
        return this.m == null ? Collections.emptyIterator() : this.m.iterator();
    }

    public final List<f> X() {
        return this.m == null ? Collections.emptyList() : this.m;
    }

    @Override // com.d.e.ac
    public final com.d.c.f.c a() {
        return this.p;
    }

    public void a(com.d.c.f.c cVar) {
        this.p = cVar;
    }

    public final f Y() {
        return this.q == null ? U() : this.q;
    }

    public final void g(f fVar) {
        this.q = fVar;
    }

    public Rectangle a(int i, int i2, com.d.c.f.d dVar, int i3, int i4) {
        Rectangle rectangle = new Rectangle(i, i2, Q(), as());
        rectangle.translate(i3, i4);
        return rectangle;
    }

    public Rectangle a(com.d.c.f.d dVar, int i, int i2) {
        return a(am(), an(), dVar, i, i2);
    }

    public final Rectangle j(com.d.c.f.d dVar) {
        return a(ab(), aa(), dVar);
    }

    private Rectangle c(com.d.c.f.d dVar) {
        return b(ab(), aa(), dVar);
    }

    public Rectangle g(com.d.c.f.d dVar) {
        return j(dVar);
    }

    public final Rectangle k(com.d.c.f.d dVar) {
        return c(dVar);
    }

    public boolean a(com.d.c.f.d dVar, Shape shape) {
        AffineTransform a2 = af().a();
        if (a2 == null || shape == null) {
            return shape == null || shape.intersects(g(dVar));
        }
        return shape.intersects(a2.createTransformedShape(g(dVar)).getBounds2D());
    }

    public Rectangle a(int i, int i2, com.d.c.f.d dVar) {
        com.d.c.f.a.h n = n(dVar);
        return new Rectangle(i + ((int) n.w()), i2 + ((int) n.t()), (Q() - ((int) n.w())) - ((int) n.u()), (as() - ((int) n.t())) - ((int) n.v()));
    }

    public Rectangle b(int i, int i2, com.d.c.f.d dVar) {
        com.d.c.f.a.h n = n(dVar);
        com.d.c.f.a.a b2 = b(dVar);
        return new Rectangle(i + ((int) n.w()) + ((int) b2.w()), i2 + ((int) n.t()) + ((int) b2.t()), (Q() - ((int) n.y())) - ((int) b2.y()), (as() - ((int) n.x())) - ((int) b2.x()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int l(com.d.c.f.d dVar) {
        com.d.c.f.a.h o = o(dVar);
        return ((int) o.w()) + d_() + ((int) o.u());
    }

    public Rectangle c(int i, int i2, com.d.c.f.d dVar) {
        com.d.c.f.a.h n = n(dVar);
        com.d.c.f.a.a b2 = b(dVar);
        com.d.c.f.a.h o = o(dVar);
        return new Rectangle(i + ((int) n.w()) + ((int) b2.w()) + ((int) o.w()), i2 + ((int) n.t()) + ((int) b2.t()) + ((int) o.t()), ((Q() - ((int) n.y())) - ((int) b2.y())) - ((int) o.y()), ((as() - ((int) n.x())) - ((int) b2.x())) - ((int) o.x()));
    }

    public final com.d.e.t Z() {
        return this.j;
    }

    public final void a(com.d.e.t tVar) {
        this.j = tVar;
    }

    public Dimension m(com.d.c.f.d dVar) {
        int am = am();
        int an = an();
        com.d.c.f.c a2 = a();
        if (!a2.a(com.d.c.a.a.S, com.d.c.a.c.e)) {
            n(am() + ((int) a2.b(com.d.c.a.a.S, Y().d_(), dVar)));
        } else if (!a2.a(com.d.c.a.a.aj, com.d.c.a.c.e)) {
            n(am() - ((int) a2.b(com.d.c.a.a.aj, Y().d_(), dVar)));
        }
        int i = 0;
        if (!Y().a().J()) {
            i = (int) Y().a().c(com.d.c.a.a.R, 0.0f, dVar);
        } else if (N()) {
            i = Y().as();
        }
        if (!a2.a(com.d.c.a.a.ar, com.d.c.a.c.e)) {
            o(an() + ((int) a2.c(com.d.c.a.a.ar, i, dVar)));
        } else if (!a2.a(com.d.c.a.a.x, com.d.c.a.c.e)) {
            o(an() - ((int) a2.c(com.d.c.a.a.x, i, dVar)));
        }
        a(new Dimension(am() - am, an() - an));
        return ag();
    }

    protected boolean N() {
        return false;
    }

    public final void l(int i) {
        this.d = i;
    }

    public final int aa() {
        return this.d;
    }

    public final void m(int i) {
        this.e = i;
    }

    public final int ab() {
        return this.e;
    }

    public final boolean ac() {
        return this.p != null;
    }

    public int ad() {
        return 15;
    }

    public void c(ab abVar) {
        abVar.p().a(abVar, this);
    }

    private boolean e() {
        if (ah() && a().aq()) {
            return true;
        }
        return f() && !U().a().aq();
    }

    public void b(ab abVar) {
        if (!e()) {
            abVar.p().b(abVar, this);
        }
    }

    public final void h(ab abVar) {
        com.d.e.y at = at();
        if (at != null) {
            if (a().aq()) {
                a(abVar, at);
            } else if (V() > 0) {
                k(0).a(abVar, at);
            }
        }
    }

    private void a(ab abVar, com.d.e.y yVar) {
        Dimension b2 = yVar.b();
        Rectangle rectangle = new Rectangle(0, 0, b2.width, b2.height);
        rectangle.add(abVar.i());
        abVar.p().a(abVar, a(), rectangle, rectangle, com.d.c.f.a.a.f1074a);
    }

    public final boolean ae() {
        if (a().aq()) {
            return true;
        }
        if (V() > 0 && k(0).a().aq()) {
            return true;
        }
        return false;
    }

    public final com.d.e.t af() {
        return this.k;
    }

    public final void b(com.d.e.t tVar) {
        this.k = tVar;
    }

    public final void r(com.d.e.v vVar) {
        if (Z() != null) {
            b(Z());
            return;
        }
        if (af() == null) {
            if (U() == null || U().af() == null) {
                throw new RuntimeException("internal error");
            }
            b(U().af());
            if (vVar.o().j() && ((r) vVar.o().f()).l().contains(this)) {
                b(vVar.o());
            }
        }
    }

    public void s(com.d.e.v vVar) {
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            k.b(vVar.o());
            k.s(vVar);
        }
    }

    public List<f> b(Element element) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            if (k.ai() == element) {
                arrayList.add(k);
            }
            arrayList.addAll(k.b(element));
        }
        return arrayList;
    }

    public void c(com.d.e.v vVar) {
        String b2;
        t(vVar);
        if (this.j != null) {
            this.j.i();
            this.j = null;
        }
        b((com.d.e.t) null);
        a((com.d.e.t) null);
        a((com.d.e.y) null);
        u(0);
        this.t = null;
        String g = vVar.e().g(ai());
        if (g != null) {
            vVar.a(g);
        }
        Element ai = ai();
        if (ai != null && (b2 = vVar.e().b(ai)) != null) {
            vVar.a(b2);
        }
    }

    public final void a(com.d.e.v vVar, int i, int i2) {
        for (int i3 = i; i3 <= i2; i3++) {
            k(i3).c(vVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void t(com.d.e.v vVar) {
        int V = V();
        for (int i = 0; i < V; i++) {
            k(i).c(vVar);
        }
    }

    public void C() {
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            k.B();
            k.C();
        }
    }

    public int a(com.d.e.v vVar, com.d.c.a.c cVar, boolean z) {
        aa a2 = vVar.p().a(vVar, this);
        if (a2 == null) {
            com.d.m.l.g(Level.WARNING, "Box has no page");
            return 0;
        }
        int i = 1;
        if (a2.b() == aa()) {
            i = 1 - 1;
            if (z && a2 == vVar.p().m()) {
                vVar.p().l();
                vVar.b(vVar.E());
                vVar.p().b((com.d.c.f.d) vVar);
            }
        }
        if ((a2.j() && cVar == com.d.c.a.c.aa) || (a2.k() && cVar == com.d.c.a.c.aJ)) {
            i++;
        }
        if (i == 0) {
            return 0;
        }
        if (i == 1 && z) {
            vVar.b(vVar.E());
        }
        int a3 = (a2.a() + vVar.A()) - aa();
        if (a2 == vVar.p().m()) {
            vVar.p().b((com.d.c.f.d) vVar);
        }
        if (i == 2) {
            aa aaVar = vVar.p().k().get(a2.i() + 1);
            a3 += aaVar.c((com.d.c.f.d) vVar);
            if (z) {
                vVar.b(vVar.E());
            }
            if (aaVar == vVar.p().m()) {
                vVar.p().b((com.d.c.f.d) vVar);
            }
        }
        o(an() + a3);
        return a3;
    }

    public final void a(com.d.e.v vVar, com.d.c.a.c cVar) {
        boolean z = false;
        aa b2 = vVar.p().b(vVar, this);
        if (b2 != null) {
            if ((b2.j() && cVar == com.d.c.a.c.aa) || (b2.k() && cVar == com.d.c.a.c.aJ)) {
                z = true;
            }
            int a2 = (b2.a() + vVar.A()) - ((aa() + g(vVar, 3)) + as());
            if (b2 == vVar.p().m()) {
                vVar.p().b((com.d.c.f.d) vVar);
            }
            if (z) {
                aa aaVar = vVar.p().k().get(b2.i() + 1);
                a2 += aaVar.c((com.d.c.f.d) vVar);
                if (aaVar == vVar.p().m()) {
                    vVar.p().b((com.d.c.f.d) vVar);
                }
            }
            t(as() + a2);
        }
    }

    public final boolean u(com.d.e.v vVar) {
        aa a2;
        return vVar.D() && (a2 = vVar.p().a(vVar, this)) != null && aa() + as() >= a2.a() - vVar.z();
    }

    public final Dimension ag() {
        return this.r;
    }

    private void a(Dimension dimension) {
        this.r = dimension;
    }

    public final boolean ah() {
        return (ai() == null || au() || ai().getParentNode().getNodeType() != 9) ? false : true;
    }

    private boolean f() {
        return U() != null && U().ah();
    }

    public final Element ai() {
        return this.f1350a;
    }

    public void a(Element element) {
        this.f1350a = element;
    }

    public final void c(com.d.c.f.d dVar, int i) {
        d(dVar);
        this.t.a(i);
    }

    public final void d(com.d.c.f.d dVar, int i) {
        d(dVar);
        this.t.c(i);
    }

    public final void e(com.d.c.f.d dVar, int i) {
        d(dVar);
        this.t.d(i);
    }

    public final void f(com.d.c.f.d dVar, int i) {
        d(dVar);
        this.t.b(i);
    }

    private void d(com.d.c.f.d dVar) {
        if (this.t == null) {
            this.t = e(dVar).z();
        }
    }

    public final com.d.c.f.a.h n(com.d.c.f.d dVar) {
        return this.t != null ? this.t : e(dVar);
    }

    private com.d.c.f.a.h e(com.d.c.f.d dVar) {
        return a().a(aj(), dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.d.c.f.a.h b(com.d.c.f.d dVar, boolean z) {
        return a().a(aj(), dVar, false);
    }

    public final com.d.c.f.a.h o(com.d.c.f.d dVar) {
        return a().b(aj(), dVar);
    }

    public com.d.c.f.a.a b(com.d.c.f.d dVar) {
        return a().a(dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int aj() {
        return Y().d_();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void p(com.d.c.f.d dVar) {
        if (this.t != null) {
            this.t.a(e(dVar).t());
        }
    }

    public final com.d.e.y c(com.d.c.f.d dVar, boolean z) {
        com.d.e.y at = at();
        if (at != null && z) {
            return at;
        }
        com.d.e.y yVar = new com.d.e.y();
        Rectangle a2 = a(ab(), aa(), dVar, 0, 0);
        yVar.a(new Dimension(a2.x + a2.width, a2.y + a2.height));
        yVar.a(g(dVar));
        if (!a().R() || a().S()) {
            a(dVar, yVar, z);
        }
        a(yVar);
        return yVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(com.d.c.f.d dVar, com.d.e.y yVar, boolean z) {
        for (int i = 0; i < V(); i++) {
            com.d.e.y c = k(i).c(dVar, z);
            a(yVar.b(), c.b());
            yVar.a().add(c.a());
        }
    }

    public final int g(com.d.c.f.d dVar, int i) {
        com.d.c.f.a.a b2 = b(dVar);
        com.d.c.f.a.h n = n(dVar);
        com.d.c.f.a.h o = o(dVar);
        switch (i) {
            case 1:
                return (int) (n.w() + b2.w() + o.w());
            case 2:
                return (int) (n.u() + b2.u() + o.u());
            case 3:
                return (int) (n.t() + b2.t() + o.t());
            case 4:
                return (int) (n.v() + b2.v() + o.v());
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(Dimension dimension, Dimension dimension2) {
        if (dimension2.width > dimension.width) {
            dimension.width = dimension2.width;
        }
        if (dimension2.height > dimension.height) {
            dimension.height = dimension2.height;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int ak() {
        return this.u;
    }

    private void a(int i) {
        this.u = i;
    }

    public final String al() {
        return this.v;
    }

    public final void a(String str) {
        this.v = str;
    }

    public final void n(int i) {
        this.f1351b = i;
    }

    public final int am() {
        return this.f1351b;
    }

    public final void o(int i) {
        this.c = i;
    }

    public final int an() {
        return this.c;
    }

    public final void p(int i) {
        this.o = i;
    }

    public final int ao() {
        return this.o;
    }

    public final void q(int i) {
        this.n = i;
    }

    public final int ap() {
        return this.n;
    }

    public final void r(int i) {
        this.g = i;
    }

    public final int aq() {
        return this.g;
    }

    public final void s(int i) {
        this.h = i;
    }

    public final int ar() {
        return this.h;
    }

    public final void t(int i) {
        this.i = i;
    }

    public int as() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void h(com.d.c.f.d dVar, int i) {
        t((int) Math.max(0.0f, (i - b(dVar).x()) - o(dVar).x()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int q(com.d.c.f.d dVar) {
        return (int) (as() + b(dVar).x() + o(dVar).x());
    }

    public final Rectangle r(com.d.c.f.d dVar) {
        com.d.c.f.a.h n = n(dVar);
        return new Rectangle(ab() + ((int) n.w()), aa() + ((int) n.t()), s(dVar), (as() - ((int) n.t())) - ((int) n.v()));
    }

    public final void u(int i) {
        this.f = i < 0 ? 0 : i;
    }

    public int d_() {
        return this.f;
    }

    public final int s(com.d.c.f.d dVar) {
        return (int) (d_() + b(dVar).y() + o(dVar).y());
    }

    public final void i(com.d.c.f.d dVar, int i) {
        u((int) ((i - b(dVar).y()) - o(dVar).y()));
    }

    public final com.d.e.y at() {
        return this.s;
    }

    private void a(com.d.e.y yVar) {
        this.s = yVar;
    }

    public final boolean au() {
        return this.w;
    }

    public final void i(boolean z) {
        this.w = true;
    }

    public final g av() {
        g gVar = new g();
        gVar.c(ar());
        gVar.d(aq());
        gVar.a(d_());
        gVar.b(as());
        return gVar;
    }

    public final void a(g gVar) {
        s(gVar.c());
        r(gVar.d());
        u(gVar.a());
        t(gVar.b());
    }

    public final boolean aw() {
        f fVar = this;
        while (true) {
            f fVar2 = fVar;
            f U = fVar2.U();
            if (U != null) {
                fVar = U;
            } else {
                return fVar2.ah();
            }
        }
    }

    public void a(com.d.e.v vVar, j jVar) {
        jVar.a(vVar, aa());
        Iterator<f> it = X().iterator();
        while (it.hasNext()) {
            it.next().a(vVar, jVar);
        }
        jVar.b(vVar, aa() + as());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean ax() {
        return false;
    }
}
