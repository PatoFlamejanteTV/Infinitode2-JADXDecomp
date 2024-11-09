package com.a.a.b.c.b;

import com.prineside.luaj.Lua;

/* loaded from: infinitode-2.jar:com/a/a/b/c/b/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<b> f105a = ThreadLocal.withInitial(b::new);

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f106b = new byte[15];
    private int c;

    private b() {
    }

    public static String a(float f) {
        return a().b(f);
    }

    private static b a() {
        return f105a.get();
    }

    private String b(float f) {
        switch (c(f)) {
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

    private int c(float f) {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        int i = floatToRawIntBits & Lua.MASK_NOT_B;
        int i2 = (floatToRawIntBits >>> 23) & 255;
        if (i2 < 255) {
            this.c = -1;
            if (floatToRawIntBits < 0) {
                d(45);
            }
            if (i2 != 0) {
                int i3 = 150 - i2;
                int i4 = 8388608 | i;
                if ((i3 > 0) & (i3 < 24)) {
                    int i5 = i4 >> i3;
                    if ((i5 << i3) == i4) {
                        return a(i5, 0);
                    }
                }
                return a(-i3, i4, 0);
            }
            if (i == 0) {
                return floatToRawIntBits == 0 ? 1 : 2;
            }
            if (i < 8) {
                return a(-149, i * 10, -1);
            }
            return a(-149, i, 0);
        }
        if (i != 0) {
            return 5;
        }
        return floatToRawIntBits > 0 ? 3 : 4;
    }

    private int a(int i, int i2, int i3) {
        long j;
        int c;
        int i4 = i2 & 1;
        long j2 = i2 << 2;
        long j3 = j2 + 2;
        if ((i2 != 8388608) | (i == -149)) {
            j = j2 - 2;
            c = c.b(i);
        } else {
            j = j2 - 1;
            c = c.c(i);
        }
        int d = i + c.d(-c) + 33;
        long e = c.e(c) + 1;
        int a2 = a(e, j2 << d);
        int a3 = a(e, j << d);
        int a4 = a(e, j3 << d);
        int i5 = a2 >> 2;
        if (i5 >= 100) {
            int i6 = 10 * ((int) ((i5 * 1717986919) >>> 34));
            int i7 = i6 + 10;
            boolean z = a3 + i4 <= (i6 << 2);
            if (z != ((i7 << 2) + i4 <= a4)) {
                return a(z ? i6 : i7, c);
            }
        }
        int i8 = i5 + 1;
        boolean z2 = a3 + i4 <= (i5 << 2);
        if (z2 != ((i8 << 2) + i4 <= a4)) {
            return a(z2 ? i5 : i8, c + i3);
        }
        int i9 = a2 - ((i5 + i8) << 1);
        return a((i9 < 0 || (i9 == 0 && (i5 & 1) == 0)) ? i5 : i8, c + i3);
    }

    private static int a(long j, long j2) {
        long a2 = c.a(j, j2);
        return (int) ((a2 >>> 31) | (((a2 & 4294967295L) + 4294967295L) >>> 32));
    }

    private int a(int i, int i2) {
        int b2 = c.b(32 - Integer.numberOfLeadingZeros(i));
        if (i >= c.a(b2)) {
            b2++;
        }
        int a2 = (int) (i * c.a(9 - b2));
        int i3 = i2 + b2;
        int i4 = (int) ((a2 * 1441151881) >>> 57);
        int i5 = a2 - (i4 * 100000000);
        if (i3 > 0 && i3 <= 7) {
            return b(i4, i5, i3);
        }
        if (-3 < i3 && i3 <= 0) {
            return c(i4, i5, i3);
        }
        return d(i4, i5, i3);
    }

    private int b(int i, int i2, int i3) {
        e(i);
        int b2 = b(i2);
        int i4 = 1;
        while (i4 < i3) {
            int i5 = b2 * 10;
            e(i5 >>> 28);
            b2 = i5 & 268435455;
            i4++;
        }
        d(46);
        while (i4 <= 8) {
            int i6 = b2 * 10;
            e(i6 >>> 28);
            b2 = i6 & 268435455;
            i4++;
        }
        b();
        return 0;
    }

    private int c(int i, int i2, int i3) {
        e(0);
        d(46);
        while (i3 < 0) {
            e(0);
            i3++;
        }
        e(i);
        a(i2);
        b();
        return 0;
    }

    private int d(int i, int i2, int i3) {
        e(i);
        d(46);
        a(i2);
        b();
        c(i3 - 1);
        return 0;
    }

    private void a(int i) {
        int b2 = b(i);
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = b2 * 10;
            e(i3 >>> 28);
            b2 = i3 & 268435455;
        }
    }

    private void b() {
        while (this.f106b[this.c] == 48) {
            this.c--;
        }
        if (this.f106b[this.c] == 46) {
            this.c++;
        }
    }

    private static int b(int i) {
        return ((int) (c.a((i + 1) << 28, 193428131138340668L) >>> 20)) - 1;
    }

    private void c(int i) {
        d(69);
        if (i < 0) {
            d(45);
            i = -i;
        }
        if (i < 10) {
            e(i);
            return;
        }
        int i2 = (i * 103) >>> 10;
        e(i2);
        e(i - (i2 * 10));
    }

    private void d(int i) {
        byte[] bArr = this.f106b;
        int i2 = this.c + 1;
        this.c = i2;
        bArr[i2] = (byte) i;
    }

    private void e(int i) {
        byte[] bArr = this.f106b;
        int i2 = this.c + 1;
        this.c = i2;
        bArr[i2] = (byte) (i + 48);
    }

    private String c() {
        return new String(this.f106b, 0, 0, this.c + 1);
    }
}
