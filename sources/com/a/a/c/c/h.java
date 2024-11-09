package com.a.a.c.c;

import com.a.a.a.al;
import com.a.a.a.am;
import com.a.a.a.an;
import com.a.a.a.q;
import com.a.a.a.t;
import com.a.a.c.a;
import com.a.a.c.a.e;
import com.a.a.c.c;
import com.a.a.c.c.a.ac;
import com.a.a.c.f.ad;
import com.a.a.c.m.aa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/h.class */
public final class h extends b implements Serializable {
    private static final Class<?>[] c = {Throwable.class};

    /* renamed from: b, reason: collision with root package name */
    public static final h f405b = new h(new com.a.a.c.b.m());

    private h(com.a.a.c.b.m mVar) {
        super(mVar);
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<Object> c(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.j b2;
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.k<?> a3 = a(jVar, a2, bVar);
        com.a.a.c.k<?> kVar = a3;
        if (a3 != null) {
            if (this.f296a.b()) {
                Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
                while (it.hasNext()) {
                    it.next();
                    gVar.a();
                    kVar = com.d.c.d.a.j.a(kVar);
                }
            }
            return kVar;
        }
        if (jVar.f()) {
            return c(gVar, bVar);
        }
        if (jVar.d() && !jVar.l() && !jVar.h() && (b2 = b(gVar, bVar)) != null) {
            return e(gVar, b2, a2.a(b2));
        }
        com.a.a.c.k<?> d = d(gVar, jVar, bVar);
        if (d != null) {
            return d;
        }
        b(jVar.b());
        g(gVar, jVar, bVar);
        com.a.a.c.k<Object> b3 = b(gVar, jVar);
        if (b3 != null) {
            return b3;
        }
        return e(gVar, jVar, bVar);
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar, Class<?> cls) {
        com.a.a.c.j b2;
        if (gVar.a(com.a.a.c.q.INFER_BUILDER_TYPE_BINDINGS)) {
            b2 = gVar.b().a(cls, jVar.x());
        } else {
            b2 = gVar.b(cls);
        }
        return f(gVar, jVar, gVar.a().a(b2, bVar));
    }

    private com.a.a.c.k<?> d(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.k<?> b2 = b(gVar, jVar, bVar);
        com.a.a.c.k<?> kVar = b2;
        if (b2 != null && this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                gVar.a();
                kVar = com.d.c.d.a.j.a(kVar);
            }
        }
        return kVar;
    }

    private static com.a.a.c.k<Object> b(com.a.a.c.g gVar, com.a.a.c.j jVar) {
        String b2 = com.a.a.c.m.f.b(jVar);
        if (b2 != null && gVar.a().i(jVar.b()) == null) {
            return new ac(jVar, b2);
        }
        return null;
    }

    private com.a.a.c.j b(com.a.a.c.g gVar, com.a.a.c.b bVar) {
        Iterator<com.a.a.c.k.a.d> it = this.f296a.h().iterator();
        while (it.hasNext()) {
            it.next();
            gVar.a();
            com.a.a.c.j b2 = com.a.a.c.k.a.d.b();
            if (b2 != null) {
                return b2;
            }
        }
        return null;
    }

    private com.a.a.c.k<Object> e(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.k<?> g;
        try {
            x a2 = a(gVar, bVar);
            g d = d(gVar, bVar);
            g gVar2 = d;
            d.a(a2);
            b(gVar, bVar, gVar2);
            a(gVar, bVar, gVar2);
            c(gVar, bVar, gVar2);
            a(bVar, gVar2);
            gVar.a();
            if (this.f296a.b()) {
                Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
                while (it.hasNext()) {
                    it.next();
                    gVar2 = com.d.c.d.a.j.a(gVar2);
                }
            }
            if (jVar.d() && !a2.d()) {
                g = gVar2.h();
            } else {
                g = gVar2.g();
            }
            if (this.f296a.b()) {
                Iterator<com.d.c.d.a.j> it2 = this.f296a.g().iterator();
                while (it2.hasNext()) {
                    it2.next();
                    g = com.d.c.d.a.j.a(g);
                }
            }
            return g;
        } catch (IllegalArgumentException e) {
            throw com.a.a.c.d.b.a(gVar.j(), com.a.a.c.m.i.g(e), bVar, (com.a.a.c.f.s) null).a(e);
        } catch (NoClassDefFoundError e2) {
            return new com.a.a.c.c.a.f(e2);
        }
    }

