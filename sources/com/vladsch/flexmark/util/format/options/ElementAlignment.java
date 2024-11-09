package com.vladsch.flexmark.util.format.options;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/options/ElementAlignment.class */
public enum ElementAlignment {
    NONE,
    LEFT_ALIGN,
    RIGHT_ALIGN;

    public final boolean isNoChange() {
        return this == NONE;
    }

    public final boolean isRight() {
        return this == RIGHT_ALIGN;
    }

    public final boolean isLeft() {
        return this == LEFT_ALIGN;
    }
}
