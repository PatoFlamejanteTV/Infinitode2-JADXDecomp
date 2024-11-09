package com.d.i;

/* loaded from: infinitode-2.jar:com/d/i/ad.class */
public final class ad {

    /* renamed from: a, reason: collision with root package name */
    private com.d.c.a.c f1330a;

    /* renamed from: b, reason: collision with root package name */
    private int f1331b;
    private int c;

    public ad(com.d.c.a.c cVar) {
        this.f1330a = cVar;
    }

    public final int a() {
        return this.f1331b;
    }

    public final void a(int i) {
        this.f1331b = i;
    }

    public final int b() {
        return this.c;
    }

    public final void b(int i) {
        if (i == 0) {
            this.c = 1;
        } else {
            this.c = i;
        }
    }

    public final com.d.c.a.c c() {
        return this.f1330a;
    }
}
