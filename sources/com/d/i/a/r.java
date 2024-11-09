package com.d.i.a;

import com.d.i.aa;
import com.d.i.ab;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/a/r.class */
public class r implements com.d.c.e.e {

    /* renamed from: a, reason: collision with root package name */
    private final String f1315a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1316b;
    private final List<com.d.c.e.a> c = new ArrayList();
    private final List<com.d.l.b> d = new ArrayList();
    private final List<Object> e = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/i/a/r$a.class */
    public enum a {
        DOCUMENT_TOP,
        PAGE_TOP,
        PAGE_BOTTOM
    }

    public static AffineTransform a(ab abVar, com.d.i.f fVar, aa aaVar, int i) {
        a aVar = abVar.p().f() ? a.PAGE_BOTTOM : a.PAGE_TOP;
        AffineTransform affineTransform = new AffineTransform();
        a(abVar, fVar, aaVar, affineTransform, aVar, i);
        return affineTransform;
    }

    public static AffineTransform a(ab abVar, com.d.i.f fVar, aa aaVar, int i, int i2) {
        float f;
        float f2;
        com.d.c.f.g i3 = fVar.a().i(com.d.c.a.a.ay);
        float b2 = fVar.a().b(com.d.c.a.a.az, fVar.Q(), abVar);
        float c = fVar.a().c(com.d.c.a.a.aA, fVar.as(), abVar);
        float f3 = abVar.p().f() ? -1.0f : 1.0f;
        float ab = b2 + fVar.ab();
        float aa = c + fVar.aa();
        if (abVar.p().f()) {
            f = ab + i;
            f2 = aaVar.b(abVar) - (aa + i2);
        } else {
            f = ab + i;
            f2 = aa + i2;
        }
        AffineTransform translateInstance = AffineTransform.getTranslateInstance(f, f2);
        AffineTransform translateInstance2 = AffineTransform.getTranslateInstance(-f, -f2);
        List<com.d.c.d.j> j = ((com.d.c.f.a.f) i3).j();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.concatenate(translateInstance);
        a(f3, j, affineTransform, fVar, abVar);
        affineTransform.concatenate(translateInstance2);
        return affineTransform;
    }

    public static AffineTransform a(com.d.i.f fVar, com.d.c.f.d dVar, AffineTransform affineTransform) {
        AffineTransform affineTransform2 = affineTransform == null ? new AffineTransform() : (AffineTransform) affineTransform.clone();
        a(dVar, fVar, null, affineTransform2, a.DOCUMENT_TOP, -1);
        return affineTransform2;
    }

    private static float a(float f, int i, aa aaVar, com.d.c.f.d dVar, com.d.i.f fVar) {
        if (i == -1 || fVar.af().c()) {
            return f + aaVar.d(dVar, 1);
        }
        return (f - ((float) aaVar.b(dVar, i).getMinX())) + aaVar.d(dVar, 1);
    }

    private static void a(com.d.c.f.d dVar, com.d.i.f fVar, aa aaVar, AffineTransform affineTransform, a aVar, int i) {
        float a2;
        float d;
        AffineTransform translateInstance;
        AffineTransform translateInstance2;
        com.d.c.f.g i2 = fVar.a().i(com.d.c.a.a.ay);
        float b2 = fVar.a().b(com.d.c.a.a.az, fVar.Q(), dVar);
        float c = fVar.a().c(com.d.c.a.a.aA, fVar.as(), dVar);
        float f = aVar == a.PAGE_BOTTOM ? -1.0f : 1.0f;
        float ab = b2 + fVar.ab();
        float aa = c + fVar.aa();
        if (aVar == a.PAGE_BOTTOM || aVar == a.PAGE_TOP) {
            if (aVar == a.PAGE_BOTTOM) {
                a2 = a(ab, i, aaVar, dVar, fVar);
                d = aaVar.b(dVar) - ((aa + aaVar.d(dVar, 3)) - aaVar.d());
            } else {
                a2 = a(ab, i, aaVar, dVar, fVar);
                d = (aa - aaVar.d()) + aaVar.d(dVar, 3);
            }
            translateInstance = AffineTransform.getTranslateInstance(a2, d);
            translateInstance2 = AffineTransform.getTranslateInstance(-a2, -d);
        } else {
            translateInstance = AffineTransform.getTranslateInstance(ab, aa);
            translateInstance2 = AffineTransform.getTranslateInstance(-ab, -aa);
        }
        List<com.d.c.d.j> j = ((com.d.c.f.a.f) i2).j();
        affineTransform.concatenate(translateInstance);
        a(f, j, affineTransform, fVar, dVar);
        affineTransform.concatenate(translateInstance2);
    }

