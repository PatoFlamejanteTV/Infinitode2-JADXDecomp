package org.a.c.h.f.a;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/j.class */
public final class j extends v {

    /* renamed from: a, reason: collision with root package name */
    private f f4576a;

    /* renamed from: b, reason: collision with root package name */
    private org.a.c.h.a.b.a f4577b;
    private k c;
    private e d;
    private int f;
    private int[] g;
    private f h;
    private u[] i;

    public j() {
        this.f4576a = null;
        this.f4577b = null;
        this.e = new org.a.c.b.a();
        this.e.a((org.a.c.b.b) org.a.c.b.j.aC);
        this.e.a((org.a.c.b.b) org.a.c.b.k.f4371a);
        this.e.a((org.a.c.b.b) org.a.c.b.k.f4371a);
        this.e.a((org.a.c.b.b) org.a.c.b.k.f4371a);
    }

    public j(org.a.c.b.a aVar) {
        this.f4576a = null;
        this.f4577b = null;
        this.e = aVar;
        this.f4576a = f.a(this.e.a(2));
        this.f4577b = org.a.c.h.a.b.a.a(this.e.a(3));
        if (this.e.b() > 4) {
            this.c = new k((org.a.c.b.d) this.e.a(4));
        }
        d();
        int b2 = b();
        float[] fArr = new float[b2];
        for (int i = 0; i < b2; i++) {
            fArr[i] = 1.0f;
        }
        this.d = new e(fArr, this);
    }

    private void d() {
        if (this.c == null) {
            return;
        }
        List<String> g = g();
        this.f = g.size();
        this.g = new int[this.f];
        for (int i = 0; i < this.f; i++) {
            this.g[i] = -1;
        }
        if (this.c.b() != null) {
            List<String> b2 = this.c.b().b();
            for (int i2 = 0; i2 < this.f; i2++) {
                this.g[i2] = b2.indexOf(g.get(i2));
            }
            this.h = this.c.b().a();
        }
        this.i = new u[this.f];
        Map<String, u> a2 = this.c.a();
        for (int i3 = 0; i3 < this.f; i3++) {
            u uVar = a2.get(g.get(i3));
            if (uVar != null) {
                this.i[i3] = uVar;
                if (!e()) {
                    this.g[i3] = -1;
                }
            } else {
                this.i[i3] = null;
            }
        }
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        if (this.c != null) {
            return b(writableRaster);
        }
        return c(writableRaster);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v71, types: [org.a.c.h.f.a.f] */
    private BufferedImage b(WritableRaster writableRaster) {
        u uVar;
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        WritableRaster raster = bufferedImage.getRaster();
        Graphics2D createGraphics = bufferedImage.createGraphics();
        createGraphics.setBackground(Color.WHITE);
        createGraphics.clearRect(0, 0, width, height);
        createGraphics.dispose();
        for (int i = 0; i < this.f; i++) {
            if (this.g[i] >= 0) {
                uVar = this.h;
            } else {
                if (this.i[i] == null) {
                    return c(writableRaster);
                }
                uVar = this.i[i];
            }
            WritableRaster createBandedRaster = Raster.createBandedRaster(0, width, height, uVar.b(), new Point(0, 0));
            int[] iArr = new int[this.f];
            int[] iArr2 = new int[uVar.b()];
            boolean z = this.g[i] >= 0;
            int i2 = this.g[i];
            for (int i3 = 0; i3 < height; i3++) {
                for (int i4 = 0; i4 < width; i4++) {
                    writableRaster.getPixel(i4, i3, iArr);
                    if (z) {
                        iArr2[i2] = iArr[i];
                    } else {
                        iArr2[0] = iArr[i];
                    }
                    createBandedRaster.setPixel(i4, i3, iArr2);
                }
            }
            WritableRaster raster2 = uVar.a(createBandedRaster).getRaster();
            int[] iArr3 = new int[3];
            int[] iArr4 = new int[3];
            for (int i5 = 0; i5 < height; i5++) {
                for (int i6 = 0; i6 < width; i6++) {
                    raster2.getPixel(i6, i5, iArr3);
                    raster.getPixel(i6, i5, iArr4);
                    iArr3[0] = (iArr3[0] * iArr4[0]) >> 8;
                    iArr3[1] = (iArr3[1] * iArr4[1]) >> 8;
                    iArr3[2] = (iArr3[2] * iArr4[2]) >> 8;
                    raster.setPixel(i6, i5, iArr3);
                }
            }
        }
        return bufferedImage;
    }

    private BufferedImage c(WritableRaster writableRaster) {
        HashMap hashMap = new HashMap();
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        WritableRaster raster = bufferedImage.getRaster();
        int[] iArr = new int[3];
        int size = g().size();
        float[] fArr = new float[size];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, fArr);
                String f = Float.toString(fArr[0]);
                for (int i3 = 1; i3 < size; i3++) {
                    f = f + "#" + Float.toString(fArr[i3]);
                }
                int[] iArr2 = (int[]) hashMap.get(f);
                if (iArr2 != null) {
                    raster.setPixel(i2, i, iArr2);
                } else {
                    for (int i4 = 0; i4 < size; i4++) {
                        fArr[i4] = fArr[i4] / 255.0f;
                    }
                    float[] a2 = this.f4576a.a(this.f4577b.a(fArr));
                    for (int i5 = 0; i5 < 3; i5++) {
                        iArr[i5] = (int) (a2[i5] * 255.0f);
                    }
                    hashMap.put(f, iArr.clone());
                    raster.setPixel(i2, i, iArr);
                }
            }
        }
        return bufferedImage;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        if (this.c != null) {
            return b(fArr);
        }
        return c(fArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v37, types: [org.a.c.h.f.a.f] */
    private float[] b(float[] fArr) {
        u uVar;
        float[] fArr2 = {1.0f, 1.0f, 1.0f};
        for (int i = 0; i < this.f; i++) {
            if (this.g[i] >= 0) {
                uVar = this.h;
            } else {
                if (this.i[i] == null) {
                    return c(fArr);
                }
                uVar = this.i[i];
            }
            boolean z = this.g[i] >= 0;
            float[] fArr3 = new float[uVar.b()];
            int i2 = this.g[i];
            if (z) {
                fArr3[i2] = fArr[i];
            } else {
                fArr3[0] = fArr[i];
            }
            float[] a2 = uVar.a(fArr3);
            fArr2[0] = fArr2[0] * a2[0];
            fArr2[1] = fArr2[1] * a2[1];
            fArr2[2] = fArr2[2] * a2[2];
        }
        return fArr2;
    }

    private float[] c(float[] fArr) {
        return this.f4576a.a(this.f4577b.a(fArr));
    }

    private boolean e() {
        return this.c != null && this.c.c();
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.aC.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return g().size();
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.d;
    }

    private List<String> g() {
        return org.a.c.h.a.a.c((org.a.c.b.a) this.e.a(1));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(a());
        sb.append('{');
        for (String str : g()) {
            sb.append('\"');
            sb.append(str);
            sb.append("\" ");
        }
        sb.append(this.f4576a.a());
        sb.append(' ');
        sb.append(this.f4577b);
        sb.append(' ');
        if (this.c != null) {
            sb.append(this.c);
        }
        sb.append('}');
        return sb.toString();
    }
}
