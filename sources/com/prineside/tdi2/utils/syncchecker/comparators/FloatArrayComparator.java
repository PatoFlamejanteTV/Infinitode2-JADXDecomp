package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/FloatArrayComparator.class */
public final class FloatArrayComparator extends DeepClassComparator<float[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<float[]> forClass() {
        return float[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(float[] fArr, float[] fArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (fArr.length != fArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(fArr.length).append(", ").append(fArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < fArr.length; i++) {
            if (fArr[i] != fArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append(fArr[i]).append(" != ").append(fArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
