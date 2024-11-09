package org.a.c.h.f.a;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/a.class */
public abstract class a extends f {
    @Override // org.a.c.h.f.a.f
    public BufferedImage a(WritableRaster writableRaster) {
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        WritableRaster raster = bufferedImage.getRaster();
        float[] fArr = new float[3];
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                writableRaster.getPixel(i2, i, fArr);
                fArr[0] = fArr[0] / 255.0f;
                fArr[1] = fArr[1] / 255.0f;
                fArr[2] = fArr[2] / 255.0f;
                float[] a2 = a(fArr);
                a2[0] = a2[0] * 255.0f;
                a2[1] = a2[1] * 255.0f;
                a2[2] = a2[2] * 255.0f;
                raster.setPixel(i2, i, a2);
            }
        }
        return bufferedImage;
    }

    public String toString() {
        return a();
    }
}
