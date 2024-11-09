package com.a.a.c.c.b;

import com.a.a.a.q;
import com.a.a.a.t;
import com.a.a.c.c.a.z;
import com.a.a.c.m.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/u.class */
public final class u extends i<Map<Object, Object>> implements com.a.a.c.c.k, com.a.a.c.c.t {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.p f364a;
    private boolean f;
    private com.a.a.c.k<Object> g;
    private com.a.a.c.i.e h;
    private com.a.a.c.c.x i;
    private com.a.a.c.k<Object> j;
    private com.a.a.c.c.a.v k;
    private boolean l;
    private Set<String> m;
    private Set<String> n;
    private n.a o;
    private boolean p;

    public u(com.a.a.c.j jVar, com.a.a.c.c.x xVar, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        super(jVar, (com.a.a.c.c.s) null, (Boolean) null);
        this.f364a = pVar;
        this.g = kVar;
        this.h = eVar;
        this.i = xVar;
        this.l = xVar.l();
        this.j = null;
        this.k = null;
        this.f = a(jVar, pVar);
        this.o = null;
        this.p = jVar.u().a(Object.class);
    }

    private u(u uVar, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar, Set<String> set, Set<String> set2) {
        super(uVar, sVar, uVar.e);
        this.f364a = pVar;
        this.g = kVar;
        this.h = eVar;
        this.i = uVar.i;
        this.k = uVar.k;
        this.j = uVar.j;
        this.l = uVar.l;
        this.m = set;
        this.n = set2;
        this.o = com.a.a.c.m.n.a(set, set2);
        this.f = a(this.f344b, pVar);
        this.p = uVar.p;
    }

    private u a(com.a.a.c.p pVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar, Set<String> set, Set<String> set2) {
        if (this.f364a == pVar && this.g == kVar && this.h == eVar && this.c == sVar && this.m == set && this.n == set2) {
            return this;
        }
        return new u(this, pVar, kVar, eVar, sVar, set, set2);
    }

    private boolean a(com.a.a.c.j jVar, com.a.a.c.p pVar) {
        com.a.a.c.j t;
        if (pVar == null || (t = jVar.t()) == null) {
            return true;
        }
        Class<?> b2 = t.b();
        return (b2 == String.class || b2 == Object.class) && a(pVar);
    }

    public final void a(Set<String> set) {
        this.m = (set == null || set.isEmpty()) ? null : set;
        this.o = com.a.a.c.m.n.a(this.m, this.n);
    }

    public final void b(Set<String> set) {
        this.n = set;
        this.o = com.a.a.c.m.n.a(this.m, this.n);
    }

