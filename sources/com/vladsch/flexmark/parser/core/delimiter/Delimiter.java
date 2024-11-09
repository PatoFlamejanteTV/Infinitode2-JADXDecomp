package com.vladsch.flexmark.parser.core.delimiter;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.parser.delimiter.DelimiterRun;
import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/delimiter/Delimiter.class */
public class Delimiter implements DelimiterRun {
    private final Text node;
    private final BasedSequence input;
    private final char delimiterChar;
    private int index;
    private final boolean canOpen;
    private final boolean canClose;
    private Delimiter previous;
    private Delimiter next;
    private boolean matched = false;
    private int numDelims = 1;

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public Delimiter getPrevious() {
        return this.previous;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public Delimiter getNext() {
        return this.next;
    }

    public void setMatched(boolean z) {
        this.matched = z;
    }

    public void setPrevious(Delimiter delimiter) {
        this.previous = delimiter;
    }

    public void setNext(Delimiter delimiter) {
        this.next = delimiter;
    }

    public void setNumDelims(int i) {
        this.numDelims = i;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public char getDelimiterChar() {
        return this.delimiterChar;
    }

    public boolean isMatched() {
        return this.matched;
    }

    public int getNumDelims() {
        return this.numDelims;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public Text getNode() {
        return this.node;
    }

    public BasedSequence getInput() {
        return this.input;
    }

    public int getStartIndex() {
        return this.index;
    }

    public int getEndIndex() {
        return this.index + this.numDelims;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public BasedSequence getTailChars(int i) {
        return this.input.subSequence(getEndIndex() - i, getEndIndex());
    }

    public BasedSequence getLeadChars(int i) {
        return this.input.subSequence(getStartIndex(), getStartIndex() + i);
    }

    public Delimiter(BasedSequence basedSequence, Text text, char c, boolean z, boolean z2, Delimiter delimiter, int i) {
        this.input = basedSequence;
        this.node = text;
        this.delimiterChar = c;
        this.canOpen = z;
        this.canClose = z2;
        this.previous = delimiter;
        this.index = i;
    }

    public Text getPreviousNonDelimiterTextNode() {
        Node previous = this.node.getPrevious();
        if (previous instanceof Text) {
            if (this.previous == null || this.previous.node != previous) {
                return (Text) previous;
            }
            return null;
        }
        return null;
    }

    public Text getNextNonDelimiterTextNode() {
        Node next = this.node.getNext();
        if (next instanceof Text) {
            if (this.next == null || this.next.node != next) {
                return (Text) next;
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void moveNodesBetweenDelimitersTo(DelimitedNode delimitedNode, Delimiter delimiter) {
        Node next = getNode().getNext();
        while (true) {
            Node node = next;
            if (node == null || node == delimiter.getNode()) {
                break;
            }
            Node next2 = node.getNext();
            ((Node) delimitedNode).appendChild(node);
            next = next2;
        }
        delimitedNode.setText(this.input.subSequence(getEndIndex(), delimiter.getStartIndex()));
        getNode().insertAfter((Node) delimitedNode);
    }

    public void convertDelimitersToText(int i, Delimiter delimiter) {
        Text text = new Text();
        text.setChars(getTailChars(i));
        Text text2 = new Text();
        text2.setChars(delimiter.getLeadChars(i));
        getNode().insertAfter(text);
        delimiter.getNode().insertBefore(text2);
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public boolean canOpen() {
        return this.canOpen;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public boolean canClose() {
        return this.canClose;
    }

    @Override // com.vladsch.flexmark.parser.delimiter.DelimiterRun
    public int length() {
        return this.numDelims;
    }
}
