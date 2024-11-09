package com.vladsch.flexmark.ext.macros;

import com.vladsch.flexmark.ext.macros.internal.MacroDefinitionBlockParser;
import com.vladsch.flexmark.ext.macros.internal.MacroDefinitionRepository;
import com.vladsch.flexmark.ext.macros.internal.MacrosInlineParserExtension;
import com.vladsch.flexmark.ext.macros.internal.MacrosNodeFormatter;
import com.vladsch.flexmark.ext.macros.internal.MacrosNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/MacrosExtension.class */
public class MacrosExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension, Parser.ReferenceHoldingExtension {
    public static final DataKey<KeepType> MACRO_DEFINITIONS_KEEP = new DataKey<>("MACRO_DEFINITIONS_KEEP", KeepType.FIRST);
    public static final DataKey<MacroDefinitionRepository> MACRO_DEFINITIONS = new DataKey<>("MACRO_DEFINITIONS", new MacroDefinitionRepository(null), MacroDefinitionRepository::new);
    public static final DataKey<ElementPlacement> MACRO_DEFINITIONS_PLACEMENT = new DataKey<>("MACRO_DEFINITIONS_PLACEMENT", ElementPlacement.AS_IS);
    public static final DataKey<ElementPlacementSort> MACRO_DEFINITIONS_SORT = new DataKey<>("MACRO_DEFINITIONS_SORT", ElementPlacementSort.AS_IS);
    public static final DataKey<Boolean> SOURCE_WRAP_MACRO_REFERENCES = new DataKey<>("SOURCE_WRAP_MACRO_REFERENCES", Boolean.FALSE);

    private MacrosExtension() {
    }

    public static MacrosExtension create() {
        return new MacrosExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ReferenceHoldingExtension
    public boolean transferReferences(MutableDataHolder mutableDataHolder, DataHolder dataHolder) {
        if (dataHolder.contains(MACRO_DEFINITIONS)) {
            return Parser.transferReferences(MACRO_DEFINITIONS.get(mutableDataHolder), MACRO_DEFINITIONS.get(dataHolder), MACRO_DEFINITIONS_KEEP.get(mutableDataHolder) == KeepType.FIRST);
        }
        return false;
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new MacrosNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new MacroDefinitionBlockParser.Factory());
        builder.customInlineParserExtensionFactory(new MacrosInlineParserExtension.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new MacrosNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
