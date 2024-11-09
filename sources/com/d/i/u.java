package com.d.i;

import com.d.h.w;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/u.class */
public final class u extends f implements com.d.e.s {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1377a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1378b;
    private m c;
    private List<ad> d;
    private int e;
    private int f;
    private List<f> g;
    private x h;
    private boolean i;
    private int j;
    private int k;
    private t l;
    private byte m = 0;

    @Override // com.d.i.f
    public final String toString() {
        return "LineBox: (" + ab() + "," + aa() + ")->(" + Q() + "," + as() + ")";
    }

    @Override // com.d.i.f
    public final Rectangle a(com.d.c.f.d dVar, int i, int i2) {
        Rectangle rectangle = new Rectangle(am(), an(), d_(), as());
        rectangle.translate(i, i2);
        return rectangle;
    }

    @Override // com.d.e.s
    public final void a(ab abVar) {
        int a2;
        if (!U().a().a(abVar, this)) {
            return;
        }
        if (i()) {
            d(abVar);
            if (this.m == 1) {
                a2 = com.d.e.q.a(abVar, this, 0, 0);
            } else {
                a2 = com.d.e.q.a(abVar, this, 0);
            }
            u(a2);
            C();
            a(true, (com.d.c.f.d) abVar);
            c((com.d.c.f.d) abVar, false);
        }
        if (this.d != null) {
            Object a3 = abVar.p().a(com.d.d.q.BACKGROUND, this);
            abVar.p().a(this);
            abVar.p().a(a3);
        }
        if (abVar.k()) {
            abVar.p().a(abVar, this, com.d.c.d.h.c);
        }
    }

    private void d(ab abVar) {
        if (V() > 0) {
            for (int i = 0; i < V(); i++) {
                f k = k(i);
                if (k instanceof r) {
                    ((r) k).d(abVar);
                }
            }
        }
    }

    public final boolean c() {
        f U = U();
        return U != null && U.V() > 0 && U.k(0) == this;
    }

