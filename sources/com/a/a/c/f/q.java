package com.a.a.c.f;

import com.a.a.a.i;
import com.a.a.a.l;
import com.a.a.a.s;
import com.a.a.c.a;
import com.a.a.c.a.e;
import com.a.a.c.m.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/f/q.class */
public final class q extends com.a.a.c.b {

    /* renamed from: a, reason: collision with root package name */
    private static final Class<?>[] f468a = new Class[0];

    /* renamed from: b, reason: collision with root package name */
    private ae f469b;
    private com.a.a.c.b.q<?> c;
    private com.a.a.c.a d;
    private d e;
    private Class<?>[] f;
    private boolean g;
    private List<s> h;
    private ad i;

    private q(ae aeVar, com.a.a.c.j jVar, d dVar) {
        super(jVar);
        this.f469b = aeVar;
        this.c = aeVar.a();
        if (this.c == null) {
            this.d = null;
        } else {
            this.d = this.c.j();
        }
        this.e = dVar;
    }

    private q(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, d dVar, List<s> list) {
        super(jVar);
        this.f469b = null;
        this.c = qVar;
        if (this.c == null) {
            this.d = null;
        } else {
            this.d = this.c.j();
        }
        this.e = dVar;
        this.h = list;
    }

    private q(ae aeVar) {
        this(aeVar, aeVar.b(), aeVar.c());
        this.i = aeVar.m();
    }

    public static q a(ae aeVar) {
        return new q(aeVar);
    }

    public static q b(ae aeVar) {
        return new q(aeVar);
    }

