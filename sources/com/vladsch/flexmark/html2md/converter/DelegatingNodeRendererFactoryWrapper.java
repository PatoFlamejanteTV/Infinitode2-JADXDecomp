package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/DelegatingNodeRendererFactoryWrapper.class */
public class DelegatingNodeRendererFactoryWrapper implements DelegatingNodeRendererFactory, Dependent, Function<DataHolder, HtmlNodeRenderer> {
    private final HtmlNodeRendererFactory nodeRendererFactory;
    private List<DelegatingNodeRendererFactoryWrapper> nodeRenderers;
    private Set<Class<?>> myDelegates = null;

    public DelegatingNodeRendererFactoryWrapper(List<DelegatingNodeRendererFactoryWrapper> list, HtmlNodeRendererFactory htmlNodeRendererFactory) {
        this.nodeRendererFactory = htmlNodeRendererFactory;
        this.nodeRenderers = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeRendererFactory, java.util.function.Function
    public HtmlNodeRenderer apply(DataHolder dataHolder) {
        return this.nodeRendererFactory.apply(dataHolder);
    }

    public HtmlNodeRendererFactory getFactory() {
        return this.nodeRendererFactory;
    }

    @Override // com.vladsch.flexmark.html2md.converter.DelegatingNodeRendererFactory
    public Set<Class<?>> getDelegates() {
        if (this.nodeRendererFactory instanceof DelegatingNodeRendererFactory) {
            return ((DelegatingNodeRendererFactory) this.nodeRendererFactory).getDelegates();
        }
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public final Set<Class<?>> getAfterDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getBeforeDependents() {
        if (this.myDelegates == null && this.nodeRenderers != null) {
            Set<Class<?>> delegates = getDelegates();
            if (delegates != null) {
                this.myDelegates = new HashSet();
                for (DelegatingNodeRendererFactoryWrapper delegatingNodeRendererFactoryWrapper : this.nodeRenderers) {
                    if (delegates.contains(delegatingNodeRendererFactoryWrapper.getFactory().getClass())) {
                        this.myDelegates.add(delegatingNodeRendererFactoryWrapper.getFactory().getClass());
                    }
                }
            }
            this.nodeRenderers = null;
        }
        return this.myDelegates;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public final boolean affectsGlobalScope() {
        return false;
    }
}
