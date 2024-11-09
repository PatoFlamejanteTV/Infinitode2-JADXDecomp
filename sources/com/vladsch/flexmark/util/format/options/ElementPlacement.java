package com.vladsch.flexmark.util.format.options;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/options/ElementPlacement.class */
public enum ElementPlacement {
    AS_IS,
    DOCUMENT_TOP,
    GROUP_WITH_FIRST,
    GROUP_WITH_LAST,
    DOCUMENT_BOTTOM;

    public final boolean isNoChange() {
        return this == AS_IS;
    }

    public final boolean isChange() {
        return this != AS_IS;
    }

    public final boolean isTop() {
        return this == DOCUMENT_TOP;
    }

    public final boolean isBottom() {
        return this == DOCUMENT_BOTTOM;
    }

    public final boolean isGroupFirst() {
        return this == GROUP_WITH_FIRST;
    }

    public final boolean isGroupLast() {
        return this == GROUP_WITH_LAST;
    }
}