    public static q a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, d dVar) {
        return new q(qVar, jVar, dVar, Collections.emptyList());
    }

    private List<s> z() {
        if (this.h == null) {
            this.h = this.f469b.d();
        }
        return this.h;
    }

    public final boolean a(String str) {
        Iterator<s> it = z().iterator();
        while (it.hasNext()) {
            if (it.next().a().equals(str)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public final boolean a(s sVar) {
        if (a(sVar.b())) {
            return false;
        }
        z().add(sVar);
        return true;
    }

    public final boolean a(com.a.a.c.w wVar) {
        return b(wVar) != null;
    }

    private s b(com.a.a.c.w wVar) {
        for (s sVar : z()) {
            if (sVar.a(wVar)) {
                return sVar;
            }
        }
        return null;
    }

    @Override // com.a.a.c.b
    public final d d() {
        return this.e;
    }

    @Override // com.a.a.c.b
    public final ad e() {
        return this.i;
    }

    @Override // com.a.a.c.b
    public final List<s> h() {
        return z();
    }

    @Override // com.a.a.c.b
    public final j p() {
        if (this.f469b == null) {
            return null;
        }
        return this.f469b.f();
    }

    @Override // com.a.a.c.b
    public final j q() {
        if (this.f469b == null) {
            return null;
        }
        return this.f469b.g();
    }

    @Override // com.a.a.c.b
    public final Set<String> i() {
        Set<String> l = this.f469b == null ? null : this.f469b.l();
        Set<String> set = l;
        if (l == null) {
            return Collections.emptySet();
        }
        return set;
    }

    @Override // com.a.a.c.b
    public final boolean f() {
        return this.e.g();
    }

    @Override // com.a.a.c.b
    public final com.a.a.c.m.b g() {
        return this.e.f();
    }

    @Override // com.a.a.c.b
    public final f o() {
        return this.e.h();
    }

    @Override // com.a.a.c.b
    public final j s() {
        if (this.f469b != null) {
            k k = this.f469b.k();
            if (k != null) {
                Class<?> a2 = k.a(0);
                if (a2 != String.class && a2 != Object.class) {
                    throw new IllegalArgumentException(String.format("Invalid 'any-setter' annotation on method '%s()': first argument not of type String or Object, but %s", k.b(), a2.getName()));
                }
                return k;
            }
            j j = this.f469b.j();
            if (j != null) {
                Class<?> d = j.d();
                if (!Map.class.isAssignableFrom(d) && !com.a.a.c.m.class.isAssignableFrom(d)) {
                    throw new IllegalArgumentException(String.format("Invalid 'any-setter' annotation on field '%s': type is not instance of `java.util.Map` or `JsonNode`", j.b()));
                }
                return j;
            }
            return null;
        }
        return null;
    }

    @Override // com.a.a.c.b
    public final Map<Object, j> v() {
        if (this.f469b != null) {
            return this.f469b.e();
        }
        return Collections.emptyMap();
    }

    @Override // com.a.a.c.b
    public final List<f> k() {
        return this.e.i();
    }

    @Override // com.a.a.c.b
    public final List<c<f, i.a>> l() {
        List<f> i = this.e.i();
        if (i.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (f fVar : i) {
            i.a a2 = this.d.a(this.c, fVar);
            if (a2 != i.a.DISABLED) {
                arrayList.add(c.a(fVar, a2));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5 */
    @Override // com.a.a.c.b
    public final Object a(boolean z) {
        Throwable th;
        f h = this.e.h();
        if (h == null) {
            return null;
        }
        boolean z2 = z;
        Object obj = z2;
        if (z2) {
            f fVar = h;
            fVar.a(this.c.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            obj = fVar;
        }
        try {
            obj = h.g();
            return obj;
        } catch (Exception e) {
            Throwable th2 = obj;
            while (true) {
                th = th2;
                if (th.getCause() == null) {
                    break;
                }
                th2 = th.getCause();
            }
            com.a.a.c.m.i.a(th);
            com.a.a.c.m.i.b(th);
            throw new IllegalArgumentException("Failed to instantiate bean of type " + this.e.a().getName() + ": (" + th.getClass().getName() + ") " + com.a.a.c.m.i.g(th), th);
        }
    }

    @Override // com.a.a.c.b
    public final k a(String str, Class<?>[] clsArr) {
        return this.e.a(str, clsArr);
    }

    @Override // com.a.a.c.b
    public final l.d a(l.d dVar) {
        l.d h;
        if (this.d != null && (h = this.d.h((b) this.e)) != null) {
            if (dVar == null) {
                dVar = h;
            } else {
                dVar = dVar.a(h);
            }
        }
        l.d f = this.c.f(this.e.d());
        if (f != null) {
            if (dVar != null) {
                dVar = dVar.a(f);
            } else {
                dVar = f;
            }
        }
        return dVar;
    }

    @Override // com.a.a.c.b
    public final Class<?>[] y() {
        if (!this.g) {
            this.g = true;
            Class<?>[] g = this.d == null ? null : this.d.g((b) this.e);
            Class<?>[] clsArr = g;
            if (g == null && !this.c.a(com.a.a.c.q.DEFAULT_VIEW_INCLUSION)) {
                clsArr = f468a;
            }
            this.f = clsArr;
        }
        return this.f;
    }

    @Override // com.a.a.c.b
    public final com.a.a.c.m.k<Object, Object> t() {
        if (this.d == null) {
            return null;
        }
        return a(this.d.s(this.e));
    }

    @Override // com.a.a.c.b
    public final s.b a(s.b bVar) {
        s.b t;
        if (this.d == null || (t = this.d.t(this.e)) == null) {
            return bVar;
        }
        return bVar == null ? t : bVar.a(t);
    }

    @Override // com.a.a.c.b
    public final j r() {
        if (this.f469b != null) {
            j i = this.f469b.i();
            if (i != null) {
                if (!Map.class.isAssignableFrom(i.d())) {
                    throw new IllegalArgumentException(String.format("Invalid 'any-getter' annotation on method %s(): return type is not instance of java.util.Map", i.b()));
                }
                return i;
            }
            j h = this.f469b.h();
            if (h != null) {
                if (!Map.class.isAssignableFrom(h.d())) {
                    throw new IllegalArgumentException(String.format("Invalid 'any-getter' annotation on field '%s': type is not instance of java.util.Map", h.b()));
                }
                return h;
            }
            return null;
        }
        return null;
    }

    @Override // com.a.a.c.b
    public final List<s> j() {
        ArrayList arrayList = null;
        HashSet hashSet = null;
        for (s sVar : z()) {
            a.C0004a x = sVar.x();
            if (x != null && x.c()) {
                String a2 = x.a();
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    HashSet hashSet2 = new HashSet();
                    hashSet = hashSet2;
                    hashSet2.add(a2);
                } else if (!hashSet.add(a2)) {
                    throw new IllegalArgumentException("Multiple back-reference properties with name " + com.a.a.c.m.i.b(a2));
                }
                arrayList.add(sVar);
            }
        }
        return arrayList;
    }

    @Override // com.a.a.c.b
    public final List<k> m() {
        List<k> j = this.e.j();
        if (j.isEmpty()) {
            return j;
        }
        ArrayList arrayList = null;
        for (k kVar : j) {
            if (a(kVar)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(kVar);
            }
        }
        if (arrayList == null) {
            return Collections.emptyList();
        }
        return arrayList;
    }

    @Override // com.a.a.c.b
    public final List<c<k, i.a>> n() {
        List<k> j = this.e.j();
        if (j.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = null;
        Iterator<k> it = j.iterator();
        while (it.hasNext()) {
            c<k, i.a> b2 = b(it.next());
            if (b2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(b2);
            }
        }
        if (arrayList == null) {
            return Collections.emptyList();
        }
        return arrayList;
    }

    private boolean a(k kVar) {
        if (!b().isAssignableFrom(kVar.m())) {
            return false;
        }
        i.a a2 = this.d.a(this.c, kVar);
        if (a2 != null && a2 != i.a.DISABLED) {
            return true;
        }
        String b2 = kVar.b();
        if ("valueOf".equals(b2) && kVar.f() == 1) {
            return true;
        }
        if ("fromString".equals(b2) && kVar.f() == 1) {
            Class<?> a3 = kVar.a(0);
            if (a3 == String.class || CharSequence.class.isAssignableFrom(a3)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private c<k, i.a> b(k kVar) {
        if (!b().isAssignableFrom(kVar.m())) {
            return null;
        }
        i.a a2 = this.d.a(this.c, kVar);
        if (a2 != null) {
            if (a2 == i.a.DISABLED) {
                return null;
            }
            return c.a(kVar, a2);
        }
        String b2 = kVar.b();
        if ("valueOf".equals(b2) && kVar.f() == 1) {
            return c.a(kVar, a2);
        }
        if ("fromString".equals(b2) && kVar.f() == 1) {
            Class<?> a3 = kVar.a(0);
            if (a3 == String.class || CharSequence.class.isAssignableFrom(a3)) {
                return c.a(kVar, a2);
            }
            return null;
        }
        return null;
    }

    @Override // com.a.a.c.b
    public final Class<?> w() {
        if (this.d == null) {
            return null;
        }
        return this.d.g(this.e);
    }

    @Override // com.a.a.c.b
    public final e.a x() {
        if (this.d == null) {
            return null;
        }
        return this.d.h(this.e);
    }

    @Override // com.a.a.c.b
    public final com.a.a.c.m.k<Object, Object> u() {
        if (this.d == null) {
            return null;
        }
        return a(this.d.C(this.e));
    }

    private com.a.a.c.m.k<Object, Object> a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof com.a.a.c.m.k) {
            return (com.a.a.c.m.k) obj;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        Class cls = (Class) obj;
        if (cls != k.a.class && !com.a.a.c.m.i.e((Class<?>) cls)) {
            if (!com.a.a.c.m.k.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<Converter>");
            }
            com.a.a.c.m.k<?, ?> m = this.c.m() == null ? null : com.a.a.c.k.a.d.m();
            com.a.a.c.m.k<?, ?> kVar = m;
            if (m == null) {
                kVar = (com.a.a.c.m.k) com.a.a.c.m.i.b(cls, this.c.g());
            }
            return kVar;
        }
        return null;
    }
}
