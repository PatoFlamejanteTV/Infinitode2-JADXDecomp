package com.a.a.c.c.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/aq.class */
public class aq extends ae<Object> implements com.a.a.c.c.k, com.a.a.c.c.t {

    /* renamed from: a, reason: collision with root package name */
    private static Object[] f327a = new Object[0];

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.k<Object> f328b;
    private com.a.a.c.k<Object> c;
    private com.a.a.c.k<Object> d;
    private com.a.a.c.k<Object> e;
    private com.a.a.c.j f;
    private com.a.a.c.j g;
    private boolean h;

    @Deprecated
    public aq() {
        this((com.a.a.c.j) null, (com.a.a.c.j) null);
    }

    public aq(com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        super((Class<?>) Object.class);
        this.f = jVar;
        this.g = jVar2;
        this.h = false;
    }

    private aq(aq aqVar, boolean z) {
        super((Class<?>) Object.class);
        this.f328b = aqVar.f328b;
        this.c = aqVar.c;
        this.d = aqVar.d;
        this.e = aqVar.e;
        this.f = aqVar.f;
        this.g = aqVar.g;
        this.h = z;
    }

    @Override // com.a.a.c.c.t
    public final void d(com.a.a.c.g gVar) {
        com.a.a.c.j b2 = gVar.b(Object.class);
        com.a.a.c.j b3 = gVar.b(String.class);
        com.a.a.c.l.o b4 = gVar.b();
        if (this.f == null) {
            this.c = b(a(gVar, b4.a(List.class, b2)));
        } else {
            this.c = a(gVar, this.f);
        }
        if (this.g == null) {
            this.f328b = b(a(gVar, b4.a(Map.class, b3, b2)));
        } else {
            this.f328b = a(gVar, this.g);
        }
        this.d = b(a(gVar, b3));
        this.e = b(a(gVar, b4.a(Number.class)));
        com.a.a.c.j b5 = com.a.a.c.l.o.b();
        this.f328b = gVar.b(this.f328b, (com.a.a.c.c) null, b5);
        this.c = gVar.b(this.c, (com.a.a.c.c) null, b5);
        this.d = gVar.b(this.d, (com.a.a.c.c) null, b5);
        this.e = gVar.b(this.e, (com.a.a.c.c) null, b5);
    }

