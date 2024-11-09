package com.d.i;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;

/* loaded from: infinitode-2.jar:com/d/i/a.class */
public abstract class a implements com.d.d.m {

    /* renamed from: a, reason: collision with root package name */
    private com.d.c.g.a f1282a;

    protected abstract void e(int i, int i2, int i3, int i4);

    @Override // com.d.d.m
    public final void a(ab abVar, s sVar) {
        r g = sVar.g();
        String d = sVar.d();
        if (sVar.a() == 1) {
            d = abVar.g().a(d);
        }
        if (d != null && d.length() > 0) {
            a(g.a().b());
            a(g.a().d(abVar));
            a(g.a().h());
            if (sVar.b() != 0.0f) {
                t tVar = new t();
                tVar.a(sVar.b());
                tVar.b(sVar.b());
                abVar.f().a(abVar.p(), d, g.ab() + sVar.e(), g.aa() + g.c(), tVar);
            } else if (sVar.g().a().at()) {
                t m = sVar.g().k().m();
                if (m != null) {
                    abVar.f().a(abVar.p(), d, g.ab() + sVar.e(), g.aa() + g.c(), m);
                } else {
                    abVar.f().a(abVar.p(), d, g.ab() + sVar.e(), g.aa() + g.c());
                }
            } else {
                abVar.f().a(abVar.p(), d, g.ab() + sVar.e(), g.aa() + g.c());
            }
        }
        if (abVar.m()) {
            b(abVar, sVar);
        }
    }

    private void b(ab abVar, s sVar) {
        r g = sVar.g();
        String d = sVar.d();
        a((com.d.c.d.g) new com.d.c.d.h(255, 51, 255));
        l e = g.a().e((com.d.c.f.d) null);
        com.d.d.r f = abVar.f();
        abVar.q();
        int a2 = f.a(g.a().d(abVar), d);
        int ab = g.ab() + sVar.e();
        int aa = g.aa() + g.c();
        e(ab, aa, ab + a2, aa);
        int ceil = aa + ((int) Math.ceil(e.b()));
        e(ab, ceil, ab + a2, ceil);
        int ceil2 = (ceil - ((int) Math.ceil(e.b()))) - ((int) Math.ceil(e.a()));
        e(ab, ceil2, ab + a2, ceil2);
    }

    @Override // com.d.d.m
    public final void a(ab abVar, r rVar, ad adVar) {
        a(rVar.a().b());
        Rectangle c = rVar.c(rVar.ab(), rVar.aa(), abVar);
        c(c.x, rVar.aa() + adVar.a(), c.width, adVar.b());
    }

    @Override // com.d.d.m
    public final void a(u uVar) {
        a(uVar.a().b());
        f U = uVar.U();
        for (ad adVar : uVar.h()) {
            if (U.a().a(com.d.c.a.a.v, com.d.c.a.c.h)) {
                c(uVar.ab(), uVar.aa() + adVar.a(), ((U.ab() + U.ap()) + U.d_()) - uVar.ab(), adVar.b());
            } else {
                c(uVar.ab(), uVar.aa() + adVar.a(), uVar.d_(), adVar.b());
            }
        }
    }

    @Override // com.d.d.m
    public final void a(ab abVar, f fVar, com.d.c.d.g gVar) {
        a(gVar);
        Rectangle a2 = fVar.a(fVar.ab(), fVar.aa(), abVar, 0, 0);
        a2.height--;
        a2.width--;
        a(a2.x, a2.y, a2.width, a2.height);
    }

    @Override // com.d.d.m
    public final void a(ab abVar, com.d.c.f.a.a aVar, Rectangle rectangle, int i) {
        e.a(rectangle, i, aVar, abVar, 0, false);
    }

    @Override // com.d.d.m
    public final void a(ab abVar, f fVar) {
        if (!fVar.a().a(abVar, fVar)) {
            return;
        }
        e.a(fVar.j(abVar), fVar.ad(), fVar.b((com.d.c.f.d) abVar), abVar, 0, true);
    }

