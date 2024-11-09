package com.vladsch.flexmark.util.sequence;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/ReplacedTextMapper.class */
public class ReplacedTextMapper {
    private ReplacedTextMapper parent;
    private BasedSequence original;
    private ArrayList<ReplacedTextRegion> regions;
    private ArrayList<BasedSequence> replacedSegments;
    private int replacedLength;
    private BasedSequence replacedSequence;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ReplacedTextMapper.class.desiredAssertionStatus();
    }

    public ReplacedTextMapper(BasedSequence basedSequence) {
        this.regions = new ArrayList<>();
        this.replacedSegments = new ArrayList<>();
        this.replacedLength = 0;
        this.replacedSequence = null;
        this.original = basedSequence;
        this.parent = null;
    }

    private ReplacedTextMapper(ReplacedTextMapper replacedTextMapper) {
        this.regions = new ArrayList<>();
        this.replacedSegments = new ArrayList<>();
        this.replacedLength = 0;
        this.replacedSequence = null;
        this.parent = replacedTextMapper.parent;
        this.original = replacedTextMapper.original;
        this.regions = replacedTextMapper.regions;
        this.replacedSegments = replacedTextMapper.replacedSegments;
        this.replacedLength = replacedTextMapper.replacedLength;
        this.replacedSequence = replacedTextMapper.getReplacedSequence();
    }

    public void startNestedReplacement(BasedSequence basedSequence) {
        if (!$assertionsDisabled && !basedSequence.equals(getReplacedSequence())) {
            throw new AssertionError();
        }
        this.parent = new ReplacedTextMapper(this);
        this.original = basedSequence;
        this.regions = new ArrayList<>();
        this.replacedSegments = new ArrayList<>();
        this.replacedLength = 0;
        this.replacedSequence = null;
    }

    public boolean isModified() {
        return this.replacedLength > 0;
    }

    public boolean isFinalized() {
        return this.replacedSequence != null;
    }

    private void finalizeMods() {
        if (this.replacedSequence == null) {
            this.replacedSequence = this.replacedSegments.isEmpty() ? BasedSequence.NULL : SegmentedSequence.create(this.original, this.replacedSegments);
        }
    }

    public ReplacedTextMapper getParent() {
        return this.parent;
    }

    public void addReplacedText(int i, int i2, BasedSequence basedSequence) {
        if (isFinalized()) {
            throw new IllegalStateException("Cannot modify finalized ReplacedTextMapper");
        }
        this.regions.add(new ReplacedTextRegion(this.original.subSequence(i, i2).getSourceRange(), Range.of(i, i2), Range.of(this.replacedLength, this.replacedLength + basedSequence.length())));
        this.replacedLength += basedSequence.length();
        this.replacedSegments.add(basedSequence);
    }

    public void addOriginalText(int i, int i2) {
        if (isFinalized()) {
            throw new IllegalStateException("Cannot modify finalized ReplacedTextMapper");
        }
        if (i < i2) {
            BasedSequence subSequence = this.original.subSequence(i, i2);
            this.regions.add(new ReplacedTextRegion(subSequence.getSourceRange(), Range.of(i, i2), Range.of(this.replacedLength, this.replacedLength + subSequence.length())));
            this.replacedLength += subSequence.length();
            this.replacedSegments.add(subSequence);
        }
    }

    public ArrayList<ReplacedTextRegion> getRegions() {
        finalizeMods();
        return this.regions;
    }

    public ArrayList<BasedSequence> getReplacedSegments() {
        finalizeMods();
        return this.replacedSegments;
    }

    public BasedSequence getReplacedSequence() {
        finalizeMods();
        return this.replacedSequence;
    }

    public int getReplacedLength() {
        finalizeMods();
        return this.replacedLength;
    }

    private int parentOriginalOffset(int i) {
        return this.parent != null ? this.parent.originalOffset(i) : i;
    }

    public int originalOffset(int i) {
        finalizeMods();
        if (this.regions.isEmpty()) {
            return parentOriginalOffset(i);
        }
        if (i == this.replacedLength) {
            return parentOriginalOffset(this.original.length());
        }
        int i2 = i;
        Iterator<ReplacedTextRegion> it = this.regions.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ReplacedTextRegion next = it.next();
            if (next.containsReplacedIndex(i)) {
                int start = (next.getOriginalRange().getStart() + i) - next.getReplacedRange().getStart();
                i2 = start;
                if (start > next.getOriginalRange().getEnd()) {
                    i2 = next.getOriginalRange().getEnd();
                }
            }
        }
        return parentOriginalOffset(i2);
    }
}
