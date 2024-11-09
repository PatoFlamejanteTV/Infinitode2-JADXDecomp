package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/TranslationContext.class */
public interface TranslationContext {
    HtmlIdGenerator getIdGenerator();

    RenderPurpose getRenderPurpose();

    MutableDataHolder getTranslationStore();

    boolean isTransformingText();

    CharSequence transformNonTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4);

    void postProcessNonTranslating(Function<String, CharSequence> function, Runnable runnable);

    <T> T postProcessNonTranslating(Function<String, CharSequence> function, Supplier<T> supplier);

    boolean isPostProcessingNonTranslating();

    CharSequence transformTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4);

    CharSequence transformAnchorRef(CharSequence charSequence, CharSequence charSequence2);

    void translatingSpan(TranslatingSpanRender translatingSpanRender);

    void nonTranslatingSpan(TranslatingSpanRender translatingSpanRender);

    void translatingRefTargetSpan(Node node, TranslatingSpanRender translatingSpanRender);

    void customPlaceholderFormat(TranslationPlaceholderGenerator translationPlaceholderGenerator, TranslatingSpanRender translatingSpanRender);

    MergeContext getMergeContext();
}
