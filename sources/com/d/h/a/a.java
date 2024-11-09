package com.d.h.a;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/h/a/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f1212a = false;

    /* renamed from: b, reason: collision with root package name */
    private List<Point2D.Float> f1213b = new ArrayList();
    private List<Point2D.Float> c;
    private List<b> d;
    private boolean e;

    public a(List<Point2D.Float> list) {
        for (Point2D.Float r0 : list) {
            this.f1213b.add(new Point2D.Float(r0.x, r0.y));
        }
        this.c = new ArrayList();
        this.d = new ArrayList();
        d();
        c();
    }

    private void c() {
        Point2D.Float r11;
        if (this.f1213b.size() <= 3) {
            if (this.f1213b.size() == 3) {
                this.d.add(new b(this.f1213b.get(0), this.f1213b.get(1), this.f1213b.get(2)));
                return;
            }
            return;
        }
        for (int i = 0; i < this.f1213b.size() - 1; i++) {
            Point2D.Float r0 = this.f1213b.get(i);
            Point2D.Float r02 = this.f1213b.get(i + 1);
            Point2D.Float r03 = new Point2D.Float();
            r03.x = r02.x - r0.x;
            r03.y = r02.y - r0.y;
            if (i == this.f1213b.size() - 2) {
                r11 = this.f1213b.get(0);
            } else {
                r11 = this.f1213b.get(i + 2);
            }
            float f = (((r11.x * r03.y) - (r11.y * r03.x)) + (r03.x * r0.y)) - (r03.y * r0.x);
            if ((f > 0.0f && this.e) || (f <= 0.0f && !this.e)) {
                this.c.add(r02);
            }
        }
    }

    private void d() {
        Point2D.Float r9;
        Point2D.Float r7;
        if (this.f1213b.size() < 3) {
            return;
        }
        int i = 0;
        Point2D.Float r8 = this.f1213b.get(0);
        for (int i2 = 1; i2 < this.f1213b.size(); i2++) {
            if (this.f1213b.get(i2).x < r8.x) {
                r8 = this.f1213b.get(i2);
                i = i2;
            } else if (this.f1213b.get(i2).x == r8.x && this.f1213b.get(i2).y > r8.y) {
                r8 = this.f1213b.get(i2);
                i = i2;
            }
        }
        if (i == 0) {
            r9 = this.f1213b.get(this.f1213b.size() - 1);
        } else {
            r9 = this.f1213b.get(i - 1);
        }
        Point2D.Float r0 = new Point2D.Float(r8.x - r9.x, r8.y - r9.y);
        if (i == this.f1213b.size() - 1) {
            r7 = this.f1213b.get(0);
        } else {
            r7 = this.f1213b.get(i + 1);
        }
        this.e = (((r7.x * r0.y) - (r7.y * r0.x)) + (r0.x * r9.y)) - (r0.y * r9.x) <= 0.0f;
    }

    private boolean a(Point2D.Float r6, Point2D.Float r7, Point2D.Float r8) {
        if (!b(r6, r7, r8)) {
            return false;
        }
        Iterator<Point2D.Float> it = this.c.iterator();
        while (it.hasNext()) {
            if (b.a(r6, r7, r8, it.next())) {
                return false;
            }
        }
        return true;
    }

    private boolean b(Point2D.Float r7, Point2D.Float r8, Point2D.Float r9) {
        Point2D.Float r0 = new Point2D.Float(r8.x - r7.x, r8.y - r7.y);
        float f = (((r9.x * r0.y) - (r9.y * r0.x)) + (r0.x * r7.y)) - (r0.y * r7.x);
        if (f <= 0.0f || !this.e) {
            return f > 0.0f || this.e;
        }
        return false;
    }

    private int a(int i, int i2) {
        int i3;
        if (i + i2 >= this.f1213b.size()) {
            i3 = this.f1213b.size() - (i + i2);
        } else if (i + i2 < 0) {
            i3 = this.f1213b.size() + i + i2;
        } else {
            i3 = i + i2;
        }
        return i3;
    }

    public final void a() {
        if (this.f1213b.size() <= 3) {
            return;
        }
        this.d.clear();
        int i = 1;
        while (true) {
            int i2 = i;
            if (this.f1213b.size() > 3) {
                if (a(this.f1213b.get(a(i2, -1)), this.f1213b.get(i2), this.f1213b.get(a(i2, 1)))) {
                    this.d.add(new b(this.f1213b.get(a(i2, -1)), this.f1213b.get(i2), this.f1213b.get(a(i2, 1))));
                    this.f1213b.remove(this.f1213b.get(i2));
                    i = a(i2, -1);
                } else {
                    i = a(i2, 1);
                }
            } else {
                this.d.add(new b(this.f1213b.get(0), this.f1213b.get(1), this.f1213b.get(2)));
                return;
            }
        }
    }

    public final List<b> b() {
        return this.d;
    }
}
