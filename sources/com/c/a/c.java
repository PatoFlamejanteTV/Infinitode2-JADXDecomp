package com.c.a;

/* loaded from: infinitode-2.jar:com/c/a/c.class */
public class c {
    private static int[] g = new int[256];

    /* renamed from: a, reason: collision with root package name */
    public byte[] f898a;

    /* renamed from: b, reason: collision with root package name */
    public int f899b;
    public int c;
    public byte[] d;
    public int e;
    public int f;

    static {
        for (int i = 0; i < g.length; i++) {
            int i2 = i;
            g[i2] = a(i2);
        }
    }

    private static int a(int i) {
        int i2;
        int i3 = i << 24;
        for (int i4 = 0; i4 < 8; i4++) {
            if ((i3 & Integer.MIN_VALUE) != 0) {
                i2 = (i3 << 1) ^ 79764919;
            } else {
                i2 = i3 << 1;
            }
            i3 = i2;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.f898a[this.f899b + 4] & 255;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        return this.f898a[this.f899b + 5] & 1;
    }

    public final int c() {
        return this.f898a[this.f899b + 5] & 2;
    }

    public final int d() {
        return this.f898a[this.f899b + 5] & 4;
    }

    public final long e() {
        return ((((((((((((((this.f898a[this.f899b + 13] & 255) << 8) | (this.f898a[this.f899b + 12] & 255)) << 8) | (this.f898a[this.f899b + 11] & 255)) << 8) | (this.f898a[this.f899b + 10] & 255)) << 8) | (this.f898a[this.f899b + 9] & 255)) << 8) | (this.f898a[this.f899b + 8] & 255)) << 8) | (this.f898a[this.f899b + 7] & 255)) << 8) | (this.f898a[this.f899b + 6] & 255);
    }

    public final int f() {
        return (this.f898a[this.f899b + 14] & 255) | ((this.f898a[this.f899b + 15] & 255) << 8) | ((this.f898a[this.f899b + 16] & 255) << 16) | ((this.f898a[this.f899b + 17] & 255) << 24);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int g() {
        return (this.f898a[this.f899b + 18] & 255) | ((this.f898a[this.f899b + 19] & 255) << 8) | ((this.f898a[this.f899b + 20] & 255) << 16) | ((this.f898a[this.f899b + 21] & 255) << 24);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h() {
        int i = 0;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i << 8) ^ g[(i >>> 24) ^ (this.f898a[this.f899b + i2] & 255)];
        }
        for (int i3 = 0; i3 < this.f; i3++) {
            i = (i << 8) ^ g[(i >>> 24) ^ (this.d[this.e + i3] & 255)];
        }
        this.f898a[this.f899b + 22] = (byte) i;
        this.f898a[this.f899b + 23] = (byte) (i >>> 8);
        this.f898a[this.f899b + 24] = (byte) (i >>> 16);
        this.f898a[this.f899b + 25] = (byte) (i >>> 24);
    }
}
