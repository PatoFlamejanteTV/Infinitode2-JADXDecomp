package com.a.a.c.f;

import com.a.a.a.b;
import com.a.a.a.i;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: infinitode-2.jar:com/a/a/c/f/ae.class */
public final class ae {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.b.q<?> f429a;

    /* renamed from: b, reason: collision with root package name */
    private a f430b;
    private boolean c;
    private com.a.a.c.j d;
    private d e;
    private ap<?> f;
    private com.a.a.c.a g;
    private boolean h;
    private boolean i;
    private LinkedHashMap<String, af> j;
    private LinkedList<af> k;
    private Map<com.a.a.c.w, com.a.a.c.w> l;
    private LinkedList<j> m;
    private LinkedList<j> n;
    private LinkedList<k> o;
    private LinkedList<j> p;
    private LinkedList<j> q;
    private LinkedList<j> r;
    private HashSet<String> s;
    private LinkedHashMap<Object, j> t;

    /* JADX INFO: Access modifiers changed from: protected */
    public ae(com.a.a.c.b.q<?> qVar, boolean z, com.a.a.c.j jVar, d dVar, a aVar) {
        this.f429a = qVar;
        this.c = z;
        this.d = jVar;
        this.e = dVar;
        if (qVar.f()) {
            this.h = true;
            this.g = this.f429a.j();
        } else {
            this.h = false;
            this.g = com.a.a.c.a.a();
        }
        this.f = this.f429a.a(jVar.b(), dVar);
        this.f430b = aVar;
        qVar.a(com.a.a.c.q.USE_STD_BEAN_NAMING);
    }

    public final com.a.a.c.b.q<?> a() {
        return this.f429a;
    }

    public final com.a.a.c.j b() {
        return this.d;
    }

    public final d c() {
        return this.e;
    }

    public final List<s> d() {
        return new ArrayList(n().values());
    }

    public final Map<Object, j> e() {
        if (!this.i) {
            o();
        }
        return this.t;
    }

    public final j f() {
        if (!this.i) {
            o();
        }
        if (this.q != null) {
            if (this.q.size() > 1) {
                a("Multiple 'as-key' properties defined (%s vs %s)", this.q.get(0), this.q.get(1));
            }
            return this.q.get(0);
        }
        return null;
    }

    public final j g() {
        if (!this.i) {
            o();
        }
        if (this.r != null) {
            if (this.r.size() > 1) {
                a("Multiple 'as-value' properties defined (%s vs %s)", this.r.get(0), this.r.get(1));
            }
            return this.r.get(0);
        }
        return null;
    }

    public final j h() {
        if (!this.i) {
            o();
        }
        if (this.n != null) {
            if (this.n.size() > 1) {
                a("Multiple 'any-getter' fields defined (%s vs %s)", this.n.get(0), this.n.get(1));
            }
            return this.n.getFirst();
        }
        return null;
    }

    public final j i() {
        if (!this.i) {
            o();
        }
        if (this.m != null) {
            if (this.m.size() > 1) {
                a("Multiple 'any-getter' methods defined (%s vs %s)", this.m.get(0), this.m.get(1));
            }
            return this.m.getFirst();
        }
        return null;
    }

    public final j j() {
        if (!this.i) {
            o();
        }
        if (this.p != null) {
            if (this.p.size() > 1) {
                a("Multiple 'any-setter' fields defined (%s vs %s)", this.p.get(0), this.p.get(1));
            }
            return this.p.getFirst();
        }
        return null;
    }

    public final k k() {
        if (!this.i) {
            o();
        }
        if (this.o != null) {
            if (this.o.size() > 1) {
                a("Multiple 'any-setter' methods defined (%s vs %s)", this.o.get(0), this.o.get(1));
            }
            return this.o.getFirst();
        }
        return null;
    }

    public final Set<String> l() {
        return this.s;
    }

    public final ad m() {
        ad a2 = this.g.a((b) this.e);
        ad adVar = a2;
        if (a2 != null) {
            adVar = this.g.a(this.e, adVar);
        }
        return adVar;
    }

    private Map<String, af> n() {
        if (!this.i) {
            o();
        }
        return this.j;
    }

