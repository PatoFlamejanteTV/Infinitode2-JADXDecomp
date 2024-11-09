package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/DoubleArrayComparator.class */
public final class DoubleArrayComparator extends DeepClassComparator<double[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<double[]> forClass() {
        return double[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(double[] dArr, double[] dArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (dArr.length != dArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(dArr.length).append(", ").append(dArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < dArr.length; i++) {
            if (dArr[i] != dArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append(dArr[i]).append(" != ").append(dArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
