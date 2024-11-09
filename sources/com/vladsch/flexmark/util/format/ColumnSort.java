package com.vladsch.flexmark.util.format;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/ColumnSort.class */
public final class ColumnSort {
    public final int column;
    public final Sort sort;

    private ColumnSort(int i, Sort sort) {
        this.column = i;
        this.sort = sort;
    }

    public static ColumnSort columnSort(int i, Sort sort) {
        return new ColumnSort(i, sort);
    }

    public static ColumnSort columnSort(int i, boolean z, boolean z2, boolean z3) {
        if (z2) {
            if (z3) {
                return new ColumnSort(i, z ? Sort.DESCENDING_NUMERIC_LAST : Sort.ASCENDING_NUMERIC_LAST);
            }
            return new ColumnSort(i, z ? Sort.DESCENDING_NUMERIC : Sort.ASCENDING_NUMERIC);
        }
        return new ColumnSort(i, z ? Sort.DESCENDING : Sort.ASCENDING);
    }
}
