package com.vladsch.flexmark.util.format;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/Sort.class */
public enum Sort {
    NONE,
    ASCENDING,
    DESCENDING,
    ASCENDING_NUMERIC,
    DESCENDING_NUMERIC,
    ASCENDING_NUMERIC_LAST,
    DESCENDING_NUMERIC_LAST;

    public final boolean isDescending() {
        return this == DESCENDING || this == DESCENDING_NUMERIC || this == DESCENDING_NUMERIC_LAST;
    }

    public final boolean isNumeric() {
        return this == ASCENDING_NUMERIC || this == ASCENDING_NUMERIC_LAST || this == DESCENDING_NUMERIC || this == DESCENDING_NUMERIC_LAST;
    }

    public final boolean isNumericLast() {
        return this == ASCENDING_NUMERIC_LAST || this == DESCENDING_NUMERIC_LAST;
    }
}
