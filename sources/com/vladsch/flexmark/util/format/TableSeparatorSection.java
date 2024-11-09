package com.vladsch.flexmark.util.format;

import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableSeparatorSection.class */
public class TableSeparatorSection extends TableSection {
    public static final TableCell DEFAULT_CELL = new TableCell(NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR, 1, 1);

    public TableSeparatorSection(TableSectionType tableSectionType) {
        super(tableSectionType);
    }

    @Override // com.vladsch.flexmark.util.format.TableSection
    public TableRow defaultRow() {
        return new TableSeparatorRow();
    }

    @Override // com.vladsch.flexmark.util.format.TableSection
    public TableCell defaultCell() {
        return DEFAULT_CELL;
    }
}
