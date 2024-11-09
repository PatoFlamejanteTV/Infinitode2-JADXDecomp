package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/ParagraphPreProcessorFactory.class */
public interface ParagraphPreProcessorFactory extends Dependent, Function<ParserState, ParagraphPreProcessor> {
    @Override // java.util.function.Function
    ParagraphPreProcessor apply(ParserState parserState);
}
