package com.badlogic.gdx.utils;

import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Bits.class */
public class Bits {
    long[] bits;

    public Bits() {
        this.bits = new long[]{0};
    }

    public Bits(int i) {
        this.bits = new long[]{0};
        checkCapacity(i >>> 6);
    }

    public Bits(Bits bits) {
        this.bits = new long[]{0};
        this.bits = new long[bits.bits.length];
        System.arraycopy(bits.bits, 0, this.bits, 0, bits.bits.length);
    }

    public boolean get(int i) {
        int i2 = i >>> 6;
        return i2 < this.bits.length && (this.bits[i2] & (1 << (i & 63))) != 0;
    }

    public boolean getAndClear(int i) {
        int i2 = i >>> 6;
        if (i2 >= this.bits.length) {
            return false;
        }
        long j = this.bits[i2];
        long[] jArr = this.bits;
        jArr[i2] = jArr[i2] & ((1 << (i & 63)) ^ (-1));
        return this.bits[i2] != j;
    }

    public boolean getAndSet(int i) {
        int i2 = i >>> 6;
        checkCapacity(i2);
        long j = this.bits[i2];
        long[] jArr = this.bits;
        jArr[i2] = jArr[i2] | (1 << (i & 63));
        return this.bits[i2] == j;
    }

    public void set(int i) {
        int i2 = i >>> 6;
        checkCapacity(i2);
        long[] jArr = this.bits;
        jArr[i2] = jArr[i2] | (1 << (i & 63));
    }

    public void flip(int i) {
        int i2 = i >>> 6;
        checkCapacity(i2);
        long[] jArr = this.bits;
        jArr[i2] = jArr[i2] ^ (1 << (i & 63));
    }

    private void checkCapacity(int i) {
        if (i >= this.bits.length) {
            long[] jArr = new long[i + 1];
            System.arraycopy(this.bits, 0, jArr, 0, this.bits.length);
            this.bits = jArr;
        }
    }

    public void clear(int i) {
        int i2 = i >>> 6;
        if (i2 >= this.bits.length) {
            return;
        }
        long[] jArr = this.bits;
        jArr[i2] = jArr[i2] & ((1 << (i & 63)) ^ (-1));
    }

    public void clear() {
        Arrays.fill(this.bits, 0L);
    }

    public int numBits() {
        return this.bits.length << 6;
    }

    public int length() {
        long[] jArr = this.bits;
        for (int length = jArr.length - 1; length >= 0; length--) {
            long j = jArr[length];
            if (j != 0) {
                for (int i = 63; i >= 0; i--) {
                    if ((j & (1 << (i & 63))) != 0) {
                        return (length << 6) + i + 1;
                    }
                }
            }
        }
        return 0;
    }

    public boolean notEmpty() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        for (long j : this.bits) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public int nextSetBit(int i) {
        long[] jArr = this.bits;
        int i2 = i >>> 6;
        int length = jArr.length;
        if (i2 >= length) {
            return -1;
        }
        long j = jArr[i2];
        if (j != 0) {
            for (int i3 = i & 63; i3 < 64; i3++) {
                if ((j & (1 << (i3 & 63))) != 0) {
                    return (i2 << 6) + i3;
                }
            }
        }
        while (true) {
            i2++;
            if (i2 < length) {
                if (i2 != 0) {
                    long j2 = jArr[i2];
                    if (j2 != 0) {
                        for (int i4 = 0; i4 < 64; i4++) {
                            if ((j2 & (1 << (i4 & 63))) != 0) {
                                return (i2 << 6) + i4;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                return -1;
            }
        }
    }

    public int nextClearBit(int i) {
        long[] jArr = this.bits;
        int i2 = i >>> 6;
        int length = jArr.length;
        if (i2 >= length) {
            return jArr.length << 6;
        }
        long j = jArr[i2];
        for (int i3 = i & 63; i3 < 64; i3++) {
            if ((j & (1 << (i3 & 63))) == 0) {
                return (i2 << 6) + i3;
            }
        }
        while (true) {
            i2++;
            if (i2 < length) {
                if (i2 == 0) {
                    return i2 << 6;
                }
                long j2 = jArr[i2];
                for (int i4 = 0; i4 < 64; i4++) {
                    if ((j2 & (1 << (i4 & 63))) == 0) {
                        return (i2 << 6) + i4;
                    }
                }
            } else {
                return jArr.length << 6;
            }
        }
    }

    public void and(Bits bits) {
        int min = Math.min(this.bits.length, bits.bits.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.bits;
            int i2 = i;
            jArr[i2] = jArr[i2] & bits.bits[i];
        }
        if (this.bits.length > min) {
            int length = this.bits.length;
            for (int i3 = min; length > i3; i3++) {
                this.bits[i3] = 0;
            }
        }
    }

    public void andNot(Bits bits) {
        int length = this.bits.length;
        int length2 = bits.bits.length;
        for (int i = 0; i < length && i < length2; i++) {
            long[] jArr = this.bits;
            int i2 = i;
            jArr[i2] = jArr[i2] & (bits.bits[i] ^ (-1));
        }
    }

    public void or(Bits bits) {
        int min = Math.min(this.bits.length, bits.bits.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.bits;
            int i2 = i;
            jArr[i2] = jArr[i2] | bits.bits[i];
        }
        if (min < bits.bits.length) {
            checkCapacity(bits.bits.length);
            int length = bits.bits.length;
            for (int i3 = min; length > i3; i3++) {
                this.bits[i3] = bits.bits[i3];
            }
        }
    }

    public void xor(Bits bits) {
        int min = Math.min(this.bits.length, bits.bits.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.bits;
            int i2 = i;
            jArr[i2] = jArr[i2] ^ bits.bits[i];
        }
        if (min < bits.bits.length) {
            checkCapacity(bits.bits.length);
            int length = bits.bits.length;
            for (int i3 = min; length > i3; i3++) {
                this.bits[i3] = bits.bits[i3];
            }
        }
    }

    public boolean intersects(Bits bits) {
        long[] jArr = this.bits;
        long[] jArr2 = bits.bits;
        for (int min = Math.min(jArr.length, jArr2.length) - 1; min >= 0; min--) {
            if ((jArr[min] & jArr2[min]) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Bits bits) {
        long[] jArr = this.bits;
        long[] jArr2 = bits.bits;
        int length = jArr2.length;
        int length2 = jArr.length;
        for (int i = length2; i < length; i++) {
            if (jArr2[i] != 0) {
                return false;
            }
        }
        for (int min = Math.min(length2, length) - 1; min >= 0; min--) {
            if ((jArr[min] & jArr2[min]) != jArr2[min]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int length = length() >>> 6;
        int i = 0;
        for (int i2 = 0; length >= i2; i2++) {
            i = (i * 127) + ((int) (this.bits[i2] ^ (this.bits[i2] >>> 32)));
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bits bits = (Bits) obj;
        long[] jArr = bits.bits;
        int min = Math.min(this.bits.length, jArr.length);
        for (int i = 0; min > i; i++) {
            if (this.bits[i] != jArr[i]) {
                return false;
            }
        }
        return this.bits.length == jArr.length || length() == bits.length();
    }
}
