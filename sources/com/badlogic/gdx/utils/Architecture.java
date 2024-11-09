package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Architecture.class */
public enum Architecture {
    x86,
    ARM,
    RISCV,
    LOONGARCH;

    public final String toSuffix() {
        return this == x86 ? "" : name().toLowerCase();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Architecture$Bitness.class */
    public enum Bitness {
        _32,
        _64,
        _128;

        public final String toSuffix() {
            return this == _32 ? "" : name().substring(1);
        }
    }
}
