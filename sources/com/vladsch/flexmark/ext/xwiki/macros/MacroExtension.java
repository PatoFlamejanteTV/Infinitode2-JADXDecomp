package com.vladsch.flexmark.ext.xwiki.macros;

import com.vladsch.flexmark.ext.xwiki.macros.internal.MacroBlockParser;
import com.vladsch.flexmark.ext.xwiki.macros.internal.MacroInlineParser;
import com.vladsch.flexmark.ext.xwiki.macros.internal.MacroNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/MacroExtension.class */
public class MacroExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Boolean> ENABLE_INLINE_MACROS = new DataKey<>("ENABLE_INLINE_MACROS", Boolean.TRUE);
    public static final DataKey<Boolean> ENABLE_BLOCK_MACROS = new DataKey<>("ENABLE_BLOCK_MACROS", Boolean.TRUE);
    public static final DataKey<Boolean> ENABLE_RENDERING = new DataKey<>("ENABLE_RENDERING", Boolean.FALSE);

    private MacroExtension() {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        builder.nodeRendererFactory(new MacroNodeRenderer.Factory());
    }

    public static MacroExtension create() {
        return new MacroExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        if (ENABLE_BLOCK_MACROS.get(builder).booleanValue()) {
            builder.customBlockParserFactory(new MacroBlockParser.Factory());
        }
        if (ENABLE_INLINE_MACROS.get(builder).booleanValue()) {
            builder.customInlineParserExtensionFactory(new MacroInlineParser.Factory());
        }
    }
}
