package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableCell.class */
public class TableCell {
    public static final TableCell NULL = new TableCell(null, BasedSequence.NULL, SequenceUtils.SPACE, BasedSequence.NULL, 1, 0, CellAlignment.NONE);
    public static final TableCell DEFAULT_CELL = new TableCell(null, BasedSequence.NULL, SequenceUtils.SPACE, BasedSequence.NULL, 1, 1, CellAlignment.NONE);
    public static final int NOT_TRACKED = Integer.MAX_VALUE;
    public final Node tableCellNode;
    public final BasedSequence openMarker;
    public final BasedSequence text;
    public final BasedSequence closeMarker;
    public final int columnSpan;
    public final int rowSpan;
    public final CellAlignment alignment;
    public final int trackedTextOffset;
    public final int spanTrackedOffset;
    public final int trackedTextAdjust;
    public final boolean afterSpace;
    public final boolean afterDelete;

    public TableCell(CharSequence charSequence, int i, int i2) {
        this(null, BasedSequence.NULL, charSequence, BasedSequence.NULL, i, i2, CellAlignment.NONE);
    }

    public TableCell(Node node, CharSequence charSequence, int i, int i2, CellAlignment cellAlignment) {
        this(node, BasedSequence.NULL, charSequence, BasedSequence.NULL, i, i2, cellAlignment);
    }

    public TableCell(Node node, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2) {
        this(node, charSequence, charSequence2, charSequence3, i, i2, CellAlignment.NONE);
    }

    public TableCell(Node node, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2, CellAlignment cellAlignment) {
        this(node, charSequence, charSequence2, charSequence3, i, i2, cellAlignment, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, false, false);
    }

    public TableCell(Node node, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2, CellAlignment cellAlignment, int i3, int i4, int i5, boolean z, boolean z2) {
        BasedSequence of = BasedSequence.of(charSequence2);
        this.tableCellNode = node;
        this.openMarker = BasedSequence.of(charSequence);
        this.closeMarker = BasedSequence.of(charSequence3);
        this.text = (!of.isEmpty() || of == BasedSequence.NULL) ? of : PrefixedSubSequence.prefixOf(SequenceUtils.SPACE, this.openMarker.isEmpty() ? this.closeMarker.subSequence(0, 0) : this.openMarker.subSequence(this.openMarker.length()));
        this.rowSpan = i;
        this.columnSpan = i2;
        this.alignment = cellAlignment != null ? cellAlignment : CellAlignment.NONE;
        this.trackedTextOffset = i3;
        this.spanTrackedOffset = i4;
        this.trackedTextAdjust = i5;
        this.afterSpace = z;
        this.afterDelete = z2;
    }

    public TableCell(TableCell tableCell, boolean z, int i, int i2, CellAlignment cellAlignment) {
        this.tableCellNode = z ? tableCell.tableCellNode : null;
        this.openMarker = tableCell.openMarker;
        this.closeMarker = tableCell.closeMarker;
        this.text = tableCell.text == BasedSequence.NULL ? PrefixedSubSequence.prefixOf(SequenceUtils.SPACE, this.openMarker.isEmpty() ? this.closeMarker.subSequence(0, 0) : this.openMarker.subSequence(this.openMarker.length())) : tableCell.text;
        this.rowSpan = i;
        this.columnSpan = i2;
        this.alignment = cellAlignment != null ? cellAlignment : CellAlignment.NONE;
        this.trackedTextOffset = tableCell.trackedTextOffset;
        this.spanTrackedOffset = tableCell.spanTrackedOffset;
        this.trackedTextAdjust = tableCell.trackedTextAdjust;
        this.afterSpace = tableCell.afterSpace;
        this.afterDelete = tableCell.afterDelete;
    }

    public TableCell withColumnSpan(int i) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, i, this.alignment, this.trackedTextOffset, this.spanTrackedOffset == Integer.MAX_VALUE ? Integer.MAX_VALUE : Utils.min(this.spanTrackedOffset, i), this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withText(CharSequence charSequence) {
        return new TableCell(this.tableCellNode, this.openMarker, charSequence, this.closeMarker, this.rowSpan, this.columnSpan, this.alignment, Integer.MAX_VALUE, this.spanTrackedOffset, this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withText(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new TableCell(this.tableCellNode, charSequence, charSequence2, charSequence3, this.rowSpan, this.columnSpan, this.alignment, Integer.MAX_VALUE, this.spanTrackedOffset, this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withRowSpan(int i) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, i, this.columnSpan, this.alignment, this.trackedTextOffset, this.spanTrackedOffset, this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withAlignment(CellAlignment cellAlignment) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, this.columnSpan, cellAlignment, this.trackedTextOffset, this.spanTrackedOffset, this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withTrackedOffset(int i) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, this.columnSpan, this.alignment, i, this.spanTrackedOffset, this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withTrackedOffset(int i, boolean z, boolean z2) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, this.columnSpan, this.alignment, i, this.spanTrackedOffset, this.trackedTextAdjust, z, z2);
    }

    public TableCell withSpanTrackedOffset(int i) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, this.columnSpan, this.alignment, this.trackedTextOffset, i, this.trackedTextAdjust, this.afterSpace, this.afterDelete);
    }

