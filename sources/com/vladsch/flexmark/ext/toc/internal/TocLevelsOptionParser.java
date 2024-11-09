package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.util.misc.DelimitedBuilder;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.options.MessageProvider;
import com.vladsch.flexmark.util.options.OptionParser;
import com.vladsch.flexmark.util.options.ParsedOption;
import com.vladsch.flexmark.util.options.ParsedOptionStatus;
import com.vladsch.flexmark.util.options.ParserMessage;
import com.vladsch.flexmark.util.options.ParserParams;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocLevelsOptionParser.class */
public class TocLevelsOptionParser implements OptionParser<TocOptions> {
    public static final String OPTION_0_VALUE_1_NOT_IN_RANGE = "{0} option value {1} is not an integer in the range [1, 6]";
    public static final String KEY_OPTION_0_VALUE_1_NOT_IN_RANGE = "options.parser.toc-levels-option.not-in-range";
    public static final String OPTION_0_VALUE_1_NOT_INTEGER = "{0} option value {1} is not an integer";
    public static final String KEY_OPTION_0_VALUE_1_NOT_INTEGER = "options.parser.toc-levels-option.not-integer";
    public static final String OPTION_0_VALUE_1_TRUNCATED_TO_RANGE_2 = "{0} option value {1} truncated to range [{2}]";
    public static final String KEY_OPTION_0_VALUE_1_TRUNCATED_TO_RANGE_2 = "options.parser.toc-levels-option.truncated-to-range";
    public static final String OPTION_0_VALUE_1_TRUNCATED_TO_EMPTY_RANGE = "{0} option value {1} truncated to empty range []";
    public static final String KEY_OPTION_0_VALUE_1_TRUNCATED_TO_EMPTY_RANGE = "options.parser.toc-levels-option.truncated-to-empty";
    private final String myOptionName;
    private static final Map<Integer, String> TOC_LEVELS_MAP;

    static {
        HashMap hashMap = new HashMap();
        TOC_LEVELS_MAP = hashMap;
        hashMap.put(4, "2");
        TOC_LEVELS_MAP.put(12, "3");
        TOC_LEVELS_MAP.put(28, "4");
        TOC_LEVELS_MAP.put(60, "5");
        TOC_LEVELS_MAP.put(124, "6");
        TOC_LEVELS_MAP.put(2, "1");
        TOC_LEVELS_MAP.put(8, "3-3");
        TOC_LEVELS_MAP.put(16, "4-4");
        TOC_LEVELS_MAP.put(32, "5-5");
        TOC_LEVELS_MAP.put(64, "6-6");
    }

