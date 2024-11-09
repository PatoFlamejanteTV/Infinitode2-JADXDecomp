package com.a.a.b.g;

import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/b/g/o.class */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    private static char[] f164a = new char[0];

    /* renamed from: b, reason: collision with root package name */
    private final a f165b;
    private char[] c;
    private int d;
    private int e;
    private ArrayList<char[]> f;
    private boolean g;
    private int h;
    private char[] i;
    private int j;
    private String k;
    private char[] l;

    public o(a aVar) {
        this.f165b = aVar;
    }

    private o(a aVar, char[] cArr) {
        this.f165b = aVar;
        this.i = cArr;
        this.j = cArr.length;
        this.d = -1;
    }

    public static o a(char[] cArr) {
        return new o(null, cArr);
    }

    public final void a() {
        this.d = -1;
        this.j = 0;
        this.e = 0;
        this.c = null;
        this.l = null;
        if (this.g) {
            l();
        }
        if (this.f165b != null && this.i != null) {
            char[] cArr = this.i;
            this.i = null;
            this.f165b.a(2, cArr);
        }
    }

    public final void a(char[] cArr, int i, int i2) {
        this.k = null;
        this.l = null;
        this.c = cArr;
        this.d = i;
        this.e = i2;
        if (this.g) {
            l();
        }
    }

    public final void b(char[] cArr, int i, int i2) {
        this.c = null;
        this.d = -1;
        this.e = 0;
        this.k = null;
        this.l = null;
        if (this.g) {
            l();
        } else if (this.i == null) {
            this.i = c(i2);
        }
        this.h = 0;
        this.j = 0;
        c(cArr, i, i2);
    }

    public final void a(String str) {
        this.c = null;
        this.d = -1;
        this.e = 0;
        this.k = str;
        this.l = null;
        if (this.g) {
            l();
        }
        this.j = 0;
    }

    private char[] c(int i) {
        if (this.f165b != null) {
            return this.f165b.a(2, i);
        }
        return new char[Math.max(i, 500)];
    }

    private void l() {
        this.g = false;
        this.f.clear();
        this.h = 0;
        this.j = 0;
    }

    public final int b() {
        if (this.d >= 0) {
            return this.e;
        }
        if (this.l != null) {
            return this.l.length;
        }
        if (this.k != null) {
            return this.k.length();
        }
        return this.h + this.j;
    }

    public final int c() {
        if (this.d >= 0) {
            return this.d;
        }
        return 0;
    }

    public final char[] d() {
        if (this.d >= 0) {
            return this.c;
        }
        if (this.l != null) {
            return this.l;
        }
        if (this.k != null) {
            char[] charArray = this.k.toCharArray();
            this.l = charArray;
            return charArray;
        }
        if (this.g) {
            return f();
        }
        return this.i == null ? f164a : this.i;
    }

    public final String e() {
        if (this.k == null) {
            if (this.l != null) {
                this.k = new String(this.l);
            } else if (this.d >= 0) {
                if (this.e <= 0) {
                    this.k = "";
                    return "";
                }
                this.k = new String(this.c, this.d, this.e);
            } else {
                int i = this.h;
                int i2 = this.j;
                if (i == 0) {
                    this.k = i2 == 0 ? "" : new String(this.i, 0, i2);
                } else {
                    StringBuilder sb = new StringBuilder(i + i2);
                    if (this.f != null) {
                        int size = this.f.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            char[] cArr = this.f.get(i3);
                            sb.append(cArr, 0, cArr.length);
                        }
                    }
                    sb.append(this.i, 0, this.j);
                    this.k = sb.toString();
                }
            }
        }
        return this.k;
    }

    public final char[] f() {
        char[] cArr = this.l;
        char[] cArr2 = cArr;
        if (cArr == null) {
            char[] n = n();
            cArr2 = n;
            this.l = n;
        }
        return cArr2;
    }

    public final double a(boolean z) {
        return com.a.a.b.c.h.b(e(), z);
    }

    public final float b(boolean z) {
        return com.a.a.b.c.h.c(e(), z);
    }

    public final int c(boolean z) {
        if (this.d >= 0 && this.c != null) {
            if (z) {
                return -com.a.a.b.c.h.a(this.c, this.d + 1, this.e - 1);
            }
            return com.a.a.b.c.h.a(this.c, this.d, this.e);
        }
        if (z) {
            return -com.a.a.b.c.h.a(this.i, 1, this.j - 1);
        }
        return com.a.a.b.c.h.a(this.i, 0, this.j);
    }

    public final long d(boolean z) {
        if (this.d >= 0 && this.c != null) {
            if (z) {
                return -com.a.a.b.c.h.b(this.c, this.d + 1, this.e - 1);
            }
            return com.a.a.b.c.h.b(this.c, this.d, this.e);
        }
        if (z) {
            return -com.a.a.b.c.h.b(this.i, 1, this.j - 1);
        }
        return com.a.a.b.c.h.b(this.i, 0, this.j);
    }

    public final void a(char c) {
        if (this.d >= 0) {
            d(16);
        }
        this.k = null;
        this.l = null;
        char[] cArr = this.i;
        if (this.j >= cArr.length) {
            m();
            cArr = this.i;
        }
        int i = this.j;
        this.j = i + 1;
        cArr[i] = c;
    }

    public final void c(char[] cArr, int i, int i2) {
        int i3;
        if (this.d >= 0) {
            d(i2);
        }
        this.k = null;
        this.l = null;
        char[] cArr2 = this.i;
        int length = cArr2.length - this.j;
        if (length >= i2) {
            System.arraycopy(cArr, i, cArr2, this.j, i2);
            this.j += i2;
            return;
        }
        if (length > 0) {
            System.arraycopy(cArr, i, cArr2, this.j, length);
            i += length;
            i2 -= length;
        }
        do {
            m();
            int min = Math.min(this.i.length, i2);
            System.arraycopy(cArr, i, this.i, 0, min);
            this.j += min;
            i += min;
            i3 = i2 - min;
            i2 = i3;
        } while (i3 > 0);
    }

    public final void a(String str, int i, int i2) {
        int i3;
        if (this.d >= 0) {
            d(i2);
        }
        this.k = null;
        this.l = null;
        char[] cArr = this.i;
        int length = cArr.length - this.j;
        if (length >= i2) {
            str.getChars(i, i + i2, cArr, this.j);
            this.j += i2;
            return;
        }
        if (length > 0) {
            str.getChars(i, i + length, cArr, this.j);
            i2 -= length;
            i += length;
        }
        do {
            m();
            int min = Math.min(this.i.length, i2);
            int i4 = i;
            str.getChars(i4, i4 + min, this.i, 0);
            this.j += min;
            i += min;
            i3 = i2 - min;
            i2 = i3;
        } while (i3 > 0);
    }

    public final char[] g() {
        if (this.d >= 0) {
            d(1);
        } else {
            char[] cArr = this.i;
            if (cArr != null) {
                if (this.j >= cArr.length) {
                    m();
                }
            } else {
                this.i = c(0);
            }
        }
        return this.i;
    }

    public final char[] h() {
        this.d = -1;
        this.j = 0;
        this.e = 0;
        this.c = null;
        this.k = null;
        this.l = null;
        if (this.g) {
            l();
        }
        char[] cArr = this.i;
        char[] cArr2 = cArr;
        if (cArr == null) {
            char[] c = c(0);
            cArr2 = c;
            this.i = c;
        }
        return cArr2;
    }

    public final int i() {
        return this.j;
    }

    public final void a(int i) {
        this.j = i;
    }

    public final String b(int i) {
        this.j = i;
        if (this.h > 0) {
            return e();
        }
        int i2 = this.j;
        String str = i2 == 0 ? "" : new String(this.i, 0, i2);
        this.k = str;
        return str;
    }

    public final char[] j() {
        if (this.f == null) {
            this.f = new ArrayList<>();
        }
        this.g = true;
        this.f.add(this.i);
        int length = this.i.length;
        this.h += length;
        this.j = 0;
        int i = length + (length >> 1);
        int i2 = i;
        if (i < 500) {
            i2 = 500;
        } else if (i2 > 65536) {
            i2 = 65536;
        }
        char[] e = e(i2);
        this.i = e;
        return e;
    }

    public final char[] k() {
        char[] cArr = this.i;
        int length = cArr.length;
        int i = length + (length >> 1);
        int i2 = i;
        if (i > 65536) {
            i2 = length + (length >> 2);
        }
        char[] copyOf = Arrays.copyOf(cArr, i2);
        this.i = copyOf;
        return copyOf;
    }

    public final String toString() {
        return e();
    }

    private void d(int i) {
        int i2 = this.e;
        this.e = 0;
        char[] cArr = this.c;
        this.c = null;
        int i3 = this.d;
        this.d = -1;
        int i4 = i2 + i;
        if (this.i == null || i4 > this.i.length) {
            this.i = c(i4);
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, this.i, 0, i2);
        }
        this.h = 0;
        this.j = i2;
    }

    private void m() {
        if (this.f == null) {
            this.f = new ArrayList<>();
        }
        char[] cArr = this.i;
        this.g = true;
        this.f.add(cArr);
        this.h += cArr.length;
        this.j = 0;
        int length = cArr.length;
        int i = length + (length >> 1);
        int i2 = i;
        if (i < 500) {
            i2 = 500;
        } else if (i2 > 65536) {
            i2 = 65536;
        }
        this.i = e(i2);
    }

    private char[] n() {
        if (this.k != null) {
            return this.k.toCharArray();
        }
        if (this.d >= 0) {
            int i = this.e;
            if (i <= 0) {
                return f164a;
            }
            int i2 = this.d;
            if (i2 == 0) {
                return Arrays.copyOf(this.c, i);
            }
            return Arrays.copyOfRange(this.c, i2, i2 + i);
        }
        int b2 = b();
        if (b2 <= 0) {
            return f164a;
        }
        int i3 = 0;
        char[] e = e(b2);
        if (this.f != null) {
            int size = this.f.size();
            for (int i4 = 0; i4 < size; i4++) {
                char[] cArr = this.f.get(i4);
                int length = cArr.length;
                System.arraycopy(cArr, 0, e, i3, length);
                i3 += length;
            }
        }
        System.arraycopy(this.i, 0, e, i3, this.j);
        return e;
    }

    private static char[] e(int i) {
        return new char[i];
    }
}
