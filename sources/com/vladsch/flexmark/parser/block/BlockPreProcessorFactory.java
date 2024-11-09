package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/BlockPreProcessorFactory.class */
public interface BlockPreProcessorFactory extends Dependent, Function<ParserState, BlockPreProcessor> {
    Set<Class<? extends Block>> getBlockTypes();

    @Override // java.util.function.Function
    BlockPreProcessor apply(ParserState parserState);
}
