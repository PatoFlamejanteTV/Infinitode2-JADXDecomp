package com.vladsch.flexmark.util.sequence.builder.tree;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/SegmentTreePos.class */
public final class SegmentTreePos {
    public final int pos;
    public final int startIndex;
    public final int iterations;

    public SegmentTreePos(int i, int i2, int i3) {
        this.pos = i;
        this.startIndex = i2;
        this.iterations = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SegmentTreePos)) {
            return false;
        }
        SegmentTreePos segmentTreePos = (SegmentTreePos) obj;
        return this.pos == segmentTreePos.pos && this.startIndex == segmentTreePos.startIndex;
    }

    public final int hashCode() {
        return (this.pos * 31) + this.startIndex;
    }

    public final String toString() {
        return "{" + this.pos + ", s: " + this.startIndex + ", i: " + this.iterations + '}';
    }
}
