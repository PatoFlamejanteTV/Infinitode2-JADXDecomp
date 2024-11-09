package com.vladsch.flexmark.ext.gitlab;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphContainer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/GitLabBlockQuote.class */
public class GitLabBlockQuote extends Block implements ParagraphContainer {
    private BasedSequence openingMarker;
    private BasedSequence openingTrailing;
    private BasedSequence closingMarker;
    private BasedSequence closingTrailing;

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.openingTrailing, "openTrail");
        segmentSpanChars(sb, this.closingMarker, "close");
        segmentSpanChars(sb, this.closingTrailing, "closeTrail");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.openingTrailing, this.closingMarker, this.closingTrailing};
    }

    @Override // com.vladsch.flexmark.ast.ParagraphContainer
    public boolean isParagraphEndWrappingDisabled(Paragraph paragraph) {
        return paragraph == getLastChild() || (paragraph.getNext() instanceof GitLabBlockQuote);
    }

    @Override // com.vladsch.flexmark.ast.ParagraphContainer
    public boolean isParagraphStartWrappingDisabled(Paragraph paragraph) {
        return paragraph == getFirstChild() || (paragraph.getPrevious() instanceof GitLabBlockQuote);
    }

    public GitLabBlockQuote() {
        this.openingMarker = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
    }

    public GitLabBlockQuote(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
    }

    public GitLabBlockQuote(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
    }

    public GitLabBlockQuote(BlockContent blockContent) {
        super(blockContent);
        this.openingMarker = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public BasedSequence getOpeningTrailing() {
        return this.openingTrailing;
    }

    public void setOpeningTrailing(BasedSequence basedSequence) {
        this.openingTrailing = basedSequence;
    }

    public BasedSequence getClosingTrailing() {
        return this.closingTrailing;
    }

    public void setClosingTrailing(BasedSequence basedSequence) {
        this.closingTrailing = basedSequence;
    }
}
