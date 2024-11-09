package org.a.c.h.f.a;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/d.class */
public final class d extends b {
    private final e f;

    public d() {
        super(org.a.c.b.j.L);
        this.f = new e(new float[]{0.0f, 0.0f, 0.0f}, this);
    }

    public d(org.a.c.b.a aVar) {
        super(aVar);
        this.f = new e(new float[]{0.0f, 0.0f, 0.0f}, this);
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.L.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 3;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        if (this.f4569b == 1.0f && this.c == 1.0f && this.d == 1.0f) {
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2];
            n d = d();
            float pow = (float) Math.pow(f, d.a());
            float pow2 = (float) Math.pow(f2, d.b());
            float pow3 = (float) Math.pow(f3, d.c());
            float[] e = e();
            return a((e[0] * pow) + (e[3] * pow2) + (e[6] * pow3), (e[1] * pow) + (e[4] * pow2) + (e[7] * pow3), (e[2] * pow) + (e[5] * pow2) + (e[8] * pow3));
        }
        return new float[]{fArr[0], fArr[1], fArr[2]};
    }

    private n d() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4568a.a(org.a.c.b.j.bu);
        org.a.c.b.a aVar2 = aVar;
        if (aVar == null) {
            org.a.c.b.a aVar3 = new org.a.c.b.a();
            aVar2 = aVar3;
            aVar3.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
            aVar2.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
            aVar2.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
            this.f4568a.a(org.a.c.b.j.bu, (org.a.c.b.b) aVar2);
        }
        return new n(aVar2);
    }

    private float[] e() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4568a.a(org.a.c.b.j.cf);
        if (aVar == null) {
            return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        }
        return aVar.d();
    }
}
