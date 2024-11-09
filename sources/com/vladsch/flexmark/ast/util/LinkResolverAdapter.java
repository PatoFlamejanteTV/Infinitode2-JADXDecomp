package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.util.LinkResolvingHandler;
import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.visitor.AstActionHandler;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/LinkResolverAdapter.class */
public class LinkResolverAdapter extends AstActionHandler<LinkResolverAdapter, Node, LinkResolvingHandler.LinkResolvingVisitor<Node>, LinkResolvingHandler<Node>> implements LinkResolvingHandler.LinkResolvingVisitor<Node> {
    protected static final LinkResolvingHandler[] EMPTY_HANDLERS = new LinkResolvingHandler[0];

    /* JADX WARN: Multi-variable type inference failed */
    public LinkResolverAdapter(LinkResolvingHandler... linkResolvingHandlerArr) {
        super(Node.AST_ADAPTER);
        super.addActionHandlers(linkResolvingHandlerArr);
    }

    public LinkResolverAdapter(LinkResolvingHandler[]... linkResolvingHandlerArr) {
        super(Node.AST_ADAPTER);
        super.addActionHandlers(linkResolvingHandlerArr);
    }

    public LinkResolverAdapter(Collection<LinkResolvingHandler> collection) {
        super(Node.AST_ADAPTER);
        addHandlers(collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LinkResolverAdapter addHandlers(Collection<LinkResolvingHandler> collection) {
        return (LinkResolverAdapter) super.addActionHandlers((LinkResolvingHandler[]) collection.toArray(EMPTY_HANDLERS));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LinkResolverAdapter addHandlers(LinkResolvingHandler[] linkResolvingHandlerArr) {
        return (LinkResolverAdapter) super.addActionHandlers(linkResolvingHandlerArr);
    }

    public LinkResolverAdapter addHandlers(LinkResolvingHandler[]... linkResolvingHandlerArr) {
        return (LinkResolverAdapter) super.addActionHandlers(linkResolvingHandlerArr);
    }

    public LinkResolverAdapter addHandler(LinkResolvingHandler linkResolvingHandler) {
        return (LinkResolverAdapter) super.addActionHandler(linkResolvingHandler);
    }

    @Override // com.vladsch.flexmark.ast.util.LinkResolvingHandler.LinkResolvingVisitor
    public ResolvedLink resolveLink(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedLink resolvedLink) {
        return (ResolvedLink) processNodeOnly(node, resolvedLink, (node2, linkResolvingVisitor) -> {
            return linkResolvingVisitor.resolveLink(node2, linkResolverBasicContext, resolvedLink);
        });
    }
}
