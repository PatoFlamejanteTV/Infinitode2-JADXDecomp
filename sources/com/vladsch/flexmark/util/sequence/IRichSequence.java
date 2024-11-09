package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.sequence.IRichSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.mappers.CharMapper;
import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/IRichSequence.class */
public interface IRichSequence<T extends IRichSequence<T>> extends SequenceUtils, CharSequence, Comparable<CharSequence> {
    boolean equals(Object obj);

    int hashCode();

    T[] emptyArray();

    T nullSequence();

    char lastChar();

    char firstChar();

    char safeCharAt(int i);

    @Override // java.lang.CharSequence
    T subSequence(int i, int i2);

    T safeSubSequence(int i, int i2);

    T safeSubSequence(int i);

    T subSequence(Range range);

    T subSequenceBefore(Range range);

    T subSequenceAfter(Range range);

    T subSequence(int i);

    T endSequence(int i, int i2);

    T endSequence(int i);

    char endCharAt(int i);

    T midSequence(int i, int i2);

    T midSequence(int i);

    char midCharAt(int i);

    T sequenceOf(CharSequence charSequence);

    T sequenceOf(CharSequence charSequence, int i);

    T sequenceOf(CharSequence charSequence, int i, int i2);

    <B extends ISequenceBuilder<B, T>> B getBuilder();

    int indexOf(CharSequence charSequence);

    int indexOf(CharSequence charSequence, int i);

    int indexOf(CharSequence charSequence, int i, int i2);

    int indexOf(char c, int i, int i2);

    int indexOf(char c, int i);

    int indexOf(char c);

    int indexOfAny(CharPredicate charPredicate, int i, int i2);

    int indexOfAny(CharPredicate charPredicate, int i);

    int indexOfAny(CharPredicate charPredicate);

    int indexOfNot(char c, int i, int i2);

    int indexOfNot(char c, int i);

    int indexOfNot(char c);

    int indexOfAnyNot(CharPredicate charPredicate, int i, int i2);

    int indexOfAnyNot(CharPredicate charPredicate, int i);

    int indexOfAnyNot(CharPredicate charPredicate);

    int lastIndexOf(CharSequence charSequence);

    int lastIndexOf(CharSequence charSequence, int i);

    int lastIndexOf(CharSequence charSequence, int i, int i2);

    int lastIndexOf(char c, int i, int i2);

    int lastIndexOf(char c, int i);

    int lastIndexOf(char c);

    int lastIndexOfAny(CharPredicate charPredicate, int i, int i2);

    int lastIndexOfAny(CharPredicate charPredicate, int i);

    int lastIndexOfAny(CharPredicate charPredicate);

    int lastIndexOfNot(char c);

    int lastIndexOfNot(char c, int i);

    int lastIndexOfNot(char c, int i, int i2);

    int lastIndexOfAnyNot(CharPredicate charPredicate, int i, int i2);

    int lastIndexOfAnyNot(CharPredicate charPredicate, int i);

    int lastIndexOfAnyNot(CharPredicate charPredicate);

    int countLeading(CharPredicate charPredicate);

    int countLeadingNot(CharPredicate charPredicate);

    int countLeading(CharPredicate charPredicate, int i);

    int countLeadingNot(CharPredicate charPredicate, int i);

    int countLeading(CharPredicate charPredicate, int i, int i2);

    int countLeadingNot(CharPredicate charPredicate, int i, int i2);

    int countTrailing(CharPredicate charPredicate);

    int countTrailingNot(CharPredicate charPredicate);

    int countTrailing(CharPredicate charPredicate, int i);

    int countTrailingNot(CharPredicate charPredicate, int i);

    int countTrailing(CharPredicate charPredicate, int i, int i2);

    int countTrailingNot(CharPredicate charPredicate, int i, int i2);

    int countLeadingSpace();

    int countLeadingNotSpace();

    int countLeadingSpace(int i);

    int countLeadingNotSpace(int i);

    int countLeadingSpace(int i, int i2);

    int countLeadingNotSpace(int i, int i2);

    int countTrailingSpace();

    int countTrailingNotSpace();

    int countTrailingSpace(int i);

