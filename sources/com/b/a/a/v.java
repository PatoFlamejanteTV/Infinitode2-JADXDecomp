package com.b.a.a;

import com.b.a.a.s;
import java.util.Iterator;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/b/a/a/v.class */
public class v extends s {
    private int[] n;
    private int[] o;
    private int[] p;
    private int q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private int[] v;
    private boolean w;
    private static /* synthetic */ boolean x;

    static {
        x = !v.class.desiredAssertionStatus();
    }

    public v(int i, int i2) {
        this.n = new int[User32.WM_MDICREATE];
        this.o = new int[35488];
        this.v = new int[34852];
        this.w = false;
        e(0, 0);
    }

    private void e(int i, int i2) {
        this.h = i;
        this.i = i2;
        this.j = 1114112;
        this.p = new int[16384];
        this.r = 16384;
        this.h = i;
        this.i = i2;
        this.j = 1114112;
        this.s = 0;
        this.u = false;
        int i3 = 0;
        while (i3 < 128) {
            this.p[i3] = this.h;
            i3++;
        }
        while (i3 < 192) {
            this.p[i3] = this.i;
            i3++;
        }
        for (int i4 = 192; i4 < 256; i4++) {
            this.p[i4] = this.h;
        }
        this.l = 192;
        this.f = 256;
        int i5 = 0;
        int i6 = 0;
        while (i6 < 128) {
            this.o[i5] = i6;
            this.v[i5] = 1;
            i5++;
            i6 += 32;
        }
        while (i6 < 192) {
            this.v[i5] = 0;
            i5++;
            i6 += 32;
        }
        this.v[6] = 34845;
        while (true) {
            i6 += 32;
            if (i6 >= 256) {
                break;
            } else {
                this.v[7] = 0;
            }
        }
        for (int i7 = 4; i7 < 2080; i7++) {
            this.o[i7] = 192;
        }
        for (int i8 = 0; i8 < 576; i8++) {
            this.o[i8 + 2080] = -1;
        }
        for (int i9 = 0; i9 < 64; i9++) {
            this.o[i9 + 2656] = 192;
        }
        this.t = 2656;
        this.q = 2720;
        int i10 = 0;
        int i11 = 0;
        while (i10 < 32) {
            this.n[i10] = i11;
            i10++;
            i11 += 64;
        }
        while (i10 < 544) {
            this.n[i10] = 2656;
            i10++;
        }
        for (int i12 = 128; i12 < 2048; i12 += 32) {
            d(i12, this.h);
        }
    }

    private v(s sVar) {
        this.n = new int[User32.WM_MDICREATE];
        this.o = new int[35488];
        this.v = new int[34852];
        this.w = false;
        e(sVar.h, sVar.i);
        Iterator<s.a> it = sVar.iterator();
        while (it.hasNext()) {
            a(it.next(), true);
        }
    }

    private boolean a(int i, boolean z) {
        int i2;
        if (Character.isHighSurrogate((char) i)) {
            i2 = GLFW.GLFW_KEY_KP_0 + (i >> 5);
        } else {
            i2 = this.n[i >> 11] + ((i >> 5) & 63);
        }
        return this.o[i2] == this.l;
    }

    private int c() {
        int i = this.q;
        int i2 = i + 64;
        if (i2 > this.o.length) {
            throw new IllegalStateException("Internal error in Trie2 creation.");
        }
        this.q = i2;
        System.arraycopy(this.o, this.t, this.o, i, 64);
        return i;
    }

    private int b(int i, boolean z) {
        if (i >= 55296 && i < 56320 && z) {
            return 2048;
        }
        int i2 = i >> 11;
        int i3 = this.n[i2];
        int i4 = i3;
        if (i3 == this.t) {
            i4 = c();
            this.n[i2] = i4;
        }
        return i4;
    }

    private int b(int i) {
        int i2;
        int i3;
        if (this.s != 0) {
            i2 = this.s;
            this.s = -this.v[i2 >> 5];
        } else {
            int i4 = this.f;
            i2 = i4;
            int i5 = i4 + 32;
            if (i5 > this.r) {
                if (this.r < 131072) {
                    i3 = 131072;
                } else if (this.r < 1115264) {
                    i3 = 1115264;
                } else {
                    throw new IllegalStateException("Internal error in Trie2 creation.");
                }
                int[] iArr = new int[i3];
                System.arraycopy(this.p, 0, iArr, 0, this.f);
                this.p = iArr;
                this.r = i3;
            }
            this.f = i5;
        }
        int[] iArr2 = this.p;
        System.arraycopy(iArr2, i, iArr2, i2, 32);
        this.v[i2 >> 5] = 0;
        return i2;
    }

