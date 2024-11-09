package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ext.abbreviation.Abbreviation;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationBlock;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationNodeRenderer.class */
public class AbbreviationNodeRenderer implements NodeRenderer {
    private final AbbreviationOptions options;

    public AbbreviationNodeRenderer(DataHolder dataHolder) {
        this.options = new AbbreviationOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(Abbreviation.class, this::render), new NodeRenderingHandler(AbbreviationBlock.class, this::render)));
    }

    private void render(AbbreviationBlock abbreviationBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(Abbreviation abbreviation, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        CharSequence charSequence;
        String unescape = abbreviation.getChars().unescape();
        BasedSequence abbreviation2 = abbreviation.getAbbreviation();
        if (this.options.useLinks) {
            htmlWriter.attr("href", "#");
            charSequence = FlexmarkHtmlConverter.A_NODE;
        } else {
            charSequence = FlexmarkHtmlConverter.ABBR_NODE;
        }
        htmlWriter.attr(Attribute.TITLE_ATTR, (CharSequence) abbreviation2);
        htmlWriter.srcPos(abbreviation.getChars()).withAttr().tag(charSequence);
        htmlWriter.text((CharSequence) unescape);
        htmlWriter.closeTag(charSequence);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new AbbreviationNodeRenderer(dataHolder);
        }
    }
}
