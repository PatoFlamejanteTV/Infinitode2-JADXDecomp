package com.d.f;

import com.d.e.v;
import com.d.i.aa;
import com.d.i.ab;
import com.d.i.c;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/f/i.class */
public final class i extends com.d.i.c {

    /* renamed from: a, reason: collision with root package name */
    private int f1190a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1191b = false;
    private int c;
    private com.d.i.j d;
    private int e;
    private int f;

    @Override // com.d.i.c
    public final com.d.i.c c() {
        i iVar = new i();
        iVar.a(a());
        iVar.a(ai());
        return iVar;
    }

    @Override // com.d.i.c
    public final boolean b_() {
        return a().J() || !a().d(com.d.c.a.a.R);
    }

    private d p() {
        return (d) U().U();
    }

    private j q() {
        return (j) U();
    }

    @Override // com.d.i.c
    public final void a_(v vVar, int i) {
        boolean z = vVar.r() && p().a().as();
        int i2 = 0;
        int i3 = 0;
        if (z) {
            i2 = vVar.A();
            i3 = vVar.z();
            g(vVar);
            v(vVar);
            vVar.b(vVar.A() + j());
            vVar.a(vVar.z() + o());
        }
        super.a_(vVar, i);
        if (z) {
            if (f(vVar)) {
                if (p().j() == this) {
                    p().e(true);
                } else {
                    e(true);
                }
            }
            vVar.b(i2);
            vVar.a(i3);
        }
    }

