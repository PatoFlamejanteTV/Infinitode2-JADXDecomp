package com.vladsch.flexmark.ext.gfm.strikethrough.internal;

import com.vladsch.flexmark.ext.gfm.strikethrough.Strikethrough;
import com.vladsch.flexmark.ext.gfm.strikethrough.Subscript;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/internal/StrikethroughYouTrackRenderer.class */
public class StrikethroughYouTrackRenderer implements NodeRenderer {
    public StrikethroughYouTrackRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Strikethrough.class, this::render));
        hashSet.add(new NodeRenderingHandler(Subscript.class, this::render));
        return hashSet;
    }

    private void render(Strikethrough strikethrough, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw("--");
        nodeRendererContext.renderChildren(strikethrough);
        htmlWriter.raw("--");
    }

    private void render(Subscript subscript, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw("~");
        nodeRendererContext.renderChildren(subscript);
        htmlWriter.raw("~");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/internal/StrikethroughYouTrackRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new StrikethroughYouTrackRenderer(dataHolder);
        }
    }
}
