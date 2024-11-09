package com.d.i;

import com.d.i.x;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/i/c.class */
public class c extends f implements com.d.e.s {

    /* renamed from: a, reason: collision with root package name */
    private x f1338a;

    /* renamed from: b, reason: collision with root package name */
    private int f1339b;
    private com.d.e.z c;
    private f d;
    private boolean e;
    private com.d.d.n f;
    private int g;
    private List<com.d.e.ac> h;
    private boolean i;
    private boolean j;
    private b k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private com.d.c.c.a q;
    private com.d.c.c.a r;
    private n s;
    private int t;
    private boolean u;
    private boolean v;

    @Override // com.d.i.f, com.d.e.ac
    public final void a(Element element) {
        super.a(element);
        this.v = com.d.m.i.a().a().t().a(element);
    }

    public c c() {
        c cVar = new c();
        cVar.a(a());
        cVar.a(ai());
        return cVar;
    }

    protected String a_() {
        return "";
    }

    @Override // com.d.i.f
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String name = getClass().getName();
        sb.append(name.substring(name.lastIndexOf(46) + 1));
        sb.append(": ");
        if (ai() != null && !au()) {
            sb.append("<");
            sb.append(ai().getNodeName());
            sb.append("> ");
        }
        if (au()) {
            sb.append("(anonymous) ");
        }
        if (al() != null) {
            sb.append(':');
            sb.append(al());
            sb.append(' ');
        }
        sb.append('(');
        sb.append(a().e(com.d.c.a.a.G).toString());
        sb.append(") ");
        if (a().P()) {
            sb.append("(running) ");
        }
        sb.append('(');
        switch (F()) {
            case 1:
                sb.append('I');
                break;
            case 2:
                sb.append('B');
                break;
            case 4:
                sb.append('E');
                break;
        }
        sb.append(") ");
        sb.append(a_());
        a(sb);
        sb.append("(" + ab() + "," + aa() + ")->(" + Q() + " x " + as() + ")");
        return sb.toString();
    }

    private void a(StringBuilder sb) {
        if (a().F()) {
            sb.append("(relative) ");
        }
        if (a().B()) {
            sb.append("(fixed) ");
        }
        if (a().A()) {
            sb.append("(absolute) ");
        }
        if (a().C()) {
            sb.append("(floated) ");
        }
    }

    public final boolean u() {
        return a().X();
    }

    public final void f(ab abVar) {
        if (a().a(abVar, this) && u()) {
            v.a(abVar, this);
        }
    }

    @Override // com.d.i.f
    public Rectangle g(com.d.c.f.d dVar) {
        Rectangle g = super.g(dVar);
        if (a().X()) {
            int i = g.x;
            g.x = 0;
            g.width += i;
        }
        return g;
    }

    @Override // com.d.e.s
    public final void a(ab abVar) {
        if (!a().a(abVar, this)) {
            return;
        }
        af().a(abVar, this);
    }

    public final boolean v() {
        f U = U();
        return (U instanceof u) || (U instanceof r);
    }

    private u f() {
        if (!v()) {
            return null;
        }
        f U = U();
        while (true) {
            f fVar = U;
            if (!(fVar instanceof u)) {
                U = fVar.U();
            } else {
                return (u) fVar;
            }
        }
    }

    public final void g(ab abVar) {
        abVar.p().a(abVar, this, com.d.c.d.h.f1057b);
    }

    public final x w() {
        return this.f1338a;
    }

    private void a(x xVar) {
        this.f1338a = xVar;
    }

    private void f(com.d.e.v vVar) {
        if (w() != null) {
            return;
        }
        ac a2 = com.d.e.q.a(vVar, this);
        boolean z = false;
        x xVar = new x();
        xVar.a(a2);
        com.d.c.f.c a3 = a();
        com.d.c.a.c e = a3.e(com.d.c.a.a.U);
        String f = a3.f(com.d.c.a.a.W);
        if (!f.equals("none")) {
            xVar.a(a(vVar, a2, f));
            z = xVar.c() != null;
        }
        if (e != com.d.c.a.c.ap && !z) {
            if (e == com.d.c.a.c.o || e == com.d.c.a.c.aR || e == com.d.c.a.c.x) {
                xVar.a(a(a2));
            } else {
                xVar.a(b(vVar, e));
            }
        }
        a(xVar);
    }

    private static x.a a(ac acVar) {
        int a2 = (int) ((acVar.a() + acVar.c()) / 3.0f);
        x.a aVar = new x.a();
        aVar.a(a2);
        aVar.b(a2 * 3);
        return aVar;
    }

    private static x.b a(com.d.e.v vVar, ac acVar, String str) {
        com.d.d.c d;
        if (!str.equals("none") && (d = vVar.q().b(str).d()) != null) {
            if (d.b() > acVar.a()) {
                d.a(-1, (int) acVar.a());
            }
            x.b bVar = new x.b();
            bVar.a(d);
            bVar.a(d.a() << 1);
            return bVar;
        }
        return null;
    }

    private x.c b(com.d.e.v vVar, com.d.c.a.c cVar) {
        String str = com.d.e.l.a(cVar, g()) + ".  ";
        com.d.d.r d = vVar.d();
        vVar.w();
        int a2 = d.a(a().d(vVar), str);
        x.c cVar2 = new x.c();
        cVar2.a(str);
        cVar2.a(a2);
        return cVar2;
    }

    private int g() {
        return this.f1339b;
    }

    public final void f(int i) {
        this.f1339b = i;
    }

    public final com.d.e.z x() {
        return this.c;
    }

    public final void a(com.d.e.z zVar) {
        this.c = zVar;
    }

    public final f y() {
        return this.d;
    }

    public final void a(f fVar) {
        this.d = fVar;
    }

    public final boolean z() {
        return this.v;
    }

    public final boolean A() {
        return this.f != null;
    }

    @Override // com.d.i.f
    public final void B() {
        f Y;
        com.d.e.n b2;
        if (K() && (b2 = this.s.b()) != null) {
            Point b3 = b2.b(this);
            m((b2.b().ab() + am()) - b3.x);
            l((b2.b().aa() + an()) - b3.y);
        }
        u f = f();
        if (f == null) {
            f U = U();
            if (U != null) {
                m(U.ab() + U.ap() + am());
                l(U.aa() + U.ao() + an());
            } else if (ac() && a().ai() && (Y = Y()) != null) {
                m(Y.ab() + am());
                l(Y.aa() + an());
            }
        } else {
            m(f.ab() + am());
            l(f.aa() + an());
        }
        if (A()) {
            Point c = E().c();
            if (c.x != ab() || c.y != aa()) {
                E().a(ab(), aa());
            }
        }
    }

    public final void h(com.d.e.v vVar) {
        Point a2 = vVar.l().a();
        com.d.e.n b2 = vVar.l().b();
        m((b2.b().ab() + am()) - a2.x);
        l((b2.b().aa() + an()) - a2.y);
    }

    @Override // com.d.i.f
    public final void C() {
        super.C();
        if (this.c != null) {
            this.c.a().a();
        }
    }

    public final boolean D() {
        return this.e;
    }

    public final void e(boolean z) {
        this.e = z;
    }

    private void j() {
        if (this.d.aa() != aa()) {
            o(this.d.aa() - aa());
            l(this.d.aa());
        }
    }

    public final void b(com.d.c.f.d dVar, int i) {
        Rectangle c;
        com.d.c.f.c a2 = a();
        int i2 = Y().c(0, 0, dVar).height;
        if (Y() instanceof c) {
            c = Y().b(0, 0, dVar);
        } else {
            c = Y().c(0, 0, dVar);
        }
        if ((i & 2) != 0) {
            n(0);
            if (!a2.a(com.d.c.a.a.S, com.d.c.a.c.e)) {
                n((int) a2.b(com.d.c.a.a.S, Y().d_(), dVar));
            } else if (!a2.a(com.d.c.a.a.aj, com.d.c.a.c.e)) {
                n((c.width - ((int) a2.b(com.d.c.a.a.aj, Y().d_(), dVar))) - Q());
            }
            n(am() + c.x);
        }
        if ((i & 1) != 0) {
            o(0);
            if (!a2.a(com.d.c.a.a.ar, com.d.c.a.c.e)) {
                o((int) a2.c(com.d.c.a.a.ar, i2, dVar));
            } else if (!a2.a(com.d.c.a.a.x, com.d.c.a.c.e)) {
                o((c.height - ((int) a2.b(com.d.c.a.a.x, i2, dVar))) - as());
            }
            int e = e(dVar);
            if (e != -1 && h(dVar) == -1) {
                t(e);
                t(dVar);
            }
            o(an() + c.y);
        }
        B();
        if ((i & 1) != 0 && a().V() && a().W()) {
            j();
        }
        C();
    }

    public final boolean i(com.d.e.v vVar) {
        float h = a().h(vVar);
        aa a2 = vVar.p().a(vVar, this);
        return a2 != null && ((float) aa()) + h > ((float) a2.a());
    }

    public final void j(com.d.e.v vVar) {
        if (vVar.r()) {
            if (a().ab() || D() || i(vVar)) {
                a(vVar, a().e(com.d.c.a.a.af), false);
                B();
                C();
                e(false);
            }
        }
    }

    public final com.d.d.n E() {
        return this.f;
    }

    private void a(com.d.d.n nVar) {
        this.f = nVar;
    }

    @Override // com.d.i.f
    public void c(com.d.e.v vVar) {
        super.c(vVar);
        a(false);
        b(false);
        g(false);
        f(false);
        b(0);
        if (A()) {
            E().a(vVar);
            a((com.d.d.n) null);
        }
        if (F() == 1) {
            R();
        }
        if (K()) {
            this.s.b().a(this);
            this.s.a().a(this);
        }
        if (a().P()) {
            vVar.p().d(this);
        }
    }

    private int c(com.d.c.f.d dVar) {
        if (!a().a(com.d.c.a.a.S, com.d.c.a.c.e) && !a().a(com.d.c.a.a.aj, com.d.c.a.c.e)) {
            Rectangle b2 = Y().b(0, 0, dVar);
            int a2 = (((b2.width - ((int) a().a(com.d.c.a.a.S, b2.width, dVar))) - ((int) a().a(com.d.c.a.a.aj, b2.width, dVar))) - ar()) - aq();
            if (a2 < 0) {
                return 0;
            }
            return a2;
        }
        return -1;
    }

    private int e(com.d.c.f.d dVar) {
        if (!a().a(com.d.c.a.a.ar, com.d.c.a.c.e) && !a().a(com.d.c.a.a.x, com.d.c.a.c.e)) {
            Rectangle b2 = Y().b(0, 0, dVar);
            int a2 = (b2.height - ((int) a().a(com.d.c.a.a.ar, b2.height, dVar))) - ((int) a().a(com.d.c.a.a.x, b2.height, dVar));
            if (a2 < 0) {
                return 0;
            }
            return a2;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(com.d.e.v vVar, int i, com.d.c.f.a.h hVar, com.d.c.f.a.a aVar) {
        int w = ((int) aVar.w()) + ((int) hVar.w()) + i + ((int) hVar.u()) + ((int) aVar.u());
        if (w < aj()) {
            int aj = aj() - w;
            boolean K = a().K();
            boolean L = a().L();
            if (K && L) {
                e(vVar, aj / 2);
                f(vVar, aj / 2);
            } else if (K) {
                e(vVar, aj);
            } else if (L) {
                f(vVar, aj);
            }
        }
    }

    private int g(com.d.e.v vVar) {
        int i = 0;
        int i2 = 0;
        boolean z = true;
        f fVar = this;
        while (true) {
            f fVar2 = fVar;
            com.d.c.f.c a2 = fVar2.a();
            if (a2.H() && !a2.ay()) {
                i += fVar2.ar();
                i2 += fVar2.aq();
                if (fVar2.Y().ax()) {
                    break;
                }
                fVar = fVar2.Y();
            } else {
                break;
            }
        }
        z = false;
        if (z) {
            return (vVar.p().a(vVar, this).d(vVar) - i) - i2;
        }
        return (aj() - ar()) - aq();
    }

    private void v(com.d.e.v vVar) {
        if (E() == null) {
            int a2 = a((com.d.c.f.d) vVar);
            int h = h((com.d.c.f.d) vVar);
            int u = u((com.d.c.f.d) vVar);
            int w = w((com.d.c.f.d) vVar);
            if (u > a2 && u > 0) {
                a2 = u;
            }
            if (w > h && w > 0) {
                h = w;
            }
            com.d.d.n a3 = vVar.v().a(vVar, this, vVar.q(), a2, h);
            if (a3 != null) {
                a(a3);
                a(vVar, a3);
            }
        }
    }

    private void a(com.d.e.v vVar, com.d.d.n nVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int a2 = a((com.d.c.f.d) vVar);
        int h = h((com.d.c.f.d) vVar);
        boolean z = a2 >= 0 && h >= 0;
        int a3 = nVar.a();
        int b2 = nVar.b();
        int v = (a().aj() || (a3 <= v((com.d.c.f.d) vVar) && a2 <= v((com.d.c.f.d) vVar))) ? a2 : v((com.d.c.f.d) vVar);
        int i5 = v;
        if (v < 0 || u((com.d.c.f.d) vVar) <= 0 || i5 >= u((com.d.c.f.d) vVar)) {
            i = i5;
        } else {
            i = u((com.d.c.f.d) vVar);
        }
        int i6 = i;
        int x = (a().ak() || (b2 <= x((com.d.c.f.d) vVar) && h <= x((com.d.c.f.d) vVar))) ? h : x((com.d.c.f.d) vVar);
        int i7 = x;
        if (x < 0 || w((com.d.c.f.d) vVar) <= 0 || i7 >= w((com.d.c.f.d) vVar)) {
            i2 = i7;
        } else {
            i2 = w((com.d.c.f.d) vVar);
        }
        int i8 = i2;
        if (a().aC()) {
            com.d.c.f.a.a b3 = b((com.d.c.f.d) vVar);
            com.d.c.f.a.h o = o((com.d.c.f.d) vVar);
            i6 = i6 < 0 ? i6 : (int) Math.max(0.0f, (i6 - b3.y()) - o.y());
            i8 = i8 < 0 ? i8 : (int) Math.max(0.0f, (i8 - b3.x()) - o.x());
        }
        if (i6 > 0 && i8 > 0) {
            if (z) {
                i3 = i6;
                i4 = i8;
            } else if (a3 <= i6 && b2 <= i8) {
                double d = a3 / i6;
                double d2 = b2 / i8;
                if (d > d2) {
                    i3 = i6;
                    i4 = (int) (b2 / d);
                } else {
                    i3 = (int) (a3 / d2);
                    i4 = i8;
                }
            } else if (a3 / i6 > b2 / i8) {
                i3 = i6;
                i4 = b2;
            } else {
                i3 = a3;
                i4 = i8;
            }
        } else if (i6 > 0) {
            i3 = i6;
            i4 = (int) ((i6 / a3) * b2);
        } else if (i8 > 0) {
            i4 = i8;
            i3 = (int) ((i8 / b2) * a3);
        } else if (i6 == 0 || i8 == 0) {
            i3 = i6;
            i4 = i8;
        } else {
            i3 = a3;
            i4 = b2;
        }
        u(i3);
        t(i4);
    }

    public final void k(com.d.e.v vVar) {
        d(vVar, a((com.d.c.f.d) vVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d(com.d.e.v vVar, int i) {
        if (!s()) {
            com.d.c.f.c a2 = a();
            com.d.c.f.a.h o = o((com.d.c.f.d) vVar);
            com.d.c.f.a.a b2 = b((com.d.c.f.d) vVar);
            if (i != -1 && !au() && ((a().a(com.d.c.a.a.aV, com.d.c.a.c.e) || a().a(com.d.c.a.a.aT, com.d.c.a.c.e)) && a().z())) {
                a(vVar, i, o, b2);
            }
            H(vVar);
            com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
            s(((int) n.w()) + ((int) b2.w()) + ((int) o.w()));
            r(((int) o.u()) + ((int) b2.u()) + ((int) n.u()));
            v(vVar);
            if (A()) {
                g(true);
                return;
            }
            if (vVar.r() && a().aw()) {
                u(g(vVar));
            } else {
                u((aj() - ar()) - aq());
            }
            t(0);
            if (!au() || (aA() && K())) {
                int i2 = -1;
                if (i != -1) {
                    if (a2.aC()) {
                        i(vVar, i);
                    } else {
                        u(i);
                    }
                } else if (a().A() || a().B()) {
                    int c = c((com.d.c.f.d) vVar);
                    i2 = c;
                    if (c != -1) {
                        u(i2);
                    }
                }
                int h = h((com.d.c.f.d) vVar);
                if (h != -1) {
                    if (a2.aC()) {
                        h(vVar, h);
                    } else {
                        t(h);
                    }
                }
                if (E() == null && i == -1 && i2 == -1 && a2.ay()) {
                    c(true);
                }
                if (!A()) {
                    f((com.d.c.f.d) vVar);
                }
            }
            g(true);
        }
    }

    private void w(com.d.e.v vVar) {
        if (a().m() && !a().C()) {
            vVar.a(0, -an());
            vVar.l().a(vVar, (f) this);
            vVar.a(0, an());
            B();
        }
    }

    private void x(com.d.e.v vVar) {
        aa a2;
        if (vVar.D() && vVar.A() > 0) {
            if ((a().x() || a().X()) && (a2 = vVar.p().a(vVar, this)) != null && a2.b() + vVar.A() > aa()) {
                int b2 = (a2.b() + vVar.A()) - aa();
                o(an() + b2);
                vVar.a(0, b2);
                B();
            }
        }
    }

    private void y(com.d.e.v vVar) {
        if (!au()) {
            String g = vVar.e().g(ai());
            if (g != null) {
                vVar.a(g, this);
            }
            String b2 = vVar.e().b(ai());
            if (b2 != null) {
                vVar.a(b2, this);
            }
        }
    }

    public void b(com.d.e.v vVar) {
        a_(vVar, 0);
    }

    public void a_(com.d.e.v vVar, int i) {
        int a2;
        aa a3;
        com.d.c.f.c a4 = a();
        boolean z = false;
        if (ah()) {
            z = true;
            vVar.a(this);
            if (vVar.r()) {
                if (!a4.a(com.d.c.a.a.ad, com.d.c.a.c.e)) {
                    vVar.b(a4.f(com.d.c.a.a.ad));
                }
                vVar.p().b((com.d.c.f.d) vVar);
            }
        } else if (a4.O() && Z() == null) {
            z = true;
            vVar.a(this);
        } else if (a4.O()) {
            Z().a(true);
            z = true;
            vVar.a(this);
        }
        if (a4.n()) {
            vVar.p();
        }
        w(vVar);
        if (ah() || a().N() || b()) {
            vVar.a(new com.d.e.b(this, vVar));
        }
        y(vVar);
        if (vVar.r() && a().a(com.d.c.a.a.r, com.d.c.a.c.bH)) {
            vVar.p().e(this);
        }
        v(vVar);
        k(vVar);
        z(vVar);
        B(vVar);
        x(vVar);
        if (vVar.r() && (a3 = vVar.p().a(vVar, this)) != null && a3.b() == aa() - l()) {
            p((com.d.c.f.d) vVar);
        }
        com.d.c.f.a.a b2 = b((com.d.c.f.d) vVar);
        com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
        com.d.c.f.a.h o = o((com.d.c.f.d) vVar);
        int as = as();
        if (!A()) {
            t(0);
        }
        boolean z2 = false;
        if (a().X()) {
            f(vVar);
            vVar.a(w());
            z2 = true;
        }
        int w = ((int) n.w()) + ((int) b2.w()) + ((int) o.w());
        int t = ((int) n.t()) + ((int) b2.t()) + ((int) o.t());
        q(w);
        p(t);
        vVar.a(ap(), ao());
        if (!A()) {
            a(vVar, i);
        }
        vVar.a(-ap(), -ao());
        b(as());
        if (!A()) {
            if (!b_() && (as - as() > 0 || h())) {
                t(as);
            }
            t((com.d.c.f.d) vVar);
        }
        if ((ah() || a().N()) && a().J() && (a2 = vVar.l().b().a(vVar, ao() + as())) > 0) {
            t(as() + a2);
            b(M() + a2);
        }
        if (z2) {
            vVar.a((x) null);
        }
        a(vVar, b2, n, o);
        if (ah() || a().N()) {
            vVar.m();
        }
        if (z) {
            vVar.n();
        }
    }

    protected boolean h() {
        return true;
    }

    protected int l() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(com.d.e.v vVar, com.d.c.f.a.a aVar, com.d.c.f.a.h hVar, com.d.c.f.a.h hVar2) {
        t(as() + ((int) hVar.t()) + ((int) aVar.t()) + ((int) hVar2.t()) + ((int) hVar2.v()) + ((int) aVar.v()) + ((int) hVar.v()));
        b(M() + ((int) hVar.t()) + ((int) aVar.t()) + ((int) hVar2.t()) + ((int) hVar2.v()) + ((int) aVar.v()) + ((int) hVar.v()));
    }

    private void z(com.d.e.v vVar) {
        if (t()) {
            u((F(vVar) - ar()) - aq());
            f((com.d.c.f.d) vVar);
            c(false);
        }
    }

    private void f(com.d.c.f.d dVar) {
        int v;
        int s = a().aC() ? s(dVar) : d_();
        if (!a().aj() && s > (v = v(dVar))) {
            if (a().aC()) {
                i(dVar, v);
            } else {
                u(v);
            }
        }
        int u = u(dVar);
        if (u > 0 && s < u) {
            if (a().aC()) {
                i(dVar, u);
            } else {
                u(u);
            }
        }
    }

    private void t(com.d.c.f.d dVar) {
        int x;
        int q = a().aC() ? q(dVar) : as();
        if (!a().ak() && q > (x = x(dVar))) {
            if (a().aC()) {
                h(dVar, x);
            } else {
                t(x);
            }
        }
        int w = w(dVar);
        if (w > 0 && q < w) {
            if (a().aC()) {
                h(dVar, w);
            } else {
                t(w);
            }
        }
    }

    public final void l(com.d.e.v vVar) {
        if (F() == 0) {
            com.d.e.c.a(vVar, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(com.d.e.v vVar, int i) {
        l(vVar);
        if (q() != null) {
            vVar.t().a(q());
        }
        if (r() != null) {
            vVar.s().a(r());
        }
        switch (F()) {
            case 1:
                a(vVar, i, m(vVar), true);
                break;
            case 2:
                com.d.e.a.a(vVar, this, i);
                break;
        }
        if (q() != null) {
            vVar.t().a();
        }
        if (r() != null) {
            vVar.s().a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(com.d.e.v vVar, int i, int i2, boolean z) {
        com.d.e.q.a(vVar, this, i, i2);
        if (vVar.r() && vVar.D() && V() > 1) {
            a(vVar, i, z);
        }
        if (z && a().at()) {
            A(vVar);
        }
    }

    private void A(com.d.e.v vVar) {
        Iterator<f> W = W();
        while (W.hasNext()) {
            ((u) W.next()).g();
        }
    }

    private void a(com.d.e.v vVar, int i, boolean z) {
        aa aaVar;
        aa a2 = vVar.p().a(vVar, (u) k(0));
        if (a2 == null) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        int V = V();
        while (i3 < V) {
            u uVar = (u) k(i3);
            if (uVar.aa() >= a2.a()) {
                break;
            }
            if (!uVar.f()) {
                i2++;
            }
            i3++;
        }
        if (i3 != V) {
            int b2 = (int) a().b(com.d.c.a.a.ab);
            if (i3 - i2 < b2) {
                e(true);
                return;
            }
            u uVar2 = (u) k(V - 1);
            List<aa> k = vVar.p().k();
            aa aaVar2 = k.get(a2.i() + 1);
            while (true) {
                aaVar = aaVar2;
                if (aaVar.i() == k.size() - 1 || aaVar.a() >= uVar2.aa()) {
                    break;
                } else {
                    aaVar2 = k.get(aaVar.i() + 1);
                }
            }
            int i4 = 0;
            int i5 = V - 1;
            while (i5 >= 0 && k(i5).aa() >= aaVar.b()) {
                u uVar3 = (u) k(i5);
                if (uVar3.aa() < aaVar.b()) {
                    break;
                }
                if (!uVar3.f()) {
                    i4++;
                }
                i5--;
            }
            int b3 = (int) a().b(com.d.c.a.a.aw);
            if (((V - 1) - i5) - i4 < b3) {
                if ((V - 1) - b3 < b2) {
                    e(true);
                } else if (z) {
                    t(vVar);
                    R();
                    a(vVar, i, (V - 1) - b3, false);
                }
            }
        }
    }

    public final int F() {
        return this.g;
    }

    public final void g(int i) {
        this.g = i;
    }

    public final List<com.d.e.ac> G() {
        return this.h;
    }

    public final void b(List<com.d.e.ac> list) {
        this.h = list;
        if (list != null) {
            for (com.d.e.ac acVar : list) {
                if (acVar instanceof f) {
                    ((f) acVar).g(this);
                }
            }
        }
    }

    protected boolean n() {
        return false;
    }

    protected boolean k() {
        return !ah() && a().ah();
    }

    private void B(com.d.e.v vVar) {
        if (!o() || !p()) {
            H(vVar);
            com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
            if (!o() && !p() && E(vVar)) {
                b bVar = this.k != null ? this.k : new b();
                a(vVar, bVar);
                a(vVar, n, bVar);
                return;
            }
            if (!o()) {
                b bVar2 = this.k != null ? this.k : new b();
                a(vVar, true, bVar2);
                if (((int) n.t()) != bVar2.a()) {
                    c(vVar, bVar2.a());
                }
            }
            if (!p()) {
                b bVar3 = new b();
                b(vVar, true, bVar3);
                a(vVar, n, bVar3);
            }
        }
    }

    private void a(com.d.e.v vVar, com.d.c.f.a.h hVar, b bVar) {
        c cVar = null;
        if (!v()) {
            cVar = a(bVar);
        }
        if (cVar != null && !(cVar instanceof com.d.i.b) && bVar.b()) {
            cVar.k = bVar;
            d((com.d.c.f.d) vVar, 0);
        } else if (((int) hVar.v()) != bVar.a()) {
            d((com.d.c.f.d) vVar, bVar.a());
        }
    }

    protected c a(b bVar) {
        c cVar;
        f T = T();
        while (true) {
            cVar = (c) T;
            if (cVar != null) {
                if (cVar instanceof com.d.i.b) {
                    ((com.d.i.b) cVar).a(bVar.a());
                }
                if (!cVar.n()) {
                    break;
                }
                T = cVar.T();
            } else {
                break;
            }
        }
        return cVar;
    }

    private void a(com.d.e.v vVar, boolean z, b bVar) {
        if (!o()) {
            if (!n()) {
                k(vVar);
                if (vVar.r() && a().ax()) {
                    g(false);
                }
                com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
                bVar.a((int) n.t());
                if (!z && ((int) n.t()) != 0) {
                    c(vVar, 0);
                }
                if (k() && C(vVar)) {
                    l(vVar);
                    if (F() == 2) {
                        Iterator<f> W = W();
                        while (W.hasNext()) {
                            c cVar = (c) W.next();
                            cVar.a(vVar, false, bVar);
                            if (!cVar.n()) {
                                break;
                            }
                        }
                    }
                }
            }
            a(true);
        }
    }

    private void b(com.d.e.v vVar, boolean z, b bVar) {
        if (!p()) {
            if (!n()) {
                k(vVar);
                if (vVar.r() && a().ax()) {
                    g(false);
                }
                com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
                bVar.a((int) n.v());
                if (!z && ((int) n.v()) != 0) {
                    d((com.d.c.f.d) vVar, 0);
                }
                if (k() && !a().q() && D(vVar)) {
                    l(vVar);
                    if (F() == 2) {
                        int V = V() - 1;
                        while (true) {
                            if (V < 0) {
                                break;
                            }
                            c cVar = (c) k(V);
                            if (cVar.n()) {
                                V--;
                            } else {
                                cVar.b(vVar, false, bVar);
                                break;
                            }
                        }
                    }
                }
            }
            b(true);
        }
    }

    private boolean C(com.d.e.v vVar) {
        return ((int) o((com.d.c.f.d) vVar).t()) == 0 && ((int) b((com.d.c.f.d) vVar).t()) == 0;
    }

    private boolean D(com.d.e.v vVar) {
        return ((int) o((com.d.c.f.d) vVar).v()) == 0 && ((int) b((com.d.c.f.d) vVar).v()) == 0;
    }

    private void a(com.d.e.v vVar, b bVar) {
        com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
        bVar.a((int) n.t());
        bVar.a((int) n.v());
        c(vVar, 0);
        a(true);
        d((com.d.c.f.d) vVar, 0);
        b(true);
        l(vVar);
        if (F() == 2) {
            Iterator<f> W = W();
            while (W.hasNext()) {
                ((c) W.next()).a(vVar, bVar);
            }
        }
    }

    private boolean E(com.d.e.v vVar) {
        com.d.c.f.c a2 = a();
        com.d.c.f.a.a a3 = a2.a(vVar);
        com.d.c.f.a.h o = o((com.d.c.f.d) vVar);
        if ((((int) a3.t()) == 0 && ((int) a3.v()) == 0 && ((int) o.t()) == 0 && ((int) o.v()) == 0) ? false : true) {
            return false;
        }
        l(vVar);
        if (F() == 1) {
            return false;
        }
        if (F() == 2) {
            Iterator<f> W = W();
            while (W.hasNext()) {
                c cVar = (c) W.next();
                if (cVar.n() || !cVar.E(vVar)) {
                    return false;
                }
            }
        }
        if (a2.b(com.d.c.a.a.Z) == 0.0f) {
            return b_() || a2.b(com.d.c.a.a.R) == 0.0f;
        }
        return false;
    }

    private boolean o() {
        return this.i;
    }

    private void a(boolean z) {
        this.i = z;
    }

    private boolean p() {
        return this.j;
    }

    private void b(boolean z) {
        this.j = z;
    }

    protected int a(com.d.c.f.d dVar) {
        return a(dVar, false);
    }

    private int a(com.d.c.f.d dVar, boolean z) {
        int b2;
        if (!au() && !a().H()) {
            if ((!z || a().I()) && (b2 = (int) a().b(com.d.c.a.a.ax, Y().d_(), dVar)) >= 0) {
                return b2;
            }
            return -1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int h(com.d.c.f.d dVar) {
        if (!au() && !b_()) {
            if (a().d(com.d.c.a.a.R)) {
                return (int) a().c(com.d.c.a.a.R, 0.0f, dVar);
            }
            return (int) a().c(com.d.c.a.a.R, ((c) Y()).h(dVar), dVar);
        }
        return -1;
    }

    public boolean b_() {
        if (a().J()) {
            return true;
        }
        if (a().d(com.d.c.a.a.R)) {
            return false;
        }
        f Y = Y();
        if (Y.ac() && (Y instanceof c)) {
            return ((c) Y).b_();
        }
        return ((Y instanceof c) && Y.ax()) ? false : true;
    }

    private int u(com.d.c.f.d dVar) {
        return a().a(dVar, aj());
    }

    private int v(com.d.c.f.d dVar) {
        return a().b(dVar, aj());
    }

    private int w(com.d.c.f.d dVar) {
        return a().c(dVar, y(dVar));
    }

    private int x(com.d.c.f.d dVar) {
        return a().d(dVar, y(dVar));
    }

    private int y(com.d.c.f.d dVar) {
        if (Y().ac() && !Y().a().J() && Y().a().d(com.d.c.a.a.R)) {
            return (int) Y().a().a(com.d.c.a.a.R, 0.0f, dVar);
        }
        return 0;
    }

    private int F(com.d.e.v vVar) {
        a(vVar);
        return Math.min(Math.max(I(), G(vVar)), H());
    }

    private int G(com.d.e.v vVar) {
        if (!a().A()) {
            return aj();
        }
        int i = 0;
        int i2 = 0;
        if (!a().a(com.d.c.a.a.S, com.d.c.a.c.e)) {
            i = (int) a().a(com.d.c.a.a.S, Y().d_(), vVar);
        }
        if (!a().a(com.d.c.a.a.aj, com.d.c.a.c.e)) {
            i2 = (int) a().a(com.d.c.a.a.aj, Y().d_(), vVar);
        }
        return (Y().l(vVar) - i) - i2;
    }

    protected boolean c_() {
        return false;
    }

    private void H(com.d.e.v vVar) {
        if (o() && p()) {
            return;
        }
        com.d.c.f.g i = a().i(com.d.c.a.a.aS);
        boolean z = (i instanceof com.d.c.f.a.e) && !i.g();
        com.d.c.f.g i2 = a().i(com.d.c.a.a.aU);
        boolean z2 = (i2 instanceof com.d.c.f.a.e) && !i2.g();
        if (!z && !z2) {
            return;
        }
        com.d.c.f.a.h b2 = b((com.d.c.f.d) vVar, false);
        com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
        if (!o() && b2.t() != n.t()) {
            c(vVar, (int) b2.t());
        }
        if (!p() && b2.v() != n.v()) {
            d((com.d.c.f.d) vVar, (int) b2.v());
        }
    }

    public void a(com.d.e.v vVar) {
        if (!J()) {
            com.d.c.f.a.h n = n((com.d.c.f.d) vVar);
            com.d.c.f.a.a b2 = b((com.d.c.f.d) vVar);
            com.d.c.f.a.h o = o((com.d.c.f.d) vVar);
            int a2 = a((com.d.c.f.d) vVar, true);
            v(vVar);
            if (A() && a2 == -1) {
                a2 = d_();
            }
            if (a2 != -1 && !c_()) {
                int w = ((int) n.w()) + ((int) b2.w()) + ((int) o.w()) + a2 + ((int) n.u()) + ((int) b2.u()) + ((int) o.u());
                this.m = w;
                this.l = w;
            } else {
                int i = -1;
                if (a2 != -1) {
                    i = d_();
                    u(a2);
                }
                int w2 = ((int) n.w()) + ((int) b2.w()) + ((int) o.w()) + ((int) n.u()) + ((int) b2.u()) + ((int) o.u());
                this.m = w2;
                this.l = w2;
                int i2 = this.m;
                if (a2 != -1) {
                    i2 += a2;
                }
                l(vVar);
                if (F() == 2 || F() == 1) {
                    switch (F()) {
                        case 1:
                            J(vVar);
                            break;
                        case 2:
                            I(vVar);
                            break;
                    }
                }
                if (i2 > this.m) {
                    this.m = i2;
                }
                if (i != -1) {
                    u(i);
                }
            }
            if (!A()) {
                a(vVar, n, b2, o);
            }
            f(true);
        }
    }

    private void a(com.d.e.v vVar, com.d.c.f.a.h hVar, com.d.c.f.a.a aVar, com.d.c.f.a.h hVar2) {
        int v;
        int w;
        int u = u((com.d.c.f.d) vVar);
        if (u > 0 && this.l < (w = u + ((int) hVar.w()) + ((int) aVar.w()) + ((int) hVar2.w()) + ((int) hVar.u()) + ((int) aVar.u()) + ((int) hVar2.u()))) {
            this.l = w;
        }
        if (!a().aj() && this.m > (v = v((com.d.c.f.d) vVar) + ((int) hVar.w()) + ((int) aVar.w()) + ((int) hVar2.w()) + ((int) hVar.u()) + ((int) aVar.u()) + ((int) hVar2.u()))) {
            if (v > this.l) {
                this.m = v;
            } else {
                this.m = this.l;
            }
        }
    }

    private void I(com.d.e.v vVar) {
        int i = 0;
        int i2 = 0;
        Iterator<f> W = W();
        while (W.hasNext()) {
            c cVar = (c) W.next();
            cVar.a(vVar);
            if (cVar.I() > i) {
                i = cVar.I();
            }
            if (cVar.H() > i2) {
                i2 = cVar.H();
            }
        }
        this.l += i;
        this.m += i2;
    }

    private void J(com.d.e.v vVar) {
        int b2 = (int) a().b(com.d.c.a.a.ap, d_(), vVar);
        if (a().X() && a().au()) {
            f(vVar);
            b2 += w().e();
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        q qVar = null;
        for (com.d.e.ac acVar : this.h) {
            if (!acVar.a().A() && !acVar.a().B() && !acVar.a().P()) {
                if (acVar.a().C() || acVar.a().p() || acVar.a().r()) {
                    if (acVar.a().C() && acVar.a().m()) {
                        if (qVar != null) {
                            i3 -= qVar.a(vVar);
                        }
                        if (i3 > i2) {
                            i2 = i3;
                        }
                        i3 = 0;
                    }
                    qVar = null;
                    c cVar = (c) acVar;
                    cVar.a(vVar);
                    i3 += cVar.H();
                    if (cVar.I() > i) {
                        i = cVar.I();
                    }
                } else {
                    q qVar2 = (q) acVar;
                    com.d.c.a.c i4 = qVar2.a().i();
                    qVar2.a(vVar, d_(), i3 == 0);
                    if (i4 == com.d.c.a.c.ar) {
                        i3 += b2 + qVar2.k();
                        if (qVar2.l() > i) {
                            i = qVar2.l();
                        }
                        qVar = qVar2;
                    } else if (i4 == com.d.c.a.c.aB) {
                        if (qVar != null) {
                            i3 -= qVar.a(vVar);
                        }
                        qVar = null;
                        if (i3 > i2) {
                            i2 = i3;
                        }
                        int m = b2 + qVar2.m();
                        if (m > i) {
                            i = m;
                        }
                        int k = qVar2.k();
                        if (k > i) {
                            i = k;
                        }
                        if (i > i2) {
                            i2 = i;
                        }
                        i3 = 0;
                    } else if (i4 == com.d.c.a.c.aD || i4 == com.d.c.a.c.aC) {
                        int m2 = i3 + b2 + qVar2.m();
                        if (qVar != null) {
                            m2 -= qVar.a(vVar);
                        }
                        if (m2 > i2) {
                            i2 = m2;
                        }
                        if (qVar2.k() > i2) {
                            i2 = qVar2.k();
                        }
                        if (qVar2.l() > i) {
                            i = qVar2.l();
                        }
                        if (i4 == com.d.c.a.c.aC) {
                            qVar = qVar2;
                        } else {
                            qVar = null;
                        }
                        i3 = 0;
                    } else {
                        i3 += b2 + qVar2.k();
                        if (qVar2.l() > i) {
                            i = b2 + qVar2.l();
                        }
                        qVar = qVar2;
                    }
                    if (b2 > 0) {
                        b2 = 0;
                    }
                }
            }
        }
        if (qVar != null) {
            i3 -= qVar.a(vVar);
        }
        if (i3 > i2) {
            i2 = i3;
        }
        this.l += i;
        this.m += i2;
    }

    public final int H() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void h(int i) {
        this.m = i;
    }

    public final int I() {
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void i(int i) {
        this.l = i;
    }

    public void a_(com.d.e.v vVar) {
        a(vVar, a());
    }

    public final void a(com.d.e.v vVar, com.d.c.f.c cVar) {
        com.d.c.c.a a2;
        if (F() == 1) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(cVar);
            for (com.d.e.ac acVar : this.h) {
                if (acVar instanceof q) {
                    q qVar = (q) acVar;
                    if (qVar.g()) {
                        if (qVar.h() != null) {
                            if (qVar.n() == null) {
                                a2 = vVar.c().a(qVar.h(), false);
                            } else {
                                a2 = vVar.c().a(qVar.h(), qVar.n());
                            }
                            linkedList.add(((com.d.c.f.c) linkedList.getLast()).a(a2));
                        } else {
                            linkedList.add(cVar.a(com.d.c.a.c.R));
                        }
                    }
                    qVar.a((com.d.c.f.c) linkedList.getLast());
                    qVar.d();
                    if (qVar.f()) {
                        linkedList.removeLast();
                    }
                }
            }
        }
    }

    @Override // com.d.i.f
    protected final void a(com.d.c.f.d dVar, com.d.e.y yVar, boolean z) {
        if (x() != null) {
            x().a().a(new d(this, dVar, z, yVar));
        }
        super.a(dVar, yVar, z);
    }

    private com.d.c.c.a q() {
        return this.r;
    }

    public final void a(com.d.c.c.a aVar) {
        this.r = aVar;
    }

    private com.d.c.c.a r() {
        return this.q;
    }

    public final void b(com.d.c.c.a aVar) {
        this.q = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean J() {
        return this.n;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(boolean z) {
        this.n = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void g(boolean z) {
        this.o = z;
    }

    private boolean s() {
        return this.o;
    }

    private void c(boolean z) {
        this.p = z;
    }

    private boolean t() {
        return this.p;
    }

    public void a(com.d.e.v vVar, c cVar, int i) {
        n(0);
        o(i);
    }

    public int d(com.d.e.v vVar) {
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            if (k instanceof u) {
                return k.aa() + ((u) k).j();
            }
            if (k instanceof com.d.f.i) {
                return k.aa() + ((com.d.f.i) k).d();
            }
            int d = ((c) k).d(vVar);
            if (d != Integer.MIN_VALUE) {
                return d;
            }
        }
        return Integer.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int m(com.d.e.v vVar) {
        com.d.e.i I = vVar.I();
        if (I != null && I.a() == this) {
            return I.b();
        }
        return 0;
    }

    public final boolean n(com.d.e.v vVar) {
        com.d.e.i I = vVar.I();
        return I != null && I.a() == this;
    }

    public final com.d.e.i o(com.d.e.v vVar) {
        u a2;
        if (vVar.r() && a().av() && (a2 = a((int) a().b(com.d.c.a.a.aw))) != null) {
            aa b2 = vVar.p().b(vVar, a2);
            aa b3 = vVar.p().b(vVar, this);
            if (b2 != null && b3 != null && b2.i() + 1 == b3.i()) {
                c cVar = (c) a2.U();
                return new com.d.e.i(cVar, cVar.h(a2));
            }
            return null;
        }
        return null;
    }

    public int d(com.d.c.f.d dVar) {
        if (A()) {
            E();
        }
        u ay = ay();
        if (ay != null) {
            return (ay.aa() + ay.j()) - aa();
        }
        return as();
    }

    private int h(f fVar) {
        int V = V();
        for (int i = 0; i < V; i++) {
            if (k(i) == fVar) {
                return i;
            }
        }
        return -1;
    }

    private u a(int i) {
        a aVar = new a(i);
        a(aVar);
        return aVar.f1341b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/c$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f1340a;

        /* renamed from: b, reason: collision with root package name */
        public u f1341b;

        public a(int i) {
            this.f1340a = i;
        }
    }

    private void a(a aVar) {
        int F = F();
        int V = V();
        if (V > 0) {
            if (F == 1) {
                for (int i = V - 1; i >= 0; i--) {
                    u uVar = (u) k(i);
                    if (uVar.as() > 0) {
                        aVar.f1341b = uVar;
                        int i2 = aVar.f1340a - 1;
                        aVar.f1340a = i2;
                        if (i2 == 0) {
                            return;
                        }
                    }
                }
                return;
            }
            if (F == 2) {
                for (int i3 = V - 1; i3 >= 0; i3--) {
                    ((c) k(i3)).a(aVar);
                    if (aVar.f1340a == 0) {
                        return;
                    }
                }
            }
        }
    }

    private u ay() {
        int F = F();
        int V = V();
        if (V > 0) {
            if (F == 1) {
                for (int i = V - 1; i >= 0; i--) {
                    u uVar = (u) k(i);
                    if (uVar.as() > 0) {
                        return uVar;
                    }
                }
                return null;
            }
            if (F == 2) {
                for (int i2 = V - 1; i2 >= 0; i2--) {
                    u ay = ((c) k(i2)).ay();
                    if (ay != null) {
                        return ay;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private u az() {
        int F = F();
        int V = V();
        if (V > 0) {
            if (F == 1) {
                for (int i = 0; i < V; i++) {
                    u uVar = (u) k(i);
                    if (uVar.as() > 0) {
                        return uVar;
                    }
                }
                return null;
            }
            if (F == 2) {
                for (int i2 = 0; i2 < V; i2++) {
                    u az = ((c) k(i2)).az();
                    if (az != null) {
                        return az;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public final boolean p(com.d.e.v vVar) {
        u az;
        if (vVar.r() && a().av() && (az = az()) != null) {
            aa a2 = vVar.p().a(vVar, az);
            aa a3 = vVar.p().a(vVar, this);
            return (a2 == null || a3 == null || a2.i() != a3.i() + 1) ? false : true;
        }
        return false;
    }

    public final boolean K() {
        return this.s != null;
    }

    public final n L() {
        return this.s;
    }

    public final void a(n nVar) {
        this.s = nVar;
    }

    public final int M() {
        return this.t;
    }

    private void b(int i) {
        this.t = i;
    }

    private boolean aA() {
        return this.u;
    }

    public final void h(boolean z) {
        this.u = true;
    }

    @Override // com.d.i.f
    protected final boolean N() {
        return v();
    }

    @Override // com.d.i.f
    public final f O() {
        f y = y();
        if (y != null) {
            return y;
        }
        return U();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean K(com.d.e.v vVar) {
        l(vVar);
        switch (F()) {
            case 1:
                return true;
            case 2:
                return X().stream().anyMatch(fVar -> {
                    return ((c) fVar).K(vVar);
                });
            case 3:
            default:
                throw new RuntimeException("internal error: no children");
            case 4:
                return false;
        }
    }

    public final boolean q(com.d.e.v vVar) {
        if (a().a(com.d.c.a.a.ad, com.d.c.a.c.e)) {
            if (vVar.B() != null && aw()) {
                vVar.c((String) null);
                return true;
            }
            return false;
        }
        String f = a().f(com.d.c.a.a.ad);
        if (!f.equals(vVar.B()) && aw()) {
            if (z() || K(vVar)) {
                vVar.c(f);
                return true;
            }
            return false;
        }
        return false;
    }

    public final boolean P() {
        return !A() && a().a(com.d.c.a.a.ac, com.d.c.a.c.P) && a().R();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(com.d.e.v vVar, j jVar, j jVar2, int i, int i2) {
        int b2;
        int a2;
        int a3 = jVar2.a();
        int b3 = jVar2.b();
        for (int i3 = a3; i3 <= b3; i3++) {
            i a4 = jVar2.a(i3);
            if (i3 != a3 && (a2 = a4.a()) != -1) {
                jVar.a(vVar, a2 - i);
            }
            if (i3 != b3 && (b2 = a4.b()) != -1) {
                jVar.b(vVar, b2 + i2);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/c$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f1342a;

        /* renamed from: b, reason: collision with root package name */
        private int f1343b;

        public final void a(int i) {
            if (i < 0 && i < this.f1343b) {
                this.f1343b = i;
            }
            if (i > 0 && i > this.f1342a) {
                this.f1342a = i;
            }
        }

        public final int a() {
            return this.f1342a + this.f1343b;
        }

        public final boolean b() {
            return (this.f1342a == 0 && this.f1343b == 0) ? false : true;
        }
    }
}
