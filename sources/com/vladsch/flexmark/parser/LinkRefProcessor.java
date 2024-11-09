package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/LinkRefProcessor.class */
public interface LinkRefProcessor {
    boolean getWantExclamationPrefix();

    int getBracketNestingLevel();

    boolean isMatch(BasedSequence basedSequence);

    Node createNode(BasedSequence basedSequence);

    BasedSequence adjustInlineText(Document document, Node node);

    boolean allowDelimiters(BasedSequence basedSequence, Document document, Node node);

    void updateNodeElements(Document document, Node node);
}
