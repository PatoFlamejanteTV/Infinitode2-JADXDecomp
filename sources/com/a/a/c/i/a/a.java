package com.a.a.c.i.a;

import com.a.a.a.af;
import com.a.a.c.m.ac;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/a.class */
public class a extends r implements Serializable {
    public a(com.a.a.c.j jVar, com.a.a.c.i.g gVar, String str, boolean z, com.a.a.c.j jVar2) {
        super(jVar, gVar, str, z, jVar2);
    }

    public a(a aVar, com.a.a.c.c cVar) {
        super(aVar, cVar);
    }

    @Override // com.a.a.c.i.e
    public com.a.a.c.i.e a(com.a.a.c.c cVar) {
        return cVar == this.c ? this : new a(this, cVar);
    }

    @Override // com.a.a.c.i.e
    public af.a a() {
        return af.a.WRAPPER_ARRAY;
    }

    @Override // com.a.a.c.i.e
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    @Override // com.a.a.c.i.e
    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    @Override // com.a.a.c.i.e
    public final Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    @Override // com.a.a.c.i.e
    public Object d(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return e(lVar, gVar);
    }

    private Object e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object V;
        if (lVar.T() && (V = lVar.V()) != null) {
            return a(lVar, gVar, V);
        }
        boolean p = lVar.p();
        String f = f(lVar, gVar);
        com.a.a.c.k<Object> a2 = a(gVar, f);
        if (this.f && !f() && lVar.a(com.a.a.b.o.START_OBJECT)) {
            ac a3 = gVar.a(lVar);
            a3.i();
            a3.a(this.e);
            a3.b(f);
            lVar.t();
            com.a.a.b.g.k a4 = com.a.a.b.g.k.a(false, a3.c(lVar), lVar);
            lVar = a4;
            a4.g();
        }
        if (p && lVar.k() == com.a.a.b.o.END_ARRAY) {
            return a2.a(gVar);
        }
        Object a5 = a2.a(lVar, gVar);
        if (p && lVar.g() != com.a.a.b.o.END_ARRAY) {
            gVar.a(h(), com.a.a.b.o.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value", new Object[0]);
        }
        return a5;
    }

    private String f(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (!lVar.p()) {
            if (this.d != null) {
                return this.f511a.a();
            }
            gVar.a(h(), com.a.a.b.o.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + g(), new Object[0]);
            return null;
        }
        if (lVar.g() == com.a.a.b.o.VALUE_STRING) {
            String w = lVar.w();
            lVar.g();
            return w;
        }
        gVar.a(h(), com.a.a.b.o.VALUE_STRING, "need JSON String that contains type id (for subtype of %s)", g());
        return null;
    }

    protected boolean f() {
        return false;
    }
}
