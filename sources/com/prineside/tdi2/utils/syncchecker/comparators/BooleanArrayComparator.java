package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/BooleanArrayComparator.class */
public final class BooleanArrayComparator extends DeepClassComparator<boolean[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<boolean[]> forClass() {
        return boolean[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(boolean[] zArr, boolean[] zArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (zArr.length != zArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(zArr.length).append(", ").append(zArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < zArr.length; i++) {
            if (zArr[i] != zArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append(zArr[i]).append(" != ").append(zArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
