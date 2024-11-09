package com.d.e;

import java.awt.Dimension;
import java.awt.Rectangle;

/* loaded from: infinitode-2.jar:com/d/e/y.class */
public final class y {

    /* renamed from: a, reason: collision with root package name */
    private Dimension f1170a;

    /* renamed from: b, reason: collision with root package name */
    private Rectangle f1171b;

    public final Rectangle a() {
        return this.f1171b;
    }

    public final void a(Rectangle rectangle) {
        this.f1171b = rectangle;
    }

    public final Dimension b() {
        return this.f1170a;
    }

    public final void a(Dimension dimension) {
        this.f1170a = dimension;
    }

    public final y c() {
        y yVar = new y();
        yVar.a(new Dimension(this.f1170a));
        yVar.a(new Rectangle(this.f1171b));
        return yVar;
    }
}
