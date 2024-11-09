package com.vladsch.flexmark.html2md.converter;

import java.io.IOException;
import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlNodeConverterSubContext.class */
public abstract class HtmlNodeConverterSubContext implements HtmlNodeConverterContext {
    protected final HtmlMarkdownWriter markdown;
    NodeRenderingHandlerWrapper<?> renderingHandlerWrapper;
    Node myRenderingNode = null;

    public HtmlNodeConverterSubContext(HtmlMarkdownWriter htmlMarkdownWriter) {
        this.markdown = htmlMarkdownWriter;
        this.markdown.setContext(this);
    }

    public Node getRenderingNode() {
        return this.myRenderingNode;
    }

    public void setRenderingNode(Node node) {
        this.myRenderingNode = node;
    }

    @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
    public HtmlMarkdownWriter getMarkdown() {
        return this.markdown;
    }

    public void flushTo(Appendable appendable, int i) {
        flushTo(appendable, getHtmlConverterOptions().maxBlankLines, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.vladsch.flexmark.util.format.MarkdownWriterBase] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Appendable] */
    public void flushTo(Appendable appendable, int i, int i2) {
        ?? line = this.markdown.line();
        try {
            line = this.markdown.appendTo(appendable, i, i2);
        } catch (IOException e) {
            line.printStackTrace();
        }
    }
}