    @Override // com.a.a.c.c.t
    public final void d(com.a.a.c.g gVar) {
        if (this.i.m()) {
            com.a.a.c.c.x xVar = this.i;
            gVar.a();
            com.a.a.c.j p = xVar.p();
            if (p == null) {
                gVar.a(this.f344b, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this.f344b, this.i.getClass().getName()));
            }
            this.j = a(gVar, p, (com.a.a.c.c) null);
        } else if (this.i.n()) {
            com.a.a.c.c.x xVar2 = this.i;
            gVar.a();
            com.a.a.c.j q = xVar2.q();
            if (q == null) {
                gVar.a(this.f344b, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this.f344b, this.i.getClass().getName()));
            }
            this.j = a(gVar, q, (com.a.a.c.c) null);
        }
        if (this.i.o()) {
            this.k = com.a.a.c.c.a.v.a(gVar, this.i, this.i.a(gVar.a()), gVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
        }
        this.f = a(this.f344b, this.f364a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.p a2;
        com.a.a.c.k<?> b2;
        com.a.a.c.f.j e;
        Set<String> b3;
        com.a.a.c.p pVar = this.f364a;
        ?? r12 = pVar;
        if (pVar == null) {
            a2 = gVar.b(this.f344b.t(), cVar);
        } else {
            boolean z = r12 instanceof com.a.a.c.c.l;
            a2 = r12;
            if (z) {
                a2 = ((com.a.a.c.c.l) r12).a();
            }
        }
        com.a.a.c.k<?> kVar = this.g;
        if (cVar != null) {
            kVar = a(gVar, cVar, kVar);
        }
        com.a.a.c.j u = this.f344b.u();
        if (kVar == null) {
            b2 = gVar.a(u, cVar);
        } else {
            b2 = gVar.b(kVar, cVar, u);
        }
        com.a.a.c.i.e eVar = this.h;
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar != null) {
            eVar2 = eVar2.a(cVar);
        }
        Set<String> set = this.m;
        Set<String> set2 = this.n;
        com.a.a.c.a f = gVar.f();
        if (b(f, cVar) && (e = cVar.e()) != null) {
            gVar.a();
            q.a b4 = f.b((com.a.a.c.f.b) e);
            if (b4 != null) {
                Set<String> c = b4.c();
                if (!c.isEmpty()) {
                    set = set == null ? new HashSet() : new HashSet(set);
                    Iterator<String> it = c.iterator();
                    while (it.hasNext()) {
                        set.add(it.next());
                    }
                }
            }
            t.a c2 = f.c((com.a.a.c.f.b) e);
            if (c2 != null && (b3 = c2.b()) != null) {
                HashSet hashSet = new HashSet();
                if (set2 == null) {
                    hashSet = new HashSet(b3);
                } else {
                    for (String str : b3) {
                        if (set2.contains(str)) {
                            hashSet.add(str);
                        }
                    }
                }
                set2 = hashSet;
            }
        }
        return a(a2, eVar2, b2, b(gVar, cVar, b2), set, set2);
    }

    @Override // com.a.a.c.c.b.i
    public final com.a.a.c.k<Object> g() {
        return this.g;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.c.x i() {
        return this.i;
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return this.g == null && this.f364a == null && this.h == null && this.m == null && this.n == null;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Map<Object, Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.k != null) {
            return e(lVar, gVar);
        }
        if (this.j != null) {
            return (Map) this.i.a(gVar, this.j.a(lVar, gVar));
        }
        if (!this.l) {
            return (Map) gVar.a(j(), i(), lVar, "no default constructor found", new Object[0]);
        }
        switch (lVar.l()) {
            case 1:
            case 2:
            case 5:
                Map<Object, Object> map = (Map) this.i.a(gVar);
                if (this.f) {
                    return c(lVar, gVar, map);
                }
                return b(lVar, gVar, map);
            case 3:
                return d(lVar, gVar);
            case 4:
            default:
                return (Map) gVar.a(e(gVar), lVar);
            case 6:
                return m(lVar, gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    public Map<Object, Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<Object, Object> map) {
        lVar.a(map);
        com.a.a.b.o k = lVar.k();
        if (k != com.a.a.b.o.START_OBJECT && k != com.a.a.b.o.FIELD_NAME) {
            return (Map) gVar.a(j(), lVar);
        }
        if (this.f) {
            e(lVar, gVar, map);
            return map;
        }
        d(lVar, gVar, map);
        return map;
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.a(lVar, gVar);
    }

    private Class<?> j() {
        return this.f344b.b();
    }

    @Override // com.a.a.c.c.b.i, com.a.a.c.c.b.ae
    public final com.a.a.c.j h() {
        return this.f344b;
    }

    private Map<Object, Object> b(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<Object, Object> map) {
        String v;
        Object a2;
        com.a.a.c.p pVar = this.f364a;
        com.a.a.c.k<Object> kVar = this.g;
        com.a.a.c.i.e eVar = this.h;
        b bVar = null;
        boolean z = kVar.f() != null;
        boolean z2 = z;
        if (z) {
            bVar = new b(this.f344b.u().b(), map);
        }
        if (lVar.q()) {
            v = lVar.h();
        } else {
            com.a.a.b.o k = lVar.k();
            if (k != com.a.a.b.o.FIELD_NAME) {
                if (k == com.a.a.b.o.END_OBJECT) {
                    return map;
                }
                gVar.a(this, com.a.a.b.o.FIELD_NAME, (String) null, new Object[0]);
            }
            v = lVar.v();
        }
        while (true) {
            String str = v;
            if (str != null) {
                Object a3 = pVar.a(str, gVar);
                com.a.a.b.o g = lVar.g();
                if (this.o != null && this.o.a(str)) {
                    lVar.j();
                } else {
                    try {
                        if (g == com.a.a.b.o.VALUE_NULL) {
                            if (!this.d) {
                                a2 = this.c.a(gVar);
                            }
                        } else if (eVar == null) {
                            a2 = kVar.a(lVar, gVar);
                        } else {
                            a2 = kVar.a(lVar, gVar, eVar);
                        }
                        if (z2) {
                            bVar.a(a3, a2);
                        } else {
                            Object put = map.put(a3, a2);
                            if (put != null) {
                                a(gVar, map, a3, put, a2);
                            }
                        }
                    } catch (com.a.a.c.c.w e) {
                        a(gVar, bVar, a3, e);
                    } catch (Exception e2) {
                        a(gVar, e2, map, str);
                    }
                }
                v = lVar.h();
            } else {
                return map;
            }
        }
    }

    private Map<Object, Object> c(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<Object, Object> map) {
        String v;
        Object a2;
        com.a.a.c.k<Object> kVar = this.g;
        com.a.a.c.i.e eVar = this.h;
        b bVar = null;
        boolean z = kVar.f() != null;
        boolean z2 = z;
        if (z) {
            bVar = new b(this.f344b.u().b(), map);
        }
        if (lVar.q()) {
            v = lVar.h();
        } else {
            com.a.a.b.o k = lVar.k();
            if (k == com.a.a.b.o.END_OBJECT) {
                return map;
            }
            if (k != com.a.a.b.o.FIELD_NAME) {
                gVar.a(this, com.a.a.b.o.FIELD_NAME, (String) null, new Object[0]);
            }
            v = lVar.v();
        }
        while (true) {
            String str = v;
            if (str != null) {
                com.a.a.b.o g = lVar.g();
                if (this.o != null && this.o.a(str)) {
                    lVar.j();
                } else {
                    try {
                        if (g == com.a.a.b.o.VALUE_NULL) {
                            if (!this.d) {
                                a2 = this.c.a(gVar);
                            }
                        } else if (eVar == null) {
                            a2 = kVar.a(lVar, gVar);
                        } else {
                            a2 = kVar.a(lVar, gVar, eVar);
                        }
                        if (z2) {
                            bVar.a(str, a2);
                        } else {
                            Object put = map.put(str, a2);
                            if (put != null) {
                                a(gVar, map, str, put, a2);
                            }
                        }
                    } catch (com.a.a.c.c.w e) {
                        a(gVar, bVar, str, e);
                    } catch (Exception e2) {
                        a(gVar, e2, map, str);
                    }
                }
                v = lVar.h();
            } else {
                return map;
            }
        }
    }

    private Map<Object, Object> e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String str;
        Object a2;
        com.a.a.c.c.a.v vVar = this.k;
        com.a.a.c.c.a.y a3 = vVar.a(lVar, gVar, null);
        com.a.a.c.k<Object> kVar = this.g;
        com.a.a.c.i.e eVar = this.h;
        if (lVar.q()) {
            str = lVar.h();
        } else if (lVar.a(com.a.a.b.o.FIELD_NAME)) {
            str = lVar.v();
        } else {
            str = null;
        }
        while (true) {
            String str2 = str;
            if (str2 != null) {
                com.a.a.b.o g = lVar.g();
                if (this.o != null && this.o.a(str2)) {
                    lVar.j();
                } else {
                    com.a.a.c.c.v a4 = vVar.a(str2);
                    if (a4 != null) {
                        if (a3.a(a4, a4.a(lVar, gVar))) {
                            lVar.g();
                            try {
                                return b(lVar, gVar, (Map<Object, Object>) vVar.a(gVar, a3));
                            } catch (Exception e) {
                                return (Map) a(gVar, e, this.f344b.b(), str2);
                            }
                        }
                    } else {
                        Object a5 = this.f364a.a(str2, gVar);
                        try {
                            if (g == com.a.a.b.o.VALUE_NULL) {
                                if (!this.d) {
                                    a2 = this.c.a(gVar);
                                }
                            } else if (eVar == null) {
                                a2 = kVar.a(lVar, gVar);
                            } else {
                                a2 = kVar.a(lVar, gVar, eVar);
                            }
                            a3.a(a5, a2);
                        } catch (Exception e2) {
                            a(gVar, e2, this.f344b.b(), str2);
                            return null;
                        }
                    }
                }
                str = lVar.h();
            } else {
                try {
                    return (Map) vVar.a(gVar, a3);
                } catch (Exception e3) {
                    a(gVar, e3, this.f344b.b(), str2);
                    return null;
                }
            }
        }
    }

    private void d(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<Object, Object> map) {
        String v;
        Object a2;
        com.a.a.c.p pVar = this.f364a;
        com.a.a.c.k<Object> kVar = this.g;
        com.a.a.c.i.e eVar = this.h;
        if (lVar.q()) {
            v = lVar.h();
        } else {
            com.a.a.b.o k = lVar.k();
            if (k == com.a.a.b.o.END_OBJECT) {
                return;
            }
            if (k != com.a.a.b.o.FIELD_NAME) {
                gVar.a(this, com.a.a.b.o.FIELD_NAME, (String) null, new Object[0]);
            }
            v = lVar.v();
        }
        while (true) {
            String str = v;
            if (str != null) {
                Object a3 = pVar.a(str, gVar);
                com.a.a.b.o g = lVar.g();
                if (this.o != null && this.o.a(str)) {
                    lVar.j();
                } else {
                    try {
                        if (g == com.a.a.b.o.VALUE_NULL) {
                            if (!this.d) {
                                map.put(a3, this.c.a(gVar));
                            }
                        } else {
                            Object obj = map.get(a3);
                            if (obj != null) {
                                if (eVar == null) {
                                    a2 = kVar.a(lVar, gVar, (com.a.a.c.g) obj);
                                } else {
                                    a2 = kVar.b(lVar, gVar, eVar);
                                }
                            } else if (eVar == null) {
                                a2 = kVar.a(lVar, gVar);
                            } else {
                                a2 = kVar.a(lVar, gVar, eVar);
                            }
                            if (a2 != obj) {
                                map.put(a3, a2);
                            }
                        }
                    } catch (Exception e) {
                        a(gVar, e, map, str);
                    }
                }
                v = lVar.h();
            } else {
                return;
            }
        }
    }

    private void e(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<Object, Object> map) {
        String v;
        Object a2;
        com.a.a.c.k<Object> kVar = this.g;
        com.a.a.c.i.e eVar = this.h;
        if (lVar.q()) {
            v = lVar.h();
        } else {
            com.a.a.b.o k = lVar.k();
            if (k == com.a.a.b.o.END_OBJECT) {
                return;
            }
            if (k != com.a.a.b.o.FIELD_NAME) {
                gVar.a(this, com.a.a.b.o.FIELD_NAME, (String) null, new Object[0]);
            }
            v = lVar.v();
        }
        while (true) {
            String str = v;
            if (str != null) {
                com.a.a.b.o g = lVar.g();
                if (this.o != null && this.o.a(str)) {
                    lVar.j();
                } else {
                    try {
                        if (g == com.a.a.b.o.VALUE_NULL) {
                            if (!this.d) {
                                map.put(str, this.c.a(gVar));
                            }
                        } else {
                            Object obj = map.get(str);
                            if (obj != null) {
                                if (eVar == null) {
                                    a2 = kVar.a(lVar, gVar, (com.a.a.c.g) obj);
                                } else {
                                    a2 = kVar.b(lVar, gVar, eVar);
                                }
                            } else if (eVar == null) {
                                a2 = kVar.a(lVar, gVar);
                            } else {
                                a2 = kVar.a(lVar, gVar, eVar);
                            }
                            if (a2 != obj) {
                                map.put(str, a2);
                            }
                        }
                    } catch (Exception e) {
                        a(gVar, e, map, str);
                    }
                }
                v = lVar.h();
            } else {
                return;
            }
        }
    }

    private void a(com.a.a.c.g gVar, Map<Object, Object> map, Object obj, Object obj2, Object obj3) {
        if (this.p && gVar.a(com.a.a.b.s.DUPLICATE_PROPERTIES)) {
            if (obj2 instanceof List) {
                ((List) obj2).add(obj3);
                map.put(obj, obj2);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(obj2);
                arrayList.add(obj3);
                map.put(obj, arrayList);
            }
        }
    }

    private void a(com.a.a.c.g gVar, b bVar, Object obj, com.a.a.c.c.w wVar) {
        if (bVar == null) {
            gVar.a(this, "Unresolved forward reference but no identity info: " + wVar, new Object[0]);
        }
        wVar.d().a(bVar.a(wVar, obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/u$b.class */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?> f367a;

        /* renamed from: b, reason: collision with root package name */
        private Map<Object, Object> f368b;
        private List<a> c = new ArrayList();

        public b(Class<?> cls, Map<Object, Object> map) {
            this.f367a = cls;
            this.f368b = map;
        }

        public final void a(Object obj, Object obj2) {
            if (this.c.isEmpty()) {
                this.f368b.put(obj, obj2);
            } else {
                this.c.get(this.c.size() - 1).f365a.put(obj, obj2);
            }
        }

        public final z.a a(com.a.a.c.c.w wVar, Object obj) {
            a aVar = new a(this, wVar, this.f367a, obj);
            this.c.add(aVar);
            return aVar;
        }

        public final void b(Object obj, Object obj2) {
            Iterator<a> it = this.c.iterator();
            Map<Object, Object> map = this.f368b;
            while (true) {
                Map<Object, Object> map2 = map;
                if (it.hasNext()) {
                    a next = it.next();
                    if (next.b(obj)) {
                        it.remove();
                        map2.put(next.f366b, obj2);
                        map2.putAll(next.f365a);
                        return;
                    }
                    map = next.f365a;
                } else {
                    throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/u$a.class */
    public static class a extends z.a {
        private final b c;

        /* renamed from: a, reason: collision with root package name */
        public final Map<Object, Object> f365a;

        /* renamed from: b, reason: collision with root package name */
        public final Object f366b;

        a(b bVar, com.a.a.c.c.w wVar, Class<?> cls, Object obj) {
            super(wVar, cls);
            this.f365a = new LinkedHashMap();
            this.c = bVar;
            this.f366b = obj;
        }

        @Override // com.a.a.c.c.a.z.a
        public final void a(Object obj, Object obj2) {
            this.c.b(obj, obj2);
        }
    }
}
