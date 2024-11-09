package com.vladsch.flexmark.parser.core.delimiter;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/delimiter/UnderscoreDelimiterProcessor.class */
public class UnderscoreDelimiterProcessor extends EmphasisDelimiterProcessor {
    public UnderscoreDelimiterProcessor(boolean z) {
        super('_', z);
    }

    @Override // com.vladsch.flexmark.parser.core.delimiter.EmphasisDelimiterProcessor, com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean canBeOpener(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if (z) {
            return !z2 || z3;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.parser.core.delimiter.EmphasisDelimiterProcessor, com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean canBeCloser(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if (z2) {
            return !z || z4;
        }
        return false;
    }
}
