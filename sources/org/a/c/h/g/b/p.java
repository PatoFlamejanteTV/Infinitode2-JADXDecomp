package org.a.c.h.g.b;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/g/b/p.class */
public final class p implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private org.a.c.b.b f4612a;

    private p() {
    }

    public p(org.a.c.b.b bVar) {
        this.f4612a = bVar;
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return this.f4612a;
    }

    public final boolean a() {
        return !(this.f4612a instanceof org.a.c.b.p);
    }

    public final boolean b() {
        return this.f4612a instanceof org.a.c.b.p;
    }

    public final q c() {
        if (!b()) {
            throw new IllegalStateException("This entry is not an appearance stream");
        }
        return new q((org.a.c.b.p) this.f4612a);
    }

    public final Map<org.a.c.b.j, q> d() {
        if (!a()) {
            throw new IllegalStateException("This entry is not an appearance subdictionary");
        }
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4612a;
        HashMap hashMap = new HashMap();
        for (org.a.c.b.j jVar : dVar.d()) {
            org.a.c.b.b a2 = dVar.a(jVar);
            if (a2 instanceof org.a.c.b.p) {
                hashMap.put(jVar, new q((org.a.c.b.p) a2));
            }
        }
        return new org.a.c.h.a.b(hashMap, dVar);
    }
}
