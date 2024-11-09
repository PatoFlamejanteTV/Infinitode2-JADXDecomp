package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.visitor.AstAction;
import com.vladsch.flexmark.util.visitor.AstHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/NodeRenderingHandler.class */
public class NodeRenderingHandler<N extends Node> extends AstHandler<N, CustomNodeRenderer<N>> {

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/NodeRenderingHandler$CustomNodeRenderer.class */
    public interface CustomNodeRenderer<N extends Node> extends AstAction<N> {
        void render(N n, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter);
    }

    public NodeRenderingHandler(Class<N> cls, CustomNodeRenderer<N> customNodeRenderer) {
        super(cls, customNodeRenderer);
    }

    public void render(Node node, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        getAdapter().render(node, nodeRendererContext, htmlWriter);
    }
}
