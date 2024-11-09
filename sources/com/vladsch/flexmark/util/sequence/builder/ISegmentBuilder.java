package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.builder.ISegmentBuilder;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/ISegmentBuilder.class */
public interface ISegmentBuilder<S extends ISegmentBuilder<S>> extends Iterable<Object> {
    public static final Options O_INCLUDE_ANCHORS = Options.INCLUDE_ANCHORS;
    public static final Options O_TRACK_FIRST256 = Options.TRACK_FIRST256;
    public static final int F_INCLUDE_ANCHORS = BitFieldSet.intMask(O_INCLUDE_ANCHORS);
    public static final int F_TRACK_FIRST256 = BitFieldSet.intMask(O_TRACK_FIRST256);
    public static final int F_DEFAULT = F_INCLUDE_ANCHORS | F_TRACK_FIRST256;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/ISegmentBuilder$Options.class */
    public enum Options {
        INCLUDE_ANCHORS,
        TRACK_FIRST256
    }

    int getOptions();

    boolean isIncludeAnchors();

    boolean isEmpty();

    boolean isBaseSubSequenceRange();

    Range getBaseSubSequenceRange();

    boolean haveOffsets();

    int getSpan();

    int getStartOffset();

    int getEndOffset();

    int size();

    CharSequence getText();

    int noAnchorsSize();

    int length();

    boolean isTrackTextFirst256();

    int getTextLength();

    int getTextSegments();

    int getTextSpaceLength();

    int getTextSpaceSegments();

    int getTextFirst256Length();

    int getTextFirst256Segments();

    @Override // java.lang.Iterable
    Iterator<Object> iterator();

    Iterable<Seg> getSegments();

    S append(int i, int i2);

    S append(CharSequence charSequence);

    S appendAnchor(int i);

    S append(Range range);

    String toStringWithRangesVisibleWhitespace(CharSequence charSequence);

    String toStringWithRanges(CharSequence charSequence);

    String toString(CharSequence charSequence);
}
