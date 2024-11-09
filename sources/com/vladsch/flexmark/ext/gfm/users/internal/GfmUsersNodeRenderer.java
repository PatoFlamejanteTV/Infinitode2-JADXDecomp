package com.vladsch.flexmark.ext.gfm.users.internal;

import com.vladsch.flexmark.ext.gfm.users.GfmUser;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersNodeRenderer.class */
public class GfmUsersNodeRenderer implements NodeRenderer {
    private final GfmUsersOptions options;

    public GfmUsersNodeRenderer(DataHolder dataHolder) {
        this.options = new GfmUsersOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(GfmUser.class, this::render));
        return hashSet;
    }

    private void render(GfmUser gfmUser, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.text((CharSequence) gfmUser.getChars());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.options.gitHubIssuesUrlRoot).append(this.options.gitHubIssueUrlPrefix).append((CharSequence) gfmUser.getText()).append(this.options.gitHubIssueUrlSuffix);
        htmlWriter.srcPos(gfmUser.getChars()).attr("href", (CharSequence) sb.toString()).withAttr().tag((CharSequence) FlexmarkHtmlConverter.A_NODE);
        htmlWriter.raw((CharSequence) this.options.gitHubUserTextPrefix);
        htmlWriter.text((CharSequence) gfmUser.getChars());
        htmlWriter.raw((CharSequence) this.options.gitHubUserTextSuffix);
        htmlWriter.tag("/a");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new GfmUsersNodeRenderer(dataHolder);
        }
    }
}
