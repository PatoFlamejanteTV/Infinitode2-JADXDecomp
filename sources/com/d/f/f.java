package com.d.f;

import com.d.e.k;
import com.d.e.v;
import com.d.i.aa;
import com.d.i.ab;
import java.awt.Rectangle;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/d/f/f.class */
public final class f extends com.d.i.c {

    /* renamed from: a, reason: collision with root package name */
    public static final f f1185a = new f();

    /* renamed from: b, reason: collision with root package name */
    private int f1186b;
    private int c;
    private d d;
    private j e;
    private com.d.c.f.a.a f;
    private com.d.c.f.a.a g;
    private a h;
    private a i;
    private a j;
    private a k;
    private static final int[] l;

    static {
        int[] iArr = new int[com.d.c.a.c.a()];
        l = iArr;
        iArr[com.d.c.a.c.z.f968a] = 1;
        l[com.d.c.a.c.aQ.f968a] = 2;
        l[com.d.c.a.c.u.f968a] = 3;
        l[com.d.c.a.c.y.f968a] = 4;
        l[com.d.c.a.c.aI.f968a] = 5;
        l[com.d.c.a.c.av.f968a] = 6;
        l[com.d.c.a.c.O.f968a] = 7;
        l[com.d.c.a.c.U.f968a] = 8;
    }

    @Override // com.d.i.c
    public final com.d.i.c c() {
        f fVar = new f();
        fVar.a(a());
        fVar.a(ai());
        return fVar;
    }

    @Override // com.d.i.f
    public final com.d.c.f.a.a b(com.d.c.f.d dVar) {
        if (f().a().am()) {
            return this.f == null ? com.d.c.f.a.a.f1074a : this.f;
        }
        return super.b(dVar);
    }

    public final void c(com.d.c.f.d dVar) {
        a v = v(dVar);
        a u = u(dVar);
        a w = w(dVar);
        a t = t(dVar);
        this.g = new com.d.c.f.a.a(v, u, w, t);
        v.a((v.c() + 1) / 2);
        u.a(u.c() / 2);
        w.a(w.c() / 2);
        t.a((t.c() + 1) / 2);
        this.f = new com.d.c.f.a.a(v, u, w, t);
        this.h = v;
        this.i = u;
        this.j = w;
        this.k = t;
    }

    public final int d() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final int e() {
        return this.f1186b;
    }

    public final void b(int i) {
        this.f1186b = i;
    }

    @Override // com.d.i.c
    public final void b(v vVar) {
        super.b(vVar);
    }

