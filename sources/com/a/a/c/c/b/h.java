package com.a.a.c.c.b;

import com.a.a.a.l;
import com.a.a.c.c.a.z;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/h.class */
public class h extends i<Collection<Object>> implements com.a.a.c.c.k {
    private com.a.a.c.k<Object> f;
    private com.a.a.c.i.e g;

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.c.x f339a;
    private com.a.a.c.k<Object> h;

    public h(com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.x xVar) {
        this(jVar, kVar, eVar, xVar, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h(com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.x xVar, com.a.a.c.k<Object> kVar2, com.a.a.c.c.s sVar, Boolean bool) {
        super(jVar, sVar, bool);
        this.f = kVar;
        this.g = eVar;
        this.f339a = xVar;
        this.h = kVar2;
    }

    protected h a(com.a.a.c.k<?> kVar, com.a.a.c.k<?> kVar2, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar, Boolean bool) {
        return new h(this.f344b, kVar2, eVar, this.f339a, kVar, sVar, bool);
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return this.f == null && this.g == null && this.h == null;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Collection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public h a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        com.a.a.c.k<?> kVar = null;
        if (this.f339a != null) {
            if (this.f339a.m()) {
                com.a.a.c.c.x xVar = this.f339a;
                gVar.a();
                com.a.a.c.j p = xVar.p();
                if (p == null) {
                    gVar.a(this.f344b, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this.f344b, this.f339a.getClass().getName()));
                }
                kVar = a(gVar, p, cVar);
            } else if (this.f339a.n()) {
                com.a.a.c.c.x xVar2 = this.f339a;
                gVar.a();
                com.a.a.c.j q = xVar2.q();
                if (q == null) {
                    gVar.a(this.f344b, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this.f344b, this.f339a.getClass().getName()));
                }
                kVar = a(gVar, q, cVar);
            }
        }
        Boolean a2 = a(gVar, cVar, Collection.class, l.a.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        com.a.a.c.k<?> a3 = a(gVar, cVar, (com.a.a.c.k<?>) this.f);
        com.a.a.c.j u = this.f344b.u();
        if (a3 == null) {
            b2 = gVar.a(u, cVar);
        } else {
            b2 = gVar.b(a3, cVar, u);
        }
        com.a.a.c.i.e eVar = this.g;
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar != null) {
            eVar2 = eVar2.a(cVar);
        }
        com.a.a.c.c.s b3 = b(gVar, cVar, b2);
        if (!Objects.equals(a2, this.e) || b3 != this.c || kVar != this.h || b2 != this.f || eVar2 != this.g) {
            return a(kVar, b2, eVar2, b3, a2);
        }
        return this;
    }

