package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/SmoothableGraphPath.class */
public interface SmoothableGraphPath<N, V extends Vector<V>> extends GraphPath<N> {
    V getNodePosition(int i);

    void swapNodes(int i, int i2);

    void truncatePath(int i);
}
