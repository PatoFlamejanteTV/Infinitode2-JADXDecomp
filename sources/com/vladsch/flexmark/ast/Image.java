package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/Image.class */
public class Image extends InlineLinkNode {
    private BasedSequence urlContent;

    @Override // com.vladsch.flexmark.ast.InlineLinkNode, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.textOpeningMarker, this.text, this.textClosingMarker, this.linkOpeningMarker, this.urlOpeningMarker, this.url, this.pageRef, this.anchorMarker, this.anchorRef, this.urlClosingMarker, this.urlContent, this.titleOpeningMarker, this.titleOpeningMarker, this.title, this.titleClosingMarker, this.linkClosingMarker};
    }

    @Override // com.vladsch.flexmark.ast.InlineLinkNode, com.vladsch.flexmark.util.ast.Node
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
        if (this.urlContent.isNotNull()) {
            segmentSpanChars(sb, this.urlContent, "urlContent");
        }
        delimitedSegmentSpanChars(sb, this.titleOpeningMarker, this.title, this.titleClosingMarker, Attribute.TITLE_ATTR);
        segmentSpanChars(sb, this.linkClosingMarker, "linkClose");
    }

    public Image() {
        this.urlContent = BasedSequence.NULL;
    }

    public Image(BasedSequence basedSequence) {
        super(basedSequence);
        this.urlContent = BasedSequence.NULL;
    }

    public Image(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7, BasedSequence basedSequence8, BasedSequence basedSequence9) {
        super(basedSequence, basedSequence2, basedSequence3, basedSequence4, basedSequence5, basedSequence6, basedSequence7, basedSequence8, basedSequence9);
        this.urlContent = BasedSequence.NULL;
    }

    public Image(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7, BasedSequence basedSequence8, BasedSequence basedSequence9, BasedSequence basedSequence10) {
        super(basedSequence, basedSequence2, basedSequence3, basedSequence4, basedSequence5, basedSequence6, basedSequence7, basedSequence8, basedSequence9, basedSequence10);
        this.urlContent = BasedSequence.NULL;
    }

    public Image(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6) {
        super(basedSequence, basedSequence2, basedSequence3, basedSequence4, basedSequence5, basedSequence6);
        this.urlContent = BasedSequence.NULL;
    }

    public Image(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7) {
        super(basedSequence, basedSequence2, basedSequence3, basedSequence4, basedSequence5, basedSequence6, basedSequence7);
        this.urlContent = BasedSequence.NULL;
    }

    @Override // com.vladsch.flexmark.ast.InlineLinkNode
    public void setTextChars(BasedSequence basedSequence) {
        int length = basedSequence.length();
        this.textOpeningMarker = basedSequence.subSequence(0, 2);
        this.text = basedSequence.subSequence(2, length - 1).trim();
        this.textClosingMarker = basedSequence.subSequence(length - 1, length);
    }

    public void setUrlContent(BasedSequence basedSequence) {
        this.urlContent = basedSequence;
    }

    public BasedSequence getUrlContent() {
        return this.urlContent;
    }
}
