package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.html.HtmlRendererOptions;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.MutableAttributes;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/NodeRendererContext.class */
public interface NodeRendererContext extends LinkResolverContext {
    MutableAttributes extendRenderingNodeAttributes(AttributablePart attributablePart, Attributes attributes);

    MutableAttributes extendRenderingNodeAttributes(Node node, AttributablePart attributablePart, Attributes attributes);

    HtmlWriter getHtmlWriter();

    NodeRendererContext getSubContext(boolean z);

    NodeRendererContext getDelegatedSubContext(boolean z);

    void delegateRender();

    String getNodeId(Node node);

    boolean isDoNotRenderLinks();

    void doNotRenderLinks(boolean z);

    void doNotRenderLinks();

    void doRenderLinks();

    RenderingPhase getRenderingPhase();

    HtmlRendererOptions getHtmlOptions();
}
