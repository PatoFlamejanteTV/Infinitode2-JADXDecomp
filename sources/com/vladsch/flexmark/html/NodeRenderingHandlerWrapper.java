package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/NodeRenderingHandlerWrapper.class */
class NodeRenderingHandlerWrapper {
    public final NodeRenderingHandler<?> myRenderingHandler;
    public final NodeRenderingHandlerWrapper myPreviousRenderingHandler;

    public NodeRenderingHandlerWrapper(NodeRenderingHandler<?> nodeRenderingHandler, NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper) {
        this.myRenderingHandler = nodeRenderingHandler;
        this.myPreviousRenderingHandler = nodeRenderingHandlerWrapper;
    }
}
