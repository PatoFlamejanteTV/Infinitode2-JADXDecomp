package com.vladsch.flexmark.ext.youtube.embedded.internal;

import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLink;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/internal/YouTubeLinkNodeRenderer.class */
public class YouTubeLinkNodeRenderer implements NodeRenderer {
    public YouTubeLinkNodeRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(YouTubeLink.class, this::render));
        return hashSet;
    }

    private void render(YouTubeLink youTubeLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(youTubeLink);
            return;
        }
        ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK, youTubeLink.getUrl().unescape(), null);
        URL url = null;
        try {
            url = new URL(resolveLink.getUrl());
        } catch (MalformedURLException unused) {
        }
        if (url != null && "youtu.be".equalsIgnoreCase(url.getHost())) {
            htmlWriter.attr("src", (CharSequence) ("https://www.youtube-nocookie.com/embed" + url.getFile().replace("?t=", "?start=")));
            htmlWriter.attr("width", "560");
            htmlWriter.attr("height", "315");
            htmlWriter.attr(Attribute.CLASS_ATTR, "youtube-embedded");
            htmlWriter.attr("allowfullscreen", "true");
            htmlWriter.attr("frameborder", "0");
            htmlWriter.attr("allow", "accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture");
            htmlWriter.srcPos(youTubeLink.getChars()).withAttr(resolveLink).tag("iframe");
            htmlWriter.tag("/iframe");
            return;
        }
        if (resolveLink.getUrl().contains("www.youtube.com/watch")) {
            htmlWriter.attr("src", (CharSequence) resolveLink.getUrl().replace("watch?v=".toLowerCase(), "embed/"));
            htmlWriter.attr("width", "420");
            htmlWriter.attr("height", "315");
            htmlWriter.attr(Attribute.CLASS_ATTR, "youtube-embedded");
            htmlWriter.attr("allowfullscreen", "true");
            htmlWriter.attr("frameborder", "0");
            htmlWriter.srcPos(youTubeLink.getChars()).withAttr(resolveLink).tag("iframe");
            htmlWriter.tag("/iframe");
            return;
        }
        htmlWriter.attr("href", (CharSequence) resolveLink.getUrl());
        if (youTubeLink.getTitle().isNotNull()) {
            htmlWriter.attr(Attribute.TITLE_ATTR, (CharSequence) youTubeLink.getTitle().unescape());
        }
        htmlWriter.srcPos(youTubeLink.getChars()).withAttr(resolveLink).tag(FlexmarkHtmlConverter.A_NODE);
        nodeRendererContext.renderChildren(youTubeLink);
        htmlWriter.tag("/a");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/internal/YouTubeLinkNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new YouTubeLinkNodeRenderer(dataHolder);
        }
    }
}
