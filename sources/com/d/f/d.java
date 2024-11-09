package com.d.f;

import com.d.e.v;
import com.d.i.aa;
import com.d.i.ab;
import com.d.m.l;
import com.vladsch.flexmark.util.sequence.builder.Seg;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/f/d.class */
public final class d extends com.d.i.c {

    /* renamed from: a, reason: collision with root package name */
    private final List f1177a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private int[] f1178b;
    private InterfaceC0024d c;
    private List d;
    private int e;
    private boolean f;
    private com.d.i.j g;
    private int h;
    private int i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.d.f.d$d, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/f/d$d.class */
    public interface InterfaceC0024d {
        void a(v vVar);

        void b(v vVar);

        void a();
    }

    @Override // com.d.i.f
    public final boolean b() {
        return this.f;
    }

    public final void a(boolean z) {
        this.f = true;
    }

    @Override // com.d.i.c
    public final com.d.i.c c() {
        d dVar = new d();
        dVar.a(a());
        dVar.a(ai());
        return dVar;
    }

    public final void a(h hVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(hVar);
    }

    public final List d() {
        return this.d == null ? Collections.EMPTY_LIST : this.d;
    }

    public final int[] e() {
        return com.d.m.a.a(this.f1178b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int[] iArr) {
        this.f1178b = iArr;
    }

    public final int f() {
        return this.f1177a.size();
    }

    public final int a(int i) {
        return ((com.d.f.b) this.f1177a.get(i)).a();
    }

    public final int b(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i && i3 < f()) {
            i2 += a(i3);
            i3++;
        }
        return i3;
    }

