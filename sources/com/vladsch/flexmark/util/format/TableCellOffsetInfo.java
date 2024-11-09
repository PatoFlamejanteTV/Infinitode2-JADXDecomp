package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.collection.BoundedMaxAggregator;
import com.vladsch.flexmark.util.collection.BoundedMinAggregator;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableCellOffsetInfo.class */
public class TableCellOffsetInfo {
    public static final int ROW_START = 1;
    public static final int TEXT_START = 2;
    public static final int TEXT_END = 4;
    public static final int ROW_END = 8;
    private static final HashMap<TableSectionType, Integer> DEFAULT_STOP_POINTS_MAP;
    public final MarkdownTable table;
    public final int offset;
    public final TableSection section;
    public final TableRow tableRow;
    public final TableCell tableCell;
    public final int row;
    public final int column;
    public final Integer insideColumn;
    public final Integer insideOffset;

    static {
        HashMap<TableSectionType, Integer> hashMap = new HashMap<>();
        DEFAULT_STOP_POINTS_MAP = hashMap;
        hashMap.put(TableSectionType.HEADER, 4);
        DEFAULT_STOP_POINTS_MAP.put(TableSectionType.SEPARATOR, 6);
        DEFAULT_STOP_POINTS_MAP.put(TableSectionType.BODY, 4);
        DEFAULT_STOP_POINTS_MAP.put(TableSectionType.CAPTION, 4);
    }

    public TableCellOffsetInfo(int i, MarkdownTable markdownTable, TableSection tableSection, TableRow tableRow, TableCell tableCell, int i2, int i3, Integer num, Integer num2) {
        this.offset = i;
        this.table = markdownTable;
        this.section = tableSection;
        this.tableRow = tableRow;
        this.tableCell = tableCell;
        this.row = i2;
        this.column = i3;
        this.insideColumn = num;
        this.insideOffset = num2;
    }

    public boolean isCaptionLine() {
        return (this.tableRow instanceof TableCaptionRow) && this.section == this.table.caption;
    }

    public boolean isSeparatorLine() {
        return this.section.sectionType == TableSectionType.SEPARATOR;
    }

    public boolean isInsideCaption() {
        return isCaptionLine() && getInsideColumn();
    }

    public boolean isAfterCaption() {
        return isCaptionLine() && isAfterCells();
    }

    public boolean isBeforeCaption() {
        return isCaptionLine() && isBeforeCells();
    }

    public boolean isInsideCell() {
        return (this.tableRow == null || this.tableCell == null || this.insideColumn == null) ? false : true;
    }

    public boolean getInsideColumn() {
        return this.insideColumn != null;
    }

    public boolean isBeforeCells() {
        return this.tableRow != null && this.tableCell != null && this.insideColumn == null && this.column < this.tableRow.cells.size() && this.offset <= this.tableCell.getStartOffset(getPreviousCell());
    }

    public TableCell getPreviousCell() {
        return getPreviousCell(1);
    }

    public TableCell getPreviousCell(int i) {
        return getPreviousCell(this.tableRow, i);
    }

    public TableCell getPreviousCell(TableRow tableRow, int i) {
        if (this.column < i || tableRow == null) {
            return null;
        }
        return tableRow.cells.get(this.column - i);
    }

    public boolean isInCellSpan() {
        return this.tableRow != null && this.tableCell != null && this.insideColumn == null && this.offset >= this.tableCell.getStartOffset(getPreviousCell()) && this.offset < this.tableCell.getEndOffset();
    }

    public boolean isAfterCells() {
        return this.tableRow != null && this.tableCell != null && this.insideColumn == null && this.column == this.tableRow.cells.size() && this.offset >= this.tableCell.getEndOffset();
    }

    public boolean canDeleteColumn() {
        return this.insideColumn != null && this.table.getMinColumnsWithoutColumns(true, this.column) > 0;
    }

    public boolean canDeleteRow() {
        return (this.tableRow == null || this.section == this.table.separator || this.table.body.rows.size() + this.table.header.rows.size() <= 1) ? false : true;
    }

