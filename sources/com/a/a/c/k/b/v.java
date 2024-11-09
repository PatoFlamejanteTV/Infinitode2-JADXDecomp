package com.a.a.c.k.b;

import com.a.a.a.l;
import com.a.a.a.s;
import com.a.a.c.k.a.k;
import com.a.a.c.m.n;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/v.class */
public class v extends com.a.a.c.k.j<Map<?, ?>> implements com.a.a.c.k.k {

    /* renamed from: b, reason: collision with root package name */
    private static com.a.a.c.j f633b = com.a.a.c.l.o.b();

    /* renamed from: a, reason: collision with root package name */
    public static final Object f634a = s.a.NON_EMPTY;
    private com.a.a.c.c c;
    private boolean d;
    private com.a.a.c.j e;
    private com.a.a.c.j f;
    private com.a.a.c.o<Object> g;
    private com.a.a.c.o<Object> i;
    private com.a.a.c.i.i j;
    private com.a.a.c.k.a.k k;
    private Set<String> l;
    private Set<String> m;
    private Object n;
    private Object o;
    private boolean p;
    private n.a q;
    private boolean r;

    @Override // com.a.a.c.k.j
    public final /* bridge */ /* synthetic */ boolean a(Map<?, ?> map) {
        return a2(map);
    }

    private v(Set<String> set, Set<String> set2, com.a.a.c.j jVar, com.a.a.c.j jVar2, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, com.a.a.c.o<?> oVar2) {
        super(Map.class, false);
        this.l = (set == null || set.isEmpty()) ? null : set;
        this.m = set2;
        this.e = jVar;
        this.f = jVar2;
        this.d = z;
        this.j = iVar;
        this.g = oVar;
        this.i = oVar2;
        this.k = com.a.a.c.k.a.k.a();
        this.c = null;
        this.n = null;
        this.r = false;
        this.o = null;
        this.p = false;
        this.q = com.a.a.c.m.n.a(this.l, this.m);
    }

    private v(v vVar, com.a.a.c.c cVar, com.a.a.c.o<?> oVar, com.a.a.c.o<?> oVar2, Set<String> set, Set<String> set2) {
        super(Map.class, false);
        this.l = (set == null || set.isEmpty()) ? null : set;
        this.m = set2;
        this.e = vVar.e;
        this.f = vVar.f;
        this.d = vVar.d;
        this.j = vVar.j;
        this.g = oVar;
        this.i = oVar2;
        this.k = com.a.a.c.k.a.k.a();
        this.c = cVar;
        this.n = vVar.n;
        this.r = vVar.r;
        this.o = vVar.o;
        this.p = vVar.p;
        this.q = com.a.a.c.m.n.a(this.l, this.m);
    }

    private v(v vVar, com.a.a.c.i.i iVar, Object obj, boolean z) {
        super(Map.class, false);
        this.l = vVar.l;
        this.m = vVar.m;
        this.e = vVar.e;
        this.f = vVar.f;
        this.d = vVar.d;
        this.j = iVar;
        this.g = vVar.g;
        this.i = vVar.i;
        this.k = vVar.k;
        this.c = vVar.c;
        this.n = vVar.n;
        this.r = vVar.r;
        this.o = obj;
        this.p = z;
        this.q = vVar.q;
    }

