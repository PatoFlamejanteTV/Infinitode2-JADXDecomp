package com.vladsch.flexmark.util.sequence.builder.tree;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/BasedOffsetTracker.class */
public class BasedOffsetTracker {
    protected final BasedSequence sequence;
    protected final SegmentOffsetTree segmentOffsetTree;
    private Segment lastSegment;

    protected BasedOffsetTracker(BasedSequence basedSequence, SegmentTree segmentTree) {
        this.sequence = basedSequence;
        this.segmentOffsetTree = segmentTree.getSegmentOffsetTree(basedSequence.getBaseSequence());
    }

    protected BasedOffsetTracker(BasedSequence basedSequence, SegmentOffsetTree segmentOffsetTree) {
        this.sequence = basedSequence;
        this.segmentOffsetTree = segmentOffsetTree;
    }

    public int size() {
        return this.segmentOffsetTree.size();
    }

    public OffsetInfo getOffsetInfo(int i, boolean z) {
        int i2;
        int i3;
        OffsetInfo offsetInfo;
        int i4;
        int i5;
        int i6 = z ? i : i + 1;
        int i7 = i6;
        if (i6 <= this.sequence.getStartOffset()) {
            offsetInfo = new OffsetInfo(-1, i, true, 0);
        } else if (i >= this.sequence.getEndOffset()) {
            offsetInfo = new OffsetInfo(this.segmentOffsetTree.size(), i, true, this.sequence.length());
        } else {
            Segment findSegmentByOffset = this.segmentOffsetTree.findSegmentByOffset(i, this.sequence.getBaseSequence(), this.lastSegment);
            if (findSegmentByOffset == null) {
                if (i < this.segmentOffsetTree.getSegment(0, this.sequence).getStartOffset()) {
                    offsetInfo = new OffsetInfo(-1, i, true, 0);
                } else {
                    if (i < this.segmentOffsetTree.getSegment(this.segmentOffsetTree.size() - 1, this.sequence).getEndOffset()) {
                        throw new IllegalStateException("Unexpected");
                    }
                    offsetInfo = new OffsetInfo(this.segmentOffsetTree.size(), i, true, this.sequence.length());
                }
            } else {
                this.lastSegment = findSegmentByOffset;
                if (i7 <= findSegmentByOffset.getStartOffset() || i >= findSegmentByOffset.getEndOffset()) {
                    if (i7 <= findSegmentByOffset.getStartOffset()) {
                        Segment previousText = this.segmentOffsetTree.getPreviousText(findSegmentByOffset, this.sequence);
                        if (previousText != null) {
                            i4 = previousText.getStartIndex();
                            i5 = previousText.getEndIndex();
                        } else {
                            int startIndex = findSegmentByOffset.getStartIndex();
                            i4 = startIndex;
                            i5 = startIndex;
                        }
                        offsetInfo = new OffsetInfo(findSegmentByOffset.getPos() - 1, i, true, i4, i5);
                    } else if (i >= findSegmentByOffset.getEndOffset()) {
                        Segment nextText = this.segmentOffsetTree.getNextText(findSegmentByOffset, this.sequence);
                        if (nextText != null) {
                            i2 = nextText.getStartIndex();
                            i3 = nextText.getEndIndex();
                        } else {
                            int endIndex = findSegmentByOffset.getEndIndex();
                            i2 = endIndex;
                            i3 = endIndex;
                        }
                        offsetInfo = new OffsetInfo(findSegmentByOffset.getPos() + 1, i, true, i2, i3);
                    } else {
                        throw new IllegalStateException(String.format("Unexpected offset: [%d, %d), seg: %s, not inside nor at start nor at end", Integer.valueOf(i), Integer.valueOf(i7), findSegmentByOffset.toString()));
                    }
                } else {
                    offsetInfo = new OffsetInfo(findSegmentByOffset.getPos(), i, z, (findSegmentByOffset.getStartIndex() + i) - findSegmentByOffset.getStartOffset(), (findSegmentByOffset.getStartIndex() + i7) - findSegmentByOffset.getStartOffset());
                }
            }
        }
        return offsetInfo;
    }

    public BasedSequence getSequence() {
        return this.sequence;
    }

    public SegmentOffsetTree getSegmentOffsetTree() {
        return this.segmentOffsetTree;
    }

    public String toString() {
        return "BasedOffsetTracker{tree=" + this.segmentOffsetTree + '}';
    }

    public static BasedOffsetTracker create(BasedSequence basedSequence) {
        return new BasedOffsetTracker(basedSequence, basedSequence.getSegmentTree());
    }

    public static BasedOffsetTracker create(BasedSequence basedSequence, SegmentOffsetTree segmentOffsetTree) {
        return new BasedOffsetTracker(basedSequence, segmentOffsetTree);
    }
}
