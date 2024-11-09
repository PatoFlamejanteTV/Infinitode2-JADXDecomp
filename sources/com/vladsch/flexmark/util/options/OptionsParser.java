package com.vladsch.flexmark.util.options;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.DelimitedBuilder;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/OptionsParser.class */
public class OptionsParser<T> implements OptionParser<T> {
    public static final String OPTION_0_IS_AMBIGUOUS = "Option {0} matches: ";
    public static final String KEY_OPTION_0_IS_AMBIGUOUS = "options.parser.option.ambiguous";
    public static final String OPTION_0_DOES_NOT_MATCH = "Option {0} does not match any of: ";
    public static final String KEY_OPTION_0_DOES_NOT_MATCH = "options.parser.option.unknown";
    private final String optionName;
    private final OptionParser<T>[] parseableOptions;
    private final String optionDelimiter;
    private final String optionValueDelimiter;

    public OptionsParser(String str, OptionParser<T>[] optionParserArr, char c, char c2) {
        this.optionName = str;
        this.parseableOptions = optionParserArr;
        this.optionDelimiter = Character.toString(c);
        this.optionValueDelimiter = Character.toString(c2);
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public String getOptionName() {
        return this.optionName;
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public Pair<T, List<ParsedOption<T>>> parseOption(BasedSequence basedSequence, T t, MessageProvider messageProvider) {
        BasedSequence[] split = basedSequence.split(this.optionDelimiter, 0, 6, (CharPredicate) null);
        T t2 = t;
        if (messageProvider == null) {
            messageProvider = MessageProvider.DEFAULT;
        }
        ArrayList arrayList = new ArrayList(split.length);
        for (BasedSequence basedSequence2 : split) {
            OptionParser<T> optionParser = null;
            DelimitedBuilder delimitedBuilder = null;
            BasedSequence[] split2 = basedSequence2.split(this.optionValueDelimiter, 2, 4, (CharPredicate) null);
            if (split2.length != 0) {
                BasedSequence basedSequence3 = split2[0];
                BasedSequence subSequence = split2.length > 1 ? split2[1] : basedSequence3.subSequence(basedSequence3.length(), basedSequence3.length());
                OptionParser<T>[] optionParserArr = this.parseableOptions;
                int length = optionParserArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    OptionParser<T> optionParser2 = optionParserArr[i];
                    if (optionParser2.getOptionName().equals(basedSequence3.toString())) {
                        optionParser = optionParser2;
                        delimitedBuilder = null;
                        break;
                    }
                    if (optionParser2.getOptionName().startsWith(basedSequence3.toString())) {
                        if (optionParser == null) {
                            optionParser = optionParser2;
                        } else {
                            if (delimitedBuilder == null) {
                                DelimitedBuilder delimitedBuilder2 = new DelimitedBuilder(", ");
                                delimitedBuilder = delimitedBuilder2;
                                delimitedBuilder2.append(messageProvider.message(KEY_OPTION_0_IS_AMBIGUOUS, OPTION_0_IS_AMBIGUOUS, basedSequence3));
                                delimitedBuilder.append(optionParser.getOptionName()).mark();
                            }
                            delimitedBuilder.append(optionParser2.getOptionName()).mark();
                        }
                    }
                    i++;
                }
                if (optionParser != null) {
                    if (delimitedBuilder == null) {
                        Pair<T, List<ParsedOption<T>>> parseOption = optionParser.parseOption(subSequence, t2, messageProvider);
                        t2 = parseOption.getFirst();
                        arrayList.add(new ParsedOption(basedSequence2, this, ParsedOptionStatus.VALID, null, parseOption.getSecond()));
                    } else {
                        arrayList.add(new ParsedOption(basedSequence2, this, ParsedOptionStatus.ERROR, new ParserMessage(basedSequence3, ParsedOptionStatus.ERROR, delimitedBuilder.toString())));
                    }
                } else {
                    DelimitedBuilder delimitedBuilder3 = new DelimitedBuilder(", ");
                    delimitedBuilder3.append(messageProvider.message(KEY_OPTION_0_DOES_NOT_MATCH, OPTION_0_DOES_NOT_MATCH, basedSequence3));
                    appendOptionNames(delimitedBuilder3);
                    arrayList.add(new ParsedOption(basedSequence2, this, ParsedOptionStatus.ERROR, new ParserMessage(basedSequence3, ParsedOptionStatus.ERROR, delimitedBuilder3.toString())));
                }
            }
        }
        return new Pair<>(t2, arrayList);
    }

    public void appendOptionNames(DelimitedBuilder delimitedBuilder) {
        for (OptionParser<T> optionParser : this.parseableOptions) {
            delimitedBuilder.append(optionParser.getOptionName()).mark();
        }
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public String getOptionText(T t, T t2) {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(String.valueOf(this.optionDelimiter));
        for (OptionParser<T> optionParser : this.parseableOptions) {
            String trim = optionParser.getOptionText(t, t2).trim();
            if (!trim.isEmpty()) {
                delimitedBuilder.append(trim).mark();
            }
        }
        return delimitedBuilder.toString();
    }
}
