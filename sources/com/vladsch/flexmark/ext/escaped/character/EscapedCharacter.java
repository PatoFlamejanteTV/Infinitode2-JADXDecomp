package com.vladsch.flexmark.ext.escaped.character;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/EscapedCharacter.class */
public class EscapedCharacter extends Node implements DoNotDecorate {
    protected BasedSequence openingMarker;
    protected BasedSequence text;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, BasedSequence.NULL, "text");
    }

    public EscapedCharacter() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
    }

    public EscapedCharacter(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
    }

    public EscapedCharacter(BasedSequence basedSequence, BasedSequence basedSequence2) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence2.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.text = basedSequence2;
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
}
