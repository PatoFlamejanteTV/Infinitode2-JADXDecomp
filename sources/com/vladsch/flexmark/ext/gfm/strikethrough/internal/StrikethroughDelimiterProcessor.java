package com.vladsch.flexmark.ext.gfm.strikethrough.internal;

import com.vladsch.flexmark.ext.gfm.strikethrough.Strikethrough;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.core.delimiter.Delimiter;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.parser.delimiter.DelimiterRun;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/internal/StrikethroughDelimiterProcessor.class */
public class StrikethroughDelimiterProcessor implements DelimiterProcessor {
    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return '~';
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return '~';
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return 2;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean canBeOpener(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return z;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean canBeCloser(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return z2;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public Node unmatchedDelimiterNode(InlineParser inlineParser, DelimiterRun delimiterRun) {
        return null;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public boolean skipNonOpenerCloser() {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public int getDelimiterUse(DelimiterRun delimiterRun, DelimiterRun delimiterRun2) {
        if (delimiterRun.length() >= 2 && delimiterRun2.length() >= 2) {
            return 2;
        }
        return 0;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public void process(Delimiter delimiter, Delimiter delimiter2, int i) {
        delimiter.moveNodesBetweenDelimitersTo(new Strikethrough(delimiter.getTailChars(i), BasedSequence.NULL, delimiter2.getLeadChars(i)), delimiter2);
    }
}