    @Override // com.d.d.m
    public final void a(ab abVar, com.d.c.f.c cVar, Rectangle rectangle, int i) {
        e.a(rectangle, i, cVar.a(abVar), abVar, 0, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.d.d.c] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.d.d.s] */
    private static com.d.d.c a(ab abVar, com.d.c.f.c cVar) {
        if (!cVar.a(com.d.c.a.a.d, com.d.c.a.c.ap)) {
            ?? f = cVar.f(com.d.c.a.a.d);
            try {
                f = abVar.e().b(f).d();
                return f;
            } catch (Exception e) {
                f.printStackTrace();
                com.d.m.k.b(e);
                return null;
            }
        }
        return null;
    }

    @Override // com.d.d.m
    public final void a(ab abVar, com.d.c.f.c cVar, Rectangle rectangle, Rectangle rectangle2, com.d.c.f.a.a aVar) {
        b(abVar, cVar, rectangle, rectangle2, aVar);
    }

    @Override // com.d.d.m
    public void b(ab abVar, f fVar) {
        if (!fVar.a().a(abVar, fVar)) {
            return;
        }
        Rectangle j = fVar.j(abVar);
        b(abVar, fVar.a(), j, j, fVar.a().a(abVar));
    }

    private void b(ab abVar, com.d.c.f.c cVar, Rectangle rectangle, Rectangle rectangle2, com.d.c.f.a.a aVar) {
        if (!com.d.m.b.a("xr.renderer.draw.backgrounds", true)) {
            return;
        }
        com.d.c.d.g c = cVar.c();
        com.d.d.c a2 = a(abVar, cVar);
        com.d.d.c cVar2 = a2;
        if (a2 == null || cVar2.b() == 0 || cVar2.a() == 0) {
            cVar2 = null;
        }
        if ((c == null || c == com.d.c.d.h.f1056a) && cVar2 == null) {
            return;
        }
        Area area = new Area(e.a(rectangle, aVar, true));
        Shape shape = null;
        if (!abVar.d()) {
            Shape c2 = c();
            shape = c2;
            if (c2 != null) {
                area.intersect(new Area(shape));
            }
            e(area);
        } else if (cVar2 != null) {
            f(area);
        }
        if (c != null && c != com.d.c.d.h.f1056a) {
            a(c);
            c(area);
        }
        if (cVar2 != null) {
            Rectangle rectangle3 = rectangle2;
            if (cVar.n()) {
                rectangle3 = abVar.i();
            }
            int i = rectangle3.x;
            int i2 = rectangle3.y;
            if (aVar != null) {
                i += (int) aVar.w();
                i2 += (int) aVar.t();
            }
            a(abVar, cVar, rectangle3, cVar2);
            float a3 = cVar2.a();
            float b2 = cVar2.b();
            com.d.m.a e = cVar.e();
            int a4 = i + a(abVar, cVar, e.a(), rectangle3.width, a3);
            int a5 = i2 + a(abVar, cVar, e.b(), rectangle3.height, b2);
            boolean T = cVar.T();
            boolean U = cVar.U();
            if (T || U) {
                if (T && U) {
                    a(cVar2, a(rectangle.x, a4, (int) a3), a(rectangle.y, a5, (int) b2), rectangle.x + rectangle.width, rectangle.y + rectangle.height, cVar.al());
                } else if (T) {
                    int a6 = a(rectangle.x, a4, (int) a3);
                    if (new Rectangle(a6, a5, (int) a3, (int) b2).intersects(rectangle)) {
                        b(cVar2, a6, a5, rectangle.x + rectangle.width, cVar.al());
                    }
                } else if (U) {
                    int a7 = a(rectangle.y, a5, (int) b2);
                    if (new Rectangle(a4, a7, (int) a3, (int) b2).intersects(rectangle)) {
                        a(cVar2, a4, a7, rectangle.y + rectangle.height, cVar.al());
                    }
                }
            } else if (new Rectangle(a4, a5, (int) a3, (int) b2).intersects(rectangle)) {
                a(cVar2, a4, a5, cVar.al());
            }
        }
        if (!abVar.d()) {
            e(shape);
        } else if (cVar2 != null) {
            h();
        }
    }

