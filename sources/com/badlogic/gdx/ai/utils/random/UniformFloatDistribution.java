package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/UniformFloatDistribution.class */
public final class UniformFloatDistribution extends FloatDistribution {
    private final float low;
    private final float high;

    public UniformFloatDistribution(float f) {
        this(0.0f, f);
    }

    public UniformFloatDistribution(float f, float f2) {
        this.low = f;
        this.high = f2;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final float nextFloat() {
        return MathUtils.random(this.low, this.high);
    }

    public final float getLow() {
        return this.low;
    }

    public final float getHigh() {
        return this.high;
    }
}
