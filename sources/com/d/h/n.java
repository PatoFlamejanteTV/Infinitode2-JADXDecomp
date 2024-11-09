package com.d.h;

import com.d.e.aa;
import com.d.h.k;
import com.d.i.ab;
import java.awt.Point;
import java.awt.Shape;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/n.class */
public final class n implements com.d.d.n, k.a {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.f.b.a f1251a;

    /* renamed from: b, reason: collision with root package name */
    private final float f1252b;
    private final float c;
    private final Map<Shape, String> d;
    private Point e = new Point(0, 0);

    private n(org.a.c.h.f.b.a aVar, Element element, aa aaVar, float f, float f2) {
        this.f1251a = aVar;
        this.f1252b = f;
        this.c = f2;
        this.d = com.d.l.b.a(element, aaVar);
    }

    private static int a(Element element) {
        if (element.getAttribute("page").isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(element.getAttribute("page")) - 1;
        } catch (NumberFormatException e) {
            com.d.m.l.a("Unable to parse page of img tag with PDF!", e);
            return 0;
        }
    }

    public static n a(org.a.c.h.b bVar, byte[] bArr, Element element, com.d.i.f fVar, com.d.c.f.d dVar, aa aaVar) {
        try {
            org.a.c.h.b a2 = org.a.c.h.b.a(bArr);
            try {
                int a3 = a(element);
                if (a3 >= a2.f()) {
                    com.d.m.l.e(Level.WARNING, "Page does not exist for pdf in img tag. Ignoring!");
                    if (a2 != null) {
                        a2.close();
                    }
                    return null;
                }
                org.a.c.h.e a4 = a2.a(a3);
                n nVar = new n(new org.a.c.e.a(bVar).a(a2, a4), element, aaVar, a4.e().h() * aaVar.s() * 1.3333334f, a4.e().i() * aaVar.s() * 1.3333334f);
                if (a2 != null) {
                    a2.close();
                }
                return nVar;
            } catch (Throwable th) {
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (org.a.c.h.c.b e) {
            com.d.m.l.a("Tried to open a password protected document as src for an img!", e);
            return null;
        } catch (IOException e2) {
            com.d.m.l.a("Could not read pdf passed as src for img element!", e2);
            return null;
        }
    }

    @Override // com.d.d.n
    public final int a() {
        return (int) this.f1252b;
    }

    @Override // com.d.d.n
    public final int b() {
        return (int) this.c;
    }

    @Override // com.d.d.n
    public final Point c() {
        return this.e;
    }

    @Override // com.d.d.n
    public final void a(int i, int i2) {
        this.e = new Point(i, i2);
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
        mVar.a(this.f1251a, cVar.c(cVar.ab(), cVar.aa(), abVar), a(), b());
    }
}
