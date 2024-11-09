package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/f.class */
public final class f extends an {
    private d[] c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(ap apVar) {
        super(apVar);
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        akVar.c();
        int c = akVar.c();
        this.c = new d[c];
        for (int i = 0; i < c; i++) {
            d dVar = new d();
            dVar.a(akVar);
            this.c[i] = dVar;
        }
        for (int i2 = 0; i2 < c; i2++) {
            this.c[i2].a(this, apVar.w(), akVar);
        }
        this.f4283a = true;
    }

    public final d[] a() {
        return this.c;
    }

    public final d a(int i, int i2) {
        for (d dVar : this.c) {
            if (dVar.b() == i && dVar.a() == i2) {
                return dVar;
            }
        }
        return null;
    }
}
