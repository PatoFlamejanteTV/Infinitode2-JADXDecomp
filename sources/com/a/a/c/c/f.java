package com.a.a.c.c;

import com.a.a.a.af;
import com.a.a.a.al;
import com.a.a.a.am;
import com.a.a.a.an;
import com.a.a.a.l;
import com.a.a.a.q;
import com.a.a.b.l;
import com.a.a.c.c;
import com.a.a.c.c.a.aa;
import com.a.a.c.c.a.ab;
import com.a.a.c.c.a.ad;
import com.a.a.c.c.a.g;
import com.a.a.c.c.a.z;
import com.a.a.c.c.b.ae;
import com.a.a.c.m.ac;
import com.a.a.c.v;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/f.class */
public abstract class f extends ae<Object> implements k, t, Serializable {
    private static com.a.a.c.w t = new com.a.a.c.w("#temporary-name");

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.j f401a;
    private l.c u;

    /* renamed from: b, reason: collision with root package name */
    protected final x f402b;
    protected com.a.a.c.k<Object> c;
    protected com.a.a.c.k<Object> d;
    protected com.a.a.c.c.a.v e;
    protected boolean f;
    protected boolean g;
    protected final com.a.a.c.c.a.c h;
    protected final com.a.a.c.c.a.ae[] i;
    protected u j;
    protected final Set<String> k;
    protected final Set<String> l;
    protected final boolean m;
    protected final boolean n;
    private Map<String, v> v;
    private transient HashMap<com.a.a.c.l.b, com.a.a.c.k<Object>> w;
    protected ad o;
    protected com.a.a.c.c.a.g p;
    protected final com.a.a.c.c.a.s q;

    @Override // com.a.a.c.k
    public abstract com.a.a.c.k<Object> a(com.a.a.c.m.r rVar);

    public abstract f a(com.a.a.c.c.a.s sVar);

    public abstract f a(Set<String> set, Set<String> set2);

    public abstract f a(boolean z);

    protected abstract f g();

    public abstract Object b(com.a.a.b.l lVar, com.a.a.c.g gVar);

