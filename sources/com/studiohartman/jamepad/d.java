package com.studiohartman.jamepad;

/* loaded from: infinitode-2.jar:com/studiohartman/jamepad/d.class */
public enum d {
    POWER_UNKNOWN,
    POWER_EMPTY,
    POWER_LOW,
    POWER_MEDIUM,
    POWER_FULL,
    POWER_WIRED,
    POWER_MAX;

    public static d a(int i) {
        return values()[i + 1];
    }
}