    public TocLevelsOptionParser(String str) {
        this.myOptionName = str;
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public String getOptionName() {
        return this.myOptionName;
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public Pair<TocOptions, List<ParsedOption<TocOptions>>> parseOption(BasedSequence basedSequence, TocOptions tocOptions, MessageProvider messageProvider) {
        Integer num;
        Integer num2;
        TocOptions tocOptions2 = tocOptions;
        BasedSequence[] split = basedSequence.split(",");
        ParserParams parserParams = new ParserParams();
        if (messageProvider == null) {
            messageProvider = MessageProvider.DEFAULT;
        }
        int i = 0;
        MessageProvider messageProvider2 = messageProvider;
        Function function = basedSequence2 -> {
            try {
                if (basedSequence2.isEmpty()) {
                    return null;
                }
                return Integer.valueOf(Integer.parseInt(basedSequence2.toString()));
            } catch (Exception unused) {
                parserParams.add(new ParserMessage(basedSequence2, ParsedOptionStatus.ERROR, messageProvider2.message(KEY_OPTION_0_VALUE_1_NOT_INTEGER, OPTION_0_VALUE_1_NOT_INTEGER, this.myOptionName, basedSequence2)));
                parserParams.skip = true;
                return null;
            }
        };
        for (BasedSequence basedSequence3 : split) {
            BasedSequence[] split2 = basedSequence3.split("-", 2, 10);
            parserParams.skip = false;
            if (split2.length >= 2) {
                num = (Integer) function.apply(split2[0]);
                num2 = split2.length >= 3 ? (Integer) function.apply(split2[2]) : null;
                if (num == null) {
                    num = 1;
                }
                if (num2 == null) {
                    num2 = 6;
                }
            } else {
                Integer num3 = (Integer) function.apply(split2[0]);
                if (num3 != null && num3.intValue() <= 2) {
                    num = num3;
                    num2 = num3;
                } else {
                    num = num3 == null ? null : 2;
                    num2 = num3;
                }
            }
            if (!parserParams.skip) {
                if (num == null) {
                    parserParams.add(new ParserMessage(basedSequence3, ParsedOptionStatus.IGNORED, messageProvider2.message(KEY_OPTION_0_VALUE_1_TRUNCATED_TO_EMPTY_RANGE, OPTION_0_VALUE_1_TRUNCATED_TO_EMPTY_RANGE, this.myOptionName, basedSequence3)));
                } else {
                    if (num2.intValue() < num.intValue()) {
                        int intValue = num.intValue();
                        num = num2;
                        num2 = Integer.valueOf(intValue);
                    }
                    if (num2.intValue() <= 0 || num.intValue() > 6) {
                        if (num.intValue() == num2.intValue()) {
                            parserParams.add(new ParserMessage(basedSequence3, ParsedOptionStatus.IGNORED, messageProvider.message(KEY_OPTION_0_VALUE_1_NOT_IN_RANGE, OPTION_0_VALUE_1_NOT_IN_RANGE, this.myOptionName, basedSequence3)));
                        } else {
                            parserParams.add(new ParserMessage(basedSequence3, ParsedOptionStatus.WARNING, messageProvider2.message(KEY_OPTION_0_VALUE_1_TRUNCATED_TO_EMPTY_RANGE, OPTION_0_VALUE_1_TRUNCATED_TO_EMPTY_RANGE, this.myOptionName, basedSequence3)));
                        }
                    } else {
                        int intValue2 = num.intValue();
                        int intValue3 = num2.intValue();
                        Integer valueOf = Integer.valueOf(Utils.minLimit(num.intValue(), 1));
                        Integer valueOf2 = Integer.valueOf(Utils.maxLimit(num2.intValue(), 6));
                        if (intValue2 != valueOf.intValue() || intValue3 != valueOf2.intValue()) {
                            parserParams.add(new ParserMessage(basedSequence3, ParsedOptionStatus.WEAK_WARNING, messageProvider2.message(KEY_OPTION_0_VALUE_1_TRUNCATED_TO_RANGE_2, OPTION_0_VALUE_1_TRUNCATED_TO_RANGE_2, this.myOptionName, basedSequence3, valueOf + ", " + valueOf2)));
                        }
                        for (int intValue4 = valueOf.intValue(); intValue4 <= valueOf2.intValue(); intValue4++) {
                            i |= 1 << intValue4;
                        }
                    }
                }
            }
        }
        if (i != 0) {
            tocOptions2 = tocOptions2.withLevels(i);
        }
        return new Pair<>(tocOptions2, Collections.singletonList(new ParsedOption(basedSequence, this, parserParams.status, parserParams.messages)));
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public String getOptionText(TocOptions tocOptions, TocOptions tocOptions2) {
        if (tocOptions2 == null || tocOptions.levels != tocOptions2.levels) {
            DelimitedBuilder delimitedBuilder = new DelimitedBuilder();
            delimitedBuilder.append("levels=");
            String str = TOC_LEVELS_MAP.get(Integer.valueOf(tocOptions.levels));
            if (str != null) {
                delimitedBuilder.append(str).mark();
            } else {
                delimitedBuilder.push(",");
                int i = 0;
                int i2 = 0;
                for (int i3 = 1; i3 <= 6; i3++) {
                    if (tocOptions.isLevelIncluded(i3)) {
                        if (i == 0) {
                            i = i3;
                        } else if (i2 + 1 != i3) {
                            if (i != i2) {
                                if (i + 1 == i2) {
                                    delimitedBuilder.append(i).mark().append(i2).mark();
                                } else {
                                    delimitedBuilder.append(i).append('-').append(i2).mark();
                                }
                            } else {
                                delimitedBuilder.append(i).mark();
                            }
                            i = i3;
                        }
                        i2 = i3;
                    }
                }
                if (i != 0) {
                    if (i != i2) {
                        if (i == 2) {
                            delimitedBuilder.append(i2).mark();
                        } else if (i + 1 == i2) {
                            delimitedBuilder.append(i).mark().append(i2).mark();
                        } else {
                            delimitedBuilder.append(i).append('-').append(i2).mark();
                        }
                    } else {
                        delimitedBuilder.append(i).mark();
                    }
                }
                delimitedBuilder.pop().mark();
            }
            return delimitedBuilder.toString();
        }
        return "";
    }
}
