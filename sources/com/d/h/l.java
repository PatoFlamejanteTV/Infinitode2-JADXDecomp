package com.d.h;

import com.a.a.c.f.w;
import com.d.e.aa;
import com.d.h.k;
import com.d.i.ab;
import java.awt.Point;
import java.awt.Shape;
import java.util.Map;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/l.class */
public final class l implements com.d.d.n, k.a {

    /* renamed from: a, reason: collision with root package name */
    private final Element f1249a;

    /* renamed from: b, reason: collision with root package name */
    private Point f1250b = new Point(0, 0);
    private final w.a c;
    private final int d;
    private final int e;
    private final int f;
    private Map<Shape, String> g;

    public l(Element element, w.a aVar, int i, int i2, aa aaVar) {
        this.f1249a = element;
        this.g = com.d.l.b.a(element, aaVar);
        this.c = aVar;
        this.d = i;
        this.e = i2;
        this.f = aaVar.s();
    }

    @Override // com.d.d.n
    public final int a() {
        return this.d;
    }

    @Override // com.d.d.n
    public final int b() {
        return this.e;
    }

    @Override // com.d.d.n
    public final Point c() {
        return this.f1250b;
    }

    @Override // com.d.d.n
    public final void a(int i, int i2) {
        this.f1250b.setLocation(i, i2);
    }

    @Override // com.d.d.n
    public final void a(com.d.e.v vVar) {
    }

    @Override // com.d.d.n
    public final void a(ab abVar, m mVar, com.d.i.c cVar) {
        w.a aVar = this.c;
        this.f1250b.getX();
        this.f1250b.getY();
        a();
        b();
        Map<Shape, String> w = aVar.w();
        if (w != null) {
            this.g = w;
        }
    }

    @Override // com.d.h.k.a
    public final Map<Shape, String> d() {
        return this.g;
    }
}
