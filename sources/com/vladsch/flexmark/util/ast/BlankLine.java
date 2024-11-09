package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/BlankLine.class */
public class BlankLine extends Block {
    private Block claimedBlankLine;

    public boolean isClaimed() {
        return this.claimedBlankLine != null;
    }

    public Block getClaimedBlankLine() {
        return this.claimedBlankLine;
    }

    public BlankLine setClaimedBlankLine(Block block) {
        this.claimedBlankLine = block;
        return this;
    }

    public BlankLine(BasedSequence basedSequence) {
        super(basedSequence);
        this.claimedBlankLine = null;
        setCharsFromContent();
    }

    public BlankLine(BasedSequence basedSequence, Block block) {
        super(basedSequence);
        this.claimedBlankLine = null;
        setCharsFromContent();
        this.claimedBlankLine = block;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }
}
