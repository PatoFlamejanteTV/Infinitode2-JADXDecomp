package com.vladsch.flexmark.ext.media.tags.internal;

import com.vladsch.flexmark.ext.media.tags.AudioLink;
import com.vladsch.flexmark.ext.media.tags.EmbedLink;
import com.vladsch.flexmark.ext.media.tags.PictureLink;
import com.vladsch.flexmark.ext.media.tags.VideoLink;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/internal/MediaTagsNodeRenderer.class */
public class MediaTagsNodeRenderer implements NodeRenderer {
    public MediaTagsNodeRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(AudioLink.class, this::renderAudioLink));
        hashSet.add(new NodeRenderingHandler(EmbedLink.class, this::renderEmbedLink));
        hashSet.add(new NodeRenderingHandler(PictureLink.class, this::renderPictureLink));
        hashSet.add(new NodeRenderingHandler(VideoLink.class, this::renderVideoLink));
        return hashSet;
    }

    private void renderAudioLink(AudioLink audioLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(audioLink);
            return;
        }
        String[] split = nodeRendererContext.resolveLink(LinkType.LINK, audioLink.getUrl().unescape(), Boolean.FALSE).getUrl().split("\\|");
        htmlWriter.attr(Attribute.TITLE_ATTR, (CharSequence) audioLink.getText()).attr((CharSequence) "controls", (CharSequence) "").withAttr().tag((CharSequence) "audio");
        for (String str : split) {
            String encodeUrl = nodeRendererContext.getHtmlOptions().percentEncodeUrls ? nodeRendererContext.encodeUrl(str) : str;
            String resolveAudioType = Utilities.resolveAudioType(str);
            htmlWriter.attr("src", (CharSequence) encodeUrl);
            if (resolveAudioType != null) {
                htmlWriter.attr("type", (CharSequence) resolveAudioType);
            }
            htmlWriter.withAttr().tag("source", true);
        }
        htmlWriter.text("Your browser does not support the audio element.");
        htmlWriter.tag("/audio");
    }

    private void renderEmbedLink(EmbedLink embedLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(embedLink);
        } else {
            htmlWriter.attr(Attribute.TITLE_ATTR, (CharSequence) embedLink.getText()).attr((CharSequence) "src", (CharSequence) nodeRendererContext.resolveLink(LinkType.LINK, embedLink.getUrl().unescape(), null).getUrl()).withAttr().tag((CharSequence) "embed", true);
        }
    }

    private void renderPictureLink(PictureLink pictureLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(pictureLink);
            return;
        }
        String[] split = nodeRendererContext.resolveLink(LinkType.LINK, pictureLink.getUrl().unescape(), Boolean.FALSE).getUrl().split("\\|");
        htmlWriter.tag("picture");
        for (int i = 0; i < split.length - 1; i++) {
            String str = split[i];
            htmlWriter.attr("srcset", (CharSequence) (nodeRendererContext.getHtmlOptions().percentEncodeUrls ? nodeRendererContext.encodeUrl(str) : str)).withAttr().tag((CharSequence) "source", true);
        }
        int length = split.length - 1;
        if (length >= 0) {
            String str2 = split[length];
            htmlWriter.attr("src", (CharSequence) (nodeRendererContext.getHtmlOptions().percentEncodeUrls ? nodeRendererContext.encodeUrl(str2) : str2)).attr((CharSequence) "alt", (CharSequence) pictureLink.getText()).withAttr().tag((CharSequence) FlexmarkHtmlConverter.IMG_NODE, true);
        }
        htmlWriter.tag("/picture");
    }

    private void renderVideoLink(VideoLink videoLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(videoLink);
            return;
        }
        String[] split = nodeRendererContext.resolveLink(LinkType.LINK, videoLink.getUrl().unescape(), Boolean.FALSE).getUrl().split("\\|");
        htmlWriter.attr(Attribute.TITLE_ATTR, (CharSequence) videoLink.getText()).attr((CharSequence) "controls", (CharSequence) "").withAttr().tag((CharSequence) "video");
        for (String str : split) {
            String encodeUrl = nodeRendererContext.getHtmlOptions().percentEncodeUrls ? nodeRendererContext.encodeUrl(str) : str;
            String resolveVideoType = Utilities.resolveVideoType(str);
            htmlWriter.attr("src", (CharSequence) encodeUrl);
            if (resolveVideoType != null) {
                htmlWriter.attr("type", (CharSequence) resolveVideoType);
            }
            htmlWriter.withAttr().tag("source", true);
        }
        htmlWriter.text("Your browser does not support the video element.");
        htmlWriter.tag("/video");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/internal/MediaTagsNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new MediaTagsNodeRenderer(dataHolder);
        }
    }
}
