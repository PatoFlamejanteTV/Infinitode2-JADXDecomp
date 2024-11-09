package com.vladsch.flexmark.util.sequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/RichSequence.class */
public interface RichSequence extends IRichSequence<RichSequence> {
    public static final RichSequence NULL = RichSequenceImpl.create("", 0, 0);
    public static final RichSequence[] EMPTY_ARRAY = new RichSequence[0];

    static RichSequence of(CharSequence charSequence) {
        return RichSequenceImpl.create(charSequence, 0, charSequence.length());
    }

    static RichSequence of(CharSequence charSequence, int i) {
        return RichSequenceImpl.create(charSequence, i, charSequence.length());
    }

    static RichSequence of(CharSequence charSequence, int i, int i2) {
        return RichSequenceImpl.create(charSequence, i, i2);
    }

    static RichSequence ofSpaces(int i) {
        return of(RepeatedSequence.ofSpaces(i));
    }

    static RichSequence repeatOf(char c, int i) {
        return of(RepeatedSequence.repeatOf(String.valueOf(c), 0, i));
    }

    static RichSequence repeatOf(CharSequence charSequence, int i) {
        return of(RepeatedSequence.repeatOf(charSequence, 0, charSequence.length() * i));
    }

    static RichSequence repeatOf(CharSequence charSequence, int i, int i2) {
        return of(RepeatedSequence.repeatOf(charSequence, i, i2));
    }
}
