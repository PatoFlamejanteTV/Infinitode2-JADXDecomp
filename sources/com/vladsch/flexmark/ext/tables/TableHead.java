package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableHead.class */
public class TableHead extends Node {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public TableHead() {
    }

    public TableHead(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
