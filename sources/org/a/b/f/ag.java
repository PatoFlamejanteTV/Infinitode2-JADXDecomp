package org.a.b.f;

/* loaded from: infinitode-2.jar:org/a/b/f/ag.class */
public class ag extends an {
    private static final org.a.a.a.a c = org.a.a.a.c.a(ag.class);
    private float d;
    private float e;
    private short f;
    private short g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;
    private String[] m;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ag(ap apVar) {
        super(apVar);
        this.m = null;
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        this.d = akVar.h();
        this.e = akVar.h();
        this.f = akVar.d();
        this.g = akVar.d();
        this.h = akVar.k();
        this.i = akVar.k();
        this.j = akVar.k();
        this.k = akVar.k();
        this.l = akVar.k();
        if (this.d == 1.0f) {
            this.m = new String[258];
            System.arraycopy(at.f4289a, 0, this.m, 0, 258);
        } else if (this.d == 2.0f) {
            int c2 = akVar.c();
            int[] iArr = new int[c2];
            this.m = new String[c2];
            int i = Integer.MIN_VALUE;
            for (int i2 = 0; i2 < c2; i2++) {
                int c3 = akVar.c();
                iArr[i2] = c3;
                if (c3 <= 32767) {
                    i = Math.max(i, c3);
                }
            }
            String[] strArr = null;
            if (i >= 258) {
                strArr = new String[(i - 258) + 1];
                for (int i3 = 0; i3 < (i - 258) + 1; i3++) {
                    strArr[i3] = akVar.a(akVar.j());
                }
            }
            for (int i4 = 0; i4 < c2; i4++) {
                int i5 = iArr[i4];
                if (i5 < 258) {
                    this.m[i4] = at.f4289a[i5];
                } else if (i5 >= 258 && i5 <= 32767) {
                    this.m[i4] = strArr[i5 - 258];
                } else {
                    this.m[i4] = ".undefined";
                }
            }
        } else if (this.d == 2.5f) {
            int[] iArr2 = new int[apVar.w()];
            for (int i6 = 0; i6 < iArr2.length; i6++) {
                int i7 = i6;
                iArr2[i7] = i7 + 1 + akVar.i();
            }
            this.m = new String[iArr2.length];
            for (int i8 = 0; i8 < this.m.length; i8++) {
                String str = at.f4289a[iArr2[i8]];
                if (str != null) {
                    this.m[i8] = str;
                }
            }
        } else if (this.d == 3.0f) {
            new StringBuilder("No PostScript name information is provided for the font ").append(this.f4284b.b());
        }
        this.f4283a = true;
    }

    public final long a() {
        return this.h;
    }

    public final float b() {
        return this.e;
    }

    public final long c() {
        return this.l;
    }

    public final long d() {
        return this.j;
    }

    public final long e() {
        return this.k;
    }

    public final long f() {
        return this.i;
    }

    public final short g() {
        return this.f;
    }

    public final short h() {
        return this.g;
    }

    public final String[] i() {
        return this.m;
    }

    public final String a(int i) {
        if (i < 0 || this.m == null || i >= this.m.length) {
            return null;
        }
        return this.m[i];
    }
}
