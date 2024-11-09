package com.b.a.a;

import com.b.a.c.i;
import java.util.ArrayList;

/* loaded from: infinitode-2.jar:com/b/a/a/az.class */
public class az {

    /* renamed from: a, reason: collision with root package name */
    private com.b.a.c.i f792a = new com.b.a.c.i(0, 1114111);

    /* renamed from: b, reason: collision with root package name */
    private com.b.a.c.i f793b;
    private ArrayList<String> c;
    private short[] d;
    private final int e;
    private boolean f;
    private boolean g;
    private a h;

    public az(com.b.a.c.i iVar, ArrayList<String> arrayList, int i) {
        int i2;
        int i3;
        this.c = arrayList;
        this.g = i == 127;
        this.f792a.a(iVar);
        if (0 != (i & 1)) {
            this.f793b = this.f792a;
        }
        this.h = new a();
        int size = this.c.size();
        int i4 = 0;
        this.f = false;
        for (int i5 = 0; i5 < size; i5++) {
            String str = this.c.get(i5);
            int length = str.length();
            if (this.f792a.a(str, i.b.CONTAINED) < length) {
                this.f = true;
            }
            if (length > i4) {
                i4 = length;
            }
        }
        this.e = i4;
        if (!this.f && (i & 64) == 0) {
            return;
        }
        if (this.g) {
            this.f792a.a();
        }
        if (this.g) {
            i2 = size << 1;
        } else {
            i2 = size;
        }
        this.d = new short[i2];
        if (this.g) {
            i3 = size;
        } else {
            i3 = 0;
        }
        for (int i6 = 0; i6 < size; i6++) {
            String str2 = this.c.get(i6);
            int length2 = str2.length();
            int a2 = this.f792a.a(str2, i.b.CONTAINED);
            if (a2 < length2) {
                if (0 != (i & 2)) {
                    if (0 != (i & 32)) {
                        this.d[i6] = c(a2);
                    }
                    if (0 != (i & 16)) {
                        this.d[i3 + i6] = c(length2 - this.f792a.b(str2, length2, i.b.CONTAINED));
                    }
                } else {
                    short[] sArr = this.d;
                    sArr[i3 + i6] = 0;
                    sArr[i6] = 0;
                }
                if (0 != (i & 1)) {
                    if (0 != (i & 32)) {
                        b(str2.codePointAt(0));
                    }
                    if (0 != (i & 16)) {
                        b(str2.codePointBefore(length2));
                    }
                }
            } else if (this.g) {
                short[] sArr2 = this.d;
                sArr2[i3 + i6] = 255;
                sArr2[i6] = 255;
            } else {
                this.d[i6] = 255;
            }
        }
        if (this.g) {
            this.f793b.a();
        }
    }

    public final boolean a() {
        return this.f;
    }

    public final boolean a(int i) {
        return this.f792a.b(i);
    }

    private void b(int i) {
        if (ba.a(this.f793b, (Object) null) || ba.a(this.f793b, this.f792a)) {
            if (this.f792a.b(i)) {
                return;
            } else {
                this.f793b = this.f792a.b();
            }
        }
        this.f793b.a(i);
    }

