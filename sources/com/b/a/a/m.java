package com.b.a.a;

/* loaded from: infinitode-2.jar:com/b/a/a/m.class */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public final o f811a;
    private a c;

    /* renamed from: b, reason: collision with root package name */
    public final b f812b;

    /* synthetic */ m(o oVar, byte b2) {
        this(oVar);
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/m$g.class */
    public static final class g extends com.b.a.c.h {
        @Override // com.b.a.c.h
        public final boolean b(int i) {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/m$i.class */
    public static abstract class i extends com.b.a.c.h {

        /* renamed from: a, reason: collision with root package name */
        public final o f819a;

        public abstract int a(int i);

        public i(o oVar) {
            this.f819a = oVar;
        }

        @Override // com.b.a.c.h
        public final int c(int i) {
            return this.f819a.e(this.f819a.a(i));
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/m$b.class */
    public static final class b extends i {
        public b(o oVar) {
            super(oVar);
        }

        @Override // com.b.a.a.m.i
        public final int a(int i) {
            return this.f819a.d(this.f819a.a(i)) ? 1 : 0;
        }

        @Override // com.b.a.c.h
        public final boolean b(int i) {
            return this.f819a.j(i);
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/m$a.class */
    public static final class a extends i {

        /* renamed from: b, reason: collision with root package name */
        private final boolean f813b;

        public a(o oVar, boolean z) {
            super(oVar);
            this.f813b = z;
        }

        @Override // com.b.a.a.m.i
        public final int a(int i) {
            return this.f819a.b(this.f819a.a(i));
        }

        @Override // com.b.a.c.h
        public final boolean b(int i) {
            return this.f819a.a(i, this.f813b, true);
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/m$c.class */
    public static final class c extends i {
        public c(o oVar) {
            super(oVar);
        }

        @Override // com.b.a.a.m.i
        public final int a(int i) {
            return this.f819a.d(this.f819a.a(i)) ? 1 : 0;
        }

        @Override // com.b.a.c.h
        public final boolean b(int i) {
            return this.f819a.k(i);
        }
    }

    private m(o oVar) {
        this.f811a = oVar;
        this.c = new a(oVar, false);
        this.f812b = new b(oVar);
        new c(oVar);
        new a(oVar, true);
    }

    private static m a(h hVar) {
        if (hVar.f818b != null) {
            throw hVar.f818b;
        }
        return hVar.f817a;
    }

    public static m a() {
        return a(d.f814a);
    }

    private static m c() {
        return a(e.f815a);
    }

    public static m b() {
        return a(f.f816a);
    }

    public static i a(int i2) {
        switch (i2) {
            case 0:
                return a().f812b;
            case 1:
                return c().f812b;
            case 2:
                return a().c;
            case 3:
                return c().c;
            default:
                return null;
        }
    }

    static {
        new n();
        new g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/m$h.class */
    public static final class h {

        /* renamed from: a, reason: collision with root package name */
        private m f817a;

        /* renamed from: b, reason: collision with root package name */
        private RuntimeException f818b;

        /* synthetic */ h(String str, byte b2) {
            this(str);
        }

        private h(String str) {
            try {
                this.f817a = new m(new o().a(str + ".nrm"), (byte) 0);
            } catch (RuntimeException e) {
                this.f818b = e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/m$d.class */
    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private static final h f814a = new h("nfc", 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/m$e.class */
    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private static final h f815a = new h("nfkc", 0);
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/m$f.class */
    static final class f {

        /* renamed from: a, reason: collision with root package name */
        private static final h f816a = new h("nfkc_cf", 0);
    }
}
