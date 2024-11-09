package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/ConstantDoubleDistribution.class */
public final class ConstantDoubleDistribution extends DoubleDistribution {
    public static final ConstantDoubleDistribution NEGATIVE_ONE = new ConstantDoubleDistribution(-1.0d);
    public static final ConstantDoubleDistribution ZERO = new ConstantDoubleDistribution(0.0d);
    public static final ConstantDoubleDistribution ONE = new ConstantDoubleDistribution(1.0d);
    private final double value;

    public ConstantDoubleDistribution(double d) {
        this.value = d;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final double nextDouble() {
        return this.value;
    }

    public final double getValue() {
        return this.value;
    }
}
