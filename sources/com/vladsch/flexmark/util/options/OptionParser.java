package com.vladsch.flexmark.util.options;

import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/OptionParser.class */
public interface OptionParser<T> {
    String getOptionName();

    Pair<T, List<ParsedOption<T>>> parseOption(BasedSequence basedSequence, T t, MessageProvider messageProvider);

    String getOptionText(T t, T t2);
}
