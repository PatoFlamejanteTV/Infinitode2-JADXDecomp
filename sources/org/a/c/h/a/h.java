package org.a.c.h.a;

import java.util.Arrays;
import org.a.c.b.l;

/* loaded from: infinitode-2.jar:org/a/c/h/a/h.class */
public final class h implements c {

    /* renamed from: a, reason: collision with root package name */
    public static final h f4488a = new h(612.0f, 792.0f);

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.a f4489b;

    static {
        new h(612.0f, 1008.0f);
        new h(2383.937f, 3370.3938f);
        new h(1683.7795f, 2383.937f);
        new h(1190.5513f, 1683.7795f);
        new h(841.8898f, 1190.5513f);
        new h(595.27563f, 841.8898f);
        new h(419.52756f, 595.27563f);
        new h(297.63782f, 419.52756f);
    }

    public h() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public h(float f, float f2) {
        this(0.0f, 0.0f, f, f2);
    }

    public h(float f, float f2, float f3, float f4) {
        this.f4489b = new org.a.c.b.a();
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(f));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(f2));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(f + f3));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(f2 + f4));
    }

    public h(org.a.b.h.a aVar) {
        this.f4489b = new org.a.c.b.a();
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(aVar.a()));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(aVar.b()));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(aVar.c()));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(aVar.d()));
    }

    public h(org.a.c.b.a aVar) {
        float[] copyOf = Arrays.copyOf(aVar.d(), 4);
        this.f4489b = new org.a.c.b.a();
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(Math.min(copyOf[0], copyOf[2])));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(Math.min(copyOf[1], copyOf[3])));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(Math.max(copyOf[0], copyOf[2])));
        this.f4489b.a((org.a.c.b.b) new org.a.c.b.f(Math.max(copyOf[1], copyOf[3])));
    }

    public final h a() {
        h hVar = new h();
        hVar.c(h());
        hVar.d(i());
        return hVar;
    }

    public final org.a.c.b.a b() {
        return this.f4489b;
    }

    public final float c() {
        return ((l) this.f4489b.b(0)).a();
    }

    public final void a(float f) {
        this.f4489b.b(0, new org.a.c.b.f(f));
    }

    public final float d() {
        return ((l) this.f4489b.b(1)).a();
    }

    public final void b(float f) {
        this.f4489b.b(1, new org.a.c.b.f(f));
    }

    public final float e() {
        return ((l) this.f4489b.b(2)).a();
    }

    public final void c(float f) {
        this.f4489b.b(2, new org.a.c.b.f(f));
    }

    public final float g() {
        return ((l) this.f4489b.b(3)).a();
    }

    public final void d(float f) {
        this.f4489b.b(3, new org.a.c.b.f(f));
    }

    public final float h() {
        return e() - c();
    }

    public final float i() {
        return g() - d();
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return this.f4489b;
    }

    public final String toString() {
        return "[" + c() + "," + d() + "," + e() + "," + g() + "]";
    }
}
