package com.vladsch.flexmark.ext.xwiki.macros;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/MacroClose.class */
public class MacroClose extends Node {
    protected BasedSequence openingMarker;
    protected BasedSequence name;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.name, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.name, this.closingMarker, Attribute.NAME_ATTR);
    }

    public MacroClose() {
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public MacroClose(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public MacroClose(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence3.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.name = basedSequence2;
        this.closingMarker = basedSequence3;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getName() {
        return this.name;
    }

    public void setName(BasedSequence basedSequence) {
        this.name = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }
}
