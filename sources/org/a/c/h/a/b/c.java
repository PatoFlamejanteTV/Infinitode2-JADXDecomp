package org.a.c.h.a.b;

import org.a.c.b.j;
import org.a.c.b.l;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/c.class */
public final class c extends a {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.a f4479a;

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.a f4480b;
    private final float c;

    public c(org.a.c.b.b bVar) {
        super(bVar);
        if (f().a(j.H) instanceof org.a.c.b.a) {
            this.f4479a = (org.a.c.b.a) f().a(j.H);
        } else {
            this.f4479a = new org.a.c.b.a();
        }
        if (this.f4479a.b() == 0) {
            this.f4479a.a((org.a.c.b.b) new org.a.c.b.f(0.0f));
        }
        if (f().a(j.I) instanceof org.a.c.b.a) {
            this.f4480b = (org.a.c.b.a) f().a(j.I);
        } else {
            this.f4480b = new org.a.c.b.a();
        }
        if (this.f4480b.b() == 0) {
            this.f4480b.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
        }
        this.c = f().l(j.co);
    }

    @Override // org.a.c.h.a.b.a
    public final int a() {
        return 2;
    }

    @Override // org.a.c.h.a.b.a
    public final float[] a(float[] fArr) {
        float pow = (float) Math.pow(fArr[0], this.c);
        float[] fArr2 = new float[Math.min(this.f4479a.b(), this.f4480b.b())];
        for (int i = 0; i < fArr2.length; i++) {
            float a2 = ((l) this.f4479a.b(i)).a();
            fArr2[i] = a2 + (pow * (((l) this.f4480b.b(i)).a() - a2));
        }
        return b(fArr2);
    }

    private org.a.c.b.a h() {
        return this.f4479a;
    }

    private org.a.c.b.a i() {
        return this.f4480b;
    }

    private float j() {
        return this.c;
    }

    @Override // org.a.c.h.a.b.a
    public final String toString() {
        return "FunctionType2{C0: " + h() + " C1: " + i() + " N: " + j() + "}";
    }
}
