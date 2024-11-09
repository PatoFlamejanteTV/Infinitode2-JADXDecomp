package com.vladsch.flexmark.parser.delimiter;

import com.vladsch.flexmark.ast.Text;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/delimiter/DelimiterRun.class */
public interface DelimiterRun {
    DelimiterRun getPrevious();

    DelimiterRun getNext();

    char getDelimiterChar();

    Text getNode();

    boolean canOpen();

    boolean canClose();

    int length();
}
