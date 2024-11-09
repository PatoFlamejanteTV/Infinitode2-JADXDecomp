package com.a.a.c.c.b;

import java.util.AbstractMap;
import java.util.Map;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/v.class */
public final class v extends i<Map.Entry<Object, Object>> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.p f369a;
    private com.a.a.c.k<Object> f;
    private com.a.a.c.i.e g;

    @Override // com.a.a.c.k
    public final /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return j();
    }

    public v(com.a.a.c.j jVar, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        super(jVar);
        if (jVar.w() != 2) {
            throw new IllegalArgumentException("Missing generic type information for " + jVar);
        }
        this.f369a = pVar;
        this.f = kVar;
        this.g = eVar;
    }

    private v(v vVar, com.a.a.c.p pVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        super(vVar);
        this.f369a = pVar;
        this.f = kVar;
        this.g = eVar;
    }

    private v a(com.a.a.c.p pVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        if (this.f369a == pVar && this.f == kVar && this.g == eVar) {
            return this;
        }
        return new v(this, pVar, kVar, eVar);
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.p a2;
        com.a.a.c.k<?> b2;
        com.a.a.c.p pVar = this.f369a;
        ?? r8 = pVar;
        if (pVar == null) {
            a2 = gVar.b(this.f344b.a(0), cVar);
        } else {
            boolean z = r8 instanceof com.a.a.c.c.l;
            a2 = r8;
            if (z) {
                a2 = ((com.a.a.c.c.l) r8).a();
            }
        }
        com.a.a.c.k<?> a3 = a(gVar, cVar, (com.a.a.c.k<?>) this.f);
        com.a.a.c.j a4 = this.f344b.a(1);
        if (a3 == null) {
            b2 = gVar.a(a4, cVar);
        } else {
            b2 = gVar.b(a3, cVar, a4);
        }
        com.a.a.c.i.e eVar = this.g;
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar != null) {
            eVar2 = eVar2.a(cVar);
        }
        return a(a2, eVar2, b2);
    }

    @Override // com.a.a.c.c.b.i
    public final com.a.a.c.k<Object> g() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Map.Entry<Object, Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.START_OBJECT) {
            oVar = lVar.g();
        } else if (oVar != com.a.a.b.o.FIELD_NAME && oVar != com.a.a.b.o.END_OBJECT) {
            if (oVar == com.a.a.b.o.START_ARRAY) {
                return d(lVar, gVar);
            }
            return (Map.Entry) gVar.a(e(gVar), lVar);
        }
        if (oVar != com.a.a.b.o.FIELD_NAME) {
            if (oVar == com.a.a.b.o.END_OBJECT) {
                return (Map.Entry) gVar.a(this, "Cannot deserialize a Map.Entry out of empty JSON Object", new Object[0]);
            }
            return (Map.Entry) gVar.a(a(), lVar);
        }
        com.a.a.c.p pVar = this.f369a;
        com.a.a.c.k<Object> kVar = this.f;
        com.a.a.c.i.e eVar = this.g;
        String v = lVar.v();
        Object a2 = pVar.a(v, gVar);
        Object obj = null;
        try {
            if (lVar.g() == com.a.a.b.o.VALUE_NULL) {
                obj = kVar.a(gVar);
            } else if (eVar == null) {
                obj = kVar.a(lVar, gVar);
            } else {
                obj = kVar.a(lVar, gVar, eVar);
            }
        } catch (Exception e) {
            a(gVar, e, Map.Entry.class, v);
        }
        com.a.a.b.o g = lVar.g();
        if (g != com.a.a.b.o.END_OBJECT) {
            if (g == com.a.a.b.o.FIELD_NAME) {
                gVar.a(this, "Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '%s')", lVar.v());
                return null;
            }
            gVar.a(this, "Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + g, new Object[0]);
            return null;
        }
        return new AbstractMap.SimpleEntry(a2, obj);
    }

    private static Map.Entry<Object, Object> j() {
        throw new IllegalStateException("Cannot update Map.Entry values");
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.a(lVar, gVar);
    }
}
