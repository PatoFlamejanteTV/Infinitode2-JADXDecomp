package com.d.i;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/aa.class */
public final class aa {

    /* renamed from: a, reason: collision with root package name */
    private static final e[] f1319a = {new i(), new j(), new k(), new d(), new h(), new a(), new b(), new c()};

    /* renamed from: b, reason: collision with root package name */
    private com.d.c.f.c f1320b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private g i;
    private com.d.c.c.e j;
    private f[] k = new f[f1319a.length];
    private int l;
    private f m;

    public final void a(int i2) {
        this.l = i2;
    }

    public final int a(com.d.c.f.d dVar) {
        h(dVar);
        return this.i.b();
    }

    public final int b(com.d.c.f.d dVar) {
        h(dVar);
        return this.i.a();
    }

    private void h(com.d.c.f.d dVar) {
        int i2;
        int j2;
        if (this.i == null) {
            com.d.c.f.c l = l();
            if (l.g(com.d.c.a.a.p)) {
                i2 = (int) l.a(com.d.c.a.a.p, 0.0f, dVar);
            } else {
                i2 = i(dVar);
            }
            if (l.g(com.d.c.a.a.q)) {
                j2 = (int) l.a(com.d.c.a.a.q, 0.0f, dVar);
            } else {
                j2 = j(dVar);
            }
            if (l.a(com.d.c.a.a.t, com.d.c.a.c.Z)) {
                int i3 = i2;
                i2 = j2;
                j2 = i3;
            }
            g gVar = new g((byte) 0);
            gVar.b(i2);
            gVar.a(j2);
            this.i = gVar;
        }
    }

    private int i(com.d.c.f.d dVar) {
        if (com.d.m.i.a().a().u() != null) {
            float floatValue = com.d.m.i.a().a().u().floatValue();
            return (int) com.d.c.f.a.e.a(l(), com.d.c.a.a.p, String.valueOf(floatValue), floatValue, com.d.m.i.a().a().w() ? (short) 8 : (short) 7, 0.0f, dVar);
        }
        return (int) com.d.c.f.a.e.a(l(), com.d.c.a.a.p, "210mm", 210.0f, (short) 7, 0.0f, dVar);
    }

    private int j(com.d.c.f.d dVar) {
        if (com.d.m.i.a().a().v() != null) {
            float floatValue = com.d.m.i.a().a().v().floatValue();
            return (int) com.d.c.f.a.e.a(l(), com.d.c.a.a.p, String.valueOf(floatValue), floatValue, com.d.m.i.a().a().w() ? (short) 8 : (short) 7, 0.0f, dVar);
        }
        return (int) com.d.c.f.a.e.a(l(), com.d.c.a.a.q, "297mm", 297.0f, (short) 7, 0.0f, dVar);
    }

    public final int c(com.d.c.f.d dVar) {
        int b2 = (b(dVar) - d(dVar, 3)) - d(dVar, 4);
        if (b2 <= 0) {
            throw new IllegalArgumentException("The content height cannot be zero or less.  Check your document margin definition.");
        }
        return b2;
    }

    public final int d(com.d.c.f.d dVar) {
        int a2 = (a(dVar) - d(dVar, 1)) - d(dVar, 2);
        if (a2 <= 0) {
            throw new IllegalArgumentException("The content width cannot be zero or less.  Check your document margin definition.");
        }
        return a2;
    }

    private com.d.c.f.c l() {
        return this.f1320b;
    }

    public final void a(com.d.c.f.c cVar) {
        this.f1320b = cVar;
    }

    public final int a() {
        return this.d;
    }

    public final int b() {
        return this.c;
    }

    public final void a(com.d.c.f.d dVar, int i2) {
        this.c = i2;
        this.d = i2 + c(dVar);
    }

    public final int c() {
        return this.f;
    }

    public final void b(int i2) {
        this.f = i2;
    }

    public final int d() {
        return this.e;
    }

    public final void c(int i2) {
        this.e = i2;
    }

    private Rectangle k(com.d.c.f.d dVar) {
        return new Rectangle(0, 0, a(dVar), b(dVar));
    }

    public final Rectangle e(com.d.c.f.d dVar) {
        return new Rectangle(0, d(), d(dVar), c(dVar));
    }

    public final Rectangle b(com.d.c.f.d dVar, int i2) {
        return new Rectangle(d(dVar) * (i2 + 1) * (g() == com.d.c.a.c.ak ? 1 : -1), d(), d(dVar), c(dVar));
    }

    public final int c(com.d.c.f.d dVar, int i2) {
        com.d.c.a.c g2 = g();
        float f2 = i2;
        if (d(dVar) == 0.0f) {
            return 0;
        }
        if (g2 == com.d.c.a.c.ak) {
            return (int) (i2 > 0 ? Math.ceil(f2 / r0) - 1.0d : 0.0d);
        }
        return (int) (i2 < 0 ? Math.ceil(Math.abs(f2) / r0) : 0.0d);
    }

    public final boolean e() {
        return f() > 0;
    }

    public final int f() {
        return l().aa();
    }

