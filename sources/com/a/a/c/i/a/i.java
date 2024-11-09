package com.a.a.c.i.a;

import com.a.a.a.af;
import com.a.a.c.m.ac;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/i.class */
public final class i extends r implements Serializable {
    public i(com.a.a.c.j jVar, com.a.a.c.i.g gVar, String str, boolean z, com.a.a.c.j jVar2) {
        super(jVar, gVar, str, z, jVar2);
    }

    private i(i iVar, com.a.a.c.c cVar) {
        super(iVar, cVar);
    }

    @Override // com.a.a.c.i.e
    public final com.a.a.c.i.e a(com.a.a.c.c cVar) {
        return cVar == this.c ? this : new i(this, cVar);
    }

    @Override // com.a.a.c.i.e
    public final af.a a() {
        return af.a.WRAPPER_OBJECT;
    }

    @Override // com.a.a.c.i.e
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    @Override // com.a.a.c.i.e
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    @Override // com.a.a.c.i.e
    public final Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    @Override // com.a.a.c.i.e
    public final Object d(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    private Object e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object V;
        if (lVar.T() && (V = lVar.V()) != null) {
            return a(lVar, gVar, V);
        }
        com.a.a.b.o k = lVar.k();
        if (k == com.a.a.b.o.START_OBJECT) {
            if (lVar.g() != com.a.a.b.o.FIELD_NAME) {
                gVar.a(h(), com.a.a.b.o.FIELD_NAME, "need JSON String that contains type id (for subtype of " + g() + ")", new Object[0]);
            }
        } else if (k != com.a.a.b.o.FIELD_NAME) {
            gVar.a(h(), com.a.a.b.o.START_OBJECT, "need JSON Object to contain As.WRAPPER_OBJECT type information for class " + g(), new Object[0]);
        }
        String w = lVar.w();
        com.a.a.c.k<Object> a2 = a(gVar, w);
        lVar.g();
        if (this.f && lVar.a(com.a.a.b.o.START_OBJECT)) {
            ac a3 = gVar.a(lVar);
            a3.i();
            a3.a(this.e);
            a3.b(w);
            lVar.t();
            com.a.a.b.g.k a4 = com.a.a.b.g.k.a(false, a3.c(lVar), lVar);
            lVar = a4;
            a4.g();
        }
        Object a5 = a2.a(lVar, gVar);
        if (lVar.g() != com.a.a.b.o.END_OBJECT) {
            gVar.a(h(), com.a.a.b.o.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value", new Object[0]);
        }
        return a5;
    }
}
