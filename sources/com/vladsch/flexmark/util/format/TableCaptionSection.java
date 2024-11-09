package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableCaptionSection.class */
public class TableCaptionSection extends TableSection {
    public static final TableCell NULL_CELL;
    public static final TableCell DEFAULT_CELL;

    static {
        BasedSequence basedSequence = BasedSequence.NULL;
        NULL_CELL = new TableCell(null, basedSequence, basedSequence, BasedSequence.NULL, 1, 0);
        DEFAULT_CELL = new TableCell(null, "[", "", "]", 1, 1);
    }

    public TableCaptionSection(TableSectionType tableSectionType) {
        super(tableSectionType);
    }

    @Override // com.vladsch.flexmark.util.format.TableSection
    public TableRow defaultRow() {
        return new TableCaptionRow();
    }

    @Override // com.vladsch.flexmark.util.format.TableSection
    public TableCell defaultCell() {
        return DEFAULT_CELL;
    }
}
