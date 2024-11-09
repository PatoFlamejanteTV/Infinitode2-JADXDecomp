package com.vladsch.flexmark.youtrack.converter;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.youtrack.converter.internal.YouTrackConverterNodeRenderer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/youtrack/converter/YouTrackConverterExtension.class */
public class YouTrackConverterExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private YouTrackConverterExtension() {
    }

    public static YouTrackConverterExtension create() {
        return new YouTrackConverterExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
        String str = HtmlRenderer.TYPE.get(mutableDataHolder);
        if (str.equals("HTML")) {
            HtmlRenderer.addRenderTypeEquivalence(mutableDataHolder, "YOUTRACK", "JIRA");
            mutableDataHolder.set((DataKey<DataKey<String>>) HtmlRenderer.TYPE, (DataKey<String>) "YOUTRACK");
        } else if (!str.equals("YOUTRACK")) {
            throw new IllegalStateException("Non HTML Renderer is already set to " + str);
        }
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("YOUTRACK")) {
            builder.nodeRendererFactory(new YouTrackConverterNodeRenderer.Factory());
            return;
        }
        throw new IllegalStateException("YouTrack Converter Extension used with non YouTrack Renderer " + str);
    }
}
