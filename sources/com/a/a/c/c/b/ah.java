package com.a.a.c.c.b;

import com.a.a.a.i;
import com.a.a.c.c.b.ag;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ah.class */
public final class ah implements com.a.a.c.c.r, Serializable {
    public static com.a.a.c.p a(com.a.a.c.m.l lVar) {
        return new ag.b(lVar, null);
    }

    public static com.a.a.c.p a(com.a.a.c.m.l lVar, com.a.a.c.f.k kVar) {
        return new ag.b(lVar, kVar);
    }

    public static com.a.a.c.p a(com.a.a.c.j jVar, com.a.a.c.k<?> kVar) {
        return new ag.a(jVar.b(), kVar);
    }

    public static com.a.a.c.p a(com.a.a.c.f fVar, com.a.a.c.j jVar) {
        com.a.a.c.b b2 = fVar.b(jVar);
        com.a.a.c.f.c<com.a.a.c.f.f, i.a> a2 = a(b2);
        if (a2 != null && a2.f448b != null) {
            return a(fVar, a2.f447a);
        }
        List<com.a.a.c.f.c<com.a.a.c.f.k, i.a>> n = b2.n();
        n.removeIf(cVar -> {
            return (((com.a.a.c.f.k) cVar.f447a).f() == 1 && ((com.a.a.c.f.k) cVar.f447a).a(0) == String.class && cVar.f448b != i.a.PROPERTIES) ? false : true;
        });
        com.a.a.c.f.k a3 = a(n);
        if (a3 != null) {
            return a(fVar, a3);
        }
        if (a2 != null) {
            return a(fVar, a2.f447a);
        }
        if (!n.isEmpty()) {
            return a(fVar, n.get(0).f447a);
        }
        return null;
    }

    private static com.a.a.c.p a(com.a.a.c.f fVar, com.a.a.c.f.j jVar) {
        if (jVar instanceof com.a.a.c.f.f) {
            Constructor<?> a2 = ((com.a.a.c.f.f) jVar).a();
            if (fVar.g()) {
                com.a.a.c.m.i.a(a2, fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            return new ag.c(a2);
        }
        Method a3 = ((com.a.a.c.f.k) jVar).a();
        if (fVar.g()) {
            com.a.a.c.m.i.a(a3, fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new ag.d(a3);
    }

    private static com.a.a.c.f.c<com.a.a.c.f.f, i.a> a(com.a.a.c.b bVar) {
        for (com.a.a.c.f.c<com.a.a.c.f.f, i.a> cVar : bVar.l()) {
            com.a.a.c.f.f fVar = cVar.f447a;
            if (fVar.f() == 1 && String.class == fVar.a(0)) {
                return cVar;
            }
        }
        return null;
    }

    private static com.a.a.c.f.k a(List<com.a.a.c.f.c<com.a.a.c.f.k, i.a>> list) {
        com.a.a.c.f.k kVar = null;
        for (com.a.a.c.f.c<com.a.a.c.f.k, i.a> cVar : list) {
            if (cVar.f448b != null) {
                if (kVar == null) {
                    kVar = cVar.f447a;
                } else {
                    throw new IllegalArgumentException("Multiple suitable annotated Creator factory methods to be used as the Key deserializer for type " + com.a.a.c.m.i.g(cVar.f447a.h()));
                }
            }
        }
        return kVar;
    }

    @Override // com.a.a.c.c.r
    public final com.a.a.c.p a(com.a.a.c.j jVar) {
        Class<?> b2 = jVar.b();
        Class<?> cls = b2;
        if (b2.isPrimitive()) {
            cls = com.a.a.c.m.i.i(cls);
        }
        return ag.a(cls);
    }
}
