package com.a.a.b.c;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.lwjgl.opengl.CGL;

/* loaded from: infinitode-2.jar:com/a/a/b/c/h.class */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private static String f115a = "-9223372036854775808".substring(1);

    /* renamed from: b, reason: collision with root package name */
    private static String f116b = "9223372036854775807";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int a(char[] cArr, int i, int i2) {
        if (i2 > 0 && cArr[i] == '+') {
            i++;
            i2--;
        }
        int i3 = cArr[(i + i2) - 1] - '0';
        switch (i2) {
            case 2:
                i3 += (cArr[i] - '0') * 10;
                break;
            case 3:
                int i4 = i;
                i++;
                i3 += (cArr[i4] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
            case 4:
                int i5 = i;
                i++;
                i3 += (cArr[i5] - '0') * 1000;
                int i42 = i;
                i++;
                i3 += (cArr[i42] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
            case 5:
                int i6 = i;
                i++;
                i3 += (cArr[i6] - '0') * CGL.kCGLBadAttribute;
                int i52 = i;
                i++;
                i3 += (cArr[i52] - '0') * 1000;
                int i422 = i;
                i++;
                i3 += (cArr[i422] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
            case 6:
                int i7 = i;
                i++;
                i3 += (cArr[i7] - '0') * 100000;
                int i62 = i;
                i++;
                i3 += (cArr[i62] - '0') * CGL.kCGLBadAttribute;
                int i522 = i;
                i++;
                i3 += (cArr[i522] - '0') * 1000;
                int i4222 = i;
                i++;
                i3 += (cArr[i4222] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
            case 7:
                int i8 = i;
                i++;
                i3 += (cArr[i8] - '0') * 1000000;
                int i72 = i;
                i++;
                i3 += (cArr[i72] - '0') * 100000;
                int i622 = i;
                i++;
                i3 += (cArr[i622] - '0') * CGL.kCGLBadAttribute;
                int i5222 = i;
                i++;
                i3 += (cArr[i5222] - '0') * 1000;
                int i42222 = i;
                i++;
                i3 += (cArr[i42222] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
            case 8:
                int i9 = i;
                i++;
                i3 += (cArr[i9] - '0') * 10000000;
                int i82 = i;
                i++;
                i3 += (cArr[i82] - '0') * 1000000;
                int i722 = i;
                i++;
                i3 += (cArr[i722] - '0') * 100000;
                int i6222 = i;
                i++;
                i3 += (cArr[i6222] - '0') * CGL.kCGLBadAttribute;
                int i52222 = i;
                i++;
                i3 += (cArr[i52222] - '0') * 1000;
                int i422222 = i;
                i++;
                i3 += (cArr[i422222] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
            case 9:
                int i10 = i;
                i++;
                i3 += (cArr[i10] - '0') * 100000000;
                int i92 = i;
                i++;
                i3 += (cArr[i92] - '0') * 10000000;
                int i822 = i;
                i++;
                i3 += (cArr[i822] - '0') * 1000000;
                int i7222 = i;
                i++;
                i3 += (cArr[i7222] - '0') * 100000;
                int i62222 = i;
                i++;
                i3 += (cArr[i62222] - '0') * CGL.kCGLBadAttribute;
                int i522222 = i;
                i++;
                i3 += (cArr[i522222] - '0') * 1000;
                int i4222222 = i;
                i++;
                i3 += (cArr[i4222222] - '0') * 100;
                i3 += (cArr[i] - '0') * 10;
                break;
        }
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b9, code lost:            if (r8 < r0) goto L43;     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00bc, code lost:            r1 = r8;        r8 = r8 + 1;        r0 = r4.charAt(r1);     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c9, code lost:            if (r0 > '9') goto L58;     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cf, code lost:            if (r0 >= '0') goto L49;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d7, code lost:            r9 = (r9 * 10) + (r0 - '0');     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e6, code lost:            if (r8 < r0) goto L60;     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d6, code lost:            return java.lang.Integer.parseInt(r4);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.lang.String r4) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.c.h.a(java.lang.String):int");
    }

    public static long b(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return (a(cArr, i, i3) * 1000000000) + a(cArr, i + i3, 9);
    }

    public static long b(String str) {
        if (str.length() <= 9) {
            return a(str);
        }
        return Long.parseLong(str);
    }

    public static boolean a(char[] cArr, int i, int i2, boolean z) {
        String str = z ? f115a : f116b;
        String str2 = str;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = cArr[i + i3] - str2.charAt(i3);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean a(String str, boolean z) {
        String str2 = f116b;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int charAt = str.charAt(i) - str2.charAt(i);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static int a(String str, int i) {
        if (str == null) {
            return i;
        }
        String trim = str.trim();
        String str2 = trim;
        int length = trim.length();
        int i2 = length;
        if (length == 0) {
            return i;
        }
        int i3 = 0;
        char charAt = str2.charAt(0);
        if (charAt == '+') {
            String substring = str2.substring(1);
            str2 = substring;
            i2 = substring.length();
        } else if (charAt == '-') {
            i3 = 1;
        }
        while (i3 < i2) {
            char charAt2 = str2.charAt(i3);
            if (charAt2 <= '9' && charAt2 >= '0') {
                i3++;
            } else {
                try {
                    return (int) b(str2, true);
                } catch (NumberFormatException unused) {
                    return i;
                }
            }
        }
        try {
            return Integer.parseInt(str2);
        } catch (NumberFormatException unused2) {
            return i;
        }
    }

    public static long a(String str, long j) {
        if (str == null) {
            return j;
        }
        String trim = str.trim();
        String str2 = trim;
        int length = trim.length();
        int i = length;
        if (length == 0) {
            return j;
        }
        int i2 = 0;
        char charAt = str2.charAt(0);
        if (charAt == '+') {
            String substring = str2.substring(1);
            str2 = substring;
            i = substring.length();
        } else if (charAt == '-') {
            i2 = 1;
        }
        while (i2 < i) {
            char charAt2 = str2.charAt(i2);
            if (charAt2 <= '9' && charAt2 >= '0') {
                i2++;
            } else {
                try {
                    return (long) b(str2, true);
                } catch (NumberFormatException unused) {
                    return j;
                }
            }
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    public static double a(String str, double d) {
        return a(str, d, false);
    }

    private static double a(String str, double d, boolean z) {
        if (str == null) {
            return d;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d;
        }
        try {
            return b(trim, false);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static double c(String str) {
        return b(str, false);
    }

    public static double b(String str, boolean z) {
        return z ? com.a.a.b.c.a.e.a(str) : Double.parseDouble(str);
    }

    public static float c(String str, boolean z) {
        return z ? com.a.a.b.c.a.e.b(str) : Float.parseFloat(str);
    }

    public static BigDecimal d(String str) {
        return a.a(str);
    }

    public static BigInteger e(String str) {
        if (str.length() > 1250) {
            return a.a(str).toBigInteger();
        }
        return new BigInteger(str);
    }
}
