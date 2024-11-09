package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.NodeContext;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/NodeContext.class */
public interface NodeContext<N, C extends NodeContext<N, C>> {
    C getSubContext();

    C getSubContext(DataHolder dataHolder);

    C getSubContext(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder);

    N getCurrentNode();

    DataHolder getOptions();
}
