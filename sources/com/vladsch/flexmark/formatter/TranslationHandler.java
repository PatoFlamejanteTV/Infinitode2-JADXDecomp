package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Document;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/TranslationHandler.class */
public interface TranslationHandler extends TranslationContext {
    void beginRendering(Document document, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter);

    List<String> getTranslatingTexts();

    void setTranslatedTexts(List<? extends CharSequence> list);

    void setRenderPurpose(RenderPurpose renderPurpose);

    void setMergeContext(MergeContext mergeContext);
}
