package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.visitor.AstAction;
import com.vladsch.flexmark.util.visitor.AstHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/AttributeProvidingHandler.class */
public class AttributeProvidingHandler<N extends Node> extends AstHandler<N, AttributeProvidingVisitor<N>> {

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/AttributeProvidingHandler$AttributeProvidingVisitor.class */
    public interface AttributeProvidingVisitor<N extends Node> extends AstAction<N> {
        void setAttributes(N n, AttributablePart attributablePart, MutableAttributes mutableAttributes);
    }

    public AttributeProvidingHandler(Class<N> cls, AttributeProvidingVisitor<N> attributeProvidingVisitor) {
        super(cls, attributeProvidingVisitor);
    }

    public void setAttributes(Node node, AttributablePart attributablePart, MutableAttributes mutableAttributes) {
        getAdapter().setAttributes(node, attributablePart, mutableAttributes);
    }
}
