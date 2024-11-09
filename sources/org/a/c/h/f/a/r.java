package org.a.c.h.f.a;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/r.class */
public final class r extends b {
    private e f;

    public r() {
        super(org.a.c.b.j.bT);
    }

    public r(org.a.c.b.a aVar) {
        super(aVar);
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.bT.a();
    }

    @Override // org.a.c.h.f.a.a, org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        WritableRaster raster = bufferedImage.getRaster();
        float a2 = e().a();
        float b2 = e().b();
        float a3 = g().a();
        float b3 = g().b();
        float[] fArr = new float[3];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, fArr);
                fArr[0] = fArr[0] / 255.0f;
                fArr[1] = fArr[1] / 255.0f;
                fArr[2] = fArr[2] / 255.0f;
                fArr[0] = fArr[0] * 100.0f;
                fArr[1] = a2 + (fArr[1] * (b2 - a2));
                fArr[2] = a3 + (fArr[2] * (b3 - a3));
                float[] a4 = a(fArr);
                a4[0] = a4[0] * 255.0f;
                a4[1] = a4[1] * 255.0f;
                a4[2] = a4[2] * 255.0f;
                raster.setPixel(i2, i, a4);
            }
        }
        return bufferedImage;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        float f = (fArr[0] + 16.0f) * 0.00862069f;
        return a(this.f4569b * a(f + (fArr[1] * 0.002f)), this.c * a(f), this.d * a(f - (fArr[2] * 0.005f)));
    }

    private static float a(float f) {
        if (f > 0.20689655172413793d) {
            return f * f * f;
        }
        return 0.12841855f * (f - 0.13793103f);
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 3;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        if (this.f == null) {
            this.f = new e(new float[]{0.0f, Math.max(0.0f, e().a()), Math.max(0.0f, g().a())}, this);
        }
        return this.f;
    }

    private static org.a.c.b.a d() {
        org.a.c.b.a aVar = new org.a.c.b.a();
        aVar.a((org.a.c.b.b) new org.a.c.b.f(-100.0f));
        aVar.a((org.a.c.b.b) new org.a.c.b.f(100.0f));
        aVar.a((org.a.c.b.b) new org.a.c.b.f(-100.0f));
        aVar.a((org.a.c.b.b) new org.a.c.b.f(100.0f));
        return aVar;
    }

    private org.a.c.h.a.g e() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4568a.a(org.a.c.b.j.db);
        org.a.c.b.a aVar2 = aVar;
        if (aVar == null) {
            aVar2 = d();
        }
        return new org.a.c.h.a.g(aVar2, 0);
    }

    private org.a.c.h.a.g g() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4568a.a(org.a.c.b.j.db);
        org.a.c.b.a aVar2 = aVar;
        if (aVar == null) {
            aVar2 = d();
        }
        return new org.a.c.h.a.g(aVar2, 1);
    }
}
