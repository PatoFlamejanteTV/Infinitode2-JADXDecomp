package com.d.i;

/* loaded from: infinitode-2.jar:com/d/i/x.class */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    private ac f1382a;

    /* renamed from: b, reason: collision with root package name */
    private c f1383b;
    private a c;
    private b d;
    private u e;
    private u f;

    public final c a() {
        return this.f1383b;
    }

    public final void a(c cVar) {
        this.f1383b = cVar;
    }

    public final a b() {
        return this.c;
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    public final b c() {
        return this.d;
    }

    public final void a(b bVar) {
        this.d = bVar;
    }

    public final ac d() {
        return this.f1382a;
    }

    public final void a(ac acVar) {
        this.f1382a = acVar;
    }

    public final int e() {
        if (this.f1383b != null) {
            return this.f1383b.b();
        }
        if (this.c != null) {
            return this.c.b();
        }
        if (this.d != null) {
            return this.d.b();
        }
        return 0;
    }

    public final u f() {
        return this.e;
    }

    public final void a(u uVar) {
        this.f = this.e;
        this.e = uVar;
    }

    public final void b(u uVar) {
        if (uVar == this.e) {
            this.e = this.f;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/x$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f1386a;

        /* renamed from: b, reason: collision with root package name */
        private com.d.d.c f1387b;

        public final com.d.d.c a() {
            return this.f1387b;
        }

        public final void a(com.d.d.c cVar) {
            this.f1387b = cVar;
        }

        public final int b() {
            return this.f1386a;
        }

        public final void a(int i) {
            this.f1386a = i;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/x$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f1384a;

        /* renamed from: b, reason: collision with root package name */
        private int f1385b;

        public final int a() {
            return this.f1384a;
        }

        public final void a(int i) {
            this.f1384a = i;
        }

        public final int b() {
            return this.f1385b;
        }

        public final void b(int i) {
            this.f1385b = i;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/i/x$c.class */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f1388a;

        /* renamed from: b, reason: collision with root package name */
        private int f1389b;

        public final String a() {
            return this.f1388a;
        }

        public final void a(String str) {
            this.f1388a = str;
        }

        public final int b() {
            return this.f1389b;
        }

        public final void a(int i) {
            this.f1389b = i;
        }
    }
}
