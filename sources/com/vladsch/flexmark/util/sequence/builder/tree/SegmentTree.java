package com.vladsch.flexmark.util.sequence.builder.tree;

import com.vladsch.flexmark.util.misc.DelimitedBuilder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.BasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.Seg;
import com.vladsch.flexmark.util.sequence.builder.tree.Segment;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/SegmentTree.class */
public class SegmentTree {
    public static final int MAX_VALUE = 536870911;
    public static final int F_ANCHOR_FLAGS = -536870912;
    protected final int[] treeData;
    protected final byte[] segmentBytes;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentTree.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SegmentTree(int[] iArr, byte[] bArr) {
        this.treeData = iArr;
        this.segmentBytes = bArr;
    }

    public int[] getTreeData() {
        return this.treeData;
    }

    public byte[] getSegmentBytes() {
        return this.segmentBytes;
    }

    public int size() {
        return this.treeData.length / 2;
    }

    public int aggrLength(int i) {
        if (i < 0) {
            return 0;
        }
        return this.treeData[i << 1];
    }

    public int byteOffsetData(int i) {
        return this.treeData[(i << 1) + 1];
    }

    public int byteOffset(int i) {
        return getByteOffset(this.treeData[(i << 1) + 1]);
    }

    public static int getByteOffset(int i) {
        int i2 = i & MAX_VALUE;
        if (i2 == 536870911) {
            return -1;
        }
        return i2;
    }

    public static int getAnchorOffset(int i) {
        return (i & F_ANCHOR_FLAGS) >>> 29;
    }

    public boolean hasPreviousAnchor(int i) {
        return getAnchorOffset(this.treeData[(i << 1) + 1]) > 0;
    }

    public int previousAnchorOffset(int i) {
        int byteOffsetData = byteOffsetData(i);
        return getByteOffset(byteOffsetData) - getAnchorOffset(byteOffsetData);
    }

    public SegmentTreePos findSegmentPos(int i) {
        return findSegmentPos(i, this.treeData, 0, size());
    }

    public Segment getSegment(int i, int i2, int i3, BasedSequence basedSequence) {
        return Segment.getSegment(this.segmentBytes, i, i2, i3, basedSequence);
    }

    public Segment findSegment(int i, BasedSequence basedSequence, Segment segment) {
        return findSegment(i, 0, size(), basedSequence, segment);
    }

    public Segment findSegment(int i, int i2, int i3, BasedSequence basedSequence, Segment segment) {
        if (segment != null) {
            int startIndex = segment.getStartIndex();
            if (i >= startIndex) {
                int endIndex = segment.getEndIndex();
                if (!$assertionsDisabled && i < endIndex) {
                    throw new AssertionError(String.format("FindSegment should not be called, index %d is in range [%d, %d) of hint segment: %s", Integer.valueOf(i), Integer.valueOf(startIndex), Integer.valueOf(endIndex), segment));
                }
                if (segment.pos + 1 >= i3) {
                    return null;
                }
                if (i < aggrLength(segment.pos + 1)) {
                    return Segment.getSegment(this.segmentBytes, byteOffset(segment.pos + 1), segment.pos + 1, endIndex, basedSequence);
                }
                i2 = segment.pos + 2;
            } else {
                if (segment.pos == i2) {
                    return null;
                }
                int aggrLength = aggrLength(segment.pos - 2);
                if (i >= aggrLength) {
                    return Segment.getSegment(this.segmentBytes, byteOffset(segment.pos - 1), segment.pos - 1, aggrLength, basedSequence);
                }
                i3 = segment.pos - 1;
            }
        }
        if (i2 >= 0 && i2 < size()) {
            if (i < aggrLength(i2)) {
                int aggrLength2 = aggrLength(i2 - 1);
                if (i >= aggrLength2) {
                    return Segment.getSegment(this.segmentBytes, byteOffset(i2), i2, aggrLength2, basedSequence);
                }
                i3 = i2;
            } else {
                i2++;
            }
        }
        if (i3 - 1 >= i2) {
            int aggrLength3 = aggrLength(i3 - 2);
            if (i >= aggrLength3) {
                if (i >= aggrLength(i3 - 1)) {
                    return null;
                }
                return Segment.getSegment(this.segmentBytes, byteOffset(i3 - 1), i3 - 1, aggrLength3, basedSequence);
            }
            i3--;
        }
        SegmentTreePos findSegmentPos = findSegmentPos(i, i2, i3);
        if (findSegmentPos != null) {
            return Segment.getSegment(this.segmentBytes, byteOffset(findSegmentPos.pos), findSegmentPos.pos, findSegmentPos.startIndex, basedSequence);
        }
        return null;
    }

