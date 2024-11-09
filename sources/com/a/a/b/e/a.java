package com.a.a.b.e;

import com.a.a.b.f;
import com.a.a.b.g.g;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/b/e/a.class */
public final class a {
    private a h;
    private AtomicReference<C0001a> i;
    private int j;
    private boolean k;
    private boolean l;

    /* renamed from: a, reason: collision with root package name */
    protected int[] f132a;

    /* renamed from: b, reason: collision with root package name */
    protected int f133b;
    private int m;
    private int n;
    protected int c;
    protected int d;
    protected String[] e;
    protected int f;
    protected int g;
    private boolean o;

    private a(int i, int i2) {
        this.h = null;
        this.d = 0;
        this.o = true;
        this.j = i2;
        this.k = false;
        this.l = true;
        this.i = new AtomicReference<>(C0001a.a(64));
    }

    private a(a aVar, int i, C0001a c0001a, boolean z, boolean z2) {
        this.h = aVar;
        this.j = i;
        this.k = z;
        this.l = z2;
        this.i = null;
        this.d = c0001a.f135b;
        this.f133b = c0001a.f134a;
        this.m = this.f133b << 2;
        this.n = this.m + (this.m >> 1);
        this.c = c0001a.c;
        this.f132a = c0001a.d;
        this.e = c0001a.e;
        this.f = c0001a.f;
        this.g = c0001a.g;
        this.o = true;
    }

