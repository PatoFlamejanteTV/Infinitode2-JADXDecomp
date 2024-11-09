package org.a.c.h.f.a;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/g.class */
public class g extends h {

    /* renamed from: a, reason: collision with root package name */
    public static g f4572a = new g();
    private volatile ICC_ColorSpace c;

    /* renamed from: b, reason: collision with root package name */
    private final e f4573b = new e(new float[]{0.0f, 0.0f, 0.0f, 1.0f}, this);
    private boolean d = false;

    protected g() {
    }

    private void d() {
        if (this.c != null) {
            return;
        }
        synchronized (this) {
            if (this.c != null) {
                return;
            }
            ICC_Profile e = e();
            if (e == null) {
                throw new IOException("Default CMYK color profile could not be loaded");
            }
            this.c = new ICC_ColorSpace(e);
            this.c.toRGB(new float[]{0.0f, 0.0f, 0.0f, 0.0f});
            this.d = System.getProperty("org.apache.pdfbox.rendering.UsePureJavaCMYKConversion") != null;
        }
    }

    private static ICC_Profile e() {
        InputStream resourceAsStream = g.class.getResourceAsStream("/org/apache/pdfbox/resources/icc/ISOcoated_v2_300_bas.icc");
        if (resourceAsStream == null) {
            throw new IOException("Error loading resource: /org/apache/pdfbox/resources/icc/ISOcoated_v2_300_bas.icc");
        }
        ICC_Profile iCC_Profile = ICC_Profile.getInstance(resourceAsStream);
        resourceAsStream.close();
        return iCC_Profile;
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.aA.a();
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        return 4;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.f4573b;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        d();
        return this.c.toRGB(fArr);
    }

    @Override // org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        d();
        return a(writableRaster, (ColorSpace) this.c);
    }

    @Override // org.a.c.h.f.a.f
    protected final BufferedImage a(WritableRaster writableRaster, ColorSpace colorSpace) {
        if (this.d) {
            BufferedImage bufferedImage = new BufferedImage(writableRaster.getWidth(), writableRaster.getHeight(), 1);
            ColorSpace colorSpace2 = bufferedImage.getColorModel().getColorSpace();
            WritableRaster raster = bufferedImage.getRaster();
            float[] fArr = new float[4];
            float[] fArr2 = new float[4];
            fArr2[0] = -1.0f;
            fArr2[1] = -1.0f;
            fArr2[2] = -1.0f;
            fArr2[3] = -1.0f;
            float[] fArr3 = new float[3];
            int width = writableRaster.getWidth();
            int minX = writableRaster.getMinX();
            int height = writableRaster.getHeight();
            int minY = writableRaster.getMinY();
            for (int i = minX; i < width + minX; i++) {
                for (int i2 = minY; i2 < height + minY; i2++) {
                    writableRaster.getPixel(i, i2, fArr);
                    if (!Arrays.equals(fArr2, fArr)) {
                        for (int i3 = 0; i3 < 4; i3++) {
                            fArr2[i3] = fArr[i3];
                            fArr[i3] = fArr[i3] / 255.0f;
                        }
                        fArr3 = colorSpace2.fromCIEXYZ(colorSpace.toCIEXYZ(fArr));
                        for (int i4 = 0; i4 < fArr3.length; i4++) {
                            fArr3[i4] = fArr3[i4] * 255.0f;
                        }
                    }
                    raster.setPixel(i, i2, fArr3);
                }
            }
            return bufferedImage;
        }
        return super.a(writableRaster, colorSpace);
    }
}
