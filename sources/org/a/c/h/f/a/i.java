package org.a.c.h.f.a;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/i.class */
public final class i extends h {

    /* renamed from: a, reason: collision with root package name */
    public static final i f4574a = new i();

    /* renamed from: b, reason: collision with root package name */
    private final e f4575b = new e(new float[]{0.0f}, this);

    private i() {
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.aB.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 1;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f4575b;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        return new float[]{fArr[0], fArr[0], fArr[0]};
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        int[] iArr = new int[1];
        int[] iArr2 = new int[3];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, iArr);
                iArr2[0] = iArr[0];
                iArr2[1] = iArr[0];
                iArr2[2] = iArr[0];
                bufferedImage.getRaster().setPixel(i2, i, iArr2);
            }
        }
        return bufferedImage;
    }
}
