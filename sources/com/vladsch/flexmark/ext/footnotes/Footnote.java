package com.vladsch.flexmark.ext.footnotes;

import com.vladsch.flexmark.ast.LinkRendered;
import com.vladsch.flexmark.ext.footnotes.internal.FootnoteRepository;
import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferencingNode;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/Footnote.class */
public class Footnote extends Node implements LinkRendered, DelimitedNode, DoNotDecorate, ReferencingNode<FootnoteRepository, FootnoteBlock> {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected FootnoteBlock footnoteBlock;
    protected int referenceOrdinal;

    public int getReferenceOrdinal() {
        return this.referenceOrdinal;
    }

    public void setReferenceOrdinal(int i) {
        this.referenceOrdinal = i;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public BasedSequence getReference() {
        return this.text;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public FootnoteBlock getReferenceNode(Document document) {
        if (this.footnoteBlock != null || this.text.isEmpty()) {
            return this.footnoteBlock;
        }
        this.footnoteBlock = getFootnoteBlock(FootnoteExtension.FOOTNOTES.get(document));
        return this.footnoteBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public FootnoteBlock getReferenceNode(FootnoteRepository footnoteRepository) {
        if (this.footnoteBlock != null || this.text.isEmpty()) {
            return this.footnoteBlock;
        }
        this.footnoteBlock = getFootnoteBlock(footnoteRepository);
        return this.footnoteBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public boolean isDefined() {
        return this.footnoteBlock != null;
    }

    @Override // com.vladsch.flexmark.ast.LinkRefDerived
    public boolean isTentative() {
        return this.footnoteBlock == null;
    }

    public FootnoteBlock getFootnoteBlock(FootnoteRepository footnoteRepository) {
        if (this.text.isEmpty()) {
            return null;
        }
        return footnoteRepository.get(this.text.toString());
    }

    public FootnoteBlock getFootnoteBlock() {
        return this.footnoteBlock;
    }

    public void setFootnoteBlock(FootnoteBlock footnoteBlock) {
        this.footnoteBlock = footnoteBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        sb.append(" ordinal: ").append(this.footnoteBlock != null ? this.footnoteBlock.getFootnoteOrdinal() : 0).append(SequenceUtils.SPACE);
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, this.closingMarker, "text");
    }

    public Footnote() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public Footnote(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public Footnote(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence3.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.text = basedSequence2;
        this.closingMarker = basedSequence3;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getText() {
        return this.text;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }
}
