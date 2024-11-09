package com.badlogic.gdx.ai.utils.random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/random/ConstantLongDistribution.class */
public final class ConstantLongDistribution extends LongDistribution {
    public static final ConstantLongDistribution NEGATIVE_ONE = new ConstantLongDistribution(-1);
    public static final ConstantLongDistribution ZERO = new ConstantLongDistribution(0);
    public static final ConstantLongDistribution ONE = new ConstantLongDistribution(1);
    private final long value;

    public ConstantLongDistribution(long j) {
        this.value = j;
    }

    @Override // com.badlogic.gdx.ai.utils.random.Distribution
    public final long nextLong() {
        return this.value;
    }

    public final long getValue() {
        return this.value;
    }
}
