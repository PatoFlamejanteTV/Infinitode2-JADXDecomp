package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/e.class */
public /* synthetic */ class e {

    /* renamed from: a, reason: collision with root package name */
    private int f4295a;

    /* renamed from: b, reason: collision with root package name */
    private int f4296b;
    private final short c;
    private final short d;
    private final short e;
    private final int f;
    private double g;
    private double h;
    private double i;
    private double j;
    private int k;
    private int l;

    /* JADX INFO: Access modifiers changed from: protected */
    public e(ak akVar) {
        this.g = 1.0d;
        this.h = 1.0d;
        this.i = 0.0d;
        this.j = 0.0d;
        this.k = 0;
        this.l = 0;
        this.e = akVar.d();
        this.f = akVar.c();
        if ((this.e & 1) != 0) {
            this.c = akVar.d();
            this.d = akVar.d();
        } else {
            this.c = (short) akVar.i();
            this.d = (short) akVar.i();
        }
        if ((this.e & 2) != 0) {
            this.k = this.c;
            this.l = this.d;
        }
        if ((this.e & 8) != 0) {
            double d = akVar.d() / 16384.0d;
            this.h = d;
            this.g = d;
        } else if ((this.e & 64) != 0) {
            this.g = akVar.d() / 16384.0d;
            this.h = akVar.d() / 16384.0d;
        } else if ((this.e & 128) != 0) {
            this.g = akVar.d() / 16384.0d;
            this.i = akVar.d() / 16384.0d;
            this.j = akVar.d() / 16384.0d;
            this.h = akVar.d() / 16384.0d;
        }
    }

    public void a(int i) {
        this.f4295a = i;
    }

    public int a() {
        return this.f4295a;
    }

    public void b(int i) {
        this.f4296b = i;
    }

    public int b() {
        return this.f4296b;
    }

    public short c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.k;
    }

    public int f() {
        return this.l;
    }

    public int a(int i, int i2) {
        return Math.round((float) ((i * this.g) + (i2 * this.j)));
    }

    public int b(int i, int i2) {
        return Math.round((float) ((i * this.i) + (i2 * this.h)));
    }
}