    public final com.d.c.a.c g() {
        return l().e(com.d.c.a.a.bd);
    }

    public final Rectangle f(com.d.c.f.d dVar) {
        Rectangle rectangle = new Rectangle(d(dVar, 1), d(dVar, 3), d(dVar), c(dVar));
        rectangle.height--;
        return rectangle;
    }

    public final com.d.c.f.a.h g(com.d.c.f.d dVar) {
        return l().a(this.h, dVar);
    }

    private Rectangle a(int i2, int i3, com.d.c.f.d dVar) {
        com.d.c.f.a.h g2 = g(dVar);
        return new Rectangle(i2 + ((int) g2.w()), i3 + ((int) g2.t()), (a(dVar) - ((int) g2.w())) - ((int) g2.u()), (b(dVar) - ((int) g2.t())) - ((int) g2.v()));
    }

    public final void a(ab abVar, int i2, short s) {
        abVar.p().a(abVar, l(), a(0, 0, abVar), 15);
    }

    public final void b(ab abVar, int i2, short s) {
        Rectangle k2 = k(abVar);
        abVar.p().a(abVar, l(), k2, k2, l().a(abVar));
    }

    public final void c(ab abVar, int i2, short s) {
        for (int i3 = 0; i3 < f1319a.length; i3++) {
            f fVar = this.k[i3];
            if (fVar != null) {
                this.m = fVar;
                com.d.f.d b2 = this.k[i3].b();
                Point a2 = fVar.a().a(abVar, this, 0, (short) 2);
                abVar.p().a(a2.x, a2.y);
                if (abVar.p().i()) {
                    b2.Z().a((com.d.c.f.d) abVar);
                    com.d.i.b.b bVar = new com.d.i.b.b(a2.x, a2.y);
                    Object a3 = abVar.p().a(com.d.d.q.RUNNING, b2);
                    bVar.a(abVar, b2.Z());
                    abVar.p().a(a3);
                } else {
                    b2.Z().a(abVar);
                }
                abVar.p().a(-a2.x, -a2.y);
            }
        }
        this.m = null;
    }

    public final com.d.c.a.d[] h() {
        if (this.m == null) {
            return null;
        }
        return this.m.a().b();
    }

    public final int i() {
        return this.g;
    }

    public final void d(int i2) {
        this.g = i2;
    }

    private int m() {
        return this.h;
    }

    public final void e(int i2) {
        this.h = i2;
    }

    public final int d(com.d.c.f.d dVar, int i2) {
        return l().a(dVar, m(), i2);
    }

    private com.d.c.c.e n() {
        return this.j;
    }

    public final void a(com.d.c.c.e eVar) {
        this.j = eVar;
    }

    public final void a(com.d.e.v vVar) {
        vVar.a(this);
        b(vVar);
        c(vVar);
    }