    private void c(int i) {
        this.v[i >> 5] = -this.s;
        this.s = i;
    }

    private boolean d(int i) {
        return i != this.l && 1 == this.v[i >> 5];
    }

    private void f(int i, int i2) {
        int[] iArr = this.v;
        int i3 = i2 >> 5;
        iArr[i3] = iArr[i3] + 1;
        int i4 = this.o[i];
        int[] iArr2 = this.v;
        int i5 = i4 >> 5;
        int i6 = iArr2[i5] - 1;
        iArr2[i5] = i6;
        if (0 == i6) {
            c(i4);
        }
        this.o[i] = i2;
    }

    private int c(int i, boolean z) {
        int b2 = b(i, z) + ((i >> 5) & 63);
        int i2 = this.o[b2];
        if (d(i2)) {
            return i2;
        }
        int b3 = b(i2);
        f(b2, b3);
        return b3;
    }

    public final v d(int i, int i2) {
        if (i < 0 || i > 1114111) {
            throw new IllegalArgumentException("Invalid code point.");
        }
        a(i, true, i2);
        this.m = 0;
        return this;
    }

    private v a(int i, boolean z, int i2) {
        if (this.u) {
            d();
        }
        this.p[c(i, z) + (i & 31)] = i2;
        return this;
    }

    private void d() {
        v vVar = new v(this);
        this.n = vVar.n;
        this.o = vVar.o;
        this.p = vVar.p;
        this.q = vVar.q;
        this.r = vVar.r;
        this.u = vVar.u;
        this.f828a = vVar.f828a;
        this.f829b = vVar.f829b;
        this.c = vVar.c;
        this.d = vVar.d;
        this.e = vVar.e;
        this.f = vVar.f;
        this.t = vVar.t;
        this.h = vVar.h;
        this.i = vVar.i;
        this.j = vVar.j;
        this.k = vVar.k;
        this.l = vVar.l;
    }

    private void g(int i, int i2) {
        int i3 = i + 32;
        while (i < i3) {
            int i4 = i;
            i++;
            this.p[i4] = i2;
        }
    }

