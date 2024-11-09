package com.vladsch.flexmark.ext.anchorlink.internal;

import com.vladsch.flexmark.ext.anchorlink.AnchorLink;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/internal/AnchorLinkNodeRenderer.class */
public class AnchorLinkNodeRenderer implements NodeRenderer {
    private final AnchorLinkOptions options;

    public AnchorLinkNodeRenderer(DataHolder dataHolder) {
        this.options = new AnchorLinkOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(AnchorLink.class, this::render));
        return hashSet;
    }

    private void render(AnchorLink anchorLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            if (this.options.wrapText) {
                nodeRendererContext.renderChildren(anchorLink);
                return;
            }
            return;
        }
        String nodeId = nodeRendererContext.getNodeId(anchorLink.getParent());
        if (nodeId != null) {
            htmlWriter.attr("href", (CharSequence) ("#" + nodeId));
            if (this.options.setId) {
                htmlWriter.attr(Attribute.ID_ATTR, (CharSequence) nodeId);
            }
            if (this.options.setName) {
                htmlWriter.attr(Attribute.NAME_ATTR, (CharSequence) nodeId);
            }
            if (!this.options.anchorClass.isEmpty()) {
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.options.anchorClass);
            }
            if (!this.options.wrapText) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.A_NODE);
                if (!this.options.textPrefix.isEmpty()) {
                    htmlWriter.raw((CharSequence) this.options.textPrefix);
                }
                if (!this.options.textSuffix.isEmpty()) {
                    htmlWriter.raw((CharSequence) this.options.textSuffix);
                }
                htmlWriter.tag("/a");
                return;
            }
            htmlWriter.withAttr().tag(FlexmarkHtmlConverter.A_NODE, false, false, () -> {
                if (!this.options.textPrefix.isEmpty()) {
                    htmlWriter.raw((CharSequence) this.options.textPrefix);
                }
                nodeRendererContext.renderChildren(anchorLink);
                if (!this.options.textSuffix.isEmpty()) {
                    htmlWriter.raw((CharSequence) this.options.textSuffix);
                }
            });
            return;
        }
        if (this.options.wrapText) {
            nodeRendererContext.renderChildren(anchorLink);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/internal/AnchorLinkNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new AnchorLinkNodeRenderer(dataHolder);
        }
    }
}