    protected abstract Object c(com.a.a.b.l lVar, com.a.a.c.g gVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public f(g gVar, com.a.a.c.b bVar, com.a.a.c.c.a.c cVar, Map<String, v> map, Set<String> set, boolean z, Set<String> set2, boolean z2) {
        super(bVar.a());
        this.f401a = bVar.a();
        this.f402b = gVar.c();
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = cVar;
        this.v = map;
        this.k = set;
        this.m = z;
        this.l = set2;
        this.j = gVar.b();
        List<com.a.a.c.c.a.ae> d = gVar.d();
        this.i = (d == null || d.isEmpty()) ? null : (com.a.a.c.c.a.ae[]) d.toArray(new com.a.a.c.c.a.ae[d.size()]);
        this.q = gVar.e();
        this.f = this.o != null || this.f402b.m() || this.f402b.o() || !this.f402b.l();
        this.u = bVar.a((l.d) null).c();
        this.n = z2;
        this.g = !this.f && this.i == null && !this.n && this.q == null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(f fVar) {
        this(fVar, fVar.m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(f fVar, boolean z) {
        super(fVar.f401a);
        this.f401a = fVar.f401a;
        this.f402b = fVar.f402b;
        this.c = fVar.c;
        this.d = fVar.d;
        this.e = fVar.e;
        this.h = fVar.h;
        this.v = fVar.v;
        this.k = fVar.k;
        this.m = z;
        this.l = fVar.l;
        this.j = fVar.j;
        this.i = fVar.i;
        this.q = fVar.q;
        this.f = fVar.f;
        this.o = fVar.o;
        this.n = fVar.n;
        this.u = fVar.u;
        this.g = fVar.g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(f fVar, com.a.a.c.m.r rVar) {
        super(fVar.f401a);
        this.f401a = fVar.f401a;
        this.f402b = fVar.f402b;
        this.c = fVar.c;
        this.d = fVar.d;
        this.e = fVar.e;
        this.v = fVar.v;
        this.k = fVar.k;
        this.m = rVar != null || fVar.m;
        this.l = fVar.l;
        this.j = fVar.j;
        this.i = fVar.i;
        this.q = fVar.q;
        this.f = fVar.f;
        ad adVar = fVar.o;
        if (rVar != null) {
            adVar = adVar != null ? adVar.a(rVar) : adVar;
            this.h = fVar.h.a(rVar);
        } else {
            this.h = fVar.h;
        }
        this.o = adVar;
        this.n = fVar.n;
        this.u = fVar.u;
        this.g = false;
    }

    public f(f fVar, com.a.a.c.c.a.s sVar) {
        super(fVar.f401a);
        this.f401a = fVar.f401a;
        this.f402b = fVar.f402b;
        this.c = fVar.c;
        this.d = fVar.d;
        this.e = fVar.e;
        this.v = fVar.v;
        this.k = fVar.k;
        this.m = fVar.m;
        this.l = fVar.l;
        this.j = fVar.j;
        this.i = fVar.i;
        this.f = fVar.f;
        this.o = fVar.o;
        this.n = fVar.n;
        this.u = fVar.u;
        this.q = sVar;
        if (sVar == null) {
            this.h = fVar.h;
            this.g = fVar.g;
        } else {
            this.h = fVar.h.a(new com.a.a.c.c.a.u(sVar, com.a.a.c.v.f766a));
            this.g = false;
        }
    }

    public f(f fVar, Set<String> set, Set<String> set2) {
        super(fVar.f401a);
        this.f401a = fVar.f401a;
        this.f402b = fVar.f402b;
        this.c = fVar.c;
        this.d = fVar.d;
        this.e = fVar.e;
        this.v = fVar.v;
        this.k = set;
        this.m = fVar.m;
        this.l = set2;
        this.j = fVar.j;
        this.i = fVar.i;
        this.f = fVar.f;
        this.o = fVar.o;
        this.n = fVar.n;
        this.u = fVar.u;
        this.g = fVar.g;
        this.q = fVar.q;
        this.h = fVar.h.a(set, set2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(f fVar, com.a.a.c.c.a.c cVar) {
        super(fVar.f401a);
        this.f401a = fVar.f401a;
        this.f402b = fVar.f402b;
        this.c = fVar.c;
        this.d = fVar.d;
        this.e = fVar.e;
        this.h = cVar;
        this.v = fVar.v;
        this.k = fVar.k;
        this.m = fVar.m;
        this.l = fVar.l;
        this.j = fVar.j;
        this.i = fVar.i;
        this.q = fVar.q;
        this.f = fVar.f;
        this.o = fVar.o;
        this.n = fVar.n;
        this.u = fVar.u;
        this.g = fVar.g;
    }

    public f a(com.a.a.c.c.a.c cVar) {
        throw new UnsupportedOperationException("Class " + getClass().getName() + " does not override `withBeanProperties()`, needs to");
    }

    @Override // com.a.a.c.c.t
    public final void d(com.a.a.c.g gVar) {
        v[] vVarArr;
        com.a.a.c.k<Object> p;
        com.a.a.c.k<Object> a2;
        g.a aVar = null;
        if (this.f402b.o()) {
            vVarArr = this.f402b.a(gVar.a());
            if (this.k != null || this.l != null) {
                int length = vVarArr.length;
                for (int i = 0; i < length; i++) {
                    if (com.a.a.c.m.n.a(vVarArr[i].a(), this.k, this.l)) {
                        vVarArr[i].f();
                    }
                }
            }
        } else {
            vVarArr = null;
        }
        ad adVar = null;
        Iterator<v> it = this.h.iterator();
        while (it.hasNext()) {
            v next = it.next();
            if (!next.n()) {
                com.a.a.c.k<?> a3 = a(gVar, next);
                com.a.a.c.k<?> kVar = a3;
                if (a3 == null) {
                    kVar = gVar.a(next.c());
                }
                a(this.h, vVarArr, next, next.a(kVar));
            }
        }
        Iterator<v> it2 = this.h.iterator();
        while (it2.hasNext()) {
            v next2 = it2.next();
            v b2 = b(gVar, next2.a(gVar.a(next2.p(), (com.a.a.c.c) next2, next2.c())));
            v vVar = b2;
            if (!(b2 instanceof com.a.a.c.c.a.m)) {
                vVar = a(vVar);
            }
            com.a.a.c.m.r c = c(gVar, vVar);
            if (c != null && (a2 = (p = vVar.p()).a(c)) != p && a2 != null) {
                v a4 = vVar.a((com.a.a.c.k<?>) a2);
                if (adVar == null) {
                    adVar = new ad();
                }
                adVar.a(a4);
                this.h.b(a4);
            } else {
                v d = d(gVar, b(gVar, vVar, vVar.d()));
                if (d != next2) {
                    a(this.h, vVarArr, next2, d);
                }
                if (d.o()) {
                    com.a.a.c.i.e q = d.q();
                    if (q.a() == af.a.EXTERNAL_PROPERTY) {
                        if (aVar == null) {
                            aVar = com.a.a.c.c.a.g.a(this.f401a);
                        }
                        aVar.a(d, q);
                        this.h.b(d);
                    }
                }
            }
        }
        if (this.j != null && !this.j.b()) {
            this.j = this.j.a(a(gVar, this.j.c(), this.j.a()));
        }
        if (this.f402b.m()) {
            x xVar = this.f402b;
            gVar.a();
            com.a.a.c.j p2 = xVar.p();
            if (p2 == null) {
                gVar.a(this.f401a, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", com.a.a.c.m.i.b(this.f401a), com.a.a.c.m.i.d(this.f402b)));
            }
            this.c = a(gVar, p2, this.f402b.s());
        }
        if (this.f402b.n()) {
            x xVar2 = this.f402b;
            gVar.a();
            com.a.a.c.j q2 = xVar2.q();
            if (q2 == null) {
                gVar.a(this.f401a, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", com.a.a.c.m.i.b(this.f401a), com.a.a.c.m.i.d(this.f402b)));
            }
            this.d = a(gVar, q2, this.f402b.t());
        }
        if (vVarArr != null) {
            this.e = com.a.a.c.c.a.v.a(gVar, this.f402b, vVarArr, this.h);
        }
        if (aVar != null) {
            this.p = aVar.a(this.h);
            this.f = true;
        }
        this.o = adVar;
        if (adVar != null) {
            this.f = true;
        }
        this.g = this.g && !this.f;
    }

    private static void a(com.a.a.c.c.a.c cVar, v[] vVarArr, v vVar, v vVar2) {
        cVar.a(vVar, vVar2);
        if (vVarArr != null) {
            int length = vVarArr.length;
            for (int i = 0; i < length; i++) {
                if (vVarArr[i] == vVar) {
                    vVarArr[i] = vVar2;
                    return;
                }
            }
        }
    }

    private com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.f.o oVar) {
        com.a.a.c.k<?> b2;
        c.b bVar = new c.b(t, jVar, null, oVar, com.a.a.c.v.f767b);
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) jVar.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = gVar.a().c(jVar);
        }
        com.a.a.c.k<?> kVar = (com.a.a.c.k) jVar.A();
        if (kVar == null) {
            b2 = a(gVar, jVar, bVar);
        } else {
            b2 = gVar.b(kVar, bVar, jVar);
        }
        if (eVar2 != null) {
            return new ab(eVar2.a(bVar), b2);
        }
        return b2;
    }

    private static com.a.a.c.k<Object> a(com.a.a.c.g gVar, v vVar) {
        Object C;
        com.a.a.c.a f = gVar.f();
        if (f != null && (C = f.C(vVar.e())) != null) {
            com.a.a.c.m.k<Object, Object> a2 = gVar.a(vVar.e(), C);
            gVar.b();
            com.a.a.c.j a3 = a2.a();
            return new com.a.a.c.c.b.ad(a2, a3, gVar.a(a3));
        }
        return null;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.c.a.c cVar2;
        com.a.a.c.c.a.c a2;
        com.a.a.c.f.ad a3;
        com.a.a.c.j jVar;
        v vVar;
        al<?> a4;
        com.a.a.c.c.a.s sVar = this.q;
        com.a.a.c.a f = gVar.f();
        com.a.a.c.f.j e = b(cVar, f) ? cVar.e() : null;
        com.a.a.c.f.j jVar2 = e;
        if (e != null && (a3 = f.a((com.a.a.c.f.b) jVar2)) != null) {
            com.a.a.c.f.ad a5 = f.a(jVar2, a3);
            Class<? extends al<?>> d = a5.d();
            an b2 = gVar.b((com.a.a.c.f.b) jVar2, a5);
            if (d == am.c.class) {
                com.a.a.c.w b3 = a5.b();
                v a6 = a(b3);
                vVar = a6;
                if (a6 == null) {
                    return (com.a.a.c.k) gVar.a(this.f401a, String.format("Invalid Object Id definition for %s: cannot find property with name %s", com.a.a.c.m.i.g(a()), com.a.a.c.m.i.a(b3)));
                }
                jVar = vVar.c();
                a4 = new com.a.a.c.c.a.w(a5.c());
            } else {
                com.a.a.c.j b4 = gVar.b(d);
                gVar.b();
                jVar = com.a.a.c.l.o.c(b4, al.class)[0];
                vVar = null;
                a4 = gVar.a((com.a.a.c.f.b) jVar2, a5);
            }
            sVar = com.a.a.c.c.a.s.a(jVar, a5.b(), a4, gVar.b(jVar), vVar, b2);
        }
        f fVar = this;
        if (sVar != null && sVar != this.q) {
            fVar = fVar.a(sVar);
        }
        if (jVar2 != null) {
            fVar = a(gVar, f, fVar, jVar2);
        }
        l.d a7 = a(gVar, cVar, a());
        l.c cVar3 = null;
        if (a7 != null) {
            if (a7.g()) {
                cVar3 = a7.c();
            }
            Boolean a8 = a7.a(l.a.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            if (a8 != null && (a2 = (cVar2 = this.h).a(a8.booleanValue())) != cVar2) {
                fVar = fVar.a(a2);
            }
        }
        if (cVar3 == null) {
            cVar3 = this.u;
        }
        if (cVar3 == l.c.ARRAY) {
            fVar = fVar.g();
        }
        return fVar;
    }

    private f a(com.a.a.c.g gVar, com.a.a.c.a aVar, f fVar, com.a.a.c.f.j jVar) {
        Set<String> set;
        gVar.a();
        q.a b2 = aVar.b((com.a.a.c.f.b) jVar);
        if (b2.d() && !this.m) {
            fVar = fVar.a(true);
        }
        Set<String> c = b2.c();
        Set<String> set2 = fVar.k;
        if (c.isEmpty()) {
            set = set2;
        } else if (set2 == null || set2.isEmpty()) {
            set = c;
        } else {
            HashSet hashSet = new HashSet(set2);
            set = hashSet;
            hashSet.addAll(c);
        }
        Set<String> set3 = fVar.l;
        Set<String> b3 = com.a.a.c.m.n.b(set3, aVar.c((com.a.a.c.f.b) jVar).b());
        if (set != set2 || b3 != set3) {
            fVar = fVar.a(set, b3);
        }
        return fVar;
    }

    private v b(com.a.a.c.g gVar, v vVar) {
        String l = vVar.l();
        if (l != null) {
            v a2 = vVar.p().a(l);
            if (a2 == null) {
                return (v) gVar.a(this.f401a, String.format("Cannot handle managed/back reference %s: no back reference property found from type %s", com.a.a.c.m.i.b(l), com.a.a.c.m.i.b(vVar.c())));
            }
            com.a.a.c.j jVar = this.f401a;
            com.a.a.c.j c = a2.c();
            boolean n = vVar.c().n();
            if (!c.b().isAssignableFrom(jVar.b())) {
                gVar.a(this.f401a, String.format("Cannot handle managed/back reference %s: back reference type (%s) not compatible with managed type (%s)", com.a.a.c.m.i.b(l), com.a.a.c.m.i.b(c), jVar.b().getName()));
            }
            return new com.a.a.c.c.a.m(vVar, l, a2, n);
        }
        return vVar;
    }

    private static v a(v vVar) {
        com.a.a.c.f.ad m = vVar.m();
        com.a.a.c.k<Object> p = vVar.p();
        com.a.a.c.c.a.s f = p == null ? null : p.f();
        if (m == null && f == null) {
            return vVar;
        }
        return new com.a.a.c.c.a.t(vVar, m);
    }

    private com.a.a.c.m.r c(com.a.a.c.g gVar, v vVar) {
        com.a.a.c.m.r c;
        com.a.a.c.f.j e = vVar.e();
        if (e != null && (c = gVar.f().c(e)) != null) {
            if (vVar instanceof m) {
                gVar.a(h(), String.format("Cannot define Creator property \"%s\" as `@JsonUnwrapped`: combination not yet supported", vVar.a()));
            }
            return c;
        }
        return null;
    }

    private v d(com.a.a.c.g gVar, v vVar) {
        Class<?> b2;
        Class<?> b3;
        com.a.a.c.k<Object> p = vVar.p();
        if ((p instanceof f) && !((f) p).i().l() && (b3 = com.a.a.c.m.i.b((b2 = vVar.c().b()))) != null && b3 == this.f401a.b()) {
            for (Constructor<?> constructor : b2.getConstructors()) {
                if (constructor.getParameterCount() == 1 && b3.equals(constructor.getParameterTypes()[0])) {
                    if (gVar.e()) {
                        com.a.a.c.m.i.a(constructor, gVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    return new com.a.a.c.c.a.j(vVar, constructor);
                }
            }
        }
        return vVar;
    }

    private v b(com.a.a.c.g gVar, v vVar, com.a.a.c.v vVar2) {
        v.a d = vVar2.d();
        if (d != null) {
            com.a.a.c.k<Object> p = vVar.p();
            Boolean a2 = p.a(gVar.a());
            if (a2 == null) {
                if (d.f769b) {
                    return vVar;
                }
            } else if (!a2.booleanValue()) {
                if (!d.f769b) {
                    gVar.a((com.a.a.c.k<?>) p);
                }
                return vVar;
            }
            com.a.a.c.f.j jVar = d.f768a;
            jVar.a(gVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            if (!(vVar instanceof aa)) {
                vVar = com.a.a.c.c.a.n.a(vVar, jVar);
            }
        }
        s a3 = a(gVar, vVar, vVar2);
        if (a3 != null) {
            vVar = vVar.a(a3);
        }
        return vVar;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.DYNAMIC;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        try {
            return this.f402b.a(gVar);
        } catch (IOException e) {
            return com.a.a.c.m.i.a(gVar, e);
        }
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.k
    public Boolean a(com.a.a.c.f fVar) {
        return Boolean.TRUE;
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Class<?> a() {
        return this.f401a.b();
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.c.a.s f() {
        return this.q;
    }

    @Override // com.a.a.c.k
    public final Collection<Object> d() {
        ArrayList arrayList = new ArrayList();
        Iterator<v> it = this.h.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.j h() {
        return this.f401a;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.POJO;
    }

    private v a(com.a.a.c.w wVar) {
        return j(wVar.b());
    }

    private v j(String str) {
        v a2 = this.h == null ? null : this.h.a(str);
        v vVar = a2;
        if (a2 == null && this.e != null) {
            vVar = this.e.a(str);
        }
        return vVar;
    }

    @Override // com.a.a.c.k
    public final v a(String str) {
        if (this.v == null) {
            return null;
        }
        return this.v.get(str);
    }

    @Override // com.a.a.c.c.b.ae
    public final x i() {
        return this.f402b;
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        Object U;
        if (this.q != null) {
            if (lVar.S() && (U = lVar.U()) != null) {
                return a(lVar, gVar, eVar.a(lVar, gVar), U);
            }
            com.a.a.b.o k = lVar.k();
            com.a.a.b.o oVar = k;
            if (k != null) {
                if (oVar.g()) {
                    return f(lVar, gVar);
                }
                if (oVar == com.a.a.b.o.START_OBJECT) {
                    oVar = lVar.g();
                }
                if (oVar == com.a.a.b.o.FIELD_NAME && this.q.c() && this.q.a(lVar.v(), lVar)) {
                    return f(lVar, gVar);
                }
            }
        }
        return eVar.a(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, Object obj2) {
        Object a2;
        com.a.a.c.k<Object> a3 = this.q.a();
        if (a3.a() == obj2.getClass()) {
            a2 = obj2;
        } else {
            a2 = a(lVar, gVar, obj2, a3);
        }
        gVar.a(a2, this.q.f281b, this.q.c).a(obj);
        v vVar = this.q.d;
        if (vVar != null) {
            return vVar.b(obj, a2);
        }
        return obj;
    }

    private static Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, com.a.a.c.k<Object> kVar) {
        ac a2 = gVar.a(lVar);
        if (obj instanceof String) {
            a2.b((String) obj);
        } else if (obj instanceof Long) {
            a2.b(((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            a2.c(((Integer) obj).intValue());
        } else {
            a2.h(obj);
        }
        com.a.a.b.l o = a2.o();
        o.g();
        return kVar.a(o, gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return b(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object f(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2 = this.q.a(lVar, gVar);
        z a3 = gVar.a(a2, this.q.f281b, this.q.c);
        Object b2 = a3.b();
        if (b2 == null) {
            throw new w(lVar, "Could not resolve Object Id [" + a2 + "] (for " + this.f401a + ").", lVar.e(), a3);
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object g(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.k<Object> j = j();
        if (j != null) {
            Object a2 = this.f402b.a(gVar, j.a(lVar, gVar));
            if (this.i != null) {
                a(gVar, a2);
            }
            return a2;
        }
        if (this.e != null) {
            return c(lVar, gVar);
        }
        Class<?> b2 = this.f401a.b();
        if (com.a.a.c.m.i.n(b2)) {
            return gVar.a(b2, (x) null, lVar, "non-static inner classes like this can only by instantiated using default, no-argument constructor", new Object[0]);
        }
        if (com.a.a.c.m.w.a(b2)) {
            return gVar.a(b2, (x) null, lVar, "cannot deserialize from Object value (no delegate- or property-based Creator): this appears to be a native image, in which case you may need to configure reflection for the class that is to be deserialized", new Object[0]);
        }
        return gVar.a(b2, i(), lVar, "cannot deserialize from Object value (no delegate- or property-based Creator)", new Object[0]);
    }

    public final Object h(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.q != null) {
            return f(lVar, gVar);
        }
        com.a.a.c.k<Object> j = j();
        l.b D = lVar.D();
        if (D == l.b.INT) {
            if (j != null && !this.f402b.f()) {
                Object a2 = this.f402b.a(gVar, j.a(lVar, gVar));
                if (this.i != null) {
                    a(gVar, a2);
                }
                return a2;
            }
            return this.f402b.a(gVar, lVar.G());
        }
        if (D == l.b.LONG) {
            if (j != null && !this.f402b.f()) {
                Object a3 = this.f402b.a(gVar, j.a(lVar, gVar));
                if (this.i != null) {
                    a(gVar, a3);
                }
                return a3;
            }
            return this.f402b.a(gVar, lVar.H());
        }
        if (D == l.b.BIG_INTEGER) {
            if (j != null && !this.f402b.h()) {
                Object a4 = this.f402b.a(gVar, j.a(lVar, gVar));
                if (this.i != null) {
                    a(gVar, a4);
                }
                return a4;
            }
            return this.f402b.a(gVar, lVar.I());
        }
        return gVar.a(a(), i(), lVar, "no suitable creator method found to deserialize from Number value (%s)", lVar.B());
    }

    public final Object i(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.q != null) {
            return f(lVar, gVar);
        }
        com.a.a.c.k<Object> j = j();
        if (j != null && !this.f402b.e()) {
            Object a2 = this.f402b.a(gVar, j.a(lVar, gVar));
            if (this.i != null) {
                a(gVar, a2);
            }
            return a2;
        }
        return m(lVar, gVar);
    }

    public final Object j(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        l.b D = lVar.D();
        if (D == l.b.DOUBLE || D == l.b.FLOAT) {
            com.a.a.c.k<Object> j = j();
            if (j != null && !this.f402b.i()) {
                Object a2 = this.f402b.a(gVar, j.a(lVar, gVar));
                if (this.i != null) {
                    a(gVar, a2);
                }
                return a2;
            }
            return this.f402b.a(gVar, lVar.K());
        }
        if (D == l.b.BIG_DECIMAL) {
            com.a.a.c.k<Object> j2 = j();
            if (j2 != null && !this.f402b.j()) {
                Object a3 = this.f402b.a(gVar, j2.a(lVar, gVar));
                if (this.i != null) {
                    a(gVar, a3);
                }
                return a3;
            }
            return this.f402b.a(gVar, lVar.L());
        }
        return gVar.a(a(), i(), lVar, "no suitable creator method found to deserialize from Number value (%s)", lVar.B());
    }

    public final Object k(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.k<Object> j = j();
        if (j != null && !this.f402b.k()) {
            Object a2 = this.f402b.a(gVar, j.a(lVar, gVar));
            if (this.i != null) {
                a(gVar, a2);
            }
            return a2;
        }
        return this.f402b.a(gVar, lVar.k() == com.a.a.b.o.VALUE_TRUE);
    }

    public final Object l(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.q != null) {
            return f(lVar, gVar);
        }
        com.a.a.c.k<Object> j = j();
        if (j != null && !this.f402b.e()) {
            Object a2 = this.f402b.a(gVar, j.a(lVar, gVar));
            if (this.i != null) {
                a(gVar, a2);
            }
            return a2;
        }
        Object N = lVar.N();
        Object obj = N;
        if (N != null && !this.f401a.c(obj.getClass())) {
            obj = gVar.a(this.f401a, obj, lVar);
        }
        return obj;
    }

    private com.a.a.c.k<Object> j() {
        com.a.a.c.k<Object> kVar = this.c;
        com.a.a.c.k<Object> kVar2 = kVar;
        if (kVar == null) {
            kVar2 = this.d;
        }
        return kVar2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(com.a.a.c.g gVar, Object obj) {
        for (com.a.a.c.c.a.ae aeVar : this.i) {
            aeVar.a(gVar, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(com.a.a.c.g gVar, Object obj, ac acVar) {
        acVar.j();
        com.a.a.b.l o = acVar.o();
        while (o.g() != com.a.a.b.o.END_OBJECT) {
            String v = o.v();
            o.g();
            b(o, gVar, obj, v);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, String str) {
        if (com.a.a.c.m.n.a(str, this.k, this.l)) {
            c(lVar, gVar, obj, str);
            return;
        }
        if (this.j != null) {
            try {
                this.j.a(lVar, gVar, obj, str);
                return;
            } catch (Exception e) {
                a(e, obj, str, gVar);
                return;
            }
        }
        b(lVar, gVar, obj, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.c.c.b.ae
    public final void b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, String str) {
        if (this.m) {
            lVar.j();
            return;
        }
        if (com.a.a.c.m.n.a(str, this.k, this.l)) {
            c(lVar, gVar, obj, str);
        }
        super.b(lVar, gVar, obj, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, String str) {
        if (gVar.a(com.a.a.c.i.FAIL_ON_IGNORED_PROPERTIES)) {
            throw com.a.a.c.d.a.a(lVar, obj, str, d());
        }
        lVar.j();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, ac acVar) {
        com.a.a.c.k<Object> b2 = b(gVar, obj);
        if (b2 != null) {
            if (acVar != null) {
                acVar.j();
                com.a.a.b.l o = acVar.o();
                o.g();
                obj = b2.a(o, gVar, (com.a.a.c.g) obj);
            }
            if (lVar != null) {
                obj = b2.a(lVar, gVar, (com.a.a.c.g) obj);
            }
            return obj;
        }
        if (acVar != null) {
            obj = a(gVar, obj, acVar);
        }
        if (lVar != null) {
            obj = a(lVar, gVar, (com.a.a.c.g) obj);
        }
        return obj;
    }

    private com.a.a.c.k<Object> b(com.a.a.c.g gVar, Object obj) {
        com.a.a.c.k<Object> kVar;
        synchronized (this) {
            kVar = this.w == null ? null : this.w.get(new com.a.a.c.l.b(obj.getClass()));
        }
        if (kVar != null) {
            return kVar;
        }
        com.a.a.c.k<Object> b2 = gVar.b(gVar.b(obj.getClass()));
        if (b2 != null) {
            synchronized (this) {
                if (this.w == null) {
                    this.w = new HashMap<>();
                }
                this.w.put(new com.a.a.c.l.b(obj.getClass()), b2);
            }
        }
        return b2;
    }

    public final void a(Throwable th, Object obj, String str, com.a.a.c.g gVar) {
        throw com.a.a.c.l.a(b(th, gVar), obj, str);
    }

    private static Throwable b(Throwable th, com.a.a.c.g gVar) {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        com.a.a.c.m.i.a(th);
        boolean z = gVar == null || gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof com.a.a.b.d)) {
                throw ((IOException) th);
            }
        } else if (!z) {
            com.a.a.c.m.i.b(th);
        }
        return th;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(Throwable th, com.a.a.c.g gVar) {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        com.a.a.c.m.i.a(th);
        if (th instanceof IOException) {
            throw ((IOException) th);
        }
        if (gVar == null) {
            throw new IllegalArgumentException(th.getMessage(), th);
        }
        if (!gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS)) {
            com.a.a.c.m.i.b(th);
        }
        return gVar.a(this.f401a.b(), (Object) null, th);
    }
}
