package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats;
import com.vladsch.flexmark.util.sequence.builder.tree.Segment;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTreeRange;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/SegmentedSequenceTree.class */
public final class SegmentedSequenceTree extends SegmentedSequence {
    private final SegmentTree segmentTree;
    private final int startIndex;
    private final int startPos;
    private final int endPos;
    private final ThreadLocal<Cache> cache;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentedSequenceTree.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/SegmentedSequenceTree$Cache.class */
    public static class Cache {
        final Segment segment;
        final CharSequence chars;
        final int indexDelta;

        public Cache(Segment segment, CharSequence charSequence, int i) {
            this.segment = segment;
            this.chars = charSequence;
            this.indexDelta = i - segment.getStartIndex();
        }

        public char charAt(int i) {
            return this.chars.charAt(i + this.indexDelta);
        }

        public int charIndex(int i) {
            return i + this.indexDelta;
        }
    }

    private SegmentedSequenceTree(BasedSequence basedSequence, int i, int i2, int i3, SegmentTree segmentTree) {
        super(basedSequence, i, i2, i3);
        this.cache = new ThreadLocal<>();
        this.segmentTree = segmentTree;
        this.startIndex = 0;
        this.startPos = 0;
        this.endPos = segmentTree.size();
    }

    private SegmentedSequenceTree(BasedSequence basedSequence, SegmentTree segmentTree, SegmentTreeRange segmentTreeRange) {
        super(basedSequence, segmentTreeRange.startOffset, segmentTreeRange.endOffset, segmentTreeRange.length);
        this.cache = new ThreadLocal<>();
        this.segmentTree = segmentTree;
        this.startIndex = segmentTreeRange.startIndex;
        this.startPos = segmentTreeRange.startPos;
        this.endPos = segmentTreeRange.endPos;
    }

    private Cache getCache(int i) {
        Cache cache = this.cache.get();
        Cache cache2 = cache;
        if (cache == null || cache2.segment.notInSegment(i + this.startIndex)) {
            Segment findSegment = this.segmentTree.findSegment(i + this.startIndex, this.startPos, this.endPos, this.baseSeq, cache2 == null ? null : cache2.segment);
            if (!$assertionsDisabled && findSegment == null) {
                throw new AssertionError();
            }
            cache2 = new Cache(findSegment, findSegment.getCharSequence(), this.startIndex);
            this.cache.set(cache2);
        }
        return cache2;
    }

    private Segment getCachedSegment() {
        Cache cache = this.cache.get();
        if (cache == null) {
            return null;
        }
        return cache.segment;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getIndexOffset(int i) {
        if (i == this.length) {
            Cache cache = getCache(i - 1);
            CharSequence charSequence = cache.chars;
            if (charSequence instanceof BasedSequence) {
                return ((BasedSequence) charSequence).getIndexOffset(cache.charIndex(i));
            }
            return -1;
        }
        SequenceUtils.validateIndexInclusiveEnd(i, length());
        Cache cache2 = getCache(i);
        CharSequence charSequence2 = cache2.chars;
        if (charSequence2 instanceof BasedSequence) {
            return ((BasedSequence) charSequence2).getIndexOffset(cache2.charIndex(i));
        }
        return -1;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder) {
        this.segmentTree.addSegments(iBasedSegmentBuilder, this.startIndex, this.startIndex + this.length, this.startOffset, this.endOffset, this.startPos, this.endPos);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final SegmentTree getSegmentTree() {
        return this.segmentTree;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        SequenceUtils.validateIndex(i, length());
        return getCache(i).charAt(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public final BasedSequence subSequence(int i, int i2) {
        if (i == 0 && i2 == this.length) {
            return this;
        }
        SequenceUtils.validateStartEnd(i, i2, length());
        return new SegmentedSequenceTree(this.baseSeq, this.segmentTree, this.segmentTree.getSegmentRange(i + this.startIndex, i2 + this.startIndex, this.startPos, this.endPos, this.baseSeq, getCachedSegment()));
    }

    public static SegmentedSequenceTree create(BasedSequence basedSequence, ISegmentBuilder<?> iSegmentBuilder) {
        SegmentedSequenceStats segmentedSequenceStats;
        SegmentTree build = SegmentTree.build(iSegmentBuilder.getSegments(), iSegmentBuilder.getText());
        if (basedSequence.anyOptions(F_COLLECT_SEGMENTED_STATS) && (segmentedSequenceStats = (SegmentedSequenceStats) basedSequence.getOption(SEGMENTED_STATS)) != null) {
            segmentedSequenceStats.addStats(iSegmentBuilder.noAnchorsSize(), iSegmentBuilder.length(), (build.getTreeData().length << 2) + build.getSegmentBytes().length);
        }
        return new SegmentedSequenceTree(basedSequence.getBaseSequence(), iSegmentBuilder.getStartOffset(), iSegmentBuilder.getEndOffset(), iSegmentBuilder.length(), build);
    }
}
