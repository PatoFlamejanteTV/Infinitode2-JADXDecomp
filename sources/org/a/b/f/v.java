package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/v.class */
public class v extends an {
    private static final org.a.a.a.a c = org.a.a.a.c.a(v.class);
    private u[] d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(ap apVar) {
        super(apVar);
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        int c2 = akVar.c();
        int i = c2;
        if (c2 != 0) {
            i = (i << 16) | akVar.c();
        }
        int i2 = 0;
        if (i == 0) {
            i2 = akVar.c();
        } else if (i == 1) {
            i2 = (int) akVar.k();
        } else {
            new StringBuilder("Skipped kerning table due to an unsupported kerning table version: ").append(i);
        }
        if (i2 > 0) {
            this.d = new u[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                u uVar = new u();
                uVar.a(akVar, i);
                this.d[i3] = uVar;
            }
        }
        this.f4283a = true;
    }
}
