package org.a.c.h.f.a;

import com.a.a.a.am;
import java.awt.Color;
import java.awt.color.CMMException;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.color.ProfileDataException;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.WritableRaster;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/o.class */
public final class o extends a {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4583a = org.a.a.a.c.a(o.class);

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.h.a.i f4584b;
    private int c;
    private f d;
    private ICC_ColorSpace f;
    private e g;
    private boolean h;
    private boolean i;
    private static final boolean j;

    static {
        j = !h() || "sun.java2d.cmm.kcms.KcmsServiceProvider".equals(System.getProperty("sun.java2d.cmm"));
    }

    public o(org.a.c.h.b bVar) {
        this.c = -1;
        this.h = false;
        this.i = false;
        this.e = new org.a.c.b.a();
        this.e.a((org.a.c.b.b) org.a.c.b.j.bz);
        this.f4584b = new org.a.c.h.a.i(bVar);
        this.e.a(this.f4584b);
    }

    @Deprecated
    private o(org.a.c.b.a aVar) {
        this.c = -1;
        this.h = false;
        this.i = false;
        a(aVar);
        this.i = System.getProperty("org.apache.pdfbox.rendering.UseAlternateInsteadOfICCColorSpace") != null;
        this.e = aVar;
        this.f4584b = new org.a.c.h.a.i((org.a.c.b.p) aVar.a(1));
        e();
    }

    public static o a(org.a.c.b.a aVar, org.a.c.h.j jVar) {
        f b2;
        a(aVar);
        org.a.c.b.b b3 = aVar.b(1);
        org.a.c.b.m mVar = null;
        if (b3 instanceof org.a.c.b.m) {
            mVar = (org.a.c.b.m) b3;
        }
        if (mVar != null && jVar != null && jVar.b() != null && (b2 = jVar.b().b(mVar)) != null && (b2 instanceof o)) {
            return (o) b2;
        }
        o oVar = new o(aVar);
        if (mVar != null && jVar != null && jVar.b() != null) {
            jVar.b().a(mVar, oVar);
        }
        return oVar;
    }

    private static void a(org.a.c.b.a aVar) {
        if (aVar.b() < 2) {
            throw new IOException("ICCBased colorspace array must have two elements");
        }
        if (!(aVar.a(1) instanceof org.a.c.b.p)) {
            throw new IOException("ICCBased colorspace array must have a stream as second element");
        }
    }

    @Override // org.a.c.h.f.a.f
    public final String a() {
        return org.a.c.b.j.bz.a();
    }

    public final org.a.c.h.a.i d() {
        return this.f4584b;
    }

    private void e() {
        if (this.i) {
            try {
                a((Exception) null);
                return;
            } catch (IOException e) {
                new StringBuilder("Error initializing alternate color space: ").append(e.getLocalizedMessage());
            }
        }
        org.a.c.b.g gVar = null;
        try {
            try {
                try {
                    gVar = this.f4584b.c();
                    synchronized (f4583a) {
                        ICC_Profile iCC_Profile = ICC_Profile.getInstance(gVar);
                        if (a(iCC_Profile)) {
                            this.h = true;
                            this.f = ColorSpace.getInstance(1000);
                            this.f.getProfile();
                        } else {
                            this.f = new ICC_ColorSpace(b(iCC_Profile));
                        }
                        float[] fArr = new float[b()];
                        for (int i = 0; i < b(); i++) {
                            fArr[i] = Math.max(0.0f, a(i).a());
                        }
                        this.g = new e(fArr, this);
                        if (j) {
                            new Color(this.f, new float[b()], 1.0f);
                        } else {
                            new ComponentColorModel(this.f, false, false, 1, 0);
                        }
                    }
                    am.a((Closeable) gVar);
                } catch (ProfileDataException e2) {
                    a((Exception) e2);
                    am.a((Closeable) gVar);
                } catch (IOException e3) {
                    a(e3);
                    am.a((Closeable) gVar);
                } catch (ArrayIndexOutOfBoundsException e4) {
                    a(e4);
                    am.a((Closeable) gVar);
                }
            } catch (IllegalArgumentException e5) {
                a(e5);
                am.a((Closeable) gVar);
            } catch (CMMException e6) {
                a((Exception) e6);
                am.a((Closeable) gVar);
            }
        } catch (Throwable th) {
            am.a((Closeable) gVar);
            throw th;
        }
    }

