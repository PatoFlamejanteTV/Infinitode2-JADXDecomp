package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/GaussianFloatDistribution.class */
public final class GaussianFloatDistribution extends FloatDistribution {
    public static final GaussianFloatDistribution STANDARD_NORMAL = new GaussianFloatDistribution(0.0f, 1.0f);
    private final float mean;
    private final float standardDeviation;

    public GaussianFloatDistribution(float f, float f2) {
        this.mean = f;
        this.standardDeviation = f2;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final float nextFloat() {
        return this.mean + (((float) MathUtils.random.nextGaussian()) * this.standardDeviation);
    }

    public final float getMean() {
        return this.mean;
    }

    public final float getStandardDeviation() {
        return this.standardDeviation;
    }
}
