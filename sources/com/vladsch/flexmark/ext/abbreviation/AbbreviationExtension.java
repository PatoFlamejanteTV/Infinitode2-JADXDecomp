package com.vladsch.flexmark.ext.abbreviation;

import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationBlockParser;
import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationNodeFormatter;
import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationNodePostProcessor;
import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationNodeRenderer;
import com.vladsch.flexmark.ext.abbreviation.internal.AbbreviationRepository;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/AbbreviationExtension.class */
public class AbbreviationExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension, Parser.ReferenceHoldingExtension {
    public static final DataKey<KeepType> ABBREVIATIONS_KEEP = new DataKey<>("ABBREVIATIONS_KEEP", KeepType.FIRST);
    public static final DataKey<AbbreviationRepository> ABBREVIATIONS = new DataKey<>("ABBREVIATIONS", new AbbreviationRepository(null), AbbreviationRepository::new);
    public static final DataKey<Boolean> USE_LINKS = new DataKey<>("USE_LINKS", Boolean.FALSE);
    public static final DataKey<ElementPlacement> ABBREVIATIONS_PLACEMENT = new DataKey<>("ABBREVIATIONS_PLACEMENT", ElementPlacement.AS_IS);
    public static final DataKey<ElementPlacementSort> ABBREVIATIONS_SORT = new DataKey<>("ABBREVIATIONS_SORT", ElementPlacementSort.AS_IS);
    public static final DataKey<Boolean> MAKE_MERGED_ABBREVIATIONS_UNIQUE = new DataKey<>("MERGE_MAKE_ABBREVIATIONS_UNIQUE", Boolean.FALSE);

    public static AbbreviationExtension create() {
        return new AbbreviationExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new AbbreviationNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ReferenceHoldingExtension
    public boolean transferReferences(MutableDataHolder mutableDataHolder, DataHolder dataHolder) {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new AbbreviationBlockParser.Factory());
        builder.postProcessorFactory(new AbbreviationNodePostProcessor.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new AbbreviationNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
