package com.vladsch.flexmark.ext.aside;

import com.vladsch.flexmark.ext.aside.internal.AsideBlockParser;
import com.vladsch.flexmark.ext.aside.internal.AsideNodeFormatter;
import com.vladsch.flexmark.ext.aside.internal.AsideNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/AsideExtension.class */
public class AsideExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> EXTEND_TO_BLANK_LINE = new DataKey<>("EXTEND_TO_BLANK_LINE", (DataKey) Parser.BLOCK_QUOTE_EXTEND_TO_BLANK_LINE);
    public static final DataKey<Boolean> IGNORE_BLANK_LINE = new DataKey<>("IGNORE_BLANK_LINE", (DataKey) Parser.BLOCK_QUOTE_IGNORE_BLANK_LINE);
    public static final DataKey<Boolean> ALLOW_LEADING_SPACE = new DataKey<>("ALLOW_LEADING_SPACE", (DataKey) Parser.BLOCK_QUOTE_ALLOW_LEADING_SPACE);
    public static final DataKey<Boolean> INTERRUPTS_PARAGRAPH = new DataKey<>("INTERRUPTS_PARAGRAPH", (DataKey) Parser.BLOCK_QUOTE_INTERRUPTS_PARAGRAPH);
    public static final DataKey<Boolean> INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("INTERRUPTS_ITEM_PARAGRAPH", (DataKey) Parser.BLOCK_QUOTE_INTERRUPTS_ITEM_PARAGRAPH);
    public static final DataKey<Boolean> WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH", (DataKey) Parser.BLOCK_QUOTE_WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH);

    private AsideExtension() {
    }

    public static AsideExtension create() {
        return new AsideExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new AsideNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new AsideBlockParser.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new AsideNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