    private static int a(int i, int i2, int i3) {
        int i4 = i2;
        if (i2 > i) {
            while (i4 > i) {
                i4 -= i3;
            }
        } else if (i4 < i) {
            while (i4 < i) {
                i4 += i3;
            }
            if (i4 != i) {
                i4 -= i3;
            }
        }
        return i4;
    }

    private void a(com.d.d.c cVar, int i, int i2, int i3, int i4, boolean z) {
        int a2 = cVar.a();
        int b2 = cVar.b();
        int i5 = i;
        while (true) {
            int i6 = i5;
            if (i6 < i3) {
                int i7 = i2;
                while (true) {
                    int i8 = i7;
                    if (i8 < i4) {
                        a(cVar, i6, i8, z);
                        i7 = i8 + b2;
                    }
                }
                i5 = i6 + a2;
            } else {
                return;
            }
        }
    }

    private void a(com.d.d.c cVar, int i, int i2, int i3, boolean z) {
        int b2 = cVar.b();
        int i4 = i2;
        while (true) {
            int i5 = i4;
            if (i5 < i3) {
                a(cVar, i, i5, z);
                i4 = i5 + b2;
            } else {
                return;
            }
        }
    }

    private void b(com.d.d.c cVar, int i, int i2, int i3, boolean z) {
        int a2 = cVar.a();
        int i4 = i;
        while (true) {
            int i5 = i4;
            if (i5 < i3) {
                a(cVar, i5, i2, z);
                i4 = i5 + a2;
            } else {
                return;
            }
        }
    }

    private static int a(com.d.c.f.d dVar, com.d.c.f.c cVar, com.d.c.d.j jVar, float f, float f2) {
        if (jVar.a() == 2) {
            float f3 = jVar.f() / 100.0f;
            return Math.round((f * f3) - (f2 * f3));
        }
        return (int) com.d.c.f.a.e.a(cVar, com.d.c.a.a.g, jVar.d(), jVar.f(), jVar.a(), 0.0f, dVar);
    }

    private void a(com.d.c.f.d dVar, com.d.c.f.c cVar, Rectangle rectangle, com.d.d.c cVar2) {
        com.d.c.f.a d = cVar.d();
        if (!d.c()) {
            if (d.b() || d.a()) {
                int b2 = (int) ((cVar2.b() * rectangle.width) / cVar2.a());
                if (d.a()) {
                    if (b2 <= rectangle.height) {
                        cVar2.a(rectangle.width, -1);
                        return;
                    }
                } else if (d.b()) {
                    if (b2 > rectangle.height) {
                        cVar2.a(rectangle.width, -1);
                        return;
                    }
                } else {
                    return;
                }
                cVar2.a(-1, rectangle.height);
                return;
            }
            cVar2.a(a(dVar, cVar, d.d(), rectangle.width), a(dVar, cVar, d.e(), rectangle.height));
        }
    }

    private static int a(com.d.c.f.d dVar, com.d.c.f.c cVar, com.d.c.d.j jVar, float f) {
        if (jVar.a() == 21) {
            return -1;
        }
        if (jVar.a() == 2) {
            return Math.round(f * (jVar.f() / 100.0f));
        }
        return (int) com.d.c.f.a.e.a(cVar, com.d.c.a.a.h, jVar.d(), jVar.f(), jVar.a(), 0.0f, dVar);
    }

    public final com.d.c.g.a o() {
        return this.f1282a;
    }

    private void a(com.d.c.g.a aVar) {
        this.f1282a = aVar;
    }

    @Override // com.d.d.m
    public boolean f() {
        return false;
    }
}
