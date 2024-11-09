package com.vladsch.flexmark.ext.typographic.internal;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/SingleQuoteDelimiterProcessor.class */
public class SingleQuoteDelimiterProcessor extends QuoteDelimiterProcessorBase {
    public SingleQuoteDelimiterProcessor(TypographicOptions typographicOptions) {
        super(typographicOptions, '\'', '\'', typographicOptions.singleQuoteOpen, typographicOptions.singleQuoteClose, typographicOptions.singleQuoteUnmatched);
    }
}
