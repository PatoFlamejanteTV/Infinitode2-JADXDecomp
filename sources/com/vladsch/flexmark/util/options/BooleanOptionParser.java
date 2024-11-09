package com.vladsch.flexmark.util.options;

import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/BooleanOptionParser.class */
public abstract class BooleanOptionParser<T> implements OptionParser<T> {
    public static final String OPTION_0_PARAMETERS_1_IGNORED = "Option {0} does not have any parameters. {1} was ignored";
    public static final String KEY_OPTION_0_PARAMETERS_1_IGNORED = "options.parser.boolean-option.ignored";
    private final String optionName;

    protected abstract T setOptions(T t);

    protected abstract boolean isOptionSet(T t);

    public BooleanOptionParser(String str) {
        this.optionName = str;
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public String getOptionName() {
        return this.optionName;
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public Pair<T, List<ParsedOption<T>>> parseOption(BasedSequence basedSequence, T t, MessageProvider messageProvider) {
        if (basedSequence.isEmpty()) {
            return new Pair<>(setOptions(t), Collections.singletonList(new ParsedOption(basedSequence, this, ParsedOptionStatus.VALID)));
        }
        if (messageProvider == null) {
            messageProvider = MessageProvider.DEFAULT;
        }
        return new Pair<>(setOptions(t), Collections.singletonList(new ParsedOption(basedSequence, this, ParsedOptionStatus.IGNORED, (List<ParserMessage>) Collections.singletonList(new ParserMessage(basedSequence, ParsedOptionStatus.IGNORED, messageProvider.message(KEY_OPTION_0_PARAMETERS_1_IGNORED, OPTION_0_PARAMETERS_1_IGNORED, this.optionName, basedSequence))))));
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public String getOptionText(T t, T t2) {
        return isOptionSet(t) ? (t2 == null || !isOptionSet(t2)) ? this.optionName : "" : "";
    }
}
