package com.vladsch.flexmark.ext.admonition;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphContainer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/AdmonitionBlock.class */
public class AdmonitionBlock extends Block implements ParagraphContainer {
    private BasedSequence openingMarker;
    private BasedSequence info;
    protected BasedSequence titleOpeningMarker;
    protected BasedSequence title;
    protected BasedSequence titleClosingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.info, this.titleOpeningMarker, this.title, this.titleClosingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegmentsForChars() {
        return new BasedSequence[]{this.openingMarker, this.info, this.titleOpeningMarker, this.title, this.titleClosingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.info, "info");
        delimitedSegmentSpanChars(sb, this.titleOpeningMarker, this.title, this.titleClosingMarker, Attribute.TITLE_ATTR);
    }

    public AdmonitionBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.info = BasedSequence.NULL;
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
    }

    public AdmonitionBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.info = BasedSequence.NULL;
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
    }

    public AdmonitionBlock(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, List<BasedSequence> list) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
        this.info = BasedSequence.NULL;
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence2;
        this.info = basedSequence3;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public void setInfo(BasedSequence basedSequence) {
        this.info = basedSequence;
    }

    public BasedSequence getInfo() {
        return this.info;
    }

    public BasedSequence getTitle() {
        return this.title;
    }

    public BasedSequence getTitleOpeningMarker() {
        return this.titleOpeningMarker;
    }

    public void setTitleOpeningMarker(BasedSequence basedSequence) {
        this.titleOpeningMarker = basedSequence;
    }

    public void setTitle(BasedSequence basedSequence) {
        this.title = basedSequence;
    }

    public BasedSequence getTitleClosingMarker() {
        return this.titleClosingMarker;
    }

    public void setTitleClosingMarker(BasedSequence basedSequence) {
        this.titleClosingMarker = basedSequence;
    }

    public BasedSequence getTitleChars() {
        return spanningChars(this.titleOpeningMarker, this.title, this.titleClosingMarker);
    }

    public void setTitleChars(BasedSequence basedSequence) {
        if (basedSequence != null && basedSequence != BasedSequence.NULL) {
            int length = basedSequence.length();
            this.titleOpeningMarker = basedSequence.subSequence(0, 1);
            this.title = basedSequence.subSequence(1, length - 1);
            this.titleClosingMarker = basedSequence.subSequence(length - 1, length);
            return;
        }
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphContainer
    public boolean isParagraphEndWrappingDisabled(Paragraph paragraph) {
        return false;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphContainer
    public boolean isParagraphStartWrappingDisabled(Paragraph paragraph) {
        if (paragraph == getFirstChild()) {
            return getChars().getBaseSequence().endOfLine(getChars().getStartOffset()) + 1 == paragraph.getStartOfLine();
        }
        return false;
    }
}
