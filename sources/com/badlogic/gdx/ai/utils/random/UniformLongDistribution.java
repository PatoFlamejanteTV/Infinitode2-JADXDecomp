package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/UniformLongDistribution.class */
public final class UniformLongDistribution extends LongDistribution {
    private final long low;
    private final long high;

    public UniformLongDistribution(long j) {
        this(0L, j);
    }

    public UniformLongDistribution(long j, long j2) {
        this.low = j;
        this.high = j2;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final long nextLong() {
        return this.low + ((long) (MathUtils.random.nextDouble() * (this.high - this.low)));
    }

    public final long getLow() {
        return this.low;
    }

    public final long getHigh() {
        return this.high;
    }
}
