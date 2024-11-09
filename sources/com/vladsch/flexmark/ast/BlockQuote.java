package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.BlockQuoteLike;
import com.vladsch.flexmark.util.ast.KeepTrailingBlankLineContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/BlockQuote.class */
public class BlockQuote extends Block implements BlockQuoteLike, KeepTrailingBlankLineContainer {
    private BasedSequence openingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "marker");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker};
    }

    public BlockQuote() {
        this.openingMarker = BasedSequence.NULL;
    }

    public BlockQuote(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
    }

    public BlockQuote(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
    }

    public BlockQuote(BlockContent blockContent) {
        super(blockContent);
        this.openingMarker = BasedSequence.NULL;
    }

    @Override // com.vladsch.flexmark.util.ast.BlockQuoteLike
    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }
}
