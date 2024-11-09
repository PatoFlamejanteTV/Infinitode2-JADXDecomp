package com.vladsch.flexmark.ext.typographic.internal;

import com.vladsch.flexmark.ext.typographic.TypographicQuotes;
import com.vladsch.flexmark.ext.typographic.TypographicSmarts;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.core.delimiter.Delimiter;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.parser.delimiter.DelimiterRun;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/QuoteDelimiterProcessorBase.class */
public class QuoteDelimiterProcessorBase implements DelimiterProcessor {
    protected final TypographicOptions myOptions;
    protected final char myOpenDelimiter;
    protected final char myCloseDelimiter;
    protected final String myOpener;
    protected final String myCloser;
    protected final String myUnmatched;

    public QuoteDelimiterProcessorBase(TypographicOptions typographicOptions, char c, char c2, String str, String str2, String str3) {
        this.myOptions = typographicOptions;
        this.myOpenDelimiter = c;
        this.myCloseDelimiter = c2;
        this.myOpener = str;
        this.myCloser = str2;
        this.myUnmatched = str3;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public final char getOpeningCharacter() {
        return this.myOpenDelimiter;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public final char getClosingCharacter() {
        return this.myCloseDelimiter;
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

    protected boolean havePreviousOpener(DelimiterRun delimiterRun) {
        int minLength = getMinLength();
        for (DelimiterRun previous = delimiterRun.getPrevious(); previous != null; previous = previous.getPrevious()) {
            if (previous.getDelimiterChar() == this.myOpenDelimiter) {
                return canOpen(previous, minLength);
            }
        }
        return false;
    }

    protected boolean haveNextCloser(DelimiterRun delimiterRun) {
        int minLength = getMinLength();
        for (DelimiterRun next = delimiterRun.getNext(); next != null; next = next.getNext()) {
            if (next.getDelimiterChar() == this.myCloseDelimiter) {
                return canClose(next, minLength);
            }
        }
        return false;
    }

    protected boolean canClose(DelimiterRun delimiterRun, int i) {
        if (delimiterRun.canClose()) {
            BasedSequence chars = delimiterRun.getNode().getChars();
            if ((delimiterRun.getNext() != null && chars.isContinuationOf(delimiterRun.getNext().getNode().getChars())) || chars.getEndOffset() >= chars.getBaseSequence().length() || isAllowed(chars.getBaseSequence(), (chars.getEndOffset() + i) - 1)) {
                return true;
            }
            return false;
        }
        return false;
    }

    protected boolean canOpen(DelimiterRun delimiterRun, int i) {
        if (delimiterRun.canOpen()) {
            BasedSequence chars = delimiterRun.getNode().getChars();
            if ((delimiterRun.getPrevious() != null && delimiterRun.getPrevious().getNode().getChars().isContinuationOf(chars)) || chars.getStartOffset() == 0 || isAllowed(chars.getBaseSequence(), chars.getStartOffset() - i)) {
                return true;
            }
            return false;
        }
        return false;
    }

    protected boolean isAllowed(char c) {
        return !Character.isLetterOrDigit(c);
    }

    protected boolean isAllowed(CharSequence charSequence, int i) {
        return i < 0 || i >= charSequence.length() || !Character.isLetterOrDigit(charSequence.charAt(i));
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public int getDelimiterUse(DelimiterRun delimiterRun, DelimiterRun delimiterRun2) {
        int minLength = getMinLength();
        if (delimiterRun.length() < minLength || delimiterRun2.length() < minLength || !canOpen(delimiterRun, minLength) || !canClose(delimiterRun2, minLength)) {
            return 0;
        }
        return minLength;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public Node unmatchedDelimiterNode(InlineParser inlineParser, DelimiterRun delimiterRun) {
        if (this.myUnmatched != null && this.myOptions.typographicSmarts) {
            BasedSequence chars = delimiterRun.getNode().getChars();
            if (chars.length() == 1) {
                return new TypographicSmarts(chars, this.myUnmatched);
            }
            return null;
        }
        return null;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
    public void process(Delimiter delimiter, Delimiter delimiter2, int i) {
        TypographicQuotes typographicQuotes = new TypographicQuotes(delimiter.getTailChars(i), BasedSequence.NULL, delimiter2.getLeadChars(i));
        typographicQuotes.setTypographicOpening(this.myOpener);
        typographicQuotes.setTypographicClosing(this.myCloser);
        delimiter.moveNodesBetweenDelimitersTo(typographicQuotes, delimiter2);
    }
}
