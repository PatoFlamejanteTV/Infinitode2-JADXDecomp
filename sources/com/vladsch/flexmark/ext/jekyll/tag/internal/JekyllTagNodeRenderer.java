package com.vladsch.flexmark.ext.jekyll.tag.internal;

import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagBlock;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagNodeRenderer.class */
public class JekyllTagNodeRenderer implements NodeRenderer {
    private final boolean embedIncludes;
    private final Map<String, String> includeContent;

    public JekyllTagNodeRenderer(DataHolder dataHolder) {
        this.includeContent = JekyllTagExtension.INCLUDED_HTML.get(dataHolder);
        this.embedIncludes = JekyllTagExtension.EMBED_INCLUDED_CONTENT.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(JekyllTag.class, this::render));
        hashSet.add(new NodeRenderingHandler(JekyllTagBlock.class, this::render));
        return hashSet;
    }

    private void render(JekyllTag jekyllTag, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.embedIncludes) {
            nodeRendererContext.renderChildren(jekyllTag);
        }
    }

    private void render(JekyllTagBlock jekyllTagBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(jekyllTagBlock);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new JekyllTagNodeRenderer(dataHolder);
        }
    }
}
