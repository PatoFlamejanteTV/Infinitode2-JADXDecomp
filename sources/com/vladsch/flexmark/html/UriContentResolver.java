package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.ResolvedContent;
import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/UriContentResolver.class */
public interface UriContentResolver {
    public static final UriContentResolver NULL = (node, linkResolverBasicContext, resolvedContent) -> {
        return resolvedContent;
    };

    ResolvedContent resolveContent(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedContent resolvedContent);
}
