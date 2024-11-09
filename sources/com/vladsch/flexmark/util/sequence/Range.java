package com.vladsch.flexmark.util.sequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/Range.class */
public class Range {
    public static final Range NULL = new Range(Integer.MAX_VALUE, Integer.MIN_VALUE);
    public static final Range EMPTY = new Range(0, 0);
    private final int start;
    private final int end;

    public static Range of(int i, int i2) {
        return (i == NULL.start && i2 == NULL.end) ? NULL : new Range(i, i2);
    }

    public static Range emptyOf(int i) {
        return new Range(i, i);
    }

    public static Range ofLength(int i, int i2) {
        return new Range(i, i + i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Range(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Range(Range range) {
        this.start = range.start;
        this.end = range.end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int component1() {
        return this.start;
    }

    public int component2() {
        return this.end;
    }

    public int getStartOffset() {
        return this.start;
    }

    public int getEndOffset() {
        return this.end;
    }

    public Range withStart(int i) {
        return i == this.start ? this : of(i, this.end);
    }

    public Range withEnd(int i) {
        return i == this.end ? this : of(this.start, i);
    }

    public Range endMinus(int i) {
        return i == 0 ? this : of(this.start, this.end - i);
    }

    public Range endPlus(int i) {
        return i == 0 ? this : of(this.start, this.end + i);
    }

    public Range startMinus(int i) {
        return i == 0 ? this : of(this.start - i, this.end);
    }

    public Range startPlus(int i) {
        return i == 0 ? this : of(this.start + i, this.end);
    }

    public Range withRange(int i, int i2) {
        return (i == this.start && i2 == this.end) ? this : of(i, i2);
    }

    public Range shiftLeft(int i) {
        return i == 0 ? this : of(this.start - i, this.end - i);
    }

    public Range shiftRight(int i) {
        return i == 0 ? this : of(this.start + i, this.end + i);
    }

    public int getSpan() {
        if (isNull()) {
            return 0;
        }
        return this.end - this.start;
    }

    public boolean isNull() {
        return this.start == NULL.start && this.end == NULL.end;
    }

    public boolean isNotNull() {
        return !isNull();
    }

    public boolean isEmpty() {
        return this.start >= this.end;
    }

    public boolean isNotEmpty() {
        return this.start < this.end;
    }

    public boolean contains(Range range) {
        return this.end >= range.end && this.start <= range.start;
    }

    public boolean doesContain(Range range) {
        return this.end >= range.end && this.start <= range.start;
    }

    public boolean contains(int i) {
        return this.start <= i && i < this.end;
    }

    public boolean doesContain(int i) {
        return this.start <= i && i < this.end;
    }

    public boolean contains(int i, int i2) {
        return this.start <= i && i2 <= this.end;
    }

    public boolean doesContain(int i, int i2) {
        return this.start <= i && i2 <= this.end;
    }

    public boolean overlaps(Range range) {
        return range.end > this.start && range.start < this.end;
    }

    public boolean doesOverlap(Range range) {
        return range.end > this.start && range.start < this.end;
    }

    public boolean doesNotOverlap(Range range) {
        return range.end <= this.start || range.start >= this.end;
    }

    public boolean overlapsOrAdjacent(Range range) {
        return range.end >= this.start && range.start <= this.end;
    }

    public boolean doesOverlapOrAdjacent(Range range) {
        return range.end >= this.start && range.start <= this.end;
    }

    public boolean doesNotOverlapOrAdjacent(Range range) {
        return range.end < this.start || range.start > this.end;
    }

    public boolean doesNotOverlapNorAdjacent(Range range) {
        return range.end < this.start || range.start > this.end;
    }

    public boolean properlyContains(Range range) {
        return this.end > range.end && this.start < range.start;
    }

    public boolean doesProperlyContain(Range range) {
        return this.end > range.end && this.start < range.start;
    }

    public boolean isAdjacent(int i) {
        return i == this.start - 1 || i == this.end;
    }

    public boolean isAdjacentAfter(int i) {
        return this.start - 1 == i;
    }

    public boolean isAdjacentBefore(int i) {
        return this.end == i;
    }

    public boolean isAdjacent(Range range) {
        return this.start == range.end || this.end == range.start;
    }

    public boolean isAdjacentBefore(Range range) {
        return this.end == range.start;
    }

    public boolean isAdjacentAfter(Range range) {
        return this.start == range.end;
    }

    public boolean isContainedBy(Range range) {
        return range.end >= this.end && range.start <= this.start;
    }

    public boolean isContainedBy(int i, int i2) {
        return i2 >= this.end && i <= this.start;
    }

    public boolean isProperlyContainedBy(Range range) {
        return range.end > this.end && range.start < this.start;
    }

    public boolean isProperlyContainedBy(int i, int i2) {
        return i2 > this.end && i < this.start;
    }

    public boolean isEqual(Range range) {
        return this.end == range.end && this.start == range.start;
    }

    public boolean isValidIndex(int i) {
        return i >= this.start && i <= this.end;
    }

    public boolean isStart(int i) {
        return i == this.start;
    }

    public boolean isEnd(int i) {
        return i == this.end;
    }

    public boolean isLast(int i) {
        return i >= this.start && i == this.end - 1;
    }

    public boolean leadBy(int i) {
        return i <= this.start;
    }

    public boolean leads(int i) {
        return this.end <= i;
    }

    public boolean trailedBy(int i) {
        return this.end <= i;
    }

    public boolean trails(int i) {
        return i <= this.start;
    }

    public Range intersect(Range range) {
        int max = Math.max(this.start, range.start);
        int min = Math.min(this.end, range.end);
        if (max >= min) {
            max = min;
        }
        return withRange(max, min);
    }

    public Range exclude(Range range) {
        int i = this.start;
        int i2 = i;
        if (i >= range.start && i2 < range.end) {
            i2 = range.end;
        }
        int i3 = this.end;
        int i4 = i3;
        if (i3 <= range.end && i4 > range.start) {
            i4 = range.start;
        }
        if (i2 >= i4) {
            i4 = 0;
            i2 = 0;
        }
        return withRange(i2, i4);
    }

    public int compare(Range range) {
        if (this.start < range.start) {
            return -1;
        }
        if (this.start > range.start) {
            return 1;
        }
        if (this.end > range.end) {
            return -1;
        }
        if (this.end < range.end) {
            return 1;
        }
        return 0;
    }

    public Range include(Range range) {
        return range.isNull() ? isNull() ? NULL : this : expandToInclude(range);
    }

    public Range include(int i) {
        return include(i, i);
    }

    public Range include(int i, int i2) {
        return isNull() ? of(i, i2) : expandToInclude(i, i2);
    }

    public Range expandToInclude(Range range) {
        return expandToInclude(range.start, range.end);
    }

    public Range expandToInclude(int i, int i2) {
        return withRange(Math.min(this.start, i), Math.max(this.end, i2));
    }

    @Deprecated
    public BasedSequence subSequence(CharSequence charSequence) {
        return basedSubSequence(charSequence);
    }

    public BasedSequence basedSubSequence(CharSequence charSequence) {
        return BasedSequence.of(charSequence).subSequence(this.start, this.end);
    }

    public BasedSequence basedSafeSubSequence(CharSequence charSequence) {
        int min = Math.min(charSequence.length(), this.end);
        return isNull() ? BasedSequence.NULL : BasedSequence.of(charSequence).subSequence(Math.min(min, Math.max(0, this.start)), min);
    }

    public RichSequence richSubSequence(CharSequence charSequence) {
        return RichSequence.of(charSequence.subSequence(this.start, this.end));
    }

    public RichSequence richSafeSubSequence(CharSequence charSequence) {
        int min = Math.min(charSequence.length(), this.end);
        return isNull() ? RichSequence.NULL : RichSequence.of(charSequence, Math.min(min, Math.max(0, this.start)), min);
    }

    public CharSequence charSubSequence(CharSequence charSequence) {
        return charSequence.subSequence(this.start, this.end);
    }

    public CharSequence safeSubSequence(CharSequence charSequence) {
        int min = Math.min(charSequence.length(), this.end);
        return isNull() ? charSequence.subSequence(0, 0) : charSequence.subSequence(Math.min(min, Math.max(0, this.start)), min);
    }

    public String toString() {
        return "[" + this.start + ", " + this.end + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Range)) {
            return false;
        }
        Range range = (Range) obj;
        return this.start == range.start && this.end == range.end;
    }

    public int hashCode() {
        return (this.start * 31) + this.end;
    }
}
