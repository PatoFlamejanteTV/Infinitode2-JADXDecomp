package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/UniformIntegerDistribution.class */
public final class UniformIntegerDistribution extends IntegerDistribution {
    private final int low;
    private final int high;

    public UniformIntegerDistribution(int i) {
        this(0, i);
    }

    public UniformIntegerDistribution(int i, int i2) {
        this.low = i;
        this.high = i2;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final int nextInt() {
        return MathUtils.random(this.low, this.high);
    }

    public final int getLow() {
        return this.low;
    }

    public final int getHigh() {
        return this.high;
    }
}
