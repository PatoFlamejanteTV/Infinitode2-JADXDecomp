package org.a.c.h.g.e;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/r.class */
public abstract class r extends p {
    /* JADX INFO: Access modifiers changed from: package-private */
    public r(d dVar) {
        super(dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(d dVar, org.a.c.b.d dVar2, l lVar) {
        super(dVar, dVar2, lVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final i n() {
        return new i((org.a.c.b.s) a(org.a.c.b.j.an), h().e());
    }

    public final void e(String str) {
        f().b(org.a.c.b.j.an, str);
    }

    public final int o() {
        int i = 0;
        org.a.c.b.l lVar = (org.a.c.b.l) a(org.a.c.b.j.cY);
        if (lVar != null) {
            i = lVar.c();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(org.a.c.b.b bVar) {
        if (bVar == null) {
            return "";
        }
        if (bVar instanceof org.a.c.b.s) {
            return ((org.a.c.b.s) bVar).b();
        }
        if (bVar instanceof org.a.c.b.p) {
            return ((org.a.c.b.p) bVar).p();
        }
        return "";
    }
}
