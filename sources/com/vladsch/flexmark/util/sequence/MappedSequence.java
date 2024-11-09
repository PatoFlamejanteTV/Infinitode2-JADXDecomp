package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.sequence.mappers.CharMapper;
import java.lang.CharSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/MappedSequence.class */
public interface MappedSequence<T extends CharSequence> extends CharSequence {
    CharMapper getCharMapper();

    T getCharSequence();
}
