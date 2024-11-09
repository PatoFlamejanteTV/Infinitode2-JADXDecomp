package com.vladsch.flexmark.util.format;

import java.util.ArrayList;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableRowManipulator.class */
public interface TableRowManipulator {
    public static final int BREAK = Integer.MIN_VALUE;

    int apply(TableRow tableRow, int i, ArrayList<TableRow> arrayList, int i2);
}
