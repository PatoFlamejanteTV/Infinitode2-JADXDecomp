package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.MutableAttributes;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesNodeRenderer.class */
public class AttributesNodeRenderer implements NodeRenderer {
    private final AttributesOptions myOptions;

    public AttributesNodeRenderer(DataHolder dataHolder) {
        this.myOptions = new AttributesOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(AttributesNode.class, (attributesNode, nodeRendererContext, htmlWriter) -> {
        }));
        hashSet.add(new NodeRenderingHandler(TextBase.class, (textBase, nodeRendererContext2, htmlWriter2) -> {
            if (this.myOptions.assignTextAttributes) {
                MutableAttributes extendRenderingNodeAttributes = nodeRendererContext2.extendRenderingNodeAttributes(AttributablePart.NODE, null);
                if (!extendRenderingNodeAttributes.isEmpty()) {
                    htmlWriter2.setAttributes((Attributes) extendRenderingNodeAttributes).withAttr().tag((CharSequence) FlexmarkHtmlConverter.SPAN_NODE);
                    nodeRendererContext2.delegateRender();
                    htmlWriter2.closeTag(FlexmarkHtmlConverter.SPAN_NODE);
                    return;
                }
            }
            nodeRendererContext2.delegateRender();
        }));
        return hashSet;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new AttributesNodeRenderer(dataHolder);
        }
    }
}
