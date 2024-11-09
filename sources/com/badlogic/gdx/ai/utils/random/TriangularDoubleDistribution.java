package com.badlogic.gdx.ai.utils.random;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/TriangularDoubleDistribution.class */
public final class TriangularDoubleDistribution extends DoubleDistribution {
    private final double low;
    private final double high;
    private final double mode;

    public TriangularDoubleDistribution(double d) {
        this(-d, d);
    }

    public TriangularDoubleDistribution(double d, double d2) {
        this(d, d2, (d + d2) * 0.5d);
    }

    public TriangularDoubleDistribution(double d, double d2, double d3) {
        this.low = d;
        this.high = d2;
        this.mode = d3;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final double nextDouble() {
        return ((-this.low) == this.high && this.mode == 0.0d) ? randomTriangular(this.high) : randomTriangular(this.low, this.high, this.mode);
    }

    public final double getLow() {
        return this.low;
    }

    public final double getHigh() {
        return this.high;
    }

    public final double getMode() {
        return this.mode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double randomTriangular(double d) {
        return (MathUtils.random.nextDouble() - MathUtils.random.nextDouble()) * d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double randomTriangular(double d, double d2, double d3) {
        double nextDouble = MathUtils.random.nextDouble();
        double d4 = d2 - d;
        return nextDouble <= (d3 - d) / d4 ? d + Math.sqrt(nextDouble * d4 * (d3 - d)) : d2 - Math.sqrt(((1.0d - nextDouble) * d4) * (d2 - d3));
    }
}
