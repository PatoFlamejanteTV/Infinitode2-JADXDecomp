package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/ar.class */
public final class ar extends an {
    private int[] c;
    private short[] d;
    private short[] e;
    private int f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ar(ap apVar) {
        super(apVar);
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        this.f = apVar.s().c();
        int w = apVar.w();
        int i = 0;
        this.c = new int[this.f];
        this.d = new short[this.f];
        for (int i2 = 0; i2 < this.f; i2++) {
            this.c[i2] = akVar.c();
            this.d[i2] = akVar.d();
            i += 4;
        }
        if (i < C()) {
            int i3 = w - this.f;
            int i4 = i3;
            if (i3 < 0) {
                i4 = w;
            }
            this.e = new short[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                if (i < C()) {
                    this.e[i5] = akVar.d();
                    i += 2;
                }
            }
        }
        this.f4283a = true;
    }

    public final int a(int i) {
        if (i < this.f) {
            return this.d[i];
        }
        return this.e[i - this.f];
    }

    public final int b(int i) {
        if (i < this.f) {
            return this.c[i];
        }
        return this.c[this.c.length - 1];
    }
}
