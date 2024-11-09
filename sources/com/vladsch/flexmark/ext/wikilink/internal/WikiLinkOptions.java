package com.vladsch.flexmark.ext.wikilink.internal;

import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkOptions.class */
public class WikiLinkOptions {
    public final boolean allowInlines;
    public final boolean allowAnchors;
    public final boolean disableRendering;
    public final boolean imageLinks;
    public final boolean linkFirstSyntax;
    public final boolean allowAnchorEscape;
    public final boolean allowPipeEscape;
    public final String imageFileExtension;
    public final String imagePrefix;
    public final String imagePrefixAbsolute;
    public final String linkFileExtension;
    public final String linkPrefix;
    public final String linkPrefixAbsolute;
    public final String linkReplaceChars;
    public final String linkEscapeChars;

    public WikiLinkOptions(DataHolder dataHolder) {
        this.allowInlines = WikiLinkExtension.ALLOW_INLINES.get(dataHolder).booleanValue();
        this.allowAnchors = WikiLinkExtension.ALLOW_ANCHORS.get(dataHolder).booleanValue();
        this.disableRendering = WikiLinkExtension.DISABLE_RENDERING.get(dataHolder).booleanValue();
        this.imageLinks = WikiLinkExtension.IMAGE_LINKS.get(dataHolder).booleanValue();
        this.linkFirstSyntax = WikiLinkExtension.LINK_FIRST_SYNTAX.get(dataHolder).booleanValue();
        this.allowAnchorEscape = WikiLinkExtension.ALLOW_ANCHOR_ESCAPE.get(dataHolder).booleanValue();
        this.allowPipeEscape = WikiLinkExtension.ALLOW_PIPE_ESCAPE.get(dataHolder).booleanValue();
        this.imageFileExtension = WikiLinkExtension.IMAGE_FILE_EXTENSION.get(dataHolder);
        this.imagePrefix = WikiLinkExtension.IMAGE_PREFIX.get(dataHolder);
        this.imagePrefixAbsolute = WikiLinkExtension.IMAGE_PREFIX_ABSOLUTE.get(dataHolder);
        this.linkFileExtension = WikiLinkExtension.LINK_FILE_EXTENSION.get(dataHolder);
        this.linkPrefix = WikiLinkExtension.LINK_PREFIX.get(dataHolder);
        this.linkPrefixAbsolute = WikiLinkExtension.LINK_PREFIX_ABSOLUTE.get(dataHolder);
        this.linkEscapeChars = WikiLinkExtension.LINK_ESCAPE_CHARS.get(dataHolder);
        this.linkReplaceChars = WikiLinkExtension.LINK_REPLACE_CHARS.get(dataHolder);
    }

    public Object getLinkPrefix(boolean z) {
        return z ? this.linkPrefixAbsolute : this.linkPrefix;
    }

    public Object getImagePrefix(boolean z) {
        return z ? this.imagePrefixAbsolute : this.imagePrefix;
    }
}
