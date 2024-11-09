package com.vladsch.flexmark.util.format;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableSeparatorRow.class */
public class TableSeparatorRow extends TableRow {
    @Override // com.vladsch.flexmark.util.format.TableRow
    public TableCell defaultCell() {
        return TableSeparatorSection.DEFAULT_CELL;
    }
}
