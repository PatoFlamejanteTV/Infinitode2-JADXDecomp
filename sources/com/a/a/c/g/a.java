package com.a.a.c.g;

import com.a.a.a.i;
import com.a.a.c.aa;
import com.a.a.c.f.f;
import com.a.a.c.g;
import com.a.a.c.j;
import com.a.a.c.k.a.l;
import com.a.a.c.k.q;
import com.a.a.c.m.i;
import com.a.a.c.m.n;
import com.a.a.c.m.o;
import com.a.a.c.m.w;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/g/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final o<n, com.a.a.c.o<Object>> f488a;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicReference<l> f489b;

    public static String[] a(Class<?> cls) {
        return c.a().a(cls);
    }

    public static f a(g gVar, com.a.a.c.b bVar, List<String> list) {
        return new C0009a(gVar, bVar).a(list);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/g/a$c.class */
    static class c {

        /* renamed from: a, reason: collision with root package name */
        private final Method f494a;

        /* renamed from: b, reason: collision with root package name */
        private final Method f495b;
        private final Method c;
        private static final c d;
        private static final RuntimeException e;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.RuntimeException] */
        static {
            c cVar = null;
            c cVar2 = null;
            c cVar3 = null;
            try {
                cVar2 = new c();
                cVar3 = cVar2;
            } catch (RuntimeException e2) {
                cVar = cVar2;
            }
            d = cVar3;
            e = cVar;
        }

        private c() {
            try {
                this.f494a = Class.class.getMethod("getRecordComponents", new Class[0]);
                Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
                this.f495b = cls.getMethod("getName", new Class[0]);
                this.c = cls.getMethod("getType", new Class[0]);
            } catch (Exception e2) {
                throw new RuntimeException(String.format("Failed to access Methods needed to support `java.lang.Record`: (%s) %s", e2.getClass().getName(), e2.getMessage()), e2);
            }
        }

        public static c a() {
            if (e != null) {
                throw e;
            }
            return d;
        }

        public final String[] a(Class<?> cls) {
            Object[] c = c(cls);
            if (c == null) {
                return null;
            }
            String[] strArr = new String[c.length];
            for (int i = 0; i < c.length; i++) {
                try {
                    strArr[i] = (String) this.f495b.invoke(c[i], new Object[0]);
                } catch (Exception e2) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(c.length), i.g(cls)), e2);
                }
            }
            return strArr;
        }

        public final b[] b(Class<?> cls) {
            Object[] c = c(cls);
            if (c == null) {
                return null;
            }
            b[] bVarArr = new b[c.length];
            for (int i = 0; i < c.length; i++) {
                try {
                    try {
                        bVarArr[i] = new b((Class) this.c.invoke(c[i], new Object[0]), (String) this.f495b.invoke(c[i], new Object[0]));
                    } catch (Exception e2) {
                        throw new IllegalArgumentException(String.format("Failed to access type of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(c.length), i.g(cls)), e2);
                    }
                } catch (Exception e3) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(c.length), i.g(cls)), e3);
                }
            }
            return bVarArr;
        }

        /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable, java.lang.Object[]] */
        private Object[] c(Class<?> cls) {
            ?? r0;
            try {
                r0 = (Object[]) this.f494a.invoke(cls, new Object[0]);
                return r0;
            } catch (Exception e2) {
                if (w.a((Throwable) r0)) {
                    return null;
                }
                throw new IllegalArgumentException("Failed to access RecordComponents of type " + i.g(cls));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/g/a$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public final Class<?> f492a;

        /* renamed from: b, reason: collision with root package name */
        public final String f493b;

        public b(Class<?> cls, String str) {
            this.f492a = cls;
            this.f493b = str;
        }
    }

    /* renamed from: com.a.a.c.g.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/g/a$a.class */
    static class C0009a {

        /* renamed from: a, reason: collision with root package name */
        private com.a.a.c.b f490a;

        /* renamed from: b, reason: collision with root package name */
        private com.a.a.c.f f491b;
        private com.a.a.c.a c;
        private List<f> d;
        private f e;
        private b[] f;

        C0009a(g gVar, com.a.a.c.b bVar) {
            this.f490a = bVar;
            this.c = gVar.f();
            this.f491b = gVar.a();
            this.f = c.a().b(bVar.b());
            if (this.f == null) {
                this.d = bVar.k();
                this.e = null;
                return;
            }
            int length = this.f.length;
            f fVar = null;
            if (length == 0) {
                fVar = bVar.o();
                this.d = Collections.singletonList(fVar);
            } else {
                this.d = bVar.k();
                Iterator<f> it = this.d.iterator();
                loop0: while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    f next = it.next();
                    if (next.f() == length) {
                        int i = 0;
                        while (i < length) {
                            i = next.a(i).equals(this.f[i].f492a) ? i + 1 : i;
                        }
                        fVar = next;
                        break loop0;
                    }
                }
            }
            if (fVar == null) {
                throw new IllegalArgumentException("Failed to find the canonical Record constructor of type " + i.b(this.f490a.a()));
            }
            this.e = fVar;
        }

        public final f a(List<String> list) {
            for (f fVar : this.d) {
                i.a a2 = this.c.a(this.f491b, fVar);
                if (a2 != null && i.a.DISABLED != a2 && (i.a.DELEGATING == a2 || fVar != this.e)) {
                    return null;
                }
            }
            if (this.f == null) {
                return null;
            }
            for (b bVar : this.f) {
                list.add(bVar.f493b);
            }
            return this.e;
        }
    }

    public a() {
        this(4000);
    }

    private a(int i) {
        this.f488a = new o<>(Math.min(64, 1000), 4000);
        this.f489b = new AtomicReference<>();
    }

    public l a() {
        l lVar = this.f489b.get();
        if (lVar != null) {
            return lVar;
        }
        return b();
    }

    private synchronized l b() {
        l lVar = this.f489b.get();
        l lVar2 = lVar;
        if (lVar == null) {
            lVar2 = l.a(this.f488a);
            this.f489b.set(lVar2);
        }
        return lVar2;
    }

    public com.a.a.c.o<Object> b(Class<?> cls) {
        com.a.a.c.o<Object> a2;
        synchronized (this) {
            a2 = this.f488a.a(new n(cls, false));
        }
        return a2;
    }

    public com.a.a.c.o<Object> a(j jVar) {
        com.a.a.c.o<Object> a2;
        synchronized (this) {
            a2 = this.f488a.a(new n(jVar, false));
        }
        return a2;
    }

    public com.a.a.c.o<Object> b(j jVar) {
        com.a.a.c.o<Object> a2;
        synchronized (this) {
            a2 = this.f488a.a(new n(jVar, true));
        }
        return a2;
    }

    public com.a.a.c.o<Object> c(Class<?> cls) {
        com.a.a.c.o<Object> a2;
        synchronized (this) {
            a2 = this.f488a.a(new n(cls, true));
        }
        return a2;
    }

    public void a(j jVar, com.a.a.c.o<Object> oVar) {
        synchronized (this) {
            if (this.f488a.a(new n(jVar, true), oVar) == null) {
                this.f489b.set(null);
            }
        }
    }

    public void a(Class<?> cls, com.a.a.c.o<Object> oVar) {
        synchronized (this) {
            if (this.f488a.a(new n(cls, true), oVar) == null) {
                this.f489b.set(null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(j jVar, com.a.a.c.o<Object> oVar, aa aaVar) {
        synchronized (this) {
            if (this.f488a.a(new n(jVar, false), oVar) == null) {
                this.f489b.set(null);
            }
            if (oVar instanceof q) {
                ((q) oVar).a(aaVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(Class<?> cls, j jVar, com.a.a.c.o<Object> oVar, aa aaVar) {
        synchronized (this) {
            com.a.a.c.o<Object> a2 = this.f488a.a(new n(cls, false), oVar);
            com.a.a.c.o<Object> a3 = this.f488a.a(new n(jVar, false), oVar);
            if (a2 == null || a3 == null) {
                this.f489b.set(null);
            }
            if (oVar instanceof q) {
                ((q) oVar).a(aaVar);
            }
        }
    }
}
