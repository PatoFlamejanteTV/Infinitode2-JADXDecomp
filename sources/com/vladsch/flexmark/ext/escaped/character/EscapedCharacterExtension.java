package com.vladsch.flexmark.ext.escaped.character;

import com.vladsch.flexmark.ext.escaped.character.internal.EscapedCharacterNodePostProcessor;
import com.vladsch.flexmark.ext.escaped.character.internal.EscapedCharacterNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/EscapedCharacterExtension.class */
public class EscapedCharacterExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private EscapedCharacterExtension() {
    }

    public static EscapedCharacterExtension create() {
        return new EscapedCharacterExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new EscapedCharacterNodePostProcessor.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new EscapedCharacterNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
