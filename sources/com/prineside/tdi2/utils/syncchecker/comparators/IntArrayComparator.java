package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IntArrayComparator.class */
public final class IntArrayComparator extends DeepClassComparator<int[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<int[]> forClass() {
        return int[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(int[] iArr, int[] iArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (iArr.length != iArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(iArr.length).append(", ").append(iArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append(iArr[i]).append(" != ").append(iArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
