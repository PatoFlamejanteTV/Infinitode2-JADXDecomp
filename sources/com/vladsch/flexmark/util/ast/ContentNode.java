package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/ContentNode.class */
public abstract class ContentNode extends Node implements Content {
    protected List<BasedSequence> lineSegments;

    public ContentNode() {
        this.lineSegments = BasedSequence.EMPTY_LIST;
    }

    public ContentNode(BasedSequence basedSequence) {
        super(basedSequence);
        this.lineSegments = BasedSequence.EMPTY_LIST;
    }

    public ContentNode(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence);
        this.lineSegments = BasedSequence.EMPTY_LIST;
        this.lineSegments = list;
    }

    public ContentNode(List<BasedSequence> list) {
        this(getSpanningChars(list), list);
    }

    public ContentNode(BlockContent blockContent) {
        this(blockContent.getSpanningChars(), blockContent.getLines());
    }

    public void setContent(BasedSequence basedSequence, List<BasedSequence> list) {
        setChars(basedSequence);
        this.lineSegments = list;
    }

    public void setContent(List<BasedSequence> list) {
        this.lineSegments = list;
        setChars(getSpanningChars());
    }

    public void setContent(BlockContent blockContent) {
        setChars(blockContent.getSpanningChars());
        this.lineSegments = blockContent.getLines();
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public BasedSequence getSpanningChars() {
        return getSpanningChars(this.lineSegments);
    }

    private static BasedSequence getSpanningChars(List<BasedSequence> list) {
        return list.isEmpty() ? BasedSequence.NULL : list.get(0).baseSubSequence(list.get(0).getStartOffset(), list.get(list.size() - 1).getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public int getLineCount() {
        return this.lineSegments.size();
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public BasedSequence getLineChars(int i) {
        return this.lineSegments.get(i);
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public List<BasedSequence> getContentLines() {
        return this.lineSegments;
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public List<BasedSequence> getContentLines(int i, int i2) {
        return this.lineSegments.subList(i, i2);
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public BasedSequence getContentChars() {
        return this.lineSegments.isEmpty() ? BasedSequence.NULL : SegmentedSequence.create(this.lineSegments.get(0), this.lineSegments);
    }

    @Override // com.vladsch.flexmark.util.ast.Content
    public BasedSequence getContentChars(int i, int i2) {
        return this.lineSegments.isEmpty() ? BasedSequence.NULL : SegmentedSequence.create(this.lineSegments.get(0), getContentLines(i, i2));
    }

    public void setContentLines(List<BasedSequence> list) {
        this.lineSegments = list;
    }

    public void setContentLine(int i, BasedSequence basedSequence) {
        ArrayList arrayList = new ArrayList(this.lineSegments);
        arrayList.set(i, basedSequence);
        this.lineSegments = arrayList;
        setCharsFromContent();
    }
}
