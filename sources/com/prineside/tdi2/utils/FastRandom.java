package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.TimeUtils;
import com.prineside.tdi2.Game;
import java.security.SecureRandom;
import java.util.Random;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FastRandom.class */
public class FastRandom {
    public static final RandomXS128 random = new RandomXS128();

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f3823a = new float[8192];

    /* renamed from: b, reason: collision with root package name */
    private static int f3824b = 0;

    static {
        for (int i = 0; i < 8192; i++) {
            f3823a[i] = random.nextFloat();
        }
    }

    public static float getFloat() {
        int i = f3824b + 1;
        f3824b = i;
        if (i == 8192) {
            f3824b = 0;
        }
        return f3823a[f3824b];
    }

    public static long getLongUUID() {
        long fairInt;
        try {
            fairInt = new SecureRandom().nextInt();
        } catch (Exception unused) {
            fairInt = getFairInt(Integer.MAX_VALUE);
        }
        return (fairInt << 32) | ((Game.getTimestampMillis() / 10) & 4294967295L);
    }

    public static int getInt(int i) {
        return (int) (getFloat() * i);
    }

    public static float getFairFloat() {
        return random.nextFloat();
    }

    public static int getFairInt(int i) {
        return random.nextInt(i);
    }

    public static String getDistinguishableString(int i, RandomXS128 randomXS128) {
        if (randomXS128 == null) {
            RandomXS128 randomXS1282 = random;
            randomXS128 = randomXS1282;
            randomXS1282.setSeed(((long) (Math.random() * 9.223372036854776E18d)) + TimeUtils.nanoTime());
        }
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(StringFormatter.DISTINGUISHABLE_STRING_CHARS.charAt(randomXS128.nextInt(32)));
        }
        return sb.toString();
    }

    public static String generateUniqueDistinguishableId() {
        String distinguishableString = StringFormatter.distinguishableString(Game.getTimestampSeconds());
        random.setState(new Random().nextLong(), Game.getTimestampMillis());
        return getDistinguishableString(4, random) + "-" + getDistinguishableString(4, random) + "-" + distinguishableString.substring(distinguishableString.length() - 6, distinguishableString.length());
    }
}
