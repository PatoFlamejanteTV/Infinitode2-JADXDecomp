package com.a.a.c.c;

import com.a.a.a.ac;
import com.a.a.a.ak;
import com.a.a.a.b;
import com.a.a.a.i;
import com.a.a.a.q;
import com.a.a.a.t;
import com.a.a.c.c;
import com.a.a.c.c.b.ah;
import com.a.a.c.c.b.al;
import com.a.a.c.c.b.am;
import com.a.a.c.c.b.ao;
import com.a.a.c.c.b.aq;
import com.a.a.c.c.b.z;
import com.a.a.c.f.af;
import com.a.a.c.f.ap;
import com.a.a.c.f.w;
import com.a.a.c.m.aa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b.class */
public abstract class b extends q implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private static final Class<?> f295b = Object.class;
    private static final Class<?> c = String.class;
    private static final Class<?> d = CharSequence.class;
    private static final Class<?> e = Iterable.class;
    private static final Class<?> f = Map.Entry.class;
    private static final Class<?> g = Serializable.class;

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.b.m f296a;

    static {
        new com.a.a.c.w("@JsonUnwrapped");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(com.a.a.c.b.m mVar) {
        this.f296a = mVar;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.j a(com.a.a.c.f fVar, com.a.a.c.j jVar) {
        com.a.a.c.j c2;
        while (true) {
            c2 = c(fVar, jVar);
            if (c2 == null) {
                return jVar;
            }
            Class<?> b2 = jVar.b();
            Class<?> b3 = c2.b();
            if (b2 == b3 || !b2.isAssignableFrom(b3)) {
                break;
            }
            jVar = c2;
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + jVar + " to " + c2 + ": latter is not a subtype of former");
    }

    private com.a.a.c.j c(com.a.a.c.f fVar, com.a.a.c.j jVar) {
        Class<?> b2 = jVar.b();
        if (this.f296a.c()) {
            Iterator<com.a.a.c.k.a.d> it = this.f296a.h().iterator();
            while (it.hasNext()) {
                it.next();
                com.a.a.c.j a2 = com.a.a.c.k.a.d.a();
                if (a2 != null && !a2.a(b2)) {
                    return a2;
                }
            }
            return null;
        }
        return null;
    }

    public final x a(com.a.a.c.g gVar, com.a.a.c.b bVar) {
        com.a.a.c.f a2 = gVar.a();
        x xVar = null;
        com.a.a.c.f.d d2 = bVar.d();
        Object f2 = gVar.f().f(d2);
        if (f2 != null) {
            xVar = a(a2, d2, f2);
        }
        if (xVar == null) {
            x b2 = com.a.a.c.c.a.k.b(bVar.b());
            xVar = b2;
            if (b2 == null) {
                xVar = b(gVar, bVar);
            }
        }
        if (this.f296a.d()) {
            for (w.a aVar : this.f296a.i()) {
                x l = aVar.l();
                xVar = l;
                if (l == null) {
                    gVar.a(bVar, "Broken registered ValueInstantiators (of type %s): returned null ValueInstantiator", aVar.getClass().getName());
                }
            }
        }
        if (xVar != null) {
            xVar = xVar.a();
        }
        return xVar;
    }

    private x b(com.a.a.c.g gVar, com.a.a.c.b bVar) {
        ArrayList arrayList;
        com.a.a.c.f.f a2;
        com.a.a.c.f a3 = gVar.a();
        ap<?> a4 = a3.a(bVar.b(), bVar.d());
        com.a.a.c.b.i e2 = a3.e();
        C0007b c0007b = new C0007b(gVar, bVar, a4, new com.a.a.c.c.a.e(bVar, a3), c(gVar, bVar));
        b(gVar, c0007b, !e2.b());
        if (bVar.a().e()) {
            if (bVar.a().j() && (a2 = com.a.a.c.g.a.a(gVar, bVar, (arrayList = new ArrayList()))) != null) {
                a(gVar, c0007b, a2, arrayList);
                return c0007b.c.a(gVar);
            }
            if (!bVar.c()) {
                a(gVar, c0007b, e2.a(bVar.b()));
                if (c0007b.h() && !c0007b.g()) {
                    a(gVar, c0007b, c0007b.i());
                }
            }
        }
        if (c0007b.d() && !c0007b.c() && !c0007b.g()) {
            b(gVar, c0007b, c0007b.e());
        }
        return c0007b.c.a(gVar);
    }

    private static Map<com.a.a.c.f.o, com.a.a.c.f.s[]> c(com.a.a.c.g gVar, com.a.a.c.b bVar) {
        Map<com.a.a.c.f.o, com.a.a.c.f.s[]> emptyMap = Collections.emptyMap();
        for (com.a.a.c.f.s sVar : bVar.h()) {
            Iterator<com.a.a.c.f.n> r = sVar.r();
            while (r.hasNext()) {
                com.a.a.c.f.n next = r.next();
                com.a.a.c.f.o e2 = next.e();
                com.a.a.c.f.s[] sVarArr = emptyMap.get(e2);
                int f2 = next.f();
                if (sVarArr == null) {
                    if (emptyMap.isEmpty()) {
                        emptyMap = new LinkedHashMap();
                    }
                    sVarArr = new com.a.a.c.f.s[e2.f()];
                    emptyMap.put(e2, sVarArr);
                } else if (sVarArr[f2] != null) {
                    gVar.a(bVar, "Conflict: parameter #%d of %s bound to more than one property; %s vs %s", Integer.valueOf(f2), e2, sVarArr[f2], sVar);
                }
                sVarArr[f2] = sVar;
            }
        }
        return emptyMap;
    }

    private static x a(com.a.a.c.f fVar, com.a.a.c.f.b bVar, Object obj) {
        x i;
        if (obj == null) {
            return null;
        }
        if (obj instanceof x) {
            return (x) obj;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + obj.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        Class cls = (Class) obj;
        if (!com.a.a.c.m.i.e((Class<?>) cls)) {
            if (!x.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<ValueInstantiator>");
            }
            if (fVar.m() != null && (i = com.a.a.c.k.a.d.i()) != null) {
                return i;
            }
            return (x) com.a.a.c.m.i.b(cls, fVar.g());
        }
        return null;
    }

    private void a(com.a.a.c.g gVar, C0007b c0007b, com.a.a.c.f.f fVar, List<String> list) {
        int f2 = fVar.f();
        com.a.a.c.a f3 = gVar.f();
        v[] vVarArr = new v[f2];
        for (int i = 0; i < f2; i++) {
            com.a.a.c.f.n c2 = fVar.c(i);
            b.a e2 = f3.e((com.a.a.c.f.j) c2);
            com.a.a.c.w D = f3.D(c2);
            com.a.a.c.w wVar = D;
            if (D == null || wVar.e()) {
                wVar = com.a.a.c.w.a(list.get(i));
            }
            vVarArr[i] = a(gVar, c0007b.f333a, wVar, i, c2, e2);
        }
        c0007b.c.a((com.a.a.c.f.o) fVar, false, vVarArr);
    }

    private void a(com.a.a.c.g gVar, C0007b c0007b, boolean z) {
        com.a.a.c.b bVar = c0007b.f333a;
        com.a.a.c.c.a.e eVar = c0007b.c;
        com.a.a.c.a a2 = c0007b.a();
        ap<?> apVar = c0007b.f334b;
        Map<com.a.a.c.f.o, com.a.a.c.f.s[]> map = c0007b.d;
        com.a.a.c.f.f o = bVar.o();
        if (o != null && (!eVar.a() || d(gVar, o))) {
            eVar.a((com.a.a.c.f.o) o);
        }
        for (com.a.a.c.f.f fVar : bVar.k()) {
            i.a a3 = a2.a(gVar.a(), fVar);
            if (i.a.DISABLED != a3) {
                if (a3 == null) {
                    if (z && apVar.a(fVar)) {
                        c0007b.b(com.a.a.c.c.a.d.a(a2, fVar, map.get(fVar)));
                    }
                } else {
                    switch (a3) {
                        case DELEGATING:
                            a(gVar, bVar, eVar, com.a.a.c.c.a.d.a(a2, fVar, null));
                            break;
                        case PROPERTIES:
                            b(gVar, bVar, eVar, com.a.a.c.c.a.d.a(a2, fVar, map.get(fVar)));
                            break;
                        default:
                            a(gVar, bVar, eVar, com.a.a.c.c.a.d.a(a2, fVar, map.get(fVar)), gVar.a().e());
                            break;
                    }
                    c0007b.f();
                }
            }
        }
    }

    private void a(com.a.a.c.g gVar, C0007b c0007b, List<com.a.a.c.c.a.d> list) {
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.b bVar = c0007b.f333a;
        com.a.a.c.c.a.e eVar = c0007b.c;
        com.a.a.c.a a3 = c0007b.a();
        ap<?> apVar = c0007b.f334b;
        LinkedList linkedList = null;
        boolean d2 = a2.e().d();
        for (com.a.a.c.c.a.d dVar : list) {
            int b2 = dVar.b();
            com.a.a.c.f.o a4 = dVar.a();
            if (b2 == 1) {
                com.a.a.c.f.s c2 = dVar.c(0);
                if (d2 || a(a3, a4, c2)) {
                    v[] vVarArr = new v[1];
                    b.a a5 = dVar.a(0);
                    com.a.a.c.w d3 = dVar.d(0);
                    com.a.a.c.w wVar = d3;
                    if (d3 == null) {
                        com.a.a.c.w f2 = dVar.f(0);
                        wVar = f2;
                        if (f2 == null && a5 == null) {
                        }
                    }
                    vVarArr[0] = a(gVar, bVar, wVar, 0, dVar.b(0), a5);
                    eVar.a(a4, false, vVarArr);
                } else {
                    a(eVar, a4, false, apVar.a(a4));
                    if (c2 != null) {
                        ((af) c2).I();
                    }
                }
            } else {
                int i = -1;
                v[] vVarArr2 = new v[b2];
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < b2; i4++) {
                    com.a.a.c.f.n c3 = a4.c(i4);
                    com.a.a.c.f.s c4 = dVar.c(i4);
                    b.a e2 = a3.e((com.a.a.c.f.j) c3);
                    com.a.a.c.w b3 = c4 == null ? null : c4.b();
                    if (c4 != null && c4.e()) {
                        i2++;
                        vVarArr2[i4] = a(gVar, bVar, b3, i4, c3, e2);
                    } else if (e2 != null) {
                        i3++;
                        vVarArr2[i4] = a(gVar, bVar, b3, i4, c3, e2);
                    } else if (a3.c((com.a.a.c.f.j) c3) != null) {
                        a(gVar, bVar, c3);
                    } else if (i < 0) {
                        i = i4;
                    }
                }
                int i5 = i2;
                if (i2 > 0 || i3 > 0) {
                    if (i5 + i3 == b2) {
                        eVar.a(a4, false, vVarArr2);
                    } else if (i2 == 0 && i3 + 1 == b2) {
                        eVar.a(a4, false, vVarArr2, 0);
                    } else {
                        com.a.a.c.w f3 = dVar.f(i);
                        if (f3 == null || f3.e()) {
                            gVar.a(bVar, "Argument #%d of constructor %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", Integer.valueOf(i), a4);
                        }
                    }
                }
                if (!eVar.a()) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(a4);
                }
            }
        }
        if (linkedList != null && !eVar.b() && !eVar.c()) {
            a(gVar, bVar, apVar, a3, eVar, linkedList);
        }
    }

    private void b(com.a.a.c.g gVar, C0007b c0007b, boolean z) {
        com.a.a.c.b bVar = c0007b.f333a;
        com.a.a.c.c.a.e eVar = c0007b.c;
        com.a.a.c.a a2 = c0007b.a();
        ap<?> apVar = c0007b.f334b;
        Map<com.a.a.c.f.o, com.a.a.c.f.s[]> map = c0007b.d;
        for (com.a.a.c.f.k kVar : bVar.m()) {
            i.a a3 = a2.a(gVar.a(), kVar);
            int f2 = kVar.f();
            if (a3 == null) {
                if (z && f2 == 1 && apVar.a((com.a.a.c.f.j) kVar)) {
                    c0007b.a(com.a.a.c.c.a.d.a(a2, kVar, null));
                }
            } else if (a3 != i.a.DISABLED) {
                if (f2 == 0) {
                    eVar.a((com.a.a.c.f.o) kVar);
                } else {
                    switch (a3) {
                        case DELEGATING:
                            a(gVar, bVar, eVar, com.a.a.c.c.a.d.a(a2, kVar, null));
                            break;
                        case PROPERTIES:
                            b(gVar, bVar, eVar, com.a.a.c.c.a.d.a(a2, kVar, map.get(kVar)));
                            break;
                        default:
                            a(gVar, bVar, eVar, com.a.a.c.c.a.d.a(a2, kVar, map.get(kVar)), com.a.a.c.b.i.f224a);
                            break;
                    }
                    c0007b.b();
                }
            }
        }
    }

    private void b(com.a.a.c.g gVar, C0007b c0007b, List<com.a.a.c.c.a.d> list) {
        com.a.a.c.b bVar = c0007b.f333a;
        com.a.a.c.c.a.e eVar = c0007b.c;
        com.a.a.c.a a2 = c0007b.a();
        ap<?> apVar = c0007b.f334b;
        Map<com.a.a.c.f.o, com.a.a.c.f.s[]> map = c0007b.d;
        for (com.a.a.c.c.a.d dVar : list) {
            int b2 = dVar.b();
            com.a.a.c.f.o a3 = dVar.a();
            com.a.a.c.f.s[] sVarArr = map.get(a3);
            if (b2 == 1) {
                com.a.a.c.f.s c2 = dVar.c(0);
                if (!a(a2, a3, c2)) {
                    a(eVar, a3, false, apVar.a(a3));
                    if (c2 != null) {
                        ((af) c2).I();
                    }
                } else {
                    com.a.a.c.f.n nVar = null;
                    v[] vVarArr = new v[b2];
                    int i = 0;
                    int i2 = 0;
                    for (int i3 = 0; i3 < b2; i3++) {
                        com.a.a.c.f.n c3 = a3.c(i3);
                        com.a.a.c.f.s sVar = sVarArr == null ? null : sVarArr[i3];
                        b.a e2 = a2.e((com.a.a.c.f.j) c3);
                        com.a.a.c.w b3 = sVar == null ? null : sVar.b();
                        if (sVar != null && sVar.e()) {
                            i++;
                            vVarArr[i3] = a(gVar, bVar, b3, i3, c3, e2);
                        } else if (e2 != null) {
                            i2++;
                            vVarArr[i3] = a(gVar, bVar, b3, i3, c3, e2);
                        } else if (a2.c((com.a.a.c.f.j) c3) != null) {
                            a(gVar, bVar, c3);
                        } else if (nVar == null) {
                            nVar = c3;
                        }
                    }
                    int i4 = i;
                    if (i > 0 || i2 > 0) {
                        if (i4 + i2 == b2) {
                            eVar.a(a3, false, vVarArr);
                        } else if (i == 0 && i2 + 1 == b2) {
                            eVar.a(a3, false, vVarArr, 0);
                        } else {
                            Object[] objArr = new Object[2];
                            objArr[0] = Integer.valueOf(nVar == null ? -1 : nVar.f());
                            objArr[1] = a3;
                            gVar.a(bVar, "Argument #%d of factory method %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", objArr);
                        }
                    }
                }
            }
        }
    }

    private void a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.c.a.e eVar, com.a.a.c.c.a.d dVar) {
        int i = -1;
        int b2 = dVar.b();
        v[] vVarArr = new v[b2];
        for (int i2 = 0; i2 < b2; i2++) {
            com.a.a.c.f.n b3 = dVar.b(i2);
            b.a a2 = dVar.a(i2);
            if (a2 != null) {
                vVarArr[i2] = a(gVar, bVar, (com.a.a.c.w) null, i2, b3, a2);
            } else if (i < 0) {
                i = i2;
            } else {
                gVar.a(bVar, "More than one argument (#%d and #%d) left as delegating for Creator %s: only one allowed", Integer.valueOf(i), Integer.valueOf(i2), dVar);
            }
        }
        if (i < 0) {
            gVar.a(bVar, "No argument left as delegating for Creator %s: exactly one required", dVar);
        }
        if (b2 == 1) {
            a(eVar, dVar.a(), true, true);
            com.a.a.c.f.s c2 = dVar.c(0);
            if (c2 != null) {
                ((af) c2).I();
                return;
            }
            return;
        }
        eVar.a(dVar.a(), true, vVarArr, i);
    }

    private void b(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.c.a.e eVar, com.a.a.c.c.a.d dVar) {
        int b2 = dVar.b();
        v[] vVarArr = new v[b2];
        for (int i = 0; i < b2; i++) {
            b.a a2 = dVar.a(i);
            com.a.a.c.f.n b3 = dVar.b(i);
            com.a.a.c.w d2 = dVar.d(i);
            com.a.a.c.w wVar = d2;
            if (d2 == null) {
                if (gVar.f().c((com.a.a.c.f.j) b3) != null) {
                    a(gVar, bVar, b3);
                }
                wVar = dVar.f(i);
                a(gVar, bVar, dVar, i, wVar, a2);
            }
            vVarArr[i] = a(gVar, bVar, wVar, i, b3, a2);
        }
        eVar.a(dVar.a(), true, vVarArr);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0061. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.a.a.c.g r12, com.a.a.c.b r13, com.a.a.c.c.a.e r14, com.a.a.c.c.a.d r15, com.a.a.c.b.i r16) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.a(com.a.a.c.g, com.a.a.c.b, com.a.a.c.c.a.e, com.a.a.c.c.a.d, com.a.a.c.b.i):void");
    }

    private static boolean a(com.a.a.c.a aVar, com.a.a.c.f.o oVar, com.a.a.c.f.s sVar) {
        String a2;
        if ((sVar != null && sVar.e()) || aVar.e((com.a.a.c.f.j) oVar.c(0)) != null) {
            return true;
        }
        if (sVar != null && (a2 = sVar.a()) != null && !a2.isEmpty() && sVar.j()) {
            return true;
        }
        return false;
    }

    private void a(com.a.a.c.g gVar, com.a.a.c.b bVar, ap<?> apVar, com.a.a.c.a aVar, com.a.a.c.c.a.e eVar, List<com.a.a.c.f.o> list) {
        com.a.a.c.f.o oVar = null;
        v[] vVarArr = null;
        Iterator<com.a.a.c.f.o> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.a.a.c.f.o next = it.next();
            if (apVar.a(next)) {
                int f2 = next.f();
                v[] vVarArr2 = new v[f2];
                int i = 0;
                while (true) {
                    if (i < f2) {
                        com.a.a.c.f.n c2 = next.c(i);
                        com.a.a.c.w a2 = a(c2, aVar);
                        if (a2 != null && !a2.e()) {
                            vVarArr2[i] = a(gVar, bVar, a2, c2.f(), c2, (b.a) null);
                            i++;
                        }
                    } else if (oVar != null) {
                        oVar = null;
                        break;
                    } else {
                        oVar = next;
                        vVarArr = vVarArr2;
                    }
                }
            }
        }
        if (oVar != null) {
            eVar.a(oVar, false, vVarArr);
            com.a.a.c.f.q qVar = (com.a.a.c.f.q) bVar;
            for (v vVar : vVarArr) {
                com.a.a.c.w b2 = vVar.b();
                if (!qVar.a(b2)) {
                    qVar.a((com.a.a.c.f.s) aa.a(gVar.a(), vVar.e(), b2));
                }
            }
        }
    }

    private static boolean a(com.a.a.c.c.a.e eVar, com.a.a.c.f.o oVar, boolean z, boolean z2) {
        Class<?> a2 = oVar.a(0);
        if (a2 == String.class || a2 == d) {
            if (z || z2) {
                eVar.a(oVar, z);
                return true;
            }
            return true;
        }
        if (a2 == Integer.TYPE || a2 == Integer.class) {
            if (z || z2) {
                eVar.b(oVar, z);
                return true;
            }
            return true;
        }
        if (a2 == Long.TYPE || a2 == Long.class) {
            if (z || z2) {
                eVar.c(oVar, z);
                return true;
            }
            return true;
        }
        if (a2 == Double.TYPE || a2 == Double.class) {
            if (z || z2) {
                eVar.e(oVar, z);
                return true;
            }
            return true;
        }
        if (a2 == Boolean.TYPE || a2 == Boolean.class) {
            if (z || z2) {
                eVar.g(oVar, z);
                return true;
            }
            return true;
        }
        if (a2 == BigInteger.class && (z || z2)) {
            eVar.d(oVar, z);
        }
        if (a2 == BigDecimal.class && (z || z2)) {
            eVar.f(oVar, z);
        }
        if (z) {
            eVar.a(oVar, z, (v[]) null, 0);
            return true;
        }
        return false;
    }

    private static void a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.c.a.d dVar, int i, com.a.a.c.w wVar, b.a aVar) {
        if (wVar == null && aVar == null) {
            gVar.a(bVar, "Argument #%d of constructor %s has no property name (and is not Injectable): can not use as property-based Creator", Integer.valueOf(i), dVar);
        }
    }

    private static void a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.f.n nVar) {
        gVar.a(bVar, "Cannot define Creator parameter %d as `@JsonUnwrapped`: combination not yet supported", Integer.valueOf(nVar.f()));
    }

    private v a(com.a.a.c.g gVar, com.a.a.c.b bVar, com.a.a.c.w wVar, int i, com.a.a.c.f.n nVar, b.a aVar) {
        com.a.a.c.v a2;
        com.a.a.c.w b2;
        com.a.a.c.f a3 = gVar.a();
        com.a.a.c.a f2 = gVar.f();
        if (f2 == null) {
            a2 = com.a.a.c.v.c;
            b2 = null;
        } else {
            a2 = com.a.a.c.v.a(f2.f((com.a.a.c.f.j) nVar), f2.j((com.a.a.c.f.b) nVar), f2.k(nVar), f2.i((com.a.a.c.f.b) nVar));
            b2 = com.a.a.c.a.b();
        }
        com.a.a.c.j a4 = a(gVar, nVar, nVar.c());
        c.b bVar2 = new c.b(wVar, a4, b2, nVar, a2);
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) a4.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = b(a3, a4);
        }
        m a5 = m.a(wVar, a4, bVar2.f(), eVar2, bVar.g(), nVar, i, aVar, a(gVar, bVar2, a2));
        com.a.a.c.k<?> a6 = a(gVar, nVar);
        com.a.a.c.k<?> kVar = a6;
        if (a6 == null) {
            kVar = (com.a.a.c.k) a4.A();
        }
        if (kVar != null) {
            a5 = a5.a(gVar.a(kVar, (com.a.a.c.c) a5, a4));
        }
        return a5;
    }

    private static com.a.a.c.w a(com.a.a.c.f.n nVar, com.a.a.c.a aVar) {
        if (aVar != null) {
            com.a.a.c.w D = aVar.D(nVar);
            if (D != null && !D.e()) {
                return D;
            }
            String g2 = aVar.g((com.a.a.c.f.j) nVar);
            if (g2 != null && !g2.isEmpty()) {
                return com.a.a.c.w.a(g2);
            }
            return null;
        }
        return null;
    }

    private static com.a.a.c.v a(com.a.a.c.g gVar, com.a.a.c.c cVar, com.a.a.c.v vVar) {
        ac.a F;
        com.a.a.c.a f2 = gVar.f();
        com.a.a.c.f a2 = gVar.a();
        ak akVar = null;
        ak akVar2 = null;
        com.a.a.c.f.j e2 = cVar.e();
        if (e2 != null) {
            if (f2 != null && (F = f2.F(e2)) != null) {
                akVar = F.c();
                akVar2 = F.d();
            }
            ac.a g2 = a2.d(cVar.c().b()).g();
            if (g2 != null) {
                if (akVar == null) {
                    akVar = g2.c();
                }
                if (akVar2 == null) {
                    akVar2 = g2.d();
                }
            }
        }
        ac.a q = a2.q();
        if (akVar == null) {
            akVar = q.c();
        }
        if (akVar2 == null) {
            akVar2 = q.d();
        }
        if (akVar != null || akVar2 != null) {
            vVar = vVar.a(akVar, akVar2);
        }
        return vVar;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.l.a aVar, com.a.a.c.b bVar) {
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.j u = aVar.u();
        com.a.a.c.k<?> kVar = (com.a.a.c.k) u.A();
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) u.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = b(a2, u);
        }
        com.a.a.c.k<?> a3 = a(aVar, a2, bVar, eVar2, kVar);
        com.a.a.c.k<?> kVar2 = a3;
        if (a3 == null) {
            if (kVar == null) {
                Class<?> b2 = u.b();
                if (u.l()) {
                    return com.a.a.c.c.b.aa.a(b2);
                }
                if (b2 == String.class) {
                    return com.a.a.c.c.b.ak.f323a;
                }
            }
            kVar2 = new z(aVar, kVar, eVar2);
        }
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                kVar2 = com.d.c.d.a.j.d(kVar2);
            }
        }
        return kVar2;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.l.e eVar, com.a.a.c.b bVar) {
        com.a.a.c.j u = eVar.u();
        com.a.a.c.k<?> kVar = (com.a.a.c.k) u.A();
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.i.e eVar2 = (com.a.a.c.i.e) u.B();
        com.a.a.c.i.e eVar3 = eVar2;
        if (eVar2 == null) {
            eVar3 = b(a2, u);
        }
        com.a.a.c.k<?> a3 = a(eVar, a2, bVar, eVar3, kVar);
        com.a.a.c.k<?> kVar2 = a3;
        if (a3 == null) {
            Class<?> b2 = eVar.b();
            if (kVar == null && EnumSet.class.isAssignableFrom(b2)) {
                kVar2 = new com.a.a.c.c.b.o(u, null);
            }
        }
        if (kVar2 == null) {
            if (eVar.k() || eVar.d()) {
                com.a.a.c.l.e a4 = a(eVar, a2);
                if (a4 == null) {
                    if (eVar.B() == null) {
                        throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Collection type " + eVar);
                    }
                    kVar2 = com.a.a.c.c.a.a(bVar);
                } else {
                    eVar = a4;
                    bVar = a2.b(eVar);
                }
            }
            if (kVar2 == null) {
                x a5 = a(gVar, bVar);
                if (!a5.l()) {
                    if (eVar.a(ArrayBlockingQueue.class)) {
                        return new com.a.a.c.c.b.a(eVar, kVar, eVar3, a5);
                    }
                    com.a.a.c.k<?> c2 = com.a.a.c.c.a.l.c(eVar);
                    if (c2 != null) {
                        return c2;
                    }
                }
                kVar2 = u.a(String.class) ? new al(eVar, kVar, a5) : new com.a.a.c.c.b.h(eVar, kVar, eVar3, a5);
            }
        }
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                kVar2 = com.d.c.d.a.j.e(kVar2);
            }
        }
        return kVar2;
    }

    private static com.a.a.c.l.e a(com.a.a.c.j jVar, com.a.a.c.f fVar) {
        Class<?> a2 = a.a(jVar);
        if (a2 != null) {
            return (com.a.a.c.l.e) fVar.p().a(jVar, a2, true);
        }
        return null;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.l.d dVar, com.a.a.c.b bVar) {
        com.a.a.c.j u = dVar.u();
        com.a.a.c.k<?> kVar = (com.a.a.c.k) u.A();
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) u.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = b(a2, u);
        }
        com.a.a.c.k<?> a3 = a(dVar, a2, bVar, eVar2, kVar);
        com.a.a.c.k<?> kVar2 = a3;
        if (a3 != null && this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                kVar2 = com.d.c.d.a.j.f(kVar2);
            }
        }
        return kVar2;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.l.h hVar, com.a.a.c.b bVar) {
        x a2;
        com.a.a.c.f a3 = gVar.a();
        com.a.a.c.j t = hVar.t();
        com.a.a.c.j u = hVar.u();
        com.a.a.c.k<?> kVar = (com.a.a.c.k) u.A();
        com.a.a.c.p pVar = (com.a.a.c.p) t.A();
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) u.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = b(a3, u);
        }
        com.a.a.c.k<?> a4 = a(hVar, a3, bVar, pVar, eVar2, kVar);
        com.a.a.c.k<?> kVar2 = a4;
        if (a4 == null) {
            Class<?> b2 = hVar.b();
            if (EnumMap.class.isAssignableFrom(b2)) {
                if (b2 == EnumMap.class) {
                    a2 = null;
                } else {
                    a2 = a(gVar, bVar);
                }
                if (!t.i()) {
                    throw new IllegalArgumentException("Cannot construct EnumMap; generic (key) type not available");
                }
                kVar2 = new com.a.a.c.c.b.n(hVar, a2, null, kVar, eVar2, null);
            }
            if (kVar2 == null) {
                if (hVar.k() || hVar.d()) {
                    com.a.a.c.l.h b3 = b(hVar, a3);
                    if (b3 != null) {
                        hVar = b3;
                        b3.b();
                        bVar = a3.b(hVar);
                    } else {
                        if (hVar.B() == null) {
                            throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Map type " + hVar);
                        }
                        kVar2 = com.a.a.c.c.a.a(bVar);
                    }
                } else {
                    com.a.a.c.k<?> d2 = com.a.a.c.c.a.l.d(hVar);
                    kVar2 = d2;
                    if (d2 != null) {
                        return kVar2;
                    }
                }
                if (kVar2 == null) {
                    com.a.a.c.c.b.u uVar = new com.a.a.c.c.b.u(hVar, a(gVar, bVar), pVar, kVar, eVar2);
                    q.a b4 = a3.b(Map.class, bVar.d());
                    uVar.a(b4 == null ? null : b4.c());
                    t.a a5 = a3.a(bVar.d());
                    uVar.b(a5 == null ? null : a5.b());
                    kVar2 = uVar;
                }
            }
        }
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                kVar2 = com.d.c.d.a.j.g(kVar2);
            }
        }
        return kVar2;
    }

    private static com.a.a.c.l.h b(com.a.a.c.j jVar, com.a.a.c.f fVar) {
        Class<?> b2 = a.b(jVar);
        if (b2 != null) {
            return (com.a.a.c.l.h) fVar.p().a(jVar, b2, true);
        }
        return null;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.l.g gVar2, com.a.a.c.b bVar) {
        com.a.a.c.j t = gVar2.t();
        com.a.a.c.j u = gVar2.u();
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.k<?> kVar = (com.a.a.c.k) u.A();
        com.a.a.c.p pVar = (com.a.a.c.p) t.A();
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) u.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = b(a2, u);
        }
        com.a.a.c.k<?> a3 = a(gVar2, a2, bVar, pVar, eVar2, kVar);
        com.a.a.c.k<?> kVar2 = a3;
        if (a3 != null && this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                kVar2 = com.d.c.d.a.j.h(kVar2);
            }
        }
        return kVar2;
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.f a2 = gVar.a();
        Class<?> b2 = jVar.b();
        com.a.a.c.k<?> b3 = b(b2, a2, bVar);
        com.a.a.c.k<?> kVar = b3;
        if (b3 == null) {
            if (b2 == Enum.class) {
                return com.a.a.c.c.a.a(bVar);
            }
            x b4 = b(gVar, bVar);
            v[] a3 = b4 == null ? null : b4.a(gVar.a());
            Iterator<com.a.a.c.f.k> it = bVar.m().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.a.a.c.f.k next = it.next();
                if (d(gVar, next)) {
                    if (next.f() == 0) {
                        kVar = com.a.a.c.c.b.l.a(a2, b2, next);
                    } else {
                        if (!next.m().isAssignableFrom(b2)) {
                            gVar.a(jVar, String.format("Invalid `@JsonCreator` annotated Enum factory method [%s]: needs to return compatible type", next.toString()));
                        }
                        kVar = com.a.a.c.c.b.l.a(a2, b2, next, b4, a3);
                    }
                }
            }
            if (kVar == null) {
                kVar = new com.a.a.c.c.b.l(a(b2, a2, bVar.q()), Boolean.valueOf(a2.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_ENUMS)));
            }
        }
        if (this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it2 = this.f296a.g().iterator();
            while (it2.hasNext()) {
                it2.next();
                kVar = com.d.c.d.a.j.b(kVar);
            }
        }
        return kVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.f fVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        Class<?> b2 = jVar.b();
        com.a.a.c.k<?> a2 = a((Class<? extends com.a.a.c.m>) b2, fVar, bVar);
        if (a2 != null) {
            return a2;
        }
        return com.a.a.c.c.b.t.a(b2);
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.l.j jVar, com.a.a.c.b bVar) {
        x a2;
        com.a.a.c.j u = jVar.u();
        com.a.a.c.k<?> kVar = (com.a.a.c.k) u.A();
        com.a.a.c.f a3 = gVar.a();
        com.a.a.c.i.e eVar = (com.a.a.c.i.e) u.B();
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = b(a3, u);
        }
        com.a.a.c.k<?> a4 = a(jVar, a3, bVar, eVar2, kVar);
        com.a.a.c.k<?> kVar2 = a4;
        if (a4 == null && jVar.b(AtomicReference.class)) {
            if (jVar.b() == AtomicReference.class) {
                a2 = null;
            } else {
                a2 = a(gVar, bVar);
            }
            return new com.a.a.c.c.b.e(jVar, a2, eVar2, kVar);
        }
        if (kVar2 != null && this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it = this.f296a.g().iterator();
            while (it.hasNext()) {
                it.next();
                kVar2 = com.d.c.d.a.j.c(kVar2);
            }
        }
        return kVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.a.a.c.c.q
    public final com.a.a.c.i.e b(com.a.a.c.f fVar, com.a.a.c.j jVar) {
        com.a.a.c.j a2;
        com.a.a.c.f.d d2 = fVar.c(jVar.b()).d();
        com.a.a.c.i.h<?> a3 = fVar.j().a((com.a.a.c.b.q<?>) fVar, d2, jVar);
        com.a.a.c.i.h<?> hVar = a3;
        if (a3 == null) {
            com.a.a.c.i.h<?> n = fVar.n();
            hVar = n;
            if (n == null) {
                return null;
            }
        }
        Collection<com.a.a.c.i.b> b2 = fVar.w().b(fVar, d2);
        if (hVar.a() == null && jVar.d() && (a2 = a(fVar, jVar)) != null && !a2.a(jVar.b())) {
            hVar = hVar.b(a2.b());
        }
        try {
            return hVar.a(fVar, jVar, b2);
        } catch (IllegalArgumentException | IllegalStateException e2) {
            throw com.a.a.c.d.b.a((com.a.a.b.l) null, com.a.a.c.m.i.g(e2), jVar).a(e2);
        }
    }

    private static com.a.a.c.k<?> d(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        return com.a.a.c.e.g.f424a.a(jVar, gVar.a(), bVar);
    }

    @Override // com.a.a.c.c.q
    public final com.a.a.c.p a(com.a.a.c.g gVar, com.a.a.c.j jVar) {
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.b bVar = null;
        com.a.a.c.p pVar = null;
        if (this.f296a.a()) {
            bVar = a2.d(jVar);
            Iterator<r> it = this.f296a.f().iterator();
            while (it.hasNext()) {
                com.a.a.c.p a3 = it.next().a(jVar);
                pVar = a3;
                if (a3 != null) {
                    break;
                }
            }
        }
        if (pVar == null) {
            if (bVar == null) {
                bVar = a2.c(jVar.b());
            }
            com.a.a.c.p b2 = b(gVar, bVar.d());
            pVar = b2;
            if (b2 == null) {
                if (jVar.h()) {
                    pVar = b(gVar, jVar);
                } else {
                    pVar = ah.a(a2, jVar);
                }
            }
        }
        if (pVar != null && this.f296a.b()) {
            Iterator<com.d.c.d.a.j> it2 = this.f296a.g().iterator();
            while (it2.hasNext()) {
                it2.next();
                pVar = com.d.c.d.a.j.a(pVar);
            }
        }
        return pVar;
    }

    private com.a.a.c.p b(com.a.a.c.g gVar, com.a.a.c.j jVar) {
        com.a.a.c.f a2 = gVar.a();
        Class<?> b2 = jVar.b();
        com.a.a.c.b a3 = a2.a(jVar);
        com.a.a.c.p b3 = b(gVar, a3.d());
        if (b3 != null) {
            return b3;
        }
        com.a.a.c.k<?> b4 = b(b2, a2, a3);
        if (b4 != null) {
            return ah.a(jVar, b4);
        }
        com.a.a.c.k<Object> a4 = a(gVar, a3.d());
        if (a4 != null) {
            return ah.a(jVar, (com.a.a.c.k<?>) a4);
        }
        com.a.a.c.m.l a5 = a(b2, a2, a3.q());
        for (com.a.a.c.f.k kVar : a3.m()) {
            if (d(gVar, kVar)) {
                if (kVar.f() == 1 && kVar.m().isAssignableFrom(b2)) {
                    if (kVar.a(0) == String.class) {
                        if (a2.g()) {
                            com.a.a.c.m.i.a(kVar.i(), gVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                        }
                        return ah.a(a5, kVar);
                    }
                } else {
                    throw new IllegalArgumentException("Unsuitable method (" + kVar + ") decorated with @JsonCreator (for Enum type " + b2.getName() + ")");
                }
            }
        }
        return ah.a(a5);
    }

    private com.a.a.c.i.e a(com.a.a.c.f fVar, com.a.a.c.j jVar, com.a.a.c.f.j jVar2) {
        com.a.a.c.i.h<?> a2 = fVar.j().a((com.a.a.c.b.q<?>) fVar, jVar2, jVar);
        if (a2 == null) {
            return b(fVar, jVar);
        }
        try {
            return a2.a(fVar, jVar, fVar.w().b(fVar, jVar2, jVar));
        } catch (IllegalArgumentException | IllegalStateException e2) {
            throw com.a.a.c.d.b.a((com.a.a.b.l) null, com.a.a.c.m.i.g(e2), jVar).a(e2);
        }
    }

    private com.a.a.c.i.e b(com.a.a.c.f fVar, com.a.a.c.j jVar, com.a.a.c.f.j jVar2) {
        com.a.a.c.i.h<?> b2 = fVar.j().b((com.a.a.c.b.q<?>) fVar, jVar2, jVar);
        com.a.a.c.j u = jVar.u();
        if (b2 == null) {
            return b(fVar, u);
        }
        return b2.a(fVar, u, fVar.w().b(fVar, jVar2, u));
    }

    public final com.a.a.c.k<?> b(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.j jVar2;
        com.a.a.c.j jVar3;
        Class<?> b2 = jVar.b();
        if (b2 == f295b || b2 == g) {
            com.a.a.c.f a2 = gVar.a();
            if (this.f296a.c()) {
                jVar3 = a(a2, List.class);
                jVar2 = a(a2, Map.class);
            } else {
                jVar2 = null;
                jVar3 = null;
            }
            return new aq(jVar3, jVar2);
        }
        if (b2 == c || b2 == d) {
            return am.f325a;
        }
        if (b2 == e) {
            com.a.a.c.l.o b3 = gVar.b();
            com.a.a.c.j[] c2 = com.a.a.c.l.o.c(jVar, e);
            return a(gVar, b3.a(Collection.class, (c2 == null || c2.length != 1) ? com.a.a.c.l.o.b() : c2[0]), bVar);
        }
        if (b2 == f) {
            com.a.a.c.j b4 = jVar.b(0);
            com.a.a.c.j b5 = jVar.b(1);
            com.a.a.c.i.e eVar = (com.a.a.c.i.e) b5.B();
            com.a.a.c.i.e eVar2 = eVar;
            if (eVar == null) {
                eVar2 = b(gVar.a(), b5);
            }
            return new com.a.a.c.c.b.v(jVar, (com.a.a.c.p) b4.A(), (com.a.a.c.k<Object>) b5.A(), eVar2);
        }
        String name = b2.getName();
        if (b2.isPrimitive() || name.startsWith("java.")) {
            com.a.a.c.k<?> a3 = com.a.a.c.c.b.x.a(b2, name);
            com.a.a.c.k<?> kVar = a3;
            if (a3 == null) {
                kVar = com.a.a.c.c.b.j.a(b2, name);
            }
            if (kVar != null) {
                return kVar;
            }
        }
        if (b2 == com.a.a.c.m.ac.class) {
            return new ao();
        }
        com.a.a.c.k<?> d2 = d(gVar, jVar, bVar);
        if (d2 != null) {
            return d2;
        }
        return com.a.a.c.c.b.r.a(gVar, b2, name);
    }

    private com.a.a.c.j a(com.a.a.c.f fVar, Class<?> cls) {
        com.a.a.c.j a2 = a(fVar, fVar.b(cls));
        if (a2 == null || a2.a(cls)) {
            return null;
        }
        return a2;
    }

    private com.a.a.c.k<?> a(Class<? extends com.a.a.c.m> cls, com.a.a.c.f fVar, com.a.a.c.b bVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> d2 = it.next().d();
            if (d2 != null) {
                return d2;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> a(com.a.a.c.l.j jVar, com.a.a.c.f fVar, com.a.a.c.b bVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> f2 = it.next().f();
            if (f2 != null) {
                return f2;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.k<Object> a(com.a.a.c.j jVar, com.a.a.c.f fVar, com.a.a.c.b bVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> e2 = it.next().e();
            if (e2 != null) {
                return e2;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> a(com.a.a.c.l.a aVar, com.a.a.c.f fVar, com.a.a.c.b bVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> g2 = it.next().g();
            if (g2 != null) {
                return g2;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> a(com.a.a.c.l.e eVar, com.a.a.c.f fVar, com.a.a.c.b bVar, com.a.a.c.i.e eVar2, com.a.a.c.k<?> kVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> h = it.next().h();
            if (h != null) {
                return h;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> a(com.a.a.c.l.d dVar, com.a.a.c.f fVar, com.a.a.c.b bVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> i = it.next().i();
            if (i != null) {
                return i;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> b(Class<?> cls, com.a.a.c.f fVar, com.a.a.c.b bVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> c2 = it.next().c();
            if (c2 != null) {
                return c2;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> a(com.a.a.c.l.h hVar, com.a.a.c.f fVar, com.a.a.c.b bVar, com.a.a.c.p pVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> j = it.next().j();
            if (j != null) {
                return j;
            }
        }
        return null;
    }

    private com.a.a.c.k<?> a(com.a.a.c.l.g gVar, com.a.a.c.f fVar, com.a.a.c.b bVar, com.a.a.c.p pVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        Iterator<w.a> it = this.f296a.e().iterator();
        while (it.hasNext()) {
            com.a.a.c.k<?> k = it.next().k();
            if (k != null) {
                return k;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.f.b bVar) {
        Object z;
        com.a.a.c.a f2 = gVar.f();
        if (f2 != null && (z = f2.z(bVar)) != null) {
            return gVar.b(bVar, z);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.p b(com.a.a.c.g gVar, com.a.a.c.f.b bVar) {
        Object A;
        com.a.a.c.a f2 = gVar.f();
        if (f2 != null && (A = f2.A(bVar)) != null) {
            return gVar.c(bVar, A);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.k<Object> c(com.a.a.c.g gVar, com.a.a.c.f.b bVar) {
        Object B;
        com.a.a.c.a f2 = gVar.f();
        if (f2 != null && (B = f2.B(bVar)) != null) {
            return gVar.b(bVar, B);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.j a(com.a.a.c.g gVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2) {
        com.a.a.c.p c2;
        com.a.a.c.a f2 = gVar.f();
        if (f2 == null) {
            return jVar2;
        }
        if (jVar2.p() && jVar2.t() != null && (c2 = gVar.c(jVar, f2.A(jVar))) != null) {
            com.a.a.c.l.g i = ((com.a.a.c.l.g) jVar2).i(c2);
            jVar2 = i;
            i.t();
        }
        if (jVar2.c()) {
            com.a.a.c.k<Object> b2 = gVar.b(jVar, f2.B(jVar));
            if (b2 != null) {
                jVar2 = jVar2.d(b2);
            }
            com.a.a.c.i.e b3 = b(gVar.a(), jVar2, jVar);
            if (b3 != null) {
                jVar2 = jVar2.b(b3);
            }
        }
        com.a.a.c.i.e a2 = a(gVar.a(), jVar2, jVar);
        if (a2 != null) {
            jVar2 = jVar2.a(a2);
        }
        return f2.b((com.a.a.c.b.q<?>) gVar.a(), (com.a.a.c.f.b) jVar, jVar2);
    }

    private static com.a.a.c.m.l a(Class<?> cls, com.a.a.c.f fVar, com.a.a.c.f.j jVar) {
        if (jVar != null) {
            if (fVar.g()) {
                com.a.a.c.m.i.a(jVar.i(), fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            return com.a.a.c.m.l.a(fVar, cls, jVar);
        }
        return com.a.a.c.m.l.a(fVar, cls);
    }

    private static boolean d(com.a.a.c.g gVar, com.a.a.c.f.b bVar) {
        i.a a2;
        com.a.a.c.a f2 = gVar.f();
        return (f2 == null || (a2 = f2.a(gVar.a(), bVar)) == null || a2 == i.a.DISABLED) ? false : true;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private static HashMap<String, Class<? extends Collection>> f297a;

        /* renamed from: b, reason: collision with root package name */
        private static HashMap<String, Class<? extends Map>> f298b;

        static {
            HashMap<String, Class<? extends Collection>> hashMap = new HashMap<>();
            hashMap.put(Collection.class.getName(), ArrayList.class);
            hashMap.put(List.class.getName(), ArrayList.class);
            hashMap.put(Set.class.getName(), HashSet.class);
            hashMap.put(SortedSet.class.getName(), TreeSet.class);
            hashMap.put(Queue.class.getName(), LinkedList.class);
            hashMap.put(AbstractList.class.getName(), ArrayList.class);
            hashMap.put(AbstractSet.class.getName(), HashSet.class);
            hashMap.put(Deque.class.getName(), LinkedList.class);
            hashMap.put(NavigableSet.class.getName(), TreeSet.class);
            f297a = hashMap;
            HashMap<String, Class<? extends Map>> hashMap2 = new HashMap<>();
            hashMap2.put(Map.class.getName(), LinkedHashMap.class);
            hashMap2.put(AbstractMap.class.getName(), LinkedHashMap.class);
            hashMap2.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
            hashMap2.put(SortedMap.class.getName(), TreeMap.class);
            hashMap2.put(NavigableMap.class.getName(), TreeMap.class);
            hashMap2.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
            f298b = hashMap2;
        }

        public static Class<?> a(com.a.a.c.j jVar) {
            return f297a.get(jVar.b().getName());
        }

        public static Class<?> b(com.a.a.c.j jVar) {
            return f298b.get(jVar.b().getName());
        }
    }

    /* renamed from: com.a.a.c.c.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b$b.class */
    public static class C0007b {
        private com.a.a.c.g e;

        /* renamed from: a, reason: collision with root package name */
        public final com.a.a.c.b f333a;

        /* renamed from: b, reason: collision with root package name */
        public final ap<?> f334b;
        public final com.a.a.c.c.a.e c;
        public final Map<com.a.a.c.f.o, com.a.a.c.f.s[]> d;
        private List<com.a.a.c.c.a.d> f;
        private int g;
        private List<com.a.a.c.c.a.d> h;
        private int i;

        public C0007b(com.a.a.c.g gVar, com.a.a.c.b bVar, ap<?> apVar, com.a.a.c.c.a.e eVar, Map<com.a.a.c.f.o, com.a.a.c.f.s[]> map) {
            this.e = gVar;
            this.f333a = bVar;
            this.f334b = apVar;
            this.c = eVar;
            this.d = map;
        }

        public final com.a.a.c.a a() {
            return this.e.f();
        }

        public final void a(com.a.a.c.c.a.d dVar) {
            if (this.f == null) {
                this.f = new LinkedList();
            }
            this.f.add(dVar);
        }

        public final void b() {
            this.g++;
        }

        public final boolean c() {
            return this.g > 0;
        }

        public final boolean d() {
            return this.f != null;
        }

        public final List<com.a.a.c.c.a.d> e() {
            return this.f;
        }

        public final void b(com.a.a.c.c.a.d dVar) {
            if (this.h == null) {
                this.h = new LinkedList();
            }
            this.h.add(dVar);
        }

        public final void f() {
            this.i++;
        }

        public final boolean g() {
            return this.i > 0;
        }

        public final boolean h() {
            return this.h != null;
        }

        public final List<com.a.a.c.c.a.d> i() {
            return this.h;
        }
    }
}
