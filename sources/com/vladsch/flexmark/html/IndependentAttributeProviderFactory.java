package com.vladsch.flexmark.html;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/IndependentAttributeProviderFactory.class */
public abstract class IndependentAttributeProviderFactory implements AttributeProviderFactory {
    @Override // com.vladsch.flexmark.html.AttributeProviderFactory, com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getAfterDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.html.AttributeProviderFactory, com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getBeforeDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.html.AttributeProviderFactory, com.vladsch.flexmark.util.dependency.Dependent
    public boolean affectsGlobalScope() {
        return false;
    }
}
