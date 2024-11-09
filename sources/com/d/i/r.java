package com.d.i;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/i/r.class */
public final class r extends f implements com.d.e.s {

    /* renamed from: a, reason: collision with root package name */
    private int f1371a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1372b;
    private boolean c;
    private List<Object> d;
    private boolean e;
    private int f;
    private List<ad> g;
    private int h;

    public r(com.d.e.v vVar, Element element, com.d.c.f.c cVar, int i) {
        this();
        a(element);
        a(cVar);
        d(i);
        c(vVar, 0);
        d(vVar, 0);
        c(true);
        b(vVar);
    }

    private r() {
    }

    private void b(com.d.e.v vVar) {
        com.d.c.f.a.a b2 = b((com.d.c.f.d) vVar);
        com.d.c.f.a.h o = o(vVar);
        l e = a().e(vVar);
        t((int) Math.ceil(b2.t() + o.t() + e.a() + e.b() + o.v() + b2.v()));
    }

    public final int c() {
        return this.f1371a;
    }

    public final void a(int i) {
        this.f1371a = i;
    }

    public final int e() {
        if (this.d == null) {
            return 0;
        }
        return this.d.size();
    }

    public final void a(com.d.e.v vVar, Object obj) {
        a(vVar, obj, true);
    }

    public final void a(com.d.e.v vVar, Object obj, boolean z) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(obj);
        if (z && h()) {
            a(vVar);
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            fVar.f(this);
            fVar.r(vVar);
        } else {
            if (obj instanceof s) {
                ((s) obj).a(this);
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    private List<Object> n() {
        return this.d == null ? Collections.emptyList() : this.d;
    }

    public final Object b(int i) {
        if (this.d == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.d.get(i);
    }

    private int o() {
        return this.f;
    }

    public final void f() {
        if (e() > 0) {
            for (int e = e() - 1; e >= 0; e--) {
                Object b2 = b(e);
                if (b2 instanceof r) {
                    r rVar = (r) b2;
                    rVar.f();
                    if (rVar.h()) {
                        j(e);
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private boolean p() {
        return this.c;
    }

    public final void a(boolean z) {
        this.c = true;
    }

    public final boolean g() {
        return this.f1372b;
    }

    public final void b(boolean z) {
        this.f1372b = z;
    }

    public final boolean h() {
        return this.e;
    }

    public final void c(boolean z) {
        this.e = z;
    }

    public final void a(com.d.e.v vVar) {
        this.e = false;
        if (U() instanceof r) {
            r rVar = (r) U();
            if (rVar.h()) {
                rVar.a(vVar);
            }
        }
        b(true);
        if (a().O()) {
            vVar.a(this);
            Z().b(true);
            s(vVar);
        }
    }

    @Override // com.d.i.f
    public final void s(com.d.e.v vVar) {
        if (e() > 0) {
            for (int i = 0; i < e(); i++) {
                Object b2 = b(i);
                if (b2 instanceof f) {
                    f fVar = (f) b2;
                    fVar.b(vVar.o());
                    fVar.s(vVar);
                }
            }
        }
    }

    @Override // com.d.e.s
    public final void a(ab abVar) {
        if (!a().a(abVar, this)) {
            return;
        }
        Object a2 = abVar.p().a(com.d.d.q.BACKGROUND, this);
        b(abVar);
        c(abVar);
        if (abVar.l()) {
            e(abVar);
        }
        List<ad> q = q();
        for (ad adVar : q) {
            com.d.c.a.c c = adVar.c();
            if (c == com.d.c.a.c.bk || c == com.d.c.a.c.ax) {
                abVar.p().a(abVar, this, adVar);
            }
        }
        abVar.p().a(a2);
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (!(b2 instanceof s)) {
                if (b2 instanceof f) {
                    abVar.p().a(abVar.p().a(com.d.d.q.INLINE_CHILD_BOX, (f) b2));
                }
            } else {
                Object a3 = abVar.p().a(com.d.d.q.TEXT, this);
                ((s) b2).a(abVar);
                abVar.p().a(a3);
            }
        }
        Object a4 = abVar.p().a(com.d.d.q.BACKGROUND, this);
        for (ad adVar2 : q) {
            if (adVar2.c() == com.d.c.a.c.ad) {
                abVar.p().a(abVar, this, adVar2);
            }
        }
        abVar.p().a(a4);
    }

    @Override // com.d.i.f
    public final int ad() {
        int i = 5;
        if (this.f1372b) {
            i = 5 + 2;
        }
        if (this.c) {
            i += 8;
        }
        return i;
    }

    @Override // com.d.i.f
    public final Rectangle a(int i, int i2, com.d.c.f.d dVar) {
        float f = 0.0f;
        float f2 = 0.0f;
        if (this.f1372b || this.c) {
            com.d.c.f.a.h n = n(dVar);
            if (this.f1372b) {
                f = n.w();
            }
            if (this.c) {
                f2 = n.u();
            }
        }
        return new Rectangle((int) (i + f), (int) ((i2 - b(dVar).t()) - o(dVar).t()), (int) ((o() - f) - f2), as());
    }

    @Override // com.d.i.f
    public final Rectangle a(int i, int i2, com.d.c.f.d dVar, int i3, int i4) {
        Rectangle a2 = a(i, i2, dVar);
        float f = 0.0f;
        float f2 = 0.0f;
        if (this.f1372b || this.c) {
            com.d.c.f.a.h n = n(dVar);
            if (this.f1372b) {
                f = n.w();
            }
            if (this.c) {
                f2 = n.u();
            }
        }
        if (f2 > 0.0f) {
            a2.width = (int) (a2.width + f2);
        }
        if (f > 0.0f) {
            a2.x = (int) (a2.x - f);
            a2.width = (int) (a2.width + f);
        }
        a2.translate(i3, i4);
        return a2;
    }

    @Override // com.d.i.f
    public final Rectangle c(int i, int i2, com.d.c.f.d dVar) {
        com.d.c.f.a.a b2 = b(dVar);
        com.d.c.f.a.h o = o(dVar);
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        if (this.f1372b || this.c) {
            com.d.c.f.a.h n = n(dVar);
            if (this.f1372b) {
                f = n.w();
                f3 = b2.w();
                f5 = o.w();
            }
            if (this.c) {
                f2 = n.u();
                f4 = b2.u();
                f6 = o.u();
            }
        }
        return new Rectangle((int) (i + f + f3 + f5), (int) ((i2 - b2.t()) - o.t()), (int) ((((((o() - f) - f3) - f5) - f6) - f4) - f2), as());
    }

    public final int a(com.d.c.f.d dVar) {
        if (this.f1372b) {
            return g(dVar, 1);
        }
        return 0;
    }

    public final int c(com.d.c.f.d dVar) {
        if (this.c) {
            return g(dVar, 2);
        }
        return 0;
    }

    public final int i() {
        return this.f;
    }

    public final void c(int i) {
        this.f = i;
    }

    public final boolean j() {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (!(b2 instanceof s)) {
                if (b2 instanceof r) {
                    if (((r) b2).j()) {
                        return true;
                    }
                } else {
                    f fVar = (f) b2;
                    if (fVar.Q() > 0 || fVar.as() > 0) {
                        return true;
                    }
                }
            } else if (!((s) b2).c()) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(com.d.c.f.d dVar, Shape shape) {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (!(b2 instanceof r)) {
                if ((b2 instanceof f) && new com.d.e.d().a(dVar, shape, (f) b2)) {
                    return true;
                }
            } else if (((r) b2).b(dVar, shape)) {
                return true;
            }
        }
        return false;
    }

    private List<ad> q() {
        return this.g == null ? Collections.emptyList() : this.g;
    }

    public final void a(List<ad> list) {
        this.g = list;
    }

    private void b(List<f> list) {
        list.add(this);
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (b2 instanceof r) {
                ((r) b2).b(list);
            } else if (b2 instanceof f) {
                list.add((f) b2);
            }
        }
    }

    public final u k() {
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

    public final List<f> l() {
        b a2;
        ArrayList arrayList = new ArrayList();
        c cVar = (c) k().U();
        do {
            List<f> b2 = cVar.b(ai());
            for (int i = 0; i < b2.size(); i++) {
                ((r) b2.get(i)).b(arrayList);
            }
            if (!(cVar instanceof b) || d(arrayList)) {
                break;
            }
            a2 = a(cVar, arrayList);
            cVar = a2;
        } while (a2 != null);
        return arrayList;
    }

    private static b a(c cVar, List<f> list) {
        f U = cVar.U();
        int i = 0;
        while (true) {
            if (i >= U.V()) {
                break;
            }
            if (U.k(i) != cVar) {
                i++;
            } else {
                i++;
                break;
            }
        }
        while (i < U.V() && !(U.k(i) instanceof b)) {
            list.add(U.k(i));
            i++;
        }
        if (i == U.V()) {
            return null;
        }
        return (b) U.k(i);
    }

    private boolean a(f fVar) {
        if (fVar instanceof r) {
            r rVar = (r) fVar;
            if (ai() == rVar.ai() && rVar.p()) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean d(List<f> list) {
        return list.stream().anyMatch(this::a);
    }

    @Override // com.d.i.f
    public final List<f> b(Element element) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (b2 instanceof f) {
                f fVar = (f) b2;
                if (fVar.ai() == element) {
                    arrayList.add(fVar);
                }
                arrayList.addAll(fVar.b(element));
            }
        }
        return arrayList;
    }

    @Override // com.d.i.f
    public final Dimension m(com.d.c.f.d dVar) {
        Dimension m = super.m(dVar);
        n(am() - m.width);
        o(an() - m.height);
        List<f> l = l();
        for (int i = 0; i < l.size(); i++) {
            f fVar = l.get(i);
            fVar.n(fVar.am() + m.width);
            fVar.o(fVar.an() + m.height);
            fVar.B();
            fVar.C();
        }
        return m;
    }

    public final void a(List list, com.d.e.t tVar) {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if ((b2 instanceof f) && ((f) b2).af() == tVar) {
                list.add((f) b2);
                if (b2 instanceof r) {
                    ((r) b2).a(list, tVar);
                }
            }
        }
    }

    private void e(ab abVar) {
        abVar.p().a(abVar, this, com.d.c.d.h.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.f
    public final void t(com.d.e.v vVar) {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (b2 instanceof f) {
                ((f) b2).c(vVar);
            }
        }
    }

    @Override // com.d.i.f
    public final void c(f fVar) {
        if (this.d != null) {
            this.d.remove(fVar);
        }
    }

    @Override // com.d.i.f
    public final void j(int i) {
        if (this.d != null) {
            this.d.remove(i);
        }
    }

    @Override // com.d.i.f
    protected final f d(f fVar) {
        if (this.d == null) {
            return null;
        }
        for (int i = 0; i < this.d.size() - 1; i++) {
            if (this.d.get(i) == fVar) {
                if (i == 0) {
                    return null;
                }
                Object obj = this.d.get(i - 1);
                if (obj instanceof f) {
                    return (f) obj;
                }
                return null;
            }
        }
        return null;
    }

    @Override // com.d.i.f
    protected final f e(f fVar) {
        if (this.d == null) {
            return null;
        }
        for (int i = 0; i < this.d.size() - 1; i++) {
            if (this.d.get(i) == fVar) {
                Object obj = this.d.get(i + 1);
                if (obj instanceof f) {
                    return (f) obj;
                }
                return null;
            }
        }
        return null;
    }

    @Override // com.d.i.f
    public final void B() {
        u k = k();
        m(k.ab() + am());
        l(k.aa() + an());
    }

    @Override // com.d.i.f
    public final void C() {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (b2 instanceof f) {
                f fVar = (f) b2;
                fVar.B();
                fVar.C();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.f
    public final void a(com.d.c.f.d dVar, com.d.e.y yVar, boolean z) {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (b2 instanceof f) {
                com.d.e.y c = ((f) b2).c(dVar, z);
                a(yVar.b(), c.b());
                yVar.a().add(c.a());
            }
        }
    }

    public final void d(ab abVar) {
        for (int i = 0; i < e(); i++) {
            Object b2 = b(i);
            if (!(b2 instanceof s)) {
                if (b2 instanceof r) {
                    ((r) b2).d(abVar);
                }
            } else {
                s sVar = (s) b2;
                if (sVar.h()) {
                    sVar.b(abVar);
                }
            }
        }
    }

    public final s m() {
        if (e() == 0) {
            return null;
        }
        s sVar = null;
        for (int e = e() - 1; e >= 0; e--) {
            Object b2 = b(e);
            if (b2 instanceof s) {
                s sVar2 = (s) b2;
                sVar = sVar2;
                if (!sVar2.c()) {
                    return sVar;
                }
            } else if (b2 instanceof r) {
                s m = ((r) b2).m();
                sVar = m;
                if (m == null || !sVar.c()) {
                    return sVar;
                }
            } else {
                return null;
            }
        }
        return sVar;
    }

    @Override // com.d.i.f
    public final int aj() {
        return this.h;
    }

    private void d(int i) {
        this.h = i;
    }

    @Override // com.d.i.f
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InlineLayoutBox: ");
        if (ai() != null) {
            sb.append("<");
            sb.append(ai().getNodeName());
            sb.append("> ");
        } else {
            sb.append("(anonymous) ");
        }
        if (g() || p()) {
            sb.append("(");
            if (g()) {
                sb.append("S");
            }
            if (p()) {
                sb.append("E");
            }
            sb.append(") ");
        }
        sb.append("(baseline=");
        sb.append(this.f1371a);
        sb.append(") ");
        sb.append("(" + ab() + "," + aa() + ")->(" + i() + " x " + as() + ")");
        return sb.toString();
    }

    public final void a(h hVar) {
        boolean at = a().at();
        for (Object obj : n()) {
            if (obj instanceof r) {
                ((r) obj).a(hVar);
            } else if ((obj instanceof s) && at) {
                ((s) obj).a(hVar);
            }
        }
    }

    public final float a(t tVar, float f) {
        float f2 = f;
        float f3 = 0.0f;
        for (Object obj : n()) {
            if (obj instanceof s) {
                s sVar = (s) obj;
                sVar.a(sVar.e() + Math.round(f3));
                float a2 = sVar.a(tVar);
                sVar.b((int) (sVar.f() + a2));
                f3 += a2;
                f2 += a2;
            } else {
                f fVar = (f) obj;
                fVar.n(fVar.am() + Math.round(f2));
                if (fVar instanceof r) {
                    float a3 = ((r) fVar).a(tVar, f2);
                    f3 += a3;
                    f2 += a3;
                }
            }
        }
        c((int) (i() + f3));
        return f3;
    }
}
