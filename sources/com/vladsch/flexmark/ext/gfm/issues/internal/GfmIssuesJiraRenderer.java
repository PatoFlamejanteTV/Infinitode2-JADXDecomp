package com.vladsch.flexmark.ext.gfm.issues.internal;

import com.vladsch.flexmark.ext.gfm.issues.GfmIssue;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/internal/GfmIssuesJiraRenderer.class */
public class GfmIssuesJiraRenderer implements NodeRenderer {
    private final GfmIssuesOptions options;

    public GfmIssuesJiraRenderer(DataHolder dataHolder) {
        this.options = new GfmIssuesOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Collections.singletonList(new NodeRenderingHandler(GfmIssue.class, this::render)));
    }

    private void render(GfmIssue gfmIssue, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.raw((CharSequence) gfmIssue.getChars());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.options.gitHubIssuesUrlRoot).append(this.options.gitHubIssueUrlPrefix).append((CharSequence) gfmIssue.getText()).append(this.options.gitHubIssueUrlSuffix);
        htmlWriter.raw("[");
        htmlWriter.raw((CharSequence) gfmIssue.getChars());
        htmlWriter.raw("|").raw((CharSequence) sb.toString()).raw((CharSequence) "]");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/internal/GfmIssuesJiraRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new GfmIssuesJiraRenderer(dataHolder);
        }
    }
}
