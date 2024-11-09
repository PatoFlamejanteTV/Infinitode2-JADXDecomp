package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/p.class */
public final class p extends an {
    private k[] c;
    private ak d;
    private t e;
    private int f;
    private int g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(ap apVar) {
        super(apVar);
        this.g = 0;
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        this.e = apVar.q();
        this.f = apVar.w();
        if (this.f < 5000) {
            this.c = new k[this.f];
        }
        this.d = akVar;
        this.f4283a = true;
    }

    public final k a(int i) {
        if (i < 0 || i >= this.f) {
            return null;
        }
        if (this.c != null && this.c[i] != null) {
            return this.c[i];
        }
        synchronized (this.d) {
            long[] a2 = this.e.a();
            if (a2[i] == a2[i + 1]) {
                return null;
            }
            long e = this.d.e();
            this.d.a(D() + a2[i]);
            k b2 = b(i);
            this.d.a(e);
            if (this.c != null && this.c[i] == null && this.g < 100) {
                this.c[i] = b2;
                this.g++;
            }
            return b2;
        }
    }

    private k b(int i) {
        k kVar = new k();
        s p = this.f4284b.p();
        kVar.a(this, this.d, p == null ? 0 : p.b(i));
        if (kVar.a().b()) {
            kVar.a().a();
        }
        return kVar;
    }
}
