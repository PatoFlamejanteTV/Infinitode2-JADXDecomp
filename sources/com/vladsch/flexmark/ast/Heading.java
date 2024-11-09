package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/Heading.class */
public class Heading extends Block implements AnchorRefTarget {
    protected int level;
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected String anchorRefId;
    protected boolean explicitAnchorRefId;

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, this.closingMarker, "text");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.ast.AnchorRefTarget
    public String getAnchorRefText() {
        return new TextCollectingVisitor().collectAndGetText(this, TextContainer.F_FOR_HEADING_ID + (HtmlRenderer.HEADER_ID_REF_TEXT_TRIM_LEADING_SPACES.get(getDocument()).booleanValue() ? 0 : TextContainer.F_NO_TRIM_REF_TEXT_START) + (HtmlRenderer.HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES.get(getDocument()).booleanValue() ? 0 : TextContainer.F_NO_TRIM_REF_TEXT_END));
    }

    @Override // com.vladsch.flexmark.ast.AnchorRefTarget
    public String getAnchorRefId() {
        return this.anchorRefId;
    }

    @Override // com.vladsch.flexmark.ast.AnchorRefTarget
    public void setAnchorRefId(String str) {
        this.anchorRefId = str;
    }

    @Override // com.vladsch.flexmark.ast.AnchorRefTarget
    public boolean isExplicitAnchorRefId() {
        return this.explicitAnchorRefId;
    }

    @Override // com.vladsch.flexmark.ast.AnchorRefTarget
    public void setExplicitAnchorRefId(boolean z) {
        this.explicitAnchorRefId = z;
    }

    public Heading() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.anchorRefId = "";
        this.explicitAnchorRefId = false;
    }

    public Heading(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.anchorRefId = "";
        this.explicitAnchorRefId = false;
    }

    public Heading(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.anchorRefId = "";
        this.explicitAnchorRefId = false;
    }

    public Heading(BlockContent blockContent) {
        super(blockContent);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.anchorRefId = "";
        this.explicitAnchorRefId = false;
    }

    public boolean isAtxHeading() {
        return this.openingMarker != BasedSequence.NULL;
    }

    public boolean isSetextHeading() {
        return this.openingMarker == BasedSequence.NULL && this.closingMarker != BasedSequence.NULL;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence == null ? BasedSequence.NULL : basedSequence;
    }

    public BasedSequence getText() {
        return this.text;
    }

    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence == null ? BasedSequence.NULL : basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence == null ? BasedSequence.NULL : basedSequence;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }
}
