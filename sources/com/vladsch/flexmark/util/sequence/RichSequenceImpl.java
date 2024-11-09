package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.RichSequenceBuilder;
import com.vladsch.flexmark.util.sequence.mappers.CharMapper;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/RichSequenceImpl.class */
public class RichSequenceImpl extends IRichSequenceBase<RichSequence> implements RichSequence {
    final CharSequence charSequence;

    private RichSequenceImpl(CharSequence charSequence) {
        super(charSequence instanceof String ? charSequence.hashCode() : 0);
        this.charSequence = charSequence;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence[] emptyArray() {
        return EMPTY_ARRAY;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence nullSequence() {
        return NULL;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence sequenceOf(CharSequence charSequence, int i, int i2) {
        return of(charSequence, i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public <B extends ISequenceBuilder<B, RichSequence>> B getBuilder() {
        return RichSequenceBuilder.emptyBuilder();
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    public RichSequence subSequence(int i, int i2) {
        SequenceUtils.validateStartEnd(i, i2, length());
        return (i == 0 && i2 == this.charSequence.length()) ? this : create(this.charSequence, i, i2);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.charSequence.length();
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        char charAt = this.charSequence.charAt(i);
        if (charAt == 0) {
            return (char) 65533;
        }
        return charAt;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public RichSequence toMapped(CharMapper charMapper) {
        return MappedRichSequence.mappedOf(charMapper, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RichSequence create(CharSequence charSequence, int i, int i2) {
        if (charSequence instanceof RichSequence) {
            return ((RichSequence) charSequence).subSequence(i, i2);
        }
        if (charSequence != null) {
            return (i == 0 && i2 == charSequence.length()) ? new RichSequenceImpl(charSequence) : new RichSequenceImpl(charSequence.subSequence(i, i2));
        }
        return NULL;
    }

    @Deprecated
    public static RichSequence of(CharSequence charSequence) {
        return RichSequence.of(charSequence, 0, charSequence.length());
    }

    @Deprecated
    public static RichSequence of(CharSequence charSequence, int i) {
        return RichSequence.of(charSequence, i, charSequence.length());
    }

    @Deprecated
    public static RichSequence of(CharSequence charSequence, int i, int i2) {
        return RichSequence.of(charSequence, i, i2);
    }
}
