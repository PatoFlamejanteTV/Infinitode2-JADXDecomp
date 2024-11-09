package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.WhiteSpace;
import com.vladsch.flexmark.ast.util.TextNodeConverter;
import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableCell.class */
public class TableCell extends Node implements DelimitedNode {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    private boolean header;
    private Alignment alignment;
    private int span;

    public void trimWhiteSpace() {
        Node firstChild = getFirstChild();
        while (true) {
            Node node = firstChild;
            if (!(node instanceof WhiteSpace)) {
                break;
            }
            Node next = node.getNext();
            node.unlink();
            firstChild = next;
        }
        Node lastChild = getLastChild();
        while (true) {
            Node node2 = lastChild;
            if (!(node2 instanceof WhiteSpace)) {
                break;
            }
            Node previous = node2.getPrevious();
            node2.unlink();
            lastChild = previous;
        }
        if (getFirstChild() == null && firstChild != null) {
            appendChild(new Text(firstChild.getChars().subSequence(0, 1)));
        }
    }

    public void mergeWhiteSpace() {
        boolean z = false;
        Node firstChild = getFirstChild();
        while (firstChild instanceof WhiteSpace) {
            Node next = firstChild.getNext();
            firstChild.insertBefore(new Text(firstChild.getChars()));
            firstChild.unlink();
            firstChild = next;
            z = true;
        }
        Node lastChild = getLastChild();
        while (lastChild instanceof WhiteSpace) {
            Node previous = lastChild.getPrevious();
            lastChild.insertBefore(new Text(lastChild.getChars()));
            lastChild.unlink();
            lastChild = previous;
            z = true;
        }
        if (z) {
            TextNodeConverter.mergeTextNodes(this);
        }
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getText() {
        return this.text;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public int getSpan() {
        return this.span;
    }

    public void setSpan(int i) {
        this.span = i;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        if (this.alignment != null) {
            sb.append(SequenceUtils.SPACE).append(this.alignment);
        }
        if (this.header) {
            sb.append(" header");
        }
        if (this.span > 1) {
            sb.append(" span=" + this.span);
        }
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, this.closingMarker, "text");
    }

    public TableCell() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.span = 1;
    }

    public TableCell(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.span = 1;
    }

    public boolean isHeader() {
        return this.header;
    }

    public void setHeader(boolean z) {
        this.header = z;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableCell$Alignment.class */
    public enum Alignment {
        LEFT,
        CENTER,
        RIGHT;

        public final CellAlignment cellAlignment() {
            switch (this) {
                case CENTER:
                    return CellAlignment.CENTER;
                case LEFT:
                    return CellAlignment.LEFT;
                case RIGHT:
                    return CellAlignment.RIGHT;
                default:
                    return CellAlignment.NONE;
            }
        }
    }
}
