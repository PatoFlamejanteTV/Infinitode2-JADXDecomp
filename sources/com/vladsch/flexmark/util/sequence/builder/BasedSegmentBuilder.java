package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PositionAnchor;
import com.vladsch.flexmark.util.sequence.Range;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/BasedSegmentBuilder.class */
public class BasedSegmentBuilder extends SegmentBuilderBase<BasedSegmentBuilder> implements IBasedSegmentBuilder<BasedSegmentBuilder> {
    final BasedSequence baseSeq;
    final SegmentOptimizer optimizer;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !BasedSegmentBuilder.class.desiredAssertionStatus();
    }

    protected BasedSegmentBuilder(BasedSequence basedSequence) {
        this(basedSequence, new CharRecoveryOptimizer(PositionAnchor.CURRENT));
    }

    protected BasedSegmentBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer) {
        this.baseSeq = basedSequence.getBaseSequence();
        this.optimizer = segmentOptimizer;
    }

    protected BasedSegmentBuilder(BasedSequence basedSequence, int i) {
        this(basedSequence, new CharRecoveryOptimizer(PositionAnchor.CURRENT), i);
    }

    protected BasedSegmentBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer, int i) {
        super(i);
        this.baseSeq = basedSequence.getBaseSequence();
        this.optimizer = segmentOptimizer;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder
    public BasedSequence getBaseSequence() {
        return this.baseSeq;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.SegmentBuilderBase
    protected Object[] optimizeText(Object[] objArr) {
        return this.optimizer.apply((CharSequence) this.baseSeq, objArr);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.SegmentBuilderBase
    protected Object[] handleOverlap(Object[] objArr) {
        Range withEnd;
        Range range = (Range) objArr[0];
        CharSequence charSequence = (CharSequence) objArr[1];
        Range range2 = (Range) objArr[2];
        if (!$assertionsDisabled && (range.isNull() || range.getEnd() <= range2.getStart())) {
            throw new AssertionError();
        }
        Range range3 = Range.NULL;
        if (range2.getEnd() <= range.getStart()) {
            withEnd = range2;
        } else if (range2.getStart() <= range.getStart()) {
            withEnd = Range.of(range2.getStart(), Math.min(range2.getEnd(), range.getEnd()));
            if (range.getEnd() < range2.getEnd()) {
                range3 = Range.of(range.getEnd(), range2.getEnd());
            }
        } else if (range2.getEnd() <= range.getEnd()) {
            withEnd = range2;
        } else {
            if (!$assertionsDisabled && range2.getStart() >= range.getEnd()) {
                throw new AssertionError();
            }
            withEnd = range2.withEnd(range.getEnd());
            range3 = range2.withStart(range.getEnd());
        }
        int span = withEnd.getSpan();
        if (!$assertionsDisabled && span + range3.getSpan() != range2.getSpan()) {
            throw new AssertionError();
        }
        if (charSequence.length() == 0) {
            objArr[1] = this.baseSeq.subSequence(withEnd.getStart(), withEnd.getEnd()).toString();
        } else {
            objArr[1] = charSequence.toString() + this.baseSeq.subSequence(withEnd.getStart(), withEnd.getEnd()).toString();
        }
        objArr[2] = range3;
        return objArr;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder
    public String toStringWithRangesVisibleWhitespace() {
        return super.toStringWithRangesVisibleWhitespace(this.baseSeq);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder
    public String toStringWithRanges() {
        return super.toStringWithRanges(this.baseSeq);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder
    public String toStringChars() {
        return super.toString(this.baseSeq);
    }

    public static BasedSegmentBuilder emptyBuilder(BasedSequence basedSequence) {
        return new BasedSegmentBuilder(basedSequence);
    }

    public static BasedSegmentBuilder emptyBuilder(BasedSequence basedSequence, int i) {
        return new BasedSegmentBuilder(basedSequence, i);
    }

    public static BasedSegmentBuilder emptyBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer) {
        return new BasedSegmentBuilder(basedSequence, segmentOptimizer);
    }

    public static BasedSegmentBuilder emptyBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer, int i) {
        return new BasedSegmentBuilder(basedSequence, segmentOptimizer, i);
    }
}
