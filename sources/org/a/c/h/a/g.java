package org.a.c.h.a;

import org.a.c.b.l;

/* loaded from: infinitode-2.jar:org/a/c/h/a/g.class */
public final class g implements c {

    /* renamed from: a, reason: collision with root package name */
    private org.a.c.b.a f4486a;

    /* renamed from: b, reason: collision with root package name */
    private int f4487b;

    public g() {
        this.f4486a = new org.a.c.b.a();
        this.f4486a.a((org.a.c.b.b) new org.a.c.b.f(0.0f));
        this.f4486a.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
        this.f4487b = 0;
    }

    public g(org.a.c.b.a aVar, int i) {
        this.f4486a = aVar;
        this.f4487b = i;
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return this.f4486a;
    }

    public final float a() {
        return ((l) this.f4486a.a(this.f4487b << 1)).a();
    }

    public final float b() {
        return ((l) this.f4486a.a((this.f4487b << 1) + 1)).a();
    }

    public final String toString() {
        return "PDRange{" + a() + ", " + b() + '}';
    }
}
