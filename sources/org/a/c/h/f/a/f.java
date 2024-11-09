package org.a.c.h.f.a;

import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ComponentColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Hashtable;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/f.class */
public abstract class f implements org.a.c.h.a.c {
    protected org.a.c.b.a e;

    public abstract String a();

    public abstract int b();

    public abstract e c();

    public abstract float[] a(float[] fArr);

    public abstract BufferedImage a(WritableRaster writableRaster);

    public static f a(org.a.c.b.b bVar) {
        return a(bVar, (org.a.c.h.j) null);
    }

    public static f a(org.a.c.b.b bVar, org.a.c.h.j jVar) {
        return a(bVar, jVar, false);
    }

    public static f a(org.a.c.b.b bVar, org.a.c.h.j jVar, boolean z) {
        if (bVar instanceof org.a.c.b.m) {
            return a((org.a.c.b.m) bVar, jVar);
        }
        if (bVar instanceof org.a.c.b.j) {
            org.a.c.b.j jVar2 = (org.a.c.b.j) bVar;
            if (jVar != null) {
                org.a.c.b.j jVar3 = null;
                if (jVar2.equals(org.a.c.b.j.aA) && jVar.c(org.a.c.b.j.as)) {
                    jVar3 = org.a.c.b.j.as;
                } else if (jVar2.equals(org.a.c.b.j.aD) && jVar.c(org.a.c.b.j.av)) {
                    jVar3 = org.a.c.b.j.av;
                } else if (jVar2.equals(org.a.c.b.j.aB) && jVar.c(org.a.c.b.j.au)) {
                    jVar3 = org.a.c.b.j.au;
                }
                if (jVar.c(jVar3) && !z) {
                    return jVar.a(jVar3, true);
                }
            }
            if (jVar2 == org.a.c.b.j.aA) {
                return g.f4572a;
            }
            if (jVar2 == org.a.c.b.j.aD) {
                return m.f4580a;
            }
            if (jVar2 == org.a.c.b.j.aB) {
                return i.f4574a;
            }
            if (jVar2 == org.a.c.b.j.cQ) {
                return new t(jVar);
            }
            if (jVar != null) {
                if (!jVar.c(jVar2)) {
                    throw new org.a.c.h.a("Missing color space: " + jVar2.a());
                }
                return jVar.b(jVar2);
            }
            throw new org.a.c.h.a("Unknown color space: " + jVar2.a());
        }
        if (bVar instanceof org.a.c.b.a) {
            org.a.c.b.a aVar = (org.a.c.b.a) bVar;
            if (aVar.b() == 0) {
                throw new IOException("Colorspace array is empty");
            }
            org.a.c.b.b a2 = aVar.a(0);
            if (!(a2 instanceof org.a.c.b.j)) {
                throw new IOException("First element in colorspace array must be a name");
            }
            org.a.c.b.j jVar4 = (org.a.c.b.j) a2;
            if (jVar4 == org.a.c.b.j.K) {
                return new c(aVar);
            }
            if (jVar4 == org.a.c.b.j.L) {
                return new d(aVar);
            }
            if (jVar4 == org.a.c.b.j.aC) {
                return new j(aVar);
            }
            if (jVar4 == org.a.c.b.j.bH) {
                return new p(aVar, jVar);
            }
            if (jVar4 == org.a.c.b.j.dp) {
                return new u(aVar);
            }
            if (jVar4 == org.a.c.b.j.bz) {
                return o.a(aVar, jVar);
            }
            if (jVar4 == org.a.c.b.j.bT) {
                return new r(aVar);
            }
            if (jVar4 == org.a.c.b.j.cQ) {
                if (aVar.b() == 1) {
                    return new t(jVar);
                }
                return new t(jVar, a(aVar.b(1)));
            }
            if (jVar4 == org.a.c.b.j.aA || jVar4 == org.a.c.b.j.aD || jVar4 == org.a.c.b.j.aB) {
                return a(jVar4, jVar, z);
            }
            throw new IOException("Invalid color space kind: " + jVar4);
        }
        throw new IOException("Expected a name or array but got: " + bVar);
    }

    private static f a(org.a.c.b.m mVar, org.a.c.h.j jVar) {
        f b2;
        if (jVar != null && jVar.b() != null && (b2 = jVar.b().b(mVar)) != null) {
            return b2;
        }
        f a2 = a(mVar.a(), jVar);
        if (jVar != null && jVar.b() != null && a2 != null) {
            jVar.b().a(mVar, a2);
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BufferedImage a(WritableRaster writableRaster, ColorSpace colorSpace) {
        BufferedImage bufferedImage = new BufferedImage(new ComponentColorModel(colorSpace, false, false, 1, writableRaster.getDataBuffer().getDataType()), writableRaster, false, (Hashtable) null);
        BufferedImage bufferedImage2 = new BufferedImage(writableRaster.getWidth(), writableRaster.getHeight(), 1);
        new ColorConvertOp((RenderingHints) null).filter(bufferedImage, bufferedImage2);
        return bufferedImage2;
    }

    @Override // org.a.c.h.a.c
    public org.a.c.b.b f() {
        return this.e;
    }
}
