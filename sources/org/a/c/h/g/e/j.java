package org.a.c.h.g.e;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/j.class */
public abstract class j implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final d f4625a;

    /* renamed from: b, reason: collision with root package name */
    private final l f4626b;
    private final org.a.c.b.d c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(d dVar) {
        this(dVar, new org.a.c.b.d(), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(d dVar, org.a.c.b.d dVar2, l lVar) {
        this.f4625a = dVar;
        this.c = dVar2;
        this.f4626b = lVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static j a(d dVar, org.a.c.b.d dVar2, l lVar) {
        return c.a(dVar, dVar2, lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.b a(org.a.c.b.j jVar) {
        if (this.c.o(jVar)) {
            return this.c.a(jVar);
        }
        if (this.f4626b != null) {
            return this.f4626b.a(jVar);
        }
        return this.f4625a.f().a(jVar);
    }

    public final void d(boolean z) {
        this.c.a(org.a.c.b.j.aV, 1, true);
    }

    public final void e(boolean z) {
        this.c.a(org.a.c.b.j.aV, 2, true);
    }

    public final org.a.c.h.g.a.p g() {
        org.a.c.b.d dVar = (org.a.c.b.d) this.c.a(org.a.c.b.j.f4369b);
        if (dVar != null) {
            return new org.a.c.h.g.a.p(dVar);
        }
        return null;
    }

    public final d h() {
        return this.f4625a;
    }

    @Override // org.a.c.h.a.c
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.c;
    }

    private String a() {
        return this.c.h(org.a.c.b.j.dG);
    }

    public final void c(String str) {
        this.c.b(org.a.c.b.j.dG, str);
    }

    public final String j() {
        String a2 = a();
        String j = this.f4626b != null ? this.f4626b.j() : null;
        String str = j;
        if (j != null) {
            if (a2 != null) {
                a2 = str + "." + a2;
            } else {
                a2 = str;
            }
        }
        return a2;
    }

    public final void d(String str) {
        this.c.b(org.a.c.b.j.dL, str);
    }

    public String toString() {
        return j() + "{type: " + getClass().getSimpleName() + " value: " + a(org.a.c.b.j.dU) + "}";
    }
}
