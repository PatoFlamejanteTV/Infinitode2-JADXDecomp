package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.PositionAnchor;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/CharRecoveryOptimizer.class */
public class CharRecoveryOptimizer implements SegmentOptimizer {
    private final PositionAnchor anchor;
    private int prevEolPos;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CharRecoveryOptimizer.class.desiredAssertionStatus();
    }

    public CharRecoveryOptimizer(PositionAnchor positionAnchor) {
        this.anchor = positionAnchor;
    }

    int prevMatchPos(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        int min = Math.min(Math.min(charSequence.length(), i2) - i, charSequence2.length());
        for (int i3 = 0; i3 < min; i3++) {
            char charAt = charSequence.charAt(i3 + i);
            char charAt2 = charSequence2.charAt(i3);
            if (charAt == SequenceUtils.EOL_CHAR) {
                this.prevEolPos = i3 + 1;
            }
            if (charAt2 != charAt) {
                return i3;
            }
        }
        return min;
    }

    int nextMatchPos(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        int max = Math.max(0, i);
        int min = Math.min(charSequence.length(), i2);
        int length = charSequence2.length();
        int min2 = Math.min(min - max, length);
        int i3 = min - min2;
        int i4 = length - min2;
        int i5 = min2;
        do {
            int i6 = i5;
            i5--;
            if (i6 > 0) {
            } else {
                return i4;
            }
        } while (charSequence2.charAt(i4 + i5) == charSequence.charAt(i3 + i5));
        return i4 + i5 + 1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.SegmentOptimizer, java.util.function.BiFunction
    public Object[] apply(CharSequence charSequence, Object[] objArr) {
        int prevMatchPos;
        int nextMatchPos;
        int endOfLine;
        int endOfLine2;
        if (objArr.length != 3 || !(objArr[0] instanceof Range) || !(objArr[1] instanceof CharSequence) || !(objArr[2] instanceof Range)) {
            return objArr;
        }
        Range range = (Range) objArr[0];
        CharSequence charSequence2 = (CharSequence) objArr[1];
        Range range2 = (Range) objArr[2];
        int length = charSequence2.length();
        if ((range.isNull() && range2.isNull()) || length == 0) {
            return objArr;
        }
        int length2 = charSequence.length();
        Range range3 = range;
        Range range4 = range2;
        this.prevEolPos = -1;
        if (range3.isNull()) {
            prevMatchPos = 0;
        } else {
            prevMatchPos = prevMatchPos(charSequence, charSequence2, range3.getEnd(), range4.isNotNull() ? range4.getStart() : length2);
        }
        int i = prevMatchPos;
        if (range4.isNull()) {
            nextMatchPos = length;
        } else {
            nextMatchPos = nextMatchPos(charSequence, charSequence2, range3.isNotNull() ? range3.getEnd() : 0, range4.getStart());
        }
        int i2 = nextMatchPos;
        if (i == 0 && i2 == length) {
            if (range3.isNotNull() && !SequenceUtils.endsWithEOL(charSequence.subSequence(range3.getStart(), range3.getEnd())) && SequenceUtils.startsWith(charSequence2, SequenceUtils.EOL) && (endOfLine2 = SequenceUtils.endOfLine(charSequence, range3.getEnd())) < length2 && SequenceUtils.isBlank(charSequence.subSequence(range3.getEnd(), endOfLine2))) {
                Range ofLength = Range.ofLength(endOfLine2, 1);
                CharSequence subSequence = charSequence2.subSequence(1, length);
                if (range4.isEmpty() && range4.getStart() < ofLength.getEnd()) {
                    range4 = Range.NULL;
                }
                if (subSequence.length() == 0) {
                    objArr[1] = ofLength;
                    objArr[2] = range4;
                } else if (range3.isNull()) {
                    objArr[0] = ofLength;
                    objArr[1] = subSequence;
                    objArr[2] = range4;
                } else if (range4.isNull()) {
                    objArr[1] = ofLength;
                    objArr[2] = subSequence;
                } else {
                    Object[] objArr2 = new Object[objArr.length + 1];
                    objArr = objArr2;
                    objArr2[0] = range3;
                    objArr[1] = ofLength;
                    objArr[2] = subSequence;
                    objArr[3] = range4;
                }
            }
            return objArr;
        }
        if (this.prevEolPos != -1 && this.prevEolPos < i) {
            i = this.prevEolPos;
            if (i2 < i) {
                i2 = i;
            }
        }
        if (!$assertionsDisabled && i2 > length) {
            throw new AssertionError("prevRange: " + range + ", '" + Utils.escapeJavaString(charSequence2) + "', nextRange: " + range2);
        }
        int i3 = i;
        int i4 = length - i2;
        int min = (i3 + i4) - Math.min(length, (range4.isNotNull() ? range4.getStart() : length2) - (range3.isNotNull() ? range3.getEnd() : 0));
        if (min > 0) {
            if (!$assertionsDisabled && (i4 <= 0 || i3 <= 0)) {
                throw new AssertionError("prevRange: " + range + ", '" + Utils.escapeJavaString(charSequence2) + "', nextRange: " + range2);
            }
            switch (this.anchor) {
                case PREVIOUS:
                    int min2 = Math.min(i3, min);
                    i3 -= min2;
                    i4 -= min - min2;
                    break;
                case NEXT:
                    int min3 = Math.min(i4, min);
                    i4 -= min3;
                    i3 -= min - min3;
                    break;
                default:
                    int min4 = Math.min(i3, min >> 1);
                    i3 -= min4;
                    i4 -= min - min4;
                    break;
            }
        }
        if (i3 > 0) {
            range3 = range3.endPlus(i3);
        }
        if (i4 > 0) {
            range4 = range4.startMinus(i4);
        }
        CharSequence subSequence2 = charSequence2.subSequence(i3, length - i4);
        Range range5 = Range.NULL;
        if (range3.isNotNull() && !SequenceUtils.endsWithEOL(charSequence.subSequence(range3.getStart(), range3.getEnd())) && SequenceUtils.startsWith(subSequence2, SequenceUtils.EOL) && (endOfLine = SequenceUtils.endOfLine(charSequence, range3.getEnd())) < length2 && ((range4.isNull() || endOfLine < range4.getStart()) && SequenceUtils.isBlank(charSequence.subSequence(range3.getEnd(), endOfLine)))) {
            range5 = Range.ofLength(endOfLine, 1);
            subSequence2 = subSequence2.subSequence(1, subSequence2.length());
        }
        if (range3.isNotNull() && range4.isNotNull() && subSequence2.length() == 0 && range3.isAdjacentBefore(range4)) {
            objArr[0] = range3.expandToInclude(range4);
            objArr[1] = null;
            objArr[2] = null;
        } else if (range5.isNotNull()) {
            if (range4.isEmpty() && range4.getStart() < range5.getEnd()) {
                range4 = Range.NULL;
            }
            if (subSequence2.length() == 0) {
                objArr[0] = range3;
                objArr[1] = range5;
                objArr[2] = range4;
            } else if (range3.isNull()) {
                objArr[0] = range5;
                objArr[1] = subSequence2;
                objArr[2] = range4;
            } else if (range4.isNull()) {
                objArr[0] = range3;
                objArr[1] = range5;
                objArr[2] = subSequence2;
            } else {
                Object[] objArr3 = new Object[objArr.length + 1];
                objArr = objArr3;
                objArr3[0] = range3;
                objArr[1] = range5;
                objArr[2] = subSequence2;
                objArr[3] = range4;
            }
        } else {
            objArr[0] = range3;
            objArr[1] = subSequence2;
            objArr[2] = range4;
        }
        return objArr;
    }
}
