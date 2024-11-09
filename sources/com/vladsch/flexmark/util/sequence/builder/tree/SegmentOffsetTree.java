package com.vladsch.flexmark.util.sequence.builder.tree;

import com.vladsch.flexmark.util.misc.DelimitedBuilder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.BasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.Seg;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/SegmentOffsetTree.class */
public class SegmentOffsetTree extends SegmentTree {
    protected final int[] startIndices;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentOffsetTree.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SegmentOffsetTree(int[] iArr, byte[] bArr, int[] iArr2) {
        super(iArr, bArr);
        this.startIndices = iArr2;
    }

    public static SegmentOffsetTree build(Iterable<Seg> iterable, CharSequence charSequence) {
        SegmentTree.SegmentTreeData buildTreeData = buildTreeData(iterable, charSequence, false);
        if ($assertionsDisabled || buildTreeData.startIndices != null) {
            return new SegmentOffsetTree(buildTreeData.treeData, buildTreeData.segmentBytes, buildTreeData.startIndices);
        }
        throw new AssertionError();
    }

    public static SegmentOffsetTree build(BasedSegmentBuilder basedSegmentBuilder) {
        SegmentTree.SegmentTreeData buildTreeData = buildTreeData(basedSegmentBuilder.getSegments(), basedSegmentBuilder.getText(), true);
        return new SegmentTree(buildTreeData.treeData, buildTreeData.segmentBytes).getSegmentOffsetTree(basedSegmentBuilder.getBaseSequence());
    }

    public static SegmentOffsetTree build(BasedSequence basedSequence) {
        return basedSequence.getSegmentTree().getSegmentOffsetTree(basedSequence);
    }

    public int endOffset(int i) {
        return super.aggrLength(i);
    }

    public int getStartIndex(int i) {
        if (i < 0) {
            return 0;
        }
        return i >= this.startIndices.length ? this.startIndices[this.startIndices.length - 1] : this.startIndices[i];
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    public Segment getSegment(int i, BasedSequence basedSequence) {
        return Segment.getSegment(this.segmentBytes, byteOffset(i), i, this.startIndices[i], basedSequence);
    }

    public SegmentTreePos findSegmentPosByOffset(int i) {
        return findSegmentPos(i, this.treeData, 0, size());
    }

    public Segment getPreviousText(Segment segment, BasedSequence basedSequence) {
        if (segment.getPos() == 0) {
            if (segment.getStartIndex() > 0) {
                Segment segment2 = getSegment(0, -1, 0, basedSequence);
                if (segment2.isText()) {
                    return segment2;
                }
                return null;
            }
            return null;
        }
        return getNextText(getSegment(segment.getPos() - 1, basedSequence), basedSequence);
    }

    public Segment getNextText(Segment segment, BasedSequence basedSequence) {
        if (segment.getByteOffset() + segment.getByteLength() < this.segmentBytes.length) {
            Segment segment2 = getSegment(segment.getByteOffset() + segment.getByteLength(), -1, segment.getEndIndex(), basedSequence);
            if (segment2.isText()) {
                return segment2;
            }
            return null;
        }
        return null;
    }

    public Segment findSegmentByOffset(int i, BasedSequence basedSequence, Segment segment) {
        SegmentTreePos findSegmentPos = super.findSegmentPos(i, 0, size());
        if (findSegmentPos != null) {
            return Segment.getSegment(this.segmentBytes, byteOffset(findSegmentPos.pos), findSegmentPos.pos, this.startIndices[findSegmentPos.pos], basedSequence);
        }
        return null;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    public String toString(BasedSequence basedSequence) {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(", ");
        delimitedBuilder.append(getClass().getSimpleName()).append("{aggr: {");
        int size = size();
        for (int i = 0; i < size; i++) {
            delimitedBuilder.append("[").append(aggrLength(i)).append(", ").append(byteOffset(i)).append(":");
            delimitedBuilder.append(", :").append(this.startIndices[i]);
            delimitedBuilder.append("]").mark();
        }
        delimitedBuilder.unmark().append(" }, seg: { ");
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.segmentBytes.length) {
                Segment segment = Segment.getSegment(this.segmentBytes, i3, 0, 0, basedSequence);
                delimitedBuilder.append(i3).append(":").append(segment).mark();
                i2 = i3 + segment.getByteLength();
            } else {
                delimitedBuilder.unmark().append(" } }");
                return delimitedBuilder.toString();
            }
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public boolean hasPreviousAnchor(int i) {
        return false;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public int previousAnchorOffset(int i) {
        return 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public int aggrLength(int i) {
        return super.aggrLength(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public SegmentTreePos findSegmentPos(int i) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public Segment findSegment(int i, BasedSequence basedSequence, Segment segment) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public Segment findSegment(int i, int i2, int i3, BasedSequence basedSequence, Segment segment) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public SegmentTreeRange getSegmentRange(int i, int i2, int i3, int i4, BasedSequence basedSequence, Segment segment) {
        return super.getSegmentRange(i, i2, i3, i4, basedSequence, segment);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder, SegmentTreeRange segmentTreeRange) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder, int i, int i2, int i3, int i4, int i5, int i6) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public SegmentTreePos findSegmentPos(int i, int i2, int i3) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree
    @Deprecated
    public Segment getPrevAnchor(int i, BasedSequence basedSequence) {
        throw new IllegalStateException("Method in SegmentOffsetTree should not be used");
    }
}
