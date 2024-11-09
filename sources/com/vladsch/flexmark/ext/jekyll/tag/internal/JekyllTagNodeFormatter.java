package com.vladsch.flexmark.ext.jekyll.tag.internal;

import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagBlock;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.formatter.FormattingPhase;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.PhasedNodeFormatter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagNodeFormatter.class */
public class JekyllTagNodeFormatter implements PhasedNodeFormatter {
    private boolean embedIncludedContent;

    public JekyllTagNodeFormatter(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public Set<FormattingPhase> getFormattingPhases() {
        return Collections.singleton(FormattingPhase.COLLECT);
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase) {
        this.embedIncludedContent = JekyllTagExtension.EMBED_INCLUDED_CONTENT.get(document).booleanValue();
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(JekyllTagBlock.class, this::render), new NodeFormattingHandler(JekyllTag.class, this::render)));
    }

    private void render(JekyllTagBlock jekyllTagBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        nodeFormatterContext.renderChildren(jekyllTagBlock);
    }

    private void render(JekyllTag jekyllTag, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (this.embedIncludedContent) {
            nodeFormatterContext.renderChildren(jekyllTag);
            return;
        }
        if (!(jekyllTag.getParent() instanceof JekyllTagBlock)) {
            Node previous = jekyllTag.getPrevious();
            if (previous != null) {
                BasedSequence chars = previous.getChars();
                markdownWriter.pushOptions().preserveSpaces().append((CharSequence) chars.baseSubSequence(chars.getEndOffset(), jekyllTag.getStartOffset())).popOptions();
            } else {
                int startOfLine = jekyllTag.getBaseSequence().startOfLine(jekyllTag.getStartOffset());
                if (startOfLine < jekyllTag.getStartOffset()) {
                    markdownWriter.pushOptions().preserveSpaces().append((CharSequence) jekyllTag.baseSubSequence(startOfLine, jekyllTag.getStartOffset())).popOptions();
                }
            }
        }
        markdownWriter.append((CharSequence) jekyllTag.getChars());
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new JekyllTagNodeFormatter(dataHolder);
        }
    }
}
