package org.a.c.h.f.a;

import java.util.Arrays;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/e.class */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f4570a;

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.j f4571b;
    private final f c;

    public e(org.a.c.b.a aVar, f fVar) {
        if (aVar.b() > 0 && (aVar.b(aVar.b() - 1) instanceof org.a.c.b.j)) {
            this.f4570a = new float[aVar.b() - 1];
            for (int i = 0; i < aVar.b() - 1; i++) {
                this.f4570a[i] = ((org.a.c.b.l) aVar.b(i)).a();
            }
            this.f4571b = (org.a.c.b.j) aVar.b(aVar.b() - 1);
        } else {
            this.f4570a = new float[aVar.b()];
            for (int i2 = 0; i2 < aVar.b(); i2++) {
                this.f4570a[i2] = ((org.a.c.b.l) aVar.b(i2)).a();
            }
            this.f4571b = null;
        }
        this.c = fVar;
    }

    public e(float[] fArr, f fVar) {
        this.f4570a = (float[]) fArr.clone();
        this.f4571b = null;
        this.c = fVar;
    }

    public final float[] a() {
        if ((this.c instanceof t) || this.c == null) {
            return (float[]) this.f4570a.clone();
        }
        return Arrays.copyOf(this.f4570a, this.c.b());
    }

    public final org.a.c.b.j b() {
        return this.f4571b;
    }

    public final f c() {
        return this.c;
    }

    public final String toString() {
        return "PDColor{components=" + Arrays.toString(this.f4570a) + ", patternName=" + this.f4571b + "}";
    }
}
