package com.vladsch.flexmark.util.sequence.builder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/PlainSegmentBuilder.class */
public class PlainSegmentBuilder extends SegmentBuilderBase<PlainSegmentBuilder> {
    public PlainSegmentBuilder() {
    }

    public PlainSegmentBuilder(int i) {
        super(i);
    }

    public static PlainSegmentBuilder emptyBuilder() {
        return new PlainSegmentBuilder();
    }

    public static PlainSegmentBuilder emptyBuilder(int i) {
        return new PlainSegmentBuilder(i);
    }
}
