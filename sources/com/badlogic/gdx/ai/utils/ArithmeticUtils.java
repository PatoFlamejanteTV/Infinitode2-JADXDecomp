package com.badlogic.gdx.ai.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/ArithmeticUtils.class */
public final class ArithmeticUtils {
    private ArithmeticUtils() {
    }

    public static float wrapAngleAroundZero(float f) {
        if (f >= 0.0f) {
            float f2 = f % 6.2831855f;
            float f3 = f2;
            if (f2 > 3.1415927f) {
                f3 -= 6.2831855f;
            }
            return f3;
        }
        float f4 = (-f) % 6.2831855f;
        float f5 = f4;
        if (f4 > 3.1415927f) {
            f5 -= 6.2831855f;
        }
        return -f5;
    }

    public static int gcdPositive(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
        int i3 = i >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros2;
        int i5 = numberOfTrailingZeros <= numberOfTrailingZeros2 ? numberOfTrailingZeros : numberOfTrailingZeros2;
        while (i3 != i4) {
            int i6 = i3 - i4;
            i4 = i3 <= i4 ? i3 : i4;
            int i7 = i6 < 0 ? -i6 : i6;
            i3 = i7 >> Integer.numberOfTrailingZeros(i7);
        }
        return i3 << i5;
    }

    public static int lcmPositive(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int abs = Math.abs(mulAndCheck(i / gcdPositive(i, i2), i2));
        if (abs == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: lcm(" + i + ", " + i2 + ") > 2^31");
        }
        return abs;
    }

    public static int gcdPositive(int... iArr) {
        if (iArr == null || iArr.length < 2) {
            throw new IllegalArgumentException("gcdPositive requires at least two arguments");
        }
        int i = iArr[0];
        int length = iArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            i = gcdPositive(i, iArr[i2]);
        }
        return i;
    }

    public static int lcmPositive(int... iArr) {
        if (iArr == null || iArr.length < 2) {
            throw new IllegalArgumentException("lcmPositive requires at least two arguments");
        }
        int i = iArr[0];
        int length = iArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            i = lcmPositive(i, iArr[i2]);
        }
        return i;
    }

    public static int mulAndCheck(int i, int i2) {
        long j = i * i2;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException();
        }
        return (int) j;
    }
}
