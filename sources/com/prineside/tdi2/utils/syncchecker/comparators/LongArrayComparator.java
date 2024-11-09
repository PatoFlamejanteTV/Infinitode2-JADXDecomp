package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/LongArrayComparator.class */
public final class LongArrayComparator extends DeepClassComparator<long[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<long[]> forClass() {
        return long[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(long[] jArr, long[] jArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (jArr.length != jArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(jArr.length).append(", ").append(jArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < jArr.length; i++) {
            if (jArr[i] != jArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append(jArr[i]).append(" != ").append(jArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
