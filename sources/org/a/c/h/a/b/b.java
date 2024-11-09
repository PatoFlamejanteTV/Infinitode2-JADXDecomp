package org.a.c.h.a.b;

import java.io.IOException;
import javax.imageio.stream.MemoryCacheImageInputStream;
import org.a.c.b.i;
import org.a.c.b.j;
import org.a.c.h.a.g;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/b.class */
public class b extends org.a.c.h.a.b.a {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4475a = org.a.a.a.c.a(b.class);

    /* renamed from: b, reason: collision with root package name */
    private org.a.c.b.a f4476b;
    private org.a.c.b.a c;
    private org.a.c.b.a d;
    private int[][] e;

    public b(org.a.c.b.b bVar) {
        super(bVar);
        this.f4476b = null;
        this.c = null;
        this.d = null;
        this.e = (int[][]) null;
    }

    @Override // org.a.c.h.a.b.a
    public final int a() {
        return 0;
    }

    private org.a.c.b.a h() {
        if (this.d == null) {
            this.d = (org.a.c.b.a) f().a(j.dr);
        }
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[][] i() {
        if (this.e == null) {
            int i = 1;
            int e = e();
            int d = d();
            org.a.c.b.a h = h();
            for (int i2 = 0; i2 < e; i2++) {
                i *= h.c(i2);
            }
            this.e = new int[i][d];
            int j = j();
            int i3 = 0;
            try {
                MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(c().c());
                for (int i4 = 0; i4 < i; i4++) {
                    for (int i5 = 0; i5 < d; i5++) {
                        this.e[i3][i5] = (int) memoryCacheImageInputStream.readBits(j);
                    }
                    i3++;
                }
                memoryCacheImageInputStream.close();
            } catch (IOException unused) {
            }
        }
        return this.e;
    }

    private int j() {
        return f().j(j.A);
    }

    private org.a.c.b.a k() {
        if (this.f4476b == null) {
            this.f4476b = (org.a.c.b.a) f().a(j.aP);
            if (this.f4476b == null) {
                this.f4476b = new org.a.c.b.a();
                int b2 = h().b();
                for (int i = 0; i < b2; i++) {
                    this.f4476b.a((org.a.c.b.b) i.f4366a);
                    this.f4476b.a((org.a.c.b.b) i.a(r0.c(i) - 1));
                }
            }
        }
        return this.f4476b;
    }

    private org.a.c.b.a l() {
        if (this.c == null) {
            this.c = (org.a.c.b.a) f().a(j.aq);
            if (this.c == null) {
                this.c = g();
            }
        }
        return this.c;
    }

    private g c(int i) {
        g gVar = null;
        org.a.c.b.a k = k();
        if (k != null && k.b() >= (i << 1) + 1) {
            gVar = new g(k, i);
        }
        return gVar;
    }

    private g d(int i) {
        g gVar = null;
        org.a.c.b.a l = l();
        if (l != null && l.b() >= (i << 1) + 1) {
            gVar = new g(l, i);
        }
        return gVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int[] iArr) {
        float[] d = h().d();
        int i = 0;
        int i2 = 1;
        int length = iArr.length;
        for (int i3 = length - 2; i3 >= 0; i3--) {
            i2 = (int) (i2 * d[i3]);
        }
        for (int i4 = length - 1; i4 >= 0; i4--) {
            i += i2 * iArr[i4];
            if (i4 - 1 >= 0) {
                i2 = (int) (i2 / d[i4 - 1]);
            }
        }
        return i;
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/b$a.class */
    class a {

        /* renamed from: a, reason: collision with root package name */
        private final float[] f4477a;

        /* renamed from: b, reason: collision with root package name */
        private final int[] f4478b;
        private final int[] c;
        private final int d;
        private final int e;

        a(float[] fArr, int[] iArr, int[] iArr2) {
            this.e = b.this.d();
            this.f4477a = fArr;
            this.f4478b = iArr;
            this.c = iArr2;
            this.d = fArr.length;
        }

        final float[] a() {
            return a(new int[this.d], 0);
        }

        private float[] a(int[] iArr, int i) {
            float[] fArr = new float[this.e];
            if (i == this.f4477a.length - 1) {
                if (this.f4478b[i] == this.c[i]) {
                    iArr[i] = this.f4478b[i];
                    int[] iArr2 = b.this.i()[b.this.a(iArr)];
                    for (int i2 = 0; i2 < this.e; i2++) {
                        fArr[i2] = iArr2[i2];
                    }
                    return fArr;
                }
                iArr[i] = this.f4478b[i];
                int[] iArr3 = b.this.i()[b.this.a(iArr)];
                iArr[i] = this.c[i];
                int[] iArr4 = b.this.i()[b.this.a(iArr)];
                for (int i3 = 0; i3 < this.e; i3++) {
                    fArr[i3] = b.a(this.f4477a[i], this.f4478b[i], this.c[i], iArr3[i3], iArr4[i3]);
                }
                return fArr;
            }
            if (this.f4478b[i] == this.c[i]) {
                iArr[i] = this.f4478b[i];
                return a(iArr, i + 1);
            }
            iArr[i] = this.f4478b[i];
            float[] a2 = a(iArr, i + 1);
            iArr[i] = this.c[i];
            float[] a3 = a(iArr, i + 1);
            for (int i4 = 0; i4 < this.e; i4++) {
                fArr[i4] = b.a(this.f4477a[i], this.f4478b[i], this.c[i], a2[i4], a3[i4]);
            }
            return fArr;
        }
    }

    @Override // org.a.c.h.a.b.a
    public final float[] a(float[] fArr) {
        float[] d = h().d();
        float pow = (float) (Math.pow(2.0d, j()) - 1.0d);
        int length = fArr.length;
        int d2 = d();
        int[] iArr = new int[length];
        int[] iArr2 = new int[length];
        float[] fArr2 = (float[]) fArr.clone();
        for (int i = 0; i < length; i++) {
            g b2 = b(i);
            g c = c(i);
            fArr2[i] = a(fArr2[i], b2.a(), b2.b());
            fArr2[i] = a(fArr2[i], b2.a(), b2.b(), c.a(), c.b());
            fArr2[i] = a(fArr2[i], 0.0f, d[i] - 1.0f);
            iArr[i] = (int) Math.floor(fArr2[i]);
            iArr2[i] = (int) Math.ceil(fArr2[i]);
        }
        float[] a2 = new a(fArr2, iArr, iArr2).a();
        for (int i2 = 0; i2 < d2; i2++) {
            g a3 = a(i2);
            g d3 = d(i2);
            a2[i2] = a(a2[i2], 0.0f, pow, d3.a(), d3.b());
            a2[i2] = a(a2[i2], a3.a(), a3.b());
        }
        return a2;
    }
}
