package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.macros.MacroDefinitionBlock;
import com.vladsch.flexmark.ext.macros.MacroReference;
import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.NodeRepositoryFormatter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosNodeFormatter.class */
public class MacrosNodeFormatter extends NodeRepositoryFormatter<MacroDefinitionRepository, MacroDefinitionBlock, MacroReference> {
    public static final DataKey<Map<String, String>> MACROS_TRANSLATION_MAP = new DataKey<>("MACROS_TRANSLATION_MAP", new HashMap());
    public static final DataKey<Map<String, String>> MACROS_UNIQUIFICATION_MAP = new DataKey<>("MACROS_UNIQUIFICATION_MAP", new HashMap());
    private final MacroFormatOptions options;

    public MacrosNodeFormatter(DataHolder dataHolder) {
        super(dataHolder, MACROS_TRANSLATION_MAP, MACROS_UNIQUIFICATION_MAP);
        this.options = new MacroFormatOptions(dataHolder);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public MacroDefinitionRepository getRepository(DataHolder dataHolder) {
        return MacrosExtension.MACRO_DEFINITIONS.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacement getReferencePlacement() {
        return this.options.macrosPlacement;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacementSort getReferenceSort() {
        return this.options.macrosSort;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public void renderReferenceBlock(MacroDefinitionBlock macroDefinitionBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine().append((CharSequence) ">>>").append((CharSequence) transformReferenceId(macroDefinitionBlock.getName().toString(), nodeFormatterContext)).line();
        Node firstChild = macroDefinitionBlock.getFirstChild();
        if ((firstChild instanceof Paragraph) && firstChild == macroDefinitionBlock.getLastChild()) {
            nodeFormatterContext.renderChildren(firstChild);
        } else {
            nodeFormatterContext.renderChildren(macroDefinitionBlock);
        }
        markdownWriter.line().append((CharSequence) "<<<").blankLine();
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(MacroReference.class, this::render), new NodeFormattingHandler(MacroDefinitionBlock.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        if (this.options.macrosPlacement.isNoChange() || !this.options.macrosSort.isUnused()) {
            return null;
        }
        return new HashSet(Arrays.asList(MacroReference.class));
    }

    private void render(MacroDefinitionBlock macroDefinitionBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderReference(macroDefinitionBlock, nodeFormatterContext, markdownWriter);
    }

    private void render(MacroReference macroReference, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append("<<<");
        if (nodeFormatterContext.isTransformingText()) {
            String transformReferenceId = transformReferenceId(macroReference.getText().toString(), nodeFormatterContext);
            nodeFormatterContext.nonTranslatingSpan((nodeFormatterContext2, markdownWriter2) -> {
                markdownWriter2.append((CharSequence) transformReferenceId);
            });
        } else {
            markdownWriter.append((CharSequence) macroReference.getText());
        }
        markdownWriter.append(">>>");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new MacrosNodeFormatter(dataHolder);
        }
    }
}
