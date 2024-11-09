package com.vladsch.flexmark.html.renderer;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/NodeRenderer.class */
public interface NodeRenderer {
    Set<NodeRenderingHandler<?>> getNodeRenderingHandlers();
}
