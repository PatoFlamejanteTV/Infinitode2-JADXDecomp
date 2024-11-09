package com.vladsch.flexmark.util.sequence.builder.tree;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/OffsetInfo.class */
public class OffsetInfo {
    public final int pos;
    public final int offset;
    public final boolean isEndOffset;
    public final int startIndex;
    public final int endIndex;

    public OffsetInfo(int i, int i2, boolean z, int i3) {
        this(i, i2, z, i3, i3);
    }

    public OffsetInfo(int i, int i2, boolean z, int i3, int i4) {
        this.pos = i;
        this.offset = i2;
        this.isEndOffset = z;
        this.startIndex = i3;
        this.endIndex = i4;
    }

    public String toString() {
        return "OffsetInfo{ p=" + this.pos + ", o=" + (this.isEndOffset ? "[" + this.offset + ")" : "[" + this.offset + ", " + (this.offset + 1) + ")") + ", i=[" + this.startIndex + ", " + this.endIndex + ") }";
    }
}
