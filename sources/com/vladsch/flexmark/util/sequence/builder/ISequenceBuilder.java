package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import java.lang.CharSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/ISequenceBuilder.class */
public interface ISequenceBuilder<T extends ISequenceBuilder<T, S>, S extends CharSequence> extends Appendable {
    S getSingleBasedSequence();

    T getBuilder();

    char charAt(int i);

    @Override // java.lang.Appendable
    T append(CharSequence charSequence, int i, int i2);

    @Override // java.lang.Appendable
    T append(char c);

    T append(char c, int i);

    S toSequence();

    int length();

    default T addAll(Iterable<? extends CharSequence> iterable) {
        return append(iterable);
    }

    default T append(Iterable<? extends CharSequence> iterable) {
        for (CharSequence charSequence : iterable) {
            append(charSequence, 0, charSequence.length());
        }
        return this;
    }

    default T add(CharSequence charSequence) {
        return append(charSequence);
    }

    @Override // java.lang.Appendable
    default T append(CharSequence charSequence) {
        if (charSequence != null) {
            return append(charSequence, 0, charSequence.length());
        }
        return this;
    }

    default T append(CharSequence charSequence, int i) {
        if (charSequence != null) {
            return append(charSequence, i, charSequence.length());
        }
        return this;
    }

    default boolean isEmpty() {
        return length() <= 0;
    }

    default boolean isNotEmpty() {
        return length() > 0;
    }
}
