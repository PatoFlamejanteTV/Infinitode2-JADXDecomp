package com.a.a.c.k.b;

import com.a.a.a.l;
import com.a.a.b.l;
import java.math.BigDecimal;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa.class */
public class aa {

    /* renamed from: a, reason: collision with root package name */
    private final int f591a;

    /* renamed from: b, reason: collision with root package name */
    private final int f592b;
    private final byte c;

    public static void a(Map<String, com.a.a.c.o<?>> map) {
        map.put(Integer.class.getName(), new e(Integer.class));
        map.put(Integer.TYPE.getName(), new e(Integer.TYPE));
        map.put(Long.class.getName(), new f(Long.class));
        map.put(Long.TYPE.getName(), new f(Long.TYPE));
        map.put(Byte.class.getName(), d.f594a);
        map.put(Byte.TYPE.getName(), d.f594a);
        map.put(Short.class.getName(), g.f595a);
        map.put(Short.TYPE.getName(), g.f595a);
        map.put(Double.class.getName(), new b(Double.class));
        map.put(Double.TYPE.getName(), new b(Double.TYPE));
        map.put(Float.class.getName(), c.f593a);
        map.put(Float.TYPE.getName(), c.f593a);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$a.class */
    public static abstract class a<T> extends an<T> implements com.a.a.c.k.k {
        protected a(Class<?> cls, l.b bVar, String str) {
            super(cls, (byte) 0);
            if (bVar == l.b.INT || bVar == l.b.LONG) {
                return;
            }
            l.b bVar2 = l.b.BIG_INTEGER;
        }

        @Override // com.a.a.c.k.k
        public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
            l.d a2 = a(aaVar, cVar, (Class<?>) a());
            if (a2 != null) {
                switch (ab.f596a[a2.c().ordinal()]) {
                    case 1:
                        if (a() == BigDecimal.class) {
                            return y.d();
                        }
                        return as.f611a;
                }
            }
            return this;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$g.class */
    public static class g extends a<Object> {

        /* renamed from: a, reason: collision with root package name */
        static final g f595a = new g();

        public g() {
            super(Short.class, l.b.INT, "number");
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.a(((Short) obj).shortValue());
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$e.class */
    public static class e extends a<Object> {
        public e(Class<?> cls) {
            super(cls, l.b.INT, "integer");
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.c(((Integer) obj).intValue());
        }

        @Override // com.a.a.c.k.b.an, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
            a(obj, hVar, aaVar);
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$d.class */
    public static class d extends a<Object> {

        /* renamed from: a, reason: collision with root package name */
        static final d f594a = new d();

        public d() {
            super(Number.class, l.b.INT, "integer");
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.c(((Number) obj).intValue());
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$f.class */
    public static class f extends a<Object> {
        public f(Class<?> cls) {
            super(cls, l.b.LONG, "number");
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.b(((Long) obj).longValue());
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$c.class */
    public static class c extends a<Object> {

        /* renamed from: a, reason: collision with root package name */
        static final c f593a = new c();

        public c() {
            super(Float.class, l.b.FLOAT, "number");
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.a(((Float) obj).floatValue());
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/aa$b.class */
    public static class b extends a<Object> {
        public b(Class<?> cls) {
            super(cls, l.b.DOUBLE, "number");
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.a(((Double) obj).doubleValue());
        }

        @Override // com.a.a.c.k.b.an, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
            Double d = (Double) obj;
            if (com.a.a.b.c.i.b(d.doubleValue())) {
                com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(obj, com.a.a.b.o.VALUE_NUMBER_FLOAT));
                hVar.a(d.doubleValue());
                iVar.b(hVar, a2);
                return;
            }
            hVar.a(d.doubleValue());
        }
    }

    public aa(int i, int i2, byte b2) {
        this.f591a = i;
        this.f592b = i2;
        this.c = b2;
    }

    public int a() {
        return this.f591a;
    }

    public int b() {
        return this.f592b;
    }

    public byte c() {
        return this.c;
    }
}
