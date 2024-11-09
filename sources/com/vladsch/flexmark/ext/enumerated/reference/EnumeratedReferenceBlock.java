package com.vladsch.flexmark.ext.enumerated.reference;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphItemContainer;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceBlock.class */
public class EnumeratedReferenceBlock extends Block implements ParagraphItemContainer, ReferenceNode<EnumeratedReferenceRepository, EnumeratedReferenceBlock, EnumeratedReferenceText> {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected BasedSequence enumeratedReference;

    @Override // java.lang.Comparable
    public int compareTo(EnumeratedReferenceBlock enumeratedReferenceBlock) {
        return SequenceUtils.compare(this.text, enumeratedReferenceBlock.text, true);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferenceNode
    public EnumeratedReferenceText getReferencingNode(Node node) {
        if (node instanceof EnumeratedReferenceText) {
            return (EnumeratedReferenceText) node;
        }
        return null;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpan(sb, this.openingMarker, "open");
        segmentSpan(sb, this.text, "text");
        segmentSpan(sb, this.closingMarker, "close");
        segmentSpan(sb, this.enumeratedReference, "enumeratedReference");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker, this.enumeratedReference};
    }

    public EnumeratedReferenceBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.enumeratedReference = BasedSequence.NULL;
    }

    public EnumeratedReferenceBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.enumeratedReference = BasedSequence.NULL;
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

    public BasedSequence getEnumeratedReference() {
        return this.enumeratedReference;
    }

    public void setEnumeratedReference(BasedSequence basedSequence) {
        this.enumeratedReference = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isItemParagraph(Paragraph paragraph) {
        return paragraph == getFirstChild();
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphWrappingDisabled(Paragraph paragraph, ListOptions listOptions, DataHolder dataHolder) {
        return true;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphInTightListItem(Paragraph paragraph) {
        return true;
    }
}
