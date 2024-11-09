package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/AutoLink.class */
public class AutoLink extends DelimitedLinkNode {
    public AutoLink() {
    }

    @Override // com.vladsch.flexmark.ast.DelimitedLinkNode, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.pageRef, this.anchorMarker, this.anchorRef, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegmentsForChars() {
        return new BasedSequence[]{this.openingMarker, this.pageRef, this.anchorMarker, this.anchorRef, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.ast.DelimitedLinkNode, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.text, "text");
        if (this.pageRef.isNotNull()) {
            segmentSpanChars(sb, this.pageRef, "pageRef");
        }
        if (this.anchorMarker.isNotNull()) {
            segmentSpanChars(sb, this.anchorMarker, "anchorMarker");
        }
        if (this.anchorRef.isNotNull()) {
            segmentSpanChars(sb, this.anchorRef, "anchorRef");
        }
        segmentSpanChars(sb, this.closingMarker, "close");
    }

    public AutoLink(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public AutoLink(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence, basedSequence2, basedSequence3);
        setUrlChars(basedSequence2);
    }
}
