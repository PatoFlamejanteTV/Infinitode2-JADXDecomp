package com.vladsch.flexmark.ext.wikilink;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/WikiImage.class */
public class WikiImage extends WikiNode {
    public WikiImage(boolean z) {
        super(z);
    }

    public WikiImage(BasedSequence basedSequence, boolean z, boolean z2) {
        super(basedSequence, z, false, z2, false);
    }
}