    private com.a.a.c.k<Object> f(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        try {
            x a2 = a(gVar, bVar);
            com.a.a.c.f a3 = gVar.a();
            g d = d(gVar, bVar);
            g gVar2 = d;
            d.a(a2);
            b(gVar, bVar, gVar2);
            a(gVar, bVar, gVar2);
            c(gVar, bVar, gVar2);
            a(bVar, gVar2);
            e.a x = bVar.x();
            String str = x == null ? "build" : x.f199a;
            com.a.a.c.f.k a4 = bVar.a(str, null);
            if (a4 != null && a3.g()) {
                com.a.a.c.m.i.a(a4.i(), a3.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            gVar2.a(a4, x);
            if (this.f296a.b()) {
                Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
                while (it.hasNext()) {
                    it.next();
                    gVar2 = com.d.c.d.a.j.a(gVar2);
                }
            }
            com.a.a.c.k<?> a5 = gVar2.a(jVar, str);
            if (this.f296a.b()) {
                Iterator<com.d.c.d.a.j> it2 = this.f296a.g().iterator();
                while (it2.hasNext()) {
                    it2.next();
                    a5 = com.d.c.d.a.j.a(a5);
                }
            }
            return a5;
        } catch (IllegalArgumentException e) {
            throw com.a.a.c.d.b.a(gVar.j(), com.a.a.c.m.i.g(e), bVar, (com.a.a.c.f.s) null);
        } catch (NoClassDefFoundError e2) {
            return new com.a.a.c.c.a.f(e2);
        }
    }

    private static void a(com.a.a.c.g gVar, com.a.a.c.b bVar, g gVar2) {
        com.a.a.c.j jVar;
        v vVar;
        al<?> a2;
        ad e = bVar.e();
        if (e == null) {
            return;
        }
        Class<? extends al<?>> d = e.d();
        an b2 = gVar.b((com.a.a.c.f.b) bVar.d(), e);
        if (d == am.c.class) {
            com.a.a.c.w b3 = e.b();
            v a3 = gVar2.a(b3);
            vVar = a3;
            if (a3 == null) {
                throw new IllegalArgumentException(String.format("Invalid Object Id definition for %s: cannot find property with name %s", com.a.a.c.m.i.b(bVar.a()), com.a.a.c.m.i.a(b3)));
            }
            jVar = vVar.c();
            a2 = new com.a.a.c.c.a.w(e.c());
        } else {
            com.a.a.c.j b4 = gVar.b(d);
            gVar.b();
            jVar = com.a.a.c.l.o.c(b4, al.class)[0];
            vVar = null;
            a2 = gVar.a((com.a.a.c.f.b) bVar.d(), e);
        }
        gVar2.a(com.a.a.c.c.a.s.a(jVar, e.b(), a2, gVar.b(jVar), vVar, b2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private com.a.a.c.k<Object> c(com.a.a.c.g gVar, com.a.a.c.b bVar) {
        com.a.a.c.f a2 = gVar.a();
        g d = d(gVar, bVar);
        g gVar2 = d;
        d.a(a(gVar, bVar));
        b(gVar, bVar, gVar2);
        Iterator<v> a3 = gVar2.a();
        while (true) {
            if (!a3.hasNext()) {
                break;
            }
            if ("setCause".equals(a3.next().e().b())) {
                a3.remove();
                break;
            }
        }
        com.a.a.c.f.k a4 = bVar.a("initCause", c);
        if (a4 != null) {
            String str = "cause";
            com.a.a.c.x k = a2.k();
            if (k != null) {
                str = k.c("cause");
            }
            v a5 = a(gVar, bVar, aa.a(gVar.a(), a4, new com.a.a.c.w(str)), a4.b(0));
            if (a5 != null) {
                gVar2.a(a5);
            }
        }
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                gVar2 = com.d.c.d.a.j.a(gVar2);
            }
        }
        com.a.a.c.k a6 = com.a.a.c.c.b.an.a((d) gVar2.g());
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it2 = this.f296a.g().iterator();
            while (it2.hasNext()) {
                it2.next();
                a6 = com.d.c.d.a.j.a((com.a.a.c.k<?>) a6);
            }
        }
        return a6;
    }

    private static g d(com.a.a.c.g gVar, com.a.a.c.b bVar) {
        return new g(bVar, gVar);
    }

    private void b(com.a.a.c.g gVar, com.a.a.c.b bVar, g gVar2) {
        Set<String> emptySet;
        v[] a2 = !bVar.a().d() ? gVar2.c().a(gVar.a()) : null;
        v[] vVarArr = a2;
        boolean z = a2 != null;
        q.a b2 = gVar.a().b(bVar.b(), bVar.d());
        if (b2 != null) {
            gVar2.a(b2.d());
            Set<String> c2 = b2.c();
            emptySet = c2;
            Iterator<String> it = c2.iterator();
            while (it.hasNext()) {
                gVar2.a(it.next());
            }
        } else {
            emptySet = Collections.emptySet();
        }
        com.a.a.c.f a3 = gVar.a();
        bVar.b();
        t.a a4 = a3.a(bVar.d());
        Set<String> set = null;
        if (a4 != null) {
            Set<String> b3 = a4.b();
            set = b3;
            if (b3 != null) {
                Iterator<String> it2 = set.iterator();
                while (it2.hasNext()) {
                    gVar2.b(it2.next());
                }
            }
        }
        com.a.a.c.f.j s = bVar.s();
        if (s != null) {
            gVar2.a(a(gVar, bVar, s));
        } else {
            Set<String> i = bVar.i();
            if (i != null) {
                Iterator<String> it3 = i.iterator();
                while (it3.hasNext()) {
                    gVar2.a(it3.next());
                }
            }
        }
        boolean z2 = gVar.a(com.a.a.c.q.USE_GETTERS_AS_SETTERS) && gVar.a(com.a.a.c.q.AUTO_DETECT_GETTERS);
        List<com.a.a.c.f.s> a5 = a(gVar, gVar2, bVar.h(), emptySet, set);
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it4 = this.f296a.g().iterator();
            while (it4.hasNext()) {
                it4.next();
                gVar.a();
                a5 = com.d.c.d.a.j.a(a5);
            }
        }
        for (com.a.a.c.f.s sVar : a5) {
            v vVar = null;
            if (sVar.k()) {
                vVar = a(gVar, bVar, sVar, sVar.o().b(0));
            } else if (sVar.l()) {
                vVar = a(gVar, bVar, sVar, sVar.p().c());
            } else {
                com.a.a.c.f.k n = sVar.n();
                if (n != null) {
                    if (z2 && a(n.d())) {
                        if (!gVar2.c(sVar.a())) {
                            vVar = a(gVar, bVar, sVar);
                        }
                    } else if (!sVar.m() && sVar.h().d() != null) {
                        vVar = a(gVar, bVar, sVar);
                    }
                }
            }
            if (z && sVar.m()) {
                String a6 = sVar.a();
                m mVar = null;
                int length = vVarArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    v vVar2 = vVarArr[i2];
                    if (!a6.equals(vVar2.a()) || !(vVar2 instanceof m)) {
                        i2++;
                    } else {
                        mVar = (m) vVar2;
                        break;
                    }
                }
                if (mVar == null) {
                    ArrayList arrayList = new ArrayList();
                    for (v vVar3 : vVarArr) {
                        arrayList.add(vVar3.a());
                    }
                    gVar.a(bVar, sVar, "Could not find creator property with name %s (known Creator properties: %s)", com.a.a.c.m.i.b(a6), arrayList);
                } else {
                    if (vVar != null) {
                        mVar.a(vVar);
                    }
                    Class<?>[] w = sVar.w();
                    Class<?>[] clsArr = w;
                    if (w == null) {
                        clsArr = bVar.y();
                    }
                    mVar.a(clsArr);
                    gVar2.c(mVar);
                }
            } else if (vVar != null) {
                Class<?>[] w2 = sVar.w();
                Class<?>[] clsArr2 = w2;
                if (w2 == null) {
                    clsArr2 = bVar.y();
                }
                vVar.a(clsArr2);
                gVar2.b(vVar);
            }
        }
    }

