package org.a.c.h.e.a;

import com.b.a.a.o;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/j.class */
public final class j extends c {
    public static j a(org.a.b.d.b bVar) {
        Map<Integer, String> b2 = bVar.b();
        j jVar = new j();
        for (Map.Entry<Integer, String> entry : b2.entrySet()) {
            jVar.a(entry.getKey().intValue(), entry.getValue());
        }
        return jVar;
    }

    public j() {
    }

    public j(org.a.c.h.e.l lVar) {
        for (o.d dVar : lVar.m()) {
            a(dVar.a(), dVar.b());
        }
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return null;
    }

    @Override // org.a.c.h.e.a.c
    public final String a() {
        return "built-in (Type 1)";
    }
}
