package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/IBasedSegmentBuilder.class */
public interface IBasedSegmentBuilder<S extends IBasedSegmentBuilder<S>> extends ISegmentBuilder<S> {
    BasedSequence getBaseSequence();

    String toStringWithRangesVisibleWhitespace();

    String toStringWithRanges();

    String toStringChars();
}
