package com.a.a.c.c.b;

import com.a.a.a.l;
import java.util.Collection;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/al.class */
public final class al extends i<Collection<String>> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.k<String> f324a;
    private com.a.a.c.c.x f;
    private com.a.a.c.k<Object> g;

    public al(com.a.a.c.j jVar, com.a.a.c.k<?> kVar, com.a.a.c.c.x xVar) {
        this(jVar, xVar, null, kVar, kVar, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private al(com.a.a.c.j jVar, com.a.a.c.c.x xVar, com.a.a.c.k<?> kVar, com.a.a.c.k<?> kVar2, com.a.a.c.c.s sVar, Boolean bool) {
        super(jVar, sVar, bool);
        this.f324a = kVar2;
        this.f = xVar;
        this.g = kVar;
    }

    private al a(com.a.a.c.k<?> kVar, com.a.a.c.k<?> kVar2, com.a.a.c.c.s sVar, Boolean bool) {
        if (Objects.equals(this.e, bool) && this.c == sVar && this.f324a == kVar2 && this.g == kVar) {
            return this;
        }
        return new al(this.f344b, this.f, kVar, kVar2, sVar, bool);
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return this.f324a == null && this.g == null;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Collection;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        com.a.a.c.k<?> kVar = null;
        if (this.f != null) {
            if (this.f.t() != null) {
                com.a.a.c.c.x xVar = this.f;
                gVar.a();
                kVar = a(gVar, xVar.q(), cVar);
            } else if (this.f.s() != null) {
                com.a.a.c.c.x xVar2 = this.f;
                gVar.a();
                kVar = a(gVar, xVar2.p(), cVar);
            }
        }
        com.a.a.c.k<String> kVar2 = this.f324a;
        com.a.a.c.j u = this.f344b.u();
        if (kVar2 == null) {
            com.a.a.c.k<?> a2 = a(gVar, cVar, kVar2);
            b2 = a2;
            if (a2 == null) {
                b2 = gVar.a(u, cVar);
            }
        } else {
            b2 = gVar.b(kVar2, cVar, u);
        }
        Boolean a3 = a(gVar, cVar, Collection.class, l.a.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        com.a.a.c.c.s b3 = b(gVar, cVar, b2);
        if (a(b2)) {
            b2 = null;
        }
        return a(kVar, b2, b3, a3);
    }

    @Override // com.a.a.c.c.b.i
    public final com.a.a.c.k<Object> g() {
        return this.f324a;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.c.x i() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Collection<String> a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.g != null) {
            return (Collection) this.f.a(gVar, this.g.a(lVar, gVar));
        }
        return a(lVar, gVar, (Collection<String>) this.f.a(gVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.a.a.c.k<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.util.Collection, java.lang.Object, java.util.Collection<java.lang.String>] */
    @Override // com.a.a.c.k
    public Collection<String> a(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<String> collection) {
        String a2;
        if (!lVar.p()) {
            return b(lVar, gVar, (Collection<String>) collection);
        }
        ?? r0 = this.f324a;
        if (r0 != 0) {
            return a(lVar, gVar, (Collection<String>) collection, this.f324a);
        }
        while (true) {
            try {
                r0 = lVar.i();
                if (r0 != 0) {
                    r0 = collection.add(r0);
                } else {
                    com.a.a.b.o k = lVar.k();
                    if (k != com.a.a.b.o.END_ARRAY) {
                        if (k == com.a.a.b.o.VALUE_NULL) {
                            r0 = this.d;
                            if (r0 == 0) {
                                a2 = (String) this.c.a(gVar);
                            }
                        } else {
                            a2 = a(lVar, gVar, this.c);
                        }
                        r0 = collection.add(a2);
                    } else {
                        return collection;
                    }
                }
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, (Object) collection, collection.size());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v8 */
    private Collection<String> a(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<String> collection, com.a.a.c.k<String> kVar) {
        ?? r0;
        String a2;
        while (true) {
            try {
                r0 = lVar.i();
                if (r0 == 0) {
                    com.a.a.b.o k = lVar.k();
                    if (k != com.a.a.b.o.END_ARRAY) {
                        if (k == com.a.a.b.o.VALUE_NULL) {
                            r0 = this.d;
                            if (r0 == 0) {
                                a2 = (String) this.c.a(gVar);
                            }
                        } else {
                            a2 = kVar.a(lVar, gVar);
                        }
                    } else {
                        return collection;
                    }
                } else {
                    a2 = kVar.a(lVar, gVar);
                }
                r0 = collection.add(a2);
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, collection, collection.size());
            }
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.b(lVar, gVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v57 */
    /* JADX WARN: Type inference failed for: r0v58 */
    private final Collection<String> b(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<String> collection) {
        String str;
        com.a.a.c.b.b a2;
        if (!(this.e == Boolean.TRUE || (this.e == null && gVar.a(com.a.a.c.i.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
                return m(lVar, gVar);
            }
            return (Collection) gVar.a(this.f344b, lVar);
        }
        com.a.a.c.k<String> kVar = this.f324a;
        if (lVar.k() == com.a.a.b.o.VALUE_NULL) {
            if (this.d) {
                return collection;
            }
            str = (String) this.c.a(gVar);
        } else {
            if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
                String w = lVar.w();
                if (!w.isEmpty()) {
                    if (h(w) && (a2 = gVar.a(b(), a(), com.a.a.c.b.b.Fail)) != com.a.a.c.b.b.Fail) {
                        return (Collection) a(gVar, a2, a());
                    }
                } else {
                    com.a.a.c.b.b a3 = gVar.a(b(), a(), com.a.a.c.b.f.EmptyString);
                    if (a3 != com.a.a.c.b.b.Fail) {
                        return (Collection) a(gVar, a3, a());
                    }
                }
            }
            ?? r0 = kVar;
            try {
                r0 = r0 == 0 ? a(lVar, gVar, this.c) : kVar.a(lVar, gVar);
                str = r0;
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, collection, collection.size());
            }
        }
        collection.add(str);
        return collection;
    }
}
