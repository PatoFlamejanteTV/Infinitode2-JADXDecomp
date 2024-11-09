package org.a.c.h.g.d.b;

import org.a.c.b.d;
import org.a.c.b.j;
import org.a.c.h.a.e;

/* loaded from: infinitode-2.jar:org/a/c/h/g/d/b/c.class */
public abstract class c extends e {
    public c() {
    }

    public c(d dVar) {
        super(dVar);
    }

    private c c() {
        org.a.c.b.b a2 = f().a(j.cN);
        if (a2 instanceof d) {
            d dVar = (d) a2;
            if (j.cE.equals(dVar.b(j.dN))) {
                return new a(dVar);
            }
            return new b(dVar);
        }
        return null;
    }

    final void c(c cVar) {
        f().a(j.cN, cVar);
    }

    public final void a(b bVar) {
        b(bVar);
        c(bVar);
        d(bVar);
    }

    private static void b(b bVar) {
        if (bVar.d() != null || bVar.c() != null) {
            throw new IllegalArgumentException("A single node with no siblings is required");
        }
    }

    private void c(b bVar) {
        bVar.c(this);
        if (!d()) {
            a((c) bVar);
        } else {
            b h = h();
            h.b((c) bVar);
            bVar.a((c) h);
        }
        b((c) bVar);
    }

    private static void d(b bVar) {
        int i = 1;
        if (bVar.b()) {
            i = 1 + bVar.e();
        }
        bVar.a(i);
    }

    private boolean d() {
        return g() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final b a(j jVar) {
        org.a.c.b.b a2 = f().a(jVar);
        if (a2 instanceof d) {
            return new b((d) a2);
        }
        return null;
    }

    private b g() {
        return a(j.aZ);
    }

    private void a(c cVar) {
        f().a(j.aZ, cVar);
    }

    private b h() {
        return a(j.bV);
    }

    private void b(c cVar) {
        f().a(j.bV, cVar);
    }

    public final int e() {
        return f().b(j.ag, 0);
    }

    private void b(int i) {
        f().a(j.ag, i);
    }

    public boolean b() {
        return e() > 0;
    }

    final void a(int i) {
        c c = c();
        if (c != null) {
            if (c.b()) {
                c.b(c.e() + i);
                c.a(i);
            } else {
                c.b(c.e() - i);
            }
        }
    }
}
