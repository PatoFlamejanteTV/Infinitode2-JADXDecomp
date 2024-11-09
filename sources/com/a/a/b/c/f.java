package com.a.a.b.c;

import com.a.a.b.g.o;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/b/c/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f111a = b.a(true);

    /* renamed from: b, reason: collision with root package name */
    private static final f f112b;

    static {
        b.b(true);
        f112b = new f();
    }

    public static f a() {
        return f112b;
    }

    public final char[] a(String str) {
        int b2;
        int length = str.length();
        char[] cArr = new char[a(length)];
        int[] f = b.f();
        int length2 = f.length;
        int i = 0;
        o oVar = null;
        int i2 = 0;
        char[] cArr2 = null;
        loop0: while (i < length) {
            do {
                char charAt = str.charAt(i);
                if (charAt >= length2 || f[charAt] == 0) {
                    if (i2 >= cArr.length) {
                        if (oVar == null) {
                            oVar = o.a(cArr);
                        }
                        cArr = oVar.j();
                        i2 = 0;
                    }
                    int i3 = i2;
                    i2++;
                    cArr[i3] = charAt;
                    i++;
                } else {
                    if (cArr2 == null) {
                        cArr2 = b();
                    }
                    int i4 = i;
                    i++;
                    char charAt2 = str.charAt(i4);
                    int i5 = f[charAt2];
                    if (i5 < 0) {
                        b2 = a(charAt2, cArr2);
                    } else {
                        b2 = b(i5, cArr2);
                    }
                    int i6 = b2;
                    if (i2 + i6 > cArr.length) {
                        int length3 = cArr.length - i2;
                        if (length3 > 0) {
                            System.arraycopy(cArr2, 0, cArr, i2, length3);
                        }
                        if (oVar == null) {
                            oVar = o.a(cArr);
                        }
                        cArr = oVar.j();
                        int i7 = i6 - length3;
                        System.arraycopy(cArr2, length3, cArr, 0, i7);
                        i2 = i7;
                    } else {
                        System.arraycopy(cArr2, 0, cArr, i2, i6);
                        i2 += i6;
                    }
                }
            } while (i < length);
        }
        if (oVar == null) {
            return Arrays.copyOfRange(cArr, 0, i2);
        }
        oVar.a(i2);
        return oVar.f();
    }

    private static char[] b() {
        return new char[]{'\\', 0, '0', '0'};
    }

    private static int a(int i, char[] cArr) {
        cArr[1] = 'u';
        cArr[4] = f111a[i >> 4];
        cArr[5] = f111a[i & 15];
        return 6;
    }

    private static int b(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private static int a(int i) {
        return Math.min(Math.max(16, i + Math.min(6 + (i >> 3), 1000)), 32000);
    }
}
