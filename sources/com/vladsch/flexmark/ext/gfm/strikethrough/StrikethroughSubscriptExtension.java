package com.vladsch.flexmark.ext.gfm.strikethrough;

import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughJiraRenderer;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughNodeRenderer;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughSubscriptDelimiterProcessor;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughYouTrackRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/StrikethroughSubscriptExtension.class */
public class StrikethroughSubscriptExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final NullableDataKey<String> STRIKETHROUGH_STYLE_HTML_OPEN = new NullableDataKey<>("STRIKETHROUGH_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> STRIKETHROUGH_STYLE_HTML_CLOSE = new NullableDataKey<>("STRIKETHROUGH_STYLE_HTML_CLOSE");
    public static final NullableDataKey<String> SUBSCRIPT_STYLE_HTML_OPEN = new NullableDataKey<>("SUBSCRIPT_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> SUBSCRIPT_STYLE_HTML_CLOSE = new NullableDataKey<>("SUBSCRIPT_STYLE_HTML_CLOSE");

    private StrikethroughSubscriptExtension() {
    }

    public static StrikethroughSubscriptExtension create() {
        return new StrikethroughSubscriptExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customDelimiterProcessor(new StrikethroughSubscriptDelimiterProcessor());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new StrikethroughNodeRenderer.Factory());
        } else if (builder.isRendererType("YOUTRACK")) {
            builder.nodeRendererFactory(new StrikethroughYouTrackRenderer.Factory());
        } else if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new StrikethroughJiraRenderer.Factory());
        }
    }
}
