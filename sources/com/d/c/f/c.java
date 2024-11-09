package com.d.c.f;

import com.d.c.a.a;
import com.d.c.d.j;
import com.d.e.ad;
import com.d.i.ab;
import com.d.i.k;
import com.d.i.l;
import com.d.i.v;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

/* loaded from: infinitode-2.jar:com/d/c/f/c.class */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private c f1086a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.c.f.a.a f1087b;
    private com.d.c.f.a.h c;
    private com.d.c.f.a.h d;
    private float e;
    private boolean f;
    private k g;
    private l h;
    private boolean i;
    private boolean j;
    private boolean k;
    private a l;
    private final Map<String, c> m;
    private final g[] n;
    private com.d.c.g.a o;

    /* JADX INFO: Access modifiers changed from: protected */
    public c() {
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = new HashMap();
        this.n = new g[com.d.c.a.a.a()];
    }

    private c(c cVar, com.d.c.c.a aVar) {
        this();
        this.f1086a = cVar;
        b(aVar);
        aD();
        aE();
        aF();
    }

    private void aD() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        if (e == com.d.c.a.c.bb || e == com.d.c.a.c.bd || e == com.d.c.a.c.ba || e == com.d.c.a.c.bc) {
            this.j = false;
        } else if ((e == com.d.c.a.c.aV || e == com.d.c.a.c.T) && am()) {
            this.j = false;
        }
    }

    private void aE() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        if (e == com.d.c.a.c.bb || e == com.d.c.a.c.bd || e == com.d.c.a.c.ba || e == com.d.c.a.c.bc || e == com.d.c.a.c.aX) {
            this.i = false;
        }
    }

    private void aF() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        if (e == com.d.c.a.c.bb || e == com.d.c.a.c.bd || e == com.d.c.a.c.ba || e == com.d.c.a.c.bc) {
            this.k = false;
        }
    }

    public final synchronized c a(com.d.c.c.a aVar) {
        String b2 = aVar.b();
        c cVar = this.m.get(b2);
        c cVar2 = cVar;
        if (cVar == null) {
            cVar2 = new c(this, aVar);
            this.m.put(b2, cVar2);
        }
        return cVar2;
    }

    public final c a() {
        return this.f1086a;
    }

    public String toString() {
        return aI();
    }

    public final com.d.c.d.g a(com.d.c.a.a aVar) {
        g i = i(aVar);
        if (i == com.d.c.a.c.bj) {
            return com.d.c.d.h.f1056a;
        }
        return i.c();
    }

    public final float b(com.d.c.a.a aVar) {
        return i(aVar).b();
    }

    public final String[] c(com.d.c.a.a aVar) {
        return i(aVar).e();
    }

    public final boolean d(com.d.c.a.a aVar) {
        boolean z;
        try {
            z = i(aVar).g();
        } catch (Exception e) {
            com.d.m.l.g(Level.WARNING, "Property " + aVar + " has an assignment we don't understand, and can't tell if it's an absolute unit or not. Assuming it is not. Exception was: " + e.getMessage());
            z = false;
        }
        return z;
    }

    public final boolean a(com.d.c.a.a aVar, com.d.c.a.c cVar) {
        return i(aVar) == cVar;
    }

    public final com.d.c.a.c e(com.d.c.a.a aVar) {
        return i(aVar).f();
    }

    public final com.d.c.d.g b() {
        return a(com.d.c.a.a.f965b);
    }

    public final com.d.c.d.g c() {
        if (i(com.d.c.a.a.c) == com.d.c.a.c.bj) {
            return null;
        }
        return a(com.d.c.a.a.c);
    }

    public final a d() {
        if (this.l == null) {
            this.l = aG();
        }
        return this.l;
    }

    private a aG() {
        g i = i(com.d.c.a.a.h);
        if (i instanceof com.d.c.a.c) {
            com.d.c.a.c cVar = (com.d.c.a.c) i;
            if (cVar == com.d.c.a.c.s) {
                return new a(false, true, false);
            }
            if (cVar == com.d.c.a.c.r) {
                return new a(true, false, false);
            }
            throw new RuntimeException("internal error");
        }
        List<j> j = ((com.d.c.f.a.f) i).j();
        boolean z = j.get(0).h() == com.d.c.a.c.e;
        boolean z2 = j.get(1).h() == com.d.c.a.c.e;
        if (z && z2) {
            return new a(false, false, true);
        }
        return new a(j.get(0), j.get(1));
    }

    public final com.d.m.a e() {
        List<j> j = ((com.d.c.f.a.f) i(com.d.c.a.a.g)).j();
        return new com.d.m.a(j.get(0), j.get(1));
    }

    public final List<ad> f() {
        g i = i(com.d.c.a.a.E);
        if (i == com.d.c.a.c.ap) {
            return null;
        }
        return ((com.d.c.f.a.c) i).j();
    }

    public final List<ad> g() {
        g i = i(com.d.c.a.a.D);
        if (i == com.d.c.a.c.ap) {
            return null;
        }
        return ((com.d.c.f.a.c) i).j();
    }

    public final com.d.c.f.a.a a(d dVar) {
        if (!this.k) {
            return com.d.c.f.a.a.f1074a;
        }
        return a(this, dVar);
    }

    public final com.d.c.g.a b(d dVar) {
        j c;
        if (this.o == null) {
            this.o = new com.d.c.g.a();
            this.o.c = i(com.d.c.a.a.O).e();
            g i = i(com.d.c.a.a.M);
            if (i instanceof com.d.c.a.c) {
                com.d.c.a.c aH = aH();
                if (aH != null) {
                    c = h.a(aH, this.o.c);
                } else {
                    c = h.c((com.d.c.a.c) i);
                }
                this.o.f1094a = com.d.c.f.a.e.a(this, com.d.c.a.a.M, c.d(), c.f(), c.a(), 0.0f, dVar);
            } else {
                this.o.f1094a = a(com.d.c.a.a.M, 0.0f, dVar);
            }
            this.o.f1095b = e(com.d.c.a.a.L);
            this.o.d = e(com.d.c.a.a.J);
            this.o.e = e(com.d.c.a.a.K);
        }
        return this.o;
    }

    public final com.d.c.g.a h() {
        return this.o;
    }

    private com.d.c.a.c aH() {
        g i = i(com.d.c.a.a.M);
        if (!(i instanceof com.d.c.a.c)) {
            return null;
        }
        com.d.c.a.c cVar = (com.d.c.a.c) i;
        if (com.d.c.d.a.l.o.get(cVar.f968a)) {
            return cVar;
        }
        com.d.c.a.c aH = a().aH();
        if (aH != null) {
            if (cVar == com.d.c.a.c.bG) {
                return h.a(aH);
            }
            if (i == com.d.c.a.c.bw) {
                return h.b(aH);
            }
            return null;
        }
        return null;
    }

    public final float a(com.d.c.a.a aVar, float f, d dVar) {
        return i(aVar).a(aVar, f, dVar);
    }

    public final float b(com.d.c.a.a aVar, float f, d dVar) {
        return i(aVar).a(aVar, f, dVar);
    }

    public final float c(com.d.c.a.a aVar, float f, d dVar) {
        return i(aVar).a(aVar, f, dVar);
    }

    public final float c(d dVar) {
        if (!this.f) {
            if (a(com.d.c.a.a.N, com.d.c.a.c.aq)) {
                float f = b(dVar).f1094a * 1.1f;
                l e = e(dVar);
                this.e = Math.max(f, (float) Math.ceil(e.b() + e.a()));
            } else if (g(com.d.c.a.a.N)) {
                this.e = c(com.d.c.a.a.N, 0.0f, dVar);
            } else {
                this.e = b(dVar).f1094a * i(com.d.c.a.a.N).b();
            }
            this.f = true;
        }
        return this.e;
    }

    public final com.d.c.f.a.h a(float f, d dVar) {
        return a(f, dVar, true);
    }

    public final com.d.c.f.a.h a(float f, d dVar, boolean z) {
        if (!this.i) {
            return com.d.c.f.a.h.f1082b;
        }
        return b(this, com.d.c.a.a.bi, com.d.c.a.a.bk, f, dVar, z);
    }

    private com.d.c.f.a.h b(float f, d dVar, boolean z) {
        if (!this.j) {
            return com.d.c.f.a.h.f1082b;
        }
        return a(this, com.d.c.a.a.bj, com.d.c.a.a.bl, f, dVar, true);
    }

    public final com.d.c.f.a.h b(float f, d dVar) {
        return b(f, dVar, true);
    }

    public final String f(com.d.c.a.a aVar) {
        return i(aVar).d();
    }

    public final boolean g(com.d.c.a.a aVar) {
        return i(aVar) instanceof com.d.c.f.a.e;
    }

    public final boolean h(com.d.c.a.a aVar) {
        g i = i(aVar);
        return (i instanceof com.d.c.f.a.g) || (i instanceof com.d.c.f.a.e);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:            if (r0 == null) goto L18;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.d.c.f.g i(com.d.c.a.a r7) {
        /*
            r6 = this;
            r0 = r6
            com.d.c.f.g[] r0 = r0.n
            r1 = r7
            int r1 = r1.f964a
            r0 = r0[r1]
            r1 = r0
            r8 = r1
            com.d.c.a.c r1 = com.d.c.a.c.L
            if (r0 != r1) goto L15
            r0 = 1
            goto L16
        L15:
            r0 = 0
        L16:
            r9 = r0
            r0 = r8
            if (r0 == 0) goto L1f
            r0 = r9
            if (r0 == 0) goto L8f
        L1f:
            r0 = r9
            if (r0 != 0) goto L3e
            r0 = r7
            boolean r0 = com.d.c.a.a.a(r0)
            if (r0 == 0) goto L3e
            r0 = r6
            com.d.c.f.c r0 = r0.f1086a
            if (r0 == 0) goto L3e
            r0 = r6
            com.d.c.f.c r0 = r0.f1086a
            r1 = r7
            com.d.c.f.g r0 = r0.i(r1)
            r1 = r0
            r8 = r1
            if (r0 != 0) goto L85
        L3e:
            r0 = r7
            java.lang.String r0 = com.d.c.a.a.b(r0)
            r1 = r0
            r8 = r1
            if (r0 != 0) goto L64
            com.d.h.w$a r0 = new com.d.h.w$a
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = r2
            java.lang.String r4 = "Property '"
            r3.<init>(r4)
            r3 = r7
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "' has no initial values assigned. Check CSSName declarations."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L64:
            r0 = r8
            r1 = 0
            char r0 = r0.charAt(r1)
            r1 = 61
            if (r0 != r1) goto L80
            r0 = r8
            r1 = 1
            java.lang.String r0 = r0.substring(r1)
            com.d.c.a.a r0 = com.d.c.a.a.a(r0)
            r8 = r0
            r0 = r6
            r1 = r8
            com.d.c.f.g r0 = r0.i(r1)
            r8 = r0
            goto L85
        L80:
            r0 = r7
            com.d.c.f.g r0 = com.d.c.a.a.c(r0)
            r8 = r0
        L85:
            r0 = r6
            com.d.c.f.g[] r0 = r0.n
            r1 = r7
            int r1 = r1.f964a
            r2 = r8
            r0[r1] = r2
        L8f:
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.d.c.f.c.i(com.d.c.a.a):com.d.c.f.g");
    }

    private void b(com.d.c.c.a aVar) {
        if (aVar == null) {
            return;
        }
        for (v vVar : aVar.a()) {
            this.n[vVar.d().f964a] = a(vVar.d(), vVar.e());
        }
    }

    private g a(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        return com.a.a.b.c.a.a(this, aVar, (j) dVar);
    }

    private String aI() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.n.length; i++) {
            com.d.c.a.a a2 = com.d.c.a.a.a(i);
            if (this.n[i] != null) {
                sb.append(a2.toString());
            } else {
                sb.append("(no prop assigned in this pos)");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    private static com.d.c.f.a.h a(c cVar, com.d.c.a.a aVar, a.C0018a c0018a, float f, d dVar, boolean z) {
        if (!z) {
            return a(cVar, aVar, c0018a, f, dVar);
        }
        if (cVar.d == null) {
            com.d.c.f.a.h a2 = a(cVar, aVar, c0018a, f, dVar);
            com.d.c.f.a.h hVar = a2;
            boolean A = a2.A();
            if (A) {
                hVar = com.d.c.f.a.h.f1082b;
            }
            cVar.d = hVar;
            if (!A && cVar.d.B()) {
                cVar.d.C();
            }
        }
        return cVar.d;
    }

    private static com.d.c.f.a.h b(c cVar, com.d.c.a.a aVar, a.C0018a c0018a, float f, d dVar, boolean z) {
        if (!z) {
            return a(cVar, aVar, c0018a, f, dVar);
        }
        if (cVar.c == null) {
            com.d.c.f.a.h a2 = a(cVar, aVar, c0018a, f, dVar);
            com.d.c.f.a.h hVar = a2;
            if (a2.A()) {
                hVar = com.d.c.f.a.h.f1082b;
            }
            cVar.c = hVar;
        }
        return cVar.c;
    }

    private static com.d.c.f.a.h a(c cVar, com.d.c.a.a aVar, a.C0018a c0018a, float f, d dVar) {
        return com.d.c.f.a.h.a(cVar, aVar, c0018a, f, dVar);
    }

    private static com.d.c.f.a.a a(c cVar, d dVar) {
        if (cVar.f1087b == null) {
            com.d.c.f.a.a a2 = com.d.c.f.a.a.a(cVar, dVar);
            com.d.c.f.a.a aVar = a2;
            boolean A = a2.A();
            if (A && !aVar.n() && !aVar.o()) {
                aVar = com.d.c.f.a.a.f1074a;
            }
            cVar.f1087b = aVar;
            if (!A && cVar.f1087b.B()) {
                cVar.f1087b.C();
            }
        }
        return cVar.f1087b;
    }

    public final int a(d dVar, int i, int i2) {
        com.d.c.f.a.a a2 = a(dVar);
        com.d.c.f.a.h a3 = a(i, dVar);
        com.d.c.f.a.h b2 = b(i, dVar);
        switch (i2) {
            case 1:
                return (int) (a3.w() + a2.w() + b2.w());
            case 2:
                return (int) (a3.u() + a2.u() + b2.u());
            case 3:
                return (int) (a3.t() + a2.t() + b2.t());
            case 4:
                return (int) (a3.v() + a2.v() + b2.v());
            default:
                throw new IllegalArgumentException();
        }
    }

    public final com.d.c.a.c i() {
        return e(com.d.c.a.a.au);
    }

    public final k d(d dVar) {
        if (this.g == null) {
            this.g = dVar.c(b(dVar));
        }
        return this.g;
    }

    public final l e(d dVar) {
        if (this.h == null) {
            this.h = dVar.a(d(dVar));
        }
        return this.h;
    }

    public final com.d.c.a.c j() {
        return e(com.d.c.a.a.av);
    }

    public final boolean k() {
        com.d.c.a.c e = e(com.d.c.a.a.z);
        return e == com.d.c.a.c.aa || e == com.d.c.a.c.k;
    }

    public final boolean l() {
        com.d.c.a.c e = e(com.d.c.a.a.z);
        return e == com.d.c.a.c.aJ || e == com.d.c.a.c.k;
    }

    public final boolean m() {
        return !a(com.d.c.a.a.z, com.d.c.a.c.ap);
    }

    public final boolean n() {
        return e(com.d.c.a.a.f) == com.d.c.a.c.B;
    }

    public final boolean o() {
        return (!a(com.d.c.a.a.G, com.d.c.a.c.R) || C() || A() || B() || P()) ? false : true;
    }

    public final boolean p() {
        return a(com.d.c.a.a.G, com.d.c.a.c.S);
    }

    public final boolean q() {
        return a(com.d.c.a.a.G, com.d.c.a.c.aV);
    }

    public final boolean r() {
        return a(com.d.c.a.a.G, com.d.c.a.c.T);
    }

    public final boolean s() {
        return a(com.d.c.a.a.G, com.d.c.a.c.aX);
    }

    public final boolean t() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        return e == com.d.c.a.c.bd || e == com.d.c.a.c.bb || e == com.d.c.a.c.ba;
    }

    public final boolean u() {
        return a(com.d.c.a.a.G, com.d.c.a.c.aW);
    }

    public final boolean v() {
        return a(com.d.c.a.a.G, com.d.c.a.c.bc);
    }

    public final boolean w() {
        return a(com.d.c.a.a.G, com.d.c.a.c.ap);
    }

    public final boolean x() {
        return a(com.d.c.a.a.G, com.d.c.a.c.h);
    }

    public final boolean y() {
        com.d.c.a.c e;
        return C() || A() || B() || P() || (e = e(com.d.c.a.a.G)) == com.d.c.a.c.R || e == com.d.c.a.c.S || e == com.d.c.a.c.T;
    }

    public final boolean z() {
        return (A() || B() || C() || p()) ? false : true;
    }

    public final boolean A() {
        return a(com.d.c.a.a.ah, com.d.c.a.c.f969b);
    }

    public final boolean B() {
        return a(com.d.c.a.a.ah, com.d.c.a.c.B);
    }

    public final boolean C() {
        com.d.c.a.c e = e(com.d.c.a.a.I);
        return e == com.d.c.a.c.aa || e == com.d.c.a.c.aJ;
    }

    public final boolean D() {
        return a(com.d.c.a.a.I, com.d.c.a.c.aa);
    }

    public final boolean E() {
        return a(com.d.c.a.a.I, com.d.c.a.c.aJ);
    }

    public final boolean F() {
        return a(com.d.c.a.a.ah, com.d.c.a.c.aE);
    }

    public final boolean G() {
        return A() || B() || F();
    }

    public final boolean H() {
        return a(com.d.c.a.a.ax, com.d.c.a.c.e);
    }

    public final boolean I() {
        return i(com.d.c.a.a.ax).g();
    }

    public final boolean J() {
        return a(com.d.c.a.a.R, com.d.c.a.c.e);
    }

    public final boolean K() {
        return a(com.d.c.a.a.aV, com.d.c.a.c.e);
    }

    public final boolean L() {
        return a(com.d.c.a.a.aT, com.d.c.a.c.e);
    }

    public final boolean M() {
        return a(com.d.c.a.a.aB, com.d.c.a.c.e);
    }

    public final boolean N() {
        g i = i(com.d.c.a.a.ah);
        if (i instanceof com.d.c.f.a.d) {
            return false;
        }
        com.d.c.a.c e = e(com.d.c.a.a.G);
        com.d.c.a.c cVar = (com.d.c.a.c) i;
        return C() || cVar == com.d.c.a.c.f969b || cVar == com.d.c.a.c.B || e == com.d.c.a.c.S || e == com.d.c.a.c.aX || !a(com.d.c.a.a.ac, com.d.c.a.c.bp);
    }

    public final boolean O() {
        if (!a(com.d.c.a.a.ay, com.d.c.a.c.ap)) {
            return true;
        }
        if (i(com.d.c.a.a.ah) instanceof com.d.c.f.a.d) {
            return false;
        }
        com.d.c.a.c e = e(com.d.c.a.a.ah);
        if (e == com.d.c.a.c.f969b || e == com.d.c.a.c.aE || e == com.d.c.a.c.B) {
            return true;
        }
        com.d.c.a.c e2 = e(com.d.c.a.a.ac);
        return (e2 == com.d.c.a.c.aM || e2 == com.d.c.a.c.e) && R();
    }

    public final boolean P() {
        return i(com.d.c.a.a.ah) instanceof com.d.c.f.a.d;
    }

    public final String Q() {
        return ((com.d.c.f.a.d) i(com.d.c.a.a.ah)).j().b().get(0).c();
    }

    public final boolean R() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        return e == com.d.c.a.c.h || e == com.d.c.a.c.ae || e == com.d.c.a.c.aV || e == com.d.c.a.c.S || e == com.d.c.a.c.aX;
    }

    public final boolean S() {
        return i(com.d.c.a.a.ac) == com.d.c.a.c.bp;
    }

    public final boolean T() {
        com.d.c.a.c e = e(com.d.c.a.a.e);
        return e == com.d.c.a.c.aG || e == com.d.c.a.c.aF;
    }

    public final boolean U() {
        com.d.c.a.c e = e(com.d.c.a.a.e);
        return e == com.d.c.a.c.aH || e == com.d.c.a.c.aF;
    }

    public final boolean V() {
        return a(com.d.c.a.a.ar, com.d.c.a.c.e);
    }

    public final boolean W() {
        return a(com.d.c.a.a.x, com.d.c.a.c.e);
    }

    public final boolean X() {
        return a(com.d.c.a.a.G, com.d.c.a.c.ae);
    }

    public final boolean Y() {
        return !a(com.d.c.a.a.A, com.d.c.a.c.e) && b(com.d.c.a.a.A) > 1.0f;
    }

    public final int Z() {
        return (int) b(com.d.c.a.a.A);
    }

    public final int aa() {
        return (int) b(com.d.c.a.a.bc);
    }

    public final boolean a(ab abVar, com.d.i.f fVar) {
        com.d.i.f fVar2;
        com.d.c.a.c e = e(com.d.c.a.a.at);
        if (e == com.d.c.a.c.bp) {
            return true;
        }
        if (abVar != null && e == com.d.c.a.c.M) {
            com.d.i.f U = fVar.U();
            while (true) {
                fVar2 = U;
                if (fVar2 == null || (fVar2.a().q() && ((com.d.f.d) fVar2).m())) {
                    break;
                }
                U = fVar2.O();
            }
            return (fVar2 == null || ((com.d.f.d) fVar2).e(abVar)) ? false : true;
        }
        return false;
    }

    public final boolean ab() {
        com.d.c.a.c e = e(com.d.c.a.a.af);
        return e == com.d.c.a.c.c || e == com.d.c.a.c.aa || e == com.d.c.a.c.aJ;
    }

    public final boolean ac() {
        com.d.c.a.c e = e(com.d.c.a.a.ae);
        return e == com.d.c.a.c.c || e == com.d.c.a.c.aa || e == com.d.c.a.c.aJ;
    }

    public final boolean ad() {
        return a(com.d.c.a.a.ag, com.d.c.a.c.f);
    }

    public final c a(com.d.c.a.c cVar) {
        return a(com.d.c.c.a.a(cVar));
    }

    public final boolean ae() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        return e == com.d.c.a.c.h || e == com.d.c.a.c.ae || e == com.d.c.a.c.aL || e == com.d.c.a.c.aV || e == com.d.c.a.c.aX || e == com.d.c.a.c.aW || e == com.d.c.a.c.S;
    }

    public final boolean af() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        return e == com.d.c.a.c.h || e == com.d.c.a.c.ae || e == com.d.c.a.c.aX || e == com.d.c.a.c.aW || e == com.d.c.a.c.S;
    }

    public final boolean ag() {
        return C() || A() || B() || P();
    }

    public final boolean ah() {
        return (!a(com.d.c.a.a.ac, com.d.c.a.c.bp) || C() || A() || B() || p()) ? false : true;
    }

    public final boolean ai() {
        return A() || B() || p() || r();
    }

    public final boolean aj() {
        return a(com.d.c.a.a.Y, com.d.c.a.c.ap);
    }

    public final boolean ak() {
        return a(com.d.c.a.a.X, com.d.c.a.c.ap);
    }

    private boolean aJ() {
        return a(com.d.c.a.a.ba, com.d.c.a.c.bW) || a(com.d.c.a.a.ba, com.d.c.a.c.bX);
    }

    public final boolean al() {
        return !aJ();
    }

    public final int a(d dVar, int i) {
        return (int) a(com.d.c.a.a.aa, i, dVar);
    }

    public final int b(d dVar, int i) {
        return (int) a(com.d.c.a.a.Y, i, dVar);
    }

    public final int c(d dVar, int i) {
        return (int) a(com.d.c.a.a.Z, i, dVar);
    }

    public final int d(d dVar, int i) {
        return (int) a(com.d.c.a.a.X, i, dVar);
    }

    public final boolean am() {
        return a(com.d.c.a.a.i, com.d.c.a.c.q);
    }

    public final int f(d dVar) {
        if (am()) {
            return 0;
        }
        return (int) a(com.d.c.a.a.j, 0.0f, dVar);
    }

    public final int g(d dVar) {
        if (am()) {
            return 0;
        }
        return (int) a(com.d.c.a.a.k, 0.0f, dVar);
    }

    public final int an() {
        int b2 = (int) b(com.d.c.a.a.Q);
        if (b2 > 0) {
            return b2;
        }
        return 1;
    }

    public final int ao() {
        int b2 = (int) b(com.d.c.a.a.P);
        if (b2 > 0) {
            return b2;
        }
        return 1;
    }

    public final float h(d dVar) {
        return a(com.d.c.a.a.w, 0.0f, dVar);
    }

    public final i a(d dVar, com.d.c.a.a aVar) {
        i iVar = new i();
        g i = i(aVar);
        if ((i instanceof com.d.c.f.a.e) || (i instanceof com.d.c.f.a.g)) {
            if (i.g()) {
                iVar.a((int) i.a(aVar, 0.0f, dVar));
                iVar.a(2);
            } else {
                iVar.a((int) i.b());
                iVar.a(3);
            }
        }
        return iVar;
    }

    public final boolean ap() {
        return am() || a(com.d.c.a.a.H, com.d.c.a.c.aO);
    }

    public final boolean aq() {
        return (a(com.d.c.a.a.c, com.d.c.a.c.bj) && a(com.d.c.a.a.d, com.d.c.a.c.ap)) ? false : true;
    }

    public final List<com.d.c.a.c> ar() {
        g i = i(com.d.c.a.a.ao);
        if (i == com.d.c.a.c.ap) {
            return null;
        }
        return (List) ((com.d.c.f.a.f) i).j().stream().map(jVar -> {
            return (com.d.c.a.c) com.a.a.b.c.a.a(this, com.d.c.a.a.ao, jVar);
        }).collect(Collectors.toList());
    }

    public final boolean as() {
        return a(com.d.c.a.a.u, com.d.c.a.c.ay);
    }

    public final boolean at() {
        return (!a(com.d.c.a.a.an, com.d.c.a.c.X) || a(com.d.c.a.a.au, com.d.c.a.c.aB) || a(com.d.c.a.a.au, com.d.c.a.c.aC)) ? false : true;
    }

    public final boolean au() {
        return a(com.d.c.a.a.V, com.d.c.a.c.V);
    }

    public final boolean av() {
        return a(com.d.c.a.a.o, com.d.c.a.c.Y);
    }

    public final boolean aw() {
        return a(com.d.c.a.a.l, com.d.c.a.c.A);
    }

    public final boolean ax() {
        return aw() && H() && !ay();
    }

    public final boolean ay() {
        return p() || C() || A() || B();
    }

    public final com.d.c.a.c az() {
        return e(com.d.c.a.a.F);
    }

    public final boolean aA() {
        return !a(com.d.c.a.a.T, com.d.c.a.c.aq);
    }

    public final boolean aB() {
        com.d.c.a.c e = e(com.d.c.a.a.G);
        return !(e == com.d.c.a.c.R || e == com.d.c.a.c.S || e == com.d.c.a.c.T) || ag();
    }

    public final boolean aC() {
        return a(com.d.c.a.a.bb, com.d.c.a.c.bY);
    }
}
