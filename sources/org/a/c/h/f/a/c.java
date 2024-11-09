package org.a.c.h.f.a;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/c.class */
public final class c extends b {
    private final e f;
    private final Map<Float, float[]> g;

    public c() {
        super(org.a.c.b.j.K);
        this.f = new e(new float[]{0.0f}, this);
        this.g = new HashMap();
    }

    public c(org.a.c.b.a aVar) {
        super(aVar);
        this.f = new e(new float[]{0.0f}, this);
        this.g = new HashMap();
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.K.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 1;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        if (this.f4569b == 1.0f && this.c == 1.0f && this.d == 1.0f) {
            float f = fArr[0];
            float[] fArr2 = this.g.get(Float.valueOf(f));
            if (fArr2 != null) {
                return (float[]) fArr2.clone();
            }
            float pow = (float) Math.pow(f, d());
            float[] a2 = a(pow, pow, pow);
            this.g.put(Float.valueOf(f), a2.clone());
            return a2;
        }
        return new float[]{fArr[0], fArr[0], fArr[0]};
    }

    private float d() {
        float f = 1.0f;
        org.a.c.b.l lVar = (org.a.c.b.l) this.f4568a.a(org.a.c.b.j.bu);
        if (lVar != null) {
            f = lVar.a();
        }
        return f;
    }
}
