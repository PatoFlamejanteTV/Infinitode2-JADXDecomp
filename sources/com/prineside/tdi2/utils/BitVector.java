package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.util.Arrays;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/BitVector.class */
public final class BitVector {
    public long[] words;

    /* synthetic */ BitVector(long[] jArr, byte b2) {
        this(jArr);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/BitVector$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<BitVector> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BitVector bitVector) {
            kryo.writeObject(output, bitVector.words);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public BitVector read2(Kryo kryo, Input input, Class<? extends BitVector> cls) {
            return new BitVector((long[]) kryo.readObject(input, long[].class), (byte) 0);
        }
    }

    private BitVector(long[] jArr) {
        this.words = new long[]{0};
        this.words = jArr;
    }

    public BitVector() {
        this.words = new long[]{0};
    }

    public BitVector(int i) {
        this.words = new long[]{0};
        a(i >>> 6);
    }

    public BitVector(BitVector bitVector) {
        this.words = new long[]{0};
        this.words = Arrays.copyOf(bitVector.words, bitVector.words.length);
    }

    public final boolean get(int i) {
        int i2 = i >>> 6;
        return i2 < this.words.length && (this.words[i2] & (1 << i)) != 0;
    }

    public final int getCapacity() {
        return this.words.length << 6;
    }

    public final void set(int i) {
        int i2 = i >>> 6;
        a(i2);
        long[] jArr = this.words;
        jArr[i2] = jArr[i2] | (1 << i);
    }

    public final void setValue(int i, boolean z) {
        if (z) {
            set(i);
        } else {
            clearAtIndex(i);
        }
    }

    public final boolean unsafeGet(int i) {
        return (this.words[i >>> 6] & (1 << i)) != 0;
    }

    public final void unsafeSet(int i) {
        long[] jArr = this.words;
        int i2 = i >>> 6;
        jArr[i2] = jArr[i2] | (1 << i);
    }

    public final void unsafeSetValue(int i, boolean z) {
        if (z) {
            unsafeSet(i);
        } else {
            unsafeClear(i);
        }
    }

    public final void flip(int i) {
        int i2 = i >>> 6;
        a(i2);
        long[] jArr = this.words;
        jArr[i2] = jArr[i2] ^ (1 << i);
    }

    public final void ensureCapacity(int i) {
        a(i >>> 6);
    }

    private void a(int i) {
        if (i >= this.words.length) {
            long[] jArr = new long[i + 1];
            System.arraycopy(this.words, 0, jArr, 0, this.words.length);
            this.words = jArr;
        }
    }

    public final void clearAtIndex(int i) {
        int i2 = i >>> 6;
        if (i2 >= this.words.length) {
            return;
        }
        long[] jArr = this.words;
        jArr[i2] = jArr[i2] & ((1 << i) ^ (-1));
    }

    public final void unsafeClear(int i) {
        long[] jArr = this.words;
        int i2 = i >>> 6;
        jArr[i2] = jArr[i2] & ((1 << i) ^ (-1));
    }

    public final void clear() {
        Arrays.fill(this.words, 0L);
    }

    public final int length() {
        long[] jArr = this.words;
        for (int length = jArr.length - 1; length >= 0; length--) {
            long j = jArr[length];
            if (j != 0) {
                return ((length << 6) + 64) - Long.numberOfLeadingZeros(j);
            }
        }
        return 0;
    }

