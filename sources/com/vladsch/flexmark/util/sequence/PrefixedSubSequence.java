package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/PrefixedSubSequence.class */
public final class PrefixedSubSequence extends BasedSequenceImpl implements ReplacedBasedSequence {
    private final CharSequence prefix;
    private final BasedSequence base;

    private PrefixedSubSequence(CharSequence charSequence, BasedSequence basedSequence, int i, int i2) {
        super(0);
        this.prefix = charSequence;
        this.base = basedSequence.subSequence(i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Object getBase() {
        return this.base.getBase();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence getBaseSequence() {
        return this.base.getBaseSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getStartOffset() {
        return this.base.getStartOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getEndOffset() {
        return this.base.getEndOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Range getSourceRange() {
        return this.base.getSourceRange();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence baseSubSequence(int i, int i2) {
        return this.base.baseSubSequence(i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final int getOptionFlags() {
        return getBaseSequence().getOptionFlags();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean allOptions(int i) {
        return getBaseSequence().allOptions(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean anyOptions(int i) {
        return getBaseSequence().anyOptions(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final <T> T getOption(DataKeyBase<T> dataKeyBase) {
        return (T) getBaseSequence().getOption(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final DataHolder getOptions() {
        return getBaseSequence().getOptions();
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.prefix.length() + this.base.length();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getIndexOffset(int i) {
        SequenceUtils.validateIndexInclusiveEnd(i, length());
        if (i < this.prefix.length()) {
            return -1;
        }
        return this.base.getIndexOffset(i - this.prefix.length());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder) {
        if (this.prefix.length() != 0) {
            iBasedSegmentBuilder.append(this.base.getStartOffset(), this.base.getStartOffset());
            iBasedSegmentBuilder.append(this.prefix.toString());
        }
        this.base.addSegments(iBasedSegmentBuilder);
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        SequenceUtils.validateIndex(i, length());
        int length = this.prefix.length();
        if (i < length) {
            return this.prefix.charAt(i);
        }
        return this.base.charAt(i - length);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public final BasedSequence subSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
        int length = this.prefix.length();
        if (i < length) {
            if (i2 <= length) {
                return new PrefixedSubSequence(this.prefix.subSequence(i, i2), this.base.subSequence(0, 0), 0, 0);
            }
            return new PrefixedSubSequence(this.prefix.subSequence(i, length), this.base, 0, i2 - length);
        }
        return this.base.subSequence(i - length, i2 - length);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequenceBase, java.lang.CharSequence
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.prefix);
        this.base.appendTo(sb);
        return sb.toString();
    }

    public static PrefixedSubSequence repeatOf(CharSequence charSequence, int i, BasedSequence basedSequence) {
        return prefixOf(RepeatedSequence.repeatOf(charSequence, i).toString(), basedSequence, 0, basedSequence.length());
    }

    public static PrefixedSubSequence repeatOf(char c, int i, BasedSequence basedSequence) {
        return prefixOf(RepeatedSequence.repeatOf(c, i), basedSequence, 0, basedSequence.length());
    }

    public static PrefixedSubSequence prefixOf(CharSequence charSequence, BasedSequence basedSequence) {
        return prefixOf(charSequence, basedSequence, 0, basedSequence.length());
    }

    public static PrefixedSubSequence prefixOf(CharSequence charSequence, BasedSequence basedSequence, int i) {
        return prefixOf(charSequence, basedSequence, i, basedSequence.length());
    }

    public static PrefixedSubSequence prefixOf(CharSequence charSequence, BasedSequence basedSequence, int i, int i2) {
        return new PrefixedSubSequence(charSequence, basedSequence, i, i2);
    }

    @Deprecated
    public static PrefixedSubSequence of(CharSequence charSequence, BasedSequence basedSequence) {
        return prefixOf(charSequence, basedSequence);
    }

    @Deprecated
    public static PrefixedSubSequence of(CharSequence charSequence, BasedSequence basedSequence, int i) {
        return prefixOf(charSequence, basedSequence, i);
    }

    @Deprecated
    public static PrefixedSubSequence of(CharSequence charSequence, BasedSequence basedSequence, int i, int i2) {
        return prefixOf(charSequence, basedSequence, i, i2);
    }
}
