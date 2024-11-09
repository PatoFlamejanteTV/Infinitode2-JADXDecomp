package com.vladsch.flexmark.util.sequence.builder.tree;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/SegmentTreeRange.class */
public class SegmentTreeRange {
    public final int startIndex;
    public final int endIndex;
    public final int startOffset;
    public final int endOffset;
    public final int startPos;
    public final int endPos;
    public final int length;

    public SegmentTreeRange(int i, int i2, int i3, int i4, int i5, int i6) {
        this.startIndex = i;
        this.endIndex = i2;
        this.startOffset = i3;
        this.endOffset = i4;
        this.startPos = i5;
        this.endPos = i6;
        this.length = i2 - i;
    }

    public String toString() {
        return "SegmentTreeRange{startIndex=" + this.startIndex + ", endIndex=" + this.endIndex + ", startOffset=" + this.startOffset + ", endOffset=" + this.endOffset + ", startPos=" + this.startPos + ", endPos=" + this.endPos + ", length=" + this.length + '}';
    }
}
