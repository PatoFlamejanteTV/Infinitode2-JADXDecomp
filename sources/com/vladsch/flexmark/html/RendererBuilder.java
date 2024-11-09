package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.HeaderIdGeneratorFactory;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/RendererBuilder.class */
public interface RendererBuilder extends DataHolder {
    RendererBuilder attributeProviderFactory(AttributeProviderFactory attributeProviderFactory);

    RendererBuilder linkResolverFactory(LinkResolverFactory linkResolverFactory);

    RendererBuilder contentResolverFactory(UriContentResolverFactory uriContentResolverFactory);

    RendererBuilder htmlIdGeneratorFactory(HeaderIdGeneratorFactory headerIdGeneratorFactory);
}
