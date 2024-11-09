package com.a.a.c.c;

import com.a.a.c.c.a.z;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/u.class */
public abstract class u implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.c f411a;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.f.j f412b;
    protected final com.a.a.c.j c;
    protected com.a.a.c.k<Object> d;
    protected final com.a.a.c.i.e e;
    protected final com.a.a.c.p f;

    public abstract u a(com.a.a.c.k<Object> kVar);

    protected abstract void b(Object obj, Object obj2, Object obj3);

    public u(com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        this.f411a = cVar;
        this.f412b = jVar;
        this.c = jVar2;
        this.d = kVar;
        this.e = eVar;
        this.f = pVar;
    }

    public static u a(com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        return new d(cVar, jVar, jVar2, pVar, kVar, eVar);
    }

    public static u a(com.a.a.c.g gVar, com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        Class<?> d2 = jVar.d();
        Class<?> cls = d2;
        if (d2 == Map.class) {
            cls = LinkedHashMap.class;
        }
        gVar.a();
        return new c(cVar, jVar, jVar2, pVar, kVar, eVar, com.a.a.c.c.a.k.b(cls));
    }

    public static u a(com.a.a.c.g gVar, com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.k<Object> kVar) {
        return new b(cVar, jVar, jVar2, kVar, gVar.l());
    }

    public final void a(com.a.a.c.f fVar) {
        this.f412b.a(fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    public final com.a.a.c.c a() {
        return this.f411a;
    }

    public final boolean b() {
        return this.d != null;
    }

    public final com.a.a.c.j c() {
        return this.c;
    }

    public final String d() {
        return this.f411a.a();
    }

    public void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, String str) {
        try {
            a(obj, this.f == null ? str : this.f.a(str, gVar), a(lVar, gVar));
        } catch (w e) {
            if (this.d.f() == null) {
                throw com.a.a.c.l.a(lVar, "Unresolved forward reference but no identity info.", e);
            }
            e.d().a((z.a) new a(this, e, this.c.b(), obj, str));
        }
    }

    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            return this.d.a(gVar);
        }
        if (this.e != null) {
            return this.d.a(lVar, gVar, this.e);
        }
        return this.d.a(lVar, gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(Object obj, Object obj2, Object obj3) {
        try {
            b(obj, obj2, obj3);
        } catch (IOException e) {
            throw this;
        } catch (Exception e2) {
            a(e2, obj2, obj3);
        }
    }

    private void a(Exception exc, Object obj, Object obj2) {
        if (exc instanceof IllegalArgumentException) {
            String d2 = com.a.a.c.m.i.d(obj2);
            StringBuilder append = new StringBuilder("Problem deserializing \"any-property\" '").append(obj);
            append.append("' of class " + e() + " (expected type: ").append(this.c);
            append.append("; actual type: ").append(d2).append(")");
            String g = com.a.a.c.m.i.g(exc);
            if (g != null) {
                append.append(", problem: ").append(g);
            } else {
                append.append(" (no error message provided)");
            }
            throw new com.a.a.c.l((Closeable) null, append.toString(), exc);
        }
        com.a.a.c.m.i.c((Throwable) exc);
        com.a.a.c.m.i.b((Throwable) exc);
        Throwable d3 = com.a.a.c.m.i.d((Throwable) exc);
        throw new com.a.a.c.l((Closeable) null, com.a.a.c.m.i.g(d3), d3);
    }

    private String e() {
        return com.a.a.c.m.i.g(this.f412b.h());
    }

    public String toString() {
        return "[any property on class " + e() + "]";
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/u$a.class */
    static class a extends z.a {

        /* renamed from: a, reason: collision with root package name */
        private final u f413a;

        /* renamed from: b, reason: collision with root package name */
        private final Object f414b;
        private final String c;

        public a(u uVar, w wVar, Class<?> cls, Object obj, String str) {
            super(wVar, cls);
            this.f413a = uVar;
            this.f414b = obj;
            this.c = str;
        }

        @Override // com.a.a.c.c.a.z.a
        public final void a(Object obj, Object obj2) {
            if (!b(obj)) {
                throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj.toString() + "] that wasn't previously registered.");
            }
            this.f413a.a(this.f414b, this.c, obj2);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/u$d.class */
    public static class d extends u implements Serializable {
        public d(com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
            super(cVar, jVar, jVar2, pVar, kVar, eVar);
        }

        @Override // com.a.a.c.c.u
        protected final void b(Object obj, Object obj2, Object obj3) {
            ((com.a.a.c.f.k) this.f412b).a(obj, obj2, obj3);
        }

        @Override // com.a.a.c.c.u
        public final u a(com.a.a.c.k<Object> kVar) {
            return new d(this.f411a, this.f412b, this.c, this.f, kVar, this.e);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/u$c.class */
    public static class c extends u implements Serializable {
        private x g;

        public c(com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, x xVar) {
            super(cVar, jVar, jVar2, pVar, kVar, eVar);
            this.g = xVar;
        }

        @Override // com.a.a.c.c.u
        public final u a(com.a.a.c.k<Object> kVar) {
            return new c(this.f411a, this.f412b, this.c, this.f, kVar, this.e, this.g);
        }

        @Override // com.a.a.c.c.u
        protected final void b(Object obj, Object obj2, Object obj3) {
            com.a.a.c.f.h hVar = (com.a.a.c.f.h) this.f412b;
            Map<Object, Object> map = (Map) hVar.b(obj);
            Map<Object, Object> map2 = map;
            if (map == null) {
                map2 = a((com.a.a.c.g) null, hVar, obj);
            }
            map2.put(obj2, obj3);
        }

        private Map<Object, Object> a(com.a.a.c.g gVar, com.a.a.c.f.h hVar, Object obj) {
            if (this.g == null) {
                throw com.a.a.c.l.a(gVar, String.format("Cannot create an instance of %s for use as \"any-setter\" '%s'", com.a.a.c.m.i.g(this.c.b()), this.f411a.a()));
            }
            Map<Object, Object> map = (Map) this.g.a(gVar);
            hVar.a(obj, map);
            return map;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/u$b.class */
    public static class b extends u implements Serializable {
        private com.a.a.c.j.l g;

        public b(com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2, com.a.a.c.k<Object> kVar, com.a.a.c.j.l lVar) {
            super(cVar, jVar, jVar2, null, kVar, null);
            this.g = lVar;
        }

        @Override // com.a.a.c.c.u
        public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, String str) {
            a(obj, str, (com.a.a.c.m) a(lVar, gVar));
        }

        @Override // com.a.a.c.c.u
        public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return this.d.a(lVar, gVar);
        }

        @Override // com.a.a.c.c.u
        protected final void b(Object obj, Object obj2, Object obj3) {
            a(obj, (String) obj2, (com.a.a.c.m) obj3);
        }

        private void a(Object obj, String str, com.a.a.c.m mVar) {
            com.a.a.c.j.r rVar;
            com.a.a.c.f.h hVar = (com.a.a.c.f.h) this.f412b;
            Object b2 = hVar.b(obj);
            if (b2 == null) {
                rVar = this.g.d();
                hVar.a(obj, rVar);
            } else {
                if (!(b2 instanceof com.a.a.c.j.r)) {
                    throw com.a.a.c.l.a((com.a.a.c.g) null, String.format("Value \"any-setter\" '%s' not `ObjectNode` but %s", d(), com.a.a.c.m.i.g(b2.getClass())));
                }
                rVar = (com.a.a.c.j.r) b2;
            }
            rVar.a(str, mVar);
        }

        @Override // com.a.a.c.c.u
        public final u a(com.a.a.c.k<Object> kVar) {
            return this;
        }
    }
}
