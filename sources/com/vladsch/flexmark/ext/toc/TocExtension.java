package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.ext.toc.internal.TocBlockParser;
import com.vladsch.flexmark.ext.toc.internal.TocNodeRenderer;
import com.vladsch.flexmark.ext.toc.internal.TocOptions;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/TocExtension.class */
public class TocExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final AttributablePart TOC_CONTENT = TocUtils.TOC_CONTENT;
    public static final AttributablePart TOC_LIST = TocUtils.TOC_LIST;
    public static final DataKey<Integer> LEVELS = new DataKey<>("LEVELS", 12);
    public static final DataKey<Boolean> IS_TEXT_ONLY = new DataKey<>("IS_TEXT_ONLY", Boolean.FALSE);
    public static final DataKey<Boolean> IS_NUMBERED = new DataKey<>("IS_NUMBERED", Boolean.FALSE);
    public static final DataKey<TocOptions.ListType> LIST_TYPE = new DataKey<>("LIST_TYPE", TocOptions.ListType.HIERARCHY);
    public static final DataKey<Boolean> IS_HTML = new DataKey<>("IS_HTML", Boolean.FALSE);
    public static final DataKey<Integer> TITLE_LEVEL = new DataKey<>("TITLE_LEVEL", 1);
    public static final NullableDataKey<String> TITLE = new NullableDataKey<>("TITLE");
    public static final DataKey<Boolean> AST_INCLUDE_OPTIONS = new DataKey<>("AST_INCLUDE_OPTIONS", Boolean.FALSE);
    public static final DataKey<Boolean> BLANK_LINE_SPACER = new DataKey<>("BLANK_LINE_SPACER", Boolean.FALSE);
    public static final DataKey<String> DIV_CLASS = new DataKey<>("DIV_CLASS", "");
    public static final DataKey<String> LIST_CLASS = new DataKey<>("LIST_CLASS", "");
    public static final DataKey<Boolean> CASE_SENSITIVE_TOC_TAG = new DataKey<>("CASE_SENSITIVE_TOC_TAG", Boolean.TRUE);
    public static final DataKey<SimTocGenerateOnFormat> FORMAT_UPDATE_ON_FORMAT = new DataKey<>("FORMAT_UPDATE_ON_FORMAT", SimTocGenerateOnFormat.UPDATE);
    public static final DataKey<TocOptions> FORMAT_OPTIONS = new DataKey<>("FORMAT_OPTIONS", new TocOptions(null, false), dataHolder -> {
        return new TocOptions(dataHolder, false);
    });

    private TocExtension() {
    }

    public static TocExtension create() {
        return new TocExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
        if (!mutableDataHolder.contains(HtmlRenderer.RENDER_HEADER_ID)) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) HtmlRenderer.GENERATE_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE);
        } else if (!mutableDataHolder.contains(HtmlRenderer.GENERATE_HEADER_ID)) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) HtmlRenderer.GENERATE_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE);
        }
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new TocBlockParser.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new TocNodeRenderer.Factory());
        }
    }
}
