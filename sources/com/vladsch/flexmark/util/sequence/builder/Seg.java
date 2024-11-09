package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.Range;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/Seg.class */
public class Seg {
    public static final Seg NULL;
    public static final Seg ANCHOR_0;
    public static final int MAX_TEXT_OFFSET = 1073741823;
    public static final int F_TEXT_OPTION = 1073741824;
    private final int start;
    private final int end;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Seg.class.desiredAssertionStatus();
        NULL = new Seg(Range.NULL.getStart(), Range.NULL.getEnd());
        ANCHOR_0 = new Seg(0, 0);
    }

    private Seg(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getSegStart() {
        return isText() ? getTextStart() : this.start;
    }

    public int getSegEnd() {
        return isText() ? getTextEnd() : this.end;
    }

    public int getTextStart() {
        return getTextOffset(this.start);
    }

    public static int getTextOffset(int i) {
        return ((-i) - 1) & MAX_TEXT_OFFSET;
    }

    public int getTextEnd() {
        return getTextOffset(this.end);
    }

    public boolean isFirst256Start() {
        return isFirst256Start(this.start);
    }

    public static boolean isFirst256Start(int i) {
        return (((-i) - 1) & 1073741824) != 0;
    }

    public boolean isRepeatedTextEnd() {
        return isRepeatedTextEnd(this.end);
    }

    public static boolean isRepeatedTextEnd(int i) {
        return (((-i) - 1) & 1073741824) != 0;
    }

    public boolean isText() {
        return this.start < 0 && this.end < 0 && (this.start & MAX_TEXT_OFFSET) > (this.end & MAX_TEXT_OFFSET);
    }

    public boolean isBase() {
        return this.start >= 0 && this.end >= 0 && this.start <= this.end;
    }

    public boolean isAnchor() {
        return this.start >= 0 && this.end >= 0 && this.start == this.end;
    }

    public boolean isNull() {
        return (isBase() || isText()) ? false : true;
    }

    public Range getRange() {
        if ($assertionsDisabled || isBase()) {
            return Range.of(this.start, this.end);
        }
        throw new AssertionError();
    }

    public int length() {
        if (isBase()) {
            return this.end - this.start;
        }
        if (isText()) {
            return (this.start & MAX_TEXT_OFFSET) - (this.end & MAX_TEXT_OFFSET);
        }
        return 0;
    }

    public String toString(CharSequence charSequence) {
        if (isNull()) {
            return "NULL";
        }
        if (isBase()) {
            if (this.start == this.end) {
                return "[" + this.start + ")";
            }
            return "[" + this.start + ", " + this.end + ")";
        }
        CharSequence subSequence = charSequence.subSequence(getTextStart(), getTextEnd());
        if (isRepeatedTextEnd() && length() > 1) {
            if (isFirst256Start()) {
                return "a:" + length() + "x'" + Utils.escapeJavaString(subSequence.subSequence(0, 1)) + "'";
            }
            return length() + "x'" + Utils.escapeJavaString(subSequence.subSequence(0, 1)) + "'";
        }
        String charSequence2 = length() <= 20 ? subSequence.toString() : subSequence.subSequence(0, 10).toString() + "…" + subSequence.subSequence(length() - 10, length()).toString();
        if (isFirst256Start()) {
            return "a:'" + Utils.escapeJavaString(charSequence2) + "'";
        }
        return "'" + Utils.escapeJavaString(charSequence2) + "'";
    }

    public String toString() {
        if (isNull()) {
            return "NULL";
        }
        if (isBase()) {
            if (this.start == this.end) {
                return "BASE[" + this.start + ")";
            }
            return "BASE[" + this.start + ", " + this.end + ")";
        }
        return "TEXT[" + getTextStart() + ", " + getTextEnd() + ")";
    }

    public static Seg segOf(int i, int i2) {
        return (i == 0 && i2 == 0) ? ANCHOR_0 : new Seg(i, i2);
    }

    public static int getTextStart(int i, boolean z) {
        if ($assertionsDisabled || i < 1073741823) {
            return (-(z ? i | 1073741824 : i)) - 1;
        }
        throw new AssertionError();
    }

    public static int getTextEnd(int i, boolean z) {
        if ($assertionsDisabled || i < 1073741823) {
            return (-(z ? i | 1073741824 : i)) - 1;
        }
        throw new AssertionError();
    }

    public static Seg textOf(int i, int i2, boolean z, boolean z2) {
        return new Seg(getTextStart(i, z), getTextEnd(i2, z2));
    }
}
