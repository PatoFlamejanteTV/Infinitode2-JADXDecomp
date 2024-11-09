package com.vladsch.flexmark.ext.jekyll.front.matter;

import com.vladsch.flexmark.ext.jekyll.front.matter.internal.JekyllFrontMatterBlockParser;
import com.vladsch.flexmark.ext.jekyll.front.matter.internal.JekyllFrontMatterNodeFormatter;
import com.vladsch.flexmark.ext.jekyll.front.matter.internal.JekyllFrontMatterNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/front/matter/JekyllFrontMatterExtension.class */
public class JekyllFrontMatterExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private JekyllFrontMatterExtension() {
    }

    public static JekyllFrontMatterExtension create() {
        return new JekyllFrontMatterExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new JekyllFrontMatterNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new JekyllFrontMatterBlockParser.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new JekyllFrontMatterNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
