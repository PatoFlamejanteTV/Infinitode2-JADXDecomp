package org.a.c.h.e.a;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/b.class */
public final class b extends c {
    private final org.a.c.b.d c;
    private final c d;
    private final Map<Integer, String> e;

    public b(org.a.c.b.d dVar) {
        this.e = new HashMap();
        this.c = dVar;
        this.d = null;
        g();
    }

    public b(org.a.c.b.d dVar, boolean z, c cVar) {
        this.e = new HashMap();
        this.c = dVar;
        c a2 = this.c.o(org.a.c.b.j.u) ? c.a(this.c.b(org.a.c.b.j.u)) : null;
        if (a2 == null) {
            if (z) {
                a2 = h.c;
            } else if (cVar != null) {
                a2 = cVar;
            } else {
                throw new IllegalArgumentException("Symbolic fonts must have a built-in encoding");
            }
        }
        this.d = a2;
        this.f4518a.putAll(this.d.f4518a);
        this.f4519b.putAll(this.d.f4519b);
        g();
    }

    private void g() {
        org.a.c.b.b a2 = this.c.a(org.a.c.b.j.aE);
        if (!(a2 instanceof org.a.c.b.a)) {
            return;
        }
        org.a.c.b.a aVar = (org.a.c.b.a) a2;
        int i = -1;
        for (int i2 = 0; i2 < aVar.b(); i2++) {
            org.a.c.b.b a3 = aVar.a(i2);
            if (a3 instanceof org.a.c.b.l) {
                i = ((org.a.c.b.l) a3).c();
            } else if (a3 instanceof org.a.c.b.j) {
                org.a.c.b.j jVar = (org.a.c.b.j) a3;
                b(i, jVar.a());
                this.e.put(Integer.valueOf(i), jVar.a());
                i++;
            }
        }
    }

    public final c b() {
        return this.d;
    }

    public final Map<Integer, String> c() {
        return this.e;
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return this.c;
    }

    @Override // org.a.c.h.e.a.c
    public final String a() {
        return this.d.a() + " with differences";
    }
}
