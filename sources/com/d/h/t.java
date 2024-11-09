package com.d.h;

import com.a.a.a.am;
import com.a.a.c.k.b.ak;
import com.d.e.aa;
import com.d.h.f;
import com.d.h.w;
import com.d.i.ab;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.PathIterator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/t.class */
public class t extends com.d.i.a implements m {

    /* renamed from: a, reason: collision with root package name */
    private static final AffineTransform f1263a;

    /* renamed from: b, reason: collision with root package name */
    private static final BasicStroke f1264b;
    private static final boolean c;
    private org.a.c.h.e d;
    private w e;
    private float f;
    private com.d.h.b g;
    private int j;
    private com.d.c.d.g n;
    private com.d.c.d.g o;
    private Area s;
    private aa t;
    private org.a.c.h.b v;
    private org.a.c.h.g.d.a.a w;
    private com.d.i.f A;
    private int B;
    private k C;
    private ab D;
    private a.a.a.a.a F;
    private static /* synthetic */ boolean G;
    private AffineTransform h = new AffineTransform();
    private final Deque<AffineTransform> i = new ArrayDeque();
    private float k = 0.0f;
    private float l = 0.0f;
    private com.d.c.d.g m = com.d.c.d.h.e;
    private Stroke p = null;
    private Stroke q = null;
    private Stroke r = null;
    private final List<a> x = new ArrayList();
    private final List<c> y = new ArrayList();
    private final o z = new o();
    private com.d.a.a E = new ak();
    private float u = 26.666666f;