    private v(v vVar, Object obj, boolean z) {
        super(Map.class, false);
        this.l = vVar.l;
        this.m = vVar.m;
        this.e = vVar.e;
        this.f = vVar.f;
        this.d = vVar.d;
        this.j = vVar.j;
        this.g = vVar.g;
        this.i = vVar.i;
        this.k = com.a.a.c.k.a.k.a();
        this.c = vVar.c;
        this.n = obj;
        this.r = z;
        this.o = vVar.o;
        this.p = vVar.p;
        this.q = vVar.q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.j
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public v b(com.a.a.c.i.i iVar) {
        if (this.j == iVar) {
            return this;
        }
        a("_withValueTypeSerializer");
        return new v(this, iVar, this.o, this.p);
    }

    private v a(com.a.a.c.c cVar, com.a.a.c.o<?> oVar, com.a.a.c.o<?> oVar2, Set<String> set, Set<String> set2, boolean z) {
        a("withResolved");
        v vVar = new v(this, cVar, oVar, oVar2, set, set2);
        if (z != vVar.r) {
            vVar = new v(vVar, this.n, z);
        }
        return vVar;
    }

    private v b(Object obj) {
        if (this.n == obj) {
            return this;
        }
        a("withFilterId");
        return new v(this, obj, this.r);
    }

    public final v a(Object obj, boolean z) {
        if (obj == this.o && z == this.p) {
            return this;
        }
        a("withContentInclusion");
        return new v(this, this.j, obj, z);
    }

    public static v a(Set<String> set, Set<String> set2, com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar, com.a.a.c.o<Object> oVar2, Object obj) {
        com.a.a.c.j t;
        com.a.a.c.j u;
        if (jVar == null) {
            com.a.a.c.j jVar2 = f633b;
            u = jVar2;
            t = jVar2;
        } else {
            t = jVar.t();
            if (jVar.a(Properties.class)) {
                u = com.a.a.c.l.o.b();
            } else {
                u = jVar.u();
            }
        }
        if (!z) {
            z = u != null && u.m();
        } else if (u.b() == Object.class) {
            z = false;
        }
        v vVar = new v(set, set2, t, u, z, iVar, oVar, oVar2);
        if (obj != null) {
            vVar = vVar.b(obj);
        }
        return vVar;
    }

    public static v a(Set<String> set, com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar, com.a.a.c.o<Object> oVar2, Object obj) {
        return a(set, null, jVar, z, iVar, oVar, oVar2, obj);
    }

    private void a(String str) {
        com.a.a.c.m.i.a((Class<?>) v.class, this, str);
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<?> b2;
        s.a c;
        Object obj;
        boolean z;
        Object d;
        Boolean a2;
        com.a.a.c.o<Object> oVar = null;
        com.a.a.c.o<Object> oVar2 = null;
        com.a.a.c.a d2 = aaVar.d();
        com.a.a.c.f.j e = cVar == null ? null : cVar.e();
        com.a.a.c.f.j jVar = e;
        if (a(e, d2)) {
            Object o = d2.o(jVar);
            if (o != null) {
                oVar2 = aaVar.b(jVar, o);
            }
            Object p = d2.p(jVar);
            if (p != null) {
                oVar = aaVar.b(jVar, p);
            }
        }
        if (oVar == null) {
            oVar = this.i;
        }
        com.a.a.c.o<?> a3 = a(aaVar, cVar, (com.a.a.c.o<?>) oVar);
        com.a.a.c.o<?> oVar3 = a3;
        if (a3 == null && this.d && !this.f.q()) {
            oVar3 = aaVar.c(this.f, cVar);
        }
        if (oVar2 == null) {
            oVar2 = this.g;
        }
        if (oVar2 == null) {
            b2 = aaVar.d(this.e, cVar);
        } else {
            b2 = aaVar.b((com.a.a.c.o<?>) oVar2, cVar);
        }
        Set<String> set = this.l;
        Set<String> set2 = this.m;
        boolean z2 = false;
        if (a(jVar, d2)) {
            aaVar.a();
            Set<String> b3 = d2.b((com.a.a.c.f.b) jVar).b();
            if (a((Collection<?>) b3)) {
                set = set == null ? new HashSet() : new HashSet(set);
                Iterator<String> it = b3.iterator();
                while (it.hasNext()) {
                    set.add(it.next());
                }
            }
            Set<String> b4 = d2.c((com.a.a.c.f.b) jVar).b();
            if (b4 != null) {
                set2 = set2 == null ? new HashSet() : new HashSet(set2);
                Iterator<String> it2 = b4.iterator();
                while (it2.hasNext()) {
                    set2.add(it2.next());
                }
            }
            z2 = Boolean.TRUE.equals(d2.u(jVar));
        }
        l.d a4 = a(aaVar, cVar, (Class<?>) Map.class);
        if (a4 != null && (a2 = a4.a(l.a.WRITE_SORTED_MAP_ENTRIES)) != null) {
            z2 = a2.booleanValue();
        }
        v a5 = a(cVar, b2, oVar3, set, set2, z2);
        if (jVar != null && (d = d2.d((com.a.a.c.f.b) jVar)) != null) {
            a5 = a5.b(d);
        }
        s.b b5 = b(aaVar, cVar, (Class<?>) Map.class);
        if (b5 != null && (c = b5.c()) != s.a.USE_DEFAULTS) {
            switch (w.f635a[c.ordinal()]) {
                case 1:
                    obj = com.a.a.c.m.f.a(this.f);
                    z = true;
                    if (obj != null && obj.getClass().isArray()) {
                        obj = com.a.a.c.m.c.a(obj);
                        break;
                    }
                    break;
                case 2:
                    z = true;
                    obj = this.f.F() ? f634a : null;
                    break;
                case 3:
                    z = true;
                    obj = f634a;
                    break;
                case 4:
                    Object a6 = aaVar.a((com.a.a.c.f.s) null, b5.e());
                    obj = a6;
                    if (a6 == null) {
                        z = true;
                        break;
                    } else {
                        z = aaVar.b(obj);
                        break;
                    }
                case 5:
                    obj = null;
                    z = true;
                    break;
                default:
                    obj = null;
                    z = false;
                    break;
            }
            a5 = a5.a(obj, z);
        }
        return a5;
    }

    public final com.a.a.c.j d() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.o
    public boolean a(com.a.a.c.aa aaVar, Map<?, ?> map) {
        if (map.isEmpty()) {
            return true;
        }
        Object obj = this.o;
        if (obj == null && !this.p) {
            return false;
        }
        com.a.a.c.o<Object> oVar = this.i;
        boolean z = f634a == obj;
        if (oVar != null) {
            for (Object obj2 : map.values()) {
                if (obj2 == null) {
                    if (!this.p) {
                        return false;
                    }
                } else if (z) {
                    if (!oVar.a(aaVar, obj2)) {
                        return false;
                    }
                } else if (obj == null || !obj.equals(map)) {
                    return false;
                }
            }
            return true;
        }
        for (Object obj3 : map.values()) {
            if (obj3 == null) {
                if (!this.p) {
                    return false;
                }
            } else {
                try {
                    com.a.a.c.o<Object> b2 = b(aaVar, obj3);
                    if (z) {
                        if (!b2.a(aaVar, obj3)) {
                            return false;
                        }
                    } else if (obj == null || !obj.equals(map)) {
                        return false;
                    }
                } catch (com.a.a.c.e unused) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    private static boolean a2(Map<?, ?> map) {
        return map.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        hVar.c(map);
        a(map, hVar, aaVar);
        hVar.j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.o
    public void a(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        hVar.a(map);
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(map, com.a.a.b.o.START_OBJECT));
        a(map, hVar, aaVar);
        iVar.b(hVar, a2);
    }

    public final void a(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        com.a.a.c.k.o a2;
        if (!map.isEmpty()) {
            if (this.r || aaVar.a(com.a.a.c.z.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = d(map, hVar, aaVar);
            }
            if (this.n != null && (a2 = a(aaVar, this.n, map)) != null) {
                a(map, hVar, aaVar, a2, this.o);
                return;
            }
            if (this.o != null || this.p) {
                a(map, hVar, aaVar, this.o);
            } else if (this.i != null) {
                a(map, hVar, aaVar, this.i);
            } else {
                c(map, hVar, aaVar);
            }
        }
    }

    private void c(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (this.j != null) {
            b(map, hVar, aaVar, null);
            return;
        }
        com.a.a.c.o<Object> oVar = this.g;
        Object obj = null;
        try {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object value = entry.getValue();
                Object key = entry.getKey();
                obj = key;
                if (key == null) {
                    aaVar.l().a(null, hVar, aaVar);
                } else if (this.q == null || !this.q.a(obj)) {
                    oVar.a(obj, hVar, aaVar);
                }
                if (value == null) {
                    aaVar.a(hVar);
                } else {
                    com.a.a.c.o<Object> oVar2 = this.i;
                    com.a.a.c.o<Object> oVar3 = oVar2;
                    if (oVar2 == null) {
                        oVar3 = b(aaVar, value);
                    }
                    oVar3.a(value, hVar, aaVar);
                }
            }
        } catch (Exception e) {
            a(aaVar, e, map, String.valueOf(obj));
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:12|(2:54|55)(2:14|(1:19)(2:52|34))|20|(3:46|47|(2:49|50)(2:51|34))(5:22|23|(1:25)|26|(3:42|43|(2:45|34))(3:28|29|(2:33|34)))|35|36|38|34|10) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d8, code lost:            r13 = move-exception;     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00da, code lost:            a(r9, r13, r7, java.lang.String.valueOf(r0));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.util.Map<?, ?> r7, com.a.a.b.h r8, com.a.a.c.aa r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.k.b.v.a(java.util.Map, com.a.a.b.h, com.a.a.c.aa, java.lang.Object):void");
    }

    private void a(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.o<Object> oVar) {
        com.a.a.c.o<Object> oVar2 = this.g;
        com.a.a.c.i.i iVar = this.j;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (this.q == null || !this.q.a(key)) {
                if (key == null) {
                    aaVar.l().a(null, hVar, aaVar);
                } else {
                    oVar2.a(key, hVar, aaVar);
                }
                Object value = entry.getValue();
                if (value == null) {
                    aaVar.a(hVar);
                } else if (iVar == null) {
                    try {
                        oVar.a(value, hVar, aaVar);
                    } catch (Exception e) {
                        a(aaVar, e, map, String.valueOf(key));
                    }
                } else {
                    oVar.a(value, hVar, aaVar, iVar);
                }
            }
        }
    }

    private void a(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.k.o oVar, Object obj) {
        com.a.a.c.o<Object> oVar2;
        com.a.a.c.o<Object> k;
        u uVar = new u(this.j, this.c);
        boolean z = f634a == obj;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (this.q == null || !this.q.a(key)) {
                if (key == null) {
                    oVar2 = aaVar.l();
                } else {
                    oVar2 = this.g;
                }
                Object value = entry.getValue();
                if (value == null) {
                    if (!this.p) {
                        k = aaVar.k();
                    }
                } else {
                    com.a.a.c.o<Object> oVar3 = this.i;
                    k = oVar3;
                    if (oVar3 == null) {
                        k = b(aaVar, value);
                    }
                    if (z) {
                        if (k.a(aaVar, value)) {
                        }
                    } else if (obj != null && obj.equals(value)) {
                    }
                }
                uVar.a(key, value, oVar2, k);
                try {
                    oVar.a(map, hVar, aaVar, uVar);
                } catch (Exception e) {
                    a(aaVar, e, map, String.valueOf(key));
                }
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:7|(2:53|54)(2:9|(1:14)(2:51|34))|15|(3:45|46|(2:48|49)(2:50|34))(5:17|18|(1:20)|21|(3:40|41|(2:44|34)(1:43))(3:23|24|(2:38|34)))|29|30|31|33|34|5) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cb, code lost:            r13 = move-exception;     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cd, code lost:            a(r9, r13, r7, java.lang.String.valueOf(r0));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(java.util.Map<?, ?> r7, com.a.a.b.h r8, com.a.a.c.aa r9, java.lang.Object r10) {
        /*
            r6 = this;
            java.lang.Object r0 = com.a.a.c.k.b.v.f634a
            r1 = r10
            if (r0 != r1) goto Lc
            r0 = 1
            goto Ld
        Lc:
            r0 = 0
        Ld:
            r11 = r0
            r0 = r7
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            r12 = r0
        L1c:
            r0 = r12
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Ldc
            r0 = r12
            java.lang.Object r0 = r0.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r1 = r0
            r13 = r1
            java.lang.Object r0 = r0.getKey()
            r1 = r0
            r14 = r1
            if (r0 != 0) goto L47
            r0 = r9
            com.a.a.c.o r0 = r0.l()
            r15 = r0
            goto L60
        L47:
            r0 = r6
            com.a.a.c.m.n$a r0 = r0.q
            if (r0 == 0) goto L5a
            r0 = r6
            com.a.a.c.m.n$a r0 = r0.q
            r1 = r14
            boolean r0 = r0.a(r1)
            if (r0 != 0) goto L1c
        L5a:
            r0 = r6
            com.a.a.c.o<java.lang.Object> r0 = r0.g
            r15 = r0
        L60:
            r0 = r13
            java.lang.Object r0 = r0.getValue()
            r1 = r0
            r13 = r1
            if (r0 != 0) goto L7d
            r0 = r6
            boolean r0 = r0.p
            if (r0 != 0) goto L1c
            r0 = r9
            com.a.a.c.o r0 = r0.k()
            r16 = r0
            goto Lb2
        L7d:
            r0 = r6
            com.a.a.c.o<java.lang.Object> r0 = r0.i
            r1 = r0
            r16 = r1
            if (r0 != 0) goto L90
            r0 = r6
            r1 = r9
            r2 = r13
            com.a.a.c.o r0 = r0.b(r1, r2)
            r16 = r0
        L90:
            r0 = r11
            if (r0 == 0) goto La3
            r0 = r16
            r1 = r9
            r2 = r13
            boolean r0 = r0.a(r1, r2)
            if (r0 == 0) goto Lb2
            goto L1c
        La3:
            r0 = r10
            if (r0 == 0) goto Lb2
            r0 = r10
            r1 = r13
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L1c
        Lb2:
            r0 = r15
            r1 = r14
            r2 = r8
            r3 = r9
            r0.a(r1, r2, r3)
            r0 = r16
            r1 = r13
            r2 = r8
            r3 = r9
            r4 = r6
            com.a.a.c.i.i r4 = r4.j     // Catch: java.lang.Exception -> Lcb
            r0.a(r1, r2, r3, r4)     // Catch: java.lang.Exception -> Lcb
            goto L1c
        Lcb:
            r13 = move-exception
            r0 = r9
            r1 = r13
            r2 = r7
            r3 = r14
            java.lang.String r3 = java.lang.String.valueOf(r3)
            a(r0, r1, r2, r3)
            goto L1c
        Ldc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.k.b.v.b(java.util.Map, com.a.a.b.h, com.a.a.c.aa, java.lang.Object):void");
    }

    public final void a(com.a.a.c.aa aaVar, com.a.a.b.h hVar, Object obj, Map<?, ?> map, com.a.a.c.k.o oVar, Object obj2) {
        com.a.a.c.o<Object> oVar2;
        com.a.a.c.o<Object> k;
        u uVar = new u(this.j, this.c);
        boolean z = f634a == obj2;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (this.q == null || !this.q.a(key)) {
                if (key == null) {
                    oVar2 = aaVar.l();
                } else {
                    oVar2 = this.g;
                }
                Object value = entry.getValue();
                if (value == null) {
                    if (!this.p) {
                        k = aaVar.k();
                    }
                } else {
                    com.a.a.c.o<Object> oVar3 = this.i;
                    k = oVar3;
                    if (oVar3 == null) {
                        k = b(aaVar, value);
                    }
                    if (z) {
                        if (k.a(aaVar, value)) {
                        }
                    } else if (obj2 != null && obj2.equals(value)) {
                    }
                }
                uVar.a(key, value, oVar2, k);
                try {
                    oVar.a(obj, hVar, aaVar, uVar);
                } catch (Exception e) {
                    a(aaVar, e, map, String.valueOf(key));
                }
            }
        }
    }

    private com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, Class<?> cls, com.a.a.c.aa aaVar) {
        k.d b2 = kVar.b(cls, aaVar, this.c);
        if (kVar != b2.f568b) {
            this.k = b2.f568b;
        }
        return b2.f567a;
    }

    private com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, com.a.a.c.j jVar, com.a.a.c.aa aaVar) {
        k.d b2 = kVar.b(jVar, aaVar, this.c);
        if (kVar != b2.f568b) {
            this.k = b2.f568b;
        }
        return b2.f567a;
    }

    private Map<?, ?> d(Map<?, ?> map, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (map instanceof SortedMap) {
            return map;
        }
        if (b(map)) {
            TreeMap treeMap = new TreeMap();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                if (key == null) {
                    a(hVar, aaVar, entry.getValue());
                } else {
                    treeMap.put(key, entry.getValue());
                }
            }
            return treeMap;
        }
        return new TreeMap(map);
    }

    private static boolean b(Map<?, ?> map) {
        return (map instanceof HashMap) && map.containsKey(null);
    }

    private void a(com.a.a.b.h hVar, com.a.a.c.aa aaVar, Object obj) {
        com.a.a.c.o<Object> oVar;
        com.a.a.c.o<Object> l = aaVar.l();
        if (obj == null) {
            if (this.p) {
                return;
            } else {
                oVar = aaVar.k();
            }
        } else {
            com.a.a.c.o<Object> oVar2 = this.i;
            oVar = oVar2;
            if (oVar2 == null) {
                oVar = b(aaVar, obj);
            }
            if (this.o == f634a) {
                if (oVar.a(aaVar, obj)) {
                    return;
                }
            } else if (this.o != null && this.o.equals(obj)) {
                return;
            }
        }
        try {
            l.a(null, hVar, aaVar);
            oVar.a(obj, hVar, aaVar);
        } catch (Exception e) {
            a(aaVar, e, obj, "");
        }
    }

    private final com.a.a.c.o<Object> b(com.a.a.c.aa aaVar, Object obj) {
        Class<?> cls = obj.getClass();
        com.a.a.c.o<Object> a2 = this.k.a(cls);
        if (a2 != null) {
            return a2;
        }
        if (this.f.s()) {
            return a(this.k, aaVar.a(this.f, cls), aaVar);
        }
        return a(this.k, cls, aaVar);
    }
}
