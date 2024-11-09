package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.RichSequenceBuilder;
import com.vladsch.flexmark.util.sequence.mappers.CharMapper;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/MappedRichSequence.class */
public class MappedRichSequence extends IRichSequenceBase<RichSequence> implements MappedSequence<RichSequence>, RichSequence {
    private final CharMapper mapper;
    private final RichSequence base;

    private MappedRichSequence(CharMapper charMapper, RichSequence richSequence) {
        super(0);
        this.base = richSequence;
        this.mapper = charMapper;
    }

    @Override // com.vladsch.flexmark.util.sequence.MappedSequence
    public CharMapper getCharMapper() {
        return this.mapper;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.MappedSequence
    public RichSequence getCharSequence() {
        return this.base;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.mapper.map(this.base.charAt(i));
    }

    public RichSequence getBaseSequence() {
        return this.base;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.base.length();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence[] emptyArray() {
        return this.base.emptyArray();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence nullSequence() {
        return this.base.nullSequence();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence sequenceOf(CharSequence charSequence, int i, int i2) {
        if (charSequence instanceof MappedRichSequence) {
            return (i == 0 && i2 == charSequence.length()) ? (RichSequence) charSequence : ((RichSequence) charSequence).subSequence(i, i2).toMapped(this.mapper);
        }
        return new MappedRichSequence(this.mapper, this.base.sequenceOf(charSequence, i, i2));
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public <B extends ISequenceBuilder<B, RichSequence>> B getBuilder() {
        return RichSequenceBuilder.emptyBuilder();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence toMapped(CharMapper charMapper) {
        return charMapper == CharMapper.IDENTITY ? this : new MappedRichSequence(this.mapper.andThen(charMapper), this.base);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public RichSequence subSequence(int i, int i2) {
        RichSequence subSequence = this.base.subSequence(i, i2);
        return subSequence == this.base ? this : new MappedRichSequence(this.mapper, subSequence);
    }

    public static RichSequence mappedOf(CharMapper charMapper, RichSequence richSequence) {
        return mappedOf(charMapper, richSequence, 0, richSequence.length());
    }

    public static RichSequence mappedOf(CharMapper charMapper, RichSequence richSequence, int i) {
        return mappedOf(charMapper, richSequence, i, richSequence.length());
    }

    public static RichSequence mappedOf(CharMapper charMapper, RichSequence richSequence, int i, int i2) {
        if (richSequence instanceof MappedRichSequence) {
            return (i == 0 && i2 == richSequence.length()) ? richSequence.toMapped(charMapper) : richSequence.subSequence(i, i2).toMapped(charMapper);
        }
        return new MappedRichSequence(charMapper, richSequence.subSequence(i, i2));
    }
}
