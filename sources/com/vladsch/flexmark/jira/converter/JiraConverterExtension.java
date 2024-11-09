package com.vladsch.flexmark.jira.converter;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.jira.converter.internal.JiraConverterNodeRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/jira/converter/JiraConverterExtension.class */
public class JiraConverterExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private JiraConverterExtension() {
    }

    public static JiraConverterExtension create() {
        return new JiraConverterExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
        String str = HtmlRenderer.TYPE.get(mutableDataHolder);
        if (str.equals("HTML")) {
            mutableDataHolder.set((DataKey<DataKey<String>>) HtmlRenderer.TYPE, (DataKey<String>) "JIRA");
        } else if (!str.equals("JIRA")) {
            throw new IllegalStateException("Non HTML Renderer is already set to " + str);
        }
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new JiraConverterNodeRenderer.Factory());
            return;
        }
        throw new IllegalStateException("Jira Converter Extension used with non Jira Renderer " + str);
    }
}