    private void o() {
        LinkedHashMap<String, af> linkedHashMap = new LinkedHashMap<>();
        a(linkedHashMap);
        c(linkedHashMap);
        if (!this.e.m()) {
            b(linkedHashMap);
        }
        d(linkedHashMap);
        e(linkedHashMap);
        f(linkedHashMap);
        p();
        Iterator<af> it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            it.next().a(this.c);
        }
        com.a.a.c.x q = q();
        if (q != null) {
            a(linkedHashMap, q);
        }
        Iterator<af> it2 = linkedHashMap.values().iterator();
        while (it2.hasNext()) {
            it2.next().J();
        }
        if (this.f429a.a(com.a.a.c.q.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            g(linkedHashMap);
        }
        h(linkedHashMap);
        this.j = linkedHashMap;
        this.i = true;
    }

    private void a(Map<String, af> map) {
        com.a.a.c.w D;
        com.a.a.c.a aVar = this.g;
        boolean z = (this.c || this.f429a.a(com.a.a.c.q.ALLOW_FINAL_FIELDS_AS_MUTATORS)) ? false : true;
        boolean a2 = this.f429a.a(com.a.a.c.q.PROPAGATE_TRANSIENT_MARKER);
        for (h hVar : this.e.l()) {
            if (Boolean.TRUE.equals(aVar.w(hVar))) {
                if (this.q == null) {
                    this.q = new LinkedList<>();
                }
                this.q.add(hVar);
            }
            if (Boolean.TRUE.equals(aVar.x(hVar))) {
                if (this.r == null) {
                    this.r = new LinkedList<>();
                }
                this.r.add(hVar);
            } else {
                boolean equals = Boolean.TRUE.equals(aVar.y(hVar));
                boolean equals2 = Boolean.TRUE.equals(aVar.E(hVar));
                if (equals || equals2) {
                    if (equals) {
                        if (this.n == null) {
                            this.n = new LinkedList<>();
                        }
                        this.n.add(hVar);
                    }
                    if (equals2) {
                        if (this.p == null) {
                            this.p = new LinkedList<>();
                        }
                        this.p.add(hVar);
                    }
                } else {
                    String g = aVar.g((j) hVar);
                    String str = g;
                    if (g == null) {
                        str = hVar.b();
                    }
                    String b2 = this.f430b.b(str);
                    if (b2 != null) {
                        com.a.a.c.w b3 = b(b2);
                        com.a.a.c.w c = aVar.c();
                        if (c != null && !c.equals(b3)) {
                            if (this.l == null) {
                                this.l = new HashMap();
                            }
                            this.l.put(c, b3);
                        }
                        if (this.c) {
                            D = aVar.v(hVar);
                        } else {
                            D = aVar.D(hVar);
                        }
                        boolean z2 = D != null;
                        boolean z3 = z2;
                        boolean z4 = z2;
                        if (z2 && D.e()) {
                            D = b(b2);
                            z4 = false;
                        }
                        boolean z5 = D != null;
                        boolean z6 = z5;
                        if (!z5) {
                            z6 = this.f.a(hVar);
                        }
                        boolean d = aVar.d((j) hVar);
                        if (hVar.g() && !z3) {
                            z6 = false;
                            if (a2) {
                                d = true;
                            }
                        }
                        if (!z || D != null || d || !Modifier.isFinal(hVar.f())) {
                            a(map, b2).a(hVar, D, z4, z6, d);
                        }
                    }
                }
            }
        }
    }

    private void b(Map<String, af> map) {
        if (!this.h) {
            return;
        }
        for (f fVar : this.e.i()) {
            if (this.k == null) {
                this.k = new LinkedList<>();
            }
            int f = fVar.f();
            for (int i = 0; i < f; i++) {
                a(map, fVar.c(i));
            }
        }
        for (k kVar : this.e.j()) {
            if (this.k == null) {
                this.k = new LinkedList<>();
            }
            int f2 = kVar.f();
            for (int i2 = 0; i2 < f2; i2++) {
                a(map, kVar.c(i2));
            }
        }
    }

    private void a(Map<String, af> map, n nVar) {
        i.a a2;
        String g = this.g.g((j) nVar);
        String str = g;
        if (g == null) {
            str = "";
        }
        com.a.a.c.w D = this.g.D(nVar);
        com.a.a.c.w wVar = D;
        boolean z = (D == null || wVar.e()) ? false : true;
        boolean z2 = z;
        if (!z) {
            if (str.isEmpty() || (a2 = this.g.a(this.f429a, nVar.e())) == null || a2 == i.a.DISABLED) {
                return;
            } else {
                wVar = com.a.a.c.w.a(str);
            }
        }
        String c = c(str);
        af a3 = (z2 && c.isEmpty()) ? a(map, wVar) : a(map, c);
        a3.a(nVar, wVar, z2, true, false);
        this.k.add(a3);
    }

