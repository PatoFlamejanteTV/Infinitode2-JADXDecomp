package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/BlockQuoteLike.class */
public interface BlockQuoteLike {
    BasedSequence getOpeningMarker();

    Node getFirstChild();

    BasedSequence getChars();

    Document getDocument();
}
