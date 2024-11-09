package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/ConstantIntegerDistribution.class */
public final class ConstantIntegerDistribution extends IntegerDistribution {
    public static final ConstantIntegerDistribution NEGATIVE_ONE = new ConstantIntegerDistribution(-1);
    public static final ConstantIntegerDistribution ZERO = new ConstantIntegerDistribution(0);
    public static final ConstantIntegerDistribution ONE = new ConstantIntegerDistribution(1);
    private final int value;

    public ConstantIntegerDistribution(int i) {
        this.value = i;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final int nextInt() {
        return this.value;
    }

    public final int getValue() {
        return this.value;
    }
}
