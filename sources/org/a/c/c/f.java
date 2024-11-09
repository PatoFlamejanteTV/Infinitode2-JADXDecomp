package org.a.c.c;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/f.class */
final class f extends OutputStream {

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f4397b;
    private final int c;
    private final int d;
    private final int e;
    private int[] f;
    private int[] g;
    private final OutputStream n;
    private static final a[] o = new a[64];
    private static final a[] p = new a[40];
    private static final a[] q;
    private static final a[] r;

    /* renamed from: a, reason: collision with root package name */
    private int f4396a = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private byte k = 0;
    private byte l = 0;
    private final int m = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(OutputStream outputStream, int i, int i2, int i3) {
        this.n = outputStream;
        this.d = i;
        this.e = i2;
        this.g = new int[i];
        this.f = new int[i];
        this.c = (i + 7) / 8;
        this.f4397b = new byte[this.c];
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.f4397b[this.f4396a] = (byte) i;
        this.f4396a++;
        if (this.f4396a == this.c) {
            a();
            this.f4396a = 0;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
        this.n.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.n.close();
    }

    private void a() {
        this.h++;
        int[] iArr = this.g;
        this.g = this.f;
        this.f = iArr;
        this.j = this.i;
        this.i = 0;
        boolean z = true;
        for (int i = 0; i < this.d; i++) {
            if ((((this.f4397b[i / 8] >> (7 - (i % 8))) & 1) == 1) == z) {
                this.f[this.i] = i;
                this.i++;
                z = !z;
            }
        }
        b();
        if (this.h == this.e) {
            d();
            d();
            e();
        }
    }

    private void b() {
        c();
    }

    private int[] a(int i, boolean z) {
        int[] iArr = {this.d, this.d};
        for (int i2 = 0; i2 < this.i; i2++) {
            if (i < this.f[i2] || (i == 0 && z)) {
                iArr[0] = this.f[i2];
                if (i2 + 1 < this.i) {
                    iArr[1] = this.f[i2 + 1];
                }
                return iArr;
            }
        }
        return iArr;
    }

    private void b(int i, boolean z) {
        int i2 = i / 64;
        a[] aVarArr = z ? p : r;
        while (i2 > 0) {
            if (i2 >= aVarArr.length) {
                a(aVarArr[aVarArr.length - 1].f4398a, aVarArr[aVarArr.length - 1].f4399b);
                i2 -= aVarArr.length;
            } else {
                a(aVarArr[i2 - 1].f4398a, aVarArr[i2 - 1].f4399b);
                i2 = 0;
            }
        }
        a aVar = z ? o[i % 64] : q[i % 64];
        a(aVar.f4398a, aVar.f4399b);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0077. Please report as an issue. */
    private void c() {
        boolean z = true;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.d) {
                int[] a2 = a(i2, z);
                int[] c = c(i2, z);
                int i3 = a2[0] - c[0];
                if (a2[0] > c[1]) {
                    a(1, 4);
                    i = c[1];
                } else if (i3 > 3 || i3 < -3) {
                    a(1, 3);
                    b(a2[0] - i2, z);
                    b(a2[1] - a2[0], !z);
                    i = a2[1];
                } else {
                    switch (i3) {
                        case -3:
                            a(2, 7);
                            break;
                        case -2:
                            a(2, 6);
                            break;
                        case -1:
                            a(2, 3);
                            break;
                        case 0:
                            a(1, 1);
                            break;
                        case 1:
                            a(3, 3);
                            break;
                        case 2:
                            a(3, 6);
                            break;
                        case 3:
                            a(3, 7);
                            break;
                    }
                    z = !z;
                    i = c[0] + i3;
                }
            } else {
                return;
            }
        }
    }

    private int[] c(int i, boolean z) {
        int[] iArr = {this.d, this.d};
        for (int i2 = z ? 0 : 1; i2 < this.j; i2 += 2) {
            if (this.g[i2] > i || (i == 0 && i2 == 0)) {
                iArr[0] = this.g[i2];
                if (i2 + 1 < this.j) {
                    iArr[1] = this.g[i2 + 1];
                }
                return iArr;
            }
        }
        return iArr;
    }

    private void a(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            boolean z = ((i >> ((i2 - i3) - 1)) & 1) == 1;
            if (this.m == 1) {
                this.k = (byte) (this.k | (z ? 1 << (7 - (this.l % 8)) : 0));
            } else {
                this.k = (byte) (this.k | (z ? 1 << (this.l % 8) : 0));
            }
            this.l = (byte) (this.l + 1);
            if (this.l == 8) {
                this.n.write(this.k);
                f();
            }
        }
    }

    private void d() {
        a(1, 12);
    }

    private void e() {
        if (this.l != 0) {
            this.n.write(this.k);
        }
        f();
    }

    private void f() {
        this.k = (byte) 0;
        this.l = (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/c/f$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        final int f4398a;

        /* renamed from: b, reason: collision with root package name */
        final int f4399b;

        /* synthetic */ a(int i, int i2, byte b2) {
            this(i, i2);
        }

        private a(int i, int i2) {
            this.f4398a = i;
            this.f4399b = i2;
        }
    }

    static {
        for (int i = 0; i < e.c.length; i++) {
            int i2 = i + 4;
            for (int i3 = 0; i3 < e.c[i].length; i3++) {
                short s = e.d[i][i3];
                short s2 = e.c[i][i3];
                if (s < 64) {
                    o[s] = new a(s2, i2, (byte) 0);
                } else {
                    p[(s / 64) - 1] = new a(s2, i2, (byte) 0);
                }
            }
        }
        q = new a[64];
        r = new a[40];
        for (int i4 = 0; i4 < e.f4391a.length; i4++) {
            int i5 = i4 + 2;
            for (int i6 = 0; i6 < e.f4391a[i4].length; i6++) {
                short s3 = e.f4392b[i4][i6];
                short s4 = e.f4391a[i4][i6];
                if (s3 < 64) {
                    q[s3] = new a(s4, i5, (byte) 0);
                } else {
                    r[(s3 / 64) - 1] = new a(s4, i5, (byte) 0);
                }
            }
        }
    }
}
