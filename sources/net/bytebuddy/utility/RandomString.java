package net.bytebuddy.utility;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/RandomString.class */
public class RandomString {
    public static final int DEFAULT_LENGTH = 8;
    private static final char[] SYMBOL;
    private static final int KEY_BITS;
    private final Random random;
    private final int length;

    static {
        StringBuilder sb = new StringBuilder();
        char c = '0';
        while (true) {
            char c2 = c;
            if (c2 > '9') {
                break;
            }
            sb.append(c2);
            c = (char) (c2 + 1);
        }
        char c3 = 'a';
        while (true) {
            char c4 = c3;
            if (c4 > 'z') {
                break;
            }
            sb.append(c4);
            c3 = (char) (c4 + 1);
        }
        char c5 = 'A';
        while (true) {
            char c6 = c5;
            if (c6 > 'Z') {
                break;
            }
            sb.append(c6);
            c5 = (char) (c6 + 1);
        }
        SYMBOL = sb.toString().toCharArray();
        int numberOfLeadingZeros = 32 - Integer.numberOfLeadingZeros(SYMBOL.length);
        KEY_BITS = numberOfLeadingZeros - (Integer.bitCount(SYMBOL.length) == numberOfLeadingZeros ? 0 : 1);
    }

    public RandomString() {
        this(8);
    }

    public RandomString(int i) {
        this(i, new Random());
    }

    public RandomString(int i, Random random) {
        if (i <= 0) {
            throw new IllegalArgumentException("A random string's length cannot be zero or negative");
        }
        this.length = i;
        this.random = random;
    }

    public static String make() {
        return make(8);
    }

    public static String make(int i) {
        return new RandomString(i).nextString();
    }

    public static String hashOf(@MaybeNull Object obj) {
        return hashOf(obj == null ? 0 : obj.getClass().hashCode() ^ System.identityHashCode(obj));
    }

    public static String hashOf(int i) {
        char[] cArr = new char[(32 / KEY_BITS) + (32 % KEY_BITS == 0 ? 0 : 1)];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = SYMBOL[(i >>> (i2 * KEY_BITS)) & ((-1) >>> (32 - KEY_BITS))];
        }
        return new String(cArr);
    }

    @SuppressFBWarnings(value = {"DMI_RANDOM_USED_ONLY_ONCE"}, justification = "Random value is used on each invocation.")
    public String nextString() {
        char[] cArr = new char[this.length];
        for (int i = 0; i < this.length; i++) {
            cArr[i] = SYMBOL[this.random.nextInt(SYMBOL.length)];
        }
        return new String(cArr);
    }
}
