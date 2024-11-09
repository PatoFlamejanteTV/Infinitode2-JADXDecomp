package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.mappers.CharMapper;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/MappedBasedSequence.class */
public final class MappedBasedSequence extends BasedSequenceImpl implements MappedSequence<BasedSequence>, ReplacedBasedSequence {
    private final CharMapper mapper;
    private final BasedSequence baseSeq;

    private MappedBasedSequence(BasedSequence basedSequence, CharMapper charMapper) {
        super(0);
        this.baseSeq = basedSequence;
        this.mapper = charMapper;
    }

    @Override // com.vladsch.flexmark.util.sequence.MappedSequence
    public final CharMapper getCharMapper() {
        return this.mapper;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.mapper.map(this.baseSeq.charAt(i));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.MappedSequence
    public final BasedSequence getCharSequence() {
        return this.baseSeq;
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.baseSeq.length();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.IRichSequence
    public final BasedSequence toMapped(CharMapper charMapper) {
        return charMapper == CharMapper.IDENTITY ? this : new MappedBasedSequence(this.baseSeq, this.mapper.andThen(charMapper));
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.IRichSequence
    public final BasedSequence sequenceOf(CharSequence charSequence, int i, int i2) {
        if (charSequence instanceof MappedBasedSequence) {
            return (i == 0 && i2 == charSequence.length()) ? (BasedSequence) charSequence : ((BasedSequence) charSequence).subSequence(i, i2).toMapped(this.mapper);
        }
        return new MappedBasedSequence(this.baseSeq.sequenceOf(charSequence, i, i2), this.mapper);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public final BasedSequence subSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
        if (i == 0 && i2 == this.baseSeq.length()) {
            return this;
        }
        return new MappedBasedSequence(this.baseSeq.subSequence(i, i2), this.mapper);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Object getBase() {
        return this.baseSeq.getBase();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence getBaseSequence() {
        return this.baseSeq.getBaseSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getStartOffset() {
        return this.baseSeq.getStartOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getEndOffset() {
        return this.baseSeq.getEndOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final int getIndexOffset(int i) {
        if (this.baseSeq.charAt(i) == charAt(i)) {
            return this.baseSeq.getIndexOffset(i);
        }
        return -1;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
    public final void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder) {
        BasedUtils.generateSegments(iBasedSegmentBuilder, this);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final Range getSourceRange() {
        return this.baseSeq.getSourceRange();
    }

    public static BasedSequence mappedOf(BasedSequence basedSequence, CharMapper charMapper) {
        return new MappedBasedSequence(basedSequence, charMapper);
    }
}
