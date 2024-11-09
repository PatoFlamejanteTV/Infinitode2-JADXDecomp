package com.vladsch.flexmark.ext.ins.internal;

import com.vladsch.flexmark.ext.ins.Ins;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/ins/internal/InsNodeRenderer.class */
public class InsNodeRenderer implements NodeRenderer {
    private final String insStyleHtmlOpen;
    private final String insStyleHtmlClose;

    public InsNodeRenderer(DataHolder dataHolder) {
        this.insStyleHtmlOpen = InsExtension.INS_STYLE_HTML_OPEN.get(dataHolder);
        this.insStyleHtmlClose = InsExtension.INS_STYLE_HTML_CLOSE.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Ins.class, this::render));
        return hashSet;
    }

    private void render(Ins ins, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.insStyleHtmlOpen == null || this.insStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.INS_NODE);
            } else {
                htmlWriter.srcPos(ins.getText()).withAttr().tag(FlexmarkHtmlConverter.INS_NODE);
            }
            nodeRendererContext.renderChildren(ins);
            htmlWriter.tag("/ins");
            return;
        }
        htmlWriter.raw((CharSequence) this.insStyleHtmlOpen);
        nodeRendererContext.renderChildren(ins);
        htmlWriter.raw((CharSequence) this.insStyleHtmlClose);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/ins/internal/InsNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new InsNodeRenderer(dataHolder);
        }
    }
}
