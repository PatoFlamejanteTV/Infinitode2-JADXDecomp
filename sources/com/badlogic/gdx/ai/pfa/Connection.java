package com.badlogic.gdx.ai.pfa;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/Connection.class */
public interface Connection<N> {
    float getCost();

    N getFromNode();

    N getToNode();
}
