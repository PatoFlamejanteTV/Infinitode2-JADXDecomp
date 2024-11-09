package com.a.a.c.k;

import com.a.a.a.af;
import com.a.a.a.al;
import com.a.a.a.am;
import com.a.a.a.q;
import com.a.a.a.t;
import com.a.a.c.a;
import com.a.a.c.aa;
import com.a.a.c.b.t;
import com.a.a.c.c;
import com.a.a.c.f.ad;
import com.a.a.c.f.w;
import com.a.a.c.k.b.aj;
import com.a.a.c.k.b.ar;
import com.a.a.c.k.b.v;
import com.a.a.c.u;
import com.a.a.c.y;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/k/h.class */
public final class h extends b implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    public static final h f645b = new h(null);

    private h(t tVar) {
        super(tVar);
    }

    @Override // com.a.a.c.k.b
    protected final Iterable<w.a> a() {
        return this.f589a.c();
    }

    @Override // com.a.a.c.k.r
    public final com.a.a.c.o<Object> a(aa aaVar, com.a.a.c.j jVar) {
        com.a.a.c.j a2;
        boolean z;
        y a3 = aaVar.a();
        com.a.a.c.b a4 = a3.a(jVar);
        com.a.a.c.o<?> a5 = a(aaVar, a4.d());
        com.a.a.c.o<?> oVar = a5;
        if (a5 != null) {
            return oVar;
        }
        com.a.a.c.a j = a3.j();
        if (j == null) {
            a2 = jVar;
        } else {
            try {
                a2 = j.a((com.a.a.c.b.q<?>) a3, (com.a.a.c.f.b) a4.d(), jVar);
            } catch (com.a.a.c.l e) {
                return (com.a.a.c.o) aaVar.a(a4, e.getMessage(), new Object[0]);
            }
        }
        if (a2 == jVar) {
            z = false;
        } else {
            z = true;
            if (!a2.a(jVar.b())) {
                a4 = a3.a(a2);
            }
        }
        com.a.a.c.m.k<Object, Object> t = a4.t();
        if (t == null) {
            return c(aaVar, a2, a4, z);
        }
        aaVar.b();
        com.a.a.c.j b2 = t.b();
        if (!b2.a(a2.b())) {
            a4 = a3.a(b2);
            oVar = a(aaVar, a4.d());
        }
        if (oVar == null && !b2.q()) {
            oVar = c(aaVar, b2, a4, true);
        }
        return new aj(t, b2, oVar);
    }

    private com.a.a.c.o<?> c(aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar, boolean z) {
        com.a.a.c.o<?> oVar = null;
        y a2 = aaVar.a();
        if (jVar.n()) {
            if (!z) {
                z = a(a2, bVar, (com.a.a.c.i.i) null);
            }
            com.a.a.c.o<?> b2 = b(aaVar, jVar, bVar, z);
            oVar = b2;
            if (b2 != null) {
                return oVar;
            }
        } else {
            if (jVar.F()) {
                oVar = a(aaVar, (com.a.a.c.l.j) jVar, bVar, z);
            } else {
                Iterator<w.a> it = a().iterator();
                while (it.hasNext()) {
                    com.a.a.c.o<?> m = it.next().m();
                    oVar = m;
                    if (m != null) {
                        break;
                    }
                }
            }
            if (oVar == null) {
                oVar = a(aaVar, jVar, bVar);
            }
        }
        if (oVar == null) {
            com.a.a.c.o<?> a3 = a(jVar);
            oVar = a3;
            if (a3 == null) {
                com.a.a.c.o<?> a4 = a(aaVar, jVar, bVar, z);
                oVar = a4;
                if (a4 == null) {
                    com.a.a.c.o<?> d = d(aaVar, jVar, bVar, z);
                    oVar = d;
                    if (d == null) {
                        oVar = aaVar.d(bVar.b());
                    }
                }
            }
        }
        if (oVar != null && this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                oVar = i.a(oVar);
            }
        }
        return oVar;
    }

    private com.a.a.c.o<Object> d(aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar, boolean z) {
        if (!a(jVar.b()) && !com.a.a.c.m.i.k(jVar.b())) {
            return null;
        }
        return e(aaVar, jVar, bVar, z);
    }

    private com.a.a.c.i.i a(com.a.a.c.j jVar, y yVar, com.a.a.c.f.j jVar2) {
        com.a.a.c.i.i a2;
        com.a.a.c.i.h<?> a3 = yVar.j().a((com.a.a.c.b.q<?>) yVar, jVar2, jVar);
        if (a3 == null) {
            a2 = a(yVar, jVar);
        } else {
            a2 = a3.a(yVar, jVar, yVar.w().a(yVar, jVar2, jVar));
        }
        return a2;
    }

    private com.a.a.c.i.i b(com.a.a.c.j jVar, y yVar, com.a.a.c.f.j jVar2) {
        com.a.a.c.i.i a2;
        com.a.a.c.j u = jVar.u();
        com.a.a.c.i.h<?> b2 = yVar.j().b((com.a.a.c.b.q<?>) yVar, jVar2, jVar);
        if (b2 == null) {
            a2 = a(yVar, u);
        } else {
            a2 = b2.a(yVar, u, yVar.w().a(yVar, jVar2, u));
        }
        return a2;
    }

    private com.a.a.c.o<Object> e(aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar, boolean z) {
        List<e> b2;
        if (bVar.b() == Object.class) {
            return aaVar.d(Object.class);
        }
        com.a.a.c.o<?> b3 = b(aaVar, jVar);
        if (b3 != null) {
            return b3;
        }
        if (b(jVar)) {
            return new ar(jVar);
        }
        y a2 = aaVar.a();
        g a3 = a(bVar);
        g gVar = a3;
        a3.a(a2);
        List<e> a4 = a(aaVar, bVar, gVar);
        if (a4 == null) {
            b2 = new ArrayList();
        } else {
            b2 = b(a4);
        }
        aaVar.d().a(a2, bVar.d(), b2);
        if (this.f589a.b()) {
            Iterator<i> it = this.f589a.e().iterator();
            while (it.hasNext()) {
                it.next();
                b2 = i.a(b2);
            }
        }
        List<e> a5 = a(a2, bVar, a(bVar, b2));
        if (this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                a5 = i.b(a5);
            }
        }
        gVar.a(a(aaVar, bVar, a5));
        gVar.a(a5);
        gVar.a(a(a2, bVar));
        com.a.a.c.f.j r = bVar.r();
        if (r != null) {
            com.a.a.c.j c = r.c();
            com.a.a.c.j u = c.u();
            com.a.a.c.i.i a6 = a(a2, u);
            com.a.a.c.o<Object> a7 = a(aaVar, r);
            com.a.a.c.o<Object> oVar = a7;
            if (a7 == null) {
                oVar = v.a((Set) null, c, a2.a(com.a.a.c.q.USE_STATIC_TYPING), a6, null, null, null);
            }
            gVar.a(new a(new c.b(com.a.a.c.w.a(r.b()), u, null, r, com.a.a.c.v.f767b), r, oVar));
        }
        a(a2, gVar);
        if (this.f589a.b()) {
            Iterator<i> it3 = this.f589a.e().iterator();
            while (it3.hasNext()) {
                it3.next();
                gVar = i.a(gVar);
            }
        }
        try {
            com.a.a.c.o<?> g = gVar.g();
            if (g == null) {
                if (jVar.j() && !com.a.a.c.m.w.a(jVar.b())) {
                    return gVar.h();
                }
                com.a.a.c.o<?> a8 = a(a2, jVar, bVar, z);
                g = a8;
                if (a8 == null && bVar.f()) {
                    return gVar.h();
                }
            }
            return g;
        } catch (RuntimeException e) {
            return (com.a.a.c.o) aaVar.a(bVar, "Failed to construct BeanSerializer for %s: (%s) %s", bVar.a(), e.getClass().getName(), e.getMessage());
        }
    }

    private static com.a.a.c.k.a.m a(aa aaVar, com.a.a.c.b bVar, List<e> list) {
        ad e = bVar.e();
        if (e == null) {
            return null;
        }
        Class<? extends al<?>> d = e.d();
        if (d == am.c.class) {
            String b2 = e.b().b();
            int size = list.size();
            for (int i = 0; i != size; i++) {
                e eVar = list.get(i);
                if (b2.equals(eVar.a())) {
                    if (i > 0) {
                        list.remove(i);
                        list.add(0, eVar);
                    }
                    return com.a.a.c.k.a.m.a(eVar.c(), (com.a.a.c.w) null, new com.a.a.c.k.a.j(e, eVar), e.f());
                }
            }
            throw new IllegalArgumentException(String.format("Invalid Object Id definition for %s: cannot find property with name %s", com.a.a.c.m.i.b(bVar.a()), com.a.a.c.m.i.b(b2)));
        }
        com.a.a.c.j a2 = aaVar.a((Type) d);
        aaVar.b();
        return com.a.a.c.k.a.m.a(com.a.a.c.l.o.c(a2, al.class)[0], e.b(), aaVar.a((com.a.a.c.f.b) bVar.d(), e), e.f());
    }

    private static e a(e eVar, Class<?>[] clsArr) {
        return com.a.a.c.k.a.d.a(eVar, clsArr);
    }

    private static m b(y yVar, com.a.a.c.b bVar) {
        return new m(yVar, bVar);
    }

    private static g a(com.a.a.c.b bVar) {
        return new g(bVar);
    }

    private static boolean a(Class<?> cls) {
        return com.a.a.c.m.i.a(cls) == null && !com.a.a.c.m.i.c(cls);
    }

    private List<e> a(aa aaVar, com.a.a.c.b bVar, g gVar) {
        List<com.a.a.c.f.s> h = bVar.h();
        y a2 = aaVar.a();
        a(a2, h);
        if (a2.a(com.a.a.c.q.REQUIRE_SETTERS_FOR_GETTERS)) {
            a(h);
        }
        if (h.isEmpty()) {
            return null;
        }
        boolean a3 = a(a2, bVar, (com.a.a.c.i.i) null);
        m b2 = b(a2, bVar);
        ArrayList arrayList = new ArrayList(h.size());
        for (com.a.a.c.f.s sVar : h) {
            com.a.a.c.f.j s = sVar.s();
            if (sVar.z()) {
                if (s != null) {
                    gVar.a(s);
                }
            } else {
                a.C0004a x = sVar.x();
                if (x == null || !x.c()) {
                    if (s instanceof com.a.a.c.f.k) {
                        arrayList.add(a(aaVar, sVar, b2, a3, (com.a.a.c.f.k) s));
                    } else {
                        arrayList.add(a(aaVar, sVar, b2, a3, (com.a.a.c.f.h) s));
                    }
                }
            }
        }
        return arrayList;
    }

    private static List<e> a(y yVar, com.a.a.c.b bVar, List<e> list) {
        q.a b2 = yVar.b(bVar.b(), bVar.d());
        Set<String> set = null;
        if (b2 != null) {
            set = b2.b();
        }
        bVar.b();
        t.a a2 = yVar.a(bVar.d());
        Set<String> set2 = null;
        if (a2 != null) {
            set2 = a2.b();
        }
        if (set2 != null || (set != null && !set.isEmpty())) {
            Iterator<e> it = list.iterator();
            while (it.hasNext()) {
                if (com.a.a.c.m.n.a(it.next().a(), set, set2)) {
                    it.remove();
                }
            }
        }
        return list;
    }

    private static List<e> a(com.a.a.c.b bVar, List<e> list) {
        if (bVar.a().b(CharSequence.class) && list.size() == 1) {
            com.a.a.c.f.j e = list.get(0).e();
            if ((e instanceof com.a.a.c.f.k) && "isEmpty".equals(e.b()) && e.h() == CharSequence.class) {
                list.remove(0);
            }
        }
        return list;
    }

    private void a(y yVar, g gVar) {
        List<e> b2 = gVar.b();
        boolean a2 = yVar.a(com.a.a.c.q.DEFAULT_VIEW_INCLUSION);
        int size = b2.size();
        int i = 0;
        e[] eVarArr = new e[size];
        for (int i2 = 0; i2 < size; i2++) {
            e eVar = b2.get(i2);
            Class<?>[] k = eVar.k();
            if (k == null || k.length == 0) {
                if (a2) {
                    eVarArr[i2] = eVar;
                }
            } else {
                i++;
                eVarArr[i2] = a(eVar, k);
            }
        }
        if (a2 && i == 0) {
            return;
        }
        gVar.a(eVarArr);
    }

    private static void a(y yVar, List<com.a.a.c.f.s> list) {
        com.a.a.c.a j = yVar.j();
        HashMap hashMap = new HashMap();
        Iterator<com.a.a.c.f.s> it = list.iterator();
        while (it.hasNext()) {
            com.a.a.c.f.s next = it.next();
            if (next.s() == null) {
                it.remove();
            } else {
                Class<?> g = next.g();
                Boolean bool = (Boolean) hashMap.get(g);
                Boolean bool2 = bool;
                if (bool == null) {
                    Boolean f = yVar.d(g).f();
                    bool2 = f;
                    if (f == null) {
                        Boolean b2 = j.b(yVar.c(g).d());
                        bool2 = b2;
                        if (b2 == null) {
                            bool2 = Boolean.FALSE;
                        }
                    }
                    hashMap.put(g, bool2);
                }
                if (bool2.booleanValue()) {
                    it.remove();
                }
            }
        }
    }

    private static void a(List<com.a.a.c.f.s> list) {
        Iterator<com.a.a.c.f.s> it = list.iterator();
        while (it.hasNext()) {
            com.a.a.c.f.s next = it.next();
            if (!next.i() && !next.d()) {
                it.remove();
            }
        }
    }

    private static List<e> b(List<e> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            e eVar = list.get(i);
            com.a.a.c.i.i h = eVar.h();
            if (h != null && h.a() == af.a.EXTERNAL_PROPERTY) {
                com.a.a.c.w a2 = com.a.a.c.w.a(h.b());
                Iterator<e> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    e next = it.next();
                    if (next != eVar && next.a(a2)) {
                        eVar.a((com.a.a.c.i.i) null);
                        break;
                    }
                }
            }
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private e a(aa aaVar, com.a.a.c.f.s sVar, m mVar, boolean z, com.a.a.c.f.j jVar) {
        com.a.a.c.w b2 = sVar.b();
        com.a.a.c.j c = jVar.c();
        c.b bVar = new c.b(b2, c, sVar.c(), jVar, sVar.h());
        com.a.a.c.o<Object> a2 = a(aaVar, jVar);
        if (a2 instanceof q) {
            ((q) a2).a(aaVar);
        }
        com.a.a.c.o<?> a3 = aaVar.a((com.a.a.c.o<?>) a2, (com.a.a.c.c) bVar);
        com.a.a.c.i.i iVar = null;
        if (c.n() || c.F()) {
            iVar = b(c, aaVar.a(), jVar);
        }
        return mVar.a(aaVar, sVar, c, a3, a(c, aaVar.a(), jVar), iVar, jVar, z);
    }

    private static com.a.a.c.o<?> b(aa aaVar, com.a.a.c.j jVar) {
        String b2 = com.a.a.c.m.f.b(jVar);
        if (b2 != null && aaVar.a().i(jVar.b()) == null) {
            return new com.a.a.c.k.a.s(jVar, b2);
        }
        return null;
    }

    private static boolean b(com.a.a.c.j jVar) {
        Class<?> b2 = jVar.b();
        return com.a.a.c.s.class.isAssignableFrom(b2) || com.a.a.c.t.class.isAssignableFrom(b2) || u.class.isAssignableFrom(b2) || com.a.a.c.d.class.isAssignableFrom(b2) || com.a.a.b.v.class.isAssignableFrom(b2) || com.a.a.b.l.class.isAssignableFrom(b2) || com.a.a.b.h.class.isAssignableFrom(b2);
    }
}
