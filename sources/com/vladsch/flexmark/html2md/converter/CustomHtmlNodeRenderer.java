package com.vladsch.flexmark.html2md.converter;

import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/CustomHtmlNodeRenderer.class */
public interface CustomHtmlNodeRenderer<N extends Node> {
    void render(N n, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter);
}
