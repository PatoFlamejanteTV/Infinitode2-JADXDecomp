package com.prineside.tdi2.enums;

import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/DifficultyMode.class */
public enum DifficultyMode {
    EASY,
    NORMAL,
    ENDLESS_I;

    public static final DifficultyMode[] values = values();

    public static boolean hasSpecialResources(DifficultyMode difficultyMode) {
        return (difficultyMode == EASY || difficultyMode == NORMAL) ? false : true;
    }

    public static boolean isEndless(DifficultyMode difficultyMode) {
        return (difficultyMode == EASY || difficultyMode == NORMAL) ? false : true;
    }
}
