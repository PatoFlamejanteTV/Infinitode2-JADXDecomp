package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/j.class */
public class j extends i {

    /* renamed from: a, reason: collision with root package name */
    private int[] f4300a;

    /* renamed from: b, reason: collision with root package name */
    private byte[] f4301b;
    private short[] c;
    private short[] d;
    private final int e;

    static {
        org.a.a.a.c.a(j.class);
    }

    public j(short s, ak akVar, short s2) {
        super(s);
        if (s == 0) {
            this.e = 0;
            return;
        }
        this.f4300a = akVar.c(s);
        int i = this.f4300a[s - 1];
        if (s == 1 && i == 65535) {
            this.e = 0;
            return;
        }
        this.e = i + 1;
        this.f4301b = new byte[this.e];
        this.c = new short[this.e];
        this.d = new short[this.e];
        a(akVar, akVar.c());
        a(this.e, akVar);
        a(this.e, akVar, s2);
    }

    @Override // org.a.b.f.l
    public final int a(int i) {
        return this.f4300a[i];
    }

    @Override // org.a.b.f.l
    public final byte b(int i) {
        return this.f4301b[i];
    }

    @Override // org.a.b.f.l
    public final short c(int i) {
        return this.c[i];
    }

    @Override // org.a.b.f.l
    public final short d(int i) {
        return this.d[i];
    }

    @Override // org.a.b.f.l
    public final boolean b() {
        return false;
    }

    @Override // org.a.b.f.l
    public final int c() {
        return this.e;
    }

    private void a(int i, ak akVar, short s) {
        short s2 = s;
        short s3 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            if ((this.f4301b[i2] & 16) != 0) {
                if ((this.f4301b[i2] & 2) != 0) {
                    s2 = (short) (s2 + ((short) akVar.j()));
                }
            } else if ((this.f4301b[i2] & 2) != 0) {
                s2 = (short) (s2 + ((short) (-((short) akVar.j()))));
            } else {
                s2 = (short) (s2 + akVar.d());
            }
            this.c[i2] = s2;
        }
        for (int i3 = 0; i3 < i; i3++) {
            if ((this.f4301b[i3] & 32) != 0) {
                if ((this.f4301b[i3] & 4) != 0) {
                    s3 = (short) (s3 + ((short) akVar.j()));
                }
            } else if ((this.f4301b[i3] & 4) != 0) {
                s3 = (short) (s3 + ((short) (-((short) akVar.j()))));
            } else {
                s3 = (short) (s3 + akVar.d());
            }
            this.d[i3] = s3;
        }
    }

    private void a(int i, ak akVar) {
        int i2 = 0;
        while (i2 < i) {
            this.f4301b[i2] = (byte) akVar.j();
            if ((this.f4301b[i2] & 8) != 0) {
                int j = akVar.j();
                for (int i3 = 1; i3 <= j && i2 + i3 < this.f4301b.length; i3++) {
                    this.f4301b[i2 + i3] = this.f4301b[i2];
                }
                i2 += j;
            }
            i2++;
        }
    }
}
