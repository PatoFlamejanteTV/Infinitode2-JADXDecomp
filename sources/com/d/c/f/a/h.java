package com.d.c.f.a;

import com.d.c.a.a;

/* loaded from: infinitode-2.jar:com/d/c/f/a/h.class */
public class h {

    /* renamed from: b, reason: collision with root package name */
    public static final h f1082b;
    protected float c;
    protected float d;
    protected float e;
    protected float f;

    static {
        com.d.c.a.a aVar = com.d.c.a.a.bi;
        f1082b = new h(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h() {
        this.f = 0.0f;
        this.e = 0.0f;
        this.d = 0.0f;
        this.c = 0.0f;
    }

    private h(float f, float f2, float f3, float f4) {
        this();
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.f = f4;
    }

    public static h a(com.d.c.f.c cVar, com.d.c.a.a aVar, a.C0018a c0018a, float f, com.d.c.f.d dVar) {
        return new h(!cVar.h(c0018a.f966a) ? 0.0f : cVar.c(c0018a.f966a, f, dVar), !cVar.h(c0018a.f967b) ? 0.0f : cVar.b(c0018a.f967b, f, dVar), !cVar.h(c0018a.c) ? 0.0f : cVar.c(c0018a.c, f, dVar), !cVar.h(c0018a.d) ? 0.0f : cVar.b(c0018a.d, f, dVar));
    }

    public String toString() {
        return "RectPropertySet[top=" + this.c + ",right=" + this.d + ",bottom=" + this.e + ",left=" + this.f + "]";
    }

    public final float t() {
        return this.c;
    }

    public final float u() {
        return this.d;
    }

    public final float v() {
        return this.e;
    }

    public final float w() {
        return this.f;
    }

    public final float x() {
        return this.c + this.e;
    }

    public final float y() {
        return this.f + this.d;
    }

    public final void a(float f) {
        this.c = f;
    }

    public final void b(float f) {
        this.d = f;
    }

    public final void c(float f) {
        this.e = f;
    }

    public final void d(float f) {
        this.f = f;
    }

    public final h z() {
        h hVar = new h();
        hVar.c = this.c;
        hVar.d = this.d;
        hVar.e = this.e;
        hVar.f = this.f;
        return hVar;
    }

    public final boolean A() {
        return this.c == 0.0f && this.d == 0.0f && this.e == 0.0f && this.f == 0.0f;
    }

    public final boolean B() {
        return this.c < 0.0f || this.d < 0.0f || this.e < 0.0f || this.f < 0.0f;
    }

    public final void C() {
        if (t() < 0.0f) {
            a(0.0f);
        }
        if (u() < 0.0f) {
            b(0.0f);
        }
        if (v() < 0.0f) {
            c(0.0f);
        }
        if (w() < 0.0f) {
            d(0.0f);
        }
    }
}
