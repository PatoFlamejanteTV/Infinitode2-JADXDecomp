package com.vladsch.flexmark.util.misc;

import com.vladsch.flexmark.util.misc.Immutable;
import com.vladsch.flexmark.util.misc.Mutable;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/Mutable.class */
public interface Mutable<M extends Mutable<M, I>, I extends Immutable<I, M>> {
    I toImmutable();
}
