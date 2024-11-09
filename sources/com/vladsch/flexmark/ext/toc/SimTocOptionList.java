package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocOptionList.class */
public class SimTocOptionList extends Node implements DoNotDecorate {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public SimTocOptionList() {
    }

    public SimTocOptionList(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
