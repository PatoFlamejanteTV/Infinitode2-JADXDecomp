package com.a.a.c.i.a;

import com.a.a.c.c.b.w;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/r.class */
public abstract class r extends com.a.a.c.i.e implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.i.g f511a;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.j f512b;
    protected final com.a.a.c.c c;
    protected final com.a.a.c.j d;
    protected final String e;
    protected final boolean f;
    private Map<String, com.a.a.c.k<Object>> g;
    private com.a.a.c.k<Object> h;

    /* JADX INFO: Access modifiers changed from: protected */
    public r(com.a.a.c.j jVar, com.a.a.c.i.g gVar, String str, boolean z, com.a.a.c.j jVar2) {
        this.f512b = jVar;
        this.f511a = gVar;
        this.e = com.a.a.c.m.i.a(str);
        this.f = z;
        this.g = new ConcurrentHashMap(16, 0.75f, 2);
        this.d = jVar2;
        this.c = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public r(r rVar, com.a.a.c.c cVar) {
        this.f512b = rVar.f512b;
        this.f511a = rVar.f511a;
        this.e = rVar.e;
        this.f = rVar.f;
        this.g = rVar.g;
        this.d = rVar.d;
        this.h = rVar.h;
        this.c = cVar;
    }

    public final String g() {
        return this.f512b.b().getName();
    }

    @Override // com.a.a.c.i.e
    public final String b() {
        return this.e;
    }

    @Override // com.a.a.c.i.e
    public final com.a.a.c.i.g c() {
        return this.f511a;
    }

    @Override // com.a.a.c.i.e
    public final Class<?> d() {
        return com.a.a.c.m.i.a(this.d);
    }

    @Override // com.a.a.c.i.e
    public final boolean e() {
        return this.d != null;
    }

    public final com.a.a.c.j h() {
        return this.f512b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(getClass().getName());
        sb.append("; base-type:").append(this.f512b);
        sb.append("; id-resolver: ").append(this.f511a);
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.k<Object> a(com.a.a.c.g gVar, String str) {
        com.a.a.c.k<Object> kVar = this.g.get(str);
        com.a.a.c.k<Object> kVar2 = kVar;
        if (kVar == null) {
            com.a.a.c.j a2 = this.f511a.a(gVar, str);
            com.a.a.c.j jVar = a2;
            if (a2 == null) {
                com.a.a.c.k<Object> a3 = a(gVar);
                kVar2 = a3;
                if (a3 == null) {
                    com.a.a.c.j c = c(gVar, str);
                    if (c == null) {
                        return w.f370a;
                    }
                    kVar2 = gVar.a(c, this.c);
                }
            } else {
                if (this.f512b != null && this.f512b.getClass() == jVar.getClass() && !jVar.s()) {
                    try {
                        jVar = gVar.a(this.f512b, jVar.b());
                    } catch (IllegalArgumentException e) {
                        throw gVar.a(this.f512b, str, e.getMessage());
                    }
                }
                kVar2 = gVar.a(jVar, this.c);
            }
            this.g.put(str, kVar2);
        }
        return kVar2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.k<Object> a(com.a.a.c.g gVar) {
        com.a.a.c.k<Object> kVar;
        if (this.d == null) {
            if (!gVar.a(com.a.a.c.i.FAIL_ON_INVALID_SUBTYPE)) {
                return w.f370a;
            }
            return null;
        }
        if (com.a.a.c.m.i.e(this.d.b())) {
            return w.f370a;
        }
        synchronized (this.d) {
            if (this.h == null) {
                this.h = gVar.a(this.d, this.c);
            }
            kVar = this.h;
        }
        return kVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        com.a.a.c.k<Object> a2;
        if (obj == null) {
            com.a.a.c.k<Object> a3 = a(gVar);
            a2 = a3;
            if (a3 == null) {
                return gVar.a(h(), "No (native) type id found when one was expected for polymorphic type handling", new Object[0]);
            }
        } else {
            a2 = a(gVar, obj instanceof String ? (String) obj : String.valueOf(obj));
        }
        return a2.a(lVar, gVar);
    }

    private com.a.a.c.j c(com.a.a.c.g gVar, String str) {
        String str2;
        String b2 = this.f511a.b();
        if (b2 != null) {
            str2 = "known type ids = " + b2;
        } else {
            str2 = "type ids are not statically known";
        }
        if (this.c != null) {
            str2 = String.format("%s (for POJO property '%s')", str2, this.c.a());
        }
        return gVar.a(this.f512b, str, this.f511a, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.j b(com.a.a.c.g gVar, String str) {
        return gVar.a(this.f512b, this.f511a, str);
    }
}