    private void c(Map<String, af> map) {
        for (k kVar : this.e.k()) {
            int f = kVar.f();
            if (f == 0) {
                a(map, kVar, this.g);
            } else if (f == 1) {
                b(map, kVar, this.g);
            } else if (f == 2 && Boolean.TRUE.equals(this.g.E(kVar))) {
                if (this.o == null) {
                    this.o = new LinkedList<>();
                }
                this.o.add(kVar);
            }
        }
    }

    private void a(Map<String, af> map, k kVar, com.a.a.c.a aVar) {
        String str;
        boolean z;
        Class<?> m = kVar.m();
        if (m != Void.TYPE) {
            if (m == Void.class && !this.f429a.a(com.a.a.c.q.ALLOW_VOID_VALUED_PROPERTIES)) {
                return;
            }
            if (Boolean.TRUE.equals(aVar.y(kVar))) {
                if (this.m == null) {
                    this.m = new LinkedList<>();
                }
                this.m.add(kVar);
                return;
            }
            if (Boolean.TRUE.equals(aVar.w(kVar))) {
                if (this.q == null) {
                    this.q = new LinkedList<>();
                }
                this.q.add(kVar);
                return;
            }
            if (Boolean.TRUE.equals(aVar.x(kVar))) {
                if (this.r == null) {
                    this.r = new LinkedList<>();
                }
                this.r.add(kVar);
                return;
            }
            com.a.a.c.w v = aVar.v(kVar);
            com.a.a.c.w wVar = v;
            boolean z2 = v != null;
            boolean z3 = z2;
            if (!z2) {
                String g = aVar.g((j) kVar);
                str = g;
                if (g == null) {
                    str = this.f430b.b(kVar, kVar.b());
                }
                if (str == null) {
                    String a2 = this.f430b.a(kVar, kVar.b());
                    str = a2;
                    if (a2 == null) {
                        return;
                    } else {
                        z = this.f.b(kVar);
                    }
                } else {
                    z = this.f.a(kVar);
                }
            } else {
                String g2 = aVar.g((j) kVar);
                str = g2;
                if (g2 == null) {
                    String b2 = this.f430b.b(kVar, kVar.b());
                    str = b2;
                    if (b2 == null) {
                        str = this.f430b.a(kVar, kVar.b());
                    }
                }
                if (str == null) {
                    str = kVar.b();
                }
                if (wVar.e()) {
                    wVar = b(str);
                    z3 = false;
                }
                z = true;
            }
            a(map, c(str)).a(kVar, wVar, z3, z, aVar.d((j) kVar));
        }
    }

    private void b(Map<String, af> map, k kVar, com.a.a.c.a aVar) {
        String str;
        boolean z;
        com.a.a.c.w D = aVar.D(kVar);
        com.a.a.c.w wVar = D;
        boolean z2 = D != null;
        boolean z3 = z2;
        if (!z2) {
            String g = aVar.g((j) kVar);
            str = g;
            if (g == null) {
                str = this.f430b.a(kVar.b());
            }
            if (str == null) {
                return;
            } else {
                z = this.f.c(kVar);
            }
        } else {
            String g2 = aVar.g((j) kVar);
            str = g2;
            if (g2 == null) {
                str = this.f430b.a(kVar.b());
            }
            if (str == null) {
                str = kVar.b();
            }
            if (wVar.e()) {
                wVar = b(str);
                z3 = false;
            }
            z = true;
        }
        a(map, c(str)).b(kVar, wVar, z3, z, aVar.d((j) kVar));
    }

    private void p() {
        for (j jVar : this.e.l()) {
            a(this.g.e(jVar), jVar);
        }
        for (k kVar : this.e.k()) {
            if (kVar.f() == 1) {
                a(this.g.e((j) kVar), kVar);
            }
        }
    }

    private void a(b.a aVar, j jVar) {
        if (aVar == null) {
            return;
        }
        Object a2 = aVar.a();
        if (this.t == null) {
            this.t = new LinkedHashMap<>();
        }
        j put = this.t.put(a2, jVar);
        if (put != null && put.getClass() == jVar.getClass()) {
            throw new IllegalArgumentException("Duplicate injectable value with id '" + a2 + "' (of type " + a2.getClass().getName() + ")");
        }
    }

