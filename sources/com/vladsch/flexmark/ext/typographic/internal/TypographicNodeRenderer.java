package com.vladsch.flexmark.ext.typographic.internal;

import com.vladsch.flexmark.ext.typographic.TypographicQuotes;
import com.vladsch.flexmark.ext.typographic.TypographicSmarts;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/TypographicNodeRenderer.class */
public class TypographicNodeRenderer implements NodeRenderer {
    public TypographicNodeRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(TypographicSmarts.class, this::render));
        hashSet.add(new NodeRenderingHandler(TypographicQuotes.class, this::render));
        return hashSet;
    }

    private void render(TypographicQuotes typographicQuotes, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (typographicQuotes.getTypographicOpening() != null && !typographicQuotes.getTypographicOpening().isEmpty()) {
            htmlWriter.raw((CharSequence) typographicQuotes.getTypographicOpening());
        }
        nodeRendererContext.renderChildren(typographicQuotes);
        if (typographicQuotes.getTypographicClosing() == null || typographicQuotes.getTypographicClosing().isEmpty()) {
            return;
        }
        htmlWriter.raw((CharSequence) typographicQuotes.getTypographicClosing());
    }

    private void render(TypographicSmarts typographicSmarts, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw((CharSequence) typographicSmarts.getTypographicText());
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/TypographicNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new TypographicNodeRenderer(dataHolder);
        }
    }
}