    private static void a(float f, List<com.d.c.d.j> list, AffineTransform affineTransform, com.d.i.f fVar, com.d.c.f.d dVar) {
        float f2;
        for (com.d.c.d.j jVar : list) {
            String a2 = jVar.n().a();
            List<com.d.c.d.j> b2 = jVar.n().b();
            if ("rotate".equalsIgnoreCase(a2)) {
                affineTransform.concatenate(AffineTransform.getRotateInstance(f * a(b2.get(0))));
            } else if ("scale".equalsIgnoreCase(a2) || "scalex".equalsIgnoreCase(a2) || "scaley".equalsIgnoreCase(a2)) {
                float f3 = b2.get(0).f();
                float f4 = b2.get(0).f();
                if (b2.size() > 1) {
                    f4 = b2.get(1).f();
                }
                if ("scalex".equalsIgnoreCase(a2)) {
                    f4 = 1.0f;
                }
                if ("scaley".equalsIgnoreCase(a2)) {
                    f3 = 1.0f;
                }
                affineTransform.concatenate(AffineTransform.getScaleInstance(f3, f4));
            } else if ("skew".equalsIgnoreCase(a2)) {
                float a3 = f * a(b2.get(0));
                float f5 = 0.0f;
                if (b2.size() > 1) {
                    f5 = a(b2.get(1));
                }
                affineTransform.concatenate(AffineTransform.getShearInstance(Math.tan(a3), Math.tan(f5)));
            } else if ("skewx".equalsIgnoreCase(a2)) {
                affineTransform.concatenate(AffineTransform.getShearInstance(Math.tan(f * a(b2.get(0))), 0.0d));
            } else if ("skewy".equalsIgnoreCase(a2)) {
                affineTransform.concatenate(AffineTransform.getShearInstance(0.0d, Math.tan(f * a(b2.get(0)))));
            } else if ("matrix".equalsIgnoreCase(a2)) {
                affineTransform.concatenate(new AffineTransform(b2.get(0).f(), b2.get(1).f(), b2.get(2).f(), b2.get(3).f(), b2.get(4).f(), b2.get(5).f()));
            } else if ("translate".equalsIgnoreCase(a2)) {
                float a4 = com.d.c.f.a.e.a(fVar.a(), null, null, b2.get(0).f(), b2.get(0).a(), fVar.Q(), dVar);
                if (b2.size() > 1) {
                    f2 = com.d.c.f.a.e.a(fVar.a(), null, null, b2.get(1).f(), b2.get(0).a(), fVar.Q(), dVar);
                } else {
                    f2 = a4;
                }
                affineTransform.concatenate(AffineTransform.getTranslateInstance(a4, f * f2));
            } else if ("translateX".equalsIgnoreCase(a2)) {
                affineTransform.concatenate(AffineTransform.getTranslateInstance(com.d.c.f.a.e.a(fVar.a(), null, null, b2.get(0).f(), b2.get(0).a(), fVar.Q(), dVar), 0.0d));
            } else if ("translateY".equalsIgnoreCase(a2)) {
                affineTransform.concatenate(AffineTransform.getTranslateInstance(0.0d, f * com.d.c.f.a.e.a(fVar.a(), null, null, b2.get(0).f(), b2.get(0).a(), fVar.as(), dVar)));
            }
        }
    }

    private static float a(com.d.c.d.j jVar) {
        if (jVar.a() == 11) {
            return (float) Math.toRadians(jVar.f());
        }
        if (jVar.a() == 12) {
            return jVar.f();
        }
        return (float) (jVar.f() * 0.015707963267948967d);
    }

    public r(String str, int i) {
        this.f1315a = str;
        this.f1316b = i;
    }

    @Override // com.d.c.e.e
    public int a() {
        return this.f1316b;
    }

    public String b() {
        return this.f1315a;
    }

    @Override // com.d.c.e.e
    public void a(com.d.c.e.d dVar) {
        this.e.add(dVar);
    }

    public void a(com.d.c.e.b bVar) {
        this.e.add(bVar);
    }

    public void a(com.d.c.e.c cVar) {
        this.e.add(cVar);
    }

    public List<Object> c() {
        return this.e;
    }

    public void a(com.d.l.b bVar) {
        this.d.add(bVar);
    }

    public List<com.d.l.b> d() {
        return this.d;
    }

    public void a(com.d.c.e.a aVar) {
        this.c.add(aVar);
    }

    public List<com.d.c.e.a> e() {
        return this.c;
    }
}
