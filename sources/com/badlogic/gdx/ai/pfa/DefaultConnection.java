package com.badlogic.gdx.ai.pfa;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/DefaultConnection.class */
public class DefaultConnection<N> implements Connection<N> {
    protected N fromNode;
    protected N toNode;

    public DefaultConnection(N n, N n2) {
        this.fromNode = n;
        this.toNode = n2;
    }

    @Override // com.badlogic.gdx.ai.pfa.Connection
    public float getCost() {
        return 1.0f;
    }

    @Override // com.badlogic.gdx.ai.pfa.Connection
    public N getFromNode() {
        return this.fromNode;
    }

    @Override // com.badlogic.gdx.ai.pfa.Connection
    public N getToNode() {
        return this.toNode;
    }
}
