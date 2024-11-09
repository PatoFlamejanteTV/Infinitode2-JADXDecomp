package com.d.e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/e/ab.class */
public final class ab {

    /* renamed from: a, reason: collision with root package name */
    private List f1108a = new ArrayList();

    public final void a(com.d.c.c.a aVar) {
        this.f1108a.add(aVar);
    }

    public final void a() {
        if (this.f1108a.size() != 0) {
            this.f1108a.remove(this.f1108a.size() - 1);
        }
    }

    public final boolean b() {
        return this.f1108a.size() != 0;
    }

    public final void c() {
        this.f1108a.clear();
    }

    public final com.d.c.f.c a(com.d.c.f.c cVar) {
        com.d.c.f.c cVar2 = cVar;
        Iterator it = e().iterator();
        while (it.hasNext()) {
            cVar2 = cVar2.a((com.d.c.c.a) it.next());
        }
        return cVar2;
    }

    private List e() {
        return this.f1108a;
    }

    public final ab d() {
        ab abVar = new ab();
        abVar.f1108a.addAll(this.f1108a);
        return abVar;
    }
}
