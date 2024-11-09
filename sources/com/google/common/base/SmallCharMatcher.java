package com.google.common.base;

import com.google.common.base.CharMatcher;
import java.util.BitSet;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/SmallCharMatcher.class */
final class SmallCharMatcher extends CharMatcher.NamedFastMatcher {
    static final int MAX_SIZE = 1023;
    private final char[] table;
    private final boolean containsZero;
    private final long filter;
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static final double DESIRED_LOAD_FACTOR = 0.5d;

    private SmallCharMatcher(char[] cArr, long j, boolean z, String str) {
        super(str);
        this.table = cArr;
        this.filter = j;
        this.containsZero = z;
    }

    static int smear(int i) {
        return C2 * Integer.rotateLeft(i * C1, 15);
    }

    private boolean checkFilter(int i) {
        return 1 == (1 & (this.filter >> i));
    }

    static int chooseTableSize(int i) {
        if (i == 1) {
            return 2;
        }
        int highestOneBit = Integer.highestOneBit(i - 1);
        while (true) {
            int i2 = highestOneBit << 1;
            if (i2 * DESIRED_LOAD_FACTOR < i) {
                highestOneBit = i2;
            } else {
                return i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharMatcher from(BitSet bitSet, String str) {
        int i;
        long j = 0;
        int cardinality = bitSet.cardinality();
        boolean z = bitSet.get(0);
        char[] cArr = new char[chooseTableSize(cardinality)];
        int length = cArr.length - 1;
        int nextSetBit = bitSet.nextSetBit(0);
        while (true) {
            int i2 = nextSetBit;
            if (i2 == -1) {
                return new SmallCharMatcher(cArr, j, z, str);
            }
            j |= 1 << i2;
            int smear = smear(i2);
            while (true) {
                i = smear & length;
                if (cArr[i] == 0) {
                    break;
                }
                smear = i + 1;
            }
            cArr[i] = (char) i2;
            nextSetBit = bitSet.nextSetBit(i2 + 1);
        }
    }

    @Override // com.google.common.base.CharMatcher
    public final boolean matches(char c) {
        if (c == 0) {
            return this.containsZero;
        }
        if (!checkFilter(c)) {
            return false;
        }
        int length = this.table.length - 1;
        int smear = smear(c) & length;
        int i = smear;
        while (this.table[i] != 0) {
            if (this.table[i] == c) {
                return true;
            }
            int i2 = (i + 1) & length;
            i = i2;
            if (i2 == smear) {
                return false;
            }
        }
        return false;
    }

    @Override // com.google.common.base.CharMatcher
    final void setBits(BitSet bitSet) {
        if (this.containsZero) {
            bitSet.set(0);
        }
        for (char c : this.table) {
            if (c != 0) {
                bitSet.set(c);
            }
        }
    }
}
