package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/CharArrayComparator.class */
public final class CharArrayComparator extends DeepClassComparator<char[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<char[]> forClass() {
        return char[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(char[] cArr, char[] cArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (cArr.length != cArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(cArr.length).append(", ").append(cArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append(cArr[i]).append(" != ").append(cArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