    public SegmentTreeRange getSegmentRange(int i, int i2, int i3, int i4, BasedSequence basedSequence, Segment segment) {
        Segment findSegment;
        Segment findSegment2;
        int startOffset;
        int startOffset2;
        if (i == i2) {
            Segment findSegment3 = (segment == null || segment.notInSegment(i)) ? findSegment(i, i3, i4, basedSequence, segment) : segment;
            findSegment = findSegment3;
            if (findSegment3 == null) {
                if (!$assertionsDisabled && i <= 0) {
                    throw new AssertionError();
                }
                findSegment = (segment == null || segment.notInSegment(i - 1)) ? findSegment(i - 1, i3, i4, basedSequence, segment) : segment;
                if (!$assertionsDisabled && findSegment == null) {
                    throw new AssertionError();
                }
                if (findSegment.notInSegment(i) && findSegment.pos + 1 < size()) {
                    Segment segment2 = getSegment(findSegment.pos + 1, basedSequence);
                    if (!segment2.notInSegment(i)) {
                        findSegment = segment2;
                    }
                }
            }
            findSegment2 = findSegment;
        } else {
            findSegment = (segment == null || segment.notInSegment(i)) ? findSegment(i, i3, i4, basedSequence, segment) : segment;
            if (!$assertionsDisabled && findSegment == null) {
                throw new AssertionError();
            }
            findSegment2 = !findSegment.notInSegment(i2 - 1) ? findSegment : (segment == null || segment.notInSegment(i2 - 1)) ? findSegment(i2 - 1, i3, i4, basedSequence, findSegment) : segment;
            if (!$assertionsDisabled && findSegment2 == null) {
                throw new AssertionError();
            }
        }
        if (findSegment.isText()) {
            startOffset = getTextStartOffset(findSegment, basedSequence);
        } else {
            startOffset = (findSegment.getStartOffset() + i) - findSegment.getStartIndex();
        }
        if (findSegment2.isText()) {
            startOffset2 = getTextEndOffset(findSegment2, basedSequence);
        } else {
            startOffset2 = (findSegment2.getStartOffset() + i2) - findSegment2.getStartIndex();
        }
        if (startOffset < 0) {
            if (findSegment.pos + 1 < size()) {
                int startOffset3 = getSegment(findSegment.pos + 1, basedSequence).getStartOffset();
                startOffset = startOffset3;
                if (startOffset3 > startOffset2 && startOffset2 != -1) {
                    startOffset = startOffset2;
                }
            } else {
                startOffset = startOffset2;
            }
        }
        if (startOffset2 < startOffset) {
            startOffset2 = startOffset;
        }
        if (startOffset > basedSequence.length()) {
            throw new IllegalStateException(String.format("startOffset:%d > baseSeq.length: %d", Integer.valueOf(startOffset), Integer.valueOf(basedSequence.length())));
        }
        if (startOffset2 > basedSequence.length()) {
            throw new IllegalStateException(String.format("endOffset:%d > baseSeq.length: %d", Integer.valueOf(startOffset2), Integer.valueOf(basedSequence.length())));
        }
        return new SegmentTreeRange(i, i2, startOffset, startOffset2, findSegment.pos, findSegment2.pos + 1);
    }

    public int getTextEndOffset(Segment segment, BasedSequence basedSequence) {
        if (!$assertionsDisabled && !segment.isText()) {
            throw new AssertionError();
        }
        if (segment.pos + 1 < size()) {
            Segment segment2 = getSegment(segment.pos + 1, basedSequence);
            if (segment2.isBase()) {
                return segment2.getStartOffset();
            }
            return -1;
        }
        return -1;
    }

