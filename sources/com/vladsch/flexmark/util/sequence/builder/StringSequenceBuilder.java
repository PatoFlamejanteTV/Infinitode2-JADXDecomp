package com.vladsch.flexmark.util.sequence.builder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/StringSequenceBuilder.class */
public final class StringSequenceBuilder implements ISequenceBuilder<StringSequenceBuilder, CharSequence> {
    private final StringBuilder segments;

    public static StringSequenceBuilder emptyBuilder() {
        return new StringSequenceBuilder();
    }

    private StringSequenceBuilder() {
        this.segments = new StringBuilder();
    }

    public StringSequenceBuilder(int i) {
        this.segments = new StringBuilder(i);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final StringSequenceBuilder getBuilder() {
        return new StringSequenceBuilder();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final char charAt(int i) {
        return this.segments.charAt(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public final StringSequenceBuilder append(CharSequence charSequence, int i, int i2) {
        if (charSequence != null && charSequence.length() > 0 && i < i2) {
            this.segments.append(charSequence, i, i2);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public final StringSequenceBuilder append(char c) {
        this.segments.append(c);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final StringSequenceBuilder append(char c, int i) {
        while (true) {
            int i2 = i;
            i--;
            if (i2 <= 0) {
                return this;
            }
            this.segments.append(c);
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final CharSequence getSingleBasedSequence() {
        return toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final CharSequence toSequence() {
        return this.segments;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final int length() {
        return this.segments.length();
    }

    public final String toString() {
        return this.segments.toString();
    }
}