    public final boolean isEmpty() {
        for (long j : this.words) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public final int nextSetBit(int i) {
        int i2 = i >>> 6;
        if (i2 < this.words.length) {
            long j = this.words[i2] >>> i;
            if (j != 0) {
                return i + Long.numberOfTrailingZeros(j);
            }
            for (int i3 = i2 + 1; i3 < this.words.length; i3++) {
                long j2 = this.words[i3];
                if (j2 != 0) {
                    return (i3 << 6) + Long.numberOfTrailingZeros(j2);
                }
            }
            return -1;
        }
        return -1;
    }

    public final int nextClearBit(int i) {
        int i2 = i >>> 6;
        if (i2 < this.words.length) {
            long j = (this.words[i2] >>> i) ^ (-1);
            if (j != 0) {
                return i + Long.numberOfTrailingZeros(j);
            }
            for (int i3 = i2 + 1; i3 < this.words.length; i3++) {
                long j2 = this.words[i3] ^ (-1);
                if (j2 != 0) {
                    return (i3 << 6) + Long.numberOfTrailingZeros(j2);
                }
            }
            return Math.min(i, this.words.length << 6);
        }
        return Math.min(i, this.words.length << 6);
    }

    public final void and(BitVector bitVector) {
        int min = Math.min(this.words.length, bitVector.words.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.words;
            int i2 = i;
            jArr[i2] = jArr[i2] & bitVector.words[i];
        }
        if (this.words.length > min) {
            int length = this.words.length;
            for (int i3 = min; length > i3; i3++) {
                this.words[i3] = 0;
            }
        }
    }

    public final void andNot(BitVector bitVector) {
        int min = Math.min(this.words.length, bitVector.words.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.words;
            int i2 = i;
            jArr[i2] = jArr[i2] & (bitVector.words[i] ^ (-1));
        }
    }

    public final void or(BitVector bitVector) {
        int min = Math.min(this.words.length, bitVector.words.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.words;
            int i2 = i;
            jArr[i2] = jArr[i2] | bitVector.words[i];
        }
        if (min < bitVector.words.length) {
            a(bitVector.words.length);
            int length = bitVector.words.length;
            for (int i3 = min; length > i3; i3++) {
                this.words[i3] = bitVector.words[i3];
            }
        }
    }

    public final void xor(BitVector bitVector) {
        int min = Math.min(this.words.length, bitVector.words.length);
        for (int i = 0; min > i; i++) {
            long[] jArr = this.words;
            int i2 = i;
            jArr[i2] = jArr[i2] ^ bitVector.words[i];
        }
        if (min < bitVector.words.length) {
            a(bitVector.words.length);
            int length = bitVector.words.length;
            for (int i3 = min; length > i3; i3++) {
                this.words[i3] = bitVector.words[i3];
            }
        }
    }

    public final boolean intersects(BitVector bitVector) {
        long[] jArr = this.words;
        long[] jArr2 = bitVector.words;
        int min = Math.min(jArr.length, jArr2.length);
        for (int i = 0; min > i; i++) {
            if ((jArr[i] & jArr2[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsAll(BitVector bitVector) {
        long[] jArr = this.words;
        long[] jArr2 = bitVector.words;
        int length = jArr2.length;
        int length2 = jArr.length;
        for (int i = length2; i < length; i++) {
            if (jArr2[i] != 0) {
                return false;
            }
        }
        int min = Math.min(length2, length);
        for (int i2 = 0; min > i2; i2++) {
            if ((jArr[i2] & jArr2[i2]) != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public final int cardinality() {
        int i = 0;
        for (long j : this.words) {
            i += Long.bitCount(j);
        }
        return i;
    }

    public final int hashCode() {
        int length = length() >>> 6;
        int i = 0;
        for (int i2 = 0; length >= i2; i2++) {
            i = (i * 127) + ((int) (this.words[i2] ^ (this.words[i2] >>> 32)));
        }
        return i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BitVector bitVector = (BitVector) obj;
        long[] jArr = bitVector.words;
        int min = Math.min(this.words.length, jArr.length);
        for (int i = 0; min > i; i++) {
            if (this.words[i] != jArr[i]) {
                return false;
            }
        }
        return this.words.length == jArr.length || length() == bitVector.length();
    }

    public final boolean exactlyTheSame(BitVector bitVector) {
        long[] jArr = bitVector.words;
        for (int i = 0; jArr.length > i; i++) {
            if (this.words[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        int cardinality = cardinality();
        int min = Math.min(128, cardinality);
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("BitVector[").append(cardinality);
        if (cardinality > 0) {
            sb.append(": {");
            int nextSetBit = nextSetBit(0);
            while (true) {
                int i2 = nextSetBit;
                if (min <= i || i2 == -1) {
                    break;
                }
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(i2);
                i++;
                nextSetBit = nextSetBit(i2 + 1);
            }
            if (cardinality > min) {
                sb.append(" ...");
            }
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
}
