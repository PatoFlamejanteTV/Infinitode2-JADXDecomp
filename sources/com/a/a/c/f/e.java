package com.a.a.c.f;

import com.a.a.c.f.t;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/e.class */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final com.a.a.c.m.b f453a = p.a();

    /* renamed from: b, reason: collision with root package name */
    private static final Class<?> f454b = Object.class;
    private static final Class<?> c = Enum.class;
    private static final Class<?> d = List.class;
    private static final Class<?> e = Map.class;
    private final com.a.a.c.b.q<?> f;
    private final com.a.a.c.a g;
    private final t.a h;
    private final com.a.a.c.l.n i;
    private final com.a.a.c.j j;
    private final Class<?> k;
    private final Class<?> l;
    private final boolean m;

    private e(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, t.a aVar) {
        this.f = qVar;
        this.j = jVar;
        this.k = jVar.b();
        this.h = aVar;
        this.i = jVar.x();
        this.g = qVar.f() ? qVar.j() : null;
        this.l = aVar == null ? null : aVar.i(this.k);
        this.m = (this.g == null || (com.a.a.c.m.i.m(this.k) && this.j.n())) ? false : true;
    }

    private e(com.a.a.c.b.q<?> qVar, Class<?> cls, t.a aVar) {
        e eVar;
        Class<?> i;
        this.f = qVar;
        this.j = null;
        this.k = cls;
        this.h = aVar;
        this.i = com.a.a.c.l.n.a();
        if (qVar == null) {
            this.g = null;
            eVar = this;
            i = null;
        } else {
            this.g = qVar.f() ? qVar.j() : null;
            eVar = this;
            i = aVar == null ? null : aVar.i(this.k);
        }
        eVar.l = i;
        this.m = this.g != null;
    }

    public static d a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, t.a aVar) {
        if (jVar.g() && b(qVar, jVar.b())) {
            return b(jVar.b());
        }
        return new e(qVar, jVar, aVar).a();
    }

    public static d a(com.a.a.c.b.q<?> qVar, Class<?> cls) {
        return a(qVar, cls, qVar);
    }

    private static d a(com.a.a.c.b.q<?> qVar, Class<?> cls, t.a aVar) {
        if (cls.isArray() && b(qVar, cls)) {
            return b(cls);
        }
        return new e(qVar, cls, aVar).b();
    }

    private static boolean b(com.a.a.c.b.q<?> qVar, Class<?> cls) {
        return qVar == null || qVar.i(cls) == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(Class<?> cls) {
        return new d(cls);
    }

    private static d b(Class<?> cls) {
        return new d(cls);
    }

    private d a() {
        ArrayList arrayList = new ArrayList(8);
        if (!this.j.a(Object.class)) {
            if (this.j.k()) {
                b(this.j, arrayList, false);
            } else {
                a(this.j, (List<com.a.a.c.j>) arrayList, false);
            }
        }
        return new d(this.j, this.k, arrayList, this.l, a(arrayList), this.i, this.g, this.h, this.f.p(), this.m);
    }

    private d b() {
        List<com.a.a.c.j> emptyList = Collections.emptyList();
        return new d(null, this.k, emptyList, this.l, a(emptyList), this.i, this.g, this.h, this.f.p(), this.m);
    }

    private static void a(com.a.a.c.j jVar, List<com.a.a.c.j> list, boolean z) {
        Class<?> b2 = jVar.b();
        if (b2 == f454b || b2 == c) {
            return;
        }
        if (z) {
            if (a(list, b2)) {
                return;
            } else {
                list.add(jVar);
            }
        }
        Iterator<com.a.a.c.j> it = jVar.z().iterator();
        while (it.hasNext()) {
            b(it.next(), list, true);
        }
        com.a.a.c.j y = jVar.y();
        if (y != null) {
            a(y, list, true);
        }
    }

    private static void b(com.a.a.c.j jVar, List<com.a.a.c.j> list, boolean z) {
        Class<?> b2 = jVar.b();
        if (z) {
            if (a(list, b2)) {
                return;
            }
            list.add(jVar);
            if (b2 == d || b2 == e) {
                return;
            }
        }
        Iterator<com.a.a.c.j> it = jVar.z().iterator();
        while (it.hasNext()) {
            b(it.next(), list, true);
        }
    }

    private static boolean a(List<com.a.a.c.j> list, Class<?> cls) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).b() == cls) {
                return true;
            }
        }
        return false;
    }

    private com.a.a.c.m.b a(List<com.a.a.c.j> list) {
        if (this.g == null) {
            return f453a;
        }
        boolean z = this.h != null && (!(this.h instanceof am) || ((am) this.h).a());
        boolean z2 = z;
        if (!z && !this.m) {
            return f453a;
        }
        p b2 = p.b();
        if (this.l != null) {
            b2 = a(b2, this.k, this.l);
        }
        if (this.m) {
            b2 = a(b2, com.a.a.c.m.i.o(this.k));
        }
        for (com.a.a.c.j jVar : list) {
            if (z2) {
                Class<?> b3 = jVar.b();
                b2 = a(b2, b3, this.h.i(b3));
            }
            if (this.m) {
                b2 = a(b2, com.a.a.c.m.i.o(jVar.b()));
            }
        }
        if (z2) {
            b2 = a(b2, Object.class, this.h.i(Object.class));
        }
        return b2.c();
    }

    private p a(p pVar, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            pVar = a(pVar, com.a.a.c.m.i.o(cls2));
            Iterator<Class<?>> it = com.a.a.c.m.i.b(cls2, cls, false).iterator();
            while (it.hasNext()) {
                pVar = a(pVar, com.a.a.c.m.i.o(it.next()));
            }
        }
        return pVar;
    }

    private p a(p pVar, Annotation[] annotationArr) {
        if (annotationArr != null) {
            for (Annotation annotation : annotationArr) {
                if (!pVar.a(annotation)) {
                    pVar = pVar.b(annotation);
                    if (this.g.a(annotation)) {
                        pVar = a(pVar, annotation);
                    }
                }
            }
        }
        return pVar;
    }

    private p a(p pVar, Annotation annotation) {
        for (Annotation annotation2 : com.a.a.c.m.i.o(annotation.annotationType())) {
            if (!(annotation2 instanceof Target) && !(annotation2 instanceof Retention) && !pVar.a(annotation2)) {
                pVar = pVar.b(annotation2);
                if (this.g.a(annotation2)) {
                    pVar = a(pVar, annotation2);
                }
            }
        }
        return pVar;
    }
}
