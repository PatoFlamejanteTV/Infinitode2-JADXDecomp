package com.vladsch.flexmark.util.misc;

import com.vladsch.flexmark.util.misc.Immutable;
import com.vladsch.flexmark.util.misc.Mutable;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/Immutable.class */
public interface Immutable<I extends Immutable<I, M>, M extends Mutable<M, I>> {
    M toMutable();
}
