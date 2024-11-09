package org.b.a.a;

/* loaded from: infinitode-2.jar:org/b/a/a/d.class */
public final class d implements b {
    @Override // org.b.a.a.b
    public final org.b.a.c a(CharSequence charSequence, int i, int i2) {
        int b2;
        int i3 = i + 3;
        if (i3 < charSequence.length() && charSequence.charAt(i + 1) == '/' && charSequence.charAt(i + 2) == '/' && (b2 = b(charSequence, i - 1, i2)) != -1) {
            return new a(org.b.a.d.URL, b2, c.a(charSequence, i3) + 1);
        }
        return null;
    }

    private static int b(CharSequence charSequence, int i, int i2) {
        int i3 = -1;
        int i4 = -1;
        for (int i5 = i; i5 >= i2; i5--) {
            char charAt = charSequence.charAt(i5);
            if (c.a(charAt)) {
                i3 = i5;
            } else if (c.b(charAt)) {
                i4 = i5;
            } else if (!a(charAt)) {
                break;
            }
        }
        if (i3 > 0 && i3 - 1 == i4) {
            i3 = -1;
        }
        return i3;
    }

    private static boolean a(char c) {
        switch (c) {
            case '+':
            case '-':
            case '.':
                return true;
            case ',':
            default:
                return false;
        }
    }
}
