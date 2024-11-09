package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocOption.class */
public class SimTocOption extends Node implements DoNotDecorate {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
    }

    public SimTocOption() {
    }

    public SimTocOption(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
