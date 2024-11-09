package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/TriangularLongDistribution.class */
public final class TriangularLongDistribution extends LongDistribution {
    private final long low;
    private final long high;
    private final double mode;

    public TriangularLongDistribution(long j) {
        this(-j, j);
    }

    public TriangularLongDistribution(long j, long j2) {
        this(j, j2, (j + j2) * 0.5d);
    }

    public TriangularLongDistribution(long j, long j2, double d) {
        this.low = j;
        this.high = j2;
        this.mode = d;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final long nextLong() {
        double randomTriangular;
        if ((-this.low) == this.high && this.mode == 0.0d) {
            randomTriangular = TriangularDoubleDistribution.randomTriangular(this.high);
        } else {
            randomTriangular = TriangularDoubleDistribution.randomTriangular(this.low, this.high, this.mode);
        }
        return Math.round(randomTriangular);
    }

    public final long getLow() {
        return this.low;
    }

    public final long getHigh() {
        return this.high;
    }

    public final double getMode() {
        return this.mode;
    }
}
