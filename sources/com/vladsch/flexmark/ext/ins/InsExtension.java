package com.vladsch.flexmark.ext.ins;

import com.vladsch.flexmark.ext.ins.internal.InsDelimiterProcessor;
import com.vladsch.flexmark.ext.ins.internal.InsJiraRenderer;
import com.vladsch.flexmark.ext.ins.internal.InsNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/ins/InsExtension.class */
public class InsExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final NullableDataKey<String> INS_STYLE_HTML_OPEN = new NullableDataKey<>("INS_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> INS_STYLE_HTML_CLOSE = new NullableDataKey<>("INS_STYLE_HTML_CLOSE");

    private InsExtension() {
    }

    public static InsExtension create() {
        return new InsExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customDelimiterProcessor(new InsDelimiterProcessor());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new InsNodeRenderer.Factory());
        } else if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new InsJiraRenderer.Factory());
        }
    }
}
