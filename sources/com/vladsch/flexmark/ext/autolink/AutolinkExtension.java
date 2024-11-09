package com.vladsch.flexmark.ext.autolink;

import com.vladsch.flexmark.ext.autolink.internal.AutolinkNodePostProcessor;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/autolink/AutolinkExtension.class */
public class AutolinkExtension implements Parser.ParserExtension {
    public static final DataKey<String> IGNORE_LINKS = new DataKey<>("IGNORE_LINKS", "");

    private AutolinkExtension() {
    }

    public static AutolinkExtension create() {
        return new AutolinkExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new AutolinkNodePostProcessor.Factory());
    }
}
