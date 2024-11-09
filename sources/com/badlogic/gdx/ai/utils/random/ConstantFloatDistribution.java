package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/ConstantFloatDistribution.class */
public final class ConstantFloatDistribution extends FloatDistribution {
    public static final ConstantFloatDistribution NEGATIVE_ONE = new ConstantFloatDistribution(-1.0f);
    public static final ConstantFloatDistribution ZERO = new ConstantFloatDistribution(0.0f);
    public static final ConstantFloatDistribution ONE = new ConstantFloatDistribution(1.0f);
    public static final ConstantFloatDistribution ZERO_POINT_FIVE = new ConstantFloatDistribution(0.5f);
    private final float value;

    public ConstantFloatDistribution(float f) {
        this.value = f;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final float nextFloat() {
        return this.value;
    }

    public final float getValue() {
        return this.value;
    }
}
