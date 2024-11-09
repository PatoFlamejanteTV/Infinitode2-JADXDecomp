package com.vladsch.flexmark.ext.abbreviation;

import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationRepository;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.DoNotLinkDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferencingNode;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/Abbreviation.class */
public class Abbreviation extends Node implements DoNotDecorate, DoNotLinkDecorate, ReferencingNode<AbbreviationRepository, AbbreviationBlock> {
    protected final BasedSequence abbreviation;

    public Abbreviation(BasedSequence basedSequence, BasedSequence basedSequence2) {
        super(basedSequence);
        this.abbreviation = basedSequence2;
    }

    public BasedSequence getAbbreviation() {
        return this.abbreviation;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected String toStringAttributes() {
        return "text=" + ((Object) getChars());
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public boolean isDefined() {
        return true;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public BasedSequence getReference() {
        return this.abbreviation;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public AbbreviationBlock getReferenceNode(Document document) {
        return getReferenceNode(AbbreviationExtension.ABBREVIATIONS.get(document));
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public AbbreviationBlock getReferenceNode(AbbreviationRepository abbreviationRepository) {
        return abbreviationRepository.get(getChars().toString());
    }
}
