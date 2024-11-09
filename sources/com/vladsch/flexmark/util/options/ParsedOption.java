package com.vladsch.flexmark.util.options;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/ParsedOption.class */
public class ParsedOption<T> {
    protected final BasedSequence source;
    protected final OptionParser<T> optionParser;
    protected final ParsedOptionStatus optionResult;
    protected final List<ParserMessage> messages;

    public ParsedOption(BasedSequence basedSequence, OptionParser<T> optionParser, ParsedOptionStatus parsedOptionStatus) {
        this(basedSequence, optionParser, parsedOptionStatus, (List<ParserMessage>) null);
    }

    public ParsedOption(BasedSequence basedSequence, OptionParser<T> optionParser, ParsedOptionStatus parsedOptionStatus, ParserMessage parserMessage) {
        this(basedSequence, optionParser, parsedOptionStatus, (List<ParserMessage>) Collections.singletonList(parserMessage));
    }

    public ParsedOption(BasedSequence basedSequence, OptionParser<T> optionParser, ParsedOptionStatus parsedOptionStatus, List<ParserMessage> list) {
        this(basedSequence, optionParser, parsedOptionStatus, list, null);
    }

    public ParsedOption(BasedSequence basedSequence, OptionParser<T> optionParser, ParsedOptionStatus parsedOptionStatus, List<ParserMessage> list, List<ParsedOption<T>> list2) {
        this.source = basedSequence;
        if (list2 != null) {
            ArrayList arrayList = list != null ? new ArrayList(list) : null;
            for (ParsedOption<T> parsedOption : list2) {
                parsedOptionStatus = parsedOptionStatus.escalate(parsedOption.getOptionResult());
                if (parsedOption.getMessages() != null) {
                    arrayList = arrayList == null ? new ArrayList() : arrayList;
                    arrayList.addAll(parsedOption.getMessages());
                }
            }
            list = arrayList;
        }
        this.optionParser = optionParser;
        this.optionResult = parsedOptionStatus;
        this.messages = list;
    }

    public BasedSequence getSource() {
        return this.source;
    }

    public OptionParser<T> getOptionParser() {
        return this.optionParser;
    }

    public ParsedOptionStatus getOptionResult() {
        return this.optionResult;
    }

    public List<ParserMessage> getMessages() {
        return this.messages;
    }
}
