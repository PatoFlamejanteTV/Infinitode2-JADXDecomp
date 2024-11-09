package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.util.options.OptionsParser;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocOptionsParser.class */
public class SimTocOptionsParser extends OptionsParser<TocOptions> {
    public SimTocOptionsParser() {
        super("SimTocOptions", SimTocOptionTypes.OPTIONS, ' ', '=');
    }
}
