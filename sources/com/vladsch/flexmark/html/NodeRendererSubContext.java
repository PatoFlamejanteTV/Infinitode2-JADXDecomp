package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.util.ast.Node;
import java.io.IOException;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/NodeRendererSubContext.class */
public abstract class NodeRendererSubContext implements NodeRendererContext {
    final HtmlWriter htmlWriter;
    NodeRenderingHandlerWrapper renderingHandlerWrapper;
    Node renderingNode = null;
    int doNotRenderLinksNesting = 0;

    public NodeRendererSubContext(HtmlWriter htmlWriter) {
        this.htmlWriter = htmlWriter;
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
    public HtmlWriter getHtmlWriter() {
        return this.htmlWriter;
    }

    public void flushTo(Appendable appendable, int i) {
        flushTo(appendable, getHtmlOptions().maxBlankLines, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.vladsch.flexmark.util.html.HtmlAppendableBase] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Appendable] */
    public void flushTo(Appendable appendable, int i, int i2) {
        ?? line = this.htmlWriter.line();
        try {
            line = this.htmlWriter.appendTo(appendable, i, i2);
        } catch (IOException e) {
            line.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDoNotRenderLinksNesting() {
        return this.doNotRenderLinksNesting;
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
    public boolean isDoNotRenderLinks() {
        return this.doNotRenderLinksNesting != 0;
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
    public void doNotRenderLinks(boolean z) {
        if (z) {
            doNotRenderLinks();
        } else {
            doRenderLinks();
        }
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
    public void doNotRenderLinks() {
        this.doNotRenderLinksNesting++;
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
    public void doRenderLinks() {
        if (this.doNotRenderLinksNesting == 0) {
            throw new IllegalStateException("Not in do not render links context");
        }
        this.doNotRenderLinksNesting--;
    }
}
