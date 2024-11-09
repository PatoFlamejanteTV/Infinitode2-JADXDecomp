package com.vladsch.flexmark.parser.core.delimiter;

import com.vladsch.flexmark.ast.Emphasis;
import com.vladsch.flexmark.ast.StrongEmphasis;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.parser.delimiter.DelimiterRun;
import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/delimiter/EmphasisDelimiterProcessor.class */
public abstract class EmphasisDelimiterProcessor implements DelimiterProcessor {
    private final char delimiterChar;
    private final int multipleUse;

    /* JADX INFO: Access modifiers changed from: protected */
    public EmphasisDelimiterProcessor(char c, boolean z) {
        this.delimiterChar = c;
        this.multipleUse = z ? 1 : 2;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return this.delimiterChar;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return this.delimiterChar;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return 1;
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
    public boolean skipNonOpenerCloser() {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public Node unmatchedDelimiterNode(InlineParser inlineParser, DelimiterRun delimiterRun) {
        return null;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public int getDelimiterUse(DelimiterRun delimiterRun, DelimiterRun delimiterRun2) {
        if ((delimiterRun.canClose() || delimiterRun2.canOpen()) && (delimiterRun.length() + delimiterRun2.length()) % 3 == 0) {
            return 0;
        }
        if (delimiterRun.length() < 3 || delimiterRun2.length() < 3) {
            return Utils.min(delimiterRun2.length(), delimiterRun.length());
        }
        if (delimiterRun2.length() % 2 == 0) {
            return 2;
        }
        return this.multipleUse;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public void process(Delimiter delimiter, Delimiter delimiter2, int i) {
        DelimitedNode strongEmphasis;
        if (i == 1) {
            strongEmphasis = new Emphasis(delimiter.getTailChars(i), BasedSequence.NULL, delimiter2.getLeadChars(i));
        } else {
            strongEmphasis = new StrongEmphasis(delimiter.getTailChars(i), BasedSequence.NULL, delimiter2.getLeadChars(i));
        }
        delimiter.moveNodesBetweenDelimitersTo(strongEmphasis, delimiter2);
    }
}
