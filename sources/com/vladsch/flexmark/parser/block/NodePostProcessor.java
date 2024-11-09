package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.parser.PostProcessor;
import com.vladsch.flexmark.util.ast.Document;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/NodePostProcessor.class */
public abstract class NodePostProcessor implements PostProcessor {
    @Override // com.vladsch.flexmark.parser.PostProcessor
    public final Document processDocument(Document document) {
        return document;
    }
}
