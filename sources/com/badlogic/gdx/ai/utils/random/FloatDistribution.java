package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/FloatDistribution.class */
public abstract class FloatDistribution implements Distribution {
    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public int nextInt() {
        return (int) nextFloat();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public long nextLong() {
        return nextFloat();
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public double nextDouble() {
        return nextFloat();
    }
}
