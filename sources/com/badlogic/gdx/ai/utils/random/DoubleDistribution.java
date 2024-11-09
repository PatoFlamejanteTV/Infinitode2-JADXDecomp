package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/DoubleDistribution.class */
public abstract class DoubleDistribution implements Distribution {
    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public int nextInt() {
        return (int) nextDouble();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public long nextLong() {
        return (long) nextDouble();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public float nextFloat() {
        return (float) nextDouble();
    }
}
