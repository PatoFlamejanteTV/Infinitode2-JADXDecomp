package com.vladsch.flexmark.ext.definition;

import com.vladsch.flexmark.ext.definition.internal.DefinitionItemBlockParser;
import com.vladsch.flexmark.ext.definition.internal.DefinitionListBlockPreProcessor;
import com.vladsch.flexmark.ext.definition.internal.DefinitionListItemBlockPreProcessor;
import com.vladsch.flexmark.ext.definition.internal.DefinitionNodeFormatter;
import com.vladsch.flexmark.ext.definition.internal.DefinitionNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.format.options.DefinitionMarker;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/DefinitionExtension.class */
public class DefinitionExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> COLON_MARKER = new DataKey<>("COLON_MARKER", Boolean.TRUE);
    public static final DataKey<Integer> MARKER_SPACES = new DataKey<>("MARKER_SPACE", 1);
    public static final DataKey<Boolean> TILDE_MARKER = new DataKey<>("TILDE_MARKER", Boolean.TRUE);
    public static final DataKey<Boolean> DOUBLE_BLANK_LINE_BREAKS_LIST = new DataKey<>("DOUBLE_BLANK_LINE_BREAKS_LIST", Boolean.FALSE);
    public static final DataKey<Integer> FORMAT_MARKER_SPACES = new DataKey<>("MARKER_SPACE", 3);
    public static final DataKey<DefinitionMarker> FORMAT_MARKER_TYPE = new DataKey<>("FORMAT_MARKER_TYPE", DefinitionMarker.ANY);

    private DefinitionExtension() {
    }

    public static DefinitionExtension create() {
        return new DefinitionExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new DefinitionNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new DefinitionItemBlockParser.Factory());
        builder.blockPreProcessorFactory(new DefinitionListItemBlockPreProcessor.Factory());
        builder.blockPreProcessorFactory(new DefinitionListBlockPreProcessor.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new DefinitionNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
