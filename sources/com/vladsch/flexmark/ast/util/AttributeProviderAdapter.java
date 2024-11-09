package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.util.AttributeProvidingHandler;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.visitor.AstActionHandler;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/AttributeProviderAdapter.class */
public class AttributeProviderAdapter extends AstActionHandler<AttributeProviderAdapter, Node, AttributeProvidingHandler.AttributeProvidingVisitor<Node>, AttributeProvidingHandler<Node>> implements AttributeProvidingHandler.AttributeProvidingVisitor<Node> {
    protected static final AttributeProvidingHandler[] EMPTY_HANDLERS = new AttributeProvidingHandler[0];

    /* JADX WARN: Multi-variable type inference failed */
    public AttributeProviderAdapter(AttributeProvidingHandler... attributeProvidingHandlerArr) {
        super(Node.AST_ADAPTER);
        super.addActionHandlers(attributeProvidingHandlerArr);
    }

    public AttributeProviderAdapter(AttributeProvidingHandler[]... attributeProvidingHandlerArr) {
        super(Node.AST_ADAPTER);
        super.addActionHandlers(attributeProvidingHandlerArr);
    }

    public AttributeProviderAdapter(Collection<AttributeProvidingHandler> collection) {
        super(Node.AST_ADAPTER);
        addHandlers(collection);
    }

    public AttributeProviderAdapter addHandlers(Collection<AttributeProvidingHandler> collection) {
        return addHandlers((AttributeProvidingHandler[]) collection.toArray(EMPTY_HANDLERS));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AttributeProviderAdapter addHandlers(AttributeProvidingHandler... attributeProvidingHandlerArr) {
        return (AttributeProviderAdapter) super.addActionHandlers(attributeProvidingHandlerArr);
    }

    public AttributeProviderAdapter addHandlers(AttributeProvidingHandler[]... attributeProvidingHandlerArr) {
        return (AttributeProviderAdapter) super.addActionHandlers(attributeProvidingHandlerArr);
    }

    public AttributeProviderAdapter addHandler(AttributeProvidingHandler attributeProvidingHandler) {
        return (AttributeProviderAdapter) super.addActionHandler(attributeProvidingHandler);
    }

    @Override // com.vladsch.flexmark.ast.util.AttributeProvidingHandler.AttributeProvidingVisitor
    public void setAttributes(Node node, AttributablePart attributablePart, MutableAttributes mutableAttributes) {
        processNode(node, false, (node2, attributeProvidingVisitor) -> {
            attributeProvidingVisitor.setAttributes(node2, attributablePart, mutableAttributes);
        });
    }
}
