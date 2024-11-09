package com.prineside.tdi2.utils.logging;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/LogLevel.class */
public final class LogLevel {
    public static final byte DEBUG = 0;
    public static final byte INFO = 1;
    public static final byte WARNING = 2;
    public static final byte ERROR = 3;

    /* renamed from: a, reason: collision with root package name */
    private static byte f3925a = 0;

    public static String getName(byte b2) {
        switch (b2) {
            case 0:
                return "DEBUG";
            case 1:
            default:
                return "INFO";
            case 2:
                return "WARN";
            case 3:
                return "ERROR";
        }
    }

    public static char getShortName(byte b2) {
        switch (b2) {
            case 0:
                return 'D';
            case 1:
            default:
                return 'I';
            case 2:
                return 'W';
            case 3:
                return 'E';
        }
    }

    public static byte shortNameToLevel(char c) {
        switch (c) {
            case 'D':
                return (byte) 0;
            case 'E':
                return (byte) 3;
            case 'W':
                return (byte) 2;
            default:
                return (byte) 1;
        }
    }

    public static boolean isDebug() {
        return f3925a <= 0;
    }

    public static void setCurrent(byte b2) {
        f3925a = b2;
    }

    public static byte getCurrent() {
        return f3925a;
    }

    public static boolean isInfo() {
        return f3925a <= 1;
    }

    public static boolean isWarning() {
        return f3925a <= 2;
    }
}
