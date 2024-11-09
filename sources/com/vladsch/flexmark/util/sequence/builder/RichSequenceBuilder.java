package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.sequence.RichSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/RichSequenceBuilder.class */
public final class RichSequenceBuilder implements ISequenceBuilder<RichSequenceBuilder, RichSequence> {
    private final StringBuilder segments;

    public static RichSequenceBuilder emptyBuilder() {
        return new RichSequenceBuilder();
    }

    private RichSequenceBuilder() {
        this.segments = new StringBuilder();
    }

    public RichSequenceBuilder(int i) {
        this.segments = new StringBuilder(i);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final RichSequenceBuilder getBuilder() {
        return new RichSequenceBuilder();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final char charAt(int i) {
        return this.segments.charAt(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public final RichSequenceBuilder append(CharSequence charSequence, int i, int i2) {
        if (charSequence != null && charSequence.length() > 0 && i < i2) {
            this.segments.append(charSequence, i, i2);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public final RichSequenceBuilder append(char c) {
        this.segments.append(c);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final RichSequenceBuilder append(char c, int i) {
        while (true) {
            int i2 = i;
            i--;
            if (i2 <= 0) {
                return this;
            }
            this.segments.append(c);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final RichSequence getSingleBasedSequence() {
        return toSequence();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final RichSequence toSequence() {
        return RichSequence.of(this.segments);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public final int length() {
        return this.segments.length();
    }

    public final String toString() {
        return this.segments.toString();
    }
}
