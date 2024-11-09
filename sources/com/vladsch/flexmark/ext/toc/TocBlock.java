package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/TocBlock.class */
public class TocBlock extends TocBlockBase {
    protected BasedSequence openingMarker;
    protected BasedSequence tocKeyword;
    protected BasedSequence style;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpan(sb, this.openingMarker, "openingMarker");
        segmentSpan(sb, this.tocKeyword, "tocKeyword");
        segmentSpan(sb, this.style, Attribute.STYLE_ATTR);
        segmentSpan(sb, this.closingMarker, "closingMarker");
    }

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        BasedSequence[] basedSequenceArr = {this.openingMarker, this.tocKeyword, this.style, this.closingMarker};
        if (this.lineSegments.size() == 0) {
            return basedSequenceArr;
        }
        BasedSequence[] basedSequenceArr2 = new BasedSequence[this.lineSegments.size() + 4];
        this.lineSegments.toArray(basedSequenceArr2);
        System.arraycopy(basedSequenceArr2, 0, basedSequenceArr2, 4, this.lineSegments.size());
        return basedSequenceArr2;
    }

    public TocBlock(BasedSequence basedSequence) {
        this(basedSequence, false);
    }

    public TocBlock(BasedSequence basedSequence, boolean z) {
        this(basedSequence, null, z);
    }

    public TocBlock(BasedSequence basedSequence, BasedSequence basedSequence2) {
        this(basedSequence, basedSequence2, false);
    }

    public TocBlock(BasedSequence basedSequence, BasedSequence basedSequence2, boolean z) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.tocKeyword = BasedSequence.NULL;
        this.style = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence.subSequence(0, 1);
        this.tocKeyword = basedSequence.subSequence(1, 4);
        if (basedSequence2 != null) {
            this.style = basedSequence2;
        }
        int indexOf = basedSequence.indexOf(']', 4);
        if (z && (indexOf == -1 || indexOf + 1 >= basedSequence.length() || basedSequence.charAt(indexOf + 1) != ':')) {
            throw new IllegalStateException("Invalid TOC block sequence");
        }
        this.closingMarker = basedSequence.subSequence(indexOf, indexOf + (z ? 2 : 1));
    }

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase
    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase
    public BasedSequence getTocKeyword() {
        return this.tocKeyword;
    }

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase
    public BasedSequence getStyle() {
        return this.style;
    }

    @Override // com.vladsch.flexmark.ext.toc.TocBlockBase
    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }
}