    public final void e() {
        if (V() > 0) {
            for (int V = V() - 1; V >= 0; V--) {
                f k = k(V);
                if (k instanceof r) {
                    r rVar = (r) k;
                    rVar.f();
                    if (rVar.h()) {
                        j(V);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public final boolean f() {
        return this.f1377a;
    }

    public final void a(boolean z) {
        this.f1377a = true;
    }

    public final void a(boolean z, com.d.c.f.d dVar) {
        com.d.c.a.c e = U().a().e(com.d.c.a.a.an);
        int i = 0;
        boolean z2 = -1;
        if (e == com.d.c.a.c.bH) {
            z2 = this.m == 1;
        }
        if (e == com.d.c.a.c.aa || e == com.d.c.a.c.X || !z2) {
            i = t() + q().a();
            if (e == com.d.c.a.c.X && z) {
                g();
            }
        } else if (e == com.d.c.a.c.n) {
            int a2 = q().a();
            i = (a2 + (((U().d_() - a2) - q().b()) / 2)) - ((d_() + t()) / 2);
        } else if (e == com.d.c.a.c.aJ || z2) {
            i = (U().d_() - q().b()) - d_();
        }
        if (i != am()) {
            n(i);
            B();
            C();
        }
    }

    public final void g() {
        if (!U().a().aA() && !o()) {
            int d_ = ((U().d_() - q().a()) - q().b()) - t();
            if (d_ > d_()) {
                int d_2 = d_ - d_();
                h p = p();
                t tVar = new t();
                if (p.b() > 1) {
                    tVar.a((d_2 * 0.2f) / (p.b() - 1));
                } else {
                    tVar.a(0.0f);
                }
                if (p.a() > 0) {
                    tVar.b((d_2 * 0.8f) / p.a());
                } else {
                    tVar.b(0.0f);
                }
                a(tVar);
                b(tVar);
            }
        }
    }

    private void a(t tVar) {
        float f = 0.0f;
        for (f fVar : X()) {
            fVar.n(fVar.am() + Math.round(f));
            if (fVar instanceof r) {
                f += ((r) fVar).a(tVar, f);
            }
        }
        C();
    }

    private boolean o() {
        f T = T();
        while (true) {
            u uVar = (u) T;
            if (uVar != null) {
                if (uVar.f()) {
                    return false;
                }
                T = uVar.T();
            } else {
                return true;
            }
        }
    }

    private h p() {
        h hVar = new h();
        for (f fVar : X()) {
            if (fVar instanceof r) {
                ((r) fVar).a(hVar);
            }
        }
        return hVar;
    }

    private m q() {
        return this.c;
    }

    public final void a(m mVar) {
        this.c = mVar;
    }

    private boolean r() {
        return this.f1378b;
    }

    public final void b(boolean z) {
        this.f1378b = true;
    }

    @Override // com.d.i.f
    public final boolean a(com.d.c.f.d dVar, Shape shape) {
        if (shape == null || b(dVar, shape)) {
            return true;
        }
        return r() && c(dVar, shape);
    }

    private boolean b(com.d.c.f.d dVar, Shape shape) {
        return shape.intersects(g(dVar));
    }

    @Override // com.d.i.f
    public final Rectangle g(com.d.c.f.d dVar) {
        Rectangle rectangle;
        f U = U();
        if (U.a().a(com.d.c.a.a.v, com.d.c.a.c.h) || m() != null) {
            rectangle = new Rectangle(ab(), aa() + this.e, ((U.ab() + U.ap()) + U.d_()) - ab(), this.f);
        } else {
            rectangle = new Rectangle(ab(), aa() + this.e, d_(), this.f);
        }
        return rectangle;
    }

    private boolean c(com.d.c.f.d dVar, Shape shape) {
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            if (!(k instanceof r)) {
                if (new com.d.e.d().a(dVar, shape, k)) {
                    return true;
                }
            } else if (((r) k).b(dVar, shape)) {
                return true;
            }
        }
        return false;
    }

    public final List<ad> h() {
        return this.d;
    }

    public final void a(List<ad> list) {
        this.d = list;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void b(int i) {
        this.e = i;
    }

    public final void a(List list, com.d.e.t tVar) {
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            if (af() == tVar) {
                list.add(k);
                if (k instanceof r) {
                    ((r) k).a(list, tVar);
                }
            }
        }
    }

    private List<f> s() {
        return this.g == null ? Collections.emptyList() : this.g;
    }

    public final void a(c cVar) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        this.g.add(cVar);
    }

    @Override // com.d.i.f
    public final void c(com.d.e.v vVar) {
        for (int i = 0; i < s().size(); i++) {
            s().get(i).c(vVar);
        }
        if (this.h != null) {
            this.h.b(this);
        }
        super.c(vVar);
    }

    @Override // com.d.i.f
    public final void B() {
        f U = U();
        if (U == null) {
            throw new w.a("calcCanvasLocation() called with no parent");
        }
        m(U.ab() + U.ap() + am());
        l(U.aa() + U.ao() + an());
    }

    @Override // com.d.i.f
    public final void C() {
        super.C();
        for (int i = 0; i < s().size(); i++) {
            f fVar = s().get(i);
            if (fVar.a().A()) {
                fVar.B();
                fVar.C();
            }
        }
    }

    public final void a(x xVar) {
        this.h = xVar;
    }

    public final boolean i() {
        return this.i;
    }

    public final void c(boolean z) {
        this.i |= z;
    }

    private int t() {
        return this.j;
    }

    public final void c(int i) {
        this.j = i;
    }

    private s u() {
        if (V() == 0) {
            return null;
        }
        for (int V = V() - 1; V >= 0; V--) {
            f k = k(V);
            if (k instanceof r) {
                s m = ((r) k).m();
                if (m == null || !m.c()) {
                    return m;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    public final void a(com.d.e.v vVar) {
        s u = u();
        if (u != null) {
            com.d.c.a.c i = u.g().a().i();
            if (i == com.d.c.a.c.aq || i == com.d.c.a.c.ar) {
                u.a(vVar);
            }
        }
    }

    public final int j() {
        return this.k;
    }

    public final void d(int i) {
        this.k = i;
    }

    public final boolean k() {
        if (!r()) {
            return false;
        }
        for (int i = 0; i < V(); i++) {
            if (!(k(i) instanceof c)) {
                return false;
            }
        }
        return true;
    }

    public final boolean l() {
        for (int i = 0; i < V(); i++) {
            f k = k(i);
            if (k instanceof c) {
                if (k.Q() > 0 || k.as() > 0) {
                    return true;
                }
            } else if (((r) k).j()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.d.i.f
    public final void a(com.d.e.v vVar, j jVar) {
        jVar.a(vVar, aa());
        jVar.b(vVar, aa() + as());
    }

    public final void a(com.d.e.v vVar, boolean z) {
        aa a2;
        if (vVar.D() && (a2 = vVar.p().a(vVar, this)) != null) {
            if (z || aa() + as() >= a2.a() - vVar.z()) {
                a(vVar, com.d.c.a.c.c, false);
                B();
            } else if (a2.b() + vVar.A() > aa()) {
                o(an() + ((a2.b() + vVar.A()) - aa()));
                B();
            }
        }
    }

    public final t m() {
        return this.l;
    }

    private void b(t tVar) {
        this.l = tVar;
    }

    public final void a(byte b2) {
        this.m = b2;
    }

    public final boolean n() {
        return this.m == 1;
    }
}
