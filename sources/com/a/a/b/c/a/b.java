package com.a.a.b.c.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/a/a/b/c/a/b.class */
public abstract class b extends a {
    abstract long a();

    abstract long b();

    abstract long c();

    abstract long a(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    abstract long b(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    private static boolean a(char c) {
        return '0' <= c && c <= '9';
    }

    private long a(CharSequence charSequence, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        char charAt;
        boolean z3;
        int i6;
        int a2;
        long j = 0;
        int i7 = -1;
        boolean z4 = false;
        char c = 0;
        while (i < i3) {
            char charAt2 = charSequence.charAt(i);
            c = charAt2;
            if (a(charAt2)) {
                j = ((10 * j) + c) - 48;
            } else {
                if (c != '.') {
                    break;
                }
                z4 |= i7 >= 0;
                i7 = i;
                while (i < i3 - 8 && (a2 = a(charSequence, i + 1)) >= 0) {
                    j = (100000000 * j) + a2;
                    i += 8;
                }
            }
            i++;
        }
        int i8 = i;
        if (i7 < 0) {
            i4 = i8 - i;
            i7 = i8;
            i5 = 0;
        } else {
            i4 = (i8 - i) - 1;
            i5 = (i7 - i8) + 1;
        }
        int i9 = 0;
        if (c == 'e' || c == 'E') {
            i++;
            char charAt3 = i < i3 ? charSequence.charAt(i) : (char) 0;
            c = charAt3;
            boolean z5 = charAt3 == '-';
            boolean z6 = z5;
            if (z5 || c == '+') {
                i++;
                c = i < i3 ? charSequence.charAt(i) : (char) 0;
            }
            z4 |= !a(c);
            do {
                if (i9 < 1024) {
                    i9 = ((i9 * 10) + c) - 48;
                }
                i++;
                charAt = i < i3 ? charSequence.charAt(i) : (char) 0;
                c = charAt;
            } while (a(charAt));
            if (z6) {
                i9 = -i9;
            }
            i5 += i9;
        }
        if (i < i3 && (c == 'd' || c == 'D' || c == 'f' || c == 'F')) {
            i++;
        }
        int c2 = c(charSequence, i, i3);
        if (z4 || c2 < i3) {
            return -1L;
        }
        if (!z2 && i4 == 0) {
            return -1L;
        }
        int i10 = 0;
        if (i4 > 19) {
            j = 0;
            int i11 = i;
            while (i11 < i8) {
                char charAt4 = charSequence.charAt(i11);
                if (charAt4 == '.') {
                    i10++;
                } else {
                    if (Long.compareUnsigned(j, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j = ((10 * j) + charAt4) - 48;
                }
                i11++;
            }
            z3 = i11 < i8;
            i6 = (i7 - i11) + i10 + i9;
        } else {
            z3 = false;
            i6 = 0;
        }
        return a(charSequence, i2, i3, z, j, i5, z3, i6);
    }

    public final long a(CharSequence charSequence, int i, int i2) {
        int i3 = i + i2;
        if (i < 0 || i3 > charSequence.length()) {
            return -1L;
        }
        int c = c(charSequence, i, i3);
        int i4 = c;
        if (c == i3) {
            return -1L;
        }
        char charAt = charSequence.charAt(i4);
        char c2 = charAt;
        boolean z = charAt == '-';
        boolean z2 = z;
        if (z || c2 == '+') {
            i4++;
            char charAt2 = i4 < i3 ? charSequence.charAt(i4) : (char) 0;
            c2 = charAt2;
            if (charAt2 == 0) {
                return -1L;
            }
        }
        if (c2 >= 'I') {
            if (c2 == 'N') {
                return b(charSequence, i4, i3);
            }
            return a(charSequence, i4, i3, z2);
        }
        boolean z3 = c2 == '0';
        boolean z4 = z3;
        if (z3) {
            i4++;
            char charAt3 = i4 < i3 ? charSequence.charAt(i4) : (char) 0;
            char c3 = charAt3;
            if (charAt3 == 'x' || c3 == 'X') {
                return a(charSequence, i4 + 1, i, i3, z2);
            }
        }
        return a(charSequence, i4, i, i3, z2, z4);
    }

    private long a(CharSequence charSequence, int i, int i2, int i3, boolean z) {
        int i4;
        boolean z2;
        char charAt;
        long j = 0;
        int i5 = 0;
        int i6 = -1;
        boolean z3 = false;
        char c = 0;
        while (i < i3) {
            char charAt2 = charSequence.charAt(i);
            c = charAt2;
            byte b2 = charAt2 > 127 ? (byte) -1 : a.f93a[c];
            byte b3 = b2;
            if (b2 >= 0) {
                j = (j << 4) | b3;
            } else {
                if (b3 != -4) {
                    break;
                }
                z3 |= i6 >= 0;
                i6 = i;
            }
            i++;
        }
        int i7 = i;
        if (i6 < 0) {
            i4 = i7 - i;
            i6 = i7;
        } else {
            i4 = (i7 - i) - 1;
            i5 = Math.min((i6 - i) + 1, 1024) << 2;
        }
        int i8 = 0;
        boolean z4 = c == 'p' || c == 'P';
        boolean z5 = z4;
        if (z4) {
            i++;
            char charAt3 = i < i3 ? charSequence.charAt(i) : (char) 0;
            c = charAt3;
            boolean z6 = charAt3 == '-';
            boolean z7 = z6;
            if (z6 || c == '+') {
                i++;
                c = i < i3 ? charSequence.charAt(i) : (char) 0;
            }
            z3 |= !a(c);
            do {
                if (i8 < 1024) {
                    i8 = ((i8 * 10) + c) - 48;
                }
                i++;
                charAt = i < i3 ? charSequence.charAt(i) : (char) 0;
                c = charAt;
            } while (a(charAt));
            if (z7) {
                i8 = -i8;
            }
            i5 += i8;
        }
        if (i < i3 && (c == 'd' || c == 'D' || c == 'f' || c == 'F')) {
            i++;
        }
        int c2 = c(charSequence, i, i3);
        if (z3 || c2 < i3 || i4 == 0 || !z5) {
            return -1L;
        }
        int i9 = 0;
        if (i4 > 16) {
            j = 0;
            c2 = i;
            while (c2 < i7) {
                char charAt4 = charSequence.charAt(c2);
                byte b4 = charAt4 > 127 ? (byte) -1 : a.f93a[charAt4];
                byte b5 = b4;
                if (b4 >= 0) {
                    if (Long.compareUnsigned(j, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j = (j << 4) | b5;
                } else {
                    i9++;
                }
                c2++;
            }
            z2 = c2 < i7;
        } else {
            z2 = false;
        }
        return b(charSequence, i2, i3, z, j, i5, z2, (i6 - c2) + i9 + i8);
    }

    private long a(CharSequence charSequence, int i, int i2, boolean z) {
        if (i + 7 < i2 && charSequence.charAt(i) == 'I' && charSequence.charAt(i + 1) == 'n' && charSequence.charAt(i + 2) == 'f' && charSequence.charAt(i + 3) == 'i' && charSequence.charAt(i + 4) == 'n' && charSequence.charAt(i + 5) == 'i' && charSequence.charAt(i + 6) == 't' && charSequence.charAt(i + 7) == 'y' && c(charSequence, i + 8, i2) == i2) {
            return z ? b() : c();
        }
        return -1L;
    }

    private long b(CharSequence charSequence, int i, int i2) {
        if (i + 2 < i2 && charSequence.charAt(i + 1) == 'a' && charSequence.charAt(i + 2) == 'N' && c(charSequence, i + 3, i2) == i2) {
            return a();
        }
        return -1L;
    }

    private static int c(CharSequence charSequence, int i, int i2) {
        while (i < i2 && charSequence.charAt(i) <= ' ') {
            i++;
        }
        return i;
    }

    private static int a(CharSequence charSequence, int i) {
        return e.a(charSequence.charAt(i) | (charSequence.charAt(i + 1) << 16) | (charSequence.charAt(i + 2) << 32) | (charSequence.charAt(i + 3) << 48), charSequence.charAt(i + 4) | (charSequence.charAt(i + 5) << 16) | (charSequence.charAt(i + 6) << 32) | (charSequence.charAt(i + 7) << 48));
    }
}
