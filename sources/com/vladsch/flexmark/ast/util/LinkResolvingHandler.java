package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.visitor.AstAction;
import com.vladsch.flexmark.util.visitor.AstHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/LinkResolvingHandler.class */
public class LinkResolvingHandler<N extends Node> extends AstHandler<N, LinkResolvingVisitor<N>> {

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/LinkResolvingHandler$LinkResolvingVisitor.class */
    public interface LinkResolvingVisitor<N extends Node> extends AstAction<N> {
        ResolvedLink resolveLink(N n, LinkResolverBasicContext linkResolverBasicContext, ResolvedLink resolvedLink);
    }

    public LinkResolvingHandler(Class<N> cls, LinkResolvingVisitor<N> linkResolvingVisitor) {
        super(cls, linkResolvingVisitor);
    }

    public ResolvedLink resolveLink(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedLink resolvedLink) {
        return getAdapter().resolveLink(node, linkResolverBasicContext, resolvedLink);
    }
}