    public boolean isFirstCell() {
        return getInsideColumn() && this.column == 0;
    }

    public boolean isLastCell() {
        return getInsideColumn() && this.column + 1 == this.tableRow.cells.size();
    }

    public boolean isLastRow() {
        return this.row + 1 == this.table.getAllRowsCount();
    }

    public TableCellOffsetInfo previousCellOffset(Integer num) {
        if (getInsideColumn() && this.column > 0) {
            TableCell previousCell = getPreviousCell();
            TableCell previousCell2 = getPreviousCell(2);
            if (num == null) {
                previousCell.textToInsideOffset(this.tableCell.insideToTextOffset(this.insideOffset == null ? 0 : this.insideOffset.intValue(), previousCell2), previousCell2);
            }
            return this.table.getCellOffsetInfo(previousCell.getTextStartOffset(previousCell2) + Utils.maxLimit(previousCell.getCellSize(previousCell2), Utils.minLimit(0, num.intValue())));
        }
        return null;
    }

    public TableCellOffsetInfo nextCellOffset(Integer num) {
        if (getInsideColumn() && this.column + 1 < this.tableRow.cells.size()) {
            TableCell previousCell = getPreviousCell();
            TableCell previousCell2 = getPreviousCell(2);
            if (num == null) {
                previousCell.textToInsideOffset(this.tableCell.insideToTextOffset(this.insideOffset == null ? 0 : this.insideOffset.intValue(), previousCell2), previousCell2);
            }
            return this.table.getCellOffsetInfo(previousCell.getTextStartOffset(previousCell2) + Utils.maxLimit(previousCell.getCellSize(previousCell2), Utils.minLimit(0, num.intValue())));
        }
        return null;
    }

    public TableCellOffsetInfo previousRowOffset(Integer num) {
        if (this.row > 0) {
            TableRow tableRow = this.table.getAllRows().get(this.row - 1);
            if (getInsideColumn() && this.column < tableRow.cells.size()) {
                TableCell previousCell = getPreviousCell();
                TableCell previousCell2 = getPreviousCell(2);
                if (num == null) {
                    previousCell.textToInsideOffset(this.tableCell.insideToTextOffset(this.insideOffset == null ? 0 : this.insideOffset.intValue(), previousCell2), previousCell2);
                }
                return this.table.getCellOffsetInfo(previousCell.getTextStartOffset(previousCell2) + Utils.maxLimit(previousCell.getCellSize(previousCell2), Utils.minLimit(0, num.intValue())));
            }
            if (isBeforeCells()) {
                return this.table.getCellOffsetInfo(tableRow.cells.get(0).getStartOffset(null));
            }
            return this.table.getCellOffsetInfo(tableRow.cells.get(tableRow.cells.size() - 1).getEndOffset());
        }
        return null;
    }

    public TableCellOffsetInfo nextRowOffset(Integer num) {
        if (this.row + 1 < this.table.getAllRowsCount()) {
            TableRow tableRow = this.table.getAllRows().get(this.row + 1);
            if (getInsideColumn() && this.column < tableRow.cells.size()) {
                TableCell tableCell = tableRow.cells.get(this.column);
                TableCell previousCell = getPreviousCell(tableRow, 1);
                if (num == null) {
                    tableCell.textToInsideOffset(this.tableCell.insideToTextOffset(this.insideOffset == null ? 0 : this.insideOffset.intValue(), previousCell), previousCell);
                }
                return this.table.getCellOffsetInfo(tableCell.getTextStartOffset(previousCell) + Utils.maxLimit(tableCell.getCellSize(previousCell), Utils.minLimit(0, num.intValue())));
            }
            if (isBeforeCells()) {
                return this.table.getCellOffsetInfo(tableRow.cells.get(0).getStartOffset(null));
            }
            return this.table.getCellOffsetInfo(tableRow.cells.get(tableRow.cells.size() - 1).getEndOffset());
        }
        return null;
    }

