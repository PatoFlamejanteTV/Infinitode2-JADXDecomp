package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/OrderedList.class */
public class OrderedList extends ListBlock {
    private int startNumber;
    private char delimiter;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public OrderedList() {
    }

    public OrderedList(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public OrderedList(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public OrderedList(BlockContent blockContent) {
        super(blockContent);
    }

    @Override // com.vladsch.flexmark.ast.ListBlock, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        super.getAstExtra(sb);
        if (this.startNumber > 1) {
            sb.append(" start:").append(this.startNumber);
        }
        sb.append(" delimiter:'").append(this.delimiter).append("'");
    }

    public int getStartNumber() {
        return this.startNumber;
    }

    public void setStartNumber(int i) {
        this.startNumber = i;
    }

    public char getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(char c) {
        this.delimiter = c;
    }
}
