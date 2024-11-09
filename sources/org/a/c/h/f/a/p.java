package org.a.c.h.f.a;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/p.class */
public final class p extends v {

    /* renamed from: a, reason: collision with root package name */
    private final e f4585a;

    /* renamed from: b, reason: collision with root package name */
    private f f4586b;
    private byte[] c;
    private float[][] d;
    private int f;
    private int[][] g;

    public p() {
        this.f4585a = new e(new float[]{0.0f}, this);
        this.f4586b = null;
        this.e = new org.a.c.b.a();
        this.e.a((org.a.c.b.b) org.a.c.b.j.bH);
        this.e.a((org.a.c.b.b) org.a.c.b.j.aD);
        this.e.a((org.a.c.b.b) org.a.c.b.i.a(255L));
        this.e.a((org.a.c.b.b) org.a.c.b.k.f4371a);
    }

    public p(org.a.c.b.a aVar, org.a.c.h.j jVar) {
        this.f4585a = new e(new float[]{0.0f}, this);
        this.f4586b = null;
        this.e = aVar;
        this.f4586b = f.a(this.e.b(1), jVar);
        h();
        d();
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.bH.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 1;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f4585a;
    }

    private void d() {
        int b2 = this.f4586b.b();
        try {
            WritableRaster createBandedRaster = Raster.createBandedRaster(0, this.f + 1, 1, b2, new Point(0, 0));
            int[] iArr = new int[b2];
            int i = this.f;
            for (int i2 = 0; i2 <= i; i2++) {
                for (int i3 = 0; i3 < b2; i3++) {
                    iArr[i3] = (int) (this.d[i2][i3] * 255.0f);
                }
                createBandedRaster.setPixel(i2, 0, iArr);
            }
            WritableRaster raster = this.f4586b.a(createBandedRaster).getRaster();
            this.g = new int[this.f + 1][3];
            int i4 = this.f;
            for (int i5 = 0; i5 <= i4; i5++) {
                this.g[i5] = raster.getPixel(i5, 0, (int[]) null);
            }
        } catch (IllegalArgumentException e) {
            throw new IOException(e);
        }
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        if (fArr.length > 1) {
            throw new IllegalArgumentException("Indexed color spaces must have one color value");
        }
        int[] iArr = this.g[Math.min(Math.max(Math.round(fArr[0]), 0), this.f)];
        return new float[]{iArr[0] / 255.0f, iArr[1] / 255.0f, iArr[2] / 255.0f};
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        WritableRaster raster = bufferedImage.getRaster();
        int[] iArr = new int[1];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, iArr);
                raster.setPixel(i2, i, this.g[Math.min(iArr[0], this.f)]);
            }
        }
        return bufferedImage;
    }

    private int e() {
        return ((org.a.c.b.l) this.e.a(2)).c();
    }

    private byte[] g() {
        if (this.c == null) {
            org.a.c.b.b a2 = this.e.a(3);
            if (a2 instanceof org.a.c.b.s) {
                this.c = ((org.a.c.b.s) a2).c();
            } else if (a2 instanceof org.a.c.b.p) {
                this.c = new org.a.c.h.a.i((org.a.c.b.p) a2).g();
            } else if (a2 == null) {
                this.c = new byte[0];
            } else {
                throw new IOException("Error: Unknown type for lookup table " + a2);
            }
        }
        return this.c;
    }

    private void h() {
        byte[] g = g();
        int min = Math.min(e(), 255);
        int b2 = this.f4586b.b();
        if (g.length / b2 < min + 1) {
            min = (g.length / b2) - 1;
        }
        this.f = min;
        this.d = new float[min + 1][b2];
        int i = 0;
        for (int i2 = 0; i2 <= min; i2++) {
            for (int i3 = 0; i3 < b2; i3++) {
                this.d[i2][i3] = (g[i] & 255) / 255.0f;
                i++;
            }
        }
    }

    public final String toString() {
        return "Indexed{base:" + this.f4586b + " hival:" + e() + " lookup:(" + this.d.length + " entries)}";
    }
}
