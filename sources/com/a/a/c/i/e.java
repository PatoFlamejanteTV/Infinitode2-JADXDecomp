package com.a.a.c.i;

import com.a.a.a.af;
import com.a.a.b.l;
import com.a.a.b.o;

/* loaded from: infinitode-2.jar:com/a/a/c/i/e.class */
public abstract class e {
    public abstract e a(com.a.a.c.c cVar);

    public abstract af.a a();

    public abstract String b();

    public abstract g c();

    public abstract Class<?> d();

    public abstract Object a(l lVar, com.a.a.c.g gVar);

    public abstract Object b(l lVar, com.a.a.c.g gVar);

    public abstract Object c(l lVar, com.a.a.c.g gVar);

    public abstract Object d(l lVar, com.a.a.c.g gVar);

    public boolean e() {
        return d() != null;
    }

    public static Object a(l lVar, com.a.a.c.g gVar, com.a.a.c.j jVar) {
        return a(lVar, jVar.b());
    }

    private static Object a(l lVar, Class<?> cls) {
        o k = lVar.k();
        if (k == null) {
            return null;
        }
        switch (f.f520a[k.ordinal()]) {
            case 1:
                if (cls.isAssignableFrom(String.class)) {
                    return lVar.w();
                }
                return null;
            case 2:
                if (cls.isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(lVar.G());
                }
                return null;
            case 3:
                if (cls.isAssignableFrom(Double.class)) {
                    return Double.valueOf(lVar.K());
                }
                return null;
            case 4:
                if (cls.isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                return null;
            case 5:
                if (cls.isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }
}
