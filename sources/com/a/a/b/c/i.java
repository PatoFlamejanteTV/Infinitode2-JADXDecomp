package com.a.a.b.c;

/* loaded from: infinitode-2.jar:com/a/a/b/c/i.class */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private static int f117a = 1000000;

    /* renamed from: b, reason: collision with root package name */
    private static int f118b = 1000000000;
    private static long c = 1000000000;
    private static long d = -2147483648L;
    private static long e = 2147483647L;
    private static String f = "-2147483648";
    private static String g = "-9223372036854775808";
    private static final int[] h = new int[1000];
    private static final String[] i;
    private static final String[] j;

    static {
        int i2 = 0;
        for (int i3 = 0; i3 < 10; i3++) {
            for (int i4 = 0; i4 < 10; i4++) {
                for (int i5 = 0; i5 < 10; i5++) {
                    int i6 = i2;
                    i2++;
                    h[i6] = ((i3 + 48) << 16) | ((i4 + 48) << 8) | (i5 + 48);
                }
            }
        }
        i = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        j = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }

    public static int a(int i2, char[] cArr, int i3) {
        int i4;
        if (i2 < 0) {
            if (i2 == Integer.MIN_VALUE) {
                return b(cArr, i3);
            }
            i3++;
            cArr[i3] = '-';
            i2 = -i2;
        }
        if (i2 < f117a) {
            if (i2 < 1000) {
                if (i2 < 10) {
                    cArr[i3] = (char) (i2 + 48);
                    return i3 + 1;
                }
                return d(i2, cArr, i3);
            }
            int i5 = i2 / 1000;
            return e(i2 - (i5 * 1000), cArr, d(i5, cArr, i3));
        }
        if (i2 >= f118b) {
            int i6 = i2 - f118b;
            int i7 = i6;
            if (i6 >= f118b) {
                i7 -= f118b;
                int i8 = i3;
                i4 = i3 + 1;
                cArr[i8] = '2';
            } else {
                int i9 = i3;
                i4 = i3 + 1;
                cArr[i9] = '1';
            }
            return c(i7, cArr, i4);
        }
        int i10 = i2 / 1000;
        int i11 = i2 - (i10 * 1000);
        int i12 = i10 / 1000;
        return e(i11, cArr, e(i10 - (i12 * 1000), cArr, d(i12, cArr, i3)));
    }

    public static int a(long j2, char[] cArr, int i2) {
        int c2;
        if (j2 < 0) {
            if (j2 > d) {
                return a((int) j2, cArr, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                return a(cArr, i2);
            }
            i2++;
            cArr[i2] = '-';
            j2 = -j2;
        } else if (j2 <= e) {
            return a((int) j2, cArr, i2);
        }
        long j3 = j2 / c;
        long j4 = j2 - (j3 * c);
        if (j3 < c) {
            c2 = b((int) j3, cArr, i2);
        } else {
            long j5 = j3 / c;
            long j6 = j3 - (j5 * c);
            c2 = c((int) j6, cArr, d((int) j5, cArr, i2));
        }
        return c((int) j4, cArr, c2);
    }

    public static String a(int i2) {
        if (i2 < i.length) {
            if (i2 >= 0) {
                return i[i2];
            }
            int i3 = (-i2) - 1;
            if (i3 < j.length) {
                return j[i3];
            }
        }
        return Integer.toString(i2);
    }

    public static String a(long j2) {
        if (j2 <= 2147483647L && j2 >= -2147483648L) {
            return a((int) j2);
        }
        return Long.toString(j2);
    }

    public static String a(double d2) {
        return a(d2, false);
    }

    public static String a(double d2, boolean z) {
        return z ? com.a.a.b.c.b.a.a(d2) : Double.toString(d2);
    }

    public static String a(float f2) {
        return a(f2, false);
    }

    public static String a(float f2, boolean z) {
        return z ? com.a.a.b.c.b.b.a(f2) : Float.toString(f2);
    }

    public static boolean b(double d2) {
        return Double.isNaN(d2) || Double.isInfinite(d2);
    }

    public static boolean b(float f2) {
        return Float.isNaN(f2) || Float.isInfinite(f2);
    }

    private static int b(int i2, char[] cArr, int i3) {
        if (i2 < f117a) {
            if (i2 < 1000) {
                return d(i2, cArr, i3);
            }
            int i4 = i2 / 1000;
            return a(cArr, i3, i4, i2 - (i4 * 1000));
        }
        int i5 = i2 / 1000;
        int i6 = i2 - (i5 * 1000);
        int i7 = i5 / 1000;
        int i8 = i5 - (i7 * 1000);
        int d2 = d(i7, cArr, i3);
        int i9 = h[i8];
        int i10 = d2 + 1;
        cArr[d2] = (char) (i9 >> 16);
        int i11 = i10 + 1;
        cArr[i10] = (char) ((i9 >> 8) & 127);
        int i12 = i11 + 1;
        cArr[i11] = (char) (i9 & 127);
        int i13 = h[i6];
        int i14 = i12 + 1;
        cArr[i12] = (char) (i13 >> 16);
        int i15 = i14 + 1;
        cArr[i14] = (char) ((i13 >> 8) & 127);
        int i16 = i15 + 1;
        cArr[i15] = (char) (i13 & 127);
        return i16;
    }

    private static int c(int i2, char[] cArr, int i3) {
        int i4 = i2 / 1000;
        int i5 = i2 - (i4 * 1000);
        int i6 = i4 / 1000;
        int i7 = h[i6];
        int i8 = i3 + 1;
        cArr[i3] = (char) (i7 >> 16);
        int i9 = i8 + 1;
        cArr[i8] = (char) ((i7 >> 8) & 127);
        int i10 = i9 + 1;
        cArr[i9] = (char) (i7 & 127);
        int i11 = h[i4 - (i6 * 1000)];
        int i12 = i10 + 1;
        cArr[i10] = (char) (i11 >> 16);
        int i13 = i12 + 1;
        cArr[i12] = (char) ((i11 >> 8) & 127);
        int i14 = i13 + 1;
        cArr[i13] = (char) (i11 & 127);
        int i15 = h[i5];
        int i16 = i14 + 1;
        cArr[i14] = (char) (i15 >> 16);
        int i17 = i16 + 1;
        cArr[i16] = (char) ((i15 >> 8) & 127);
        int i18 = i17 + 1;
        cArr[i17] = (char) (i15 & 127);
        return i18;
    }

    private static int a(char[] cArr, int i2, int i3, int i4) {
        int i5 = h[i3];
        if (i3 > 9) {
            if (i3 > 99) {
                i2++;
                cArr[i2] = (char) (i5 >> 16);
            }
            int i6 = i2;
            i2++;
            cArr[i6] = (char) ((i5 >> 8) & 127);
        }
        int i7 = i2;
        int i8 = i2 + 1;
        cArr[i7] = (char) (i5 & 127);
        int i9 = h[i4];
        int i10 = i8 + 1;
        cArr[i8] = (char) (i9 >> 16);
        int i11 = i10 + 1;
        cArr[i10] = (char) ((i9 >> 8) & 127);
        int i12 = i11 + 1;
        cArr[i11] = (char) (i9 & 127);
        return i12;
    }

    private static int d(int i2, char[] cArr, int i3) {
        int i4 = h[i2];
        if (i2 > 9) {
            if (i2 > 99) {
                i3++;
                cArr[i3] = (char) (i4 >> 16);
            }
            int i5 = i3;
            i3++;
            cArr[i5] = (char) ((i4 >> 8) & 127);
        }
        int i6 = i3;
        int i7 = i3 + 1;
        cArr[i6] = (char) (i4 & 127);
        return i7;
    }

    private static int e(int i2, char[] cArr, int i3) {
        int i4 = h[i2];
        int i5 = i3 + 1;
        cArr[i3] = (char) (i4 >> 16);
        int i6 = i5 + 1;
        cArr[i5] = (char) ((i4 >> 8) & 127);
        int i7 = i6 + 1;
        cArr[i6] = (char) (i4 & 127);
        return i7;
    }

    private static int a(char[] cArr, int i2) {
        int length = g.length();
        g.getChars(0, length, cArr, i2);
        return i2 + length;
    }

    private static int b(char[] cArr, int i2) {
        int length = f.length();
        f.getChars(0, length, cArr, i2);
        return i2 + length;
    }
}