    private void a(int i, int i2, int i3, int i4, int i5, boolean z) {
        int i6 = i + i3;
        if (z) {
            for (int i7 = i + i2; i7 < i6; i7++) {
                this.p[i7] = i4;
            }
            return;
        }
        for (int i8 = i + i2; i8 < i6; i8++) {
            if (this.p[i8] == i5) {
                this.p[i8] = i4;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0129, code lost:            if (r0 == r8.l) goto L54;     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0134  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.b.a.a.v a(int r9, int r10, int r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.v.a(int, int, int, boolean):com.b.a.a.v");
    }

    private v a(s.a aVar, boolean z) {
        this.m = 0;
        if (aVar.d) {
            for (int i = aVar.f830a; i <= aVar.f831b; i++) {
                a((char) i, aVar.c);
            }
        } else {
            a(aVar.f830a, aVar.f831b, aVar.c, true);
        }
        return this;
    }

    private v a(char c, int i) {
        this.m = 0;
        a((int) c, false, i);
        return this;
    }

    @Override // com.b.a.a.s
    public final int a(int i) {
        if (i < 0 || i > 1114111) {
            return this.i;
        }
        return d(i, true);
    }

    private int d(int i, boolean z) {
        int i2;
        if (i >= this.j && (i < 55296 || i >= 56320 || z)) {
            return this.p[this.f - 4];
        }
        if (i >= 55296 && i < 56320 && z) {
            i2 = GLFW.GLFW_KEY_KP_0 + (i >> 5);
        } else {
            i2 = this.n[i >> 11] + ((i >> 5) & 63);
        }
        return this.p[this.o[i2] + (i & 31)];
    }

    @Override // com.b.a.a.s
    public final int a(char c) {
        return d((int) c, false);
    }

    private static boolean a(int[] iArr, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (iArr[i + i4] != iArr[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    private int h(int i, int i2) {
        int i3 = i - 64;
        for (int i4 = 0; i4 <= i3; i4++) {
            if (a(this.o, i4, i2, 64)) {
                return i4;
            }
        }
        return -1;
    }

    private int b(int i, int i2, int i3) {
        int i4 = i - i3;
        for (int i5 = 0; i5 <= i4; i5 += 4) {
            if (a(this.p, i5, i2, i3)) {
                return i5;
            }
        }
        return -1;
    }

    private int e(int i) {
        int i2;
        int i3;
        if (i == this.h) {
            i2 = this.t;
            i3 = this.l;
        } else {
            i2 = -1;
            i3 = -1;
        }
        int i4 = 544;
        int i5 = 1114112;
        while (i5 > 0) {
            i4--;
            int i6 = this.n[i4];
            if (i6 == i2) {
                i5 -= 2048;
            } else {
                i2 = i6;
                if (i6 == this.t) {
                    if (i != this.h) {
                        return i5;
                    }
                    i5 -= 2048;
                } else {
                    int i7 = 64;
                    while (i7 > 0) {
                        i7--;
                        int i8 = this.o[i6 + i7];
                        if (i8 == i3) {
                            i5 -= 32;
                        } else {
                            i3 = i8;
                            if (i8 == this.l) {
                                if (i != this.h) {
                                    return i5;
                                }
                                i5 -= 32;
                            } else {
                                int i9 = 32;
                                while (i9 > 0) {
                                    i9--;
                                    if (this.p[i8 + i9] != i) {
                                        return i5;
                                    }
                                    i5--;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private void e() {
        int i = 192;
        int i2 = 0;
        int i3 = 0;
        while (i2 < 192) {
            this.v[i3] = i2;
            i2 += 32;
            i3++;
        }
        int i4 = 64;
        int i5 = 2;
        int i6 = 192;
        while (i6 < this.f) {
            if (i6 == 2176) {
                i4 = 32;
                i5 = 1;
            }
            if (this.v[i6 >> 5] <= 0) {
                i6 += i4;
            } else {
                int b2 = b(i, i6, i4);
                int i7 = b2;
                if (b2 >= 0) {
                    int i8 = i6 >> 5;
                    for (int i9 = i5; i9 > 0; i9--) {
                        int i10 = i8;
                        i8++;
                        this.v[i10] = i7;
                        i7 += 32;
                    }
                    i6 += i4;
                } else {
                    int i11 = i4 - 4;
                    while (i11 > 0 && !a(this.p, i - i11, i6, i11)) {
                        i11 -= 4;
                    }
                    if (i11 > 0 || i < i6) {
                        int i12 = i - i11;
                        int i13 = i6 >> 5;
                        for (int i14 = i5; i14 > 0; i14--) {
                            int i15 = i13;
                            i13++;
                            this.v[i15] = i12;
                            i12 += 32;
                        }
                        i6 += i11;
                        for (int i16 = i4 - i11; i16 > 0; i16--) {
                            int i17 = i;
                            i++;
                            int i18 = i6;
                            i6++;
                            this.p[i17] = this.p[i18];
                        }
                    } else {
                        int i19 = i6 >> 5;
                        for (int i20 = i5; i20 > 0; i20--) {
                            int i21 = i19;
                            i19++;
                            this.v[i21] = i6;
                            i6 += 32;
                        }
                        i = i6;
                    }
                }
            }
        }
        int i22 = 0;
        while (i22 < this.q) {
            if (i22 == 2080) {
                i22 += User32.WM_TOUCH;
            }
            this.o[i22] = this.v[this.o[i22] >> 5];
            i22++;
        }
        this.l = this.v[this.l >> 5];
        while ((i & 3) != 0) {
            int i23 = i;
            i++;
            this.p[i23] = this.h;
        }
        this.f = i;
    }

    private void f() {
        int i = 0;
        int i2 = 0;
        while (i < 2080) {
            this.v[i2] = i;
            i += 64;
            i2++;
        }
        int i3 = 2080 + 32 + ((this.j - 65536) >> 11);
        int i4 = 2656;
        while (i4 < this.q) {
            int h = h(i3, i4);
            if (h >= 0) {
                this.v[i4 >> 6] = h;
                i4 += 64;
            } else {
                int i5 = 63;
                while (i5 > 0 && !a(this.o, i3 - i5, i4, i5)) {
                    i5--;
                }
                if (i5 > 0 || i3 < i4) {
                    this.v[i4 >> 6] = i3 - i5;
                    i4 += i5;
                    for (int i6 = 64 - i5; i6 > 0; i6--) {
                        int i7 = i3;
                        i3++;
                        int i8 = i4;
                        i4++;
                        this.o[i7] = this.o[i8];
                    }
                } else {
                    this.v[i4 >> 6] = i4;
                    i4 += 64;
                    i3 = i4;
                }
            }
        }
        for (int i9 = 0; i9 < 544; i9++) {
            this.n[i9] = this.v[this.n[i9] >> 6];
        }
        this.t = this.v[this.t >> 6];
        while ((i3 & 3) != 0) {
            int i10 = i3;
            i3++;
            this.o[i10] = 262140;
        }
        this.q = i3;
    }

    private void g() {
        int a2 = a(1114111);
        int e = (e(a2) + 2047) & (-2048);
        if (e == 1114112) {
            a2 = this.i;
        }
        this.j = e;
        if (this.j < 1114112) {
            a(this.j <= 65536 ? 65536 : this.j, 1114111, this.h, true);
        }
        e();
        if (this.j > 65536) {
            f();
        }
        int[] iArr = this.p;
        int i = this.f;
        this.f = i + 1;
        iArr[i] = a2;
        while ((this.f & 3) != 0) {
            int[] iArr2 = this.p;
            int i2 = this.f;
            this.f = i2 + 1;
            iArr2[i2] = this.h;
        }
        this.u = true;
    }

    public final y b() {
        y yVar = new y();
        a(yVar, s.e.BITS_32);
        return yVar;
    }

    private void a(s sVar, s.e eVar) {
        int i;
        int i2;
        if (!this.u) {
            g();
        }
        if (this.j <= 65536) {
            i = 2112;
        } else {
            i = this.q;
        }
        if (eVar == s.e.BITS_16) {
            i2 = i;
        } else {
            i2 = 0;
        }
        if (i > 65535 || i2 + this.l > 65535 || i2 + 2176 > 65535 || i2 + this.f > 262140) {
            throw new UnsupportedOperationException("Trie2 data is too large.");
        }
        int i3 = i;
        if (eVar == s.e.BITS_16) {
            i3 += this.f;
        } else {
            sVar.d = new int[this.f];
        }
        sVar.f829b = new char[i3];
        sVar.e = i;
        sVar.f = this.f;
        if (this.j <= 65536) {
            sVar.g = 65535;
        } else {
            sVar.g = 0 + this.t;
        }
        sVar.h = this.h;
        sVar.i = this.i;
        sVar.j = this.j;
        sVar.k = (i2 + this.f) - 4;
        sVar.l = i2 + this.l;
        sVar.f828a = new s.c();
        sVar.f828a.f834a = 1416784178;
        sVar.f828a.f835b = eVar == s.e.BITS_16 ? 0 : 1;
        sVar.f828a.c = sVar.e;
        sVar.f828a.d = sVar.f >> 2;
        sVar.f828a.e = sVar.g;
        sVar.f828a.f = sVar.l;
        sVar.f828a.g = sVar.j >> 11;
        int i4 = 0;
        for (int i5 = 0; i5 < 2080; i5++) {
            int i6 = i4;
            i4++;
            sVar.f829b[i6] = (char) ((this.o[i5] + i2) >> 2);
        }
        int i7 = 0;
        while (i7 < 2) {
            int i8 = i4;
            i4++;
            sVar.f829b[i8] = (char) (i2 + 128);
            i7++;
        }
        while (i7 < 32) {
            int i9 = i4;
            i4++;
            sVar.f829b[i9] = (char) (i2 + this.o[i7 << 1]);
            i7++;
        }
        if (this.j > 65536) {
            int i10 = (this.j - 65536) >> 11;
            int i11 = i10 + 2112;
            for (int i12 = 0; i12 < i10; i12++) {
                int i13 = i4;
                i4++;
                sVar.f829b[i13] = (char) (0 + this.n[i12 + 32]);
            }
            for (int i14 = 0; i14 < this.q - i11; i14++) {
                int i15 = i4;
                i4++;
                sVar.f829b[i15] = (char) ((i2 + this.o[i11 + i14]) >> 2);
            }
        }
        switch (eVar) {
            case BITS_16:
                if (!x && i4 != i2) {
                    throw new AssertionError();
                }
                sVar.c = i4;
                for (int i16 = 0; i16 < this.f; i16++) {
                    int i17 = i4;
                    i4++;
                    sVar.f829b[i17] = (char) this.p[i16];
                }
                return;
            case BITS_32:
                for (int i18 = 0; i18 < this.f; i18++) {
                    sVar.d[i18] = this.p[i18];
                }
                return;
            default:
                return;
        }
    }
}
