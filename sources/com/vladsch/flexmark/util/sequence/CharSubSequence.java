package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/CharSubSequence.class */
public final class CharSubSequence extends BasedSequenceImpl {
    private final char[] baseChars;
    private final CharSubSequence base;
    private final int startOffset;
    private final int endOffset;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CharSubSequence.class.desiredAssertionStatus();
    }

    private CharSubSequence(char[] cArr, int i) {
        super(i);
        this.base = this;
        this.baseChars = cArr;
        this.startOffset = 0;
        this.endOffset = this.baseChars.length;
    }

    private CharSubSequence(CharSubSequence charSubSequence, int i, int i2) {
        super(0);
        if (!$assertionsDisabled && (i < 0 || i2 < i || i2 > charSubSequence.baseChars.length)) {
            throw new AssertionError(String.format("CharSubSequence must have (startIndex > 0 || endIndex < %d) && endIndex >= startIndex, got startIndex:%d, endIndex: %d", Integer.valueOf(charSubSequence.baseChars.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (!$assertionsDisabled && i <= 0 && i2 >= charSubSequence.baseChars.length) {
            throw new AssertionError(String.format("CharSubSequence must be proper subsequences [1, %d) got startIndex:%d, endIndex: %d", Integer.valueOf(Math.max(0, charSubSequence.baseChars.length - 1)), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        this.base = charSubSequence;
        this.baseChars = charSubSequence.baseChars;
        this.startOffset = this.base.startOffset + i;
        this.endOffset = this.base.startOffset + i2;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final int getOptionFlags() {
        return 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean allOptions(int i) {
        return false;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean anyOptions(int i) {
        return false;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final <T> T getOption(DataKeyBase<T> dataKeyBase) {
        return dataKeyBase.get(null);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final DataHolder getOptions() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final CharSubSequence getBaseSequence() {
        return this.base;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final char[] getBase() {
        return this.baseChars;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getStartOffset() {
        return this.startOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getEndOffset() {
        return this.endOffset;
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.endOffset - this.startOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Range getSourceRange() {
        return Range.of(this.startOffset, this.endOffset);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getIndexOffset(int i) {
        SequenceUtils.validateIndexInclusiveEnd(i, length());
        return this.startOffset + i;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        SequenceUtils.validateIndex(i, length());
        char c = this.baseChars[i + this.startOffset];
        if (c == 0) {
            return (char) 65533;
        }
        return c;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public final CharSubSequence subSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
        return this.base.baseSubSequence(this.startOffset + i, this.startOffset + i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final CharSubSequence baseSubSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, this.baseChars.length);
        return (i == this.startOffset && i2 == this.endOffset) ? this : this.base != this ? this.base.baseSubSequence(i, i2) : new CharSubSequence(this.base, i, i2);
    }

    public static CharSubSequence of(CharSequence charSequence) {
        return of(charSequence, 0, charSequence.length());
    }

    public static CharSubSequence of(CharSequence charSequence, int i) {
        if ($assertionsDisabled || i <= charSequence.length()) {
            return of(charSequence, i, charSequence.length());
        }
        throw new AssertionError();
    }

    @Deprecated
    public static CharSubSequence of(char[] cArr, int i, int i2) {
        if (!$assertionsDisabled && (i < 0 || i > i2 || i2 > cArr.length)) {
            throw new AssertionError();
        }
        char[] cArr2 = new char[cArr.length];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        return (i == 0 && i2 == cArr.length) ? new CharSubSequence(cArr2, 0) : new CharSubSequence(cArr2, 0).subSequence(i, i2);
    }

    @Deprecated
    public static CharSubSequence of(CharSequence charSequence, int i, int i2) {
        CharSubSequence charSubSequence;
        if (!$assertionsDisabled && (i < 0 || i > i2 || i2 > charSequence.length())) {
            throw new AssertionError();
        }
        if (charSequence instanceof CharSubSequence) {
            charSubSequence = (CharSubSequence) charSequence;
        } else if (charSequence instanceof String) {
            charSubSequence = new CharSubSequence(((String) charSequence).toCharArray(), ((String) charSequence).hashCode());
        } else if (charSequence instanceof StringBuilder) {
            char[] cArr = new char[charSequence.length()];
            ((StringBuilder) charSequence).getChars(0, charSequence.length(), cArr, 0);
            charSubSequence = new CharSubSequence(cArr, 0);
        } else {
            charSubSequence = new CharSubSequence(charSequence.toString().toCharArray(), 0);
        }
        if (i == 0 && i2 == charSequence.length()) {
            return charSubSequence;
        }
        return charSubSequence.subSequence(i, i2);
    }
}
