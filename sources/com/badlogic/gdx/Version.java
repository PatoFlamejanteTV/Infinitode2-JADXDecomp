package com.badlogic.gdx;

import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Version.class */
public class Version {
    public static final String VERSION = "1.12.1";
    public static final int MAJOR;
    public static final int MINOR;
    public static final int REVISION;

    static {
        try {
            String[] split = VERSION.split("\\.");
            MAJOR = split.length <= 0 ? 0 : Integer.valueOf(split[0]).intValue();
            MINOR = split.length < 2 ? 0 : Integer.valueOf(split[1]).intValue();
            REVISION = split.length < 3 ? 0 : Integer.valueOf(split[2]).intValue();
        } catch (Throwable th) {
            throw new GdxRuntimeException("Invalid version 1.12.1", th);
        }
    }

    public static boolean isHigher(int i, int i2, int i3) {
        return isHigherEqual(i, i2, i3 + 1);
    }

    public static boolean isHigherEqual(int i, int i2, int i3) {
        return MAJOR != i ? MAJOR > i : MINOR != i2 ? MINOR > i2 : REVISION >= i3;
    }

    public static boolean isLower(int i, int i2, int i3) {
        return isLowerEqual(i, i2, i3 - 1);
    }

    public static boolean isLowerEqual(int i, int i2, int i3) {
        return MAJOR != i ? MAJOR < i : MINOR != i2 ? MINOR < i2 : REVISION <= i3;
    }
}
