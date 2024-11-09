package com.vladsch.flexmark.ext.gfm.issues;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/GfmIssue.class */
public class GfmIssue extends Node implements DoNotDecorate {
    protected BasedSequence openingMarker;
    protected BasedSequence text;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, BasedSequence.NULL, "text");
    }

    public GfmIssue() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
    }

    public GfmIssue(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
    }

    public GfmIssue(BasedSequence basedSequence, BasedSequence basedSequence2) {
        super(spanningChars(basedSequence, basedSequence2));
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.text = basedSequence2;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getText() {
        return this.text;
    }

    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence;
    }
}
