package com.vladsch.flexmark.ext.wikilink;

import com.vladsch.flexmark.ast.LinkRendered;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/WikiLink.class */
public class WikiLink extends WikiNode implements LinkRendered {
    public WikiLink(boolean z) {
        super(z);
    }

    public WikiLink(BasedSequence basedSequence, boolean z, boolean z2, boolean z3, boolean z4) {
        super(basedSequence, z, z2, z3, z4);
    }
}
