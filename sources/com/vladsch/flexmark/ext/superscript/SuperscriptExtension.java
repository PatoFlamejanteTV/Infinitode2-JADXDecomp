package com.vladsch.flexmark.ext.superscript;

import com.vladsch.flexmark.ext.superscript.internal.SuperscriptDelimiterProcessor;
import com.vladsch.flexmark.ext.superscript.internal.SuperscriptJiraRenderer;
import com.vladsch.flexmark.ext.superscript.internal.SuperscriptNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/superscript/SuperscriptExtension.class */
public class SuperscriptExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final NullableDataKey<String> SUPERSCRIPT_STYLE_HTML_OPEN = new NullableDataKey<>("SUPERSCRIPT_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> SUPERSCRIPT_STYLE_HTML_CLOSE = new NullableDataKey<>("SUPERSCRIPT_STYLE_HTML_CLOSE");

    private SuperscriptExtension() {
    }

    public static SuperscriptExtension create() {
        return new SuperscriptExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customDelimiterProcessor(new SuperscriptDelimiterProcessor());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new SuperscriptNodeRenderer.Factory());
        } else if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new SuperscriptJiraRenderer.Factory());
        }
    }
}
