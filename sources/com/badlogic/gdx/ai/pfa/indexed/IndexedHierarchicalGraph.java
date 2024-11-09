package com.badlogic.gdx.ai.pfa.indexed;

import com.badlogic.gdx.ai.pfa.HierarchicalGraph;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/indexed/IndexedHierarchicalGraph.class */
public abstract class IndexedHierarchicalGraph<N> implements HierarchicalGraph<N>, IndexedGraph<N> {
    protected int levelCount;
    protected int level = 0;

    @Override // com.badlogic.gdx.ai.pfa.HierarchicalGraph
    public abstract N convertNodeBetweenLevels(int i, N n, int i2);

    public IndexedHierarchicalGraph(int i) {
        this.levelCount = i;
    }

    @Override // com.badlogic.gdx.ai.pfa.HierarchicalGraph
    public int getLevelCount() {
        return this.levelCount;
    }

    @Override // com.badlogic.gdx.ai.pfa.HierarchicalGraph
    public void setLevel(int i) {
        this.level = i;
    }
}
