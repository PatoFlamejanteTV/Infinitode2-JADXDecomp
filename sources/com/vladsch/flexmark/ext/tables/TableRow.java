package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.util.ast.LineBreakNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableRow.class */
public class TableRow extends Node implements LineBreakNode {
    private int rowNumber;

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        super.getAstExtra(sb);
        if (this.rowNumber != 0) {
            sb.append(" rowNumber=").append(this.rowNumber);
        }
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public TableRow() {
    }

    public TableRow(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public void setRowNumber(int i) {
        this.rowNumber = i;
    }
}
