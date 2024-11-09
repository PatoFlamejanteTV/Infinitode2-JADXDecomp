package org.a.c.h.g.e;

import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/h.class */
public final class h extends g {
    public h(d dVar) {
        super(dVar);
        b(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(d dVar, org.a.c.b.d dVar2, l lVar) {
        super(dVar, dVar2, lVar);
    }

    public final void c(boolean z) {
        f().a(org.a.c.b.j.aV, 262144, true);
    }

    @Override // org.a.c.h.g.e.g, org.a.c.h.g.e.p
    final void c() {
        a aVar = new a(this);
        List<String> e = e();
        if (!e.isEmpty()) {
            aVar.a(e.get(0));
        } else {
            aVar.a("");
        }
    }
}