    public TableCellOffsetInfo nextOffsetStop(Map<TableSectionType, Integer> map) {
        int stopOffset = getStopOffset(this.offset, this.table, map, true);
        if (stopOffset != -1) {
            return this.table.getCellOffsetInfo(stopOffset);
        }
        List<TableRow> allSectionRows = this.table.getAllSectionRows();
        TableRow tableRow = allSectionRows.get(allSectionRows.size() - 1);
        TableCell tableCell = tableRow.cells.get(tableRow.cells.size() - 1);
        int endOffset = tableCell.getEndOffset();
        BasedSequence baseSequence = tableCell.text.getBaseSequence();
        int endOfLineAnyEOL = baseSequence.endOfLineAnyEOL(endOffset);
        return this.table.getCellOffsetInfo(endOfLineAnyEOL == -1 ? endOffset : endOfLineAnyEOL + baseSequence.eolStartLength(endOfLineAnyEOL));
    }

    public TableCellOffsetInfo previousOffsetStop(Map<TableSectionType, Integer> map) {
        int stopOffset = getStopOffset(this.offset, this.table, map, false);
        if (stopOffset != -1) {
            return this.table.getCellOffsetInfo(stopOffset);
        }
        return this.table.getCellOffsetInfo(this.table.getTableStartOffset());
    }

    private static boolean haveStopPoint(int i, int i2) {
        return (i & i2) != 0;
    }

    private static boolean haveRowStart(int i) {
        return (i & 1) != 0;
    }

    private static boolean haveRowEnd(int i) {
        return (i & 8) != 0;
    }

    private static boolean haveTextStart(int i) {
        return (i & 2) != 0;
    }

    private static boolean haveTextEnd(int i) {
        return (i & 4) != 0;
    }

    private static int getStopOffset(int i, MarkdownTable markdownTable, Map<TableSectionType, Integer> map, boolean z) {
        Integer[] numArr = {null};
        Map<TableSectionType, Integer> map2 = map == null ? DEFAULT_STOP_POINTS_MAP : map;
        BiFunction boundedMinAggregator = z ? new BoundedMinAggregator(i) : new BoundedMaxAggregator(i);
        markdownTable.forAllSectionRows((tableRow, i2, arrayList, i3) -> {
            int intValue;
            TableSection allRowsSection = markdownTable.getAllRowsSection(i2);
            if (!tableRow.cells.isEmpty() && map2.containsKey(allRowsSection.sectionType) && (intValue = ((Integer) map2.get(allRowsSection.sectionType)).intValue()) != 0) {
                int startOffset = tableRow.cells.get(0).getStartOffset(null);
                int endOffset = tableRow.cells.get(tableRow.cells.size() - 1).getEndOffset();
                if (haveRowStart(intValue)) {
                    numArr[0] = (Integer) boundedMinAggregator.apply(numArr[0], Integer.valueOf(startOffset));
                }
                if (haveStopPoint(intValue, 6)) {
                    TableCell tableCell = null;
                    for (TableCell tableCell2 : tableRow.cells) {
                        if (haveTextStart(intValue)) {
                            numArr[0] = (Integer) boundedMinAggregator.apply(numArr[0], Integer.valueOf(tableCell2.getTextStartOffset(tableCell)));
                        }
                        if (haveTextEnd(intValue)) {
                            numArr[0] = (Integer) boundedMinAggregator.apply(numArr[0], Integer.valueOf(tableCell2.getTextEndOffset(tableCell)));
                        }
                        tableCell = tableCell2;
                    }
                }
                if (haveRowEnd(intValue)) {
                    numArr[0] = (Integer) boundedMinAggregator.apply(numArr[0], Integer.valueOf(endOffset));
                    return 0;
                }
                return 0;
            }
            return 0;
        });
        if (numArr[0] == null) {
            return -1;
        }
        return numArr[0].intValue();
    }

    public String toString() {
        return "CellOffsetInfo{ offset=" + this.offset + ", row=" + this.row + ", column=" + this.column + ", insideColumn=" + this.insideColumn + ", insideOffset=" + this.insideOffset + '}';
    }
}
