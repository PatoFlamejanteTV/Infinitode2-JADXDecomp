package com.badlogic.gdx.ai.pfa;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/GraphPath.class */
public interface GraphPath<N> extends Iterable<N> {
    int getCount();

    N get(int i);

    void add(N n);

    void clear();

    void reverse();
}
