package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.ext.toc.internal.SimTocBlockParser;
import com.vladsch.flexmark.ext.toc.internal.SimTocNodeFormatter;
import com.vladsch.flexmark.ext.toc.internal.SimTocNodeRenderer;
import com.vladsch.flexmark.ext.toc.internal.TocOptions;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocExtension.class */
public class SimTocExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final AttributablePart TOC_CONTENT = TocUtils.TOC_CONTENT;
    public static final AttributablePart TOC_LIST = TocUtils.TOC_LIST;
    public static final DataKey<Integer> LEVELS = TocExtension.LEVELS;
    public static final DataKey<Boolean> IS_TEXT_ONLY = TocExtension.IS_TEXT_ONLY;
    public static final DataKey<Boolean> IS_NUMBERED = TocExtension.IS_NUMBERED;
    public static final DataKey<TocOptions.ListType> LIST_TYPE = TocExtension.LIST_TYPE;
    public static final DataKey<Boolean> IS_HTML = TocExtension.IS_HTML;
    public static final DataKey<Integer> TITLE_LEVEL = TocExtension.TITLE_LEVEL;
    public static final NullableDataKey<String> TITLE = TocExtension.TITLE;
    public static final DataKey<Boolean> AST_INCLUDE_OPTIONS = TocExtension.AST_INCLUDE_OPTIONS;
    public static final DataKey<Boolean> BLANK_LINE_SPACER = TocExtension.BLANK_LINE_SPACER;
    public static final DataKey<String> DIV_CLASS = TocExtension.DIV_CLASS;
    public static final DataKey<String> LIST_CLASS = TocExtension.LIST_CLASS;
    public static final DataKey<Boolean> CASE_SENSITIVE_TOC_TAG = TocExtension.CASE_SENSITIVE_TOC_TAG;
    public static final DataKey<SimTocGenerateOnFormat> FORMAT_UPDATE_ON_FORMAT = TocExtension.FORMAT_UPDATE_ON_FORMAT;
    public static final DataKey<TocOptions> FORMAT_OPTIONS = TocExtension.FORMAT_OPTIONS;

    private SimTocExtension() {
    }

    public static SimTocExtension create() {
        return new SimTocExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
        if (!mutableDataHolder.contains(HtmlRenderer.GENERATE_HEADER_ID)) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) HtmlRenderer.GENERATE_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE);
        }
        if (!mutableDataHolder.contains(Formatter.GENERATE_HEADER_ID)) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Formatter.GENERATE_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE);
        }
        if (!mutableDataHolder.contains(HtmlRenderer.RENDER_HEADER_ID)) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE);
        }
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new SimTocNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new SimTocBlockParser.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new SimTocNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
