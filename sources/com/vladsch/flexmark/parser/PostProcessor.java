package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/PostProcessor.class */
public interface PostProcessor {
    Document processDocument(Document document);

    void process(NodeTracker nodeTracker, Node node);
}
