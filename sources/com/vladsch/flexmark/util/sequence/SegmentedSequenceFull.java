package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/SegmentedSequenceFull.class */
public final class SegmentedSequenceFull extends SegmentedSequence {
    private final boolean nonBaseChars;
    private final int[] baseOffsets;
    private final int baseStartOffset;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentedSequenceFull.class.desiredAssertionStatus();
    }

    private SegmentedSequenceFull(BasedSequence basedSequence, int i, int i2, int i3, boolean z, int[] iArr, int i4) {
        super(basedSequence, i, i2, i3);
        this.nonBaseChars = z;
        this.baseOffsets = iArr;
        this.baseStartOffset = i4;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getIndexOffset(int i) {
        SequenceUtils.validateIndexInclusiveEnd(i, length());
        int i2 = this.baseOffsets[this.baseStartOffset + i];
        if (i2 < 0) {
            return -1;
        }
        return i2;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder) {
        BasedUtils.generateSegments(iBasedSegmentBuilder, this);
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        SequenceUtils.validateIndex(i, length());
        int i2 = this.baseOffsets[this.baseStartOffset + i];
        if (i2 >= 0) {
            return this.baseSeq.charAt(i2);
        }
        return (char) ((-i2) - 1);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public final BasedSequence subSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
        if (i == 0 && i2 == this.length) {
            return this;
        }
        return subSequence(this.baseSeq, this.baseOffsets, this.baseStartOffset + i, this.nonBaseChars, i2 - i);
    }

    public static SegmentedSequenceFull create(BasedSequence basedSequence, ISegmentBuilder<?> iSegmentBuilder) {
        SegmentedSequenceStats segmentedSequenceStats;
        BasedSequence baseSequence = basedSequence.getBaseSequence();
        int length = iSegmentBuilder.length();
        int[] iArr = new int[length + 1];
        int i = 0;
        for (Object obj : iSegmentBuilder) {
            if (!(obj instanceof Range)) {
                if (!(obj instanceof CharSequence)) {
                    if (obj != null) {
                        throw new IllegalStateException("Invalid part type " + obj.getClass());
                    }
                } else {
                    CharSequence charSequence = (CharSequence) obj;
                    int length2 = charSequence.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        int i3 = i;
                        i++;
                        iArr[i3] = (-charSequence.charAt(i2)) - 1;
                    }
                }
            } else if (!((Range) obj).isEmpty()) {
                int end = ((Range) obj).getEnd();
                for (int start = ((Range) obj).getStart(); start < end; start++) {
                    int i4 = i;
                    i++;
                    iArr[i4] = start;
                }
            }
        }
        int i5 = iArr[length - 1];
        iArr[length] = i5 < 0 ? i5 - 1 : i5 + 1;
        int startOffset = iSegmentBuilder.getStartOffset();
        int endOffset = iSegmentBuilder.getEndOffset();
        boolean z = iSegmentBuilder.getTextLength() > 0;
        if (baseSequence.anyOptions(F_COLLECT_SEGMENTED_STATS) && (segmentedSequenceStats = (SegmentedSequenceStats) baseSequence.getOption(SEGMENTED_STATS)) != null) {
            segmentedSequenceStats.addStats(iSegmentBuilder.noAnchorsSize(), length, iArr.length << 2);
        }
        return new SegmentedSequenceFull(baseSequence, startOffset, endOffset, length, z, iArr, 0);
    }

    private SegmentedSequenceFull subSequence(BasedSequence basedSequence, int[] iArr, int i, boolean z, int i2) {
        int length = iArr.length - 1;
        if (!$assertionsDisabled && i + i2 > length) {
            throw new AssertionError("Sub-sequence offsets list length < baseStartOffset + sub-sequence length");
        }
        int i3 = 0;
        int i4 = 0;
        if (!z) {
            if (i < length) {
                i3 = iArr[i];
            } else {
                i3 = basedSequence.getEndOffset();
            }
            if (i2 == 0) {
                i4 = i3;
            } else {
                i4 = iArr[(i + i2) - 1] + 1;
                if (!$assertionsDisabled && i3 > i4) {
                    throw new AssertionError();
                }
            }
        } else {
            boolean z2 = false;
            int i5 = i;
            while (true) {
                if (i5 >= length) {
                    break;
                }
                if (iArr[i5] < 0) {
                    i5++;
                } else {
                    i3 = iArr[i5];
                    if (i2 != 0) {
                        int i6 = i + i2;
                        while (true) {
                            int i7 = i6;
                            i6--;
                            if (i7 <= i5) {
                                break;
                            }
                            if (iArr[i6] >= 0) {
                                i4 = iArr[i6] + 1;
                                if (!$assertionsDisabled && i3 > i4) {
                                    throw new AssertionError();
                                }
                                z2 = true;
                            }
                        }
                    }
                    if (!z2) {
                        i4 = i3;
                    }
                    z2 = true;
                }
            }
            if (!z2) {
                int endOffset = basedSequence.getEndOffset();
                i3 = endOffset;
                i4 = endOffset;
            }
        }
        return new SegmentedSequenceFull(basedSequence, i3, i4, i2, z, iArr, i);
    }

    @Deprecated
    public static BasedSequence of(BasedSequence basedSequence, Iterable<? extends BasedSequence> iterable) {
        return SegmentedSequence.create(basedSequence, iterable);
    }

    @Deprecated
    public static BasedSequence of(BasedSequence... basedSequenceArr) {
        return SegmentedSequence.create(basedSequenceArr);
    }
}
