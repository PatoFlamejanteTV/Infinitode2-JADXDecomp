package org.a.c.c;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/c/m.class */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public static final m f4405a = new m();

    /* renamed from: b, reason: collision with root package name */
    private final Map<org.a.c.b.j, l> f4406b = new HashMap();

    private m() {
        n nVar = new n();
        i iVar = new i();
        g gVar = new g();
        r rVar = new r();
        d dVar = new d();
        a aVar = new a();
        u uVar = new u();
        h hVar = new h();
        q qVar = new q();
        p pVar = new p();
        this.f4406b.put(org.a.c.b.j.bc, nVar);
        this.f4406b.put(org.a.c.b.j.bd, nVar);
        this.f4406b.put(org.a.c.b.j.ao, iVar);
        this.f4406b.put(org.a.c.b.j.ap, iVar);
        this.f4406b.put(org.a.c.b.j.O, gVar);
        this.f4406b.put(org.a.c.b.j.P, gVar);
        this.f4406b.put(org.a.c.b.j.ca, rVar);
        this.f4406b.put(org.a.c.b.j.cb, rVar);
        this.f4406b.put(org.a.c.b.j.n, dVar);
        this.f4406b.put(org.a.c.b.j.o, dVar);
        this.f4406b.put(org.a.c.b.j.p, aVar);
        this.f4406b.put(org.a.c.b.j.q, aVar);
        this.f4406b.put(org.a.c.b.j.dl, uVar);
        this.f4406b.put(org.a.c.b.j.dm, uVar);
        this.f4406b.put(org.a.c.b.j.ak, hVar);
        this.f4406b.put(org.a.c.b.j.bN, qVar);
        this.f4406b.put(org.a.c.b.j.bL, pVar);
    }

    public final l a(org.a.c.b.j jVar) {
        l lVar = this.f4406b.get(jVar);
        if (lVar == null) {
            throw new IOException("Invalid filter: " + jVar);
        }
        return lVar;
    }
}
