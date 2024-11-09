package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/HtmlInnerBlock.class */
public class HtmlInnerBlock extends HtmlBlockBase {
    @Override // com.vladsch.flexmark.ast.HtmlBlockBase, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
    }

    public HtmlInnerBlock() {
    }

    public HtmlInnerBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
