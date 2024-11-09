package com.vladsch.flexmark.ext.footnotes;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphItemContainer;
import com.vladsch.flexmark.ext.footnotes.internal.FootnoteRepository;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/FootnoteBlock.class */
public class FootnoteBlock extends Block implements ParagraphItemContainer, ReferenceNode<FootnoteRepository, FootnoteBlock, Footnote> {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected BasedSequence footnote;
    private int footnoteOrdinal;
    private int firstReferenceOffset;
    private int footnoteReferences;

    @Override // java.lang.Comparable
    public int compareTo(FootnoteBlock footnoteBlock) {
        return SequenceUtils.compare(this.text, footnoteBlock.text, true);
    }

    public int getFootnoteReferences() {
        return this.footnoteReferences;
    }

    public void setFootnoteReferences(int i) {
        this.footnoteReferences = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferenceNode
    public Footnote getReferencingNode(Node node) {
        if (node instanceof Footnote) {
            return (Footnote) node;
        }
        return null;
    }

    public int getFirstReferenceOffset() {
        return this.firstReferenceOffset;
    }

    public void setFirstReferenceOffset(int i) {
        this.firstReferenceOffset = i;
    }

    public void addFirstReferenceOffset(int i) {
        if (this.firstReferenceOffset < i) {
            this.firstReferenceOffset = i;
        }
    }

    public boolean isReferenced() {
        return this.firstReferenceOffset < Integer.MAX_VALUE;
    }

    public int getFootnoteOrdinal() {
        return this.footnoteOrdinal;
    }

    public void setFootnoteOrdinal(int i) {
        this.footnoteOrdinal = i;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        sb.append(" ordinal: ").append(this.footnoteOrdinal).append(SequenceUtils.SPACE);
        segmentSpan(sb, this.openingMarker, "open");
        segmentSpan(sb, this.text, "text");
        segmentSpan(sb, this.closingMarker, "close");
        segmentSpan(sb, this.footnote, "footnote");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker, this.footnote};
    }

    public FootnoteBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.footnote = BasedSequence.NULL;
        this.footnoteOrdinal = 0;
        this.firstReferenceOffset = Integer.MAX_VALUE;
        this.footnoteReferences = 0;
    }

    public FootnoteBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.footnote = BasedSequence.NULL;
        this.footnoteOrdinal = 0;
        this.firstReferenceOffset = Integer.MAX_VALUE;
        this.footnoteReferences = 0;
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

    public BasedSequence getFootnote() {
        return this.footnote;
    }

    public void setFootnote(BasedSequence basedSequence) {
        this.footnote = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isItemParagraph(Paragraph paragraph) {
        return paragraph == getFirstChild();
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphWrappingDisabled(Paragraph paragraph, ListOptions listOptions, DataHolder dataHolder) {
        return false;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphInTightListItem(Paragraph paragraph) {
        return false;
    }
}
