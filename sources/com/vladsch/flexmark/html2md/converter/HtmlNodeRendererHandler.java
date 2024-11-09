package com.vladsch.flexmark.html2md.converter;

import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlNodeRendererHandler.class */
public class HtmlNodeRendererHandler<N extends Node> implements CustomHtmlNodeRenderer<N> {
    protected final String myTagName;
    protected final Class<? extends N> myClass;
    protected final CustomHtmlNodeRenderer<N> myAdapter;

    public HtmlNodeRendererHandler(String str, Class<? extends N> cls, CustomHtmlNodeRenderer<N> customHtmlNodeRenderer) {
        this.myTagName = str;
        this.myClass = cls;
        this.myAdapter = customHtmlNodeRenderer;
    }

    @Override // com.vladsch.flexmark.html2md.converter.CustomHtmlNodeRenderer
    public void render(Node node, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        this.myAdapter.render(node, htmlNodeConverterContext, htmlMarkdownWriter);
    }

    public Class<? extends N> getNodeType() {
        return this.myClass;
    }

    public String getTagName() {
        return this.myTagName;
    }

    public CustomHtmlNodeRenderer<N> getNodeAdapter() {
        return this.myAdapter;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HtmlNodeRendererHandler htmlNodeRendererHandler = (HtmlNodeRendererHandler) obj;
        return this.myClass == htmlNodeRendererHandler.myClass && this.myAdapter == htmlNodeRendererHandler.myAdapter;
    }

    public int hashCode() {
        return (this.myClass.hashCode() * 31) + this.myAdapter.hashCode();
    }
}
