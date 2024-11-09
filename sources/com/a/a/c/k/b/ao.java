package com.a.a.c.k.b;

import com.a.a.a.l;
import com.a.a.a.s;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ao.class */
public abstract class ao<T> extends com.a.a.c.o<T> implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f610a = new Object();
    protected final Class<T> h;

    @Override // com.a.a.c.o
    public abstract void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public ao(Class<T> cls) {
        this.h = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ao(com.a.a.c.j jVar) {
        this.h = (Class<T>) jVar.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public ao(Class<?> cls, byte b2) {
        this.h = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ao(ao<?> aoVar) {
        this.h = (Class<T>) aoVar.h;
    }

    @Override // com.a.a.c.o
    public final Class<T> a() {
        return this.h;
    }

    public static void a(com.a.a.c.aa aaVar, Throwable th, Object obj, String str) {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        com.a.a.c.m.i.a(th);
        boolean z = aaVar == null || aaVar.a(com.a.a.c.z.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof com.a.a.b.d)) {
                throw ((IOException) th);
            }
        } else if (!z) {
            com.a.a.c.m.i.b(th);
        }
        throw com.a.a.c.l.a(th, obj, str);
    }

    public static void a(com.a.a.c.aa aaVar, Throwable th, Object obj, int i) {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        com.a.a.c.m.i.a(th);
        boolean z = aaVar == null || aaVar.a(com.a.a.c.z.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof com.a.a.b.d)) {
                throw ((IOException) th);
            }
        } else if (!z) {
            com.a.a.c.m.i.b(th);
        }
        throw com.a.a.c.l.a(th, obj, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar, com.a.a.c.o<?> oVar) {
        Map map = (Map) aaVar.a(f610a);
        Map map2 = map;
        if (map != null) {
            if (map2.get(cVar) != null) {
                return oVar;
            }
        } else {
            map2 = new IdentityHashMap();
            aaVar.a(f610a, map2);
        }
        map2.put(cVar, Boolean.TRUE);
        try {
            com.a.a.c.o<?> b2 = b(aaVar, cVar, oVar);
            if (b2 != null) {
                com.a.a.c.o<?> b3 = aaVar.b(b2, cVar);
                map2.remove(cVar);
                return b3;
            }
            return oVar;
        } finally {
            map2.remove(cVar);
        }
    }

    @Deprecated
    private static com.a.a.c.o<?> b(com.a.a.c.aa aaVar, com.a.a.c.c cVar, com.a.a.c.o<?> oVar) {
        com.a.a.c.f.j e;
        Object i;
        com.a.a.c.a d = aaVar.d();
        if (a(d, cVar) && (e = cVar.e()) != null && (i = d.i(e)) != null) {
            com.a.a.c.m.k<Object, Object> a2 = aaVar.a((com.a.a.c.f.b) cVar.e(), i);
            aaVar.b();
            com.a.a.c.j b2 = a2.b();
            if (oVar == null && !b2.q()) {
                oVar = aaVar.a(b2);
            }
            return new aj(a2, b2, oVar);
        }
        return oVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.k.o a(com.a.a.c.aa aaVar, Object obj, Object obj2) {
        com.a.a.c.k.a.d i = aaVar.i();
        if (i == null) {
            return (com.a.a.c.k.o) aaVar.a((Class<?>) a(), "Cannot resolve PropertyFilter with id '" + obj + "'; no FilterProvider configured");
        }
        return i.a(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static l.d a(com.a.a.c.aa aaVar, com.a.a.c.c cVar, Class<?> cls) {
        if (cVar != null) {
            return cVar.a(aaVar.a(), cls);
        }
        return aaVar.a(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Boolean a(com.a.a.c.aa aaVar, com.a.a.c.c cVar, Class<?> cls, l.a aVar) {
        l.d a2 = a(aaVar, cVar, cls);
        if (a2 != null) {
            return a2.a(aVar);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static s.b b(com.a.a.c.aa aaVar, com.a.a.c.c cVar, Class<?> cls) {
        if (cVar != null) {
            return cVar.b(aaVar.a(), cls);
        }
        return aaVar.b(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.o<?> b(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        Object p;
        if (cVar != null) {
            com.a.a.c.f.j e = cVar.e();
            com.a.a.c.a d = aaVar.d();
            if (e != null && (p = d.p(e)) != null) {
                return aaVar.b(e, p);
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(com.a.a.c.o<?> oVar) {
        return com.a.a.c.m.i.e(oVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean a(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean a(Collection<?> collection) {
        return (collection == null || collection.isEmpty()) ? false : true;
    }
}
