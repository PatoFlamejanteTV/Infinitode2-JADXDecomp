package com.vladsch.flexmark.ext.gitlab;

import com.vladsch.flexmark.ext.gitlab.internal.GitLabBlockQuoteParser;
import com.vladsch.flexmark.ext.gitlab.internal.GitLabInlineMathParser;
import com.vladsch.flexmark.ext.gitlab.internal.GitLabInlineParser;
import com.vladsch.flexmark.ext.gitlab.internal.GitLabNodeFormatter;
import com.vladsch.flexmark.ext.gitlab.internal.GitLabNodeRenderer;
import com.vladsch.flexmark.ext.gitlab.internal.GitLabOptions;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/GitLabExtension.class */
public class GitLabExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    private static final String[] DEFAULT_MATH_LANGUAGES = {FlexmarkHtmlConverter.MATH_NODE};
    private static final String[] DEFAULT_MERMAID_LANGUAGES = {"mermaid"};
    public static final DataKey<Boolean> INS_PARSER = new DataKey<>("INS_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> DEL_PARSER = new DataKey<>("DEL_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> BLOCK_QUOTE_PARSER = new DataKey<>("BLOCK_QUOTE_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> NESTED_BLOCK_QUOTES = new DataKey<>("NESTED_BLOCK_QUOTES", Boolean.TRUE);
    public static final DataKey<Boolean> INLINE_MATH_PARSER = new DataKey<>("INLINE_MATH_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> RENDER_BLOCK_MATH = new DataKey<>("RENDER_BLOCK_MATH", Boolean.TRUE);
    public static final DataKey<Boolean> RENDER_BLOCK_MERMAID = new DataKey<>("RENDER_BLOCK_MERMAID", Boolean.TRUE);
    public static final DataKey<Boolean> RENDER_VIDEO_IMAGES = new DataKey<>("RENDER_VIDEO_IMAGES", Boolean.TRUE);
    public static final DataKey<Boolean> RENDER_VIDEO_LINK = new DataKey<>("RENDER_VIDEO_LINK", Boolean.TRUE);
    public static final DataKey<String[]> MATH_LANGUAGES = new DataKey<>("MATH_LANGUAGES", DEFAULT_MATH_LANGUAGES);
    public static final DataKey<String[]> MERMAID_LANGUAGES = new DataKey<>("MERMAID_LANGUAGES", DEFAULT_MERMAID_LANGUAGES);
    public static final DataKey<String> INLINE_MATH_CLASS = new DataKey<>("INLINE_MATH_CLASS", "katex");
    public static final DataKey<String> BLOCK_MATH_CLASS = new DataKey<>("BLOCK_MATH_CLASS", "katex");
    public static final DataKey<String> BLOCK_MERMAID_CLASS = new DataKey<>("BLOCK_MERMAID_CLASS", "mermaid");
    public static final DataKey<String> VIDEO_IMAGE_CLASS = new DataKey<>("VIDEO_IMAGE_CLASS", "video-container");
    public static final DataKey<String> VIDEO_IMAGE_LINK_TEXT_FORMAT = new DataKey<>("VIDEO_IMAGE_LINK_TEXT_FORMAT", "Download '%s'");

    @Deprecated
    public static final DataKey<String> BLOCK_INFO_DELIMITERS = HtmlRenderer.FENCED_CODE_LANGUAGE_DELIMITERS;
    public static final DataKey<String> VIDEO_IMAGE_EXTENSIONS = new DataKey<>("VIDEO_IMAGE_EXTENSIONS", "mp4,m4v,mov,webm,ogv");

    private GitLabExtension() {
    }

    public static GitLabExtension create() {
        return new GitLabExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new GitLabNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        GitLabOptions gitLabOptions = new GitLabOptions(builder);
        if (gitLabOptions.blockQuoteParser) {
            builder.customBlockParserFactory(new GitLabBlockQuoteParser.Factory());
        }
        if (gitLabOptions.delParser || gitLabOptions.insParser) {
            builder.customInlineParserExtensionFactory(new GitLabInlineParser.Factory());
        }
        if (gitLabOptions.inlineMathParser) {
            builder.customInlineParserExtensionFactory(new GitLabInlineMathParser.Factory());
        }
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new GitLabNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
