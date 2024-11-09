package com.b.a.a;

import java.io.IOException;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/b/a/a/ba.class */
public final class ba {

    /* renamed from: a, reason: collision with root package name */
    private static char[] f796a;

    public static final boolean a(Object obj, Object obj2) {
        return obj == obj2;
    }

    static {
        System.getProperty("line.separator");
        f796a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    }

    public static String a(long j, int i) {
        if (j == Long.MIN_VALUE) {
            return "-8000000000000000";
        }
        boolean z = j < 0;
        boolean z2 = z;
        if (z) {
            j = -j;
        }
        String upperCase = Long.toString(j, 16).toUpperCase(Locale.ENGLISH);
        String str = upperCase;
        if (upperCase.length() < 6) {
            str = "0000000000000000".substring(str.length(), 6) + str;
        }
        if (z2) {
            return "-" + str;
        }
        return str;
    }

    public static boolean a(int i) {
        return i < 32 || i > 126;
    }

    public static <T extends Appendable> boolean a(T t, int i) {
        try {
            if (a(i)) {
                t.append('\\');
                if ((i & (-65536)) != 0) {
                    t.append('U');
                    t.append(f796a[15 & (i >> 28)]);
                    t.append(f796a[15 & (i >> 24)]);
                    t.append(f796a[15 & (i >> 20)]);
                    t.append(f796a[15 & (i >> 16)]);
                } else {
                    t.append('u');
                }
                t.append(f796a[15 & (i >> 12)]);
                t.append(f796a[15 & (i >> 8)]);
                t.append(f796a[15 & (i >> 4)]);
                t.append(f796a[15 & i]);
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new l(e);
        }
    }
}
