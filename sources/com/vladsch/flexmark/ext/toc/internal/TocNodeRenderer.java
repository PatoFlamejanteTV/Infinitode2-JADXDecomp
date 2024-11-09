package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.util.HeadingCollectingVisitor;
import com.vladsch.flexmark.ext.toc.TocBlock;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.toc.TocUtils;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.Paired;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocNodeRenderer.class */
public class TocNodeRenderer implements NodeRenderer {
    private final TocOptions options;
    private final boolean haveTitle;

    public TocNodeRenderer(DataHolder dataHolder) {
        this.haveTitle = dataHolder != null && dataHolder.contains(TocExtension.TITLE);
        this.options = new TocOptions(dataHolder, false);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(TocBlock.class, this::render));
        return hashSet;
    }

    private void render(TocBlock tocBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        ArrayList<Heading> collectAndGetHeadings = new HeadingCollectingVisitor().collectAndGetHeadings(nodeRendererContext.getDocument());
        if (collectAndGetHeadings != null) {
            renderTocHeaders(nodeRendererContext, htmlWriter, tocBlock, collectAndGetHeadings, new TocOptionsParser().parseOption(tocBlock.getStyle(), this.haveTitle ? this.options : this.options.withTitle(""), null).getFirst());
        }
    }

    private void renderTocHeaders(NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, Node node, List<Heading> list, TocOptions tocOptions) {
        Paired<List<Heading>, List<String>> htmlHeadingTexts = TocUtils.htmlHeadingTexts(nodeRendererContext, TocUtils.filteredHeadings(list, tocOptions), tocOptions);
        TocUtils.renderHtmlToc(htmlWriter, nodeRendererContext.getHtmlOptions().sourcePositionAttribute.isEmpty() ? BasedSequence.NULL : node.getChars(), (List) htmlHeadingTexts.getFirst().stream().map((v0) -> {
            return v0.getLevel();
        }).collect(Collectors.toList()), htmlHeadingTexts.getSecond(), (List) htmlHeadingTexts.getFirst().stream().map((v0) -> {
            return v0.getAnchorRefId();
        }).collect(Collectors.toList()), tocOptions);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new TocNodeRenderer(dataHolder);
        }
    }
}
