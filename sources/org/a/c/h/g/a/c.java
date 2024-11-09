package org.a.c.h.g.a;

/* loaded from: infinitode-2.jar:org/a/c/h/g/a/c.class */
public final class c extends a {
    public c() {
        a("GoTo");
    }

    public c(org.a.c.b.d dVar) {
        super(dVar);
    }

    public final void a(org.a.c.h.g.d.a.a aVar) {
        if (aVar instanceof org.a.c.h.g.d.a.b) {
            org.a.c.b.a f = ((org.a.c.h.g.d.a.b) aVar).f();
            if (f.b() > 0 && !(f.a(0) instanceof org.a.c.b.d)) {
                throw new IllegalArgumentException("Destination of a GoTo action must be a page dictionary object");
            }
        }
        f().a(org.a.c.b.j.am, aVar);
    }
}
