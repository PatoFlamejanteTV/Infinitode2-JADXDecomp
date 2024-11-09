package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/Paragraph.class */
public class Paragraph extends Block implements TextContainer {
    private static final int[] EMPTY_INDENTS = new int[0];
    private int[] lineIndents;
    private boolean trailingBlankLine;
    private boolean hasTableSeparator;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        super.getAstExtra(sb);
        if (this.trailingBlankLine) {
            sb.append(" isTrailingBlankLine");
        }
    }

    public Paragraph() {
        this.lineIndents = EMPTY_INDENTS;
        this.trailingBlankLine = false;
    }

    public Paragraph(BasedSequence basedSequence) {
        super(basedSequence);
        this.lineIndents = EMPTY_INDENTS;
        this.trailingBlankLine = false;
    }

    public Paragraph(BasedSequence basedSequence, List<BasedSequence> list, List<Integer> list2) {
        super(basedSequence, list);
        this.lineIndents = EMPTY_INDENTS;
        this.trailingBlankLine = false;
        if (list.size() != list2.size()) {
            throw new IllegalArgumentException("line segments and line indents have to be of the same size");
        }
        setLineIndents(list2);
    }

    public Paragraph(BasedSequence basedSequence, List<BasedSequence> list, int[] iArr) {
        super(basedSequence, list);
        this.lineIndents = EMPTY_INDENTS;
        this.trailingBlankLine = false;
        if (list.size() != iArr.length) {
            throw new IllegalArgumentException("line segments and line indents have to be of the same size");
        }
        this.lineIndents = iArr;
    }

    public Paragraph(BlockContent blockContent) {
        super(blockContent);
        this.lineIndents = EMPTY_INDENTS;
        this.trailingBlankLine = false;
        setLineIndents(blockContent.getLineIndents());
    }

    protected void setLineIndents(List<Integer> list) {
        this.lineIndents = new int[list.size()];
        int i = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int i2 = i;
            i++;
            this.lineIndents[i2] = it.next().intValue();
        }
    }

    @Override // com.vladsch.flexmark.util.ast.ContentNode
    public void setContent(BasedSequence basedSequence, List<BasedSequence> list) {
        super.setContent(basedSequence, list);
    }

    public void setContent(BasedSequence basedSequence, List<BasedSequence> list, List<Integer> list2) {
        super.setContent(basedSequence, list);
        if (list.size() != list2.size()) {
            throw new IllegalArgumentException("line segments and line indents have to be of the same size");
        }
        setLineIndents(list2);
    }

    @Override // com.vladsch.flexmark.util.ast.ContentNode
    public void setContent(List<BasedSequence> list) {
        super.setContent(list);
    }

    @Override // com.vladsch.flexmark.util.ast.ContentNode
    public void setContent(BlockContent blockContent) {
        super.setContent(blockContent);
        setLineIndents(blockContent.getLineIndents());
    }

    public void setContent(BlockContent blockContent, int i, int i2) {
        super.setContent(blockContent.getLines().subList(i, i2));
        setLineIndents(blockContent.getLineIndents().subList(i, i2));
    }

    public void setContent(Paragraph paragraph, int i, int i2) {
        super.setContent(paragraph.getContentLines(i, i2));
        if (i2 > i) {
            int[] iArr = new int[i2 - i];
            System.arraycopy(paragraph.lineIndents, i, iArr, 0, i2 - i);
            this.lineIndents = iArr;
            return;
        }
        this.lineIndents = EMPTY_INDENTS;
    }

    public void setLineIndents(int[] iArr) {
        this.lineIndents = iArr;
    }

    public int getLineIndent(int i) {
        return this.lineIndents[i];
    }

    public int[] getLineIndents() {
        return this.lineIndents;
    }

    public boolean isTrailingBlankLine() {
        return this.trailingBlankLine;
    }

    public void setTrailingBlankLine(boolean z) {
        this.trailingBlankLine = z;
    }

    public void setHasTableSeparator(boolean z) {
        this.hasTableSeparator = z;
    }

    public boolean hasTableSeparator() {
        return this.hasTableSeparator;
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        if (!iSequenceBuilder.isEmpty()) {
            iSequenceBuilder.add("\n\n");
            return true;
        }
        return true;
    }
}
