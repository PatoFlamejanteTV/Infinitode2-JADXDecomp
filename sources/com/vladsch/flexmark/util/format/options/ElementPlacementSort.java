package com.vladsch.flexmark.util.format.options;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/options/ElementPlacementSort.class */
public enum ElementPlacementSort {
    AS_IS,
    SORT,
    SORT_UNUSED_LAST,
    SORT_DELETE_UNUSED,
    DELETE_UNUSED;

    public final boolean isUnused() {
        return this == SORT_UNUSED_LAST || this == SORT_DELETE_UNUSED || this == DELETE_UNUSED;
    }

    public final boolean isDeleteUnused() {
        return this == SORT_DELETE_UNUSED || this == DELETE_UNUSED;
    }

    public final boolean isSort() {
        return this == SORT_UNUSED_LAST || this == SORT_DELETE_UNUSED || this == SORT;
    }
}
