package org.a.c.i;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;
import org.a.c.b.l;

/* loaded from: infinitode-2.jar:org/a/c/i/d.class */
public final class d implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private static float[] f4657a = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    /* renamed from: b, reason: collision with root package name */
    private final float[] f4658b;

    public d() {
        this.f4658b = new float[f4657a.length];
        System.arraycopy(f4657a, 0, this.f4658b, 0, f4657a.length);
    }

    public d(org.a.c.b.a aVar) {
        this.f4658b = new float[f4657a.length];
        this.f4658b[0] = ((l) aVar.a(0)).a();
        this.f4658b[1] = ((l) aVar.a(1)).a();
        this.f4658b[3] = ((l) aVar.a(2)).a();
        this.f4658b[4] = ((l) aVar.a(3)).a();
        this.f4658b[6] = ((l) aVar.a(4)).a();
        this.f4658b[7] = ((l) aVar.a(5)).a();
        this.f4658b[8] = 1.0f;
    }

    public d(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f4658b = new float[f4657a.length];
        this.f4658b[0] = f;
        this.f4658b[1] = f2;
        this.f4658b[3] = f3;
        this.f4658b[4] = f4;
        this.f4658b[6] = f5;
        this.f4658b[7] = f6;
        this.f4658b[8] = 1.0f;
    }

    public d(AffineTransform affineTransform) {
        this.f4658b = new float[f4657a.length];
        System.arraycopy(f4657a, 0, this.f4658b, 0, f4657a.length);
        this.f4658b[0] = (float) affineTransform.getScaleX();
        this.f4658b[1] = (float) affineTransform.getShearY();
        this.f4658b[3] = (float) affineTransform.getShearX();
        this.f4658b[4] = (float) affineTransform.getScaleY();
        this.f4658b[6] = (float) affineTransform.getTranslateX();
        this.f4658b[7] = (float) affineTransform.getTranslateY();
    }

    public static d a(org.a.c.b.b bVar) {
        if (!(bVar instanceof org.a.c.b.a)) {
            return new d();
        }
        org.a.c.b.a aVar = (org.a.c.b.a) bVar;
        if (aVar.b() < 6) {
            return new d();
        }
        for (int i = 0; i < 6; i++) {
            if (!(aVar.a(i) instanceof l)) {
                return new d();
            }
        }
        return new d(aVar);
    }

    public final AffineTransform a() {
        return new AffineTransform(this.f4658b[0], this.f4658b[1], this.f4658b[3], this.f4658b[4], this.f4658b[6], this.f4658b[7]);
    }

    public final Point2D.Float a(float f, float f2) {
        float f3 = this.f4658b[0];
        float f4 = this.f4658b[1];
        float f5 = this.f4658b[3];
        float f6 = this.f4658b[4];
        return new Point2D.Float((f * f3) + (f2 * f5) + this.f4658b[6], (f * f4) + (f2 * f6) + this.f4658b[7]);
    }

    public static d a(double d, float f, float f2) {
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        d dVar = new d();
        dVar.f4658b[0] = cos;
        dVar.f4658b[1] = sin;
        dVar.f4658b[3] = -sin;
        dVar.f4658b[4] = cos;
        dVar.f4658b[6] = f;
        dVar.f4658b[7] = f2;
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public d clone() {
        d dVar = new d();
        System.arraycopy(this.f4658b, 0, dVar.f4658b, 0, 9);
        return dVar;
    }

    public final float b() {
        return this.f4658b[0];
    }

    public final float c() {
        return this.f4658b[4];
    }

    public final String toString() {
        return "[" + this.f4658b[0] + "," + this.f4658b[1] + "," + this.f4658b[3] + "," + this.f4658b[4] + "," + this.f4658b[6] + "," + this.f4658b[7] + "]";
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f4658b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.f4658b, ((d) obj).f4658b);
    }
}
