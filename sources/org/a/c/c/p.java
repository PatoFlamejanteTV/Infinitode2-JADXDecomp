package org.a.c.c;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/c/p.class */
public final class p extends l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4408a = org.a.a.a.c.a(p.class);

    /* renamed from: b, reason: collision with root package name */
    private static boolean f4409b = false;

    private static synchronized void b() {
        if (!f4409b) {
            f4409b = true;
        }
    }

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i, j jVar) {
        ImageReader a2 = a("JBIG2", "jbig2-imageio is not installed");
        if (a2.getClass().getName().contains("levigo")) {
            b();
        }
        int b2 = dVar.b(org.a.c.b.j.z, 1);
        org.a.c.b.d a3 = a(dVar, i);
        ImageReadParam defaultReadParam = a2.getDefaultReadParam();
        defaultReadParam.setSourceSubsampling(jVar.b(), jVar.c(), jVar.d(), jVar.e());
        defaultReadParam.setSourceRegion(jVar.a());
        org.a.c.b.p pVar = null;
        if (a3 != null) {
            pVar = (org.a.c.b.p) a3.a(org.a.c.b.j.bM);
        }
        ImageInputStream imageInputStream = null;
        try {
            if (pVar != null) {
                imageInputStream = ImageIO.createImageInputStream(new SequenceInputStream(pVar.k(), inputStream));
                a2.setInput(imageInputStream);
            } else {
                imageInputStream = ImageIO.createImageInputStream(inputStream);
                a2.setInput(imageInputStream);
            }
            try {
                BufferedImage read = a2.read(0, defaultReadParam);
                if (read.getColorModel().getPixelSize() != b2) {
                    BufferedImage bufferedImage = new BufferedImage(read.getWidth(), read.getHeight(), 12);
                    Graphics graphics = bufferedImage.getGraphics();
                    graphics.drawImage(read, 0, 0, (ImageObserver) null);
                    graphics.dispose();
                    read = bufferedImage;
                }
                DataBufferByte dataBuffer = read.getData().getDataBuffer();
                if (dataBuffer.getDataType() == 0) {
                    outputStream.write(dataBuffer.getData());
                    return new k(dVar);
                }
                throw new IOException("Unexpected image buffer type");
            } catch (Exception e) {
                throw new IOException("Could not read JBIG2 image", e);
            }
        } finally {
            if (imageInputStream != null) {
                imageInputStream.close();
            }
            a2.dispose();
        }
    }

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        return a(inputStream, outputStream, dVar, i, j.f4400a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        throw new UnsupportedOperationException("JBIG2 encoding not implemented");
    }
}
