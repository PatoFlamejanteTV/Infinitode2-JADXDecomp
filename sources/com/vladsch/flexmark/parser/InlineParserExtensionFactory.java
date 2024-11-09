package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/InlineParserExtensionFactory.class */
public interface InlineParserExtensionFactory extends Dependent, Function<LightInlineParser, InlineParserExtension> {
    CharSequence getCharacters();

    @Override // java.util.function.Function
    InlineParserExtension apply(LightInlineParser lightInlineParser);
}