    public final int a(CharSequence charSequence, int i, i.b bVar) {
        if (bVar == i.b.NOT_CONTAINED) {
            return b(charSequence, i, (com.a.a.a.am) null);
        }
        int a2 = this.f792a.a(charSequence, i, i.b.CONTAINED);
        if (a2 == charSequence.length()) {
            return a2;
        }
        return a(charSequence, i, a2, bVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x01cb, code lost:            return r0;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized int a(java.lang.CharSequence r7, int r8, int r9, com.b.a.c.i.b r10) {
        /*
            Method dump skipped, instructions count: 555
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.az.a(java.lang.CharSequence, int, int, com.b.a.c.i$b):int");
    }

    public final int a(CharSequence charSequence, int i, i.b bVar, com.a.a.a.am amVar) {
        int i2;
        if (bVar == i.b.NOT_CONTAINED) {
            return b(charSequence, i, amVar);
        }
        if (bVar == i.b.CONTAINED) {
            return a(charSequence, i, amVar);
        }
        int size = this.c.size();
        int length = charSequence.length();
        int i3 = i;
        int i4 = 0;
        for (int i5 = length - i; i5 != 0; i5 -= i2) {
            int a2 = a(this.f792a, charSequence, i3, i5);
            i2 = a2 > 0 ? a2 : 0;
            for (int i6 = 0; i6 < size; i6++) {
                String str = this.c.get(i6);
                int length2 = str.length();
                if (i2 < length2 && length2 <= i5 && a(charSequence, i3, length, str, length2)) {
                    i2 = length2;
                }
            }
            if (i2 == 0) {
                amVar.f47a = i4;
                return i3;
            }
            i4++;
            i3 += i2;
        }
        amVar.f47a = i4;
        return i3;
    }

    private synchronized int a(CharSequence charSequence, int i, com.a.a.a.am amVar) {
        this.h.a(this.e);
        int size = this.c.size();
        int length = charSequence.length();
        int i2 = i;
        int i3 = length - i;
        int i4 = 0;
        while (i3 != 0) {
            int a2 = a(this.f792a, charSequence, i2, i3);
            if (a2 > 0) {
                this.h.a(a2, i4 + 1);
            }
            for (int i5 = 0; i5 < size; i5++) {
                String str = this.c.get(i5);
                int length2 = str.length();
                if (length2 <= i3 && !this.h.b(length2, i4 + 1) && a(charSequence, i2, length, str, length2)) {
                    this.h.a(length2, i4 + 1);
                }
            }
            if (this.h.a()) {
                amVar.f47a = i4;
                return i2;
            }
            int a3 = this.h.a(amVar);
            i4 = amVar.f47a;
            i2 += a3;
            i3 -= a3;
        }
        amVar.f47a = i4;
        return i2;
    }

    public final synchronized int b(CharSequence charSequence, int i, i.b bVar) {
        if (bVar == i.b.NOT_CONTAINED) {
            return a(charSequence, i);
        }
        int b2 = this.f792a.b(charSequence, i, i.b.CONTAINED);
        int i2 = b2;
        if (b2 == 0) {
            return 0;
        }
        int i3 = i - i2;
        int i4 = 0;
        if (bVar == i.b.CONTAINED) {
            i4 = this.e;
        }
        this.h.a(i4);
        int size = this.c.size();
        int i5 = 0;
        if (this.g) {
            i5 = size;
        }
        while (true) {
            if (bVar == i.b.CONTAINED) {
                for (int i6 = 0; i6 < size; i6++) {
                    short s = this.d[i5 + i6];
                    int i7 = s;
                    if (s != 255) {
                        String str = this.c.get(i6);
                        int length = str.length();
                        if (i7 >= 254) {
                            i7 = length - str.offsetByCodePoints(0, 1);
                        }
                        if (i7 > i3) {
                            i7 = i3;
                        }
                        for (int i8 = length - i7; i8 <= i2; i8++) {
                            if (!this.h.d(i8) && a(charSequence, i2 - i8, i, str, length)) {
                                if (i8 == i2) {
                                    return 0;
                                }
                                this.h.c(i8);
                            }
                            if (i7 != 0) {
                                i7--;
                            }
                        }
                    }
                }
            } else {
                int i9 = 0;
                int i10 = 0;
                for (int i11 = 0; i11 < size; i11++) {
                    int i12 = this.d[i5 + i11];
                    String str2 = this.c.get(i11);
                    int length2 = str2.length();
                    if (i12 >= 254) {
                        i12 = length2;
                    }
                    if (i12 > i3) {
                        i12 = i3;
                    }
                    int i13 = length2 - i12;
                    while (true) {
                        if (i13 <= i2 && i12 >= i10) {
                            if ((i12 > i10 || i13 > i9) && a(charSequence, i2 - i13, i, str2, length2)) {
                                i9 = i13;
                                i10 = i12;
                                break;
                            }
                            i12--;
                            i13++;
                        }
                    }
                }
                if (i9 != 0 || i10 != 0) {
                    int i14 = i2 - i9;
                    i2 = i14;
                    if (i14 == 0) {
                        return 0;
                    }
                    i3 = 0;
                }
            }
            if (i3 != 0 || i2 == i) {
                if (this.h.a()) {
                    return i2;
                }
            } else if (this.h.a()) {
                int i15 = i2;
                i2 = this.f792a.b(charSequence, i15, i.b.CONTAINED);
                i3 = i15 - i2;
                if (i2 == 0 || i3 == 0) {
                    break;
                }
            } else {
                int a2 = a(this.f792a, charSequence, i2);
                if (a2 > 0) {
                    if (a2 != i2) {
                        i2 -= a2;
                        this.h.b(a2);
                        i3 = 0;
                    } else {
                        return 0;
                    }
                }
            }
            i2 -= this.h.a((com.a.a.a.am) null);
            i3 = 0;
        }
        return i2;
    }

    private int b(CharSequence charSequence, int i, com.a.a.a.am amVar) {
        int a2;
        int i2;
        int a3;
        String str;
        int length;
        int length2 = charSequence.length();
        int i3 = i;
        int size = this.c.size();
        int i4 = 0;
        do {
            if (amVar == null) {
                a2 = this.f793b.a(charSequence, i3, i.b.NOT_CONTAINED);
            } else {
                a2 = this.f793b.a(charSequence, i3, i.b.NOT_CONTAINED, amVar);
                int i5 = i4 + amVar.f47a;
                i4 = i5;
                amVar.f47a = i5;
            }
            if (a2 == length2) {
                return length2;
            }
            int i6 = a2;
            i2 = length2 - a2;
            a3 = a(this.f792a, charSequence, i6, i2);
            if (a3 > 0) {
                return i6;
            }
            for (int i7 = 0; i7 < size; i7++) {
                if (this.d[i7] != 255 && (length = (str = this.c.get(i7)).length()) <= i2 && a(charSequence, i6, length2, str, length)) {
                    return i6;
                }
            }
            i3 = i6 - a3;
            i4++;
        } while (i2 + a3 != 0);
        if (amVar != null) {
            amVar.f47a = i4;
        }
        return length2;
    }

    private int a(CharSequence charSequence, int i) {
        int i2;
        String str;
        int length;
        int i3 = i;
        int size = this.c.size();
        do {
            int b2 = this.f793b.b(charSequence, i3, i.b.NOT_CONTAINED);
            if (b2 != 0) {
                int a2 = a(this.f792a, charSequence, b2);
                if (a2 > 0) {
                    return b2;
                }
                for (int i4 = 0; i4 < size; i4++) {
                    if (this.d[i4] != 255 && (length = (str = this.c.get(i4)).length()) <= b2 && a(charSequence, b2 - length, i, str, length)) {
                        return b2;
                    }
                }
                i2 = b2 + a2;
                i3 = i2;
            } else {
                return 0;
            }
        } while (i2 != 0);
        return 0;
    }

    private static short c(int i) {
        if (i < 254) {
            return (short) i;
        }
        return (short) 254;
    }

    private static boolean a(CharSequence charSequence, int i, String str, int i2) {
        int i3 = i + i2;
        do {
            int i4 = i2;
            i2--;
            if (i4 <= 0) {
                return true;
            }
            i3--;
        } while (charSequence.charAt(i3) == str.charAt(i2));
        return false;
    }

    private static boolean a(CharSequence charSequence, int i, int i2, String str, int i3) {
        if (!a(charSequence, i, str, i3)) {
            return false;
        }
        if (i > 0 && Character.isHighSurrogate(charSequence.charAt(i - 1)) && Character.isLowSurrogate(charSequence.charAt(i))) {
            return false;
        }
        return (i + i3 < i2 && Character.isHighSurrogate(charSequence.charAt((i + i3) - 1)) && Character.isLowSurrogate(charSequence.charAt(i + i3))) ? false : true;
    }

    private static int a(com.b.a.c.i iVar, CharSequence charSequence, int i, int i2) {
        char charAt = charSequence.charAt(i);
        if (charAt >= 55296 && charAt <= 56319 && i2 >= 2) {
            char charAt2 = charSequence.charAt(i + 1);
            if (com.a.a.a.am.b(charAt2)) {
                return iVar.b(Character.toCodePoint(charAt, charAt2)) ? 2 : -2;
            }
        }
        return iVar.b(charAt) ? 1 : -1;
    }

    private static int a(com.b.a.c.i iVar, CharSequence charSequence, int i) {
        char charAt = charSequence.charAt(i - 1);
        if (charAt >= 56320 && charAt <= 57343 && i >= 2) {
            char charAt2 = charSequence.charAt(i - 2);
            if (com.a.a.a.am.c(charAt2)) {
                return iVar.b(Character.toCodePoint(charAt2, charAt)) ? 2 : -2;
            }
        }
        return iVar.b(charAt) ? 1 : -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/az$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private int[] f794a = new int[16];

        /* renamed from: b, reason: collision with root package name */
        private int f795b;
        private int c;
        private static /* synthetic */ boolean d;

        static {
            d = !az.class.desiredAssertionStatus();
        }

        public final void a(int i) {
            if (i > this.f794a.length) {
                this.f794a = new int[i];
            }
            b();
        }

        private void b() {
            int length = this.f794a.length;
            while (true) {
                int i = length;
                length--;
                if (i > 0) {
                    this.f794a[length] = 0;
                } else {
                    this.f795b = 0;
                    this.c = 0;
                    return;
                }
            }
        }

        public final boolean a() {
            return this.f795b == 0;
        }

        public final void b(int i) {
            int i2 = this.c + i;
            int i3 = i2;
            if (i2 >= this.f794a.length) {
                i3 -= this.f794a.length;
            }
            if (this.f794a[i3] != 0) {
                this.f794a[i3] = 0;
                this.f795b--;
            }
            this.c = i3;
        }

        public final void c(int i) {
            int i2 = this.c + i;
            int i3 = i2;
            if (i2 >= this.f794a.length) {
                i3 -= this.f794a.length;
            }
            if (!d && this.f794a[i3] != 0) {
                throw new AssertionError();
            }
            this.f794a[i3] = 1;
            this.f795b++;
        }

        public final void a(int i, int i2) {
            if (!d && i2 <= 0) {
                throw new AssertionError();
            }
            int i3 = this.c + i;
            int i4 = i3;
            if (i3 >= this.f794a.length) {
                i4 -= this.f794a.length;
            }
            if (this.f794a[i4] == 0) {
                this.f794a[i4] = i2;
                this.f795b++;
            } else if (i2 < this.f794a[i4]) {
                this.f794a[i4] = i2;
            }
        }

        public final boolean d(int i) {
            int i2 = this.c + i;
            int i3 = i2;
            if (i2 >= this.f794a.length) {
                i3 -= this.f794a.length;
            }
            return this.f794a[i3] != 0;
        }

        public final boolean b(int i, int i2) {
            int i3 = this.c + i;
            int i4 = i3;
            if (i3 >= this.f794a.length) {
                i4 -= this.f794a.length;
            }
            int i5 = this.f794a[i4];
            return i5 != 0 && i5 <= i2;
        }

        public final int a(com.a.a.a.am amVar) {
            int i;
            int i2;
            int i3 = this.c;
            do {
                i3++;
                if (i3 < this.f794a.length) {
                    i2 = this.f794a[i3];
                } else {
                    int length = this.f794a.length - this.c;
                    int i4 = 0;
                    while (true) {
                        i = this.f794a[i4];
                        if (i != 0) {
                            break;
                        }
                        i4++;
                    }
                    this.f794a[i4] = 0;
                    this.f795b--;
                    this.c = i4;
                    if (amVar != null) {
                        amVar.f47a = i;
                    }
                    return length + i4;
                }
            } while (i2 == 0);
            this.f794a[i3] = 0;
            this.f795b--;
            int i5 = i3 - this.c;
            this.c = i3;
            if (amVar != null) {
                amVar.f47a = i2;
            }
            return i5;
        }
    }
}
