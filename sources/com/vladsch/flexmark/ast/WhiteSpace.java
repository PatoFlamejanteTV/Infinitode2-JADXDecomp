package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/WhiteSpace.class */
public class WhiteSpace extends Node {
    public WhiteSpace() {
    }

    public WhiteSpace(BasedSequence basedSequence) {
        super(basedSequence);
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
}
