package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.util.HeadingCollectingVisitor;
import com.vladsch.flexmark.ext.toc.SimTocBlock;
import com.vladsch.flexmark.ext.toc.SimTocContent;
import com.vladsch.flexmark.ext.toc.SimTocOption;
import com.vladsch.flexmark.ext.toc.SimTocOptionList;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocNodeRenderer.class */
public class SimTocNodeRenderer implements NodeRenderer {
    private final TocOptions options;

    public SimTocNodeRenderer(DataHolder dataHolder) {
        this.options = new TocOptions(dataHolder, true);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(SimTocBlock.class, this::render), new NodeRenderingHandler(SimTocContent.class, this::render), new NodeRenderingHandler(SimTocOptionList.class, this::render), new NodeRenderingHandler(SimTocOption.class, this::render)));
    }

    private void render(SimTocContent simTocContent, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(SimTocOptionList simTocOptionList, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(SimTocOption simTocOption, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(SimTocBlock simTocBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        ArrayList<Heading> collectAndGetHeadings = new HeadingCollectingVisitor().collectAndGetHeadings(nodeRendererContext.getDocument());
        if (collectAndGetHeadings != null) {
            TocOptions first = new SimTocOptionsParser().parseOption(simTocBlock.getStyle(), this.options, null).getFirst();
            if (simTocBlock.getTitle().isNotNull()) {
                first = first.withTitle(simTocBlock.getTitle().unescape());
            }
            renderTocHeaders(nodeRendererContext, htmlWriter, simTocBlock, collectAndGetHeadings, first);
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new SimTocNodeRenderer(dataHolder);
        }
    }
}
