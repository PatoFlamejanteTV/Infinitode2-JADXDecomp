package com.vladsch.flexmark.util.format;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableCellManipulator.class */
public interface TableCellManipulator {
    public static final int BREAK = Integer.MIN_VALUE;

    int apply(TableCell tableCell, int i, int i2, int i3);
}
