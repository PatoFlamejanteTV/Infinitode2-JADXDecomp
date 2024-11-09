package com.a.a.c.c;

import com.a.a.a.al;
import com.a.a.a.an;
import com.a.a.c.c.a.z;
import com.a.a.c.k;
import com.a.a.c.p;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/c/n.class */
public abstract class n extends com.a.a.c.g implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private transient LinkedHashMap<al.a, z> f407b;
    private List<an> c;

    public abstract n a(com.a.a.c.f fVar, com.a.a.b.l lVar, com.a.a.c.k.a.d dVar);

    public abstract n a(com.a.a.c.f fVar);

    protected n(q qVar, p pVar) {
        super(qVar, pVar);
    }

    protected n(n nVar, com.a.a.c.f fVar, com.a.a.b.l lVar, com.a.a.c.k.a.d dVar) {
        super(nVar, fVar, lVar, dVar);
    }

    protected n(n nVar, com.a.a.c.f fVar) {
        super(nVar, fVar);
    }

    @Override // com.a.a.c.g
    public final z a(Object obj, al<?> alVar, an anVar) {
        if (obj == null) {
            return null;
        }
        al.a a2 = alVar.a(obj);
        if (this.f407b == null) {
            this.f407b = new LinkedHashMap<>();
        } else {
            z zVar = this.f407b.get(a2);
            if (zVar != null) {
                return zVar;
            }
        }
        an anVar2 = null;
        if (this.c == null) {
            this.c = new ArrayList(8);
        } else {
            Iterator<an> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                an next = it.next();
                if (next.a(anVar)) {
                    anVar2 = next;
                    break;
                }
            }
        }
        if (anVar2 == null) {
            anVar2 = anVar.a();
            this.c.add(anVar2);
        }
        z a3 = a(a2);
        a3.a(anVar2);
        this.f407b.put(a2, a3);
        return a3;
    }

    private static z a(al.a aVar) {
        return new z(aVar);
    }

    @Override // com.a.a.c.g
    public final com.a.a.c.k<Object> b(com.a.a.c.f.b bVar, Object obj) {
        com.a.a.c.k<?> kVar;
        if (obj == null) {
            return null;
        }
        if (obj instanceof com.a.a.c.k) {
            kVar = (com.a.a.c.k) obj;
        } else {
            if (!(obj instanceof Class)) {
                throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + obj.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
            }
            Class cls = (Class) obj;
            if (cls != k.a.class && !com.a.a.c.m.i.e((Class<?>) cls)) {
                if (!com.a.a.c.k.class.isAssignableFrom(cls)) {
                    throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonDeserializer>");
                }
                com.a.a.c.k.a.d m = this.f487a.m();
                com.a.a.c.k<?> d = m == null ? null : m.d();
                kVar = d;
                if (d == null) {
                    kVar = (com.a.a.c.k) com.a.a.c.m.i.b(cls, this.f487a.g());
                }
            } else {
                return null;
            }
        }
        if (kVar instanceof t) {
            ((t) kVar).d(this);
        }
        return kVar;
    }

    @Override // com.a.a.c.g
    public final com.a.a.c.p c(com.a.a.c.f.b bVar, Object obj) {
        com.a.a.c.p pVar;
        if (obj == null) {
            return null;
        }
        if (obj instanceof com.a.a.c.p) {
            pVar = (com.a.a.c.p) obj;
        } else {
            if (!(obj instanceof Class)) {
                throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + obj.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
            }
            Class cls = (Class) obj;
            if (cls != p.a.class && !com.a.a.c.m.i.e((Class<?>) cls)) {
                if (!com.a.a.c.p.class.isAssignableFrom(cls)) {
                    throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<KeyDeserializer>");
                }
                com.a.a.c.k.a.d m = this.f487a.m();
                com.a.a.c.p e = m == null ? null : m.e();
                pVar = e;
                if (e == null) {
                    pVar = (com.a.a.c.p) com.a.a.c.m.i.b(cls, this.f487a.g());
                }
            } else {
                return null;
            }
        }
        if (pVar instanceof t) {
            ((t) pVar).d(this);
        }
        return pVar;
    }

    public final Object a(com.a.a.b.l lVar, com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, Object obj) {
        if (this.f487a.a()) {
            return b(lVar, jVar, kVar, obj);
        }
        if (obj == null) {
            return kVar.a(lVar, this);
        }
        return kVar.a(lVar, (com.a.a.c.g) this, (n) obj);
    }

    private Object b(com.a.a.b.l lVar, com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, Object obj) {
        Object a2;
        String b2 = this.f487a.e(jVar).b();
        if (lVar.k() != com.a.a.b.o.START_OBJECT) {
            a(jVar, com.a.a.b.o.START_OBJECT, "Current token not START_OBJECT (needed to unwrap root name %s), but %s", com.a.a.c.m.i.b(b2), lVar.k());
        }
        if (lVar.g() != com.a.a.b.o.FIELD_NAME) {
            a(jVar, com.a.a.b.o.FIELD_NAME, "Current token not FIELD_NAME (to contain expected root name %s), but %s", com.a.a.c.m.i.b(b2), lVar.k());
        }
        String v = lVar.v();
        if (!b2.equals(v)) {
            a(jVar, v, "Root name (%s) does not match expected (%s) for type %s", com.a.a.c.m.i.b(v), com.a.a.c.m.i.b(b2), com.a.a.c.m.i.b(jVar));
        }
        lVar.g();
        if (obj == null) {
            a2 = kVar.a(lVar, this);
        } else {
            a2 = kVar.a(lVar, (com.a.a.c.g) this, (n) obj);
        }
        if (lVar.g() != com.a.a.b.o.END_OBJECT) {
            a(jVar, com.a.a.b.o.END_OBJECT, "Current token not END_OBJECT (to match wrapper object with root name %s), but %s", com.a.a.c.m.i.b(b2), lVar.k());
        }
        return a2;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/n$a.class */
    public static final class a extends n {
        public a(q qVar) {
            super(qVar, (p) null);
        }

        private a(a aVar, com.a.a.c.f fVar, com.a.a.b.l lVar, com.a.a.c.k.a.d dVar) {
            super(aVar, fVar, lVar, dVar);
        }

        private a(a aVar, com.a.a.c.f fVar) {
            super(aVar, fVar);
        }

        @Override // com.a.a.c.c.n
        public final n a(com.a.a.c.f fVar, com.a.a.b.l lVar, com.a.a.c.k.a.d dVar) {
            return new a(this, fVar, lVar, dVar);
        }

        @Override // com.a.a.c.c.n
        public final n a(com.a.a.c.f fVar) {
            return new a(this, fVar);
        }
    }
}