    private static com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.j jVar) {
        return gVar.a(jVar);
    }

    private static com.a.a.c.k<Object> b(com.a.a.c.k<Object> kVar) {
        if (com.a.a.c.m.i.e(kVar)) {
            return null;
        }
        return kVar;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        boolean z = cVar == null && Boolean.FALSE.equals(gVar.a().g(Object.class));
        if (this.d == null && this.e == null && this.f328b == null && this.c == null && getClass() == aq.class) {
            return ar.a(z);
        }
        if (z != this.h) {
            return new aq(this, z);
        }
        return this;
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Untyped;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return null;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        switch (lVar.l()) {
            case 1:
            case 2:
            case 5:
                if (this.f328b != null) {
                    return this.f328b.a(lVar, gVar);
                }
                return e(lVar, gVar);
            case 3:
                if (gVar.a(com.a.a.c.i.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return f(lVar, gVar);
                }
                if (this.c != null) {
                    return this.c.a(lVar, gVar);
                }
                return c(lVar, gVar);
            case 4:
            default:
                return gVar.a(Object.class, lVar);
            case 6:
                if (this.d != null) {
                    return this.d.a(lVar, gVar);
                }
                return lVar.w();
            case 7:
                if (this.e != null) {
                    return this.e.a(lVar, gVar);
                }
                if (gVar.a(r)) {
                    return u(lVar, gVar);
                }
                return lVar.B();
            case 8:
                if (this.e != null) {
                    return this.e.a(lVar, gVar);
                }
                if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return lVar.L();
                }
                return lVar.B();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return lVar.N();
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        switch (lVar.l()) {
            case 1:
            case 3:
            case 5:
                return eVar.d(lVar, gVar);
            case 2:
            case 4:
            default:
                return gVar.a(Object.class, lVar);
            case 6:
                if (this.d != null) {
                    return this.d.a(lVar, gVar);
                }
                return lVar.w();
            case 7:
                if (this.e != null) {
                    return this.e.a(lVar, gVar);
                }
                if (gVar.a(r)) {
                    return u(lVar, gVar);
                }
                return lVar.B();
            case 8:
                if (this.e != null) {
                    return this.e.a(lVar, gVar);
                }
                if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return lVar.L();
                }
                return lVar.B();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return lVar.N();
        }
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        if (this.h) {
            return a(lVar, gVar);
        }
        switch (lVar.l()) {
            case 1:
            case 2:
            case 5:
                if (this.f328b != null) {
                    return this.f328b.a(lVar, gVar, (com.a.a.c.g) obj);
                }
                if (obj instanceof Map) {
                    return a(lVar, gVar, (Map<Object, Object>) obj);
                }
                return e(lVar, gVar);
            case 3:
                if (this.c != null) {
                    return this.c.a(lVar, gVar, (com.a.a.c.g) obj);
                }
                if (obj instanceof Collection) {
                    return a(lVar, gVar, (Collection<Object>) obj);
                }
                if (gVar.a(com.a.a.c.i.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return f(lVar, gVar);
                }
                return c(lVar, gVar);
            case 4:
            default:
                return a(lVar, gVar);
            case 6:
                if (this.d != null) {
                    return this.d.a(lVar, gVar, (com.a.a.c.g) obj);
                }
                return lVar.w();
            case 7:
                if (this.e != null) {
                    return this.e.a(lVar, gVar, (com.a.a.c.g) obj);
                }
                if (gVar.a(r)) {
                    return u(lVar, gVar);
                }
                return lVar.B();
            case 8:
                if (this.e != null) {
                    return this.e.a(lVar, gVar, (com.a.a.c.g) obj);
                }
                if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return lVar.L();
                }
                return lVar.B();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return lVar.N();
        }
    }

    private Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.g() == com.a.a.b.o.END_ARRAY) {
            return new ArrayList(2);
        }
        Object a2 = a(lVar, gVar);
        if (lVar.g() == com.a.a.b.o.END_ARRAY) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(a2);
            return arrayList;
        }
        Object a3 = a(lVar, gVar);
        if (lVar.g() == com.a.a.b.o.END_ARRAY) {
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(a2);
            arrayList2.add(a3);
            return arrayList2;
        }
        com.a.a.c.m.f n = gVar.n();
        Object[] a4 = n.a();
        a4[0] = a2;
        int i = 0 + 1 + 1;
        a4[1] = a3;
        int i2 = 2;
        do {
            Object a5 = a(lVar, gVar);
            i2++;
            if (i >= a4.length) {
                a4 = n.a(a4);
                i = 0;
            }
            int i3 = i;
            i++;
            a4[i3] = a5;
        } while (lVar.g() != com.a.a.b.o.END_ARRAY);
        ArrayList arrayList3 = new ArrayList(i2);
        n.a(a4, i, arrayList3);
        gVar.a(n);
        return arrayList3;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<Object> collection) {
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            collection.add(a(lVar, gVar));
        }
        return collection;
    }

    private Object e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String str;
        String h;
        com.a.a.b.o k = lVar.k();
        if (k == com.a.a.b.o.START_OBJECT) {
            str = lVar.h();
        } else if (k == com.a.a.b.o.FIELD_NAME) {
            str = lVar.v();
        } else {
            if (k != com.a.a.b.o.END_OBJECT) {
                return gVar.a(a(), lVar);
            }
            str = null;
        }
        if (str == null) {
            return new LinkedHashMap(2);
        }
        lVar.g();
        Object a2 = a(lVar, gVar);
        String h2 = lVar.h();
        if (h2 == null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(2);
            linkedHashMap.put(str, a2);
            return linkedHashMap;
        }
        lVar.g();
        Object a3 = a(lVar, gVar);
        String h3 = lVar.h();
        String str2 = h3;
        if (h3 == null) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(str, a2);
            if (linkedHashMap2.put(h2, a3) != null) {
                return a(lVar, gVar, linkedHashMap2, str, a2, a3, str2);
            }
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(str, a2);
        if (linkedHashMap3.put(h2, a3) != null) {
            return a(lVar, gVar, linkedHashMap3, str, a2, a3, str2);
        }
        do {
            lVar.g();
            Object a4 = a(lVar, gVar);
            Object put = linkedHashMap3.put(str2, a4);
            if (put != null) {
                return a(lVar, gVar, linkedHashMap3, str2, put, a4, lVar.h());
            }
            h = lVar.h();
            str2 = h;
        } while (h != null);
        return linkedHashMap3;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<String, Object> map, String str, Object obj, Object obj2, String str2) {
        boolean a2 = gVar.a(com.a.a.b.s.DUPLICATE_PROPERTIES);
        if (a2) {
            a(map, str, obj, obj2);
        }
        while (str2 != null) {
            lVar.g();
            Object a3 = a(lVar, gVar);
            Object put = map.put(str2, a3);
            if (put != null && a2) {
                a(map, str, put, a3);
            }
            str2 = lVar.h();
        }
        return map;
    }

    private static void a(Map<String, Object> map, String str, Object obj, Object obj2) {
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            map.put(str, obj);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj);
            arrayList.add(obj2);
            map.put(str, arrayList);
        }
    }

    private Object[] f(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.g() == com.a.a.b.o.END_ARRAY) {
            return f327a;
        }
        com.a.a.c.m.f n = gVar.n();
        Object[] a2 = n.a();
        int i = 0;
        do {
            Object a3 = a(lVar, gVar);
            if (i >= a2.length) {
                a2 = n.a(a2);
                i = 0;
            }
            int i2 = i;
            i++;
            a2[i2] = a3;
        } while (lVar.g() != com.a.a.b.o.END_ARRAY);
        Object[] b2 = n.b(a2, i);
        gVar.a(n);
        return b2;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Map<Object, Object> map) {
        Object a2;
        String h;
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.START_OBJECT) {
            oVar = lVar.g();
        }
        if (oVar == com.a.a.b.o.END_OBJECT) {
            return map;
        }
        String v = lVar.v();
        do {
            lVar.g();
            Object obj = map.get(v);
            if (obj != null) {
                a2 = a(lVar, gVar, obj);
            } else {
                a2 = a(lVar, gVar);
            }
            if (a2 != obj) {
                map.put(v, a2);
            }
            h = lVar.h();
            v = h;
        } while (h != null);
        return map;
    }
}