    private static com.a.a.c.w b(String str) {
        return com.a.a.c.w.a(str, null);
    }

    private String c(String str) {
        com.a.a.c.w wVar;
        if (this.l != null && (wVar = this.l.get(b(str))) != null) {
            return wVar.b();
        }
        return str;
    }

    private void d(Map<String, af> map) {
        Iterator<af> it = map.values().iterator();
        while (it.hasNext()) {
            af next = it.next();
            if (!next.K()) {
                it.remove();
            } else if (next.L()) {
                if (!next.M()) {
                    it.remove();
                    a(next.a());
                } else {
                    next.H();
                    if (!next.i()) {
                        a(next.a());
                    }
                }
            }
        }
    }

    private void e(Map<String, af> map) {
        boolean a2 = this.f429a.a(com.a.a.c.q.INFER_PROPERTY_MUTATORS);
        Iterator<af> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().a(a2, this.c ? null : this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str) {
        if (!this.c && str != null) {
            if (this.s == null) {
                this.s = new HashSet<>();
            }
            this.s.add(str);
        }
    }

    private void f(Map<String, af> map) {
        Iterator<Map.Entry<String, af>> it = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            af value = it.next().getValue();
            Set<com.a.a.c.w> N = value.N();
            if (!N.isEmpty()) {
                it.remove();
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                if (N.size() != 1) {
                    linkedList.addAll(value.a(N));
                } else {
                    linkedList.add(value.b(N.iterator().next()));
                }
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                af afVar = (af) it2.next();
                String a2 = afVar.a();
                af afVar2 = map.get(a2);
                if (afVar2 == null) {
                    map.put(a2, afVar);
                } else {
                    afVar2.a(afVar);
                }
                if (a(afVar, this.k) && this.s != null) {
                    this.s.remove(a2);
                }
            }
        }
    }

    private void a(Map<String, af> map, com.a.a.c.x xVar) {
        String b2;
        af[] afVarArr = (af[]) map.values().toArray(new af[map.size()]);
        map.clear();
        for (af afVar : afVarArr) {
            af afVar2 = afVar;
            com.a.a.c.w b3 = afVar.b();
            String str = null;
            if (!afVar2.e() || this.f429a.a(com.a.a.c.q.ALLOW_EXPLICIT_PROPERTY_RENAMING)) {
                if (this.c) {
                    if (afVar2.D()) {
                        afVar2.n();
                        str = xVar.b(b3.b());
                    } else if (afVar2.l()) {
                        afVar2.p();
                        str = xVar.a(b3.b());
                    }
                } else if (afVar2.k()) {
                    afVar2.F();
                    str = xVar.c(b3.b());
                } else if (afVar2.m()) {
                    afVar2.q();
                    str = xVar.d(b3.b());
                } else if (afVar2.l()) {
                    afVar2.G();
                    str = xVar.a(b3.b());
                } else if (afVar2.D()) {
                    afVar2.E();
                    str = xVar.b(b3.b());
                }
            }
            if (str != null && !b3.c(str)) {
                afVar2 = afVar2.a(str);
                b2 = str;
            } else {
                b2 = b3.b();
            }
            af afVar3 = map.get(b2);
            if (afVar3 == null) {
                map.put(b2, afVar2);
            } else {
                afVar3.a(afVar2);
            }
            a(afVar2, this.k);
        }
    }

