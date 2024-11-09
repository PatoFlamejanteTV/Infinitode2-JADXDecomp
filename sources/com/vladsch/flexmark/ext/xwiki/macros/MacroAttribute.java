package com.vladsch.flexmark.ext.xwiki.macros;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/MacroAttribute.class */
public class MacroAttribute extends Node implements DoNotDecorate {
    protected BasedSequence attribute;
    protected BasedSequence separator;
    protected BasedSequence openingMarker;
    protected BasedSequence value;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.attribute, this.separator, this.openingMarker, this.value, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.attribute, "attribute");
        segmentSpanChars(sb, this.separator, "separator");
        delimitedSegmentSpanChars(sb, this.openingMarker, this.value, this.closingMarker, "value");
    }

    public MacroAttribute() {
        this.attribute = BasedSequence.NULL;
        this.separator = BasedSequence.NULL;
        this.openingMarker = BasedSequence.NULL;
        this.value = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public MacroAttribute(BasedSequence basedSequence) {
        super(basedSequence);
        this.attribute = BasedSequence.NULL;
        this.separator = BasedSequence.NULL;
        this.openingMarker = BasedSequence.NULL;
        this.value = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public MacroAttribute(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5) {
        super(spanningChars(basedSequence, basedSequence2, basedSequence3, basedSequence4, basedSequence5));
        this.attribute = BasedSequence.NULL;
        this.separator = BasedSequence.NULL;
        this.openingMarker = BasedSequence.NULL;
        this.value = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.attribute = basedSequence;
        this.separator = basedSequence2;
        this.openingMarker = basedSequence3;
        this.value = basedSequence4;
        this.closingMarker = basedSequence5;
    }

    public BasedSequence getAttribute() {
        return this.attribute;
    }

    public void setAttribute(BasedSequence basedSequence) {
        this.attribute = basedSequence;
    }

    public BasedSequence getSeparator() {
        return this.separator;
    }

    public void setSeparator(BasedSequence basedSequence) {
        this.separator = basedSequence;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getValue() {
        return this.value;
    }

    public void setValue(BasedSequence basedSequence) {
        this.value = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }
}