    public TableCell withTrackedTextAdjust(int i) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, this.columnSpan, this.alignment, this.trackedTextOffset, this.spanTrackedOffset, i, this.afterSpace, this.afterDelete);
    }

    public TableCell withAfterSpace(boolean z) {
        return new TableCell(this.tableCellNode, this.openMarker, this.text, this.closeMarker, this.rowSpan, this.columnSpan, this.alignment, this.trackedTextOffset, this.spanTrackedOffset, this.trackedTextAdjust, z, this.afterDelete);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasedSequence getLastSegment() {
        return !this.closeMarker.isEmpty() ? this.closeMarker : this.text;
    }

    public int getEndOffset() {
        return !this.closeMarker.isEmpty() ? this.closeMarker.getEndOffset() : this.text.getEndOffset();
    }

    public int getStartOffset(TableCell tableCell) {
        return tableCell != null ? tableCell.getEndOffset() : !this.openMarker.isEmpty() ? this.openMarker.getStartOffset() : this.text.getStartOffset();
    }

    public int getInsideStartOffset(TableCell tableCell) {
        return tableCell != null ? tableCell.getEndOffset() : !this.openMarker.isEmpty() ? this.openMarker.getEndOffset() : this.text.getStartOffset();
    }

    public int getTextStartOffset(TableCell tableCell) {
        return !this.text.isEmpty() ? this.text.getStartOffset() : !this.openMarker.isEmpty() ? this.openMarker.getEndOffset() + 1 : tableCell != null ? tableCell.getEndOffset() + 1 : this.closeMarker.getStartOffset() - 1;
    }

    public int getTextEndOffset(TableCell tableCell) {
        return !this.text.isEmpty() ? this.text.getEndOffset() : !this.openMarker.isEmpty() ? this.openMarker.getEndOffset() + 1 : tableCell != null ? tableCell.getEndOffset() + 1 : this.closeMarker.getStartOffset() - 1;
    }

    public int getInsideEndOffset() {
        return !this.closeMarker.isEmpty() ? this.closeMarker.getStartOffset() : this.text.getEndOffset();
    }

    public int getCellSize(TableCell tableCell) {
        return getEndOffset() - getStartOffset(tableCell);
    }

    public int insideToTextOffset(int i, TableCell tableCell) {
        return Utils.maxLimit(this.text.length(), Utils.minLimit((i - getInsideStartOffset(tableCell)) + getTextStartOffset(tableCell), 0));
    }

    public int textToInsideOffset(int i, TableCell tableCell) {
        return Utils.maxLimit(getCellSize(tableCell), Utils.minLimit((i - getTextStartOffset(tableCell)) + getInsideStartOffset(tableCell), 0));
    }

    public boolean isInsideCell(int i, TableCell tableCell) {
        return i >= getInsideStartOffset(tableCell) && i <= getInsideEndOffset();
    }

    public boolean isAtCell(int i, TableCell tableCell) {
        return i >= getInsideStartOffset(tableCell) && i <= getInsideEndOffset();
    }

    public int getCellLength(TableCell tableCell) {
        return getEndOffset() - getStartOffset(tableCell);
    }

    public int getCellPrefixLength(TableCell tableCell) {
        return getInsideStartOffset(tableCell) - getStartOffset(tableCell);
    }

    private CharSequence dumpSequence(BasedSequence basedSequence) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"").append((CharSequence) basedSequence.replace("\"", "\\\"")).append("\" [").append(basedSequence.getStartOffset()).append(", ").append(basedSequence.getEndOffset()).append("), length=").append(basedSequence.length()).append("}");
        return sb;
    }

    public String toString() {
        return getClass().getName().substring(getClass().getPackage().getName().length() + 1) + "{openMarker=" + ((Object) dumpSequence(this.openMarker)) + ", text=" + ((Object) dumpSequence(this.text)) + ", closeMarker=" + ((Object) dumpSequence(this.closeMarker)) + ", columnSpan=" + this.columnSpan + ", rowSpan=" + this.rowSpan + ", alignment=" + this.alignment + ", trackedTextOffset=" + this.trackedTextOffset + ", spanTrackedOffset=" + this.spanTrackedOffset + ", trackedTextAdjust=" + this.trackedTextAdjust + ", afterSpace=" + this.afterSpace + ", afterDelete=" + this.afterDelete + '}';
    }
}
