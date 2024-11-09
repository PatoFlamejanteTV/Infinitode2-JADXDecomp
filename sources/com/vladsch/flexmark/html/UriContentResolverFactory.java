package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/UriContentResolverFactory.class */
public interface UriContentResolverFactory extends Dependent, Function<LinkResolverBasicContext, UriContentResolver> {
    @Override // com.vladsch.flexmark.util.dependency.Dependent
    Set<Class<?>> getAfterDependents();

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    Set<Class<?>> getBeforeDependents();

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    boolean affectsGlobalScope();

    @Override // java.util.function.Function
    UriContentResolver apply(LinkResolverBasicContext linkResolverBasicContext);
}
