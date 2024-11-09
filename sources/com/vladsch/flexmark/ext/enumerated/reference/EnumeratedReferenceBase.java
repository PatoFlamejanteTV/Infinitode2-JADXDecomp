package com.vladsch.flexmark.ext.enumerated.reference;

import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferencingNode;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceBase.class */
public class EnumeratedReferenceBase extends Node implements DelimitedNode, DoNotDecorate, ReferencingNode<EnumeratedReferenceRepository, EnumeratedReferenceBlock> {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected EnumeratedReferenceBlock enumeratedReferenceBlock;

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public BasedSequence getReference() {
        return this.text;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public EnumeratedReferenceBlock getReferenceNode(Document document) {
        return this.enumeratedReferenceBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public EnumeratedReferenceBlock getReferenceNode(EnumeratedReferenceRepository enumeratedReferenceRepository) {
        if (this.enumeratedReferenceBlock != null || this.text.isEmpty()) {
            return this.enumeratedReferenceBlock;
        }
        this.enumeratedReferenceBlock = getEnumeratedReferenceBlock(enumeratedReferenceRepository);
        return this.enumeratedReferenceBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public boolean isDefined() {
        return this.enumeratedReferenceBlock != null;
    }

    public EnumeratedReferenceBlock getEnumeratedReferenceBlock(EnumeratedReferenceRepository enumeratedReferenceRepository) {
        if (this.text.isEmpty()) {
            return null;
        }
        return enumeratedReferenceRepository.get(EnumeratedReferenceRepository.getType(this.text.toString()));
    }

    public EnumeratedReferenceBlock getEnumeratedReferenceBlock() {
        return this.enumeratedReferenceBlock;
    }

    public void setEnumeratedReferenceBlock(EnumeratedReferenceBlock enumeratedReferenceBlock) {
        this.enumeratedReferenceBlock = enumeratedReferenceBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, this.closingMarker, "text");
    }

    public EnumeratedReferenceBase() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public EnumeratedReferenceBase(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public EnumeratedReferenceBase(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
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
