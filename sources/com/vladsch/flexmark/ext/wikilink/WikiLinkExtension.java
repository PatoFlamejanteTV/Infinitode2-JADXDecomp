package com.vladsch.flexmark.ext.wikilink;

import com.vladsch.flexmark.ext.wikilink.internal.WikiLinkJiraRenderer;
import com.vladsch.flexmark.ext.wikilink.internal.WikiLinkLinkRefProcessor;
import com.vladsch.flexmark.ext.wikilink.internal.WikiLinkLinkResolver;
import com.vladsch.flexmark.ext.wikilink.internal.WikiLinkNodeFormatter;
import com.vladsch.flexmark.ext.wikilink.internal.WikiLinkNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.LinkResolverFactory;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/WikiLinkExtension.class */
public class WikiLinkExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> ALLOW_INLINES = new DataKey<>("ALLOW_INLINES", Boolean.FALSE);
    public static final DataKey<Boolean> ALLOW_ANCHORS = new DataKey<>("ALLOW_ANCHORS", Boolean.FALSE);
    public static final DataKey<Boolean> ALLOW_ANCHOR_ESCAPE = new DataKey<>("ALLOW_ANCHOR_ESCAPE", Boolean.FALSE);
    public static final DataKey<Boolean> ALLOW_PIPE_ESCAPE = new DataKey<>("ALLOW_PIPE_ESCAPE", Boolean.FALSE);
    public static final DataKey<Boolean> DISABLE_RENDERING = new DataKey<>("DISABLE_RENDERING", Boolean.FALSE);
    public static final DataKey<Boolean> LINK_FIRST_SYNTAX = new DataKey<>("LINK_FIRST_SYNTAX", Boolean.FALSE);
    public static final DataKey<String> LINK_PREFIX = new DataKey<>("LINK_PREFIX", "");
    public static final DataKey<String> LINK_PREFIX_ABSOLUTE = new DataKey<>("LINK_PREFIX_ABSOLUTE", (DataKey) LINK_PREFIX);
    public static final DataKey<String> IMAGE_PREFIX = new DataKey<>("IMAGE_PREFIX", "");
    public static final DataKey<String> IMAGE_PREFIX_ABSOLUTE = new DataKey<>("IMAGE_PREFIX_ABSOLUTE", (DataKey) IMAGE_PREFIX);
    public static final DataKey<Boolean> IMAGE_LINKS = new DataKey<>("IMAGE_LINKS", Boolean.FALSE);
    public static final DataKey<String> LINK_FILE_EXTENSION = new DataKey<>("LINK_FILE_EXTENSION", "");
    public static final DataKey<String> IMAGE_FILE_EXTENSION = new DataKey<>("IMAGE_FILE_EXTENSION", "");
    public static final DataKey<String> LINK_ESCAPE_CHARS = new DataKey<>("LINK_ESCAPE_CHARS", " +/<>");
    public static final DataKey<String> LINK_REPLACE_CHARS = new DataKey<>("LINK_REPLACE_CHARS", "-----");
    public static final LinkType WIKI_LINK = new LinkType("WIKI");

    private WikiLinkExtension() {
    }

    public static WikiLinkExtension create() {
        return new WikiLinkExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new WikiLinkNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.linkRefProcessorFactory(new WikiLinkLinkRefProcessor.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new WikiLinkNodeRenderer.Factory());
            builder.linkResolverFactory((LinkResolverFactory) new WikiLinkLinkResolver.Factory());
        } else if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new WikiLinkJiraRenderer.Factory());
            builder.linkResolverFactory((LinkResolverFactory) new WikiLinkLinkResolver.Factory());
        }
    }
}
