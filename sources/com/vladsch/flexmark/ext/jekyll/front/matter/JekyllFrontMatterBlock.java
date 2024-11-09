package com.vladsch.flexmark.ext.jekyll.front.matter;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/front/matter/JekyllFrontMatterBlock.class */
public class JekyllFrontMatterBlock extends Block {
    protected BasedSequence openingMarker;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpan(sb, this.openingMarker, "open");
        segmentSpan(sb, getContent(), "content");
        segmentSpan(sb, this.closingMarker, "close");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.closingMarker};
    }

    public JekyllFrontMatterBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public JekyllFrontMatterBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public JekyllFrontMatterBlock(Node node) {
        this.openingMarker = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        appendChild(node);
        setCharsFromContent();
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getContent() {
        return getContentChars();
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public void accept(JekyllFrontMatterVisitor jekyllFrontMatterVisitor) {
        jekyllFrontMatterVisitor.visit(this);
    }
}
