package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/PathSmootherRequest.class */
public class PathSmootherRequest<N, V extends Vector<V>> {
    public boolean isNew = true;
    public int outputIndex;
    public int inputIndex;
    public SmoothableGraphPath<N, V> path;

    public void refresh(SmoothableGraphPath<N, V> smoothableGraphPath) {
        this.path = smoothableGraphPath;
        this.isNew = true;
    }
}
