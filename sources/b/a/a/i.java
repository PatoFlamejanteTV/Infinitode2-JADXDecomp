package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/i.class */
public class i implements f {

    /* renamed from: a, reason: collision with root package name */
    protected b.a.a.b f11a;

    /* renamed from: b, reason: collision with root package name */
    protected g f12b;
    private n h;
    private n i;
    private m j;
    private int k;
    protected int c;
    protected int d;
    protected a[] e;
    protected b.a.a.d f;
    static final float[] g = {2.0f, 1.587401f, 1.2599211f, 1.0f, 0.7937005f, 0.62996054f, 0.5f, 0.39685026f, 0.31498027f, 0.25f, 0.19842513f, 0.15749013f, 0.125f, 0.099212565f, 0.07874507f, 0.0625f, 0.049606282f, 0.039372534f, 0.03125f, 0.024803141f, 0.019686267f, 0.015625f, 0.012401571f, 0.009843133f, 0.0078125f, 0.0062007853f, 0.0049215667f, 0.00390625f, 0.0031003926f, 0.0024607833f, 0.001953125f, 0.0015501963f, 0.0012303917f, 9.765625E-4f, 7.7509816E-4f, 6.1519584E-4f, 4.8828125E-4f, 3.8754908E-4f, 3.0759792E-4f, 2.4414062E-4f, 1.9377454E-4f, 1.5379896E-4f, 1.2207031E-4f, 9.688727E-5f, 7.689948E-5f, 6.1035156E-5f, 4.8443635E-5f, 3.844974E-5f, 3.0517578E-5f, 2.4221818E-5f, 1.922487E-5f, 1.5258789E-5f, 1.2110909E-5f, 9.612435E-6f, 7.6293945E-6f, 6.0554544E-6f, 4.8062175E-6f, 3.8146973E-6f, 3.0277272E-6f, 2.4031087E-6f, 1.9073486E-6f, 1.5138636E-6f, 1.2015544E-6f, 0.0f};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/i$a.class */
    public static abstract class a {
        public abstract void a(b.a.a.b bVar, g gVar, b.a.a.d dVar);

        public abstract void a(b.a.a.b bVar, g gVar);

        public abstract boolean a(b.a.a.b bVar);

        public abstract boolean a(int i, n nVar, n nVar2);
    }

    public i() {
        this.f = null;
        this.f = new b.a.a.d();
    }

    public final void a(b.a.a.b bVar, g gVar, n nVar, n nVar2, m mVar, int i) {
        this.f11a = bVar;
        this.f12b = gVar;
        this.h = nVar;
        this.i = nVar2;
        this.j = mVar;
        this.k = 0;
    }

    @Override // b.a.a.f
    public final void a() {
        this.d = this.f12b.k();
        this.e = new a[32];
        this.c = this.f12b.f();
        b();
        d();
        c();
        if (this.f != null || this.f12b.g()) {
            e();
            f();
        }
    }

    protected void b() {
        if (this.c == 3) {
            for (int i = 0; i < this.d; i++) {
                this.e[i] = new b(i);
            }
            return;
        }
        if (this.c == 1) {
            int i2 = 0;
            while (i2 < this.f12b.l()) {
                this.e[i2] = new d(i2);
                i2++;
            }
            while (i2 < this.d) {
                this.e[i2] = new c(i2);
                i2++;
            }
            return;
        }
        for (int i3 = 0; i3 < this.d; i3++) {
            this.e[i3] = new d(i3);
        }
    }

    private void d() {
        for (int i = 0; i < this.d; i++) {
            this.e[i].a(this.f11a, this.f12b, this.f);
        }
    }

    protected void c() {
    }

    private void e() {
        for (int i = 0; i < this.d; i++) {
            this.e[i].a(this.f11a, this.f12b);
        }
    }

