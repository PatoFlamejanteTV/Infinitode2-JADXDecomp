package com.vladsch.flexmark.ext.gfm.issues.internal;

import com.vladsch.flexmark.ext.gfm.issues.GfmIssue;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/internal/GfmIssuesNodeRenderer.class */
public class GfmIssuesNodeRenderer implements NodeRenderer {
    private final GfmIssuesOptions options;

    public GfmIssuesNodeRenderer(DataHolder dataHolder) {
        this.options = new GfmIssuesOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(GfmIssue.class, this::render));
        return hashSet;
    }

    private void render(GfmIssue gfmIssue, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.text((CharSequence) gfmIssue.getChars());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.options.gitHubIssuesUrlRoot).append(this.options.gitHubIssueUrlPrefix).append((CharSequence) gfmIssue.getText()).append(this.options.gitHubIssueUrlSuffix);
        htmlWriter.srcPos(gfmIssue.getChars()).attr("href", (CharSequence) sb.toString()).withAttr().tag((CharSequence) FlexmarkHtmlConverter.A_NODE);
        htmlWriter.raw((CharSequence) this.options.gitHubIssueTextPrefix);
        htmlWriter.text((CharSequence) gfmIssue.getChars());
        htmlWriter.raw((CharSequence) this.options.gitHubIssueTextSuffix);
        htmlWriter.tag("/a");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/internal/GfmIssuesNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new GfmIssuesNodeRenderer(dataHolder);
        }
    }
}
