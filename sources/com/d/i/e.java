package com.d.i;

import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/e.class */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private String f1346a;

    /* renamed from: b, reason: collision with root package name */
    private List<com.d.c.d.j> f1347b;

    public static Path2D a(Rectangle rectangle, com.d.c.f.a.a aVar, boolean z) {
        Path2D a2 = a(rectangle, 1, aVar, false, 1.0f, 1.0f);
        a2.append(a(rectangle, 8, aVar, false, 1.0f, 1.0f), true);
        a2.append(a(rectangle, 4, aVar, false, 1.0f, 1.0f), true);
        a2.append(a(rectangle, 2, aVar, false, 1.0f, 1.0f), true);
        return a2;
    }

    private static Path2D a(Rectangle rectangle, int i, com.d.c.f.a.a aVar, boolean z, float f, float f2) {
        float w;
        com.d.c.f.a.a a2 = aVar.a(new Rectangle(rectangle.width, rectangle.height));
        a aVar2 = new a(a2, i, f2);
        if (aVar2.g()) {
            w = rectangle.height - (((f + 1.0f) * f2) * (a2.t() + a2.v()));
        } else {
            w = rectangle.width - (((f + 1.0f) * f2) * (a2.w() + a2.u()));
        }
        Path2D.Float r0 = new Path2D.Float();
        float f3 = 90.0f;
        float c = aVar2.c() + aVar2.d();
        if (c != 0.0f) {
            f3 = (90.0f * aVar2.c()) / c;
        }
        a(r0, 0.0f - aVar2.d(), 0.0f - aVar2.c(), aVar2.b().b(), aVar2.b().c(), f3 + 90.0f, (-f3) - 1.0f, aVar2.c(), aVar2.d(), f, true);
        float f4 = 90.0f;
        float c2 = aVar2.c() + aVar2.e();
        if (c2 != 0.0f) {
            f4 = (90.0f * aVar2.c()) / c2;
        }
        a(r0, w + aVar2.e(), 0.0f - aVar2.c(), aVar2.a().c(), aVar2.a().b(), 90.0f, (-f4) - 1.0f, aVar2.c(), aVar2.e(), f, false);
        if (z) {
            a(r0, w, 0.0f, aVar2.a().c(), aVar2.a().b(), 90.0f - f4, f4 + 1.0f, aVar2.c(), aVar2.e(), f + 1.0f, false);
            float f5 = 90.0f;
            float c3 = aVar2.c() + aVar2.d();
            if (c3 != 0.0f) {
                f5 = (90.0f * aVar2.c()) / c3;
            }
            a(r0, 0.0f, 0.0f, aVar2.b().b(), aVar2.b().c(), 90.0f, f5 + 1.0f, aVar2.c(), aVar2.d(), f + 1.0f, true);
            r0.closePath();
        }
        r0.transform(AffineTransform.getTranslateInstance((!aVar2.g() ? (-rectangle.width) / 2.0f : (-rectangle.height) / 2.0f) + ((f + 1.0f) * aVar2.d()), (aVar2.g() ? (-rectangle.width) / 2.0f : (-rectangle.height) / 2.0f) + ((f + 1.0f) * aVar2.c())));
        r0.transform(AffineTransform.getRotateInstance(aVar2.f()));
        r0.transform(AffineTransform.getTranslateInstance((rectangle.width / 2.0f) + rectangle.x, (rectangle.height / 2.0f) + rectangle.y));
        return r0;
    }

    private static void a(Path2D path2D, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z) {
        float f10 = ((f4 * 2.0f) - (f9 * f8)) - (f9 * f8);
        float f11 = ((f3 * 2.0f) - (f9 * f7)) - (f9 * f7);
        if (f10 > 0.0f && f11 > 0.0f) {
            path2D.append(new Arc2D.Float(f - (z ? 0.0f : f10), f2, f10, f11, f5, f6, 0), true);
        } else if (path2D.getCurrentPoint() == null) {
            path2D.moveTo(f, f2);
        } else {
            path2D.lineTo(f, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/e$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final float f1348a;

        /* renamed from: b, reason: collision with root package name */
        private final float f1349b;
        private final float c;
        private final com.d.c.f.b d;
        private final com.d.c.f.b e;
        private final double f;
        private final boolean g;

        public a(com.d.c.f.a.a aVar, int i, float f) {
            if ((i & 1) == 1) {
                this.f1348a = aVar.t() * f;
                this.f1349b = aVar.w() * f;
                this.c = aVar.u() * f;
                this.d = aVar.s();
                this.e = aVar.r();
                this.f = 0.0d;
                this.g = false;
                return;
            }
            if ((i & 8) == 8) {
                this.f1348a = aVar.u() * f;
                this.f1349b = aVar.t() * f;
                this.c = aVar.v() * f;
                this.d = aVar.r();
                this.e = aVar.p();
                this.f = 1.5707963267948966d;
                this.g = true;
                return;
            }
            if ((i & 4) == 4) {
                this.f1348a = aVar.v() * f;
                this.f1349b = aVar.u() * f;
                this.c = aVar.w() * f;
                this.d = aVar.p();
                this.e = aVar.q();
                this.f = 3.141592653589793d;
                this.g = false;
                return;
            }
            if ((i & 2) == 2) {
                this.f1348a = aVar.w() * f;
                this.f1349b = aVar.v() * f;
                this.c = aVar.t() * f;
                this.d = aVar.q();
                this.e = aVar.s();
                this.f = 4.71238898038469d;
                this.g = true;
                return;
            }
            throw new IllegalArgumentException("No side found");
        }

        public final com.d.c.f.b a() {
            return this.e;
        }

        public final com.d.c.f.b b() {
            return this.d;
        }

        public final float c() {
            return this.f1348a;
        }

        public final float d() {
            return this.f1349b;
        }

        public final float e() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double f() {
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean g() {
            return this.g;
        }
    }

    public static void a(Rectangle rectangle, int i, com.d.c.f.a.a aVar, ab abVar, int i2, boolean z) {
        if ((i & 1) == 1 && aVar.b()) {
            i--;
        }
        if ((i & 2) == 2 && aVar.e()) {
            i -= 2;
        }
        if ((i & 4) == 4 && aVar.d()) {
            i -= 4;
        }
        if ((i & 8) == 8 && aVar.c()) {
            i -= 8;
        }
        if ((i & 1) == 1 && aVar.j() != com.d.c.d.h.f1056a) {
            a(abVar.p(), aVar, rectangle, i, 1, aVar.f(), 0, z);
        }
        if ((i & 4) == 4 && aVar.l() != com.d.c.d.h.f1056a) {
            a(abVar.p(), aVar, rectangle, i, 4, aVar.h(), 0, z);
        }
        if ((i & 2) == 2 && aVar.m() != com.d.c.d.h.f1056a) {
            a(abVar.p(), aVar, rectangle, i, 2, aVar.i(), 0, z);
        }
        if ((i & 8) == 8 && aVar.k() != com.d.c.d.h.f1056a) {
            a(abVar.p(), aVar, rectangle, i, 8, aVar.g(), 0, z);
        }
    }

    private static void a(com.d.d.m mVar, com.d.c.f.a.a aVar, Rectangle rectangle, int i, int i2, com.d.c.a.c cVar, int i3, boolean z) {
        com.d.c.f.a.a a2;
        com.d.c.f.a.a aVar2;
        if (cVar == com.d.c.a.c.aI || cVar == com.d.c.a.c.O) {
            new com.d.c.f.a.a((int) (aVar.t() / 2.0f), (int) (aVar.u() / 2.0f), (int) (aVar.v() / 2.0f), (int) (aVar.w() / 2.0f));
            if (cVar == com.d.c.a.c.aI) {
                a2 = aVar;
                aVar2 = aVar.a();
            } else {
                a2 = aVar.a();
                aVar2 = aVar;
            }
            a(mVar, rectangle, a2, aVar2, 0.0f, 1.0f, i, i2, z);
            a(mVar, rectangle, aVar2, a2, 1.0f, 0.5f, i, i2, z);
            return;
        }
        if (cVar == com.d.c.a.c.av) {
            a(mVar, rectangle, aVar, aVar.a(), 0.0f, 1.0f, i, i2, z);
            return;
        }
        if (cVar == com.d.c.a.c.U) {
            a(mVar, rectangle, aVar.a(), aVar, 0.0f, 1.0f, i, i2, z);
            return;
        }
        if (cVar == com.d.c.a.c.aQ) {
            mVar.a((Stroke) new BasicStroke(1.0f));
            if (i2 == 1) {
                mVar.a(aVar.j());
                mVar.c(a(rectangle, 1, aVar, true, 0.0f, 1.0f));
            }
            if (i2 == 8) {
                mVar.a(aVar.k());
                mVar.c(a(rectangle, 8, aVar, true, 0.0f, 1.0f));
            }
            if (i2 == 4) {
                mVar.a(aVar.l());
                mVar.c(a(rectangle, 4, aVar, true, 0.0f, 1.0f));
            }
            if (i2 == 2) {
                mVar.a(aVar.m());
                mVar.c(a(rectangle, 2, aVar, true, 0.0f, 1.0f));
                return;
            }
            return;
        }
        if (cVar == com.d.c.a.c.z) {
            a(mVar, aVar, rectangle, i, i2, z);
            return;
        }
        int i4 = 0;
        if (i2 == 1) {
            i4 = (int) aVar.t();
        }
        if (i2 == 4) {
            i4 = (int) aVar.v();
        }
        if (i2 == 8) {
            i4 = (int) aVar.u();
        }
        if (i2 == 2) {
            i4 = (int) aVar.w();
        }
        if (cVar == com.d.c.a.c.u) {
            a(mVar, rectangle, aVar, aVar, new float[]{8.0f + (i4 << 1), 4.0f + i4}, i2, i3);
        }
        if (cVar == com.d.c.a.c.y) {
            a(mVar, rectangle, aVar, aVar, new float[]{i4, i4}, i2, i3);
        }
    }

    private static void a(com.d.d.m mVar, com.d.c.f.a.a aVar, Rectangle rectangle, int i, int i2, boolean z) {
        a(mVar, rectangle, aVar, 0.0f, 0.33333334f, i2);
        a(mVar, rectangle, aVar, 2.0f, 0.33333334f, i2);
    }

    private static void a(com.d.d.m mVar, Rectangle rectangle, com.d.c.f.a.a aVar, com.d.c.f.a.a aVar2, float[] fArr, int i, int i2) {
        Stroke d = mVar.d();
        Path2D a2 = a(rectangle, i, aVar, false, 0.5f, 1.0f);
        Area area = new Area(a(rectangle, i, aVar, true, 0.0f, 1.0f));
        Shape shape = null;
        if (!mVar.i()) {
            Shape c = mVar.c();
            shape = c;
            if (c != null) {
                area.intersect(new Area(shape));
            }
            mVar.e(area);
        } else {
            mVar.f(area);
        }
        if (i == 1) {
            mVar.a(aVar2.j());
            mVar.a((Stroke) new BasicStroke(2 * ((int) aVar.t()), 0, 2, 0.0f, fArr, i2));
            aVar.t();
            mVar.a((Shape) a2);
        } else if (i == 2) {
            mVar.a(aVar2.m());
            mVar.a((Stroke) new BasicStroke(2 * ((int) aVar.w()), 0, 2, 0.0f, fArr, 0.0f));
            aVar.w();
            mVar.a((Shape) a2);
        } else if (i == 8) {
            mVar.a(aVar2.k());
            mVar.a((Stroke) new BasicStroke(2 * ((int) aVar.u()), 0, 2, 0.0f, fArr, 0.0f));
            aVar.u();
            mVar.a((Shape) a2);
        } else if (i == 4) {
            mVar.a(aVar2.l());
            mVar.a((Stroke) new BasicStroke(2 * ((int) aVar.v()), 0, 2, 0.0f, fArr, i2));
            aVar.v();
            mVar.a((Shape) a2);
        }
        if (!mVar.i()) {
            mVar.e(shape);
        } else {
            mVar.h();
        }
        mVar.a(d);
    }

    private static void a(com.d.d.m mVar, Rectangle rectangle, com.d.c.f.a.a aVar, com.d.c.f.a.a aVar2, float f, float f2, int i, int i2, boolean z) {
        if (i2 == 1) {
            a(mVar, rectangle, aVar, f, f2, i2);
            return;
        }
        if (i2 == 4) {
            a(mVar, rectangle, aVar2, f, f2, i2);
        } else if (i2 == 8) {
            a(mVar, rectangle, aVar2, f, f2, i2);
        } else if (i2 == 2) {
            a(mVar, rectangle, aVar, f, f2, i2);
        }
    }

    private static void a(com.d.d.m mVar, Rectangle rectangle, com.d.c.f.a.a aVar, float f, float f2, int i) {
        if (i == 1) {
            mVar.a(aVar.j());
            if (((int) aVar.t()) == 1) {
                mVar.b((Shape) a(rectangle, i, aVar, false, f, f2));
                return;
            } else {
                mVar.c(a(rectangle, i, aVar, true, f, f2));
                return;
            }
        }
        if (i == 4) {
            mVar.a(aVar.l());
            if (((int) aVar.v()) == 1) {
                mVar.b((Shape) a(rectangle, i, aVar, false, f, f2));
                return;
            } else {
                mVar.c(a(rectangle, i, aVar, true, f, f2));
                return;
            }
        }
        if (i == 8) {
            mVar.a(aVar.k());
            if (((int) aVar.u()) == 1) {
                mVar.b((Shape) a(rectangle, i, aVar, false, f, f2));
                return;
            } else {
                mVar.c(a(rectangle, i, aVar, true, f, f2));
                return;
            }
        }
        if (i == 2) {
            mVar.a(aVar.m());
            if (((int) aVar.w()) == 1) {
                mVar.b((Shape) a(rectangle, i, aVar, false, f, f2));
            } else {
                mVar.c(a(rectangle, i, aVar, true, f, f2));
            }
        }
    }

    public e(String str, List<com.d.c.d.j> list) {
        this.f1346a = str;
        this.f1347b = list;
    }

    public String a() {
        return this.f1346a;
    }

    public List<com.d.c.d.j> b() {
        return this.f1347b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1346a);
        sb.append('(');
        Iterator<com.d.c.d.j> it = this.f1347b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(',');
        }
        sb.append(')');
        return sb.toString();
    }
}
