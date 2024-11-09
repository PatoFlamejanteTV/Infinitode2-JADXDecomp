package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.collection.iteration.ArrayIterable;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.IRichSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.mappers.ChangeCase;
import com.vladsch.flexmark.util.sequence.mappers.CharMapper;
import com.vladsch.flexmark.util.sequence.mappers.SpaceMapper;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/IRichSequenceBase.class */
public abstract class IRichSequenceBase<T extends IRichSequence<T>> implements IRichSequence<T> {
    private int hash;

    public IRichSequenceBase(int i) {
        this.hash = i;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean equals(Object obj) {
        return SequenceUtils.equals(this, obj);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int hashCode() {
        int i = this.hash;
        int i2 = i;
        if (i == 0 && length() > 0) {
            i2 = SequenceUtils.hashCode(this);
            this.hash = i2;
        }
        return i2;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean equalsIgnoreCase(Object obj) {
        if (this != obj) {
            return (obj instanceof CharSequence) && ((CharSequence) obj).length() == length() && matchChars((CharSequence) obj, 0, true);
        }
        return true;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean equals(Object obj, boolean z) {
        if (this != obj) {
            return (obj instanceof CharSequence) && ((CharSequence) obj).length() == length() && matchChars((CharSequence) obj, 0, z);
        }
        return true;
    }

    @Override // java.lang.Comparable
    public int compareTo(CharSequence charSequence) {
        return SequenceUtils.compare(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T sequenceOf(CharSequence charSequence) {
        return charSequence == null ? nullSequence() : sequenceOf(charSequence, 0, charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T sequenceOf(CharSequence charSequence, int i) {
        return charSequence == null ? nullSequence() : sequenceOf(charSequence, i, charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T subSequence(int i) {
        return subSequence(i, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T subSequence(Range range) {
        return range.isNull() ? this : subSequence(range.getStart(), range.getEnd());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T subSequenceBefore(Range range) {
        return range.isNull() ? nullSequence() : subSequence(0, range.getStart());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T subSequenceAfter(Range range) {
        return range.isNull() ? nullSequence() : subSequence(range.getEnd());
    }

    public final Pair<T, T> subSequenceBeforeAfter(Range range) {
        return Pair.of(subSequenceBefore(range), subSequenceAfter(range));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T endSequence(int i, int i2) {
        int length = length();
        int i3 = length - i;
        int rangeLimit = Utils.rangeLimit(length - i2, 0, length);
        return subSequence(Utils.rangeLimit(i3, 0, rangeLimit), rangeLimit);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T endSequence(int i) {
        return endSequence(i, 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final char endCharAt(int i) {
        int length = length();
        if (i < 0 || i >= length) {
            return (char) 0;
        }
        return charAt(length - i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T midSequence(int i, int i2) {
        int length = length();
        int i3 = i < 0 ? length + i : i;
        int rangeLimit = Utils.rangeLimit(i2 < 0 ? length + i2 : i2, 0, length);
        return subSequence(Utils.rangeLimit(i3, 0, rangeLimit), rangeLimit);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T midSequence(int i) {
        return midSequence(i, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final char midCharAt(int i) {
        int length = length();
        if (i < (-length) || i >= length) {
            return (char) 0;
        }
        return charAt(i < 0 ? length + i : i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final char lastChar() {
        if (isEmpty()) {
            return (char) 0;
        }
        return charAt(length() - 1);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final char firstChar() {
        if (isEmpty()) {
            return (char) 0;
        }
        return charAt(0);
    }

    public final void validateIndex(int i) {
        SequenceUtils.validateIndex(i, length());
    }

    public final void validateIndexInclusiveEnd(int i) {
        SequenceUtils.validateIndexInclusiveEnd(i, length());
    }

    public final void validateStartEnd(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public char safeCharAt(int i) {
        if (i < 0 || i >= length()) {
            return (char) 0;
        }
        return charAt(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T safeSubSequence(int i, int i2) {
        int length = length();
        int max = Math.max(0, Math.min(length, i));
        return subSequence(max, Math.max(max, Math.min(length, i2)));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T safeSubSequence(int i) {
        int length = length();
        return subSequence(Math.max(0, Math.min(length, i)), length);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public boolean isCharAt(int i, CharPredicate charPredicate) {
        return charPredicate.test(safeCharAt(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public String toStringOrNull() {
        if (isNull()) {
            return null;
        }
        return toString();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOf(CharSequence charSequence) {
        return SequenceUtils.indexOf(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOf(CharSequence charSequence, int i) {
        return SequenceUtils.indexOf(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOf(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.indexOf(this, charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOf(char c) {
        return SequenceUtils.indexOf(this, c);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOf(char c, int i) {
        return SequenceUtils.indexOf(this, c, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfAny(CharPredicate charPredicate) {
        return SequenceUtils.indexOfAny(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfAny(CharPredicate charPredicate, int i) {
        return SequenceUtils.indexOfAny(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfAnyNot(CharPredicate charPredicate) {
        return SequenceUtils.indexOfAnyNot(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfAnyNot(CharPredicate charPredicate, int i) {
        return SequenceUtils.indexOfAnyNot(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfAnyNot(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.indexOfAnyNot(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfNot(char c) {
        return SequenceUtils.indexOfNot(this, c);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfNot(char c, int i) {
        return SequenceUtils.indexOfNot(this, c, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOf(char c) {
        return SequenceUtils.lastIndexOf(this, c);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOf(char c, int i) {
        return SequenceUtils.lastIndexOf(this, c, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfNot(char c) {
        return SequenceUtils.lastIndexOfNot(this, c);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfNot(char c, int i) {
        return SequenceUtils.lastIndexOfNot(this, c, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOf(CharSequence charSequence) {
        return SequenceUtils.lastIndexOf(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOf(CharSequence charSequence, int i) {
        return SequenceUtils.lastIndexOf(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfAny(CharPredicate charPredicate, int i) {
        return SequenceUtils.lastIndexOfAny(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfAny(CharPredicate charPredicate) {
        return SequenceUtils.lastIndexOfAny(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfAnyNot(CharPredicate charPredicate) {
        return SequenceUtils.lastIndexOfAnyNot(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfAnyNot(CharPredicate charPredicate, int i) {
        return SequenceUtils.lastIndexOfAnyNot(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfAnyNot(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.lastIndexOfAnyNot(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOf(char c, int i, int i2) {
        return SequenceUtils.indexOf(this, c, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfNot(char c, int i, int i2) {
        return SequenceUtils.indexOfNot(this, c, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int indexOfAny(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.indexOfAny(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOf(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.lastIndexOf(this, charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOf(char c, int i, int i2) {
        return SequenceUtils.lastIndexOf(this, c, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfNot(char c, int i, int i2) {
        return SequenceUtils.lastIndexOfNot(this, c, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int lastIndexOfAny(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.lastIndexOfAny(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfSpaceTab() {
        return SequenceUtils.countOfSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfNotSpaceTab() {
        return SequenceUtils.countOfNotSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfWhitespace() {
        return SequenceUtils.countOfWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfNotWhitespace() {
        return SequenceUtils.countOfNotWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfAny(CharPredicate charPredicate, int i) {
        return SequenceUtils.countOfAny(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfAny(CharPredicate charPredicate) {
        return SequenceUtils.countOfAny(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfAnyNot(CharPredicate charPredicate, int i) {
        return SequenceUtils.countOfAnyNot(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfAnyNot(CharPredicate charPredicate) {
        return SequenceUtils.countOfAnyNot(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfAny(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.countOfAny(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countOfAnyNot(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.countOfAnyNot(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeading(CharPredicate charPredicate) {
        return SequenceUtils.countLeading(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeading(CharPredicate charPredicate, int i) {
        return SequenceUtils.countLeading(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNot(CharPredicate charPredicate) {
        return SequenceUtils.countLeadingNot(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNot(CharPredicate charPredicate, int i) {
        return SequenceUtils.countLeadingNot(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailing(CharPredicate charPredicate) {
        return SequenceUtils.countTrailing(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailing(CharPredicate charPredicate, int i) {
        return SequenceUtils.countTrailing(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNot(CharPredicate charPredicate) {
        return SequenceUtils.countTrailingNot(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNot(CharPredicate charPredicate, int i) {
        return SequenceUtils.countTrailingNot(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNot(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.countLeadingNot(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNot(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.countTrailingNot(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeading(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.countLeading(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingColumns(int i, CharPredicate charPredicate) {
        return SequenceUtils.countLeadingColumns(this, i, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailing(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.countTrailing(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingSpace() {
        return SequenceUtils.countLeadingSpace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotSpace() {
        return SequenceUtils.countLeadingNotSpace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingSpace(int i) {
        return SequenceUtils.countLeadingSpace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotSpace(int i) {
        return SequenceUtils.countLeadingNotSpace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingSpace(int i, int i2) {
        return SequenceUtils.countLeadingSpace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotSpace(int i, int i2) {
        return SequenceUtils.countLeadingNotSpace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingSpace() {
        return SequenceUtils.countTrailingSpace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotSpace() {
        return SequenceUtils.countTrailingNotSpace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingSpace(int i) {
        return SequenceUtils.countTrailingSpace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotSpace(int i) {
        return SequenceUtils.countTrailingNotSpace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingSpace(int i, int i2) {
        return SequenceUtils.countTrailingSpace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotSpace(int i, int i2) {
        return SequenceUtils.countTrailingNotSpace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingSpaceTab() {
        return SequenceUtils.countLeadingSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotSpaceTab() {
        return SequenceUtils.countLeadingNotSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingSpaceTab(int i) {
        return SequenceUtils.countLeadingSpaceTab(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotSpaceTab(int i) {
        return SequenceUtils.countLeadingNotSpaceTab(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingSpaceTab(int i, int i2) {
        return SequenceUtils.countLeadingSpaceTab(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotSpaceTab(int i, int i2) {
        return SequenceUtils.countLeadingNotSpaceTab(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingSpaceTab() {
        return SequenceUtils.countTrailingSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotSpaceTab() {
        return SequenceUtils.countTrailingNotSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingSpaceTab(int i) {
        return SequenceUtils.countTrailingSpaceTab(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotSpaceTab(int i) {
        return SequenceUtils.countTrailingNotSpaceTab(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingSpaceTab(int i, int i2) {
        return SequenceUtils.countTrailingSpaceTab(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotSpaceTab(int i, int i2) {
        return SequenceUtils.countTrailingNotSpaceTab(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingWhitespace() {
        return SequenceUtils.countLeadingWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotWhitespace() {
        return SequenceUtils.countLeadingNotWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingWhitespace(int i) {
        return SequenceUtils.countLeadingWhitespace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotWhitespace(int i) {
        return SequenceUtils.countLeadingNotWhitespace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingWhitespace(int i, int i2) {
        return SequenceUtils.countLeadingWhitespace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countLeadingNotWhitespace(int i, int i2) {
        return SequenceUtils.countLeadingNotWhitespace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingWhitespace() {
        return SequenceUtils.countTrailingWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotWhitespace() {
        return SequenceUtils.countTrailingNotWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingWhitespace(int i) {
        return SequenceUtils.countTrailingWhitespace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotWhitespace(int i) {
        return SequenceUtils.countTrailingNotWhitespace(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingWhitespace(int i, int i2) {
        return SequenceUtils.countTrailingWhitespace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int countTrailingNotWhitespace(int i, int i2) {
        return SequenceUtils.countTrailingNotWhitespace(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimStart(CharPredicate charPredicate) {
        return subSequence(trimStartRange(0, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedStart(CharPredicate charPredicate) {
        return trimmedStart(0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimEnd(CharPredicate charPredicate) {
        return trimEnd(0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedEnd(CharPredicate charPredicate) {
        return trimmedEnd(0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trim(CharPredicate charPredicate) {
        return trim(0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Pair<T, T> trimmed(CharPredicate charPredicate) {
        return trimmed(0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimStart(int i) {
        return trimStart(i, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedStart(int i) {
        return trimmedStart(i, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimEnd(int i) {
        return trimEnd(i, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedEnd(int i) {
        return trimmedEnd(i, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trim(int i) {
        return trim(i, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Pair<T, T> trimmed(int i) {
        return trimmed(i, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimStart() {
        return trimStart(0, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedStart() {
        return trimmedStart(0, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimEnd() {
        return trimEnd(0, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedEnd() {
        return trimmedEnd(0, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trim() {
        return trim(0, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Pair<T, T> trimmed() {
        return trimmed(0, CharPredicate.WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimStart(int i, CharPredicate charPredicate) {
        return subSequence(trimStartRange(i, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedStart(int i, CharPredicate charPredicate) {
        return subSequenceBefore(trimStartRange(i, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimEnd(int i, CharPredicate charPredicate) {
        return subSequence(trimEndRange(i, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedEnd(int i, CharPredicate charPredicate) {
        return subSequenceAfter(trimEndRange(i, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trim(int i, CharPredicate charPredicate) {
        return subSequence(trimRange(i, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Pair<T, T> trimmed(int i, CharPredicate charPredicate) {
        return subSequenceBeforeAfter(trimRange(i, charPredicate));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimStartRange(int i, CharPredicate charPredicate) {
        return SequenceUtils.trimStartRange(this, i, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimEndRange(int i, CharPredicate charPredicate) {
        return SequenceUtils.trimEndRange(this, i, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimRange(int i, CharPredicate charPredicate) {
        return SequenceUtils.trimRange(this, i, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimStartRange(CharPredicate charPredicate) {
        return SequenceUtils.trimStartRange(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimEndRange(CharPredicate charPredicate) {
        return SequenceUtils.trimEndRange(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimRange(CharPredicate charPredicate) {
        return SequenceUtils.trimRange(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimStartRange(int i) {
        return SequenceUtils.trimStartRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimEndRange(int i) {
        return SequenceUtils.trimEndRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimRange(int i) {
        return SequenceUtils.trimRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimStartRange() {
        return SequenceUtils.trimStartRange(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimEndRange() {
        return SequenceUtils.trimEndRange(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trimRange() {
        return SequenceUtils.trimRange(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T padding(int i, char c) {
        return i <= length() ? nullSequence() : sequenceOf(RepeatedSequence.repeatOf(c, i - length()));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T padding(int i) {
        return padStart(i, ' ');
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T padStart(int i, char c) {
        T padding = padding(i, c);
        return padding.isEmpty() ? this : (T) getBuilder().append((CharSequence) padding).append((CharSequence) this).toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T padEnd(int i, char c) {
        T padding = padding(i, c);
        return padding.isEmpty() ? this : (T) getBuilder().append((CharSequence) this).append((CharSequence) padding).toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T padStart(int i) {
        return padStart(i, ' ');
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T padEnd(int i) {
        return padEnd(i, ' ');
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int eolEndLength() {
        return SequenceUtils.eolEndLength(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int eolEndLength(int i) {
        return SequenceUtils.eolEndLength(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int eolStartLength(int i) {
        return SequenceUtils.eolStartLength(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int endOfLine(int i) {
        return SequenceUtils.endOfLine(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int endOfLineAnyEOL(int i) {
        return SequenceUtils.endOfLineAnyEOL(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int startOfLine(int i) {
        return SequenceUtils.startOfLine(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int startOfLineAnyEOL(int i) {
        return SequenceUtils.startOfLineAnyEOL(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int startOfDelimitedByAnyNot(CharPredicate charPredicate, int i) {
        return startOfDelimitedByAny(charPredicate.negate(), i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int endOfDelimitedByAnyNot(CharPredicate charPredicate, int i) {
        return endOfDelimitedByAny(charPredicate.negate(), i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int startOfDelimitedBy(CharSequence charSequence, int i) {
        return SequenceUtils.startOfDelimitedBy(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int startOfDelimitedByAny(CharPredicate charPredicate, int i) {
        return SequenceUtils.startOfDelimitedByAny(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int endOfDelimitedBy(CharSequence charSequence, int i) {
        return SequenceUtils.endOfDelimitedBy(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int endOfDelimitedByAny(CharPredicate charPredicate, int i) {
        return SequenceUtils.endOfDelimitedByAny(this, charPredicate, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range lineRangeAt(int i) {
        return SequenceUtils.lineRangeAt(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range lineRangeAtAnyEOL(int i) {
        return SequenceUtils.lineRangeAtAnyEOL(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T lineAt(int i) {
        return subSequence(lineRangeAt(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T lineAtAnyEOL(int i) {
        return subSequence(lineRangeAtAnyEOL(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range eolEndRange(int i) {
        return SequenceUtils.eolEndRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public Range eolStartRange(int i) {
        return SequenceUtils.eolStartRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimEOL() {
        int eolEndLength = eolEndLength();
        return eolEndLength > 0 ? subSequence(0, length() - eolEndLength) : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimmedEOL() {
        int eolEndLength = eolEndLength();
        return eolEndLength > 0 ? subSequence(length() - eolEndLength) : nullSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimTailBlankLines() {
        Range trailingBlankLinesRange = trailingBlankLinesRange();
        return trailingBlankLinesRange.isNull() ? this : subSequenceBefore(trailingBlankLinesRange);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T trimLeadBlankLines() {
        Range leadingBlankLinesRange = leadingBlankLinesRange();
        return leadingBlankLinesRange.isNull() ? this : subSequenceAfter(leadingBlankLinesRange);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range leadingBlankLinesRange() {
        return SequenceUtils.leadingBlankLinesRange(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range leadingBlankLinesRange(int i) {
        return SequenceUtils.leadingBlankLinesRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range leadingBlankLinesRange(int i, int i2) {
        return SequenceUtils.leadingBlankLinesRange(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trailingBlankLinesRange() {
        return SequenceUtils.trailingBlankLinesRange(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trailingBlankLinesRange(int i) {
        return SequenceUtils.trailingBlankLinesRange(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trailingBlankLinesRange(int i, int i2) {
        return SequenceUtils.trailingBlankLinesRange(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range trailingBlankLinesRange(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.trailingBlankLinesRange(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Range leadingBlankLinesRange(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.leadingBlankLinesRange(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<Range> blankLinesRemovedRanges() {
        return SequenceUtils.blankLinesRemovedRanges(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<Range> blankLinesRemovedRanges(int i) {
        return SequenceUtils.blankLinesRemovedRanges(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<Range> blankLinesRemovedRanges(int i, int i2) {
        return SequenceUtils.blankLinesRemovedRanges(this, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<Range> blankLinesRemovedRanges(CharPredicate charPredicate, int i, int i2) {
        return SequenceUtils.blankLinesRemovedRanges(this, charPredicate, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToEndOfLine(boolean z) {
        return trimToEndOfLine(CharPredicate.EOL, z, 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToEndOfLine(int i) {
        return trimToEndOfLine(CharPredicate.EOL, false, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToEndOfLine() {
        return trimToEndOfLine(CharPredicate.EOL, false, 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToEndOfLine(boolean z, int i) {
        return trimToEndOfLine(CharPredicate.EOL, z, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToStartOfLine(boolean z) {
        return trimToStartOfLine(CharPredicate.EOL, z, 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToStartOfLine(int i) {
        return trimToStartOfLine(CharPredicate.EOL, false, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToStartOfLine() {
        return trimToStartOfLine(CharPredicate.EOL, false, 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToStartOfLine(boolean z, int i) {
        return trimToStartOfLine(CharPredicate.EOL, z, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToEndOfLine(CharPredicate charPredicate, boolean z, int i) {
        int endOfDelimitedByAny = endOfDelimitedByAny(charPredicate, i);
        if (endOfDelimitedByAny < length()) {
            return subSequence(0, z ? endOfDelimitedByAny + eolStartLength(endOfDelimitedByAny) : endOfDelimitedByAny);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T trimToStartOfLine(CharPredicate charPredicate, boolean z, int i) {
        int startOfDelimitedByAny = startOfDelimitedByAny(charPredicate, i);
        if (startOfDelimitedByAny > 0) {
            return subSequence(z ? startOfDelimitedByAny - eolEndLength(startOfDelimitedByAny - 1) : startOfDelimitedByAny);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T ifNull(T t) {
        return isNull() ? t : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T ifNullEmptyAfter(T t) {
        return isNull() ? (T) t.subSequence(t.length(), t.length()) : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T ifNullEmptyBefore(T t) {
        return isNull() ? (T) t.subSequence(0, 0) : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfEmpty() {
        return isEmpty() ? nullSequence() : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfBlank() {
        return isBlank() ? nullSequence() : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIf(boolean z) {
        return z ? nullSequence() : this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNot(BiPredicate<? super T, ? super CharSequence> biPredicate, CharSequence... charSequenceArr) {
        return nullIf(biPredicate.negate(), charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIf(Predicate<? super CharSequence> predicate, CharSequence... charSequenceArr) {
        return nullIf((iRichSequence, charSequence) -> {
            return predicate.test(charSequence);
        }, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNot(Predicate<? super CharSequence> predicate, CharSequence... charSequenceArr) {
        return nullIfNot((iRichSequence, charSequence) -> {
            return predicate.test(charSequence);
        }, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIf(CharSequence... charSequenceArr) {
        return nullIf(this::matches, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNot(CharSequence... charSequenceArr) {
        return nullIfNot(this::matches, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfStartsWith(CharSequence... charSequenceArr) {
        return nullIf(this::startsWith, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNotStartsWith(CharSequence... charSequenceArr) {
        return nullIfNot(this::startsWith, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfEndsWith(CharSequence... charSequenceArr) {
        return nullIf(this::endsWith, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNotEndsWith(CharSequence... charSequenceArr) {
        return nullIfNot(this::endsWith, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfStartsWithIgnoreCase(CharSequence... charSequenceArr) {
        return nullIf(this::startsWithIgnoreCase, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNotStartsWithIgnoreCase(CharSequence... charSequenceArr) {
        return nullIfNot(this::startsWithIgnoreCase, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfEndsWithIgnoreCase(CharSequence... charSequenceArr) {
        return nullIf(this::endsWithIgnoreCase, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNotEndsWithIgnoreCase(CharSequence... charSequenceArr) {
        return nullIfNot(this::endsWithIgnoreCase, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfStartsWith(boolean z, CharSequence... charSequenceArr) {
        return nullIf(charSequence -> {
            return startsWith(charSequence, z);
        }, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNotStartsWith(boolean z, CharSequence... charSequenceArr) {
        return nullIfNot(charSequence -> {
            return startsWith(charSequence, z);
        }, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfEndsWith(boolean z, CharSequence... charSequenceArr) {
        return nullIf(charSequence -> {
            return endsWith(charSequence, z);
        }, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIfNotEndsWith(boolean z, CharSequence... charSequenceArr) {
        return nullIfNot(charSequence -> {
            return endsWith(charSequence, z);
        }, charSequenceArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T nullIf(BiPredicate<? super T, ? super CharSequence> biPredicate, CharSequence... charSequenceArr) {
        for (CharSequence charSequence : charSequenceArr) {
            if (biPredicate.test(this, charSequence)) {
                return nullSequence();
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean isNull() {
        return this == nullSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean isNotNull() {
        return this != nullSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean isEmpty() {
        return SequenceUtils.isEmpty(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean isBlank() {
        return SequenceUtils.isBlank(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean isNotEmpty() {
        return SequenceUtils.isNotEmpty(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean isNotBlank() {
        return SequenceUtils.isNotBlank(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWith(CharSequence charSequence) {
        return SequenceUtils.endsWith(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWith(CharSequence charSequence, boolean z) {
        return SequenceUtils.endsWith(this, charSequence, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWith(CharSequence charSequence) {
        return SequenceUtils.startsWith(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWith(CharSequence charSequence, boolean z) {
        return SequenceUtils.startsWith(this, charSequence, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWith(CharPredicate charPredicate) {
        return SequenceUtils.endsWith(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWith(CharPredicate charPredicate) {
        return SequenceUtils.startsWith(this, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWithEOL() {
        return SequenceUtils.endsWithEOL(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWithAnyEOL() {
        return SequenceUtils.endsWithAnyEOL(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWithSpace() {
        return SequenceUtils.endsWithSpace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWithSpaceTab() {
        return SequenceUtils.endsWithSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWithWhitespace() {
        return SequenceUtils.endsWithWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWithEOL() {
        return SequenceUtils.startsWithEOL(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWithAnyEOL() {
        return SequenceUtils.startsWithAnyEOL(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWithSpace() {
        return SequenceUtils.startsWithSpace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWithSpaceTab() {
        return SequenceUtils.startsWithSpaceTab(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWithWhitespace() {
        return SequenceUtils.startsWithWhitespace(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeSuffix(CharSequence charSequence) {
        return !endsWith(charSequence) ? this : subSequence(0, length() - charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removePrefix(CharSequence charSequence) {
        return !startsWith(charSequence) ? this : subSequence(charSequence.length(), length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeProperSuffix(CharSequence charSequence) {
        return (length() <= charSequence.length() || !endsWith(charSequence)) ? this : subSequence(0, length() - charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeProperPrefix(CharSequence charSequence) {
        return (length() <= charSequence.length() || !startsWith(charSequence)) ? this : subSequence(charSequence.length(), length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean endsWithIgnoreCase(CharSequence charSequence) {
        return length() > 0 && matchCharsReversed(charSequence, length() - 1, true);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean startsWithIgnoreCase(CharSequence charSequence) {
        return length() > 0 && matchChars(charSequence, 0, true);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeSuffixIgnoreCase(CharSequence charSequence) {
        return !endsWithIgnoreCase(charSequence) ? this : subSequence(0, length() - charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removePrefixIgnoreCase(CharSequence charSequence) {
        return !startsWithIgnoreCase(charSequence) ? this : subSequence(charSequence.length(), length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeProperSuffixIgnoreCase(CharSequence charSequence) {
        return (length() <= charSequence.length() || !endsWithIgnoreCase(charSequence)) ? this : subSequence(0, length() - charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeProperPrefixIgnoreCase(CharSequence charSequence) {
        return (length() <= charSequence.length() || !startsWithIgnoreCase(charSequence)) ? this : subSequence(charSequence.length(), length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeSuffix(CharSequence charSequence, boolean z) {
        return !endsWith(charSequence, z) ? this : subSequence(0, length() - charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removePrefix(CharSequence charSequence, boolean z) {
        return !startsWith(charSequence, z) ? this : subSequence(charSequence.length(), length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeProperSuffix(CharSequence charSequence, boolean z) {
        return (length() <= charSequence.length() || !endsWith(charSequence, z)) ? this : subSequence(0, length() - charSequence.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T removeProperPrefix(CharSequence charSequence, boolean z) {
        return (length() <= charSequence.length() || !startsWith(charSequence, z)) ? this : subSequence(charSequence.length(), length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T insert(int i, CharSequence charSequence) {
        int max = Math.max(0, Math.min(length(), i));
        if (charSequence.length() == 0) {
            return this;
        }
        if (max == 0) {
            return prefixWith(charSequence);
        }
        if (max == length()) {
            return suffixWith(charSequence);
        }
        return (T) getBuilder().add(subSequence(0, max)).add(charSequence).add(subSequence(max)).toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T delete(int i, int i2) {
        int max = Math.max(0, Math.min(length(), i2));
        int min = Math.min(max, Math.max(0, i));
        if (min == max) {
            return this;
        }
        if (min == 0) {
            return subSequence(max);
        }
        if (max != length()) {
            return (T) getBuilder().add(subSequence(0, min)).add(subSequence(max)).toSequence();
        }
        return subSequence(0, min);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T toLowerCase() {
        return toMapped(ChangeCase.toLowerCase);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T toUpperCase() {
        return toMapped(ChangeCase.toUpperCase);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T toNbSp() {
        return toMapped(SpaceMapper.toNonBreakSpace);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T toSpc() {
        return toMapped(SpaceMapper.fromNonBreakSpace);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matches(CharSequence charSequence, boolean z) {
        return SequenceUtils.matches(this, charSequence, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matches(CharSequence charSequence) {
        return SequenceUtils.matches(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchesIgnoreCase(CharSequence charSequence) {
        return SequenceUtils.matchesIgnoreCase(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchChars(CharSequence charSequence, int i, boolean z) {
        return SequenceUtils.matchChars(this, charSequence, i, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchChars(CharSequence charSequence, int i) {
        return SequenceUtils.matchChars(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchCharsIgnoreCase(CharSequence charSequence, int i) {
        return SequenceUtils.matchCharsIgnoreCase(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchChars(CharSequence charSequence, boolean z) {
        return SequenceUtils.matchChars(this, charSequence, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchChars(CharSequence charSequence) {
        return SequenceUtils.matchChars(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchCharsIgnoreCase(CharSequence charSequence) {
        return SequenceUtils.matchCharsIgnoreCase(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchCharsReversed(CharSequence charSequence, int i, boolean z) {
        return SequenceUtils.matchCharsReversed(this, charSequence, i, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchCharsReversed(CharSequence charSequence, int i) {
        return SequenceUtils.matchCharsReversed(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final boolean matchCharsReversedIgnoreCase(CharSequence charSequence, int i) {
        return SequenceUtils.matchCharsReversedIgnoreCase(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCount(CharSequence charSequence, int i, int i2, boolean z) {
        return SequenceUtils.matchedCharCount(this, charSequence, i, i2, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCount(CharSequence charSequence, int i, boolean z) {
        return SequenceUtils.matchedCharCount(this, charSequence, i, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCount(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.matchedCharCount(this, charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCount(CharSequence charSequence, int i) {
        return SequenceUtils.matchedCharCount(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountIgnoreCase(CharSequence charSequence, int i) {
        return SequenceUtils.matchedCharCountIgnoreCase(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountIgnoreCase(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.matchedCharCountIgnoreCase(this, charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountReversedIgnoreCase(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.matchedCharCountReversedIgnoreCase(this, charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountReversed(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.matchedCharCountReversed(this, charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountReversed(CharSequence charSequence, int i, boolean z) {
        return SequenceUtils.matchedCharCountReversed(this, charSequence, i, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountReversed(CharSequence charSequence, int i) {
        return SequenceUtils.matchedCharCountReversed(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountReversedIgnoreCase(CharSequence charSequence, int i) {
        return SequenceUtils.matchedCharCountReversedIgnoreCase(this, charSequence, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCount(CharSequence charSequence, int i, int i2, boolean z, boolean z2) {
        return SequenceUtils.matchedCharCount(this, charSequence, i, i2, z, z2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int matchedCharCountReversed(CharSequence charSequence, int i, int i2, boolean z) {
        return SequenceUtils.matchedCharCountReversed(this, charSequence, i, i2, z);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        int length = length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(charAt(i));
        }
        return sb.toString();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final String normalizeEOL() {
        return Escaping.normalizeEOL(toString());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final String normalizeEndWithEOL() {
        return Escaping.normalizeEndWithEOL(toString());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final String toVisibleWhitespaceString() {
        return SequenceUtils.toVisibleWhitespaceString(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitList(CharSequence charSequence) {
        return SequenceUtils.splitList(this, charSequence, 0, 0, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitList(CharSequence charSequence, int i, boolean z, CharPredicate charPredicate) {
        return SequenceUtils.splitList(this, charSequence, i, z ? 1 : 0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitList(CharSequence charSequence, int i, int i2) {
        return SequenceUtils.splitList(this, charSequence, i, i2, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitList(CharSequence charSequence, boolean z, CharPredicate charPredicate) {
        return SequenceUtils.splitList(this, charSequence, 0, z ? 1 : 0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitListEOL() {
        return SequenceUtils.splitList(this, SequenceUtils.EOL, 0, 1, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitListEOL(boolean z) {
        return SequenceUtils.splitList(this, SequenceUtils.EOL, 0, z ? 1 : 0, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitListEOL(boolean z, CharPredicate charPredicate) {
        return SequenceUtils.splitList(this, SequenceUtils.EOL, 0, z ? 1 : 0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final List<T> splitList(CharSequence charSequence, int i, int i2, CharPredicate charPredicate) {
        return SequenceUtils.splitList(this, charSequence, i, i2, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] splitEOL() {
        return split(SequenceUtils.EOL, 0, 1, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] splitEOL(boolean z) {
        return split(SequenceUtils.EOL, 0, z ? 1 : 0, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] split(CharSequence charSequence, boolean z, CharPredicate charPredicate) {
        return split(charSequence, 0, z ? 1 : 0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] split(CharSequence charSequence) {
        return split(charSequence, 0, 0, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] split(CharSequence charSequence, int i, boolean z, CharPredicate charPredicate) {
        return split(charSequence, i, z ? 1 : 0, charPredicate);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] split(CharSequence charSequence, int i, int i2) {
        return split(charSequence, i, i2, (CharPredicate) null);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T[] split(CharSequence charSequence, int i, int i2, CharPredicate charPredicate) {
        return (T[]) ((IRichSequence[]) SequenceUtils.splitList(this, charSequence, i, i2, charPredicate).toArray(emptyArray()));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendTo(StringBuilder sb, CharMapper charMapper) {
        return appendTo(sb, charMapper, 0, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendTo(StringBuilder sb, CharMapper charMapper, int i) {
        return appendTo(sb, charMapper, i, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendTo(StringBuilder sb) {
        return appendTo(sb, null, 0, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendTo(StringBuilder sb, int i) {
        return appendTo(sb, null, i, length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendTo(StringBuilder sb, int i, int i2) {
        return appendTo(sb, null, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendTo(StringBuilder sb, CharMapper charMapper, int i, int i2) {
        sb.append((CharSequence) (charMapper == null ? this : toMapped(charMapper)), i, i2);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendRangesTo(StringBuilder sb, CharMapper charMapper, Range... rangeArr) {
        return appendRangesTo(sb, charMapper, new ArrayIterable(rangeArr));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendRangesTo(StringBuilder sb, Range... rangeArr) {
        return appendRangesTo(sb, (CharMapper) null, new ArrayIterable(rangeArr));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendRangesTo(StringBuilder sb, Iterable<? extends Range> iterable) {
        return appendRangesTo(sb, (CharMapper) null, iterable);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendRangesTo(StringBuilder sb, CharMapper charMapper, Iterable<? extends Range> iterable) {
        T mapped = charMapper == null ? this : toMapped(charMapper);
        for (Range range : iterable) {
            if (range != null && range.isNotNull()) {
                sb.append((CharSequence) mapped, range.getStart(), range.getEnd());
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int[] indexOfAll(CharSequence charSequence) {
        return SequenceUtils.indexOfAll(this, charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendEOL() {
        return suffixWith(SequenceUtils.EOL);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T suffixWithEOL() {
        return suffixWith(SequenceUtils.EOL);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T prefixWithEOL() {
        return prefixWith(SequenceUtils.EOL);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T prefixOnceWithEOL() {
        return prefixOnceWith(SequenceUtils.EOL);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T suffixOnceWithEOL() {
        return suffixOnceWith(SequenceUtils.EOL);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendSpace() {
        return suffixWith(SequenceUtils.SPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T suffixWithSpace() {
        return suffixWith(SequenceUtils.SPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T prefixWithSpace() {
        return prefixWith(SequenceUtils.SPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T appendSpaces(int i) {
        return suffixWith(RepeatedSequence.ofSpaces(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T suffixWithSpaces(int i) {
        return suffixWith(RepeatedSequence.ofSpaces(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T prefixWithSpaces(int i) {
        return prefixWith(RepeatedSequence.ofSpaces(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T prefixOnceWithSpace() {
        return prefixOnceWith(SequenceUtils.SPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T suffixOnceWithSpace() {
        return suffixOnceWith(SequenceUtils.SPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T prefixWith(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0) ? this : (T) getBuilder().add(charSequence).add(this).toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public T suffixWith(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0) ? this : (T) getBuilder().add(this).add(charSequence).toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T prefixOnceWith(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0 || startsWith(charSequence)) ? this : prefixWith(charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T suffixOnceWith(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0 || endsWith(charSequence)) ? this : suffixWith(charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T replace(int i, int i2, CharSequence charSequence) {
        int length = length();
        return (T) getBuilder().add(subSequence(0, Math.max(i, 0))).add(charSequence).add(subSequence(Math.min(i2, length))).toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T replace(CharSequence charSequence, CharSequence charSequence2) {
        int[] indexOfAll = indexOfAll(charSequence);
        if (indexOfAll.length == 0) {
            return this;
        }
        ISequenceBuilder builder = getBuilder();
        int length = indexOfAll.length;
        int length2 = length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i;
            i++;
            int i4 = indexOfAll[i3];
            if (i2 < i4) {
                builder.add(subSequence(i2, i4));
            }
            i2 = i4 + charSequence.length();
            builder.add(charSequence2);
        }
        if (i2 < length2) {
            builder.add(subSequence(i2, length2));
        }
        return (T) builder.toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T append(CharSequence... charSequenceArr) {
        return append(new ArrayIterable(charSequenceArr));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T append(Iterable<? extends CharSequence> iterable) {
        ISequenceBuilder builder = getBuilder();
        builder.add(this);
        Iterator<? extends CharSequence> it = iterable.iterator();
        while (it.hasNext()) {
            builder.add(it.next());
        }
        return (T) builder.toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T extractRanges(Range... rangeArr) {
        return extractRanges(new ArrayIterable(rangeArr));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final T extractRanges(Iterable<Range> iterable) {
        ISequenceBuilder builder = getBuilder();
        for (Range range : iterable) {
            if (range != null && !range.isNull()) {
                builder.add(range.safeSubSequence(this));
            }
        }
        return (T) builder.toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final int columnAtIndex(int i) {
        return SequenceUtils.columnAtIndex(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public final Pair<Integer, Integer> lineColumnAtIndex(int i) {
        return SequenceUtils.lineColumnAtIndex(this, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public boolean isIn(String[] strArr) {
        return SequenceUtils.containedBy(strArr, this);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public boolean isIn(Collection<? extends CharSequence> collection) {
        return SequenceUtils.containedBy(collection, this);
    }
}
