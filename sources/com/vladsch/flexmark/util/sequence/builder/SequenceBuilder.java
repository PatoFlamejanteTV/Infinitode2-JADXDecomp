package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SequenceBuilder.class */
public class SequenceBuilder implements ISequenceBuilder<SequenceBuilder, BasedSequence> {
    private final BasedSegmentBuilder segments;
    private final BasedSequence baseSeq;
    private final BasedSequence altBase;
    private final HashMap<BasedSequence, Boolean> equivalentBases;
    private BasedSequence resultSeq;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SequenceBuilder.class.desiredAssertionStatus();
    }

    private SequenceBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer) {
        this(basedSequence, segmentOptimizer, new HashMap());
    }

    private SequenceBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer, HashMap<BasedSequence, Boolean> hashMap) {
        this.altBase = basedSequence;
        this.baseSeq = basedSequence.getBaseSequence();
        this.equivalentBases = hashMap;
        int i = PlainSegmentBuilder.F_DEFAULT;
        i = (!this.baseSeq.anyOptions(BasedSequence.F_FULL_SEGMENTED_SEQUENCES) || this.baseSeq.anyOptions(BasedSequence.F_COLLECT_FIRST256_STATS)) ? i | PlainSegmentBuilder.F_TRACK_FIRST256 : i;
        i = this.baseSeq.anyOptions(BasedSequence.F_NO_ANCHORS) ? i & (PlainSegmentBuilder.F_INCLUDE_ANCHORS ^ (-1)) : i;
        this.segments = segmentOptimizer == null ? BasedSegmentBuilder.emptyBuilder(this.baseSeq, i) : BasedSegmentBuilder.emptyBuilder(this.baseSeq, segmentOptimizer, i);
    }

    private SequenceBuilder(BasedSequence basedSequence, int i, SegmentOptimizer segmentOptimizer, HashMap<BasedSequence, Boolean> hashMap) {
        this.altBase = basedSequence;
        this.baseSeq = basedSequence.getBaseSequence();
        this.equivalentBases = hashMap;
        i = (!this.baseSeq.anyOptions(BasedSequence.F_FULL_SEGMENTED_SEQUENCES) || this.baseSeq.anyOptions(BasedSequence.F_COLLECT_FIRST256_STATS)) ? i | PlainSegmentBuilder.F_TRACK_FIRST256 : i;
        i = this.baseSeq.anyOptions(BasedSequence.F_NO_ANCHORS) ? i & (PlainSegmentBuilder.F_INCLUDE_ANCHORS ^ (-1)) : i;
        this.segments = segmentOptimizer == null ? BasedSegmentBuilder.emptyBuilder(this.baseSeq, i) : BasedSegmentBuilder.emptyBuilder(this.baseSeq, segmentOptimizer, i);
    }

    public BasedSequence getBaseSequence() {
        return this.baseSeq;
    }

    public BasedSegmentBuilder getSegmentBuilder() {
        return this.segments;
    }

    public Range getLastRangeOrNull() {
        Object part = this.segments.getPart(this.segments.size());
        if ((part instanceof Range) && ((Range) part).isNotNull()) {
            return (Range) part;
        }
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public BasedSequence getSingleBasedSequence() {
        Range baseSubSequenceRange = this.segments.getBaseSubSequenceRange();
        if (baseSubSequenceRange == null) {
            return null;
        }
        return this.baseSeq.subSequence(baseSubSequenceRange.getStart(), baseSubSequenceRange.getEnd());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SequenceBuilder getBuilder() {
        return new SequenceBuilder(this.altBase, this.segments.options, this.segments.optimizer, this.equivalentBases);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public char charAt(int i) {
        return toSequence().charAt(i);
    }

    boolean isCommonBaseSequence(BasedSequence basedSequence) {
        if (basedSequence.isNull()) {
            return false;
        }
        BasedSequence baseSequence = basedSequence.getBaseSequence();
        if (baseSequence == this.baseSeq) {
            return true;
        }
        Boolean bool = this.equivalentBases.get(baseSequence);
        if (bool == null) {
            boolean equals = this.baseSeq.equals(baseSequence);
            this.equivalentBases.put(baseSequence, Boolean.valueOf(equals));
            return equals;
        }
        return bool.booleanValue();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public SequenceBuilder append(CharSequence charSequence, int i, int i2) {
        if ((charSequence instanceof BasedSequence) && isCommonBaseSequence((BasedSequence) charSequence)) {
            if (((BasedSequence) charSequence).isNotNull()) {
                if (i == 0 && i2 == charSequence.length()) {
                    ((BasedSequence) charSequence).addSegments(this.segments);
                } else {
                    ((BasedSequence) charSequence).subSequence(i, i2).addSegments(this.segments);
                }
                this.resultSeq = null;
            }
        } else if (charSequence != null && i < i2) {
            if (i == 0 && i2 == charSequence.length()) {
                this.segments.append(charSequence);
            } else {
                this.segments.append(charSequence.subSequence(i, i2));
            }
            this.resultSeq = null;
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public SequenceBuilder append(char c) {
        this.segments.append(c);
        this.resultSeq = null;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SequenceBuilder append(char c, int i) {
        if (i > 0) {
            this.segments.append(c, i);
            this.resultSeq = null;
        }
        return this;
    }

    public SequenceBuilder append(int i, int i2) {
        return addByOffsets(i, i2);
    }

    public SequenceBuilder append(Range range) {
        return addRange(range);
    }

    public SequenceBuilder addRange(Range range) {
        this.segments.append(range);
        this.resultSeq = null;
        return this;
    }

    public SequenceBuilder addByOffsets(int i, int i2) {
        if (i < 0 || i > i2 || i2 > this.baseSeq.length()) {
            throw new IllegalArgumentException("addByOffsets start/end must be a valid range in [0, " + this.baseSeq.length() + "], got: [" + i + ", " + i2 + "]");
        }
        this.segments.append(Range.of(i, i2));
        this.resultSeq = null;
        return this;
    }

    public SequenceBuilder addByLength(int i, int i2) {
        return add(this.baseSeq.subSequence(i, i + i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public BasedSequence toSequence() {
        if (this.resultSeq == null) {
            this.resultSeq = SegmentedSequence.create(this);
        }
        return this.resultSeq;
    }

    public BasedSequence toSequence(BasedSequence basedSequence) {
        return toSequence(basedSequence, null, null);
    }

    public BasedSequence toSequence(BasedSequence basedSequence, CharPredicate charPredicate, CharPredicate charPredicate2) {
        if (basedSequence == this.altBase) {
            return toSequence();
        }
        if (!$assertionsDisabled && !basedSequence.equals(this.altBase)) {
            throw new AssertionError(String.format("altSequence must be character identical to builder.altBase\naltBase: '%s'\n altSeq: '%s'\n", this.altBase.toVisibleWhitespaceString(), basedSequence.toVisibleWhitespaceString()));
        }
        SequenceBuilder sequenceBuilder = new SequenceBuilder(basedSequence, this.segments.options, this.segments.optimizer, new HashMap());
        int i = 0;
        Iterator<Object> it = this.segments.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (!(next instanceof Range)) {
                if (next instanceof CharSequence) {
                    sequenceBuilder.append((CharSequence) next);
                } else if (next != null) {
                    throw new IllegalStateException("Invalid part type " + next.getClass());
                }
            } else {
                BasedSequence subSequence = basedSequence.subSequence(i + ((Range) next).getStart(), i + ((Range) next).getEnd());
                int countLeading = charPredicate == null ? 0 : subSequence.countLeading(charPredicate);
                int i2 = countLeading;
                if (countLeading > 0) {
                    i += i2;
                    subSequence = basedSequence.subSequence(i + ((Range) next).getStart(), i + ((Range) next).getEnd());
                }
                sequenceBuilder.append((CharSequence) subSequence);
            }
        }
        BasedSequence create = SegmentedSequence.create(sequenceBuilder);
        BasedSequence sequence = toSequence();
        if (!$assertionsDisabled && SequenceUtils.compare(create, sequence, false, charPredicate2) != 0) {
            throw new AssertionError(String.format("result must be character identical to builder.toSequence()\nresult: '%s'\n sequence: '%s'\n", create.toVisibleWhitespaceString(), sequence.toVisibleWhitespaceString()));
        }
        return create;
    }

    public BasedSequence toSequenceByIndex(BasedSequence basedSequence, CharPredicate charPredicate, CharPredicate charPredicate2) {
        if (basedSequence == this.altBase) {
            return toSequence();
        }
        if (!$assertionsDisabled && !basedSequence.equals(this.altBase)) {
            throw new AssertionError(String.format("altSequence must be character identical to builder.altBase\naltBase: '%s'\n altSeq: '%s'\n", this.altBase.toVisibleWhitespaceString(), basedSequence.toVisibleWhitespaceString()));
        }
        SequenceBuilder sequenceBuilder = new SequenceBuilder(basedSequence, this.segments.options, this.segments.optimizer, new HashMap());
        int i = 0;
        int i2 = 0;
        Iterator<Object> it = this.segments.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (!(next instanceof Range)) {
                if (next instanceof CharSequence) {
                    sequenceBuilder.append((CharSequence) next);
                    i += ((CharSequence) next).length();
                } else if (next != null) {
                    throw new IllegalStateException("Invalid part type " + next.getClass());
                }
            } else {
                BasedSequence subSequence = basedSequence.subSequence(i + i2, i + i2 + ((Range) next).getSpan());
                int countLeading = charPredicate == null ? 0 : subSequence.countLeading(charPredicate);
                int i3 = countLeading;
                if (countLeading > 0) {
                    i2 += i3;
                    subSequence = basedSequence.subSequence(i + i2, i + i2 + ((Range) next).getSpan());
                }
                sequenceBuilder.append((CharSequence) subSequence);
                i += ((Range) next).getSpan();
            }
        }
        BasedSequence create = SegmentedSequence.create(sequenceBuilder);
        BasedSequence sequence = toSequence();
        if (!$assertionsDisabled && SequenceUtils.compare(create, sequence, false, charPredicate2) != 0) {
            throw new AssertionError(String.format("result must be character identical to builder.toSequence()\nresult: '%s'\n sequence: '%s'\n", create.toVisibleWhitespaceString(), sequence.toVisibleWhitespaceString()));
        }
        return create;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public int length() {
        return this.segments.length();
    }

    public String toStringWithRanges() {
        return this.segments.toStringWithRangesVisibleWhitespace(this.baseSeq);
    }

    public String toStringWithRanges(boolean z) {
        return z ? this.segments.toStringWithRangesVisibleWhitespace(this.baseSeq) : this.segments.toStringWithRanges(this.baseSeq);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = this.segments.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Range) {
                BasedSequence subSequence = this.baseSeq.subSequence(((Range) next).getStart(), ((Range) next).getEnd());
                if (subSequence.isNotEmpty()) {
                    subSequence.appendTo(sb);
                }
            } else if (next instanceof CharSequence) {
                sb.append(next);
            } else if (next != null) {
                throw new IllegalStateException("Invalid part type " + next.getClass());
            }
        }
        return sb.toString();
    }

    public String toStringNoAddedSpaces() {
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = this.segments.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Range) {
                sb.append((CharSequence) this.baseSeq.subSequence(((Range) next).getStart(), ((Range) next).getEnd()));
            } else if (next instanceof CharSequence) {
                sb.append(next);
            } else if (next != null) {
                throw new IllegalStateException("Invalid part type " + next.getClass());
            }
        }
        return sb.toString();
    }

    public static SequenceBuilder emptyBuilder(BasedSequence basedSequence) {
        return new SequenceBuilder(basedSequence, null);
    }

    public static SequenceBuilder emptyBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer) {
        return new SequenceBuilder(basedSequence, segmentOptimizer);
    }

    public static SequenceBuilder emptyBuilder(BasedSequence basedSequence, int i) {
        return new SequenceBuilder(basedSequence, i, null, new HashMap());
    }

    public static SequenceBuilder emptyBuilder(BasedSequence basedSequence, int i, SegmentOptimizer segmentOptimizer) {
        return new SequenceBuilder(basedSequence, i, segmentOptimizer, new HashMap());
    }
}
