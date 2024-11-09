package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/HtmlInlineComment.class */
public class HtmlInlineComment extends HtmlInlineBase {
    @Override // com.vladsch.flexmark.ast.HtmlInlineBase, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.ast.HtmlInlineBase, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
    }

    public HtmlInlineComment() {
    }

    public HtmlInlineComment(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
