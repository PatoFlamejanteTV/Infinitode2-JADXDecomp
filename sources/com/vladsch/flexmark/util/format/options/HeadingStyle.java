package com.vladsch.flexmark.util.format.options;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/options/HeadingStyle.class */
public enum HeadingStyle {
    AS_IS,
    ATX_PREFERRED,
    SETEXT_PREFERRED;

    public final boolean isNoChange() {
        return this == AS_IS;
    }

    public final boolean isNoChange(boolean z, int i) {
        if (this == AS_IS) {
            return true;
        }
        if (this != SETEXT_PREFERRED || (!z && i <= 2)) {
            return this == ATX_PREFERRED && !z;
        }
        return true;
    }

    public final boolean isAtx() {
        return this == ATX_PREFERRED;
    }

    public final boolean isSetext() {
        return this == SETEXT_PREFERRED;
    }
}
