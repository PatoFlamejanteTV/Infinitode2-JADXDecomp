package com.a.a.c.c.a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/c.class */
public final class c implements Serializable, Iterable<com.a.a.c.c.v> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f253a;

    /* renamed from: b, reason: collision with root package name */
    private int f254b;
    private int c;
    private int d;
    private Object[] e;
    private final com.a.a.c.c.v[] f;
    private final Map<String, List<com.a.a.c.w>> g;
    private final Map<String, String> h;
    private final Locale i;

    private c(boolean z, Collection<com.a.a.c.c.v> collection, Map<String, List<com.a.a.c.w>> map, Locale locale) {
        this.f253a = z;
        this.f = (com.a.a.c.c.v[]) collection.toArray(new com.a.a.c.c.v[collection.size()]);
        this.g = map;
        this.i = locale;
        this.h = a(map, z, locale);
        a(collection);
    }

    private c(c cVar, com.a.a.c.c.v vVar, int i, int i2) {
        this.f253a = cVar.f253a;
        this.i = cVar.i;
        this.f254b = cVar.f254b;
        this.c = cVar.c;
        this.d = cVar.d;
        this.g = cVar.g;
        this.h = cVar.h;
        this.e = Arrays.copyOf(cVar.e, cVar.e.length);
        this.f = (com.a.a.c.c.v[]) Arrays.copyOf(cVar.f, cVar.f.length);
        this.e[i] = vVar;
        this.f[i2] = vVar;
    }

    private c(c cVar, com.a.a.c.c.v vVar, String str, int i) {
        this.f253a = cVar.f253a;
        this.i = cVar.i;
        this.f254b = cVar.f254b;
        this.c = cVar.c;
        this.d = cVar.d;
        this.g = cVar.g;
        this.h = cVar.h;
        this.e = Arrays.copyOf(cVar.e, cVar.e.length);
        int length = cVar.f.length;
        this.f = (com.a.a.c.c.v[]) Arrays.copyOf(cVar.f, length + 1);
        this.f[length] = vVar;
        int i2 = this.f254b + 1;
        int i3 = i << 1;
        if (this.e[i3] != null) {
            i3 = (i2 + (i >> 1)) << 1;
            if (this.e[i3] != null) {
                i3 = ((i2 + (i2 >> 1)) << 1) + this.d;
                this.d += 2;
                if (i3 >= this.e.length) {
                    this.e = Arrays.copyOf(this.e, this.e.length + 4);
                }
            }
        }
        this.e[i3] = str;
        this.e[i3 + 1] = vVar;
    }

    private c(c cVar, boolean z) {
        this.f253a = z;
        this.i = cVar.i;
        this.g = cVar.g;
        this.h = cVar.h;
        this.f = (com.a.a.c.c.v[]) Arrays.copyOf(cVar.f, cVar.f.length);
        a(Arrays.asList(this.f));
    }

    public final c a(boolean z) {
        if (this.f253a == z) {
            return this;
        }
        return new c(this, z);
    }

    private void a(Collection<com.a.a.c.c.v> collection) {
        this.c = collection.size();
        int a2 = a(this.c);
        this.f254b = a2 - 1;
        Object[] objArr = new Object[(a2 + (a2 >> 1)) << 1];
        int i = 0;
        for (com.a.a.c.c.v vVar : collection) {
            if (vVar != null) {
                String c = c(vVar);
                int c2 = c(c);
                int i2 = c2 << 1;
                if (objArr[i2] != null) {
                    i2 = (a2 + (c2 >> 1)) << 1;
                    if (objArr[i2] != null) {
                        i2 = ((a2 + (a2 >> 1)) << 1) + i;
                        i += 2;
                        if (i2 >= objArr.length) {
                            Object[] objArr2 = objArr;
                            objArr = Arrays.copyOf(objArr2, objArr2.length + 4);
                        }
                    }
                }
                objArr[i2] = c;
                objArr[i2 + 1] = vVar;
            }
        }
        this.e = objArr;
        this.d = i;
    }

    private static final int a(int i) {
        if (i <= 5) {
            return 8;
        }
        if (i <= 12) {
            return 16;
        }
        int i2 = 32;
        while (true) {
            int i3 = i2;
            if (i3 < i + (i >> 2)) {
                i2 = i3 + i3;
            } else {
                return i3;
            }
        }
    }

    public static c a(com.a.a.c.b.q<?> qVar, Collection<com.a.a.c.c.v> collection, Map<String, List<com.a.a.c.w>> map, boolean z) {
        return new c(z, collection, map, qVar.t());
    }

    public final c a(com.a.a.c.c.v vVar) {
        String c = c(vVar);
        int length = this.e.length;
        for (int i = 1; i < length; i += 2) {
            com.a.a.c.c.v vVar2 = (com.a.a.c.c.v) this.e[i];
            if (vVar2 != null && vVar2.a().equals(c)) {
                return new c(this, vVar, i, d(vVar2));
            }
        }
        return new c(this, vVar, c, c(c));
    }

    public final c a() {
        int i = 0;
        int length = this.e.length;
        for (int i2 = 1; i2 < length; i2 += 2) {
            com.a.a.c.c.v vVar = (com.a.a.c.c.v) this.e[i2];
            if (vVar != null) {
                int i3 = i;
                i++;
                vVar.a(i3);
            }
        }
        return this;
    }

    public final c a(com.a.a.c.m.r rVar) {
        if (rVar == null || rVar == com.a.a.c.m.r.f742a) {
            return this;
        }
        int length = this.f.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            com.a.a.c.c.v vVar = this.f[i];
            if (vVar == null) {
                arrayList.add(vVar);
            } else {
                arrayList.add(a(vVar, rVar));
            }
        }
        return new c(this.f253a, arrayList, this.g, this.i);
    }

    public final c a(Collection<String> collection, Collection<String> collection2) {
        if ((collection == null || collection.isEmpty()) && collection2 == null) {
            return this;
        }
        int length = this.f.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            com.a.a.c.c.v vVar = this.f[i];
            if (vVar != null && !com.a.a.c.m.n.a(vVar.a(), collection, collection2)) {
                arrayList.add(vVar);
            }
        }
        return new c(this.f253a, arrayList, this.g, this.i);
    }

    public final void a(com.a.a.c.c.v vVar, com.a.a.c.c.v vVar2) {
        int length = this.e.length;
        for (int i = 1; i < length; i += 2) {
            if (this.e[i] == vVar) {
                this.e[i] = vVar2;
                this.f[d(vVar)] = vVar2;
                return;
            }
        }
        throw new NoSuchElementException("No entry '" + vVar.a() + "' found, can't replace");
    }

    public final void b(com.a.a.c.c.v vVar) {
        ArrayList arrayList = new ArrayList(this.c);
        String c = c(vVar);
        boolean z = false;
        int length = this.e.length;
        for (int i = 1; i < length; i += 2) {
            com.a.a.c.c.v vVar2 = (com.a.a.c.c.v) this.e[i];
            if (vVar2 != null) {
                if (!z) {
                    boolean equals = c.equals(this.e[i - 1]);
                    z = equals;
                    if (equals) {
                        this.f[d(vVar2)] = null;
                    }
                }
                arrayList.add(vVar2);
            }
        }
        if (!z) {
            throw new NoSuchElementException("No entry '" + vVar.a() + "' found, can't remove");
        }
        a(arrayList);
    }

    public final int b() {
        return this.c;
    }

    public final boolean c() {
        return this.f253a;
    }

    @Override // java.lang.Iterable
    public final Iterator<com.a.a.c.c.v> iterator() {
        return e().iterator();
    }

    private List<com.a.a.c.c.v> e() {
        ArrayList arrayList = new ArrayList(this.c);
        int length = this.e.length;
        for (int i = 1; i < length; i += 2) {
            com.a.a.c.c.v vVar = (com.a.a.c.c.v) this.e[i];
            if (vVar != null) {
                arrayList.add(vVar);
            }
        }
        return arrayList;
    }

    public final com.a.a.c.c.v[] d() {
        return this.f;
    }

    private String c(com.a.a.c.c.v vVar) {
        return this.f253a ? vVar.a().toLowerCase(this.i) : vVar.a();
    }

    public final com.a.a.c.c.v a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Cannot pass null property name");
        }
        if (this.f253a) {
            str = str.toLowerCase(this.i);
        }
        int hashCode = str.hashCode() & this.f254b;
        int i = hashCode << 1;
        Object obj = this.e[i];
        if (obj == str || str.equals(obj)) {
            return (com.a.a.c.c.v) this.e[i + 1];
        }
        return a(str, hashCode, obj);
    }

    private final com.a.a.c.c.v a(String str, int i, Object obj) {
        if (obj == null) {
            return b(this.h.get(str));
        }
        int i2 = this.f254b + 1;
        int i3 = (i2 + (i >> 1)) << 1;
        Object obj2 = this.e[i3];
        if (str.equals(obj2)) {
            return (com.a.a.c.c.v) this.e[i3 + 1];
        }
        if (obj2 != null) {
            int i4 = (i2 + (i2 >> 1)) << 1;
            int i5 = i4 + this.d;
            for (int i6 = i4; i6 < i5; i6 += 2) {
                Object obj3 = this.e[i6];
                if (obj3 == str || str.equals(obj3)) {
                    return (com.a.a.c.c.v) this.e[i6 + 1];
                }
            }
        }
        return b(this.h.get(str));
    }

    private com.a.a.c.c.v b(String str) {
        if (str == null) {
            return null;
        }
        int c = c(str);
        int i = c << 1;
        Object obj = this.e[i];
        if (str.equals(obj)) {
            return (com.a.a.c.c.v) this.e[i + 1];
        }
        if (obj == null) {
            return null;
        }
        return a(str, c);
    }

    private com.a.a.c.c.v a(String str, int i) {
        int i2 = this.f254b + 1;
        int i3 = (i2 + (i >> 1)) << 1;
        Object obj = this.e[i3];
        if (str.equals(obj)) {
            return (com.a.a.c.c.v) this.e[i3 + 1];
        }
        if (obj != null) {
            int i4 = (i2 + (i2 >> 1)) << 1;
            int i5 = i4 + this.d;
            for (int i6 = i4; i6 < i5; i6 += 2) {
                Object obj2 = this.e[i6];
                if (obj2 == str || str.equals(obj2)) {
                    return (com.a.a.c.c.v) this.e[i6 + 1];
                }
            }
            return null;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        int i = 0;
        Iterator<com.a.a.c.c.v> it = iterator();
        while (it.hasNext()) {
            com.a.a.c.c.v next = it.next();
            int i2 = i;
            i++;
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(next.a());
            sb.append('(');
            sb.append(next.c());
            sb.append(')');
        }
        sb.append(']');
        if (!this.g.isEmpty()) {
            sb.append("(aliases: ");
            sb.append(this.g);
            sb.append(")");
        }
        return sb.toString();
    }

    private static com.a.a.c.c.v a(com.a.a.c.c.v vVar, com.a.a.c.m.r rVar) {
        com.a.a.c.k<Object> a2;
        if (vVar == null) {
            return vVar;
        }
        com.a.a.c.c.v a3 = vVar.a(rVar.a(vVar.a()));
        com.a.a.c.c.v vVar2 = a3;
        com.a.a.c.k<Object> p = a3.p();
        if (p != null && (a2 = p.a(rVar)) != p) {
            vVar2 = vVar2.a((com.a.a.c.k<?>) a2);
        }
        return vVar2;
    }

    private final int d(com.a.a.c.c.v vVar) {
        int length = this.f.length;
        for (int i = 0; i < length; i++) {
            if (this.f[i] == vVar) {
                return i;
            }
        }
        throw new IllegalStateException("Illegal state: property '" + vVar.a() + "' missing from _propsInOrder");
    }

    private final int c(String str) {
        return str.hashCode() & this.f254b;
    }

    private static Map<String, String> a(Map<String, List<com.a.a.c.w>> map, boolean z, Locale locale) {
        if (map == null || map.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, List<com.a.a.c.w>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (z) {
                key = key.toLowerCase(locale);
            }
            Iterator<com.a.a.c.w> it = entry.getValue().iterator();
            while (it.hasNext()) {
                String b2 = it.next().b();
                if (z) {
                    b2 = b2.toLowerCase(locale);
                }
                hashMap.put(b2, key);
            }
        }
        return hashMap;
    }
}
