package org.a.b.f;

import java.io.IOException;

/* loaded from: infinitode-2.jar:org/a/b/f/t.class */
public final class t extends an {
    private long[] c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(ap apVar) {
        super(apVar);
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        q n = apVar.n();
        int w = apVar.w();
        this.c = new long[w + 1];
        for (int i = 0; i < w + 1; i++) {
            if (n.f() == 0) {
                this.c[i] = akVar.c() << 1;
            } else if (n.f() == 1) {
                this.c[i] = akVar.k();
            } else {
                throw new IOException("Error:TTF.loca unknown offset format.");
            }
        }
        this.f4283a = true;
    }

    public final long[] a() {
        return this.c;
    }
}
