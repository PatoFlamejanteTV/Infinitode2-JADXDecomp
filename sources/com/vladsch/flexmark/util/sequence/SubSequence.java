package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/SubSequence.class */
public final class SubSequence extends BasedSequenceImpl {
    private final CharSequence charSequence;
    private final SubSequence baseSeq;
    private final int startOffset;
    private final int endOffset;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SubSequence.class.desiredAssertionStatus();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final SubSequence getBaseSequence() {
        return this.baseSeq;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final int getOptionFlags() {
        if (this.charSequence instanceof BasedOptionsHolder) {
            return ((BasedOptionsHolder) this.charSequence).getOptionFlags();
        }
        return 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean allOptions(int i) {
        return (this.charSequence instanceof BasedOptionsHolder) && ((BasedOptionsHolder) this.charSequence).allOptions(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean anyOptions(int i) {
        return (this.charSequence instanceof BasedOptionsHolder) && ((BasedOptionsHolder) this.charSequence).anyOptions(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final <T> T getOption(DataKeyBase<T> dataKeyBase) {
        return this.charSequence instanceof BasedOptionsHolder ? (T) ((BasedOptionsHolder) this.charSequence).getOption(dataKeyBase) : dataKeyBase.get(null);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final DataHolder getOptions() {
        if (this.charSequence instanceof BasedOptionsHolder) {
            return ((BasedOptionsHolder) this.charSequence).getOptions();
        }
        return null;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final CharSequence getBase() {
        return this.charSequence;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getStartOffset() {
        return this.startOffset;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getEndOffset() {
        return this.endOffset;
    }

    private SubSequence(CharSequence charSequence) {
        super(charSequence instanceof String ? charSequence.hashCode() : 0);
        if (!$assertionsDisabled && (charSequence instanceof BasedSequence)) {
            throw new AssertionError();
        }
        this.baseSeq = this;
        this.charSequence = charSequence;
        this.startOffset = 0;
        this.endOffset = charSequence.length();
    }

    private SubSequence(SubSequence subSequence, int i, int i2) {
        super(0);
        if (!$assertionsDisabled && (i < 0 || i2 < i || i2 > subSequence.length())) {
            throw new AssertionError(String.format("SubSequence must have startIndex >= 0 && endIndex >= startIndex && endIndex <= %d, got startIndex:%d, endIndex: %d", Integer.valueOf(subSequence.length()), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        this.baseSeq = subSequence;
        this.charSequence = subSequence.charSequence;
        this.startOffset = i;
        this.endOffset = i2;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder) {
        if (!$assertionsDisabled && iBasedSegmentBuilder.getBaseSequence() != this.baseSeq && !iBasedSegmentBuilder.getBaseSequence().equals(this.baseSeq)) {
            throw new AssertionError();
        }
        iBasedSegmentBuilder.append(this.startOffset, this.endOffset);
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
        char charAt = this.charSequence.charAt(i + this.startOffset);
        if (charAt == 0) {
            return (char) 65533;
        }
        return charAt;
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public final SubSequence subSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
        return baseSubSequence(this.startOffset + i, this.startOffset + i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final SubSequence baseSubSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, this.baseSeq.length());
        return (i == this.startOffset && i2 == this.endOffset) ? this : this.baseSeq != this ? this.baseSeq.baseSubSequence(i, i2) : new SubSequence(this, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BasedSequence create(CharSequence charSequence) {
        return charSequence == null ? BasedSequence.NULL : charSequence instanceof BasedSequence ? (BasedSequence) charSequence : new SubSequence(charSequence);
    }

    @Deprecated
    public static BasedSequence of(CharSequence charSequence) {
        return BasedSequence.of(charSequence);
    }

    @Deprecated
    public static BasedSequence of(CharSequence charSequence, int i) {
        return BasedSequence.of(charSequence).subSequence(i, charSequence == null ? 0 : charSequence.length());
    }

    @Deprecated
    public static BasedSequence of(CharSequence charSequence, int i, int i2) {
        return BasedSequence.of(charSequence).subSequence(i, i2);
    }
}
