package com.vladsch.flexmark.ext.attributes;

import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NonRenderingInline;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/AttributesNode.class */
public class AttributesNode extends Node implements DelimitedNode, DoNotDecorate, NonRenderingInline {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, this.closingMarker, "text");
    }

    public AttributesNode() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public AttributesNode(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public AttributesNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence3.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.text = basedSequence2;
        this.closingMarker = basedSequence3;
    }

    public AttributesNode(BasedSequence basedSequence, String str) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
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
}