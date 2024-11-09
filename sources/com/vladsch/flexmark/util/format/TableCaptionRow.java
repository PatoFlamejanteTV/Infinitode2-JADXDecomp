package com.vladsch.flexmark.util.format;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableCaptionRow.class */
public class TableCaptionRow extends TableRow {
    @Override // com.vladsch.flexmark.util.format.TableRow
    public TableCell defaultCell() {
        return TableCaptionSection.NULL_CELL;
    }
}
