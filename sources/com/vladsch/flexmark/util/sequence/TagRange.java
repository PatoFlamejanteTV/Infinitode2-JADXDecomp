package com.vladsch.flexmark.util.sequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/TagRange.class */
public class TagRange extends Range {
    protected final String tag;

    public static TagRange of(CharSequence charSequence, int i, int i2) {
        return new TagRange(charSequence, i, i2);
    }

    public TagRange(CharSequence charSequence, Range range) {
        super(range);
        this.tag = String.valueOf(charSequence);
    }

    public TagRange(CharSequence charSequence, int i, int i2) {
        super(i, i2);
        this.tag = String.valueOf(charSequence);
    }

    public String getTag() {
        return this.tag;
    }

    public TagRange withTag(CharSequence charSequence) {
        return this.tag.equals(String.valueOf(charSequence)) ? this : new TagRange(charSequence, getStart(), getEnd());
    }

    @Override // com.vladsch.flexmark.util.sequence.Range
    public TagRange withStart(int i) {
        return i == getStart() ? this : new TagRange(getTag(), i, getEnd());
    }

    @Override // com.vladsch.flexmark.util.sequence.Range
    public TagRange withEnd(int i) {
        return i == getEnd() ? this : new TagRange(getTag(), getStart(), i);
    }

    @Override // com.vladsch.flexmark.util.sequence.Range
    public TagRange withRange(int i, int i2) {
        return (i == getStart() && i2 == getEnd()) ? this : new TagRange(getTag(), i, i2);
    }
}
