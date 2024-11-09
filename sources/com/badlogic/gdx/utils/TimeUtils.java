package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/TimeUtils.class */
public final class TimeUtils {
    private static final long nanosPerMilli = 1000000;

    public static long nanoTime() {
        return System.nanoTime();
    }

    public static long millis() {
        return System.currentTimeMillis();
    }

    public static long nanosToMillis(long j) {
        return j / nanosPerMilli;
    }

    public static long millisToNanos(long j) {
        return j * nanosPerMilli;
    }

    public static long timeSinceNanos(long j) {
        return nanoTime() - j;
    }

    public static long timeSinceMillis(long j) {
        return millis() - j;
    }
}
