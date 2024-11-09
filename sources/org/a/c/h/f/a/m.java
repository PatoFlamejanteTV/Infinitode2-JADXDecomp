package org.a.c.h.f.a;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/m.class */
public final class m extends h {

    /* renamed from: a, reason: collision with root package name */
    public static final m f4580a = new m();

    /* renamed from: b, reason: collision with root package name */
    private final e f4581b = new e(new float[]{0.0f, 0.0f, 0.0f}, this);
    private volatile ColorSpace c;

    private m() {
    }

    private void d() {
        if (this.c != null) {
            return;
        }
        synchronized (this) {
            if (this.c != null) {
                return;
            }
            this.c = ColorSpace.getInstance(1000);
            this.c.toRGB(new float[]{0.0f, 0.0f, 0.0f, 0.0f});
        }
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.aD.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 3;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f4581b;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        return fArr;
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        d();
        BufferedImage bufferedImage = new BufferedImage(writableRaster.getWidth(), writableRaster.getHeight(), 1);
        bufferedImage.setData(writableRaster);
        return bufferedImage;
    }
}
