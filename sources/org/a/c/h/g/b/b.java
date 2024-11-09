package org.a.c.h.g.b;

import com.badlogic.gdx.net.HttpResponseHeader;
import java.io.IOException;

/* loaded from: infinitode-2.jar:org/a/c/h/g/b/b.class */
public abstract class b implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4608a = org.a.a.a.c.a(b.class);

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.d f4609b;

    public static b a(org.a.c.b.b bVar) {
        b iVar;
        if (bVar instanceof org.a.c.b.d) {
            org.a.c.b.d dVar = (org.a.c.b.d) bVar;
            String g = dVar.g(org.a.c.b.j.dE);
            if ("FileAttachment".equals(g)) {
                iVar = new c(dVar);
            } else if ("Line".equals(g)) {
                iVar = new d(dVar);
            } else if (HttpResponseHeader.Link.equals(g)) {
                iVar = new e(dVar);
            } else if ("Popup".equals(g)) {
                iVar = new g(dVar);
            } else if ("Stamp".equals(g)) {
                iVar = new h(dVar);
            } else if ("Square".equals(g) || "Circle".equals(g)) {
                iVar = new i(dVar);
            } else if ("Text".equals(g)) {
                iVar = new j(dVar);
            } else if ("Highlight".equals(g) || "Underline".equals(g) || "Squiggly".equals(g) || "StrikeOut".equals(g)) {
                iVar = new k(dVar);
            } else if ("Widget".equals(g)) {
                iVar = new m(dVar);
            } else if ("FreeText".equals(g) || "Polygon".equals(g) || "PolyLine".equals(g) || "Caret".equals(g) || "Ink".equals(g) || "Sound".equals(g)) {
                iVar = new f(dVar);
            } else {
                iVar = new l(dVar);
                new StringBuilder("Unknown or unsupported annotation subtype ").append(g);
            }
            return iVar;
        }
        throw new IOException("Error: Unknown annotation type " + bVar);
    }

    public b() {
        this.f4609b = new org.a.c.b.d();
        this.f4609b.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.h);
    }

    public b(org.a.c.b.d dVar) {
        this.f4609b = dVar;
        this.f4609b.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.h);
    }

    public final org.a.c.h.a.h a() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4609b.a(org.a.c.b.j.dd);
        org.a.c.h.a.h hVar = null;
        if (aVar != null) {
            if (aVar.b() == 4 && (aVar.a(0) instanceof org.a.c.b.l) && (aVar.a(1) instanceof org.a.c.b.l) && (aVar.a(2) instanceof org.a.c.b.l) && (aVar.a(3) instanceof org.a.c.b.l)) {
                hVar = new org.a.c.h.a.h(aVar);
            } else {
                new StringBuilder().append(aVar).append(" is not a rectangle array, returning null");
            }
        }
        return hVar;
    }

    public final void a(org.a.c.h.a.h hVar) {
        this.f4609b.a(org.a.c.b.j.dd, (org.a.c.b.b) hVar.b());
    }

    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4609b;
    }

    private org.a.c.b.j h() {
        return f().b(org.a.c.b.j.l);
    }

    public final void a(String str) {
        f().a(org.a.c.b.j.l, str);
    }

    public final o c() {
        org.a.c.b.b a2 = this.f4609b.a(org.a.c.b.j.j);
        if (a2 instanceof org.a.c.b.d) {
            return new o((org.a.c.b.d) a2);
        }
        return null;
    }

    public final void a(o oVar) {
        this.f4609b.a(org.a.c.b.j.j, oVar);
    }

    public final q d() {
        p b2;
        o c = c();
        if (c == null || (b2 = c.b()) == null) {
            return null;
        }
        if (b2.a()) {
            return b2.d().get(h());
        }
        return b2.c();
    }

    public final boolean e() {
        return f().c(org.a.c.b.j.aU, 2);
    }

    public final void a(boolean z) {
        f().a(org.a.c.b.j.aU, 2, true);
    }

    public final void b(boolean z) {
        f().a(org.a.c.b.j.aU, 4, true);
    }

    public final boolean g() {
        return f().c(org.a.c.b.j.aU, 32);
    }

    public final void a(org.a.c.h.e eVar) {
        f().a(org.a.c.b.j.cJ, eVar);
    }
}
