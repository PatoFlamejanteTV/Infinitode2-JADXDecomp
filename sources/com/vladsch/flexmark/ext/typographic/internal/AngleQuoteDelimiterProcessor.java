package com.vladsch.flexmark.ext.typographic.internal;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/AngleQuoteDelimiterProcessor.class */
public class AngleQuoteDelimiterProcessor extends QuoteDelimiterProcessorBase {
    public AngleQuoteDelimiterProcessor(TypographicOptions typographicOptions) {
        super(typographicOptions, '<', '>', typographicOptions.angleQuoteOpen, typographicOptions.angleQuoteClose, typographicOptions.angleQuoteUnmatched);
    }

    @Override // com.vladsch.flexmark.ext.typographic.internal.QuoteDelimiterProcessorBase, com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return 2;
    }

    @Override // com.vladsch.flexmark.ext.typographic.internal.QuoteDelimiterProcessorBase, com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean canBeOpener(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return true;
    }

    @Override // com.vladsch.flexmark.ext.typographic.internal.QuoteDelimiterProcessorBase, com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean canBeCloser(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return true;
    }

    @Override // com.vladsch.flexmark.ext.typographic.internal.QuoteDelimiterProcessorBase
    protected boolean isAllowed(CharSequence charSequence, int i) {
        return true;
    }
}
