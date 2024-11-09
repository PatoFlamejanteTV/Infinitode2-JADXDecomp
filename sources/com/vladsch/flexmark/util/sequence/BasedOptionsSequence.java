package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedOptionsHolder;
import java.util.stream.IntStream;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedOptionsSequence.class */
public final class BasedOptionsSequence implements BasedOptionsHolder, CharSequence {
    private final CharSequence chars;
    private final int optionFlags;
    private final DataHolder options;

    private BasedOptionsSequence(CharSequence charSequence, int i, DataHolder dataHolder) {
        this.chars = charSequence;
        this.optionFlags = i & (((dataHolder == null || SEGMENTED_STATS.get(dataHolder) == null) ? F_COLLECT_SEGMENTED_STATS : 0) ^ (-1));
        this.options = dataHolder;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final int getOptionFlags() {
        return this.optionFlags;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean allOptions(int i) {
        return (this.optionFlags & i) == i;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final boolean anyOptions(int i) {
        return (this.optionFlags & i) != 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final <T> T getOption(DataKeyBase<T> dataKeyBase) {
        return dataKeyBase.get(this.options);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
    public final DataHolder getOptions() {
        return this.options;
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.chars.length();
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.chars.charAt(i);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i, int i2) {
        return this.chars.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.chars.toString();
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.chars.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.chars.codePoints();
    }

    public final boolean equals(Object obj) {
        return this.chars.equals(obj);
    }

    public final int hashCode() {
        return this.chars.hashCode();
    }

    public static BasedOptionsSequence of(CharSequence charSequence, BitFieldSet<BasedOptionsHolder.Options> bitFieldSet) {
        return new BasedOptionsSequence(charSequence, bitFieldSet.toInt(), null);
    }

    public static BasedOptionsSequence of(CharSequence charSequence, int i) {
        return new BasedOptionsSequence(charSequence, i, null);
    }

    public static BasedOptionsSequence of(CharSequence charSequence, BitFieldSet<BasedOptionsHolder.Options> bitFieldSet, DataHolder dataHolder) {
        return new BasedOptionsSequence(charSequence, bitFieldSet.toInt(), dataHolder);
    }

    public static BasedOptionsSequence of(CharSequence charSequence, int i, DataHolder dataHolder) {
        return new BasedOptionsSequence(charSequence, i, dataHolder);
    }
}
