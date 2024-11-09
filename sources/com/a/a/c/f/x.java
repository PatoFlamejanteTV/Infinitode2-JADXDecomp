package com.a.a.c.f;

import com.a.a.a.ac;
import com.a.a.a.ad;
import com.a.a.a.af;
import com.a.a.a.am;
import com.a.a.a.b;
import com.a.a.a.i;
import com.a.a.a.l;
import com.a.a.a.q;
import com.a.a.a.s;
import com.a.a.a.t;
import com.a.a.a.x;
import com.a.a.c.a;
import com.a.a.c.a.b;
import com.a.a.c.a.e;
import com.a.a.c.a.f;
import com.a.a.c.k;
import com.a.a.c.m.k;
import com.a.a.c.o;
import com.a.a.c.p;
import java.io.Closeable;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/f/x.class */
public final class x extends com.a.a.c.a implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final Class<? extends Annotation>[] f481a = {com.a.a.c.a.f.class, com.a.a.a.aj.class, com.a.a.a.l.class, com.a.a.a.af.class, com.a.a.a.aa.class, com.a.a.a.ah.class, com.a.a.a.h.class, com.a.a.a.v.class};

    /* renamed from: b, reason: collision with root package name */
    private static final Class<? extends Annotation>[] f482b = {com.a.a.c.a.c.class, com.a.a.a.aj.class, com.a.a.a.l.class, com.a.a.a.af.class, com.a.a.a.ah.class, com.a.a.a.h.class, com.a.a.a.v.class, com.a.a.a.w.class};
    private static final com.a.a.c.e.c c;
    private transient com.a.a.c.m.o<Class<?>, Boolean> d = new com.a.a.c.m.o<>(48, 48);
    private boolean e = true;

    static {
        com.a.a.c.e.c cVar = null;
        try {
            cVar = com.a.a.c.e.c.a();
        } catch (Throwable unused) {
        }
        c = cVar;
    }

    @Override // com.a.a.c.a
    public final boolean a(Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        Boolean a2 = this.d.a(annotationType);
        Boolean bool = a2;
        if (a2 == null) {
            bool = Boolean.valueOf(annotationType.getAnnotation(com.a.a.a.a.class) != null);
            this.d.b(annotationType, bool);
        }
        return bool.booleanValue();
    }

    @Override // com.a.a.c.a
    public final String[] a(Class<?> cls, Enum<?>[] enumArr, String[] strArr) {
        com.a.a.a.x xVar;
        HashMap hashMap = null;
        for (Field field : cls.getDeclaredFields()) {
            if (field.isEnumConstant() && (xVar = (com.a.a.a.x) field.getAnnotation(com.a.a.a.x.class)) != null) {
                String a2 = xVar.a();
                if (!a2.isEmpty()) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(field.getName(), a2);
                }
            }
        }
        if (hashMap != null) {
            int length = enumArr.length;
            for (int i = 0; i < length; i++) {
                String str = (String) hashMap.get(enumArr[i].name());
                if (str != null) {
                    strArr[i] = str;
                }
            }
        }
        return strArr;
    }

    @Override // com.a.a.c.a
    public final void a(Class<?> cls, Enum<?>[] enumArr, String[][] strArr) {
        com.a.a.a.c cVar;
        for (Field field : cls.getDeclaredFields()) {
            if (field.isEnumConstant() && (cVar = (com.a.a.a.c) field.getAnnotation(com.a.a.a.c.class)) != null) {
                String[] a2 = cVar.a();
                if (a2.length != 0) {
                    String name = field.getName();
                    int length = enumArr.length;
                    for (int i = 0; i < length; i++) {
                        if (name.equals(enumArr[i].name())) {
                            strArr[i] = a2;
                        }
                    }
                }
            }
        }
    }

    @Override // com.a.a.c.a
    public final Enum<?> a(Class<Enum<?>> cls) {
        return com.a.a.c.m.i.a(cls, com.a.a.a.j.class);
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.w a(d dVar) {
        com.a.a.a.ab abVar = (com.a.a.a.ab) a(dVar, com.a.a.a.ab.class);
        if (abVar == null) {
            return null;
        }
        String b2 = abVar.b();
        String str = b2;
        if (b2 != null && str.isEmpty()) {
            str = null;
        }
        return com.a.a.c.w.a(abVar.a(), str);
    }

    @Override // com.a.a.c.a
    public final Boolean b(d dVar) {
        com.a.a.a.r rVar = (com.a.a.a.r) a(dVar, com.a.a.a.r.class);
        if (rVar == null) {
            return null;
        }
        return Boolean.valueOf(rVar.a());
    }

    @Override // com.a.a.c.a
    public final q.a b(b bVar) {
        com.a.a.a.q qVar = (com.a.a.a.q) a(bVar, com.a.a.a.q.class);
        if (qVar == null) {
            return q.a.a();
        }
        return q.a.a(qVar);
    }

    @Override // com.a.a.c.a
    @Deprecated
    public final q.a e(b bVar) {
        return b(bVar);
    }

    @Override // com.a.a.c.a
    public final t.a c(b bVar) {
        com.a.a.a.t tVar = (com.a.a.a.t) a(bVar, com.a.a.a.t.class);
        if (tVar == null) {
            return t.a.a();
        }
        return t.a.a(tVar);
    }

    @Override // com.a.a.c.a
    public final Object d(b bVar) {
        com.a.a.a.k kVar = (com.a.a.a.k) a(bVar, com.a.a.a.k.class);
        if (kVar != null) {
            String a2 = kVar.a();
            if (!a2.isEmpty()) {
                return a2;
            }
            return null;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object c(d dVar) {
        com.a.a.c.a.d dVar2 = (com.a.a.c.a.d) a(dVar, com.a.a.c.a.d.class);
        if (dVar2 == null) {
            return null;
        }
        return dVar2.a();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.a.a.c.f.ap<?>, com.a.a.c.f.ap] */
    @Override // com.a.a.c.a
    public final ap<?> a(d dVar, ap<?> apVar) {
        com.a.a.a.f fVar = (com.a.a.a.f) a(dVar, com.a.a.a.f.class);
        return fVar == null ? apVar : apVar.a(fVar);
    }

    @Override // com.a.a.c.a
    public final String g(j jVar) {
        com.a.a.c.w L = L(jVar);
        if (L == null) {
            return null;
        }
        return L.b();
    }

    @Override // com.a.a.c.a
    public final List<com.a.a.c.w> l(b bVar) {
        com.a.a.a.c cVar = (com.a.a.a.c) a(bVar, com.a.a.a.c.class);
        if (cVar == null) {
            return null;
        }
        String[] a2 = cVar.a();
        int length = a2.length;
        if (length != 0) {
            ArrayList arrayList = new ArrayList(length);
            for (String str : a2) {
                arrayList.add(com.a.a.c.w.a(str));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    @Override // com.a.a.c.a
    public final boolean d(j jVar) {
        return K(jVar);
    }

    @Override // com.a.a.c.a
    public final Boolean f(j jVar) {
        com.a.a.a.x xVar = (com.a.a.a.x) a(jVar, com.a.a.a.x.class);
        if (xVar != null) {
            return Boolean.valueOf(xVar.c());
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final x.a m(b bVar) {
        com.a.a.a.x xVar = (com.a.a.a.x) a(bVar, com.a.a.a.x.class);
        if (xVar != null) {
            return xVar.f();
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final String j(b bVar) {
        com.a.a.a.y yVar = (com.a.a.a.y) a(bVar, com.a.a.a.y.class);
        if (yVar == null) {
            return null;
        }
        return yVar.a();
    }

    @Override // com.a.a.c.a
    public final Integer k(b bVar) {
        int d;
        com.a.a.a.x xVar = (com.a.a.a.x) a(bVar, com.a.a.a.x.class);
        if (xVar != null && (d = xVar.d()) != -1) {
            return Integer.valueOf(d);
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final String i(b bVar) {
        com.a.a.a.x xVar = (com.a.a.a.x) a(bVar, com.a.a.a.x.class);
        if (xVar == null) {
            return null;
        }
        String e = xVar.e();
        if (e.isEmpty()) {
            return null;
        }
        return e;
    }

    @Override // com.a.a.c.a
    public final l.d h(b bVar) {
        com.a.a.a.l lVar = (com.a.a.a.l) a(bVar, com.a.a.a.l.class);
        if (lVar == null) {
            return null;
        }
        return l.d.a(lVar);
    }

    @Override // com.a.a.c.a
    public final a.C0004a b(j jVar) {
        com.a.a.a.v vVar = (com.a.a.a.v) a(jVar, com.a.a.a.v.class);
        if (vVar != null) {
            return a.C0004a.a(vVar.a());
        }
        com.a.a.a.h hVar = (com.a.a.a.h) a(jVar, com.a.a.a.h.class);
        if (hVar != null) {
            return a.C0004a.b(hVar.a());
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.m.r c(j jVar) {
        com.a.a.a.ah ahVar = (com.a.a.a.ah) a(jVar, com.a.a.a.ah.class);
        if (ahVar == null || !ahVar.a()) {
            return null;
        }
        return com.a.a.c.m.r.a(ahVar.b(), ahVar.c());
    }

    @Override // com.a.a.c.a
    public final b.a e(j jVar) {
        String name;
        com.a.a.a.b bVar = (com.a.a.a.b) a(jVar, com.a.a.a.b.class);
        if (bVar == null) {
            return null;
        }
        b.a a2 = b.a.a(bVar);
        b.a aVar = a2;
        if (!a2.b()) {
            if (!(jVar instanceof k)) {
                name = jVar.d().getName();
            } else {
                k kVar = (k) jVar;
                if (kVar.f() == 0) {
                    name = jVar.d().getName();
                } else {
                    name = kVar.a(0).getName();
                }
            }
            aVar = aVar.b(name);
        }
        return aVar;
    }

    @Override // com.a.a.c.a
    @Deprecated
    public final Object h(j jVar) {
        b.a e = e(jVar);
        if (e == null) {
            return null;
        }
        return e.a();
    }

    @Override // com.a.a.c.a
    public final Class<?>[] g(b bVar) {
        com.a.a.a.aj ajVar = (com.a.a.a.aj) a(bVar, com.a.a.a.aj.class);
        if (ajVar == null) {
            return null;
        }
        return ajVar.a();
    }

    @Override // com.a.a.c.a
    public final k a(k kVar, k kVar2) {
        Class<?> a2 = kVar.a(0);
        Class<?> a3 = kVar2.a(0);
        if (a2.isPrimitive()) {
            if (!a3.isPrimitive()) {
                return kVar;
            }
            return null;
        }
        if (a3.isPrimitive()) {
            return kVar2;
        }
        if (a2 == String.class) {
            if (a3 != String.class) {
                return kVar;
            }
            return null;
        }
        if (a3 == String.class) {
            return kVar2;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.w c() {
        return null;
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.i.h<?> a(com.a.a.c.b.q<?> qVar, d dVar, com.a.a.c.j jVar) {
        return c(qVar, dVar, jVar);
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.i.h<?> a(com.a.a.c.b.q<?> qVar, j jVar, com.a.a.c.j jVar2) {
        if (jVar2.n() || jVar2.F()) {
            return null;
        }
        return c(qVar, jVar, jVar2);
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.i.h<?> b(com.a.a.c.b.q<?> qVar, j jVar, com.a.a.c.j jVar2) {
        if (jVar2.u() == null) {
            throw new IllegalArgumentException("Must call method with a container or reference type (got " + jVar2 + ")");
        }
        return c(qVar, jVar, jVar2);
    }

    @Override // com.a.a.c.a
    public final List<com.a.a.c.i.b> f(b bVar) {
        com.a.a.a.ad adVar = (com.a.a.a.ad) a(bVar, com.a.a.a.ad.class);
        if (adVar == null) {
            return null;
        }
        ad.a[] a2 = adVar.a();
        if (adVar.b()) {
            return a(bVar.b(), a2);
        }
        ArrayList arrayList = new ArrayList(a2.length);
        for (ad.a aVar : a2) {
            arrayList.add(new com.a.a.c.i.b(aVar.a(), aVar.b()));
            for (String str : aVar.c()) {
                arrayList.add(new com.a.a.c.i.b(aVar.a(), str));
            }
        }
        return arrayList;
    }

    private static List<com.a.a.c.i.b> a(String str, ad.a[] aVarArr) {
        ArrayList arrayList = new ArrayList(aVarArr.length);
        HashSet hashSet = new HashSet();
        for (ad.a aVar : aVarArr) {
            String b2 = aVar.b();
            if (!b2.isEmpty() && hashSet.contains(b2)) {
                throw new IllegalArgumentException("Annotated type [" + str + "] got repeated subtype name [" + b2 + "]");
            }
            hashSet.add(b2);
            arrayList.add(new com.a.a.c.i.b(aVar.a(), b2));
            for (String str2 : aVar.c()) {
                if (!str2.isEmpty() && hashSet.contains(str2)) {
                    throw new IllegalArgumentException("Annotated type [" + str + "] got repeated subtype name [" + str2 + "]");
                }
                hashSet.add(str2);
                arrayList.add(new com.a.a.c.i.b(aVar.a(), str2));
            }
        }
        return arrayList;
    }

    @Override // com.a.a.c.a
    public final String d(d dVar) {
        com.a.a.a.ag agVar = (com.a.a.a.ag) a(dVar, com.a.a.a.ag.class);
        if (agVar == null) {
            return null;
        }
        return agVar.a();
    }

    @Override // com.a.a.c.a
    public final Boolean a(j jVar) {
        return Boolean.valueOf(b(jVar, (Class<? extends Annotation>) com.a.a.a.ae.class));
    }

    @Override // com.a.a.c.a
    public final ad a(b bVar) {
        com.a.a.a.n nVar = (com.a.a.a.n) a(bVar, com.a.a.a.n.class);
        if (nVar == null || nVar.b() == am.b.class) {
            return null;
        }
        return new ad(com.a.a.c.w.a(nVar.a()), nVar.d(), nVar.b(), nVar.c());
    }

    @Override // com.a.a.c.a
    public final ad a(b bVar, ad adVar) {
        com.a.a.a.o oVar = (com.a.a.a.o) a(bVar, com.a.a.a.o.class);
        if (oVar == null) {
            return adVar;
        }
        if (adVar == null) {
            adVar = ad.a();
        }
        return adVar.a(oVar.a());
    }

    @Override // com.a.a.c.a
    public final Object n(b bVar) {
        Class<? extends com.a.a.c.o> a2;
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar != null && (a2 = fVar.a()) != o.a.class) {
            return a2;
        }
        com.a.a.a.aa aaVar = (com.a.a.a.aa) a(bVar, com.a.a.a.aa.class);
        if (aaVar != null && aaVar.a()) {
            return new com.a.a.c.k.b.ad(bVar.d());
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object o(b bVar) {
        Class<? extends com.a.a.c.o> c2;
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar != null && (c2 = fVar.c()) != o.a.class) {
            return c2;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object p(b bVar) {
        Class<? extends com.a.a.c.o> b2;
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar != null && (b2 = fVar.b()) != o.a.class) {
            return b2;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object q(b bVar) {
        Class<? extends com.a.a.c.o> d;
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar != null && (d = fVar.d()) != o.a.class) {
            return d;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final s.b t(b bVar) {
        com.a.a.a.s sVar = (com.a.a.a.s) a(bVar, com.a.a.a.s.class);
        s.b a2 = sVar == null ? s.b.a() : s.b.a(sVar);
        s.b bVar2 = a2;
        if (a2.b() == s.a.USE_DEFAULTS) {
            bVar2 = a(bVar, bVar2);
        }
        return bVar2;
    }

    private s.b a(b bVar, s.b bVar2) {
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar != null) {
            switch (y.f483a[fVar.k().ordinal()]) {
                case 1:
                    return bVar2.a(s.a.ALWAYS);
                case 2:
                    return bVar2.a(s.a.NON_NULL);
                case 3:
                    return bVar2.a(s.a.NON_DEFAULT);
                case 4:
                    return bVar2.a(s.a.NON_EMPTY);
            }
        }
        return bVar2;
    }

    @Override // com.a.a.c.a
    public final f.b r(b bVar) {
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar == null) {
            return null;
        }
        return fVar.h();
    }

    @Override // com.a.a.c.a
    public final Object s(b bVar) {
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        if (fVar == null) {
            return null;
        }
        return a(fVar.i(), k.a.class);
    }

    @Override // com.a.a.c.a
    public final Object i(j jVar) {
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(jVar, com.a.a.c.a.f.class);
        if (fVar == null) {
            return null;
        }
        return a(fVar.j(), k.a.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.a.a.c.j] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.a.a.c.l.o] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Throwable, java.lang.Class] */
    /* JADX WARN: Type inference failed for: r0v46, types: [com.a.a.c.j] */
    /* JADX WARN: Type inference failed for: r0v55, types: [java.lang.Throwable, java.lang.Class] */
    /* JADX WARN: Type inference failed for: r0v81, types: [java.lang.Throwable, java.lang.Class] */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.a.a.c.j] */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r1v26, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r1v38, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object[]] */
    @Override // com.a.a.c.a
    public final com.a.a.c.j a(com.a.a.c.b.q<?> qVar, b bVar, com.a.a.c.j jVar) {
        com.a.a.c.j a2;
        com.a.a.c.j a3;
        ?? r10 = jVar;
        ?? p = qVar.p();
        com.a.a.c.a.f fVar = (com.a.a.c.a.f) a(bVar, com.a.a.c.a.f.class);
        Class<?> b2 = fVar == null ? null : b(fVar.e());
        ?? r1 = b2;
        ?? r102 = r10;
        if (b2 != null) {
            if (r10.a(r1)) {
                r102 = r10.a();
            } else {
                ?? b3 = r10.b();
                try {
                    if (r1.isAssignableFrom(b3)) {
                        r102 = com.a.a.c.l.o.b((com.a.a.c.j) r10, (Class<?>) r1);
                    } else if (b3.isAssignableFrom(r1)) {
                        r102 = p.a(r10, r1);
                    } else if (b((Class<?>) b3, (Class<?>) r1)) {
                        r102 = r10.a();
                    } else {
                        throw a(String.format("Cannot refine serialization type %s into %s; types not related", new Object[]{r10, r1.getName()}));
                    }
                } catch (IllegalArgumentException e) {
                    throw a((Throwable) b3, String.format("Failed to widen type %s with annotation (value %s), from '%s': %s", new Object[]{r10, r1.getName(), bVar.b(), e.getMessage()}));
                }
            }
        }
        boolean p2 = (r102 == true ? 1 : 0).p();
        ?? r103 = r102;
        if (p2) {
            ?? t = (r102 == true ? 1 : 0).t();
            Class<?> b4 = fVar == null ? null : b(fVar.f());
            ?? r12 = b4;
            r103 = r102;
            if (b4 != null) {
                if (t.a(r12)) {
                    a3 = t.a();
                } else {
                    ?? b5 = t.b();
                    try {
                        if (r12.isAssignableFrom(b5)) {
                            a3 = com.a.a.c.l.o.b((com.a.a.c.j) t, (Class<?>) r12);
                        } else if (b5.isAssignableFrom(r12)) {
                            a3 = p.a(t, r12);
                        } else if (b((Class<?>) b5, (Class<?>) r12)) {
                            a3 = t.a();
                        } else {
                            throw a(String.format("Cannot refine serialization key type %s into %s; types not related", new Object[]{t, r12.getName()}));
                        }
                    } catch (IllegalArgumentException e2) {
                        throw a((Throwable) b5, String.format("Failed to widen key type of %s with concrete-type annotation (value %s), from '%s': %s", r102 == true ? 1 : 0, r12.getName(), bVar.b(), e2.getMessage()));
                    }
                }
                r103 = (r102 == true ? 1 : 0).e(a3);
            }
        }
        ?? u = (r103 == true ? 1 : 0).u();
        com.a.a.c.j jVar2 = r103;
        if (u != 0) {
            Class<?> b6 = fVar == null ? null : b(fVar.g());
            ?? r13 = b6;
            jVar2 = r103;
            if (b6 != null) {
                if (u.a(r13)) {
                    a2 = u.a();
                } else {
                    ?? b7 = u.b();
                    try {
                        if (r13.isAssignableFrom(b7)) {
                            a2 = com.a.a.c.l.o.b((com.a.a.c.j) u, (Class<?>) r13);
                        } else if (b7.isAssignableFrom(r13)) {
                            a2 = p.a(u, r13);
                        } else if (b((Class<?>) b7, (Class<?>) r13)) {
                            a2 = u.a();
                        } else {
                            throw a(String.format("Cannot refine serialization content type %s into %s; types not related", new Object[]{u, r13.getName()}));
                        }
                    } catch (IllegalArgumentException e3) {
                        throw a((Throwable) b7, String.format("Internal error: failed to refine value type of %s with concrete-type annotation (value %s), from '%s': %s", r103 == true ? 1 : 0, r13.getName(), bVar.b(), e3.getMessage()));
                    }
                }
                jVar2 = (r103 == true ? 1 : 0).a(a2);
            }
        }
        return jVar2;
    }

    @Override // com.a.a.c.a
    public final String[] e(d dVar) {
        com.a.a.a.z zVar = (com.a.a.a.z) a(dVar, com.a.a.a.z.class);
        if (zVar == null) {
            return null;
        }
        return zVar.a();
    }

    @Override // com.a.a.c.a
    public final Boolean u(b bVar) {
        return J(bVar);
    }

    private final Boolean J(b bVar) {
        com.a.a.a.z zVar = (com.a.a.a.z) a(bVar, com.a.a.a.z.class);
        if (zVar != null && zVar.b()) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final void a(com.a.a.c.b.q<?> qVar, d dVar, List<com.a.a.c.k.e> list) {
        com.a.a.c.a.b bVar = (com.a.a.c.a.b) a(dVar, com.a.a.c.a.b.class);
        if (bVar == null) {
            return;
        }
        boolean c2 = bVar.c();
        com.a.a.c.j jVar = null;
        b.a[] a2 = bVar.a();
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            if (jVar == null) {
                jVar = qVar.b(Object.class);
            }
            com.a.a.c.k.e a3 = a(a2[i], qVar, dVar, jVar);
            if (c2) {
                list.add(i, a3);
            } else {
                list.add(a3);
            }
        }
        b.InterfaceC0006b[] b2 = bVar.b();
        int length2 = b2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            com.a.a.c.k.e a4 = a(b2[i2], qVar, dVar);
            if (c2) {
                list.add(i2, a4);
            } else {
                list.add(a4);
            }
        }
    }

    private com.a.a.c.k.e a(b.a aVar, com.a.a.c.b.q<?> qVar, d dVar, com.a.a.c.j jVar) {
        com.a.a.c.v vVar = aVar.e() ? com.a.a.c.v.f766a : com.a.a.c.v.f767b;
        String a2 = aVar.a();
        com.a.a.c.w a3 = a(aVar.b(), aVar.c());
        com.a.a.c.w wVar = a3;
        if (!a3.c()) {
            wVar = com.a.a.c.w.a(a2);
        }
        return com.a.a.c.k.a.a.a(a2, com.a.a.c.m.aa.a(qVar, new ao(dVar, dVar.d(), a2, jVar), wVar, vVar, aVar.d()), dVar.f(), jVar);
    }

    private com.a.a.c.k.e a(b.InterfaceC0006b interfaceC0006b, com.a.a.c.b.q<?> qVar, d dVar) {
        com.a.a.c.v vVar = interfaceC0006b.e() ? com.a.a.c.v.f766a : com.a.a.c.v.f767b;
        com.a.a.c.w a2 = a(interfaceC0006b.b(), interfaceC0006b.c());
        com.a.a.c.m.aa.a(qVar, new ao(dVar, dVar.d(), a2.b(), qVar.b(interfaceC0006b.f())), a2, vVar, interfaceC0006b.d());
        Class<? extends com.a.a.c.k.s> a3 = interfaceC0006b.a();
        com.a.a.c.k.s n = qVar.m() == null ? null : com.a.a.c.k.a.d.n();
        com.a.a.c.k.s sVar = n;
        if (n == null) {
            sVar = (com.a.a.c.k.s) com.a.a.c.m.i.b(a3, qVar.g());
        }
        return sVar.l();
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.w v(b bVar) {
        boolean z = false;
        com.a.a.a.m mVar = (com.a.a.a.m) a(bVar, com.a.a.a.m.class);
        if (mVar != null) {
            String a2 = mVar.a();
            if (!a2.isEmpty()) {
                return com.a.a.c.w.a(a2);
            }
            z = true;
        }
        com.a.a.a.x xVar = (com.a.a.a.x) a(bVar, com.a.a.a.x.class);
        if (xVar != null) {
            String b2 = xVar.b();
            String str = b2;
            if (b2 != null && str.isEmpty()) {
                str = null;
            }
            return com.a.a.c.w.a(xVar.a(), str);
        }
        if (z || a(bVar, f481a)) {
            return com.a.a.c.w.f770a;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Boolean w(b bVar) {
        com.a.a.a.u uVar = (com.a.a.a.u) a(bVar, com.a.a.a.u.class);
        if (uVar == null) {
            return null;
        }
        return Boolean.valueOf(uVar.a());
    }

    @Override // com.a.a.c.a
    public final Boolean x(b bVar) {
        com.a.a.a.ai aiVar = (com.a.a.a.ai) a(bVar, com.a.a.a.ai.class);
        if (aiVar == null) {
            return null;
        }
        return Boolean.valueOf(aiVar.a());
    }

    @Override // com.a.a.c.a
    public final Boolean y(b bVar) {
        com.a.a.a.d dVar = (com.a.a.a.d) a(bVar, com.a.a.a.d.class);
        if (dVar == null) {
            return null;
        }
        return Boolean.valueOf(dVar.a());
    }

    @Override // com.a.a.c.a
    @Deprecated
    public final boolean b(k kVar) {
        return b(kVar, (Class<? extends Annotation>) com.a.a.a.d.class);
    }

    @Override // com.a.a.c.a
    @Deprecated
    public final boolean a(k kVar) {
        com.a.a.a.ai aiVar = (com.a.a.a.ai) a(kVar, com.a.a.a.ai.class);
        return aiVar != null && aiVar.a();
    }

    @Override // com.a.a.c.a
    public final Object z(b bVar) {
        Class<? extends com.a.a.c.k> a2;
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(bVar, com.a.a.c.a.c.class);
        if (cVar != null && (a2 = cVar.a()) != k.a.class) {
            return a2;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object A(b bVar) {
        Class<? extends com.a.a.c.p> c2;
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(bVar, com.a.a.c.a.c.class);
        if (cVar != null && (c2 = cVar.c()) != p.a.class) {
            return c2;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object B(b bVar) {
        Class<? extends com.a.a.c.k> b2;
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(bVar, com.a.a.c.a.c.class);
        if (cVar != null && (b2 = cVar.b()) != k.a.class) {
            return b2;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Object C(b bVar) {
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(bVar, com.a.a.c.a.c.class);
        if (cVar == null) {
            return null;
        }
        return a(cVar.e(), k.a.class);
    }

    @Override // com.a.a.c.a
    public final Object j(j jVar) {
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(jVar, com.a.a.c.a.c.class);
        if (cVar == null) {
            return null;
        }
        return a(cVar.f(), k.a.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v27, types: [com.a.a.c.j] */
    /* JADX WARN: Type inference failed for: r0v37, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v38, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v44, types: [com.a.a.c.l.g] */
    /* JADX WARN: Type inference failed for: r0v49, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v50, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v53, types: [com.a.a.c.j] */
    @Override // com.a.a.c.a
    public final com.a.a.c.j b(com.a.a.c.b.q<?> qVar, b bVar, com.a.a.c.j jVar) {
        ?? a2;
        ?? a3;
        ?? a4;
        com.a.a.c.j jVar2 = jVar;
        com.a.a.c.l.o p = qVar.p();
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(bVar, com.a.a.c.a.c.class);
        Class<?> b2 = cVar == null ? null : b(cVar.g());
        Class<?> cls = b2;
        if (b2 != null && !jVar2.a(cls) && (a4 = a(jVar2, cls)) == 0) {
            try {
                a4 = p.a(jVar2, cls);
                jVar2 = a4;
            } catch (IllegalArgumentException e) {
                throw a((Throwable) a4, String.format("Failed to narrow type %s with annotation (value %s), from '%s': %s", jVar2, cls.getName(), bVar.b(), e.getMessage()));
            }
        }
        if (jVar2.p()) {
            com.a.a.c.j t = jVar2.t();
            Class<?> b3 = cVar == null ? null : b(cVar.h());
            Class<?> cls2 = b3;
            if (b3 != null && (a3 = a(t, cls2)) == 0) {
                try {
                    a3 = ((com.a.a.c.l.g) jVar2).e(p.a(t, cls2));
                    jVar2 = a3;
                } catch (IllegalArgumentException e2) {
                    throw a((Throwable) a3, String.format("Failed to narrow key type of %s with concrete-type annotation (value %s), from '%s': %s", jVar2, cls2.getName(), bVar.b(), e2.getMessage()));
                }
            }
        }
        com.a.a.c.j u = jVar2.u();
        if (u != null) {
            Class<?> b4 = cVar == null ? null : b(cVar.i());
            Class<?> cls3 = b4;
            if (b4 != null && (a2 = a(u, cls3)) == 0) {
                try {
                    a2 = jVar2.a(p.a(u, cls3));
                    jVar2 = a2;
                } catch (IllegalArgumentException e3) {
                    throw a((Throwable) a2, String.format("Failed to narrow value type of %s with concrete-type annotation (value %s), from '%s': %s", jVar2, cls3.getName(), bVar.b(), e3.getMessage()));
                }
            }
        }
        return jVar2;
    }

    @Override // com.a.a.c.a
    public final Object f(d dVar) {
        com.a.a.c.a.i iVar = (com.a.a.c.a.i) a(dVar, com.a.a.c.a.i.class);
        if (iVar == null) {
            return null;
        }
        return iVar.a();
    }

    @Override // com.a.a.c.a
    public final Class<?> g(d dVar) {
        com.a.a.c.a.c cVar = (com.a.a.c.a.c) a(dVar, com.a.a.c.a.c.class);
        if (cVar == null) {
            return null;
        }
        return b(cVar.d());
    }

    @Override // com.a.a.c.a
    public final e.a h(d dVar) {
        com.a.a.c.a.e eVar = (com.a.a.c.a.e) a(dVar, com.a.a.c.a.e.class);
        if (eVar == null) {
            return null;
        }
        return new e.a(eVar);
    }

    @Override // com.a.a.c.a
    public final com.a.a.c.w D(b bVar) {
        boolean z = false;
        com.a.a.a.ac acVar = (com.a.a.a.ac) a(bVar, com.a.a.a.ac.class);
        if (acVar != null) {
            String a2 = acVar.a();
            if (a2.isEmpty()) {
                z = true;
            } else {
                return com.a.a.c.w.a(a2);
            }
        }
        com.a.a.a.x xVar = (com.a.a.a.x) a(bVar, com.a.a.a.x.class);
        if (xVar != null) {
            String b2 = xVar.b();
            String str = b2;
            if (b2 != null && str.isEmpty()) {
                str = null;
            }
            return com.a.a.c.w.a(xVar.a(), str);
        }
        if (z || a(bVar, f482b)) {
            return com.a.a.c.w.f770a;
        }
        return null;
    }

    @Override // com.a.a.c.a
    public final Boolean E(b bVar) {
        com.a.a.a.e eVar = (com.a.a.a.e) a(bVar, com.a.a.a.e.class);
        if (eVar == null) {
            return null;
        }
        return Boolean.valueOf(eVar.a());
    }

    @Override // com.a.a.c.a
    public final ac.a F(b bVar) {
        return ac.a.a((com.a.a.a.ac) a(bVar, com.a.a.a.ac.class));
    }

    @Override // com.a.a.c.a
    public final Boolean G(b bVar) {
        com.a.a.a.w wVar = (com.a.a.a.w) a(bVar, com.a.a.a.w.class);
        if (wVar == null) {
            return null;
        }
        return wVar.a().a();
    }

    @Override // com.a.a.c.a
    @Deprecated
    public final boolean H(b bVar) {
        Boolean b2;
        com.a.a.a.i iVar = (com.a.a.a.i) a(bVar, com.a.a.a.i.class);
        if (iVar != null) {
            return iVar.a() != i.a.DISABLED;
        }
        if (this.e && (bVar instanceof f) && c != null && (b2 = c.b(bVar)) != null) {
            return b2.booleanValue();
        }
        return false;
    }

    @Override // com.a.a.c.a
    @Deprecated
    public final i.a I(b bVar) {
        com.a.a.a.i iVar = (com.a.a.a.i) a(bVar, com.a.a.a.i.class);
        if (iVar == null) {
            return null;
        }
        return iVar.a();
    }

    @Override // com.a.a.c.a
    public final i.a a(com.a.a.c.b.q<?> qVar, b bVar) {
        Boolean b2;
        com.a.a.a.i iVar = (com.a.a.a.i) a(bVar, com.a.a.a.i.class);
        if (iVar != null) {
            return iVar.a();
        }
        if (this.e && qVar.a(com.a.a.c.q.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES) && (bVar instanceof f) && c != null && (b2 = c.b(bVar)) != null && b2.booleanValue()) {
            return i.a.PROPERTIES;
        }
        return null;
    }

    private boolean K(b bVar) {
        Boolean a2;
        com.a.a.a.p pVar = (com.a.a.a.p) a(bVar, com.a.a.a.p.class);
        if (pVar != null) {
            return pVar.a();
        }
        if (c != null && (a2 = c.a(bVar)) != null) {
            return a2.booleanValue();
        }
        return false;
    }

    private static Class<?> b(Class<?> cls) {
        if (cls == null || com.a.a.c.m.i.e(cls)) {
            return null;
        }
        return cls;
    }

    private Class<?> a(Class<?> cls, Class<?> cls2) {
        Class<?> b2 = b(cls);
        if (b2 == null || b2 == cls2) {
            return null;
        }
        return b2;
    }

    private static com.a.a.c.w a(String str, String str2) {
        if (str.isEmpty()) {
            return com.a.a.c.w.f770a;
        }
        if (str2 == null || str2.isEmpty()) {
            return com.a.a.c.w.a(str);
        }
        return com.a.a.c.w.a(str, str2);
    }

    private static com.a.a.c.w L(b bVar) {
        com.a.a.c.w a2;
        if (bVar instanceof n) {
            n nVar = (n) bVar;
            if (nVar.e() != null && c != null && (a2 = c.a(nVar)) != null) {
                return a2;
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v19, types: [com.a.a.c.i.h] */
    private com.a.a.c.i.h<?> c(com.a.a.c.b.q<?> qVar, b bVar, com.a.a.c.j jVar) {
        com.a.a.c.i.h<?> d;
        com.a.a.a.af afVar = (com.a.a.a.af) a(bVar, com.a.a.a.af.class);
        com.a.a.c.a.h hVar = (com.a.a.c.a.h) a(bVar, com.a.a.c.a.h.class);
        if (hVar != null) {
            if (afVar == null) {
                return null;
            }
            d = qVar.a(bVar, hVar.a());
        } else {
            if (afVar == null) {
                return null;
            }
            if (afVar.a() == af.b.NONE) {
                return e();
            }
            d = d();
        }
        com.a.a.c.a.g gVar = (com.a.a.c.a.g) a(bVar, com.a.a.c.a.g.class);
        ?? a2 = d.a(afVar.a(), gVar == null ? null : qVar.b(bVar, gVar.a()));
        af.a b2 = afVar.b();
        af.a aVar = b2;
        if (b2 == af.a.EXTERNAL_PROPERTY && (bVar instanceof d)) {
            aVar = af.a.PROPERTY;
        }
        com.a.a.c.i.h a3 = a2.a(aVar).a(afVar.c());
        Class<?> d2 = afVar.d();
        if (d2 != af.c.class && !d2.isAnnotation()) {
            a3 = a3.a(d2);
        }
        return a3.a(afVar.e());
    }

    private static com.a.a.c.i.a.o d() {
        return new com.a.a.c.i.a.o();
    }

    private static com.a.a.c.i.a.o e() {
        return com.a.a.c.i.a.o.b();
    }

    private static boolean b(Class<?> cls, Class<?> cls2) {
        return cls.isPrimitive() ? cls == com.a.a.c.m.i.j(cls2) : cls2.isPrimitive() && cls2 == com.a.a.c.m.i.j(cls);
    }

    private static boolean a(com.a.a.c.j jVar, Class<?> cls) {
        if (jVar.l()) {
            return jVar.a(com.a.a.c.m.i.j(cls));
        }
        return cls.isPrimitive() && cls == com.a.a.c.m.i.j(jVar.b());
    }

    private static com.a.a.c.l a(String str) {
        return new com.a.a.c.l(null, str);
    }

    private static com.a.a.c.l a(Throwable th, String str) {
        return new com.a.a.c.l((Closeable) null, str, th);
    }
}
