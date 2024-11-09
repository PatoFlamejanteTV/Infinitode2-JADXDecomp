package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.format.MarkdownTable;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableRow.class */
public class TableRow {
    protected int beforeOffset = Integer.MAX_VALUE;
    protected int afterOffset = Integer.MAX_VALUE;
    private boolean normalized = true;
    protected final List<TableCell> cells = new ArrayList();

    public List<TableCell> getCells() {
        return this.cells;
    }

    public void forAllCells(TableCellConsumer tableCellConsumer) {
        forAllCells(0, Integer.MAX_VALUE, tableCellConsumer);
    }

    public void forAllCells(int i, TableCellConsumer tableCellConsumer) {
        forAllCells(i, Integer.MAX_VALUE, tableCellConsumer);
    }

    public void forAllCells(int i, int i2, TableCellConsumer tableCellConsumer) {
        forAllCells(i, i2, (tableCell, i3, i4, i5) -> {
            tableCellConsumer.accept(tableCell, i3, i4);
            return 0;
        });
    }

    public void forAllCells(TableCellManipulator tableCellManipulator) {
        forAllCells(0, Integer.MAX_VALUE, tableCellManipulator);
    }

    public void forAllCells(int i, TableCellManipulator tableCellManipulator) {
        forAllCells(i, Integer.MAX_VALUE, tableCellManipulator);
    }

    public void forAllCells(int i, int i2, TableCellManipulator tableCellManipulator) {
        int size = this.cells.size();
        if (i < size && i2 > 0) {
            int i3 = 0;
            int i4 = i2;
            int i5 = 0;
            int i6 = 0;
            while (i6 < size) {
                TableCell tableCell = this.cells.get(i6);
                if (i6 >= i) {
                    int apply = tableCellManipulator.apply(tableCell, i6, i3, i5);
                    if (apply == Integer.MIN_VALUE) {
                        return;
                    }
                    if (apply < 0) {
                        i5 -= apply;
                        i4 += apply;
                        size += apply;
                    } else {
                        i6 += apply + 1;
                        i3 += tableCell.columnSpan;
                        i4--;
                        size += apply;
                    }
                    i5++;
                    if (i4 <= 0) {
                        return;
                    }
                } else {
                    i6++;
                    i5++;
                    i3 += tableCell.columnSpan;
                }
            }
        }
    }

    public int getColumns() {
        return this.cells.size();
    }

    public int getTotalColumns() {
        return getSpannedColumns();
    }

    public int getSpannedColumns() {
        int i = 0;
        for (TableCell tableCell : this.cells) {
            if (tableCell != null) {
                i += tableCell.columnSpan;
            }
        }
        return i;
    }

    public int getBeforeOffset() {
        return this.beforeOffset;
    }

    public void setBeforeOffset(int i) {
        this.beforeOffset = i;
    }

    public int getAfterOffset() {
        return this.afterOffset;
    }

    public void setAfterOffset(int i) {
        this.afterOffset = i;
    }

    public int columnOf(int i) {
        return columnOfOrNull(Integer.valueOf(i)).intValue();
    }

    public Integer columnOfOrNull(Integer num) {
        if (num == null) {
            return null;
        }
        int i = 0;
        int maxLimit = Utils.maxLimit(num.intValue(), this.cells.size());
        for (int i2 = 0; i2 < maxLimit; i2++) {
            i += this.cells.get(i2).columnSpan;
        }
        return Integer.valueOf(i);
    }

    public void appendColumns(int i) {
        appendColumns(i, null);
    }

    public void appendColumns(int i, TableCell tableCell) {
        if (tableCell == null || tableCell.columnSpan == 0) {
            tableCell = defaultCell();
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.cells.add(this.cells.size(), tableCell);
        }
    }

    public TableCell defaultCell() {
        return new TableCell(SequenceUtils.SPACE, 1, 1);
    }

    public void addColumn(int i) {
        this.cells.add(i, defaultCell());
    }

    public void insertColumns(int i, int i2) {
        insertColumns(i, i2, null);
    }

    public void insertColumns(int i, int i2, TableCell tableCell) {
        if (i2 <= 0 || i < 0) {
            return;
        }
        normalizeIfNeeded();
        if (tableCell == null || tableCell.columnSpan == 0) {
            tableCell = defaultCell();
        }
        if (i >= getTotalColumns()) {
            appendColumns(i2, tableCell);
            return;
        }
        MarkdownTable.IndexSpanOffset indexOf = indexOf(i);
        int i3 = indexOf.index;
        int i4 = indexOf.spanOffset;
        if (i4 > 0 && i3 < this.cells.size()) {
            TableCell tableCell2 = this.cells.get(i3);
            if (tableCell.text.isBlank() || i2 > 1) {
                this.cells.remove(i3);
                this.cells.add(i3, tableCell2.withColumnSpan(tableCell2.columnSpan + i2));
                return;
            } else {
                this.cells.remove(i3);
                this.cells.add(i3, tableCell2.withColumnSpan(i4));
                this.cells.add(i3 + 1, tableCell.withColumnSpan(Utils.minLimit(1, (tableCell2.columnSpan - i4) + 1)));
                return;
            }
        }
        for (int i5 = 0; i5 < i2; i5++) {
            this.cells.add(i3, tableCell);
        }
    }

