package org.a.c.h.g.e;

import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/m.class */
public final class m extends e {
    public m(d dVar) {
        super(dVar);
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(d dVar, org.a.c.b.d dVar2, l lVar) {
        super(dVar, dVar2, lVar);
    }

    @Override // org.a.c.h.g.e.e
    public final List<String> b() {
        return Collections.emptyList();
    }

    @Override // org.a.c.h.g.e.e
    public final void a(List<String> list) {
        if (!list.isEmpty()) {
            throw new IllegalArgumentException("A PDPushButton shall not use the Opt entry in the field dictionary");
        }
    }

    @Override // org.a.c.h.g.e.e
    public final String a() {
        return "";
    }

    @Override // org.a.c.h.g.e.e, org.a.c.h.g.e.p
    final void c() {
    }
}
