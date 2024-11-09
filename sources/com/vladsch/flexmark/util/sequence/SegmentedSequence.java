package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.collection.iteration.ArrayIterable;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/SegmentedSequence.class */
public abstract class SegmentedSequence extends BasedSequenceImpl implements ReplacedBasedSequence {
    protected final BasedSequence baseSeq;
    protected final int startOffset;
    protected final int endOffset;
    protected final int length;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentedSequence.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SegmentedSequence(BasedSequence basedSequence, int i, int i2, int i3) {
        super(0);
        if (!$assertionsDisabled && basedSequence != basedSequence.getBaseSequence()) {
            throw new AssertionError();
        }
        if (i < 0 && i2 < 0) {
            i = 0;
            i2 = 0;
        }
        if (!$assertionsDisabled && i < 0) {
            throw new AssertionError("startOffset: " + i);
        }
        if (!$assertionsDisabled && (i2 < i || i2 > basedSequence.length())) {
            throw new AssertionError("endOffset: " + i2);
        }
        this.baseSeq = basedSequence;
        this.startOffset = i;
        this.endOffset = i2;
        this.length = i3;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Object getBase() {
        return this.baseSeq.getBase();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence getBaseSequence() {
        return this.baseSeq;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getStartOffset() {
        return this.startOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getEndOffset() {
        return this.endOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final int getOptionFlags() {
        return getBaseSequence().getOptionFlags();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean allOptions(int i) {
        return getBaseSequence().allOptions(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean anyOptions(int i) {
        return getBaseSequence().anyOptions(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final <T> T getOption(DataKeyBase<T> dataKeyBase) {
        return (T) getBaseSequence().getOption(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final DataHolder getOptions() {
        return getBaseSequence().getOptions();
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.length;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Range getSourceRange() {
        return Range.of(getStartOffset(), getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence baseSubSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, this.baseSeq.length());
        return this.baseSeq.baseSubSequence(i, i2);
    }

    public static BasedSequence create(BasedSequence basedSequence, Iterable<? extends BasedSequence> iterable) {
        return create(basedSequence.getBuilder().addAll(iterable));
    }

    public static BasedSequence create(BasedSequence... basedSequenceArr) {
        return basedSequenceArr.length == 0 ? BasedSequence.NULL : create(basedSequenceArr[0], new ArrayIterable(basedSequenceArr));
    }

    public static BasedSequence create(SequenceBuilder sequenceBuilder) {
        BasedSequence singleBasedSequence = sequenceBuilder.getSingleBasedSequence();
        if (singleBasedSequence != null) {
            return singleBasedSequence;
        }
        if (!sequenceBuilder.isEmpty()) {
            BasedSequence baseSequence = sequenceBuilder.getBaseSequence();
            if (baseSequence.anyOptions(F_FULL_SEGMENTED_SEQUENCES)) {
                return SegmentedSequenceFull.create(baseSequence, (ISegmentBuilder<?>) sequenceBuilder.getSegmentBuilder());
            }
            if (baseSequence.anyOptions(F_TREE_SEGMENTED_SEQUENCES)) {
                return SegmentedSequenceTree.create(baseSequence, (ISegmentBuilder<?>) sequenceBuilder.getSegmentBuilder());
            }
            return SegmentedSequenceTree.create(baseSequence, (ISegmentBuilder<?>) sequenceBuilder.getSegmentBuilder());
        }
        return BasedSequence.NULL;
    }

    @Deprecated
    public static BasedSequence of(BasedSequence basedSequence, Iterable<? extends BasedSequence> iterable) {
        return create(basedSequence, iterable);
    }

    @Deprecated
    public static BasedSequence of(BasedSequence... basedSequenceArr) {
        return create(basedSequenceArr);
    }
}
