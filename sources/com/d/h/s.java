package com.d.h;

import com.d.d.p;
import com.d.e.aa;
import com.d.h.k;
import com.d.i.ab;
import java.awt.Point;
import java.awt.Shape;
import java.util.Map;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/s.class */
public final class s implements com.d.d.n, k.a {

    /* renamed from: a, reason: collision with root package name */
    private final p.a f1261a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<Shape, String> f1262b;
    private Point c = new Point(0, 0);

    public s(Element element, com.d.d.p pVar, int i, int i2, com.d.i.f fVar, com.d.c.f.d dVar, aa aaVar) {
        aaVar.s();
        this.f1261a = pVar.a();
        this.f1262b = com.d.l.b.a(element, aaVar);
    }

    @Override // com.d.d.n
    public final int a() {
        return this.f1261a.a();
    }

    @Override // com.d.d.n
    public final int b() {
        return this.f1261a.b();
    }

    @Override // com.d.d.n
    public final Point c() {
        return this.c;
    }

    @Override // com.d.d.n
    public final void a(int i, int i2) {
        this.c.setLocation(i, i2);
    }

    @Override // com.d.d.n
    public final void a(com.d.e.v vVar) {
    }

    @Override // com.d.d.n
    public final void a(ab abVar, m mVar, com.d.i.c cVar) {
        this.c.getX();
        this.c.getY();
    }

    @Override // com.d.h.k.a
    public final Map<Shape, String> d() {
        return this.f1262b;
    }
}
