package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.util.HeadingCollectingVisitor;
import com.vladsch.flexmark.ext.toc.SimTocBlock;
import com.vladsch.flexmark.ext.toc.SimTocContent;
import com.vladsch.flexmark.ext.toc.TocUtils;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.MarkdownTable;
import com.vladsch.flexmark.util.misc.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocNodeFormatter.class */
public class SimTocNodeFormatter implements NodeFormatter {
    private final TocOptions options;
    private final TocFormatOptions formatOptions;
    private MarkdownTable myTable;

    public SimTocNodeFormatter(DataHolder dataHolder) {
        this.options = new TocOptions(dataHolder, true);
        this.formatOptions = new TocFormatOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(SimTocBlock.class, this::render), new NodeFormattingHandler(SimTocContent.class, this::render)));
    }

    private void render(SimTocBlock simTocBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        switch (this.formatOptions.updateOnFormat) {
            case REMOVE:
                markdownWriter.append((CharSequence) TocUtils.getSimTocPrefix(this.options, this.options));
                if (this.options.isBlankLineSpacer) {
                    markdownWriter.blankLine();
                    return;
                }
                return;
            case UPDATE:
                ArrayList<Heading> collectAndGetHeadings = new HeadingCollectingVisitor().collectAndGetHeadings(nodeFormatterContext.getDocument());
                if (collectAndGetHeadings != null) {
                    TocOptions first = new SimTocOptionsParser().parseOption(simTocBlock.getStyle(), this.options, null).getFirst();
                    if (simTocBlock.getTitle().isNotNull()) {
                        first = first.withTitle(simTocBlock.getTitle().unescape());
                    }
                    markdownWriter.append((CharSequence) TocUtils.getSimTocPrefix(first, this.options));
                    if (first.isBlankLineSpacer) {
                        markdownWriter.blankLine();
                    }
                    renderTocHeaders(markdownWriter, collectAndGetHeadings, first);
                    return;
                }
                return;
            case AS_IS:
                markdownWriter.openPreFormatted(false).append((CharSequence) simTocBlock.getChars()).closePreFormatted();
                return;
            default:
                throw new IllegalStateException(this.formatOptions.updateOnFormat.toString() + " is not implemented");
        }
    }

    private void renderTocHeaders(MarkdownWriter markdownWriter, List<Heading> list, TocOptions tocOptions) {
        Pair<List<Heading>, List<String>> markdownHeaderTexts = TocUtils.markdownHeaderTexts(TocUtils.filteredHeadings(list, tocOptions), tocOptions);
        TocUtils.renderTocContent(markdownWriter, tocOptions, this.options, markdownHeaderTexts.getFirst(), markdownHeaderTexts.getSecond());
    }

    private void render(SimTocContent simTocContent, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new SimTocNodeFormatter(dataHolder);
        }
    }
}