    private static boolean a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    private List<com.a.a.c.f.s> a(com.a.a.c.g gVar, g gVar2, List<com.a.a.c.f.s> list, Set<String> set, Set<String> set2) {
        Class<?> g;
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap hashMap = new HashMap();
        for (com.a.a.c.f.s sVar : list) {
            String a2 = sVar.a();
            if (!com.a.a.c.m.n.a(a2, set, set2)) {
                if (!sVar.m() && (g = sVar.g()) != null && a(gVar.a(), g, hashMap)) {
                    gVar2.a(a2);
                } else {
                    arrayList.add(sVar);
                }
            }
        }
        return arrayList;
    }

    private void c(com.a.a.c.g gVar, com.a.a.c.b bVar, g gVar2) {
        List<com.a.a.c.f.s> j = bVar.j();
        if (j != null) {
            for (com.a.a.c.f.s sVar : j) {
                gVar2.a(sVar.y(), a(gVar, bVar, sVar, sVar.f()));
            }
        }
    }

    private static void a(com.a.a.c.b bVar, g gVar) {
        Map<Object, com.a.a.c.f.j> v = bVar.v();
        if (v != null) {
            for (Map.Entry<Object, com.a.a.c.f.j> entry : v.entrySet()) {
                com.a.a.c.f.j value = entry.getValue();
                com.a.a.c.w a2 = com.a.a.c.w.a(value.b());
                com.a.a.c.j c2 = value.c();
                bVar.g();
                gVar.a(a2, c2, value, entry.getKey());
            }
        }
    }

