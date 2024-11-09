package com.a.a.c.k;

import com.a.a.a.al;
import com.a.a.c.aa;
import com.a.a.c.k.a.v;
import com.a.a.c.o;
import com.a.a.c.w;
import com.a.a.c.y;
import com.a.a.c.z;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/k/l.class */
public abstract class l extends aa implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private transient Map<Object, v> f646b;
    private transient ArrayList<al<?>> c;
    private transient com.a.a.b.h d;

    public abstract l a(y yVar, r rVar);

    protected l() {
    }

    protected l(aa aaVar, y yVar, r rVar) {
        super(aaVar, yVar, rVar);
    }

    @Override // com.a.a.c.aa
    public final com.a.a.c.o<Object> b(com.a.a.c.f.b bVar, Object obj) {
        com.a.a.c.o<?> oVar;
        if (obj == null) {
            return null;
        }
        if (obj instanceof com.a.a.c.o) {
            oVar = (com.a.a.c.o) obj;
        } else {
            if (!(obj instanceof Class)) {
                a(bVar.c(), "AnnotationIntrospector returned serializer definition of type " + obj.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
            }
            Class cls = (Class) obj;
            if (cls != o.a.class && !com.a.a.c.m.i.e((Class<?>) cls)) {
                if (!com.a.a.c.o.class.isAssignableFrom(cls)) {
                    a(bVar.c(), "AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonSerializer>");
                }
                com.a.a.c.k.a.d m = this.f206a.m();
                com.a.a.c.o<?> f = m == null ? null : m.f();
                oVar = f;
                if (f == null) {
                    oVar = (com.a.a.c.o) com.a.a.c.m.i.b(cls, this.f206a.g());
                }
            } else {
                return null;
            }
        }
        return a(oVar);
    }

    @Override // com.a.a.c.aa
    public final Object a(com.a.a.c.f.s sVar, Class<?> cls) {
        if (cls == null) {
            return null;
        }
        Object o = this.f206a.m() == null ? null : com.a.a.c.k.a.d.o();
        Object obj = o;
        if (o == null) {
            obj = com.a.a.c.m.i.b(cls, this.f206a.g());
        }
        return obj;
    }

    @Override // com.a.a.c.aa
    public final boolean b(Object obj) {
        if (obj == null) {
            return true;
        }
        try {
            return obj.equals(null);
        } catch (Exception e) {
            a(obj.getClass(), String.format("Problem determining whether filter of type '%s' should filter out `null` values: (%s) %s", obj.getClass().getName(), e.getClass().getName(), com.a.a.c.m.i.g(e)), e);
            return false;
        }
    }

    @Override // com.a.a.c.aa
    public final v a(Object obj, al<?> alVar) {
        if (this.f646b == null) {
            this.f646b = n();
        } else {
            v vVar = this.f646b.get(obj);
            if (vVar != null) {
                return vVar;
            }
        }
        al<?> alVar2 = null;
        if (this.c == null) {
            this.c = new ArrayList<>(8);
        } else {
            int i = 0;
            int size = this.c.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                al<?> alVar3 = this.c.get(i);
                if (!alVar3.a(alVar)) {
                    i++;
                } else {
                    alVar2 = alVar3;
                    break;
                }
            }
        }
        if (alVar2 == null) {
            alVar2 = alVar.b();
            this.c.add(alVar2);
        }
        v vVar2 = new v(alVar2);
        this.f646b.put(obj, vVar2);
        return vVar2;
    }

    private Map<Object, v> n() {
        if (a(z.USE_EQUALITY_FOR_OBJECT_ID)) {
            return new HashMap();
        }
        return new IdentityHashMap();
    }

    @Override // com.a.a.c.aa
    public final com.a.a.b.h j() {
        return this.d;
    }

    public final void a(com.a.a.b.h hVar, Object obj) {
        this.d = hVar;
        if (obj == null) {
            b(hVar);
            return;
        }
        Class<?> cls = obj.getClass();
        com.a.a.c.o<Object> a2 = a(cls, true, (com.a.a.c.c) null);
        w x = this.f206a.x();
        if (x == null) {
            if (this.f206a.a(z.WRAP_ROOT_VALUE)) {
                a(hVar, obj, a2, this.f206a.h(cls));
                return;
            }
        } else if (!x.e()) {
            a(hVar, obj, a2, x);
            return;
        }
        a(hVar, obj, a2);
    }

    public final void a(com.a.a.b.h hVar, Object obj, com.a.a.c.j jVar) {
        this.d = hVar;
        if (obj == null) {
            b(hVar);
            return;
        }
        if (!jVar.b().isAssignableFrom(obj.getClass())) {
            a(obj, jVar);
        }
        com.a.a.c.o<Object> a2 = a(jVar, true, (com.a.a.c.c) null);
        w x = this.f206a.x();
        if (x == null) {
            if (this.f206a.a(z.WRAP_ROOT_VALUE)) {
                a(hVar, obj, a2, this.f206a.e(jVar));
                return;
            }
        } else if (!x.e()) {
            a(hVar, obj, a2, x);
            return;
        }
        a(hVar, obj, a2);
    }

    public final void a(com.a.a.b.h hVar, Object obj, com.a.a.c.j jVar, com.a.a.c.o<Object> oVar) {
        w e;
        this.d = hVar;
        if (obj == null) {
            b(hVar);
            return;
        }
        if (jVar != null && !jVar.b().isAssignableFrom(obj.getClass())) {
            a(obj, jVar);
        }
        if (oVar == null) {
            oVar = a(jVar, true, (com.a.a.c.c) null);
        }
        w x = this.f206a.x();
        if (x == null) {
            if (this.f206a.a(z.WRAP_ROOT_VALUE)) {
                if (jVar == null) {
                    e = this.f206a.h(obj.getClass());
                } else {
                    e = this.f206a.e(jVar);
                }
                a(hVar, obj, oVar, e);
                return;
            }
        } else if (!x.e()) {
            a(hVar, obj, oVar, x);
            return;
        }
        a(hVar, obj, oVar);
    }

    public final void a(com.a.a.b.h hVar, Object obj, com.a.a.c.j jVar, com.a.a.c.o<Object> oVar, com.a.a.c.i.i iVar) {
        boolean z;
        this.d = hVar;
        if (obj == null) {
            b(hVar);
            return;
        }
        if (jVar != null && !jVar.b().isAssignableFrom(obj.getClass())) {
            a(obj, jVar);
        }
        if (oVar == null) {
            if (jVar != null && jVar.n()) {
                oVar = a(jVar, (com.a.a.c.c) null);
            } else {
                oVar = a(obj.getClass(), (com.a.a.c.c) null);
            }
        }
        w x = this.f206a.x();
        if (x == null) {
            boolean a2 = this.f206a.a(z.WRAP_ROOT_VALUE);
            z = a2;
            if (a2) {
                hVar.i();
                hVar.b(this.f206a.h(obj.getClass()).a(this.f206a));
            }
        } else if (x.e()) {
            z = false;
        } else {
            z = true;
            hVar.i();
            hVar.a(x.b());
        }
        try {
            oVar.a(obj, hVar, this, iVar);
            if (z) {
                hVar.j();
            }
        } catch (Exception e) {
            throw a(hVar, e);
        }
    }

    private final void a(com.a.a.b.h hVar, Object obj, com.a.a.c.o<Object> oVar, w wVar) {
        try {
            hVar.i();
            hVar.b(wVar.a(this.f206a));
            oVar.a(obj, hVar, this);
            hVar.j();
        } catch (Exception e) {
            throw a(hVar, e);
        }
    }

    private final void a(com.a.a.b.h hVar, Object obj, com.a.a.c.o<Object> oVar) {
        try {
            oVar.a(obj, hVar, this);
        } catch (Exception e) {
            throw a(hVar, e);
        }
    }

    private void b(com.a.a.b.h hVar) {
        try {
            k().a(null, hVar, this);
        } catch (Exception e) {
            throw a(hVar, e);
        }
    }

    private static IOException a(com.a.a.b.h hVar, Exception exc) {
        if (exc instanceof IOException) {
            return (IOException) exc;
        }
        String g = com.a.a.c.m.i.g(exc);
        String str = g;
        if (g == null) {
            str = "[no message for " + exc.getClass().getName() + "]";
        }
        return new com.a.a.c.l(hVar, str, exc);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/l$a.class */
    public static final class a extends l {
        public a() {
        }

        private a(aa aaVar, y yVar, r rVar) {
            super(aaVar, yVar, rVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.l
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(y yVar, r rVar) {
            return new a(this, yVar, rVar);
        }
    }
}