    private boolean f(v vVar) {
        aa a2 = vVar.p().a(vVar, this);
        if (aa() + as() < a2.a()) {
            return false;
        }
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            int e = ((f) W.next()).e(vVar);
            if (e != Integer.MIN_VALUE && e < a2.a()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.d.i.f
    public final void a(v vVar, com.d.i.j jVar) {
        if (p().a().as()) {
            this.d = new com.d.i.j(vVar, aa());
            this.d.a(jVar);
            if (jVar != null) {
                jVar.a(vVar, aa());
                jVar.b(vVar, aa() + as());
            }
            Iterator<com.d.i.f> W = W();
            while (W.hasNext()) {
                W.next().a(vVar, this.d);
            }
            if (jVar != null && this.d.c()) {
                a(vVar, jVar, this.d, j(), o());
                return;
            }
            return;
        }
        super.a(vVar, jVar);
    }

    private void g(v vVar) {
        int i = 0;
        Iterator<com.d.i.f> W = W();
        while (W.hasNext()) {
            f fVar = (f) W.next();
            int t = ((int) fVar.o((com.d.c.f.d) vVar).t()) + ((int) fVar.b((com.d.c.f.d) vVar).t());
            if (t > i) {
                i = t;
            }
        }
        this.e = i;
    }

    private void v(v vVar) {
        int v;
        int i = 0;
        int ak = ak();
        int g = q().g();
        List d = q().d();
        if (d.size() > 0 && ak < d.size()) {
            List a2 = ((c) d.get(ak)).a();
            for (int i2 = 0; i2 < a2.size(); i2++) {
                f fVar = (f) a2.get(i2);
                if (fVar != null && fVar != f.f1185a && ((ak >= g - 1 || q().a(ak + 1, i2) != fVar) && (v = ((int) fVar.o((com.d.c.f.d) vVar).v()) + ((int) fVar.b((com.d.c.f.d) vVar).v())) > i)) {
                    i = v;
                }
            }
        }
        this.f = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final void a(v vVar, int i) {
        l(vVar);
        j q = q();
        if (q.o()) {
            q.g(vVar);
            q.a(false);
        }
        if (F() != 4) {
            Iterator<com.d.i.f> W = W();
            while (W.hasNext()) {
                b(vVar, (f) W.next(), 0);
            }
        }
    }

    private void w(v vVar) {
        int i;
        int[] iArr = new int[V()];
        int i2 = Integer.MIN_VALUE;
        boolean z = false;
        for (int i3 = 0; i3 < V(); i3++) {
            f fVar = (f) k(i3);
            if (fVar.j() == com.d.c.a.c.g) {
                int d = fVar.d(vVar);
                iArr[i3] = d;
                if (d > i2) {
                    i2 = d;
                }
                z = true;
            }
        }
        if (z) {
            for (int i4 = 0; i4 < V(); i4++) {
                f fVar2 = (f) k(i4);
                if (fVar2.j() == com.d.c.a.c.g && (i = i2 - iArr[i4]) != 0) {
                    if (vVar.r() && fVar2.c(vVar, i)) {
                        a(vVar, fVar2, i);
                    } else {
                        fVar2.c(i);
                        fVar2.t(fVar2.as() + i);
                    }
                }
            }
            b(i2 - aa());
            a(true);
        }
    }

    private boolean x(v vVar) {
        com.d.c.a.c j;
        int a2;
        boolean z = false;
        int ak = ak();
        int g = q().g();
        List d = q().d();
        if (d.size() > 0 && ak < d.size()) {
            List a3 = ((c) d.get(ak)).a();
            for (int i = 0; i < a3.size(); i++) {
                f fVar = (f) a3.get(i);
                if (fVar != null && fVar != f.f1185a && ((ak >= g - 1 || q().a(ak + 1, i) != fVar) && (((j = fVar.j()) == com.d.c.a.c.al || j == com.d.c.a.c.l) && (a2 = a(fVar, j)) > 0))) {
                    if (vVar.r() && fVar.c(vVar, a2)) {
                        int as = fVar.as();
                        a(vVar, fVar, a2);
                        if (as + a2 != fVar.as()) {
                            z = true;
                        }
                    } else {
                        fVar.c(a2);
                        fVar.t(fVar.as() + a2);
                    }
                }
            }
        }
        return z;
    }

    private int a(f fVar, com.d.c.a.c cVar) {
        int aa;
        if (fVar.a().an() == 1) {
            aa = as() - fVar.M();
        } else {
            aa = (aa() + as()) - (fVar.aa() + fVar.M());
        }
        if (cVar == com.d.c.a.c.al) {
            return aa / 2;
        }
        return aa;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final void a(v vVar, com.d.c.f.a.a aVar, com.d.c.f.a.h hVar, com.d.c.f.a.h hVar2) {
        if (f() > 0) {
            t(f());
        }
        w(vVar);
        c((com.d.c.f.d) vVar);
        if (x(vVar)) {
            c((com.d.c.f.d) vVar);
        }
        if (!t()) {
            y(vVar);
        }
        s();
    }

    private void c(com.d.c.f.d dVar) {
        int i;
        int aa;
        int c;
        int aa2 = aa();
        if (as() != 0) {
            i = aa2 + as();
        } else {
            i = aa2;
        }
        if (r() && (c = p().c(dVar)) > 0 && c > i) {
            i = c;
        }
        int ak = ak();
        int g = q().g();
        List d = q().d();
        if (d.size() > 0 && ak < d.size()) {
            List a2 = ((c) d.get(ak)).a();
            for (int i2 = 0; i2 < a2.size(); i2++) {
                f fVar = (f) a2.get(i2);
                if (fVar != null && fVar != f.f1185a && ((ak >= g - 1 || q().a(ak + 1, i2) != fVar) && (aa = fVar.aa() + fVar.as()) > i)) {
                    i = aa;
                }
            }
        }
        t(i - aa2);
    }

    private boolean r() {
        p();
        j q = q();
        return d.b(q, true) == null && q.k(q.V() - 1) == this;
    }

    private void y(v vVar) {
        int i = 0;
        int ak = ak();
        int g = q().g();
        List d = q().d();
        if (d.size() > 0 && ak < d.size()) {
            List a2 = ((c) d.get(ak)).a();
            for (int i2 = 0; i2 < a2.size(); i2++) {
                f fVar = (f) a2.get(i2);
                if (fVar != null && fVar != f.f1185a && (ak >= g - 1 || q().a(ak + 1, i2) != fVar)) {
                    Rectangle c = fVar.c(fVar.ab(), fVar.aa(), vVar);
                    int i3 = c.y + c.height;
                    if (i3 > i) {
                        i = i3;
                    }
                }
            }
        }
        if (i > 0) {
            b(i - aa());
        }
        a(true);
    }

    private void s() {
        int ak = ak();
        int g = q().g();
        List d = q().d();
        if (d.size() > 0 && ak < d.size()) {
            List a2 = ((c) d.get(ak)).a();
            for (int i = 0; i < a2.size(); i++) {
                f fVar = (f) a2.get(i);
                if (fVar != null && fVar != f.f1185a && (ak >= g - 1 || q().a(ak + 1, i) != fVar)) {
                    if (fVar.a().an() == 1) {
                        fVar.t(as());
                    } else {
                        fVar.t((aa() + as()) - fVar.aa());
                    }
                }
            }
        }
    }

    private void a(v vVar, f fVar, int i) {
        int Q = fVar.Q();
        fVar.c(vVar);
        fVar.b(vVar, Q);
        b(vVar, fVar, i);
    }

    private static void b(v vVar, f fVar, int i) {
        fVar.r(vVar);
        fVar.B();
        fVar.a_(vVar, i);
    }

    @Override // com.d.i.c
    public final void a(v vVar, com.d.i.c cVar, int i) {
        n(0);
        o(cVar.as() + p().a().g(vVar));
        vVar.a(0, an() - i);
    }

    public final int d() {
        return this.f1190a;
    }

    private void b(int i) {
        this.f1190a = i;
    }

    @Override // com.d.i.c
    protected final boolean n() {
        return true;
    }

    @Override // com.d.i.f
    public final void c(ab abVar) {
    }

    @Override // com.d.i.f
    public final void b(ab abVar) {
    }

    @Override // com.d.i.c, com.d.i.f
    public final void c(v vVar) {
        super.c(vVar);
        a(false);
        q().a(true);
        a((com.d.i.j) null);
    }

    private boolean t() {
        return this.f1191b;
    }

    private void a(boolean z) {
        this.f1191b = z;
    }

    @Override // com.d.i.c
    protected final String a_() {
        if (t()) {
            return "(baseline=" + d() + ") ";
        }
        return "";
    }

    public final int f() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final com.d.i.j g() {
        return this.d;
    }

    private void a(com.d.i.j jVar) {
        this.d = jVar;
    }

    public final int j() {
        return this.e;
    }

    public final int o() {
        return this.f;
    }

    @Override // com.d.i.f
    public final int a(v vVar, com.d.c.a.c cVar, boolean z) {
        aa a2;
        int a3 = super.a(vVar, cVar, z);
        if (vVar.r() && a().am() && (a2 = vVar.p().a(vVar, aa() + a3)) != null) {
            int i = 0;
            Iterator<com.d.i.f> W = W();
            while (W.hasNext()) {
                com.d.c.f.a.a p = ((f) W.next()).p();
                if (p != null) {
                    i = Math.max(i, ((int) p.t()) / 2);
                }
            }
            int b2 = a2.b() - (((aa() + a3) + ((int) n((com.d.c.f.d) vVar).t())) - i);
            if (b2 > 0) {
                o(an() + b2);
                a3 += b2;
            }
        }
        return a3;
    }

    @Override // com.d.i.c
    protected final com.d.i.c a(c.b bVar) {
        return null;
    }
}
