package com.vladsch.flexmark.ext.anchorlink;

import com.vladsch.flexmark.ext.anchorlink.internal.AnchorLinkNodePostProcessor;
import com.vladsch.flexmark.ext.anchorlink.internal.AnchorLinkNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/AnchorLinkExtension.class */
public class AnchorLinkExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> ANCHORLINKS_WRAP_TEXT = new DataKey<>("ANCHORLINKS_WRAP_TEXT", Boolean.TRUE);
    public static final DataKey<String> ANCHORLINKS_TEXT_PREFIX = new DataKey<>("ANCHORLINKS_TEXT_PREFIX", "");
    public static final DataKey<String> ANCHORLINKS_TEXT_SUFFIX = new DataKey<>("ANCHORLINKS_TEXT_SUFFIX", "");
    public static final DataKey<String> ANCHORLINKS_ANCHOR_CLASS = new DataKey<>("ANCHORLINKS_ANCHOR_CLASS", "");
    public static final DataKey<Boolean> ANCHORLINKS_SET_NAME = new DataKey<>("ANCHORLINKS_SET_NAME", Boolean.FALSE);
    public static final DataKey<Boolean> ANCHORLINKS_SET_ID = new DataKey<>("ANCHORLINKS_SET_ID", Boolean.TRUE);
    public static final DataKey<Boolean> ANCHORLINKS_NO_BLOCK_QUOTE = new DataKey<>("ANCHORLINKS_NO_BLOCK_QUOTE", Boolean.FALSE);

    private AnchorLinkExtension() {
    }

    public static AnchorLinkExtension create() {
        return new AnchorLinkExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new AnchorLinkNodePostProcessor.Factory(builder));
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new AnchorLinkNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
