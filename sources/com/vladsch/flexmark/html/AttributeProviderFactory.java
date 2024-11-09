package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/AttributeProviderFactory.class */
public interface AttributeProviderFactory extends Dependent, Function<LinkResolverContext, AttributeProvider> {
    @Override // com.vladsch.flexmark.util.dependency.Dependent
    Set<Class<?>> getAfterDependents();

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    Set<Class<?>> getBeforeDependents();

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    boolean affectsGlobalScope();

    @Override // java.util.function.Function
    AttributeProvider apply(LinkResolverContext linkResolverContext);
}
