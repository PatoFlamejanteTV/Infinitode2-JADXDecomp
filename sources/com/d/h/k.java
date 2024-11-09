package com.d.h;

import com.d.e.aa;
import com.d.h.w;
import com.d.i.ab;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Element;

@Deprecated
/* loaded from: infinitode-2.jar:com/d/h/k.class */
public final class k {

    /* renamed from: b, reason: collision with root package name */
    private final aa f1246b;
    private final float c;
    private final com.d.i.f d;
    private final m e;

    /* renamed from: a, reason: collision with root package name */
    private final Map<org.a.c.h.e, Set<String>> f1245a = new HashMap();
    private final List<b> f = new ArrayList();

    /* loaded from: infinitode-2.jar:com/d/h/k$a.class */
    public interface a {
        Map<Shape, String> d();
    }

    /* loaded from: infinitode-2.jar:com/d/h/k$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        ab f1247a;

        /* renamed from: b, reason: collision with root package name */
        com.d.i.f f1248b;
        org.a.c.h.e c;
        float d;
        AffineTransform e;
    }

    public k(aa aaVar, float f, com.d.i.f fVar, m mVar) {
        this.f1246b = aaVar;
        this.c = f;
        this.d = fVar;
        this.e = mVar;
    }

    private Rectangle2D a(ab abVar, com.d.i.f fVar, float f, AffineTransform affineTransform) {
        com.d.i.f fVar2;
        com.d.i.f fVar3 = fVar;
        while (true) {
            fVar2 = fVar3;
            com.d.i.f S = fVar2.S();
            if (S == null || S.ai() != fVar.ai()) {
                break;
            }
            fVar3 = S;
        }
        Rectangle2D a2 = a(abVar, fVar2, f, affineTransform, this.e);
        com.d.i.f T = fVar2.T();
        while (true) {
            com.d.i.f fVar4 = T;
            if (fVar4 == null || fVar4.ai() != fVar.ai()) {
                break;
            }
            a2 = a(a2, a(abVar, fVar4, f, affineTransform, this.e));
            T = fVar4.T();
        }
        return a2;
    }

    private static Rectangle2D a(Rectangle2D rectangle2D, Rectangle2D rectangle2D2) {
        return rectangle2D.createUnion(rectangle2D2);
    }

    private static String a(Rectangle2D rectangle2D, Shape shape, AffineTransform affineTransform) {
        StringBuilder sb = new StringBuilder(rectangle2D.getMinX() + ":" + rectangle2D.getMaxY() + ":" + rectangle2D.getMaxX() + ":" + rectangle2D.getMinY());
        if (shape != null) {
            PathIterator pathIterator = shape.getPathIterator(affineTransform);
            double[] dArr = new double[6];
            while (!pathIterator.isDone()) {
                switch (pathIterator.currentSegment(dArr)) {
                    case 0:
                        sb.append("M");
                        sb.append(dArr[0]).append(":").append(dArr[1]).append(":");
                        break;
                    case 1:
                        sb.append("L");
                        sb.append(dArr[0]).append(":").append(dArr[1]).append(":");
                        break;
                    case 2:
                        sb.append("Q");
                        sb.append(dArr[0]).append(":").append(dArr[1]).append(":").append(dArr[2]).append(":").append(dArr[3]);
                        break;
                    case 3:
                        sb.append("C");
                        sb.append(dArr[0]).append(":").append(dArr[1]).append(":").append(dArr[2]).append(":").append(dArr[3]).append(":").append(dArr[4]).append(":").append(dArr[5]);
                        break;
                    case 4:
                        sb.append("cp");
                        break;
                }
                pathIterator.next();
            }
        }
        return sb.toString();
    }

    private Rectangle2D a(org.a.c.h.e eVar, ab abVar, com.d.i.f fVar, float f, AffineTransform affineTransform, Shape shape) {
        Rectangle2D a2 = a(abVar, fVar, f, affineTransform);
        String a3 = a(a2, shape, affineTransform);
        Set<String> set = this.f1245a.get(eVar);
        Set<String> set2 = set;
        if (set == null) {
            set2 = new HashSet();
            this.f1245a.put(eVar, set2);
        }
        if (set2.contains(a3)) {
            return null;
        }
        set2.add(a3);
        return a2;
    }

    private void b(ab abVar, com.d.i.f fVar, org.a.c.h.e eVar, float f, AffineTransform affineTransform) {
        Map<Shape, String> d;
        com.d.d.l l;
        String f2;
        Element ai = fVar.ai();
        if (ai != null && (f2 = (l = this.f1246b.l()).f(ai)) != null) {
            a(abVar, fVar, eVar, f, affineTransform, ai, l, f2, null);
        }
        if (fVar instanceof com.d.i.c) {
            com.d.d.n E = ((com.d.i.c) fVar).E();
            if ((E instanceof a) && (d = ((a) E).d()) != null) {
                for (Map.Entry<Shape, String> entry : d.entrySet()) {
                    a(abVar, fVar, eVar, f, affineTransform, ai, this.f1246b.l(), entry.getValue(), entry.getKey());
                }
            }
        }
    }

    private static boolean a(Point2D.Float r5, Point2D.Float r6) {
        return ((double) Math.abs(r5.x - r6.x)) < 1.0E-6d && ((double) Math.abs(r5.y - r6.y)) < 1.0E-6d;
    }

    private static void a(List<Point2D.Float> list) {
        boolean z;
        do {
            z = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (a(list.get(i), list.get(i + 1))) {
                    list.remove(i);
                    z = true;
                }
            }
            for (int i2 = 0; i2 < list.size() - 2; i2++) {
                if (a(list.get(i2), list.get(i2 + 2))) {
                    list.remove(i2);
                    z = true;
                }
            }
        } while (z);
    }

    private void a(ab abVar, com.d.i.f fVar, org.a.c.h.e eVar, float f, AffineTransform affineTransform, Element element, com.d.d.l lVar, String str, Shape shape) {
        org.a.c.h.g.a.a aVar;
        if (str.length() <= 1 || str.charAt(0) != '#') {
            if (str.contains("://")) {
                org.a.c.h.g.a.o oVar = new org.a.c.h.g.a.o();
                oVar.b(str);
                Rectangle2D a2 = a(eVar, abVar, fVar, f, affineTransform, shape);
                if (a2 == null) {
                    return;
                }
                org.a.c.h.g.b.e eVar2 = new org.a.c.h.g.b.e();
                eVar2.a(oVar);
                if (!a(affineTransform, shape, a2, eVar2)) {
                    return;
                }
                a(eVar, eVar2);
                return;
            }
            return;
        }
        com.d.i.f a3 = this.f1246b.a(str.substring(1));
        if (a3 != null) {
            org.a.c.h.g.d.a.d a4 = a(abVar, a3);
            if (lVar.a(element, "onclick") != null && !"".equals(lVar.a(element, "onclick"))) {
                aVar = new org.a.c.h.g.a.f(lVar.a(element, "onclick"));
            } else {
                org.a.c.h.g.a.c cVar = new org.a.c.h.g.a.c();
                cVar.a(a4);
                aVar = cVar;
            }
            Rectangle2D a5 = a(eVar, abVar, fVar, f, affineTransform, shape);
            if (a5 == null) {
                return;
            }
            org.a.c.h.g.b.e eVar3 = new org.a.c.h.g.b.e();
            eVar3.a(aVar);
            if (!a(affineTransform, shape, a5, eVar3)) {
                return;
            }
            a(eVar, eVar3);
        }
    }

    private boolean a(AffineTransform affineTransform, Shape shape, Rectangle2D rectangle2D, org.a.c.h.g.b.e eVar) {
        eVar.a(new org.a.c.h.a.h((float) rectangle2D.getMinX(), (float) rectangle2D.getMinY(), (float) rectangle2D.getWidth(), (float) rectangle2D.getHeight()));
        if (shape != null) {
            float[] a2 = a(affineTransform, shape, rectangle2D);
            if (a2.length == 0) {
                return false;
            }
            eVar.a(a2);
            return true;
        }
        return true;
    }

    private static float[] a(AffineTransform affineTransform, Shape shape, Rectangle2D rectangle2D) {
        ArrayList arrayList = new ArrayList();
        AffineTransform affineTransform2 = new AffineTransform();
        affineTransform2.translate(rectangle2D.getMinX(), rectangle2D.getMinY());
        affineTransform2.translate(0.0d, rectangle2D.getHeight());
        affineTransform2.scale(1.0d, -1.0d);
        affineTransform2.concatenate(affineTransform);
        PathIterator pathIterator = new Area(shape).getPathIterator(affineTransform2, 1.0d);
        double[] dArr = new double[6];
        while (!pathIterator.isDone()) {
            switch (pathIterator.currentSegment(dArr)) {
                case 0:
                    arrayList.add(new Point2D.Float((float) dArr[0], (float) dArr[1]));
                    break;
                case 1:
                    arrayList.add(new Point2D.Float((float) dArr[0], (float) dArr[1]));
                    break;
                case 2:
                    throw new RuntimeException("Invalid State, Area should never give us a curve here!");
                case 3:
                    throw new RuntimeException("Invalid State, Area should never give us a curve here!");
            }
            pathIterator.next();
        }
        a(arrayList);
        com.d.h.a.a aVar = new com.d.h.a.a(arrayList);
        aVar.a();
        float[] fArr = new float[aVar.b().size() << 3];
        int i = 0;
        for (com.d.h.a.b bVar : aVar.b()) {
            int i2 = i;
            int i3 = i + 1;
            fArr[i2] = bVar.f1214a.x;
            int i4 = i3 + 1;
            fArr[i3] = bVar.f1214a.y;
            int i5 = i4 + 1;
            fArr[i4] = bVar.f1215b.x;
            int i6 = i5 + 1;
            fArr[i5] = bVar.f1215b.y;
            int i7 = i6 + 1;
            fArr[i6] = bVar.f1215b.x + ((bVar.c.x - bVar.f1215b.x) / 2.0f);
            int i8 = i7 + 1;
            fArr[i7] = bVar.f1215b.y + ((bVar.c.y - bVar.f1215b.y) / 2.0f);
            int i9 = i8 + 1;
            fArr[i8] = bVar.c.x;
            i = i9 + 1;
            fArr[i9] = bVar.c.y;
        }
        if (fArr.length % 8 != 0) {
            throw new IllegalStateException("Not exact 8xn QuadPoints!");
        }
        while (i < fArr.length) {
            if (fArr[i] < rectangle2D.getMinX() || fArr[i] > rectangle2D.getMaxX()) {
                throw new IllegalStateException("Invalid rectangle calculation. Map shape is out of bound.");
            }
            if (fArr[i + 1] >= rectangle2D.getMinY() && fArr[i + 1] <= rectangle2D.getMaxY()) {
                i += 2;
            } else {
                throw new IllegalStateException("Invalid rectangle calculation. Map shape is out of bound.");
            }
        }
        return fArr;
    }

    private static void a(org.a.c.h.e eVar, org.a.c.h.g.b.e eVar2) {
        org.a.c.h.g.b.r rVar = new org.a.c.h.g.b.r();
        rVar.a(0.0f);
        rVar.a("S");
        eVar2.a(rVar);
        try {
            List<org.a.c.h.g.b.b> i = eVar.i();
            List<org.a.c.h.g.b.b> list = i;
            if (i == null) {
                list = new ArrayList();
                eVar.a(list);
            }
            list.add(eVar2);
        } catch (IOException e) {
            throw new w.a("processLink", (Exception) e);
        }
    }

    private org.a.c.h.g.d.a.d a(ab abVar, com.d.i.f fVar) {
        org.a.c.h.g.d.a.d dVar = new org.a.c.h.g.d.a.d();
        com.d.i.aa a2 = this.d.Z().a((com.d.c.f.d) abVar, this.e.b(fVar));
        dVar.a((int) ((a2.b(abVar) / this.c) - (((int) (a2.d(abVar, 3) + ((fVar.aa() + fVar.n(abVar).t()) - a2.b()))) / this.c)));
        dVar.a(this.e.j().a(this.e.l() + a2.i()));
        return dVar;
    }

    public static Rectangle2D a(ab abVar, com.d.i.f fVar, float f, AffineTransform affineTransform, m mVar) {
        Point2D transform = affineTransform.transform(new Point2D.Float(r0.x, (float) fVar.c(fVar.ab(), fVar.aa(), abVar).getMaxY()), (Point2D) null);
        return new Rectangle2D.Float((float) transform.getX(), mVar.a((float) transform.getY(), f), mVar.a(r0.width), mVar.a(r0.height));
    }

    public final void a(ab abVar, com.d.i.f fVar, org.a.c.h.e eVar, float f, AffineTransform affineTransform) {
        if (((fVar instanceof com.d.i.c) && ((com.d.i.c) fVar).E() != null) || (fVar.ai() != null && fVar.ai().getNodeName().equals(FlexmarkHtmlConverter.A_NODE))) {
            b bVar = new b();
            bVar.f1247a = abVar;
            bVar.f1248b = fVar;
            bVar.c = eVar;
            bVar.d = f;
            bVar.e = (AffineTransform) affineTransform.clone();
            this.f.add(bVar);
        }
    }

    public final void a() {
        for (b bVar : this.f) {
            b(bVar.f1247a, bVar.f1248b, bVar.c, bVar.d, bVar.e);
        }
    }
}