    /* loaded from: infinitode-2.jar:com/d/h/t$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        String f1267a;

        /* renamed from: b, reason: collision with root package name */
        f.b f1268b;
        int c;
        int d;
    }

    static {
        G = !t.class.desiredAssertionStatus();
        f1263a = new AffineTransform();
        f1264b = new BasicStroke(1.0f);
        c = com.d.m.b.a("xr.pdf.round.rect.dimensions.down", false);
    }

    public t(float f, boolean z) {
    }

    @Override // com.d.h.m
    public final void a(org.a.c.h.b bVar) {
        this.v = bVar;
    }

    @Override // com.d.h.m
    public final org.a.c.h.b j() {
        return this.v;
    }

    @Override // com.d.h.m
    public final void a(org.a.c.h.g gVar, org.a.c.h.e eVar, float f) {
        this.e = new w(gVar);
        this.d = eVar;
        this.f = f;
        if (!i()) {
            this.e.i();
        }
        this.h = new AffineTransform();
        this.h.scale(1.0d / this.u, 1.0d / this.u);
        this.k = 0.0f;
        this.l += f * this.u;
        this.p = b((Stroke) f1264b);
        this.q = this.p;
        this.r = this.p;
        a(this.p, (Stroke) null);
        if (this.w == null) {
            new org.a.c.h.g.d.a.c().a(eVar);
        }
    }

    @Override // com.d.h.m
    public final void k() {
        if (!i()) {
            this.e.h();
        }
        this.e.b();
    }

    @Override // com.d.d.m
    public final void a(ab abVar, com.d.i.c cVar) {
        cVar.E().a(abVar, this, cVar);
    }

    @Override // com.d.i.a, com.d.d.m
    public final void b(ab abVar, com.d.i.f fVar) {
        super.b(abVar, fVar);
        this.C.a(abVar, fVar, this.d, this.f, this.h);
        if (fVar.ai() != null && fVar.ai().getNodeName().equals("form")) {
            this.z.a(fVar, this);
        } else if (fVar.ai() != null && com.d.m.a.a(fVar.ai().getNodeName(), FlexmarkHtmlConverter.INPUT_NODE, "textarea", "button", "select", "openhtmltopdf-combo")) {
            this.z.a(fVar, this.d, this.h, abVar, this.f);
        }
    }

    private void p() {
        this.z.a(this.t, this.v, this.A);
    }

    @Override // com.d.h.m
    public final float a(float f) {
        return f / this.u;
    }

    @Override // com.d.d.m
    public final void a(Shape shape) {
        b(shape);
    }

    @Override // com.d.d.m
    public final void a(com.d.c.d.g gVar) {
        if (gVar instanceof com.d.c.d.h) {
            this.m = gVar;
            return;
        }
        if (gVar instanceof com.d.c.d.f) {
            this.m = gVar;
        } else if (!G && !(this.m instanceof com.d.c.d.h) && !(this.m instanceof com.d.c.d.f)) {
            throw new AssertionError();
        }
    }

    @Override // com.d.d.m
    public final void b(Shape shape) {
        a(shape, 2);
    }

    @Override // com.d.i.a
    protected final void e(int i, int i2, int i3, int i4) {
        b((Shape) new Line2D.Double(i, i2, i3, i4));
    }

    @Override // com.d.d.m
    public final void a(int i, int i2, int i3, int i4) {
        b((Shape) new Rectangle(i, i2, i3, i4));
    }

    @Override // com.d.d.m
    public final void b(int i, int i2, int i3, int i4) {
        b((Shape) new Ellipse2D.Float(i, i2, i3, i4));
    }

    @Override // com.d.d.m
    public final void c(Shape shape) {
        a(shape, 1);
    }

    @Override // com.d.d.m
    public final void c(int i, int i2, int i3, int i4) {
        if (c) {
            c((Shape) new Rectangle(i, i2, i3 - 1, i4 - 1));
        } else {
            c((Shape) new Rectangle(i, i2, i3, i4));
        }
    }

    @Override // com.d.d.m
    public final void d(int i, int i2, int i3, int i4) {
        c((Shape) new Ellipse2D.Float(i, i2, i3, i4));
    }

    @Override // com.d.d.m
    public final void a(double d, double d2) {
        this.h.translate(d, d2);
    }

    @Override // com.d.d.m
    public final Object e() {
        return null;
    }

    @Override // com.d.d.m
    public final void a(com.d.i.k kVar) {
        this.g = (com.d.h.b) kVar;
    }

    private AffineTransform b(AffineTransform affineTransform) {
        double[] dArr = new double[6];
        new AffineTransform().getMatrix(dArr);
        dArr[3] = -1.0d;
        dArr[5] = this.f;
        AffineTransform affineTransform2 = new AffineTransform(dArr);
        affineTransform2.concatenate(affineTransform);
        return affineTransform2;
    }

    @Override // com.d.h.m
    public final void a(String str, float f, float f2, com.d.i.t tVar) {
        try {
            this.g.b().get(0).c().b(str);
            a(str, f, f2, tVar, this.g.b().get(0), this.g.a());
        } catch (Exception unused) {
            float f3 = 0.0f;
            for (b bVar : u.a(this.g, str, this.E)) {
                a(bVar.f1267a, f + f3, f2, tVar, bVar.f1268b, this.g.a());
                try {
                    f3 += (bVar.f1268b.c().b(bVar.f1267a) / 1000.0f) * this.g.a();
                } catch (Exception e) {
                    com.d.m.l.g(Level.WARNING, "BUG. Font didn't contain expected character.", e);
                }
            }
        }
    }

    private void a(String str, float f, float f2, com.d.i.t tVar, f.b bVar, float f3) {
        if (str.length() == 0) {
            return;
        }
        r();
        AffineTransform affineTransform = (AffineTransform) q().clone();
        affineTransform.translate(f, f2);
        AffineTransform b2 = b(affineTransform);
        b2.concatenate(AffineTransform.getScaleInstance(1.0d, -1.0d));
        b2.scale(this.u, this.u);
        double[] dArr = new double[6];
        b2.getMatrix(dArr);
        float f4 = (float) dArr[1];
        float f5 = (float) dArr[2];
        float f6 = f3 / this.u;
        boolean z = false;
        com.d.c.g.a o = o();
        if (o != null) {
            if (com.a.a.b.c.a.a(o.f1095b) > bVar.a()) {
                this.e.a(org.a.c.h.f.e.a.FILL_STROKE);
                this.e.a(f6 * 0.04f);
                z = true;
                s();
            }
            if (o.d == com.d.c.a.c.W && bVar.b() != com.d.c.a.c.W) {
                f4 = 0.0f;
                f5 = 0.21256f;
            }
        }
        this.e.j();
        this.e.a(bVar.c(), f6);
        this.e.b((float) dArr[0], f4, f5, (float) dArr[3], (float) dArr[4], (float) dArr[5]);
        if (tVar != null) {
            this.e.a(a(str, tVar));
        } else {
            this.e.a(str);
        }
        this.e.k();
        if (z) {
            this.e.a(org.a.c.h.f.e.a.FILL);
            this.e.a(1.0f);
        }
    }

    private Object[] a(String str, com.d.i.t tVar) {
        float b2;
        ArrayList arrayList = new ArrayList(str.length() << 1);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            arrayList.add(Character.toString(charAt));
            if (i != length - 1) {
                if (charAt == ' ' || charAt == 160 || charAt == 12288) {
                    b2 = tVar.b();
                } else {
                    b2 = tVar.a();
                }
                arrayList.add(Float.valueOf((((-b2) / this.u) * 1000.0f) / (this.g.a() / this.u)));
            }
        }
        return arrayList.toArray();
    }

    private AffineTransform q() {
        return this.h;
    }

    private void r() {
        if (!this.m.equals(this.n)) {
            this.n = this.m;
            if (this.n instanceof com.d.c.d.h) {
                com.d.c.d.h hVar = (com.d.c.d.h) this.n;
                this.e.b(hVar.c(), hVar.b(), hVar.a());
            } else if (this.n instanceof com.d.c.d.f) {
                com.d.c.d.f fVar = (com.d.c.d.f) this.n;
                this.e.d(fVar.a(), fVar.b(), fVar.c(), fVar.d());
            } else if (!G && !(this.n instanceof com.d.c.d.h) && !(this.n instanceof com.d.c.d.f)) {
                throw new AssertionError();
            }
        }
    }

    private void s() {
        if (!this.m.equals(this.o)) {
            this.o = this.m;
            if (this.o instanceof com.d.c.d.h) {
                com.d.c.d.h hVar = (com.d.c.d.h) this.o;
                this.e.a(hVar.c(), hVar.b(), hVar.a());
            } else if (this.o instanceof com.d.c.d.f) {
                com.d.c.d.f fVar = (com.d.c.d.f) this.o;
                this.e.c(fVar.a(), fVar.b(), fVar.c(), fVar.d());
            } else if (!G && !(this.o instanceof com.d.c.d.h) && !(this.o instanceof com.d.c.d.f)) {
                throw new AssertionError();
            }
        }
    }

    private void a(Shape shape, int i) {
        PathIterator pathIterator;
        if (shape == null) {
            return;
        }
        if (i == 2 && !(this.p instanceof BasicStroke)) {
            a(this.p.createStrokedShape(shape), 1);
            return;
        }
        if (i == 2) {
            a(this.p, this.r);
            this.r = this.p;
            s();
        } else if (i == 1) {
            r();
        }
        if (i == 3) {
            pathIterator = shape.getPathIterator(f1263a);
        } else {
            pathIterator = shape.getPathIterator(this.h);
        }
        float[] fArr = new float[6];
        int i2 = 0;
        while (!pathIterator.isDone()) {
            i2++;
            int currentSegment = pathIterator.currentSegment(fArr);
            a(fArr);
            switch (currentSegment) {
                case 0:
                    this.e.b(fArr[0], fArr[1]);
                    break;
                case 1:
                    this.e.a(fArr[0], fArr[1]);
                    break;
                case 2:
                    this.e.b(fArr[0], fArr[1], fArr[2], fArr[3]);
                    break;
                case 3:
                    this.e.a(fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5]);
                    break;
                case 4:
                    this.e.a();
                    break;
            }
            pathIterator.next();
        }
        switch (i) {
            case 1:
                if (i2 > 0) {
                    if (pathIterator.getWindingRule() == 0) {
                        this.e.c();
                        return;
                    } else {
                        this.e.d();
                        return;
                    }
                }
                return;
            case 2:
                if (i2 > 0) {
                    this.e.e();
                    return;
                }
                return;
            default:
                if (i2 == 0) {
                    this.e.a(0.0f, 0.0f, 0.0f, 0.0f);
                }
                if (pathIterator.getWindingRule() == 0) {
                    this.e.g();
                    return;
                } else {
                    this.e.f();
                    return;
                }
        }
    }

    private float b(float f) {
        return this.f - f;
    }

    @Override // com.d.h.m
    public final float a(float f, float f2) {
        return f2 - f;
    }

    private void a(float[] fArr) {
        fArr[1] = b(fArr[1]);
        fArr[3] = b(fArr[3]);
        fArr[5] = b(fArr[5]);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.awt.Stroke r5, java.awt.Stroke r6) {
        /*
            Method dump skipped, instructions count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.d.h.t.a(java.awt.Stroke, java.awt.Stroke):void");
    }

    @Override // com.d.d.m
    public final void a(Stroke stroke) {
        this.q = stroke;
        this.p = b(stroke);
    }

    private Stroke b(Stroke stroke) {
        if (!(stroke instanceof BasicStroke)) {
            return stroke;
        }
        BasicStroke basicStroke = (BasicStroke) stroke;
        float sqrt = (float) Math.sqrt(Math.abs(this.h.getDeterminant()));
        float[] dashArray = basicStroke.getDashArray();
        if (dashArray != null) {
            for (int i = 0; i < dashArray.length; i++) {
                int i2 = i;
                dashArray[i2] = dashArray[i2] * sqrt;
            }
        }
        return new BasicStroke(basicStroke.getLineWidth() * sqrt, basicStroke.getEndCap(), basicStroke.getLineJoin(), basicStroke.getMiterLimit(), dashArray, basicStroke.getDashPhase() * sqrt);
    }

    @Override // com.d.d.m
    public final void d(Shape shape) {
        if (i()) {
            com.d.m.l.h(Level.SEVERE, "clip MUST not be used by the fast renderer. Please consider reporting this bug.");
            return;
        }
        if (shape != null) {
            Shape createTransformedShape = this.h.createTransformedShape(shape);
            if (this.s == null) {
                this.s = new Area(createTransformedShape);
            } else {
                this.s.intersect(new Area(createTransformedShape));
            }
            a(createTransformedShape, 3);
            return;
        }
        if (!G && shape == null) {
            throw new AssertionError();
        }
    }

    @Override // com.d.d.m
    public final Shape c() {
        if (i()) {
            com.d.m.l.h(Level.SEVERE, "getClip MUST not be used by the fast renderer. Please consider reporting this bug.");
            return null;
        }
        try {
            return this.h.createInverse().createTransformedShape(this.s);
        } catch (NoninvertibleTransformException unused) {
            return null;
        }
    }

    @Override // com.d.d.m
    public final void h() {
        this.e.h();
        u();
    }

    @Override // com.d.d.m
    public final void f(Shape shape) {
        this.e.i();
        if (shape != null) {
            a(this.h.createTransformedShape(shape), 3);
        }
    }

    @Override // com.d.d.m
    public final void e(Shape shape) {
        if (i()) {
            com.d.m.l.h(Level.SEVERE, "setClip MUST not be used by the fast renderer. Please consider reporting this bug.");
            return;
        }
        this.e.h();
        t();
        this.e.i();
        this.j = this.i.size();
        if (shape != null) {
            shape = this.h.createTransformedShape(shape);
        }
        if (shape == null) {
            this.s = null;
        } else {
            this.s = new Area(shape);
            a(shape, 3);
        }
        u();
    }

    @Override // com.d.d.m
    public final Stroke d() {
        return this.q;
    }

    @Override // com.d.h.m
    public final void a(i iVar) {
        org.a.c.h.f.c.b a2;
        try {
            if (iVar.g()) {
                a2 = am.a(this.v, new ByteArrayInputStream(iVar.c()));
            } else {
                a2 = org.a.c.h.f.c.a.a(this.v, ImageIO.read(new ByteArrayInputStream(iVar.c())));
            }
            iVar.d();
            iVar.a(a2);
        } catch (IOException e) {
            throw new w.a("realizeImage", (Exception) e);
        }
    }

    @Override // com.d.d.m
    public final void a(com.d.d.c cVar, int i, int i2, boolean z) {
        org.a.c.h.f.c.b e = ((i) cVar).e();
        if (z) {
            e.a(true);
        } else {
            try {
                InputStream j = e.c().f().j();
                org.a.c.h.f.c.b bVar = new org.a.c.h.f.c.b(this.v, j, org.a.c.b.j.bc, e.h(), e.g(), e.d(), e.e());
                bVar.a(false);
                if (e.a() != null) {
                    bVar.f().a(org.a.c.b.j.ds, e.a());
                }
                j.close();
                e = bVar;
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        AffineTransform affineTransform = (AffineTransform) q().clone();
        affineTransform.translate(i, i2);
        affineTransform.translate(0.0d, r0.b());
        AffineTransform b2 = b(affineTransform);
        b2.scale(r0.a(), -r0.b());
        double[] dArr = new double[6];
        b2.getMatrix(dArr);
        this.e.a(e, (float) dArr[4], (float) dArr[5], (float) dArr[0], (float) dArr[3]);
    }

    @Override // com.d.h.m
    public final void a(org.a.c.h.f.b.a aVar, Rectangle rectangle, float f, float f2) {
        throw new UnsupportedOperationException("Use the fast mode!");
    }

    @Override // com.d.h.m
    public final void a(Document document) {
        this.C = new k(this.t, this.u, this.A, this);
        b(document);
        c(document);
    }

    @Override // com.d.h.m
    public final void c(ab abVar, com.d.i.f fVar) {
        p();
        this.C.a();
        d(abVar, fVar);
    }

    private void d(ab abVar, com.d.i.f fVar) {
        if (this.x.size() > 0) {
            org.a.c.h.g.d.b.a c2 = this.v.c().c();
            org.a.c.h.g.d.b.a aVar = c2;
            if (c2 == null) {
                aVar = new org.a.c.h.g.d.b.a();
                this.v.c().a(aVar);
            }
            a(abVar, fVar, aVar, this.x);
        }
    }

    private void a(ab abVar, com.d.i.f fVar, org.a.c.h.g.d.b.c cVar, List<a> list) {
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            a(abVar, fVar, cVar, it.next());
        }
    }

    @Override // com.d.h.m
    public final int b(com.d.i.f fVar) {
        if (fVar instanceof com.d.i.r) {
            com.d.i.r rVar = (com.d.i.r) fVar;
            return rVar.aa() + rVar.c();
        }
        return fVar.aa();
    }

    private void a(ab abVar, com.d.i.f fVar, org.a.c.h.g.d.b.c cVar, a aVar) {
        com.d.i.f a2;
        String a3 = aVar.a();
        org.a.c.h.g.d.a.d dVar = null;
        if (a3.length() > 0 && a3.charAt(0) == '#' && (a2 = this.t.a(a3.substring(1))) != null) {
            com.d.i.aa a4 = fVar.Z().a((com.d.c.f.d) abVar, b(a2));
            int d = a4.d(abVar, 3) + (a2.aa() - a4.b());
            org.a.c.h.g.d.a.d dVar2 = new org.a.c.h.g.d.a.d();
            dVar = dVar2;
            dVar2.a((int) b(d / this.u));
            dVar.a(this.v.a(this.B + a4.i()));
        }
        org.a.c.h.g.d.b.b bVar = new org.a.c.h.g.d.b.b();
        bVar.a(dVar == null ? this.w : dVar);
        bVar.a(aVar.b());
        cVar.a(bVar);
        a(abVar, fVar, bVar, aVar.c());
    }

    private void b(Document document) {
        Element a2;
        List<Element> b2;
        Element a3 = com.a.a.b.c.a.a(document.getDocumentElement(), "head");
        if (a3 != null && (a2 = com.a.a.b.c.a.a(a3, "bookmarks")) != null && (b2 = com.a.a.b.c.a.b(a2, "bookmark")) != null) {
            Iterator<Element> it = b2.iterator();
            while (it.hasNext()) {
                a((a) null, it.next());
            }
        }
    }

    private void a(a aVar, Element element) {
        a aVar2 = new a(element.getAttribute(Attribute.NAME_ATTR), element.getAttribute("href"));
        if (aVar == null) {
            this.x.add(aVar2);
        } else {
            aVar.a(aVar2);
        }
        List<Element> b2 = com.a.a.b.c.a.b(element, "bookmark");
        if (b2 != null) {
            Iterator<Element> it = b2.iterator();
            while (it.hasNext()) {
                a(aVar2, it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/t$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final String f1265a;

        /* renamed from: b, reason: collision with root package name */
        private final String f1266b;
        private List<a> c;

        public a(String str, String str2) {
            this.f1265a = str;
            this.f1266b = str2;
        }

        public final String a() {
            return this.f1266b;
        }

        public final String b() {
            return this.f1265a;
        }

        public final void a(a aVar) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(aVar);
        }

        public final List<a> c() {
            return this.c == null ? Collections.emptyList() : this.c;
        }
    }

    @Override // com.d.h.m
    public final String a(String str) {
        for (c cVar : this.y) {
            if (cVar != null && cVar.b().equalsIgnoreCase(str)) {
                return cVar.a();
            }
        }
        return null;
    }

    private void c(Document document) {
        Element a2;
        Element a3 = com.a.a.b.c.a.a(document.getDocumentElement(), "head");
        if (a3 != null) {
            List<Element> b2 = com.a.a.b.c.a.b(a3, "meta");
            if (b2 != null) {
                for (Element element : b2) {
                    String attribute = element.getAttribute(Attribute.NAME_ATTR);
                    if (attribute != null) {
                        this.y.add(new c(attribute, element.getAttribute("content")));
                    }
                }
            }
            if (a(Attribute.TITLE_ATTR) == null && (a2 = com.a.a.b.c.a.a(a3, Attribute.TITLE_ATTR)) != null) {
                this.y.add(new c(Attribute.TITLE_ATTR, com.a.a.b.c.a.a(a2).trim()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/t$c.class */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f1269a;

        /* renamed from: b, reason: collision with root package name */
        private String f1270b;

        c(String str, String str2) {
            this.f1269a = str;
            this.f1270b = str2;
        }

        public final String a() {
            return this.f1270b;
        }

        public final String b() {
            return this.f1269a;
        }
    }

    @Override // com.d.h.m
    public final List<c> n() {
        return this.y;
    }

    @Override // com.d.h.m
    public final void a(aa aaVar) {
        this.t = aaVar;
        aaVar.j().a(true);
    }

    @Override // com.d.h.m
    public final void a(com.d.i.f fVar) {
        this.A = fVar;
    }

    @Override // com.d.h.m
    public final int l() {
        return this.B;
    }

    @Override // com.d.h.m
    public final void a(int i) {
        this.B = i;
    }

    @Override // com.d.h.m
    public final void a(ab abVar) {
        this.D = abVar;
    }

    @Override // com.d.h.m
    public final void a(com.d.a.a aVar) {
        this.E = aVar;
    }

    @Override // com.d.d.m
    public final void b(List<AffineTransform> list) {
        Collections.reverse(list);
        for (AffineTransform affineTransform : list) {
            this.i.pop();
            this.e.a(affineTransform);
        }
    }

    @Override // com.d.d.m
    public final List<AffineTransform> a(List<AffineTransform> list) {
        if (list.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        try {
            for (AffineTransform affineTransform : list) {
                double[] dArr = new double[6];
                affineTransform.getMatrix(dArr);
                dArr[4] = dArr[4] / this.u;
                dArr[5] = dArr[5] / this.u;
                dArr[5] = -dArr[5];
                AffineTransform affineTransform2 = new AffineTransform(dArr);
                arrayList.add(affineTransform2.createInverse());
                this.i.push(affineTransform2);
                this.e.a(affineTransform2);
            }
        } catch (NoninvertibleTransformException unused) {
            com.d.m.l.h(Level.WARNING, "Tried to set a non-invertible CSS transform. Ignored.");
        }
        return arrayList;
    }

    private void t() {
        int i = 0;
        Iterator<AffineTransform> descendingIterator = this.i.descendingIterator();
        while (descendingIterator.hasNext()) {
            AffineTransform next = descendingIterator.next();
            if (i >= this.j) {
                this.e.a(next);
            }
            i++;
        }
    }

    @Override // com.d.d.m
    public final float a() {
        return 0.0f;
    }

    @Override // com.d.d.m
    public final float b() {
        return this.l;
    }

    @Override // com.d.i.a, com.d.d.m
    public final boolean f() {
        return true;
    }

    @Override // com.d.h.m
    public final void m() {
        if (this.F != null) {
            a.a.a.a.a aVar = this.F;
            throw null;
        }
    }

    private AffineTransform c(AffineTransform affineTransform) {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        dArr[4] = dArr[4] / this.u;
        dArr[5] = dArr[5] / this.u;
        return new AffineTransform(dArr);
    }

    @Override // com.d.d.m
    public final void a(AffineTransform affineTransform) {
        this.e.i();
        this.e.a(c(affineTransform));
    }

    @Override // com.d.d.m
    public final void g() {
        this.e.h();
        u();
    }

    @Override // com.d.d.m
    public final boolean i() {
        return this.D.d();
    }

    private void u() {
        this.n = null;
        this.o = null;
        this.r = null;
    }

    @Override // com.d.d.m
    public final Object a(com.d.d.q qVar, com.d.i.f fVar) {
        return null;
    }

    @Override // com.d.d.m
    public final void a(Object obj) {
    }
}
