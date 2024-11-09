package org.a.c.c;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import org.lwjgl.system.macosx.CoreGraphics;

/* loaded from: infinitode-2.jar:org/a/c/c/q.class */
public final class q extends l {
    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i, j jVar) {
        k kVar = new k(new org.a.c.b.d());
        kVar.a().a(dVar);
        BufferedImage a2 = a(inputStream, jVar, kVar);
        WritableRaster raster = a2.getRaster();
        switch (raster.getDataBuffer().getDataType()) {
            case 0:
                outputStream.write(raster.getDataBuffer().getData());
                return kVar;
            case 1:
                for (short s : raster.getDataBuffer().getData()) {
                    outputStream.write(s >> 8);
                    outputStream.write(s);
                }
                return kVar;
            case 2:
            default:
                throw new IOException("Data type " + raster.getDataBuffer().getDataType() + " not implemented");
            case 3:
                int[] iArr = new int[raster.getNumBands()];
                for (int i2 = 0; i2 < a2.getHeight(); i2++) {
                    for (int i3 = 0; i3 < a2.getWidth(); i3++) {
                        raster.getPixel(i3, i2, iArr);
                        for (int i4 : iArr) {
                            outputStream.write(i4);
                        }
                    }
                }
                return kVar;
        }
    }

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        return a(inputStream, outputStream, dVar, i, j.f4400a);
    }

    private static BufferedImage a(InputStream inputStream, j jVar, k kVar) {
        ImageReader a2 = a("JPEG2000", "Java Advanced Imaging (JAI) Image I/O Tools are not installed");
        ImageInputStream imageInputStream = null;
        try {
            imageInputStream = new MemoryCacheImageInputStream(inputStream);
            a2.setInput(imageInputStream, true, true);
            ImageReadParam defaultReadParam = a2.getDefaultReadParam();
            defaultReadParam.setSourceRegion(jVar.a());
            defaultReadParam.setSourceSubsampling(jVar.b(), jVar.c(), jVar.d(), jVar.e());
            try {
                BufferedImage read = a2.read(0, defaultReadParam);
                org.a.c.b.d a3 = kVar.a();
                a3.a(org.a.c.b.j.z, read.getColorModel().getPixelSize() / read.getRaster().getNumBands());
                if (!a3.b(org.a.c.b.j.bF, false)) {
                    a3.a(org.a.c.b.j.aq, (org.a.c.b.b) null);
                }
                a3.a(org.a.c.b.j.ea, a2.getWidth(0));
                a3.a(org.a.c.b.j.bx, a2.getHeight(0));
                if (!a3.o(org.a.c.b.j.ac)) {
                    if ((read.getSampleModel() instanceof MultiPixelPackedSampleModel) && read.getColorModel().getPixelSize() == 1 && read.getRaster().getNumBands() == 1 && (read.getColorModel() instanceof IndexColorModel)) {
                        kVar.a(new org.a.c.h.f.a.q(ColorSpace.getInstance(CoreGraphics.kCGErrorInvalidContext)));
                    } else {
                        kVar.a(new org.a.c.h.f.a.q(read.getColorModel().getColorSpace()));
                    }
                }
                imageInputStream.close();
                a2.dispose();
                return read;
            } catch (Exception e) {
                throw new IOException("Could not read JPEG 2000 (JPX) image", e);
            }
        } catch (Throwable th) {
            if (imageInputStream != null) {
                imageInputStream.close();
            }
            a2.dispose();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        throw new UnsupportedOperationException("JPX encoding not implemented");
    }
}
