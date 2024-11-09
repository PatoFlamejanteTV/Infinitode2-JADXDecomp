package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/l.class */
public class l {
    private int s;

    /* renamed from: a, reason: collision with root package name */
    public int f929a;

    /* renamed from: b, reason: collision with root package name */
    public int f930b;
    private int t;
    private int u;
    private int v;
    int d;
    private int w;
    int e;
    int f;
    int g;
    int h;
    int[] c = new int[2];
    o[] i = null;
    int[] j = null;
    Object[] k = null;
    int[] l = null;
    Object[] m = null;
    int[] n = null;
    Object[] o = null;
    int[] p = null;
    Object[] q = null;
    v[] r = null;
    private r[] x = new r[64];

    static {
        "vorbis".getBytes();
    }

    public final void a() {
        this.f930b = 0;
    }

    public final void b() {
        for (int i = 0; i < this.d; i++) {
            this.i[i] = null;
        }
        this.i = null;
        for (int i2 = 0; i2 < this.w; i2++) {
            i[] iVarArr = i.f926a;
        }
        this.k = null;
        for (int i3 = 0; i3 < this.e; i3++) {
            k[] kVarArr = k.f928a;
        }
        this.m = null;
        for (int i4 = 0; i4 < this.f; i4++) {
            h[] hVarArr = h.f925a;
        }
        this.o = null;
        for (int i5 = 0; i5 < this.g; i5++) {
            j[] jVarArr = j.f927a;
        }
        this.q = null;
        for (int i6 = 0; i6 < this.h; i6++) {
            if (this.r[i6] != null) {
                this.r[i6] = null;
            }
        }
        this.r = null;
    }

    private int a(com.c.a.a aVar) {
        this.s = aVar.c(32);
        if (this.s != 0) {
            return -1;
        }
        this.f929a = aVar.c(8);
        this.f930b = aVar.c(32);
        this.t = aVar.c(32);
        this.u = aVar.c(32);
        this.v = aVar.c(32);
        this.c[0] = 1 << aVar.c(4);
        this.c[1] = 1 << aVar.c(4);
        if (this.f930b <= 0 || this.f929a <= 0 || this.c[0] < 8 || this.c[1] < this.c[0] || aVar.c(1) != 1) {
            b();
            return -1;
        }
        return 0;
    }

    private int b(com.c.a.a aVar) {
        this.h = aVar.c(8) + 1;
        if (this.r == null || this.r.length != this.h) {
            this.r = new v[this.h];
        }
        for (int i = 0; i < this.h; i++) {
            this.r[i] = new v();
            if (this.r[i].a(aVar) != 0) {
                b();
                return -1;
            }
        }
        this.e = aVar.c(6) + 1;
        if (this.l == null || this.l.length != this.e) {
            this.l = new int[this.e];
        }
        if (this.m == null || this.m.length != this.e) {
            this.m = new Object[this.e];
        }
        for (int i2 = 0; i2 < this.e; i2++) {
            this.l[i2] = aVar.c(16);
            if (this.l[i2] < 0 || this.l[i2] > 0) {
                b();
                return -1;
            }
            this.m[i2] = k.f928a[this.l[i2]].a();
            if (this.m[i2] == null) {
                b();
                return -1;
            }
        }
        this.f = aVar.c(6) + 1;
        if (this.n == null || this.n.length != this.f) {
            this.n = new int[this.f];
        }
        if (this.o == null || this.o.length != this.f) {
            this.o = new Object[this.f];
        }
        for (int i3 = 0; i3 < this.f; i3++) {
            this.n[i3] = aVar.c(16);
            if (this.n[i3] < 0 || this.n[i3] >= 2) {
                b();
                return -1;
            }
            this.o[i3] = h.f925a[this.n[i3]].a(this, aVar);
            if (this.o[i3] == null) {
                b();
                return -1;
            }
        }
        this.g = aVar.c(6) + 1;
        if (this.p == null || this.p.length != this.g) {
            this.p = new int[this.g];
        }
        if (this.q == null || this.q.length != this.g) {
            this.q = new Object[this.g];
        }
        for (int i4 = 0; i4 < this.g; i4++) {
            this.p[i4] = aVar.c(16);
            if (this.p[i4] < 0 || this.p[i4] >= 3) {
                b();
                return -1;
            }
            this.q[i4] = j.f927a[this.p[i4]].a(this, aVar);
            if (this.q[i4] == null) {
                b();
                return -1;
            }
        }
        this.w = aVar.c(6) + 1;
        if (this.j == null || this.j.length != this.w) {
            this.j = new int[this.w];
        }
        if (this.k == null || this.k.length != this.w) {
            this.k = new Object[this.w];
        }
        for (int i5 = 0; i5 < this.w; i5++) {
            this.j[i5] = aVar.c(16);
            if (this.j[i5] < 0 || this.j[i5] > 0) {
                b();
                return -1;
            }
            this.k[i5] = i.f926a[this.j[i5]].a(this, aVar);
            if (this.k[i5] == null) {
                b();
                return -1;
            }
        }
        this.d = aVar.c(6) + 1;
        if (this.i == null || this.i.length != this.d) {
            this.i = new o[this.d];
        }
        for (int i6 = 0; i6 < this.d; i6++) {
            this.i[i6] = new o();
            this.i[i6].f934a = aVar.c(1);
            this.i[i6].f935b = aVar.c(16);
            this.i[i6].c = aVar.c(16);
            this.i[i6].d = aVar.c(8);
            if (this.i[i6].f935b > 0 || this.i[i6].c > 0 || this.i[i6].d >= this.w) {
                b();
                return -1;
            }
        }
        if (aVar.c(1) != 1) {
            b();
            return -1;
        }
        return 0;
    }

    public final int a(c cVar, com.c.a.b bVar) {
        com.c.a.a aVar = new com.c.a.a();
        if (bVar != null) {
            aVar.a(bVar.f896a, bVar.f897b, bVar.c);
            byte[] bArr = new byte[6];
            int c = aVar.c(8);
            aVar.a(bArr, 6);
            if (bArr[0] != 118 || bArr[1] != 111 || bArr[2] != 114 || bArr[3] != 98 || bArr[4] != 105 || bArr[5] != 115) {
                return -1;
            }
            switch (c) {
                case 1:
                    if (bVar.d == 0 || this.f930b != 0) {
                        return -1;
                    }
                    return a(aVar);
                case 2:
                case 4:
                default:
                    return -1;
                case 3:
                    if (this.f930b == 0) {
                        return -1;
                    }
                    return cVar.a(aVar);
                case 5:
                    if (this.f930b == 0 || cVar.f911a == null) {
                        return -1;
                    }
                    return b(aVar);
            }
        }
        return -1;
    }

    public String toString() {
        return "version:" + Integer.valueOf(this.s) + ", channels:" + Integer.valueOf(this.f929a) + ", rate:" + Integer.valueOf(this.f930b) + ", bitrate:" + Integer.valueOf(this.t) + "," + Integer.valueOf(this.u) + "," + Integer.valueOf(this.v);
    }
}
