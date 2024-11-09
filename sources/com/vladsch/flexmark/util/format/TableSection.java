package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableSection.class */
public class TableSection {
    public final TableSectionType sectionType;
    public final ArrayList<TableRow> rows = new ArrayList<>();
    protected int row = 0;
    protected int column = 0;

    public TableSection(TableSectionType tableSectionType) {
        this.sectionType = tableSectionType;
    }

    public ArrayList<TableRow> getRows() {
        return this.rows;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void nextRow() {
        this.row++;
        this.column = 0;
    }

    public void setCell(int i, int i2, TableCell tableCell) {
        expandTo(i).set(i2, tableCell);
    }

    public void normalize() {
        Iterator<TableRow> it = this.rows.iterator();
        while (it.hasNext()) {
            it.next().normalize();
        }
    }

    public TableRow expandTo(int i) {
        return expandTo(i, (TableCell) null);
    }

    public TableRow expandTo(int i, TableCell tableCell) {
        while (i >= this.rows.size()) {
            this.rows.add(defaultRow());
        }
        return this.rows.get(i);
    }

    public TableRow expandTo(int i, int i2) {
        return expandTo(i, i2, null);
    }

    public TableRow expandTo(int i, int i2, TableCell tableCell) {
        while (i >= this.rows.size()) {
            TableRow defaultRow = defaultRow();
            defaultRow.expandTo(i2, tableCell);
            this.rows.add(defaultRow);
        }
        return this.rows.get(i).expandTo(i2);
    }

    public TableRow defaultRow() {
        return new TableRow();
    }

    public TableCell defaultCell() {
        return TableCell.NULL;
    }

    public TableRow get(int i) {
        return expandTo(i, (TableCell) null);
    }

    public int getMaxColumns() {
        int i = 0;
        Iterator<TableRow> it = this.rows.iterator();
        while (it.hasNext()) {
            int spannedColumns = it.next().getSpannedColumns();
            if (i < spannedColumns) {
                i = spannedColumns;
            }
        }
        return i;
    }

    public int getMinColumns() {
        int i = 0;
        Iterator<TableRow> it = this.rows.iterator();
        while (it.hasNext()) {
            int spannedColumns = it.next().getSpannedColumns();
            if (i > spannedColumns || i == 0) {
                i = spannedColumns;
            }
        }
        return i;
    }

    private CharSequence dumpRows() {
        StringBuilder sb = new StringBuilder();
        Iterator<TableRow> it = this.rows.iterator();
        while (it.hasNext()) {
            sb.append("  ").append(it.next().toString()).append(SequenceUtils.EOL);
        }
        return sb;
    }

    public String toString() {
        return getClass().getName().substring(getClass().getPackage().getName().length() + 1) + "[sectionType=" + this.sectionType + ", rows=[\n" + ((Object) dumpRows()) + ']';
    }
}
