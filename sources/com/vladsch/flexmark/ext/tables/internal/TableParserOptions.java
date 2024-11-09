package com.vladsch.flexmark.ext.tables.internal;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableParserOptions.class */
public class TableParserOptions {
    public final int maxHeaderRows;
    public final int minHeaderRows;
    public final int minSeparatorDashes;
    public final boolean appendMissingColumns;
    public final boolean discardExtraColumns;
    public final boolean columnSpans;
    public final boolean trimCellWhitespace;
    public final boolean headerSeparatorColumnMatch;
    public final String className;
    public final boolean withCaption;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TableParserOptions(DataHolder dataHolder) {
        this.maxHeaderRows = TablesExtension.MAX_HEADER_ROWS.get(dataHolder).intValue();
        this.minHeaderRows = TablesExtension.MIN_HEADER_ROWS.get(dataHolder).intValue();
        this.minSeparatorDashes = TablesExtension.MIN_SEPARATOR_DASHES.get(dataHolder).intValue();
        this.appendMissingColumns = TablesExtension.APPEND_MISSING_COLUMNS.get(dataHolder).booleanValue();
        this.discardExtraColumns = TablesExtension.DISCARD_EXTRA_COLUMNS.get(dataHolder).booleanValue();
        this.columnSpans = TablesExtension.COLUMN_SPANS.get(dataHolder).booleanValue();
        this.trimCellWhitespace = TablesExtension.TRIM_CELL_WHITESPACE.get(dataHolder).booleanValue();
        this.headerSeparatorColumnMatch = TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH.get(dataHolder).booleanValue();
        this.className = TablesExtension.CLASS_NAME.get(dataHolder);
        this.withCaption = TablesExtension.WITH_CAPTION.get(dataHolder).booleanValue();
    }
}
