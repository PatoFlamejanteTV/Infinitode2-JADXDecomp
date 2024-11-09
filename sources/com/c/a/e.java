package com.c.a;

/* loaded from: infinitode-2.jar:com/c/a/e.class */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public byte[] f902a;

    /* renamed from: b, reason: collision with root package name */
    private int f903b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private c h = new c();
    private byte[] i = new byte[4];

    public final int a() {
        this.f902a = null;
        return 0;
    }

    public final int a(int i) {
        if (this.d != 0) {
            this.c -= this.d;
            if (this.c > 0) {
                System.arraycopy(this.f902a, this.d, this.f902a, 0, this.c);
            }
            this.d = 0;
        }
        if (512 > this.f903b - this.c) {
            int i2 = 512 + this.c + 4096;
            if (this.f902a != null) {
                byte[] bArr = new byte[i2];
                System.arraycopy(this.f902a, 0, bArr, 0, this.f902a.length);
                this.f902a = bArr;
            } else {
                this.f902a = new byte[i2];
            }
            this.f903b = i2;
        }
        return this.c;
    }

    public final int b(int i) {
        if (this.c + i > this.f903b) {
            return -1;
        }
        this.c += i;
        return 0;
    }

    private int b(c cVar) {
        int i = this.d;
        int i2 = this.c - this.d;
        if (this.f == 0) {
            if (i2 < 27) {
                return 0;
            }
            if (this.f902a[i] != 79 || this.f902a[i + 1] != 103 || this.f902a[i + 2] != 103 || this.f902a[i + 3] != 83) {
                this.f = 0;
                this.g = 0;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i4 >= i2 - 1) {
                        break;
                    }
                    if (this.f902a[i + 1 + i4] != 79) {
                        i4++;
                    } else {
                        i3 = i + 1 + i4;
                        break;
                    }
                }
                if (i3 == 0) {
                    i3 = this.c;
                }
                this.d = i3;
                return -(i3 - i);
            }
            int i5 = (this.f902a[i + 26] & 255) + 27;
            if (i2 < i5) {
                return 0;
            }
            for (int i6 = 0; i6 < (this.f902a[i + 26] & 255); i6++) {
                this.g += this.f902a[i + 27 + i6] & 255;
            }
            this.f = i5;
        }
        if (this.g + this.f > i2) {
            return 0;
        }
        synchronized (this.i) {
            System.arraycopy(this.f902a, i + 22, this.i, 0, 4);
            this.f902a[i + 22] = 0;
            this.f902a[i + 23] = 0;
            this.f902a[i + 24] = 0;
            this.f902a[i + 25] = 0;
            c cVar2 = this.h;
            cVar2.f898a = this.f902a;
            cVar2.f899b = i;
            cVar2.c = this.f;
            cVar2.d = this.f902a;
            cVar2.e = i + this.f;
            cVar2.f = this.g;
            cVar2.h();
            if (this.i[0] != this.f902a[i + 22] || this.i[1] != this.f902a[i + 23] || this.i[2] != this.f902a[i + 24] || this.i[3] != this.f902a[i + 25]) {
                System.arraycopy(this.i, 0, this.f902a, i + 22, 4);
                this.f = 0;
                this.g = 0;
                int i7 = 0;
                int i8 = 0;
                while (true) {
                    if (i8 >= i2 - 1) {
                        break;
                    }
                    if (this.f902a[i + 1 + i8] != 79) {
                        i8++;
                    } else {
                        i7 = i + 1 + i8;
                        break;
                    }
                }
                if (i7 == 0) {
                    i7 = this.c;
                }
                this.d = i7;
                return -(i7 - i);
            }
            int i9 = this.d;
            if (cVar != null) {
                cVar.f898a = this.f902a;
                cVar.f899b = i9;
                cVar.c = this.f;
                cVar.d = this.f902a;
                cVar.e = i9 + this.f;
                cVar.f = this.g;
            }
            this.e = 0;
            int i10 = this.d;
            int i11 = this.f + this.g;
            this.d = i10 + i11;
            this.f = 0;
            this.g = 0;
            return i11;
        }
    }

    public final int a(c cVar) {
        do {
            int b2 = b(cVar);
            if (b2 > 0) {
                return 1;
            }
            if (b2 == 0) {
                return 0;
            }
        } while (this.e != 0);
        this.e = 1;
        return -1;
    }
}
