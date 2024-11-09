package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TrackedOffset.class */
public final class TrackedOffset implements Comparable<TrackedOffset> {
    private static final int F_AFTER_SPACE_EDIT;
    private static final int F_AFTER_INSERT;
    private static final int F_AFTER_DELETE;
    private final TrackedOffset original;
    private final int offset;
    private final int flags;
    private int spacesBefore;
    private int spacesAfter;
    private boolean isSpliced;
    private int index;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TrackedOffset$Flags.class */
    private enum Flags {
        AFTER_SPACE_EDIT,
        AFTER_INSERT,
        AFTER_DELETE
    }

    static {
        $assertionsDisabled = !TrackedOffset.class.desiredAssertionStatus();
        F_AFTER_SPACE_EDIT = BitFieldSet.intMask(Flags.AFTER_SPACE_EDIT);
        F_AFTER_INSERT = BitFieldSet.intMask(Flags.AFTER_INSERT);
        F_AFTER_DELETE = BitFieldSet.intMask(Flags.AFTER_DELETE);
    }

    private TrackedOffset(int i, boolean z, boolean z2, boolean z3) {
        this.original = null;
        this.offset = i;
        int i2 = z ? 0 | F_AFTER_SPACE_EDIT : 0;
        i2 = z2 ? i2 | F_AFTER_INSERT : i2;
        this.flags = z3 ? i2 | F_AFTER_DELETE : i2;
        this.index = -1;
        this.spacesBefore = -1;
        this.spacesAfter = -1;
    }

    private TrackedOffset(TrackedOffset trackedOffset) {
        this.original = trackedOffset.original;
        this.offset = trackedOffset.offset;
        this.flags = trackedOffset.flags;
        this.index = -1;
        this.spacesBefore = trackedOffset.spacesBefore;
        this.spacesAfter = trackedOffset.spacesAfter;
    }

    private TrackedOffset(TrackedOffset trackedOffset, int i) {
        this.original = trackedOffset;
        this.offset = i;
        this.flags = trackedOffset.flags;
        this.index = -1;
        this.spacesBefore = trackedOffset.spacesBefore;
        this.spacesAfter = trackedOffset.spacesAfter;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getSpacesBefore() {
        return this.spacesBefore;
    }

    public final void setSpacesBefore(int i) {
        this.spacesBefore = i;
    }

    public final int getSpacesAfter() {
        return this.spacesAfter;
    }

    public final void setSpacesAfter(int i) {
        this.spacesAfter = i;
    }

    public final boolean isSpliced() {
        return this.isSpliced;
    }

    public final void setSpliced(boolean z) {
        this.isSpliced = z;
    }

    public final boolean isResolved() {
        return this.index != -1;
    }

    public final int getIndex() {
        return this.index == -1 ? this.offset : this.index;
    }

    public final void setIndex(int i) {
        if (this.original != null) {
            this.original.index = i;
        }
        this.index = i;
    }

    public final boolean isAfterSpaceEdit() {
        return BitFieldSet.any(this.flags, F_AFTER_SPACE_EDIT);
    }

    public final boolean isAfterInsert() {
        return BitFieldSet.any(this.flags, F_AFTER_INSERT);
    }

    public final boolean isAfterDelete() {
        return BitFieldSet.any(this.flags, F_AFTER_DELETE);
    }

    public final TrackedOffset plusOffsetDelta(int i) {
        return new TrackedOffset(this, this.offset + i);
    }

    public final TrackedOffset withOffset(int i) {
        return new TrackedOffset(this, i);
    }

    @Override // java.lang.Comparable
    public final int compareTo(TrackedOffset trackedOffset) {
        return Integer.compare(this.offset, trackedOffset.offset);
    }

    public final int compareTo(Integer num) {
        return Integer.compare(this.offset, num.intValue());
    }

    public final int compareTo(int i) {
        return Integer.compare(this.offset, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass() || (obj instanceof Integer)) {
            return obj instanceof Integer ? ((Integer) obj).intValue() == this.offset : this.offset == ((TrackedOffset) obj).offset;
        }
        return false;
    }

    public final int hashCode() {
        return this.offset;
    }

    public final String toString() {
        String str;
        String str2;
        StringBuilder append = new StringBuilder("{").append(this.offset).append(isSpliced() ? " ><" : "");
        if (this.spacesBefore >= 0 || this.spacesAfter >= 0) {
            str = SequenceUtils.SPACE + (this.spacesBefore >= 0 ? Integer.toString(this.spacesBefore) : TypeDescription.Generic.OfWildcardType.SYMBOL) + "|" + (this.spacesAfter >= 0 ? Integer.toString(this.spacesAfter) : TypeDescription.Generic.OfWildcardType.SYMBOL);
        } else {
            str = "";
        }
        StringBuilder append2 = append.append(str);
        if (BitFieldSet.any(this.flags, F_AFTER_SPACE_EDIT | F_AFTER_INSERT | F_AFTER_DELETE)) {
            str2 = SequenceUtils.SPACE + (isAfterSpaceEdit() ? "s" : "") + (isAfterInsert() ? FlexmarkHtmlConverter.I_NODE : "") + (isAfterDelete() ? "d" : "");
        } else {
            str2 = "";
        }
        return append2.append(str2).append(isResolved() ? " -> " + this.index : "").append("}").toString();
    }

    public static TrackedOffset track(TrackedOffset trackedOffset) {
        return new TrackedOffset(trackedOffset);
    }

    public static TrackedOffset track(int i) {
        return track(i, false, false, false);
    }

    public static TrackedOffset track(int i, Character ch, boolean z) {
        return track(i, ch != null && ch.charValue() == ' ', (ch == null || z) ? false : true, z);
    }

    public static TrackedOffset track(int i, boolean z, boolean z2, boolean z3) {
        if ($assertionsDisabled || !((z2 || z3) && z2 == z3)) {
            return new TrackedOffset(i, z, z2, z3);
        }
        throw new AssertionError("Cannot have both afterInsert and afterDelete true");
    }
}
