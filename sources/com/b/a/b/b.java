package com.b.a.b;

import com.b.a.a.ab;

/* loaded from: infinitode-2.jar:com/b/a/b/b.class */
public final class b {

    /* loaded from: infinitode-2.jar:com/b/a/b/b$a.class */
    public enum a {
        NOT_ENCODED,
        UNKNOWN,
        EXCLUDED,
        LIMITED_USE,
        ASPIRATIONAL,
        RECOMMENDED
    }

    public static final int a(int i) {
        if ((i >= 0) & (i <= 1114111)) {
            int a2 = ab.f780a.a(i, 0) & 12583167;
            if (a2 < 4194304) {
                return a2;
            }
            if (a2 < 8388608) {
                return 0;
            }
            if (a2 >= 12582912) {
                return ab.f780a.f781b[a2 & 255];
            }
            return 1;
        }
        throw new IllegalArgumentException(Integer.toString(i));
    }

    static {
        a.values();
    }
}
