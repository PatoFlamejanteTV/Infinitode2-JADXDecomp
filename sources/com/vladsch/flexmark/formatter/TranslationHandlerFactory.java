package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.html.renderer.HtmlIdGeneratorFactory;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/TranslationHandlerFactory.class */
public interface TranslationHandlerFactory extends TranslationContext {
    TranslationHandler create(DataHolder dataHolder, HtmlIdGeneratorFactory htmlIdGeneratorFactory);
}
