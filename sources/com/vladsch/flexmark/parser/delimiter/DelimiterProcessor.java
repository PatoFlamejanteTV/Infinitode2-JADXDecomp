package com.vladsch.flexmark.parser.delimiter;

import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.core.delimiter.Delimiter;
import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/delimiter/DelimiterProcessor.class */
public interface DelimiterProcessor {
    char getOpeningCharacter();

    char getClosingCharacter();

    int getMinLength();

    int getDelimiterUse(DelimiterRun delimiterRun, DelimiterRun delimiterRun2);

    void process(Delimiter delimiter, Delimiter delimiter2, int i);

    Node unmatchedDelimiterNode(InlineParser inlineParser, DelimiterRun delimiterRun);

    boolean canBeOpener(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6);

    boolean canBeCloser(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6);

    boolean skipNonOpenerCloser();
}
