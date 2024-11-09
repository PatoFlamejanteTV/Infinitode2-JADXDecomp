package com.d.h;

import com.d.e.aa;
import com.d.h.h;
import com.d.h.w;
import com.d.i.ab;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.a.c.h.e.ae;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: infinitode-2.jar:com/d/h/o.class */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Element, h> f1253a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final List<h.b> f1254b = new ArrayList();
    private final Set<Element> c = new HashSet();
    private final Map<org.a.c.h.e.u, String> d = new HashMap();
    private final Map<h.a, org.a.c.h.g.b.q> e = new EnumMap(h.a.class);
    private org.a.c.h.g.b.q f;
    private org.a.c.h.g.b.q g;
    private org.a.c.h.g.b.q h;
    private org.a.c.h.j i;

    public final org.a.c.h.g.b.q a(h.a aVar) {
        return this.e.get(aVar);
    }

    public final org.a.c.h.g.b.q a() {
        return this.f;
    }

    public final org.a.c.h.g.b.q b() {
        return this.g;
    }

    public final org.a.c.h.g.b.q c() {
        return this.h;
    }

    public final void a(com.d.i.f fVar, m mVar) {
        if (!this.f1253a.containsKey(fVar.ai())) {
            this.f1253a.put(fVar.ai(), h.a(fVar.ai(), this, mVar));
        }
    }

    public final void a(com.d.i.f fVar, org.a.c.h.e eVar, AffineTransform affineTransform, ab abVar, float f) {
        if (!this.c.contains(fVar.ai())) {
            this.f1254b.add(new h.b(fVar, eVar, new AffineTransform(affineTransform), abVar, f));
            this.c.add(fVar.ai());
        }
    }

    private String a(aa aaVar, h.b bVar) {
        String str;
        org.a.c.h.e.u c = ((b) aaVar.a(bVar.f1235a.a().h())).b().get(0).c();
        if (!this.d.containsKey(c)) {
            str = "OpenHTMLFont" + this.d.size();
            this.d.put(c, str);
        } else {
            str = this.d.get(c);
        }
        return str;
    }

    private void a(org.a.c.h.b bVar, h.b bVar2) {
        h.a a2 = h.a.a(bVar2.f1235a.a().e(com.d.c.a.a.n));
        if (!this.e.containsKey(a2)) {
            this.e.put(a2, h.a(a2, bVar, this.i));
        }
        if (this.f == null) {
            this.f = h.a("q\nQ\n", bVar, this.i);
        }
    }

    private void a(org.a.c.h.b bVar) {
        if (this.g == null) {
            this.g = h.a("q\nQ\n", bVar, this.i);
        }
        if (this.h == null) {
            this.h = h.a(h.a.DIAMOND, bVar, this.i);
        }
    }

    private void d() {
        if (this.i == null) {
            this.i = new org.a.c.h.j();
            this.i.a(org.a.c.b.j.a("OpenHTMLZap"), ae.r);
        }
    }

    public final void a(aa aaVar, org.a.c.h.b bVar, com.d.i.f fVar) {
        for (h.b bVar2 : this.f1254b) {
            h a2 = a(bVar2.f1235a.ai());
            String str = null;
            if (!com.d.m.a.a(bVar2.f1235a.ai().getAttribute("type"), "checkbox", "radio", "hidden")) {
                str = a(aaVar, bVar2);
            } else if (bVar2.f1235a.ai().getAttribute("type").equals("checkbox")) {
                d();
                a(bVar, bVar2);
            } else if (bVar2.f1235a.ai().getAttribute("type").equals("radio")) {
                d();
                a(bVar);
            }
            if (a2 != null) {
                a2.a(bVar2, str);
            }
        }
        org.a.c.h.j jVar = new org.a.c.h.j();
        for (Map.Entry<org.a.c.h.e.u, String> entry : this.d.entrySet()) {
            jVar.a(org.a.c.b.j.a(entry.getValue()), entry.getKey());
        }
        if (this.f1253a.size() != 0) {
            int i = 0;
            org.a.c.h.g.e.d dVar = new org.a.c.h.g.e.d(bVar);
            dVar.a(Boolean.TRUE);
            dVar.a(jVar);
            bVar.c().a(dVar);
            Iterator<h> it = this.f1253a.values().iterator();
            while (it.hasNext()) {
                try {
                    i = 1 + it.next().a(dVar, i, fVar);
                } catch (IOException e) {
                    throw new w.a("processControls", (Exception) e);
                }
            }
        }
    }

    private h a(Node node) {
        Element a2 = com.a.a.b.c.a.a(node, "form");
        if (a2 != null && this.f1253a.containsKey(a2)) {
            return this.f1253a.get(a2);
        }
        com.d.m.l.d(Level.WARNING, "Found form control (" + node.getNodeName() + ") with no enclosing form. Ignoring.");
        return null;
    }
}
