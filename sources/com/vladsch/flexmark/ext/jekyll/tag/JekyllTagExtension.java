package com.vladsch.flexmark.ext.jekyll.tag;

import com.vladsch.flexmark.ext.jekyll.tag.internal.IncludeNodePostProcessor;
import com.vladsch.flexmark.ext.jekyll.tag.internal.JekyllTagBlockParser;
import com.vladsch.flexmark.ext.jekyll.tag.internal.JekyllTagInlineParserExtension;
import com.vladsch.flexmark.ext.jekyll.tag.internal.JekyllTagNodeFormatter;
import com.vladsch.flexmark.ext.jekyll.tag.internal.JekyllTagNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.LinkResolverFactory;
import com.vladsch.flexmark.html.UriContentResolverFactory;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/JekyllTagExtension.class */
public class JekyllTagExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> ENABLE_INLINE_TAGS = new DataKey<>("ENABLE_INLINE_TAGS", Boolean.TRUE);
    public static final DataKey<Boolean> ENABLE_BLOCK_TAGS = new DataKey<>("ENABLE_BLOCK_TAGS", Boolean.TRUE);
    public static final DataKey<Boolean> LIST_INCLUDES_ONLY = new DataKey<>("LIST_INCLUDES_ONLY", Boolean.TRUE);
    public static final DataKey<Boolean> EMBED_INCLUDED_CONTENT = new DataKey<>("EMBED_INCLUDED_CONTENT", Boolean.FALSE);
    public static final DataKey<List<LinkResolverFactory>> LINK_RESOLVER_FACTORIES = new DataKey<>("LINK_RESOLVER_FACTORIES", Collections.emptyList());
    public static final DataKey<List<UriContentResolverFactory>> CONTENT_RESOLVER_FACTORIES = new DataKey<>("LINK_RESOLVER_FACTORIES", Collections.emptyList());
    public static final NullableDataKey<Map<String, String>> INCLUDED_HTML = new NullableDataKey<>("INCLUDED_HTML");
    public static final DataKey<List<JekyllTag>> TAG_LIST = new DataKey<>("TAG_LIST", ArrayList::new);

    @Deprecated
    public static final DataKey<Boolean> ENABLE_RENDERING = new DataKey<>("ENABLE_RENDERING", Boolean.FALSE);

    private JekyllTagExtension() {
    }

    public static JekyllTagExtension create() {
        return new JekyllTagExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new JekyllTagNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        if (ENABLE_BLOCK_TAGS.get(builder).booleanValue()) {
            builder.customBlockParserFactory(new JekyllTagBlockParser.Factory());
        }
        if (ENABLE_INLINE_TAGS.get(builder).booleanValue()) {
            builder.customInlineParserExtensionFactory(new JekyllTagInlineParserExtension.Factory());
        }
        Map<String, String> map = INCLUDED_HTML.get(builder);
        if ((map != null && !map.isEmpty()) || !LINK_RESOLVER_FACTORIES.get(builder).isEmpty()) {
            builder.postProcessorFactory(new IncludeNodePostProcessor.Factory());
        }
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if ("HTML".equals(str)) {
            builder.nodeRendererFactory(new JekyllTagNodeRenderer.Factory());
        }
    }
}
