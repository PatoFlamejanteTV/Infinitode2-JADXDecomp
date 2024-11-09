package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.util.options.OptionsParser;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocOptionsParser.class */
public class TocOptionsParser extends OptionsParser<TocOptions> {
    public TocOptionsParser() {
        super("TocOptions", TocOptionTypes.OPTIONS, ' ', '=');
    }
}
