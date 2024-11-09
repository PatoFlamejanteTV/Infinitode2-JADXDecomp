package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.ext.media.tags.internal.MediaTagsNodePostProcessor;
import com.vladsch.flexmark.ext.media.tags.internal.MediaTagsNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/MediaTagsExtension.class */
public class MediaTagsExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private MediaTagsExtension() {
    }

    public static MediaTagsExtension create() {
        return new MediaTagsExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new MediaTagsNodePostProcessor.Factory(builder));
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
            builder.nodeRendererFactory(new MediaTagsNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
