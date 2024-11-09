package org.b.a.a;

/* loaded from: infinitode-2.jar:org/b/a/a/e.class */
public final class e implements b {
    @Override // org.b.a.a.b
    public final org.b.a.c a(CharSequence charSequence, int i, int i2) {
        int b2;
        int a2;
        int i3 = i + 4;
        if (i3 < charSequence.length() && b(charSequence, i) && (b2 = b(charSequence, i, i2)) != -1 && (a2 = a(charSequence, i3)) != -1) {
            return new a(org.b.a.d.WWW, b2, a2 + 1);
        }
        return null;
    }

    private static int b(CharSequence charSequence, int i, int i2) {
        if (i == i2) {
            return i;
        }
        if (a(charSequence.charAt(i - 1))) {
            return i;
        }
        return -1;
    }

    private static int a(CharSequence charSequence, int i) {
        int a2 = c.a(charSequence, i);
        int i2 = a2;
        while (true) {
            i2--;
            if (i2 > i) {
                if (charSequence.charAt(i2) == '.' && i2 > i) {
                    return a2;
                }
            } else {
                return -1;
            }
        }
    }

    private static boolean a(char c) {
        return (c == '.' || c.c(c)) ? false : true;
    }

    private static boolean b(CharSequence charSequence, int i) {
        return charSequence.charAt(i + 1) == 'w' && charSequence.charAt(i + 2) == 'w' && charSequence.charAt(i + 3) == '.';
    }
}