    @Override // com.a.a.c.c.b.i
    public final com.a.a.c.k<Object> g() {
        return this.f;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.c.x i() {
        return this.f339a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Collection<Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.h != null) {
            return (Collection) this.f339a.a(gVar, this.h.a(lVar, gVar));
        }
        if (lVar.p()) {
            return a(lVar, gVar, d(gVar));
        }
        if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
            return a(lVar, gVar, lVar.w());
        }
        return c(lVar, gVar, d(gVar));
    }

    protected Collection<Object> d(com.a.a.c.g gVar) {
        return (Collection) this.f339a.a(gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Collection<Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<Object> collection) {
        if (lVar.p()) {
            return a(lVar, gVar, collection);
        }
        return c(lVar, gVar, collection);
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.b(lVar, gVar);
    }

    private Collection<Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar, String str) {
        com.a.a.c.b.b a2;
        Class<?> a3 = a();
        if (str.isEmpty()) {
            com.a.a.c.b.b a4 = gVar.a(b(), a3, com.a.a.c.b.f.EmptyString);
            if (a4 != null && a4 != com.a.a.c.b.b.Fail) {
                return (Collection) a(gVar, a4, a3);
            }
        } else if (h(str) && (a2 = gVar.a(b(), a3, com.a.a.c.b.b.Fail)) != com.a.a.c.b.b.Fail) {
            return (Collection) a(gVar, a2, a3);
        }
        return c(lVar, gVar, d(gVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<Object> collection) {
        Object a2;
        lVar.a(collection);
        com.a.a.c.k<Object> kVar = this.f;
        if (kVar.f() != null) {
            return d(lVar, gVar, collection);
        }
        com.a.a.c.i.e eVar = this.g;
        while (true) {
            com.a.a.b.o g = lVar.g();
            if (g != com.a.a.b.o.END_ARRAY) {
                try {
                    if (g == com.a.a.b.o.VALUE_NULL) {
                        if (!this.d) {
                            a2 = this.c.a(gVar);
                        }
                    } else if (eVar == null) {
                        a2 = kVar.a(lVar, gVar);
                    } else {
                        a2 = kVar.a(lVar, gVar, eVar);
                    }
                    collection.add(a2);
                } catch (Exception e) {
                    if (!(gVar == null || gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS))) {
                        com.a.a.c.m.i.b((Throwable) e);
                    }
                    throw com.a.a.c.l.a(e, collection, collection.size());
                }
            } else {
                return collection;
            }
        }
    }

    private Collection<Object> c(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<Object> collection) {
        Object a2;
        if (!(this.e == Boolean.TRUE || (this.e == null && gVar.a(com.a.a.c.i.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            return (Collection) gVar.a(this.f344b, lVar);
        }
        com.a.a.c.k<Object> kVar = this.f;
        com.a.a.c.i.e eVar = this.g;
        try {
            if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
                if (this.d) {
                    return collection;
                }
                a2 = this.c.a(gVar);
            } else if (eVar == null) {
                a2 = kVar.a(lVar, gVar);
            } else {
                a2 = kVar.a(lVar, gVar, eVar);
            }
            collection.add(a2);
            return collection;
        } catch (Exception e) {
            if (!gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS)) {
                com.a.a.c.m.i.b((Throwable) e);
            }
            throw com.a.a.c.l.a(e, Object.class, collection.size());
        }
    }

    private Collection<Object> d(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<Object> collection) {
        Object a2;
        if (!lVar.p()) {
            return c(lVar, gVar, collection);
        }
        lVar.a(collection);
        com.a.a.c.k<Object> kVar = this.f;
        com.a.a.c.i.e eVar = this.g;
        b bVar = new b(this.f344b.u().b(), collection);
        while (true) {
            com.a.a.b.o g = lVar.g();
            if (g != com.a.a.b.o.END_ARRAY) {
                try {
                } catch (com.a.a.c.c.w e) {
                    e.d().a(bVar.a(e));
                } catch (Exception e2) {
                    if (!(gVar == null || gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS))) {
                        com.a.a.c.m.i.b((Throwable) e2);
                    }
                    throw com.a.a.c.l.a(e2, collection, collection.size());
                }
                if (g == com.a.a.b.o.VALUE_NULL) {
                    if (!this.d) {
                        a2 = this.c.a(gVar);
                    }
                } else if (eVar == null) {
                    a2 = kVar.a(lVar, gVar);
                } else {
                    a2 = kVar.a(lVar, gVar, eVar);
                }
                bVar.a(a2);
            } else {
                return collection;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/h$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?> f342a;

        /* renamed from: b, reason: collision with root package name */
        private final Collection<Object> f343b;
        private List<a> c = new ArrayList();

        public b(Class<?> cls, Collection<Object> collection) {
            this.f342a = cls;
            this.f343b = collection;
        }

        public final void a(Object obj) {
            if (this.c.isEmpty()) {
                this.f343b.add(obj);
            } else {
                this.c.get(this.c.size() - 1).f341a.add(obj);
            }
        }

        public final z.a a(com.a.a.c.c.w wVar) {
            a aVar = new a(this, wVar, this.f342a);
            this.c.add(aVar);
            return aVar;
        }

        public final void a(Object obj, Object obj2) {
            Iterator<a> it = this.c.iterator();
            Collection collection = this.f343b;
            while (true) {
                Collection collection2 = collection;
                if (it.hasNext()) {
                    a next = it.next();
                    if (next.b(obj)) {
                        it.remove();
                        collection2.add(obj2);
                        collection2.addAll(next.f341a);
                        return;
                    }
                    collection = next.f341a;
                } else {
                    throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/h$a.class */
    public static final class a extends z.a {

        /* renamed from: b, reason: collision with root package name */
        private final b f340b;

        /* renamed from: a, reason: collision with root package name */
        public final List<Object> f341a;

        a(b bVar, com.a.a.c.c.w wVar, Class<?> cls) {
            super(wVar, cls);
            this.f341a = new ArrayList();
            this.f340b = bVar;
        }

        @Override // com.a.a.c.c.a.z.a
        public final void a(Object obj, Object obj2) {
            this.f340b.a(obj, obj2);
        }
    }
}
