package com.d.c.d;

/* loaded from: infinitode-2.jar:com/d/c/d/f.class */
public final class f implements g {

    /* renamed from: a, reason: collision with root package name */
    private final float f1054a;

    /* renamed from: b, reason: collision with root package name */
    private final float f1055b;
    private final float c;
    private final float d;

    public f(float f, float f2, float f3, float f4) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException();
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException();
        }
        if (f3 < 0.0f || f3 > 1.0f) {
            throw new IllegalArgumentException();
        }
        if (f4 < 0.0f || f4 > 1.0f) {
            throw new IllegalArgumentException();
        }
        this.f1054a = f;
        this.f1055b = f2;
        this.c = f3;
        this.d = f4;
    }

    public final float a() {
        return this.f1054a;
    }

    public final float b() {
        return this.f1055b;
    }

    public final float c() {
        return this.c;
    }

    public final float d() {
        return this.d;
    }

    public final String toString() {
        return "cmyk(" + this.f1054a + ", " + this.f1055b + ", " + this.c + ", " + this.d + ")";
    }

    @Override // com.d.c.d.g
    public final g e() {
        return new f(Math.min(1.0f, this.f1054a / 0.8f), Math.min(1.0f, this.f1055b / 0.8f), Math.min(1.0f, this.c / 0.8f), this.d);
    }

    public final int hashCode() {
        return ((((((31 + Float.floatToIntBits(this.d)) * 31) + Float.floatToIntBits(this.f1054a)) * 31) + Float.floatToIntBits(this.f1055b)) * 31) + Float.floatToIntBits(this.c);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        if (Float.floatToIntBits(this.d) != Float.floatToIntBits(fVar.d) || Float.floatToIntBits(this.f1054a) != Float.floatToIntBits(fVar.f1054a) || Float.floatToIntBits(this.f1055b) != Float.floatToIntBits(fVar.f1055b) || Float.floatToIntBits(this.c) != Float.floatToIntBits(fVar.c)) {
            return false;
        }
        return true;
    }
}
