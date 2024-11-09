package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.collection.MaxAggregator;
import com.vladsch.flexmark.util.collection.MinAggregator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.DiscretionaryText;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.misc.ArrayUtils;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Ref;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownTable.class */
public class MarkdownTable {
    public final TableSection header;
    public final TableSection separator;
    public final TableSection body;
    public final TableSection caption;
    public TableFormatOptions options;
    private boolean isHeading;
    private boolean isSeparator;
    CharSequence formatTableIndentPrefix;
    private CellAlignment[] alignments;
    private int[] columnWidths;
    private final ArrayList<TrackedOffset> trackedOffsets;
    private final TableSection[] ALL_SECTIONS;
    private final TableSection[] ALL_TABLE_ROWS;
    private final TableSection[] ALL_CONTENT_ROWS;
    private final TableSection[] ALL_HEADER_ROWS;
    private final TableSection[] ALL_BODY_ROWS;
    public static final CharPredicate COLON_TRIM_CHARS;
    private final CharSequence tableChars;
    public static final NumericSuffixPredicate NO_SUFFIXES;
    public static final NumericSuffixPredicate ALL_SUFFIXES_SORT;
    public static final NumericSuffixPredicate ALL_SUFFIXES_NO_SORT;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !MarkdownTable.class.desiredAssertionStatus();
        COLON_TRIM_CHARS = CharPredicate.anyOf(':');
        NO_SUFFIXES = str -> {
            return false;
        };
        ALL_SUFFIXES_SORT = str2 -> {
            return true;
        };
        ALL_SUFFIXES_NO_SORT = new NumericSuffixPredicate() { // from class: com.vladsch.flexmark.util.format.MarkdownTable.1
            @Override // java.util.function.Predicate
            public final boolean test(String str3) {
                return true;
            }

            @Override // com.vladsch.flexmark.util.format.NumericSuffixPredicate
            public final boolean sortSuffix(String str3) {
                return false;
            }
        };
    }

    public MarkdownTable(CharSequence charSequence, DataHolder dataHolder) {
        this(charSequence, new TableFormatOptions(dataHolder));
    }

    public MarkdownTable(CharSequence charSequence, TableFormatOptions tableFormatOptions) {
        this.trackedOffsets = new ArrayList<>();
        this.tableChars = charSequence;
        this.formatTableIndentPrefix = tableFormatOptions == null ? "" : tableFormatOptions.formatTableIndentPrefix;
        this.header = new TableSection(TableSectionType.HEADER);
        this.separator = new TableSeparatorSection(TableSectionType.SEPARATOR);
        this.body = new TableSection(TableSectionType.BODY);
        this.caption = new TableCaptionSection(TableSectionType.CAPTION);
        this.isHeading = true;
        this.isSeparator = false;
        this.options = tableFormatOptions == null ? new TableFormatOptions(null) : tableFormatOptions;
        this.ALL_SECTIONS = new TableSection[]{this.header, this.separator, this.body, this.caption};
        this.ALL_TABLE_ROWS = new TableSection[]{this.header, this.separator, this.body};
        this.ALL_CONTENT_ROWS = new TableSection[]{this.header, this.body};
        this.ALL_HEADER_ROWS = new TableSection[]{this.header};
        this.ALL_BODY_ROWS = new TableSection[]{this.body};
    }

    public CharSequence getTableChars() {
        return this.tableChars;
    }

    public TableCell getCaptionCell() {
        return (this.caption.rows.size() <= 0 || this.caption.rows.get(0).cells.size() <= 0) ? TableCaptionSection.NULL_CELL : this.caption.rows.get(0).cells.get(0);
    }

    public CharSequence getFormatTableIndentPrefix() {
        return this.formatTableIndentPrefix;
    }

    public void setFormatTableIndentPrefix(CharSequence charSequence) {
        this.formatTableIndentPrefix = charSequence;
    }

    public void setCaptionCell(TableCell tableCell) {
        if (this.caption.rows.size() == 0) {
            this.caption.rows.add(this.caption.defaultRow());
        }
        this.caption.rows.get(0).cells.clear();
        this.caption.rows.get(0).cells.add(tableCell);
    }

    public BasedSequence getCaption() {
        return getCaptionCell().text;
    }

    public void setCaption(CharSequence charSequence) {
        TableCell captionCell = getCaptionCell();
        setCaptionCell(captionCell.withText(captionCell.openMarker.isEmpty() ? "[" : captionCell.openMarker, charSequence, captionCell.closeMarker.isEmpty() ? "]" : captionCell.closeMarker));
    }

    public void setCaptionWithMarkers(Node node, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        setCaptionCell(new TableCell(node, charSequence, this.options.formatTableCaptionSpaces == DiscretionaryText.AS_IS ? charSequence2 : BasedSequence.of(charSequence2).trim(), charSequence3, 1, 1));
    }

    public int getHeadingRowCount() {
        return this.header.rows.size();
    }

    public int getSeparatorRowCount() {
        return this.separator.rows.size();
    }

    public int getBodyRowCount() {
        return this.body.rows.size();
    }

    public int getCaptionRowCount() {
        return this.caption.rows.size();
    }

    public int getMaxHeadingColumns() {
        return this.header.getMaxColumns();
    }

    public int getMaxSeparatorColumns() {
        return this.separator.getMaxColumns();
    }

    public int getMaxBodyColumns() {
        return this.body.getMaxColumns();
    }

    public boolean getHaveCaption() {
        return this.caption.rows.size() > 0 && this.caption.rows.get(0).cells.size() > 0 && this.caption.rows.get(0).cells.get(0).columnSpan != 0;
    }

    public int getMinColumns() {
        int minColumns = this.header.getMinColumns();
        int minColumns2 = this.separator.getMinColumns();
        int minColumns3 = this.body.getMinColumns();
        int i = minColumns == 0 ? Integer.MAX_VALUE : minColumns;
        int[] iArr = new int[2];
        iArr[0] = minColumns2;
        iArr[1] = minColumns3 == 0 ? Integer.MAX_VALUE : minColumns3;
        return Utils.min(i, iArr);
    }

    public int getMaxColumns() {
        return Utils.max(this.header.getMaxColumns(), this.separator.getMaxColumns(), this.body.getMaxColumns());
    }

    public int getMinColumnsWithoutColumns(boolean z, int... iArr) {
        return aggregateTotalColumnsWithoutColumns(z ? this.ALL_TABLE_ROWS : this.ALL_CONTENT_ROWS, MinAggregator.INSTANCE, iArr);
    }

    public int getMaxColumnsWithoutColumns(boolean z, int... iArr) {
        return aggregateTotalColumnsWithoutColumns(z ? this.ALL_TABLE_ROWS : this.ALL_CONTENT_ROWS, MaxAggregator.INSTANCE, iArr);
    }

    public int getMinColumnsWithoutRows(boolean z, int... iArr) {
        return aggregateTotalColumnsWithoutRows(z ? this.ALL_TABLE_ROWS : this.ALL_CONTENT_ROWS, MinAggregator.INSTANCE, iArr);
    }

    public int getMaxColumnsWithoutRows(boolean z, int... iArr) {
        return aggregateTotalColumnsWithoutRows(z ? this.ALL_TABLE_ROWS : this.ALL_CONTENT_ROWS, MaxAggregator.INSTANCE, iArr);
    }

    public List<TrackedOffset> getTrackedOffsets() {
        return this.trackedOffsets;
    }

    private TrackedOffset findTrackedOffset(int i) {
        Iterator<TrackedOffset> it = this.trackedOffsets.iterator();
        while (it.hasNext()) {
            TrackedOffset next = it.next();
            if (next.getOffset() == i) {
                return next;
            }
            if (next.getOffset() > i) {
                return null;
            }
        }
        return null;
    }

    public TrackedOffset getTrackedOffset(int i) {
        return findTrackedOffset(i);
    }

    public int getTrackedOffsetIndex(int i) {
        TrackedOffset findTrackedOffset = findTrackedOffset(i);
        return findTrackedOffset == null ? i : findTrackedOffset.getIndex();
    }

    public int getTableStartOffset() {
        List<TableRow> allRows = getAllRows();
        if (!allRows.isEmpty()) {
            TableRow tableRow = allRows.get(0);
            tableRow.normalizeIfNeeded();
            if (tableRow.cells.size() > 0) {
                return tableRow.cells.get(0).getStartOffset(null);
            }
            return 0;
        }
        return 0;
    }

    public TableCellOffsetInfo getCellOffsetInfo(int i) {
        int i2 = 0;
        for (TableRow tableRow : getAllSectionRows()) {
            tableRow.normalizeIfNeeded();
            TableCell tableCell = tableRow.cells.get(tableRow.cells.size() - 1);
            BasedSequence lastSegment = tableCell.getLastSegment();
            int baseEndOfLineAnyEOL = lastSegment.baseEndOfLineAnyEOL();
            int i3 = baseEndOfLineAnyEOL;
            if (baseEndOfLineAnyEOL == -1) {
                i3 = lastSegment.getEndOffset();
            }
            if (i <= i3) {
                int i4 = 0;
                TableCell tableCell2 = null;
                for (TableCell tableCell3 : tableRow.cells) {
                    if (!tableCell3.closeMarker.isEmpty()) {
                        if (i < tableCell3.closeMarker.getEndOffset()) {
                            if (i >= tableCell3.getInsideStartOffset(tableCell2) || i > tableCell3.getInsideEndOffset()) {
                                return new TableCellOffsetInfo(i, this, getAllRowsSection(i2), tableRow, tableCell3, i2, i4, null, null);
                            }
                            int i5 = i4;
                            return new TableCellOffsetInfo(i, this, getAllRowsSection(i2), tableRow, tableCell3, i2, i5, Integer.valueOf(i5), Integer.valueOf(i - tableCell3.getInsideStartOffset(tableCell2)));
                        }
                        i4++;
                        tableCell2 = tableCell3;
                    } else {
                        if (i <= tableCell3.text.getEndOffset()) {
                            if (i >= tableCell3.getInsideStartOffset(tableCell2)) {
                            }
                            return new TableCellOffsetInfo(i, this, getAllRowsSection(i2), tableRow, tableCell3, i2, i4, null, null);
                        }
                        i4++;
                        tableCell2 = tableCell3;
                    }
                }
                return new TableCellOffsetInfo(i, this, getAllRowsSection(i2), tableRow, tableCell, i2, i4, null, null);
            }
            i2++;
        }
        return new TableCellOffsetInfo(i, this, getAllRowsSection(i2 - 1), null, null, i2, 0, null, null);
    }

    @Deprecated
    public boolean addTrackedOffset(int i) {
        return addTrackedOffset(TrackedOffset.track(i, null, false));
    }

    @Deprecated
    public boolean addTrackedOffset(int i, boolean z) {
        return addTrackedOffset(TrackedOffset.track(i, z ? ' ' : null, false));
    }

    @Deprecated
    public boolean addTrackedOffset(int i, boolean z, boolean z2) {
        return addTrackedOffset(TrackedOffset.track(i, z ? ' ' : null, z2));
    }

    @Deprecated
    public boolean addTrackedOffset(int i, Character ch, boolean z) {
        return addTrackedOffset(TrackedOffset.track(i, ch, z));
    }

    public boolean addTrackedOffset(TrackedOffset trackedOffset) {
        int offset = trackedOffset.getOffset();
        this.trackedOffsets.removeIf(trackedOffset2 -> {
            return trackedOffset2.getOffset() == offset;
        });
        this.trackedOffsets.add(trackedOffset);
        TableCellOffsetInfo cellOffsetInfo = getCellOffsetInfo(offset);
        if (cellOffsetInfo.getInsideColumn()) {
            cellOffsetInfo.tableRow.cells.set(cellOffsetInfo.column, cellOffsetInfo.tableCell.withTrackedOffset(offset - cellOffsetInfo.tableCell.getTextStartOffset(cellOffsetInfo.column == 0 ? null : cellOffsetInfo.tableRow.cells.get(cellOffsetInfo.column - 1)), trackedOffset.isAfterSpaceEdit(), trackedOffset.isAfterDelete()));
            return true;
        }
        if (cellOffsetInfo.isBeforeCells()) {
            cellOffsetInfo.tableRow.setBeforeOffset(offset);
            return true;
        }
        if (cellOffsetInfo.isInCellSpan()) {
            cellOffsetInfo.tableRow.cells.set(cellOffsetInfo.column, cellOffsetInfo.tableCell.withSpanTrackedOffset(offset - cellOffsetInfo.tableCell.getInsideEndOffset()));
            return true;
        }
        if (cellOffsetInfo.isAfterCells()) {
            cellOffsetInfo.tableRow.setAfterOffset(offset);
            return true;
        }
        return false;
    }

    public List<TableRow> getAllRows() {
        return getAllSectionsRows(this.ALL_TABLE_ROWS);
    }

    public List<TableRow> getAllContentRows() {
        return getAllSectionsRows(this.ALL_CONTENT_ROWS);
    }

    public List<TableRow> getAllSectionRows() {
        return getAllSectionsRows(this.ALL_SECTIONS);
    }

    private List<TableRow> getAllSectionsRows(TableSection... tableSectionArr) {
        ArrayList arrayList = new ArrayList(this.header.rows.size() + this.body.rows.size());
        for (TableSection tableSection : tableSectionArr) {
            arrayList.addAll(tableSection.rows);
        }
        return arrayList;
    }

    public boolean isAllRowsSeparator(int i) {
        return i >= this.header.rows.size() && i < this.header.rows.size() + this.separator.rows.size();
    }

    public TableSection getAllRowsSection(int i) {
        for (TableSection tableSection : this.ALL_SECTIONS) {
            if (i < tableSection.rows.size()) {
                return tableSection;
            }
            i -= tableSection.rows.size();
        }
        return null;
    }

    public int getAllRowsCount() {
        return this.header.rows.size() + this.separator.rows.size() + this.body.rows.size();
    }

    public int getAllContentRowsCount() {
        return this.header.rows.size() + this.body.rows.size();
    }

    public int getAllSectionsRowsCount() {
        return this.header.rows.size() + this.separator.rows.size() + this.body.rows.size() + this.caption.rows.size();
    }

    public void forAllRows(TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_TABLE_ROWS, tableRowManipulator);
    }

    public void forAllRows(int i, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, Integer.MAX_VALUE, this.ALL_TABLE_ROWS, tableRowManipulator);
    }

    public void forAllRows(int i, int i2, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, i2, this.ALL_TABLE_ROWS, tableRowManipulator);
    }

    public void forAllContentRows(TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_CONTENT_ROWS, tableRowManipulator);
    }

    public void forAllContentRows(int i, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, Integer.MAX_VALUE, this.ALL_CONTENT_ROWS, tableRowManipulator);
    }

    public void forAllContentRows(int i, int i2, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, i2, this.ALL_CONTENT_ROWS, tableRowManipulator);
    }

    public void forAllSectionRows(TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_SECTIONS, tableRowManipulator);
    }

    public void forAllSectionRows(int i, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, Integer.MAX_VALUE, this.ALL_SECTIONS, tableRowManipulator);
    }

    public void forAllSectionRows(int i, int i2, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, i2, this.ALL_SECTIONS, tableRowManipulator);
    }

    public void forAllHeaderRows(TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_HEADER_ROWS, tableRowManipulator);
    }

    public void forAllHeaderRows(int i, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, Integer.MAX_VALUE, this.ALL_HEADER_ROWS, tableRowManipulator);
    }

    public void forAllHeaderRows(int i, int i2, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, i2, this.ALL_HEADER_ROWS, tableRowManipulator);
    }

    public void forAllBodyRows(TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_BODY_ROWS, tableRowManipulator);
    }

    public void forAllBodyRows(int i, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, Integer.MAX_VALUE, this.ALL_HEADER_ROWS, tableRowManipulator);
    }

    public void forAllBodyRows(int i, int i2, TableRowManipulator tableRowManipulator) {
        forAllSectionsRows(i, i2, this.ALL_HEADER_ROWS, tableRowManipulator);
    }

    public void deleteRows(int i, int i2) {
        if (i <= this.header.rows.size()) {
            int i3 = i2;
            while (true) {
                int i4 = i3;
                i3--;
                if (i4 > 0 && i < this.header.rows.size()) {
                    this.header.rows.remove(i);
                } else {
                    return;
                }
            }
        } else if (i >= this.header.rows.size() + this.separator.rows.size()) {
            int size = (i - this.header.rows.size()) - this.separator.rows.size();
            int i5 = i2;
            while (true) {
                int i6 = i5;
                i5--;
                if (i6 > 0 && size < this.body.rows.size()) {
                    this.body.rows.remove(size);
                } else {
                    return;
                }
            }
        }
    }

    public void insertRows(int i, int i2) {
        int maxColumns = getMaxColumns();
        if (i <= this.header.rows.size()) {
            insertRows(this.header.rows, i, i2, maxColumns);
        } else {
            insertRows(this.body.rows, Utils.rangeLimit((i - this.header.rows.size()) - this.separator.rows.size(), 0, this.body.rows.size()), i2, maxColumns);
        }
    }

    private void insertRows(ArrayList<TableRow> arrayList, int i, int i2, int i3) {
        int i4 = i2;
        while (true) {
            int i5 = i4;
            i4--;
            if (i5 > 0) {
                TableRow tableRow = new TableRow();
                tableRow.appendColumns(i3);
                if (i >= arrayList.size()) {
                    arrayList.add(tableRow);
                } else {
                    arrayList.add(i, tableRow);
                }
            } else {
                return;
            }
        }
    }

    public void insertColumns(int i, int i2) {
        forAllContentRows((tableRow, i3, arrayList, i4) -> {
            ((TableRow) arrayList.get(i4)).insertColumns(i, i2);
            return 0;
        });
        Iterator<TableRow> it = this.separator.rows.iterator();
        while (it.hasNext()) {
            it.next().insertColumns(i, i2);
        }
    }

    public void deleteColumns(int i, int i2) {
        forAllContentRows((tableRow, i3, arrayList, i4) -> {
            ((TableRow) arrayList.get(i4)).deleteColumns(i, i2);
            return 0;
        });
        Iterator<TableRow> it = this.separator.rows.iterator();
        while (it.hasNext()) {
            it.next().deleteColumns(i, i2);
        }
    }

    public void moveColumn(int i, int i2) {
        forAllContentRows((tableRow, i3, arrayList, i4) -> {
            ((TableRow) arrayList.get(i4)).moveColumn(i, i2);
            return 0;
        });
        Iterator<TableRow> it = this.separator.rows.iterator();
        while (it.hasNext()) {
            it.next().moveColumn(i, i2);
        }
    }

    public boolean isEmptyColumn(int i) {
        boolean[] zArr = {true};
        forAllContentRows((tableRow, i2, arrayList, i3) -> {
            if (!tableRow.isEmptyColumn(i)) {
                zArr[0] = false;
                return Integer.MIN_VALUE;
            }
            return 0;
        });
        return zArr[0];
    }

    public boolean isAllRowsEmptyAt(int i) {
        return isEmptyRowAt(i, this.ALL_TABLE_ROWS);
    }

    public boolean isContentRowsEmptyAt(int i) {
        return isEmptyRowAt(i, this.ALL_CONTENT_ROWS);
    }

    private boolean isEmptyRowAt(int i, TableSection[] tableSectionArr) {
        boolean[] zArr = {false};
        forAllSectionsRows(i, 1, tableSectionArr, (tableRow, i2, arrayList, i3) -> {
            if (tableRow.isEmpty()) {
                zArr[0] = true;
                return Integer.MIN_VALUE;
            }
            return Integer.MIN_VALUE;
        });
        return zArr[0];
    }

    public boolean getHeader() {
        return this.isHeading;
    }

    public void setHeader(boolean z) {
        this.isHeading = z;
    }

    public boolean isSeparator() {
        return this.isSeparator;
    }

    public void setSeparator(boolean z) {
        this.isSeparator = z;
    }

    public void setHeader() {
        this.isHeading = true;
        this.isSeparator = false;
    }

    public void setSeparator() {
        this.isSeparator = true;
        this.isHeading = false;
    }

    public void setBody() {
        this.isSeparator = false;
        this.isHeading = false;
    }

    public void nextRow() {
        if (this.isSeparator) {
            throw new IllegalStateException("Only one separator row allowed");
        }
        if (this.isHeading) {
            this.header.nextRow();
        } else {
            this.body.nextRow();
        }
    }

    public void addCell(TableCell tableCell) {
        TableSection tableSection = this.isSeparator ? this.separator : this.isHeading ? this.header : this.body;
        if (this.isSeparator && (tableCell.columnSpan != 1 || tableCell.rowSpan != 1)) {
            throw new IllegalStateException("Separator columns cannot span rows/columns");
        }
        TableRow tableRow = tableSection.get(tableSection.row);
        while (tableSection.column < tableRow.cells.size() && tableRow.cells.get(tableSection.column) != null) {
            tableSection.column++;
        }
        for (int i = 0; i < tableCell.rowSpan; i++) {
            tableSection.get(tableSection.row + i).set(tableSection.column, tableCell);
            for (int i2 = 1; i2 < tableCell.columnSpan; i2++) {
                tableSection.expandTo(tableSection.row + i, tableSection.column + i2);
                if (tableSection.get(tableSection.row + i).cells.get(tableSection.column + i2) == null) {
                    tableSection.rows.get(tableSection.row + i).set(tableSection.column + i2, TableCell.NULL);
                }
            }
        }
        tableSection.column += tableCell.columnSpan;
    }

    public void normalize() {
        this.header.normalize();
        this.separator.normalize();
        this.body.normalize();
    }

    /* JADX WARN: Type inference failed for: r1v100, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r1v126, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r1v148, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r1v72, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, java.lang.Integer] */
    public void finalizeTable() {
        int i;
        int i2;
        normalize();
        if (this.options.fillMissingColumns) {
            fillMissingColumns(this.options.formatTableFillMissingMinColumn);
        }
        int maxColumns = getMaxColumns();
        this.alignments = new CellAlignment[maxColumns];
        this.columnWidths = new int[maxColumns];
        BitSet bitSet = new BitSet(maxColumns);
        ArrayList<ColumnSpan> arrayList = new ArrayList();
        Ref<Integer> ref = new Ref<>(0);
        if (this.separator.rows.size() > 0) {
            TableRow tableRow = this.separator.rows.get(0);
            int i3 = 0;
            ref.value = 0;
            for (TableCell tableCell : tableRow.cells) {
                if ((this.alignments[i3] == null || (tableCell.columnSpan == 1 && bitSet.get(i3))) && tableCell.alignment != CellAlignment.NONE) {
                    this.alignments[i3] = tableCell.alignment;
                    if (tableCell.columnSpan > 1) {
                        bitSet.set(i3);
                    }
                }
                i3 += tableCell.columnSpan;
            }
        }
        if (this.header.rows.size() > 0) {
            Iterator<TableRow> it = this.header.rows.iterator();
            while (it.hasNext()) {
                TableRow next = it.next();
                int i4 = 0;
                int i5 = 0;
                int size = next.cells.size();
                for (int i6 = 0; i6 < size; i6++) {
                    TableCell tableCell2 = next.cells.get(i6);
                    if ((this.alignments[i5] == null || (tableCell2.columnSpan == 1 && bitSet.get(i5))) && tableCell2.alignment != CellAlignment.NONE) {
                        this.alignments[i5] = tableCell2.alignment;
                        if (tableCell2.columnSpan > 1) {
                            bitSet.set(i5);
                        }
                    }
                    ref.value = 0;
                    int stringWidth = this.options.charWidthProvider.getStringWidth(cellText(next.cells, i6, false, true, 0, null, ref)) + this.options.spacePad + (this.options.pipeWidth * tableCell2.columnSpan);
                    if (tableCell2.columnSpan > 1) {
                        arrayList.add(new ColumnSpan(i4, tableCell2.columnSpan, stringWidth));
                    } else if (this.columnWidths[i5] < stringWidth) {
                        this.columnWidths[i5] = stringWidth;
                    }
                    i4++;
                    i5 += tableCell2.columnSpan;
                }
            }
        }
        if (this.body.rows.size() > 0) {
            Iterator<TableRow> it2 = this.body.rows.iterator();
            while (it2.hasNext()) {
                TableRow next2 = it2.next();
                int i7 = 0;
                int size2 = next2.cells.size();
                for (int i8 = 0; i8 < size2; i8++) {
                    TableCell tableCell3 = next2.cells.get(i8);
                    ref.value = 0;
                    int stringWidth2 = this.options.charWidthProvider.getStringWidth(cellText(next2.cells, i8, false, false, 0, null, ref)) + this.options.spacePad + (this.options.pipeWidth * tableCell3.columnSpan);
                    if (tableCell3.columnSpan > 1) {
                        arrayList.add(new ColumnSpan(i7, tableCell3.columnSpan, stringWidth2));
                    } else if (this.columnWidths[i7] < stringWidth2) {
                        this.columnWidths[i7] = stringWidth2;
                    }
                    i7 += tableCell3.columnSpan;
                }
            }
        }
        if (this.separator.rows.size() == 0 || this.body.rows.size() > 0 || this.header.rows.size() > 0) {
            int i9 = 0;
            ref.value = 0;
            for (CellAlignment cellAlignment : this.alignments) {
                CellAlignment adjustCellAlignment = adjustCellAlignment(cellAlignment);
                if (adjustCellAlignment == CellAlignment.LEFT || adjustCellAlignment == CellAlignment.RIGHT) {
                    i = 1;
                } else {
                    i = adjustCellAlignment == CellAlignment.CENTER ? 2 : 0;
                }
                int i10 = i;
                int minLimit = Utils.minLimit(0, this.options.minSeparatorColumnWidth - i10, this.options.minSeparatorDashes);
                int i11 = ((minLimit > 0 ? minLimit : 0) * this.options.dashWidth) + (i10 * this.options.colonWidth) + this.options.pipeWidth;
                if (this.columnWidths[i9] < i11) {
                    this.columnWidths[i9] = i11;
                }
                i9++;
            }
        } else {
            int i12 = 0;
            ref.value = 0;
            for (TableCell tableCell4 : this.separator.rows.get(0).cells) {
                CellAlignment adjustCellAlignment2 = adjustCellAlignment(tableCell4.alignment);
                if (adjustCellAlignment2 == CellAlignment.LEFT || adjustCellAlignment2 == CellAlignment.RIGHT) {
                    i2 = 1;
                } else {
                    i2 = adjustCellAlignment2 == CellAlignment.CENTER ? 2 : 0;
                }
                int i13 = i2;
                int length = tableCell4.text.trim(COLON_TRIM_CHARS).length();
                int i14 = length;
                int minLimit2 = Utils.minLimit(length, this.options.minSeparatorColumnWidth - i13, this.options.minSeparatorDashes);
                if (i14 < minLimit2) {
                    i14 = minLimit2;
                }
                int i15 = (i14 * this.options.dashWidth) + (i13 * this.options.colonWidth) + this.options.pipeWidth;
                if (this.columnWidths[i12] < i15) {
                    this.columnWidths[i12] = i15;
                }
                i12++;
            }
        }
        if (!arrayList.isEmpty()) {
            BitSet bitSet2 = new BitSet(maxColumns);
            ArrayList<ColumnSpan> arrayList2 = new ArrayList(arrayList.size());
            for (ColumnSpan columnSpan : arrayList) {
                if (spanWidth(columnSpan.startColumn, columnSpan.columnSpan) < columnSpan.width) {
                    bitSet2.set(columnSpan.startColumn, columnSpan.startColumn + columnSpan.columnSpan);
                    arrayList2.add(columnSpan);
                }
            }
            while (!arrayList2.isEmpty()) {
                BitSet bitSet3 = new BitSet(maxColumns);
                arrayList2.clear();
                for (ColumnSpan columnSpan2 : arrayList2) {
                    if (spanWidth(columnSpan2.startColumn, columnSpan2.columnSpan) <= spanFixedWidth(bitSet2, columnSpan2.startColumn, columnSpan2.columnSpan)) {
                        bitSet3.set(columnSpan2.startColumn, columnSpan2.startColumn + columnSpan2.columnSpan);
                    } else {
                        arrayList2.add(columnSpan2);
                    }
                }
                bitSet2.andNot(bitSet3);
                arrayList2.clear();
                for (ColumnSpan columnSpan3 : arrayList2) {
                    int spanWidth = spanWidth(columnSpan3.startColumn, columnSpan3.columnSpan);
                    int spanFixedWidth = spanFixedWidth(bitSet2, columnSpan3.startColumn, columnSpan3.columnSpan);
                    if (spanWidth > spanFixedWidth) {
                        int i16 = spanWidth - spanFixedWidth;
                        int cardinality = bitSet2.get(columnSpan3.startColumn, columnSpan3.startColumn + columnSpan3.columnSpan).cardinality();
                        int i17 = i16 / cardinality;
                        int i18 = i16 - (i17 * cardinality);
                        for (int i19 = 0; i19 < columnSpan3.columnSpan; i19++) {
                            if (bitSet2.get(columnSpan3.startColumn + i19)) {
                                int[] iArr = this.columnWidths;
                                int i20 = columnSpan3.startColumn + i19;
                                iArr[i20] = iArr[i20] + i17;
                                if (i18 > 0) {
                                    int[] iArr2 = this.columnWidths;
                                    int i21 = columnSpan3.startColumn + i19;
                                    iArr2[i21] = iArr2[i21] + 1;
                                    i18--;
                                }
                            }
                        }
                        arrayList2.add(columnSpan3);
                    }
                }
            }
        }
    }

    public void fillMissingColumns() {
        fillMissingColumns(null);
    }

    public void fillMissingColumns(Integer num) {
        int minColumns = getMinColumns();
        int maxColumns = getMaxColumns();
        if (minColumns < maxColumns) {
            Iterator<TableRow> it = this.header.rows.iterator();
            while (it.hasNext()) {
                it.next().fillMissingColumns(num, maxColumns);
            }
            Iterator<TableRow> it2 = this.body.rows.iterator();
            while (it2.hasNext()) {
                it2.next().fillMissingColumns(num, maxColumns);
            }
        }
    }

    private boolean setTrackedOffsetIndex(int i, int i2) {
        TrackedOffset findTrackedOffset = findTrackedOffset(i);
        if (findTrackedOffset != null) {
            findTrackedOffset.setIndex(i2);
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v50 */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.vladsch.flexmark.util.format.TableCell[], com.vladsch.flexmark.util.format.TableCell[][]] */
    public MarkdownTable transposed(int i) {
        MarkdownTable markdownTable = new MarkdownTable(this.tableChars, this.options);
        markdownTable.trackedOffsets.addAll(this.trackedOffsets);
        int allRowsCount = getAllRowsCount() - 1;
        int maxColumns = getMaxColumns();
        ?? r0 = new TableCell[allRowsCount];
        for (int i2 = 0; i2 < allRowsCount; i2++) {
            r0[i2] = new TableCell[maxColumns];
        }
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_CONTENT_ROWS, (tableRow, i3, arrayList, i4) -> {
            TableCell[] tableCellArr = r0[i3];
            int size = tableRow.cells.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                TableCell tableCell = tableRow.cells.get(i4);
                int i5 = 0;
                while (i5 < tableCell.columnSpan) {
                    int i6 = i3;
                    i3++;
                    tableCellArr[i6] = new TableCell(tableCell, i5 == 0, 1, 1, (CellAlignment) null);
                    i5++;
                }
            }
            return 0;
        });
        markdownTable.setHeader();
        int min = Math.min(Math.max(0, i), maxColumns);
        for (int i5 = 0; i5 < min; i5++) {
            for (int i6 = 0; i6 < allRowsCount; i6++) {
                ?? r02 = r0[i6][i5];
                markdownTable.addCell(r02 == 0 ? TableCell.DEFAULT_CELL : r02);
            }
            markdownTable.nextRow();
        }
        TableRow tableRow2 = this.separator.rows.get(0);
        markdownTable.setSeparator();
        int size = tableRow2.cells.size();
        for (int i7 = 0; i7 < allRowsCount; i7++) {
            if (i7 < size) {
                markdownTable.addCell(new TableCell(tableRow2.cells.get(i7), true, 1, 1, (CellAlignment) null));
            } else {
                markdownTable.addCell(new TableCell(NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR, 1, 1));
            }
        }
        markdownTable.setBody();
        for (int i8 = min; i8 < maxColumns; i8++) {
            for (int i9 = 0; i9 < allRowsCount; i9++) {
                ?? r03 = r0[i9][i8];
                markdownTable.addCell(r03 == 0 ? TableCell.DEFAULT_CELL : r03);
            }
            markdownTable.nextRow();
        }
        markdownTable.setCaptionCell(getCaptionCell());
        return markdownTable;
    }

    public MarkdownTable sorted(ColumnSort[] columnSortArr, int i, NumericSuffixPredicate numericSuffixPredicate) {
        MarkdownTable markdownTable = new MarkdownTable(this.tableChars, this.options);
        markdownTable.trackedOffsets.addAll(this.trackedOffsets);
        markdownTable.setHeader();
        forAllSectionsRows(0, Integer.MAX_VALUE, this.ALL_HEADER_ROWS, (tableRow, i2, arrayList, i3) -> {
            int size = tableRow.cells.size();
            for (int i2 = 0; i2 < size; i2++) {
                TableCell tableCell = tableRow.cells.get(i2);
                markdownTable.addCell(tableCell == TableCell.DEFAULT_CELL ? tableCell : new TableCell(tableCell, true, tableCell.rowSpan, tableCell.columnSpan, tableCell.alignment));
            }
            markdownTable.nextRow();
            return 0;
        });
        markdownTable.setSeparator();
        TableRow tableRow2 = this.separator.rows.get(0);
        int size = tableRow2.cells.size();
        CellAlignment[] cellAlignmentArr = new CellAlignment[size];
        for (int i4 = 0; i4 < size; i4++) {
            TableCell tableCell = tableRow2.cells.get(i4);
            markdownTable.addCell(tableCell == TableCell.DEFAULT_CELL ? tableCell : new TableCell(tableCell, true, tableCell.rowSpan, tableCell.columnSpan, tableCell.alignment));
            cellAlignmentArr[i4] = tableCell.alignment;
        }
        List<TableRow> allSectionsRows = getAllSectionsRows(this.body);
        int[] iArr = new int[size];
        int size2 = allSectionsRows.size();
        int maxBodyColumns = getMaxBodyColumns();
        for (int i5 = 0; i5 < size2; i5++) {
            for (ColumnSort columnSort : columnSortArr) {
                int i6 = columnSort.column;
                if (i6 >= 0 && i6 < maxBodyColumns) {
                    IndexSpanOffset indexOf = allSectionsRows.get(i5).indexOf(i6);
                    TableCell tableCell2 = allSectionsRows.get(i5).cells.get(indexOf.index);
                    if (indexOf.index == i6 && tableCell2 != null) {
                        iArr[i6] = Math.max(iArr[i6], tableCell2.text.length());
                    }
                }
            }
        }
        TextCollectingVisitor textCollectingVisitor = new TextCollectingVisitor();
        NumericSuffixPredicate numericSuffixPredicate2 = numericSuffixPredicate == null ? ALL_SUFFIXES_SORT : numericSuffixPredicate;
        allSectionsRows.sort((tableRow3, tableRow4) -> {
            int i7;
            int i8;
            for (ColumnSort columnSort2 : columnSortArr) {
                int i9 = columnSort2.column;
                if (i9 >= 0 && i9 < maxBodyColumns && (i7 = iArr[i9]) > 0) {
                    Sort sort = columnSort2.sort;
                    boolean isDescending = sort.isDescending();
                    boolean isNumeric = sort.isNumeric();
                    boolean isNumericLast = sort.isNumericLast();
                    IndexSpanOffset indexOf2 = tableRow3.indexOf(i9);
                    TableCell tableCell3 = tableRow3.cells.get(indexOf2.index);
                    IndexSpanOffset indexOf3 = tableRow4.indexOf(i9);
                    TableCell tableCell4 = tableRow4.cells.get(indexOf3.index);
                    if (indexOf2.index == i9 && tableCell3 != null && indexOf3.index == i9 && tableCell4 != null) {
                        int i10 = 0;
                        int i11 = 0;
                        int i12 = 0;
                        int i13 = 0;
                        String obj = tableCell3.tableCellNode == null ? tableCell3.text.toString() : textCollectingVisitor.collectAndGetText(tableCell3.tableCellNode, i).trim();
                        String obj2 = tableCell4.tableCellNode == null ? tableCell4.text.toString() : textCollectingVisitor.collectAndGetText(tableCell4.tableCellNode, i).trim();
                        int length = i7 - obj.length();
                        int length2 = i7 - obj2.length();
                        switch (cellAlignmentArr[i9]) {
                            case RIGHT:
                                i10 = length;
                                i11 = length2;
                                break;
                            case CENTER:
                                i10 = length >> 1;
                                i12 = i7 - i10;
                                i11 = length2 >> 1;
                                i13 = i7 - i11;
                                break;
                        }
                        if (isNumeric) {
                            Pair<Number, String> parseNumberPrefixOrNull = SequenceUtils.parseNumberPrefixOrNull(obj, numericSuffixPredicate2);
                            Pair<Number, String> parseNumberPrefixOrNull2 = SequenceUtils.parseNumberPrefixOrNull(obj2, numericSuffixPredicate2);
                            if (parseNumberPrefixOrNull != null && parseNumberPrefixOrNull2 != null) {
                                i8 = Utils.compare(parseNumberPrefixOrNull.getFirst(), parseNumberPrefixOrNull2.getFirst());
                                String second = parseNumberPrefixOrNull.getSecond();
                                String second2 = parseNumberPrefixOrNull2.getSecond();
                                if (i8 == 0 && (numericSuffixPredicate2.sortSuffix(second) || numericSuffixPredicate2.sortSuffix(second2))) {
                                    if (!second.isEmpty() && numericSuffixPredicate2.sortSuffix(second) && !second2.isEmpty() && numericSuffixPredicate2.sortSuffix(second2)) {
                                        i8 = second.compareTo(parseNumberPrefixOrNull2.getSecond());
                                    } else if (!second.isEmpty() && numericSuffixPredicate2.sortSuffix(second)) {
                                        i8 = isNumericLast ? -1 : 1;
                                        isDescending = false;
                                    } else if (!second2.isEmpty() && numericSuffixPredicate2.sortSuffix(second2)) {
                                        i8 = isNumericLast ? 1 : -1;
                                        isDescending = false;
                                    }
                                }
                            } else if (parseNumberPrefixOrNull != null) {
                                i8 = isNumericLast ? 1 : -1;
                                isDescending = false;
                            } else if (parseNumberPrefixOrNull2 != null) {
                                i8 = isNumericLast ? -1 : 1;
                                isDescending = false;
                            } else {
                                i8 = (RepeatedSequence.ofSpaces(i10).toString() + obj + ((Object) RepeatedSequence.ofSpaces(i12))).compareTo(RepeatedSequence.ofSpaces(i11).toString() + obj2 + ((Object) RepeatedSequence.ofSpaces(i13)));
                            }
                        } else {
                            i8 = (RepeatedSequence.ofSpaces(i10).toString() + obj + ((Object) RepeatedSequence.ofSpaces(i12))).compareTo(RepeatedSequence.ofSpaces(i11).toString() + obj2 + ((Object) RepeatedSequence.ofSpaces(i13)));
                        }
                    } else {
                        i8 = (indexOf2.index != i9 || tableCell3 == null) ? (indexOf3.index != i9 || tableCell4 == null) ? 0 : -1 : 1;
                    }
                    if (i8 != 0) {
                        return isDescending ? -i8 : i8;
                    }
                }
            }
            return 0;
        });
        markdownTable.setBody();
        int size3 = allSectionsRows.size();
        for (int i7 = 0; i7 < size3; i7++) {
            TableRow tableRow5 = allSectionsRows.get(i7);
            int size4 = tableRow5.cells.size();
            for (int i8 = 0; i8 < size4; i8++) {
                TableCell tableCell3 = tableRow5.cells.get(i8);
                markdownTable.addCell(tableCell3 == TableCell.DEFAULT_CELL ? tableCell3 : new TableCell(tableCell3, true, tableCell3.rowSpan, tableCell3.columnSpan, tableCell3.alignment));
            }
            markdownTable.nextRow();
        }
        markdownTable.setCaptionCell(getCaptionCell());
        return markdownTable;
    }

    int appendDashes(LineAppendable lineAppendable, int i, BasedSequence basedSequence, int i2) {
        int length = basedSequence.length();
        int max = Math.max(0, length - i2);
        int i3 = max;
        if (max >= i) {
            lineAppendable.append((CharSequence) basedSequence.subSequence(i2, i2 + i));
            i3 -= i;
        } else {
            int i4 = 0;
            if (i3 > 1) {
                lineAppendable.append((CharSequence) basedSequence.subSequence(i2, i2 + 1));
                i3--;
                i4 = 0 + 1;
            }
            lineAppendable.append('-', i - Math.max(0, i3 + i4));
            if (i3 > 0) {
                lineAppendable.append((CharSequence) basedSequence.subSequence(i2, i2 + i3));
                i3 = 0;
            }
        }
        return length - i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v100, types: [T, java.lang.Integer] */
    public void appendTable(LineAppendable lineAppendable) {
        int i;
        CharSequence charSequence = this.formatTableIndentPrefix;
        this.trackedOffsets.sort(Comparator.comparing((v0) -> {
            return v0.getOffset();
        }));
        lineAppendable.pushOptions();
        lineAppendable.removeOptions(LineAppendable.F_WHITESPACE_REMOVAL);
        finalizeTable();
        appendRows(lineAppendable, this.header.rows, true, charSequence);
        lineAppendable.append(charSequence);
        TableRow tableRow = this.separator.rows.size() > 0 ? this.separator.rows.get(0) : null;
        TableRow tableRow2 = tableRow;
        if (tableRow != null && tableRow2.beforeOffset != Integer.MAX_VALUE) {
            setTrackedOffsetIndex(tableRow2.beforeOffset, lineAppendable.offsetWithPending());
        }
        int i2 = 0;
        Ref ref = new Ref(0);
        for (CellAlignment cellAlignment : this.alignments) {
            CellAlignment adjustCellAlignment = adjustCellAlignment(cellAlignment);
            if (adjustCellAlignment == CellAlignment.LEFT || adjustCellAlignment == CellAlignment.RIGHT) {
                i = 1;
            } else {
                i = adjustCellAlignment == CellAlignment.CENTER ? 2 : 0;
            }
            int i3 = i;
            int i4 = (this.columnWidths[i2] - (i3 * this.options.colonWidth)) - this.options.pipeWidth;
            int intValue = (((Integer) ref.value).intValue() + i4) / this.options.dashWidth;
            int i5 = intValue;
            int minLimit = Utils.minLimit(intValue, this.options.minSeparatorColumnWidth - i3, this.options.minSeparatorDashes);
            if (i5 < minLimit) {
                i5 = minLimit;
            }
            if (Math.abs((((Integer) ref.value).intValue() + i4) - ((i5 + 1) * this.options.dashWidth)) < Math.abs((((Integer) ref.value).intValue() + i4) - (i5 * this.options.dashWidth))) {
                i5++;
            }
            ref.value = Integer.valueOf(((Integer) ref.value).intValue() + (i4 - (i5 * this.options.dashWidth)));
            TableCell tableCell = null;
            TableCell tableCell2 = null;
            if (tableRow2 != null) {
                List<TableCell> list = tableRow2.cells;
                if (i2 < list.size()) {
                    tableCell = list.get(i2);
                    if (i2 > 0) {
                        tableCell2 = list.get(i2 - 1);
                    }
                }
            }
            int minLimit2 = tableCell == null ? Integer.MAX_VALUE : Utils.minLimit(tableCell.trackedTextOffset, 0);
            BasedSequence trim = tableCell == null ? BasedSequence.NULL : tableCell.text.trim(COLON_TRIM_CHARS);
            if (minLimit2 != Integer.MAX_VALUE) {
                if (this.options.leadTrailPipes && i2 == 0) {
                    lineAppendable.append('|');
                }
                boolean z = minLimit2 == 0 && tableCell.text.charAt(minLimit2) == ':';
                boolean z2 = minLimit2 == 1 && tableCell.text.charAt(minLimit2 - 1) == ':';
                boolean z3 = minLimit2 == tableCell.text.length() - 1 && tableCell.text.charAt(minLimit2) == ':';
                boolean z4 = minLimit2 == tableCell.text.length() && tableCell.text.charAt(minLimit2 - 1) == ':';
                boolean z5 = minLimit2 == tableCell.text.length() && tableCell.text.charAt(minLimit2 - 1) == '-';
                if (adjustCellAlignment == CellAlignment.LEFT || adjustCellAlignment == CellAlignment.CENTER) {
                    if (z) {
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        minLimit2 = Integer.MAX_VALUE;
                        lineAppendable.append(':');
                    } else if (z2) {
                        lineAppendable.append(':');
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        minLimit2 = Integer.MAX_VALUE;
                    } else {
                        lineAppendable.append(':');
                    }
                } else {
                    z = false;
                    z2 = false;
                }
                if (!z2 && !z && !z4 && !z3) {
                    if (minLimit2 == 0) {
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        minLimit2 = Integer.MAX_VALUE;
                        appendDashes(lineAppendable, i5, trim, 0);
                    } else if (!z5 && minLimit2 < i5) {
                        int appendDashes = appendDashes(lineAppendable, minLimit2, trim, 0);
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        appendDashes(lineAppendable, i5 - minLimit2, trim, appendDashes);
                        minLimit2 = Integer.MAX_VALUE;
                    } else {
                        appendDashes(lineAppendable, i5, trim, 0);
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        minLimit2 = Integer.MAX_VALUE;
                    }
                } else {
                    appendDashes(lineAppendable, i5, trim, 0);
                }
                if (adjustCellAlignment == CellAlignment.RIGHT || adjustCellAlignment == CellAlignment.CENTER) {
                    if (z4) {
                        lineAppendable.append(':');
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        minLimit2 = Integer.MAX_VALUE;
                    } else if (z3) {
                        setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                        minLimit2 = Integer.MAX_VALUE;
                        lineAppendable.append(':');
                    } else {
                        lineAppendable.append(':');
                    }
                } else if (z4 || z3) {
                    setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getInsideStartOffset(tableCell2), lineAppendable.offsetWithPending());
                    minLimit2 = Integer.MAX_VALUE;
                }
                if (!$assertionsDisabled && minLimit2 != Integer.MAX_VALUE) {
                    throw new AssertionError();
                }
            } else {
                if (this.options.leadTrailPipes && i2 == 0) {
                    lineAppendable.append('|');
                }
                if (adjustCellAlignment == CellAlignment.LEFT || adjustCellAlignment == CellAlignment.CENTER) {
                    lineAppendable.append(':');
                }
                appendDashes(lineAppendable, i5, trim, 0);
                if (adjustCellAlignment == CellAlignment.RIGHT || adjustCellAlignment == CellAlignment.CENTER) {
                    lineAppendable.append(':');
                }
            }
            i2++;
            if (this.options.leadTrailPipes || i2 < this.alignments.length) {
                lineAppendable.append('|');
            }
        }
        if (tableRow2 != null && tableRow2.afterOffset != Integer.MAX_VALUE) {
            setTrackedOffsetIndex(tableRow2.afterOffset, lineAppendable.offsetWithPending());
        }
        lineAppendable.line();
        appendRows(lineAppendable, this.body.rows, false, charSequence);
        TableCell captionCell = getCaptionCell();
        String formattedCaption = formattedCaption(captionCell.text, this.options);
        if (formattedCaption != null) {
            BasedSequence subSequence = BasedSequence.of(formattedCaption).subSequence(0, formattedCaption.length());
            boolean z6 = false;
            if (this.caption.rows.size() > 0) {
                TableRow tableRow3 = this.caption.rows.get(0);
                if (captionCell.trackedTextOffset != Integer.MAX_VALUE || tableRow3.beforeOffset != Integer.MAX_VALUE || tableRow3.afterOffset != Integer.MAX_VALUE) {
                    lineAppendable.line();
                    if (tableRow3.beforeOffset != Integer.MAX_VALUE) {
                        setTrackedOffsetIndex(tableRow3.beforeOffset, lineAppendable.offsetWithPending());
                    }
                    TableCell withText = captionCell.withText(captionCell.text.trim());
                    if (captionCell.trackedTextOffset != Integer.MAX_VALUE) {
                        withText = withText.withTrackedOffset(Utils.minLimit(captionCell.trackedTextOffset - captionCell.text.trimmedStart().length(), 0));
                    }
                    boolean z7 = false;
                    boolean z8 = false;
                    if (!withText.text.isBlank()) {
                        switch (this.options.formatTableCaptionSpaces) {
                            case ADD:
                                z7 = true;
                                z8 = true;
                                break;
                            case REMOVE:
                                break;
                            default:
                                z7 = captionCell.text.startsWith(SequenceUtils.SPACE);
                                z8 = captionCell.text.endsWith(SequenceUtils.SPACE);
                                break;
                        }
                    }
                    lineAppendable.append(charSequence);
                    lineAppendable.append('[');
                    if (z7) {
                        lineAppendable.append(' ');
                    }
                    int offsetWithPending = lineAppendable.offsetWithPending();
                    tableRow3.cells.set(0, withText);
                    BasedSequence cellText = cellText(tableRow3.cells, 0, true, false, 0, CellAlignment.LEFT, new Ref<>(0));
                    lineAppendable.offsetWithPending();
                    if (tableRow3.cells.size() <= 0) {
                        tableRow3.cells.add(captionCell);
                    } else {
                        if (captionCell.trackedTextOffset != Integer.MAX_VALUE) {
                            TableCell tableCell3 = tableRow3.cells.get(0);
                            if (tableCell3.trackedTextOffset != Integer.MAX_VALUE) {
                                setTrackedOffsetIndex(captionCell.trackedTextOffset + captionCell.text.getStartOffset(), offsetWithPending + (cellText.isBlank() ? 1 : Utils.minLimit(tableCell3.trackedTextOffset, 0) + tableCell3.trackedTextAdjust));
                            }
                        }
                        tableRow3.cells.set(0, captionCell);
                    }
                    lineAppendable.append((CharSequence) cellText);
                    if (z8) {
                        lineAppendable.append(' ');
                    }
                    lineAppendable.append(']');
                    if (tableRow3.afterOffset != Integer.MAX_VALUE) {
                        setTrackedOffsetIndex(tableRow3.afterOffset, lineAppendable.offsetWithPending());
                    }
                    lineAppendable.line();
                    z6 = true;
                }
            }
            if (!z6) {
                lineAppendable.popOptions().pushOptions();
                lineAppendable.line().append(charSequence).append('[').append((CharSequence) subSequence).append(']').line();
            }
        }
        lineAppendable.popOptions();
    }

    public static void appendFormattedCaption(LineAppendable lineAppendable, BasedSequence basedSequence, TableFormatOptions tableFormatOptions) {
        String formattedCaption = formattedCaption(basedSequence, tableFormatOptions);
        if (formattedCaption != null) {
            lineAppendable.line().append('[').append((CharSequence) formattedCaption).append(']').line();
        }
    }

    public static String formattedCaption(BasedSequence basedSequence, TableFormatOptions tableFormatOptions) {
        boolean isNotNull = basedSequence.isNotNull();
        switch (tableFormatOptions.formatTableCaption) {
            case ADD:
                isNotNull = true;
                break;
            case REMOVE_EMPTY:
                isNotNull = !basedSequence.isBlank();
                break;
            case REMOVE:
                isNotNull = false;
                break;
        }
        if (isNotNull) {
            String str = "";
            switch (tableFormatOptions.formatTableCaptionSpaces) {
                case ADD:
                    str = SequenceUtils.SPACE;
                    break;
            }
            return str + basedSequence.toString() + str;
        }
        return null;
    }

    private boolean pipeNeedsSpaceBefore(TableCell tableCell) {
        return tableCell.text.equals(SequenceUtils.SPACE) || !tableCell.text.endsWith(SequenceUtils.SPACE);
    }

    private boolean pipeNeedsSpaceAfter(TableCell tableCell) {
        return tableCell.text.equals(SequenceUtils.SPACE) || !tableCell.text.startsWith(SequenceUtils.SPACE);
    }

    private void appendRows(LineAppendable lineAppendable, List<TableRow> list, boolean z, CharSequence charSequence) {
        for (TableRow tableRow : list) {
            int i = 0;
            int i2 = 0;
            Ref<Integer> ref = new Ref<>(0);
            lineAppendable.append(charSequence);
            if (tableRow.beforeOffset != Integer.MAX_VALUE) {
                setTrackedOffsetIndex(tableRow.beforeOffset, lineAppendable.offsetWithPending());
            }
            int size = tableRow.cells.size();
            int i3 = 0;
            while (i3 < size) {
                TableCell tableCell = tableRow.cells.get(i3);
                if (i == 0) {
                    if (this.options.leadTrailPipes) {
                        lineAppendable.append('|');
                        if (this.options.spaceAroundPipes && pipeNeedsSpaceAfter(tableCell)) {
                            lineAppendable.append(' ');
                        }
                    }
                } else if (this.options.spaceAroundPipes && pipeNeedsSpaceAfter(tableCell)) {
                    lineAppendable.append(' ');
                }
                BasedSequence cellText = cellText(tableRow.cells, i3, true, z, (spanWidth(i2, tableCell.columnSpan) - this.options.spacePad) - (this.options.pipeWidth * tableCell.columnSpan), (!z || tableCell.alignment == CellAlignment.NONE) ? this.alignments[i2] : tableCell.alignment, ref);
                if (tableCell.trackedTextOffset != Integer.MAX_VALUE) {
                    TableCell tableCell2 = tableRow.cells.get(i3);
                    if (tableCell2.trackedTextOffset != Integer.MAX_VALUE) {
                        if (!setTrackedOffsetIndex(tableCell.trackedTextOffset + tableCell.getTextStartOffset(i3 == 0 ? null : tableRow.cells.get(i3 - 1)), lineAppendable.offsetWithPending() + Utils.minLimit(tableCell2.trackedTextOffset + (tableCell.text.isBlank() ? -1 : 0), 0) + tableCell2.trackedTextAdjust)) {
                            PrintStream printStream = System.out;
                            Object[] objArr = new Object[3];
                            objArr[0] = Integer.valueOf(tableCell.trackedTextOffset);
                            objArr[1] = Integer.valueOf(tableCell.trackedTextOffset + tableCell.getTextStartOffset(i3 == 0 ? null : tableRow.cells.get(i3 - 1)));
                            objArr[2] = this.trackedOffsets;
                            printStream.println(String.format("Offset not found: cell.trackedTextOffset: %d, adjusted trackedOffset: %d in offsets: %s", objArr));
                        }
                    }
                }
                lineAppendable.append((CharSequence) cellText);
                i++;
                i2 += tableCell.columnSpan;
                if (i < this.alignments.length) {
                    if (this.options.spaceAroundPipes && pipeNeedsSpaceBefore(tableCell)) {
                        lineAppendable.append(' ');
                    }
                    appendColumnSpan(lineAppendable, tableCell.columnSpan, tableCell.getInsideEndOffset(), tableCell.spanTrackedOffset);
                } else if (this.options.leadTrailPipes) {
                    if (this.options.spaceAroundPipes && pipeNeedsSpaceBefore(tableCell)) {
                        lineAppendable.append(' ');
                    }
                    appendColumnSpan(lineAppendable, tableCell.columnSpan, tableCell.getInsideEndOffset(), tableCell.spanTrackedOffset);
                } else {
                    if (this.options.spaceAroundPipes && pipeNeedsSpaceBefore(tableCell)) {
                        lineAppendable.append(' ');
                    }
                    appendColumnSpan(lineAppendable, tableCell.columnSpan - 1, tableCell.getInsideEndOffset(), tableCell.spanTrackedOffset);
                }
                i3++;
            }
            if (tableRow.afterOffset != Integer.MAX_VALUE) {
                setTrackedOffsetIndex(tableRow.afterOffset, lineAppendable.offsetWithPending());
            }
            if (i > 0) {
                lineAppendable.line();
            }
        }
    }

    private void appendColumnSpan(LineAppendable lineAppendable, int i, int i2, int i3) {
        if (i3 == Integer.MAX_VALUE) {
            lineAppendable.append('|', i);
            return;
        }
        if (i3 == 0) {
            setTrackedOffsetIndex(i2 + i3, lineAppendable.offsetWithPending());
            lineAppendable.append('|', i);
        } else if (i3 < i) {
            lineAppendable.append('|', i3);
            setTrackedOffsetIndex(i2 + i3, lineAppendable.offsetWithPending());
            lineAppendable.append('|', i - i3);
        } else {
            lineAppendable.append('|', i);
            setTrackedOffsetIndex(i2 + i3, lineAppendable.offsetWithPending());
        }
    }

    /* JADX WARN: Type inference failed for: r1v25, types: [T, java.lang.Integer] */
    private BasedSequence cellText(List<TableCell> list, int i, boolean z, boolean z2, int i2, CellAlignment cellAlignment, Ref<Integer> ref) {
        TableCell tableCell = list.get(i);
        TableCell tableCell2 = tableCell;
        BasedSequence basedSequence = tableCell.text;
        boolean z3 = tableCell.trackedTextOffset != Integer.MAX_VALUE && tableCell.trackedTextOffset >= tableCell.text.length();
        boolean z4 = false;
        if (tableCell.trackedTextOffset != Integer.MAX_VALUE) {
            if (tableCell.trackedTextOffset <= tableCell.text.length()) {
                if (tableCell.trackedTextOffset < 0) {
                    z4 = true;
                }
            } else {
                basedSequence = basedSequence.append(RepeatedSequence.repeatOf(' ', (tableCell.trackedTextOffset - tableCell.text.length()) - 1));
            }
        }
        int stringWidth = this.options.charWidthProvider.getStringWidth(basedSequence);
        if (this.options.adjustColumnWidth && (stringWidth < i2 || tableCell.trackedTextOffset > tableCell.text.length())) {
            if (!this.options.applyColumnAlignment || cellAlignment == null || cellAlignment == CellAlignment.NONE) {
                cellAlignment = (!z2 || this.options.leftAlignMarker == DiscretionaryText.ADD) ? CellAlignment.LEFT : CellAlignment.CENTER;
            } else if (z2 && cellAlignment == CellAlignment.LEFT && this.options.leftAlignMarker == DiscretionaryText.REMOVE) {
                cellAlignment = CellAlignment.CENTER;
            }
            int i3 = i2 - stringWidth;
            int intValue = (ref.value.intValue() + i3) / this.options.spaceWidth;
            if (i2 > 0 && Math.abs((ref.value.intValue() + i3) - ((intValue + 1) * this.options.spaceWidth)) < Math.abs((ref.value.intValue() + i3) - (intValue * this.options.spaceWidth))) {
                intValue++;
            }
            ref.value = Integer.valueOf(ref.value.intValue() + (i3 - (intValue * this.options.spaceWidth)));
            switch (cellAlignment) {
                case LEFT:
                    if (intValue > 0) {
                        basedSequence = basedSequence.append(PrefixedSubSequence.repeatOf(SequenceUtils.SPACE, intValue, basedSequence.getEmptySuffix()));
                    }
                    if (z && z3 && tableCell.afterSpace && intValue <= 0) {
                        tableCell2 = tableCell2.withTrackedTextAdjust(1);
                        break;
                    }
                    break;
                case RIGHT:
                    if (intValue > 0) {
                        basedSequence = PrefixedSubSequence.repeatOf(SequenceUtils.SPACE, intValue, basedSequence);
                        if (z && tableCell.trackedTextOffset != Integer.MAX_VALUE) {
                            tableCell2 = tableCell.withTrackedOffset(Utils.maxLimit(basedSequence.length(), tableCell.trackedTextOffset + intValue));
                        }
                        if (z && z4 && tableCell.afterSpace) {
                            tableCell2 = tableCell2.withTrackedTextAdjust(1);
                        }
                    }
                    if (z && z3 && tableCell.afterSpace && (intValue <= 0 || !tableCell.afterDelete)) {
                        tableCell2 = tableCell2.withTrackedTextAdjust(1);
                        break;
                    }
                    break;
                case CENTER:
                    int i4 = intValue / 2;
                    if (intValue > 0) {
                        basedSequence = PrefixedSubSequence.repeatOf(SequenceUtils.SPACE, i4, basedSequence).append(PrefixedSubSequence.repeatOf(SequenceUtils.SPACE, intValue - i4, basedSequence.getEmptySuffix()));
                        if (z && tableCell.trackedTextOffset != Integer.MAX_VALUE) {
                            tableCell2 = tableCell.withTrackedOffset(Utils.maxLimit(basedSequence.length(), tableCell.trackedTextOffset + i4));
                        }
                        if (z && z4 && tableCell.afterSpace) {
                            tableCell2 = tableCell2.withTrackedTextAdjust(1);
                            break;
                        }
                    } else if (z && z3 && tableCell.afterSpace) {
                        tableCell2 = tableCell2.withTrackedTextAdjust(1);
                        break;
                    }
                    break;
            }
        }
        if (z && tableCell2.trackedTextOffset != Integer.MAX_VALUE) {
            if (tableCell2.trackedTextOffset > basedSequence.length()) {
                tableCell2 = tableCell2.withTrackedOffset(basedSequence.length());
            }
            if (tableCell2 != tableCell) {
                list.set(i, tableCell2);
            }
        }
        return basedSequence;
    }

    private int spanWidth(int i, int i2) {
        if (i2 > 1) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                i3 += this.columnWidths[i4 + i];
            }
            return i3;
        }
        return this.columnWidths[i];
    }

    private int spanFixedWidth(BitSet bitSet, int i, int i2) {
        if (i2 > 1) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                if (!bitSet.get(i4)) {
                    i3 += this.columnWidths[i4 + i];
                }
            }
            return i3;
        }
        if (bitSet.get(i)) {
            return 0;
        }
        return this.columnWidths[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownTable$ColumnSpan.class */
    public static class ColumnSpan {
        final int startColumn;
        final int columnSpan;
        final int width;
        int additionalWidth = 0;

        public ColumnSpan(int i, int i2, int i3) {
            this.startColumn = i;
            this.columnSpan = i2;
            this.width = i3;
        }
    }

    private CellAlignment adjustCellAlignment(CellAlignment cellAlignment) {
        switch (this.options.leftAlignMarker) {
            case ADD:
                if (cellAlignment == null || cellAlignment == CellAlignment.NONE) {
                    cellAlignment = CellAlignment.LEFT;
                    break;
                }
                break;
            case REMOVE:
                if (cellAlignment == CellAlignment.LEFT) {
                    cellAlignment = CellAlignment.NONE;
                    break;
                }
                break;
        }
        return cellAlignment;
    }

    private int aggregateTotalColumnsWithoutColumns(TableSection[] tableSectionArr, BiFunction<Integer, Integer, Integer> biFunction, int... iArr) {
        Integer[] numArr = {null};
        forAllSectionsRows(0, Integer.MAX_VALUE, tableSectionArr, (tableRow, i, arrayList, i2) -> {
            int size = tableRow.cells.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                if (!ArrayUtils.contained(i2, iArr)) {
                    i += tableRow.cells.get(i2).columnSpan;
                }
            }
            if (i != 0) {
                numArr[0] = (Integer) biFunction.apply(numArr[0], Integer.valueOf(i));
                return 0;
            }
            return 0;
        });
        if (numArr[0] == null) {
            return 0;
        }
        return numArr[0].intValue();
    }

    private int aggregateTotalColumnsWithoutRows(TableSection[] tableSectionArr, BiFunction<Integer, Integer, Integer> biFunction, int... iArr) {
        Integer[] numArr = {null};
        forAllSectionsRows(0, Integer.MAX_VALUE, tableSectionArr, (tableRow, i, arrayList, i2) -> {
            int totalColumns;
            if (!ArrayUtils.contained(i, iArr) && (totalColumns = tableRow.getTotalColumns()) > 0) {
                numArr[0] = (Integer) biFunction.apply(numArr[0], Integer.valueOf(totalColumns));
                return 0;
            }
            return 0;
        });
        if (numArr[0] == null) {
            return 0;
        }
        return numArr[0].intValue();
    }

    private void forAllSectionsRows(int i, int i2, TableSection[] tableSectionArr, TableRowManipulator tableRowManipulator) {
        if (i2 <= 0) {
            return;
        }
        int i3 = i2;
        int i4 = i;
        int i5 = i;
        for (TableSection tableSection : tableSectionArr) {
            if (i4 >= tableSection.rows.size()) {
                i4 -= tableSection.rows.size();
            } else {
                int i6 = i4;
                i4 = 0;
                while (i6 < tableSection.rows.size()) {
                    int apply = tableRowManipulator.apply(tableSection.rows.get(i6), i5, tableSection.rows, i6);
                    if (apply == Integer.MIN_VALUE) {
                        return;
                    }
                    if (apply < 0) {
                        i5 -= apply;
                        i3 += apply;
                    } else {
                        i6 += apply + 1;
                        i3--;
                    }
                    if (i3 <= 0) {
                        return;
                    } else {
                        i5++;
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownTable$IndexSpanOffset.class */
    public static class IndexSpanOffset {
        public final int index;
        public final int spanOffset;

        public IndexSpanOffset(int i, int i2) {
            this.index = i;
            this.spanOffset = i2;
        }

        public String toString() {
            return "IndexSpanOffset{index=" + this.index + ", spanOffset=" + this.spanOffset + '}';
        }
    }

    public String toString() {
        return getClass().getName().substring(getClass().getPackage().getName().length() + 1) + "{header=" + this.header + ",\nseparator=" + this.separator + ",\nbody=" + this.body + ",\ncaption=" + this.caption + ",\noptions=" + this.options + ",\ntrackedOffsets=" + this.trackedOffsets + "}";
    }
}
