package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.definition.DefinitionItem;
import com.vladsch.flexmark.ext.definition.DefinitionList;
import com.vladsch.flexmark.ext.definition.DefinitionTerm;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionNodeFormatter.class */
public class DefinitionNodeFormatter implements NodeFormatter {
    private final DefinitionFormatOptions options;
    private final ListOptions listOptions;

    public DefinitionNodeFormatter(DataHolder dataHolder) {
        this.options = new DefinitionFormatOptions(dataHolder);
        this.listOptions = ListOptions.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(DefinitionList.class, this::render), new NodeFormattingHandler(DefinitionTerm.class, this::render), new NodeFormattingHandler(DefinitionItem.class, this::render)));
    }

    private void render(DefinitionList definitionList, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        nodeFormatterContext.renderChildren(definitionList);
    }

    private void render(DefinitionTerm definitionTerm, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        nodeFormatterContext.renderChildren(definitionTerm);
    }

    private void render(DefinitionItem definitionItem, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        BasedSequence prefixOf = definitionItem.getChars().prefixOf(definitionItem.getFirstChild().getChars());
        BasedSequence subSequence = prefixOf.subSequence(0, 1);
        BasedSequence subSequence2 = prefixOf.subSequence(1);
        if (this.options.markerSpaces > 0 && subSequence2.length() != this.options.markerSpaces) {
            subSequence2 = BasedSequence.of(RepeatedSequence.repeatOf(' ', this.options.markerSpaces));
        }
        switch (this.options.markerType) {
            case COLON:
                subSequence = BasedSequence.of(":").subSequence(0, ":".length());
                break;
            case TILDE:
                subSequence = BasedSequence.of("~").subSequence(0, "~".length());
                break;
        }
        markdownWriter.line().append((CharSequence) subSequence).append((CharSequence) subSequence2);
        markdownWriter.pushPrefix().addPrefix(RepeatedSequence.ofSpaces(nodeFormatterContext.getFormatterOptions().itemContentIndent ? subSequence.length() + subSequence2.length() : this.listOptions.getItemIndent()));
        nodeFormatterContext.renderChildren(definitionItem);
        markdownWriter.popPrefix();
        if (!Parser.BLANK_LINES_IN_AST.get(nodeFormatterContext.getOptions()).booleanValue()) {
            Node lastChild = definitionItem.getLastChild();
            if ((lastChild instanceof Paragraph) && ((Paragraph) lastChild).isTrailingBlankLine()) {
                markdownWriter.blankLine();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new DefinitionNodeFormatter(dataHolder);
        }
    }
}
