package com.vladsch.flexmark.ext.typographic;

import com.vladsch.flexmark.ext.typographic.internal.AngleQuoteDelimiterProcessor;
import com.vladsch.flexmark.ext.typographic.internal.DoubleQuoteDelimiterProcessor;
import com.vladsch.flexmark.ext.typographic.internal.SingleQuoteDelimiterProcessor;
import com.vladsch.flexmark.ext.typographic.internal.SmartsInlineParser;
import com.vladsch.flexmark.ext.typographic.internal.TypographicNodeRenderer;
import com.vladsch.flexmark.ext.typographic.internal.TypographicOptions;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/TypographicExtension.class */
public class TypographicExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> ENABLE_QUOTES = new DataKey<>("ENABLE_QUOTES", Boolean.TRUE);
    public static final DataKey<Boolean> ENABLE_SMARTS = new DataKey<>("ENABLE_SMARTS", Boolean.TRUE);
    public static final DataKey<String> ANGLE_QUOTE_CLOSE = new DataKey<>("ANGLE_QUOTE_CLOSE", "&raquo;");
    public static final DataKey<String> ANGLE_QUOTE_OPEN = new DataKey<>("ANGLE_QUOTE_OPEN", "&laquo;");
    public static final NullableDataKey<String> ANGLE_QUOTE_UNMATCHED = new NullableDataKey<>("ANGLE_QUOTE_UNMATCHED");
    public static final DataKey<String> DOUBLE_QUOTE_CLOSE = new DataKey<>("DOUBLE_QUOTE_CLOSE", "&rdquo;");
    public static final DataKey<String> DOUBLE_QUOTE_OPEN = new DataKey<>("DOUBLE_QUOTE_OPEN", "&ldquo;");
    public static final NullableDataKey<String> DOUBLE_QUOTE_UNMATCHED = new NullableDataKey<>("DOUBLE_QUOTE_UNMATCHED");
    public static final DataKey<String> ELLIPSIS = new DataKey<>("ELLIPSIS", "&hellip;");
    public static final DataKey<String> ELLIPSIS_SPACED = new DataKey<>("ELLIPSIS_SPACED", "&hellip;");
    public static final DataKey<String> EM_DASH = new DataKey<>("EM_DASH", "&mdash;");
    public static final DataKey<String> EN_DASH = new DataKey<>("EN_DASH", "&ndash;");
    public static final DataKey<String> SINGLE_QUOTE_CLOSE = new DataKey<>("SINGLE_QUOTE_CLOSE", "&rsquo;");
    public static final DataKey<String> SINGLE_QUOTE_OPEN = new DataKey<>("SINGLE_QUOTE_OPEN", "&lsquo;");
    public static final DataKey<String> SINGLE_QUOTE_UNMATCHED = new DataKey<>("SINGLE_QUOTE_UNMATCHED", "&rsquo;");

    private TypographicExtension() {
    }

    public static TypographicExtension create() {
        return new TypographicExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        if (ENABLE_QUOTES.get(builder).booleanValue()) {
            TypographicOptions typographicOptions = new TypographicOptions(builder);
            builder.customDelimiterProcessor(new AngleQuoteDelimiterProcessor(typographicOptions));
            builder.customDelimiterProcessor(new SingleQuoteDelimiterProcessor(typographicOptions));
            builder.customDelimiterProcessor(new DoubleQuoteDelimiterProcessor(typographicOptions));
        }
        if (ENABLE_SMARTS.get(builder).booleanValue()) {
            builder.customInlineParserExtensionFactory(new SmartsInlineParser.Factory());
        }
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML") || builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new TypographicNodeRenderer.Factory());
        }
    }
}
