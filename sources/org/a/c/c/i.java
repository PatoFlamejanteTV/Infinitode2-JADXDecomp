package org.a.c.c;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* loaded from: infinitode-2.jar:org/a/c/c/i.class */
final class i extends l {
    static {
        org.a.a.a.c.a(i.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x010b A[Catch: all -> 0x0142, TryCatch #0 {all -> 0x0142, blocks: (B:3:0x000c, B:5:0x001d, B:6:0x0025, B:8:0x0068, B:10:0x0083, B:11:0x008c, B:24:0x0095, B:27:0x00c1, B:29:0x00cc, B:16:0x011b, B:31:0x00eb, B:32:0x00f6, B:33:0x0101, B:34:0x010a, B:13:0x010b, B:15:0x0114, B:38:0x00a3, B:37:0x00b1, B:40:0x0070), top: B:2:0x000c, inners: #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.a.c.c.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.a.c.c.k a(java.io.InputStream r7, java.io.OutputStream r8, org.a.c.b.d r9, int r10, org.a.c.c.j r11) {
        /*
            Method dump skipped, instructions count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.c.i.a(java.io.InputStream, java.io.OutputStream, org.a.c.b.d, int, org.a.c.c.j):org.a.c.c.k");
    }

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        return a(inputStream, outputStream, dVar, i, j.f4400a);
    }

    private static Integer a(IIOMetadata iIOMetadata) {
        NodeList elementsByTagName = ((Element) ((Element) iIOMetadata.getAsTree("javax_imageio_jpeg_image_1.0")).getElementsByTagName("markerSequence").item(0)).getElementsByTagName("app14Adobe");
        if (elementsByTagName != null && elementsByTagName.getLength() > 0) {
            return Integer.valueOf(Integer.parseInt(((Element) elementsByTagName.item(0)).getAttribute("transform")));
        }
        return 0;
    }

    private static int a(ImageInputStream imageInputStream) {
        int i = 0;
        imageInputStream.seek(0L);
        while (true) {
            int read = imageInputStream.read();
            if (read != -1) {
                if ("Adobe".charAt(i) == read) {
                    i++;
                    if (i == 5) {
                        i = 0;
                        long streamPosition = imageInputStream.getStreamPosition();
                        imageInputStream.seek(imageInputStream.getStreamPosition() - 9);
                        if (imageInputStream.readUnsignedShort() != 65518) {
                            imageInputStream.seek(streamPosition);
                        } else {
                            int readUnsignedShort = imageInputStream.readUnsignedShort();
                            if (readUnsignedShort >= 12) {
                                byte[] bArr = new byte[Math.max(readUnsignedShort, 12)];
                                if (imageInputStream.read(bArr) >= 12) {
                                    return bArr[11];
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        continue;
                    }
                } else {
                    i = 0;
                }
            } else {
                return 0;
            }
        }
    }

    private WritableRaster a(Raster raster) {
        WritableRaster createCompatibleWritableRaster = raster.createCompatibleWritableRaster();
        int[] iArr = new int[4];
        int height = raster.getHeight();
        for (int i = 0; i < height; i++) {
            int width = raster.getWidth();
            for (int i2 = 0; i2 < width; i2++) {
                raster.getPixel(i2, i, iArr);
                float f = iArr[0];
                float f2 = iArr[1];
                float f3 = iArr[2];
                float f4 = iArr[3];
                int a2 = a((f + (1.402f * f3)) - 179.456f);
                int a3 = a(((f - (0.34414f * f2)) - (0.71414f * f3)) + 135.45984f);
                int a4 = a((f + (1.772f * f2)) - 226.816f);
                iArr[0] = 255 - a2;
                iArr[1] = 255 - a3;
                iArr[2] = 255 - a4;
                iArr[3] = (int) f4;
                createCompatibleWritableRaster.setPixel(i2, i, iArr);
            }
        }
        return createCompatibleWritableRaster;
    }

    private WritableRaster b(Raster raster) {
        WritableRaster createCompatibleWritableRaster = raster.createCompatibleWritableRaster();
        int[] iArr = new int[4];
        int height = raster.getHeight();
        for (int i = 0; i < height; i++) {
            int width = raster.getWidth();
            for (int i2 = 0; i2 < width; i2++) {
                raster.getPixel(i2, i, iArr);
                float f = iArr[0];
                float f2 = iArr[1];
                float f3 = iArr[2];
                float f4 = iArr[3];
                int a2 = a((1.164f * (f - 16.0f)) + (1.596f * (f3 - 128.0f)));
                int a3 = a((1.164f * (f - 16.0f)) + ((-0.392f) * (f2 - 128.0f)) + ((-0.813f) * (f3 - 128.0f)));
                int a4 = a((1.164f * (f - 16.0f)) + (2.017f * (f2 - 128.0f)));
                iArr[0] = 255 - a2;
                iArr[1] = 255 - a3;
                iArr[2] = 255 - a4;
                iArr[3] = (int) f4;
                createCompatibleWritableRaster.setPixel(i2, i, iArr);
            }
        }
        return createCompatibleWritableRaster;
    }

    private static WritableRaster c(Raster raster) {
        WritableRaster createCompatibleWritableRaster = raster.createCompatibleWritableRaster();
        int width = raster.getWidth();
        int height = raster.getHeight();
        int i = width * 3;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < height; i2++) {
            raster.getPixels(0, i2, width, 1, iArr);
            for (int i3 = 0; i3 < i; i3 += 3) {
                int i4 = iArr[i3];
                iArr[i3] = iArr[i3 + 2];
                iArr[i3 + 2] = i4;
            }
            createCompatibleWritableRaster.setPixels(0, i2, width, 1, iArr);
        }
        return createCompatibleWritableRaster;
    }

    private static String a(ImageReader imageReader) {
        Element element;
        try {
            IIOMetadata imageMetadata = imageReader.getImageMetadata(0);
            if (imageMetadata == null || (element = (Element) imageMetadata.getAsTree("javax_imageio_1.0").getElementsByTagName("NumChannels").item(0)) == null) {
                return "";
            }
            return element.getAttribute("value");
        } catch (IOException unused) {
            return "";
        } catch (NegativeArraySizeException unused2) {
            return "";
        }
    }

    private static int a(float f) {
        return (int) (f < 0.0f ? 0.0f : f > 255.0f ? 255.0f : f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        throw new UnsupportedOperationException("DCTFilter encoding not implemented, use the JPEGFactory methods instead");
    }
}
