package com.a.a.c.k.b;

import com.a.a.a.af;
import com.d.h.w;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/t.class */
public final class t extends ao<Object> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.f.j f628a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.i.i f629b;
    private com.a.a.c.o<Object> c;
    private com.a.a.c.c d;
    private com.a.a.c.j e;
    private boolean f;
    private transient com.a.a.c.k.a.k g;

    public t(com.a.a.c.f.j jVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar) {
        super(jVar.c());
        this.f628a = jVar;
        this.e = jVar.c();
        this.f629b = iVar;
        this.c = oVar;
        this.d = null;
        this.f = true;
        this.g = com.a.a.c.k.a.k.a();
    }

    private t(t tVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, boolean z) {
        super(a((Class<?>) tVar.a()));
        this.f628a = tVar.f628a;
        this.e = tVar.e;
        this.f629b = iVar;
        this.c = oVar;
        this.d = cVar;
        this.f = z;
        this.g = com.a.a.c.k.a.k.a();
    }

    private static final Class<Object> a(Class<?> cls) {
        return cls == null ? Object.class : cls;
    }

    private t a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, boolean z) {
        if (this.d == cVar && this.f629b == iVar && this.c == oVar && z == this.f) {
            return this;
        }
        return new t(this, cVar, iVar, oVar, z);
    }

    @Override // com.a.a.c.o
    public final boolean a(com.a.a.c.aa aaVar, Object obj) {
        Object b2 = this.f628a.b(obj);
        if (b2 == null) {
            return true;
        }
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            try {
                oVar2 = a(aaVar, b2.getClass());
            } catch (com.a.a.c.l e) {
                throw new w.a(e);
            }
        }
        return oVar2.a(aaVar, b2);
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.i.i iVar = this.f629b;
        com.a.a.c.i.i iVar2 = iVar;
        if (iVar != null) {
            iVar2 = iVar2.a(cVar);
        }
        com.a.a.c.o<?> oVar = this.c;
        if (oVar == null) {
            if (aaVar.a(com.a.a.c.q.USE_STATIC_TYPING) || this.e.m()) {
                com.a.a.c.o<Object> b2 = aaVar.b(this.e, cVar);
                return a(cVar, iVar2, (com.a.a.c.o<?>) b2, a(this.e.b(), (com.a.a.c.o<?>) b2));
            }
            if (cVar != this.d) {
                return a(cVar, iVar2, oVar, this.f);
            }
            return this;
        }
        return a(cVar, iVar2, aaVar.a(oVar, cVar), this.f);
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        Object obj2;
        try {
            obj2 = this.f628a.b(obj);
        } catch (Exception e) {
            obj2 = null;
            a(aaVar, e, obj, this.f628a.b() + "()");
        }
        if (obj2 == null) {
            aaVar.a(hVar);
            return;
        }
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            oVar2 = a(aaVar, obj2.getClass());
        }
        if (this.f629b != null) {
            oVar2.a(obj2, hVar, aaVar, this.f629b);
        } else {
            oVar2.a(obj2, hVar, aaVar);
        }
    }

    @Override // com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        Object obj2;
        try {
            obj2 = this.f628a.b(obj);
        } catch (Exception e) {
            obj2 = null;
            a(aaVar, e, obj, this.f628a.b() + "()");
        }
        if (obj2 == null) {
            aaVar.a(hVar);
            return;
        }
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            oVar2 = a(aaVar, obj2.getClass());
        } else if (this.f) {
            com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(obj, com.a.a.b.o.VALUE_STRING));
            oVar2.a(obj2, hVar, aaVar);
            iVar.b(hVar, a2);
            return;
        }
        oVar2.a(obj2, hVar, aaVar, new a(iVar, obj));
    }

    private boolean a(Class<?> cls, com.a.a.c.o<?> oVar) {
        if (cls.isPrimitive()) {
            if (cls != Integer.TYPE && cls != Boolean.TYPE && cls != Double.TYPE) {
                return false;
            }
        } else if (cls != String.class && cls != Integer.class && cls != Boolean.class && cls != Double.class) {
            return false;
        }
        return a(oVar);
    }

    private com.a.a.c.o<Object> a(com.a.a.c.aa aaVar, Class<?> cls) {
        com.a.a.c.o<Object> a2 = this.g.a(cls);
        com.a.a.c.o<Object> oVar = a2;
        if (a2 == null) {
            if (this.e.s()) {
                com.a.a.c.j a3 = aaVar.a(this.e, cls);
                oVar = aaVar.b(a3, this.d);
                this.g = this.g.a(a3, oVar).f568b;
            } else {
                oVar = aaVar.b(cls, this.d);
                this.g = this.g.a(cls, oVar).f568b;
            }
        }
        return oVar;
    }

    public final String toString() {
        return "(@JsonValue serializer for method " + this.f628a.h() + "#" + this.f628a.b() + ")";
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/t$a.class */
    static class a extends com.a.a.c.i.i {

        /* renamed from: a, reason: collision with root package name */
        private com.a.a.c.i.i f630a;

        /* renamed from: b, reason: collision with root package name */
        private Object f631b;

        public a(com.a.a.c.i.i iVar, Object obj) {
            this.f630a = iVar;
            this.f631b = obj;
        }

        @Override // com.a.a.c.i.i
        public final com.a.a.c.i.i a(com.a.a.c.c cVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.a.a.c.i.i
        public final af.a a() {
            return this.f630a.a();
        }

        @Override // com.a.a.c.i.i
        public final String b() {
            return this.f630a.b();
        }

        @Override // com.a.a.c.i.i
        public final com.a.a.b.f.a a(com.a.a.b.h hVar, com.a.a.b.f.a aVar) {
            aVar.f144a = this.f631b;
            return this.f630a.a(hVar, aVar);
        }

        @Override // com.a.a.c.i.i
        public final com.a.a.b.f.a b(com.a.a.b.h hVar, com.a.a.b.f.a aVar) {
            return this.f630a.b(hVar, aVar);
        }
    }
}
