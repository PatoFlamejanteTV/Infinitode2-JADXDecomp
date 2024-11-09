package com.vladsch.flexmark.ext.abbreviation;

import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationRepository;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/AbbreviationBlock.class */
public class AbbreviationBlock extends Block implements ReferenceNode<AbbreviationRepository, AbbreviationBlock, Abbreviation> {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected BasedSequence abbreviation;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferenceNode
    public Abbreviation getReferencingNode(Node node) {
        if (node instanceof Abbreviation) {
            return (Abbreviation) node;
        }
        return null;
    }

    @Override // java.lang.Comparable
    public int compareTo(AbbreviationBlock abbreviationBlock) {
        return SequenceUtils.compare(this.text, abbreviationBlock.text, true);
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpan(sb, this.openingMarker, "open");
        segmentSpan(sb, this.text, "text");
        segmentSpan(sb, this.closingMarker, "close");
        segmentSpan(sb, this.abbreviation, "abbreviation");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker, this.abbreviation};
    }

    public AbbreviationBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.abbreviation = BasedSequence.NULL;
    }

    public AbbreviationBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.abbreviation = BasedSequence.NULL;
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

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public BasedSequence getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(BasedSequence basedSequence) {
        this.abbreviation = basedSequence;
    }
}
