package com.d.e;

import com.a.a.c.f.w;
import com.d.e.ad;
import java.awt.Rectangle;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: infinitode-2.jar:com/d/e/aa.class */
public final class aa {
    private com.d.d.r c;
    private String d;
    private com.d.d.s e;
    private Map<String, com.d.i.f> g;
    private float i;
    private boolean j;
    private Map<Element, com.d.c.f.c> k;
    private com.d.d.o l;
    private Rectangle m;
    private com.d.d.k n;
    private a o;
    private w.a p;
    private com.d.d.l q;
    private Float r;
    private Float s;
    private boolean t;
    private boolean f = true;
    private int h = 1;
    private String u = "#";
    private com.d.d.g v = new ae(BreakIterator.getLineInstance(Locale.US));
    private com.d.d.g w = new ad.a(BreakIterator.getCharacterInstance(Locale.US));
    private com.d.d.h x = new ad.b(Locale.US);
    private com.d.d.h y = new ad.d(Locale.US);
    private com.d.d.h z = new ad.c();

    /* renamed from: a, reason: collision with root package name */
    public String f1106a = null;

    /* renamed from: b, reason: collision with root package name */
    public String f1107b = null;

    public final v a() {
        return new v(this);
    }

    public final com.d.i.ab b() {
        return new com.d.i.ab(this);
    }

    public final com.d.d.k c() {
        return this.n;
    }

    public final String d() {
        return this.d;
    }

    public final com.d.d.r e() {
        return this.c;
    }

    public final boolean f() {
        return false;
    }

    public final boolean g() {
        return false;
    }

    public final boolean h() {
        return false;
    }

    public final boolean i() {
        return false;
    }

    public final a j() {
        return this.o;
    }

    public final void a(a aVar) {
        this.o = aVar;
    }

    private w.a F() {
        return this.p;
    }

    public final Rectangle k() {
        if (F() == null) {
            return this.m;
        }
        Rectangle t = F().t();
        t.translate(F().u(), F().v());
        return t;
    }

    public final void a(com.d.d.l lVar) {
        this.q = lVar;
    }

    public final com.d.d.l l() {
        return this.q;
    }

    public final void a(String str, com.d.i.f fVar) {
        if (this.g == null) {
            this.g = new HashMap();
        }
        this.g.put(str, fVar);
    }

    public final com.d.i.f a(String str) {
        if (this.g == null) {
            this.g = new HashMap();
        }
        return this.g.get(str);
    }

    public final void b(String str) {
        if (this.g != null) {
            this.g.remove(str);
        }
    }

    public final void a(com.d.d.r rVar) {
        this.c = rVar;
    }

    private void e(String str) {
        this.d = str;
    }

    @Deprecated
    public final com.d.d.s m() {
        return this.e;
    }

    public final com.d.d.s n() {
        return this.e;
    }

    public final void a(com.d.d.s sVar) {
        a j = j();
        if (j != null) {
            j.a(sVar);
        }
        this.e = sVar;
    }

    public final void a(float f) {
        this.i = 25.4f / f;
    }

    public final float o() {
        return this.i;
    }

    public final com.d.i.k a(com.d.c.g.a aVar) {
        return c().a(this, aVar);
    }

    public final float a(com.d.d.j jVar, com.d.c.g.a aVar) {
        com.d.i.l a2 = e().a(c().a(this, aVar));
        return (a2.a() - (2.0f * Math.abs(a2.c()))) + a2.d();
    }

    public final String p() {
        return this.e.a();
    }

    public final void c(String str) {
        this.e.e(str);
    }

    public final boolean q() {
        return this.f;
    }

    public final void a(boolean z) {
        this.f = false;
    }

    public final boolean r() {
        return this.j;
    }

    public final void b(boolean z) {
        this.j = true;
        e("print");
    }

    public final void a(com.d.d.k kVar) {
        this.n = kVar;
    }

    public final int s() {
        return this.h;
    }

    public final void a(int i) {
        this.h = 20;
    }

    public final com.d.c.f.c a(Element element) {
        return a(element, false);
    }

    private com.d.c.f.c a(Element element, boolean z) {
        com.d.c.f.c a2;
        if (this.k == null) {
            this.k = new HashMap(1024, 0.75f);
        }
        com.d.c.f.c cVar = this.k.get(element);
        com.d.c.f.c cVar2 = cVar;
        if (cVar == null) {
            Node parentNode = element.getParentNode();
            if (parentNode instanceof Document) {
                a2 = new com.d.c.f.f();
            } else {
                a2 = a((Element) parentNode, false);
            }
            cVar2 = a2.a(j().a(element, false));
            this.k.put(element, cVar2);
        }
        return cVar2;
    }

    public final com.d.d.o t() {
        return this.l;
    }

    public final void a(com.d.d.o oVar) {
        this.l = oVar;
    }

    public final Float u() {
        return this.s;
    }

    public final Float v() {
        return this.r;
    }

    public final boolean w() {
        return this.t;
    }

    public final String x() {
        return this.u;
    }

    public final void d(String str) {
        this.u = str;
    }

    public final void a(Float f, Float f2, boolean z) {
        this.s = f;
        this.r = f2;
        this.t = z;
    }

    public final com.d.d.g y() {
        return this.v;
    }

    public final void a(com.d.d.g gVar) {
        this.v = gVar;
    }

    public final com.d.d.g z() {
        return this.w;
    }

    public final void b(com.d.d.g gVar) {
        this.w = gVar;
    }

    public final void A() {
        com.d.m.i.a().a(this);
    }

    public static void B() {
        com.d.m.i.a().a(null);
    }

    public final com.d.d.h C() {
        return this.x;
    }

    public final com.d.d.h D() {
        return this.y;
    }

    public final com.d.d.h E() {
        return this.z;
    }

    public final void a(com.d.d.h hVar) {
        this.x = hVar;
    }

    public final void b(com.d.d.h hVar) {
        this.y = hVar;
    }

    public final void c(com.d.d.h hVar) {
        this.z = hVar;
    }
}
