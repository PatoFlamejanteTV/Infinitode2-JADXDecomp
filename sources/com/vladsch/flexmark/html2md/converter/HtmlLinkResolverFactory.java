package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlLinkResolverFactory.class */
public interface HtmlLinkResolverFactory extends Dependent, Function<HtmlNodeConverterContext, HtmlLinkResolver> {
    @Override // com.vladsch.flexmark.util.dependency.Dependent
    Set<Class<?>> getAfterDependents();

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    Set<Class<?>> getBeforeDependents();

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    boolean affectsGlobalScope();

    @Override // java.util.function.Function
    HtmlLinkResolver apply(HtmlNodeConverterContext htmlNodeConverterContext);
}
