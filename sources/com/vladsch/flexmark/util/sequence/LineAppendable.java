package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineAppendable.class */
public interface LineAppendable extends Appendable, Iterable<LineInfo> {

    @Deprecated
    public static final int ALLOW_LEADING_WHITESPACE = 0;

    @Deprecated
    public static final int ALLOW_LEADING_EOL = 0;
    public static final Options O_CONVERT_TABS = Options.CONVERT_TABS;
    public static final Options O_COLLAPSE_WHITESPACE = Options.COLLAPSE_WHITESPACE;
    public static final Options O_TRIM_TRAILING_WHITESPACE = Options.TRIM_TRAILING_WHITESPACE;
    public static final Options O_PASS_THROUGH = Options.PASS_THROUGH;
    public static final Options O_TRIM_LEADING_WHITESPACE = Options.TRIM_LEADING_WHITESPACE;
    public static final Options O_TRIM_LEADING_EOL = Options.TRIM_LEADING_EOL;
    public static final Options O_PREFIX_PRE_FORMATTED = Options.PREFIX_PRE_FORMATTED;
    public static final BitFieldSet<Options> O_FORMAT_ALL = BitFieldSet.of(O_CONVERT_TABS, O_COLLAPSE_WHITESPACE, O_TRIM_TRAILING_WHITESPACE, O_TRIM_LEADING_WHITESPACE);
    public static final int F_CONVERT_TABS = BitFieldSet.intMask(O_CONVERT_TABS);
    public static final int F_COLLAPSE_WHITESPACE = BitFieldSet.intMask(O_COLLAPSE_WHITESPACE);
    public static final int F_TRIM_TRAILING_WHITESPACE = BitFieldSet.intMask(O_TRIM_TRAILING_WHITESPACE);
    public static final int F_PASS_THROUGH = BitFieldSet.intMask(O_PASS_THROUGH);
    public static final int F_TRIM_LEADING_WHITESPACE = BitFieldSet.intMask(O_TRIM_LEADING_WHITESPACE);
    public static final int F_TRIM_LEADING_EOL = BitFieldSet.intMask(O_TRIM_LEADING_EOL);
    public static final int F_PREFIX_PRE_FORMATTED = BitFieldSet.intMask(O_PREFIX_PRE_FORMATTED);
    public static final int F_FORMAT_ALL = (((F_CONVERT_TABS | F_COLLAPSE_WHITESPACE) | F_TRIM_TRAILING_WHITESPACE) | F_TRIM_LEADING_WHITESPACE) | F_TRIM_LEADING_EOL;
    public static final int F_WHITESPACE_REMOVAL = (F_COLLAPSE_WHITESPACE | F_TRIM_TRAILING_WHITESPACE) | F_TRIM_LEADING_WHITESPACE;

    @Deprecated
    public static final int CONVERT_TABS = F_CONVERT_TABS;

    @Deprecated
    public static final int COLLAPSE_WHITESPACE = F_COLLAPSE_WHITESPACE;

    @Deprecated
    public static final int TRIM_TRAILING_WHITESPACE = F_TRIM_TRAILING_WHITESPACE;

    @Deprecated
    public static final int PASS_THROUGH = F_PASS_THROUGH;

    @Deprecated
    public static final int TRIM_LEADING_WHITESPACE = F_TRIM_LEADING_WHITESPACE;

    @Deprecated
    public static final int PREFIX_PRE_FORMATTED = F_PREFIX_PRE_FORMATTED;

    @Deprecated
    public static final int FORMAT_ALL = F_FORMAT_ALL;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineAppendable$Options.class */
    public enum Options {
        CONVERT_TABS,
        COLLAPSE_WHITESPACE,
        TRIM_TRAILING_WHITESPACE,
        PASS_THROUGH,
        TRIM_LEADING_WHITESPACE,
        TRIM_LEADING_EOL,
        PREFIX_PRE_FORMATTED
    }

    LineAppendable getEmptyAppendable();

    BitFieldSet<Options> getOptionSet();

    LineAppendable pushOptions();

    LineAppendable popOptions();

    LineAppendable changeOptions(int i, int i2);

    ISequenceBuilder<?, ?> getBuilder();

    int getTrailingBlankLines(int i);

    boolean endsWithEOL();

    @Override // java.lang.Appendable
    LineAppendable append(CharSequence charSequence);

    @Override // java.lang.Appendable
    LineAppendable append(CharSequence charSequence, int i, int i2);

    @Override // java.lang.Appendable
    LineAppendable append(char c);

    LineAppendable append(char c, int i);

    LineAppendable append(LineAppendable lineAppendable, int i, int i2, boolean z);

    LineAppendable line();

    LineAppendable lineWithTrailingSpaces(int i);

    LineAppendable lineIf(boolean z);

