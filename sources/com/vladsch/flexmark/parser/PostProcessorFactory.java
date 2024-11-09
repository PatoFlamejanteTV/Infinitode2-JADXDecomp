package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/PostProcessorFactory.class */
public interface PostProcessorFactory extends Dependent, Function<Document, PostProcessor> {
    Map<Class<?>, Set<Class<?>>> getNodeTypes();

    @Override // java.util.function.Function
    PostProcessor apply(Document document);
}
