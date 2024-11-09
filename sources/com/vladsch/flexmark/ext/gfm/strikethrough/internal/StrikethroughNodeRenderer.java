package com.vladsch.flexmark.ext.gfm.strikethrough.internal;

import com.vladsch.flexmark.ext.gfm.strikethrough.Strikethrough;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.Subscript;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/internal/StrikethroughNodeRenderer.class */
public class StrikethroughNodeRenderer implements NodeRenderer {
    private final String strikethroughStyleHtmlOpen;
    private final String strikethroughStyleHtmlClose;
    private final String subscriptStyleHtmlOpen;
    private final String subscriptStyleHtmlClose;

    public StrikethroughNodeRenderer(DataHolder dataHolder) {
        this.strikethroughStyleHtmlOpen = StrikethroughSubscriptExtension.STRIKETHROUGH_STYLE_HTML_OPEN.get(dataHolder);
        this.strikethroughStyleHtmlClose = StrikethroughSubscriptExtension.STRIKETHROUGH_STYLE_HTML_CLOSE.get(dataHolder);
        this.subscriptStyleHtmlOpen = StrikethroughSubscriptExtension.SUBSCRIPT_STYLE_HTML_OPEN.get(dataHolder);
        this.subscriptStyleHtmlClose = StrikethroughSubscriptExtension.SUBSCRIPT_STYLE_HTML_CLOSE.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Strikethrough.class, this::render));
        hashSet.add(new NodeRenderingHandler(Subscript.class, this::render));
        return hashSet;
    }

    private void render(Strikethrough strikethrough, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.strikethroughStyleHtmlOpen == null || this.strikethroughStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.DEL_NODE);
            } else {
                htmlWriter.srcPos(strikethrough.getText()).withAttr().tag(FlexmarkHtmlConverter.DEL_NODE);
            }
            nodeRendererContext.renderChildren(strikethrough);
            htmlWriter.tag("/del");
            return;
        }
        htmlWriter.raw((CharSequence) this.strikethroughStyleHtmlOpen);
        nodeRendererContext.renderChildren(strikethrough);
        htmlWriter.raw((CharSequence) this.strikethroughStyleHtmlClose);
    }

    private void render(Subscript subscript, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.subscriptStyleHtmlOpen == null || this.subscriptStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.SUB_NODE);
            } else {
                htmlWriter.srcPos(subscript.getText()).withAttr().tag(FlexmarkHtmlConverter.SUB_NODE);
            }
            nodeRendererContext.renderChildren(subscript);
            htmlWriter.tag("/sub");
            return;
        }
        htmlWriter.raw((CharSequence) this.subscriptStyleHtmlOpen);
        nodeRendererContext.renderChildren(subscript);
        htmlWriter.raw((CharSequence) this.subscriptStyleHtmlClose);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/internal/StrikethroughNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new StrikethroughNodeRenderer(dataHolder);
        }
    }
}
