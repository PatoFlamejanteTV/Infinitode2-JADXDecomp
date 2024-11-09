package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/ShortArrayComparator.class */
public final class ShortArrayComparator extends DeepClassComparator<short[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<short[]> forClass() {
        return short[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(short[] sArr, short[] sArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (sArr.length != sArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(sArr.length).append(", ").append(sArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != sArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append((int) sArr[i]).append(" != ").append((int) sArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
