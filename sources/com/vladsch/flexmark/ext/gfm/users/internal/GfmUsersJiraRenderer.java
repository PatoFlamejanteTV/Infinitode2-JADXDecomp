package com.vladsch.flexmark.ext.gfm.users.internal;

import com.vladsch.flexmark.ext.gfm.users.GfmUser;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersJiraRenderer.class */
public class GfmUsersJiraRenderer implements NodeRenderer {
    private final GfmUsersOptions options;

    public GfmUsersJiraRenderer(DataHolder dataHolder) {
        this.options = new GfmUsersOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Collections.singletonList(new NodeRenderingHandler(GfmUser.class, this::render)));
    }

    private void render(GfmUser gfmUser, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.raw((CharSequence) gfmUser.getChars());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.options.gitHubIssuesUrlRoot).append(this.options.gitHubIssueUrlPrefix).append((CharSequence) gfmUser.getText()).append(this.options.gitHubIssueUrlSuffix);
        htmlWriter.raw("[");
        htmlWriter.raw((CharSequence) gfmUser.getChars());
        htmlWriter.raw("|").raw((CharSequence) sb.toString()).raw((CharSequence) "]");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersJiraRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new GfmUsersJiraRenderer(dataHolder);
        }
    }
}
