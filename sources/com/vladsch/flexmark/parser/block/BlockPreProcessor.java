package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.util.ast.Block;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/BlockPreProcessor.class */
public interface BlockPreProcessor {
    void preProcess(ParserState parserState, Block block);
}
