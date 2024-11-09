package com.vladsch.flexmark.util.sequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/ReplacedTextRegion.class */
public class ReplacedTextRegion {
    private final Range base;
    private final Range original;
    private final Range replaced;

    public ReplacedTextRegion(Range range, Range range2, Range range3) {
        this.base = range;
        this.original = range2;
        this.replaced = range3;
    }

    public Range getBaseRange() {
        return this.base;
    }

    public Range getOriginalRange() {
        return this.original;
    }

    public Range getReplacedRange() {
        return this.replaced;
    }

    public boolean containsReplacedIndex(int i) {
        return this.replaced.contains(i);
    }

    public boolean containsBaseIndex(int i) {
        return this.base.contains(i);
    }

    public boolean containsOriginalIndex(int i) {
        return this.original.contains(i);
    }
}
