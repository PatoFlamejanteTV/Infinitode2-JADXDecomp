package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/IntegerDistribution.class */
public abstract class IntegerDistribution implements Distribution {
    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public long nextLong() {
        return nextInt();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public float nextFloat() {
        return nextInt();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public double nextDouble() {
        return nextInt();
    }
}
