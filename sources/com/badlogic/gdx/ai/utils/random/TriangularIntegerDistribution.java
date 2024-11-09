package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/TriangularIntegerDistribution.class */
public final class TriangularIntegerDistribution extends IntegerDistribution {
    private final int low;
    private final int high;
    private final float mode;

    public TriangularIntegerDistribution(int i) {
        this(-i, i);
    }

    public TriangularIntegerDistribution(int i, int i2) {
        this(i, i2, (i + i2) * 0.5f);
    }

    public TriangularIntegerDistribution(int i, int i2, float f) {
        this.low = i;
        this.high = i2;
        this.mode = f;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final int nextInt() {
        float randomTriangular;
        if ((-this.low) == this.high && this.mode == 0.0f) {
            randomTriangular = MathUtils.randomTriangular(this.high);
        } else {
            randomTriangular = MathUtils.randomTriangular(this.low, this.high, this.mode);
        }
        return Math.round(randomTriangular);
    }

    public final int getLow() {
        return this.low;
    }

    public final int getHigh() {
        return this.high;
    }

    public final float getMode() {
        return this.mode;
    }
}
