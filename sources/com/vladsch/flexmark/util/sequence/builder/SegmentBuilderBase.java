package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.DelimitedBuilder;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.SegmentBuilderBase;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentBuilderBase.class */
public class SegmentBuilderBase<S extends SegmentBuilderBase<S>> implements ISegmentBuilder<S> {
    public static final int MIN_PART_CAPACITY = 8;
    public static final int[] EMPTY_PARTS;
    protected int[] parts;
    protected int partsSize;
    protected int anchorsSize;
    protected int startOffset;
    protected int endOffset;
    protected int length;
    protected final SegmentStats stats;
    protected final SegmentStats textStats;
    protected final int options;
    protected final StringBuilder text;
    protected int immutableOffset;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentBuilderBase.class.desiredAssertionStatus();
        EMPTY_PARTS = new int[0];
    }

    private static int[] ensureCapacity(int[] iArr, int i) {
        if (!$assertionsDisabled && i < 0) {
            throw new AssertionError();
        }
        int length = iArr.length / 2;
        if (length <= i) {
            return Arrays.copyOf(iArr, Math.max(8, Math.max((length + length) >> 1, i)) << 1);
        }
        return iArr;
    }

    private void ensureCapacity(int i) {
        this.parts = ensureCapacity(this.parts, i + 1);
    }

    public void trimToSize() {
        if (this.parts.length > this.partsSize) {
            this.parts = Arrays.copyOf(this.parts, this.partsSize << 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SegmentBuilderBase() {
        this(F_INCLUDE_ANCHORS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SegmentBuilderBase(int i) {
        this.parts = EMPTY_PARTS;
        this.partsSize = 0;
        this.anchorsSize = 0;
        this.startOffset = Range.NULL.getStart();
        this.endOffset = Range.NULL.getEnd();
        this.length = 0;
        this.text = new StringBuilder();
        this.immutableOffset = 0;
        this.options = i & (F_INCLUDE_ANCHORS | F_TRACK_FIRST256);
        this.stats = new SegmentStats((i & F_TRACK_FIRST256) != 0);
        this.textStats = new SegmentStats((i & F_TRACK_FIRST256) != 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getStartOffset() {
        if (this.startOffset <= this.endOffset) {
            return this.startOffset;
        }
        return -1;
    }

    public boolean needStartOffset() {
        return getStartOffsetIfNeeded() != -1;
    }

    public int getStartOffsetIfNeeded() {
        int startOffset = getStartOffset();
        Seg segOrNull = getSegOrNull(0);
        if (startOffset == -1 || segOrNull == null || !segOrNull.isBase() || startOffset == segOrNull.getStart()) {
            return -1;
        }
        return startOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getEndOffset() {
        if (this.endOffset >= this.startOffset) {
            return this.endOffset;
        }
        return -1;
    }

    public boolean needEndOffset() {
        return getEndOffsetIfNeeded() != -1;
    }

    public int getEndOffsetIfNeeded() {
        int endOffset = getEndOffset();
        Seg segOrNull = getSegOrNull(this.partsSize - 1);
        if (endOffset == -1 || segOrNull == null || !segOrNull.isBase() || endOffset == segOrNull.getEnd()) {
            return -1;
        }
        return endOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public boolean isBaseSubSequenceRange() {
        return getBaseSubSequenceRange() != null;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public Range getBaseSubSequenceRange() {
        if (this.partsSize == 1 && !haveDanglingText()) {
            Seg seg = getSeg(this.partsSize - 1);
            Seg seg2 = seg;
            if (seg.length() != 0 && this.anchorsSize == 1) {
                seg2 = getSeg(this.partsSize - 2);
            }
            if (seg2.isBase() && seg2.getStart() == this.startOffset && seg2.getEnd() == this.endOffset) {
                return seg2.getRange();
            }
            return null;
        }
        return null;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public boolean haveOffsets() {
        return this.startOffset <= this.endOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int size() {
        return this.partsSize + (haveDanglingText() ? 1 : 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public CharSequence getText() {
        return this.text;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int noAnchorsSize() {
        return size() - this.anchorsSize;
    }

    private int computeLength() {
        int i = 0;
        int i2 = this.partsSize;
        for (int i3 = 0; i3 < i2; i3++) {
            i += getSeg(i3).length();
        }
        if (haveDanglingText()) {
            i += this.text.length() - this.immutableOffset;
        }
        return i;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int length() {
        if ($assertionsDisabled || this.length == computeLength()) {
            return this.length;
        }
        throw new AssertionError("length:" + this.length + " != computeLength(): " + computeLength());
    }

    public SegmentStats getStats() {
        return this.stats;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public boolean isTrackTextFirst256() {
        return this.stats.isTrackTextFirst256();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getTextLength() {
        return this.stats.getTextLength();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getTextSegments() {
        return this.stats.getTextSegments();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getTextSpaceLength() {
        return this.stats.getTextSpaceLength();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getTextSpaceSegments() {
        return this.stats.getTextSpaceSegments();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getTextFirst256Length() {
        return this.stats.getTextFirst256Length();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getTextFirst256Segments() {
        return this.stats.getTextFirst256Segments();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder, java.lang.Iterable
    public Iterator<Object> iterator() {
        return new PartsIterator(this);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentBuilderBase$PartsIterator.class */
    static class PartsIterator implements Iterator<Object> {
        final SegmentBuilderBase<?> builder;
        int nextIndex;

        public PartsIterator(SegmentBuilderBase<?> segmentBuilderBase) {
            this.builder = segmentBuilderBase;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < this.builder.size();
        }

        @Override // java.util.Iterator
        public Object next() {
            SegmentBuilderBase<?> segmentBuilderBase = this.builder;
            int i = this.nextIndex;
            this.nextIndex = i + 1;
            return segmentBuilderBase.getPart(i);
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public Iterable<Seg> getSegments() {
        return new SegIterable(this);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentBuilderBase$SegIterable.class */
    static class SegIterable implements Iterable<Seg> {
        final SegmentBuilderBase<?> builder;

        public SegIterable(SegmentBuilderBase<?> segmentBuilderBase) {
            this.builder = segmentBuilderBase;
        }

        @Override // java.lang.Iterable
        public Iterator<Seg> iterator() {
            return new SegIterator(this.builder);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentBuilderBase$SegIterator.class */
    static class SegIterator implements Iterator<Seg> {
        final SegmentBuilderBase<?> builder;
        int nextIndex;

        public SegIterator(SegmentBuilderBase<?> segmentBuilderBase) {
            this.builder = segmentBuilderBase;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < this.builder.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Seg next() {
            SegmentBuilderBase<?> segmentBuilderBase = this.builder;
            int i = this.nextIndex;
            this.nextIndex = i + 1;
            return segmentBuilderBase.getSegPart(i);
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getOptions() {
        return this.options;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public boolean isIncludeAnchors() {
        return (this.options & F_INCLUDE_ANCHORS) != 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public int getSpan() {
        if (this.startOffset > this.endOffset) {
            return -1;
        }
        return this.endOffset - this.startOffset;
    }

    private Seg getSegOrNull(int i) {
        int i2 = i << 1;
        if (i2 + 1 >= this.parts.length) {
            return null;
        }
        return Seg.segOf(this.parts[i2], this.parts[i2 + 1]);
    }

    private Seg getSeg(int i) {
        int i2 = i << 1;
        return i2 + 1 >= this.parts.length ? Seg.NULL : Seg.segOf(this.parts[i2], this.parts[i2 + 1]);
    }

    public Object getPart(int i) {
        if (i == this.partsSize && haveDanglingText()) {
            return this.text.subSequence(this.immutableOffset, this.text.length());
        }
        int i2 = i << 1;
        Seg segOf = i2 + 1 >= this.parts.length ? Seg.NULL : Seg.segOf(this.parts[i2], this.parts[i2 + 1]);
        Seg seg = segOf;
        if (segOf.isBase()) {
            return seg.getRange();
        }
        return seg.isText() ? this.text.subSequence(seg.getTextStart(), seg.getTextEnd()) : Range.NULL;
    }

    Seg getSegPart(int i) {
        if (i == this.partsSize && haveDanglingText()) {
            return Seg.textOf(this.immutableOffset, this.text.length(), this.textStats.isTextFirst256(), this.textStats.isRepeatedText());
        }
        int i2 = i << 1;
        return i2 + 1 >= this.parts.length ? Seg.NULL : Seg.segOf(this.parts[i2], this.parts[i2 + 1]);
    }

    private void setSegEnd(int i, int i2) {
        int i3 = i << 1;
        if (!$assertionsDisabled && i3 + 1 >= this.parts.length) {
            throw new AssertionError();
        }
        if (this.parts[i3] == i2) {
            if (this.parts[i3] != this.parts[i3 + 1]) {
                this.anchorsSize++;
            }
        } else if (this.parts[i3] == this.parts[i3 + 1]) {
            this.anchorsSize--;
        }
        this.parts[i3 + 1] = i2;
    }

    private void addSeg(int i, int i2) {
        ensureCapacity(this.partsSize);
        int i3 = this.partsSize << 1;
        this.parts[i3] = i;
        this.parts[i3 + 1] = i2;
        this.partsSize++;
        if (i == i2) {
            this.anchorsSize++;
        }
    }

    private Seg lastSegOrNull() {
        if (this.partsSize == 0) {
            return null;
        }
        return getSegOrNull(this.partsSize - 1);
    }

    protected boolean haveDanglingText() {
        return this.text.length() > this.immutableOffset;
    }

    protected Object[] optimizeText(Object[] objArr) {
        return objArr;
    }

    protected Object[] handleOverlap(Object[] objArr) {
        Range range = (Range) objArr[0];
        CharSequence charSequence = (CharSequence) objArr[1];
        Range range2 = (Range) objArr[2];
        if (!$assertionsDisabled && (range.isNull() || range.getEnd() <= range2.getStart())) {
            throw new AssertionError();
        }
        if (range.getEnd() < range2.getEnd()) {
            if (charSequence.length() > 0) {
                objArr[2] = Range.of(range.getEnd(), range2.getEnd());
                return objArr;
            }
            objArr[0] = range.withEnd(range2.getEnd());
        }
        objArr[2] = Range.NULL;
        return objArr;
    }

    private void processParts(int i, int i2, boolean z, boolean z2, Function<Object[], Object[]> function) {
        if (!$assertionsDisabled && (i < 0 || i2 < 0 || i > i2)) {
            throw new AssertionError();
        }
        CharSequence subSequence = this.text.subSequence(this.immutableOffset, this.text.length());
        if (!$assertionsDisabled && !z && subSequence.length() <= 0) {
            throw new AssertionError();
        }
        Seg lastSegOrNull = lastSegOrNull();
        Range range = (lastSegOrNull == null || !lastSegOrNull.isBase()) ? Range.NULL : lastSegOrNull.getRange();
        if (!isIncludeAnchors() && haveOffsets() && (range.isNull() || range.getEnd() < this.endOffset)) {
            range = Range.emptyOf(this.endOffset);
        }
        if (!haveOffsets()) {
            this.startOffset = i;
        }
        if (!z) {
            this.endOffset = Math.max(this.endOffset, i2);
        }
        Object[] objArr = new Object[3];
        objArr[0] = range;
        objArr[1] = subSequence;
        objArr[2] = z2 ? Range.NULL : Range.of(i, i2);
        Object[] objArr2 = (Object[]) objArr.clone();
        Object[] apply = function.apply(objArr);
        if (!$assertionsDisabled && apply.length <= 0) {
            throw new AssertionError();
        }
        if (Arrays.equals(apply, objArr2)) {
            if (!$assertionsDisabled && z) {
                throw new AssertionError();
            }
            if (i2 > i || isIncludeAnchors()) {
                if (subSequence.length() > 0) {
                    commitText();
                }
                this.length += i2 - i;
                addSeg(i, i2);
                return;
            }
            return;
        }
        this.textStats.commitText();
        this.stats.commitText();
        this.stats.remove(this.textStats);
        this.textStats.clear();
        this.length -= subSequence.length();
        this.text.delete(this.immutableOffset, this.text.length());
        if (lastSegOrNull != null && lastSegOrNull.isBase()) {
            this.length -= lastSegOrNull.length();
            this.partsSize--;
            if (lastSegOrNull.length() == 0) {
                this.anchorsSize--;
            }
        }
        int length = apply.length;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (int i5 = 0; i5 < length; i5++) {
            Object obj = apply[i5];
            if (!(obj instanceof CharSequence)) {
                if (obj instanceof Range) {
                    if (((Range) obj).isNotNull()) {
                        int start = ((Range) obj).getStart();
                        int end = ((Range) obj).getEnd();
                        if (!$assertionsDisabled && (start < 0 || end < 0 || start > end)) {
                            throw new AssertionError();
                        }
                        if (i3 == Integer.MAX_VALUE) {
                            i3 = start;
                        }
                        if (start < i4) {
                            throw new IllegalStateException(String.format("Accumulated range [%d, %d) overlaps Transformed Range[%d]: [%d, %d)", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(start), Integer.valueOf(end)));
                        }
                        i4 = Math.max(i4, end);
                        boolean haveDanglingText = haveDanglingText();
                        if (haveDanglingText && z) {
                            processParts(start, end, false, false, this::optimizeText);
                        } else {
                            this.startOffset = Math.min(this.startOffset, start);
                            this.endOffset = Math.max(this.endOffset, end);
                            if (start != end || isIncludeAnchors()) {
                                if (haveDanglingText) {
                                    commitText();
                                }
                                this.length += end - start;
                                addSeg(start, end);
                            }
                        }
                    } else {
                        continue;
                    }
                } else if (obj != null) {
                    throw new IllegalStateException("Invalid optimized part type " + obj.getClass());
                }
            } else {
                CharSequence charSequence = (CharSequence) obj;
                if (charSequence.length() > 0) {
                    addText(charSequence);
                }
            }
        }
    }

    private void commitText() {
        addSeg(Seg.getTextStart(this.immutableOffset, this.textStats.isTextFirst256()), Seg.getTextEnd(this.text.length(), this.textStats.isRepeatedText()));
        this.immutableOffset = this.text.length();
        this.stats.commitText();
        this.textStats.clear();
    }

    private void addText(CharSequence charSequence) {
        this.length += charSequence.length();
        this.text.append(charSequence);
        this.stats.addText(charSequence);
        this.textStats.addText(charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public S appendAnchor(int i) {
        return append(i, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public S append(Range range) {
        return append(range.getStart(), range.getEnd());
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public S append(int i, int i2) {
        if (i2 < 0 || i > i2) {
            return this;
        }
        int i3 = i2 - i;
        if (i3 == 0 && (!isIncludeAnchors() || i < this.endOffset)) {
            if (i >= this.endOffset) {
                if (haveDanglingText()) {
                    processParts(i, i2, false, false, this::optimizeText);
                } else {
                    if (!haveOffsets()) {
                        this.startOffset = i;
                    }
                    this.endOffset = i;
                }
            }
            return this;
        }
        if (this.endOffset > i) {
            processParts(i, i2, true, false, this::handleOverlap);
        } else if (this.endOffset == i) {
            if (haveDanglingText()) {
                processParts(i, i2, false, false, this::optimizeText);
            } else {
                this.endOffset = i2;
                this.length += i3;
                if (this.partsSize == 0) {
                    addSeg(i, i2);
                } else {
                    setSegEnd(this.partsSize - 1, i2);
                }
            }
        } else if (haveDanglingText()) {
            processParts(i, i2, false, false, this::optimizeText);
        } else {
            if (!haveOffsets()) {
                this.startOffset = i;
            }
            this.endOffset = i2;
            this.length += i3;
            addSeg(i, i2);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public S append(CharSequence charSequence) {
        int length = charSequence.length();
        if (length != 0) {
            this.stats.addText(charSequence);
            this.textStats.addText(charSequence);
            this.text.append(charSequence);
            this.length += length;
        }
        return this;
    }

    public S append(char c) {
        this.stats.addText(c);
        this.textStats.addText(c);
        this.text.append(c);
        this.length++;
        return this;
    }

    public S append(char c, int i) {
        if (i > 0) {
            this.stats.addText(c, i);
            this.textStats.addText(c, i);
            this.length += i;
            while (true) {
                int i2 = i;
                i--;
                if (i2 <= 0) {
                    break;
                }
                this.text.append(c);
            }
        }
        return this;
    }

    public String toString(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Function<CharSequence, CharSequence> function) {
        if (this.endOffset > charSequence.length()) {
            throw new IllegalArgumentException("baseSequence length() must be at least " + this.endOffset + ", got: " + charSequence.length());
        }
        if (haveDanglingText() && haveOffsets()) {
            processParts(this.endOffset, this.endOffset, false, true, this::optimizeText);
        }
        StringBuilder sb = new StringBuilder();
        int i = this.partsSize;
        for (int i2 = 0; i2 < i; i2++) {
            Seg seg = getSeg(i2);
            if (seg.isBase()) {
                sb.append(charSequence2).append(function.apply(charSequence.subSequence(seg.getStart(), seg.getEnd()))).append(charSequence3);
            } else {
                sb.append(function.apply(this.text.subSequence(seg.getTextStart(), seg.getTextEnd())));
            }
        }
        if (haveDanglingText()) {
            sb.append(function.apply(this.text.subSequence(this.immutableOffset, this.text.length())));
        }
        return sb.toString();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public String toStringWithRangesVisibleWhitespace(CharSequence charSequence) {
        return toString(charSequence, "⟦", "⟧", SequenceUtils::toVisibleWhitespaceString);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public String toStringWithRanges(CharSequence charSequence) {
        return toString(charSequence, "⟦", "⟧", Function.identity());
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder
    public String toString(CharSequence charSequence) {
        return toString(charSequence, "", "", Function.identity());
    }

    public String toStringPrep() {
        if (haveDanglingText() && haveOffsets()) {
            processParts(this.endOffset, this.endOffset, false, true, this::optimizeText);
        }
        return toString();
    }

    public String toString() {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(", ");
        delimitedBuilder.append(getClass().getSimpleName()).append("{");
        if (haveOffsets()) {
            delimitedBuilder.append("[").append(this.startOffset).mark().append(this.endOffset).unmark().append(")").mark();
        } else {
            delimitedBuilder.append("NULL").mark();
        }
        delimitedBuilder.append(this.stats.committedCopy()).mark().append("l=").append(this.length).mark().append("sz=").append(size()).mark().append("na=").append(noAnchorsSize());
        if (size() > 0) {
            delimitedBuilder.append(": ");
        }
        int i = this.partsSize;
        for (int i2 = 0; i2 < i; i2++) {
            delimitedBuilder.append(getSeg(i2).toString(this.text)).mark();
        }
        if (haveDanglingText()) {
            delimitedBuilder.append(Seg.textOf(this.immutableOffset, this.text.length(), this.textStats.isTextFirst256(), this.textStats.isRepeatedText()).toString(this.text)).mark();
        }
        delimitedBuilder.unmark().append(" }");
        return delimitedBuilder.toString();
    }
}
