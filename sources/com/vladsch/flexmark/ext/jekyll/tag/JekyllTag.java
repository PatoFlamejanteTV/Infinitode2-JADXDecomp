package com.vladsch.flexmark.ext.jekyll.tag;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/JekyllTag.class */
public class JekyllTag extends Block {
    protected BasedSequence openingMarker;
    protected BasedSequence tag;
    protected BasedSequence parameters;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.tag, this.parameters, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.tag, "tag");
        segmentSpanChars(sb, this.parameters, "parameters");
        segmentSpanChars(sb, this.closingMarker, "close");
    }

    public JekyllTag() {
        this.openingMarker = BasedSequence.NULL;
        this.tag = BasedSequence.NULL;
        this.parameters = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public JekyllTag(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.tag = BasedSequence.NULL;
        this.parameters = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public JekyllTag(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence4.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.tag = BasedSequence.NULL;
        this.parameters = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.tag = basedSequence2;
        this.parameters = basedSequence3;
        this.closingMarker = basedSequence4;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getTag() {
        return this.tag;
    }

    public void setTag(BasedSequence basedSequence) {
        this.tag = basedSequence;
    }

    public BasedSequence getParameters() {
        return this.parameters;
    }

    public void setParameters(BasedSequence basedSequence) {
        this.parameters = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }
}