    int countTrailingNotSpace(int i);

    int countTrailingSpace(int i, int i2);

    int countTrailingNotSpace(int i, int i2);

    int countLeadingSpaceTab();

    int countLeadingNotSpaceTab();

    int countLeadingSpaceTab(int i);

    int countLeadingNotSpaceTab(int i);

    int countLeadingSpaceTab(int i, int i2);

    int countLeadingNotSpaceTab(int i, int i2);

    int countTrailingSpaceTab();

    int countTrailingNotSpaceTab();

    int countTrailingSpaceTab(int i);

    int countTrailingNotSpaceTab(int i);

    int countTrailingSpaceTab(int i, int i2);

    int countTrailingNotSpaceTab(int i, int i2);

    int countLeadingWhitespace();

    int countLeadingNotWhitespace();

    int countLeadingWhitespace(int i);

    int countLeadingNotWhitespace(int i);

    int countLeadingWhitespace(int i, int i2);

    int countLeadingNotWhitespace(int i, int i2);

    int countTrailingWhitespace();

    int countTrailingNotWhitespace();

    int countTrailingWhitespace(int i);

    int countTrailingNotWhitespace(int i);

    int countTrailingWhitespace(int i, int i2);

    int countTrailingNotWhitespace(int i, int i2);

    int countOfSpaceTab();

    int countOfNotSpaceTab();

    int countOfWhitespace();

    int countOfNotWhitespace();

    int countOfAny(CharPredicate charPredicate);

    int countOfAnyNot(CharPredicate charPredicate);

    int countOfAny(CharPredicate charPredicate, int i);

    int countOfAnyNot(CharPredicate charPredicate, int i);

    int countOfAny(CharPredicate charPredicate, int i, int i2);

    int countOfAnyNot(CharPredicate charPredicate, int i, int i2);

    int countLeadingColumns(int i, CharPredicate charPredicate);

    Range trimStartRange(int i, CharPredicate charPredicate);

    Range trimEndRange(int i, CharPredicate charPredicate);

    Range trimRange(int i, CharPredicate charPredicate);

    Range trimStartRange(CharPredicate charPredicate);

    Range trimEndRange(CharPredicate charPredicate);

    Range trimRange(CharPredicate charPredicate);

    Range trimStartRange(int i);

    Range trimEndRange(int i);

    Range trimRange(int i);

    Range trimStartRange();

    Range trimEndRange();

    Range trimRange();

    T trimStart(int i, CharPredicate charPredicate);

    T trimEnd(int i, CharPredicate charPredicate);

    T trim(int i, CharPredicate charPredicate);

    T trimStart(int i);

    T trimEnd(int i);

    T trim(int i);

    T trimStart(CharPredicate charPredicate);

    T trimEnd(CharPredicate charPredicate);

    T trim(CharPredicate charPredicate);

    T trimStart();

    T trimEnd();

    T trim();

    T trimmedStart(int i, CharPredicate charPredicate);

    T trimmedEnd(int i, CharPredicate charPredicate);

    Pair<T, T> trimmed(int i, CharPredicate charPredicate);

    T trimmedStart(int i);

    T trimmedEnd(int i);

    Pair<T, T> trimmed(int i);

    T trimmedStart(CharPredicate charPredicate);

    T trimmedEnd(CharPredicate charPredicate);

    Pair<T, T> trimmed(CharPredicate charPredicate);

    T trimmedStart();

    T trimmedEnd();

    Pair<T, T> trimmed();

    T padding(int i, char c);

    T padding(int i);

    T padStart(int i, char c);

    T padEnd(int i, char c);

    T padStart(int i);

    T padEnd(int i);

    boolean isEmpty();

    boolean isBlank();

    boolean isNotEmpty();

    boolean isNotBlank();

    boolean isNull();

    boolean isNotNull();

    T ifNull(T t);

    T ifNullEmptyAfter(T t);

    T ifNullEmptyBefore(T t);

    T nullIfEmpty();

    T nullIfBlank();

    T nullIf(boolean z);

    T nullIf(BiPredicate<? super T, ? super CharSequence> biPredicate, CharSequence... charSequenceArr);

