package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/LinkResolver.class */
public interface LinkResolver {
    public static final LinkResolver NULL = (node, linkResolverBasicContext, resolvedLink) -> {
        return resolvedLink;
    };

    ResolvedLink resolveLink(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedLink resolvedLink);
}
