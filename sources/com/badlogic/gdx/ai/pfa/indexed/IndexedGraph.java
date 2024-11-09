package com.badlogic.gdx.ai.pfa.indexed;

import com.badlogic.gdx.ai.pfa.Graph;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/indexed/IndexedGraph.class */
public interface IndexedGraph<N> extends Graph<N> {
    int getIndex(N n);

    int getNodeCount();
}