    T nullIfNot(BiPredicate<? super T, ? super CharSequence> biPredicate, CharSequence... charSequenceArr);

    T nullIf(Predicate<? super CharSequence> predicate, CharSequence... charSequenceArr);

    T nullIfNot(Predicate<? super CharSequence> predicate, CharSequence... charSequenceArr);

    T nullIf(CharSequence... charSequenceArr);

    T nullIfNot(CharSequence... charSequenceArr);

    T nullIfStartsWith(CharSequence... charSequenceArr);

    T nullIfNotStartsWith(CharSequence... charSequenceArr);

    T nullIfEndsWith(CharSequence... charSequenceArr);

    T nullIfNotEndsWith(CharSequence... charSequenceArr);

    T nullIfStartsWithIgnoreCase(CharSequence... charSequenceArr);

    T nullIfNotStartsWithIgnoreCase(CharSequence... charSequenceArr);

    T nullIfEndsWithIgnoreCase(CharSequence... charSequenceArr);

    T nullIfNotEndsWithIgnoreCase(CharSequence... charSequenceArr);

    T nullIfStartsWith(boolean z, CharSequence... charSequenceArr);

    T nullIfNotStartsWith(boolean z, CharSequence... charSequenceArr);

    T nullIfEndsWith(boolean z, CharSequence... charSequenceArr);

    T nullIfNotEndsWith(boolean z, CharSequence... charSequenceArr);

    int eolEndLength();

    int eolEndLength(int i);

    int eolStartLength(int i);

    Range eolEndRange(int i);

    Range eolStartRange(int i);

    T trimEOL();

    T trimmedEOL();

    int endOfDelimitedBy(CharSequence charSequence, int i);

    int endOfDelimitedByAny(CharPredicate charPredicate, int i);

    int endOfDelimitedByAnyNot(CharPredicate charPredicate, int i);

    int startOfDelimitedBy(CharSequence charSequence, int i);

    int startOfDelimitedByAny(CharPredicate charPredicate, int i);

    int startOfDelimitedByAnyNot(CharPredicate charPredicate, int i);

    int endOfLine(int i);

    int endOfLineAnyEOL(int i);

    int startOfLine(int i);

    int startOfLineAnyEOL(int i);

    Range lineRangeAt(int i);

    Range lineRangeAtAnyEOL(int i);

    T lineAt(int i);

    T lineAtAnyEOL(int i);

    T trimTailBlankLines();

    T trimLeadBlankLines();

    Range leadingBlankLinesRange(CharPredicate charPredicate, int i, int i2);

    Range trailingBlankLinesRange(CharPredicate charPredicate, int i, int i2);

    Range leadingBlankLinesRange();

    Range leadingBlankLinesRange(int i);

    Range leadingBlankLinesRange(int i, int i2);

    Range trailingBlankLinesRange();

    Range trailingBlankLinesRange(int i);

    Range trailingBlankLinesRange(int i, int i2);

    List<Range> blankLinesRemovedRanges();

    List<Range> blankLinesRemovedRanges(int i);

    List<Range> blankLinesRemovedRanges(int i, int i2);

    List<Range> blankLinesRemovedRanges(CharPredicate charPredicate, int i, int i2);

    T trimToEndOfLine(CharPredicate charPredicate, boolean z, int i);

    T trimToEndOfLine(boolean z, int i);

    T trimToEndOfLine(boolean z);

    T trimToEndOfLine(int i);

    T trimToEndOfLine();

    T trimToStartOfLine(CharPredicate charPredicate, boolean z, int i);

    T trimToStartOfLine(boolean z, int i);

    T trimToStartOfLine(boolean z);

    T trimToStartOfLine(int i);

    T trimToStartOfLine();

    String normalizeEOL();

    String normalizeEndWithEOL();

    boolean matches(CharSequence charSequence);

    boolean matchesIgnoreCase(CharSequence charSequence);

    boolean matches(CharSequence charSequence, boolean z);

    boolean equalsIgnoreCase(Object obj);

    boolean equals(Object obj, boolean z);

    boolean matchChars(CharSequence charSequence);

