package com.vladsch.flexmark.ext.yaml.front.matter.internal;

import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterBlock;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/internal/YamlFrontMatterNodeFormatter.class */
public class YamlFrontMatterNodeFormatter implements PhasedNodeFormatter {
    public YamlFrontMatterNodeFormatter(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public Set<FormattingPhase> getFormattingPhases() {
        return new HashSet(Collections.singleton(FormattingPhase.DOCUMENT_FIRST));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase) {
        if (formattingPhase == FormattingPhase.DOCUMENT_FIRST) {
            Node firstChild = document.getFirstChild();
            if (firstChild instanceof YamlFrontMatterBlock) {
                markdownWriter.openPreFormatted(false);
                markdownWriter.append((CharSequence) firstChild.getChars()).blankLine();
                markdownWriter.closePreFormatted();
            }
        }
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Collections.singletonList(new NodeFormattingHandler(YamlFrontMatterBlock.class, this::render)));
    }

    private void render(YamlFrontMatterBlock yamlFrontMatterBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/internal/YamlFrontMatterNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new YamlFrontMatterNodeFormatter(dataHolder);
        }
    }
}
