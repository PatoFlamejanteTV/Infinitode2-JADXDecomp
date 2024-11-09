package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.parser.block.ParagraphPreProcessor;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.internal.InlineParserImpl;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ReferencePreProcessorFactory.class */
public class ReferencePreProcessorFactory implements ParagraphPreProcessorFactory {
    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public boolean affectsGlobalScope() {
        return true;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getAfterDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getBeforeDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory, java.util.function.Function
    public ParagraphPreProcessor apply(ParserState parserState) {
        return (InlineParserImpl) parserState.getInlineParser();
    }
}
