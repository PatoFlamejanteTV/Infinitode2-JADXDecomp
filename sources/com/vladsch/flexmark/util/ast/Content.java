package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/Content.class */
public interface Content {
    BasedSequence getSpanningChars();

    int getLineCount();

    BasedSequence getLineChars(int i);

    BasedSequence getContentChars();

    BasedSequence getContentChars(int i, int i2);

    List<BasedSequence> getContentLines();

    List<BasedSequence> getContentLines(int i, int i2);
}