    boolean matchCharsIgnoreCase(CharSequence charSequence);

    boolean matchChars(CharSequence charSequence, boolean z);

    boolean matchChars(CharSequence charSequence, int i, boolean z);

    boolean matchChars(CharSequence charSequence, int i);

    boolean matchCharsIgnoreCase(CharSequence charSequence, int i);

    int matchedCharCount(CharSequence charSequence, int i, int i2, boolean z, boolean z2);

    int matchedCharCount(CharSequence charSequence, int i, int i2, boolean z);

    int matchedCharCount(CharSequence charSequence, int i, boolean z);

    int matchedCharCount(CharSequence charSequence, int i, int i2);

    int matchedCharCount(CharSequence charSequence, int i);

    int matchedCharCountIgnoreCase(CharSequence charSequence, int i, int i2);

    int matchedCharCountIgnoreCase(CharSequence charSequence, int i);

    boolean matchCharsReversed(CharSequence charSequence, int i, boolean z);

    boolean matchCharsReversed(CharSequence charSequence, int i);

    boolean matchCharsReversedIgnoreCase(CharSequence charSequence, int i);

    int matchedCharCountReversed(CharSequence charSequence, int i, int i2, boolean z);

    int matchedCharCountReversed(CharSequence charSequence, int i, int i2);

    int matchedCharCountReversedIgnoreCase(CharSequence charSequence, int i, int i2);

    int matchedCharCountReversed(CharSequence charSequence, int i, boolean z);

    int matchedCharCountReversed(CharSequence charSequence, int i);

    int matchedCharCountReversedIgnoreCase(CharSequence charSequence, int i);

    boolean endsWith(CharSequence charSequence);

    boolean endsWith(CharPredicate charPredicate);

    boolean endsWithEOL();

    boolean endsWithAnyEOL();

    boolean endsWithSpace();

    boolean endsWithSpaceTab();

    boolean endsWithWhitespace();

    boolean endsWithIgnoreCase(CharSequence charSequence);

    boolean endsWith(CharSequence charSequence, boolean z);

    boolean startsWith(CharSequence charSequence);

    boolean startsWith(CharPredicate charPredicate);

    boolean startsWithEOL();

    boolean startsWithAnyEOL();

    boolean startsWithSpace();

    boolean startsWithSpaceTab();

    boolean startsWithWhitespace();

    boolean startsWithIgnoreCase(CharSequence charSequence);

    boolean startsWith(CharSequence charSequence, boolean z);

    T removeSuffix(CharSequence charSequence);

    T removeSuffixIgnoreCase(CharSequence charSequence);

    T removeSuffix(CharSequence charSequence, boolean z);

    T removePrefix(CharSequence charSequence);

    T removePrefixIgnoreCase(CharSequence charSequence);

    T removePrefix(CharSequence charSequence, boolean z);

    T removeProperSuffix(CharSequence charSequence);

    T removeProperSuffixIgnoreCase(CharSequence charSequence);

    T removeProperSuffix(CharSequence charSequence, boolean z);

    T removeProperPrefix(CharSequence charSequence);

    T removeProperPrefixIgnoreCase(CharSequence charSequence);

    T removeProperPrefix(CharSequence charSequence, boolean z);

    T insert(int i, CharSequence charSequence);

    T delete(int i, int i2);

    T replace(int i, int i2, CharSequence charSequence);

    T replace(CharSequence charSequence, CharSequence charSequence2);

    T toLowerCase();

    T toUpperCase();

    T toMapped(CharMapper charMapper);

    T toNbSp();

    T toSpc();

    String toVisibleWhitespaceString();

    List<T> splitList(CharSequence charSequence, int i, int i2, CharPredicate charPredicate);

    List<T> splitList(CharSequence charSequence, int i, int i2);

    List<T> splitList(CharSequence charSequence);

    T[] split(CharSequence charSequence, int i, int i2, CharPredicate charPredicate);

    T[] split(CharSequence charSequence, int i, int i2);

    T[] split(CharSequence charSequence);

    List<T> splitList(CharSequence charSequence, int i, boolean z, CharPredicate charPredicate);

