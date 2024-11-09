package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocBlock.class */
public class SimTocBlock extends TocBlockBase {
    protected BasedSequence anchorMarker;
    protected BasedSequence openingTitleMarker;
    protected BasedSequence title;
    protected BasedSequence closingTitleMarker;

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        super.getAstExtra(sb);
        segmentSpanChars(sb, this.anchorMarker, "anchorMarker");
        segmentSpanChars(sb, this.openingTitleMarker, "openingTitleMarker");
        segmentSpanChars(sb, this.title, Attribute.TITLE_ATTR);
        segmentSpanChars(sb, this.closingTitleMarker, "closingTitleMarker");
    }

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        BasedSequence[] basedSequenceArr = {this.openingMarker, this.tocKeyword, this.style, this.closingMarker, this.anchorMarker, this.openingTitleMarker, this.title, this.closingTitleMarker};
        if (this.lineSegments.size() == 0) {
            return basedSequenceArr;
        }
        BasedSequence[] basedSequenceArr2 = new BasedSequence[this.lineSegments.size() + 8];
        this.lineSegments.toArray(basedSequenceArr2);
        System.arraycopy(basedSequenceArr2, 0, basedSequenceArr2, 8, this.lineSegments.size());
        return basedSequenceArr2;
    }

    public SimTocBlock(BasedSequence basedSequence) {
        this(basedSequence, null, null);
    }

    public SimTocBlock(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence, basedSequence2, true);
        this.anchorMarker = BasedSequence.NULL;
        this.openingTitleMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.closingTitleMarker = BasedSequence.NULL;
        int indexOf = basedSequence.indexOf('#', this.closingMarker.getEndOffset() - basedSequence.getStartOffset());
        if (indexOf == -1) {
            throw new IllegalStateException("Invalid TOC block sequence");
        }
        this.anchorMarker = basedSequence.subSequence(indexOf, indexOf + 1);
        if (basedSequence3 != null) {
            if (basedSequence3.length() < 2) {
                throw new IllegalStateException("Invalid TOC block title sequence");
            }
            this.openingTitleMarker = basedSequence3.subSequence(0, 1);
            this.title = basedSequence3.midSequence(1, -1);
            this.closingTitleMarker = basedSequence3.endSequence(1);
        }
    }

    public BasedSequence getAnchorMarker() {
        return this.anchorMarker;
    }

    public BasedSequence getOpeningTitleMarker() {
        return this.openingTitleMarker;
    }

    public BasedSequence getTitle() {
        return this.title;
    }

    public BasedSequence getClosingTitleMarker() {
        return this.closingTitleMarker;
    }
}
