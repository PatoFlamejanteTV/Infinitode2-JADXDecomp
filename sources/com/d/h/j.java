package com.d.h;

import com.d.e.aa;
import com.d.h.k;
import com.d.i.ab;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Map;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/j.class */
public final class j implements com.d.d.n, k.a {

    /* renamed from: a, reason: collision with root package name */
    private final com.d.d.c f1243a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f1244b;
    private Point c = new Point(0, 0);
    private final Map<Shape, String> d;

    public j(Element element, com.d.d.c cVar, aa aaVar, boolean z) {
        this.f1243a = cVar;
        this.f1244b = z;
        this.d = com.d.l.b.a(element, aaVar);
    }

    @Override // com.d.d.n
    public final int a() {
        return this.f1243a.a();
    }

    @Override // com.d.d.n
    public final int b() {
        return this.f1243a.b();
    }

    @Override // com.d.d.n
    public final Point c() {
        return this.c;
    }

    @Override // com.d.d.n
    public final void a(int i, int i2) {
        this.c = new Point(i, i2);
    }

    private com.d.d.c e() {
        return this.f1243a;
    }

    @Override // com.d.h.k.a
    public final Map<Shape, String> d() {
        return this.d;
    }

    @Override // com.d.d.n
    public final void a(com.d.e.v vVar) {
    }

    @Override // com.d.d.n
    public final void a(ab abVar, m mVar, com.d.i.c cVar) {
        Rectangle c = cVar.c(cVar.ab(), cVar.aa(), abVar);
        com.d.d.c e = ((j) cVar.E()).e();
        e.a(c.width, c.height);
        mVar.a(e, c.x, c.y, this.f1244b);
    }
}
