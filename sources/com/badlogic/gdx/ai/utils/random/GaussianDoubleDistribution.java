package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/GaussianDoubleDistribution.class */
public final class GaussianDoubleDistribution extends DoubleDistribution {
    public static final GaussianDoubleDistribution STANDARD_NORMAL = new GaussianDoubleDistribution(0.0d, 1.0d);
    private final double mean;
    private final double standardDeviation;

    public GaussianDoubleDistribution(double d, double d2) {
        this.mean = d;
        this.standardDeviation = d2;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final double nextDouble() {
        return this.mean + (MathUtils.random.nextGaussian() * this.standardDeviation);
    }

    public final double getMean() {
        return this.mean;
    }

    public final double getStandardDeviation() {
        return this.standardDeviation;
    }
}
