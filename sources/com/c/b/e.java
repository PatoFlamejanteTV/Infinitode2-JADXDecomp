package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/e.class */
public class e {

    /* renamed from: a, reason: collision with root package name */
    l f914a;

    /* renamed from: b, reason: collision with root package name */
    int f915b;
    private float[][] g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    b[] e;
    Object[] f;
    Object[][] d = new Object[2];
    float[][][][][] c = new float[2][][];

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object[][]] */
    /* JADX WARN: Type inference failed for: r1v3, types: [float[][][][], float[][][][][]] */
    public e() {
        this.c[0] = new float[2][];
        this.c[0][0] = new float[2];
        this.c[0][1] = new float[2];
        this.c[0][0][0] = new float[2];
        this.c[0][0][1] = new float[2];
        this.c[0][1][0] = new float[2];
        this.c[0][1][1] = new float[2];
        this.c[1] = new float[2][];
        this.c[1][0] = new float[2];
        this.c[1][1] = new float[2];
        this.c[1][0][0] = new float[2];
        this.c[1][0][1] = new float[2];
        this.c[1][1][0] = new float[2];
        this.c[1][1][1] = new float[2];
    }

    private static float[] a(int i, int i2, int i3, int i4) {
        float[] fArr = new float[i2];
        switch (i) {
            case 0:
                int i5 = (i2 / 4) - (i3 / 2);
                int i6 = (i2 - (i2 / 4)) - (i4 / 2);
                for (int i7 = 0; i7 < i3; i7++) {
                    float sin = (float) Math.sin((float) ((((i7 + 0.5d) / i3) * 3.1415927410125732d) / 2.0d));
                    fArr[i7 + i5] = (float) Math.sin((float) (sin * sin * 1.5707963705062866d));
                }
                for (int i8 = i5 + i3; i8 < i6; i8++) {
                    fArr[i8] = 1.0f;
                }
                for (int i9 = 0; i9 < i4; i9++) {
                    float sin2 = (float) Math.sin((float) (((((i4 - i9) - 0.5d) / i4) * 3.1415927410125732d) / 2.0d));
                    fArr[i9 + i6] = (float) Math.sin((float) (sin2 * sin2 * 1.5707963705062866d));
                }
                return fArr;
            default:
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v52, types: [float[], float[][]] */
    private int b(l lVar) {
        this.f914a = lVar;
        this.f915b = o.b(lVar.d);
        this.d[0] = new Object[1];
        this.d[1] = new Object[1];
        this.d[0][0] = new q();
        this.d[1][0] = new q();
        ((q) this.d[0][0]).a(lVar.c[0]);
        ((q) this.d[1][0]).a(lVar.c[1]);
        this.c[0][0][0] = new float[1];
        this.c[0][0][1] = this.c[0][0][0];
        this.c[0][1][0] = this.c[0][0][0];
        this.c[0][1][1] = this.c[0][0][0];
        this.c[1][0][0] = new float[1];
        this.c[1][0][1] = new float[1];
        this.c[1][1][0] = new float[1];
        this.c[1][1][1] = new float[1];
        for (int i = 0; i <= 0; i++) {
            this.c[0][0][0][0] = a(0, lVar.c[0], lVar.c[0] / 2, lVar.c[0] / 2);
            this.c[1][0][0][0] = a(0, lVar.c[1], lVar.c[0] / 2, lVar.c[0] / 2);
            this.c[1][0][1][0] = a(0, lVar.c[1], lVar.c[0] / 2, lVar.c[1] / 2);
            this.c[1][1][0][0] = a(0, lVar.c[1], lVar.c[1] / 2, lVar.c[0] / 2);
            this.c[1][1][1][0] = a(0, lVar.c[1], lVar.c[1] / 2, lVar.c[1] / 2);
        }
        this.e = new b[lVar.h];
        for (int i2 = 0; i2 < lVar.h; i2++) {
            this.e[i2] = new b();
            this.e[i2].a(lVar.r[i2]);
        }
        this.h = 8192;
        this.g = new float[lVar.f929a];
        for (int i3 = 0; i3 < lVar.f929a; i3++) {
            this.g[i3] = new float[this.h];
        }
        this.k = 0;
        this.l = 0;
        this.m = lVar.c[1] / 2;
        this.i = this.m;
        this.f = new Object[lVar.d];
        for (int i4 = 0; i4 < lVar.d; i4++) {
            int i5 = lVar.i[i4].d;
            this.f[i4] = i.f926a[lVar.j[i5]].a(this, lVar.i[i4], lVar.k[i5]);
        }
        return 0;
    }

    public final int a(l lVar) {
        b(lVar);
        this.j = this.m;
        this.m -= (lVar.c[this.l] / 4) + (lVar.c[this.k] / 4);
        this.n = -1L;
        this.o = -1L;
        return 0;
    }

    public final int a(a aVar) {
        if (this.m > this.f914a.c[1] / 2 && this.j > 8192) {
            int i = this.m - (this.f914a.c[1] / 2);
            int i2 = this.j < i ? this.j : i;
            this.i -= i2;
            this.m -= i2;
            this.j -= i2;
            if (i2 != 0) {
                for (int i3 = 0; i3 < this.f914a.f929a; i3++) {
                    float[] fArr = this.g[i3];
                    System.arraycopy(fArr, i2, fArr, 0, this.i);
                }
            }
        }
        this.k = this.l;
        this.l = aVar.d;
        this.p = this.p;
        this.q = this.q;
        this.r = this.r;
        this.s = this.s;
        if (this.o + 1 != aVar.j) {
            this.n = -1L;
        }
        this.o = aVar.j;
        int i4 = this.f914a.c[this.l];
        int i5 = this.m + (this.f914a.c[this.k] / 4) + (i4 / 4);
        int i6 = i5;
        int i7 = i5 - (i4 / 2);
        int i8 = i7 + i4;
        int i9 = 0;
        int i10 = 0;
        if (i8 > this.h) {
            this.h = i8 + this.f914a.c[1];
            for (int i11 = 0; i11 < this.f914a.f929a; i11++) {
                float[] fArr2 = new float[this.h];
                System.arraycopy(this.g[i11], 0, fArr2, 0, this.g[i11].length);
                this.g[i11] = fArr2;
            }
        }
        switch (this.l) {
            case 0:
                i9 = 0;
                i10 = this.f914a.c[0] / 2;
                break;
            case 1:
                int i12 = (this.f914a.c[1] / 4) - (this.f914a.c[this.k] / 4);
                i9 = i12;
                i10 = i12 + (this.f914a.c[this.k] / 2);
                break;
        }
        for (int i13 = 0; i13 < this.f914a.f929a; i13++) {
            int i14 = i9;
            while (i14 < i10) {
                float[] fArr3 = this.g[i13];
                int i15 = i7 + i14;
                fArr3[i15] = fArr3[i15] + aVar.f904a[i13][i14];
                i14++;
            }
            while (i14 < i4) {
                this.g[i13][i7 + i14] = aVar.f904a[i13][i14];
                i14++;
            }
        }
        if (this.n != -1) {
            this.n += i6 - this.m;
            if (aVar.i != -1 && this.n != aVar.i) {
                if (this.n > aVar.i && aVar.h != 0) {
                    i6 = (int) (i6 - (this.n - aVar.i));
                }
            }
            this.m = i6;
            this.i = i8;
            return 0;
        }
        this.n = aVar.i;
        this.m = i6;
        this.i = i8;
        return 0;
    }

    public final int a(float[][][] fArr, int[] iArr) {
        if (this.j < this.m) {
            for (int i = 0; i < this.f914a.f929a; i++) {
                iArr[i] = this.j;
            }
            fArr[0] = this.g;
            return this.m - this.j;
        }
        return 0;
    }

    public final int a(int i) {
        if (i != 0 && this.j + i > this.m) {
            return -1;
        }
        this.j += i;
        return 0;
    }
}
