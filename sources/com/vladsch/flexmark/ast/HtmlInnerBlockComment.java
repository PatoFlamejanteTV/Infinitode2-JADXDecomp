package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/HtmlInnerBlockComment.class */
public class HtmlInnerBlockComment extends HtmlBlockBase {
    @Override // com.vladsch.flexmark.ast.HtmlBlockBase, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
    }

    public HtmlInnerBlockComment() {
    }

    public HtmlInnerBlockComment(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
