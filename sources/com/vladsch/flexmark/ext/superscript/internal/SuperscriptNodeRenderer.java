package com.vladsch.flexmark.ext.superscript.internal;

import com.vladsch.flexmark.ext.superscript.Superscript;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/superscript/internal/SuperscriptNodeRenderer.class */
public class SuperscriptNodeRenderer implements NodeRenderer {
    private final String superscriptStyleHtmlOpen;
    private final String superscriptStyleHtmlClose;

    public SuperscriptNodeRenderer(DataHolder dataHolder) {
        this.superscriptStyleHtmlOpen = SuperscriptExtension.SUPERSCRIPT_STYLE_HTML_OPEN.get(dataHolder);
        this.superscriptStyleHtmlClose = SuperscriptExtension.SUPERSCRIPT_STYLE_HTML_CLOSE.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Superscript.class, this::render));
        return hashSet;
    }

    private void render(Superscript superscript, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.superscriptStyleHtmlOpen == null || this.superscriptStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.SUP_NODE);
            } else {
                htmlWriter.srcPos(superscript.getText()).withAttr().tag(FlexmarkHtmlConverter.SUP_NODE);
            }
            nodeRendererContext.renderChildren(superscript);
            htmlWriter.tag("/sup");
            return;
        }
        htmlWriter.raw((CharSequence) this.superscriptStyleHtmlOpen);
        nodeRendererContext.renderChildren(superscript);
        htmlWriter.raw((CharSequence) this.superscriptStyleHtmlClose);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/superscript/internal/SuperscriptNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new SuperscriptNodeRenderer(dataHolder);
        }
    }
}