    public int getTextStartOffset(Segment segment, BasedSequence basedSequence) {
        if (!$assertionsDisabled && !segment.isText()) {
            throw new AssertionError();
        }
        Segment prevAnchor = getPrevAnchor(segment.pos, basedSequence);
        Segment segment2 = prevAnchor;
        if (prevAnchor == null && segment.pos > 0) {
            segment2 = getSegment(segment.pos - 1, basedSequence);
        }
        if (segment2 != null && segment2.isBase()) {
            return segment2.getEndOffset();
        }
        return -1;
    }

    public void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder, SegmentTreeRange segmentTreeRange) {
        addSegments(iBasedSegmentBuilder, segmentTreeRange.startIndex, segmentTreeRange.endIndex, segmentTreeRange.startOffset, segmentTreeRange.endOffset, segmentTreeRange.startPos, segmentTreeRange.endPos);
    }

    public void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder, int i, int i2, int i3, int i4, int i5, int i6) {
        Segment prevAnchor;
        if (i3 != -1) {
            iBasedSegmentBuilder.appendAnchor(i3);
        }
        int i7 = i3;
        BasedSequence baseSequence = iBasedSegmentBuilder.getBaseSequence();
        for (int i8 = i5; i8 < i6; i8++) {
            Segment segment = getSegment(i8, baseSequence);
            if (segment.isText() && (prevAnchor = getPrevAnchor(i8, baseSequence)) != null) {
                iBasedSegmentBuilder.appendAnchor(prevAnchor.getStartOffset());
            }
            CharSequence charSequence = getCharSequence(segment, i, i2, i5, i6);
            if (segment.isText()) {
                iBasedSegmentBuilder.append(charSequence);
                int byteLength = segment.byteOffset + segment.getByteLength();
                if (byteLength < this.segmentBytes.length && (i8 + 1 >= size() || byteLength != byteOffset(i8 + 1))) {
                    Segment segment2 = Segment.getSegment(this.segmentBytes, byteLength, 0, 0, baseSequence);
                    if (segment2.isAnchor()) {
                        iBasedSegmentBuilder.appendAnchor(segment2.getStartOffset());
                    }
                }
            } else {
                if (!$assertionsDisabled && !(charSequence instanceof BasedSequence)) {
                    throw new AssertionError();
                }
                BasedSequence basedSequence = (BasedSequence) charSequence;
                i7 = Math.max(i7, basedSequence.getEndOffset());
                iBasedSegmentBuilder.append(basedSequence.getStartOffset(), basedSequence.getEndOffset());
            }
        }
        if (i4 != -1) {
            iBasedSegmentBuilder.appendAnchor(Math.max(i7, i4));
        }
    }

    public static CharSequence getCharSequence(Segment segment, int i, int i2, int i3, int i4) {
        CharSequence charSequence;
        int i5 = segment.pos;
        if (i5 == i3 && i5 + 1 == i4) {
            charSequence = segment.getCharSequence().subSequence(i - segment.getStartIndex(), i2 - segment.getStartIndex());
        } else if (i5 == i3) {
            charSequence = segment.getCharSequence().subSequence(i - segment.getStartIndex(), segment.length());
        } else if (i5 + 1 == i4) {
            charSequence = segment.getCharSequence().subSequence(0, i2 - segment.getStartIndex());
        } else {
            charSequence = segment.getCharSequence();
        }
        return charSequence;
    }

    public SegmentTreePos findSegmentPos(int i, int i2, int i3) {
        return findSegmentPos(i, this.treeData, i2, i3);
    }

    public Segment getSegment(int i, BasedSequence basedSequence) {
        return Segment.getSegment(this.segmentBytes, byteOffset(i), i, aggrLength(i - 1), basedSequence);
    }

    public Segment getPrevAnchor(int i, BasedSequence basedSequence) {
        return getPrevAnchor(i, this.treeData, this.segmentBytes, basedSequence);
    }

    public String toString(BasedSequence basedSequence) {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(", ");
        delimitedBuilder.append(getClass().getSimpleName()).append("{aggr: {");
        int size = size();
        for (int i = 0; i < size; i++) {
            delimitedBuilder.append("[").append(aggrLength(i)).append(", ").append(byteOffset(i)).append(":");
            if (hasPreviousAnchor(i)) {
                delimitedBuilder.append(", ").append(previousAnchorOffset(i)).append(":");
            }
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

    public String toString() {
        return toString(BasedSequence.NULL);
    }

    public static int aggrLength(int i, int[] iArr) {
        if (i < 0) {
            return 0;
        }
        return iArr[i << 1];
    }

    public static int byteOffsetData(int i, int[] iArr) {
        return iArr[(i << 1) + 1];
    }

    public static int byteOffset(int i, int[] iArr) {
        return getByteOffset(byteOffsetData(i, iArr));
    }

    public static void setTreeData(int i, int[] iArr, int i2, int i3, int i4) {
        if (!$assertionsDisabled && i3 > 536870911) {
            throw new AssertionError();
        }
        iArr[i << 1] = i2;
        iArr[(i << 1) + 1] = i3 | (i4 == 0 ? 0 : i4 << 29);
    }

    public static boolean hasPreviousAnchor(int i, int[] iArr) {
        return getAnchorOffset(iArr[(i << 1) + 1]) > 0;
    }

    public static int previousAnchorOffset(int i, int[] iArr) {
        int byteOffsetData = byteOffsetData(i, iArr);
        return getByteOffset(byteOffsetData) - getAnchorOffset(byteOffsetData);
    }

    public static SegmentTreePos findSegmentPos(int i, int[] iArr, int i2, int i3) {
        if (i == 0 && i2 == 0) {
            return new SegmentTreePos(0, 0, 0);
        }
        int i4 = 0;
        while (i2 < i3) {
            int i5 = (i2 + i3) >> 1;
            int i6 = i2;
            int i7 = i3;
            i4++;
            if (i >= aggrLength(i5, iArr)) {
                i2 = i5 + 1;
            } else {
                int aggrLength = aggrLength(i5 - 1, iArr);
                if (i < aggrLength) {
                    i3 = i5;
                } else {
                    return new SegmentTreePos(i5, aggrLength, i4);
                }
            }
            if (!$assertionsDisabled && i6 == i2 && i7 == i3) {
                throw new AssertionError("Range and position did not change after iteration: pos=" + i5 + ", startPos=" + i2 + ", endPos=" + i3 + SequenceUtils.EOL + Arrays.toString(iArr));
            }
        }
        return null;
    }

    public static Segment findSegment(int i, int[] iArr, int i2, int i3, byte[] bArr, BasedSequence basedSequence) {
        SegmentTreePos findSegmentPos = findSegmentPos(i, iArr, i2, i3);
        if (findSegmentPos != null) {
            return Segment.getSegment(bArr, byteOffset(findSegmentPos.pos, iArr), findSegmentPos.pos, findSegmentPos.startIndex, basedSequence);
        }
        return null;
    }

    public static Segment getSegment(int i, int[] iArr, byte[] bArr, BasedSequence basedSequence) {
        return Segment.getSegment(bArr, byteOffset(i, iArr), i, aggrLength(i, iArr), basedSequence);
    }

    public static Segment getPrevAnchor(int i, int[] iArr, byte[] bArr, BasedSequence basedSequence) {
        int byteOffsetData = byteOffsetData(i, iArr);
        int anchorOffset = getAnchorOffset(byteOffsetData);
        if (anchorOffset > 0) {
            Segment segment = Segment.getSegment(bArr, getByteOffset(byteOffsetData) - anchorOffset, -1, 0, basedSequence);
            if ($assertionsDisabled || segment.isAnchor()) {
                return segment;
            }
            throw new AssertionError();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/SegmentTree$SegmentTreeData.class */
    public static class SegmentTreeData {
        public final int[] treeData;
        public final byte[] segmentBytes;
        public final int[] startIndices;

        public SegmentTreeData(int[] iArr, byte[] bArr, int[] iArr2) {
            this.treeData = iArr;
            this.segmentBytes = bArr;
            this.startIndices = iArr2;
        }
    }

    public static SegmentTree build(Iterable<Seg> iterable, CharSequence charSequence) {
        SegmentTreeData buildTreeData = buildTreeData(iterable, charSequence, true);
        return new SegmentTree(buildTreeData.treeData, buildTreeData.segmentBytes);
    }

    public static SegmentTree build(BasedSegmentBuilder basedSegmentBuilder) {
        SegmentTreeData buildTreeData = buildTreeData(basedSegmentBuilder.getSegments(), basedSegmentBuilder.getText(), true);
        return new SegmentTree(buildTreeData.treeData, buildTreeData.segmentBytes);
    }

    public static SegmentTreeData buildTreeData(Iterable<Seg> iterable, CharSequence charSequence, boolean z) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Seg seg : iterable) {
            Segment.SegType segType = Segment.getSegType(seg, charSequence);
            i += Segment.getSegByteLength(segType, seg.getSegStart(), seg.length());
            if (z) {
                if (segType == Segment.SegType.ANCHOR) {
                    i3 = seg.getEnd();
                }
                i2++;
                i3 = seg.getEnd();
            } else {
                if (segType != Segment.SegType.BASE && segType != Segment.SegType.ANCHOR) {
                    i3 = seg.getEnd();
                }
                i2++;
                i3 = seg.getEnd();
            }
        }
        int[] iArr = new int[i2 << 1];
        byte[] bArr = new byte[i];
        int[] iArr2 = z ? null : new int[i2];
        int[] iArr3 = z ? null : new int[2];
        int i4 = 0;
        int i5 = -1;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (Seg seg2 : iterable) {
            int i9 = i7;
            i7 = Segment.addSegBytes(bArr, i7, seg2, charSequence);
            Segment.SegType fromTypeMask = Segment.SegType.fromTypeMask(bArr[i9]);
            if (z) {
                if (fromTypeMask == Segment.SegType.ANCHOR) {
                    i5 = i9;
                } else {
                    i8 += seg2.length();
                    setTreeData(i6, iArr, i8, i9, i5 == -1 ? 0 : i9 - i5);
                    i6++;
                    i5 = -1;
                }
            } else {
                iArr2[i6] = i8;
                if (i4 > 0 && seg2.getStart() >= 0) {
                    int i10 = i4;
                    for (int i11 = 0; i11 < i10; i11++) {
                        iArr[iArr3[i11] << 1] = seg2.getStart();
                    }
                    i4 = 0;
                }
                i8 += seg2.length();
                if (fromTypeMask == Segment.SegType.BASE || fromTypeMask == Segment.SegType.ANCHOR) {
                    setTreeData(i6, iArr, seg2.getEnd(), i9, 0);
                    int i12 = i4;
                    i4++;
                    iArr3[i12] = i6;
                    i6++;
                }
            }
        }
        if (!z) {
            for (int i13 = 0; i13 < i4; i13++) {
                iArr[iArr3[i13] << 1] = i3;
            }
        }
        return new SegmentTreeData(iArr, bArr, iArr2);
    }

    public SegmentOffsetTree getSegmentOffsetTree(BasedSequence basedSequence) {
        int i = 0;
        int length = this.segmentBytes.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Segment segment = Segment.getSegment(this.segmentBytes, i2, i, 0, basedSequence);
            i2 += segment.getByteLength();
            if (segment.isBase()) {
                i++;
                i3 = segment.getEndOffset();
            }
        }
        int[] iArr = new int[i << 1];
        int[] iArr2 = new int[i];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int[] iArr3 = new int[2];
        int i7 = 0;
        while (i5 < length) {
            Segment segment2 = Segment.getSegment(this.segmentBytes, i5, i, i6, basedSequence);
            if (i7 > 0 && segment2.getStartOffset() >= 0) {
                int i8 = i7;
                for (int i9 = 0; i9 < i8; i9++) {
                    iArr[iArr3[i9] << 1] = segment2.getStartOffset();
                }
                i7 = 0;
            }
            if (segment2.isBase()) {
                setTreeData(i4, iArr, segment2.getEndOffset(), i5, 0);
                int i10 = i7;
                i7++;
                iArr3[i10] = i4;
                iArr2[i4] = i6;
                i4++;
            }
            i5 += segment2.getByteLength();
            i6 += segment2.length();
        }
        for (int i11 = 0; i11 < i7; i11++) {
            iArr[iArr3[i11] << 1] = i3;
        }
        return new SegmentOffsetTree(iArr, this.segmentBytes, iArr2);
    }
}
