package com.d.h;

import java.awt.geom.AffineTransform;
import java.io.IOException;

/* loaded from: infinitode-2.jar:com/d/h/w.class */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.g f1276a;

    /* renamed from: b, reason: collision with root package name */
    private int f1277b = 0;

    /* loaded from: infinitode-2.jar:com/d/h/w$a.class */
    public static class a extends RuntimeException {
        public a(String str, Exception exc) {
            super(str, exc);
        }

        public a(com.a.a.c.l lVar) {
            super(lVar);
        }

        public a(String str) {
            super(str);
            a(str);
        }

        public a(String str, Throwable th) {
            super(str, th);
            a(str, th);
        }

        private static void a(String str) {
            com.d.m.l.c("Unhandled exception. " + str);
        }

        private static void a(String str, Throwable th) {
            com.d.m.l.a("Unhandled exception. " + str, th);
        }
    }

    private static void a(String str, IOException iOException) {
        com.d.m.l.a("Exception in PDF writing method: " + str, iOException);
        throw new a(str, (Exception) iOException);
    }

    public w(org.a.c.h.g gVar) {
        this.f1276a = gVar;
    }

    public final void a(float f, float f2, float f3, float f4) {
        try {
            this.f1276a.c(0.0f, 0.0f, 0.0f, 0.0f);
        } catch (IOException e) {
            a("addRect", e);
        }
    }

    public final void a() {
        try {
            this.f1276a.i();
        } catch (IOException e) {
            a("closeSubpath", e);
        }
    }

    public final void a(float f, float f2, float f3, float f4, float f5, float f6) {
        try {
            this.f1276a.a(f, f2, f3, f4, f5, f6);
        } catch (IOException e) {
            a("curveTo(6)", e);
        }
    }

    public final void b(float f, float f2, float f3, float f4) {
        try {
            this.f1276a.d(f, f2, f3, f4);
        } catch (IOException e) {
            a("curveTo(4)", e);
        }
    }

    public final void b() {
        try {
            this.f1276a.close();
        } catch (IOException e) {
            a("closeContent", e);
        }
    }

    public final void a(float f, float f2) {
        try {
            this.f1276a.c(f, f2);
        } catch (IOException e) {
            a("lineTo", e);
        }
    }

    public final void b(float f, float f2) {
        try {
            this.f1276a.b(f, f2);
        } catch (IOException e) {
            a("moveTo", e);
        }
    }

    public final void c() {
        try {
            this.f1276a.h();
        } catch (IOException e) {
            a("fillEvenOdd", e);
        }
    }

    public final void d() {
        try {
            this.f1276a.g();
        } catch (IOException e) {
            a("fillNonZero", e);
        }
    }

    public final void e() {
        try {
            this.f1276a.e();
        } catch (IOException e) {
            a("stroke", e);
        }
    }

    public final void f() {
        try {
            this.f1276a.j();
        } catch (IOException e) {
            a("clipNonZero", e);
        }
    }

    public final void g() {
        try {
            this.f1276a.k();
        } catch (IOException e) {
            a("clipEvenOdd", e);
        }
    }

    public final void a(int i, int i2, int i3) {
        try {
            this.f1276a.a(i, i2, i3);
        } catch (IOException e) {
            a("setStrokingColor", e);
        }
    }

    public final void c(float f, float f2, float f3, float f4) {
        try {
            this.f1276a.a(f, f2, f3, f4);
        } catch (IOException e) {
            a("setStrokingColor(CMYK)", e);
        }
    }

    public final void b(int i, int i2, int i3) {
        try {
            this.f1276a.b(i, i2, i3);
        } catch (IOException e) {
            a("setFillColor", e);
        }
    }

    public final void d(float f, float f2, float f3, float f4) {
        try {
            this.f1276a.b(f, f2, f3, f4);
        } catch (IOException e) {
            a("setFillColor(CMYK)", e);
        }
    }

    public final void a(float f) {
        try {
            this.f1276a.a(f);
        } catch (IOException e) {
            a("setLineWidth", e);
        }
    }

    public final void a(int i) {
        try {
            this.f1276a.c(i);
        } catch (IOException e) {
            a("setLineCap", e);
        }
    }

    public final void b(int i) {
        try {
            this.f1276a.b(i);
        } catch (IOException e) {
            a("setLineJoin", e);
        }
    }

    public final void a(float[] fArr, float f) {
        try {
            this.f1276a.a(fArr, f);
        } catch (IOException e) {
            a("setLineDash", e);
        }
    }

    public final void h() {
        try {
            this.f1277b--;
            this.f1276a.d();
            if (this.f1277b < 0) {
                throw new IllegalStateException("Invalid save/restore pairing!");
            }
        } catch (IOException e) {
            a("restoreGraphics", e);
        }
    }

    public final void i() {
        try {
            this.f1277b++;
            this.f1276a.c();
        } catch (IOException e) {
            a("saveGraphics", e);
        }
    }

    public final void j() {
        try {
            this.f1276a.a();
        } catch (IOException e) {
            a("beginText", e);
        }
    }

    public final void k() {
        try {
            this.f1276a.b();
        } catch (IOException e) {
            a("endText", e);
        }
    }

    public final void a(org.a.c.h.e.u uVar, float f) {
        try {
            this.f1276a.a(uVar, f);
        } catch (IOException e) {
            a("setFont", e);
        }
    }

    public final void b(float f, float f2, float f3, float f4, float f5, float f6) {
        try {
            this.f1276a.a(new org.a.c.i.d(f, f2, f3, f4, f5, f6));
        } catch (IOException e) {
            a("setTextMatrix", e);
        }
    }

    public final void a(org.a.c.h.f.e.a aVar) {
        try {
            this.f1276a.a(aVar);
        } catch (IOException e) {
            a("setRenderingMode", e);
        }
    }

    public final void a(String str) {
        try {
            this.f1276a.a(str);
        } catch (IOException e) {
            a("drawString", e);
        }
    }

    public final void a(org.a.c.h.f.c.b bVar, float f, float f2, float f3, float f4) {
        try {
            this.f1276a.a(bVar, f, f2, f3, f4);
        } catch (IOException e) {
            a("drawImage", e);
        }
    }

    public final void b(float f) {
        try {
            if (f > 0.0d) {
                this.f1276a.b(f);
            }
        } catch (IOException e) {
            a("setMiterLimit", e);
        }
    }

    public final void a(Object[] objArr) {
        try {
            this.f1276a.a(objArr);
        } catch (IOException e) {
            a("drawStringWithPositioning", e);
        }
    }

    public final void a(AffineTransform affineTransform) {
        try {
            this.f1276a.b(new org.a.c.i.d(affineTransform));
        } catch (IOException e) {
            a("applyPdfMatrix", e);
        }
    }
}