    private void b(com.d.e.v vVar) {
        com.d.i.c a2;
        List<v> b2 = n().b();
        if (b2 != null && b2.size() > 0) {
            for (v vVar2 : b2) {
                if (vVar2.d() == com.d.c.a.a.C) {
                    List<com.d.c.d.j> l = ((com.d.c.d.j) vVar2.e()).l();
                    if (l.size() == 1) {
                        com.d.c.d.j jVar = l.get(0);
                        if (jVar.i() == 7 && com.d.e.c.a(jVar.n()) && (a2 = com.d.e.c.a(vVar, jVar)) != null) {
                            a2.ai();
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void c(com.d.e.v vVar) {
        com.d.c.f.a.h g2 = g(vVar);
        for (int i2 = 0; i2 < f1319a.length; i2++) {
            e eVar = f1319a[i2];
            Dimension a2 = eVar.a(vVar, this, g2);
            com.d.f.d a3 = com.d.e.c.a(vVar, this.j, eVar.b(), (int) a2.getHeight(), eVar.a());
            if (a3 != null) {
                a3.g(new w(new Rectangle((int) a2.getWidth(), (int) a2.getHeight())));
                try {
                    vVar.c(1);
                    vVar.a(false);
                    vVar.a(a3);
                    vVar.p().b((com.d.c.f.d) vVar);
                    a3.b(vVar);
                    vVar.n();
                    this.k[i2] = new f(eVar, a3);
                } finally {
                    vVar.c(0);
                }
            }
        }
    }

    public final boolean j() {
        return this.g % 2 != 0;
    }

    public final boolean k() {
        return this.g % 2 == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/aa$g.class */
    public static final class g {

        /* renamed from: a, reason: collision with root package name */
        private int f1324a;

        /* renamed from: b, reason: collision with root package name */
        private int f1325b;

        private g() {
        }

        /* synthetic */ g(byte b2) {
            this();
        }

        public final int a() {
            return this.f1325b;
        }

        public final void a(int i) {
            this.f1325b = i;
        }

        public final int b() {
            return this.f1324a;
        }

        public final void b(int i) {
            this.f1324a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/aa$f.class */
    public static class f {

        /* renamed from: a, reason: collision with root package name */
        private final e f1322a;

        /* renamed from: b, reason: collision with root package name */
        private final com.d.f.d f1323b;

        public f(e eVar, com.d.f.d dVar) {
            this.f1322a = eVar;
            this.f1323b = dVar;
        }

        public final e a() {
            return this.f1322a;
        }

        public final com.d.f.d b() {
            return this.f1323b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/aa$e.class */
    public static abstract class e {

        /* renamed from: a, reason: collision with root package name */
        private final com.d.c.a.d[] f1321a;

        public abstract Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar);

        public abstract Point a(ab abVar, aa aaVar, int i, short s);

        public e(com.d.c.a.d dVar) {
            this.f1321a = new com.d.c.a.d[]{dVar};
        }

        public e(com.d.c.a.d[] dVarArr) {
            this.f1321a = dVarArr;
        }

        public final com.d.c.a.d[] b() {
            return this.f1321a;
        }

        public int a() {
            return 2;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$i.class */
    static class i extends e {
        public i() {
            super(com.d.c.a.d.f970a);
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension((int) hVar.w(), (int) hVar.t());
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int i2;
            if (s == 1) {
                i2 = aaVar.d();
            } else if (s == 2) {
                i2 = 0;
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(i, i2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$k.class */
    static class k extends e {
        public k() {
            super(com.d.c.a.d.e);
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension((int) hVar.u(), (int) hVar.t());
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int i2;
            int a2 = (i + aaVar.a(abVar)) - ((int) aaVar.g(abVar).u());
            if (s == 1) {
                i2 = aaVar.d();
            } else if (s == 2) {
                i2 = 0;
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(a2, i2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$c.class */
    static class c extends e {
        public c() {
            super(com.d.c.a.d.j);
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension((int) hVar.u(), (int) hVar.v());
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int b2;
            int a2 = (i + aaVar.a(abVar)) - ((int) aaVar.g(abVar).u());
            if (s == 1) {
                b2 = aaVar.c() - ((int) aaVar.g(abVar).v());
            } else if (s == 2) {
                b2 = aaVar.b(abVar) - ((int) aaVar.g(abVar).v());
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(a2, b2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$a.class */
    static class a extends e {
        public a() {
            super(com.d.c.a.d.f);
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension((int) hVar.w(), (int) hVar.v());
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int b2;
            if (s == 1) {
                b2 = aaVar.c() - ((int) aaVar.g(abVar).v());
            } else if (s == 2) {
                b2 = aaVar.b(abVar) - ((int) aaVar.g(abVar).v());
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(i, b2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$d.class */
    static class d extends e {
        public d() {
            super(new com.d.c.a.d[]{com.d.c.a.d.k, com.d.c.a.d.l, com.d.c.a.d.m});
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension((int) hVar.w(), aaVar.c(dVar));
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int t;
            if (s == 1) {
                t = aaVar.d() + ((int) aaVar.g(abVar).t());
            } else if (s == 2) {
                t = (int) aaVar.g(abVar).t();
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(i, t);
        }

        @Override // com.d.i.aa.e
        public final int a() {
            return 1;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$h.class */
    static class h extends e {
        public h() {
            super(new com.d.c.a.d[]{com.d.c.a.d.n, com.d.c.a.d.o, com.d.c.a.d.p});
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension((int) hVar.w(), aaVar.c(dVar));
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int t;
            int a2 = (i + aaVar.a(abVar)) - ((int) aaVar.g(abVar).u());
            if (s == 1) {
                t = aaVar.d() + ((int) aaVar.g(abVar).t());
            } else if (s == 2) {
                t = (int) aaVar.g(abVar).t();
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(a2, t);
        }

        @Override // com.d.i.aa.e
        public final int a() {
            return 1;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$j.class */
    static class j extends e {
        public j() {
            super(new com.d.c.a.d[]{com.d.c.a.d.f971b, com.d.c.a.d.c, com.d.c.a.d.d});
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension(aaVar.d(dVar), (int) hVar.t());
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int i2;
            int w = i + ((int) aaVar.g(abVar).w());
            if (s == 1) {
                i2 = aaVar.d();
            } else if (s == 2) {
                i2 = 0;
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(w, i2);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/aa$b.class */
    static class b extends e {
        public b() {
            super(new com.d.c.a.d[]{com.d.c.a.d.g, com.d.c.a.d.h, com.d.c.a.d.i});
        }

        @Override // com.d.i.aa.e
        public final Dimension a(com.d.c.f.d dVar, aa aaVar, com.d.c.f.a.h hVar) {
            return new Dimension(aaVar.d(dVar), (int) hVar.v());
        }

        @Override // com.d.i.aa.e
        public final Point a(ab abVar, aa aaVar, int i, short s) {
            int b2;
            int w = i + ((int) aaVar.g(abVar).w());
            if (s == 1) {
                b2 = aaVar.c() - ((int) aaVar.g(abVar).v());
            } else if (s == 2) {
                b2 = aaVar.b(abVar) - ((int) aaVar.g(abVar).v());
            } else {
                throw new IllegalArgumentException("Illegal mode");
            }
            return new Point(w, b2);
        }
    }
}
