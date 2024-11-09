package com.d.h;

/* loaded from: infinitode-2.jar:com/d/h/p.class */
public final class p implements com.d.d.b {

    /* renamed from: a, reason: collision with root package name */
    public final float f1255a;

    /* renamed from: b, reason: collision with root package name */
    public final float f1256b;
    public final float c;
    public final float e;
    public final float d = 100.0f;
    public final float f = 50.0f;

    private p(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f1255a = f;
        this.f1256b = f2;
        this.c = f3;
        this.e = f5;
    }

    public static p a(org.a.c.h.e.u uVar, org.a.c.h.e.v vVar) {
        return new p(uVar.e().d(), -uVar.e().b(), (-vVar.j().g()) / 3.0f, 100.0f, -vVar.l(), 50.0f);
    }
}
