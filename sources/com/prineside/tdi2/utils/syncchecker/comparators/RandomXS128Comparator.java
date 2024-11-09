package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.math.RandomXS128;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/RandomXS128Comparator.class */
public final class RandomXS128Comparator extends DeepClassComparator<RandomXS128> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<RandomXS128> forClass() {
        return RandomXS128.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(RandomXS128 randomXS128, RandomXS128 randomXS1282, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (randomXS128.getState(0) != randomXS1282.getState(0)) {
            deepClassComparisonConfig.appendPrefix().append(": seed 0 differ (").append(randomXS128.getState(0)).append(", ").append(randomXS1282.getState(0)).append(")\n");
        } else if (randomXS128.getState(1) != randomXS1282.getState(1)) {
            deepClassComparisonConfig.appendPrefix().append(": seed 1 differ (").append(randomXS128.getState(1)).append(", ").append(randomXS1282.getState(1)).append(")\n");
        }
    }
}
