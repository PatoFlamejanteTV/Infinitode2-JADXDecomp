package com.vladsch.flexmark.ext.emoji.internal;

import com.vladsch.flexmark.ext.emoji.Emoji;
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
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiNodeRenderer.class */
public class EmojiNodeRenderer implements NodeRenderer {
    final EmojiOptions myOptions;

    public EmojiNodeRenderer(DataHolder dataHolder) {
        this.myOptions = new EmojiOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Emoji.class, this::render));
        return hashSet;
    }

    private void render(Emoji emoji, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        EmojiResolvedShortcut emojiText = EmojiResolvedShortcut.getEmojiText(emoji, this.myOptions.useShortcutType, this.myOptions.useImageType, this.myOptions.rootImagePath);
        if (emojiText.emoji == null || emojiText.emojiText == null) {
            htmlWriter.text(":");
            nodeRendererContext.renderChildren(emoji);
            htmlWriter.text(":");
        } else {
            if (emojiText.isUnicode) {
                htmlWriter.text((CharSequence) emojiText.emojiText);
                return;
            }
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.IMAGE, emojiText.emojiText, null);
            htmlWriter.attr("src", (CharSequence) resolveLink.getUrl());
            htmlWriter.attr("alt", (CharSequence) emojiText.alt);
            if (!this.myOptions.attrImageSize.isEmpty()) {
                htmlWriter.attr("height", (CharSequence) this.myOptions.attrImageSize).attr((CharSequence) "width", (CharSequence) this.myOptions.attrImageSize);
            }
            if (!this.myOptions.attrAlign.isEmpty()) {
                htmlWriter.attr("align", (CharSequence) this.myOptions.attrAlign);
            }
            if (!this.myOptions.attrImageClass.isEmpty()) {
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.myOptions.attrImageClass);
            }
            htmlWriter.withAttr(resolveLink);
            htmlWriter.tagVoid(FlexmarkHtmlConverter.IMG_NODE);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new EmojiNodeRenderer(dataHolder);
        }
    }
}
