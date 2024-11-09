package com.vladsch.flexmark.ext.yaml.front.matter;

import com.vladsch.flexmark.ext.yaml.front.matter.internal.YamlFrontMatterBlockParser;
import com.vladsch.flexmark.ext.yaml.front.matter.internal.YamlFrontMatterNodeFormatter;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/YamlFrontMatterExtension.class */
public class YamlFrontMatterExtension implements Formatter.FormatterExtension, Parser.ParserExtension {
    private YamlFrontMatterExtension() {
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new YamlFrontMatterNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new YamlFrontMatterBlockParser.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    public static YamlFrontMatterExtension create() {
        return new YamlFrontMatterExtension();
    }
}
