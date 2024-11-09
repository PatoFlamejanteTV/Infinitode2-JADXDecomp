package org.a.c.h.f.c;

import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import org.a.c.b.d;
import org.a.c.b.j;
import org.a.c.c.l;
import org.a.c.h.f.a.f;
import org.a.c.h.f.a.g;
import org.a.c.h.f.a.i;
import org.a.c.h.f.a.m;
import org.a.c.h.f.a.o;

/* loaded from: infinitode-2.jar:org/a/c/h/f/c/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f4594a = true;

    public static b a(org.a.c.h.b bVar, BufferedImage bufferedImage) {
        b a2;
        if ((bufferedImage.getType() == 10 && bufferedImage.getColorModel().getPixelSize() <= 8) || (bufferedImage.getType() == 12 && bufferedImage.getColorModel().getPixelSize() == 1)) {
            return a(bufferedImage, bVar);
        }
        if (f4594a && (a2 = new C0048a(bVar, bufferedImage).a()) != null) {
            if (a2.e() == m.f4580a && a2.d() < 16 && bufferedImage.getWidth() * bufferedImage.getHeight() <= 2500) {
                b b2 = b(bufferedImage, bVar);
                if (b2.f().n() < a2.f().n()) {
                    a2.f().close();
                    return b2;
                }
                b2.f().close();
            }
            return a2;
        }
        return b(bufferedImage, bVar);
    }

    private static b a(BufferedImage bufferedImage, org.a.c.h.b bVar) {
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        int[] iArr = new int[width];
        int pixelSize = bufferedImage.getColorModel().getPixelSize();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((((width * pixelSize) / 8) + ((width * pixelSize) % 8 != 0 ? 1 : 0)) * height);
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
        for (int i = 0; i < height; i++) {
            int length = bufferedImage.getRGB(0, i, width, 1, iArr, 0, width).length;
            for (int i2 = 0; i2 < length; i2++) {
                memoryCacheImageOutputStream.writeBits(r0[i2] & 255, pixelSize);
            }
            int bitOffset = memoryCacheImageOutputStream.getBitOffset();
            if (bitOffset != 0) {
                memoryCacheImageOutputStream.writeBits(0L, 8 - bitOffset);
            }
        }
        memoryCacheImageOutputStream.flush();
        memoryCacheImageOutputStream.close();
        return b(bVar, byteArrayOutputStream.toByteArray(), bufferedImage.getWidth(), bufferedImage.getHeight(), pixelSize, i.f4574a);
    }

    private static b b(BufferedImage bufferedImage, org.a.c.h.b bVar) {
        byte[] bArr;
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        int[] iArr = new int[width];
        m mVar = m.f4580a;
        byte[] bArr2 = new byte[width * height * 3];
        int i = 0;
        int i2 = 0;
        int i3 = 7;
        int transparency = bufferedImage.getTransparency();
        int i4 = transparency == 2 ? 1 : 8;
        if (transparency != 1) {
            bArr = new byte[(((width * i4) / 8) + ((width * i4) % 8 != 0 ? 1 : 0)) * height];
        } else {
            bArr = new byte[0];
        }
        for (int i5 = 0; i5 < height; i5++) {
            for (int i6 : bufferedImage.getRGB(0, i5, width, 1, iArr, 0, width)) {
                int i7 = i;
                int i8 = i + 1;
                bArr2[i7] = (byte) (i6 >> 16);
                int i9 = i8 + 1;
                bArr2[i8] = (byte) (i6 >> 8);
                i = i9 + 1;
                bArr2[i9] = (byte) i6;
                if (transparency != 1) {
                    if (transparency == 2) {
                        byte[] bArr3 = bArr;
                        int i10 = i2;
                        bArr3[i10] = (byte) (bArr3[i10] | (((i6 >> 24) & 1) << i3));
                        i3--;
                        if (i3 < 0) {
                            i3 = 7;
                            i2++;
                        }
                    } else {
                        int i11 = i2;
                        i2++;
                        bArr[i11] = (byte) (i6 >>> 24);
                    }
                }
            }
            if (transparency == 2 && i3 != 7) {
                i3 = 7;
                i2++;
            }
        }
        b b2 = b(bVar, bArr2, bufferedImage.getWidth(), bufferedImage.getHeight(), 8, mVar);
        if (transparency != 1) {
            b2.f().a(j.ds, b(bVar, bArr, bufferedImage.getWidth(), bufferedImage.getHeight(), i4, i.f4574a));
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static b b(org.a.c.h.b bVar, byte[] bArr, int i, int i2, int i3, f fVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length / 2);
        org.a.c.c.m.f4405a.a(j.bc).b(new ByteArrayInputStream(bArr), byteArrayOutputStream, new d());
        return new b(bVar, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), j.bc, i, i2, i3, fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.a.c.h.f.c.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/c/h/f/c/a$a.class */
    public static class C0048a {

        /* renamed from: a, reason: collision with root package name */
        private final org.a.c.h.b f4595a;

        /* renamed from: b, reason: collision with root package name */
        private final BufferedImage f4596b;
        private final int c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;
        private final int h;
        private final byte[] i;
        private final byte[] j;
        private final byte[] k;
        private final byte[] l;
        private final byte[] m;
        private int n;
        private boolean o;
        private byte[] p;
        private byte[] q;
        private byte[] r;
        private byte[] s;
        private byte[] t;

        C0048a(org.a.c.h.b bVar, BufferedImage bufferedImage) {
            this.f4595a = bVar;
            this.f4596b = bufferedImage;
            this.c = bufferedImage.getColorModel().getNumComponents();
            this.d = bufferedImage.getRaster().getTransferType();
            this.e = (this.d == 2 || this.d == 1) ? 2 : 1;
            this.f = bufferedImage.getColorModel().getNumColorComponents() * this.e;
            this.g = bufferedImage.getHeight();
            this.h = bufferedImage.getWidth();
            this.n = bufferedImage.getType();
            this.o = bufferedImage.getColorModel().getNumComponents() != bufferedImage.getColorModel().getNumColorComponents();
            this.p = this.o ? new byte[this.h * this.g * this.e] : null;
            int i = (this.h * this.f) + 1;
            this.i = new byte[i];
            this.j = new byte[i];
            this.k = new byte[i];
            this.l = new byte[i];
            this.m = new byte[i];
            this.i[0] = 0;
            this.j[0] = 1;
            this.k[0] = 2;
            this.l[0] = 3;
            this.m[0] = 4;
            this.q = new byte[this.f];
            this.r = new byte[this.f];
            this.s = new byte[this.f];
            this.t = new byte[this.f];
        }

        final b a() {
            int i;
            int[] iArr;
            int[] iArr2;
            short[] sArr;
            short[] sArr2;
            int[] iArr3;
            int[] iArr4;
            byte[] bArr;
            byte[] bArr2;
            WritableRaster raster = this.f4596b.getRaster();
            switch (this.n) {
                case 0:
                    switch (raster.getTransferType()) {
                        case 0:
                            i = this.c;
                            iArr = new byte[this.h * i];
                            iArr2 = new byte[this.h * i];
                            break;
                        case 1:
                            i = this.c;
                            iArr = new short[this.h * i];
                            iArr2 = new short[this.h * i];
                            break;
                        default:
                            return null;
                    }
                case 1:
                case 2:
                case 4:
                    i = 1;
                    iArr = new int[this.h];
                    iArr2 = new int[this.h];
                    break;
                case 3:
                default:
                    return null;
                case 5:
                case 6:
                    i = this.c;
                    iArr = new byte[this.h * i];
                    iArr2 = new byte[this.h * i];
                    break;
            }
            int i2 = this.h * i;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((this.g * this.h) * this.f) / 2);
            Deflater deflater = new Deflater(l.a());
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            int i3 = 0;
            for (int i4 = 0; i4 < this.g; i4++) {
                raster.getDataElements(0, i4, this.h, 1, iArr2);
                int i5 = 1;
                Arrays.fill(this.q, (byte) 0);
                Arrays.fill(this.r, (byte) 0);
                if (iArr2 instanceof byte[]) {
                    bArr2 = (byte[]) iArr2;
                    bArr = (byte[]) iArr;
                    iArr3 = null;
                    iArr4 = null;
                    sArr2 = null;
                    sArr = null;
                } else if (iArr2 instanceof int[]) {
                    iArr4 = iArr2;
                    iArr3 = iArr;
                    sArr2 = null;
                    sArr = null;
                    bArr = null;
                    bArr2 = null;
                } else {
                    sArr = (short[]) iArr2;
                    sArr2 = (short[]) iArr;
                    iArr3 = null;
                    iArr4 = null;
                    bArr = null;
                    bArr2 = null;
                }
                int i6 = 0;
                while (i6 < i2) {
                    if (bArr2 != null) {
                        a(bArr2, i6, this.t, this.p, i3);
                        a(bArr, i6, this.s, (byte[]) null, 0);
                    } else if (iArr4 != null) {
                        a(iArr4, i6, this.t, this.p, i3);
                        a(iArr3, i6, this.s, (byte[]) null, 0);
                    } else {
                        a(sArr, i6, this.t, this.p, i3);
                        a(sArr2, i6, this.s, (byte[]) null, 0);
                    }
                    int length = this.t.length;
                    for (int i7 = 0; i7 < length; i7++) {
                        int i8 = this.t[i7] & 255;
                        int i9 = this.q[i7] & 255;
                        int i10 = this.s[i7] & 255;
                        int i11 = this.r[i7] & 255;
                        this.i[i5] = (byte) i8;
                        this.j[i5] = a(i8, i9);
                        this.k[i5] = b(i8, i10);
                        this.l[i5] = a(i8, i9, i10);
                        this.m[i5] = a(i8, i9, i10, i11);
                        i5++;
                    }
                    System.arraycopy(this.t, 0, this.q, 0, this.f);
                    System.arraycopy(this.s, 0, this.r, 0, this.f);
                    i6 += i;
                    i3 += this.e;
                }
                byte[] b2 = b();
                deflaterOutputStream.write(b2, 0, b2.length);
                int[] iArr5 = iArr;
                iArr = iArr2;
                iArr2 = iArr5;
            }
            deflaterOutputStream.close();
            deflater.end();
            return a(byteArrayOutputStream, this.e << 3);
        }

        private void a(int[] iArr, int i, byte[] bArr, byte[] bArr2, int i2) {
            int i3 = iArr[i];
            byte b2 = (byte) i3;
            byte b3 = (byte) (i3 >> 8);
            byte b4 = (byte) (i3 >> 16);
            switch (this.n) {
                case 1:
                    bArr[0] = b4;
                    bArr[1] = b3;
                    bArr[2] = b2;
                    return;
                case 2:
                    bArr[0] = b4;
                    bArr[1] = b3;
                    bArr[2] = b2;
                    if (bArr2 != null) {
                        bArr2[i2] = (byte) (i3 >>> 24);
                        return;
                    }
                    return;
                case 3:
                default:
                    return;
                case 4:
                    bArr[0] = b2;
                    bArr[1] = b3;
                    bArr[2] = b4;
                    return;
            }
        }

        private static void a(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2) {
            System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
            if (bArr3 != null) {
                bArr3[i2] = bArr[i + bArr2.length];
            }
        }

        private static void a(short[] sArr, int i, byte[] bArr, byte[] bArr2, int i2) {
            int i3 = i;
            for (int i4 = 0; i4 < bArr.length; i4 += 2) {
                int i5 = i3;
                i3++;
                short s = sArr[i5];
                bArr[i4] = (byte) (s >> 8);
                bArr[i4 + 1] = (byte) s;
            }
            if (bArr2 != null) {
                short s2 = sArr[i3];
                bArr2[i2] = (byte) (s2 >> 8);
                bArr2[i2 + 1] = (byte) s2;
            }
        }

        private b a(ByteArrayOutputStream byteArrayOutputStream, int i) {
            ICC_Profile profile;
            int height = this.f4596b.getHeight();
            int width = this.f4596b.getWidth();
            ICC_ColorSpace colorSpace = this.f4596b.getColorModel().getColorSpace();
            f fVar = colorSpace.getType() != 9 ? m.f4580a : g.f4572a;
            if ((colorSpace instanceof ICC_ColorSpace) && (profile = colorSpace.getProfile()) != ICC_Profile.getInstance(1000)) {
                o oVar = new o(this.f4595a);
                OutputStream a2 = oVar.d().a(j.bc);
                a2.write(profile.getData());
                a2.close();
                oVar.d().f().a(j.co, colorSpace.getNumComponents());
                oVar.d().f().a(j.g, (org.a.c.b.b) (colorSpace.getType() == 9 ? j.aA : j.aD));
                fVar = oVar;
            }
            b bVar = new b(this.f4595a, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), j.bc, width, height, i, fVar);
            d dVar = new d();
            dVar.a(j.z, (org.a.c.b.b) org.a.c.b.i.a(i));
            dVar.a(j.cT, (org.a.c.b.b) org.a.c.b.i.a(15L));
            dVar.a(j.ad, (org.a.c.b.b) org.a.c.b.i.a(width));
            dVar.a(j.ab, (org.a.c.b.b) org.a.c.b.i.a(colorSpace.getNumComponents()));
            bVar.f().a(j.ar, (org.a.c.b.b) dVar);
            if (this.f4596b.getTransparency() != 1) {
                bVar.f().a(j.ds, a.b(this.f4595a, this.p, this.f4596b.getWidth(), this.f4596b.getHeight(), 8 * this.e, i.f4574a));
            }
            return bVar;
        }

        private byte[] b() {
            byte[] bArr = this.i;
            long a2 = a(this.i);
            long a3 = a(this.j);
            long a4 = a(this.k);
            long a5 = a(this.l);
            long a6 = a(this.m);
            if (a2 > a3) {
                bArr = this.j;
                a2 = a3;
            }
            if (a2 > a4) {
                bArr = this.k;
                a2 = a4;
            }
            if (a2 > a5) {
                bArr = this.l;
                a2 = a5;
            }
            if (a2 > a6) {
                bArr = this.m;
            }
            return bArr;
        }

        private static byte a(int i, int i2) {
            return (byte) ((i & 255) - (i2 & 255));
        }

        private static byte b(int i, int i2) {
            return a(i, i2);
        }

        private static byte a(int i, int i2, int i3) {
            return (byte) (i - ((i3 + i2) / 2));
        }

        private static byte a(int i, int i2, int i3, int i4) {
            int i5;
            int i6 = (i2 + i3) - i4;
            int abs = Math.abs(i6 - i2);
            int abs2 = Math.abs(i6 - i3);
            int abs3 = Math.abs(i6 - i4);
            if (abs <= abs2 && abs <= abs3) {
                i5 = i2;
            } else if (abs2 <= abs3) {
                i5 = i3;
            } else {
                i5 = i4;
            }
            return (byte) (i - i5);
        }

        private static long a(byte[] bArr) {
            long j = 0;
            for (byte b2 : bArr) {
                j += Math.abs((int) b2);
            }
            return j;
        }
    }
}
