package com.vladsch.flexmark.ext.tables.internal;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableColumnSeparator.class */
public class TableColumnSeparator extends Node implements DoNotDecorate {
    public TableColumnSeparator() {
    }

    public TableColumnSeparator(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public TableColumnSeparator(String str) {
        super(BasedSequence.of(str));
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
