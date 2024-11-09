package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ext.definition.DefinitionItem;
import com.vladsch.flexmark.ext.definition.DefinitionList;
import com.vladsch.flexmark.ext.definition.DefinitionTerm;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.CoreNodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionNodeRenderer.class */
public class DefinitionNodeRenderer implements NodeRenderer {
    private final ListOptions listOptions;

    public DefinitionNodeRenderer(DataHolder dataHolder) {
        this.listOptions = ListOptions.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(DefinitionList.class, this::render));
        hashSet.add(new NodeRenderingHandler(DefinitionTerm.class, this::render));
        hashSet.add(new NodeRenderingHandler(DefinitionItem.class, this::render));
        return hashSet;
    }

    private void render(DefinitionList definitionList, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().tag(FlexmarkHtmlConverter.DL_NODE).indent();
        nodeRendererContext.renderChildren(definitionList);
        htmlWriter.unIndent().tag((CharSequence) "/dl");
    }

    private void render(DefinitionTerm definitionTerm, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (definitionTerm.getFirstChild() != null) {
            htmlWriter.srcPosWithEOL(definitionTerm.getChars()).withAttr(CoreNodeRenderer.TIGHT_LIST_ITEM).withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.DT_NODE, () -> {
                htmlWriter.text((CharSequence) definitionTerm.getMarkerSuffix().unescape());
                nodeRendererContext.renderChildren(definitionTerm);
            });
        }
    }

    private void render(DefinitionItem definitionItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.listOptions.isTightListItem(definitionItem)) {
            htmlWriter.srcPosWithEOL(definitionItem.getChars()).withAttr(CoreNodeRenderer.TIGHT_LIST_ITEM).withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.DD_NODE, () -> {
                htmlWriter.text((CharSequence) definitionItem.getMarkerSuffix().unescape());
                nodeRendererContext.renderChildren(definitionItem);
            });
        } else {
            htmlWriter.srcPosWithEOL(definitionItem.getChars()).withAttr(CoreNodeRenderer.LOOSE_LIST_ITEM).tagIndent(FlexmarkHtmlConverter.DD_NODE, () -> {
                htmlWriter.text((CharSequence) definitionItem.getMarkerSuffix().unescape());
                nodeRendererContext.renderChildren(definitionItem);
            });
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new DefinitionNodeRenderer(dataHolder);
        }
    }
}
