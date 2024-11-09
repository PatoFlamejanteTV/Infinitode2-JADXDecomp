package org.a.c.c;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

/* loaded from: infinitode-2.jar:org/a/c/c/l.class */
public abstract class l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4404a = org.a.a.a.c.a(l.class);

    public abstract k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar);

    public k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i, j jVar) {
        return a(inputStream, outputStream, dVar, i);
    }

    public final void b(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        a(inputStream, outputStream, dVar.i());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static org.a.c.b.d a(org.a.c.b.d dVar, int i) {
        org.a.c.b.b a2 = dVar.a(org.a.c.b.j.aY, org.a.c.b.j.aU);
        org.a.c.b.b a3 = dVar.a(org.a.c.b.j.ar, org.a.c.b.j.aJ);
        if ((a2 instanceof org.a.c.b.j) && (a3 instanceof org.a.c.b.d)) {
            return (org.a.c.b.d) a3;
        }
        if ((a2 instanceof org.a.c.b.a) && (a3 instanceof org.a.c.b.a)) {
            org.a.c.b.a aVar = (org.a.c.b.a) a3;
            if (i < aVar.b() && (aVar.a(i) instanceof org.a.c.b.d)) {
                return (org.a.c.b.d) aVar.a(i);
            }
        } else if (a3 != null && !(a2 instanceof org.a.c.b.a) && !(a3 instanceof org.a.c.b.a)) {
            new StringBuilder("Expected DecodeParams to be an Array or Dictionary but found ").append(a3.getClass().getName());
        }
        return new org.a.c.b.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ImageReader a(String str, String str2) {
        Iterator imageReadersByFormatName = ImageIO.getImageReadersByFormatName(str);
        ImageReader imageReader = null;
        while (imageReadersByFormatName.hasNext()) {
            ImageReader imageReader2 = (ImageReader) imageReadersByFormatName.next();
            imageReader = imageReader2;
            if (imageReader2 != null && imageReader.canReadRaster()) {
                break;
            }
        }
        if (imageReader == null) {
            throw new s("Cannot read " + str + " image: " + str2);
        }
        return imageReader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v7, types: [int] */
    public static int a() {
        NumberFormatException numberFormatException = -1;
        int i = -1;
        try {
            numberFormatException = Integer.parseInt(System.getProperty("org.apache.pdfbox.filter.deflatelevel", "-1"));
            i = numberFormatException;
        } catch (NumberFormatException e) {
            numberFormatException.getMessage();
        }
        return Math.max(-1, Math.min(9, i));
    }
}
