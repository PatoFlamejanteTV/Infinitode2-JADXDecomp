package com.a.a.b.c.b;

/* loaded from: infinitode-2.jar:com/a/a/b/c/b/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<a> f101a = ThreadLocal.withInitial(a::new);

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f102b = new byte[24];
    private int c;

    private a() {
    }

    public static String a(double d) {
        return a().b(d);
    }

    private static a a() {
        return f101a.get();
    }

    private String b(double d) {
        switch (c(d)) {
            case 0:
                return c();
            case 1:
                return "0.0";
            case 2:
                return "-0.0";
            case 3:
                return "Infinity";
            case 4:
                return "-Infinity";
            default:
                return "NaN";
        }
    }

    private int c(double d) {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        long j = doubleToRawLongBits & 4503599627370495L;
        int i = ((int) (doubleToRawLongBits >>> 52)) & 2047;
        if (i >= 2047) {
            if (j != 0) {
                return 5;
            }
            return doubleToRawLongBits > 0 ? 3 : 4;
        }
        this.c = -1;
        if (doubleToRawLongBits < 0) {
            e(45);
        }
        if (i == 0) {
            if (j == 0) {
                return doubleToRawLongBits == 0 ? 1 : 2;
            }
            if (j < 3) {
                return a(-1074, 10 * j, -1);
            }
            return a(-1074, j, 0);
        }
        int i2 = 1075 - i;
        long j2 = 4503599627370496L | j;
        if ((i2 > 0) & (i2 < 53)) {
            long j3 = j2 >> i2;
            if ((j3 << i2) == j2) {
                return a(j3, 0);
            }
        }
        return a(-i2, j2, 0);
    }

    private int a(int i, long j, int i2) {
        long j2;
        int c;
        int i3 = ((int) j) & 1;
        long j3 = j << 2;
        long j4 = j3 + 2;
        if ((j != 4503599627370496L) | (i == -1074)) {
            j2 = j3 - 2;
            c = c.b(i);
        } else {
            j2 = j3 - 1;
            c = c.c(i);
        }
        int d = i + c.d(-c) + 2;
        long e = c.e(c);
        long f = c.f(c);
        long a2 = a(e, f, j3 << d);
        long a3 = a(e, f, j2 << d);
        long a4 = a(e, f, j4 << d);
        long j5 = a2 >> 2;
        if (j5 >= 100) {
            long a5 = 10 * c.a(j5, 1844674407370955168L);
            long j6 = a5 + 10;
            boolean z = a3 + ((long) i3) <= (a5 << 2);
            if (z != ((j6 << 2) + ((long) i3) <= a4)) {
                return a(z ? a5 : j6, c);
            }
        }
        long j7 = j5 + 1;
        boolean z2 = a3 + ((long) i3) <= (j5 << 2);
        if (z2 != ((j7 << 2) + ((long) i3) <= a4)) {
            return a(z2 ? j5 : j7, c + i2);
        }
        long j8 = a2 - ((j5 + j7) << 1);
        return a((j8 < 0 || (j8 == 0 && (j5 & 1) == 0)) ? j5 : j7, c + i2);
    }

    private static long a(long j, long j2, long j3) {
        long a2 = ((j * j3) >>> 1) + c.a(j2, j3);
        return (c.a(j, j3) + (a2 >>> 63)) | (((a2 & Long.MAX_VALUE) + Long.MAX_VALUE) >>> 63);
    }

    private int a(long j, int i) {
        int b2 = c.b(64 - Long.numberOfLeadingZeros(j));
        if (j >= c.a(b2)) {
            b2++;
        }
        long a2 = j * c.a(17 - b2);
        int i2 = i + b2;
        long a3 = c.a(a2, 193428131138340668L) >>> 20;
        int i3 = (int) (a2 - (100000000 * a3));
        int i4 = (int) ((a3 * 1441151881) >>> 57);
        int i5 = (int) (a3 - (i4 * 100000000));
        if (i2 > 0 && i2 <= 7) {
            return a(i4, i5, i3, i2);
        }
        if (-3 < i2 && i2 <= 0) {
            return b(i4, i5, i3, i2);
        }
        return c(i4, i5, i3, i2);
    }

    private int a(int i, int i2, int i3, int i4) {
        f(i);
        int c = c(i2);
        int i5 = 1;
        while (i5 < i4) {
            int i6 = c * 10;
            f(i6 >>> 28);
            c = i6 & 268435455;
            i5++;
        }
        e(46);
        while (i5 <= 8) {
            int i7 = c * 10;
            f(i7 >>> 28);
            c = i7 & 268435455;
            i5++;
        }
        a(i3);
        return 0;
    }

    private int b(int i, int i2, int i3, int i4) {
        f(0);
        e(46);
        while (i4 < 0) {
            f(0);
            i4++;
        }
        f(i);
        b(i2);
        a(i3);
        return 0;
    }

    private int c(int i, int i2, int i3, int i4) {
        f(i);
        e(46);
        b(i2);
        a(i3);
        d(i4 - 1);
        return 0;
    }

    private void a(int i) {
        if (i != 0) {
            b(i);
        }
        b();
    }

    private void b(int i) {
        int c = c(i);
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = c * 10;
            f(i3 >>> 28);
            c = i3 & 268435455;
        }
    }

    private void b() {
        while (this.f102b[this.c] == 48) {
            this.c--;
        }
        if (this.f102b[this.c] == 46) {
            this.c++;
        }
    }

    private static int c(int i) {
        return ((int) (c.a((i + 1) << 28, 193428131138340668L) >>> 20)) - 1;
    }

    private void d(int i) {
        e(69);
        if (i < 0) {
            e(45);
            i = -i;
        }
        if (i < 10) {
            f(i);
            return;
        }
        if (i >= 100) {
            int i2 = (i * 1311) >>> 17;
            f(i2);
            i -= i2 * 100;
        }
        int i3 = (i * 103) >>> 10;
        f(i3);
        f(i - (i3 * 10));
    }

    private void e(int i) {
        byte[] bArr = this.f102b;
        int i2 = this.c + 1;
        this.c = i2;
        bArr[i2] = (byte) i;
    }

    private void f(int i) {
        byte[] bArr = this.f102b;
        int i2 = this.c + 1;
        this.c = i2;
        bArr[i2] = (byte) (i + 48);
    }

    private String c() {
        return new String(this.f102b, 0, 0, this.c + 1);
    }
}
