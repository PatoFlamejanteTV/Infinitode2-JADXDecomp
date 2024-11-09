package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedOptionsHolder.class */
public interface BasedOptionsHolder {
    public static final int F_LIBRARY_OPTIONS = 65535;
    public static final int F_APPLICATION_OPTIONS = -65536;
    public static final Options O_COLLECT_SEGMENTED_STATS = Options.COLLECT_SEGMENTED_STATS;
    public static final Options O_COLLECT_FIRST256_STATS = Options.COLLECT_FIRST256_STATS;
    public static final Options O_NO_ANCHORS = Options.NO_ANCHORS;
    public static final Options O_FULL_SEGMENTED_SEQUENCES = Options.FULL_SEGMENTED_SEQUENCES;
    public static final Options O_TREE_SEGMENTED_SEQUENCES = Options.TREE_SEGMENTED_SEQUENCES;
    public static final int F_COLLECT_SEGMENTED_STATS = BitFieldSet.intMask(O_COLLECT_SEGMENTED_STATS);
    public static final int F_COLLECT_FIRST256_STATS = BitFieldSet.intMask(O_COLLECT_FIRST256_STATS);
    public static final int F_NO_ANCHORS = BitFieldSet.intMask(O_NO_ANCHORS);
    public static final int F_FULL_SEGMENTED_SEQUENCES = BitFieldSet.intMask(O_FULL_SEGMENTED_SEQUENCES);
    public static final int F_TREE_SEGMENTED_SEQUENCES = BitFieldSet.intMask(O_TREE_SEGMENTED_SEQUENCES);
    public static final NullableDataKey<SegmentedSequenceStats> SEGMENTED_STATS = new NullableDataKey<>("SEGMENTED_STATS", (SegmentedSequenceStats) null);

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedOptionsHolder$Options.class */
    public enum Options {
        COLLECT_SEGMENTED_STATS,
        COLLECT_FIRST256_STATS,
        NO_ANCHORS,
        FULL_SEGMENTED_SEQUENCES,
        TREE_SEGMENTED_SEQUENCES
    }

    int getOptionFlags();

    boolean allOptions(int i);

    boolean anyOptions(int i);

    <T> T getOption(DataKeyBase<T> dataKeyBase);

    DataHolder getOptions();

    static String optionsToString(int i) {
        return BitFieldSet.of(Options.class, i).toString();
    }
}
