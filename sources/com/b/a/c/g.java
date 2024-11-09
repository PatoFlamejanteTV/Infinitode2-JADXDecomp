package com.b.a.c;

import java.nio.BufferOverflowException;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/b/a/c/g.class */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private char[] f867a = new char[100];

    /* renamed from: b, reason: collision with root package name */
    private int f868b;
    private int c;

    public final void a() {
        this.c = 0;
        this.f868b = 0;
    }

    private void b(int i) {
        this.f867a[this.f868b - 1] = (char) i;
    }

    private int e() {
        if (this.f868b > 0) {
            return this.f867a[this.f868b - 1];
        }
        return 65535;
    }

    public final void a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("addUnchanged(" + i + "): length must not be negative");
        }
        int e = e();
        if (e < 4095) {
            int i2 = 4095 - e;
            if (i2 >= i) {
                b(e + i);
                return;
            } else {
                b(4095);
                i -= i2;
            }
        }
        while (i >= 4096) {
            c(4095);
            i -= 4096;
        }
        if (i > 0) {
            c(i - 1);
        }
    }

    public final void a(int i, int i2) {
        int i3;
        int i4;
        if (i == i2 && i > 0 && i <= 6) {
            int e = e();
            if (4095 < e && e < 28671 && (e >> 12) == i && (e & 4095) < 4095) {
                b(e + 1);
                return;
            } else {
                c(i << 12);
                return;
            }
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("addReplace(" + i + ", " + i2 + "): both lengths must be non-negative");
        }
        if (i == 0 && i2 == 0) {
            return;
        }
        int i5 = i2 - i;
        if (i5 != 0) {
            if ((i5 > 0 && this.c >= 0 && i5 > Integer.MAX_VALUE - this.c) || (i5 < 0 && this.c < 0 && i5 < Integer.MIN_VALUE - this.c)) {
                throw new IndexOutOfBoundsException();
            }
            this.c += i5;
        }
        if (i < 61 && i2 < 61) {
            c(28672 | (i << 6) | i2);
            return;
        }
        if (this.f867a.length - this.f868b < 5) {
            f();
        }
        int i6 = this.f868b + 1;
        if (i < 61) {
            i3 = 28672 | (i << 6);
        } else if (i <= 32767) {
            i3 = 32576;
            i6++;
            this.f867a[i6] = (char) (32768 | i);
        } else {
            i3 = 28672 | ((62 + (i >> 30)) << 6);
            int i7 = i6 + 1;
            this.f867a[i6] = (char) (32768 | (i >> 15));
            i6 = i7 + 1;
            this.f867a[i7] = (char) (32768 | i);
        }
        if (i2 < 61) {
            i4 = i3 | i2;
        } else if (i2 <= 32767) {
            i4 = i3 | 61;
            int i8 = i6;
            i6++;
            this.f867a[i8] = (char) (32768 | i2);
        } else {
            i4 = i3 | (62 + (i2 >> 30));
            int i9 = i6;
            int i10 = i6 + 1;
            this.f867a[i9] = (char) (32768 | (i2 >> 15));
            i6 = i10 + 1;
            this.f867a[i10] = (char) (32768 | i2);
        }
        this.f867a[this.f868b] = (char) i4;
        this.f868b = i6;
    }

    private void c(int i) {
        if (this.f868b >= this.f867a.length) {
            f();
        }
        char[] cArr = this.f867a;
        int i2 = this.f868b;
        this.f868b = i2 + 1;
        cArr[i2] = (char) i;
    }

    private boolean f() {
        int length;
        if (this.f867a.length == 100) {
            length = 2000;
        } else {
            if (this.f867a.length == Integer.MAX_VALUE) {
                throw new BufferOverflowException();
            }
            if (this.f867a.length >= 1073741823) {
                length = Integer.MAX_VALUE;
            } else {
                length = 2 * this.f867a.length;
            }
        }
        if (length - this.f867a.length < 5) {
            throw new BufferOverflowException();
        }
        this.f867a = Arrays.copyOf(this.f867a, length);
        return true;
    }

    public final int b() {
        return this.c;
    }

    public final boolean c() {
        if (this.c != 0) {
            return true;
        }
        for (int i = 0; i < this.f868b; i++) {
            if (this.f867a[i] > 4095) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/b/a/c/g$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final char[] f869a;

        /* renamed from: b, reason: collision with root package name */
        private int f870b;
        private final int c;
        private int d;
        private final boolean e;
        private final boolean f;
        private boolean g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int l;
        private static /* synthetic */ boolean m;

        /* synthetic */ a(char[] cArr, int i, boolean z, boolean z2, byte b2) {
            this(cArr, i, false, true);
        }

        static {
            m = !g.class.desiredAssertionStatus();
        }

        private a(char[] cArr, int i, boolean z, boolean z2) {
            this.f869a = cArr;
            this.c = i;
            this.e = z;
            this.f = z2;
        }

        private int a(int i) {
            if (i < 61) {
                return i;
            }
            if (i < 62) {
                if (!m && this.f870b >= this.c) {
                    throw new AssertionError();
                }
                if (!m && this.f869a[this.f870b] < 32768) {
                    throw new AssertionError();
                }
                char[] cArr = this.f869a;
                int i2 = this.f870b;
                this.f870b = i2 + 1;
                return cArr[i2] & 32767;
            }
            if (!m && this.f870b + 2 > this.c) {
                throw new AssertionError();
            }
            if (!m && this.f869a[this.f870b] < 32768) {
                throw new AssertionError();
            }
            if (!m && this.f869a[this.f870b + 1] < 32768) {
                throw new AssertionError();
            }
            int i3 = ((i & 1) << 30) | ((this.f869a[this.f870b] & 32767) << 15) | (this.f869a[this.f870b + 1] & 32767);
            this.f870b += 2;
            return i3;
        }

        private void g() {
            this.j += this.h;
            if (this.g) {
                this.k += this.i;
            }
            this.l += this.i;
        }

        private boolean h() {
            this.g = false;
            this.i = 0;
            this.h = 0;
            return false;
        }

        public final boolean a() {
            return a(this.e);
        }

        private boolean a(boolean z) {
            char c;
            g();
            if (this.d > 0) {
                this.d--;
                return true;
            }
            if (this.f870b >= this.c) {
                return h();
            }
            char[] cArr = this.f869a;
            int i = this.f870b;
            this.f870b = i + 1;
            char c2 = cArr[i];
            char c3 = c2;
            if (c2 <= 4095) {
                this.g = false;
                this.h = c3 + 1;
                while (this.f870b < this.c) {
                    char c4 = this.f869a[this.f870b];
                    c3 = c4;
                    if (c4 > 4095) {
                        break;
                    }
                    this.f870b++;
                    this.h += c3 + 1;
                }
                this.i = this.h;
                if (z) {
                    g();
                    if (this.f870b >= this.c) {
                        return h();
                    }
                    this.f870b++;
                } else {
                    return true;
                }
            }
            this.g = true;
            if (c3 <= 28671) {
                if (this.f) {
                    int i2 = ((c3 & 4095) + 1) * (c3 >> '\f');
                    this.i = i2;
                    this.h = i2;
                } else {
                    int i3 = c3 >> '\f';
                    this.i = i3;
                    this.h = i3;
                    this.d = c3 & 4095;
                    return true;
                }
            } else {
                if (!m && c3 > 32767) {
                    throw new AssertionError();
                }
                this.h = a((c3 >> 6) & 63);
                this.i = a(c3 & '?');
                if (!this.f) {
                    return true;
                }
            }
            while (this.f870b < this.c && (c = this.f869a[this.f870b]) > 4095) {
                this.f870b++;
                if (c <= 28671) {
                    int i4 = ((c & 4095) + 1) * (c >> '\f');
                    this.h += i4;
                    this.i += i4;
                } else {
                    if (!m && c > 32767) {
                        throw new AssertionError();
                    }
                    int a2 = a((c >> 6) & 63);
                    int a3 = a(c & '?');
                    this.h += a2;
                    this.i += a3;
                }
            }
            return true;
        }

        public final boolean b() {
            return this.g;
        }

        public final int c() {
            return this.h;
        }

        public final int d() {
            return this.i;
        }

        public final int e() {
            return this.j;
        }

        public final int f() {
            return this.k;
        }
    }

    public final a d() {
        return new a(this.f867a, this.f868b, false, true, (byte) 0);
    }
}
