package com.d.h.a;

import java.awt.geom.Point2D;

/* loaded from: infinitode-2.jar:com/d/h/a/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final Point2D.Float f1214a;

    /* renamed from: b, reason: collision with root package name */
    public final Point2D.Float f1215b;
    public final Point2D.Float c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Point2D.Float r4, Point2D.Float r5, Point2D.Float r6) {
        this.f1214a = r4;
        this.f1215b = r5;
        this.c = r6;
    }

    public static boolean a(Point2D.Float r6, Point2D.Float r7, Point2D.Float r8, Point2D.Float r9) {
        Point2D.Float r0 = new Point2D.Float(r7.x - r6.x, r7.y - r6.y);
        Point2D.Float r02 = new Point2D.Float(r8.x - r6.x, r8.y - r6.y);
        double d = (r0.x * r02.y) - (r02.x * r0.y);
        Point2D.Float r03 = new Point2D.Float(r9.x - r6.x, r9.y - r6.y);
        double d2 = ((r03.x * r02.y) - (r02.x * r03.y)) / d;
        double d3 = ((r0.x * r03.y) - (r03.x * r0.y)) / d;
        return d2 > 0.0d && d3 > 0.0d && d2 + d3 < 1.0d;
    }
}
