package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/Graph.class */
public interface Graph<N> {
    Array<Connection<N>> getConnections(N n);
}
