package com.a.a.c.c;

import com.a.a.a.l;
import com.a.a.c.c.b.ad;
import com.a.a.c.k;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/a/a/c/c/p.class */
public final class p implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.m.o<com.a.a.c.j, com.a.a.c.k<Object>> f409a;

    /* renamed from: b, reason: collision with root package name */
    private HashMap<com.a.a.c.j, com.a.a.c.k<Object>> f410b;

    public p() {
        this(2000);
    }

    private p(int i) {
        this.f410b = new HashMap<>(8);
        this.f409a = new com.a.a.c.m.o<>(Math.min(64, 500), 2000);
    }

    public final com.a.a.c.k<Object> a(com.a.a.c.g gVar, q qVar, com.a.a.c.j jVar) {
        com.a.a.c.k<Object> a2 = a(jVar);
        com.a.a.c.k<Object> kVar = a2;
        if (a2 == null) {
            com.a.a.c.k<Object> c = c(gVar, qVar, jVar);
            kVar = c;
            if (c == null) {
                kVar = a(gVar, jVar);
            }
        }
        return kVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final com.a.a.c.p b(com.a.a.c.g gVar, q qVar, com.a.a.c.j jVar) {
        com.a.a.c.p a2 = qVar.a(gVar, jVar);
        if (a2 == 0) {
            return b(gVar, jVar);
        }
        if (a2 instanceof t) {
            ((t) a2).d(gVar);
        }
        return a2;
    }

    private com.a.a.c.k<Object> a(com.a.a.c.j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Null JavaType passed");
        }
        if (b(jVar)) {
            return null;
        }
        return this.f409a.a(jVar);
    }

    private com.a.a.c.k<Object> c(com.a.a.c.g gVar, q qVar, com.a.a.c.j jVar) {
        com.a.a.c.k<Object> kVar;
        synchronized (this.f410b) {
            com.a.a.c.k<Object> a2 = a(jVar);
            if (a2 != null) {
                return a2;
            }
            int size = this.f410b.size();
            if (size > 0 && (kVar = this.f410b.get(jVar)) != null) {
                return kVar;
            }
            try {
                com.a.a.c.k<Object> d = d(gVar, qVar, jVar);
                if (size == 0 && this.f410b.size() > 0) {
                    this.f410b.clear();
                }
                return d;
            } catch (Throwable th) {
                if (size == 0 && this.f410b.size() > 0) {
                    this.f410b.clear();
                }
                throw th;
            }
        }
    }

    private com.a.a.c.k<Object> d(com.a.a.c.g gVar, q qVar, com.a.a.c.j jVar) {
        com.a.a.c.k<Object> kVar;
        try {
            kVar = e(gVar, qVar, jVar);
        } catch (IllegalArgumentException e) {
            gVar.a(jVar, com.a.a.c.m.i.g(e));
            kVar = null;
        }
        if (kVar == null) {
            return null;
        }
        boolean z = !b(jVar) && kVar.c();
        if (kVar instanceof t) {
            this.f410b.put(jVar, kVar);
            ((t) kVar).d(gVar);
            this.f410b.remove(jVar);
        }
        if (z) {
            this.f409a.a(jVar, kVar);
        }
        return kVar;
    }

    private com.a.a.c.k<Object> e(com.a.a.c.g gVar, q qVar, com.a.a.c.j jVar) {
        com.a.a.c.f a2 = gVar.a();
        if (jVar.d() || jVar.p() || jVar.o()) {
            jVar = qVar.a(a2, jVar);
        }
        com.a.a.c.b a3 = a2.a(jVar);
        com.a.a.c.k<Object> a4 = a(gVar, a3.d());
        if (a4 != null) {
            return a4;
        }
        com.a.a.c.j a5 = a(gVar, a3.d(), jVar);
        if (a5 != jVar) {
            jVar = a5;
            a3 = a2.a(a5);
        }
        Class<?> w = a3.w();
        if (w != null) {
            return qVar.a(gVar, jVar, a3, w);
        }
        com.a.a.c.m.k<Object, Object> u = a3.u();
        if (u == null) {
            return a(gVar, qVar, jVar, a3);
        }
        gVar.b();
        com.a.a.c.j a6 = u.a();
        if (!a6.a(jVar.b())) {
            a3 = a2.a(a6);
        }
        return new ad(u, a6, a(gVar, qVar, a6, a3));
    }

    private static com.a.a.c.k<?> a(com.a.a.c.g gVar, q qVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        com.a.a.c.f a2 = gVar.a();
        if (jVar.h()) {
            return qVar.a(gVar, jVar, bVar);
        }
        if (jVar.n()) {
            if (jVar.g()) {
                return qVar.a(gVar, (com.a.a.c.l.a) jVar, bVar);
            }
            if (jVar.p() && bVar.a((l.d) null).c() != l.c.OBJECT) {
                com.a.a.c.l.g gVar2 = (com.a.a.c.l.g) jVar;
                if (gVar2 instanceof com.a.a.c.l.h) {
                    return qVar.a(gVar, (com.a.a.c.l.h) gVar2, bVar);
                }
                return qVar.a(gVar, gVar2, bVar);
            }
            if (jVar.o() && bVar.a((l.d) null).c() != l.c.OBJECT) {
                com.a.a.c.l.d dVar = (com.a.a.c.l.d) jVar;
                if (dVar instanceof com.a.a.c.l.e) {
                    return qVar.a(gVar, (com.a.a.c.l.e) dVar, bVar);
                }
                return qVar.a(gVar, dVar, bVar);
            }
        }
        if (jVar.F()) {
            return qVar.a(gVar, (com.a.a.c.l.j) jVar, bVar);
        }
        if (com.a.a.c.m.class.isAssignableFrom(jVar.b())) {
            return qVar.a(a2, jVar, bVar);
        }
        return qVar.c(gVar, jVar, bVar);
    }

    private com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.f.b bVar) {
        Object z = gVar.f().z(bVar);
        if (z == null) {
            return null;
        }
        return a(gVar, bVar, gVar.b(bVar, z));
    }

    private com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.f.b bVar, com.a.a.c.k<Object> kVar) {
        com.a.a.c.m.k<Object, Object> b2 = b(gVar, bVar);
        if (b2 != null) {
            gVar.b();
            return new ad(b2, b2.a(), kVar);
        }
        return kVar;
    }

    private static com.a.a.c.m.k<Object, Object> b(com.a.a.c.g gVar, com.a.a.c.f.b bVar) {
        Object C = gVar.f().C(bVar);
        if (C == null) {
            return null;
        }
        return gVar.a(bVar, C);
    }

    private com.a.a.c.j a(com.a.a.c.g gVar, com.a.a.c.f.b bVar, com.a.a.c.j jVar) {
        Object B;
        com.a.a.c.j t;
        Object A;
        com.a.a.c.p c;
        com.a.a.c.a f = gVar.f();
        if (f == null) {
            return jVar;
        }
        if (jVar.p() && (t = jVar.t()) != null && t.A() == null && (A = f.A(bVar)) != null && (c = gVar.c(bVar, A)) != null) {
            jVar = ((com.a.a.c.l.g) jVar).i(c);
        }
        com.a.a.c.j u = jVar.u();
        if (u != null && u.A() == null && (B = f.B(bVar)) != null) {
            com.a.a.c.k<Object> kVar = null;
            if (B instanceof com.a.a.c.k) {
                kVar = (com.a.a.c.k) B;
            } else {
                Class<?> a2 = a(B, "findContentDeserializer", (Class<?>) k.a.class);
                if (a2 != null) {
                    kVar = gVar.b(bVar, a2);
                }
            }
            if (kVar != null) {
                jVar = jVar.d(kVar);
            }
        }
        return f.b(gVar.a(), bVar, jVar);
    }

    private static boolean b(com.a.a.c.j jVar) {
        if (jVar.n()) {
            com.a.a.c.j u = jVar.u();
            if (u != null && (u.A() != null || u.B() != null)) {
                return true;
            }
            if (jVar.p() && jVar.t().A() != null) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static Class<?> a(Object obj, String str, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector." + str + "() returned value of type " + obj.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
        }
        Class<?> cls2 = (Class) obj;
        if (cls2 == cls || com.a.a.c.m.i.e(cls2)) {
            return null;
        }
        return cls2;
    }

    private static com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.j jVar) {
        if (!com.a.a.c.m.i.d(jVar.b())) {
            return (com.a.a.c.k) gVar.a(jVar, "Cannot find a Value deserializer for abstract type " + jVar);
        }
        return (com.a.a.c.k) gVar.a(jVar, "Cannot find a Value deserializer for type " + jVar);
    }

    private static com.a.a.c.p b(com.a.a.c.g gVar, com.a.a.c.j jVar) {
        return (com.a.a.c.p) gVar.a(jVar, "Cannot find a (Map) Key deserializer for type " + jVar);
    }
}
