package com.a.a.c.c;

import com.a.a.a.al;
import com.a.a.a.am;
import com.a.a.a.an;
import com.a.a.c.c.a.z;
import com.a.a.c.c.x;
import com.a.a.c.f.ad;
import java.io.Serializable;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a.class */
public final class a extends com.a.a.c.k<Object> implements k, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.j f246a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.c.a.s f247b;
    private Map<String, v> c;
    private transient Map<String, v> d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public a(g gVar, com.a.a.c.b bVar, Map<String, v> map, Map<String, v> map2) {
        this.f246a = bVar.a();
        this.f247b = gVar.e();
        this.c = map;
        this.d = map2;
        Class<?> b2 = this.f246a.b();
        this.e = b2.isAssignableFrom(String.class);
        this.f = b2 == Boolean.TYPE || b2.isAssignableFrom(Boolean.class);
        this.g = b2 == Integer.TYPE || b2.isAssignableFrom(Integer.class);
        this.h = b2 == Double.TYPE || b2.isAssignableFrom(Double.class);
    }

    private a(com.a.a.c.b bVar) {
        this.f246a = bVar.a();
        this.f247b = null;
        this.c = null;
        Class<?> b2 = this.f246a.b();
        this.e = b2.isAssignableFrom(String.class);
        this.f = b2 == Boolean.TYPE || b2.isAssignableFrom(Boolean.class);
        this.g = b2 == Integer.TYPE || b2.isAssignableFrom(Integer.class);
        this.h = b2 == Double.TYPE || b2.isAssignableFrom(Double.class);
    }

    private a(a aVar, com.a.a.c.c.a.s sVar, Map<String, v> map) {
        this.f246a = aVar.f246a;
        this.c = aVar.c;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.f247b = sVar;
        this.d = map;
    }

    public static a a(com.a.a.c.b bVar) {
        return new a(bVar);
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.f.j e;
        ad a2;
        com.a.a.c.j jVar;
        al<?> a3;
        com.a.a.c.a f = gVar.f();
        if (cVar != null && f != null && (e = cVar.e()) != null && (a2 = f.a((com.a.a.c.f.b) e)) != null) {
            v vVar = null;
            an b2 = gVar.b((com.a.a.c.f.b) e, a2);
            ad a4 = f.a(e, a2);
            Class<? extends al<?>> d = a4.d();
            if (d == am.c.class) {
                com.a.a.c.w b3 = a4.b();
                v vVar2 = this.d == null ? null : this.d.get(b3.b());
                vVar = vVar2;
                if (vVar2 == null) {
                    gVar.a(this.f246a, String.format("Invalid Object Id definition for %s: cannot find property with name %s", com.a.a.c.m.i.g(a()), com.a.a.c.m.i.a(b3)));
                }
                jVar = vVar.c();
                a3 = new com.a.a.c.c.a.w(a4.c());
            } else {
                b2 = gVar.b((com.a.a.c.f.b) e, a4);
                com.a.a.c.j b4 = gVar.b(d);
                gVar.b();
                jVar = com.a.a.c.l.o.c(b4, al.class)[0];
                a3 = gVar.a((com.a.a.c.f.b) e, a4);
            }
            return new a(this, com.a.a.c.c.a.s.a(jVar, a4.b(), a3, gVar.b(jVar), vVar, b2), null);
        }
        if (this.d == null) {
            return this;
        }
        return new a(this, this.f247b, null);
    }

    @Override // com.a.a.c.k
    public final Class<?> a() {
        return this.f246a.b();
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.POJO;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return null;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.c.a.s f() {
        return this.f247b;
    }

    @Override // com.a.a.c.k
    public final v a(String str) {
        if (this.c == null) {
            return null;
        }
        return this.c.get(str);
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        if (this.f247b != null) {
            com.a.a.b.o k = lVar.k();
            com.a.a.b.o oVar = k;
            if (k != null) {
                if (oVar.g()) {
                    return b(lVar, gVar);
                }
                if (oVar == com.a.a.b.o.START_OBJECT) {
                    oVar = lVar.g();
                }
                if (oVar == com.a.a.b.o.FIELD_NAME && this.f247b.c() && this.f247b.a(lVar.v(), lVar)) {
                    return b(lVar, gVar);
                }
            }
        }
        Object a2 = a(lVar);
        if (a2 != null) {
            return a2;
        }
        return eVar.a(lVar, gVar);
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return gVar.a(this.f246a.b(), new x.a(this.f246a), lVar, "abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information", new Object[0]);
    }

    private Object a(com.a.a.b.l lVar) {
        switch (lVar.l()) {
            case 6:
                if (this.e) {
                    return lVar.w();
                }
                return null;
            case 7:
                if (this.g) {
                    return Integer.valueOf(lVar.G());
                }
                return null;
            case 8:
                if (this.h) {
                    return Double.valueOf(lVar.K());
                }
                return null;
            case 9:
                if (this.f) {
                    return Boolean.TRUE;
                }
                return null;
            case 10:
                if (this.f) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }

    private Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2 = this.f247b.a(lVar, gVar);
        z a3 = gVar.a(a2, this.f247b.f281b, this.f247b.c);
        Object b2 = a3.b();
        if (b2 == null) {
            throw new w(lVar, "Could not resolve Object Id [" + a2 + "] -- unresolved forward-reference?", lVar.e(), a3);
        }
        return b2;
    }
}
