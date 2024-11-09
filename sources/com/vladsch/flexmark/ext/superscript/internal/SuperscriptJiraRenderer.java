package com.vladsch.flexmark.ext.superscript.internal;

import com.vladsch.flexmark.ext.superscript.Superscript;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/superscript/internal/SuperscriptJiraRenderer.class */
public class SuperscriptJiraRenderer implements NodeRenderer {
    public SuperscriptJiraRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Superscript.class, this::render));
        return hashSet;
    }

    private void render(Superscript superscript, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw("^");
        nodeRendererContext.renderChildren(superscript);
        htmlWriter.raw("^");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/superscript/internal/SuperscriptJiraRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new SuperscriptJiraRenderer(dataHolder);
        }
    }
}
