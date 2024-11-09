package com.d.f;

/* loaded from: infinitode-2.jar:com/d/f/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private com.d.c.a.c f1173a;

    /* renamed from: b, reason: collision with root package name */
    private int f1174b;
    private com.d.c.d.g c;
    private int d;

    private a(com.d.c.a.c cVar, int i, com.d.c.d.g gVar, int i2) {
        this.f1173a = cVar;
        this.f1174b = i;
        this.c = gVar;
        this.d = i2;
    }

    public final com.d.c.d.g a() {
        return this.c;
    }

    public final com.d.c.a.c b() {
        return this.f1173a;
    }

    public final int c() {
        return this.f1174b;
    }

    public final void a(int i) {
        this.f1174b = i;
    }

    public final int d() {
        return this.d;
    }

    public final boolean e() {
        return this.f1173a != null;
    }

    public final boolean f() {
        return (this.f1173a == null || this.f1173a == com.d.c.a.c.ap || this.f1173a == com.d.c.a.c.P) ? false : true;
    }

    public final boolean g() {
        return this.f1173a == com.d.c.a.c.P;
    }

    public static a a(com.d.c.f.a.a aVar, int i) {
        return new a(aVar.i(), (int) aVar.w(), aVar.m(), i);
    }

    public static a b(com.d.c.f.a.a aVar, int i) {
        return new a(aVar.g(), (int) aVar.u(), aVar.k(), i);
    }

    public static a c(com.d.c.f.a.a aVar, int i) {
        return new a(aVar.f(), (int) aVar.t(), aVar.j(), i);
    }

    public static a d(com.d.c.f.a.a aVar, int i) {
        return new a(aVar.h(), (int) aVar.v(), aVar.l(), i);
    }
}