    private void a(Exception exc) {
        this.f = null;
        this.d = g();
        if (this.d.equals(m.f4580a)) {
            this.h = true;
        }
        if (exc != null) {
            new StringBuilder("Can't read embedded ICC profile (").append(exc.getLocalizedMessage()).append("), using alternate color space: ").append(this.d.a());
        }
        this.g = this.d.c();
    }

    private static boolean a(ICC_Profile iCC_Profile) {
        return new String(Arrays.copyOfRange(iCC_Profile.getData(1751474532), 52, 59), org.a.c.i.a.f4651a).trim().equals("sRGB");
    }

    private static ICC_Profile b(ICC_Profile iCC_Profile) {
        if (iCC_Profile.getProfileClass() != 1) {
            byte[] data = iCC_Profile.getData();
            if (data[64] == 0) {
                a(1835955314, data, 12);
                return ICC_Profile.getInstance(data);
            }
        }
        return iCC_Profile;
    }

    private static void a(int i, byte[] bArr, int i2) {
        bArr[12] = 109;
        bArr[13] = 110;
        bArr[14] = 116;
        bArr[15] = 114;
    }

    @Override // org.a.c.h.f.a.f
    public final float[] a(float[] fArr) {
        if (this.h) {
            return fArr;
        }
        if (this.f != null) {
            return this.f.toRGB(a(this.f, fArr));
        }
        return this.d.a(fArr);
    }

    private static float[] a(ICC_ColorSpace iCC_ColorSpace, float[] fArr) {
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            float minValue = iCC_ColorSpace.getMinValue(i);
            float maxValue = iCC_ColorSpace.getMaxValue(i);
            fArr2[i] = fArr[i] < minValue ? minValue : fArr[i] > maxValue ? maxValue : fArr[i];
        }
        return fArr2;
    }

    @Override // org.a.c.h.f.a.a, org.a.c.h.f.a.f
    public final BufferedImage a(WritableRaster writableRaster) {
        if (this.f != null) {
            return a(writableRaster, (ColorSpace) this.f);
        }
        return this.d.a(writableRaster);
    }

    @Override // org.a.c.h.f.a.f
    public final int b() {
        if (this.c < 0) {
            this.c = this.f4584b.f().j(org.a.c.b.j.co);
        }
        return this.c;
    }

    @Override // org.a.c.h.f.a.f
    public final e c() {
        return this.g;
    }

    private f g() {
        org.a.c.b.a aVar;
        org.a.c.b.j jVar;
        org.a.c.b.b a2 = this.f4584b.f().a(org.a.c.b.j.g);
        if (a2 == null) {
            aVar = new org.a.c.b.a();
            int b2 = b();
            switch (b2) {
                case 1:
                    jVar = org.a.c.b.j.aB;
                    break;
                case 2:
                default:
                    throw new IOException("Unknown color space number of components:" + b2);
                case 3:
                    jVar = org.a.c.b.j.aD;
                    break;
                case 4:
                    jVar = org.a.c.b.j.aA;
                    break;
            }
            aVar.a((org.a.c.b.b) jVar);
        } else if (a2 instanceof org.a.c.b.a) {
            aVar = (org.a.c.b.a) a2;
        } else if (a2 instanceof org.a.c.b.j) {
            org.a.c.b.a aVar2 = new org.a.c.b.a();
            aVar = aVar2;
            aVar2.a(a2);
        } else {
            throw new IOException("Error: expected COSArray or COSName and not " + a2.getClass().getName());
        }
        return f.a(aVar);
    }

    private org.a.c.h.a.g a(int i) {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4584b.f().a(org.a.c.b.j.db);
        if (aVar != null && aVar.b() >= (b() << 1)) {
            return new org.a.c.h.a.g(aVar, i);
        }
        return new org.a.c.h.a.g();
    }

    @Override // org.a.c.h.f.a.a
    public final String toString() {
        return a() + "{numberOfComponents: " + b() + "}";
    }

    private static boolean h() {
        StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.specification.version"), ".");
        try {
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            int i = 0;
            if (stringTokenizer.hasMoreTokens()) {
                i = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (parseInt <= 1) {
                return parseInt == 1 && i >= 8;
            }
            return true;
        } catch (NumberFormatException unused) {
            return true;
        }
    }
}
