package com.a.a.c.f;

import com.a.a.a.ac;
import com.a.a.a.s;
import com.a.a.a.x;
import com.a.a.c.a;
import com.a.a.c.v;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/* loaded from: infinitode-2.jar:com/a/a/c/f/af.class */
public final class af extends s implements Comparable<af> {
    private static final a.C0004a c = a.C0004a.a("");
    private boolean d;
    private com.a.a.c.b.q<?> e;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.a f431b;
    private com.a.a.c.w f;
    private com.a.a.c.w g;
    private a<h> h;
    private a<n> i;
    private a<k> j;
    private a<k> k;
    private transient com.a.a.c.v l;
    private transient a.C0004a m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/f/af$c.class */
    public interface c<T> {
        T a(j jVar);
    }

    public af(com.a.a.c.b.q<?> qVar, com.a.a.c.a aVar, boolean z, com.a.a.c.w wVar) {
        this(qVar, aVar, z, wVar, wVar);
    }

    private af(com.a.a.c.b.q<?> qVar, com.a.a.c.a aVar, boolean z, com.a.a.c.w wVar, com.a.a.c.w wVar2) {
        this.e = qVar;
        this.f431b = aVar;
        this.g = wVar;
        this.f = wVar2;
        this.d = z;
    }

    private af(af afVar, com.a.a.c.w wVar) {
        this.e = afVar.e;
        this.f431b = afVar.f431b;
        this.g = afVar.g;
        this.f = wVar;
        this.h = afVar.h;
        this.i = afVar.i;
        this.j = afVar.j;
        this.k = afVar.k;
        this.d = afVar.d;
    }

    public final af b(com.a.a.c.w wVar) {
        return new af(this, wVar);
    }

