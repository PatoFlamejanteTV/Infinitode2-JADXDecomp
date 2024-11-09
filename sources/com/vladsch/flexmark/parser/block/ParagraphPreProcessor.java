package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.ast.Paragraph;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/ParagraphPreProcessor.class */
public interface ParagraphPreProcessor {
    int preProcessBlock(Paragraph paragraph, ParserState parserState);
}
