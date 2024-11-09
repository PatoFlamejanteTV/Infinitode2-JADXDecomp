package com.c.a;

/* loaded from: infinitode-2.jar:com/c/a/d.class */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f900a;

    /* renamed from: b, reason: collision with root package name */
    private int f901b;
    private int c;
    private int d;
    private int[] e;
    private long[] f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private long m;

    public d() {
        b();
    }

    private void b() {
        this.f901b = 16384;
        this.f900a = new byte[this.f901b];
        this.g = 1024;
        this.e = new int[this.g];
        this.f = new long[this.g];
    }

    public final void a(int i) {
        if (this.f900a == null) {
            b();
        } else {
            for (int i2 = 0; i2 < this.f900a.length; i2++) {
                this.f900a[i2] = 0;
            }
            for (int i3 = 0; i3 < this.e.length; i3++) {
                this.e[i3] = 0;
            }
            for (int i4 = 0; i4 < this.f.length; i4++) {
                this.f[i4] = 0;
            }
        }
        this.k = i;
    }

    public final void a() {
        this.f900a = null;
        this.e = null;
        this.f = null;
    }

    private void b(int i) {
        if (this.f901b <= this.c + i) {
            this.f901b += i + 1024;
            byte[] bArr = new byte[this.f901b];
            System.arraycopy(this.f900a, 0, bArr, 0, this.f900a.length);
            this.f900a = bArr;
        }
    }

    private void c(int i) {
        if (this.g <= this.h + i) {
            this.g += i + 32;
            int[] iArr = new int[this.g];
            System.arraycopy(this.e, 0, iArr, 0, this.e.length);
            this.e = iArr;
            long[] jArr = new long[this.g];
            System.arraycopy(this.f, 0, jArr, 0, this.f.length);
            this.f = jArr;
        }
    }

    public final int a(b bVar) {
        int i = this.j;
        if (this.i <= i) {
            return 0;
        }
        if ((this.e[i] & 1024) != 0) {
            this.j++;
            this.m++;
            return -1;
        }
        int i2 = this.e[i] & 255;
        bVar.f896a = this.f900a;
        bVar.f897b = this.d;
        bVar.e = this.e[i] & 512;
        bVar.d = this.e[i] & 256;
        int i3 = i2;
        int i4 = 0;
        while (true) {
            int i5 = i3 + i4;
            if (i2 == 255) {
                i++;
                int i6 = this.e[i];
                i2 = i6 & 255;
                if ((i6 & 512) != 0) {
                    bVar.e = 512;
                }
                i3 = i5;
                i4 = i2;
            } else {
                bVar.g = this.m;
                bVar.f = this.f[i];
                bVar.c = i5;
                this.d += i5;
                this.j = i + 1;
                this.m++;
                return 1;
            }
        }
    }

    public final int a(c cVar) {
        byte[] bArr = cVar.f898a;
        int i = cVar.f899b;
        byte[] bArr2 = cVar.d;
        int i2 = cVar.e;
        int i3 = cVar.f;
        int i4 = 0;
        int a2 = cVar.a();
        int b2 = cVar.b();
        int c = cVar.c();
        int d = cVar.d();
        long e = cVar.e();
        int f = cVar.f();
        int g = cVar.g();
        int i5 = bArr[i + 26] & 255;
        int i6 = this.j;
        int i7 = this.d;
        if (i7 != 0) {
            this.c -= i7;
            if (this.c != 0) {
                byte[] bArr3 = this.f900a;
                System.arraycopy(bArr3, i7, bArr3, 0, this.c);
            }
            this.d = 0;
        }
        if (i6 != 0) {
            if (this.h - i6 != 0) {
                int[] iArr = this.e;
                System.arraycopy(iArr, i6, iArr, 0, this.h - i6);
                long[] jArr = this.f;
                System.arraycopy(jArr, i6, jArr, 0, this.h - i6);
            }
            this.h -= i6;
            this.i -= i6;
            this.j = 0;
        }
        if (f != this.k || a2 > 0) {
            return -1;
        }
        c(i5 + 1);
        if (g != this.l) {
            for (int i8 = this.i; i8 < this.h; i8++) {
                this.c -= this.e[i8] & 255;
            }
            this.h = this.i;
            if (this.l != -1) {
                int[] iArr2 = this.e;
                int i9 = this.h;
                this.h = i9 + 1;
                iArr2[i9] = 1024;
                this.i++;
            }
            if (b2 != 0) {
                c = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int i10 = bArr[i + 27 + i4] & 255;
                    i2 += i10;
                    i3 -= i10;
                    if (i10 >= 255) {
                        i4++;
                    } else {
                        i4++;
                        break;
                    }
                }
            }
        }
        if (i3 != 0) {
            b(i3);
            System.arraycopy(bArr2, i2, this.f900a, this.c, i3);
            this.c += i3;
        }
        int i11 = -1;
        while (i4 < i5) {
            int i12 = bArr[i + 27 + i4] & 255;
            this.e[this.h] = i12;
            this.f[this.h] = -1;
            if (c != 0) {
                int[] iArr3 = this.e;
                int i13 = this.h;
                iArr3[i13] = iArr3[i13] | 256;
                c = 0;
            }
            if (i12 < 255) {
                i11 = this.h;
            }
            this.h++;
            i4++;
            if (i12 < 255) {
                this.i = this.h;
            }
        }
        if (i11 != -1) {
            this.f[i11] = e;
        }
        if (d != 0 && this.h > 0) {
            int[] iArr4 = this.e;
            int i14 = this.h - 1;
            iArr4[i14] = iArr4[i14] | 512;
        }
        this.l = g + 1;
        return 0;
    }
}
