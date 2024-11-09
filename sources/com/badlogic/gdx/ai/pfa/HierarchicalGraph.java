package com.badlogic.gdx.ai.pfa;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/HierarchicalGraph.class */
public interface HierarchicalGraph<N> extends Graph<N> {
    int getLevelCount();

    void setLevel(int i);

    N convertNodeBetweenLevels(int i, N n, int i2);
}
