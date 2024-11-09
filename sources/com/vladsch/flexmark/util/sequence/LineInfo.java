package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.misc.BitField;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.misc.Utils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineInfo.class */
public final class LineInfo {
    public static final Flags BLANK_PREFIX;
    public static final Flags BLANK_TEXT;
    public static final Flags PREFORMATTED;
    public static final int F_PREFORMATTED;
    public static final int F_BLANK_PREFIX;
    public static final int F_BLANK_TEXT;
    public static final LineInfo NULL;
    public final CharSequence lineSeq;
    public final int index;
    public final int prefixLength;
    public final int textLength;
    public final int length;
    public final int sumPrefixLength;
    public final int sumTextLength;
    public final int sumLength;
    public final int flags;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !LineInfo.class.desiredAssertionStatus();
        BLANK_PREFIX = Flags.BLANK_PREFIX;
        BLANK_TEXT = Flags.BLANK_TEXT;
        PREFORMATTED = Flags.PREFORMATTED;
        F_PREFORMATTED = BitFieldSet.intMask(Flags.PREFORMATTED);
        F_BLANK_PREFIX = BitFieldSet.intMask(Flags.BLANK_PREFIX);
        F_BLANK_TEXT = BitFieldSet.intMask(Flags.BLANK_TEXT);
        NULL = new LineInfo(BasedSequence.NULL, -1, 0, 0, 0, 0, 0, 0, true, true, Preformatted.NONE);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineInfo$Flags.class */
    public enum Flags implements BitField {
        PREFORMATTED(2),
        BLANK_PREFIX,
        BLANK_TEXT;

        final int bits;

        Flags() {
            this(1);
        }

        Flags(int i) {
            this.bits = i;
        }

        @Override // com.vladsch.flexmark.util.misc.BitField
        public final int getBits() {
            return this.bits;
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineInfo$Preformatted.class */
    public enum Preformatted {
        NONE,
        FIRST,
        BODY,
        LAST;

        final int mask = BitFieldSet.setBitField(0, Flags.PREFORMATTED, ordinal());

        Preformatted() {
        }

        static Preformatted get(int i) {
            int i2 = i & LineInfo.F_PREFORMATTED;
            return i2 == FIRST.mask ? FIRST : i2 == BODY.mask ? BODY : i2 == LAST.mask ? LAST : NONE;
        }
    }

    private LineInfo(CharSequence charSequence, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2, Preformatted preformatted) {
        if (!$assertionsDisabled && ((charSequence != BasedSequence.NULL || i != -1) && i2 + i3 >= i4)) {
            throw new AssertionError("Line must be terminated by an EOL");
        }
        if (!$assertionsDisabled && charSequence.length() != i4) {
            throw new AssertionError();
        }
        this.lineSeq = charSequence;
        this.index = i;
        this.prefixLength = i2;
        this.textLength = i3;
        this.length = i4;
        this.sumPrefixLength = i5 + i2;
        this.sumTextLength = i6 + i3;
        this.sumLength = i7 + i4;
        this.flags = ((z || i2 == 0) ? F_BLANK_PREFIX : 0) | ((z2 || i3 == 0) ? F_BLANK_TEXT : 0) | preformatted.ordinal();
    }

    public final boolean needAggregateUpdate(LineInfo lineInfo) {
        return (this.sumPrefixLength == lineInfo.sumPrefixLength && this.sumTextLength == lineInfo.sumTextLength && this.sumLength == lineInfo.sumLength) ? false : true;
    }

    public final boolean isNull() {
        return this == NULL;
    }

    public final boolean isNotNull() {
        return this != NULL;
    }

    public final boolean isBlankPrefix() {
        return BitFieldSet.any(this.flags, F_BLANK_PREFIX);
    }

    public final boolean isBlankText() {
        return BitFieldSet.any(this.flags, F_BLANK_TEXT);
    }

    public final boolean isPreformatted() {
        return BitFieldSet.any(this.flags, F_PREFORMATTED);
    }

    public final Preformatted getPreformatted() {
        return Preformatted.get(this.flags);
    }

    public final boolean isBlankTextAndPrefix() {
        return BitFieldSet.all(this.flags, F_BLANK_PREFIX | F_BLANK_TEXT);
    }

    public final int getTextStart() {
        return this.prefixLength;
    }

    public final int getTextEnd() {
        return this.prefixLength + this.textLength;
    }

    public final BasedSequence getLine() {
        return this.lineSeq instanceof BasedSequence ? (BasedSequence) this.lineSeq : BasedSequence.of(this.lineSeq);
    }

    public final BasedSequence getPrefix() {
        return getLine().subSequence(0, this.prefixLength);
    }

    public final BasedSequence getTextNoEOL() {
        return getLine().subSequence(this.prefixLength, this.prefixLength + this.textLength);
    }

    public final BasedSequence getText() {
        return getLine().subSequence(this.prefixLength, this.length);
    }

    public final BasedSequence getLineNoEOL() {
        return getLine().subSequence(0, this.prefixLength + this.textLength);
    }

    public final BasedSequence getEOL() {
        return getLine().subSequence(this.prefixLength + this.textLength, this.length);
    }

    public final String toString() {
        String str;
        StringBuilder append = new StringBuilder("LineInfo{i=").append(this.index).append(", pl=").append(this.prefixLength).append(", tl=").append(this.textLength).append(", l=").append(this.length).append(", sumPl=").append(this.sumPrefixLength).append(", sumTl=").append(this.sumTextLength).append(", sumL=").append(this.sumLength);
        if (this.flags != 0) {
            str = "," + (isBlankPrefix() ? " bp" : "") + (isBlankText() ? " bt" : "") + (isPreformatted() ? " p" : "");
        } else {
            str = "";
        }
        return append.append(str).append(", '").append(Utils.escapeJavaString(this.lineSeq)).append("'}").toString();
    }

    public static LineInfo create(CharSequence charSequence, int i, int i2, int i3, boolean z, boolean z2, Preformatted preformatted) {
        return new LineInfo(charSequence, 0, i, i2, i3, 0, 0, 0, z, z2, preformatted);
    }

    public static LineInfo create(CharSequence charSequence, LineInfo lineInfo, int i, int i2, int i3, boolean z, boolean z2, Preformatted preformatted) {
        return new LineInfo(charSequence, lineInfo.index + 1, i, i2, i3, lineInfo.sumPrefixLength, lineInfo.sumTextLength, lineInfo.sumLength, z, z2, preformatted);
    }

    public static LineInfo create(LineInfo lineInfo, LineInfo lineInfo2) {
        return new LineInfo(lineInfo2.lineSeq, lineInfo.index + 1, lineInfo2.prefixLength, lineInfo2.textLength, lineInfo2.length, lineInfo.sumPrefixLength, lineInfo.sumTextLength, lineInfo.sumLength, lineInfo2.isBlankPrefix(), lineInfo2.isBlankText(), lineInfo2.getPreformatted());
    }
}