    LineAppendable blankLine();

    LineAppendable blankLineIf(boolean z);

    LineAppendable blankLine(int i);

    boolean isPreFormatted();

    LineAppendable openPreFormatted(boolean z);

    LineAppendable closePreFormatted();

    LineAppendable indent();

    LineAppendable unIndent();

    LineAppendable unIndentNoEol();

    BasedSequence getIndentPrefix();

    LineAppendable setIndentPrefix(CharSequence charSequence);

    BasedSequence getPrefix();

    BasedSequence getBeforeEolPrefix();

    LineAppendable addPrefix(CharSequence charSequence, boolean z);

    LineAppendable setPrefix(CharSequence charSequence, boolean z);

    LineAppendable pushPrefix();

    LineAppendable popPrefix(boolean z);

    int getAfterEolPrefixDelta();

    int column();

    int offset();

    int offsetWithPending();

    boolean isPendingSpace();

    int getPendingSpace();

    int getPendingEOL();

    LineAppendable lineOnFirstText(boolean z);

    LineAppendable addIndentOnFirstEOL(Runnable runnable);

    LineAppendable removeIndentOnFirstEOL(Runnable runnable);

    int getLineCount();

    int getLineCountWithPending();

    LineInfo getLineInfo(int i);

    BasedSequence getLine(int i);

    Iterator<LineInfo> iterator();

    Iterable<BasedSequence> getLines(int i, int i2, int i3, boolean z);

    Iterable<LineInfo> getLinesInfo(int i, int i2, int i3);

    void setPrefixLength(int i, int i2);

    void setLine(int i, CharSequence charSequence, CharSequence charSequence2);

    void insertLine(int i, CharSequence charSequence, CharSequence charSequence2);

    LineAppendable removeLines(int i, int i2);

    String toString(int i, int i2, boolean z);

    CharSequence toSequence(int i, int i2, boolean z);

    <T extends Appendable> T appendTo(T t, boolean z, int i, int i2, int i3, int i4);

    LineAppendable removeExtraBlankLines(int i, int i2, int i3, int i4);

    static BitFieldSet<Options> toOptionSet(int i) {
        return BitFieldSet.of(Options.class, i);
    }

    static BitFieldSet<Options> toOptionSet(Options... optionsArr) {
        return BitFieldSet.of(Options.class, (Enum[]) optionsArr);
    }

    default int getOptions() {
        return getOptionSet().toInt();
    }

    default LineAppendable copyAppendable(int i, int i2, boolean z) {
        return getEmptyAppendable().append(this, i, i2, z);
    }

    default LineAppendable copyAppendable(int i, int i2) {
        return getEmptyAppendable().append(this, i, i2, false);
    }

    default LineAppendable copyAppendable(int i) {
        return getEmptyAppendable().append(this, i, Integer.MAX_VALUE, false);
    }

    default LineAppendable copyAppendable() {
        return getEmptyAppendable().append(this, 0, Integer.MAX_VALUE, false);
    }

    default LineAppendable copyAppendable(boolean z) {
        return getEmptyAppendable().append(this, 0, Integer.MAX_VALUE, z);
    }

    default LineAppendable noTrimLeading() {
        return changeOptions(0, F_TRIM_LEADING_WHITESPACE);
    }

    default LineAppendable trimLeading() {
        return changeOptions(F_TRIM_LEADING_WHITESPACE, 0);
    }

    default LineAppendable preserveSpaces() {
        return changeOptions(0, F_TRIM_LEADING_WHITESPACE | F_COLLAPSE_WHITESPACE);
    }

    default LineAppendable noPreserveSpaces() {
        return changeOptions(F_TRIM_LEADING_WHITESPACE | F_COLLAPSE_WHITESPACE, 0);
    }

    default LineAppendable removeOptions(int i) {
        return changeOptions(0, i);
    }

    default LineAppendable addOptions(int i) {
        return changeOptions(i, 0);
    }

    default LineAppendable setOptions(int i) {
        return setOptions(toOptionSet(i));
    }

    default LineAppendable setOptions(Options... optionsArr) {
        return setOptions(toOptionSet(optionsArr).toInt());
    }

    default LineAppendable setOptions(BitFieldSet<Options> bitFieldSet) {
        return setOptions(bitFieldSet.toInt());
    }

    default int getTrailingBlankLines() {
        return getTrailingBlankLines(getLineCountWithPending());
    }

    default LineAppendable appendAll(Iterable<CharSequence> iterable) {
        Iterator<CharSequence> it = iterable.iterator();
        while (it.hasNext()) {
            append(it.next());
        }
        return this;
    }

    default LineAppendable append(LineAppendable lineAppendable) {
        return append(lineAppendable, 0, Integer.MAX_VALUE, true);
    }