    private void g(Map<String, af> map) {
        com.a.a.c.w b2;
        Iterator<Map.Entry<String, af>> it = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            af value = it.next().getValue();
            if (value.v() != null && (b2 = com.a.a.c.a.b()) != null && b2.c() && !b2.equals(value.b())) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(value.b(b2));
                it.remove();
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                af afVar = (af) it2.next();
                String a2 = afVar.a();
                af afVar2 = map.get(a2);
                if (afVar2 == null) {
                    map.put(a2, afVar);
                } else {
                    afVar2.a(afVar);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v52, types: [java.util.Collection] */
    private void h(Map<String, af> map) {
        boolean booleanValue;
        Map<? extends Object, ? extends Object> linkedHashMap;
        LinkedList<af> linkedList;
        com.a.a.c.a aVar = this.g;
        Boolean u = aVar.u(this.e);
        if (u == null) {
            booleanValue = this.f429a.h();
        } else {
            booleanValue = u.booleanValue();
        }
        boolean z = booleanValue;
        boolean a2 = a(map.values());
        String[] e = aVar.e(this.e);
        if (!z && !a2 && this.k == null && e == null) {
            return;
        }
        int size = map.size();
        if (z) {
            linkedHashMap = new TreeMap();
        } else {
            linkedHashMap = new LinkedHashMap(size + size);
        }
        for (af afVar : map.values()) {
            linkedHashMap.put(afVar.a(), afVar);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(size + size);
        if (e != null) {
            int length = e.length;
            for (int i = 0; i < length; i++) {
                String str = e[i];
                af afVar2 = (af) linkedHashMap.remove(str);
                af afVar3 = afVar2;
                if (afVar2 == null) {
                    Iterator<af> it = map.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        af next = it.next();
                        if (str.equals(next.C())) {
                            afVar3 = next;
                            str = next.a();
                            break;
                        }
                    }
                }
                if (afVar3 != null) {
                    linkedHashMap2.put(str, afVar3);
                }
            }
        }
        if (a2) {
            TreeMap treeMap = new TreeMap();
            Iterator<Map.Entry<? extends Object, ? extends Object>> it2 = linkedHashMap.entrySet().iterator();
            while (it2.hasNext()) {
                af afVar4 = (af) it2.next().getValue();
                Integer b2 = afVar4.h().b();
                if (b2 != null) {
                    treeMap.put(b2, afVar4);
                    it2.remove();
                }
            }
            for (af afVar5 : treeMap.values()) {
                linkedHashMap2.put(afVar5.a(), afVar5);
            }
        }
        if (this.k != null && (!z || this.f429a.a(com.a.a.c.q.SORT_CREATOR_PROPERTIES_FIRST))) {
            if (z) {
                TreeMap treeMap2 = new TreeMap();
                Iterator<af> it3 = this.k.iterator();
                while (it3.hasNext()) {
                    af next2 = it3.next();
                    treeMap2.put(next2.a(), next2);
                }
                linkedList = treeMap2.values();
            } else {
                linkedList = this.k;
            }
            for (af afVar6 : linkedList) {
                String a3 = afVar6.a();
                if (linkedHashMap.containsKey(a3)) {
                    linkedHashMap2.put(a3, afVar6);
                }
            }
        }
        linkedHashMap2.putAll(linkedHashMap);
        map.clear();
        map.putAll(linkedHashMap2);
    }

    private static boolean a(Collection<af> collection) {
        Iterator<af> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next().h().c()) {
                return true;
            }
        }
        return false;
    }

    private void a(String str, Object... objArr) {
        throw new IllegalArgumentException("Problem with definition of " + this.e + ": " + String.format(str, objArr));
    }

    private af a(Map<String, af> map, com.a.a.c.w wVar) {
        String b2 = wVar.b();
        af afVar = map.get(b2);
        af afVar2 = afVar;
        if (afVar == null) {
            afVar2 = new af(this.f429a, this.g, this.c, wVar);
            map.put(b2, afVar2);
        }
        return afVar2;
    }

    private af a(Map<String, af> map, String str) {
        af afVar = map.get(str);
        af afVar2 = afVar;
        if (afVar == null) {
            afVar2 = new af(this.f429a, this.g, this.c, com.a.a.c.w.a(str));
            map.put(str, afVar2);
        }
        return afVar2;
    }

    private com.a.a.c.x q() {
        com.a.a.c.x l;
        Object c = this.g.c(this.e);
        if (c == null) {
            return this.f429a.k();
        }
        if (c instanceof com.a.a.c.x) {
            return (com.a.a.c.x) c;
        }
        if (!(c instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned PropertyNamingStrategy definition of type " + c.getClass().getName() + "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
        }
        Class cls = (Class) c;
        if (cls != com.a.a.c.x.class) {
            if (!com.a.a.c.x.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<PropertyNamingStrategy>");
            }
            if (this.f429a.m() != null && (l = com.a.a.c.k.a.d.l()) != null) {
                return l;
            }
            return (com.a.a.c.x) com.a.a.c.m.i.b(cls, this.f429a.g());
        }
        return null;
    }

    private static boolean a(af afVar, List<af> list) {
        if (list != null) {
            String C = afVar.C();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).C().equals(C)) {
                    list.set(i, afVar);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
