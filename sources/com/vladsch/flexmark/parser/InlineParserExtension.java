package com.vladsch.flexmark.parser;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/InlineParserExtension.class */
public interface InlineParserExtension {
    void finalizeDocument(InlineParser inlineParser);

    void finalizeBlock(InlineParser inlineParser);

    boolean parse(LightInlineParser lightInlineParser);
}
