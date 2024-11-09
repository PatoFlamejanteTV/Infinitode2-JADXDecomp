package com.vladsch.flexmark.ext.typographic.internal;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/DoubleQuoteDelimiterProcessor.class */
public class DoubleQuoteDelimiterProcessor extends QuoteDelimiterProcessorBase {
    public DoubleQuoteDelimiterProcessor(TypographicOptions typographicOptions) {
        super(typographicOptions, '\"', '\"', typographicOptions.doubleQuoteOpen, typographicOptions.doubleQuoteClose, typographicOptions.doubleQuoteUnmatched);
    }
}
