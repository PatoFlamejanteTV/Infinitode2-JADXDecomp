package com.a.a.c.c;

import com.a.a.c.f.ad;
import com.a.a.c.m.af;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

/* loaded from: infinitode-2.jar:com/a/a/c/c/v.class */
public abstract class v extends com.a.a.c.f.v implements Serializable {
    private static com.a.a.c.k<Object> g = new com.a.a.c.c.a.h("No _valueDeserializer assigned");
    private com.a.a.c.w h;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.j f415b;
    private com.a.a.c.w i;
    private transient com.a.a.c.m.b j;
    protected final com.a.a.c.k<Object> c;
    protected final com.a.a.c.i.e d;
    protected final s e;
    private String k;
    protected ad f;
    private af l;
    private int m;

    public abstract v a(com.a.a.c.k<?> kVar);

    public abstract v a(com.a.a.c.w wVar);

    public abstract v a(s sVar);

    @Override // com.a.a.c.c
    public abstract com.a.a.c.f.j e();

    public abstract void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj);

    public abstract Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj);

    public abstract void a(Object obj, Object obj2);

    public abstract Object b(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: protected */
    public v(com.a.a.c.f.s sVar, com.a.a.c.j jVar, com.a.a.c.i.e eVar, com.a.a.c.m.b bVar) {
        this(sVar.b(), jVar, sVar.c(), eVar, bVar, sVar.h());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public v(com.a.a.c.w wVar, com.a.a.c.j jVar, com.a.a.c.w wVar2, com.a.a.c.i.e eVar, com.a.a.c.m.b bVar, com.a.a.c.v vVar) {
        super(vVar);
        this.m = -1;
        if (wVar == null) {
            this.h = com.a.a.c.w.f771b;
        } else {
            this.h = wVar.a();
        }
        this.f415b = jVar;
        this.i = wVar2;
        this.j = bVar;
        this.l = null;
        this.d = eVar != null ? eVar.a(this) : eVar;
        this.c = g;
        this.e = g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public v(com.a.a.c.w wVar, com.a.a.c.j jVar, com.a.a.c.v vVar, com.a.a.c.k<Object> kVar) {
        super(vVar);
        this.m = -1;
        if (wVar == null) {
            this.h = com.a.a.c.w.f771b;
        } else {
            this.h = wVar.a();
        }
        this.f415b = jVar;
        this.i = null;
        this.j = null;
        this.l = null;
        this.d = null;
        this.c = kVar;
        this.e = kVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public v(v vVar) {
        super(vVar);
        this.m = -1;
        this.h = vVar.h;
        this.f415b = vVar.f415b;
        this.i = vVar.i;
        this.j = vVar.j;
        this.c = vVar.c;
        this.d = vVar.d;
        this.k = vVar.k;
        this.m = vVar.m;
        this.l = vVar.l;
        this.e = vVar.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public v(v vVar, com.a.a.c.k<?> kVar, s sVar) {
        super(vVar);
        this.m = -1;
        this.h = vVar.h;
        this.f415b = vVar.f415b;
        this.i = vVar.i;
        this.j = vVar.j;
        this.d = vVar.d;
        this.k = vVar.k;
        this.m = vVar.m;
        if (kVar == null) {
            this.c = g;
        } else {
            this.c = kVar;
        }
        this.l = vVar.l;
        this.e = sVar == g ? this.c : sVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public v(v vVar, com.a.a.c.w wVar) {
        super(vVar);
        this.m = -1;
        this.h = wVar;
        this.f415b = vVar.f415b;
        this.i = vVar.i;
        this.j = vVar.j;
        this.c = vVar.c;
        this.d = vVar.d;
        this.k = vVar.k;
        this.m = vVar.m;
        this.l = vVar.l;
        this.e = vVar.e;
    }

    public final v a(String str) {
        com.a.a.c.w wVar = this.h == null ? new com.a.a.c.w(str) : this.h.b(str);
        return wVar == this.h ? this : a(wVar);
    }

    public final void b(String str) {
        this.k = str;
    }

    public final void a(ad adVar) {
        this.f = adVar;
    }

    public final void a(Class<?>[] clsArr) {
        if (clsArr == null) {
            this.l = null;
        } else {
            this.l = af.a(clsArr);
        }
    }

    public void a(int i) {
        if (this.m != -1) {
            throw new IllegalStateException("Property '" + a() + "' already had index (" + this.m + "), trying to assign " + i);
        }
        this.m = i;
    }

    public void a(com.a.a.c.f fVar) {
    }

    public void f() {
    }

    public boolean g() {
        return false;
    }

    @Override // com.a.a.c.c, com.a.a.c.m.v
    public final String a() {
        return this.h.b();
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.w b() {
        return this.h;
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.j c() {
        return this.f415b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Class<?> k() {
        return e().h();
    }

    public String l() {
        return this.k;
    }

    public ad m() {
        return this.f;
    }

    public boolean n() {
        return (this.c == null || this.c == g) ? false : true;
    }

    public boolean o() {
        return this.d != null;
    }

    public com.a.a.c.k<Object> p() {
        com.a.a.c.k<Object> kVar = this.c;
        if (kVar == g) {
            return null;
        }
        return kVar;
    }

    public com.a.a.c.i.e q() {
        return this.d;
    }

    public final s r() {
        return this.e;
    }

    public boolean a(Class<?> cls) {
        return this.l == null || this.l.a(cls);
    }

    public boolean s() {
        return this.l != null;
    }

    public int h() {
        throw new IllegalStateException(String.format("Internal error: no creator index for property '%s' (of type %s)", a(), getClass().getName()));
    }

    public Object i() {
        return null;
    }

    public boolean j() {
        return false;
    }

    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            return this.e.a(gVar);
        }
        if (this.d != null) {
            return this.c.a(lVar, gVar, this.d);
        }
        Object a2 = this.c.a(lVar, gVar);
        Object obj = a2;
        if (a2 == null) {
            obj = this.e.a(gVar);
        }
        return obj;
    }

    public final Object c(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            if (com.a.a.c.c.a.q.a(this.e)) {
                return obj;
            }
            return this.e.a(gVar);
        }
        if (this.d != null) {
            return gVar.a(gVar.b().a((Type) obj.getClass()), this).a(lVar, gVar, (com.a.a.c.g) obj);
        }
        Object a2 = this.c.a(lVar, gVar, (com.a.a.c.g) obj);
        Object obj2 = a2;
        if (a2 == null) {
            if (com.a.a.c.c.a.q.a(this.e)) {
                return obj;
            }
            obj2 = this.e.a(gVar);
        }
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(com.a.a.b.l lVar, Exception exc, Object obj) {
        if (exc instanceof IllegalArgumentException) {
            StringBuilder append = new StringBuilder("Problem deserializing property '").append(a()).append("' (expected type: ").append(c()).append("; actual type: ").append(com.a.a.c.m.i.d(obj)).append(")");
            String g2 = com.a.a.c.m.i.g(exc);
            if (g2 != null) {
                append.append(", problem: ").append(g2);
            } else {
                append.append(" (no error message provided)");
            }
            throw com.a.a.c.l.a(lVar, append.toString(), exc);
        }
        a(lVar, exc);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static IOException a(com.a.a.b.l lVar, Exception exc) {
        com.a.a.c.m.i.c((Throwable) exc);
        com.a.a.c.m.i.b((Throwable) exc);
        Throwable d = com.a.a.c.m.i.d((Throwable) exc);
        throw com.a.a.c.l.a(lVar, com.a.a.c.m.i.g(d), d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Exception exc, Object obj) {
        a((com.a.a.b.l) null, exc, obj);
    }

    public String toString() {
        return "[property '" + a() + "']";
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/v$a.class */
    public static abstract class a extends v {
        protected final v g;

        protected abstract v a(v vVar);

        /* JADX INFO: Access modifiers changed from: protected */
        public a(v vVar) {
            super(vVar);
            this.g = vVar;
        }

        private v b(v vVar) {
            if (vVar == this.g) {
                return this;
            }
            return a(vVar);
        }

        @Override // com.a.a.c.c.v
        public final v a(com.a.a.c.k<?> kVar) {
            return b(this.g.a(kVar));
        }

        @Override // com.a.a.c.c.v
        public final v a(com.a.a.c.w wVar) {
            return b(this.g.a(wVar));
        }

        @Override // com.a.a.c.c.v
        public final v a(s sVar) {
            return b(this.g.a(sVar));
        }

        @Override // com.a.a.c.c.v
        public final void a(int i) {
            this.g.a(i);
        }

        @Override // com.a.a.c.c.v
        public void a(com.a.a.c.f fVar) {
            this.g.a(fVar);
        }

        @Override // com.a.a.c.c.v
        protected final Class<?> k() {
            return this.g.k();
        }

        @Override // com.a.a.c.c.v
        public final String l() {
            return this.g.l();
        }

        @Override // com.a.a.c.c.v
        public final ad m() {
            return this.g.m();
        }

        @Override // com.a.a.c.c.v
        public final boolean n() {
            return this.g.n();
        }

        @Override // com.a.a.c.c.v
        public final boolean o() {
            return this.g.o();
        }

        @Override // com.a.a.c.c.v
        public final com.a.a.c.k<Object> p() {
            return this.g.p();
        }

        @Override // com.a.a.c.c.v
        public final com.a.a.c.i.e q() {
            return this.g.q();
        }

        @Override // com.a.a.c.c.v
        public final boolean a(Class<?> cls) {
            return this.g.a(cls);
        }

        @Override // com.a.a.c.c.v
        public final boolean s() {
            return this.g.s();
        }

        @Override // com.a.a.c.c.v
        public final int h() {
            return this.g.h();
        }

        @Override // com.a.a.c.c.v
        public final Object i() {
            return this.g.i();
        }

        @Override // com.a.a.c.c.v
        public final boolean j() {
            return this.g.j();
        }

        @Override // com.a.a.c.c.v, com.a.a.c.c
        public final com.a.a.c.f.j e() {
            return this.g.e();
        }

        @Override // com.a.a.c.c.v
        public void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
            this.g.a(lVar, gVar, obj);
        }

        @Override // com.a.a.c.c.v
        public Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
            return this.g.b(lVar, gVar, obj);
        }

        @Override // com.a.a.c.c.v
        public void a(Object obj, Object obj2) {
            this.g.a(obj, obj2);
        }

        @Override // com.a.a.c.c.v
        public Object b(Object obj, Object obj2) {
            return this.g.b(obj, obj2);
        }
    }
}
