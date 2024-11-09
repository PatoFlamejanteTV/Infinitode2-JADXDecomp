package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/LongDistribution.class */
public abstract class LongDistribution implements Distribution {
    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public int nextInt() {
        return (int) nextLong();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public float nextFloat() {
        return (float) nextLong();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public double nextDouble() {
        return nextLong();
    }
}