    public static a a() {
        long currentTimeMillis = System.currentTimeMillis();
        return d((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    private static a d(int i) {
        return new a(64, i);
    }

    public final a a(int i) {
        return new a(this, this.j, this.i.get(), f.a.INTERN_FIELD_NAMES.a(i), f.a.FAIL_ON_SYMBOL_HASH_OVERFLOW.a(i));
    }

    public final void b() {
        if (this.h != null && c()) {
            this.h.a(new C0001a(this));
            this.o = true;
        }
    }

    private void a(C0001a c0001a) {
        int i = c0001a.f135b;
        C0001a c0001a2 = this.i.get();
        if (i == c0001a2.f135b) {
            return;
        }
        if (i > 6000) {
            c0001a = C0001a.a(64);
        }
        this.i.compareAndSet(c0001a2, c0001a);
    }

    private boolean c() {
        return !this.o;
    }

    private int d() {
        int i = 0;
        int i2 = this.m;
        for (int i3 = 3; i3 < i2; i3 += 4) {
            if (this.f132a[i3] != 0) {
                i++;
            }
        }
        return i;
    }

    private int e() {
        int i = 0;
        int i2 = this.n;
        for (int i3 = this.m + 3; i3 < i2; i3 += 4) {
            if (this.f132a[i3] != 0) {
                i++;
            }
        }
        return i;
    }

    private int f() {
        int i = 0;
        int i2 = this.n + 3;
        int i3 = i2 + this.f133b;
        for (int i4 = i2; i4 < i3; i4 += 4) {
            if (this.f132a[i4] != 0) {
                i++;
            }
        }
        return i;
    }

    private int g() {
        return (this.f - l()) >> 2;
    }

    private int h() {
        int i = 0;
        int i2 = this.f133b << 3;
        for (int i3 = 3; i3 < i2; i3 += 4) {
            if (this.f132a[i3] != 0) {
                i++;
            }
        }
        return i;
    }

    public final String toString() {
        int d = d();
        int e = e();
        int f = f();
        int g = g();
        return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", getClass().getName(), Integer.valueOf(this.d), Integer.valueOf(this.f133b), Integer.valueOf(d), Integer.valueOf(e), Integer.valueOf(f), Integer.valueOf(g), Integer.valueOf(d + e + f + g), Integer.valueOf(h()));
    }

    public final String b(int i) {
        int e = e(h(i));
        int[] iArr = this.f132a;
        int i2 = iArr[e + 3];
        if (i2 == 1) {
            if (iArr[e] == i) {
                return this.e[e >> 2];
            }
        } else if (i2 == 0) {
            return null;
        }
        int i3 = this.m + ((e >> 3) << 2);
        int i4 = iArr[i3 + 3];
        if (i4 == 1) {
            if (iArr[i3] == i) {
                return this.e[i3 >> 2];
            }
        } else if (i4 == 0) {
            return null;
        }
        return b(e, i);
    }

    public final String a(int i, int i2) {
        int e = e(c(i, i2));
        int[] iArr = this.f132a;
        int i3 = iArr[e + 3];
        if (i3 == 2) {
            if (i == iArr[e] && i2 == iArr[e + 1]) {
                return this.e[e >> 2];
            }
        } else if (i3 == 0) {
            return null;
        }
        int i4 = this.m + ((e >> 3) << 2);
        int i5 = iArr[i4 + 3];
        if (i5 == 2) {
            if (i == iArr[i4] && i2 == iArr[i4 + 1]) {
                return this.e[i4 >> 2];
            }
        } else if (i5 == 0) {
            return null;
        }
        return b(e, i, i2);
    }

    public final String a(int i, int i2, int i3) {
        int e = e(c(i, i2, i3));
        int[] iArr = this.f132a;
        int i4 = iArr[e + 3];
        if (i4 == 3) {
            if (i == iArr[e] && iArr[e + 1] == i2 && iArr[e + 2] == i3) {
                return this.e[e >> 2];
            }
        } else if (i4 == 0) {
            return null;
        }
        int i5 = this.m + ((e >> 3) << 2);
        int i6 = iArr[i5 + 3];
        if (i6 == 3) {
            if (i == iArr[i5] && iArr[i5 + 1] == i2 && iArr[i5 + 2] == i3) {
                return this.e[i5 >> 2];
            }
        } else if (i6 == 0) {
            return null;
        }
        return a(e, i, i2, i3);
    }

    public final String a(int[] iArr, int i) {
        if (i < 4) {
            switch (i) {
                case 1:
                    return b(iArr[0]);
                case 2:
                    return a(iArr[0], iArr[1]);
                case 3:
                    return a(iArr[0], iArr[1], iArr[2]);
                default:
                    return "";
            }
        }
        int c = c(iArr, i);
        int e = e(c);
        int[] iArr2 = this.f132a;
        int i2 = iArr2[e + 3];
        if (c == iArr2[e] && i2 == i && a(iArr, i, iArr2[e + 1])) {
            return this.e[e >> 2];
        }
        if (i2 == 0) {
            return null;
        }
        int i3 = this.m + ((e >> 3) << 2);
        int i4 = iArr2[i3 + 3];
        if (c == iArr2[i3] && i4 == i && a(iArr, i, iArr2[i3 + 1])) {
            return this.e[i3 >> 2];
        }
        return a(e, c, iArr, i);
    }

    private final int e(int i) {
        return (i & (this.f133b - 1)) << 2;
    }

    private String b(int i, int i2) {
        int i3 = this.n + ((i >> (this.c + 2)) << this.c);
        int[] iArr = this.f132a;
        int i4 = i3 + (1 << this.c);
        while (i3 < i4) {
            int i5 = iArr[i3 + 3];
            if (i2 == iArr[i3] && 1 == i5) {
                return this.e[i3 >> 2];
            }
            if (i5 != 0) {
                i3 += 4;
            } else {
                return null;
            }
        }
        for (int l = l(); l < this.f; l += 4) {
            if (i2 == iArr[l] && 1 == iArr[l + 3]) {
                return this.e[l >> 2];
            }
        }
        return null;
    }

    private String b(int i, int i2, int i3) {
        int i4 = this.n + ((i >> (this.c + 2)) << this.c);
        int[] iArr = this.f132a;
        int i5 = i4 + (1 << this.c);
        while (i4 < i5) {
            int i6 = iArr[i4 + 3];
            if (i2 == iArr[i4] && i3 == iArr[i4 + 1] && 2 == i6) {
                return this.e[i4 >> 2];
            }
            if (i6 != 0) {
                i4 += 4;
            } else {
                return null;
            }
        }
        for (int l = l(); l < this.f; l += 4) {
            if (i2 == iArr[l] && i3 == iArr[l + 1] && 2 == iArr[l + 3]) {
                return this.e[l >> 2];
            }
        }
        return null;
    }

    private String a(int i, int i2, int i3, int i4) {
        int i5 = this.n + ((i >> (this.c + 2)) << this.c);
        int[] iArr = this.f132a;
        int i6 = i5 + (1 << this.c);
        while (i5 < i6) {
            int i7 = iArr[i5 + 3];
            if (i2 == iArr[i5] && i3 == iArr[i5 + 1] && i4 == iArr[i5 + 2] && 3 == i7) {
                return this.e[i5 >> 2];
            }
            if (i7 != 0) {
                i5 += 4;
            } else {
                return null;
            }
        }
        for (int l = l(); l < this.f; l += 4) {
            if (i2 == iArr[l] && i3 == iArr[l + 1] && i4 == iArr[l + 2] && 3 == iArr[l + 3]) {
                return this.e[l >> 2];
            }
        }
        return null;
    }

    private String a(int i, int i2, int[] iArr, int i3) {
        int i4 = this.n + ((i >> (this.c + 2)) << this.c);
        int[] iArr2 = this.f132a;
        int i5 = i4 + (1 << this.c);
        while (i4 < i5) {
            int i6 = iArr2[i4 + 3];
            if (i2 == iArr2[i4] && i3 == i6 && a(iArr, i3, iArr2[i4 + 1])) {
                return this.e[i4 >> 2];
            }
            if (i6 != 0) {
                i4 += 4;
            } else {
                return null;
            }
        }
        for (int l = l(); l < this.f; l += 4) {
            if (i2 == iArr2[l] && i3 == iArr2[l + 3] && a(iArr, i3, iArr2[l + 1])) {
                return this.e[l >> 2];
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0090 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(int[] r6, int r7, int r8) {
        /*
            Method dump skipped, instructions count: 199
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.e.a.a(int[], int, int):boolean");
    }

    private boolean b(int[] iArr, int i, int i2) {
        int i3 = 0;
        do {
            int i4 = i3;
            i3++;
            int i5 = i2;
            i2++;
            if (iArr[i4] != this.f132a[i5]) {
                return false;
            }
        } while (i3 < i);
        return true;
    }

    public final String a(String str, int[] iArr, int i) {
        int f;
        i();
        if (this.k) {
            str = g.f159a.a(str);
        }
        switch (i) {
            case 1:
                f = f(h(iArr[0]));
                this.f132a[f] = iArr[0];
                this.f132a[f + 3] = 1;
                break;
            case 2:
                f = f(c(iArr[0], iArr[1]));
                this.f132a[f] = iArr[0];
                this.f132a[f + 1] = iArr[1];
                this.f132a[f + 3] = 2;
                break;
            case 3:
                f = f(c(iArr[0], iArr[1], iArr[2]));
                this.f132a[f] = iArr[0];
                this.f132a[f + 1] = iArr[1];
                this.f132a[f + 2] = iArr[2];
                this.f132a[f + 3] = 3;
                break;
            default:
                int c = c(iArr, i);
                f = f(c);
                this.f132a[f] = c;
                this.f132a[f + 1] = b(iArr, i);
                this.f132a[f + 3] = i;
                break;
        }
        this.e[f >> 2] = str;
        this.d++;
        return str;
    }

    private void i() {
        if (this.o) {
            if (this.h == null) {
                if (this.d == 0) {
                    throw new IllegalStateException("Cannot add names to Root symbol table");
                }
                throw new IllegalStateException("Cannot add names to Placeholder symbol table");
            }
            this.f132a = Arrays.copyOf(this.f132a, this.f132a.length);
            this.e = (String[]) Arrays.copyOf(this.e, this.e.length);
            this.o = false;
        }
    }

    private int f(int i) {
        int e = e(i);
        int[] iArr = this.f132a;
        if (iArr[e + 3] == 0) {
            return e;
        }
        if (j()) {
            return g(i);
        }
        int i2 = this.m + ((e >> 3) << 2);
        if (iArr[i2 + 3] == 0) {
            return i2;
        }
        int i3 = this.n + ((e >> (this.c + 2)) << this.c);
        int i4 = i3 + (1 << this.c);
        while (i3 < i4) {
            if (iArr[i3 + 3] != 0) {
                i3 += 4;
            } else {
                return i3;
            }
        }
        int i5 = this.f;
        this.f += 4;
        if (this.f >= (this.f133b << 3)) {
            if (this.l) {
                m();
            }
            return g(i);
        }
        return i5;
    }

    private int g(int i) {
        k();
        int e = e(i);
        int[] iArr = this.f132a;
        if (iArr[e + 3] == 0) {
            return e;
        }
        int i2 = this.m + ((e >> 3) << 2);
        if (iArr[i2 + 3] == 0) {
            return i2;
        }
        int i3 = this.n + ((e >> (this.c + 2)) << this.c);
        int i4 = i3 + (1 << this.c);
        while (i3 < i4) {
            if (iArr[i3 + 3] != 0) {
                i3 += 4;
            } else {
                return i3;
            }
        }
        int i5 = this.f;
        this.f += 4;
        return i5;
    }

    private boolean j() {
        if (this.d > (this.f133b >> 1)) {
            if (((this.f - l()) >> 2) > ((1 + this.d) >> 7) || this.d > this.f133b * 0.8d) {
                return true;
            }
            return false;
        }
        return false;
    }

    private int b(int[] iArr, int i) {
        int i2 = this.g;
        if (i2 + i > this.f132a.length) {
            this.f132a = Arrays.copyOf(this.f132a, this.f132a.length + Math.max((i2 + i) - this.f132a.length, Math.min(4096, this.f133b)));
        }
        System.arraycopy(iArr, 0, this.f132a, i2, i);
        this.g += i;
        return i2;
    }

    private int h(int i) {
        int i2 = i ^ this.j;
        int i3 = i2 + (i2 >>> 16);
        int i4 = i3 ^ (i3 << 3);
        return i4 + (i4 >>> 12);
    }

    private int c(int i, int i2) {
        int i3 = i + (i >>> 15);
        int i4 = ((i3 ^ (i3 >>> 9)) + (i2 * 33)) ^ this.j;
        int i5 = i4 + (i4 >>> 16);
        int i6 = i5 ^ (i5 >>> 4);
        return i6 + (i6 << 3);
    }

    private int c(int i, int i2, int i3) {
        int i4 = i ^ this.j;
        int i5 = (((i4 + (i4 >>> 9)) * 31) + i2) * 33;
        int i6 = (i5 + (i5 >>> 15)) ^ i3;
        int i7 = i6 + (i6 >>> 4);
        int i8 = i7 + (i7 >>> 15);
        return i8 ^ (i8 << 9);
    }

    private int c(int[] iArr, int i) {
        if (i < 4) {
            throw new IllegalArgumentException();
        }
        int i2 = iArr[0] ^ this.j;
        int i3 = i2 + (i2 >>> 9) + iArr[1];
        int i4 = ((i3 + (i3 >>> 15)) * 33) ^ iArr[2];
        int i5 = i4 + (i4 >>> 4);
        for (int i6 = 3; i6 < i; i6++) {
            int i7 = iArr[i6];
            i5 += i7 ^ (i7 >> 21);
        }
        int i8 = i5 * 65599;
        int i9 = i8 + (i8 >>> 19);
        return i9 ^ (i9 << 5);
    }

    private void k() {
        this.o = false;
        int[] iArr = this.f132a;
        String[] strArr = this.e;
        int i = this.f133b;
        int i2 = this.d;
        int i3 = i + i;
        int i4 = this.f;
        if (i3 > 65536) {
            a(true);
            return;
        }
        this.f132a = new int[iArr.length + (i << 3)];
        this.f133b = i3;
        this.m = i3 << 2;
        this.n = this.m + (this.m >> 1);
        this.c = c(i3);
        this.e = new String[strArr.length << 1];
        a(false);
        int i5 = 0;
        int[] iArr2 = new int[16];
        for (int i6 = 0; i6 < i4; i6 += 4) {
            int i7 = iArr[i6 + 3];
            if (i7 != 0) {
                i5++;
                String str = strArr[i6 >> 2];
                switch (i7) {
                    case 1:
                        iArr2[0] = iArr[i6];
                        a(str, iArr2, 1);
                        break;
                    case 2:
                        iArr2[0] = iArr[i6];
                        iArr2[1] = iArr[i6 + 1];
                        a(str, iArr2, 2);
                        break;
                    case 3:
                        iArr2[0] = iArr[i6];
                        iArr2[1] = iArr[i6 + 1];
                        iArr2[2] = iArr[i6 + 2];
                        a(str, iArr2, 3);
                        break;
                    default:
                        if (i7 > iArr2.length) {
                            iArr2 = new int[i7];
                        }
                        System.arraycopy(iArr, iArr[i6 + 1], iArr2, 0, i7);
                        a(str, iArr2, i7);
                        break;
                }
            }
        }
        if (i5 != i2) {
            throw new IllegalStateException("Failed rehash(): old count=" + i2 + ", copyCount=" + i5);
        }
    }

    private void a(boolean z) {
        this.d = 0;
        this.f = l();
        this.g = this.f133b << 3;
        if (z) {
            Arrays.fill(this.f132a, 0);
            Arrays.fill(this.e, (Object) null);
        }
    }

    private final int l() {
        int i = this.f133b;
        return (i << 3) - i;
    }

    private void m() {
        if (this.f133b <= 1024) {
        } else {
            throw new IllegalStateException("Spill-over slots in symbol table with " + this.d + " entries, hash area of " + this.f133b + " slots is now full (all " + (this.f133b >> 3) + " slots -- suspect a DoS attack based on hash collisions. You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`");
        }
    }

    static int c(int i) {
        int i2 = i >> 2;
        if (i2 < 64) {
            return 4;
        }
        if (i2 <= 256) {
            return 5;
        }
        if (i2 <= 1024) {
            return 6;
        }
        return 7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.a.a.b.e.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/b/e/a$a.class */
    public static final class C0001a {

        /* renamed from: a, reason: collision with root package name */
        public final int f134a;

        /* renamed from: b, reason: collision with root package name */
        public final int f135b;
        public final int c;
        public final int[] d;
        public final String[] e;
        public final int f;
        public final int g;

        private C0001a(int i, int i2, int i3, int[] iArr, String[] strArr, int i4, int i5) {
            this.f134a = i;
            this.f135b = 0;
            this.c = i3;
            this.d = iArr;
            this.e = strArr;
            this.f = i4;
            this.g = i5;
        }

        public C0001a(a aVar) {
            this.f134a = aVar.f133b;
            this.f135b = aVar.d;
            this.c = aVar.c;
            this.d = aVar.f132a;
            this.e = aVar.e;
            this.f = aVar.f;
            this.g = aVar.g;
        }

        public static C0001a a(int i) {
            int i2 = i << 3;
            return new C0001a(i, 0, a.c(i), new int[i2], new String[i << 1], i2 - i, i2);
        }
    }
}