    public void deleteColumns(int i, int i2) {
        if (i2 <= 0 || i < 0) {
            return;
        }
        normalizeIfNeeded();
        int i3 = i2;
        MarkdownTable.IndexSpanOffset indexOf = indexOf(i);
        int i4 = indexOf.index;
        int i5 = indexOf.spanOffset;
        while (true) {
            int i6 = i5;
            if (i4 < this.cells.size() && i3 > 0) {
                TableCell tableCell = this.cells.get(i4);
                this.cells.remove(i4);
                if (i6 > 0) {
                    if (tableCell.columnSpan - i6 > i3) {
                        this.cells.add(i4, tableCell.withColumnSpan(tableCell.columnSpan - i3));
                        return;
                    } else {
                        this.cells.add(i4, tableCell.withColumnSpan(i6));
                        i4++;
                    }
                } else if (tableCell.columnSpan - i6 > i3) {
                    this.cells.add(i4, defaultCell().withColumnSpan(tableCell.columnSpan - i3));
                    return;
                }
                i3 -= tableCell.columnSpan - i6;
                i5 = 0;
            } else {
                return;
            }
        }
    }

    public void moveColumn(int i, int i2) {
        if (i < 0 || i2 < 0) {
            return;
        }
        normalizeIfNeeded();
        int totalColumns = getTotalColumns();
        if (i >= totalColumns) {
            return;
        }
        if (i2 >= totalColumns) {
            i2 = totalColumns - 1;
        }
        if (i != i2 && i2 < totalColumns) {
            MarkdownTable.IndexSpanOffset indexOf = indexOf(i);
            int i3 = indexOf.index;
            int i4 = indexOf.spanOffset;
            TableCell withColumnSpan = this.cells.get(i3).withColumnSpan(1);
            if (indexOf(i2).index != i3) {
                if (i4 > 0) {
                    insertColumns(i2 + (i <= i2 ? 1 : 0), 1, defaultCell());
                } else {
                    insertColumns(i2 + (i <= i2 ? 1 : 0), 1, withColumnSpan.withColumnSpan(1));
                }
                deleteColumns(i + (i2 <= i ? 1 : 0), 1);
            }
        }
    }

    public TableRow expandTo(int i) {
        return expandTo(i, TableCell.NULL);
    }

    public TableRow expandTo(int i, TableCell tableCell) {
        if (tableCell == null || tableCell.columnSpan == 0) {
            this.normalized = false;
        }
        while (i >= this.cells.size()) {
            this.cells.add(tableCell);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fillMissingColumns(Integer num, int i) {
        int spannedColumns = getSpannedColumns();
        if (spannedColumns < i) {
            int size = num == null ? this.cells.size() : num.intValue();
            int i2 = i - spannedColumns;
            if (num == null || num.intValue() >= spannedColumns) {
                size = this.cells.size();
            }
            TableCell defaultCell = defaultCell();
            TableCell tableCell = size > 0 ? this.cells.get(size - 1) : defaultCell;
            while (true) {
                int i3 = i2;
                i2--;
                if (i3 > 0) {
                    int endOffset = tableCell.getEndOffset();
                    defaultCell = defaultCell.withText(PrefixedSubSequence.prefixOf(SequenceUtils.SPACE, tableCell.getLastSegment().getBaseSequence(), endOffset, endOffset));
                    List<TableCell> list = this.cells;
                    list.add(Math.min(size, list.size()), defaultCell);
                    tableCell = defaultCell;
                    size++;
                } else {
                    return;
                }
            }
        }
    }

    public void set(int i, TableCell tableCell) {
        expandTo(i, null);
        this.cells.set(i, tableCell);
    }

    public boolean isEmptyColumn(int i) {
        int i2 = indexOf(i).index;
        return i2 >= this.cells.size() || this.cells.get(i2).text.isBlank();
    }

    public boolean isEmpty() {
        for (TableCell tableCell : this.cells) {
            if (tableCell != null && !tableCell.text.isBlank()) {
                return false;
            }
        }
        return true;
    }

    public MarkdownTable.IndexSpanOffset indexOf(int i) {
        return indexOfOrNull(Integer.valueOf(i));
    }

    public MarkdownTable.IndexSpanOffset indexOfOrNull(Integer num) {
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
        int i = 0;
        for (TableCell tableCell : this.cells) {
            if (tableCell.columnSpan <= intValue) {
                intValue -= tableCell.columnSpan;
                if (tableCell.columnSpan > 0) {
                    i++;
                }
            } else {
                return new MarkdownTable.IndexSpanOffset(i, intValue);
            }
        }
        return new MarkdownTable.IndexSpanOffset(i, 0);
    }

    public void normalizeIfNeeded() {
        if (!this.normalized) {
            normalize();
        }
    }

    public void normalize() {
        int i = 0;
        while (i < this.cells.size()) {
            TableCell tableCell = this.cells.get(i);
            if (tableCell == null || tableCell == TableCell.NULL) {
                this.cells.remove(i);
            } else {
                i++;
            }
        }
        this.normalized = true;
    }

    private CharSequence dumpCells() {
        StringBuilder sb = new StringBuilder();
        Iterator<TableCell> it = this.cells.iterator();
        while (it.hasNext()) {
            sb.append("    ").append(it.next().toString()).append(SequenceUtils.EOL);
        }
        return sb;
    }

    public String toString() {
        return getClass().getName().substring(getClass().getPackage().getName().length() + 1) + "{ beforeOffset=" + this.beforeOffset + ", afterOffset=" + this.afterOffset + ", normalized=" + this.normalized + ", cells=[\n" + ((Object) dumpCells()) + "    ]\n  }";
    }
}
