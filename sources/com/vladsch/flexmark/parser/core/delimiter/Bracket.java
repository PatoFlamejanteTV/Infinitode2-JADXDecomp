package com.vladsch.flexmark.parser.core.delimiter;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/delimiter/Bracket.class */
public class Bracket {
    private final Text node;
    private final int index;
    private final boolean image;
    private final Bracket previous;
    private final Delimiter previousDelimiter;
    private boolean allowed = true;
    private boolean bracketAfter = false;

    public boolean isAllowed() {
        return this.allowed;
    }

    public void setAllowed(boolean z) {
        this.allowed = z;
    }

    public boolean isBracketAfter() {
        return this.bracketAfter;
    }

    public void setBracketAfter(boolean z) {
        this.bracketAfter = z;
    }

    public Bracket getPrevious() {
        return this.previous;
    }

    public boolean isImage() {
        return this.image;
    }

    public Delimiter getPreviousDelimiter() {
        return this.previousDelimiter;
    }

    public int getStartIndex() {
        return this.index;
    }

    public int getEndIndex() {
        return this.image ? this.index + 2 : this.index + 1;
    }

    public Text getNode() {
        return this.node;
    }

    public static Bracket link(BasedSequence basedSequence, Text text, int i, Bracket bracket, Delimiter delimiter) {
        return new Bracket(basedSequence, text, i, bracket, delimiter, false);
    }

    public static Bracket image(BasedSequence basedSequence, Text text, int i, Bracket bracket, Delimiter delimiter) {
        return new Bracket(basedSequence, text, i, bracket, delimiter, true);
    }

    private Bracket(BasedSequence basedSequence, Text text, int i, Bracket bracket, Delimiter delimiter, boolean z) {
        this.node = text;
        this.index = i;
        this.image = z;
        this.previous = bracket;
        this.previousDelimiter = delimiter;
    }

    public boolean isStraddling(BasedSequence basedSequence) {
        int endIndex;
        int startOffset = basedSequence.getStartOffset();
        int endOffset = basedSequence.getEndOffset();
        Delimiter next = this.previousDelimiter == null ? null : this.previousDelimiter.getNext();
        while (true) {
            Delimiter delimiter = next;
            if (next != null && (endIndex = delimiter.getEndIndex()) < endOffset) {
                if (endIndex >= startOffset && !delimiter.isMatched()) {
                    return true;
                }
                next = delimiter.getNext();
            } else {
                return false;
            }
        }
    }
}
