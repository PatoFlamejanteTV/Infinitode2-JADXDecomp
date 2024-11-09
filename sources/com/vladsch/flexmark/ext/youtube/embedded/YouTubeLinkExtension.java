package com.vladsch.flexmark.ext.youtube.embedded;

import com.vladsch.flexmark.ext.youtube.embedded.internal.YouTubeLinkNodePostProcessor;
import com.vladsch.flexmark.ext.youtube.embedded.internal.YouTubeLinkNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/YouTubeLinkExtension.class */
public class YouTubeLinkExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private YouTubeLinkExtension() {
    }

    public static YouTubeLinkExtension create() {
        return new YouTubeLinkExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new YouTubeLinkNodePostProcessor.Factory(builder));
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new YouTubeLinkNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
