package org.a.c.h.f.a;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/q.class */
public final class q extends f {

    /* renamed from: a, reason: collision with root package name */
    private final ColorSpace f4587a;

    public q(ColorSpace colorSpace) {
        this.f4587a = colorSpace;
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return "JPX";
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return this.f4587a.getNumComponents();
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        throw new UnsupportedOperationException("JPX color spaces don't support drawing");
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        throw new UnsupportedOperationException("JPX color spaces don't support drawing");
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        return a(writableRaster, this.f4587a);
    }

    @Override // org.a.c.h.f.a.f, org.a.c.h.a.c
    public final org.a.c.b.b f() {
        throw new UnsupportedOperationException("JPX color spaces don't have COS objects");
    }
}
