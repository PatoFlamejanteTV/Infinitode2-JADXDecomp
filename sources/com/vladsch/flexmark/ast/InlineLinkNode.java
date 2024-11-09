package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/InlineLinkNode.class */
public abstract class InlineLinkNode extends LinkNode {
    protected BasedSequence textOpeningMarker;
    protected BasedSequence text;
    protected BasedSequence textClosingMarker;
    protected BasedSequence linkOpeningMarker;
    protected BasedSequence linkClosingMarker;

    public abstract void setTextChars(BasedSequence basedSequence);

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.textOpeningMarker, this.text, this.textClosingMarker, this.linkOpeningMarker, this.urlOpeningMarker, this.url, this.pageRef, this.anchorMarker, this.anchorRef, this.urlClosingMarker, this.titleOpeningMarker, this.title, this.titleClosingMarker, this.linkClosingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegmentsForChars() {
        return new BasedSequence[]{this.textOpeningMarker, this.text, this.textClosingMarker, this.linkOpeningMarker, this.urlOpeningMarker, this.pageRef, this.anchorMarker, this.anchorRef, this.urlClosingMarker, this.titleOpeningMarker, this.title, this.titleClosingMarker, this.linkClosingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.textOpeningMarker, this.text, this.textClosingMarker, "text");
        segmentSpanChars(sb, this.linkOpeningMarker, "linkOpen");
        delimitedSegmentSpanChars(sb, this.urlOpeningMarker, this.url, this.urlClosingMarker, "url");
        if (this.pageRef.isNotNull()) {
            segmentSpanChars(sb, this.pageRef, "pageRef");
        }
        if (this.anchorMarker.isNotNull()) {
            segmentSpanChars(sb, this.anchorMarker, "anchorMarker");
        }
        if (this.anchorRef.isNotNull()) {
            segmentSpanChars(sb, this.anchorRef, "anchorRef");
        }
        delimitedSegmentSpanChars(sb, this.titleOpeningMarker, this.title, this.titleClosingMarker, Attribute.TITLE_ATTR);
        segmentSpanChars(sb, this.linkClosingMarker, "linkClose");
    }

    public InlineLinkNode() {
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.linkOpeningMarker = BasedSequence.NULL;
        this.linkClosingMarker = BasedSequence.NULL;
    }

    public InlineLinkNode(BasedSequence basedSequence) {
        super(basedSequence);
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.linkOpeningMarker = BasedSequence.NULL;
        this.linkClosingMarker = BasedSequence.NULL;
    }

    public InlineLinkNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7, BasedSequence basedSequence8, BasedSequence basedSequence9) {
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.linkOpeningMarker = BasedSequence.NULL;
        this.linkClosingMarker = BasedSequence.NULL;
        this.textOpeningMarker = basedSequence;
        this.text = basedSequence2.trim();
        this.textClosingMarker = basedSequence3;
        this.linkOpeningMarker = basedSequence4;
        this.url = basedSequence5;
        this.titleOpeningMarker = basedSequence6;
        this.title = basedSequence7;
        this.titleClosingMarker = basedSequence8;
        this.linkClosingMarker = basedSequence9;
    }

    public InlineLinkNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7, BasedSequence basedSequence8, BasedSequence basedSequence9, BasedSequence basedSequence10) {
        super(basedSequence);
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.linkOpeningMarker = BasedSequence.NULL;
        this.linkClosingMarker = BasedSequence.NULL;
        this.textOpeningMarker = basedSequence2;
        this.text = basedSequence3.trim();
        this.textClosingMarker = basedSequence4;
        this.linkOpeningMarker = basedSequence5;
        this.url = basedSequence6;
        this.titleOpeningMarker = basedSequence7;
        this.title = basedSequence8;
        this.titleClosingMarker = basedSequence9;
        this.linkClosingMarker = basedSequence10;
    }

    public InlineLinkNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6) {
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.linkOpeningMarker = BasedSequence.NULL;
        this.linkClosingMarker = BasedSequence.NULL;
        this.textOpeningMarker = basedSequence;
        this.text = basedSequence2.trim();
        this.textClosingMarker = basedSequence3;
        this.linkOpeningMarker = basedSequence4;
        this.url = basedSequence5;
        this.linkClosingMarker = basedSequence6;
    }

    public InlineLinkNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7) {
        super(basedSequence);
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.linkOpeningMarker = BasedSequence.NULL;
        this.linkClosingMarker = BasedSequence.NULL;
        this.textOpeningMarker = basedSequence2;
        this.text = basedSequence3.trim();
        this.textClosingMarker = basedSequence4;
        this.linkOpeningMarker = basedSequence5;
        this.url = basedSequence6;
        this.linkClosingMarker = basedSequence7;
    }

    public void setUrl(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        this.linkOpeningMarker = basedSequence;
        setUrlChars(basedSequence2);
        this.linkClosingMarker = basedSequence3;
    }

    public BasedSequence getText() {
        return this.text;
    }

    public BasedSequence getTextOpeningMarker() {
        return this.textOpeningMarker;
    }

    public void setTextOpeningMarker(BasedSequence basedSequence) {
        this.textOpeningMarker = basedSequence;
    }

    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence.trim();
    }

    public BasedSequence getTextClosingMarker() {
        return this.textClosingMarker;
    }

    public void setTextClosingMarker(BasedSequence basedSequence) {
        this.textClosingMarker = basedSequence;
    }

    public BasedSequence getLinkOpeningMarker() {
        return this.linkOpeningMarker;
    }

    public void setLinkOpeningMarker(BasedSequence basedSequence) {
        this.linkOpeningMarker = basedSequence;
    }

    public BasedSequence getLinkClosingMarker() {
        return this.linkClosingMarker;
    }

    public void setLinkClosingMarker(BasedSequence basedSequence) {
        this.linkClosingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected String toStringAttributes() {
        return "text=" + ((Object) this.text) + ", url=" + ((Object) this.url) + ", title=" + ((Object) this.title);
    }
}
