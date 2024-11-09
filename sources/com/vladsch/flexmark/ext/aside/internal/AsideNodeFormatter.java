package com.vladsch.flexmark.ext.aside.internal;

import com.vladsch.flexmark.ext.aside.AsideBlock;
import com.vladsch.flexmark.formatter.FormatterUtils;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideNodeFormatter.class */
public class AsideNodeFormatter implements NodeFormatter {
    public AsideNodeFormatter(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public char getBlockQuoteLikePrefixChar() {
        return '|';
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeFormattingHandler(AsideBlock.class, this::render));
        return hashSet;
    }

    private void render(AsideBlock asideBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterUtils.renderBlockQuoteLike(asideBlock, nodeFormatterContext, markdownWriter);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new AsideNodeFormatter(dataHolder);
        }
    }
}
