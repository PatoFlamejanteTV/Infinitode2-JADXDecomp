package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Node;
import java.io.IOException;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/NodeFormatterSubContext.class */
public abstract class NodeFormatterSubContext implements NodeFormatterContext {
    protected final MarkdownWriter markdown;
    List<NodeFormattingHandler<?>> rendererList = null;
    int rendererIndex = -1;
    Node renderingNode = null;

    public NodeFormatterSubContext(MarkdownWriter markdownWriter) {
        this.markdown = markdownWriter;
    }

    public Node getRenderingNode() {
        return this.renderingNode;
    }

    public void setRenderingNode(Node node) {
        this.renderingNode = node;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
    public MarkdownWriter getMarkdown() {
        return this.markdown;
    }

    public void flushTo(Appendable appendable, int i) {
        flushTo(appendable, getFormatterOptions().maxBlankLines, i);
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
