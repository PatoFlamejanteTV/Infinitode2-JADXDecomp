package com.a.a.c.i.a;

import com.a.a.a.af;
import com.a.a.c.m.ac;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/g.class */
public class g extends a {
    private af.a g;
    private String h;

    public g(com.a.a.c.j jVar, com.a.a.c.i.g gVar, String str, boolean z, com.a.a.c.j jVar2, af.a aVar) {
        super(jVar, gVar, str, z, jVar2);
        this.h = this.c == null ? String.format("missing type id property '%s'", this.e) : String.format("missing type id property '%s' (for POJO property '%s')", this.e, this.c.a());
        this.g = aVar;
    }

    public g(g gVar, com.a.a.c.c cVar) {
        super(gVar, cVar);
        this.h = this.c == null ? String.format("missing type id property '%s'", this.e) : String.format("missing type id property '%s' (for POJO property '%s')", this.e, this.c.a());
        this.g = gVar.g;
    }

    @Override // com.a.a.c.i.a.a, com.a.a.c.i.e
    public com.a.a.c.i.e a(com.a.a.c.c cVar) {
        return cVar == this.c ? this : new g(this, cVar);
    }

    @Override // com.a.a.c.i.a.a, com.a.a.c.i.e
    public final af.a a() {
        return this.g;
    }

    @Override // com.a.a.c.i.a.a, com.a.a.c.i.e
    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String R;
        Object V;
        if (lVar.T() && (V = lVar.V()) != null) {
            return a(lVar, gVar, V);
        }
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.START_OBJECT) {
            oVar = lVar.g();
        } else if (oVar != com.a.a.b.o.FIELD_NAME) {
            return b(lVar, gVar, null, this.h);
        }
        ac acVar = null;
        boolean a2 = gVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        while (oVar == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            if ((v.equals(this.e) || (a2 && v.equalsIgnoreCase(this.e))) && (R = lVar.R()) != null) {
                return a(lVar, gVar, acVar, R);
            }
            if (acVar == null) {
                acVar = gVar.a(lVar);
            }
            acVar.a(v);
            acVar.b(lVar);
            oVar = lVar.g();
        }
        return b(lVar, gVar, acVar, this.h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, ac acVar, String str) {
        com.a.a.c.k<Object> a2 = a(gVar, str);
        if (this.f) {
            if (acVar == null) {
                acVar = gVar.a(lVar);
            }
            acVar.a(lVar.v());
            acVar.b(str);
        }
        if (acVar != null) {
            lVar.t();
            lVar = com.a.a.b.g.k.a(false, acVar.c(lVar), lVar);
        }
        if (lVar.k() != com.a.a.b.o.END_OBJECT) {
            lVar.g();
        }
        return a2.a(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, ac acVar, String str) {
        if (!e()) {
            Object a2 = com.a.a.c.i.e.a(lVar, gVar, this.f512b);
            if (a2 != null) {
                return a2;
            }
            if (lVar.p()) {
                return super.d(lVar, gVar);
            }
            if (lVar.a(com.a.a.b.o.VALUE_STRING) && gVar.a(com.a.a.c.i.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && lVar.w().trim().isEmpty()) {
                return null;
            }
        }
        com.a.a.c.k<Object> a3 = a(gVar);
        com.a.a.c.k<Object> kVar = a3;
        if (a3 == null) {
            com.a.a.c.j b2 = b(gVar, str);
            if (b2 == null) {
                return null;
            }
            kVar = gVar.a(b2, this.c);
        }
        if (acVar != null) {
            acVar.j();
            com.a.a.b.l c = acVar.c(lVar);
            lVar = c;
            c.g();
        }
        return kVar.a(lVar, gVar);
    }

    @Override // com.a.a.c.i.a.a, com.a.a.c.i.e
    public final Object d(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.START_ARRAY)) {
            return super.b(lVar, gVar);
        }
        return a(lVar, gVar);
    }
}
