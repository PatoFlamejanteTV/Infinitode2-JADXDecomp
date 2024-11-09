package com.b.a.a;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/b/a/a/x.class */
public final class x extends s {
    public static x b(ByteBuffer byteBuffer) {
        return (x) s.a(byteBuffer);
    }

    @Override // com.b.a.a.s
    public final int a(int i) {
        if (i >= 0) {
            if (i < 55296 || (i > 56319 && i <= 65535)) {
                return this.f829b[(this.f829b[i >> 5] << 2) + (i & 31)];
            }
            if (i <= 65535) {
                return this.f829b[(this.f829b[2048 + ((i - 55296) >> 5)] << 2) + (i & 31)];
            }
            if (i < this.j) {
                return this.f829b[(this.f829b[this.f829b[2080 + (i >> 11)] + ((i >> 5) & 63)] << 2) + (i & 31)];
            }
            if (i <= 1114111) {
                return this.f829b[this.k];
            }
        }
        return this.i;
    }

    @Override // com.b.a.a.s
    public final int a(char c) {
        return this.f829b[(this.f829b[c >> 5] << 2) + (c & 31)];
    }

    public final int b() {
        return 16 + ((this.f828a.c + this.f) << 1);
    }

    @Override // com.b.a.a.s
    final int a(int i, int i2, int i3) {
        char c;
        int i4;
        int i5 = i;
        loop0: while (true) {
            if (i5 >= i2) {
                break;
            }
            if (i5 < 55296 || (i5 > 56319 && i5 <= 65535)) {
                c = 0;
                i4 = this.f829b[i5 >> 5] << 2;
            } else if (i5 < 65535) {
                c = 2048;
                i4 = this.f829b[2048 + ((i5 - 55296) >> 5)] << 2;
            } else if (i5 < this.j) {
                c = this.f829b[2080 + (i5 >> 11)];
                i4 = this.f829b[c + ((i5 >> 5) & 63)] << 2;
            } else if (i3 == this.f829b[this.k]) {
                i5 = i2;
            }
            if (c == this.g) {
                if (i3 != this.h) {
                    break;
                }
                i5 += 2048;
            } else if (i4 == this.l) {
                if (i3 != this.h) {
                    break;
                }
                i5 += 32;
            } else {
                int i6 = i4 + (i5 & 31);
                int i7 = i4 + 32;
                for (int i8 = i6; i8 < i7; i8++) {
                    if (this.f829b[i8] != i3) {
                        i5 += i8 - i6;
                        break loop0;
                    }
                }
                i5 += i7 - i6;
            }
        }
        if (i5 > i2) {
            i5 = i2;
        }
        return i5 - 1;
    }
}
