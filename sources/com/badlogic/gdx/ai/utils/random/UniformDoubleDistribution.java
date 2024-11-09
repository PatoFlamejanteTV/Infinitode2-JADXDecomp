package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/UniformDoubleDistribution.class */
public final class UniformDoubleDistribution extends DoubleDistribution {
    private final double low;
    private final double high;

    public UniformDoubleDistribution(double d) {
        this(0.0d, d);
    }

    public UniformDoubleDistribution(double d, double d2) {
        this.low = d;
        this.high = d2;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final double nextDouble() {
        return this.low + (MathUtils.random.nextDouble() * (this.high - this.low));
    }

    public final double getLow() {
        return this.low;
    }

    public final double getHigh() {
        return this.high;
    }
}