    default LineAppendable append(LineAppendable lineAppendable, boolean z) {
        return append(lineAppendable, 0, Integer.MAX_VALUE, z);
    }

    default LineAppendable addPrefix(CharSequence charSequence) {
        return addPrefix(charSequence, getPendingEOL() == 0);
    }

    default LineAppendable setPrefix(CharSequence charSequence) {
        return setPrefix(charSequence, getPendingEOL() == 0);
    }

    default LineAppendable popPrefix() {
        return popPrefix(false);
    }

    default LineAppendable setLineOnFirstText() {
        return lineOnFirstText(true);
    }

    default LineAppendable clearLineOnFirstText() {
        return lineOnFirstText(false);
    }

    default boolean isEmpty() {
        return getLineCountWithPending() == 0;
    }

    default boolean isNotEmpty() {
        return getLineCountWithPending() != 0;
    }

    default LineInfo get(int i) {
        return getLineInfo(i);
    }

    default Iterable<BasedSequence> getLines(int i) {
        return getLines(i, 0, Integer.MAX_VALUE, true);
    }

    default Iterable<BasedSequence> getLines() {
        return getLines(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, true);
    }

    default Iterable<BasedSequence> getLines(int i, boolean z) {
        return getLines(i, 0, Integer.MAX_VALUE, z);
    }

    default Iterable<BasedSequence> getLines(boolean z) {
        return getLines(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, z);
    }

    default Iterable<LineInfo> getLinesInfo(int i) {
        return getLinesInfo(i, 0, Integer.MAX_VALUE);
    }

    default Iterable<LineInfo> getLinesInfo() {
        return getLinesInfo(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
    }

    default BasedSequence getLineContent(int i) {
        LineInfo lineInfo = getLineInfo(i);
        return getLine(i).subSequence(lineInfo.prefixLength, lineInfo.prefixLength + lineInfo.textLength);
    }

    default BasedSequence getLinePrefix(int i) {
        return getLine(i).subSequence(0, getLineInfo(i).prefixLength);
    }

    default String toString(int i, int i2) {
        return toString(i, i2, true);
    }

    default String toString(int i, boolean z) {
        return toString(i, i, z);
    }

    default String toString(boolean z) {
        return toString(Integer.MAX_VALUE, Integer.MAX_VALUE, z);
    }

    default String toString(int i) {
        return toString(i, i, true);
    }

    default CharSequence toSequence(int i, int i2) {
        return toSequence(i, i2, true);
    }

    default CharSequence toSequence(int i, boolean z) {
        return toSequence(i, i, z);
    }

    default CharSequence toSequence(boolean z) {
        return toSequence(Integer.MAX_VALUE, Integer.MAX_VALUE, z);
    }

    default CharSequence toSequence() {
        return toSequence(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
    }

    @Deprecated
    default <T extends Appendable> T appendTo(T t, int i) {
        return (T) appendTo(t, Integer.MAX_VALUE, i);
    }

    default <T extends Appendable> T appendTo(T t, int i, int i2, int i3, int i4) {
        return (T) appendTo(t, true, i, i2, i3, i4);
    }

    default <T extends Appendable> T appendTo(T t, int i, int i2) {
        return (T) appendTo(t, i, i2, 0, Integer.MAX_VALUE);
    }

    default <T extends Appendable> T appendTo(T t) {
        return (T) appendTo(t, 0, 0, 0, Integer.MAX_VALUE);
    }

    default <T extends Appendable> T appendToSilently(T t, boolean z, int i, int i2, int i3, int i4) {
        try {
            appendTo(t, z, i, i2, i3, i4);
        } catch (IOException unused) {
        }
        return t;
    }

    default <T extends Appendable> T appendToSilently(T t, int i, int i2, int i3, int i4) {
        appendToSilently(t, true, i, i2, i3, i4);
        return t;
    }

    default <T extends Appendable> T appendToSilently(T t, int i, int i2) {
        appendToSilently(t, i, i2, 0, Integer.MAX_VALUE);
        return t;
    }

    default <T extends Appendable> T appendToSilently(T t) {
        return (T) appendToSilently(t, 0, 0, 0, Integer.MAX_VALUE);
    }

    default LineAppendable removeExtraBlankLines(int i, int i2) {
        return removeExtraBlankLines(i, i2, 0, Integer.MAX_VALUE);
    }

    static CharSequence combinedPrefix(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != null && charSequence.length() > 0 && charSequence2 != null && charSequence2.length() > 0) {
            return String.valueOf(charSequence) + ((Object) charSequence2);
        }
        if (charSequence != null && charSequence.length() > 0) {
            return charSequence;
        }
        if (charSequence2 != null && charSequence2.length() > 0) {
            return charSequence2;
        }
        return BasedSequence.NULL;
    }
}
