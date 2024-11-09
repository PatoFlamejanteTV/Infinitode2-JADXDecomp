package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/FencedCodeBlock.class */
public class FencedCodeBlock extends Block implements DoNotDecorate {
    private int fenceIndent;
    private BasedSequence openingMarker;
    private BasedSequence info;
    private BasedSequence attributes;
    private BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        BasedSequence contentChars = getContentChars();
        int size = getContentLines().size();
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.info, "info");
        segmentSpanChars(sb, this.attributes, "attributes");
        segmentSpan(sb, contentChars, "content");
        sb.append(" lines[").append(size).append("]");
        segmentSpanChars(sb, this.closingMarker, "close");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.info, this.attributes, getContentChars(), this.closingMarker};
    }

    public FencedCodeBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.info = BasedSequence.NULL;
        this.attributes = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public FencedCodeBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.info = BasedSequence.NULL;
        this.attributes = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public FencedCodeBlock(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, List<BasedSequence> list, BasedSequence basedSequence4) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
        this.info = BasedSequence.NULL;
        this.attributes = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence2;
        this.info = basedSequence3;
        this.closingMarker = basedSequence4;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public void setInfo(BasedSequence basedSequence) {
        this.info = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public BasedSequence getOpeningFence() {
        return this.openingMarker;
    }

    public BasedSequence getInfo() {
        return this.info;
    }

    public BasedSequence getAttributes() {
        return this.attributes;
    }

    public void setAttributes(BasedSequence basedSequence) {
        this.attributes = basedSequence;
    }

    public BasedSequence getInfoDelimitedByAny(CharPredicate charPredicate) {
        BasedSequence basedSequence = BasedSequence.NULL;
        if (this.info.isNotNull() && !this.info.isBlank()) {
            int indexOfAny = this.info.indexOfAny(charPredicate);
            basedSequence = indexOfAny == -1 ? this.info : this.info.subSequence(0, indexOfAny);
        }
        return basedSequence;
    }

    public BasedSequence getClosingFence() {
        return this.closingMarker;
    }

    public int getFenceLength() {
        return getInfo().length();
    }

    public int getFenceIndent() {
        return this.fenceIndent;
    }

    public void setFenceIndent(int i) {
        this.fenceIndent = i;
    }
}