    public final int c(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += a(i3);
        }
        return i2;
    }

    public final void d(int i) {
        com.d.f.b bVar = new com.d.f.b();
        bVar.a(i);
        this.f1177a.add(bVar);
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            ((j) W.next()).a(this.f1177a.size());
        }
    }

    @Override // com.d.i.f, com.d.e.ac
    public final void a(com.d.c.f.c cVar) {
        super.a(cVar);
        if (b()) {
            this.c = new c(this);
        } else if (a().a(com.d.c.a.a.am, com.d.c.a.c.e) || a().H()) {
            this.c = new a(this);
        } else {
            this.c = new b(this);
        }
    }

    @Override // com.d.i.c
    public final void a(v vVar) {
        if (!J()) {
            f(vVar);
            if (a().am()) {
                g(vVar);
            }
            this.c.a(vVar);
            f(true);
        }
    }

    public final void a(int i, int i2) {
        com.d.f.b bVar = new com.d.f.b();
        bVar.a(i2);
        this.f1177a.add(i, bVar);
        com.d.f.b bVar2 = (com.d.f.b) this.f1177a.get(i + 1);
        bVar2.a(bVar2.a() - i2);
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            ((j) W.next()).b(i);
        }
    }

    public final int a(com.d.c.f.d dVar, boolean z) {
        int i = 0;
        com.d.c.f.a.h n = n(dVar);
        if (!z || !a().K()) {
            i = 0 + ((int) n.w());
        }
        if (!z || !a().L()) {
            i += (int) n.u();
        }
        com.d.c.f.a.a b2 = b(dVar);
        int w = i + ((int) b2.w()) + ((int) b2.u());
        if (!a().am()) {
            com.d.c.f.a.h o = o(dVar);
            w = (int) (w + o.w() + o.u() + ((f() + 1) * a().f(dVar)));
        }
        return w;
    }

    public final List g() {
        return this.f1177a;
    }

    private void f(v vVar) {
        l(vVar);
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            ((j) W.next()).e(vVar);
        }
    }

    private void g(v vVar) {
        l(vVar);
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            ((j) W.next()).f(vVar);
        }
    }

    @Override // com.d.i.c
    protected final boolean h() {
        return false;
    }

    @Override // com.d.i.c
    public final void b(v vVar) {
        a(vVar);
        k(vVar);
        p();
        z(vVar);
        if (!au()) {
            g(false);
            d(vVar, d_());
        }
        this.c.b(vVar);
        A(vVar);
        v(vVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final void a(v vVar, int i, com.d.c.f.a.h hVar, com.d.c.f.a.a aVar) {
        if (I() <= d_() + a((com.d.c.f.d) vVar, true)) {
            super.a(vVar, i, hVar, aVar);
            return;
        }
        if (a().K()) {
            e(vVar, 0);
        }
        if (a().L()) {
            f(vVar, 0);
        }
    }

    private void v(v vVar) {
        boolean z = vVar.r() && a().as();
        int i = 0;
        int i2 = 0;
        if (z) {
            i = vVar.A();
            i2 = vVar.z();
            vVar.b(vVar.A() + ((int) o((com.d.c.f.d) vVar).t()) + ((int) b((com.d.c.f.d) vVar).t()) + a().g(vVar));
            vVar.a(vVar.z() + ((int) o((com.d.c.f.d) vVar).v()) + ((int) b((com.d.c.f.d) vVar).v()) + a().g(vVar));
        }
        super.b(vVar);
        if (z) {
            if (o()) {
                y(vVar);
                w(0);
                x(0);
            } else {
                w(vVar.A() - i);
                x(vVar.z() - i2);
            }
            vVar.b(i);
            vVar.a(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final void a(v vVar, int i) {
        l(vVar);
        if (vVar.r() && a().as()) {
            int w = w(vVar);
            int x = x(vVar);
            if (aa() + ao() + w + x + (x == 0 ? 0 : a().g(vVar)) > vVar.p().a(vVar, this).a()) {
                e(true);
            }
        }
        super.a(vVar, i);
    }

    private int w(v vVar) {
        int i = 0;
        if (V() > 0) {
            j jVar = (j) k(0);
            if (jVar.q()) {
                vVar.c(vVar.C() + 1);
                jVar.r(vVar);
                jVar.b(vVar);
                vVar.b(vVar.A() + jVar.as());
                i = jVar.as();
                jVar.c(vVar);
                vVar.c(vVar.C() - 1);
            }
        }
        return i;
    }

    private int x(v vVar) {
        int i = 0;
        if (V() > 0) {
            j jVar = (j) k(V() - 1);
            if (jVar.p()) {
                vVar.c(vVar.C() + 1);
                jVar.r(vVar);
                jVar.b(vVar);
                vVar.a(vVar.z() + jVar.as() + a().g(vVar));
                i = jVar.as();
                jVar.c(vVar);
                vVar.c(vVar.C() - 1);
            }
        }
        return i;
    }

    private boolean o() {
        com.d.i.f U = U();
        while (true) {
            com.d.i.f fVar = U;
            if (fVar != null) {
                if (fVar.a().q() && fVar.a().as()) {
                    return false;
                }
                U = fVar.U();
            } else {
                return true;
            }
        }
    }

    private void y(v vVar) {
        a(vVar, (com.d.i.j) null);
    }

    @Override // com.d.i.f
    public final void a(v vVar, com.d.i.j jVar) {
        this.g = new com.d.i.j(vVar, aa());
        this.g.a(jVar);
        if (jVar != null) {
            jVar.a(vVar, aa());
            jVar.b(vVar, aa() + as());
        }
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            W.next().a(vVar, this.g);
        }
        if (jVar != null && this.g.c()) {
            if (q() > 0 || r() > 0) {
                a(vVar, jVar, this.g, q(), r());
            }
        }
    }

    @Override // com.d.i.f
    public final void b(ab abVar) {
        if (this.g == null) {
            super.b(abVar);
        } else if (a().a(abVar, this)) {
            abVar.p().a(abVar, a(), i(abVar), j(abVar), a().a(abVar));
        }
    }

    @Override // com.d.i.f
    public final void c(ab abVar) {
        if (this.g == null) {
            super.c(abVar);
        } else if (a().a(abVar, this)) {
            abVar.p().a(abVar, a(), i(abVar), ad());
        }
    }

    private Rectangle i(ab abVar) {
        int a2;
        int b2;
        Rectangle j = j(abVar);
        com.d.i.i a3 = this.g.a(abVar.t());
        if (a3 == null) {
            l.g(Level.WARNING, "No content limit found");
            return j;
        }
        if (a3.a() == -1 || a3.b() == -1) {
            return j;
        }
        com.d.c.f.a.h o = o(abVar);
        com.d.c.f.a.a b3 = b((com.d.c.f.d) abVar);
        if (abVar.t() == this.g.a()) {
            a2 = j.y;
        } else {
            a2 = ((a3.a() - ((int) o.t())) - ((int) b3.t())) - a().g(abVar);
            if (V() > 0) {
                j jVar = (j) k(0);
                if (jVar.q()) {
                    a2 -= jVar.as();
                }
            }
        }
        if (abVar.t() == this.g.b()) {
            b2 = j.y + j.height;
        } else {
            b2 = a3.b() + ((int) o.v()) + ((int) b3.v()) + a().g(abVar);
            if (V() > 0) {
                j jVar2 = (j) k(V() - 1);
                if (jVar2.p()) {
                    b2 += jVar2.as();
                }
            }
        }
        j.y = a2;
        j.height = b2 - a2;
        return j;
    }

    public final void d(ab abVar) {
        com.d.i.i a2 = this.g.a(abVar.t());
        if (a2 != null) {
            a(abVar, a2);
            b(abVar, a2);
        }
    }

    private void a(ab abVar, com.d.i.i iVar) {
        int a2;
        if ((iVar.a() != -1 || abVar.t() == this.g.a()) && V() > 0) {
            j jVar = (j) k(0);
            if (jVar.q()) {
                if (!jVar.r()) {
                    jVar.c(jVar.aa());
                    jVar.d(true);
                }
                if (abVar.t() == this.g.a()) {
                    a2 = jVar.s();
                } else {
                    a2 = (iVar.a() - a().g(abVar)) - jVar.as();
                }
                int aa = a2 - jVar.aa();
                if (aa != 0) {
                    jVar.o(jVar.an() + aa);
                    jVar.B();
                    jVar.C();
                    jVar.c((com.d.c.f.d) abVar, false);
                }
            }
        }
    }

    private void b(ab abVar, com.d.i.i iVar) {
        int b2;
        if ((iVar.b() != -1 || abVar.t() == this.g.b()) && V() > 0) {
            j jVar = (j) k(V() - 1);
            if (jVar.p()) {
                if (!jVar.r()) {
                    jVar.c(jVar.aa());
                    jVar.d(true);
                }
                if (abVar.t() == this.g.b()) {
                    b2 = jVar.s();
                } else {
                    b2 = iVar.b();
                }
                int aa = b2 - jVar.aa();
                if (aa != 0) {
                    jVar.o(jVar.an() + aa);
                    jVar.B();
                    jVar.C();
                    jVar.c((com.d.c.f.d) abVar, false);
                }
            }
        }
    }

    private void z(v vVar) {
        aa a2;
        i i;
        if (vVar.r() && a().am() && (a2 = vVar.p().a(vVar, this)) != null && (i = i()) != null) {
            int i2 = 0;
            Iterator<com.d.i.f> W = i.W();
            while (W.hasNext()) {
                int t = ((int) ((f) W.next()).p().t()) / 2;
                if (t > i2) {
                    i2 = t;
                }
            }
            int b2 = a2.b() - ((aa() + ((int) n((com.d.c.f.d) vVar).t())) - i2);
            if (b2 > 0) {
                o(an() + b2);
                v(b2);
                B();
                vVar.a(0, b2);
            }
        }
    }

    private void p() {
        if (I() > Q()) {
            u((d_() + I()) - Q());
        } else if (a().a(com.d.c.a.a.ax, com.d.c.a.c.e) && H() < Q()) {
            u(d_() - (Q() - H()));
        }
    }

    public final i i() {
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            j jVar = (j) W.next();
            if (jVar.V() > 0) {
                return (i) jVar.k(0);
            }
        }
        return null;
    }

    public final i j() {
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            j jVar = (j) W.next();
            if (!jVar.q() && !jVar.p() && jVar.V() > 0) {
                return (i) jVar.k(0);
            }
        }
        return null;
    }

    private void A(v vVar) {
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            com.d.i.c cVar = (com.d.i.c) W.next();
            if (cVar.a().t()) {
                ((j) cVar).g(vVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final void a(v vVar, com.d.c.f.a.a aVar, com.d.c.f.a.h hVar, com.d.c.f.a.h hVar2) {
        super.a(vVar, aVar, hVar, hVar2);
        if (V() > 0) {
            t(as() + a().g(vVar));
        }
    }

    @Override // com.d.i.c, com.d.i.f
    public final void c(v vVar) {
        super.c(vVar);
        this.g = null;
        this.c.a();
    }

    @Override // com.d.i.c
    protected final int a(com.d.c.f.d dVar) {
        if (a().H()) {
            return -1;
        }
        int b2 = (int) a().b(com.d.c.a.a.ax, Y().d_(), dVar);
        com.d.c.f.a.a b3 = b(dVar);
        int w = b2 - (((int) b3.w()) + ((int) b3.u()));
        if (!a().am()) {
            com.d.c.f.a.h o = o(dVar);
            w -= ((int) o.w()) + ((int) o.u());
        }
        if (w >= 0) {
            return w;
        }
        return -1;
    }

    public final h e(int i) {
        List<h> d = d();
        if (d.size() == 0) {
            return null;
        }
        int i2 = 0;
        for (h hVar : d) {
            int ao = i2 + hVar.a().ao();
            i2 = ao;
            if (ao > i) {
                return hVar;
            }
        }
        return null;
    }

    public final Rectangle a(com.d.c.f.d dVar, int i) {
        int b2 = b(i);
        int f = a().f(dVar);
        int g = a().g(dVar);
        Rectangle c2 = c(ab(), aa(), dVar);
        c2.y += g;
        c2.height -= g << 1;
        c2.x += this.f1178b[b2] + f;
        return c2;
    }

    @Override // com.d.i.f
    public final com.d.c.f.a.a b(com.d.c.f.d dVar) {
        if (a().am()) {
            return com.d.c.f.a.a.f1074a;
        }
        return super.b(dVar);
    }

    public final int c(com.d.c.f.d dVar) {
        int h;
        if (!au() && (h = h(dVar)) != -1) {
            return (((aa() + h) - ((int) b(dVar).v())) - ((int) o(dVar).v())) - a().g(dVar);
        }
        return -1;
    }

    @Override // com.d.i.c
    protected final boolean k() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static j a(j jVar, boolean z) {
        j jVar2 = (j) jVar.S();
        j jVar3 = jVar2;
        if (jVar2 == null) {
            return null;
        }
        while (jVar3 != null && jVar3.g() <= 0 && z) {
            jVar3 = (j) jVar3.S();
        }
        return jVar3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static j b(j jVar, boolean z) {
        j jVar2 = (j) jVar.T();
        j jVar3 = jVar2;
        if (jVar2 == null) {
            return null;
        }
        while (jVar3 != null && jVar3.g() <= 0 && z) {
            jVar3 = (j) jVar3.T();
        }
        return jVar3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final f a(f fVar) {
        j jVar;
        f a2;
        int e = fVar.e();
        int i = 0;
        if (e > 0) {
            jVar = fVar.g();
            i = e - 1;
        } else {
            j a3 = a(fVar.g(), true);
            jVar = a3;
            if (a3 != null) {
                i = jVar.g() - 1;
            }
        }
        if (jVar != null) {
            int b2 = b(fVar.d());
            do {
                a2 = jVar.a(i, b2);
                b2--;
                if (a2 != f.f1185a) {
                    break;
                }
            } while (b2 >= 0);
            if (a2 == f.f1185a) {
                return null;
            }
            return a2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final f b(f fVar) {
        j jVar;
        f a2;
        int e = (fVar.e() + fVar.a().an()) - 1;
        int i = 0;
        if (e < fVar.g().g() - 1) {
            jVar = fVar.g();
            i = e + 1;
        } else {
            j b2 = b(fVar.g(), true);
            jVar = b2;
            if (b2 != null) {
                i = 0;
            }
        }
        if (jVar != null) {
            int b3 = b(fVar.d());
            do {
                a2 = jVar.a(i, b3);
                b3--;
                if (a2 != f.f1185a) {
                    break;
                }
            } while (b3 >= 0);
            if (a2 == f.f1185a) {
                return null;
            }
            return a2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final f c(f fVar) {
        f a2;
        j g = fVar.g();
        int b2 = b(fVar.d());
        int i = b2;
        if (b2 == 0) {
            return null;
        }
        do {
            a2 = g.a(fVar.e(), i - 1);
            i--;
            if (a2 != f.f1185a) {
                break;
            }
        } while (i >= 0);
        if (a2 == f.f1185a) {
            return null;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final f d(f fVar) {
        f a2;
        int b2 = b(fVar.d() + fVar.a().ao());
        if (b2 < f() && (a2 = fVar.g().a(fVar.e(), b2)) != f.f1185a) {
            return a2;
        }
        return null;
    }

    @Override // com.d.i.c
    public final int d(com.d.c.f.d dVar) {
        int i = 0;
        boolean z = false;
        Iterator<com.d.i.f> W = W();
        while (true) {
            if (!W.hasNext()) {
                break;
            }
            Iterator<com.d.i.f> W2 = ((j) W.next()).W();
            if (W2.hasNext()) {
                i iVar = (i) W2.next();
                z = true;
                i = (iVar.aa() + iVar.d()) - aa();
                break;
            }
        }
        if (!z) {
            i = as();
        }
        return i;
    }

    @Override // com.d.i.c
    protected final int l() {
        return this.e;
    }

    private void v(int i) {
        this.e = i;
    }

    public final boolean m() {
        return this.g != null;
    }

    public final boolean e(ab abVar) {
        return m() && this.g.a() == abVar.t();
    }

    private int q() {
        return this.h;
    }

    private void w(int i) {
        this.h = i;
    }

    private int r() {
        return this.i;
    }

    private void x(int i) {
        this.i = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/f/d$c.class */
    public static class c extends a {
        public c(d dVar) {
            super(dVar);
        }

        @Override // com.d.f.d.a
        protected final int c() {
            return 0;
        }

        @Override // com.d.f.d.a, com.d.f.d.InterfaceC0024d
        public final void a(v vVar) {
            super.a(vVar);
            a.C0023a[] b2 = b();
            if (b2.length == 3) {
                a.C0023a c0023a = b2[1];
                if (c0023a.a().c() && c0023a.d() == 0) {
                    return;
                }
                if (b2[0].c() > b2[2].c()) {
                    b2[2] = b2[0];
                    return;
                }
                if (b2[2].c() > b2[0].c()) {
                    b2[0] = b2[2];
                    return;
                }
                a.C0023a c0023a2 = new a.C0023a();
                c0023a2.a(Math.max(b2[0].c(), b2[2].c()));
                c0023a2.c(c0023a2.c());
                c0023a2.b(Math.max(b2[0].d(), b2[2].d()));
                c0023a2.d(c0023a2.d());
                b2[0] = c0023a2;
                b2[2] = c0023a2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/f/d$b.class */
    public static class b implements InterfaceC0024d {

        /* renamed from: a, reason: collision with root package name */
        private final d f1183a;

        /* renamed from: b, reason: collision with root package name */
        private List f1184b;

        public b(d dVar) {
            this.f1183a = dVar;
        }

        @Override // com.d.f.d.InterfaceC0024d
        public final void a() {
            this.f1184b = null;
        }

        private void b() {
            this.f1184b = new ArrayList(this.f1183a.f());
            for (int i = 0; i < this.f1183a.f(); i++) {
                this.f1184b.add(new com.d.c.f.i());
            }
        }

        private int c(v vVar) {
            b();
            d dVar = this.f1183a;
            int i = 0;
            int f = dVar.f();
            int i2 = 0;
            for (h hVar : dVar.d()) {
                int ao = hVar.a().ao();
                com.d.c.f.i a2 = hVar.a().a(vVar, com.d.c.a.a.ax);
                com.d.c.f.i iVar = a2;
                if (a2.c() && hVar.c() != null) {
                    iVar = hVar.c().a().a(vVar, com.d.c.a.a.ax);
                }
                long j = 0;
                if (iVar.d() && iVar.a() > 0) {
                    j = Math.min(iVar.a(), 1073741823L);
                }
                int i3 = 0;
                int i4 = 0;
                while (i3 < ao) {
                    if (i + i4 >= f) {
                        dVar.d(ao - i3);
                        f++;
                        this.f1184b.add(new com.d.c.f.i());
                    }
                    int a3 = dVar.a(i + i4);
                    if ((iVar.d() || iVar.e()) && iVar.a() > 0) {
                        this.f1184b.set(i + i4, new com.d.c.f.i(iVar.a() * a3, iVar.b()));
                        i2 = (int) (i2 + (j * a3));
                    }
                    i3 += a3;
                    i4++;
                }
                i += i4;
            }
            int i5 = 0;
            i i6 = this.f1183a.i();
            if (i6 != null) {
                Iterator<com.d.i.f> W = i6.W();
                while (W.hasNext()) {
                    f fVar = (f) W.next();
                    com.d.c.f.i e = fVar.e((com.d.c.f.d) vVar);
                    int ao2 = fVar.a().ao();
                    long j2 = 0;
                    if (e.d() && e.a() > 0) {
                        j2 = e.a();
                    }
                    int i7 = 0;
                    int i8 = 0;
                    while (i7 < ao2) {
                        int a4 = this.f1183a.a(i5 + i8);
                        if (((com.d.c.f.i) this.f1184b.get(i5 + i8)).c() && !e.c()) {
                            this.f1184b.set(i5 + i8, new com.d.c.f.i(e.a() * a4, e.b()));
                            i2 = (int) (i2 + (j2 * a4));
                        }
                        i7 += a4;
                        i8++;
                    }
                    i5 += i8;
                }
            }
            return i2;
        }

        @Override // com.d.f.d.InterfaceC0024d
        public final void a(v vVar) {
            int a2 = this.f1183a.a((com.d.c.f.d) vVar, true);
            this.f1183a.k(vVar);
            this.f1183a.g(false);
            int c = c(vVar) + a2;
            d dVar = this.f1183a;
            dVar.i(Math.max(c, dVar.Q()));
            this.f1183a.h(this.f1183a.I());
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.f1184b.size()) {
                    break;
                }
                if (((com.d.c.f.i) this.f1184b.get(i)).d()) {
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                this.f1183a.h(Seg.MAX_TEXT_OFFSET);
            }
        }

        @Override // com.d.f.d.InterfaceC0024d
        public final void b(v vVar) {
            int Q = this.f1183a.Q() - this.f1183a.a((com.d.c.f.d) vVar, false);
            int i = Q;
            int f = this.f1183a.f();
            long[] jArr = new long[f];
            for (int i2 = 0; i2 < jArr.length; i2++) {
                jArr[i2] = -1;
            }
            for (int i3 = 0; i3 < f; i3++) {
                com.d.c.f.i iVar = (com.d.c.f.i) this.f1184b.get(i3);
                if (iVar.d()) {
                    jArr[i3] = iVar.a();
                    i = (int) (i - iVar.a());
                }
            }
            if (i > 0) {
                int i4 = 0;
                for (int i5 = 0; i5 < f; i5++) {
                    com.d.c.f.i iVar2 = (com.d.c.f.i) this.f1184b.get(i5);
                    if (iVar2.e()) {
                        i4 = (int) (i4 + iVar2.a());
                    }
                }
                int i6 = (Q * i4) / 100;
                int i7 = i6;
                if (i6 > i) {
                    i7 = i;
                }
                for (int i8 = 0; i > 0 && i8 < f; i8++) {
                    com.d.c.f.i iVar3 = (com.d.c.f.i) this.f1184b.get(i8);
                    if (iVar3.e()) {
                        long a2 = (i7 * iVar3.a()) / i4;
                        i = (int) (i - a2);
                        jArr[i8] = a2;
                    }
                }
            }
            if (i > 0) {
                int i9 = 0;
                for (int i10 = 0; i10 < f; i10++) {
                    if (((com.d.c.f.i) this.f1184b.get(i10)).c()) {
                        i9++;
                    }
                }
                for (int i11 = 0; i > 0 && i11 < f; i11++) {
                    if (((com.d.c.f.i) this.f1184b.get(i11)).c()) {
                        int i12 = i / i9;
                        i -= i12;
                        jArr[i11] = i12;
                        i9--;
                    }
                }
            }
            for (int i13 = 0; i13 < f; i13++) {
                if (jArr[i13] < 0) {
                    jArr[i13] = 0;
                }
            }
            if (i > 0) {
                int i14 = f;
                int i15 = f;
                while (true) {
                    int i16 = i15;
                    i15--;
                    if (i16 <= 0) {
                        break;
                    }
                    int i17 = i / i14;
                    i -= i17;
                    i14--;
                    jArr[i15] = jArr[i15] + i17;
                }
            }
            int i18 = 0;
            int f2 = this.f1183a.a().f(vVar);
            int[] iArr = new int[f + 1];
            for (int i19 = 0; i19 < f; i19++) {
                iArr[i19] = i18;
                i18 = (int) (i18 + jArr[i19] + f2);
            }
            iArr[iArr.length - 1] = i18;
            this.f1183a.a(iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/f/d$a.class */
    public static class a implements InterfaceC0024d {

        /* renamed from: a, reason: collision with root package name */
        private final d f1179a;

        /* renamed from: b, reason: collision with root package name */
        private C0023a[] f1180b;
        private List c;

        public a(d dVar) {
            this.f1179a = dVar;
        }

        @Override // com.d.f.d.InterfaceC0024d
        public final void a() {
            this.f1180b = null;
            this.c = null;
        }

        protected final C0023a[] b() {
            return this.f1180b;
        }

        private void c(v vVar) {
            this.f1180b = new C0023a[this.f1179a.f()];
            for (int i = 0; i < this.f1180b.length; i++) {
                this.f1180b[i] = new C0023a();
                this.f1180b[i].a(c());
                this.f1180b[i].b(c());
            }
            this.c = new ArrayList();
            d dVar = this.f1179a;
            int f = dVar.f();
            int i2 = 0;
            for (h hVar : dVar.d()) {
                int ao = hVar.a().ao();
                com.d.c.f.i a2 = hVar.a().a(vVar, com.d.c.a.a.ax);
                com.d.c.f.i iVar = a2;
                if (a2.c() && hVar.c() != null) {
                    iVar = hVar.c().a().a(vVar, com.d.c.a.a.ax);
                }
                if ((iVar.d() && iVar.a() == 0) || (iVar.e() && iVar.a() == 0)) {
                    iVar = new com.d.c.f.i();
                }
                int b2 = dVar.b(i2);
                if (!iVar.c() && ao == 1 && b2 < f && dVar.a(b2) == 1) {
                    this.f1180b[b2].a(iVar);
                    if (iVar.d() && this.f1180b[b2].d() < iVar.a()) {
                        this.f1180b[b2].b(iVar.a());
                    }
                }
                i2 += ao;
            }
            for (int i3 = 0; i3 < f; i3++) {
                a(vVar, i3);
            }
        }

        protected int c() {
            return 1;
        }

        private void a(v vVar, int i) {
            C0023a c0023a = this.f1180b[i];
            Iterator<com.d.i.f> W = this.f1179a.W();
            while (W.hasNext()) {
                j jVar = (j) W.next();
                int g = jVar.g();
                for (int i2 = 0; i2 < g; i2++) {
                    f a2 = jVar.a(i2, i);
                    if (a2 != f.f1185a && a2 != null) {
                        if (a2.a().ao() == 1) {
                            c0023a.a(Math.max(c0023a.c(), c()));
                            c0023a.b(Math.max(c0023a.d(), c()));
                            a2.a(vVar);
                            if (a2.I() > c0023a.c()) {
                                c0023a.a(a2.I());
                            }
                            if (a2.H() > c0023a.d()) {
                                c0023a.b(a2.H());
                            }
                            com.d.c.f.i f = a2.f((com.d.c.f.d) vVar);
                            f.a(Math.min(1073741823L, Math.max(0L, f.a())));
                            switch (f.b()) {
                                case 2:
                                    if (f.a() > 0 && !c0023a.a().e()) {
                                        if (!c0023a.a().d()) {
                                            c0023a.a(f);
                                        } else if (f.a() > c0023a.a().a()) {
                                            c0023a.a().a(f.a());
                                        }
                                        if (f.a() > c0023a.d()) {
                                            c0023a.b(f.a());
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                    break;
                                case 3:
                                    if (f.a() > 0 && (!c0023a.a().e() || f.a() > c0023a.a().a())) {
                                        c0023a.a(f);
                                        break;
                                    }
                                    break;
                            }
                        } else if (i == 0 || jVar.a(i2, i - 1) != a2) {
                            c0023a.a(Math.max(c0023a.c(), c()));
                            c0023a.b(Math.max(c0023a.d(), c()));
                            this.c.add(a2);
                        }
                    }
                }
            }
            c0023a.b(Math.max(c0023a.d(), c0023a.c()));
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to find 'out' block for switch in B:18:0x0103. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:30:0x016a  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x017f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private long d(com.d.e.v r8) {
            /*
                Method dump skipped, instructions count: 1454
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.d.f.d.a.d(com.d.e.v):long");
        }

        @Override // com.d.f.d.InterfaceC0024d
        public void a(v vVar) {
            d dVar = this.f1179a;
            c(vVar);
            C0023a[] c0023aArr = this.f1180b;
            long d = d(vVar);
            long j = 0;
            long j2 = 0;
            long j3 = 0;
            long j4 = 0;
            int i = 100;
            for (int i2 = 0; i2 < c0023aArr.length; i2++) {
                j += c0023aArr[i2].e();
                j2 += c0023aArr[i2].f();
                if (c0023aArr[i2].b().e()) {
                    long min = Math.min(c0023aArr[i2].b().a(), i);
                    i = (int) (i - min);
                    j3 = Math.max((c0023aArr[i2].f() * 100) / Math.max(min, 1L), j3);
                } else {
                    j4 += c0023aArr[i2].f();
                }
            }
            long max = Math.max(Math.max(Math.max(((j4 * 100) + 50) / Math.max(i, 1), j2), j3), d);
            int a2 = dVar.a((com.d.c.f.d) vVar, true);
            long j5 = j + a2;
            long j6 = max + a2;
            com.d.c.f.i a3 = dVar.a().a(vVar, com.d.c.a.a.ax);
            if (a3.d() && a3.a() > 0) {
                dVar.k(vVar);
                long max2 = Math.max(j5, dVar.d_() + dVar.a((com.d.c.f.d) vVar, true));
                j5 = max2;
                j6 = max2;
            }
            dVar.h((int) Math.min(j6, 1073741823L));
            dVar.i((int) Math.min(j5, 1073741823L));
        }

        @Override // com.d.f.d.InterfaceC0024d
        public final void b(v vVar) {
            d dVar = this.f1179a;
            int Q = dVar.Q() - dVar.a((com.d.c.f.d) vVar, false);
            int i = Q;
            int f = dVar.f();
            boolean z = false;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            C0023a[] c0023aArr = this.f1180b;
            for (int i8 = 0; i8 < f; i8++) {
                long e = c0023aArr[i8].e();
                c0023aArr[i8].e(e);
                i = (int) (i - e);
                com.d.c.f.i b2 = c0023aArr[i8].b();
                switch (b2.b()) {
                    case 1:
                        i2++;
                        i4 = (int) (i4 + c0023aArr[i8].f());
                        i7 = (int) (i7 + e);
                        break;
                    case 2:
                        i3++;
                        i5 = (int) (i5 + c0023aArr[i8].f());
                        break;
                    case 3:
                        z = true;
                        i6 = (int) (i6 + b2.a());
                        break;
                }
            }
            if (i > 0 && z) {
                for (int i9 = 0; i9 < f; i9++) {
                    com.d.c.f.i b3 = c0023aArr[i9].b();
                    if (b3.e()) {
                        long max = Math.max(c0023aArr[i9].e(), b3.b(Q));
                        i = (int) (i + (c0023aArr[i9].g() - max));
                        c0023aArr[i9].e(max);
                    }
                }
                if (i6 > 100) {
                    int i10 = (Q * (i6 - 100)) / 100;
                    for (int i11 = f - 1; i11 >= 0; i11--) {
                        if (c0023aArr[i11].b().e()) {
                            long g = c0023aArr[i11].g();
                            long min = Math.min(g, i10);
                            i10 = (int) (i10 - min);
                            long max2 = Math.max(c0023aArr[i11].e(), g - min);
                            i = (int) (i + (g - max2));
                            c0023aArr[i11].e(max2);
                        }
                    }
                }
            }
            if (i > 0) {
                for (int i12 = 0; i12 < f; i12++) {
                    com.d.c.f.i b4 = c0023aArr[i12].b();
                    if (b4.d() && b4.a() > c0023aArr[i12].g()) {
                        i = (int) (i + (c0023aArr[i12].g() - b4.a()));
                        c0023aArr[i12].e(b4.a());
                    }
                }
            }
            if (i > 0 && i2 > 0) {
                i += i7;
                for (int i13 = 0; i13 < f; i13++) {
                    if (c0023aArr[i13].b().c() && i4 != 0) {
                        long max3 = Math.max(c0023aArr[i13].g(), (i * c0023aArr[i13].f()) / i4);
                        i = (int) (i - max3);
                        i4 = (int) (i4 - c0023aArr[i13].f());
                        c0023aArr[i13].e(max3);
                    }
                }
            }
            if (i > 0 && i3 > 0) {
                for (int i14 = 0; i14 < f; i14++) {
                    if (c0023aArr[i14].b().d()) {
                        long f2 = (i * c0023aArr[i14].f()) / i5;
                        i = (int) (i - f2);
                        i5 = (int) (i5 - c0023aArr[i14].f());
                        c0023aArr[i14].e(c0023aArr[i14].g() + f2);
                    }
                }
            }
            if (i > 0 && z && i6 < 100) {
                for (int i15 = 0; i15 < f; i15++) {
                    com.d.c.f.i b5 = c0023aArr[i15].b();
                    if (b5.e()) {
                        long a2 = (i * b5.a()) / i6;
                        i = (int) (i - a2);
                        i6 = (int) (i6 - b5.a());
                        c0023aArr[i15].e(c0023aArr[i15].g() + a2);
                        if (i != 0 && i6 != 0) {
                        }
                    }
                }
            }
            if (i > 0) {
                int i16 = f;
                int i17 = f;
                while (true) {
                    int i18 = i17;
                    i17--;
                    if (i18 > 0) {
                        int i19 = i / i16;
                        i -= i19;
                        i16--;
                        c0023aArr[i17].e(c0023aArr[i17].g() + i19);
                    }
                }
            }
            if (i < 0) {
                if (i < 0) {
                    int i20 = 0;
                    for (int i21 = f - 1; i21 >= 0; i21--) {
                        if (c0023aArr[i21].b().c()) {
                            i20 = (int) (i20 + (c0023aArr[i21].g() - c0023aArr[i21].e()));
                        }
                    }
                    for (int i22 = f - 1; i22 >= 0 && i20 > 0; i22--) {
                        if (c0023aArr[i22].b().c()) {
                            long g2 = c0023aArr[i22].g() - c0023aArr[i22].e();
                            long j = (i * g2) / i20;
                            c0023aArr[i22].e(c0023aArr[i22].g() + j);
                            i = (int) (i - j);
                            i20 = (int) (i20 - g2);
                            if (i < 0) {
                            }
                        }
                    }
                }
                if (i < 0) {
                    int i23 = 0;
                    for (int i24 = f - 1; i24 >= 0; i24--) {
                        if (c0023aArr[i24].b().d()) {
                            i23 = (int) (i23 + (c0023aArr[i24].g() - c0023aArr[i24].e()));
                        }
                    }
                    for (int i25 = f - 1; i25 >= 0 && i23 > 0; i25--) {
                        if (c0023aArr[i25].b().d()) {
                            long g3 = c0023aArr[i25].g() - c0023aArr[i25].e();
                            long j2 = (i * g3) / i23;
                            c0023aArr[i25].e(c0023aArr[i25].g() + j2);
                            i = (int) (i - j2);
                            i23 = (int) (i23 - g3);
                            if (i < 0) {
                            }
                        }
                    }
                }
                if (i < 0) {
                    int i26 = 0;
                    for (int i27 = f - 1; i27 >= 0; i27--) {
                        if (c0023aArr[i27].b().e()) {
                            i26 = (int) (i26 + (c0023aArr[i27].g() - c0023aArr[i27].e()));
                        }
                    }
                    for (int i28 = f - 1; i28 >= 0 && i26 > 0; i28--) {
                        if (c0023aArr[i28].b().e()) {
                            long g4 = c0023aArr[i28].g() - c0023aArr[i28].e();
                            long j3 = (i * g4) / i26;
                            c0023aArr[i28].e(c0023aArr[i28].g() + j3);
                            i = (int) (i - j3);
                            i26 = (int) (i26 - g4);
                            if (i < 0) {
                            }
                        }
                    }
                }
            }
            int i29 = 0;
            int f3 = this.f1179a.a().f(vVar);
            int[] iArr = new int[f + 1];
            for (int i30 = 0; i30 < f; i30++) {
                iArr[i30] = i29;
                i29 = (int) (i29 + c0023aArr[i30].g() + f3);
            }
            iArr[iArr.length - 1] = i29;
            this.f1179a.a(iArr);
        }

        /* renamed from: com.d.f.d$a$a, reason: collision with other inner class name */
        /* loaded from: infinitode-2.jar:com/d/f/d$a$a.class */
        public static class C0023a {

            /* renamed from: a, reason: collision with root package name */
            private com.d.c.f.i f1181a = new com.d.c.f.i();

            /* renamed from: b, reason: collision with root package name */
            private com.d.c.f.i f1182b = new com.d.c.f.i();
            private long c = 1;
            private long d = 1;
            private long e = 0;
            private long f = 0;
            private long g = 0;

            public final com.d.c.f.i a() {
                return this.f1181a;
            }

            public final void a(com.d.c.f.i iVar) {
                this.f1181a = iVar;
            }

            public final com.d.c.f.i b() {
                return this.f1182b;
            }

            public final void b(com.d.c.f.i iVar) {
                this.f1182b = iVar;
            }

            public final long c() {
                return this.c;
            }

            public final void a(long j) {
                this.c = j;
            }

            public final long d() {
                return this.d;
            }

            public final void b(long j) {
                this.d = j;
            }

            public final long e() {
                return this.e;
            }

            public final void c(long j) {
                this.e = j;
            }

            public final long f() {
                return this.f;
            }

            public final void d(long j) {
                this.f = j;
            }

            public final long g() {
                return this.g;
            }

            public final void e(long j) {
                this.g = j;
            }
        }
    }
}
