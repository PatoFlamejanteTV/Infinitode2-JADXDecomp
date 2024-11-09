package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/TriangularFloatDistribution.class */
public final class TriangularFloatDistribution extends FloatDistribution {
    private final float low;
    private final float high;
    private final float mode;

    public TriangularFloatDistribution(float f) {
        this(-f, f);
    }

    public TriangularFloatDistribution(float f, float f2) {
        this(f, f2, (f + f2) * 0.5f);
    }

    public TriangularFloatDistribution(float f, float f2, float f3) {
        this.low = f;
        this.high = f2;
        this.mode = f3;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final float nextFloat() {
        return ((-this.low) == this.high && this.mode == 0.0f) ? MathUtils.randomTriangular(this.high) : MathUtils.randomTriangular(this.low, this.high, this.mode);
    }

    public final float getLow() {
        return this.low;
    }

    public final float getHigh() {
        return this.high;
    }

    public final float getMode() {
        return this.mode;
    }
}
