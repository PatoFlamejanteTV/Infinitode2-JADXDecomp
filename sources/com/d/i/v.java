package com.d.i;

import com.d.i.x;
import java.awt.RenderingHints;

/* loaded from: infinitode-2.jar:com/d/i/v.class */
public class v {

    /* renamed from: a, reason: collision with root package name */
    private String f1379a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.c.a.a f1380b;
    private com.d.c.d.d c;
    private boolean d;
    private int e;
    private String f;

    public static void a(ab abVar, c cVar) {
        if (cVar.w() == null) {
            return;
        }
        x w = cVar.w();
        if (w.c() != null) {
            a(abVar, cVar, w);
            return;
        }
        com.d.c.f.c a2 = cVar.a();
        com.d.c.a.c e = a2.e(com.d.c.a.a.U);
        abVar.p().a(a2.b());
        if (w.b() != null) {
            a(abVar, cVar, a2, e);
        } else if (w.a() != null) {
            c(abVar, cVar);
        }
    }

    private static void a(ab abVar, c cVar, x xVar) {
        x.b c = xVar.c();
        com.d.d.c a2 = c.a();
        if (a2 != null) {
            abVar.p().a(a2, b(abVar, cVar) + (-c.b()) + ((c.b() / 2) - (a2.a() / 2)), (int) ((a(cVar) - (cVar.w().d().a() / 2.0f)) - (a2.b() / 2)), cVar.a().al());
        }
    }

    private static int b(ab abVar, c cVar) {
        x w = cVar.w();
        if (w.f() != null) {
            return w.f().ab();
        }
        return cVar.ab() + ((int) cVar.n(abVar).w());
    }

    private static int a(c cVar) {
        x w = cVar.w();
        ac d = cVar.w().d();
        if (w.f() != null) {
            return w.f().aa() + d.b();
        }
        return cVar.aa() + cVar.ao() + d.b();
    }

    private static void a(ab abVar, c cVar, com.d.c.f.c cVar2, com.d.c.a.c cVar3) {
        com.d.d.m p = abVar.p();
        RenderingHints.Key key = RenderingHints.KEY_ANTIALIASING;
        Object e = p.e();
        abVar.p();
        RenderingHints.Key key2 = RenderingHints.KEY_ANTIALIASING;
        Object obj = RenderingHints.VALUE_ANTIALIAS_ON;
        ac d = cVar.w().d();
        x.a b2 = cVar.w().b();
        int b3 = b(abVar, cVar);
        if (cVar2.az() == com.d.c.a.c.ak) {
            b3 -= b2.b();
        }
        if (cVar2.az() == com.d.c.a.c.aK) {
            b3 += cVar.w().f().Q() + b2.b();
        }
        int a2 = (a(cVar) - (((int) d.a()) / 2)) - (b2.a() / 2);
        if (cVar3 == com.d.c.a.c.x) {
            abVar.p().d(b3, a2, b2.a(), b2.a());
        } else if (cVar3 == com.d.c.a.c.aR) {
            abVar.p().c(b3, a2, b2.a(), b2.a());
        } else if (cVar3 == com.d.c.a.c.o) {
            abVar.p().b(b3, a2, b2.a(), b2.a());
        }
        abVar.p();
        RenderingHints.Key key3 = RenderingHints.KEY_ANTIALIASING;
        if (e == null) {
            Object obj2 = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
        }
    }

    private static void c(ab abVar, c cVar) {
        x.c a2 = cVar.w().a();
        int b2 = b(abVar, cVar) - a2.b();
        int a3 = a(cVar);
        abVar.p().a(cVar.a().b());
        abVar.p().a(cVar.a().d(abVar));
        abVar.f().a(abVar.p(), a2.a(), b2, a3);
    }

    public v(com.d.c.a.a aVar, com.d.c.d.d dVar, boolean z, int i) {
        this.f1379a = aVar.toString();
        this.f1380b = aVar;
        this.c = dVar;
        this.d = z;
        this.e = i;
    }

    public String toString() {
        return c() + ": " + e().toString();
    }

    public String a() {
        if (this.f == null) {
            this.f = (80 + this.f1380b.f964a + 58) + ((com.d.c.d.j) this.c).o() + ';';
        }
        return this.f;
    }

    public int b() {
        if (this.e == 0) {
            return 1;
        }
        if (this.e == 1) {
            if (this.d) {
                return 5;
            }
            return 2;
        }
        if (this.d) {
            return 4;
        }
        return 3;
    }

    public String c() {
        return this.f1379a;
    }

    public com.d.c.a.a d() {
        return this.f1380b;
    }

    public com.d.c.d.d e() {
        return this.c;
    }

    public boolean f() {
        return this.d;
    }

    public int g() {
        return this.e;
    }
}