    private void f() {
        boolean z = false;
        boolean z2 = false;
        int f = this.f12b.f();
        do {
            for (int i = 0; i < this.d; i++) {
                z = this.e[i].a(this.f11a);
            }
            do {
                for (int i2 = 0; i2 < this.d; i2++) {
                    z2 = this.e[i2].a(this.k, this.h, this.i);
                }
                this.h.a(this.j);
                if (this.k == 0 && f != 3) {
                    this.i.a(this.j);
                }
            } while (!z2);
        } while (!z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/i$b.class */
    public static class b extends a {

        /* renamed from: a, reason: collision with root package name */
        public static final float[] f13a = {0.0f, 0.6666667f, 0.2857143f, 0.13333334f, 0.06451613f, 0.031746034f, 0.015748031f, 0.007843138f, 0.0039138943f, 0.0019550342f, 9.770396E-4f, 4.884005E-4f, 2.4417043E-4f, 1.2207776E-4f, 6.103702E-5f};

        /* renamed from: b, reason: collision with root package name */
        public static final float[] f14b = {0.0f, -0.6666667f, -0.85714287f, -0.93333334f, -0.9677419f, -0.984127f, -0.992126f, -0.99607843f, -0.99804306f, -0.9990225f, -0.9995115f, -0.9997558f, -0.9998779f, -0.99993896f, -0.9999695f};
        protected int c;
        private int j = 0;
        protected int d;
        protected float e;
        protected int f;
        protected float g;
        protected float h;
        protected float i;

        public b(int i) {
            this.c = i;
        }

        @Override // b.a.a.i.a
        public void a(b.a.a.b bVar, g gVar, b.a.a.d dVar) {
            int d = bVar.d(4);
            this.d = d;
            if (d == 15) {
                throw new e(514, (Throwable) null);
            }
            if (dVar != null) {
                dVar.a(this.d, 4);
            }
            if (this.d != 0) {
                this.f = this.d + 1;
                this.h = f13a[this.d];
                this.i = f14b[this.d];
            }
        }

        @Override // b.a.a.i.a
        public void a(b.a.a.b bVar, g gVar) {
            if (this.d != 0) {
                this.e = i.g[bVar.d(6)];
            }
        }

        @Override // b.a.a.i.a
        public boolean a(b.a.a.b bVar) {
            if (this.d != 0) {
                this.g = bVar.d(this.f);
            }
            int i = this.j + 1;
            this.j = i;
            if (i == 12) {
                this.j = 0;
                return true;
            }
            return false;
        }

        @Override // b.a.a.i.a
        public boolean a(int i, n nVar, n nVar2) {
            if (this.d != 0 && i != 2) {
                nVar.a(((this.g * this.h) + this.i) * this.e, this.c);
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/i$c.class */
    public static class c extends b {
        private float j;

        public c(int i) {
            super(i);
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final void a(b.a.a.b bVar, g gVar, b.a.a.d dVar) {
            super.a(bVar, gVar, dVar);
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final void a(b.a.a.b bVar, g gVar) {
            if (this.d != 0) {
                this.e = i.g[bVar.d(6)];
                this.j = i.g[bVar.d(6)];
            }
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final boolean a(b.a.a.b bVar) {
            return super.a(bVar);
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final boolean a(int i, n nVar, n nVar2) {
            if (this.d != 0) {
                this.g = (this.g * this.h) + this.i;
                if (i == 0) {
                    float f = this.g * this.e;
                    float f2 = this.g * this.j;
                    nVar.a(f, this.c);
                    nVar2.a(f2, this.c);
                    return true;
                }
                if (i == 1) {
                    nVar.a(this.g * this.e, this.c);
                    return true;
                }
                nVar.a(this.g * this.j, this.c);
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:b/a/a/i$d.class */
    public static class d extends b {
        private int j;
        private float k;
        private int l;
        private float m;
        private float n;
        private float o;

        public d(int i) {
            super(i);
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final void a(b.a.a.b bVar, g gVar, b.a.a.d dVar) {
            this.d = bVar.d(4);
            this.j = bVar.d(4);
            if (dVar != null) {
                dVar.a(this.d, 4);
                dVar.a(this.j, 4);
            }
            if (this.d != 0) {
                this.f = this.d + 1;
                this.h = f13a[this.d];
                this.i = f14b[this.d];
            }
            if (this.j != 0) {
                this.l = this.j + 1;
                this.n = f13a[this.j];
                this.o = f14b[this.j];
            }
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final void a(b.a.a.b bVar, g gVar) {
            if (this.d != 0) {
                this.e = i.g[bVar.d(6)];
            }
            if (this.j != 0) {
                this.k = i.g[bVar.d(6)];
            }
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final boolean a(b.a.a.b bVar) {
            boolean a2 = super.a(bVar);
            if (this.j != 0) {
                this.m = bVar.d(this.l);
            }
            return a2;
        }

        @Override // b.a.a.i.b, b.a.a.i.a
        public final boolean a(int i, n nVar, n nVar2) {
            super.a(i, nVar, nVar2);
            if (this.j != 0 && i != 1) {
                float f = ((this.m * this.n) + this.o) * this.k;
                if (i == 0) {
                    nVar2.a(f, this.c);
                    return true;
                }
                nVar.a(f, this.c);
                return true;
            }
            return true;
        }
    }
}
