package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.parser.LinkRefProcessorFactory;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/LinkRefProcessorData.class */
public class LinkRefProcessorData {
    public final List<LinkRefProcessorFactory> processors;
    public final int maxNesting;
    public final int[] nestingIndex;

    public LinkRefProcessorData(List<LinkRefProcessorFactory> list, int i, int[] iArr) {
        this.processors = list;
        this.maxNesting = i;
        this.nestingIndex = iArr;
    }
}
