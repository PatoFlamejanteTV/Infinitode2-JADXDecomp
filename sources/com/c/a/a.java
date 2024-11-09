package com.c.a;

import com.prineside.luaj.Lua;
import com.vladsch.flexmark.util.sequence.builder.Seg;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;
import org.lwjgl.opengl.GL11;

/* loaded from: infinitode-2.jar:com/c/a/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f894a = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, Lua.MASK_NOT_Bx, 32767, 65535, Lua.MAXARG_sBx, Lua.MAXARG_Bx, 524287, GL11.GL_ALL_ATTRIB_BITS, 2097151, 4194303, Lua.MASK_NOT_B, 16777215, 33554431, Lua.MAXARG_Ax, 134217727, 268435455, SegmentTree.MAX_VALUE, Seg.MAX_TEXT_OFFSET, Integer.MAX_VALUE, -1};

    /* renamed from: b, reason: collision with root package name */
    private int f895b = 0;
    private byte[] c = null;
    private int d = 0;
    private int e = 0;
    private int f = 0;

    public final void a(byte[] bArr, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i;
            i--;
            if (i3 != 0) {
                int i4 = i2;
                i2++;
                bArr[i4] = (byte) c(8);
            } else {
                return;
            }
        }
    }

    public final void a(byte[] bArr, int i, int i2) {
        this.f895b = i;
        this.c = bArr;
        this.e = 0;
        this.d = 0;
        this.f = i2;
    }

    public final int a(int i) {
        int i2 = f894a[i];
        int i3 = i + this.d;
        if (this.e + 4 >= this.f && this.e + ((i3 - 1) / 8) >= this.f) {
            return -1;
        }
        int i4 = (this.c[this.f895b] & 255) >>> this.d;
        if (i3 > 8) {
            i4 |= (this.c[this.f895b + 1] & 255) << (8 - this.d);
            if (i3 > 16) {
                i4 |= (this.c[this.f895b + 2] & 255) << (16 - this.d);
                if (i3 > 24) {
                    i4 |= (this.c[this.f895b + 3] & 255) << (24 - this.d);
                    if (i3 > 32 && this.d != 0) {
                        i4 |= (this.c[this.f895b + 4] & 255) << (32 - this.d);
                    }
                }
            }
        }
        return i2 & i4;
    }

    public final void b(int i) {
        int i2 = i + this.d;
        this.f895b += i2 / 8;
        this.e += i2 / 8;
        this.d = i2 & 7;
    }

    public final int c(int i) {
        int i2 = f894a[i];
        int i3 = i + this.d;
        if (this.e + 4 >= this.f && this.e + ((i3 - 1) / 8) >= this.f) {
            this.f895b += i3 / 8;
            this.e += i3 / 8;
            this.d = i3 & 7;
            return -1;
        }
        int i4 = (this.c[this.f895b] & 255) >>> this.d;
        if (i3 > 8) {
            i4 |= (this.c[this.f895b + 1] & 255) << (8 - this.d);
            if (i3 > 16) {
                i4 |= (this.c[this.f895b + 2] & 255) << (16 - this.d);
                if (i3 > 24) {
                    i4 |= (this.c[this.f895b + 3] & 255) << (24 - this.d);
                    if (i3 > 32 && this.d != 0) {
                        i4 |= (this.c[this.f895b + 4] & 255) << (32 - this.d);
                    }
                }
            }
        }
        int i5 = i4 & i2;
        this.f895b += i3 / 8;
        this.e += i3 / 8;
        this.d = i3 & 7;
        return i5;
    }

    public final int a() {
        if (this.e >= this.f) {
            this.d++;
            if (this.d > 7) {
                this.d = 0;
                this.f895b++;
                this.e++;
                return -1;
            }
            return -1;
        }
        int i = (this.c[this.f895b] >> this.d) & 1;
        this.d++;
        if (this.d > 7) {
            this.d = 0;
            this.f895b++;
            this.e++;
        }
        return i;
    }
}