    public final d f() {
        if (this.d == null) {
            this.d = (d) U().U().U();
        }
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final j g() {
        if (this.e == null) {
            this.e = (j) U().U();
        }
        return this.e;
    }

    public final com.d.c.f.i e(com.d.c.f.d dVar) {
        com.d.c.f.i a2 = a().a(dVar, com.d.c.a.a.ax);
        if (a2.c() || a2.e()) {
            return a2;
        }
        com.d.c.f.a.a b2 = b(dVar);
        int w = 0 + ((int) b2.w()) + ((int) b2.u());
        com.d.c.f.a.h o = o(dVar);
        a2.a(a2.a() + w + ((int) o.w()) + ((int) o.u()));
        return a2;
    }

    public final com.d.c.f.i f(com.d.c.f.d dVar) {
        com.d.c.f.i e = e(dVar);
        if (a().ao() > 1 || !e.c()) {
            return e;
        }
        h e2 = f().e(d());
        if (e2 != null) {
            e = e2.a().a(dVar, com.d.c.a.a.ax);
        }
        return e;
    }

    public final void b(v vVar, int i) {
        k(vVar);
        u((i - ar()) - aq());
    }

    @Override // com.d.i.c
    public final boolean b_() {
        return a().J() || !a().d(com.d.c.a.a.R);
    }

    @Override // com.d.i.c
    public final int d(v vVar) {
        int d = super.d(vVar);
        if (d != Integer.MIN_VALUE) {
            return d;
        }
        return (int) c(ab(), aa(), vVar).getY();
    }

    public final int e(v vVar) {
        return super.d(vVar);
    }

    public final void c(int i) {
        for (int i2 = 0; i2 < V(); i2++) {
            com.d.i.f k = k(i2);
            k.o(k.an() + i);
        }
        x().a().a(new g(this, i));
        C();
    }

    public final boolean c(v vVar, int i) {
        if (!vVar.D()) {
            return false;
        }
        aa a2 = vVar.p().a(vVar, this);
        int aa = aa() + M();
        if (a2 != null) {
            return aa >= a2.a() - vVar.z() || aa + i >= a2.a() - vVar.z();
        }
        return false;
    }

    public final com.d.c.a.c j() {
        com.d.c.a.c e = a().e(com.d.c.a.a.as);
        if (e == com.d.c.a.c.bi || e == com.d.c.a.c.al || e == com.d.c.a.c.l) {
            return e;
        }
        return com.d.c.a.c.g;
    }

    private boolean ay() {
        return a().ap() || F() != 4;
    }

    @Override // com.d.i.f
    public final void b(ab abVar) {
        Rectangle j;
        if (ay() && a().a(abVar, this)) {
            if (abVar.o() && f().a().as()) {
                j = d(abVar);
            } else {
                j = j(abVar);
            }
            if (j != null) {
                a(abVar, j);
            }
        }
    }

    private void a(ab abVar, Rectangle rectangle) {
        com.d.c.f.a.a a2 = a().a(abVar);
        h e = f().e(d());
        if (e != null) {
            abVar.p().a(abVar, e.a(), rectangle, f().a(abVar, d()), a2);
        }
        com.d.i.f U = U();
        com.d.i.f U2 = U.U();
        com.d.c.f.c a3 = f().a();
        com.d.c.f.c a4 = U2.a();
        Rectangle j = U2.j(abVar);
        j.y += a3.g(abVar);
        j.height -= a3.g(abVar);
        j.x += a3.f(abVar);
        j.width -= 2 * a3.f(abVar);
        abVar.p().a(abVar, a4, rectangle, j, a2);
        com.d.c.f.c a5 = U.a();
        Rectangle j2 = U.j(abVar);
        j2.x += a3.f(abVar);
        j2.width -= 2 * a3.f(abVar);
        abVar.p().a(abVar, a5, rectangle, j2, a2);
        abVar.p().a(abVar, a(), rectangle, j(abVar), this.f != null ? this.f : a2);
    }

    @Override // com.d.i.f
    public final void c(ab abVar) {
        if (ay() && !o()) {
            if (abVar.o() && f().a().as() && a().a(abVar, this)) {
                Rectangle d = d(abVar);
                if (d != null) {
                    abVar.p().a(abVar, a(), d, ad());
                    return;
                }
                return;
            }
            super.c(abVar);
        }
    }

    public final void a(ab abVar, int i) {
        abVar.p().a(abVar, p(), x((com.d.c.f.d) abVar), i);
    }

    private Rectangle d(ab abVar) {
        int a2;
        int b2;
        Rectangle j = j(abVar);
        j g = g();
        if (g.q() || g.p()) {
            return j;
        }
        com.d.i.j g2 = ((i) U()).g();
        com.d.i.i a3 = g2.a(abVar.t());
        if (a3 == null) {
            return null;
        }
        if (a3.a() != -1 && a3.b() != -1) {
            if (abVar.t() == g2.a()) {
                a2 = j.y;
            } else {
                a2 = a3.a() - ((i) U()).j();
            }
            if (abVar.t() == g2.b()) {
                b2 = j.y + j.height;
            } else {
                b2 = a3.b() + ((i) U()).o();
            }
            j.y = a2;
            j.height = b2 - a2;
            return j;
        }
        return j;
    }

    @Override // com.d.i.c
    protected final boolean c_() {
        return f().a().a(com.d.c.a.a.am, com.d.c.a.c.e);
    }

    @Override // com.d.i.c
    protected final boolean n() {
        return true;
    }

    public static a a(a aVar, a aVar2, boolean z) {
        if (!aVar2.e()) {
            return aVar;
        }
        if (!aVar.e()) {
            return aVar2;
        }
        if (aVar.b() == com.d.c.a.c.P) {
            return aVar;
        }
        if (aVar2.b() == com.d.c.a.c.P) {
            return aVar2;
        }
        if (aVar2.b() == com.d.c.a.c.ap) {
            return aVar;
        }
        if (aVar.b() == com.d.c.a.c.ap) {
            return aVar2;
        }
        if (aVar.c() != aVar2.c()) {
            return aVar.c() > aVar2.c() ? aVar : aVar2;
        }
        if (aVar.b() != aVar2.b()) {
            return l[aVar.b().f968a] > l[aVar2.b().f968a] ? aVar : aVar2;
        }
        if (z && aVar.d() == aVar2.d()) {
            return null;
        }
        return aVar.d() >= aVar2.d() ? aVar : aVar2;
    }

    private static a a(a aVar, a aVar2) {
        return a(aVar, aVar2, false);
    }

    private a t(com.d.c.f.d dVar) {
        h e;
        a a2 = a.a(a().a(dVar), 10);
        f c = f().c(this);
        if (c != null) {
            a a3 = a(a2, a.b(c.a().a(dVar), 10));
            a2 = a3;
            if (a3.g()) {
                return a2;
            }
        } else if (d() == 0) {
            a a4 = a(a2, a.a(U().a().a(dVar), 9));
            if (a4.g()) {
                return a4;
            }
            a a5 = a(a4, a.a(g().a().a(dVar), 8));
            a2 = a5;
            if (a5.g()) {
                return a2;
            }
        }
        h e2 = f().e(d());
        if (e2 != null) {
            a a6 = a(a2, a.a(e2.a().a(dVar), 7));
            a2 = a6;
            if (a6.g()) {
                return a2;
            }
        }
        if (d() > 0 && (e = f().e(d() - 1)) != null) {
            a a7 = a(a2, a.b(e.a().a(dVar), 7));
            a2 = a7;
            if (a7.g()) {
                return a2;
            }
        }
        if (d() == 0) {
            a a8 = a(a2, a.a(f().a().a(dVar), 6));
            a2 = a8;
            if (a8.g()) {
                return a2;
            }
        }
        return a2;
    }

    private a u(com.d.c.f.d dVar) {
        d f = f();
        boolean z = false;
        if (f.b((d() + a().ao()) - 1) == f.f() - 1) {
            z = true;
        }
        a b2 = a.b(a().a(dVar), 10);
        if (!z) {
            f d = f.d(this);
            if (d != null) {
                a a2 = a(b2, a.a(d.a().a(dVar), 10));
                b2 = a2;
                if (a2.g()) {
                    return b2;
                }
            }
        } else {
            a a3 = a(b2, a.b(U().a().a(dVar), 9));
            if (a3.g()) {
                return a3;
            }
            a a4 = a(a3, a.b(g().a().a(dVar), 8));
            b2 = a4;
            if (a4.g()) {
                return b2;
            }
        }
        h e = f().e((d() + a().ao()) - 1);
        if (e != null) {
            a a5 = a(b2, a.b(e.a().a(dVar), 7));
            b2 = a5;
            if (a5.g()) {
                return b2;
            }
        }
        if (!z) {
            h e2 = f.e(d() + a().ao());
            if (e2 != null) {
                a a6 = a(b2, a.a(e2.a().a(dVar), 7));
                b2 = a6;
                if (a6.g()) {
                    return b2;
                }
            }
        } else {
            a a7 = a(b2, a.b(f.a().a(dVar), 6));
            b2 = a7;
            if (a7.g()) {
                return b2;
            }
        }
        return b2;
    }

    private a v(com.d.c.f.d dVar) {
        i j;
        a c = a.c(a().a(dVar), 10);
        f a2 = f().a(this);
        if (a2 != null) {
            a a3 = a(c, a.d(a2.a().a(dVar), 10));
            c = a3;
            if (a3.g()) {
                return c;
            }
        }
        a a4 = a(c, a.c(U().a().a(dVar), 9));
        a aVar = a4;
        if (a4.g()) {
            return aVar;
        }
        if (a2 != null) {
            if (a2.g() == g()) {
                j = (i) U().S();
            } else {
                j = a2.g().j();
            }
            if (j != null) {
                a a5 = a(aVar, a.d(j.a().a(dVar), 9));
                aVar = a5;
                if (a5.g()) {
                    return aVar;
                }
            }
        }
        j g = g();
        if (e() == 0) {
            a a6 = a(aVar, a.c(g.a().a(dVar), 8));
            aVar = a6;
            if (a6.g()) {
                return aVar;
            }
            f();
            j a7 = d.a(g, false);
            g = a7;
            if (a7 != null) {
                a a8 = a(aVar, a.d(g.a().a(dVar), 8));
                aVar = a8;
                if (a8.g()) {
                    return aVar;
                }
            }
        }
        if (g == null) {
            h e = f().e(d());
            if (e != null) {
                a a9 = a(aVar, a.c(e.a().a(dVar), 7));
                aVar = a9;
                if (a9.g()) {
                    return aVar;
                }
            }
            a a10 = a(aVar, a.c(f().a().a(dVar), 6));
            aVar = a10;
            if (a10.g()) {
                return aVar;
            }
        }
        return aVar;
    }

    private a w(com.d.c.f.d dVar) {
        a d = a.d(a().a(dVar), 10);
        f b2 = f().b(this);
        if (b2 != null) {
            a a2 = a(d, a.c(b2.a().a(dVar), 10));
            d = a2;
            if (a2.g()) {
                return d;
            }
        }
        a a3 = a(d, a.d(U().a().a(dVar), 9));
        a aVar = a3;
        if (a3.g()) {
            return aVar;
        }
        if (b2 != null) {
            a a4 = a(aVar, a.c(b2.U().a().a(dVar), 9));
            aVar = a4;
            if (a4.g()) {
                return aVar;
            }
        }
        j g = g();
        if (e() + a().an() >= g.g()) {
            a a5 = a(aVar, a.d(g.a().a(dVar), 8));
            aVar = a5;
            if (a5.g()) {
                return aVar;
            }
            f();
            j b3 = d.b(g, false);
            g = b3;
            if (b3 != null) {
                a a6 = a(aVar, a.c(g.a().a(dVar), 8));
                aVar = a6;
                if (a6.g()) {
                    return aVar;
                }
            }
        }
        if (g == null) {
            h e = f().e(d());
            if (e != null) {
                a a7 = a(aVar, a.d(e.a().a(dVar), 7));
                aVar = a7;
                if (a7.g()) {
                    return aVar;
                }
            }
            a a8 = a(aVar, a.d(f().a().a(dVar), 6));
            aVar = a8;
            if (a8.g()) {
                return aVar;
            }
        }
        return aVar;
    }

    private Rectangle x(com.d.c.f.d dVar) {
        com.d.c.f.a.a p = p();
        Rectangle j = j(dVar);
        j.x -= ((int) p.w()) / 2;
        j.y -= ((int) p.t()) / 2;
        j.width += (((int) p.w()) / 2) + ((((int) p.u()) + 1) / 2);
        j.height += (((int) p.t()) / 2) + ((((int) p.v()) + 1) / 2);
        return j;
    }

    @Override // com.d.i.c, com.d.i.f
    public final Rectangle g(com.d.c.f.d dVar) {
        if (o()) {
            return x(dVar);
        }
        return super.g(dVar);
    }

    public final boolean o() {
        return this.g != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.d.c.f.a.a p() {
        return this.g;
    }

    public final a q() {
        return this.j;
    }

    public final a r() {
        return this.k;
    }

    public final a s() {
        return this.i;
    }

    public final a t() {
        return this.h;
    }

    public final void a(Set<a> set, List<k> list) {
        if (this.h.f() && !set.contains(this.h)) {
            set.add(this.h);
            list.add(new k(this, 1));
        }
        if (this.i.f() && !set.contains(this.i)) {
            set.add(this.i);
            list.add(new k(this, 8));
        }
        if (this.j.f() && !set.contains(this.j)) {
            set.add(this.j);
            list.add(new k(this, 4));
        }
        if (this.k.f() && !set.contains(this.k)) {
            set.add(this.k);
            list.add(new k(this, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final int h(com.d.c.f.d dVar) {
        if (a().J()) {
            return -1;
        }
        int b2 = (int) a().b(com.d.c.a.a.R, Y().d_(), dVar);
        com.d.c.f.a.a b3 = b(dVar);
        int t = b2 - (((int) b3.t()) + ((int) b3.v()));
        com.d.c.f.a.h o = o(dVar);
        int t2 = t - (((int) o.t()) + ((int) o.v()));
        if (t2 >= 0) {
            return t2;
        }
        return -1;
    }

    @Override // com.d.i.c
    protected final boolean h() {
        return false;
    }
}
