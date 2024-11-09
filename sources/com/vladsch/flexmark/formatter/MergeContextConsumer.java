package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Document;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/MergeContextConsumer.class */
public interface MergeContextConsumer {
    void accept(TranslationContext translationContext, Document document, int i);
}
