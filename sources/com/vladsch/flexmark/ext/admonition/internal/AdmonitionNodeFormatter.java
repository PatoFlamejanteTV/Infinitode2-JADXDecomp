package com.vladsch.flexmark.ext.admonition.internal;

import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionNodeFormatter.class */
public class AdmonitionNodeFormatter implements NodeFormatter {
    private final AdmonitionOptions options;

    public AdmonitionNodeFormatter(DataHolder dataHolder) {
        this.options = new AdmonitionOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Collections.singletonList(new NodeFormattingHandler(AdmonitionBlock.class, this::render)));
    }

    private void render(AdmonitionBlock admonitionBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine();
        markdownWriter.append((CharSequence) admonitionBlock.getOpeningMarker()).append(' ');
        markdownWriter.appendNonTranslating(admonitionBlock.getInfo());
        if (admonitionBlock.getTitle().isNotNull()) {
            markdownWriter.append(' ').append('\"').appendTranslating(admonitionBlock.getTitle()).append('\"');
        }
        markdownWriter.line();
        markdownWriter.pushPrefix().addPrefix((CharSequence) RepeatedSequence.repeatOf(SequenceUtils.SPACE, this.options.contentIndent).toString());
        nodeFormatterContext.renderChildren(admonitionBlock);
        markdownWriter.blankLine();
        markdownWriter.popPrefix();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new AdmonitionNodeFormatter(dataHolder);
        }
    }
}
