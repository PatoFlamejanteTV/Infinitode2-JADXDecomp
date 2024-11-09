package org.a.c.h.f.a;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/u.class */
public final class u extends v {

    /* renamed from: a, reason: collision with root package name */
    private final e f4590a;

    /* renamed from: b, reason: collision with root package name */
    private f f4591b;
    private org.a.c.h.a.b.a c;
    private Map<Integer, float[]> d;

    public u() {
        this.f4590a = new e(new float[]{1.0f}, this);
        this.f4591b = null;
        this.c = null;
        this.d = null;
        this.e = new org.a.c.b.a();
        this.e.a((org.a.c.b.b) org.a.c.b.j.dp);
        this.e.a((org.a.c.b.b) org.a.c.b.j.a(""));
        this.e.a((org.a.c.b.b) org.a.c.b.k.f4371a);
        this.e.a((org.a.c.b.b) org.a.c.b.k.f4371a);
    }

    public u(org.a.c.b.a aVar) {
        this.f4590a = new e(new float[]{1.0f}, this);
        this.f4591b = null;
        this.c = null;
        this.d = null;
        this.e = aVar;
        this.f4591b = f.a(this.e.a(2));
        this.c = org.a.c.h.a.b.a.a(this.e.a(3));
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.dp.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 1;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f4590a;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        int i = (int) (fArr[0] * 255.0f);
        float[] fArr2 = this.d.get(Integer.valueOf(i));
        if (fArr2 != null) {
            return fArr2;
        }
        float[] a2 = this.f4591b.a(this.c.a(fArr));
        this.d.put(Integer.valueOf(i), a2);
        return a2;
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        if (this.f4591b instanceof r) {
            return b(writableRaster);
        }
        WritableRaster createBandedRaster = Raster.createBandedRaster(0, writableRaster.getWidth(), writableRaster.getHeight(), this.f4591b.b(), new Point(0, 0));
        int b2 = this.f4591b.b();
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        float[] fArr = new float[1];
        HashMap hashMap = new HashMap();
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, fArr);
                Integer valueOf = Integer.valueOf(Float.floatToIntBits(fArr[0]));
                int[] iArr = (int[]) hashMap.get(valueOf);
                int[] iArr2 = iArr;
                if (iArr == null) {
                    iArr2 = new int[b2];
                    a(fArr, iArr2);
                    hashMap.put(valueOf, iArr2);
                }
                createBandedRaster.setPixel(i2, i, iArr2);
            }
        }
        return this.f4591b.a(createBandedRaster);
    }

    private BufferedImage b(WritableRaster writableRaster) {
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        WritableRaster raster = bufferedImage.getRaster();
        float[] fArr = new float[1];
        HashMap hashMap = new HashMap();
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, fArr);
                Integer valueOf = Integer.valueOf(Float.floatToIntBits(fArr[0]));
                int[] iArr = (int[]) hashMap.get(valueOf);
                int[] iArr2 = iArr;
                if (iArr == null) {
                    fArr[0] = fArr[0] / 255.0f;
                    float[] a2 = this.f4591b.a(this.c.a(fArr));
                    iArr2 = r0;
                    int[] iArr3 = {(int) (a2[0] * 255.0f)};
                    iArr2[1] = (int) (a2[1] * 255.0f);
                    iArr2[2] = (int) (a2[2] * 255.0f);
                    hashMap.put(valueOf, iArr2);
                }
                raster.setPixel(i2, i, iArr2);
            }
        }
        return bufferedImage;
    }

    private void a(float[] fArr, int[] iArr) {
        fArr[0] = fArr[0] / 255.0f;
        float[] a2 = this.c.a(fArr);
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = (int) (a2[i] * 255.0f);
        }
    }

    private String d() {
        return ((org.a.c.b.j) this.e.a(1)).a();
    }

    public final String toString() {
        return a() + "{\"" + d() + "\" " + this.f4591b.a() + SequenceUtils.SPACE + this.c + "}";
    }
}