    public final af a(String str) {
        com.a.a.c.w b2 = this.f.b(str);
        return b2 == this.f ? this : new af(this, b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(af afVar) {
        if (this.i != null) {
            if (afVar.i == null) {
                return -1;
            }
        } else if (afVar.i != null) {
            return 1;
        }
        return a().compareTo(afVar.a());
    }

    @Override // com.a.a.c.f.s, com.a.a.c.m.v
    public final String a() {
        if (this.f == null) {
            return null;
        }
        return this.f.b();
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.w b() {
        return this.f;
    }

    @Override // com.a.a.c.f.s
    public final boolean a(com.a.a.c.w wVar) {
        return this.f.equals(wVar);
    }

    public final String C() {
        return this.g.b();
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.w c() {
        if (v() == null || this.f431b == null) {
            return null;
        }
        return com.a.a.c.a.b();
    }

    @Override // com.a.a.c.f.s
    public final boolean d() {
        return e(this.h) || e(this.j) || e(this.k) || f(this.i);
    }

    @Override // com.a.a.c.f.s
    public final boolean e() {
        return f(this.h) || f(this.j) || f(this.k) || f(this.i);
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.v h() {
        if (this.l == null) {
            j O = O();
            if (O != null) {
                Boolean f = this.f431b.f(O);
                String j = this.f431b.j((com.a.a.c.f.b) O);
                Integer k = this.f431b.k(O);
                String i = this.f431b.i((com.a.a.c.f.b) O);
                if (f == null && k == null && i == null) {
                    this.l = j == null ? com.a.a.c.v.c : com.a.a.c.v.c.a(j);
                } else {
                    this.l = com.a.a.c.v.a(f, j, k, i);
                }
                if (!this.d) {
                    this.l = a(this.l, O);
                }
            } else {
                this.l = com.a.a.c.v.c;
            }
        }
        return this.l;
    }

    private com.a.a.c.v a(com.a.a.c.v vVar, j jVar) {
        Boolean i;
        Boolean G;
        boolean z = true;
        com.a.a.a.ak akVar = null;
        com.a.a.a.ak akVar2 = null;
        j s = s();
        if (jVar != null) {
            if (this.f431b != null) {
                if (s != null && (G = this.f431b.G(jVar)) != null) {
                    z = false;
                    if (G.booleanValue()) {
                        vVar = vVar.a(v.a.c(s));
                    }
                }
                ac.a F = this.f431b.F(jVar);
                if (F != null) {
                    akVar = F.c();
                    akVar2 = F.d();
                }
            }
            if (z || akVar == null || akVar2 == null) {
                com.a.a.c.b.g d = this.e.d(a(jVar));
                ac.a g = d.g();
                if (g != null) {
                    if (akVar == null) {
                        akVar = g.c();
                    }
                    if (akVar2 == null) {
                        akVar2 = g.d();
                    }
                }
                if (z && s != null && (i = d.i()) != null) {
                    z = false;
                    if (i.booleanValue()) {
                        vVar = vVar.a(v.a.b(s));
                    }
                }
            }
        }
        if (z || akVar == null || akVar2 == null) {
            ac.a q = this.e.q();
            if (akVar == null) {
                akVar = q.c();
            }
            if (akVar2 == null) {
                akVar2 = q.d();
            }
            if (z) {
                if (Boolean.TRUE.equals(this.e.r()) && s != null) {
                    vVar = vVar.a(v.a.a(s));
                }
            }
        }
        if (akVar != null || akVar2 != null) {
            vVar = vVar.a(akVar, akVar2);
        }
        return vVar;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.j f() {
        if (this.d) {
            k n = n();
            k kVar = n;
            if (n == null) {
                h p = p();
                kVar = p;
                if (p == null) {
                    return com.a.a.c.l.o.b();
                }
            }
            return kVar.c();
        }
        n q = q();
        n nVar = q;
        if (q == null) {
            k o = o();
            if (o != null) {
                return o.b(0);
            }
            nVar = p();
        }
        if (nVar == null) {
            k n2 = n();
            nVar = n2;
            if (n2 == null) {
                return com.a.a.c.l.o.b();
            }
        }
        return nVar.c();
    }

    @Override // com.a.a.c.f.s
    public final Class<?> g() {
        return f().b();
    }

    public final boolean D() {
        return this.j != null;
    }

    @Override // com.a.a.c.f.s
    public final boolean k() {
        return this.k != null;
    }

    @Override // com.a.a.c.f.s
    public final boolean l() {
        return this.h != null;
    }

    @Override // com.a.a.c.f.s
    public final boolean m() {
        return this.i != null;
    }

    @Override // com.a.a.c.f.s
    public final boolean i() {
        return (this.i == null && this.k == null && this.h == null) ? false : true;
    }

    @Override // com.a.a.c.f.s
    public final boolean j() {
        return (this.j == null && this.h == null) ? false : true;
    }

    @Override // com.a.a.c.f.s
    public final k n() {
        a<k> aVar = this.j;
        a<k> aVar2 = aVar;
        if (aVar == null) {
            return null;
        }
        a<k> aVar3 = aVar2.f433b;
        if (aVar3 == null) {
            return aVar2.f432a;
        }
        for (a<k> aVar4 = aVar3; aVar4 != null; aVar4 = aVar4.f433b) {
            Class<?> h = aVar2.f432a.h();
            Class<?> h2 = aVar4.f432a.h();
            if (h != h2) {
                if (h.isAssignableFrom(h2)) {
                    aVar2 = aVar4;
                } else if (h2.isAssignableFrom(h)) {
                    continue;
                }
            }
            int a2 = a(aVar4.f432a);
            int a3 = a(aVar2.f432a);
            if (a2 != a3) {
                if (a2 < a3) {
                    aVar2 = aVar4;
                }
            } else {
                throw new IllegalArgumentException("Conflicting getter definitions for property \"" + a() + "\": " + aVar2.f432a.j() + " vs " + aVar4.f432a.j());
            }
        }
        this.j = aVar2.a();
        return aVar2.f432a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final k E() {
        a<k> aVar = this.j;
        if (aVar == null) {
            return null;
        }
        return aVar.f432a;
    }

    @Override // com.a.a.c.f.s
    public final k o() {
        a<k> aVar = this.k;
        a<k> aVar2 = aVar;
        if (aVar == null) {
            return null;
        }
        a<k> aVar3 = aVar2.f433b;
        if (aVar3 == null) {
            return aVar2.f432a;
        }
        for (a<k> aVar4 = aVar3; aVar4 != null; aVar4 = aVar4.f433b) {
            k a2 = a(aVar2.f432a, aVar4.f432a);
            if (a2 != aVar2.f432a) {
                if (a2 == aVar4.f432a) {
                    aVar2 = aVar4;
                } else {
                    return a(aVar2, aVar4);
                }
            }
        }
        this.k = aVar2.a();
        return aVar2.f432a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final k F() {
        a<k> aVar = this.k;
        if (aVar == null) {
            return null;
        }
        return aVar.f432a;
    }

    private k a(a<k> aVar, a<k> aVar2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(aVar.f432a);
        arrayList.add(aVar2.f432a);
        a<k> aVar3 = aVar2.f433b;
        while (true) {
            a<k> aVar4 = aVar3;
            if (aVar4 == null) {
                break;
            }
            k a2 = a(aVar.f432a, aVar4.f432a);
            if (a2 != aVar.f432a) {
                if (a2 == aVar4.f432a) {
                    arrayList.clear();
                    aVar = aVar4;
                } else {
                    arrayList.add(aVar4.f432a);
                }
            }
            aVar3 = aVar4.f433b;
        }
        if (arrayList.isEmpty()) {
            this.k = aVar.a();
            return aVar.f432a;
        }
        throw new IllegalArgumentException(String.format("Conflicting setter definitions for property \"%s\": %s", a(), (String) arrayList.stream().map((v0) -> {
            return v0.j();
        }).collect(Collectors.joining(" vs "))));
    }

    private k a(k kVar, k kVar2) {
        Class<?> h = kVar.h();
        Class<?> h2 = kVar2.h();
        if (h != h2) {
            if (h.isAssignableFrom(h2)) {
                return kVar2;
            }
            if (h2.isAssignableFrom(h)) {
                return kVar;
            }
        }
        int b2 = b(kVar2);
        int b3 = b(kVar);
        if (b2 != b3) {
            if (b2 < b3) {
                return kVar2;
            }
            return kVar;
        }
        if (this.f431b == null) {
            return null;
        }
        return this.f431b.a(kVar, kVar2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0086, code lost:            throw new java.lang.IllegalArgumentException("Multiple fields representing property \"" + a() + "\": " + r7.j() + " vs " + r0.j());     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.a.a.c.f.s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.a.a.c.f.h p() {
        /*
            r6 = this;
            r0 = r6
            com.a.a.c.f.af$a<com.a.a.c.f.h> r0 = r0.h
            if (r0 != 0) goto L9
            r0 = 0
            return r0
        L9:
            r0 = r6
            com.a.a.c.f.af$a<com.a.a.c.f.h> r0 = r0.h
            T r0 = r0.f432a
            com.a.a.c.f.h r0 = (com.a.a.c.f.h) r0
            r7 = r0
            r0 = r6
            com.a.a.c.f.af$a<com.a.a.c.f.h> r0 = r0.h
            com.a.a.c.f.af$a<T> r0 = r0.f433b
            r8 = r0
        L1c:
            r0 = r8
            if (r0 == 0) goto L8f
            r0 = r8
            T r0 = r0.f432a
            com.a.a.c.f.h r0 = (com.a.a.c.f.h) r0
            r9 = r0
            r0 = r7
            java.lang.Class r0 = r0.h()
            r10 = r0
            r0 = r9
            java.lang.Class r0 = r0.h()
            r11 = r0
            r0 = r10
            r1 = r11
            if (r0 == r1) goto L54
            r0 = r10
            r1 = r11
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L4a
            r0 = r9
            r7 = r0
            goto L87
        L4a:
            r0 = r11
            r1 = r10
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L87
        L54:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = r2
            java.lang.String r4 = "Multiple fields representing property \""
            r3.<init>(r4)
            r3 = r6
            java.lang.String r3 = r3.a()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "\": "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r7
            java.lang.String r3 = r3.j()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " vs "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r9
            java.lang.String r3 = r3.j()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L87:
            r0 = r8
            com.a.a.c.f.af$a<T> r0 = r0.f433b
            r8 = r0
            goto L1c
        L8f:
            r0 = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.f.af.p():com.a.a.c.f.h");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final h G() {
        a<h> aVar = this.h;
        if (aVar == null) {
            return null;
        }
        return aVar.f432a;
    }

    @Override // com.a.a.c.f.s
    public final n q() {
        if (this.i == null) {
            return null;
        }
        a<n> aVar = this.i;
        while (!(aVar.f432a.e() instanceof f)) {
            a<n> aVar2 = aVar.f433b;
            aVar = aVar2;
            if (aVar2 == null) {
                return this.i.f432a;
            }
        }
        return aVar.f432a;
    }

    @Override // com.a.a.c.f.s
    public final Iterator<n> r() {
        if (this.i == null) {
            return com.a.a.c.m.i.a();
        }
        return new b(this.i);
    }

    @Override // com.a.a.c.f.s
    public final j v() {
        if (this.d) {
            return s();
        }
        j t = t();
        j jVar = t;
        if (t == null) {
            jVar = s();
        }
        return jVar;
    }

    private j O() {
        if (this.d) {
            if (this.j != null) {
                return this.j.f432a;
            }
            if (this.h != null) {
                return this.h.f432a;
            }
            return null;
        }
        if (this.i != null) {
            return this.i.f432a;
        }
        if (this.k != null) {
            return this.k.f432a;
        }
        if (this.h != null) {
            return this.h.f432a;
        }
        if (this.j != null) {
            return this.j.f432a;
        }
        return null;
    }

    private static int a(k kVar) {
        String b2 = kVar.b();
        if (b2.startsWith("get") && b2.length() > 3) {
            return 1;
        }
        if (b2.startsWith("is") && b2.length() > 2) {
            return 2;
        }
        return 3;
    }

    private static int b(k kVar) {
        String b2 = kVar.b();
        if (b2.startsWith("set") && b2.length() > 3) {
            return 1;
        }
        return 2;
    }

    @Override // com.a.a.c.f.s
    public final Class<?>[] w() {
        return (Class[]) a(new ag(this));
    }

    @Override // com.a.a.c.f.s
    public final a.C0004a x() {
        a.C0004a c0004a = this.m;
        if (c0004a != null) {
            if (c0004a == c) {
                return null;
            }
            return c0004a;
        }
        a.C0004a c0004a2 = (a.C0004a) a(new ah(this));
        this.m = c0004a2 == null ? c : c0004a2;
        return c0004a2;
    }

    @Override // com.a.a.c.f.s
    public final boolean z() {
        Boolean bool = (Boolean) a(new ai(this));
        return bool != null && bool.booleanValue();
    }

    @Override // com.a.a.c.f.s
    public final ad A() {
        return (ad) a(new aj(this));
    }

    @Override // com.a.a.c.f.s
    public final s.b B() {
        s.b t = this.f431b == null ? null : this.f431b.t(s());
        return t == null ? s.b.a() : t;
    }

    private x.a P() {
        return (x.a) a((c<ak>) new ak(this), (ak) x.a.AUTO);
    }

    public final void a(h hVar, com.a.a.c.w wVar, boolean z, boolean z2, boolean z3) {
        this.h = new a<>(hVar, this.h, wVar, z, z2, z3);
    }

    public final void a(n nVar, com.a.a.c.w wVar, boolean z, boolean z2, boolean z3) {
        this.i = new a<>(nVar, this.i, wVar, z, true, false);
    }

    public final void a(k kVar, com.a.a.c.w wVar, boolean z, boolean z2, boolean z3) {
        this.j = new a<>(kVar, this.j, wVar, z, z2, z3);
    }

    public final void b(k kVar, com.a.a.c.w wVar, boolean z, boolean z2, boolean z3) {
        this.k = new a<>(kVar, this.k, wVar, z, z2, z3);
    }

    public final void a(af afVar) {
        this.h = b(this.h, afVar.h);
        this.i = b(this.i, afVar.i);
        this.j = b(this.j, afVar.j);
        this.k = b(this.k, afVar.k);
    }

    private static <T> a<T> b(a<T> aVar, a<T> aVar2) {
        if (aVar == null) {
            return aVar2;
        }
        if (aVar2 == null) {
            return aVar;
        }
        return aVar.b(aVar2);
    }

    public final void H() {
        this.h = b(this.h);
        this.j = b(this.j);
        this.k = b(this.k);
        this.i = b(this.i);
    }

    public final x.a a(boolean z, ae aeVar) {
        x.a P = P();
        x.a aVar = P;
        if (P == null) {
            aVar = x.a.AUTO;
        }
        switch (aVar) {
            case READ_ONLY:
                if (aeVar != null) {
                    aeVar.a(a());
                    Iterator<com.a.a.c.w> it = N().iterator();
                    while (it.hasNext()) {
                        aeVar.a(it.next().b());
                    }
                }
                this.k = null;
                this.i = null;
                if (!this.d) {
                    this.h = null;
                    break;
                }
                break;
            case READ_WRITE:
                break;
            case WRITE_ONLY:
                this.j = null;
                if (this.d) {
                    this.h = null;
                    break;
                }
                break;
            default:
                this.j = c(this.j);
                this.i = c(this.i);
                if (!z || this.j == null) {
                    this.h = c(this.h);
                    this.k = c(this.k);
                    break;
                }
                break;
        }
        return aVar;
    }

    public final void I() {
        this.i = null;
    }

    public final void J() {
        this.h = d(this.h);
        this.j = d(this.j);
        this.k = d(this.k);
        this.i = d(this.i);
    }

    public final void a(boolean z) {
        if (z) {
            if (this.j != null) {
                this.j = a(this.j, a(0, this.j, this.h, this.i, this.k));
                return;
            } else {
                if (this.h != null) {
                    this.h = a(this.h, a(0, this.h, this.i, this.k));
                    return;
                }
                return;
            }
        }
        if (this.i != null) {
            this.i = a(this.i, a(0, this.i, this.k, this.h, this.j));
        } else if (this.k != null) {
            this.k = a(this.k, a(0, this.k, this.h, this.j));
        } else if (this.h != null) {
            this.h = a(this.h, a(0, this.h, this.j));
        }
    }

    private aa a(int i, a<? extends j>... aVarArr) {
        aa a2 = a(aVarArr[i]);
        do {
            i++;
            if (i >= aVarArr.length) {
                return a2;
            }
        } while (aVarArr[i] == null);
        return aa.a(a2, a(i, aVarArr));
    }

    private <T extends j> aa a(a<T> aVar) {
        aa k = aVar.f432a.k();
        if (aVar.f433b != null) {
            k = aa.a(k, a(aVar.f433b));
        }
        return k;
    }

    private <T extends j> a<T> a(a<T> aVar, aa aaVar) {
        j jVar = (j) aVar.f432a.a(aaVar);
        if (aVar.f433b != null) {
            aVar = aVar.a(a(aVar.f433b, aaVar));
        }
        return aVar.a((a<T>) jVar);
    }

    private static <T> a<T> b(a<T> aVar) {
        if (aVar == null) {
            return aVar;
        }
        return aVar.b();
    }

    private static <T> a<T> c(a<T> aVar) {
        if (aVar == null) {
            return aVar;
        }
        return aVar.c();
    }

    private static <T> a<T> d(a<T> aVar) {
        if (aVar == null) {
            return aVar;
        }
        return aVar.d();
    }

    private static <T> boolean e(a<T> aVar) {
        while (aVar != null) {
            if (aVar.c == null || !aVar.c.c()) {
                aVar = aVar.f433b;
            } else {
                return true;
            }
        }
        return false;
    }

    private static <T> boolean f(a<T> aVar) {
        while (aVar != null) {
            if (aVar.c == null || !aVar.d) {
                aVar = aVar.f433b;
            } else {
                return true;
            }
        }
        return false;
    }

    public final boolean K() {
        return g(this.h) || g(this.j) || g(this.k) || g(this.i);
    }

    private static <T> boolean g(a<T> aVar) {
        while (aVar != null) {
            if (!aVar.e) {
                aVar = aVar.f433b;
            } else {
                return true;
            }
        }
        return false;
    }

    public final boolean L() {
        return h(this.h) || h(this.j) || h(this.k) || h(this.i);
    }

    private static <T> boolean h(a<T> aVar) {
        while (aVar != null) {
            if (!aVar.f) {
                aVar = aVar.f433b;
            } else {
                return true;
            }
        }
        return false;
    }

    public final boolean M() {
        return i(this.h) || i(this.j) || i(this.k) || j(this.i);
    }

    private static <T> boolean i(a<T> aVar) {
        while (aVar != null) {
            if (aVar.f || aVar.c == null || !aVar.c.c()) {
                aVar = aVar.f433b;
            } else {
                return true;
            }
        }
        return false;
    }

    private static <T> boolean j(a<T> aVar) {
        while (aVar != null) {
            if (aVar.f || aVar.c == null || !aVar.d) {
                aVar = aVar.f433b;
            } else {
                return true;
            }
        }
        return false;
    }

    public final Set<com.a.a.c.w> N() {
        Set<com.a.a.c.w> a2 = a(this.i, a(this.k, a(this.j, a(this.h, (Set<com.a.a.c.w>) null))));
        if (a2 == null) {
            return Collections.emptySet();
        }
        return a2;
    }

    public final Collection<af> a(Collection<com.a.a.c.w> collection) {
        HashMap hashMap = new HashMap();
        a(collection, hashMap, this.h);
        a(collection, hashMap, this.j);
        a(collection, hashMap, this.k);
        a(collection, hashMap, this.i);
        return hashMap.values();
    }

    private void a(Collection<com.a.a.c.w> collection, Map<com.a.a.c.w, af> map, a<?> aVar) {
        a aVar2 = aVar;
        while (true) {
            a aVar3 = aVar2;
            if (aVar3 != null) {
                com.a.a.c.w wVar = aVar3.c;
                if (!aVar3.d || wVar == null) {
                    if (aVar3.e) {
                        throw new IllegalStateException("Conflicting/ambiguous property name definitions (implicit name " + com.a.a.c.m.i.a(this.f) + "): found multiple explicit names: " + collection + ", but also implicit accessor: " + aVar3);
                    }
                } else {
                    af afVar = map.get(wVar);
                    af afVar2 = afVar;
                    if (afVar == null) {
                        afVar2 = new af(this.e, this.f431b, this.d, this.g, wVar);
                        map.put(wVar, afVar2);
                    }
                    if (aVar != this.h) {
                        if (aVar != this.j) {
                            if (aVar != this.k) {
                                if (aVar == this.i) {
                                    afVar2.i = aVar3.a((a) afVar2.i);
                                } else {
                                    throw new IllegalStateException("Internal error: mismatched accessors, property: " + this);
                                }
                            } else {
                                afVar2.k = aVar3.a((a) afVar2.k);
                            }
                        } else {
                            afVar2.j = aVar3.a((a) afVar2.j);
                        }
                    } else {
                        afVar2.h = aVar3.a((a) afVar2.h);
                    }
                }
                aVar2 = aVar3.f433b;
            } else {
                return;
            }
        }
    }

    private static Set<com.a.a.c.w> a(a<? extends j> aVar, Set<com.a.a.c.w> set) {
        while (aVar != null) {
            if (aVar.d && aVar.c != null) {
                if (set == null) {
                    set = new HashSet();
                }
                set.add(aVar.c);
            }
            aVar = aVar.f433b;
        }
        return set;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Property '").append(this.f).append("'; ctors: ").append(this.i).append(", field(s): ").append(this.h).append(", getter(s): ").append(this.j).append(", setter(s): ").append(this.k);
        sb.append("]");
        return sb.toString();
    }

    private <T> T a(c<T> cVar) {
        T t = null;
        if (this.f431b != null) {
            if (this.d) {
                if (this.j != null) {
                    t = cVar.a(this.j.f432a);
                }
            } else {
                if (this.i != null) {
                    t = cVar.a(this.i.f432a);
                }
                if (t == null && this.k != null) {
                    t = cVar.a(this.k.f432a);
                }
            }
            if (t == null && this.h != null) {
                t = cVar.a(this.h.f432a);
            }
        }
        return t;
    }

    private <T> T a(c<T> cVar, T t) {
        T a2;
        T a3;
        T a4;
        T a5;
        T a6;
        T a7;
        T a8;
        T a9;
        if (this.f431b == null) {
            return null;
        }
        if (this.d) {
            if (this.j != null && (a9 = cVar.a(this.j.f432a)) != null && a9 != t) {
                return a9;
            }
            if (this.h != null && (a8 = cVar.a(this.h.f432a)) != null && a8 != t) {
                return a8;
            }
            if (this.i != null && (a7 = cVar.a(this.i.f432a)) != null && a7 != t) {
                return a7;
            }
            if (this.k != null && (a6 = cVar.a(this.k.f432a)) != null && a6 != t) {
                return a6;
            }
            return null;
        }
        if (this.i != null && (a5 = cVar.a(this.i.f432a)) != null && a5 != t) {
            return a5;
        }
        if (this.k != null && (a4 = cVar.a(this.k.f432a)) != null && a4 != t) {
            return a4;
        }
        if (this.h != null && (a3 = cVar.a(this.h.f432a)) != null && a3 != t) {
            return a3;
        }
        if (this.j != null && (a2 = cVar.a(this.j.f432a)) != null && a2 != t) {
            return a2;
        }
        return null;
    }

    private static Class<?> a(j jVar) {
        if (jVar instanceof k) {
            k kVar = (k) jVar;
            if (kVar.f() > 0) {
                return kVar.b(0).b();
            }
        }
        return jVar.c().b();
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/af$b.class */
    public static class b<T extends j> implements Iterator<T> {

        /* renamed from: a, reason: collision with root package name */
        private a<T> f434a;

        public b(a<T> aVar) {
            this.f434a = aVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f434a != null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public T next() {
            if (this.f434a == null) {
                throw new NoSuchElementException();
            }
            T t = this.f434a.f432a;
            this.f434a = this.f434a.f433b;
            return t;
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/af$a.class */
    public static final class a<T> {

        /* renamed from: a, reason: collision with root package name */
        public final T f432a;

        /* renamed from: b, reason: collision with root package name */
        public final a<T> f433b;
        public final com.a.a.c.w c;
        public final boolean d;
        public final boolean e;
        public final boolean f;

        public a(T t, a<T> aVar, com.a.a.c.w wVar, boolean z, boolean z2, boolean z3) {
            this.f432a = t;
            this.f433b = aVar;
            this.c = (wVar == null || wVar.e()) ? null : wVar;
            if (z) {
                if (this.c == null) {
                    throw new IllegalArgumentException("Cannot pass true for 'explName' if name is null/empty");
                }
                if (!wVar.c()) {
                    z = false;
                }
            }
            this.d = z;
            this.e = z2;
            this.f = z3;
        }

        public final a<T> a() {
            if (this.f433b == null) {
                return this;
            }
            return new a<>(this.f432a, null, this.c, this.d, this.e, this.f);
        }

        public final a<T> a(T t) {
            if (t == this.f432a) {
                return this;
            }
            return new a<>(t, this.f433b, this.c, this.d, this.e, this.f);
        }

        public final a<T> a(a<T> aVar) {
            if (aVar == this.f433b) {
                return this;
            }
            return new a<>(this.f432a, aVar, this.c, this.d, this.e, this.f);
        }

        public final a<T> b() {
            a<T> b2;
            if (this.f) {
                if (this.f433b == null) {
                    return null;
                }
                return this.f433b.b();
            }
            if (this.f433b != null && (b2 = this.f433b.b()) != this.f433b) {
                return a((a) b2);
            }
            return this;
        }

        public final a<T> c() {
            a<T> c = this.f433b == null ? null : this.f433b.c();
            return this.e ? a((a) c) : c;
        }

        protected final a<T> b(a<T> aVar) {
            if (this.f433b == null) {
                return a((a) aVar);
            }
            return a((a) this.f433b.b(aVar));
        }

        public final a<T> d() {
            if (this.f433b == null) {
                return this;
            }
            a<T> d = this.f433b.d();
            if (this.c != null) {
                if (d.c == null) {
                    return a((a) null);
                }
                return a((a) d);
            }
            if (d.c != null) {
                return d;
            }
            if (this.e == d.e) {
                return a((a) d);
            }
            return this.e ? a((a) null) : d;
        }

        public final String toString() {
            String format = String.format("%s[visible=%b,ignore=%b,explicitName=%b]", this.f432a.toString(), Boolean.valueOf(this.e), Boolean.valueOf(this.f), Boolean.valueOf(this.d));
            if (this.f433b != null) {
                format = format + ", " + this.f433b.toString();
            }
            return format;
        }
    }
}
