package com.vladsch.flexmark.html2md.converter;

import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/NodeRenderingHandlerWrapper.class */
class NodeRenderingHandlerWrapper<N extends Node> {
    public final HtmlNodeRendererHandler<N> myRenderingHandler;
    public final NodeRenderingHandlerWrapper<N> myPreviousRenderingHandler;

    public NodeRenderingHandlerWrapper(HtmlNodeRendererHandler<N> htmlNodeRendererHandler, NodeRenderingHandlerWrapper<N> nodeRenderingHandlerWrapper) {
        this.myRenderingHandler = htmlNodeRendererHandler;
        this.myPreviousRenderingHandler = nodeRenderingHandlerWrapper;
    }
}