    private u a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.f.j jVar) {
        com.a.a.c.j t;
        com.a.a.c.j u;
        c.b bVar2;
        boolean z = jVar instanceof com.a.a.c.f.h;
        if (jVar instanceof com.a.a.c.f.k) {
            com.a.a.c.f.k kVar = (com.a.a.c.f.k) jVar;
            t = kVar.b(0);
            u = a(gVar, jVar, kVar.b(1));
            bVar2 = new c.b(com.a.a.c.w.a(jVar.b()), u, null, jVar, com.a.a.c.v.f767b);
        } else if (z) {
            com.a.a.c.j c2 = ((com.a.a.c.f.h) jVar).c();
            if (c2.p()) {
                com.a.a.c.j a2 = a(gVar, jVar, c2);
                t = a2.t();
                u = a2.u();
                bVar2 = new c.b(com.a.a.c.w.a(jVar.b()), a2, null, jVar, com.a.a.c.v.f767b);
            } else {
                if (c2.a(com.a.a.c.m.class) || c2.a(com.a.a.c.j.r.class)) {
                    com.a.a.c.j a3 = a(gVar, jVar, c2);
                    com.a.a.c.j b2 = gVar.b(com.a.a.c.m.class);
                    return u.a(gVar, new c.b(com.a.a.c.w.a(jVar.b()), a3, null, jVar, com.a.a.c.v.f767b), jVar, b2, gVar.b(b2));
                }
                return (u) gVar.a(bVar.a(), String.format("Unsupported type for any-setter: %s -- only support `Map`s, `JsonNode` and `ObjectNode` ", com.a.a.c.m.i.b(c2)));
            }
        } else {
            return (u) gVar.a(bVar.a(), String.format("Unrecognized mutator type for any-setter: %s", com.a.a.c.m.i.g(jVar.getClass())));
        }
        com.a.a.c.p b3 = b(gVar, jVar);
        com.a.a.c.p pVar = b3;
        if (b3 == null) {
            pVar = (com.a.a.c.p) t.A();
        }
        if (pVar == null) {
            pVar = gVar.b(t, bVar2);
        } else if (pVar instanceof l) {
            pVar = ((l) pVar).a();
        }
        com.a.a.c.k<?> c3 = c(gVar, jVar);
        com.a.a.c.k<?> kVar2 = c3;
        if (c3 == null) {
            kVar2 = (com.a.a.c.k) u.A();
        }
        if (kVar2 != null) {
            kVar2 = gVar.a(kVar2, (com.a.a.c.c) bVar2, u);
        }
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) u.B();
        if (z) {
            return u.a(gVar, bVar2, jVar, u, pVar, kVar2, eVar);
        }
        return u.a(bVar2, jVar, u, pVar, kVar2, eVar);
    }

    private v a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.f.s sVar, com.a.a.c.j jVar) {
        com.a.a.c.f.j u = sVar.u();
        if (u == null) {
            gVar.a(bVar, sVar, "No non-constructor mutator available", new Object[0]);
        }
        com.a.a.c.j a2 = a(gVar, u, jVar);
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) a2.B();
        v oVar = u instanceof com.a.a.c.f.k ? new com.a.a.c.c.a.o(sVar, a2, eVar, bVar.g(), (com.a.a.c.f.k) u) : new com.a.a.c.c.a.i(sVar, a2, eVar, bVar.g(), (com.a.a.c.f.h) u);
        com.a.a.c.k<?> a3 = a(gVar, u);
        com.a.a.c.k<?> kVar = a3;
        if (a3 == null) {
            kVar = (com.a.a.c.k) a2.A();
        }
        if (kVar != null) {
            oVar = oVar.a(gVar.a(kVar, (com.a.a.c.c) oVar, a2));
        }
        a.C0004a x = sVar.x();
        if (x != null && x.b()) {
            oVar.b(x.a());
        }
        ad A = sVar.A();
        if (A != null) {
            oVar.a(A);
        }
        return oVar;
    }

    private v a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.f.s sVar) {
        com.a.a.c.f.k n = sVar.n();
        com.a.a.c.j a2 = a(gVar, n, n.c());
        com.a.a.c.c.a.aa aaVar = new com.a.a.c.c.a.aa(sVar, a2, (com.a.a.c.i.e) a2.B(), bVar.g(), n);
        com.a.a.c.k<?> a3 = a(gVar, n);
        com.a.a.c.k<?> kVar = a3;
        if (a3 == null) {
            kVar = (com.a.a.c.k) a2.A();
        }
        if (kVar != null) {
            aaVar = aaVar.a(gVar.a(kVar, (com.a.a.c.c) aaVar, a2));
        }
        return aaVar;
    }

    private static boolean b(Class<?> cls) {
        String a2 = com.a.a.c.m.i.a(cls);
        if (a2 != null) {
            throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + a2 + ") as a Bean");
        }
        if (com.a.a.c.m.i.c(cls)) {
            throw new IllegalArgumentException("Cannot deserialize Proxy class " + cls.getName() + " as a Bean");
        }
        String a3 = com.a.a.c.m.i.a(cls, true);
        if (a3 != null) {
            throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + a3 + ") as a Bean");
        }
        return true;
    }

    private static boolean a(com.a.a.c.f fVar, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool;
        Boolean bool2 = map.get(cls);
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        if (cls == String.class || cls.isPrimitive()) {
            bool = Boolean.FALSE;
        } else {
            Boolean f = fVar.d(cls).f();
            bool = f;
            if (f == null) {
                Boolean b2 = fVar.j().b(fVar.c(cls).d());
                bool = b2;
                if (b2 == null) {
                    bool = Boolean.FALSE;
                }
            }
        }
        map.put(cls, bool);
        return bool.booleanValue();
    }

    private static void g(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.i.a.q.a().a(gVar, jVar, bVar);
    }
}
