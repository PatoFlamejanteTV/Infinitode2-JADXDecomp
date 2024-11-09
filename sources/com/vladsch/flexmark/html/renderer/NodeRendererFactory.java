package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.util.data.DataHolder;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/NodeRendererFactory.class */
public interface NodeRendererFactory extends Function<DataHolder, NodeRenderer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.function.Function
    NodeRenderer apply(DataHolder dataHolder);
}
