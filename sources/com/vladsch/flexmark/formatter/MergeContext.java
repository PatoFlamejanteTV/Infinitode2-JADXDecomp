package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Document;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/MergeContext.class */
public interface MergeContext {
    Document getDocument(TranslationContext translationContext);

    void forEachPrecedingDocument(Document document, MergeContextConsumer mergeContextConsumer);
}