    List<T> splitList(CharSequence charSequence, boolean z, CharPredicate charPredicate);

    T[] split(CharSequence charSequence, int i, boolean z, CharPredicate charPredicate);

    T[] split(CharSequence charSequence, boolean z, CharPredicate charPredicate);

    T[] splitEOL();

    T[] splitEOL(boolean z);

    List<T> splitListEOL();

    List<T> splitListEOL(boolean z);

    List<T> splitListEOL(boolean z, CharPredicate charPredicate);

    int[] indexOfAll(CharSequence charSequence);

    T prefixWith(CharSequence charSequence);

    T suffixWith(CharSequence charSequence);

    T prefixOnceWith(CharSequence charSequence);

    T suffixOnceWith(CharSequence charSequence);

    T appendEOL();

    T suffixWithEOL();

    T prefixWithEOL();

    T prefixOnceWithEOL();

    T suffixOnceWithEOL();

    T appendSpace();

    T suffixWithSpace();

    T prefixWithSpace();

    T appendSpaces(int i);

    T suffixWithSpaces(int i);

    T prefixWithSpaces(int i);

    T prefixOnceWithSpace();

    T suffixOnceWithSpace();

    T appendTo(StringBuilder sb, CharMapper charMapper, int i, int i2);

    T appendTo(StringBuilder sb, CharMapper charMapper);

    T appendTo(StringBuilder sb, CharMapper charMapper, int i);

    T appendTo(StringBuilder sb, int i, int i2);

    T appendTo(StringBuilder sb);

    T appendTo(StringBuilder sb, int i);

    T appendRangesTo(StringBuilder sb, CharMapper charMapper, Range... rangeArr);

    T appendRangesTo(StringBuilder sb, Range... rangeArr);

    T appendRangesTo(StringBuilder sb, CharMapper charMapper, Iterable<? extends Range> iterable);

    T appendRangesTo(StringBuilder sb, Iterable<? extends Range> iterable);

    T extractRanges(Range... rangeArr);

    T extractRanges(Iterable<Range> iterable);

    T append(CharSequence... charSequenceArr);

    T append(Iterable<? extends CharSequence> iterable);

    Pair<Integer, Integer> lineColumnAtIndex(int i);

    int columnAtIndex(int i);

    boolean isCharAt(int i, CharPredicate charPredicate);

    String toStringOrNull();

    boolean isIn(String[] strArr);

    boolean isIn(Collection<? extends CharSequence> collection);

    @Deprecated
    default int countLeading(char c) {
        return countLeading(CharPredicate.anyOf(c));
    }

    @Deprecated
    default int countLeading() {
        return countLeadingSpaceTab();
    }

    @Deprecated
    default int countTrailing() {
        return countLeadingSpaceTab();
    }

    @Deprecated
    default int countOf(char c) {
        return countOfAny(CharPredicate.anyOf(c));
    }

    @Deprecated
    default T nullIfStartsWithNot(CharSequence... charSequenceArr) {
        return nullIfNotStartsWith(charSequenceArr);
    }

    @Deprecated
    default T nullIfEndsWithNot(CharSequence... charSequenceArr) {
        return nullIfNotEndsWith(charSequenceArr);
    }

    @Deprecated
    default int eolStartLength() {
        return eolEndLength();
    }

    @Deprecated
    default int eolLength(int i) {
        return eolStartLength(i);
    }

    @Deprecated
    default T insert(CharSequence charSequence, int i) {
        return insert(i, charSequence);
    }

    @Deprecated
    default T[] split(char c, int i, int i2) {
        return split(Character.toString(c), i, i2, (CharPredicate) null);
    }

    @Deprecated
    default T[] split(char c, int i) {
        return split(Character.toString(c), i, 0, (CharPredicate) null);
    }

    @Deprecated
    default T[] split(char c) {
        return split(Character.toString(c), 0, 0, (CharPredicate) null);
    }

    @Deprecated
    default Pair<Integer, Integer> getLineColumnAtIndex(int i) {
        return lineColumnAtIndex(i);
    }

    @Deprecated
    default int getColumnAtIndex(int i) {
        return columnAtIndex(i);
    }
}
